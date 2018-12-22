package de.blanke.ba.cbr;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import de.blanke.ba.logik.Board;
import de.blanke.ba.mas.AgentenOperations;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;
import de.dfki.mycbr.core.ICaseBase;
import de.dfki.mycbr.core.Project;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.model.Concept;
import de.dfki.mycbr.core.model.IntegerDesc;
import de.dfki.mycbr.core.retrieval.Retrieval;
import de.dfki.mycbr.core.similarity.AmalgamationFct;
import de.dfki.mycbr.core.similarity.Similarity;
import de.dfki.mycbr.util.Pair;

/**
 * Diese Klasse stellt den Zugriff auf das System bereit.
 * @author Paul Blanke, 05.12.2018
 *
 */
public class CBRController {
	
	// CBR Einstellungen
	private CBR_Engine engine = new CBR_Engine();
	private Project project = engine.loadCBRProject();
	private String conceptName = "m�hle";
	private Concept concept = null;
	private ICaseBase casebase = null;
	private AgentenOperations operations = new AgentenOperations();
	private CBR_AdaptionProcess process = new CBR_AdaptionProcess();
	private List<Stein> resultSet = new ArrayList<>();
	private static final Logger logger = Logger.getLogger(CBRController.class);
	/**
	 * Diese Methode initalisiert die zentrale CBR Infrastruktur, die ben�tigt wird, und l�det alle F�lle.
	 */
	private void initCBRStrcuture() {
		PropertyConfigurator.configure(CBRController.class.getResource("log4j.info"));
		this.concept = project.getConceptByID(conceptName);
		try {
			this.concept = project.getConceptByID(conceptName);
			this.casebase = project.createDefaultCB("cb");
			Collection<Instance> instances = concept.getAllInstances();
			for(Instance ini:instances) {
				casebase.addCase(ini);
			}
			List<AmalgamationFct> alleFKt = concept.getAvailableAmalgamFcts();
			concept.setActiveAmalgamFct(alleFKt.get(0));
			
			logger.info("CBR System: Laden beendent: Es sind " + casebase.getCases().size()+  " vorhanden (Initalisierungsphase)");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Kontruktor
	public CBRController() {
		this.initCBRStrcuture();
	}

// Methoden 
	/**
	 * Diese Methode verarbeitet die Query an das System.
	 * @param board Eingabeparameter.
	 * @param spieler Eingabeparameter.
	 * @return Liste von Integers als Ausgabeparameter.
	 */
	public List<Stein> executeQuery(Board board, Spieler spieler) {
		// Werte die Daten aus:
		this.resultSet.clear();
		int spielphase = spieler.getSpielPhase();
		int anzahlDerEigenenSpielsteine = spieler.getAnzahlSteine();
		int m�hlen = spieler.getAnzahlM�hlen();
		int spielsteineR1 = 0;
		int	spielsteineR2 = 0;
		int spielsteineR3 = 0;
		List<Stein> dataToCheck = spieler.getPosiSteine();
		
		for(Stein stein:dataToCheck) {
			if(stein.getRing() == 0) {
				spielsteineR1++;
			} else if(stein.getRing() == 1) {
				spielsteineR2++;
			} else {
				spielsteineR3++;
			}
		}
		
		// Set up the retrieval & prepare the Query
		try {
			Retrieval retrieval = new Retrieval(concept, casebase);
			Instance query = retrieval.getQueryInstance();
			query.addAttribute("Spielphase", spielphase);
			query.addAttribute("Anzahl_d_eigenen_Spielsteine", anzahlDerEigenenSpielsteine);
			query.addAttribute("Spielsteine_Ring_1", spielsteineR1);
			query.addAttribute("Spielsteine_Ring_2", spielsteineR2);
			query.addAttribute("Spielsteine_Ring_3", spielsteineR3);
			query.addAttribute("M�hlen", m�hlen);
			retrieval.setRetrievalMethod(Retrieval.RetrievalMethod.RETRIEVE_SORTED );
			retrieval.start();
			logger.info("CBR Query execute with: " + spielphase + ", "+ anzahlDerEigenenSpielsteine + ", "
					+ spielsteineR1 + ", "+ spielsteineR2 + ", " + spielsteineR3 + ","+ m�hlen);
			// Result auswerten 
			List<Pair<Instance, Similarity>> result = retrieval.getResult();
			List<Instance> dataResult = new ArrayList<>();
			for(int i = 0; i < 5; i++) {
				dataResult.add(result.get(i).getFirst());
				logger.info("Fall: " + i +  " :" + result.get(i).getFirst().getName()
				+ "Sim: " + result.get(i).getSecond().getValue());
			}
			this.analyseQuery(spieler, board, dataResult);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	/**
	 * Diese Methode verarbeitet die Anfrage und passt sie entsprechend an.
	 * @param spieler der aktuelle Spieler
	 * @param board Spielsituation
	 * @param data Eingabeparameter
	 */
	private void analyseQuery(Spieler spieler, Board board, List<Instance> data) {
		// Ausleseparameter
		IntegerDesc spQuery = (IntegerDesc) concept.getAllAttributeDescs().get("Spielphase");
		IntegerDesc l�sungADesc = (IntegerDesc) concept.getAllAttributeDescs().get("L�sungfeld_Start");
		IntegerDesc l�sungBDesc = (IntegerDesc) concept.getAllAttributeDescs().get("L�sungsfeld_Ziel");
		// Zielparameter
		List<Instance> relevant = new ArrayList<>();
		int check = spieler.getSpielPhase();
		List<Integer> evaluateData = new ArrayList<>();
		// Preprocessing - Suche alle passenden F�lle �ber die richtige Spielphase
		for(Instance a: data) {
			int ergebnis = Integer.parseInt(a.getAttForDesc(spQuery).getValueAsString());
			if(ergebnis == check) {
				relevant.add(a);
			}
		}
		logger.info("Es wurden: " + relevant.size() + " relevante F�lle gefunden!");
		// Processing 1 a - Hole die konkreten Instanzen
		for(Instance r: relevant) {
			evaluateData.add(Integer.parseInt(r.getAttForDesc(l�sungADesc).getValueAsString()));
			evaluateData.add(Integer.parseInt(r.getAttForDesc(l�sungBDesc).getValueAsString()));
		}
		// Processing 1 b - Wandle auf Steine um 
		List<Stein> caculateSol = new ArrayList<>();
		for(Integer a: evaluateData) {
			caculateSol.add(operations.getSteineFuerCBRSystem(a));
		}
		for(int i = 0; i < caculateSol.size(); i++) {
			Stein eins = caculateSol.get(i);
			Stein zwei = caculateSol.get(i+1);
			if(process.evaluateSolution(spieler, board, eins, zwei)) {
				this.resultSet.add(eins);
				this.resultSet.add(zwei);
				break;
			}
		}
		
	}
}	

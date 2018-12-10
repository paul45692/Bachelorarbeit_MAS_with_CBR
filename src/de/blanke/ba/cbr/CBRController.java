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
	private CBR_AdaptionProcess adaption = new CBR_AdaptionProcess();
	private CBR_Learning_Process_Prototyp learning = new CBR_Learning_Process_Prototyp();
	private AgentenOperations operations = new AgentenOperations();
	private Instance learnProblem = null;
	
	// F�r die Fehlerbehandlung wird ein Logger eingesetzt. @log4j
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
	
	/**
	 * Diese Methode verarbeitet die Query an das System.
	 * @param board Eingabeparameter.
	 * @param spieler Eingabeparameter.
	 * @return Liste von Integers als Ausgabeparameter.
	 */
	public List<Integer> executeQuery(Board board, Spieler spieler) {
		// Werte die Daten aus:
		int spielphase = spieler.getSpielPhase();
		int anzahlDerEigenenSpielsteine = spieler.getAnzahlSteine();
		int m�hlen = spieler.getVorhandeneMuehlen().size();
		int spielsteineR1 = 0;
		int	spielsteineR2 = 0;
		int spielsteineR3 = 0;
		List<Stein> dataToCheck = spieler.getPosiSteine();
		List<Integer> resultSet = new ArrayList<>();
		
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
			this.learnProblem = query;
			query.addAttribute("Spielphase", spielphase);
			query.addAttribute("Anzahl_d_eigenen_Spielsteine", anzahlDerEigenenSpielsteine);
			query.addAttribute("Spielsteine_Ring_1", spielsteineR1);
			query.addAttribute("Spielsteine_Ring_2", spielsteineR2);
			query.addAttribute("Spielsteine_Ring_3", spielsteineR3);
			query.addAttribute("M�hlen", m�hlen);
			retrieval.start();
			logger.info("CBR Query execute with: " + spielphase + ", "+ anzahlDerEigenenSpielsteine + ", "
					+ spielsteineR1 + ", "+ spielsteineR2 + ", " + spielsteineR3 + ","+ m�hlen);
			resultSet = this.analyseResultQuery(retrieval.getResult(), board, spieler);
			List<Pair<Instance, Similarity>> result = retrieval.getResult();
			System.out.println("Ergebnis" + result.get(0).getFirst().getName());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultSet;
	}
	/**
	 * Diese Methode bereitet das ResultSet f�r die sp�tere Verarbeitung vor.
	 * @param result 
	 * @return die beiden extrahierenden Felder als R�ckgabeparameter.
	 */
	private List<Integer> analyseResultQuery(List<Pair<Instance, Similarity>> result, Board board, Spieler spieler) {
		logger.info("Check the results:" + result.get(0).getFirst().getName() + "�hnlichkeit:  "
				+ result.get(0).getSecond().getValue() );
		List<Integer> dataResult = new ArrayList<>();
		boolean l�sungAccept = false;
		IntegerDesc l�sungADesc = (IntegerDesc) concept.getAllAttributeDescs().get("L�sungfeld_Start");
		IntegerDesc l�sungBDesc = (IntegerDesc) concept.getAllAttributeDescs().get("L�sungsfeld_Ziel");
		Instance adaptEins = concept.getInstance(result.get(0).getFirst().getName());
		Instance selectForResult = null;
		
		// Durchlaufe die ersten 5 F�lle aus der R�ckgabe.
		while(!l�sungAccept) {
			// Durchlaufe alle 5 F�lle
			for(int i = 0; i < 5; i++) {
				Instance evaluateResult = concept.getInstance(result.get(i).getFirst().getName());
				if(evaluateResult != null) {
					int l�sungfeldA = Integer.parseInt(evaluateResult.getAttForDesc(l�sungADesc).getValueAsString());
					int l�sungfeldB = Integer.parseInt(evaluateResult.getAttForDesc(l�sungBDesc).getValueAsString());
					
					if(this.evaluateSolution(spieler, board, l�sungfeldA, l�sungfeldB)) {
						l�sungAccept = true;
						dataResult.add(l�sungfeldA);
						dataResult.add(l�sungfeldB);
						selectForResult = evaluateResult;
						break;
					} else if(i== 4) {
						l�sungAccept = true;
					}
				}
			}
			
			if(l�sungAccept) {
				break;
			}
		}
		if(!dataResult.isEmpty()) {
			// Versuche die L�sung zu adaptieren
			if(Integer.parseInt(adaptEins.getAttForDesc(l�sungADesc).getValueAsString()) != dataResult.get(0)) {
				casebase.addCase(adaption.provideNewCase(adaptEins, selectForResult, l�sungADesc, l�sungBDesc));
			}
		} else if(dataResult.isEmpty()) {
			System.out.println("Achtung: CBR System hat ein Problem erzeugt");
			logger.error("CBR-System: Es muss gelernt werden.");
			Instance gelernt = learning.letsTryToLearnANewCase(this.learnProblem, spieler, board, l�sungADesc, l�sungBDesc);
			if(gelernt != null) {
				System.out.println("CBR System: Ein neuer Fall wurde gelernt");
				logger.info("CBR-System: Ein neuer Fall wurde angelernt.");
				casebase.addCase(gelernt);
				dataResult.add(learning.learningParameterA);
				dataResult.add(learning.learningParameterB);
			}
			
		}

		// Check ob die Liste immer noch leer ist.
		
		return dataResult;
	}
	/**
	 * Pr�fe die L�sung f�r den Einsatz.
	 * @param spieler Eingabeparameter
	 * @param board Eingabeparameter
	 * @param startF Startfeld der neuen Lsg.
	 * @param zielF  Endfeld der neuen Lsg.
	 * @return ja / nein
	 */
	private boolean evaluateSolution(Spieler spieler, Board board, int startF, int zielF) {
		Stein start = operations.getSteineFuerCBRSystem(startF);
		Stein ziel = operations.getSteineFuerCBRSystem(zielF);
		return adaption.evaluateSolution(spieler, board, start, ziel);
	}
	
}

package de.blanke.ba.cbr;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import de.blanke.ba.logik.Board;
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
	
	// F�r die Fehlerbehandlung wird ein Logger eingesetzt. @log4j
	private static final Logger logger = Logger.getLogger(CBRController.class);
	
	
	

	/**
	 * Diese Methode initalisiert die zentrale CBR Infrastruktur, die ben�tigt wird, und l�det alle F�lle.
	 */
	private void initCBRStrcuture() {
		this.concept = project.getConceptByID(conceptName);
		try {
			this.concept = project.getConceptByID(conceptName);
			this.casebase = project.createDefaultCB("cb");
			Collection<Instance> instances = concept.getAllInstances();
			for(Instance ini:instances) {
				casebase.addCase(ini);
			}
			logger.info("CBR System: Laden beendent: Es sind " + casebase.getCases().size()+  " vorhanden (Initalisierungsphase");
			System.out.println("Laden beendent: Es sind " + casebase.getCases().size()+  " vorhanden (Initalisierungsphase");
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
	 * Diese Methode verarbeitet die Query an das System
	 * @param board
	 * @param spieler
	 * @return
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
		List<Stein> resultSet = new ArrayList<>();
		
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
			query.addAttribute("Anzahl der eigenen Spielsteine", anzahlDerEigenenSpielsteine);
			query.addAttribute("Spielsteine Ring 1", spielsteineR1);
			query.addAttribute("Spielsteine R2", spielsteineR2);
			query.addAttribute("Spielsteine R3", spielsteineR3);
			query.addAttribute("M�hlen", m�hlen);
			retrieval.start();
			logger.info("CBR Query execute with: " + spielphase + ", "+ anzahlDerEigenenSpielsteine + ", "
					+ spielsteineR1 + ", "+ spielsteineR2 + ", " + spielsteineR3 + ","+ m�hlen);
			this.analyseResultQuery(retrieval.getResult());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * Diese Methode bereitet das ResultSet f�r die sp�tere Verarbeitung vor.
	 * @param result
	 * @return
	 */
	private List<Integer> analyseResultQuery(List<Pair<Instance, Similarity>> result) {
		logger.info("Check the results:" + result.get(0).getFirst().getName() + "�hnlichkeit:  "
				+ result.get(0).getSecond().getValue() );
		List<Integer> dataResult = new ArrayList<>();
		// Hole eine Instance aus dem ResultSet
		Instance firstResult = concept.getInstance(result.get(0).getFirst().getName());
		IntegerDesc l�sungADesc = (IntegerDesc) concept.getAllAttributeDescs().get("L�sungsfeld A");
		IntegerDesc l�sungBDesc = (IntegerDesc) concept.getAllAttributeDescs().get("L�sungsfeld B");
		// Check for a error
		if(firstResult != null) {
			// extract the mapping fields from the result
			int l�sungfeldA = Integer.parseInt(firstResult.getAttForDesc(l�sungADesc).getValueAsString());
			int l�sungfeldB = Integer.parseInt(firstResult.getAttForDesc(l�sungBDesc).getValueAsString());
			dataResult.add(l�sungfeldA);
			dataResult.add(l�sungfeldB);
			
		} else {
			// �berdenken!
			System.out.println("Error-Log: Miss matches with no result @CBR Query!!");
			logger.info("Error-Log: Miss matches with no result @CBR Query!!");
			System.out.println("lets started the apdation process");
			Instance secondResult = concept.getInstance(result.get(1).getFirst().getName());
			Instance adapResult = adaption.provideNewCase(firstResult, secondResult, l�sungADesc, l�sungBDesc);
			if(adapResult != null) {
				casebase.addCase(adapResult);
			}
		}
		
		return dataResult;
	}
	
}

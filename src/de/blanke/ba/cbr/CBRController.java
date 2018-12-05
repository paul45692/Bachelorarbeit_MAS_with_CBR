package de.blanke.ba.cbr;

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

/**
 * Diese Klasse stellt den Zugriff auf das System bereit.
 * @author Paul Blanke, 05.12.2018
 *
 */
public class CBRController {
	
	// CBR Einstellungen
	private CBR_Engine engine = new CBR_Engine();
	private Project project = engine.loadCBRProject();
	private String conceptName = "mühle";
	private Concept concept = null;
	private ICaseBase casebase = null;
	
	// Zur Absicherung wird ein Logger eingesetzt
	private static final Logger logger = Logger.getLogger(CBRController.class);

	/**
	 * Diese Methode initalisiert die zentrale CBR Infrastruktur, die benötigt wird, und lädet alle Fälle.
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
	public List<Stein> executeQuery(Board board, Spieler spieler) {
		// Werte die Daten aus:
		int spielphase = spieler.getSpielPhase();
		int anzahlDerEigenenSpielsteine = spieler.getAnzahlSteine();
		int mühlen = spieler.getVorhandeneMuehlen().size();
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
		
		
		return null;
	}
	
}

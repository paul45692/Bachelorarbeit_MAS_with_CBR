package de.blanke.ba.cbr;

import java.util.List;

import de.blanke.ba.logik.Board;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;
import de.dfki.mycbr.core.Project;

/**
 * Diese Klasse stellt den Zugriff auf das System bereit
 * @author paul4
 *
 */
public class CBRController {
	
	
	private CBR_Engine engine = new CBR_Engine();
	private Project project = engine.loadCBRProject();

	
	/**
	 * Diese Methode verarbeitet die Query an das System
	 * @param board
	 * @param spieler
	 * @return
	 */
	public List<Stein> executeQuery(Board board, Spieler spieler) {
		return null;
	}
}

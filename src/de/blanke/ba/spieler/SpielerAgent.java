package de.blanke.ba.spieler;

import java.awt.Color;
import java.util.List;

import de.blanke.ba.logik.Board;
import de.blanke.ba.mas.ControllerAgent;
import de.blanke.ba.model.Spielstein;
/**
 * Diese Klasse erweitert den normalen Spieler um die AI Komponente und 
 * sorgt für die Anbindung an das MAS
 * @author Paul Blanke
 * 25.11.2018
 *
 */
public class SpielerAgent extends Spieler {

	private static final long serialVersionUID = 1L;
	/**
	 * Bestimmt ob der Spieler als RBS Agent auftritt oder als CBR Agent
	 */
	private boolean rbs;
	
	// Kontruktor
	public SpielerAgent(Color farbe,String name, boolean rbs) {
		super(farbe, name);
		this.rbs = rbs;
		// TODO Auto-generated constructor stub
	}
	/**
	 * Diese Methode stellt den Einstiegspunkt in das MAS bereit
	 * @param agent der ControllerAgent der für das MAS verantwortlich ist.
	 * @return
	 */
	public Spielstein calculateGameMove(ControllerAgent agent, Board board) {
		
			
			agent.leiteZugEin(board, rbs, this);
			while(agent.isAufgabeFertig()== false) {
				System.out.println("Warten auf eine fertige Aufgabe");
				if(agent.isAufgabeFertig()) {
					System.out.println("Aufgabe ist erfüllt");
					break;
				}
			}
			return null;
			
	}

}

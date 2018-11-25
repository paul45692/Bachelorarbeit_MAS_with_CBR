package de.blanke.ba.spieler;

import java.awt.Color;

import de.blanke.ba.logik.Board;
import de.blanke.ba.mas.ControllerAgent;
import de.blanke.ba.model.Spielstein;

public class SpielerAgent extends Spieler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean rbs;
	public SpielerAgent(Color farbe, boolean rbs) {
		super(farbe);
		this.rbs = rbs;
		// TODO Auto-generated constructor stub
	}
	/**
	 * Diese Methode stellt den Einstiegspunkt in das MAS bereit
	 * @param agent der ControllerAgent der für das MAS verantwortlich ist.
	 * @return
	 */
	public Spielstein calculateGameMove(ControllerAgent agent, Board board) {
		return null;
	}

}

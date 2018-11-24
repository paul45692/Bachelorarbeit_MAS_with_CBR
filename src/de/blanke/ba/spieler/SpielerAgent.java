package de.blanke.ba.spieler;

import java.awt.Color;

import de.blanke.ba.mas.ControllerAgent;
import de.blanke.ba.model.Spielstein;

public class SpielerAgent extends Spieler {

	public SpielerAgent(Color farbe) {
		super(farbe);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Diese Methode stellt den Einstiegspunkt in das MAS bereit
	 * @param agent der ControllerAgent der für das MAS verantwortlich ist.
	 * @return
	 */
	public Spielstein calculateGameMove(ControllerAgent agent) {
		return null;
	}

}

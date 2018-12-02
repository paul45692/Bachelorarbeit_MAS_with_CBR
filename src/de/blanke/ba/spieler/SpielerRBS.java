package de.blanke.ba.spieler;

import java.awt.Color;

import de.blanke.ba.rbs.RegelInterpreter;

public class SpielerRBS extends Spieler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RegelInterpreter interpreter;
	

	public SpielerRBS(Color farbe, String name) {
		super(farbe, name);
		this.interpreter = new RegelInterpreter();
	}

}

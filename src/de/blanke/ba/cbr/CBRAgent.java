package de.blanke.ba.cbr;

import jade.core.Agent;

public class CBRAgent extends Agent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {
		System.out.print("Ich bin der zweite Agent");
		super.setup();
	}
}

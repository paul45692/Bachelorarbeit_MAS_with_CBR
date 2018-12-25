package de.blanke.ba.mas;

import jade.core.Agent;
/**
 * Diese Klasse stellt den Controller Agent bereit.
 * @author Paul Blanke.
 *
 */
public class ControllerAgent extends Agent {
	private static final long serialVersionUID = 1L;
	// Agenten Methoden
	@Override
	protected void setup() {
		System.out.println("Controller Agent aktiv");
		super.setup();
	}
	@Override
	protected void takeDown() {
		// TODO Auto-generated method stub
		super.takeDown();
	}
}	

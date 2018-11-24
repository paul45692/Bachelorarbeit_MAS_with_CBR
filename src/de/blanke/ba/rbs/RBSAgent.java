package de.blanke.ba.rbs;

import jade.core.Agent;
/**
 * Diese Klasse stellt die Kontrolle über das RBS bereit
 * @author paul4
 *
 */
public class RBSAgent extends Agent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {
		System.out.println("Ich bin der erste Agent!");
		super.setup();
	}

	@Override
	protected void takeDown() {
		// TODO Auto-generated method stub
		super.takeDown();
	}
	
}

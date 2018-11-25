package de.blanke.ba.rbs;

import java.io.IOException;
import java.util.List;
import de.blanke.ba.logik.Board;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

/**
 * Dieser Agent verwaltet das RBS System. Seine einzige Aufgabe ist es, 
 * auf den Nachrichtenempfang zu warten.
 * @author Paul Blanke
 * 25.11.18
 *
 */
public class RBSAgent extends Agent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Eine Instanz der Klasse @RegelInterpreter
	 */
	private RegelInterpreter interpreter = new RegelInterpreter();
	
	@Override
	protected void setup() {
		System.out.println("RBS System aktiv");
		super.setup();
		/**
		 * Diese Methode stellt die Logik des Agenten bereit.
		 */
		addBehaviour(new CyclicBehaviour() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				/**
				 * Warte auf Nachrichtenempfang
				 */
				ACLMessage msg = receive();
				if(msg != null) {
					/**
					 * Werte die Nachricht und sende eine Nachricht zurück.
					 */
					List<Stein> rueckgabe = null;
					try {
						// Hole den Zwischenstand aus der Nachricht
						Board board = (Board) msg.getContentObject();
						Spieler spieler = (Spieler) msg.getContentObject();
						rueckgabe = interpreter.sendQuery(board, spieler);
					} catch (UnreadableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Beginne R&uecksendung!
					msg = new ACLMessage(ACLMessage.INFORM);
					msg.addReceiver(new AID("Controller Agent", AID.ISLOCALNAME));
					msg.setContent("Zug beendet");
					Stein eins = rueckgabe.get(0);
					Stein zwei = null;
					if(rueckgabe.get(1) != null) {
						 zwei = rueckgabe.get(1);
					}
					try {
						msg.setContentObject(eins);
						msg.setContentObject(zwei);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					send(msg);
					msg = receive();
					
					
				} else {
					block();
				}
				
			}
			
		});
	}

	@Override
	protected void takeDown() {
		// TODO Auto-generated method stub
		super.takeDown();
	}
	
}

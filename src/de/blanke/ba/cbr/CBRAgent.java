package de.blanke.ba.cbr;

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
 * Dieser Agent verwaltet das CBR System.
 * Siehe auch @RBSAgent
 * @author paul4
 *
 */
public class CBRAgent extends Agent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CBRController interpreter = new CBRController();

	@Override
	protected void setup() {
		System.out.print("CBR System aktiv!");
		super.setup();
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
						rueckgabe = interpreter.executeQuery(board, spieler);
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
}

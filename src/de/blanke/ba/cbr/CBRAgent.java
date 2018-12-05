package de.blanke.ba.cbr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import de.blanke.ba.logik.Board;
import de.blanke.ba.mas.AgentenOperations;
import de.blanke.ba.mas.MessageBox;
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
	// CBR Controller
	// private CBRController interpreter = new CBRController();
	private CBRController interpreter = null;
	/**
	 * Für das Entkopplung der Logik vom Agenten wird an dieser Stelle
	 * eine Instanz @AgentenOperations gebraucht.
	 */
	private AgentenOperations operations = new AgentenOperations();

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
				MessageBox box = null;
				if(msg != null) {
					/**
					 * Werte die Nachricht und sende eine Nachricht zurück.
					 */
					List<Stein> rueckgabe = new ArrayList<>();
					List<Integer> extractData = new ArrayList<>();
					try {
						box = (MessageBox) msg.getContentObject();
						// Hole mir die Daten raus
						
					} catch (UnreadableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Board board = box.getBoard();
					Spieler spieler = box.getSpieler();
					// Fehlerhandling
					if(board != null && spieler != null) {
						extractData = interpreter.executeQuery(board, spieler);
						for(Integer i: extractData) {
							rueckgabe.add(operations.getSteineFuerCBRSystem(i));
						}
					} else {
						System.out.println(" Übertragungsproblem !!");
					}
					
					
					// ---
					
					
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

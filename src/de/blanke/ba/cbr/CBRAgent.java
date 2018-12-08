package de.blanke.ba.cbr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import de.blanke.ba.logik.Board;
import de.blanke.ba.mas.AgentenOperations;
import de.blanke.ba.mas.MessageBox;
import de.blanke.ba.mas.MessageBoxSteine;
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
	private static final Logger logger = Logger.getLogger(CBRAgent.class);
	

	@Override
	protected void setup() {
		System.out.print("CBR System aktiv!");
		PropertyConfigurator.configure(CBRAgent.class.getResource("log4j.info"));
		logger.info(" Das CBR System wurde aktiviert (@" + CBRAgent.class);
		super.setup();
		addBehaviour(new CyclicBehaviour() {
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
						System.out.println("Test: Integrationspunkt 1");
						logger.info("CBR-System: Eine Nachricht ist eingetroffen!");
						
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
						System.out.println("Error-AgentenEbene: Übertragungsproblem !!");
						logger.error("CBR System: Übertragungsproblem");
					}
					System.out.println("Test: Integrationspunkt 2");
					logger.info("CBR-System: Eine Nachricht wurde erzeugt und wird versendet");
					box.cleartheBox();
					// Rücktransport
					 ACLMessage aclmsg = new ACLMessage(ACLMessage.INFORM);
					aclmsg.addReceiver(new AID("Controller Agent", AID.ISLOCALNAME));
					aclmsg.setContent("Zug beendet");
					Stein eins = rueckgabe.get(0);
					Stein zwei = null;
					MessageBoxSteine steine = null;
					
					if(rueckgabe.get(1) != null) {
						 zwei = rueckgabe.get(1);
						 steine = new MessageBoxSteine(eins, zwei);
					} else if(rueckgabe.isEmpty()) {
						System.out.print("Error");
						logger.error("CBR System: Übertragungsproblem");
						steine = new MessageBoxSteine(null, null);
					}
					try {
						msg.setContentObject(steine);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Test: Integrationspunkt 3");
					send(msg);
					System.out.println("Test: Integrationspunkt 4: versendet");
					logger.info("CBR-System: Aufgabe wurde abgeschlossen!");
					
					
				} else {
					block();
				}
				
			}
			
		});
	}
}

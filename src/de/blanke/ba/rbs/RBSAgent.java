package de.blanke.ba.rbs;

import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import de.blanke.ba.logik.Board;
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
 * Dieser Agent verwaltet das RBS System. Seine einzige Aufgabe ist es, 
 * auf den Nachrichtenempfang zu warten.
 * @author Paul Blanke
 * 08.12.2018.
 *
 */
public class RBSAgent extends Agent {
	private static final long serialVersionUID = 1L;
	/**
	 * Eine Instanz der Klasse @RegelInterpreter
	 */
	private RegelInterpreter interpreter = new RegelInterpreter();
	private static final Logger logger = Logger.getLogger(RBSAgent.class);
	
	@Override
	protected void setup() {
		System.out.println("RBS System aktiv");
		PropertyConfigurator.configure(RBSAgent.class.getResource("log4j.info"));
		logger.info(" Das RBS System wurde aktiviert (@" + RBSAgent.class);
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
					logger.info("RBS Agent: Eine Nachricht wurde empfangen!");
					List<Stein> rueckgabe = null;
					try {
						// Hole den Zwischenstand aus der Nachricht
						MessageBox box = (MessageBox) msg.getContentObject();
						Spieler spieler = box.getSpieler();
						Spieler spielerB = box.getSpielerB();
						Board board = box.getBoard();
						rueckgabe = interpreter.sendQuery(board, spieler, spielerB);
						
					} catch (UnreadableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Beginne R&uecksendung!
					msg = new ACLMessage(ACLMessage.INFORM);
					msg.addReceiver(new AID("Controller Agent", AID.ISLOCALNAME));
					msg.setContent("Zug beendet");
					Stein eins, zwei = null;
					if(!rueckgabe.isEmpty() & rueckgabe.size() >= 2) {
						eins = rueckgabe.get(0);
						zwei = rueckgabe.get(1);
					} else if(!rueckgabe.isEmpty() & rueckgabe.size() < 2) {
						eins = rueckgabe.get(0);
						zwei = null;
						logger.info("RBS Agent(Rückgabe): Nur ein Parameter!");
					}  else {
						eins = null;
						zwei = null;
						logger.info("RBS Agent(Rückgabe)-Error: keine Parameter!)");
					}
					
					MessageBoxSteine steine = new MessageBoxSteine(eins, zwei);
					
					try {
						msg.setContentObject(steine);
						// msg.setContentObject(zwei);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					send(msg);
					msg = receive();
					
					logger.info("RBS Agent: Die Nachricht wurde beantwortet!");
					
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

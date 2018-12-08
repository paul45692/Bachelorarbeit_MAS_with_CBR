package de.blanke.ba.mas;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import de.blanke.ba.cbr.CBRAgent;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Diese Klasse leitet den Spielzug des Agenten ein.
 * @author Paul Blanke
 *
 */
public class GameBehaviour  extends OneShotBehaviour{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GameBehaviour.class);
	
	private MessageBox box;
	private boolean rbsAmZug;
	private boolean finished;
	
	public boolean getFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public GameBehaviour(ControllerAgent agent, MessageBox box, boolean rbs) {
		super(agent);
		PropertyConfigurator.configure(GameBehaviour.class.getResource("log4j.info"));
		if(box != null) {
			this.box = box;
		} else {
			this.box = box;
			logger.info("Controller Agent (Hinweg) : Ein Problem ist aufgetreten");
		}
		
		this.rbsAmZug = rbs;
	}

	@Override
	public void action() {
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		if(rbsAmZug) {
			msg.addReceiver(new AID("RBS Agent", AID.ISLOCALNAME));
		} else {
			msg.addReceiver(new AID("CBR Agent", AID.ISLOCALNAME));
		}
		msg.setContent("Du bist am Zug");
		try {
			msg.setContentObject(box);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		this.myAgent.send(msg);
		logger.info("Controller Agent (Hinweg): Eine Nachricht wurde auf den Weg gebracht!");
		}
		
}


package de.blanke.ba.mas;

import java.io.IOException;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
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
		this.box = box;
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
		System.out.println("Ich wurde versandet");
		}
		
}


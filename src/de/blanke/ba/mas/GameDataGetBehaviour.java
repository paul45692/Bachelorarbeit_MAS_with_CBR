package de.blanke.ba.mas;

import de.blanke.ba.model.Spielstein;
import de.blanke.ba.model.Stein;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class GameDataGetBehaviour  extends OneShotBehaviour{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Spielstein spielstein; 
	private AgentenOperations operations = new AgentenOperations();
	
	public GameDataGetBehaviour(ControllerAgent agent) {
		super(agent);
	}
		
	@Override
	public void action() {
		ACLMessage empfang = this.myAgent.receive();
		System.out.println("Ich warte!");
		if(empfang != null) {
			try {
				MessageBoxSteine box = (MessageBoxSteine) empfang.getContentObject();
				
				System.out.println("Die Nachricht ist angekommen!");
				Stein stein = box.getEntferneStein();
				if(stein != null) {
					setSpielstein(operations.wandeleSteinzuSpielSteinUm(stein));
				}
				
				
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Ich bin fertig");
		} else {
			block();
		}
		
	}

	public Spielstein getSpielstein() {
		return spielstein;
	}

	public void setSpielstein(Spielstein spielstein) {
		this.spielstein = spielstein;
	}

}

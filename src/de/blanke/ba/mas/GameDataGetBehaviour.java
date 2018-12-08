package de.blanke.ba.mas;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import de.blanke.ba.model.Spielstein;
import de.blanke.ba.model.Stein;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
/**
 * Diese Klasse fügt eine Methode für den Empfang von Nachrichten hinzu.
 * @author Paul Blanke
 *
 */
public class GameDataGetBehaviour  extends OneShotBehaviour{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Spielstein spielstein; 
	private AgentenOperations operations = new AgentenOperations();
	private static final Logger logger = Logger.getLogger(GameDataGetBehaviour.class);
	
	public GameDataGetBehaviour(ControllerAgent agent) {
		super(agent);
		PropertyConfigurator.configure(GameDataGetBehaviour.class.getResource("log4j.info"));
	}
		
	@Override
	public void action() {
		ACLMessage empfang = this.myAgent.receive();
		
		if(empfang != null) {
			try {
				MessageBoxSteine box = (MessageBoxSteine) empfang.getContentObject();
				
				logger.info("Controller Agent (Auswertung): Eine Nachricht wurde angekommen.");
				Stein stein = box.getEntferneStein();
				if(stein != null) {
					setSpielstein(operations.wandeleSteinzuSpielSteinUm(stein));
				} else {
					logger.info("Controller Agent (Auswertung): Ein technisches Problem ist aufgetreten.");
				}
				
				
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("Controller Agent (Auswertung): Auswertung wurde abgeschlossen.");
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

package de.blanke.ba.spielStart;



import de.blanke.ba.cbr.CBRAgent;
import de.blanke.ba.gui.GameLoop;
import de.blanke.ba.mas.ControllerAgent;
import de.blanke.ba.rbs.RBSAgent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class TestClass {
	
	private static ControllerAgent conAgent = null;
	
	
	public static void main(String[] args) {
		// Set up MAS
		setUpMAS();	
				// Treffe Spieleinstellungen
			       	// Diese Einstellung setzt die KI auf mit true
		int agenten_anzahl = 0;       
		GameLoop loop = new GameLoop(conAgent, agenten_anzahl);
		loop.setVisible(true);
				
	}

	
	private static void setUpMAS() {
		Runtime runtime = Runtime.instance();
	       Profile profile = new ProfileImpl();
	       profile.setParameter(Profile.MAIN_HOST, "localhost");
	       // Für Gui auf true setzen
	       profile.setParameter(Profile.GUI, "false");
	       ContainerController containerController = runtime.createMainContainer(profile);
	       AgentController ac, ac1, ac2;
	       RBSAgent rbsAgent = new RBSAgent();
	       CBRAgent cbrAgent = new CBRAgent();
	       conAgent = new ControllerAgent();
	      
	       try {
	    	 ac = containerController.acceptNewAgent("CBR Agent", cbrAgent);
			ac1 = containerController.acceptNewAgent("RBS Agent",rbsAgent );
			ac2 = containerController.acceptNewAgent("Controller Agent", conAgent);
			ac1.start();
			ac.start();
			ac2.start();
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

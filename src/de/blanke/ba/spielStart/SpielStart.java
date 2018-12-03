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

public class SpielStart {
	
	
	
	
	public static void main(String[] args) {
		// Set up MAS
		// setUpMAS();	
				// Treffe Spieleinstellungen
			       	// Diese Einstellung setzt die KI auf mit true
		boolean masSpiel = true;      
		GameLoop loop = new GameLoop(masSpiel);
		loop.setVisible(true);
				
	}

	
	
}

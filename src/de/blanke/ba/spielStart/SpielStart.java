package de.blanke.ba.spielStart;

import de.blanke.ba.gui.GameLoop;
/**
 * Diese Klasse stellt den Spieleinstieg bereit.
 * @author paul4
 *
 */
public class SpielStart {
	public static void main(String[] args) { 
		/**
		 * Passt das Spielfeld an:
		 * 0 normaler Spieler gegen Spieler -Spiel
		 * 1 CBR Agent gegen RBS Agent
		 * 2 Spieler gegen RBS Agent 
		 */
		int configParameter = 1;
		GameLoop loop = new GameLoop(configParameter);
		loop.setVisible(true);	
	}

	
	
}

package de.blanke.ba.spielStart;

import de.blanke.ba.gui.GameLoop;
/**
 * Diese Klasse stellt den Spieleinstieg bereit.
 * Das Spielfeld, das aus dem res Ordner geladen wird, wurde von der
 * folgenden Quelle übernommen (Stand 30.12.3018) 
 * https://de.wikipedia.org/wiki/M%C3%BChle_(Spiel)#/media/File:Nine_Men%27s_Morris_board.svg
 * @author Paul Blanke.
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

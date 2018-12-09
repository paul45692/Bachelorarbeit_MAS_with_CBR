package de.blanke.ba.spielStart;

import de.blanke.ba.gui.GameLoop;
/**
 * Diese Klasse stellt den Spieleinstieg bereit.
 * @author paul4
 *
 */
public class SpielStart {
	public static void main(String[] args) {
		boolean masSpiel = true;      
		GameLoop loop = new GameLoop(masSpiel);
		loop.setVisible(true);	
	}

	
	
}

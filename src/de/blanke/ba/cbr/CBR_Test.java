package de.blanke.ba.cbr;

import java.awt.Color;
import java.util.List;
import de.blanke.ba.logik.Board;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;
/**
 * Diese Klasse testet das CBR System von Auﬂen ohne die GUI und die Agenten.
 * 
 * @author Paul Blanke
 *
 */
public class CBR_Test {
		public static void main(String[]args) {
			 CBRController control = new CBRController();
			 Spieler spielerA = new Spieler(Color.WHITE, "Spieler A");
			 Board board = new Board();
			 List<Stein> data = control.executeQuery(board, spielerA);
			 for(Stein x: data) {
				 System.out.println("Ergebnisse (besser): " + x.toString());
			 }
		}
}

package de.blanke.ba.cbr;

import java.awt.Color;
import java.util.List;
import de.blanke.ba.logik.Board;
import de.blanke.ba.spieler.Spieler;

public class CBR_Test {
	
		
		
		public static void main(String[]args) {
			 CBRController control = new CBRController();
			 Spieler spielerA = new Spieler(Color.WHITE, "Spieler A");
			 Board board = new Board();
			 List<Integer> data = control.executeQuery(board, spielerA);
			 System.out.println("Ergebnis:" + data.get(0));
			 
		}
	
		
	
}

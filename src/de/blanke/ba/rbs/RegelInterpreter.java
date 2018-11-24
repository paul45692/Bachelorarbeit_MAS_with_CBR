package de.blanke.ba.rbs;

import java.awt.Color;
import java.util.List;

import de.blanke.ba.logik.Board;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;

public class RegelInterpreter {
	
	private RegelSet data = new RegelSet();
	private List<RegelSpielPhase0> spielphase0 = data.getSpielphase0();
	
	public RegelInterpreter() {
		
	}
	/**
	 * Diese Methode verarbeitet eine Anfrage an das System.
	 * @param board
	 * @param spieler
	 * @return
	 */
	public Stein sendQuery(Board board, Spieler spieler) {
		this.setzeColor(spieler.getSpielFarbe());
		Stein stein = null;
		switch(spieler.getSpielPhase()) {
		
		case 0:    	analyseQuerySpielPhase0(board, spieler);
					break;
					
		case 1:     analyseQuerySpielPhase1(board, spieler);
					break;
					
		case 2:     analyseQuerySpielPhase2(board, spieler);
					break;
					
		case 3: 	analyseQuerySpielPhase3(board, spieler);
					break;
		default:    break;
		}
		return null;
	}
	
	
/**
 * Die folgenden Methoden analysieren die Anfrage und liefern eine passende Antwort.	
 * @param board
 * @param spieler
 * @return
 */
	
	
	
	
	private Stein analyseQuerySpielPhase0(Board board, Spieler spieler) {
		Stein stein = null;
		return stein;
	}
	
	private Stein analyseQuerySpielPhase1(Board board, Spieler spieler) {
		Stein stein = null;
		return stein;
	}
	private Stein analyseQuerySpielPhase2(Board board, Spieler spieler) {
		Stein stein = null;
		return stein;
	}
	private Stein analyseQuerySpielPhase3(Board board, Spieler spieler) {
		Stein stein = null;
		return stein;
	}

	
	private void setzeColor(Color color) {
		for(RegelSpielPhase0 regel: spielphase0) {
			regel.setColor(color);
		}
	}
}

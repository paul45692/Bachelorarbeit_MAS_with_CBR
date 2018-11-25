package de.blanke.ba.rbs;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.blanke.ba.logik.Board;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;

public class RegelInterpreter {
	
	private RegelSet data = new RegelSet();
	private List<RegelSpielPhase0> spielphase0 = data.getSpielphase0();
	private List<Stein> dataBack = new ArrayList<>();
	

	/**
	 * Diese Methode verarbeitet eine Anfrage an das System.
	 * @param board
	 * @param spieler
	 * @return
	 */
	public List<Stein> sendQuery(Board board, Spieler spieler) {
		this.setzeColor(spieler.getSpielFarbe());
		this.dataBack.clear();
		
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
		return this.dataBack;
	}
	
	
/**
 * Die folgenden Methoden analysieren die Anfrage und liefern eine passende Antwort.	
 * @param board
 * @param spieler
 * @return
 */
	
	
	
	
	private void analyseQuerySpielPhase0(Board board, Spieler spieler) {
		// Ein Schnittstellen problem
		
		for(RegelSpielPhase0 regel: spielphase0) {
			if(regel.getIfTeil().contains("Frei") && board.checkFeld(regel.getIfStein().convertToFeld())) {
				this.dataBack.add(regel.getElseStein());
				spielphase0.remove(regel);
				break;
			} else  if(regel.getIfTeil().contains("Belegt") && !board.checkFeld(regel.getIfStein().convertToFeld()) &&
						board.checkFeld(regel.getElseStein().convertToFeld())){
				this.dataBack.add(regel.getElseStein());
				spielphase0.remove(regel);
				break;
			} else if(regel.getIfTeil().contains("Zufall")) {
				// Solange wie nichts gefunden wurde, suche weiter:)
				while(!board.checkFeld(regel.getIfStein().convertToFeld())) {
					regel.erzeugeZufällig();
					System.out.println("Suche!");
					if(board.checkFeld(regel.getIfStein().convertToFeld())) {
						
						this.dataBack.add(regel.getElseStein());
						regel.erzeugeZufällig();
						break;
					}
				}
			}
			
		}
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

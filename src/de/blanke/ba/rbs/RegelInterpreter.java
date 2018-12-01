package de.blanke.ba.rbs;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.blanke.ba.logik.Board;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;
/**
 * Diese Klasse stellt die Regel bereit und wertet eine Anfrage an das System aus.
 * @author Paul Blanke
 *
 */
public class RegelInterpreter {
	// Regeln für die Spielphasen
	private RegelSet data = new RegelSet();
	private List<RegelSpielPhase0> spielphase0 = data.getSpielphase0();
	private List<RegelSpielPhase1u2> spielphase1 = data.getSpielphase1();
	private List<RegelSpielPhase1u2> spielphase2 = data.getSpielphase1();
	private List<RegelSpielPhase0> spielphase3 = data.getSpielphase3();
	// Rückgabe Liste von Steinen speziell für Spielphase 1 und 2.
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
 */
	private void analyseQuerySpielPhase0(Board board, Spieler spieler) {
		
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
	
	private void analyseQuerySpielPhase1(Board board, Spieler spieler) {
		List<Stein> spielData = spieler.getPosiSteine();
		
		for(RegelSpielPhase1u2 regel: spielphase1) {
			// Wenn der Spieler das Feld besetzt dann werte Regel weiter aus.
			if(spielData.contains(regel.getBesetztesFeld()) && board.checkFeld(regel.getBewegungsFeld().convertToFeld())) {
				dataBack.add(regel.getBesetztesFeld());
				dataBack.add(regel.getBewegungsFeld());
				break;
				
			} else if(regel.getBesetztesFeld() == null){
				// Wende später zufällige Regel an.
			}
			
		}
		
		
		
	}
	private void analyseQuerySpielPhase2(Board board, Spieler spieler) {
		List<Stein> spielData = spieler.getPosiSteine();
		
		for(RegelSpielPhase1u2 regel: spielphase2) {
			// Wenn der Spieler das Feld besetzt dann werte Regel weiter aus.
			if(spielData.contains(regel.getBesetztesFeld()) && board.checkFeld(regel.getBewegungsFeld().convertToFeld())) {
				dataBack.add(regel.getBesetztesFeld());
				dataBack.add(regel.getBewegungsFeld());
				break;
				
			} else if(regel.getBesetztesFeld() == null){
				// Wende später zufällige Regel an.
			}
			
		}
	}
	
	private void analyseQuerySpielPhase3(Board board, Spieler spieler) {
		for(RegelSpielPhase0 regel: spielphase3) {
			if(regel.getIfTeil().contains("Besetzt") && !board.checkFeld(regel.getIfStein().convertToFeld())) {
				this.dataBack.add(regel.getElseStein());
				break;
			}  else if(regel.getIfTeil().contains("zufall")) {
				// Solange wie nichts gefunden wurde, suche weiter:)
				while(board.checkFeld(regel.getIfStein().convertToFeld())) {
					regel.erzeugeZufällig();
					System.out.println("Suche!");
					if(!board.checkFeld(regel.getIfStein().convertToFeld())) {
						
						this.dataBack.add(regel.getElseStein());
						regel.erzeugeZufällig();
						break;
					}
				}
			}
			
		}
		
		
	}

	
	private void setzeColor(Color color) {
		for(RegelSpielPhase0 regel: spielphase0) {
			regel.setColor(color);
		}
	}
}

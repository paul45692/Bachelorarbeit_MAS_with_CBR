package de.blanke.ba.logik;

import java.util.ArrayList;
import java.util.List;

import de.blanke.ba.model.Feld;
import de.blanke.ba.model.Spielstein;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;

/**
 * Diese Klasse untersucht mögliche Züge
 * @author paul4
 * Die Klasse übernimmt die Züge auf der Logik-Ebene.
 */
public class SpielController {
	// Attribute
	private Board board;
	private boolean muehle;
	// Diese Klasse übernimmt das Erkennung von Mühlen.
	private MuehleDecting decting;
	
	// Getter und Setter
	/**
	 * Eine Instanz der Klasse @Schiedsrichter 
	 * die prüft wann das Spiel beendet wurde.
	 */
	private Converter helper = new Converter();
	private Feld feld = null;
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean isMuehle() {
		return muehle;
	}

	public void setMuehle(boolean muehle) {
		this.muehle = muehle;
	}
	
	
	// Kontruktor
	public SpielController() {
		this.board = new Board();
		this.decting = new MuehleDecting();
	}
	
	/**
	 * Erste Phase
	 * 
	 */
	public boolean setSpielStein(int x, int y, Spieler spieler, Spielstein steinGUI) {
	
		 Feld feld = helper.ermitteleFeld(x, y);
		// 1. Spielphase
		if(spieler.getAnzahlSteine() < 9 && spieler.getSpielPhase() == 0) {
			if(board.setzteStein(feld.getRingZahl(), feld.getxCord(), feld.getyCord(), spieler, steinGUI)) {
				
				spieler.setAnzahlSteine(spieler.getAnzahlSteine() + 1);
				spieler.setAnzahlSpielZüge(spieler.getAnzahlSpielZüge() + 1);
				Stein stein = new Stein(feld.getRingZahl(), feld.getxCord(), feld.getyCord(), spieler.getSpielFarbe());
				spieler.setzeSpielstein(stein);
				
				if(spieler.getAnzahlSteine() == 9) {
					spieler.setSpielPhase(1);
					System.out.print("Info: Der "  + spieler.getName() + "hat die erste Spielphase verlassen!");
				}
				return true;
				
			} else {
				System.out.println("Das Feld ist belegt");
				return false;
			}
			
		} else  if(spieler.getSpielPhase() == 1)	{
				// Teste auf Fehler beim Spielzug
				if(!this.testAufSpielEnde(spieler)) { 
					if(board.setzteStein(feld.getRingZahl(), feld.getxCord(), feld.getyCord(), spieler, steinGUI)
						&& (this.feld.checkObFeldNachbarnIst(board, feld))) {
				
						spieler.setAnzahlSpielZüge(spieler.getAnzahlSpielZüge() + 1);
						Stein stein = new Stein(feld.getRingZahl(), feld.getxCord(), feld.getyCord(), spieler.getSpielFarbe());
						spieler.setzeSpielstein(stein);
						
						if(spieler.getAnzahlSteine() == 3) {
							spieler.setSpielPhase(2);
						}
				
						return true;
				
					} else {
						System.out.println("Das Feld ist belegt");
				
						return false;
					}
				} else {
					return true;
				}
				
				
			
		} else if(spieler.getSpielPhase() == 2)  {
			if(!this.testAufSpielEnde(spieler)) {
				if(board.setzteStein(feld.getRingZahl(), feld.getxCord(), feld.getyCord(), spieler, steinGUI)) {
				
					spieler.setAnzahlSpielZüge(spieler.getAnzahlSpielZüge() + 1);
					Stein stein = new Stein(feld.getRingZahl(), feld.getxCord(), feld.getyCord(), spieler.getSpielFarbe());
					spieler.setzeSpielstein(stein);
					return true;
			
				} else {
					System.out.println("Spiellogik: Das Feld ist belegt");
					return false;
				}
			} else {
				return false;
			}
		} else  {
			System.out.print("Ein Fehler ist passiert");
			return false;
		}
		
		
	}
	
	
	/**
	 * Diese Methode entfernt jeden beliebigen Stein.
	 * @param x
	 * @param y
	 * @return
	 */
	public Spielstein entferneSteinVonFeld(int x, int y, Spieler spieler) {
		
		feld = helper.ermitteleFeld(x, y);
		Spielstein spielstein = board.entferneStein(feld);
		if(spieler.getSpielPhase() != 0) {
			spieler.setAnzahlSteine(spieler.getAnzahlSteine() -1);
		}
		
		spieler.removeStein(feld.getStein());
		
		return spielstein;
	}
	/**
	 * Diese Methode kontrolliert die Mühle Prüfung.
	 * @param spieler
	 */
	public boolean pruefeAufMuehle(Spieler spieler) {
		
		if(spieler.getAnzahlSteine() > 2) { 
			
			return decting.findeMühle(spieler);
			
		} else {
			
			return false;
		}
	}
	/**
	 * Diese Methode prüft auf ein mögliches Spielende!
	 * Sobald in der ersten Spielphase für einen Spieler keine Züge mehr möglich sind, wird
	 * das Spiel beendet.
	 * @return
	 */
	public boolean testAufSpielEnde(Spieler spieler) {
		boolean check = false;
		List<Feld> dataCheck = new ArrayList<>();
		for(Stein stein:spieler.getPosiSteine()) {
			Feld f = stein.convertToFeld();
			if(!f.allefreienNachbarn(board).isEmpty()) {
				dataCheck.add(f.allefreienNachbarn(board).get(0));
			}
		}
		
		if(dataCheck.isEmpty()) {
			check = true;
			System.out.println("Spiel Ende: Der Spieler:"+  spieler.getName() + "kann keine Züge mehr ausführen!");
		}
		return check;
	}
	
	public void testData() {
		System.out.println(board.getAnzahlFreiFelder());
	}
}

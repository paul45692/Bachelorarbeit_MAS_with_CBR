package de.blanke.ba.logik;

import java.util.ArrayList;
import java.util.List;

import de.blanke.ba.model.Feld;
import de.blanke.ba.model.Spielstein;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;

/**
 * Diese Klasse untersucht m�gliche Z�ge
 * @author paul4
 * Die Klasse �bernimmt die Z�ge auf der Logik-Ebene.
 */
public class SpielController {
	// Attribute
	private Board board;
	private boolean muehle;
	// Diese Klasse �bernimmt das Erkennung von M�hlen.
	private MuehleDecting decting;
	
	// Getter und Setter
	/**
	 * Eine Instanz der Klasse @Schiedsrichter 
	 * die pr�ft wann das Spiel beendet wurde.
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
				spieler.setAnzahlSpielZ�ge(spieler.getAnzahlSpielZ�ge() + 1);
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
				
						spieler.setAnzahlSpielZ�ge(spieler.getAnzahlSpielZ�ge() + 1);
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
				
					spieler.setAnzahlSpielZ�ge(spieler.getAnzahlSpielZ�ge() + 1);
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
	 * Diese Methode kontrolliert die M�hle Pr�fung.
	 * @param spieler
	 */
	public boolean pruefeAufMuehle(Spieler spieler) {
		
		if(spieler.getAnzahlSteine() > 2) { 
			
			return decting.findeM�hle(spieler);
			
		} else {
			
			return false;
		}
	}
	/**
	 * Diese Methode pr�ft auf ein m�gliches Spielende!
	 * Sobald in der ersten Spielphase f�r einen Spieler keine Z�ge mehr m�glich sind, wird
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
			System.out.println("Spiel Ende: Der Spieler:"+  spieler.getName() + "kann keine Z�ge mehr ausf�hren!");
		}
		return check;
	}
	
	public void testData() {
		System.out.println(board.getAnzahlFreiFelder());
	}
}

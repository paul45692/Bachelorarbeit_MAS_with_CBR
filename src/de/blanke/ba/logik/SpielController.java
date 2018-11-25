package de.blanke.ba.logik;


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
			System.out.println("Zug auf: " + feld.getRingZahl() + " , "
					+ feld.getxCord() + ", " + feld.getyCord());
			if(board.setzteStein(feld.getRingZahl(), feld.getxCord(), feld.getyCord(), spieler, steinGUI)) {
				
				spieler.setAnzahlSteine(spieler.getAnzahlSteine() + 1);
				spieler.setAnzahlSpielZ�ge(spieler.getAnzahlSpielZ�ge() + 1);
				Stein stein = new Stein(feld.getRingZahl(), feld.getxCord(), feld.getyCord(), spieler.getSpielFarbe());
				spieler.setzeSpielstein(stein);
				
				if(spieler.getAnzahlSteine() == 9) {
					spieler.setSpielPhase(1);
					System.out.print("Der Spieler hat die erste Spielphase verlassen!");
				}
				///this.pruefeAufMuehle(spieler);
				return true;
				
			} else {
				System.out.println("Das Feld ist belegt");
				return false;
			}
			
		} else  if(spieler.getSpielPhase() == 1)	{
			// betrachte die Nachbarn Fkt.
				System.out.println(this.feld.checkObFeldNachbarnIst(board, feld));
			
				if(board.setzteStein(feld.getRingZahl(), feld.getxCord(), feld.getyCord(), spieler, steinGUI)) {
				
					spieler.setAnzahlSpielZ�ge(spieler.getAnzahlSpielZ�ge() + 1);
					Stein stein = new Stein(feld.getRingZahl(), feld.getxCord(), feld.getyCord(), spieler.getSpielFarbe());
					spieler.setzeSpielstein(stein);
				
					
				return true;
				
			} else {
				System.out.println("Das Feld ist belegt");
				
				return false;
			}
			
			
			
		} else if(spieler.getSpielPhase() == 2)  {
			
			return false;
		} else  {
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
		spieler.setAnzahlSteine(spieler.getAnzahlSteine() -1);
		spieler.removeStein(feld.getStein());
		
		return spielstein;
	}
	
	public boolean setzeSpielZugPhaseZweiUm(List<Spielstein> data, Spieler spieler) {
		boolean rueckgabe = false;
		Spielstein dataEins = data.get(0);
		Spielstein dataZwei = data.get(1);
		Feld startFeld = helper.ermitteleFeld(dataEins.getX(), dataZwei.getY());
		Feld zielFeld = helper.ermitteleFeld(dataZwei.getX(), dataZwei.getY());
		List<Feld> nachbarn = startFeld.allefreienNachbarn(board);
		if(nachbarn.contains(zielFeld)) {
			System.out.println("Das passt zu den Regeln");
		} else  if(this.setSpielStein(dataZwei.getX(), dataZwei.getY(), spieler, dataZwei)) {
			rueckgabe = true;
		}
		
		
		return rueckgabe;
	}
	/**
	 * Diese Methode kontrolliert die M�hle Pr�fung.
	 * @param spieler
	 */
	public void pruefeAufMuehle(Spieler spieler) {
		
		if(spieler.getAnzahlSteine() > 2) {
			boolean ergebnis = decting.findeM�hle(spieler);
			System.out.println("M�hlen Fund: " + ergebnis);
		}
	}
	
	
	
	
}

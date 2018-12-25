package de.blanke.ba.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import de.blanke.ba.logik.Board;

/**
 * Diese Klasse repräsentiert ein Knoten auf dem Spielfeld 
 * in Form eines Feldes. Jedes belegte Feld enthält ein Instanz 
 * des Steines auf dem Feld.
 * @author Paul Blanke.
 *
 */
public class Feld implements Serializable {
// Attribute
	private static final long serialVersionUID = -93812416829409512L;
	private int ringZahl;
	private int xCord;
	private int yCord;
	private Stein stein;
	private List<Feld> nachbarn;
	private boolean belegt;
	
// Getter und Setter
	
	public int getRingZahl() {
		return ringZahl;
	}
	public void setRingZahl(int ringZahl) {
		this.ringZahl = ringZahl;
	}
	public int getxCord() {
		return xCord;
	}
	public void setxCord(int xCord) {
		this.xCord = xCord;
	}
	public int getyCord() {
		return yCord;
	}
	public boolean getBelegt() {
		return  belegt;
	}
	public void setyCord(int yCord) {
		this.yCord = yCord;
	}
	public Stein getStein() {
		return stein;
	}
	public void entferneStein() {
		this.belegt = false;
		this.stein = null;
	}
	public void setStein(Stein stein) {
		this.stein = stein;
		this.belegt = true;
	}
	
	public void setNachbarn(List<Feld> nachbarn) {
		this.nachbarn = nachbarn;
	}
	
	public List<Feld> getNachbarn() {
		return nachbarn;
	}
	
// Konstruktor
	public Feld(int ringZahl, int xCord, int yCord) {
		this.setRingZahl(ringZahl);
		this.setxCord(xCord);
		this.setyCord(yCord);
		this.belegt = false;
	}
// Methoden	
	/**
	 * Diese Methode untersucht bei einen bestimmten Spielstand die Nachbarn 
	 * des Eingabeparametern.
	 * @param board Spielsituation
	 */
	private void sucheAlleNachbarn(Board board) {
		// Felder mussen aus der Board Klasse geholt werden ?
		this.nachbarn  = new ArrayList<>();
		
		// Untersuche welche Punkte geadded werden sollen
		// Pruefe Ring.
		if(this.getRingZahl() == 0) {
			// Eckpunkte
			if(((this.xCord == 0) && (this.yCord == 0))) {
				// Nehme beide Nachbarn auf
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord + 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord +1, yCord));
				
			} else if(((this.xCord == 2) && (this.yCord == 0))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord + 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1, yCord));
				
			} else if(((this.xCord == 0) && (this.yCord == 2))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord - 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord + 1, yCord));
				
			} else if(((this.xCord == 2) && (this.yCord == 2))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord - 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1, yCord));
				
			} else if(((this.xCord == 1) && (this.yCord == 0))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord + 1 , yCord));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1 , yCord));
				nachbarn.add(board.gebeFeldZurueck(ringZahl + 1, xCord, yCord ));
				
			} else if(((this.xCord == 1) && (this.yCord == 2))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord + 1 , yCord));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1 , yCord));
				nachbarn.add(board.gebeFeldZurueck(ringZahl + 1, xCord, yCord));
				
			} else if(((this.xCord == 0) && (this.yCord == 1))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord + 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1 , yCord - 1));
				nachbarn.add(board.gebeFeldZurueck( ringZahl + 1, xCord, yCord ));
				
			} if(((this.xCord == 2) && (this.yCord == 1))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord + 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1 , yCord - 1));
				nachbarn.add(board.gebeFeldZurueck( ringZahl + 1, xCord, yCord ));
				
			} 
				
			// zweiter Ring
		} else if(this.getRingZahl() == 1) {
			
			if(((this.xCord == 0) && (this.yCord == 0))) {
				// Nehme beide Nachbarn auf
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord + 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord +1, yCord));
				
			} else if(((this.xCord == 2) && (this.yCord == 0))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord + 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1, yCord));
				
			} else if(((this.xCord == 0) && (this.yCord == 2))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord - 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord + 1, yCord));
				
			} else if(((this.xCord == 2) && (this.yCord == 2))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord - 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1, yCord));
				
			} else if(((this.xCord == 1) && (this.yCord == 0))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord + 1 , yCord));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1 , yCord));
				nachbarn.add(board.gebeFeldZurueck(ringZahl + 1, xCord, yCord));
				nachbarn.add(board.gebeFeldZurueck(ringZahl - 1, xCord, yCord));
				
			} else if(((this.xCord == 1) && (this.yCord == 2))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord + 1 , yCord));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1 , yCord));
				nachbarn.add(board.gebeFeldZurueck(ringZahl + 1, xCord, yCord));
				nachbarn.add(board.gebeFeldZurueck(ringZahl - 1, xCord, yCord));
				
			} else if(((this.xCord == 0) && (this.yCord == 1))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord + 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1 , yCord - 1));
				nachbarn.add(board.gebeFeldZurueck( ringZahl + 1, xCord, yCord ));
				nachbarn.add(board.gebeFeldZurueck(ringZahl - 1, xCord, yCord));
				
			} else if(((this.xCord == 2) && (this.yCord == 1))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord + 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1 , yCord - 1));
				nachbarn.add(board.gebeFeldZurueck( ringZahl+ 1, xCord, yCord ));
				nachbarn.add(board.gebeFeldZurueck(ringZahl - 1, xCord, yCord));
				
			} 
			
			
			// 3. Ring
		} else if(this.getRingZahl() == 2) {
			
			if(((this.xCord == 0) && (this.yCord == 0))) {
				// Nehme beide Nachbarn auf
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord + 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord +1, yCord));
				
			} else if(((this.xCord == 2) && (this.yCord == 0))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord + 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1, yCord));
				
			} else if(((this.xCord == 0) && (this.yCord == 2))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord - 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord + 1, yCord));
				
			} else if(((this.xCord == 2) && (this.yCord == 2))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord - 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1, yCord));
				
			} else if(((this.xCord == 1) && (this.yCord == 0))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord + 1 , yCord));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1 , yCord));
				nachbarn.add(board.gebeFeldZurueck(ringZahl - 1, xCord, yCord));
				
			} else if(((this.xCord == 1) && (this.yCord == 2))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord + 1 , yCord));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1 , yCord));
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl - 1, xCord, yCord));
				
			} else if(((this.xCord == 0) && (this.yCord == 1))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord + 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1 , yCord - 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl - 1, xCord, yCord));
				
			} if(((this.xCord == 2) && (this.yCord == 1))) {
				
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord , yCord + 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl, xCord - 1 , yCord - 1));
				nachbarn.add(board.gebeFeldZurueck(ringZahl - 1, xCord, yCord));
				
			} 
		
		}
		
		
	}
	
	/**
	 * Diese Methode gibt zu einem Feld alle freien Nachbarn an.
	 * @param board Spielsituation
	 * @return alle freien Nachbarnfelder.
	 */
	public List<Feld> allefreienNachbarn(Board board) {
		List<Feld> frei = new ArrayList<>();
		this.sucheAlleNachbarn(board);
		// Durchsuche alle Nachbarn, falls kein Stein vorhanden, füge den freien hinzu
		for(Feld f: nachbarn) {
			if(f.getStein() == null) {
				frei.add(f);
			}
		}
		return frei;
	}
	/**
	 * Diese Methode sucht alle Nachbarn der gleichen Farbe heraus.
	 * @param board Spielstand
	 * @param color Spielerfarbe
	 * @return passende Nachbarn.
	 */
	public List<Feld> alleGleichFarbenNachbarn(Board board, Color color) {
		sucheAlleNachbarn(board);
		List<Feld> data = new ArrayList<>();
		for(Feld feld: nachbarn) {
			if(feld.getStein().getFarbe() == color) {
				data.add(feld);
			}
		}
		return data;
	}
	
	public boolean checkObFeldNachbarnIst(Board board,Feld test) {
		boolean wert = false;
		this.allefreienNachbarn(board);
		for(Feld f: nachbarn) {
			if(f.getRingZahl() == test.getRingZahl() && f.getxCord() ==  test.getxCord() && f.getyCord() == test.getyCord()) {
				wert = true;
			}
		}
		return wert;
	}
	
	public Stein convertToStein() {
		return new Stein(this.getRingZahl(), this.getxCord(), this.getyCord(), null);
	}
	/**
	 *@Override Diese Methode überschreibt die Prüfung auf 
	 *	Gleichheit zweier Objekte.Hier wird auf Gleicheit über die
	 *  Position geprüft.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean rueckgabe = false;
		Feld feld = (Feld) obj;
		if(this.ringZahl == feld.getRingZahl() && this.xCord == feld.getxCord() 
				&& this.yCord == feld.getyCord()) {
			rueckgabe = true;
		}
		return rueckgabe;
	}
}

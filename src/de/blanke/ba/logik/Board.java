package de.blanke.ba.logik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import de.blanke.ba.model.Feld;
import de.blanke.ba.model.Spielstein;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;

/**
 * Diese Klasse enthaeltet die technischen Daten des Boards.
 * 
 * @author paul4
 * 0 Platz ist frei
 * 1 Schwarz
 * 2 weiss
 * 
 *
 */
public class Board implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Dieses Attribute stellt die Felder dar.
	 */
	private List<Feld> felder;
	private int anzahlFreiFelder = 24;
	
	public List<Feld> getFelder() {
		return felder;
	}

	public void setFelder(List<Feld> felder) {
		this.felder = felder;
	}
	
	

	public int getAnzahlFreiFelder() {
		return anzahlFreiFelder;
	}

	public void setAnzahlFreiFelder(int anzahlFreiFelder) {
		this.anzahlFreiFelder = anzahlFreiFelder;
	}

	public Board() {
		felder = new ArrayList<>();
		erzeugeFelder();
	}
	// Diese methode stellt das Feld bereit.
	private void erzeugeFelder() {
		int anzahlFelder = 0;
		
		for(int r = 0; r < 3; r++) {
			
			for(int x = 0; x < 3; x++) {
				
				for(int y = 0; y < 3; y++) {
					Feld feld = new Feld(r,x, y);
					felder.add(feld);
					anzahlFelder++;
					// Loesche unnötige Felder :)
					if(x== 1 && y== 1 && ( r== 0 || r == 1 || r == 2)) {
						felder.remove(feld);
						anzahlFelder--;
					} 
					
				
				}
			}
		}
		
		System.out.println(anzahlFelder);
	}	
		
		
		/**
		 * Diese Methode setzt ein Stein auf das Feld, falls @Feld getbelegt false ist.
		 * @param r
		 * @param x
		 * @param y
		 * @return war erfolgreich?
		 */
		public boolean setzteStein(int r, int x, int y, Spieler spieler, Spielstein spielstein) {
			Stein stein = new Stein(r,x,y, spieler.getSpielFarbe(), spielstein);
			boolean rueckgabe = false;
			// Durchläuft alle felder
			for(Feld f: felder) {
				
				if((f.getRingZahl() == r) && (f.getxCord() == x) && (f.getyCord() == y)) {
					
					if(!f.getBelegt()) {
						
						f.setStein(stein);
						System.out.println("Der Stein wurde auf :" + r + " ," + x +  "," + y + "platziert");
						anzahlFreiFelder --;
						rueckgabe = true;
						
					} else {
						
						System.out.println("Dieser Zug ist nicht erlaubt! - Logik-Fehler ist belegt");
					}
					
					break;
					
				}
				
			}
			return rueckgabe;
			
		}
		// Diese Methode ist analog zu oberen Methoden @setzteStein
		// Gibt nur ein Feld zurück.
		public Feld gebeFeldZurueck(int r, int x, int y) {
			Feld feld = null;
			for(Feld f: felder) {
				if((f.getRingZahl() == r) && (f.getxCord() == x) && (f.getyCord() == y)) {
					
					feld = f;
					
					break;
					
				}
			}
			return feld;
		}
		
		/**
		 * Diese Methode holt einen Stein vom Feld 
		 * @param feld
		 * @param spieler
		 * @return
		 */
		public boolean entferneStein(Feld feld, Spieler spieler) {
			
			
			if((feld.getStein().getFarbe() == spieler.getSpielFarbe()) && feld.getBelegt()) {
				felder.remove(feld);
				feld.entferneStein();
				felder.add(feld);
				return true;
				
			} else {
				
				return false;
			}
		}
		
		
		
		public Spielstein entferneStein(Feld feld) {
			Stein stein = null;
			Spielstein spielstein = null;
			for(Feld f: felder) {
				// Suche Feld heraus
				if((f.getRingZahl() == feld.getRingZahl()) && (f.getxCord() == feld.getxCord()) 
						&& (f.getyCord() == feld.getyCord()) ) {
					
					stein = f.getStein();
					f.entferneStein();
					System.out.println("Feld gefunden");
					break;
				}
			}
			
			if(stein != null) {
				spielstein = stein.getSpielstein();
			} else {
				System.out.println("Fehler auf der Logik Ebene");
			}
			
			return spielstein;
		}
		
		/**
		 * Diese Methode überprüft ein Feld ob es frei ist.
		 * @param feld
		 * @return
		 */
		public boolean checkFeld(Feld feld) {
			boolean check = false;
			
			for(Feld f: felder) {
				
				if((f.getRingZahl() == feld.getRingZahl()) && (f.getxCord() == feld.getxCord()) 
						&& (f.getyCord() == feld.getyCord()) ) {
					if(f.getBelegt() == false) {
						check = true;
					}
					break;
				}
			}
			return check;
		}
		
		
		
		
		
}


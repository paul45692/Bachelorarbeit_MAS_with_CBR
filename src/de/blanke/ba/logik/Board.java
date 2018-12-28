package de.blanke.ba.logik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import de.blanke.ba.model.Feld;
import de.blanke.ba.model.Spielstein;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;
/**
 * Diese Klasse präsentiert die Datenhaltung über eine Liste von @Feldern.
 * @author Paul Blanke, 23.12.2018.
 *
 */
public class Board implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Feld> felder;
	private int anzahlFreiFelder = 0;
// Getter und Setter	
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
	
// Methoden	
	/**
	 * Diese Methode initalisiert das Board mit 24 Feldern.
	 */
	private void erzeugeFelder() {
		for(int r = 0; r < 3; r++) {
			for(int x = 0; x < 3; x++) {
				for(int y = 0; y < 3; y++) {
					Feld feld = new Feld(r,x, y);
					felder.add(feld);
					this.anzahlFreiFelder++;
					// Loesche unnötige Felder :)
					if(x== 1 && y== 1 && ( r== 0 || r == 1 || r == 2)) {
						felder.remove(feld);
						this.anzahlFreiFelder--;
					} 
				}
			}
		}
	}	
		
/**
* Diese Methode setzt ein Stein auf das Feld, falls @Feld getbelegt false ist.
* @param r Koordinaten
* @param x "
* @param y "
* @return  Der Stein wurde gesetzt ja / Nein.
 */
	public boolean setzteStein(int r, int x, int y, Spieler spieler, Spielstein spielstein) {
		Stein stein = new Stein(r,x,y, spieler.getSpielFarbe(), spielstein);
		boolean rueckgabe = false;
			// Durchläuft alle felder
			for(Feld f: felder) {
				if((f.getRingZahl() == r) && (f.getxCord() == x) && (f.getyCord() == y)) {
					
					if(!f.getBelegt()) {
						f.setStein(stein);
						System.out.println("Der Stein wurde auf :" + r + " ," + x +  "," + y + " platziert");
						anzahlFreiFelder --;
						rueckgabe = true;
					} else {
						System.out.println("Das Feld ist belegt!");
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
		 * Diese Methode löscht einen Spielstein vom Feld herunter.
		 * @param feld Das Ziel, welches gelöscht werden soll.
		 * @return Der Spielstein auf dem Feld.
		 */
		public Spielstein entferneStein(Feld feld, Spieler spieler) {
			Stein stein = null;
			Spielstein spielstein = null;
			for(Feld f: felder) {
				// Suche Feld heraus
				if(f.equals(feld) && spieler.steinIstVorhanden(f.convertToStein())) {
					stein = f.getStein();
					if(!spieler.pruefeObSteinTeilEinerMuehhleIst(stein)) {
						f.entferneStein();
					}
					break;
				}
			}
			if(stein != null) {
				spielstein = stein.getSpielstein();
			} else {
				System.out.println("Der Stein gehört zu einer Mühle und darf nicht entfernt werden!");
			}
			return spielstein;
		}
		/**
		 * Diese Methode überprüft ob ein Feld belegt ist.
		 * @param feld das zu prüfende Feld
		 * @return True, wenn das Feld belegt ist, sonst false
		 */
		public boolean checkAufBelegtFeld(Feld feld) {
			boolean check = false;
			for(Feld f: felder) {
				if(f.equals(feld)) {
					feld = f;
					break;
				}
			}
			if(feld.getBelegt()) {
				check = true;
			}
			return check;
		}
}


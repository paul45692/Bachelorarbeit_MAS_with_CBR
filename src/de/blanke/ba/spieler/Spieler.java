package de.blanke.ba.spieler;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import de.blanke.ba.model.M�hle;
import de.blanke.ba.model.Stein;
/**
 * Diese klasse bildet einen Spieler ab.
 * @author Paul Blanke.
 *
 */
public class Spieler implements Serializable{
// Attribute	
	private static final long serialVersionUID = -7740050342688455840L;
	private int anzahlSteine;
	private int anzahlSpielZ�ge;
	private Color spielFarbe;
	private String name;
	private int spielPhase;
	private int tempspielPhase;
	private List<Stein> posiSteine = new ArrayList<>();
	private List<M�hle> vorhandeneMuehlen = new ArrayList<>();
// Getter und Setter	
	public int getAnzahlSteine() {
		return anzahlSteine;
	}
	public void setAnzahlSteine(int anzahlSteine) {
		this.anzahlSteine = anzahlSteine;
	}
	public int getAnzahlSpielZ�ge() {
		return anzahlSpielZ�ge;
	}
	public void setAnzahlSpielZ�ge(int anzahlSpielZ�ge) {
		this.anzahlSpielZ�ge = anzahlSpielZ�ge;
	}
	public int getSpielPhase() {
		return spielPhase;
	}
	public void setSpielPhase(int spielPhase) {
		this.spielPhase = spielPhase;
	}
	public Color getSpielFarbe() {
		return spielFarbe;
	}
	public void setSpielFarbe(Color spielFarbe) {
		this.spielFarbe = spielFarbe;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<M�hle> getVorhandeneMuehlen() {
		return vorhandeneMuehlen;
	}
	public void setVorhandeneMuehlen(List<M�hle> vorhandeneMuehlen) {
		this.vorhandeneMuehlen = vorhandeneMuehlen;
	}
	public void setWeitereM�hlenDazu(List<M�hle> weitere) {
		for(M�hle m�hle: weitere) {
			this.vorhandeneMuehlen.add(m�hle);
		}
	}
	public void remove�berfl�ssigeM�hlen(List<M�hle> �berz�hlig) {
		for(M�hle m�hle: �berz�hlig) {
			this.vorhandeneMuehlen.remove(m�hle);
		}
	}
	public int getTempspielPhase() {
		return tempspielPhase;
	}
	public void setTempspielPhase(int tempspielPhase) {
		this.tempspielPhase = tempspielPhase;
	}
	public int getAnzahlM�hlen() {
		if(this.vorhandeneMuehlen.isEmpty()) {
			return 0;
		} else {
			return this.vorhandeneMuehlen.size();
		}
	}
// Kontruktor
	public Spieler(Color farbe, String name) {
		this.anzahlSpielZ�ge = 0;
		this.anzahlSteine = 0;
		this.setName(name);
		this.spielFarbe = farbe;
		this.spielPhase = 0;
		
	}
// Methoden	
	public void removeStein(Stein stein) {
		this.posiSteine.remove(stein);
	}
	/**
	 * Diese Methode setzt die Steine des Spieler beim
	 * M�hlen Fund auf Belegt.
	 * @param muehle
	 */
	public void addMuehle(M�hle muehle) {
		for(Stein stein: muehle.getSteine()) {
			for(Stein s: this.posiSteine) {
				if(s.equals(stein)) {
					s.setIsTeilVonMuehle(true);
				}
			}
		}
		this.vorhandeneMuehlen.add(muehle);
	}
	
	public void removeMuehle(M�hle muehle) {
		for(Stein stein: muehle.getSteine()) {
			for(Stein s: this.posiSteine) {
				if(s.equals(stein)) {
					s.setIsTeilVonMuehle(false);
				}
			}
		}
		
		this.vorhandeneMuehlen.remove(muehle);
	}
	
	public void setzeSpielstein(Stein stein) {
		this.posiSteine.add(stein);
	}

	public List<Stein> getPosiSteine() {
		return posiSteine;
	}

	public void setPosiSteine(List<Stein> posiSteine) {
		this.posiSteine = posiSteine;
	}
	
	public boolean steineIsVorhanden(Stein stein) {
		boolean check = false;
		for(Stein s: posiSteine) {
			if(stein.equals(s)) {
				check = true;
				break;
			}
		}
		return check;
	}
}

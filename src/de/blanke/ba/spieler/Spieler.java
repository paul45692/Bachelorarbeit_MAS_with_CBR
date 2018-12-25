package de.blanke.ba.spieler;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import de.blanke.ba.model.Mühle;
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
	private int anzahlSpielZüge;
	private Color spielFarbe;
	private String name;
	private int spielPhase;
	private int tempspielPhase;
	private List<Stein> posiSteine = new ArrayList<>();
	private List<Mühle> vorhandeneMuehlen = new ArrayList<>();
// Getter und Setter	
	public int getAnzahlSteine() {
		return anzahlSteine;
	}
	public void setAnzahlSteine(int anzahlSteine) {
		this.anzahlSteine = anzahlSteine;
	}
	public int getAnzahlSpielZüge() {
		return anzahlSpielZüge;
	}
	public void setAnzahlSpielZüge(int anzahlSpielZüge) {
		this.anzahlSpielZüge = anzahlSpielZüge;
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
	public List<Mühle> getVorhandeneMuehlen() {
		return vorhandeneMuehlen;
	}
	public void setVorhandeneMuehlen(List<Mühle> vorhandeneMuehlen) {
		this.vorhandeneMuehlen = vorhandeneMuehlen;
	}
	public void setWeitereMühlenDazu(List<Mühle> weitere) {
		for(Mühle mühle: weitere) {
			this.vorhandeneMuehlen.add(mühle);
		}
	}
	public void removeÜberflüssigeMühlen(List<Mühle> überzählig) {
		for(Mühle mühle: überzählig) {
			this.vorhandeneMuehlen.remove(mühle);
		}
	}
	public int getTempspielPhase() {
		return tempspielPhase;
	}
	public void setTempspielPhase(int tempspielPhase) {
		this.tempspielPhase = tempspielPhase;
	}
	public int getAnzahlMühlen() {
		if(this.vorhandeneMuehlen.isEmpty()) {
			return 0;
		} else {
			return this.vorhandeneMuehlen.size();
		}
	}
// Kontruktor
	public Spieler(Color farbe, String name) {
		this.anzahlSpielZüge = 0;
		this.anzahlSteine = 0;
		this.setName(name);
		this.spielFarbe = farbe;
		this.spielPhase = 0;
		
	}
// Methoden	
	public void removeStein(Stein stein) {
		this.posiSteine.remove(stein);
	}
	
	public void addMuehle(Mühle muehle) {
		this.vorhandeneMuehlen.add(muehle);
		
	}
	
	public void removeMuehle(Mühle muehle) {
		
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
			if((s.getRing() == stein.getRing()) && stein.getxCord() == s.getxCord() && stein.getyCord() == s.getyCord()) {
				check = true;
				break;
			}
		}
		return check;
	}
}

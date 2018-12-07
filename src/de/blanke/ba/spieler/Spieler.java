package de.blanke.ba.spieler;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import de.blanke.ba.model.Mühle;
import de.blanke.ba.model.Stein;


/**
 * Diese klasse hält Spieler informationen bereit.
 * @author paul4
 *
 */
public class Spieler implements Serializable{
	
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
// Kontruktor
	public Spieler(Color farbe, String name) {
		this.anzahlSpielZüge = 0;
		this.anzahlSteine = 0;
		this.setName(name);
	}
	
	
	
public Spieler() {
	// TODO Auto-generated constructor stub
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
	
	
	public Spieler copyInstance() {
		
		Spieler spielerCopy = new Spieler();
		spielerCopy.setName(this.getName());
		spielerCopy.setSpielFarbe(this.getSpielFarbe());
		spielerCopy.setSpielPhase(this.getSpielPhase());
		spielerCopy.setAnzahlSteine(this.getAnzahlSpielZüge());
		spielerCopy.setAnzahlSpielZüge(this.getAnzahlSpielZüge());
		spielerCopy.setPosiSteine(this.getPosiSteine());
		spielerCopy.setTempspielPhase(this.getTempspielPhase());
		spielerCopy.setVorhandeneMuehlen(this.getVorhandeneMuehlen());
		
		return spielerCopy;
	}
	

	
	
}

package de.blanke.ba.spieler;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import de.blanke.ba.model.M�hle;
import de.blanke.ba.model.Stein;


/**
 * Diese klasse h�lt Spieler informationen bereit.
 * @author paul4
 *
 */
public class Spieler implements Serializable{
	
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
// Kontruktor
	public Spieler(Color farbe, String name) {
		this.anzahlSpielZ�ge = 0;
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
	
	public void addMuehle(M�hle muehle) {
		this.vorhandeneMuehlen.add(muehle);
		
	}
	
	public void removeMuehle(M�hle muehle) {
		
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
		spielerCopy.setAnzahlSteine(this.getAnzahlSpielZ�ge());
		spielerCopy.setAnzahlSpielZ�ge(this.getAnzahlSpielZ�ge());
		spielerCopy.setPosiSteine(this.getPosiSteine());
		spielerCopy.setTempspielPhase(this.getTempspielPhase());
		spielerCopy.setVorhandeneMuehlen(this.getVorhandeneMuehlen());
		
		return spielerCopy;
	}
	

	
	
}

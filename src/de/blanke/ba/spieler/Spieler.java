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
	/**
	 * Diese Var. speichert die aktuelle Anzahl der Steine auf dem Spielfeld.
	 */
	private int anzahlSteine;
	/**
	 * Speichert die Anzahl der gemachten Spielz�ge.
	 */
	private int anzahlSpielZ�ge;
	/**
	 * Weiss 0, Blau 1.
	 */
	private Color spielFarbe;
	
	/**
	 * Das Attribute h�lt die SpielPhase des Spielers.
	 */
	private int spielPhase;
	private int tempspielPhase;
	
	/**
	 * Diese Liste zeigt die Position der eignen Spielsteine an.
	 * 
	 */
	private List<Stein> posiSteine = new ArrayList<>();
	
	/**
	 * Dieses Attribute h�lt die M�hlen des Spielers.
	 */
	private List<M�hle> vorhandeneMuehlen = new ArrayList<>();
	
	
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

	public List<M�hle> getVorhandeneMuehlen() {
		return vorhandeneMuehlen;
	}

	public void setVorhandeneMuehlen(List<M�hle> vorhandeneMuehlen) {
		this.vorhandeneMuehlen = vorhandeneMuehlen;
	}
	
	
	
// Kontruktor
	
	public int getTempspielPhase() {
		return tempspielPhase;
	}

	public void setTempspielPhase(int tempspielPhase) {
		this.tempspielPhase = tempspielPhase;
	}

	public Spieler(Color farbe) {
		this.anzahlSpielZ�ge = 0;
		this.anzahlSteine = 0;
	}
	
	
	
// Methoden	
	public void setzStein(Stein stein) {
		this.posiSteine.add(stein);
	}
	
	public void removeStein(Stein stein) {
		this.posiSteine.remove(stein);
	}
	
	public void addMuehle(M�hle muehle) {
		this.vorhandeneMuehlen.add(muehle);
		
	}
	
	public void removeMuehle(M�hle muehle) {
		
		this.vorhandeneMuehlen.remove(muehle);
	}
	
	public List<Stein> getAlleSteinePosition() {
		return this.posiSteine;
	}
	

	
	
}

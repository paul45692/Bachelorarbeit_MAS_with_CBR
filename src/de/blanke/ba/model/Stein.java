package de.blanke.ba.model;

import java.awt.Color;
// Diese Klasse bildet die Spielsteine auf der Logikebene ab.
public class Stein {
	
	private Color farbe;
	private int ring;
	private int xCord;
	private int yCord;
	private int isTeilVonMuehle;
	private Spielstein spielstein;
	
// Getter und Setter
	public Color getFarbe() {
		return farbe;
	}

	public void setFarbe(Color farbe) {
		this.farbe = farbe;
	}

	public int getRing() {
		return ring;
	}

	public void setRing(int ring) {
		this.ring = ring;
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

	public void setyCord(int yCord) {
		this.yCord = yCord;
	}
	
	
	public Spielstein getSpielstein() {
		return spielstein;
	}

	public void setSpielstein(Spielstein spielstein) {
		this.spielstein = spielstein;
	}

	public int getIsTeilVonMuehle() {
		return isTeilVonMuehle;
	}

	public void setIsTeilVonMuehle(int isTeilVonMuehle) {
		this.isTeilVonMuehle = isTeilVonMuehle;
	}


	
	// Kontruktor	
	public Stein(int ring, int xCord, int yCord, Color farbe) {
		this.ring = ring;
		this.xCord = xCord;
		this.yCord = yCord;
		this.farbe = farbe;
		
		
	}
	public Stein(int ring, int xCord, int yCord, Color farbe, Spielstein spielstein) {
		this.ring = ring;
		this.xCord = xCord;
		this.yCord = yCord;
		this.farbe = farbe;
		this.setSpielstein(spielstein);
		
		
	}
	
	/**
	 * Diese Methode kann nur durch einen validen Zug gemacht werden.
	 */
	public void bewegeStein(int ring, int xCord, int yCord) {
		this.ring = ring;
		this.xCord = xCord;
		this.yCord = yCord;
	}
	
	public Feld convertToFeld() {
		return new Feld(this.ring, this.xCord, this.yCord);
	}

}

package de.blanke.ba.model;

import java.awt.Color;

import jade.util.leap.Serializable;
/**
 * Diese Klasse speichert die Daten zum zeichnen zwischen.
 * @author paul4
 *
 */
public class Spielstein implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 625361226234884439L;
	/**
	 * 
	 */

	/**
	 * Die verknüpfung des Steines aus der Logik schicht
	 */
	private int x;
	private int y;
	// Farbe
	private Color color;

	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Spielstein(int x, int y, Color color) {
		this.setX(x);
		this.setY(y);
		this.setColor(color);
	}
	
	

}

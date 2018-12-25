package de.blanke.ba.model;

import java.awt.Color;

import jade.util.leap.Serializable;
/**
 * Diese Klasse repräsentiert den Spielstein auf der GUI.
 * X,Y Koordinaten auf dem Panel und die Color ist die Farbe des Steines.
 * @author paul4
 *
 */
public class Spielstein implements Serializable  {
// Attribute
	private static final long serialVersionUID = 625361226234884439L;
	private int x;
	private int y;
	private Color color;
// Getter und Setter
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
// Konstruktor	
	public Spielstein(int x, int y, Color color) {
		this.setX(x);
		this.setY(y);
		this.setColor(color);
	}
}

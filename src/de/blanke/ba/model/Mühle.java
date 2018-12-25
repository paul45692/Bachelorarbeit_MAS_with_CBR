package de.blanke.ba.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Diese Klasse bildet eine Mühle ab.
 * Die liste der Steine enthält alle Positionen der Mühle.
 * @author Paul Blanke.
 *
 */
public class Mühle {
// Attribute
	private int index;
	private List<Stein> steine = new ArrayList<>();
// Getter und Setter	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<Stein> getSteine() {
		return steine;
	}
	public void setSteine(List<Stein> steine) {
		this.steine = steine;
	}
// Konstruktor
	public Mühle(int index, List<Stein> steine) {
		this.setIndex(index);
		this.setSteine(steine);		
	}
// Methoden
	public void setFarbe(Color color) {
		for(Stein stein: steine) {
			stein.setFarbe(color);
		}
	}
}

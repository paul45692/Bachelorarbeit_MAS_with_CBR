package de.blanke.ba.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Diese Klasse bildet eine M�hle ab.
 * Die liste der Steine enth�lt alle Positionen der M�hle.
 * @author Paul Blanke.
 *
 */
public class M�hle {
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
	public M�hle(int index, List<Stein> steine) {
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

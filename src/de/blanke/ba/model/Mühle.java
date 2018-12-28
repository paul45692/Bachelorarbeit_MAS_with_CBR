package de.blanke.ba.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import jade.util.leap.Serializable;
/**
 * Diese Klasse bildet eine Mühle ab.
 * Die liste der Steine enthält alle Positionen der Mühle.
 * @author Paul Blanke.
 *
 */
public class Mühle implements Serializable {
	// Attribute
	private static final long serialVersionUID = 1L;
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
	/**
	 * Diese Methode vergleicht zwei Mühlen über die übergegebenden Steinen
	 * miteinander, wenn alle drei Steine gleich sind, dann true,
	 * sonst false.
	 */
	@Override
	public boolean equals(Object object) {
		boolean rueckgabe = false;
		Mühle mühle = (Mühle) object;
		List<Stein> steine = mühle.getSteine();
		int counter = 0;
		for(Stein stein:this.steine) {
			for(Stein s: steine) {
				if(stein.equals(s)) {
					counter++;
					break;
				}
			}
		}
		if(counter == 3) {
			rueckgabe = true;
		}
		return rueckgabe;
		
	}
		
		
}

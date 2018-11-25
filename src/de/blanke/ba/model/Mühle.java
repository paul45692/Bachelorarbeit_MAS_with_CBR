package de.blanke.ba.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.blanke.ba.model.Feld;
/**
 * Diese Klasse speichert eine vorhandene Mühle
 * @author paul4
 *
 */
public class Mühle {
	
	private int index;
	private List<Stein> steine = new ArrayList<>();
	
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

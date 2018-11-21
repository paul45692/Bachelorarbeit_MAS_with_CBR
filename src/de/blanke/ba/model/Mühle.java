package de.blanke.ba.model;

import java.util.List;

import de.blanke.ba.model.Feld;
/**
 * Diese Klasse speichert eine vorhandene Mühle
 * @author paul4
 *
 */
public class Mühle {
	private List<Feld> felder;
	private int muehleIstOffen = 0;
	
	public List<Feld> getFelder() {
		return felder;
	}

	public void setFelder(List<Feld> felder) {
		this.felder = felder;
	}

	public int getMuehleIstOffen() {
		return muehleIstOffen;
	}

	public void setMuehleIstOffen(int muehleIstOffen) {
		this.muehleIstOffen = muehleIstOffen;
	}

	public Mühle(List<Feld> felder) {
		this.felder = felder;
		this.muehleIstOffen = 0;
	}
	
	public void offeneMuehle() {
		this.muehleIstOffen = 1;
	}
	
	public void schliesseMuehle() {
		this.muehleIstOffen = 0;
	}
	
	public void addFeld(Feld feld) {
		this.felder.add(feld);
	}
	
	
	
	
}

package de.blanke.ba.rbs;

import java.util.ArrayList;
import java.util.List;

import de.blanke.ba.model.Stein;
/**
 * Diese Klasse enthält alle Regeln in Listen bereit.
 * Innerhalb dieser Klasse werden die Regeln beim Programmstart zusammen gestellt.
 * @author Paul Blanke
 *
 */
public class RegelSet {	
	
	// Attribute
	protected List<RegelSpielPhase0> spielphase0 = new ArrayList<>();
	protected List<RegelSpielPhase1u2> spielphase1 = new ArrayList<>();
	protected List<RegelSpielPhase1u2> spielphase2 = new ArrayList<>();
	protected List<RegelSpielPhase0> spielphase3 = new ArrayList<>();
	
// Getter und Setter	
	protected List<RegelSpielPhase0> getSpielphase0() {
		return spielphase0;
	}
	protected void setSpielphase0(List<RegelSpielPhase0> spielphase0) {
		this.spielphase0 = spielphase0;
	}
	
	
	protected List<RegelSpielPhase0> getSpielphase3() {
		return spielphase3;
	}
	protected void setSpielphase3(List<RegelSpielPhase0> spielphase3) {
		this.spielphase3 = spielphase3;
	}
	// private Methoden 	
	/**
	 * Diese Methode setzt alle Regeln der Spielphase 0 neu auf.
	 */
	private void setUpSteineSetzen() {
		spielphase0.add(new RegelSpielPhase0("Frei", new Stein(0,0,0, null), "Setze", new Stein(0,0,0, null)));
		spielphase0.add(new RegelSpielPhase0("Belegt", new Stein(0,0,0, null), "Setze", new Stein(0,1,2, null)));
		spielphase0.add(new RegelSpielPhase0("Frei", new Stein(0,0,2, null), "Setze", new Stein(0,0,2, null)));
		spielphase0.add(new RegelSpielPhase0("Frei", new Stein(0,1,2, null), "Setze", new Stein(0,1,2, null)));
		spielphase0.add(new RegelSpielPhase0("Frei", new Stein(0,2,2, null), "Setze", new Stein(0,2,2, null)));
		spielphase0.add(new RegelSpielPhase0("Frei", new Stein(1,0,1, null), "Setze", new Stein(1,0,1, null)));
		spielphase0.add(new RegelSpielPhase0("Frei", new Stein(1,0,2, null), "Setze", new Stein(1,0,2, null)));
		spielphase0.add(new RegelSpielPhase0("Frei", new Stein(1,1,0, null), "Setze", new Stein(1,1,0, null)));
		spielphase0.add(new RegelSpielPhase0("Frei", new Stein(1,2,1, null), "Setze", new Stein(1,2,1, null)));
		spielphase0.add(new RegelSpielPhase0("Frei", new Stein(2,1,2, null), "Setze", new Stein(2,1,2, null)));
		// nehme Zufallsregel auf
		RegelSpielPhase0 zufall = new RegelSpielPhase0("Zufall", new Stein(0,1,2, null), "", new Stein(0,1,2, null));
		zufall.erzeugeZufällig();
		for(RegelSpielPhase0 regel: spielphase0) {
			if(regel.equals(zufall)) {
				regel.erzeugeZufällig();
				break;
			} 
		}
		spielphase0.add(zufall);
		// setzeSonstigeRegelAuf
	}
	
	private void setSteineVerschiebenZweiteSpielPhaseAuf() {
		
	}
	
	private void setSteineVerschiebenDritteSpielPhaseAuf() {
		
	}
	
	private void setUpSteineLoeschen() {
		spielphase3.add(new RegelSpielPhase0("Belegt", new Stein(0,0,0, null), "Entferne", new Stein(0,0,0, null)));
		spielphase3.add(new RegelSpielPhase0("Belegt", new Stein(0,0,1, null), "Entferne", new Stein(0,0,1, null)));
		spielphase3.add(new RegelSpielPhase0("Belegt", new Stein(0,0,2, null), "Entferne", new Stein(0,0,2, null)));
		spielphase3.add(new RegelSpielPhase0("Belegt", new Stein(0,1,0, null), "Entferne", new Stein(0,1,0, null)));
		spielphase3.add(new RegelSpielPhase0("Belegt", new Stein(0,2,0, null), "Entferne", new Stein(0,2,0, null)));
		spielphase3.add(new RegelSpielPhase0("Belegt", new Stein(1,0,2, null), "Entferne", new Stein(0,0,2, null)));
		spielphase3.add(new RegelSpielPhase0("Belegt", new Stein(1,1,0, null), "Entferne", new Stein(0,1,0, null)));
		spielphase3.add(new RegelSpielPhase0("Belegt", new Stein(1,2,0, null), "Entferne", new Stein(0,2,0, null)));
		spielphase3.add(new RegelSpielPhase0("Belegt", new Stein(1,0,2, null), "Entferne", new Stein(0,0,2, null)));
		spielphase3.add(new RegelSpielPhase0("Belegt", new Stein(2,1,0, null), "Entferne", new Stein(0,1,0, null)));
		spielphase3.add(new RegelSpielPhase0("Belegt", new Stein(2,2,0, null), "Entferne", new Stein(0,2,0, null)));
		RegelSpielPhase0 zufall = new RegelSpielPhase0("Belegt", new Stein(0,1,2, null), "", new Stein(0,1,2, null));
		zufall.erzeugeZufällig();
		for(RegelSpielPhase0 regel: spielphase0) {
			if(regel.equals(zufall)) {
				regel.erzeugeZufällig();
				break;
			} 
		}
		spielphase0.add(zufall);
	}

// konstruktor
	
	public RegelSet() {
		this.setUpSteineSetzen();
		this.setUpSteineLoeschen();
		this.setSteineVerschiebenZweiteSpielPhaseAuf();
		this.setSteineVerschiebenDritteSpielPhaseAuf();
	}
	
}

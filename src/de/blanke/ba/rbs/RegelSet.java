package de.blanke.ba.rbs;

import java.util.ArrayList;
import java.util.List;

import de.blanke.ba.model.Stein;
/**
 * Diese Klasse instanziert die Regeln beim Programm Start und 
 * speichert sie in Listen.
 * @author Paul Blanke.
 *
 */
public class RegelSet {	
// Attribute
	protected List<RegelSpielPhase0> spielphase0 = new ArrayList<>();
	protected List<RegelSpielPhase1u2> spielphase1 = new ArrayList<>();
	protected List<RegelSpielPhase1u2> spielphase2 = new ArrayList<>();
	protected List<RegelSpielPhase0> spielphase3 = new ArrayList<>();
	protected List<Regel> ubergreifendeRegeln = new ArrayList<>();
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
	public List<RegelSpielPhase1u2> getSpielphase1() {
		return spielphase1;
	}
	public List<RegelSpielPhase1u2> getSpielphase2() {
		return spielphase2;
	}
	public void setSpielphase1(List<RegelSpielPhase1u2> spielphase1) {
		this.spielphase1 = spielphase1;
	}
	public void setSpielphase2(List<RegelSpielPhase1u2> spielphase2) {
		this.spielphase2 = spielphase2;
	}
	/**
	 * @return the ubergreifendeRegeln
	 */
	public List<Regel> getUbergreifendeRegeln() {
		return ubergreifendeRegeln;
	}
	/**
	 * @param ubergreifendeRegeln the ubergreifendeRegeln to set
	 */
	public void setUbergreifendeRegeln(List<Regel> ubergreifendeRegeln) {
		this.ubergreifendeRegeln = ubergreifendeRegeln;
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
		RegelSpielPhase1u2 regel01 = new RegelSpielPhase1u2("Verschiebe", new Stein(0,0,0, null), new Stein(0,1,0, null), "-",  new Stein(0,1,0, null));
		spielphase1.add(regel01);
		RegelSpielPhase1u2 regel02 = new RegelSpielPhase1u2("Verschiebe", new Stein(0,2,0, null), new Stein(0,2,1, null), "-",  new Stein(0,2,1, null));
		spielphase1.add(regel02);
		RegelSpielPhase1u2 regel03 = new RegelSpielPhase1u2("Verschiebe", new Stein(1,0,0, null), new Stein(1,1,0, null), "-",  new Stein(1,1,0, null));
		spielphase1.add(regel03);
		RegelSpielPhase1u2 regel04 = new RegelSpielPhase1u2("Verschiebe", new Stein(1,0,2, null), new Stein(1,1,2, null), "-",  new Stein(1,1,2, null));
		spielphase1.add(regel04);
		RegelSpielPhase1u2 regel05 = new RegelSpielPhase1u2("Verschiebe", new Stein(2,2,0, null), new Stein(2,2,1, null), "-",  new Stein(2,2,1, null));
		spielphase1.add(regel05);
		RegelSpielPhase1u2 regel06 = new RegelSpielPhase1u2("Verschiebe", new Stein(2,0,2, null), new Stein(2,0,1, null), "-",  new Stein(2,0,1, null));
		spielphase1.add(regel06);
		RegelSpielPhase1u2 regel07 = new RegelSpielPhase1u2("zufall", new Stein(0,0,0, null), new Stein(0,0,0,null), "zufall", new Stein(0,0,0, null));
		spielphase1.add(regel07);
	}
	
	private void setSteineVerschiebenDritteSpielPhaseAuf() {
		RegelSpielPhase1u2 regel01 = new RegelSpielPhase1u2("Verschiebe", new Stein(0,0,0, null), new Stein(1,0,1, null), "Springe zu ",  new Stein(1,0,1, null));
		spielphase2.add(regel01);
		RegelSpielPhase1u2 regel02 = new RegelSpielPhase1u2("Verschiebe", new Stein(0,2,0, null), new Stein(0,0,1, null), "Springe zu",  new Stein(0,0,1, null));
		spielphase2.add(regel02);
		RegelSpielPhase1u2 regel03 = new RegelSpielPhase1u2("Verschiebe", new Stein(1,0,0, null), new Stein(1,1,2, null), "Springe zu",  new Stein(1,1,2, null));
		spielphase2.add(regel03);
		RegelSpielPhase1u2 regel04 = new RegelSpielPhase1u2("Verschiebe", new Stein(1,0,2, null), new Stein(0,2,1, null), "Springe zu ",  new Stein(0,2,1, null));
		spielphase2.add(regel04);
		RegelSpielPhase1u2 regel05 = new RegelSpielPhase1u2("Verschiebe", new Stein(2,2,0, null), new Stein(0,0,1, null), "Springe zu",  new Stein(0,0,1, null));
		spielphase2.add(regel05);
		RegelSpielPhase1u2 regel06 = new RegelSpielPhase1u2("Verschiebe", new Stein(2,0,2, null), new Stein(2,1,0, null), "Spring zu ",  new Stein(2,1,0, null));
		spielphase2.add(regel06);
		RegelSpielPhase1u2 regel07 = new RegelSpielPhase1u2("zufall", new Stein(0,0,0, null), new Stein(0,0,0,null), "zufall", new Stein(0,0,0, null));
		spielphase1.add(regel07);
		
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
		RegelSpielPhase0 zufall = new RegelSpielPhase0("zufall", new Stein(0,1,2, null), "", new Stein(0,1,2, null));
		zufall.erzeugeZufällig();
		for(RegelSpielPhase0 regel: spielphase0) {
			if(regel.equals(zufall)) {
				regel.erzeugeZufällig();
				break;
			} 
		}
		spielphase0.add(zufall);
	}

	private void setUpUeberGreifendeRegelnAuf() {
		Regel eins = new Regel("Zwei  eigene Steine in einer Reihe", "Einen Stein dazu");
		ubergreifendeRegeln.add(eins);
		Regel zwei = new Regel("Zwei gegenerische Steine in einer Reihe", "Einen Stein dazu");
		ubergreifendeRegeln.add(zwei);
	}
	
// konstruktor
	
	public RegelSet() {
		this.setUpSteineSetzen();
		this.setUpSteineLoeschen();
		this.setSteineVerschiebenZweiteSpielPhaseAuf();
		this.setSteineVerschiebenDritteSpielPhaseAuf();
		this.setUpUeberGreifendeRegelnAuf();
	}
	
}

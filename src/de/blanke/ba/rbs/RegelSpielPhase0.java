package de.blanke.ba.rbs;

import java.awt.Color;
import java.util.Random;
import de.blanke.ba.model.Stein;
/**
 * Diese Klasse erweitert die Regel um SpielSteine.
 * 
 * @author Paul Blanke.
 *
 */
public class RegelSpielPhase0 extends Regel {
	
	private Stein ifStein;
	private	Stein elseStein;

	public Stein getIfStein() {
		return ifStein;
	}
	public void setIfStein(Stein ifStein) {
		this.ifStein = ifStein;
	}
	public Stein getElseStein() {
		return elseStein;
	}
	public void setElseStein(Stein elseStein) {
		this.elseStein = elseStein;
	}
	public RegelSpielPhase0(String ifTeil,Stein ifStein,  String elseTeil, Stein elseStein) {
		super(ifTeil, elseTeil);
		this.setIfStein(ifStein);
		this.setElseStein(elseStein);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Diese Methode legt zufällige Steine an.
	 */
	protected void erzeugeZufällig() {
		Random r = new Random();
		for(int i = 0; i < 2; i++) {
			int zahlR = r.nextInt((3-0));
			int zahlX = r.nextInt(3-0);
			int zahlY = r.nextInt(3-0);
			if(i== 0) {
				this.setIfStein(new Stein(zahlR, zahlX, zahlY, null));
			} else {
				this.setElseStein(new Stein(zahlR, zahlX, zahlY, null));
			}
		}
	}
	
	
	/**
	 * Diese Methode setzt nachträglich die Farbe der Steine auf.
	 */
	@Override
	protected void setColor(Color color) {
		super.setColor(color);
		this.ifStein.setFarbe(color);
		this.elseStein.setFarbe(color);
		if(this.getElseTeil().contains("Entferne") & color == Color.BLUE) {
			color = Color.WHITE;
			this.ifStein.setFarbe(color);
			this.elseStein.setFarbe(color);
		} else if(this.getElseTeil().contains("Entferne") & color == Color.WHITE) {
			color = Color.BLUE;
			this.ifStein.setFarbe(color);
			this.elseStein.setFarbe(color);
		}
	}
}

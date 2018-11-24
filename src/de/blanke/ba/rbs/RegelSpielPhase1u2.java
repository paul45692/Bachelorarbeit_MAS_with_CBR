package de.blanke.ba.rbs;

import java.awt.Color;
import java.util.Random;
import de.blanke.ba.model.Stein;

public class RegelSpielPhase1u2  extends Regel {
	

	// Attribute
	private Stein freiesFeld;
	private Stein besetztesFeld;
	private Stein bewegungsFeld;
	
	
	// Getter und Setter	
	
	public Stein getFreiesFeld() {
		return freiesFeld;
	}

	public void setFreiesFeld(Stein freiesFeld) {
		this.freiesFeld = freiesFeld;
	}

	public Stein getBesetztesFeld() {
		return besetztesFeld;
	}

	public void setBesetztesFeld(Stein besetztesFeld) {
		this.besetztesFeld = besetztesFeld;
	}

	public Stein getBewegungsFeld() {
		return bewegungsFeld;
	}

	public void setBewegungsFeld(Stein bewegungsFeld) {
		this.bewegungsFeld = bewegungsFeld;
	}

	public RegelSpielPhase1u2(String ifTeil,Stein besetztesFeld, Stein freiesFeld, String elseTeil, Stein bewegungsFeld) {
		super(ifTeil, elseTeil);
		this.besetztesFeld = besetztesFeld;
		this.freiesFeld = freiesFeld;
		this.bewegungsFeld = bewegungsFeld;
		// TODO Auto-generated constructor stub
	}
	
	protected void erzeugeZufällig() {
		Random r = new Random();
		
		for(int i = 0; i < 2; i++) {
			int zahlR = r.nextInt((3-0));
			int zahlX = r.nextInt(10-0);
			int zahlY = r.nextInt(10-0);
			if(i== 0) {
				this.setBesetztesFeld(new Stein(zahlR, zahlX, zahlY, null));
			} else {
				this.setFreiesFeld(new Stein(zahlR, zahlX, zahlY, null));
				this.setBewegungsFeld(new Stein(zahlR, zahlX, zahlY, null));
			}
		}
	}
	
	@Override
	protected void setColor(Color color) {
		super.setColor(color);
		this.freiesFeld.setFarbe(color);
		this.besetztesFeld.setFarbe(color);
		this.bewegungsFeld.setFarbe(color);
	}
}

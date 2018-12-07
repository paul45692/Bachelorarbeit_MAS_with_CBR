package de.blanke.ba.mas;

import java.io.Serializable;

import de.blanke.ba.model.Stein;
/**
 * Diese Klasse stellt eine Transportbox für den Transport über das MAS bereit.
 * @author Paul Blanke
 *
 */
public class MessageBoxSteine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7125119367559353478L;
	
	private Stein entferneStein;
	private Stein zielStein;
	
	public Stein getEntferneStein() {
		return entferneStein;
	}

	public void setEntferneStein(Stein entferneStein) {
		this.entferneStein = entferneStein;
	}

	public Stein getZielStein() {
		return zielStein;
	}

	public void setZielStein(Stein zielStein) {
		this.zielStein = zielStein;
	}

	public MessageBoxSteine(Stein entferneStein, Stein zielStein) {
		this.setEntferneStein(entferneStein);
		this.setZielStein(zielStein);
	}
	
	public void clear() {
		this.entferneStein = null;
		this.zielStein = null;
	}
	

}

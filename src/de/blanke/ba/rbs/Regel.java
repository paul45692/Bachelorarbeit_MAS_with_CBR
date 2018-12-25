package de.blanke.ba.rbs;

import java.awt.Color;

/**
 * Diese Klasse stellt die einfachste Regelform dar.
 * @author Paul Blanke.
 *
 */
public class Regel {
	
	private String ifTeil;
	private String elseTeil;
	
	public String getIfTeil() {
		return ifTeil;
	}
	public void setIfTeil(String ifTeil) {
		this.ifTeil = ifTeil;
	}
	public String getElseTeil() {
		return elseTeil;
	}
	public void setElseTeil(String elseTeil) {
		this.elseTeil = elseTeil;
	}
	
	public Regel(String ifTeil, String elseTeil) {
		this.ifTeil = ifTeil;
		this.elseTeil = elseTeil;
	}
	/**
	 * Eine Methode die später überschrieben wird
	 * Vererbung
	 * @param color Spielerfarbe
	 */
	protected void setColor(Color color) {
		
	}
}

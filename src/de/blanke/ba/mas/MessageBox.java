package de.blanke.ba.mas;

import java.io.Serializable;

import de.blanke.ba.logik.Board;
import de.blanke.ba.spieler.Spieler;

/**
 * Diese Klasse funktioniert als eine Art Transportbox für die Agenten Kommunikation
 * @author Paul Blanke.
 *
 */
public class MessageBox implements Serializable {
// Attribute
	private static final long serialVersionUID = -5119553190580869433L;
	private Spieler spieler;
	private Spieler spielerB;
	private Board board;
// getter und setter
	public Spieler getSpieler() {
		return spieler;
	}
	public Board getBoard() {
		return board;
	}
	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Spieler getSpielerB() {
		return spielerB;
	}
	public void setSpielerB(Spieler spielerB) {
		this.spielerB = spielerB;
	}
// Konstruktor	
	public MessageBox(Spieler spieler,Spieler spielerB, Board board) {
		this.spieler = spieler;
		this.spielerB = spielerB;
		this.board = board;
		
	}
// Methoden	
	public void cleartheBox() {
		this.spieler = null;
		this.spielerB = null;
		this.board = null;
	}
}

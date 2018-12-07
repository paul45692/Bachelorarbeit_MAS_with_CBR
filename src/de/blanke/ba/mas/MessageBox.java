package de.blanke.ba.mas;

import java.io.Serializable;

import de.blanke.ba.logik.Board;
import de.blanke.ba.spieler.Spieler;

/**
 * Diese Klasse funktioniert als eine Art Transportbox für die Agenten Kommunikation
 * @author paul4
 *
 */
public class MessageBox implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5119553190580869433L;
	// Attribute
	private Spieler spieler;
	private Board board;
	
	public Spieler getSpieler() {
		return spieler;
	}
	
	// Getter und Setter
	public Board getBoard() {
		return board;
	}
	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public MessageBox(Spieler spieler, Board board) {
		this.spieler = spieler;
		this.board = board;
	}
	
	public void cleartheBox() {
		this.spieler = null;
		this.board = null;
	}

}

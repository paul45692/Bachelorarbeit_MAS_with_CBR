package de.blanke.ba.testszenarien;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.blanke.ba.logik.Board;
import de.blanke.ba.model.Mühle;
import de.blanke.ba.model.Spielstein;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;

/**
 * Diese Klasse stellt TestSzenen bereit, die im Rahmen des Spiel aktiviert werden können.
 * @author paul4
 *
 */
public class SetUpTestSzenarien {
	
	private Spieler spielerA = null;
	private Spieler spielerB = null;
	private Board vorbereitet = null;
	// Getter und Setter
	public Spieler getSpielerA() {
		return spielerA;
	}
	public void setSpielerA(Spieler spielerA) {
		this.spielerA = spielerA;
	}
	public Spieler getSpielerB() {
		return spielerB;
	}
	public void setSpielerB(Spieler spielerB) {
		this.spielerB = spielerB;
	}
	public Board getVorbereitet() {
		return vorbereitet;
	}
	public void setVorbereitet(Board vorbereitet) {
		this.vorbereitet = vorbereitet;
	}
	// Erstelle die zweite TestSzene
	/**
	 * 2 Testszenario 
	 * Jeder Spieler hat 3 Spielsteine
	 * Keine Mühlen, Spielphase 0 "Setzen", gleichmäßig verteilt
	 * @return
	 */
	public List<Spielstein> createSzenario02(Spieler spielerA, Spieler spielerB, Board board) {
		List<Spielstein> data = new ArrayList<>(); 
		// Bereite die Spieler vor.
		spielerA.setAnzahlSpielZüge(3);
		spielerA.setAnzahlSteine(3);
		spielerA.setSpielPhase(0);
		spielerA.setTempspielPhase(0);
		spielerB.setAnzahlSpielZüge(3);
		spielerB.setAnzahlSteine(3);
		spielerB.setSpielPhase(0);
		spielerB.setTempspielPhase(0);
		// Setze die 6 Steine auf.
		 Stein eins = new Stein(0,2,0, Color.WHITE);
		 spielerA.setzeSpielstein(eins);
		 Spielstein einsA = (new Spielstein(730, 12, Color.WHITE));
		 board.setzteStein(0, 2, 0, spielerA, einsA);
		 data.add(einsA);
		 Stein zwei = new Stein(1,1,2, Color.WHITE);
		 spielerA.setzeSpielstein(zwei);
		 Spielstein zweiA = (new Spielstein(371, 568, Color.WHITE));
		 board.setzteStein(1, 1, 2, spielerA, zweiA);
		 data.add(zweiA);
		 Stein drei = new Stein(2,2,2, Color.WHITE);
		 spielerA.setzeSpielstein(drei);
		 Spielstein dreiA = (new Spielstein(492, 455, Color.WHITE));
		 board.setzteStein(2, 2, 2, spielerA, dreiA);
		 data.add(dreiA);
		 
		 Stein vier = new Stein(0,0,1, Color.BLUE);
		 spielerB.setzeSpielstein(vier);
		 Spielstein vierB = (new Spielstein(14, 347, Color.BLUE));
		 board.setzteStein(0, 0, 1, spielerB, vierB);
		 data.add(vierB);
		 
		 Stein fünf = new Stein(1,2,1, Color.BLUE);
		 spielerB.setzeSpielstein(fünf);
		 Spielstein fünfB = (new Spielstein(614, 350, Color.BLUE));
		 board.setzteStein(1, 2, 1, spielerB, fünfB);
		 data.add(fünfB);
		 
		 Stein sechs = new Stein(2,0,0, Color.BLUE);
		 spielerA.setzeSpielstein(sechs);
		 Spielstein sechsA = (new Spielstein(255, 235, Color.WHITE));
		 board.setzteStein(2, 0, 0, spielerA, sechsA);
		 data.add(sechsA);
		
		 this.setSpielerA(spielerA);
		 this.setSpielerB(spielerB);
		 this.setVorbereitet(board);
		
		return data;
	}
	
	public List<Spielstein> createSzenario03(Spieler spielerA, Spieler spielerB, Board board) {
		List<Spielstein> data = new ArrayList<>(); 
		// Bereite die Spieler vor.
		spielerA.setAnzahlSpielZüge(25);
		spielerA.setAnzahlSteine(9);
		spielerA.setSpielPhase(1);
		spielerA.setTempspielPhase(1);
		spielerB.setAnzahlSpielZüge(25);
		spielerB.setAnzahlSteine(6);
		spielerB.setSpielPhase(1);
		spielerB.setTempspielPhase(1);
		
		List<Stein> mühleSet = new ArrayList<>();
		// Setze die 6 Steine auf.
		 Stein eins = new Stein(0,2,0, Color.WHITE);
		 spielerA.setzeSpielstein(eins);
		 mühleSet.add(eins);
		 Spielstein einsA = (new Spielstein(730, 12, Color.WHITE));
		 board.setzteStein(0, 2, 0, spielerA, einsA);
		 data.add(einsA);
		 Stein zwei = new Stein(1,1,2, Color.WHITE);
		 spielerA.setzeSpielstein(zwei);
		 Spielstein zweiA = (new Spielstein(371, 568, Color.WHITE));
		 board.setzteStein(1, 1, 2, spielerA, zweiA);
		 data.add(zweiA);
		 Stein drei = new Stein(2,2,2, Color.WHITE);
		 spielerA.setzeSpielstein(drei);
		 Spielstein dreiA = (new Spielstein(492, 455, Color.WHITE));
		 board.setzteStein(2, 2, 2, spielerA, dreiA);
		 data.add(dreiA);
		 Stein einsB = new Stein(0,0,0, Color.WHITE);
		 spielerA.setzeSpielstein(einsB);
		 mühleSet.add(einsB);
		 Spielstein einsBS = (new Spielstein(15, 11, Color.WHITE));
		 board.setzteStein(0, 0, 0, spielerA, einsBS);
		 data.add(einsBS);
		 Stein zweiB = new Stein(0,1,0, Color.WHITE);
		 spielerA.setzeSpielstein(zweiB);
		 mühleSet.add(zweiB);
		 Spielstein zweiBS = (new Spielstein(368, 6, Color.WHITE));
		 board.setzteStein(0, 1, 0, spielerA, zweiBS);
		 data.add(zweiBS);
		 Stein dreiB = new Stein(1,0,1, Color.WHITE);
		 spielerA.setzeSpielstein(dreiB);
		 Spielstein dreiBS = (new Spielstein(135, 347, Color.WHITE));
		 board.setzteStein(1, 0, 1, spielerA, dreiBS);
		 data.add(dreiBS);
		 Stein einsC = new Stein(2,0,1, Color.WHITE);
		 spielerA.setzeSpielstein(einsC);
		 Spielstein einsCS = (new Spielstein(255, 235, Color.WHITE));
		 board.setzteStein(2, 0, 1, spielerA, einsCS);
		 data.add(einsCS);
		 Stein zweiC = new Stein(2,0,2, Color.WHITE);
		 spielerA.setzeSpielstein(zweiC);
		 Spielstein zweiCS = (new Spielstein(255, 455, Color.WHITE));
		 board.setzteStein(2, 0, 2, spielerA, zweiCS);
		 data.add(zweiCS);
		 Stein dreiC = new Stein(2,2,1, Color.WHITE);
		 spielerA.setzeSpielstein(dreiC);
		 Spielstein dreiCS = (new Spielstein(492, 348, Color.WHITE));
		 board.setzteStein(2, 2, 1, spielerA, dreiCS);
		 data.add(dreiCS);
		 
		 Stein vier = new Stein(0,2,0, Color.BLUE);
		 spielerB.setzeSpielstein(vier);
		 Spielstein vierB = (new Spielstein(730, 12, Color.BLUE));
		 board.setzteStein(0, 2, 0, spielerB, vierB);
		 data.add(vierB);
		 Stein fünf = new Stein(1,2,1, Color.BLUE);
		 spielerB.setzeSpielstein(fünf);
		 Spielstein fünfB = (new Spielstein(614, 350, Color.BLUE));
		 board.setzteStein(1, 2, 1, spielerB, fünfB);
		 data.add(fünfB);
		 Stein sechs = new Stein(2,0,0, Color.BLUE);
		 spielerA.setzeSpielstein(sechs);
		 Spielstein sechsA = (new Spielstein(255, 235, Color.WHITE));
		 board.setzteStein(2, 0, 0, spielerA, sechsA);
		 data.add(sechsA);
		 Stein vierB1 = new Stein(1,0,0, Color.BLUE);
		 spielerB.setzeSpielstein(vierB1);
		 Spielstein vierBS = (new Spielstein(134, 123, Color.BLUE));
		 board.setzteStein(1, 0, 0, spielerB, vierBS);
		 data.add(vierBS);
		 Stein fünfB1 = new Stein(2,1,0, Color.BLUE);
		 spielerB.setzeSpielstein(fünfB1);
		 Spielstein fünfBS = (new Spielstein(373, 124, Color.BLUE));
		 board.setzteStein(2, 1, 0, spielerB, fünfBS);
		 data.add(fünfBS);
		 Stein sechsB1 = new Stein(0,1,2, Color.BLUE);
		 spielerA.setzeSpielstein(sechsB1);
		 Spielstein sechsBS = (new Spielstein(371, 682, Color.WHITE));
		 board.setzteStein(0, 1, 2, spielerA, sechsBS);
		 data.add(sechsBS);
		 
		 spielerA.addMuehle(new Mühle(0, mühleSet));
		 this.setSpielerA(spielerA);
		 this.setSpielerB(spielerB);
		 this.setVorbereitet(board);
		
		return data;
	}
}

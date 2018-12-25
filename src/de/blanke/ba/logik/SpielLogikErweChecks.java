package de.blanke.ba.logik;

import java.util.ArrayList;
import java.util.List;

import de.blanke.ba.model.Feld;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;

/**
 * Diese Klasse stellt Methoden zur Verfügung,
 * die im Kontext der WBS verwendet werden sollen.
 * @author Paul Blanke
 *
 */
public class SpielLogikErweChecks {
	/**
	 * Diese Methode untersucht ob zwei Steine nebeneinander liegen.
	 * @param data Liste von Steinen
	 * @return zwei Steinen neben einander
	 */
	public List<Stein> sucheZweiInGleicherReihe(List<Stein> data, Board board) {
		
		List<Stein> rueckgabe = new ArrayList<>();
		// Durchlaufe alle Steine, nehme den ersten aus der Liste und suche nach passenden.
		for(int i = 0; i < data.size(); i++) {
			Stein eins = data.remove(0);
			for(Stein stein: data) {
				if(eins.equalsReihe(stein)) {
					rueckgabe.add(eins);
					rueckgabe.add(stein);
					i= data.size();
					break;
				}
			}
		}
		if(!rueckgabe.isEmpty()) {
			List<Feld> freieFelder = rueckgabe.get(0).convertToFeld().allefreienNachbarn(board);
			if(!freieFelder.isEmpty()) {
				rueckgabe.clear();
				rueckgabe.add(freieFelder.get(0).convertToStein());
			} else {
				freieFelder = rueckgabe.get(1).convertToFeld().allefreienNachbarn(board);
				rueckgabe.clear();
				rueckgabe.add(freieFelder.get(0).convertToStein());
			}
		} else {
			System.out.println("Es wurden keine zwei Steine in einer Reihe gefunden!");
		}
		
		return rueckgabe;
		
	}
	
	/**
	 * Diese Methode liefert einen freies Feld + einen Stein in der Nähe dazu.
	 * @param data Die Liste aller Steine
	 * @param board Eingabeparameter
	 * @return Liste von Steine: 0 freies Feld, 1  nächster Stein
	 */
	public List<Stein> sucheZweiGleicheReiheUnddrittenSteinDazu(List<Stein> data, Board board) {

		List<Stein> rueckgabe = new ArrayList<>();
		// Durchlaufe alle Steine, nehme den ersten aus der Liste und suche nach passenden.
		for(int i = 0; i < data.size(); i++) {
			Stein eins = data.remove(0);
			for(Stein stein: data) {
				if(eins.equalsReihe(stein)) {
					rueckgabe.add(eins);
					rueckgabe.add(stein);
					data.remove(stein);
					i= data.size();
					break;
				}
			}
		}
		Stein eins = null;
		if(!rueckgabe.isEmpty()) {
			eins = rueckgabe.get(0);
			List<Feld> freieFelder = rueckgabe.get(0).convertToFeld().allefreienNachbarn(board);
			if(!freieFelder.isEmpty()) {
				rueckgabe.clear();
				rueckgabe.add(freieFelder.get(0).convertToStein());
			} else {
				freieFelder = rueckgabe.get(1).convertToFeld().allefreienNachbarn(board);
				rueckgabe.clear();
				rueckgabe.add(freieFelder.get(0).convertToStein());
			}
			for(Stein s: data) {
				if(eins.getRing() == s.getRing()) {
					rueckgabe.add(s);
					break;
				}
			}
			
			
		} else {
			System.out.println("Es wurden keine zwei Steine in einer Reihe gefunden!");
		}
		
		
		
		
		return rueckgabe;
	}
	
	/**
	 * Diese Methode sucht einen Stein in im selben Ring heraus
	 * @param data
	 * @param spieler
	 * @return Eine Liste aller Steine im selben Ring
	 */
	public List<Stein> sucheSteinInderNäheUmGegnerZuBlocken(List<Stein> data, Spieler spieler) {
		List<Stein> rueckgabe = new ArrayList<>();
		Stein toSearch = data.get(0);
		for(Stein s: spieler.getPosiSteine()) {
			if(toSearch.getRing() == s.getRing()) {
				rueckgabe.add(s);
				
			}
		}
		return rueckgabe;
	}
}

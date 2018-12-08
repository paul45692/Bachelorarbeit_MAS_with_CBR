package de.blanke.ba.logik;

import java.util.ArrayList;
import java.util.List;

import de.blanke.ba.model.Feld;
import de.blanke.ba.model.Stein;

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
		// Für Funde:
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
	

}

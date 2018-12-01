package de.blanke.ba.logik;

import java.util.ArrayList;
import java.util.List;

import de.blanke.ba.model.Stein;

/**
 * Diese Klasse stellt Methoden zur Verfügung,
 * die im Kontext der WBS verwendet werden sollen.
 * @author Paul Blanke
 *
 */
public class Hilfsklasse {
	/**
	 * Diese Methode untersucht ob zwei Steine nebeneinander liegen.
	 * @param data Liste von Steinen
	 * @return zwei Steinen neben einander
	 */
	public List<Stein> sucheZweiInGleicherReihe(List<Stein> data) {
		List<Stein> rueckgabe = new ArrayList<>();
		for(int i = 0; i < data.size(); i++) {
			Stein eins = data.get(i);
			for(Stein stein: data) {
				
			}
		}
		
		
		
		
		
		return rueckgabe;
		
	}
	

}

package de.blanke.ba.cbr;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import de.blanke.ba.logik.Board;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.model.IntegerDesc;
/**
 * 
 * Diese Klasse versucht eine L�sung zu adaptieren.
 * Die zweite Methode in dieser Klasse analysiert eine m�gliche Lsg. und
 * pr�ft ob die passenden Felder frei sind.
 * @author Paul Blanke
 *
 */
public class CBR_AdaptionProcess {
	/**
	 * Diese Methode versucht eine Verbesserung an einen vorhanden Fall vorzunehmen
	 * @return
	 */
	protected Instance provideNewCase(Instance instance1, Instance instance2, IntegerDesc intA, IntegerDesc intB) {
		try {
			Integer l�sungA = Integer.parseInt(instance2.getAttForDesc(intA).getValueAsString());
			Integer l�sungB = Integer.parseInt(instance2.getAttForDesc(intB).getValueAsString());
			instance1.addAttribute("L�sungfeld A", l�sungA);
			instance1.addAttribute("L�sungsfeld B", l�sungB);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance1;
	}
	/**
	 * Diese Methode untersucht die (vorgeschlagende) L�sung.
	 * @return L�sung ist in Ordnung oder nicht.
	 */
	protected List<Stein> evaluateSolution(Spieler spieler, Board board, Stein start, Stein ziel) {
		List<Stein> rueckgabe = new ArrayList<>();
		switch (spieler.getSpielPhase()) {
		
			case 0: 		if(board.checkAufBelegtFeld(start.convertToFeld()) == false) {
								rueckgabe.add(start);
							} else if(board.checkAufBelegtFeld(ziel.convertToFeld()) == false) {
								rueckgabe.add(ziel);
							}
							break;
							
			case 1:         if(spieler.getPosiSteine().contains(start) && !board.checkAufBelegtFeld(ziel.convertToFeld())) {
								rueckgabe.add(start);
								rueckgabe.add(ziel);
							}
							break;
							
			case 2:         if(spieler.getPosiSteine().contains(start) && !board.checkAufBelegtFeld(ziel.convertToFeld())) {
								rueckgabe.add(start);
								rueckgabe.add(ziel);
							}
							break;
							
			case 3:			if(board.checkAufBelegtFeld(start.convertToFeld()) && !spieler.getPosiSteine().contains(start))  {
								rueckgabe.add(start);
							}
							else if (board.checkAufBelegtFeld(ziel.convertToFeld()) && !spieler.getPosiSteine().contains(ziel)) {
								rueckgabe.add(ziel);
							}
							break;	
		}	
		return rueckgabe;
	}
}

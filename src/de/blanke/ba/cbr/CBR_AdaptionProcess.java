package de.blanke.ba.cbr;

import java.text.ParseException;

import de.blanke.ba.logik.Board;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.model.IntegerDesc;

/**
 * Diese Klasse versucht bei einer ung�ltigen L�sung oder gar keiner L�sung
 * eine Fallanpassung zu provozieren.
 * @author paul4
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
	protected boolean evaluateSolution(Spieler spieler, Board board, Stein start, Stein ziel) {
		boolean rueckgabe = false;
		// Analyse abh�ngig von der Spielphase
		switch (spieler.getSpielPhase()) {
		
			case 0: 		if(board.checkFeld(start.convertToFeld()) || board.checkFeld(ziel.convertToFeld())) {
								rueckgabe = true;
							}
							break;
							
			case 1:         if(spieler.getPosiSteine().contains(start) && board.checkFeld(ziel.convertToFeld())) {
								rueckgabe = true;
							}
							break;
							
			case 2:         if(spieler.getPosiSteine().contains(start) && board.checkFeld(ziel.convertToFeld())) {
								rueckgabe = true;
							}
							break;
							
			case 3:			if((!board.checkFeld(start.convertToFeld()) && !spieler.getPosiSteine().contains(start)) 
							|| (!board.checkFeld(ziel.convertToFeld()) && !spieler.getPosiSteine().contains(ziel))) {
								rueckgabe = true;
							}
							break;	
		}
			
		return rueckgabe;
		
	}

}

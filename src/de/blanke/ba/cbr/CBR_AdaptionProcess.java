package de.blanke.ba.cbr;

import java.text.ParseException;

import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.model.IntegerDesc;

/**
 * Diese Klasse versucht bei einer ungültigen Lösung oder gar keiner Lösung
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
			Integer lösungA = Integer.parseInt(instance2.getAttForDesc(intA).getValueAsString());
			Integer lösungB = Integer.parseInt(instance2.getAttForDesc(intB).getValueAsString());
			instance1.addAttribute("Lösungfeld A", lösungA);
			instance1.addAttribute("Lösungsfeld B", lösungB);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return instance1;
		
	}

}

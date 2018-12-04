package de.blanke.ba.cbr;

import java.util.Arrays;
import java.util.HashSet;

import de.dfki.mycbr.core.DefaultCaseBase;
import de.dfki.mycbr.core.Project;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.model.Concept;
import de.dfki.mycbr.core.model.SymbolDesc;
import de.dfki.mycbr.core.retrieval.Retrieval;

/**
 * Diese Klasse stellt die notwendigen Daten bereit,
 * um ein CBR Project zu laden.
 * @author Paul Blanke
 *
 */
public class CBR_Engine {
	
	
	
	public Project createCBRProject() {
			Project p = null;
			Concept c = null;
			
			
			try {
				p = new Project();
				c = p.createTopConcept("Mühle_Basis");
				HashSet<String> data = new HashSet<String>();
				String[] dataNames = {"A","B","C"};
				data.addAll(Arrays.asList(dataNames));
				SymbolDesc counter = new SymbolDesc(c,"Counter", data);
				
				DefaultCaseBase cb = p.createDefaultCB("myCaseBase");
				Instance i = c.addInstance("Fall 1");
				i.addAttribute(counter, "A");
				cb.addCase(i);
				System.out.print("Erfolgreich erstellt");
				
				Retrieval r = new Retrieval(c, cb);
				Instance q = r.getQueryInstance();
				q.addAttribute(counter.getName(),counter.getAttribute("A"));
				r.start();
				System.out.println("Abgeschickt: " + r.entrySet().size());
				p.save();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return p;
	}
	
}

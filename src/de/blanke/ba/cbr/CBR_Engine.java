package de.blanke.ba.cbr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import de.dfki.mycbr.core.DefaultCaseBase;
import de.dfki.mycbr.core.Project;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.model.Concept;
import de.dfki.mycbr.core.model.SymbolDesc;
import de.dfki.mycbr.core.retrieval.Retrieval;
import de.dfki.mycbr.core.similarity.Similarity;

/**
 * Diese Klasse stellt die notwendigen Daten bereit,
 * um ein CBR Project zu laden.
 * @author Paul Blanke
 *
 */
public class CBR_Engine {
	
	private String projectPfad = "C:\\Users\\paul4\\git\\bachelorarbeitAgentenCBR\\src\\res\\";
	private String projectName = "mühle_spiel.prj";
	
	
	
	public Project loadCBRProject() {
			Project  p = null;
			
			
			try {
				p = new Project(projectPfad + projectName);
				Thread.sleep(200);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Das System wurde geladen!");
		
		return p;
	}
	
}

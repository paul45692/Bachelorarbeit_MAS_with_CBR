package de.blanke.ba.cbr;

import de.dfki.mycbr.core.Project;

/**
 * Diese Klasse stellt die notwendigen Daten bereit,
 * um ein CBR Project zu laden.
 * @author Paul Blanke
 *
 */
public class CBR_Engine {
	
	private String data_path = "src\\res\\*";
	private String projectName = "mühle.prj";
	
	public Project loadCBRProject() {
			Project project = null;
		try {
			 project = new Project(data_path + projectName);
		} catch (Exception e) {
			System.out.println("Das Einlesen des Projektes ist fehlgeschlagen!");
			e.printStackTrace();
		}
		return project;
	}
	
}

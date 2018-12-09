package de.blanke.ba.cbr;


import de.dfki.mycbr.core.Project;
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

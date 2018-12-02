package de.blanke.ba.mas;

import java.util.ArrayList;
import java.util.List;

import de.blanke.ba.model.Spielstein;
import de.blanke.ba.model.Stein;

/**
 * Diese Klasse stellt Methoden bereit um die Agenten zu unterstützen.
 * @author Paul Blanke
 *
 */
public class AgentenOperations {
	
	private List<Stein> fixeSteineRing1 = new ArrayList<>();
	private List<Stein> fixeSteineRing2 = new ArrayList<>();
	private List<Stein> fixeSteineRing3 = new ArrayList<>();
	private List<Stein> alleSteineFesteReihenFolge = new ArrayList<>();
	
	
	/**
	 * Setzte die Steine auf für das MAS.
	 */
	private void setzeSpielsteineAuf() {
		// Add fixe Spielsteine für den ersten Ring
		fixeSteineRing1.add(new Stein(0,0,0,null, new Spielstein(15,11, null)));
		fixeSteineRing1.add(new Stein(0,1,0,null, new Spielstein(368, 6, null)));
		fixeSteineRing1.add(new Stein(0,2,0,null, new Spielstein(730, 12, null)));
		fixeSteineRing1.add(new Stein(0,0,1,null, new Spielstein( 14, 347, null)));
		fixeSteineRing1.add(new Stein(0,0,2,null, new Spielstein(15, 681, null)));
		fixeSteineRing1.add(new Stein(0,1,2,null, new Spielstein( 371, 682, null)));
		fixeSteineRing1.add(new Stein(0,2,2,null, new Spielstein(731, 678, null)));
		fixeSteineRing1.add(new Stein(0,2,1,null, new Spielstein(731, 350, null)));
		// Add fixe Spielsteine für den ersten Ring
		fixeSteineRing2.add(new Stein(1,0,0,null, new Spielstein(134,123, null)));
		fixeSteineRing2.add(new Stein(1,1,0,null, new Spielstein(370, 124, null)));
		fixeSteineRing2.add(new Stein(1,2,0,null, new Spielstein(609, 343, null)));
		fixeSteineRing2.add(new Stein(1,0,1,null, new Spielstein( 135, 347, null)));
		fixeSteineRing2.add(new Stein(1,0,2,null, new Spielstein(135, 568, null)));
		fixeSteineRing2.add(new Stein(1,1,2,null, new Spielstein( 371, 568, null)));
		fixeSteineRing2.add(new Stein(1,2,2,null, new Spielstein(614, 568, null)));
		fixeSteineRing2.add(new Stein(1,2,1,null, new Spielstein(614, 350, null)));
		// Add fixe Spielsteine für den dritten Ring
		fixeSteineRing3.add(new Stein(2,0,0,null, new Spielstein(255,235, null)));
		fixeSteineRing3.add(new Stein(2,1,0,null, new Spielstein(373, 235, null)));
		fixeSteineRing3.add(new Stein(2,2,0,null, new Spielstein(493, 236, null)));
		fixeSteineRing3.add(new Stein(2,0,1,null, new Spielstein(255, 345, null)));
		fixeSteineRing3.add(new Stein(2,0,2,null, new Spielstein(255, 455, null)));
		fixeSteineRing3.add(new Stein(2,1,2,null, new Spielstein(370, 455, null)));
		fixeSteineRing3.add(new Stein(2,2,2,null, new Spielstein(492, 455, null)));
		fixeSteineRing3.add(new Stein(2,2,1,null, new Spielstein(494, 348, null)));
		
		alleSteineFesteReihenFolge.add(new Stein(0,0,0,null, new Spielstein(15,11, null)));
		alleSteineFesteReihenFolge.add(new Stein(0,0,0,null, new Spielstein(15,11, null)));
		alleSteineFesteReihenFolge.add(new Stein(0,1,0,null, new Spielstein(368, 6, null)));
		alleSteineFesteReihenFolge.add(new Stein(0,2,0,null, new Spielstein(730, 12, null)));
		alleSteineFesteReihenFolge.add(new Stein(0,2,1,null, new Spielstein(731, 350, null)));
		alleSteineFesteReihenFolge.add(new Stein(0,2,2,null, new Spielstein(731, 678, null)));
		alleSteineFesteReihenFolge.add(new Stein(0,1,2,null, new Spielstein( 371, 682, null)));
		alleSteineFesteReihenFolge.add(new Stein(0,0,2,null, new Spielstein(15, 681, null)));
		alleSteineFesteReihenFolge.add(new Stein(0,0,1,null, new Spielstein( 14, 347, null)));
		
		alleSteineFesteReihenFolge.add(new Stein(1,0,0,null, new Spielstein(134,123, null)));
		alleSteineFesteReihenFolge.add(new Stein(1,1,0,null, new Spielstein(370, 124, null)));
		alleSteineFesteReihenFolge.add(new Stein(1,2,0,null, new Spielstein(609, 343, null)));
		alleSteineFesteReihenFolge.add(new Stein(1,2,1,null, new Spielstein(614, 350, null)));
		alleSteineFesteReihenFolge.add(new Stein(1,2,2,null, new Spielstein(614, 568, null)));
		alleSteineFesteReihenFolge.add(new Stein(1,1,2,null, new Spielstein( 371, 568, null)));
		alleSteineFesteReihenFolge.add(new Stein(1,0,2,null, new Spielstein(135, 568, null)));
		alleSteineFesteReihenFolge.add(new Stein(1,0,1,null, new Spielstein( 135, 347, null)));
		
		alleSteineFesteReihenFolge.add(new Stein(2,0,0,null, new Spielstein(255,235, null)));
		alleSteineFesteReihenFolge.add(new Stein(2,1,0,null, new Spielstein(373, 235, null)));
		alleSteineFesteReihenFolge.add(new Stein(2,2,0,null, new Spielstein(493, 236, null)));
		alleSteineFesteReihenFolge.add(new Stein(2,2,1,null, new Spielstein(494, 348, null)));
		alleSteineFesteReihenFolge.add(new Stein(2,2,2,null, new Spielstein(492, 455, null)));
		alleSteineFesteReihenFolge.add(new Stein(2,1,2,null, new Spielstein(370, 455, null)));
		alleSteineFesteReihenFolge.add(new Stein(2,0,2,null, new Spielstein(255, 455, null)));
		alleSteineFesteReihenFolge.add(new Stein(2,0,1,null, new Spielstein(255, 345, null)));
		
		
	}
	
	
	public AgentenOperations() {
		this.setzeSpielsteineAuf();
	}
	
	public Spielstein wandeleSteinzuSpielSteinUm(Stein stein) {

		Spielstein rueckgabe = null;
		if(stein.getRing() == 0) {
			for(Stein s: fixeSteineRing1) {
				if((s.getxCord() == stein.getxCord())&& (s.getyCord() == s.getyCord())) {
					rueckgabe = s.getSpielstein();
					break;
				}
			}
			
		}else if(stein.getRing() == 1) {
			for(Stein s: fixeSteineRing2) {
				if((s.getxCord() == stein.getxCord())&& (s.getyCord() == s.getyCord())) {
					rueckgabe = s.getSpielstein();
					break;
				}
				
			}	
		} else {
			for(Stein s: fixeSteineRing3) {
				if((s.getxCord() == stein.getxCord())&& (s.getyCord() == s.getyCord())) {
					rueckgabe = s.getSpielstein();
					break;
				}
			}	
		}
		
		return rueckgabe;
	}
	
	/**
	 * Diese Methode holt aus der Liste der Steine einen passenden Stein für das CBR System.
	 * @param index
	 * @return
	 */
	public Stein getSteineFuerCBRSystem(int index) {
		return alleSteineFesteReihenFolge.get(index);
	}
}

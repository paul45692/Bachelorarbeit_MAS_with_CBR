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
		fixeSteineRing1.add(new Stein(0,0,1,null, new Spielstein(14, 340, null)));
		fixeSteineRing1.add(new Stein(0,0,2,null, new Spielstein(15, 660, null)));
		fixeSteineRing1.add(new Stein(0,1,2,null, new Spielstein(371, 660, null)));
		fixeSteineRing1.add(new Stein(0,2,2,null, new Spielstein(731, 660, null)));
		fixeSteineRing1.add(new Stein(0,2,1,null, new Spielstein(731, 340, null)));
		// Add fixe Spielsteine für den ersten Ring
		fixeSteineRing2.add(new Stein(1,0,0,null, new Spielstein(134,123, null)));
		fixeSteineRing2.add(new Stein(1,1,0,null, new Spielstein(370, 124, null)));
		fixeSteineRing2.add(new Stein(1,2,0,null, new Spielstein(609, 124, null)));
		fixeSteineRing2.add(new Stein(1,0,1,null, new Spielstein(135, 340, null)));
		fixeSteineRing2.add(new Stein(1,0,2,null, new Spielstein(135, 568, null)));
		fixeSteineRing2.add(new Stein(1,1,2,null, new Spielstein(371, 568, null)));
		fixeSteineRing2.add(new Stein(1,2,2,null, new Spielstein(614, 568, null)));
		fixeSteineRing2.add(new Stein(1,2,1,null, new Spielstein(614, 340, null)));
		// Add fixe Spielsteine für den dritten Ring
		fixeSteineRing3.add(new Stein(2,0,0,null, new Spielstein(255,235, null)));
		fixeSteineRing3.add(new Stein(2,1,0,null, new Spielstein(373, 235, null)));
		fixeSteineRing3.add(new Stein(2,2,0,null, new Spielstein(493, 236, null)));
		fixeSteineRing3.add(new Stein(2,0,1,null, new Spielstein(255, 340, null)));
		fixeSteineRing3.add(new Stein(2,0,2,null, new Spielstein(255, 435, null)));
		fixeSteineRing3.add(new Stein(2,1,2,null, new Spielstein(370, 435, null)));
		fixeSteineRing3.add(new Stein(2,2,2,null, new Spielstein(492, 435, null)));
		fixeSteineRing3.add(new Stein(2,2,1,null, new Spielstein(494, 340, null)));
		
		alleSteineFesteReihenFolge.add(new Stein(0,0,0,null, new Spielstein(15,11, null), 0));
		alleSteineFesteReihenFolge.add(new Stein(0,0,0,null, new Spielstein(15,11, null),1));
		alleSteineFesteReihenFolge.add(new Stein(0,1,0,null, new Spielstein(368, 6, null),2));
		alleSteineFesteReihenFolge.add(new Stein(0,2,0,null, new Spielstein(730, 12, null), 3));
		alleSteineFesteReihenFolge.add(new Stein(0,2,1,null, new Spielstein(731, 342, null), 4));
		alleSteineFesteReihenFolge.add(new Stein(0,2,2,null, new Spielstein(731, 660, null), 5));
		alleSteineFesteReihenFolge.add(new Stein(0,1,2,null, new Spielstein(371, 660, null), 6));
		alleSteineFesteReihenFolge.add(new Stein(0,0,2,null, new Spielstein(15, 660, null), 7));
		alleSteineFesteReihenFolge.add(new Stein(0,0,1,null, new Spielstein( 14, 342, null), 8));
		
		alleSteineFesteReihenFolge.add(new Stein(1,0,0,null, new Spielstein(134,123, null), 9));
		alleSteineFesteReihenFolge.add(new Stein(1,1,0,null, new Spielstein(370, 124, null), 10));
		alleSteineFesteReihenFolge.add(new Stein(1,2,0,null, new Spielstein(614, 124, null), 11));
		alleSteineFesteReihenFolge.add(new Stein(1,2,1,null, new Spielstein(614, 350, null), 12));
		alleSteineFesteReihenFolge.add(new Stein(1,2,2,null, new Spielstein(614, 568, null), 13));
		alleSteineFesteReihenFolge.add(new Stein(1,1,2,null, new Spielstein(371, 568, null), 14));
		alleSteineFesteReihenFolge.add(new Stein(1,0,2,null, new Spielstein(135, 568, null), 15));
		alleSteineFesteReihenFolge.add(new Stein(1,0,1,null, new Spielstein( 135, 347, null), 16));
		
		alleSteineFesteReihenFolge.add(new Stein(2,0,0,null, new Spielstein(255,235, null), 17));
		alleSteineFesteReihenFolge.add(new Stein(2,1,0,null, new Spielstein(373, 235, null), 18));
		alleSteineFesteReihenFolge.add(new Stein(2,2,0,null, new Spielstein(493, 236, null), 19));
		alleSteineFesteReihenFolge.add(new Stein(2,2,1,null, new Spielstein(494, 348, null), 20));
		alleSteineFesteReihenFolge.add(new Stein(2,2,2,null, new Spielstein(492, 435, null), 21));
		alleSteineFesteReihenFolge.add(new Stein(2,1,2,null, new Spielstein(370, 435, null), 22));
		alleSteineFesteReihenFolge.add(new Stein(2,0,2,null, new Spielstein(255, 435, null), 23));
		alleSteineFesteReihenFolge.add(new Stein(2,0,1,null, new Spielstein(255, 345, null), 24));
		
		
	}
	
	
	public AgentenOperations() {
		this.setzeSpielsteineAuf();
	}
	
	public Spielstein wandeleSteinzuSpielSteinUm(Stein stein) {
		Spielstein rueckgabe = null;
		if(stein.getRing() == 0) {
			for(Stein s: fixeSteineRing1) {
				if(s.equals(stein)) {
					rueckgabe = s.getSpielstein();
					break;
				}
			}
			
		} else if(stein.getRing() == 1) {
			for(Stein s: fixeSteineRing2) {
				if(s.equals(stein)) {
					rueckgabe = s.getSpielstein();
					break;
				}
				
			}	
		} else {
			for(Stein s: fixeSteineRing3) {
				if(s.equals(stein)) {
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
	
	public int getIndexFromStein(Stein stein) {
		int rueckgabe = 1;
		for(Stein s: alleSteineFesteReihenFolge) {
			if((s.getRing()== stein.getRing()) &&(s.getxCord() == stein.getxCord())&& (s.getyCord() == s.getyCord())) {
				rueckgabe = s.getIndex();
			}
		}
		return rueckgabe;
	}
}

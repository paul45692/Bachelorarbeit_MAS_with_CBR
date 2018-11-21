package de.blanke.ba.logik;

import java.util.ArrayList;
import java.util.List;
import de.blanke.ba.model.Feld;
import de.blanke.ba.model.Mühle;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;

/**
 * Diese Klasse stellt die Logik bereit um eine Mühle zu finden
 * 
 * @author paul4
 *
 */
public class MuehleDecting {
	// Attribute
	/**
	 * Diese Listen halten die Position der Mühlen
	 */
	List<Stein> posiRing1A = new ArrayList<>();
	List<Stein> posiRing1B = new ArrayList<>();
	List<Stein> posiRing1C = new ArrayList<>();
	List<Stein> posiRing1D = new ArrayList<>();
	
	List<Stein> posiRing2A = new ArrayList<>();
	List<Stein> posiRing2B = new ArrayList<>();
	List<Stein> posiRing2C = new ArrayList<>();
	List<Stein> posiRing2D = new ArrayList<>();
	
	List<Stein> posiRing3A = new ArrayList<>();
	List<Stein> posiRing3B = new ArrayList<>();
	List<Stein> posiRing3C = new ArrayList<>();
	List<Stein> posiRing3D = new ArrayList<>();
	
	List<Stein> posiRing4A = new ArrayList<>();
	List<Stein> posiRing4B = new ArrayList<>();
	List<Stein> posiRing4C = new ArrayList<>();
	List<Stein> posiRing4D = new ArrayList<>();
	
	
	
	public MuehleDecting() {
		erzeugeMühlen();
	}
	/**
	 * Diese Methode enthält die zentrale Logik der Klasse.
	 * @return gefundene Mühle oder nicht.
	 */
	public boolean findeMühle(Spieler spieler) {
		List<Stein> spielsteine = spieler.getAlleSteinePosition();
		
		List<Stein> alleRing1 = new ArrayList<>();
		List<Stein> alleRing2 = new ArrayList<>();
		List<Stein> alleRing3 = new ArrayList<>();
		boolean rueckgabe = false;
		
		for(Stein s: spielsteine) {
			if(s.getRing()== 0) {
				alleRing1.add(s);
			} else if(s.getRing() == 1) {
				alleRing2.add(s);
			} else if(s.getRing() == 2) {
				alleRing3.add(s);
			}
		}
		int counter = 0;
		if(alleRing1.size() >= 3) {
			for(Stein s: alleRing1) {
				
				for(Stein check: posiRing1A) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
					
				} else {
					counter = 0;
				}
				for(Stein check: posiRing1B) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}
				for(Stein check: posiRing1C) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}
				for(Stein check: posiRing1D) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}	
			}
			
		} else if(alleRing2.size() >= 3) {
			
			for(Stein s: alleRing2) {
				
				for(Stein check: posiRing2A) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}
				for(Stein check: posiRing2B) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}
				for(Stein check: posiRing2C) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}
				for(Stein check: posiRing2D) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
					break;
				} else {
					counter = 0;
				}	
			}
			
		} else if(alleRing3.size() >= 3) {
			for(Stein s: alleRing3) {
				for(Stein check: posiRing3A) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}
				for(Stein check: posiRing3B) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}
				for(Stein check: posiRing3C) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}
				for(Stein check: posiRing3D) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}	
			}
			
		} else {
			for(Stein s:spielsteine) {
				for(Stein check: posiRing4A) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}
				for(Stein check: posiRing4B) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}
				for(Stein check: posiRing4C) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}
				for(Stein check: posiRing4D) {
					if((s.getxCord() == check.getxCord()) && (s.getyCord() == check.getyCord())) {
						counter++;
					}
				}
				if(counter==3) {
					if(!checkObMühleBeiSpielerIst(spieler, posiRing1A)) {
						rueckgabe = true;
						break;
					} else {
						counter = 0;
					}
				} else {
					counter = 0;
				}
			}
		}
		
		
		return rueckgabe;
	}
	/**
	 * Diese Methode setzt zu Beginn die Mühle auf.
	 */
	private void erzeugeMühlen() {
		// Erster Ring(Außen)
		posiRing1A.add(new Stein(0,0,0,null));
		posiRing1A.add(new Stein(0,1,0,null));
		posiRing1A.add(new Stein(0,2,0,null));
		posiRing1B.add(new Stein(0,0,0,null));
		posiRing1B.add(new Stein(0,0,1,null));
		posiRing1B.add(new Stein(0,0,2,null));
		posiRing1C.add(new Stein(0,0,2,null));
		posiRing1C.add(new Stein(0,1,2,null));
		posiRing1C.add(new Stein(0,2,2,null));
		posiRing1D.add(new Stein(0,2,2,null));
		posiRing1D.add(new Stein(0,2,1,null));
		posiRing1D.add(new Stein(0,2,0,null));
		// zweiter Ring
		posiRing2A.add(new Stein(1,0,0,null));
		posiRing2A.add(new Stein(1,1,0,null));
		posiRing2A.add(new Stein(1,2,0,null));
		posiRing2B.add(new Stein(1,0,0,null));
		posiRing2B.add(new Stein(1,0,1,null));
		posiRing2B.add(new Stein(1,0,2,null));
		posiRing2C.add(new Stein(1,0,2,null));
		posiRing2C.add(new Stein(1,1,2,null));
		posiRing2C.add(new Stein(1,2,2,null));
		posiRing2D.add(new Stein(1,2,2,null));
		posiRing2D.add(new Stein(1,2,1,null));
		posiRing2D.add(new Stein(1,2,0,null));
		// 3. Ring (Innen)
		posiRing3A.add(new Stein(2,0,0,null));
		posiRing3A.add(new Stein(2,1,0,null));
		posiRing3A.add(new Stein(2,2,0,null));
		posiRing3B.add(new Stein(2,0,0,null));
		posiRing3B.add(new Stein(2,0,1,null));
		posiRing3B.add(new Stein(2,0,2,null));
		posiRing3C.add(new Stein(2,0,2,null));
		posiRing3C.add(new Stein(2,1,2,null));
		posiRing3C.add(new Stein(2,2,2,null));
		posiRing3D.add(new Stein(2,2,2,null));
		posiRing3D.add(new Stein(2,2,1,null));
		posiRing3D.add(new Stein(1,2,0,null));
		// Sonstige Mühlen über alle Felder
		posiRing4A.add(new Stein(0,1,0,null));
		posiRing4A.add(new Stein(1,1,0,null));
		posiRing4A.add(new Stein(2,1,0,null));
		posiRing4B.add(new Stein(0,1,2,null));
		posiRing4B.add(new Stein(1,1,2,null));
		posiRing4B.add(new Stein(2,1,2,null));
		posiRing4C.add(new Stein(0,0,1,null));
		posiRing4C.add(new Stein(1,0,1,null));
		posiRing4C.add(new Stein(2,0,1,null));
		posiRing4D.add(new Stein(0,2,1,null));
		posiRing4D.add(new Stein(1,2,1,null));
		posiRing4D.add(new Stein(2,2,1,null));
		
		
	
	}
	
	/**
	 * Diese Methode untersucht ob eine gefundene Mühle bereits zu dem Spieler gehört.
	 * Falls das denn der Fall ist wird die Mühle nicht weiter betrachetet und sonst wieder sie 
	 * hinzu gefügt.
	 * @param spieler Der aktuelle Spieler am Zug
	 * @param data eine Mühle die untersucht wird
	 * @return ja / nein
	 */
	private boolean checkObMühleBeiSpielerIst(Spieler spieler, List<Stein> data) {
		boolean rueckgabe = false;
		for(Mühle mühle: spieler.getVorhandeneMuehlen()) {
			for(Feld f: mühle.getFelder()) {
				for(Stein s: data) {
					if((f.getRingZahl() == s.getRing())&& (f.getxCord() == s.getxCord()) &&(f.getyCord() == s.getyCord()) ) {
						rueckgabe = true;
						break;
					}
				}
			}
		}
		
		if(!rueckgabe) {
			List<Feld> felder = new ArrayList<>();
			for(Stein s: data) {
				felder.add(new Feld(s.getRing(), s.getxCord(), s.getyCord()));
				System.out.println("Eine Mühle wurde bei Spieler: " + spieler.getSpielFarbe() + " angefügt!");
				
			}
			
			spieler.addMuehle(new Mühle(felder));
		}
		return rueckgabe;
		
	}
}

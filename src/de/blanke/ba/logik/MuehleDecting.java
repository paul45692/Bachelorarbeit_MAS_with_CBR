package de.blanke.ba.logik;

import java.util.ArrayList;
import java.util.List;
import de.blanke.ba.model.M�hle;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;

/**
 * Diese Klasse untersucht, ob ein Spieler eine M�hle erzeugt hat.
 * @author Paul Blanke
 *
 */
public class MuehleDecting {
// Attribute
	/**
	 * Diese Listen halten die Position der M�hlen
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
	
	List<M�hle> alleM�hlen = new ArrayList<>();
	List<M�hle> tempGefunden = new ArrayList<>();
	
	public MuehleDecting() {
		erzeugeM�hlen();
	}
	/**
	 * Diese Methode enth�lt die zentrale Logik der Klasse.
	 * @return gefundene M�hle oder nicht.
	 */
	public boolean findeM�hle(Spieler spieler) {
		List<Stein> spielsteine = spieler.getPosiSteine();
		// Sortiere die Steine nach den Ringen
		List<Stein> alleRing1 = new ArrayList<>();
		List<Stein> alleRing2 = new ArrayList<>();
		List<Stein> alleRing3 = new ArrayList<>();
	
		// Splitte auf
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
			
				for(Stein check: posiRing1A) {
					for(Stein s:alleRing1) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(0));
						}
					}
				}
				counter = 0;
				for(Stein check: posiRing1B) {
					for(Stein s:alleRing1) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(1));
						}
					}
				}
				counter = 0;
			
				for(Stein check: posiRing1C) {
					for(Stein s:alleRing1) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(2));
						}
					}
				}
				counter = 0;
				
				for(Stein check: posiRing1D) {
					for(Stein s:alleRing1) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(3));
						}
					}
				}
				counter = 0;
			
			
		} else if(alleRing2.size() >= 3) {
			
			
				for(Stein check: posiRing2A) {
					for(Stein s:alleRing2) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(4));
						}
					}
				}
				counter = 0;
				for(Stein check: posiRing2B) {
					for(Stein s:alleRing2) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(5));
						}
					}
				}
				counter = 0;
				for(Stein check: posiRing2C) {
					for(Stein s:alleRing2) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(6));
						}
					}
				}
				counter = 0;
				for(Stein check: posiRing2D) {
					for(Stein s:alleRing2) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(7));
						}
					}
				}
				counter = 0;
			
			
		} else if(alleRing3.size() >= 3) {
			
				for(Stein check: posiRing3A) {
					for(Stein s:alleRing3) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(8));
						}
					}
				}
				counter = 0;
				
				for(Stein check: posiRing3B) {
					for(Stein s:alleRing3) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(9));
						}
					}
				}
				counter = 0;
				
				for(Stein check: posiRing3C) {
					for(Stein s:alleRing3) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(10));
						}
					}
				}
				counter = 0;
				
				for(Stein check: posiRing3D) {
					for(Stein s:alleRing3) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(11));
						}
					}
				}
				counter = 0;
			
			
		} else {
			
				for(Stein check: posiRing4A) {
					for(Stein s:spielsteine) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(12));
						}
					}
				}
				counter = 0;
				
				for(Stein check: posiRing4B) {
					for(Stein s:spielsteine) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(13));
						}
					}
				}
				counter = 0;
				
				for(Stein check: posiRing4C) {
					for(Stein s:spielsteine) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(14));
						}
					}
				}
				counter = 0;
				
				for(Stein check: posiRing4D) {
					for(Stein s: spielsteine) {
						if(s.equals(check)) {
							counter++;
						} else if(counter == 3) {
							tempGefunden.add(alleM�hlen.get(15));
						}
					}
				}
				counter = 0;
			}
	
		
		return pruefeAufvorHandeneM�hlen(spieler);
	}
	/**
	 * Diese Methode untersucht ob die gefundenen M�hlen schon zum Spieler geh�ren.
	 * @param spieler
	 * @return
	 */
	private boolean pruefeAufvorHandeneM�hlen(Spieler spieler) {
		boolean rueckgabe = false;
		List<M�hle> vorh_Data = spieler.getVorhandeneMuehlen();
		// Wenn keine M�hlen vorhanden sind, dannn f�ge die erste dazu
		if(vorh_Data.isEmpty() && !tempGefunden.isEmpty()) {
			
			spieler.addMuehle(tempGefunden.get(0));
			rueckgabe = true;
			
		} else if(vorh_Data.size() > 0 && tempGefunden.size() > 0)  {
			// Sonst vergleich die vorhanden M�hlen mit den gefundenen M�hlen
			for(M�hle m�hle: tempGefunden) {
				for(M�hle vergleich:vorh_Data) {
					if(m�hle.getIndex() == vergleich.getIndex()) {
						tempGefunden.remove(m�hle);
						vorh_Data.remove(vergleich);
						break;
					}
				}
			} 
			if(!tempGefunden.isEmpty()) {
				
				for(M�hle m�hle: tempGefunden) {
					spieler.addMuehle(m�hle);
					rueckgabe = true;
				}
				
			} else if(tempGefunden.isEmpty() && vorh_Data.size()> 0) {
				spieler.getVorhandeneMuehlen().clear();
			} 
			
		}
		// Das ist wichtig um eine leere Liste am Ende zu haben.
		tempGefunden.clear();
		return rueckgabe;
	}
	
	/**
	 * Diese Methode setzt zu Beginn die M�hle auf.
	 * Dabei werden 16 m�gliche Positionen getrackt.
	 */
	private void erzeugeM�hlen() {
		// Erster Ring(Au�en)
		posiRing1A.add(new Stein(0,0,0,null));
		posiRing1A.add(new Stein(0,1,0,null));
		posiRing1A.add(new Stein(0,2,0,null));
		alleM�hlen.add(new M�hle(0, posiRing1A));
		posiRing1B.add(new Stein(0,0,0,null));
		posiRing1B.add(new Stein(0,0,1,null));
		posiRing1B.add(new Stein(0,0,2,null));
		alleM�hlen.add(new M�hle(1, posiRing1B));
		posiRing1C.add(new Stein(0,0,2,null));
		posiRing1C.add(new Stein(0,1,2,null));
		posiRing1C.add(new Stein(0,2,2,null));
		alleM�hlen.add(new M�hle(2, posiRing1C));
		posiRing1D.add(new Stein(0,2,2,null));
		posiRing1D.add(new Stein(0,2,1,null));
		posiRing1D.add(new Stein(0,2,0,null));
		alleM�hlen.add(new M�hle(3, posiRing1D));
		
		// Alle Positionen
		posiRing2A.add(new Stein(1,0,0,null));
		posiRing2A.add(new Stein(1,1,0,null));
		posiRing2A.add(new Stein(1,2,0,null));
		alleM�hlen.add(new M�hle(4, posiRing2A));
		posiRing2B.add(new Stein(1,0,0,null));
		posiRing2B.add(new Stein(1,0,1,null));
		posiRing2B.add(new Stein(1,0,2,null));
		alleM�hlen.add(new M�hle(5, posiRing2B));
		posiRing2C.add(new Stein(1,0,2,null));
		posiRing2C.add(new Stein(1,1,2,null));
		posiRing2C.add(new Stein(1,2,2,null));
		alleM�hlen.add(new M�hle(6, posiRing2C));
		posiRing2D.add(new Stein(1,2,2,null));
		posiRing2D.add(new Stein(1,2,1,null));
		posiRing2D.add(new Stein(1,2,0,null));
		alleM�hlen.add(new M�hle(7, posiRing2D));
		
		// 3. Ring (Innen)
		posiRing3A.add(new Stein(2,0,0,null));
		posiRing3A.add(new Stein(2,1,0,null));
		posiRing3A.add(new Stein(2,2,0,null));
		alleM�hlen.add(new M�hle(8, posiRing3A));
		posiRing3B.add(new Stein(2,0,0,null));
		posiRing3B.add(new Stein(2,0,1,null));
		posiRing3B.add(new Stein(2,0,2,null));
		alleM�hlen.add(new M�hle(9, posiRing3B));
		posiRing3C.add(new Stein(2,0,2,null));
		posiRing3C.add(new Stein(2,1,2,null));
		posiRing3C.add(new Stein(2,2,2,null));
		alleM�hlen.add(new M�hle(10, posiRing3C));
		posiRing3D.add(new Stein(2,2,2,null));
		posiRing3D.add(new Stein(2,2,1,null));
		posiRing3D.add(new Stein(2,2,0,null));
		alleM�hlen.add(new M�hle(11, posiRing3D));
		// Sonstige M�hlen �ber alle Felder
		posiRing4A.add(new Stein(0,1,0,null));
		posiRing4A.add(new Stein(1,1,0,null));
		posiRing4A.add(new Stein(2,1,0,null));
		alleM�hlen.add(new M�hle(12, posiRing4A));
		posiRing4B.add(new Stein(0,1,2,null));
		posiRing4B.add(new Stein(1,1,2,null));
		posiRing4B.add(new Stein(2,1,2,null));
		alleM�hlen.add(new M�hle(13, posiRing4B));
		posiRing4C.add(new Stein(0,0,1,null));
		posiRing4C.add(new Stein(1,0,1,null));
		posiRing4C.add(new Stein(2,0,1,null));
		alleM�hlen.add(new M�hle(14, posiRing4C));
		posiRing4D.add(new Stein(0,2,1,null));
		posiRing4D.add(new Stein(1,2,1,null));
		posiRing4D.add(new Stein(2,2,1,null));
		alleM�hlen.add(new M�hle(15, posiRing4D));
		
		
	
	}
	
}

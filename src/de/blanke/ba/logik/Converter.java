package de.blanke.ba.logik;

import java.util.ArrayList;
import java.util.List;
import de.blanke.ba.model.Feld;
import de.blanke.ba.model.M�hle;
import de.blanke.ba.model.Spielstein;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;

public class Converter {
	
	/**
	 * Diese Methode soll M�hlen erkennen und aufstellen
	 */
	public boolean ermitteleM�hle(Spieler spieler, Board board) {
		// Es muss f�r alle Steine eines Spieler �berlegt werden, ob sie in einer M�hle sind
		// Variablen
		List<Stein> data = spieler.getAlleSteinePosition();
		List<Feld> felder = new ArrayList<Feld>();
		List<Feld> m�hlePos = new ArrayList<Feld>();
		M�hle m�hle = null;
		
		// mache aus den Steinen Felder
		for(Stein stein: data) {
			felder.add(board.gebeFeldZurueck(stein.getRing(), stein.getxCord(), stein.getyCord()));
		}
		// Durchlaufe alle Felder und entferne eins und vergleiche es mit den anderen
		for(int i = 0; i < felder.size(); i++) {
			Feld feld = felder.remove(i);
			m�hlePos.add(feld);
			// Durchsuche andere Felder
			for(Feld f: felder) {
				if(f.getxCord() == feld.getxCord()) {
					m�hlePos.add(f);
				} else if(f.getyCord() == feld.getyCord()) {
					m�hlePos.add(f);
				}
			}
			
			// Wenn nach dem Durchlauf 3 Felder vorhanden sind
			if(m�hlePos.size() == 3) {
				
				m�hle = new M�hle(m�hlePos);
				spieler.addMuehle(m�hle);
				m�hlePos.clear();
				// Setze die Steine auf protected durch M�hle
				
				
			} else {
				m�hlePos.clear();
			}
			
			
		}
		
		 boolean rueckgabe = false;
		// laufe �ber alle neun Steine r�ber.
		
		
		return rueckgabe;
	}
	
	public Feld ermitteleFeld(int xCord, int yCord) {
		int ring = 0;
		int x = 0;
		int y = 0;
		
		
		if(xCord < 30) {
			
			if(yCord < 30) {
				// 0, 0, 0
				return new Feld(ring, x, y);
			} else if(340 < yCord && yCord < 360) {
				// 0,0,1
				y = 1;
				return new Feld(ring, x, y);
				
			} else if(665 < yCord && yCord < 690) {
				// 0,0,2
				y = 2;
				return new Feld(ring, x, y);
			} else {
				System.out.println("Das Feld ist nicht vorhanden");
				return null;
			}
			
		} else if(365 < xCord && xCord < 390)  {
			
			if(yCord < 30) {
				x = 1;
				return new Feld(ring, x, y);
				
			} else if(670 <yCord && yCord <690) {
				
				x= 1;
				y = 2;
				return new Feld(ring, x, y);
				
			} else if(110 < yCord && yCord < 130) {
				ring = 1;
				x = 1;
				return new Feld(ring, x, y);
				
			} else if(540 < yCord && yCord < 580) {
				ring = 1;
				x = 1;
				y = 2;
				return new Feld(ring, x, y);
			} else if(220 < yCord && yCord < 260) {
				ring = 2;
				x = 1;
				return new Feld(ring, x, y);
				
			} else if(450 < yCord && yCord < 490) {
				ring = 2;
				x = 1;
				y = 2;
				return new Feld(ring, x, y);
			}	else {
				System.out.println("Das Feld ist nicht vorhanden");
				return null;
			}
			
		} else if(725 < xCord && xCord < 760) {
			
			if(yCord < 30) {
				x = 2;
				return new Feld(ring, x, y);
				
			} else if(340 <yCord && yCord < 360) {
				x = 2;
				y = 1;
				return new Feld(ring, x, y);
				
			} else if(660 < yCord && yCord < 690) {
				x = 2;
				y = 2;
				return new Feld(ring, x, y);
				
			} else {
				return null;
			}
			
		} else if(120 < xCord && xCord < 150) {
			ring = 1;
			
			if( 110 < yCord && yCord < 140) {
				return new Feld(ring, x, y);
			} else if(340 < yCord && yCord < 360) {
				y = 1;
				return new Feld(ring, x, y);
			} else if(550 < yCord && yCord < 580) {
				y = 2;
				return new Feld(ring, x, y);
			} else {
				System.out.println("Das Feld ist nicht vorhanden");
				return null;
			}
			
		}  else if(600 < xCord && xCord < 640) {
			ring = 1;
			
			if( 110 < yCord && yCord < 140) {
				x = 2;
				return new Feld(ring, x, y);
			} else if(340 < yCord && yCord < 370) {
				x = 2;
				y = 1;
				return new Feld(ring, x, y);
			} else if(550 < yCord && yCord < 580) {
				x = 2;
				y = 2;
				return new Feld(ring, x, y);
			} else {
				System.out.println("Feld nicht erkannt:(");
				return null;
			}
			
		} else if(250 < xCord && xCord <300) {
			ring = 2;
			if(230 < yCord && yCord < 260) {
				return new Feld(ring, x, y);
			} else if(340 <yCord && yCord < 380) {
				y = 1;
				return new Feld(ring, x, y);
			} else if(450 < yCord && yCord < 500) {
				y = 2;
				return new Feld(ring, x, y);
				
			} else {
				System.out.println("Das Feld ist nicht vorhanden");
				return null;
			}
			
		} else if(480  < xCord && xCord < 530) {
			ring = 2;
			if(230 < yCord && yCord < 275) {
				x = 2;
				return new Feld(ring, x, y);
			} else if(345 < yCord && yCord < 385) {
				x = 2;
				y = 1;
				return new Feld(ring, x, y);
			} else if(445 < yCord && yCord < 485) {
				x = 2;
				y = 2;
				return new Feld(ring, x, y);
			} else {
				System.out.println("Der Zug ist nicht erlaubt");
				return null;
			}
			
			
		} else {
			System.out.println("Bitte klicken Sie in den Spielbereich und auf das Feld");
			return null;
		}
		
	}
	
	
	
}

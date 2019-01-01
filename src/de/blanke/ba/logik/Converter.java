package de.blanke.ba.logik;

import de.blanke.ba.model.Feld;
/**
 * Diese Klasse stellt eine Funktion bereit, um 
 * Koordinaten von der Oberfläche in ein Feld umzuwandeln.
 * @author Paul Blanke, 23.12.2018.
 *
 */
public class Converter {
	
	public Feld ermitteleFeld(int xCord, int yCord) {
		int ring = 0;
		int x = 0;
		int y = 0;
		
		
		if(xCord < 30) {
			
			if(yCord < 30) {
				// 0, 0, 0
				return new Feld(ring, x, y);
			} else if(330 < yCord && yCord < 360) {
				// 0,0,1
				y = 1;
				return new Feld(ring, x, y);
				
			} else if(655 < yCord && yCord < 690) {
				// 0,0,2
				y = 2;
				return new Feld(ring, x, y);
			} else {
				System.out.println("Die Koordinaten konnten leider nicht zugeordnet werden!");
				return null;
			}
			
		} else if(365 < xCord && xCord < 390)  {
			
			if(yCord < 30) {
				x = 1;
				return new Feld(ring, x, y);
				
			} else if(655 <yCord && yCord <690) {
				
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
				
			} else if(430 < yCord && yCord < 490) {
				ring = 2;
				x = 1;
				y = 2;
				return new Feld(ring, x, y);
			}	else {
				System.out.println("Die Koordinaten konnten leider nicht zugeordnet werden!");
				return null;
			}
			
		} else if(725 < xCord && xCord < 760) {
			
			if(yCord < 30) {
				x = 2;
				return new Feld(ring, x, y);
				
			} else if(330 <yCord && yCord < 360) {
				x = 2;
				y = 1;
				return new Feld(ring, x, y);
				
			} else if(655 < yCord && yCord < 690) {
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
			} else if(330 < yCord && yCord < 360) {
				y = 1;
				return new Feld(ring, x, y);
			} else if(550 < yCord && yCord < 580) {
				y = 2;
				return new Feld(ring, x, y);
			} else {
				System.out.println("Die Koordinaten konnten leider nicht zugeordnet werden!");
				return null;
			}
			
		}  else if(600 < xCord && xCord < 640) {
			ring = 1;
			
			if( 110 < yCord && yCord < 140) {
				x = 2;
				return new Feld(ring, x, y);
			} else if(330 < yCord && yCord < 370) {
				x = 2;
				y = 1;
				return new Feld(ring, x, y);
			} else if(550 < yCord && yCord < 580) {
				x = 2;
				y = 2;
				return new Feld(ring, x, y);
			} else {
				System.out.println("Die Koordinaten konnten leider nicht zugeordnet werden!");
				return null;
			}
			
		} else if(250 < xCord && xCord <300) {
			ring = 2;
			if(230 < yCord && yCord < 260) {
				return new Feld(ring, x, y);
			} else if(330 <yCord && yCord < 380) {
				y = 1;
				return new Feld(ring, x, y);
			} else if(420 < yCord && yCord < 500) {
				y = 2;
				return new Feld(ring, x, y);
				
			} else {
				System.out.println("Die Koordinaten konnten leider nicht zugeordnet werden!");
				return null;
			}
			
		} else if(480  < xCord && xCord < 530) {
			ring = 2;
			if(230 < yCord && yCord < 275) {
				x = 2;
				return new Feld(ring, x, y);
			} else if(330 < yCord && yCord < 380) {
				x = 2;
				y = 1;
				return new Feld(ring, x, y);
			} else if(430 < yCord && yCord < 485) {
				x = 2;
				y = 2;
				return new Feld(ring, x, y);
			} else {
				System.out.println("Die Koordinaten konnten leider nicht zugeordnet werden!");
				return null;
			}
		} else {
			System.out.println("Die Koordinaten konnten leider nicht zugeordnet werden!");
			System.out.println("Bitte klicken Sie in den Spielbereich und auf das Feld");
			return null;
		}
	}
}

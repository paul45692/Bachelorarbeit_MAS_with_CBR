package de.blanke.ba.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import de.blanke.ba.logik.SpielController;
import de.blanke.ba.mas.ControllerAgent;
import de.blanke.ba.model.Spielstein;
import de.blanke.ba.spieler.Spieler;


public class Spielbrett extends JPanel implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3334802508888319034L;
	float interpolation;
	// Change the player
	int player = 0;
	boolean zugwechsel = true;
	boolean spielEnde = false;
	// Speichert Punkte.
	int xCord = 0;
	int yCord = 0;
	// Speichert Bild und Steine
	private BufferedImage bi;
	private List<Spielstein> spielsteine;
	// Logik
	private SpielController spielController;
	// Spieler A: weiss und @player false;
	private Spieler spielerA;
	// Spieler B: blau und @player true;
	private Spieler spielerB;
	// Spieler Agent wird eingesetzt
	private ControllerAgent controller;
	private boolean agenten_ai_on = false;
	
	
	
	public Spielbrett(ControllerAgent controller, boolean agenten_ai_on) {
		this.setLayout(new BorderLayout());
		this.spielsteine = new ArrayList<>();
		this.spielerA = new Spieler(Color.WHITE);
		this.spielerB = new Spieler(Color.BLUE);
		this.spielController = new SpielController();
		this.controller = controller;
		this.agenten_ai_on = agenten_ai_on;
		
		try {
			bi = ImageIO.read(this.getClass().getResource("/res/Muehlefeld.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		this.addMouseListener(this);
		this.setVisible(true);
		
	}
	
	public void setInterpolation(float interpol) {
		this.interpolation = interpol;
	}
	
	public void update() {
		
		
		
		
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bi, 0,0, this.getWidth(), this.getHeight(), this);
		
		
		if(spielsteine != null) {
			
			for(Spielstein k :spielsteine) {
				Graphics2D g2d = (Graphics2D) g;
			//	g2d.setColor(Color.BLUE);
				g2d.drawOval(k.getX(), k.getY(), 50, 50);
				// Steuert die Farbe.
				g2d.setColor(k.getColor());
				
				g2d.fillOval(k.getX(), k.getY(), 50, 50);
				
			}
		}
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		
		xCord = me.getX();
		yCord = me.getY();
		System.out.println(" " + xCord + "- " + yCord);
		List<Spielstein> zugDoppel = new ArrayList<>();
		// Checke ob das Spiel schon zu Ende ist.
		pruefeSpielEnde();
		
		if(player == 1) {
			 
			
			Spielstein spielstein = new Spielstein(xCord, yCord, Color.BLUE);
			// Wechsele die Spielphasen durch
			switch(spielerB.getSpielPhase()) {
			
			case 0:
						// Erste Spielphase (Steine frei setzen)
						if(spielController.setSpielStein(xCord, yCord, spielerB, spielstein)) {
							spielsteine.add(spielstein);
							player = 0;
							break;
						}
				
			case 1:
						// zweite Spielphase(Steine auf Nachbarfelder)
						if(zugwechsel) {
							
							Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
							spielsteine.remove(stein);
							zugDoppel.add(stein);
							zugwechsel =! zugwechsel;
							break;
						} else if(spielController.setSpielStein(xCord, yCord, spielerB, spielstein))  {
							spielsteine.add(spielstein);
							player = 0;
							zugwechsel =! zugwechsel;
							break;
						} else {
							System.out.println("Ein Fehler wurde gemacht");
							break;
						}
						
					
			case 2:		if(zugwechsel) {
				
							Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
							spielsteine.remove(stein);
							zugDoppel.add(stein);
							zugwechsel =! zugwechsel;
							break;
						} else if(spielController.setSpielStein(xCord, yCord, spielerB, spielstein))  {
							spielsteine.add(spielstein);
							player = 0;
							zugwechsel =! zugwechsel;
							break;
						} else {
							System.out.println("Ein Fehler wurde gemacht");
							break;
						}
			case 3:		
						// Mühle Spielphase! Gegener Stein darf geworfen werden.
						Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerA);
						spielsteine.remove(stein);
						// Der Spieler muss wieder in die Spielphase zurück
						player = 0;
						spielerB.setSpielPhase(spielerB.getTempspielPhase());
						break;
			default: 						
						break;	
				
			}
			
			spielController.pruefeAufMuehle(spielerB);
			
			
		} else if(player == 0)  {
			
			
			Spielstein spielstein = new Spielstein(xCord, yCord, Color.white);
			
			switch(spielerA.getSpielPhase()) {
			
			case 0:
						if(spielController.setSpielStein(xCord, yCord, spielerA, spielstein)) {
							spielsteine.add(spielstein);
							player = 1;
							
						}
						
						break;
				
			case 1:     if(zugwechsel) {
				
							Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
							spielsteine.remove(stein);
							zugwechsel =! zugwechsel;
							break;
						} else if(spielController.setSpielStein(xCord, yCord, spielerB, spielstein))  {
							spielsteine.add(spielstein);
							player = 1;
							zugwechsel =! zugwechsel;
							break;
						} else {
							System.out.println("Ein Fehler wurde gemacht");
							break;
						}
			
			case 2:		if(zugwechsel) {
				
							Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
							spielsteine.remove(stein);
							zugwechsel =! zugwechsel;
							break;
						} else if(spielController.setSpielStein(xCord, yCord, spielerB, spielstein))  {
							spielsteine.add(spielstein);
							player = 1;
							zugwechsel =! zugwechsel;
							break;
						} else {
							System.out.println("Ein Fehler wurde gemacht");
							break;
						}
						
						
			case 3:		Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
						spielsteine.remove(stein);
						// Der Spieler muss wieder in die Spielphase zurück
						player = 0;
						spielerA.setSpielPhase(spielerA.getTempspielPhase());
				
						break;
			default: 						
						break;	
				
			}
			spielController.pruefeAufMuehle(spielerA);
		}	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean isSpielEnde() {
		return spielEnde;
	}

	public void setSpielEnde(boolean spielEnde) {
		this.spielEnde = spielEnde;
	}
	// Diese Methode untersucht ob ein Spieler gewonnnen hat.
	private void pruefeSpielEnde() {
		if(spielerA.getAnzahlSteine() < 3 && spielerA.getSpielPhase() == 2) {
			this.spielEnde = true;
			System.out.println("Der Spieler " + spielerB.getSpielFarbe().toString() + "  gewinnt!");
		} else if(spielerB.getAnzahlSpielZüge() < 3 && spielerB.getSpielPhase() == 2) {
			this.spielEnde = true;
			System.out.println("Der Spieler " + spielerA.getSpielFarbe().toString() + "  gewinnt!");
		}
	}
 
}

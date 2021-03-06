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
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import de.blanke.ba.logik.SpielController;
import de.blanke.ba.model.Spielstein;
import de.blanke.ba.spieler.Spieler;
/**
 * Diese Klasse stellt das Spielbrett f�r ein 1 vs 1 
 * ohne den MAS-Einsatz bereit.
 * @author Paul Blanke
 *
 */
public class Spielbrett extends JPanel implements MouseListener {
// Attribute
	private static final long serialVersionUID = -3334802508888319034L;
	private static final Logger logger = Logger.getLogger(Spielbrett.class);
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
	private int playerA;
	// Spieler B: blau und @player true;
	private Spieler spielerB;
	private int playerB;
	private String ausgabe;
// konstruktor	
	public Spielbrett() {
		PropertyConfigurator.configure(Spielbrett.class.getResource("log4j.info"));
		logger.info("Spiel: Programm gestarted");
		this.setLayout(new BorderLayout());
		this.spielsteine = new ArrayList<>();
		this.spielController = new SpielController();
		this.spielerA = new Spieler(Color.WHITE,"Spieler A");
		this.playerA = 0;
		this.spielerB = new Spieler(Color.BLUE, "Spieler B");
		this.playerB = 1;
		this.setAusgabe("Hallo! Spieler A beginnt. Bitten klicken Sie auf das Feld um Ihren Zug zu machen");
		try {
			bi = ImageIO.read(this.getClass().getResource("/res/Muehlefeld.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.addMouseListener(this);
		this.setVisible(true);
	}
// Methoden
	public void setInterpolation(float interpol) {
		this.interpolation = interpol;
	}
	
	public void update() {	
	}
	// Setz die Steine auf das Panel.
	public void paintComponent(Graphics g) {
		g.drawImage(bi, 0,0, this.getWidth(), this.getHeight(), this);
		if(spielsteine != null) {
			for(Spielstein k :spielsteine) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.drawOval(k.getX(), k.getY(), 50, 50);
				g2d.setColor(k.getColor());
				g2d.fillOval(k.getX(), k.getY(), 50, 50);
			}
		}
	}
// zentrale Spielmethode �ber den Mouse Klick.
	@Override
	public void mouseClicked(MouseEvent me) {
		xCord = me.getX();
		yCord = me.getY();
		logger.info("Spiel-Eingabe: " + xCord + "- " + yCord);
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
							player = playerA;
							if(spielController.pruefeAufMuehle(spielerB)) {
								System.out.println("-->  Der Spieler B hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								setAusgabe("-->  Der Spieler B hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								player = playerB;
								spielerB.setTempspielPhase(spielerB.getSpielPhase());
								spielerB.setSpielPhase(3);
								
							} else {
								System.out.println("Der Spieler A (Weiss) ist am Zug!");
								
								logger.info("Neuer Spielzug: Der Spieler A (Weiss) ist am Zug!");
								int ausgabe = 9 - spielerA.getAnzahlSteine();
								this.setAusgabe("Der Spieler A (Weiss) ist am Zug! Es k�nnen noch " + ausgabe + " Spielsteine gesetzt werden!");
								logger.info("Info:Der Spieler " + spielerA.getName() + " kann noch " + ausgabe + " Spielsteine setzen!");
								System.out.println("Info: Der Spieler" + spielerA.getName() + " kann noch " + ausgabe + " Spielsteine setzen!");
							}
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
							player = playerA;
							zugwechsel =! zugwechsel;
							if(spielController.pruefeAufMuehle(spielerB)) {
								System.out.println("-->  Der Spieler B hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								setAusgabe("-->  Der Spieler B hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								logger.info("Spielzug:-->  Der Spieler B hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								player = playerB;
								spielerB.setTempspielPhase(spielerB.getSpielPhase());
								spielerB.setSpielPhase(3);
								
							} else {
								System.out.println("Der Spieler A (Weiss) ist am Zug!");
								this.setAusgabe("Der Spieler A ist nun am Zug");
								logger.info("Neuer Spielzug: Der Spieler A (Weiss) ist am Zug!");
								
							}
							break;
						} else {
							logger.info("Spielzug: Ein Fehler wurde gemacht");
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
							player = playerA;
							zugwechsel =! zugwechsel;
							if(spielController.pruefeAufMuehle(spielerB)) {
								System.out.println("-->  Der Spieler B hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								logger.info("Spielzug:-->  Der Spieler B hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								setAusgabe("-->  Der Spieler B hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								player = playerB;
								spielerB.setTempspielPhase(spielerB.getSpielPhase());
								spielerB.setSpielPhase(3);
								
							} else {
								System.out.println("Der Spieler A (Weiss) ist am Zug!");
								this.setAusgabe("Der Spieler A ist nun am Zug!");
								logger.info("Neuer Spielzug: Der Spieler A (Weiss) ist am Zug!");
							}
							break;
						} else {
							System.out.println("Ein Fehler wurde gemacht");
							break;
						}
			case 3:		
						// M�hle Spielphase! Gegener Stein darf geworfen werden.
						Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerA);
						spielsteine.remove(stein);
						// Der Spieler muss wieder in die Spielphase zur�ck
						player  = playerA;
						spielerB.setSpielPhase(spielerB.getTempspielPhase());
						System.out.println("Der Spieler A (Weiss) ist nun am Zug!");
						this.setAusgabe("Der Spieler A ist nun am Zug!");
						logger.info("Neuer Spielzug: Der Spieler A (Weiss) ist am Zug!");
						break;
			default: 						
						break;	
				
			}
		} else if(player == 0)  {
			
			Spielstein spielstein = new Spielstein(xCord, yCord, Color.white);
			switch(spielerA.getSpielPhase()) {
			
			case 0:
						if(spielController.setSpielStein(xCord, yCord, spielerA, spielstein)) {
							spielsteine.add(spielstein);
							player = playerB;
							if(spielController.pruefeAufMuehle(spielerA)) {
								System.out.println("-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								setAusgabe("-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								logger.info("Spielzug:-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								player = playerA;
								spielerA.setTempspielPhase(spielerA.getSpielPhase());
								spielerA.setSpielPhase(3);
								
							} else {
								System.out.println("Der Spieler B (Blau) ist am Zug!");
								logger.info("Neuer Spielzug: Der Spieler B (Blau) ist am Zug!");
								int ausgabe = 9 - spielerB.getAnzahlSteine();
								this.setAusgabe("Der Spieler B ist nun am Zug und er kann noch " + ausgabe + " Spielsteine setzen");
								logger.info("Neuer Spielzug: Der Spieler" + spielerB.getName() +"  kann noch " + ausgabe + " Spielsteine setzen!");
								System.out.println("Info: Der Spieler" + spielerB.getName() + " kann noch " + ausgabe + " Spielsteine setzen!");
							}
							
						}
						break;
						
			case 1:     if(zugwechsel) {
				
							Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
							spielsteine.remove(stein);
							zugwechsel =! zugwechsel;
							break;
							
						} else if(spielController.setSpielStein(xCord, yCord, spielerB, spielstein))  {
							
							spielsteine.add(spielstein);
							player = playerB;
							zugwechsel =! zugwechsel;
							if(spielController.pruefeAufMuehle(spielerA)) {
								logger.info("Spielzug:-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								System.out.println("-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								setAusgabe("-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								player = playerA;
								spielerA.setTempspielPhase(spielerA.getSpielPhase());
								spielerA.setSpielPhase(3);
								
							} else {
								System.out.println("Der Spieler B (Blau) ist am Zug!");
								setAusgabe("-->  Der Spieler B ist nun am Zug");
								logger.info("Neuer Spielzug: Der Spieler B (Blau) ist am Zug!");
							}
							break;
						} else {
							System.out.println("Fehlerhafter Spielzug!");
							logger.info("Spielzug: Ein Fehler wurde erkannt!");
							break;
						}
			
			case 2:		if(zugwechsel) {
				
							Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
							spielsteine.remove(stein);
							zugwechsel =! zugwechsel;
							break;
						} else if(spielController.setSpielStein(xCord, yCord, spielerB, spielstein))  {
							spielsteine.add(spielstein);
							player = playerB;
							zugwechsel =! zugwechsel;
							
							if(spielController.pruefeAufMuehle(spielerA)) {
								System.out.println("-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								setAusgabe("-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								logger.info("Spielzug:-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								player = playerA;
								spielerA.setTempspielPhase(spielerA.getSpielPhase());
								spielerA.setSpielPhase(3);
								
							} else {
								System.out.println("Der Spieler B (Blau) ist am Zug!");
								setAusgabe("-->  Der Spieler B ist nun am Zug!");
								logger.info("Neuer Spielzug: Der Spieler B (Blau) ist am Zug!");
							}
							break;
						} else {
							logger.error("Ein Fehler wurde gemacht");
							break;
						}
						
						
			case 3:		Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
						spielsteine.remove(stein);
						// Der Spieler muss wieder in die Spielphase zur�ck
						player = playerB;
						spielerA.setSpielPhase(spielerA.getTempspielPhase());
						System.out.println("Der Spieler B (Blau) ist am Zug!"); 
						setAusgabe("Der Spieler B ist nun am Zug");
						logger.info("Neuer Spielzug: Der Spieler B (Blau) ist am Zug!");
				
						break;
			default: 						
						break;	
				
			}
			
		}	
	}
	// Diese Methode untersucht ob ein Spieler gewonnnen hat.
	private void pruefeSpielEnde() {
		if(spielerA.getAnzahlSteine() < 3 && spielerA.getSpielPhase() == 2) {
			this.spielEnde = true;
			System.out.println("!! Der Spieler " + spielerA.getName() + "  gewinnt!");
			logger.info("Der Spieler " + spielerB.getSpielFarbe().toString() + "  gewinnt!");
		} else if(spielerB.getAnzahlSpielZ�ge() < 3 && spielerB.getSpielPhase() == 2) {
			this.spielEnde = true;
			System.out.println("!! Der Spieler " + spielerA.getName() + "  gewinnt!");
			logger.info("Der Spieler " + spielerA.getSpielFarbe().toString() + "  gewinnt!");
		} 
	}
// Methode und sonstiges 
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
	public String getAusgabe() {
		return ausgabe;
	}
	public void setAusgabe(String ausgabe) {
		this.ausgabe = ausgabe;
	}
}

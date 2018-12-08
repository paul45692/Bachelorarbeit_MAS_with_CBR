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

import de.blanke.ba.cbr.CBRAgent;
import de.blanke.ba.logik.SpielController;
import de.blanke.ba.mas.ControllerAgent;
import de.blanke.ba.mas.GameBehaviour;
import de.blanke.ba.mas.GameDataGetBehaviour;
import de.blanke.ba.mas.MessageBox;
import de.blanke.ba.model.Spielstein;
import de.blanke.ba.rbs.RBSAgent;
import de.blanke.ba.spieler.Spieler;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
/**
 * Diese Klasse repräsentiert den Versuch das MAS an die GUI anzubinden.
 * @author Paul Blanke
 *
 */
public class SpielbrettMAS extends JPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Grafikeinstellungen
	private BufferedImage bi;
	private List<Spielstein> spielsteine;
	float interpolation;
	private String ausgabe;
	// Logikeinstelluung
	private SpielController spielController;
	private ControllerAgent controllerMAS;
	private Spieler spielerA;
	private int playerA = 0;
	private Spieler spielerB;
	private int playerB = 1;
	private int player = 0;
	private static final Logger logger = Logger.getLogger(SpielbrettMAS.class);
	
	
	public SpielbrettMAS() {
		this.setLayout(new BorderLayout());
		this.spielsteine = new ArrayList<>();
		this.spielController = new SpielController();
		this.spielerA = new Spieler(Color.WHITE,"Spieler A");
		this.spielerB = new Spieler(Color.BLUE, "Spieler B");
		PropertyConfigurator.configure(SpielbrettMAS.class.getResource("log4j.info"));
		logger.info("Spiel (MAS): Variate mit MAS ! Der Spielverlauf wird eher weniger geloggt!");
		
		try {
			bi = ImageIO.read(this.getClass().getResource("/res/Muehlefeld.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		this.addMouseListener(this);
		this.setVisible(true);
		this.setUpMAS();
	}
	
	/**
	 * Diese Methoden zeichnen das Spielbrett und setze die Steine dazu.
	 */
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
	
	
// Methoden für die Eingabe	
	/**
	 * Diese Methode stellt eine modifizierte Version aus dem Spielbrett dar.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {

	boolean zugwechsel = false;
		
		List<Spielstein> zugDoppel = new ArrayList<>();
		// Checke ob das Spiel schon zu Ende ist.
		// pruefeSpielEnde();
		
		if(player == 1) {
			Spielstein spielstein = this.executeSpielZug(spielerB.copyInstance(), spielerA.copyInstance());
			spielstein.setColor(Color.BLUE);
			int xCord = spielstein.getX();
			int yCord = spielstein.getY();
			
			// Wechsele die Spielphasen durch
			switch(spielerB.getSpielPhase()) {
			
			case 0:
						// Erste Spielphase (Steine frei setzen)
						if(spielController.setSpielStein(xCord, yCord, spielerB, spielstein)) {
							spielsteine.add(spielstein);
							player = playerA;
							if(spielController.pruefeAufMuehle(spielerB)) {
								System.out.println("-->  Der Spieler B hat eine Mühle erzeugt! Entferne einen gegnerischen Stein");
								player = playerB;
								spielerB.setTempspielPhase(spielerB.getSpielPhase());
								spielerB.setSpielPhase(3);
								
							} else {
								ausgabe = "Der Spieler A (Weiss) ist am Zug!";
								System.out.println("Der Spieler A (Weiss) ist am Zug!");
								int ausgabe = 9 - spielerA.getAnzahlSteine();
								System.out.println("Info: Der Spieler" + spielerA.getName() + " kann noch " + ausgabe + " Spielsteine setzen!");
							}
							break;
						}
				
			case 1:
						/**
						 * Dieses Feld muss noch angepasst werden.
						 */
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
								System.out.println("-->  Der Spieler B hat eine Mühle erzeugt! Entferne einen gegnerischen Stein");
								player = playerB;
								spielerB.setTempspielPhase(spielerB.getSpielPhase());
								spielerB.setSpielPhase(3);
								
							} else {
								ausgabe = "Der Spieler A (Weiss) ist am Zug!";
								System.out.println("Der Spieler A (Weiss) ist am Zug!");
								
							}
							break;
						} else {
							logger.error("Ein Fehler wurde gemacht");
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
								System.out.println("-->  Der Spieler B hat eine Mühle erzeugt! Entferne einen gegnerischen Stein");
								player = playerB;
								spielerB.setTempspielPhase(spielerB.getSpielPhase());
								spielerB.setSpielPhase(3);
								
							} else {
								ausgabe = "Der Spieler A (Weiss) ist am Zug!";
								System.out.println("Der Spieler A (Weiss) ist am Zug!");
							}
							break;
						} else {
							logger.info("Spiel(MAS): Ein Fehler wurde gemacht auf dieser Ebene.");
							System.out.println("Ein Fehler wurde gemacht");
							break;
						}
			case 3:		
						// Mühle Spielphase! Gegener Stein darf geworfen werden.
						Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerA);
						spielsteine.remove(stein);
						// Der Spieler muss wieder in die Spielphase zurück
						player  = playerA;
						spielerB.setSpielPhase(spielerB.getTempspielPhase());
						ausgabe = "Der Spieler A (Weiss) ist am Zug!";
						System.out.println("Der Spieler A (Weiss) ist nun am Zug!"); 
						break;
			default: 						
						break;	
				
			}
			
		
			
		} else if(player == 0)  {
			
			Spielstein spielstein = this.executeSpielZug(spielerA.copyInstance(), spielerB.copyInstance());
			int xCord = spielstein.getX();
			int yCord = spielstein.getY();
			spielstein.setColor(Color.WHITE);
			
			
			
			switch(spielerA.getSpielPhase()) {
			
			case 0:
						if(spielController.setSpielStein(xCord, yCord, spielerA, spielstein)) {
							spielsteine.add(spielstein);
							player = playerB;
							if(spielController.pruefeAufMuehle(spielerA)) {
								System.out.println("-->  Der Spieler A hat eine Mühle erzeugt! Entferne einen gegnerischen Stein");
								player = playerA;
								spielerA.setTempspielPhase(spielerA.getSpielPhase());
								spielerA.setSpielPhase(3);
								
							} else {
								ausgabe = "Der Spieler B (Blau) ist am Zug!";
								System.out.println("Der Spieler B (Blau) ist am Zug!");
								int ausgabe = 9 - spielerB.getAnzahlSteine();
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
								System.out.println("-->  Der Spieler A hat eine Mühle erzeugt! Entferne einen gegnerischen Stein");
								player = playerA;
								spielerA.setTempspielPhase(spielerA.getSpielPhase());
								spielerA.setSpielPhase(3);
								
							} else {
								ausgabe = "Der Spieler B (Blau) ist am Zug!";
								System.out.println("Der Spieler B (Blau) ist am Zug!");
							}
							break;
						} else {
							System.out.println("Ein Fehler wurde gemacht");
							logger.info("Spiel(MAS): Ein Fehler wurde gemacht auf dieser Ebene.");
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
								System.out.println("-->  Der Spieler A hat eine Mühle erzeugt! Entferne einen gegnerischen Stein");
								player = playerA;
								spielerA.setTempspielPhase(spielerA.getSpielPhase());
								spielerA.setSpielPhase(3);
								
							} else {
								ausgabe = "Der Spieler B (Blau) ist am Zug!";
								System.out.println("Der Spieler B (Blau) ist am Zug!");
							}
							break;
						} else {
							logger.info("Spiel(MAS): Ein Fehler wurde gemacht auf dieser Ebene.");
							break;
						}
						
						
			case 3:		Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
						spielsteine.remove(stein);
						// Der Spieler muss wieder in die Spielphase zurück
						player = playerB;
						spielerA.setSpielPhase(spielerA.getTempspielPhase());
						ausgabe = "Der Spieler B (Blau) ist am Zug!";
						System.out.println("Der Spieler B (Blau) ist am Zug!"); 
				
						break;
			default: 						
						break;	
				
			}
			
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

	public String getAusgabe() {
		return ausgabe;
	}

	public void setAusgabe(String ausgabe) {
		this.ausgabe = ausgabe;
	}
	
	private void setUpMAS() {
		Runtime runtime = Runtime.instance();
	       Profile profile = new ProfileImpl();
	       profile.setParameter(Profile.MAIN_HOST, "localhost");
	       // Für Gui auf true setzen
	       profile.setParameter(Profile.GUI, "false");
	       ContainerController containerController = runtime.createMainContainer(profile);
	       AgentController ac, ac1, ac2;
	       RBSAgent rbsAgent = new RBSAgent();
	       CBRAgent cbrAgent = new CBRAgent();
	       this.controllerMAS = new ControllerAgent();
	      
	       try {
	    	 ac = containerController.acceptNewAgent("CBR Agent", cbrAgent);
			ac1 = containerController.acceptNewAgent("RBS Agent",rbsAgent );
			ac2 = containerController.acceptNewAgent("Controller Agent", controllerMAS);
			ac1.start();
			ac.start();
			ac2.start();
		} catch (StaleProxyException e) {
			logger.info("DAS MAS wurde nicht aufgesetzt!!!--Fehler");
			e.printStackTrace();
		}
	}
	/**
	 * Diese Methode hält das Script für das MAS bereit
	 * @return
	 */
	private Spielstein executeSpielZug(Spieler spieler, Spieler spielerB) {
		logger.info("Spiel(MAS): Ein Spielzug beginnt!");
		MessageBox box  = new MessageBox(spieler, spielerB, spielController.getBoard());
		GameBehaviour spielZug = new GameBehaviour(this.controllerMAS,box, true);
		this.controllerMAS.addBehaviour(spielZug);
		try {
			Thread.sleep(500);
			logger.info("Spiel(MAS): Warten...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 GameDataGetBehaviour data = new GameDataGetBehaviour(this.controllerMAS);
		this.controllerMAS.addBehaviour(data);
		try {
			Thread.sleep(500);
			logger.info("Spiel(MAS): Warten...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Spielzug(MAS): Zug angefangen- Daten Eingang");
		return data.getSpielstein();
		
		
	}
	
}

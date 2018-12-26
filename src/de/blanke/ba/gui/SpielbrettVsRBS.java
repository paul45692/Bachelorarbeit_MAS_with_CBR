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
 * Diese Spielfeld versucht ein Spiel gegen den RBS Agenten abzubilden.
 * @author Paul Blanke.
 *
 */
public class SpielbrettVsRBS extends JPanel implements MouseListener  {
// Attribute	
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
	private Spieler spielerB;
	private boolean spielEnde = false;
	private static final Logger logger = Logger.getLogger(SpielbrettMAS.class);
// konstruktor	
	public SpielbrettVsRBS() {
		this.setLayout(new BorderLayout());
		this.spielsteine = new ArrayList<>();
		this.spielController = new SpielController();
		this.spielerA = new Spieler(Color.WHITE,"Spieler A");
		this.spielerB = new Spieler(Color.BLUE, "Spieler B");
		PropertyConfigurator.configure(SpielbrettMAS.class.getResource("log4j.info"));
		logger.info("Spiel (MAS): Variate mit MAS ! Der Spielverlauf wird eher weniger geloggt!");
	//	this.prepareTestszenario(0);
		try {
			bi = ImageIO.read(this.getClass().getResource("/res/Muehlefeld.jpg"));
		} catch (IOException e) {
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
				g2d.drawOval(k.getX(), k.getY(), 50, 50);
				g2d.setColor(k.getColor());
				g2d.fillOval(k.getX(), k.getY(), 50, 50);
			}
		}
	}
// Methoden f�r die Eingabe	
	/**
	 * Diese Methode stellt eine modifizierte Version aus dem Spielbrett dar.
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		boolean zugwechsel = false;
		int xCord = me.getX();
		int yCord = me.getY();
		Spielstein spielstein = new Spielstein(xCord, yCord, Color.WHITE);
		this.pruefeAufSpielEnde();
		switch(spielerA.getSpielPhase()) {
			
			case 0:
						if(spielController.setSpielStein(xCord, yCord, spielerA, spielstein)) {
							spielsteine.add(spielstein);
							
							if(spielController.pruefeAufMuehle(spielerA)) {
								System.out.println("-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								spielerA.setTempspielPhase(spielerA.getSpielPhase());
								spielerA.setSpielPhase(3);
								
							} else {
								ausgabe = "Der Spieler B (Blau) ist am Zug!";
								System.out.println("Der Spieler B (Blau) ist am Zug!");
								int ausgabe = 9 - spielerB.getAnzahlSteine();
								System.out.println("Info: Der Spieler" + spielerB.getName() + " kann noch " + ausgabe + " Spielsteine setzen!");
								this.f�hreAgentenZugAus();
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
							zugwechsel =! zugwechsel;
							if(spielController.pruefeAufMuehle(spielerA)) {
								System.out.println("-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
								spielerA.setTempspielPhase(spielerA.getSpielPhase());
								spielerA.setSpielPhase(3);
								
							} else {
								ausgabe = "Der Spieler B (Blau) ist am Zug!";
								System.out.println("Der Spieler B (Blau) ist am Zug!");
							}
							break;
						}
			
			case 2:		if(zugwechsel) {
				
							Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
							spielsteine.remove(stein);
							zugwechsel =! zugwechsel;
							break;
						} else if(spielController.setSpielStein(xCord, yCord, spielerB, spielstein))  {
							spielsteine.add(spielstein);
				
							zugwechsel =! zugwechsel;
							
							if(spielController.pruefeAufMuehle(spielerA)) {
								System.out.println("-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
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
						// Der Spieler muss wieder in die Spielphase zur�ck
						spielerA.setSpielPhase(spielerA.getTempspielPhase());
						ausgabe = "Der Spieler B (Blau) ist am Zug!";
						System.out.println("Der Spieler B (Blau) ist am Zug!"); 
						break;
			default: 						
						break;	
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
	
	public boolean isSpielEnde() {
		return spielEnde;
	}

	public void setSpielEnde(boolean spielEnde) {
		this.spielEnde = spielEnde;
	}

	private void setUpMAS() {
			Runtime runtime = Runtime.instance();
	       Profile profile = new ProfileImpl();
	       profile.setParameter(Profile.MAIN_HOST, "localhost");
	       // F�r Gui auf true setzen
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
	 * Diese Methode h�lt das Script f�r das MAS bereit
	 * @return
	 */
	private List<Spielstein> executeSpielZug(Spieler spieler, Spieler spielerB) {
		logger.info("Spiel(MAS): Ein Spielzug beginnt!");
		MessageBox box  = new MessageBox(spieler, spielerB, spielController.getBoard());
		boolean changeAgent = true;
		
		GameBehaviour spielZug = new GameBehaviour(this.controllerMAS,box, changeAgent);
		this.controllerMAS.addBehaviour(spielZug);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 GameDataGetBehaviour data = new GameDataGetBehaviour(this.controllerMAS);
		this.controllerMAS.addBehaviour(data);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Spielzug(MAS): Zug angefangen- Daten Eingang");
		return data.getSpielstein();
	}
	/**
	 * Diese Methode �berpr�ft das Spielende.
	 */
	private void pruefeAufSpielEnde() {
		if(spielerA.getAnzahlSteine() == 2 && spielerA.getSpielPhase() == 3) {
			System.out.println("Spieler B gewinnt das Spiel!");
			this.spielEnde = true;
			System.out.println("Spieler B gewinnt das Spiel!");
		} else if(spielerB.getAnzahlSteine() == 2 && spielerB.getSpielPhase() == 3) {
			System.out.println("Spieler A gewinnt das Spiel!");
			this.spielEnde = true;
		}
	}
	/**
	 * Diese Methode setzt den Spielzug auf der GUI um.
	 */
	private void f�hreAgentenZugAus() {
		List<Spielstein> data = this.executeSpielZug(this.spielerB, spielerA);
		switch(this.spielerB.getSpielPhase()) {
		
		case 0:  	Spielstein spielstein = data.get(0);	
					int xCord = spielstein.getX();
					int yCord = spielstein.getY();
					if(spielController.setSpielStein(xCord, yCord, spielerB, spielstein)) {
							spielstein.setColor(Color.BLUE);
							spielsteine.add(spielstein);
	
								if(spielController.pruefeAufMuehle(spielerB)) {
									System.out.println("-->  Der Spieler B hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
									spielerB.setTempspielPhase(spielerB.getSpielPhase());
									spielerB.setSpielPhase(3);
								} else {
									ausgabe = "Der Spieler A (weiss) ist am Zug!";
									System.out.println("Der Spieler A (weiss) ist am Zug!");
									int ausgabe = 9 - spielerA.getAnzahlSteine();
									System.out.println("Info: Der Spieler" + spielerA.getName() + " kann noch " + ausgabe + " Spielsteine setzen!");
								}
						}
					break;	
				
				
		case 1: 	Spielstein zuEntfernen = data.get(0);
					Spielstein ziel = data.get(1);
					 xCord = zuEntfernen.getX();
					 yCord = zuEntfernen.getY();
					Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
					spielsteine.remove(stein);
					if(spielController.setSpielStein(xCord, yCord, spielerB, ziel))  {
						spielsteine.add(ziel);
						if(spielController.pruefeAufMuehle(spielerA)) {
							System.out.println("-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
							spielerB.setTempspielPhase(spielerA.getSpielPhase());
							spielerB.setSpielPhase(3);
							
						} else {
							ausgabe = "Der Spieler A (weiss) ist am Zug!";
							System.out.println("Der Spieler A (weiss) ist am Zug!");
						}
						break;
					}
					break;
					
		case 2:		zuEntfernen = data.get(0);
					ziel = data.get(1);
					xCord = zuEntfernen.getX();
					yCord = zuEntfernen.getY();
					stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
					spielsteine.remove(stein);
					if(spielController.setSpielStein(xCord, yCord, spielerB, ziel))  {
						spielsteine.add(ziel);
						if(spielController.pruefeAufMuehle(spielerA)) {
							System.out.println("-->  Der Spieler A hat eine M�hle erzeugt! Entferne einen gegnerischen Stein");
							spielerB.setTempspielPhase(spielerA.getSpielPhase());
							spielerB.setSpielPhase(3);
								
						} else {
							ausgabe = "Der Spieler A (weiss) ist am Zug!";
							System.out.println("Der Spieler A (weiss) ist am Zug!");
						}
						break;
					}
					break;
					
		case 3: 	break;	
		}
	}
	
}

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
import de.blanke.ba.testszenarien.SetUpTestSzenarien;
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
	private boolean spielEnde = false;
	private static final Logger logger = Logger.getLogger(SpielbrettMAS.class);
	
	
	public SpielbrettMAS() {
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
	private List<Spielstein> executeSpielZug(Spieler spieler, Spieler spielerB) {
		logger.info("Spiel(MAS): Ein Spielzug beginnt!");
		MessageBox box  = new MessageBox(spieler, spielerB, spielController.getBoard());
		boolean changeAgent = false;
		if(spieler.getName().contains("A")) {
			changeAgent = true;
		} 
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
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Spielzug(MAS): Zug angefangen- Daten Eingang");
		return data.getSpielstein();
	}
	/**
	 * Diese Methode überprüft das Spielende.
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
	
	private void führeZugDurch() {
		
		if(player == 1 && !spielEnde) {
			switch(spielerB.getSpielPhase()) {
			
			case 0:
						// Erste Spielphase (Steine frei setzen)
						Spielstein spielstein = this.executeSpielZug(spielerB.copyInstance(), spielerA.copyInstance()).get(0);
						spielstein.setColor(Color.BLUE);
						int xCord = spielstein.getX();
						int yCord = spielstein.getY();
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
								this.pruefeAufSpielEnde();
								System.out.println("Info: Der Spieler" + spielerA.getName() + " kann noch " + ausgabe + " Spielsteine setzen!");
							}
							break;
						}
				
			case 1:				// zweite Spielphase(Steine auf Nachbarfelder)
								spielstein = this.executeSpielZug(spielerB.copyInstance(), spielerA.copyInstance()).get(0);
								Spielstein spielstein2 = this.executeSpielZug(spielerB.copyInstance(), spielerA.copyInstance()).get(1);
								spielstein.setColor(Color.BLUE);
								xCord = spielstein.getX();
								yCord = spielstein.getY();
								spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
								spielsteine.remove(spielstein);
								xCord = spielstein2.getX();
								yCord = spielstein2.getY();
								
							if(spielController.setSpielStein(xCord, yCord, spielerB, spielstein2))  {
								spielsteine.add(spielstein2);
								player = playerA;
							
								if(spielController.pruefeAufMuehle(spielerB)) {
									System.out.println("-->  Der Spieler B hat eine Mühle erzeugt! Entferne einen gegnerischen Stein");
									player = playerB;
									spielerB.setTempspielPhase(spielerB.getSpielPhase());
									spielerB.setSpielPhase(3);
								
								} else {
									ausgabe = "Der Spieler A (Weiss) ist am Zug!";
									this.pruefeAufSpielEnde();
									System.out.println("Der Spieler A (Weiss) ist am Zug!");
								
							}
							break;
						} 
						
					
			case 2:		// zweite Spielphase(Steine auf Nachbarfelder)
						spielstein = this.executeSpielZug(spielerB.copyInstance(), spielerA.copyInstance()).get(0);
						spielstein2 = this.executeSpielZug(spielerB.copyInstance(), spielerA.copyInstance()).get(1);
						spielstein.setColor(Color.BLUE);
						xCord = spielstein.getX();
						yCord = spielstein.getY();
						spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
						spielsteine.remove(spielstein);
						xCord = spielstein2.getX();
						yCord = spielstein2.getY();
				
						if(spielController.setSpielStein(xCord, yCord, spielerB, spielstein2))  {
							spielsteine.add(spielstein2);
							player = playerA;
			
							if(spielController.pruefeAufMuehle(spielerB)) {
								System.out.println("-->  Der Spieler B hat eine Mühle erzeugt! Entferne einen gegnerischen Stein");
								player = playerB;
								spielerB.setTempspielPhase(spielerB.getSpielPhase());
								spielerB.setSpielPhase(3);
				
							} else {
								ausgabe = "Der Spieler A (Weiss) ist am Zug!";
								this.pruefeAufSpielEnde();
								System.out.println("Der Spieler A (Weiss) ist am Zug!");
				
							}
							break;
		} 
						
			case 3:		
						spielstein = this.executeSpielZug(spielerB.copyInstance(), spielerA.copyInstance()).get(0);
						spielstein.setColor(Color.BLUE);
						xCord = spielstein.getX();
						yCord = spielstein.getY();
						Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerA);
						spielsteine.remove(stein);
						// Der Spieler muss wieder in die Spielphase zurück
						player  = playerA;
						spielerB.setSpielPhase(spielerB.getTempspielPhase());
						ausgabe = "Der Spieler A (Weiss) ist am Zug!";
						this.pruefeAufSpielEnde();
						System.out.println("Der Spieler A (Weiss) ist nun am Zug!"); 
						break;
			default: 						
						break;	
				
			}
			
		
			
		}
	}
	
}

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
import de.blanke.ba.logik.Board;
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
	private boolean cbrreasoning;
	private Spieler spielerB;
	private boolean spielEnde = false;
	private static final Logger logger = Logger.getLogger(SpielbrettMAS.class);
// Konstruktor	
	public SpielbrettMAS() {
		this.setLayout(new BorderLayout());
		this.spielsteine = new ArrayList<>();
		this.spielController = new SpielController();
		this.spielerA = new Spieler(Color.WHITE,"RBS Agent");
		this.spielerB = new Spieler(Color.BLUE, "CBR Agent");
		this.ausgabe = " Das Spiel kann beginnen, bitte klicken Sie auf das Spielfeld!";
		PropertyConfigurator.configure(SpielbrettMAS.class.getResource("log4j.info"));
		logger.info("Spiel (MAS): Variate mit MAS ! Der Spielverlauf wird eher weniger geloggt!");
		try {
			bi = ImageIO.read(this.getClass().getResource("/res/Muehlefeld.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addMouseListener(this);
		this.setVisible(true);
		this.setUpMAS();
	}
// Methoden	
	
	public void setInterpolation(float interpol) {
		this.interpolation = interpol;
	}
	
	public void update() {	
	}
	// Fügt die Spielsteine dem Panel hinzu.
	public void paintComponent(Graphics g) {
		g.drawImage(bi, 0,0, this.getWidth(), this.getHeight(), this);
		if(spielsteine != null) {
			
			for(Spielstein k :spielsteine) {
				Graphics2D g2d = (Graphics2D) g;
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
		this.beginneSpiel();
	}
	// Diese Methode beginnt ein Spiel.
	public void	beginneSpiel() {
		for(int i = 0; i < 12; i++) {
			Spieler spieler = this.spielerA;
			Spieler spielerB = this.spielerB;
			if(i % 2 == 0) {
				// legt fest welcher Spieler am Anfang beginnt.
				spieler = this.spielerB;
				spielerB = this.spielerA;
			}
			Board board = this.spielController.getBoard();
			this.führeZugDurch(spieler, spielerB, board);
			this.paint(getGraphics());
		}
		System.out.println("Agenten Spiel beendet!");
		ausgabe = "Das Spiel mit den Agenten wurde beendet! CBR Agent (Blau) & RBS Agent (Weiss)";
	}
	/**
	 * Diese Methode führt einen Spielzug des Agenten durch.
	 * @param spieler der aktive Spieler am Zug.
	 * @param spielerB der zweite Spieler.
	 * @param board der Spielstand (aktuell).
	 */
	private void führeZugDurch(Spieler spieler, Spieler spielerB, Board board) {
		List<Spielstein> data = this.executeSpielZug(spieler, spielerB, board);
		// Löse erneut aus, falls ein Problem auftritt.
		if(data.isEmpty()) {
			this.cbrreasoning = true;
			data = this.executeSpielZug(spieler, spielerB, board);
		}
		if(!spielEnde) {
			
			switch(spieler.getSpielPhase()) {
			case 0:
						Spielstein spielstein = data.get(0);
						if(spieler.getName().contains("CBR")) {
							spielstein.setColor(Color.BLUE);
						} else {
							spielstein.setColor(Color.WHITE);
						}
						
						int xCord = spielstein.getX();
						int yCord = spielstein.getY();
						if(spielController.setSpielStein(xCord, yCord, spieler, spielstein)) {
							spielsteine.add(spielstein);
							
							if(spielController.pruefeAufMuehle(spieler)) {
								System.out.println("-->  Der "+ spieler.getName() + " hat eine Mühle erzeugt! Entferne einen gegnerischen Stein");
								 spieler.setTempspielPhase(spieler.getSpielPhase());
								 spieler.setSpielPhase(3);
								 this.führeZugDurch(spieler, spielerB, board);
								
							} else {
								// Unterscheide welcher Spieler als nächstes dran ist
								ausgabe = "Der Spieler " + spielerB.getName() +"ist am Zug!";
								System.out.println("Der Spieler" + spielerB.getName() + " ist am Zug!");
								int ausgabe = 9 - spielerB.getAnzahlSteine();
								this.pruefeAufSpielEnde();
								System.out.println("Info:" + spielerB.getName() + " kann noch " + ausgabe + " Spielsteine setzen!");
							}
							break;
						} 
						break;
				
			case 1:		// zweite Spielphase(Steine auf Nachbarfelder)
						spielstein = data.get(0);
						Spielstein spielstein2 = data.get(1);	
						// Farbe steuern.
						if(spieler.getName().contains("CBR")) {
							spielstein2.setColor(Color.BLUE);
						} else {
							spielstein2.setColor(Color.WHITE);
						}
						
						xCord = spielstein.getX();
						yCord = spielstein.getY();
						spielController.entferneSteinVonFeld(xCord, yCord, spieler);
						spielsteine.remove(spielstein);
						xCord = spielstein2.getX();
						yCord = spielstein2.getY();
								
						if(spielController.setSpielStein(xCord, yCord, spieler, spielstein2))  {
							spielsteine.add(spielstein2);
							
							if(spielController.pruefeAufMuehle(spieler)) {
								System.out.println("-->  Der" + spieler.getName() +" hat eine Mühle erzeugt! Entferne einen gegnerischen Stein");
								spieler.setTempspielPhase(spieler.getSpielPhase());
								spieler.setSpielPhase(3);
								this.führeZugDurch(spieler, spielerB, board);
								
							} else {
								// Unterscheide welcher Spieler als nächstes dran ist
								ausgabe = "Der Spieler " + spielerB.getName() +"ist am Zug!";
								System.out.println("Der Spieler" + spielerB.getName() + " ist am Zug!");
							}
							break;
						} 
						break;
						
					
			case 2:		
							spielstein = data.get(0);
							spielstein2 = data.get(1);	
						// Farbe steuern.
						if(spieler.getName().contains("CBR")) {
							spielstein2.setColor(Color.BLUE);
						} else {
							spielstein2.setColor(Color.WHITE);
						}
						xCord = spielstein.getX();
						yCord = spielstein.getY();
						spielController.entferneSteinVonFeld(xCord, yCord, spieler);
						spielsteine.remove(spielstein);
						xCord = spielstein2.getX();
						yCord = spielstein2.getY();
				
						if(spielController.setSpielStein(xCord, yCord, spieler, spielstein2))  {
							spielsteine.add(spielstein2);
							if(spielController.pruefeAufMuehle(spieler)) {
								System.out.println("-->  Der" + spieler.getName() + " hat eine Mühle erzeugt! Entferne einen gegnerischen Stein");
								spieler.setTempspielPhase(spieler.getSpielPhase());
								spielerB.setSpielPhase(3);
								this.führeZugDurch(spieler, spielerB, board);
							} else {
								// Unterscheide welcher Spieler als nächstes dran ist
								ausgabe = "Der Spieler " + spielerB.getName() +"ist am Zug!";
							}
							break;
						} 
						break;
						
			case 3:		
						spielstein = data.get(0);
						xCord = spielstein.getX();
						yCord = spielstein.getY();
						Spielstein stein = spielController.entferneSteinVonFeld(xCord, yCord, spielerB);
						if(stein != null) {
							spielsteine.remove(stein);
							spieler.setSpielPhase(spieler.getTempspielPhase());
							// Unterscheide welcher Spieler als nächstes dran ist
							ausgabe = "Der Spieler " + spielerB.getName() +"ist am Zug!";
							System.out.println("Der Spieler" + spielerB.getName() + " ist am Zug!");
							this.pruefeAufSpielEnde();
						} else {
							System.out.println("Das Schlagen ist fehlgeschlagen!");
						}
						break;
						
			default: 						
						break;	
			}
			
		}
		
	}	

	/**
	 * Diese Methode setzt das MAS bei Initialisierung auf.
	 * 
	 */
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
			ac1 = containerController.acceptNewAgent("RBS Agent",	rbsAgent );
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
	 * Diese Methode sorgt für die Ausführung eines Spielzuges über das MAS.
	 * @param spieler		aktueller Spieler
	 * @param spielerB      zweiter Spieler
	 * @param board			Spielsituation
	 * @return   eine Liste mit Spielzügen.
	 */
	private List<Spielstein> executeSpielZug(Spieler spieler, Spieler spielerB, Board board) {
		logger.info("Spiel(MAS): Ein Spielzug beginnt!");
		MessageBox box  = new MessageBox(spieler, spielerB, board);
		boolean changeAgent = false;
		if(spieler.getName().contains("RBS")) {
			changeAgent = true;
		}  else if(cbrreasoning) {
			box.setReosoning(true);
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
			Thread.sleep(300);
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
			System.out.println( spielerB.getName() + " gewinnt das Spiel!");
			this.spielEnde = true;
		} else if(spielerB.getAnzahlSteine() == 2 && spielerB.getSpielPhase() == 3) {
			System.out.println( spielerA.getName() + "gewinnt das Spiel!");
			this.spielEnde = true;
		}
	}
// Getter / Setter Methoden und leere Methoden
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

	public boolean isCbrreasoning() {
		return cbrreasoning;
	}

	public void setCbrreasoning(boolean cbrreasoning) {
		this.cbrreasoning = cbrreasoning;
	}
}

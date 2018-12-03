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
	private Spieler spielerB;
	
	
	
	public SpielbrettMAS() {
		this.setLayout(new BorderLayout());
		this.spielsteine = new ArrayList<>();
		this.spielController = new SpielController();
		this.spielerA = new Spieler(Color.WHITE,"Spieler A");
		
		this.spielerB = new Spieler(Color.BLUE, "Spieler B");
		
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
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.executeSpielZug();
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void executeSpielZug() {
		MessageBox box  = new MessageBox(spielerA, spielController.getBoard());
		GameBehaviour spielZug = new GameBehaviour(this.controllerMAS,box, true);
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
		Spielstein stein = data.getSpielstein();
		spielsteine.add(stein);
		ausgabe = "Der nächste Spieler ist am Zug";
		
	}
	
}

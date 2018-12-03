package de.blanke.ba.gui;

import java.awt.BorderLayout;
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
	private BufferedImage bi;
	private List<Spielstein> spielsteine;
	private SpielController spielController;
	private ControllerAgent controllerMAS;
	float interpolation;
	private String ausgabe;
	
	public SpielbrettMAS(ControllerAgent controllerMAS) {
		this.setLayout(new BorderLayout());
		this.spielsteine = new ArrayList<>();
		this.spielController = new SpielController();
		this.controllerMAS = controllerMAS;
		try {
			bi = ImageIO.read(this.getClass().getResource("/res/Muehlefeld.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		this.addMouseListener(this);
		this.setVisible(true);
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
		System.out.println("Ein Button wurde geklickt!");
		ausgabe = "Hallo";
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

}

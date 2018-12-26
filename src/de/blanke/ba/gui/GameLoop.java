package de.blanke.ba.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Diese Klasse bildet die Meachanik einer Game Loop im Hintergrund ab.
 * Diese Klasse wurde von folgender Quelle übernommen und erweitert:
 * http://www.java-gaming.org/index.php?topic=24220.0 (Stand 25.12.2018)
 * @author Paul Blanke
 *
 */
public class GameLoop extends JFrame implements ActionListener {
// Attribute
	// Design Änderung
	private static final long serialVersionUID = 1L;
	// Normales Mühle Spiel
	private Spielbrett spielbrett;
	// MAS Mühle
	private SpielbrettMAS spielbrettMAS;
	private SpielbrettVsRBS spielbrettRBS;
	int mas = 0;
	private JButton startButton = new JButton("Start");
	private JButton endeButton = new JButton("Ende!");
	private JButton pausedButton = new JButton("Pause");
	private JLabel textLabel = new JLabel(" ");
	private boolean running = false;
	private boolean paused = false;
	private int fps = 60;
	private int framecount = 0;
	private boolean spielEnde = false;
	/**
	 * Konstruktor
	 */
	public GameLoop(int mas) {
		
		super("Bachelorarbeit-Mühle mit KI");
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,2));
		p.add(startButton);
		p.add(pausedButton);
		p.add(endeButton);
		this.mas = mas;
		switch(mas) {
		case 0:			this.spielbrett = new Spielbrett();
						this.spielbrettMAS = null;
						this.spielbrettRBS = null;
						cp.add(spielbrett, BorderLayout.CENTER);
						this.textLabel.setText("normaler Spielmodus: Klicke auf Start!");
						break;
						
		case 1:     	this.spielbrett = null;
						this.spielbrettMAS = new SpielbrettMAS();
						this.spielbrettRBS = null;
						cp.add(spielbrettMAS, BorderLayout.CENTER);
						this.textLabel.setText("MAS-Spielmodus (CBR & RBS): Klicke auf Start!");
						break;
			
		case 2:         this.spielbrett = null;
						this.spielbrettMAS = null;
						this.spielbrettRBS = new SpielbrettVsRBS();
						cp.add(spielbrettRBS, BorderLayout.CENTER);
						this.textLabel.setText("regelbasierter Modus(vs. RBS Agent): Klicke auf Start!");
						break;
						
		default:        break;
		}
		cp.add(textLabel, BorderLayout.NORTH);
		cp.add(p, BorderLayout.SOUTH);
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startButton.addActionListener(this);
		pausedButton.addActionListener(this);
		endeButton.addActionListener(this);
		this.spielEnde = false;
	}
	/**
	 * Diese Methode verarbeitet Button Klicks.
	 */
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		
		if(s == startButton) {
			running = !running;
			if(running) {
				startButton.setText("Stop");
				runGameLoop();
			} else {
				startButton.setText("Start");
			}
			
		} else if(s == pausedButton) {
			paused = !paused;
			
			if( paused) {
				pausedButton.setText("Fortsetzen");
			} else {
				pausedButton.setText("Pause");
			}
		} else if(s == endeButton) {
			// beendet das programm
			
			System.exit(0);
		}
	}
	
	// Diese Methode lässt das Spiel als eigenen Thread laufen.
	public void runGameLoop() {
		Thread loop = new Thread() {
			public void run() {
				gameLoop();
			}
		};
		loop.start();
	}
	
	private void gameLoop() {
		final double GAME_HERTZ = 30.00;
		final double TIME_BEWTEEN_UPDATES = 1000000000 / GAME_HERTZ;
		final int MAX_UPDATES_BEFORE_RENDER = 5;
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();
		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / GAME_HERTZ;
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		
		while(running) {
			double now = System.nanoTime();
			int updateCount = 0;
			
			if (!paused)
	         {
	             //Do as many game updates as we need to, potentially playing catchup.
	            while( now - lastUpdateTime > TIME_BEWTEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
	            {
	               updateGame();
	               lastUpdateTime += TIME_BEWTEEN_UPDATES;
	               updateCount++;
	            }
	   
	            //If for some reason an update takes forever, we don't want to do an insane number of catchups.
	            //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
	            if ( now - lastUpdateTime > TIME_BEWTEEN_UPDATES)
	            {
	               lastUpdateTime = now - TIME_BEWTEEN_UPDATES;
	            }
	         
	            //Render. To do so, we need to calculate interpolation for a smooth render.
	            float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BEWTEEN_UPDATES) );
	            drawGame(interpolation);
	            lastRenderTime = now;
	         
	            //Update the frames we got.
	            int thisSecond = (int) (lastUpdateTime / 1000000000);
	            if (thisSecond > lastSecondTime)
	            {
	             //  System.out.println("NEW SECOND " + thisSecond + " " + framecount);
	               fps = framecount;
	               framecount = 0;
	               lastSecondTime = thisSecond;
	            }
	         
	            //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
	            while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BEWTEEN_UPDATES)
	            {
	               Thread.yield();
	            
	               //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
	               //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
	               //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
	               try {Thread.sleep(1);} catch(Exception e) {} 
	            
	               now = System.nanoTime();
	            }
	         }
		}
		
		
	}
	/**
	 * Diese Methode zeichnen ein Update auf das Spielfeld
	 */
	private void updateGame() {
		switch(mas) {
		case 0:		spielbrett.update();
					this.textLabel.setText(spielbrett.getAusgabe());
					this.spielEnde = spielbrett.isSpielEnde();
					break;
					
		case 1:     spielbrettMAS.update();
					this.textLabel.setText(spielbrettMAS.getAusgabe());
					this.spielEnde = spielbrettMAS.isSpielEnde();
					break;	
					
		case 2:     spielbrettRBS.update(); 
					this.textLabel.setText(spielbrettRBS.getAusgabe());
					this.spielEnde = spielbrettRBS.isSpielEnde();
					break;
		} 
	}
	
	private void drawGame(float interpolation) {
		switch(mas) {
		case 0:	  	this.spielbrett.repaint();
					this.textLabel.setText(spielbrett.getAusgabe());
					this.spielEnde = spielbrett.isSpielEnde();
					break;
					
		case 1:     this.spielbrettMAS.repaint();
					this.textLabel.setText(spielbrettMAS.getAusgabe());
					this.spielEnde = spielbrettMAS.isSpielEnde();
					break; 
					
		case 2:     this.spielbrettRBS.repaint();
					this.textLabel.setText(spielbrettRBS.getAusgabe());
					this.spielEnde = spielbrettRBS.isSpielEnde();
					break; 	
		}
	}
}

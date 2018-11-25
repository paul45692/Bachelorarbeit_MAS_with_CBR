package de.blanke.ba.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import de.blanke.ba.mas.ControllerAgent;

/**
 * Diese Klasse bildet das Herzst�ck des Spiel.
 * �bernommen und erweitert bzw. Angepasst
 * http://www.java-gaming.org/index.php?topic=24220.0 (Stand 04.08.2018)
 * 
 * @author Paul Blanke
 *
 */
public class GameLoop extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	// Design �nderung
	private static final long serialVersionUID = 1L;

	private Spielbrett spielbrett;
	
	private JButton startButton = new JButton("Neues Spiel");
	private JButton endeButton = new JButton("Ende!");
	private JButton pausedButton = new JButton("Pause");
	private boolean running = false;
	private boolean paused = false;
	private int fps = 60;
	private int framecount = 0;
	// Interene Spieleinstellung vornehmen
	private boolean agent_ai_on = false;
	private boolean spielEnde = false;
	private ControllerAgent agent = null;
	
	
	
	
	
	/**
	 * Konstruktor
	 */
	public GameLoop(ControllerAgent agent, boolean agent_ai_on) {
		
		super("Bachelorarbeit-M�hle mit KI");
		this.spielbrett = new Spielbrett(agent, agent_ai_on);
		this.agent = agent;
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,2));
		p.add(startButton);
		p.add(pausedButton);
		p.add(endeButton);
		// K�nnte man durch Spielfeld ersetzen?
		cp.add(spielbrett, BorderLayout.CENTER);
		cp.add(p, BorderLayout.SOUTH);
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startButton.addActionListener(this);
		pausedButton.addActionListener(this);
		endeButton.addActionListener(this);
		this.agent_ai_on = false;
		this.spielEnde = false;
		
	}
	
	
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
	 * Diese Methode updatet das Game sp�ter
	 */
	private void updateGame() {
		 spielbrett.update();
		 
	}
	
	private void drawGame(float interpolation) {
		 spielbrett.repaint();
	}


	public boolean isAgent_ai_on() {
		return agent_ai_on;
	}


	public void setAgent_ai_on(boolean agent_ai_on) {
		this.agent_ai_on = agent_ai_on;
	}


	public boolean isSpielEnde() {
		return spielEnde;
	}


	public void setSpielEnde(boolean spielEnde) {
		this.spielEnde = spielEnde;
	}
	
	
	

}
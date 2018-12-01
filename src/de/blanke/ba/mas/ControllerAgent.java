package de.blanke.ba.mas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import de.blanke.ba.logik.Board;
import de.blanke.ba.model.Feld;
import de.blanke.ba.model.Spielstein;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class ControllerAgent extends Agent {
	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 1L;
	// Ein Instanz des Board das den Spielstand repräsentiert.
	private Board board;
	private Spieler spieler;
	private MessageBox box;
	private boolean rbsAmZug;
	private boolean aufgabeFertig = false;
	// Fixe Steine aus denen Spielsteine erzeugt werden
	private List<Stein> fixeSteineRing1 = new ArrayList<>();
	private List<Stein> fixeSteineRing2 = new ArrayList<>();
	private List<Stein> fixeSteineRing3 = new ArrayList<>();
	// Rückgabe Parameter
	private List<Stein> data = new ArrayList<>();

	public List<Stein> getData() {
		return data;
	}


	public void setData(List<Stein> data) {
		this.data = data;
	}


	// Getter und Setter
	public Board getBoard() {
		return board;
	}


	public void setBoard(Board board) {
		this.board = board;
	}

	public Spieler getSpieler() {
		return spieler;
	}


	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}


	public boolean isAufgabeFertig() {
		return aufgabeFertig;
	}


	public void setAufgabeFertig(boolean aufgabeFertig) {
		this.aufgabeFertig = aufgabeFertig;
	}


	// Agenten Methoden
	@Override
	protected void setup() {
		System.out.println("Controller Agent aktiv");
		setzeSpielsteineAuf();
		super.setup();
	}
	

	@Override
	protected void takeDown() {
		// TODO Auto-generated method stub
		super.takeDown();
	}
	// Konstruktor
	public void leiteZugEin(Board board, boolean rbsAmZug, Spieler spieler) {
		this.board = board;
		this.spieler = spieler;
		this.box = new MessageBox(spieler, board);
		this.rbsAmZug = rbsAmZug;
		addBehaviour(new B1(this));
		
	}
	
	private void setzeSpielsteineAuf() {
		// Add fixe Spielsteine für den ersten Ring
		fixeSteineRing1.add(new Stein(0,0,0,null, new Spielstein(15,11, null)));
		fixeSteineRing1.add(new Stein(0,1,0,null, new Spielstein(368, 6, null)));
		fixeSteineRing1.add(new Stein(0,2,0,null, new Spielstein(730, 12, null)));
		fixeSteineRing1.add(new Stein(0,0,1,null, new Spielstein( 14, 347, null)));
		fixeSteineRing1.add(new Stein(0,0,2,null, new Spielstein(15, 681, null)));
		fixeSteineRing1.add(new Stein(0,1,2,null, new Spielstein( 371, 682, null)));
		fixeSteineRing1.add(new Stein(0,2,2,null, new Spielstein(731, 678, null)));
		fixeSteineRing1.add(new Stein(0,2,1,null, new Spielstein(731, 350, null)));
		// Add fixe Spielsteine für den ersten Ring
		fixeSteineRing2.add(new Stein(1,0,0,null, new Spielstein(134,123, null)));
		fixeSteineRing2.add(new Stein(1,1,0,null, new Spielstein(370, 124, null)));
		fixeSteineRing2.add(new Stein(1,2,0,null, new Spielstein(609, 343, null)));
		fixeSteineRing2.add(new Stein(1,0,1,null, new Spielstein( 135, 347, null)));
		fixeSteineRing2.add(new Stein(1,0,2,null, new Spielstein(135, 568, null)));
		fixeSteineRing2.add(new Stein(1,1,2,null, new Spielstein( 371, 568, null)));
		fixeSteineRing2.add(new Stein(1,2,2,null, new Spielstein(614, 568, null)));
		fixeSteineRing2.add(new Stein(1,2,1,null, new Spielstein(614, 350, null)));
		// Add fixe Spielsteine für den dritten Ring
		fixeSteineRing3.add(new Stein(2,0,0,null, new Spielstein(255,235, null)));
		fixeSteineRing3.add(new Stein(2,1,0,null, new Spielstein(373, 235, null)));
		fixeSteineRing3.add(new Stein(2,2,0,null, new Spielstein(493, 236, null)));
		fixeSteineRing3.add(new Stein(2,0,1,null, new Spielstein(255, 345, null)));
		fixeSteineRing3.add(new Stein(2,0,2,null, new Spielstein(255, 455, null)));
		fixeSteineRing3.add(new Stein(2,1,2,null, new Spielstein(370, 455, null)));
		fixeSteineRing3.add(new Stein(2,2,2,null, new Spielstein(492, 455, null)));
		fixeSteineRing3.add(new Stein(2,2,1,null, new Spielstein(494, 348, null)));
	}
	
	public Spielstein sucheSteinRaus(Stein stein) {
		Spielstein rueckgabe = null;
		if(stein.getRing() == 0) {
			for(Stein s: fixeSteineRing1) {
				if((s.getxCord() == stein.getxCord())&& (s.getyCord() == s.getyCord())) {
					rueckgabe = s.getSpielstein();
					break;
				}
			}
			
		}else if(stein.getRing() == 1) {
			for(Stein s: fixeSteineRing2) {
				if((s.getxCord() == stein.getxCord())&& (s.getyCord() == s.getyCord())) {
					rueckgabe = s.getSpielstein();
					break;
				}
				
			}	
		} else {
			for(Stein s: fixeSteineRing3) {
				if((s.getxCord() == stein.getxCord())&& (s.getyCord() == s.getyCord())) {
					rueckgabe = s.getSpielstein();
					break;
				}
			}	
		}
		
		return rueckgabe;
	}
	
	 
	
	
	class B1 extends OneShotBehaviour {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7247228369210425231L;
		
		
			public B1(Agent a) {
				super(a);
			}
			

			@Override
			public void action() {
			// Der Agent sendet die Nachricht ab	
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			if(rbsAmZug) {
				msg.addReceiver(new AID("RBS Agent", AID.ISLOCALNAME));
			} else {
				msg.addReceiver(new AID("CBR Agent", AID.ISLOCALNAME));
			}
			msg.setContent("Du bist am Zug");
			try {
				msg.setContentObject(box);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			send(msg);
			block();
			// Er wartet auf eine neue Nachricht und holt das Feld aus der Nachricht.
			msg = receive();
			if(msg != null) {
				try {
					MessageBoxSteine box = (MessageBoxSteine) msg.getContentObject();
					data.add(box.getZielStein());
					System.out.println("Die Nachricht ist angekommen!");
					
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
			
			setAufgabeFertig(true);
			}
	
	}
}

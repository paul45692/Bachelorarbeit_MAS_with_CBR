package de.blanke.ba.mas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import de.blanke.ba.logik.Board;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
/**
 * Diese Klasse repräsentiert den Controller Agenten.
 * @author paul4
 *
 */
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
		super.setup();
	}
	

	@Override
	protected void takeDown() {
		// TODO Auto-generated method stub
		super.takeDown();
	}
	
	public void leiteZugEin(Board board, boolean rbsAmZug, Spieler spieler) {
		this.board = board;
		this.spieler = spieler;
		this.box = new MessageBox(spieler, board);
		this.rbsAmZug = rbsAmZug;
		addBehaviour(new B1(this));
		
	}
	
	
	
	/**
	 * Diese Klasse stellt die Innere Cycle Behaviour bereit.
	 * @author paul4
	 *
	 */
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

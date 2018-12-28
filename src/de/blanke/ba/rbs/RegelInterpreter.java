package de.blanke.ba.rbs;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import de.blanke.ba.logik.Board;
import de.blanke.ba.logik.SpielLogikErweChecks;
import de.blanke.ba.model.Feld;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;
/**
 * Diese Klasse stellt die Regeln bereit und wertet eine Anfrage an das System aus.
 * @author Paul Blanke.
 *
 */
public class RegelInterpreter {
// Attribute
	private RegelSet data = new RegelSet();
	private List<RegelSpielPhase0> spielphase0 = data.getSpielphase0();
	private List<RegelSpielPhase1u2> spielphase1 = data.getSpielphase1();
	private List<RegelSpielPhase1u2> spielphase2 = data.getSpielphase1();
	private List<RegelSpielPhase0> spielphase3 = data.getSpielphase3();
	private List<Regel> uebergreifendeRegeln = data.getUbergreifendeRegeln();
	private SpielLogikErweChecks logikCheck = new SpielLogikErweChecks();
	private List<Stein> dataBack = new ArrayList<>();
	private static final Logger logger = Logger.getLogger(RegelInterpreter.class);
// Konstruktor für den Logger
	public RegelInterpreter() {
		PropertyConfigurator.configure(RegelInterpreter.class.getResource("log4j.info"));
		logger.info("RBS Agent (Auswertung: Bereit!");
	}
	/**
	 * Diese Methode verarbeitet eine Anfrage an das System.
	 * @param board
	 * @param spieler
	 * @return Steine die gesetzt werden können.
	 */
	public List<Stein> sendQuery(Board board, Spieler spieler, Spieler spielerB) {
		this.setzeColor(spieler.getSpielFarbe());
		this.dataBack.clear();
		logger.info("RBS Agent (Auswertung): Eine Anfrage wird beantwortet ....");
		
		switch(spieler.getSpielPhase()) {
		
		case 0:    	if(this.analyseQueryBefore(spieler, spielerB, board)) {
						System.out.println("RBS Agent: Eine allgemeine Regel hat gefeuert!");
					} else {
						analyseQuerySpielPhase0(board, spieler);
					}
					break;
					
		case 1:     if(this.analyseQueryBefore(spieler, spielerB, board)) {
						System.out.println("RBS Agent: Eine allgemeine Regel hat gefeuert!");
					} else {
						analyseQuerySpielPhase1(board, spieler);
					}
					break;
					
		case 2:     if(this.analyseQueryBefore(spieler, spielerB, board)) {
						System.out.println("RBS Agent: Eine allegemeine Regel hat gefeuert!");
					} else {
						analyseQuerySpielPhase2(board, spieler);
					}
					break;
					
		case 3: 	
					analyseQuerySpielPhase3(board, spieler);
					break;
					
		default:    break;
		}
		String ausgabeLog = "";
		if(dataBack.size() < 2) {
			ausgabeLog = "Leer";
		} else if(dataBack.isEmpty()) {
			System.out.println("Error!");
		} else {
			ausgabeLog = dataBack.get(1).toString();
		} 
		String spielzug = "RBS Agent: ";
		if(spieler.getSpielPhase() == 0) {
			spielzug += "Der Stein wurde auf: " + dataBack.get(0).toString() + " gesetzt.";
		} else if(spieler.getSpielPhase() == 3) {
			spielzug += "Der Stein:" + dataBack.get(0).toString() + "soll entfernt werden.";
		} else {
			spielzug += "Spielzug von " + dataBack.get(0).toString() + "nach " + dataBack.get(1).toString();
		}
		System.out.println(spielzug);
		logger.info("RBS Agent (Auswertung): Ergebnis war: Start:" + dataBack.get(0).toString() + "  Ziel:" + ausgabeLog);
		return this.dataBack;
	}	
/**
 * Die folgenden Methoden analysieren die Anfrage und liefern eine passende Antwort.
 * Je nach Spielphase wird eine andere Methode aufgerufen.	
 * @param board Spielstand
 * @param spieler Spieler der am Zug ist.
 */
	private void analyseQuerySpielPhase0(Board board, Spieler spieler) {
		for(RegelSpielPhase0 regel: spielphase0) {
			if(regel.getIfTeil().contains("Frei") && !board.checkAufBelegtFeld(regel.getIfStein().convertToFeld())) {
				this.dataBack.add(regel.getElseStein());
				spielphase0.remove(regel);
				break;
			} else  if(regel.getIfTeil().contains("Belegt") && board.checkAufBelegtFeld(regel.getIfStein().convertToFeld()) &&
						!board.checkAufBelegtFeld(regel.getElseStein().convertToFeld())){
				this.dataBack.add(regel.getElseStein());
				spielphase0.remove(regel);
				break;
			} else if(regel.getIfTeil().contains("Zufall")) {
				// Solange wie nichts gefunden wurde, suche weiter:)
				while(board.checkAufBelegtFeld(regel.getIfStein().convertToFeld())) {
					regel.erzeugeZufällig();
					if(!board.checkAufBelegtFeld(regel.getIfStein().convertToFeld())) {
						this.dataBack.add(regel.getElseStein());
						regel.erzeugeZufällig();
						break;
					}
				}
			}
		}
	}
	
	private void analyseQuerySpielPhase1(Board board, Spieler spieler) {
		List<Stein> spielData = spieler.getPosiSteine();
		
		for(RegelSpielPhase1u2 regel: spielphase1) {
			// Wenn der Spieler das Feld besetzt dann werte Regel weiter aus.
			if(spieler.steinIstVorhanden(regel.getBesetztesFeld()) && !board.checkAufBelegtFeld(regel.getBewegungsFeld().convertToFeld())) {
				dataBack.add(regel.getBesetztesFeld());
				dataBack.add(regel.getBewegungsFeld());
				break;
				
			} else if(regel.getIfTeil().contains("zufall")){
				for(int i = 0; i < spielData.size(); i++) {
					Stein steineins = spielData.get(i);
					if(steineins.getxCord() < 2) {
						Stein zwei = new Stein(steineins.getRing(), steineins.getxCord() + 1, steineins.getyCord(), null);
						if(!board.checkAufBelegtFeld(zwei.convertToFeld())) {
							dataBack.add(steineins);
							dataBack.add(zwei);
							break;
						}
						
					} else if(steineins.getyCord() <2 && steineins.getxCord() != 1) {
						Stein zwei = new Stein(steineins.getRing(), steineins.getxCord(), steineins.getyCord() + 1, null);
						if(!board.checkAufBelegtFeld(zwei.convertToFeld())) {
							dataBack.add(steineins);
							dataBack.add(zwei);
							break;
						}
					}
				}
			}
			
		}
		
		
		
	}
	private void analyseQuerySpielPhase2(Board board, Spieler spieler) {
		List<Stein> spielData = spieler.getPosiSteine();
		
		for(RegelSpielPhase1u2 regel: spielphase2) {
			// Wenn der Spieler das Feld besetzt dann werte Regel weiter aus.
			if(spieler.steinIstVorhanden(regel.getBesetztesFeld()) && !board.checkAufBelegtFeld(regel.getBewegungsFeld().convertToFeld())) {
				dataBack.add(regel.getBesetztesFeld());
				dataBack.add(regel.getBewegungsFeld());
				break;
				
			} else if(regel.getIfTeil().contains("zufall")){
				for(int i = 0; i < spielData.size(); i++) {
					Stein steineins = spielData.get(i);
					if(steineins.getxCord() < 2) {
						Stein zwei = new Stein(steineins.getRing(), steineins.getxCord() + 1, steineins.getyCord(), null);
						if(board.checkAufBelegtFeld(zwei.convertToFeld())) {
							dataBack.add(steineins);
							dataBack.add(zwei);
							break;
						}
						
					} else if(steineins.getyCord() <2 && steineins.getxCord() != 1) {
						Stein zwei = new Stein(steineins.getRing(), steineins.getxCord(), steineins.getyCord() + 1, null);
						if(board.checkAufBelegtFeld(zwei.convertToFeld())) {
							dataBack.add(steineins);
							dataBack.add(zwei);
							break;
						}
					}
				}
			}
			
		}
	}
	
	private void analyseQuerySpielPhase3(Board board, Spieler spieler) {
		
		for(RegelSpielPhase0 regel: spielphase3) {
			if(regel.getIfTeil().contains("Belegt") && board.checkAufBelegtFeld(regel.getIfStein().convertToFeld()) 
					&& !spieler.steinIstVorhanden(regel.getIfStein())) {
				this.dataBack.add(regel.getElseStein());
				System.out.println("Ein Stein wurde gefunden!");
				break;
			}  else if(regel.getIfTeil().contains("zufall")) {
				// Solange wie nichts gefunden wurde, suche weiter:)
				while(!board.checkAufBelegtFeld(regel.getIfStein().convertToFeld())) {
					regel.erzeugeZufällig();
					if(board.checkAufBelegtFeld(regel.getIfStein().convertToFeld()) && !spieler.steinIstVorhanden(regel.getIfStein())) {
						this.dataBack.add(regel.getElseStein());
						regel.erzeugeZufällig();
						break;
					}
				}
			}
		}
		System.out.println("Ergebnis ?" + this.dataBack.size());
	}

	
	private void setzeColor(Color color) {
		for(RegelSpielPhase0 regel: spielphase0) {
			regel.setColor(color);
		}
	}
	/**
	 * Diese Methode verwendet einen Trick um die Regeln am Anfang auszuwerten.
	 */
	private boolean analyseQueryBefore(Spieler spieler,Spieler spielerB,  Board board) {
		// Switch mit der Spielphase des Spielers
		boolean back = false;
		switch (spieler.getSpielPhase()) {
			case 0: 	// Werte nur Regel 1 aus
						List<Stein> rueckgabe = this.logikCheck.sucheZweiInGleicherReihe(spieler.getPosiSteine(), board);
						
						if(!rueckgabe.isEmpty() && !rueckgabe.get(0).equals(new Stein(0,0,0, null))) {
							dataBack.add(rueckgabe.get(0));
							back = true;
						} 
						break;
						
			case 1:		for(Regel regel: uebergreifendeRegeln) {
							if(regel.getIfTeil().contains("eigene Steine")) {
								List<Stein> getDataBack = this.logikCheck.sucheZweiGleicheReiheUnddrittenSteinDazu(spieler.getPosiSteine(), board);
								if(!getDataBack.isEmpty()) {
									Stein stein = getDataBack.get(1);
									dataBack.add(stein);
									Feld nachbarn = stein.convertToFeld();
									List<Feld> moeglicheZuege = nachbarn.allefreienNachbarn(board);
									if(!moeglicheZuege.isEmpty()) {
										dataBack.add(moeglicheZuege.get(0).convertToStein());
										back = true;
									} 
								}
								
							} else {
								List<Stein> zweiGegner = this.logikCheck.sucheZweiInGleicherReihe(spielerB.getPosiSteine(), board);
								if(!zweiGegner.isEmpty()) {
									List<Stein> result = this.logikCheck.sucheSteinInderNäheUmGegnerZuBlocken(zweiGegner, spieler);
									if(!result.isEmpty()) {
										Stein stein = result.get(0);
										dataBack.add(stein);
										Feld nachbarn = stein.convertToFeld();
										List<Feld> moeglicheZuege = nachbarn.allefreienNachbarn(board);
										if(!moeglicheZuege.isEmpty()) {
											dataBack.add(moeglicheZuege.get(0).convertToStein());
											back = true;
										}
									}
								}
							}
						}
						break;
						
			case 2:		List<Stein> rueckgabe2 = this.logikCheck.sucheZweiGleicheReiheUnddrittenSteinDazu(spieler.getPosiSteine(), board);
						if(!rueckgabe2.isEmpty()) {
							dataBack.add(rueckgabe2.get(0));
							dataBack.add(rueckgabe2.get(1));
							back = true;
						}
						break;
			
			default: break;	
		}
		return back;
	}
}

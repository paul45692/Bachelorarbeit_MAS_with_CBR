package de.blanke.ba.cbr;

import java.text.ParseException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import de.blanke.ba.logik.Board;
import de.blanke.ba.mas.AgentenOperations;
import de.blanke.ba.model.Feld;
import de.blanke.ba.model.Stein;
import de.blanke.ba.spieler.Spieler;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.model.IntegerDesc;

/**
 * Diese Klasse versucht einen Lernvorgang abzubilden, falls das CBR System gar keine neuen Fall
 * finden wird.
 * (Diese Klasse ist ein Prototyp und kommt wahrscheinlich nicht zum Einsatz.)
 * @author Paul Blanke
 *
 */
public class CBR_Learning_Process_Prototyp {
	
	int learningParameterA;
	int learningParameterB;
	AgentenOperations operation = new AgentenOperations();
	Random ran = new Random();
	private static final Logger logger = Logger.getLogger(CBRAgent.class);
	
	
	public Instance letsTryToLearnANewCase(Instance eingabe, Spieler spieler, Board board, IntegerDesc intA, IntegerDesc intB) {
		PropertyConfigurator.configure(CBRAgent.class.getResource("log4j.info"));
		logger.info(" CBR System: !! Ein neuer Fall wird versuchsweise angelernt!");
		System.out.println("!!! --- >CBR-System: Ein neuer Fall wird angelernt!");
		switch(spieler.getSpielPhase()) {
		
		case 0:		this.calcuteLearningSpielphase1(board);
					break;
					
		case 1:		this.calcuteLearningSpielphase2(board, spieler);
					break;
			
		case 2:		this.calcuteLearningSpielphase3(board, spieler);
					break;
			
		case 3:	   	this.calcuteLearningSpielphase4(board, spieler);
					break;
		
		}
		try {
			eingabe.addAttribute(intA, this.learningParameterA);
			eingabe.addAttribute(intB, this.learningParameterB);
		} catch (ParseException e) {
			System.out.println("CBR System: das Lernen eines neuen Falles ist fehlgeschlagen !!");
			logger.info("CBR System-Learning: Anlernen ist fehl geschlagen!");
			e.printStackTrace();
		}
		
		return eingabe;
	}
	
	/**
	 * Diese Methode lernen zufällig neue Parameter.
	 * @param board
	 */
	private void calcuteLearningSpielphase1(Board board) {
		while(true) {
			int tryTest = ran.nextInt((24-1));
			Stein stein = operation.getSteineFuerCBRSystem(tryTest);
			if(!board.checkAufBelegtFeld(stein.convertToFeld())) {
				this.learningParameterA = tryTest;
				this.learningParameterB = tryTest + 1;
				break;
			}
		}
	}
	
	private void calcuteLearningSpielphase2(Board board, Spieler spieler) {
		Stein aStart = null;
		Stein bZiel = null;
		for(int i = 0; i < 9; i++) {
			aStart = spieler.getPosiSteine().get(i);
			Feld b = aStart.convertToFeld();
			if(!b.allefreienNachbarn(board).isEmpty()) {
				bZiel = b.allefreienNachbarn(board).get(0).convertToStein();
				break;
			}
		}
		if(aStart != null && bZiel != null) {
			this.learningParameterA = operation.getIndexFromStein(aStart);
			this.learningParameterB = operation.getIndexFromStein(bZiel);
		} else {
			System.out.print("CBR-System: Das Lernen ist fehlgeschlagen!");
		}
	}
	private void calcuteLearningSpielphase3(Board board, Spieler spieler) {
		this.learningParameterA = operation.getIndexFromStein(spieler.getPosiSteine().get(0));
		
		while(true) {
			int tryTest = ran.nextInt((24-1));
			Stein stein = operation.getSteineFuerCBRSystem(tryTest);
			if(board.checkAufBelegtFeld(stein.convertToFeld())) {
				this.learningParameterB = tryTest;
				break;
			}
		}
		
	}
	private void calcuteLearningSpielphase4(Board board, Spieler spieler) {
		while(true) {
			int tryTest = ran.nextInt((24-1));
			Stein stein = operation.getSteineFuerCBRSystem(tryTest);
			if(!board.checkAufBelegtFeld(stein.convertToFeld()) && !spieler.steineIsVorhanden(stein)) {
				this.learningParameterA = tryTest;
				this.learningParameterB = tryTest + 1;
				break;
			}
		}
	}
}

package de.blanke.ba.logger;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Test {
	
	private static final Logger logger = Logger.getLogger(Test.class);
	
	public static void main(String[]args) {
		PropertyConfigurator.configure(Test.class.getResource("log4j.info"));
		logger.info("Programm gestarted");
	}

}

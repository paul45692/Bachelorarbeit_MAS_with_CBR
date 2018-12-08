package de.blanke.ba.cbr;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CBR_Test {
	
		private static final Logger logger = Logger.getLogger(CBR_Test.class);
		
		public static void main(String[]args) {
			PropertyConfigurator.configure(CBR_Test.class.getResource("log4j.info"));
			logger.info("CBR_Agent loggt hier rein");
		}
	
	
}

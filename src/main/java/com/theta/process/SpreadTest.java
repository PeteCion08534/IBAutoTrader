package com.theta.process;
import java.util.Set;

import org.apache.log4j.Logger;

import com.theta.dao.SpreadDAO;
import com.theta.domain.Spread;


public class SpreadTest {

	private static Logger log = Logger.getLogger(SpreadTest.class);

	SpreadDAO dao;
	
	
	public SpreadTest(SpreadDAO dao) {
		this.dao = dao;
	}
	
	
	public void run() throws InterruptedException , ThetaExceptionExc {
		
		Set<Spread> spreads = dao.findSpreadByPositionId(74122);
		
		for (Spread spread : spreads) {
			System.out.println("SOP: TOP of loop.  Make change now! Spread: " + spread.toStringTest());
			log.info("LOG: TOP of loop.  Make change now! Spread: " + spread.toStringTest());
			
			Thread.sleep(30000);
			
			System.out.println("SOP: MIDDLE of loop.  Spread: " + spread.toStringTest());
			log.info("LOG: MIDDLE of loop.  Spread: " + spread.toStringTest());
			
			dao.flush();
			
			
			System.out.println("SOP: After flush .  Spread: " + spread.toStringTest());
			log.info("LOG: After flush .  Spread: " + spread.toStringTest());

			try {
				
				verifySpreadVersion(spread);
				System.out.println("All is ok with versions.");
				log.info("All is ok with versions.");
				spread.setOpenOrClosed("OPEN");
				spread.setEnterApproval("HOLD_LMT");
				dao.store(spread);
			} catch (ThetaExceptionExc e) {
				 System.out.println("******** VERSIONS DO NOT MATCH. ");
				 log.info("******** VERSIONS DO NOT MATCH. ");
			}
			
			
			System.out.println("SOP: After store .  Spread: " + spread.toStringTest());
			log.info("LOG: After store .  Spread: " + spread.toStringTest());
		}
		
		
		
	}
	
	
	public void verifySpreadVersion(Spread spread) throws ThetaExceptionExc {
		
		System.out.println("TOP of verifySpreadVersion!");
		Spread testSpread = dao.findSpreadBySpreadId(spread.getSpreadId());
		System.out.println("TOP of verifySpreadVersion - AFTER FIND!");
		System.out.println("Here are the ids: test: " + testSpread.getVersion());
		System.out.println("Here are the ids: spread: " + spread.getVersion());
		
		if ( !testSpread.getVersion().equals(spread.getVersion()) ) {
			System.out.println("NOT EQUAL - THROWING...");
			throw new ThetaExceptionExc("Versions are not equal.");
		}
		
	}
	
}

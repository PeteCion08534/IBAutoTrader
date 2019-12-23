package com.theta.process;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.theta.client.PortfolioItem;
import com.theta.client.ThetaClient3;
import com.theta.client.ThetaClientInterface;
import com.theta.dao.PositionDAO;
import com.theta.dao.SpreadDAO;
import com.theta.dao.HeartbeatDAO;
import com.theta.dao.StrategyAccountDAO;
import com.theta.domain.IbAccount;
import com.theta.domain.Position;
import com.theta.domain.Spread;
import com.theta.domain.StrategyAccount;
import com.theta.enums.OpenOrClosedCode;

public class BalanceIntVsExt {

	SpreadDAO spreadDAO;
	PositionDAO positionDAO;
	ThetaClientInterface client;
	ThetaUtil thetaUtil;
		
	protected BalanceIntVsExt(SpreadDAO _spreadDAO, PositionDAO _positionDAO, ThetaClientInterface _client, ThetaUtil _thetaUtil){
		positionDAO = _positionDAO;
		spreadDAO = _spreadDAO;
		client = _client;
		thetaUtil = _thetaUtil;
	}
	
	private static Logger log = Logger.getLogger(ThetaMain.class);

	
	
	protected void balanceAgainstReality(String IbAccountIdExt, StrategyAccountDAO strategyAccountDAO)
	throws ThetaExceptionExc {

		log.info("\n **** BALANCE AGAINST REALITY **** \n");
		
		ArrayList<OptionCompare> fromDB = new ArrayList<OptionCompare>(400);
		ArrayList<OptionCompare> fromIB = new ArrayList<OptionCompare>(400);
		
		Set<Spread> allOpenOptSpreadsForAcct = new HashSet<Spread>(400);
		Set<Spread> allOpenSpreads = spreadDAO.findSpreadByOpenOrClosed("OPEN");
		Set<Spread> allPendSpreads = spreadDAO.findSpreadByOpenOrClosedContaining("PEND");
		Set<Spread> allHailMarySpreads = spreadDAO.findSpreadByOpenOrClosedContaining("HAILMARY");

		Set<Spread> allNotExecSpreads = spreadDAO.findSpreadByOpenOrClosed("NOTEXEC");
		
		addOptSpreadsForAccount(allOpenSpreads, allOpenOptSpreadsForAcct, IbAccountIdExt, strategyAccountDAO);
		addOptSpreadsForAccount(allPendSpreads, allOpenOptSpreadsForAcct, IbAccountIdExt, strategyAccountDAO);
		addOptSpreadsForAccount(allHailMarySpreads, allOpenOptSpreadsForAcct, IbAccountIdExt, strategyAccountDAO);
		
		try {
		    addIBInfoToFromIB(fromIB, IbAccountIdExt);
		    log.info("After addIBInfoToFromIB.  here is fromIB size: " + fromIB.size());
		} catch (java.util.ConcurrentModificationException ex) {
			log.error("\n\n **** Problem with adding info from IB - client is still adding data - quitting balanceAgainstReality!!! **** \n\n", ex);
			return;
		}
		   
		int allOpenSpreadsSize = allOpenOptSpreadsForAcct.size();
		if (allOpenSpreadsSize == 0 ) {
			log.info("MATCH - allOpenSpreadsForAcct is 0!. ");
			return;
		}
		log.info("Just after if statement.  Here is allOpenSpreadsSize: " + allOpenSpreadsSize);
		
		addDBInfoToFromDB(fromDB, allOpenOptSpreadsForAcct, strategyAccountDAO);

		log.info("After addDBInfoToFromDB.  Here is fromDB size: " + fromDB.size());
		
		List<OptionCompare> IBOptNoMatch = balanceFromIBWithFromDB(fromIB, fromDB);
		
		//int fromIBSize = fromIB.size();
		//int IBOptNoMatchSize = IBOptNoMatch.size();

		/*
		int oneThirdOfOrigSize = origFromIBSize/3;
		if (IBOptNoMatchSize < oneThirdOfOrigSize) {
		} else {
		}
		*/
	    // go ahead - balance anyway!
		log.info("Just after balanceFromIBWithFromDB.  IBOptNoMatchSize : fromIBSize " + IBOptNoMatch.size() + " : " + fromIB.size()  );
		updateNotExecSpreadsFromNoMatch(allNotExecSpreads, IBOptNoMatch, spreadDAO);

	    
		// Write out to a file
		StringBuilder builder = new StringBuilder();
		builder.append("\n" + OptionCompare.headerString() + "\n");
		for (OptionCompare fromib : fromIB) {
			if (fromib.getPrintToFile()) {
				builder.append("NOMATCH(IB): " + fromib.toString() + "\n");
			    log.info("NOMATCH(IB): " + fromib.toString() + "\n");
			}
		}
		for (OptionCompare fromdb : fromDB) {
			if (fromdb.getPrintToFile()) {
				builder.append("NOMATCH(DB): " + fromdb.toString() + "\n");
				log.info("NOMATCH(DB): " + fromdb.toString() + "\n");
			}
		}

		String opCompareStr = builder.toString();
		String matchFileName = ThetaConstants.MATCH_FILE;
		ThetaUtil.writeToFile(opCompareStr, matchFileName);
		
	}
	
	/**
	 * 
	 * @param allNotExecSpreads
	 * @param IBOptNoMatch
	 */
	private void updateNotExecSpreadsFromNoMatch(Set<Spread> allNotExecSpreads, List<OptionCompare> IBOptNoMatch, SpreadDAO spreadDao){
		log.info("in updateNotExecSpreadsFromNoMatch!");
		
		for ( Spread notExecSpread : allNotExecSpreads ) {

			Calendar threeDaysAgo = Calendar.getInstance();
			threeDaysAgo.add(Calendar.DAY_OF_MONTH, -3);
			
			Calendar todayOneAM = Calendar.getInstance();
			todayOneAM.set(Calendar.HOUR, 1);
			todayOneAM.set(Calendar.MINUTE, 0);
			
			log.info("created date, threeDaysAgo: " +notExecSpread.getCreatedDate().getTime() + " : " + threeDaysAgo.getTime() );
			
			if (notExecSpread.getCreatedDate().after(threeDaysAgo) &&
					notExecSpread.getCreatedDate().before(todayOneAM)) {
				String moneymkrPositionId = notExecSpread.getMoneymkrPositionId();
				String insurancePositionId = notExecSpread.getInsurancePositionId();
				
				log.info("updateNotExecSpreads:  " + notExecSpread.getCreatedDate().getTime() + " : " + moneymkrPositionId + " : " + insurancePositionId);
				
				boolean moneymkrPositionMatches = false;
				boolean insurancePositionMatches = false;
				for (OptionCompare optionCompare : IBOptNoMatch) {
					String IBOptNoMatchConId = optionCompare.getConId();
					log.info("IBOptNoMatchConId: " + IBOptNoMatchConId);
					if (IBOptNoMatchConId.equals(moneymkrPositionId))
						moneymkrPositionMatches = true;
					if (IBOptNoMatchConId.equals(insurancePositionId))
						insurancePositionMatches = true;
				}
				if (moneymkrPositionMatches && insurancePositionMatches){
					log.info("Spread: " + notExecSpread);
					
					notExecSpread.setOpenOrClosed("OPEN");
					notExecSpread.setEnterMoneymkrDate(Calendar.getInstance());
					notExecSpread.setEnterInsuranceDate(Calendar.getInstance());
					notExecSpread.setReopenDate(Calendar.getInstance());
					try {
					    spreadDAO.store(notExecSpread);
					} catch (Exception ex){
						log.error("Error storing notExecSpread to spreadDAO!", ex);
						log.info("Here is notExecSpread: " + notExecSpread);
					}
					return;
				}
			}
		}
	}
		
	
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @param IbAccountIdExt
	 * @throws ThetaExceptionExc
	 */
	private void addOptSpreadsForAccount(Set<Spread> from, Set<Spread> to, String IbAccountIdExt, StrategyAccountDAO strategyAccountDAO)
	throws ThetaExceptionExc {

		Iterator<Spread> fromIter = from.iterator();
		while (fromIter.hasNext()){
			Spread spread = (Spread) fromIter.next();
			
			Integer stkNumShares = spread.getStkNumShares();
			if (stkNumShares != null && stkNumShares > 0){
				log.info("StkNumShares is more than 0 : " + stkNumShares + " - not including in MATCH");
				continue;
			}
			
			
			Integer positionId = spread.getPositionId();
			Position position = positionDAO.findPositionByPositionId(positionId);
			
			//Position position = spread.getPosition();
			//Position position = positionDAO.find();
			
			StrategyAccount strategyAcct = strategyAccountDAO.findStrategyAccountByPrimaryKey(position.getStrategyAccountId());
			IbAccount ibAccount = strategyAcct.getIbAccount();
			if (IbAccountIdExt.equals(ibAccount.getIbAccountIdExt())) {
				to.add(spread);
				//log.info("Spread: " + spread.toStringShort() );
			}
		}		
	}

	/**
	 * 
	 * @param fromIB
	 * @return
	 * @throws ThetaExceptionExc
	 */
	protected boolean addIBInfoToFromIB(ArrayList<OptionCompare> fromIB, String accountCode)
	throws ThetaExceptionExc, java.util.ConcurrentModificationException {

		ThetaClient3 client3 = (ThetaClient3) client;
		
		// Note - it looks like the code is not waiting long enough after requesting the data
		// from the client.  If the code starts to iterate through the allPortfolioItems Set
		// and items are still being placed into this by the client, this will result in 
		// a java.util.ConcurrentModificationException.
		client3.portfolioItems.clear();
		//client3.reqAccountUpdates(accountCode);
		thetaUtil.secondsToSleep(ThetaConstants.THIRTY_INT);
		//Set<PortfolioItem> allPortfolioItems = new HashSet<PortfolioItem>(client3.portfolioItems);
		Set<PortfolioItem> allPortfolioItems = client3.portfolioItems;
		
		Iterator<PortfolioItem> allPortfolioItemsIter = allPortfolioItems.iterator();
		Integer IbId = ThetaConstants.ZERO_INT;
		while (allPortfolioItemsIter.hasNext()){
			PortfolioItem pfItem = (PortfolioItem) allPortfolioItemsIter.next();
			//String matchMsg = "PortfolioItem (MATCH): " + pfItem.getPosition() + "," +
			//		pfItem.getContract().m_expiry + "," +
			//		pfItem.getContract().m_right + "," +
			//		pfItem.getContract().m_strike + "," +
			//		pfItem.getContract().m_conId;

			//Calendar now = Calendar.getInstance();
			//log.info(matchMsg);
			//thetaUtil.enterMetricsIntoHeartbeat(matchMsg, 0, now, now);
			//log.info("Here is portfolioItem: " + pfItem.toString());
		
			
			// ONLY options!
			if (!pfItem.getContract().m_secType.equals("OPT")){
				continue;
			}
			
			Integer numOptions = pfItem.getPosition();
			Integer numOptionsAbs = Math.abs(numOptions);
			Integer posOrNeg = ThetaConstants.ZERO_INT;
			if (numOptionsAbs != 0) {
				posOrNeg = numOptions / numOptionsAbs;
			}
			for(int i=ThetaConstants.ZERO_INT; i < numOptionsAbs; i++){

				OptionCompare opt = new OptionCompare();
				opt.setOptionCompareId(IbId); 
				IbId++;
				opt.setAccountName(pfItem.getAccountName());
				opt.setSymbol(pfItem.getContract().m_symbol);
				opt.setExpMonth(pfItem.getContract().m_expiry);
				opt.setExpYear(pfItem.getContract().m_expiry);

				Double strikeDouble = (pfItem.getContract().m_strike * ThetaConstants.ONE_HUNDRED_DOUBLE);
				opt.setStrikePrice(strikeDouble.intValue());
				opt.setRight(pfItem.getContract().m_right);
				opt.setConId(new Integer(pfItem.getContract().m_conId).toString());
				opt.setPosition(posOrNeg);

				/*
				averageCost = 53.9855;
		    	marketPrice = 1.016;
		    	marketValue = 101.6;
		    	position = 2;
		    	realizedPNL = 0.0;
		    	unrealizedPNL = -6.02;
				*/
				
				// External (per position!)
				Integer extAverageCostI = (int) ( pfItem.getAverageCost() * ThetaConstants.ONE_HUNDRED_DOUBLE );
				opt.setExtAverageCost(extAverageCostI);
				Integer extMarketPriceI = (int) ( pfItem.getMarketPrice() * ThetaConstants.TEN_THOUSAND_DOUBLE );
				opt.setExtMarketPrice(extMarketPriceI);
				
				if (numOptionsAbs != ThetaConstants.ZERO_INT) {
					Integer extUnrealizedPNLPerPosnI = (int) ( (pfItem.getUnrealizedPNL() * ThetaConstants.ONE_HUNDRED_DOUBLE) / numOptionsAbs);
					opt.setExtUnrealizedPNLPerPosn(extUnrealizedPNLPerPosnI);
				}
				fromIB.add(opt); 
				//thetaUtil.enterRecordIntoHeartbeat("FROM IB (for MATCH):, " + opt.toString());
			}//	for(int i=ThetaConstants.ZERO_INT; i < numOptionsAbs; i++){
		} // while (allPortfolioItemsIter.hasNext()){

		return true;
	} //private void addIBInfoToFromIB(ArrayList<OptionCompare> fromIB)

	/**
	 * 
	 * @param fromDB
	 * @param allOpenSpreadsForAcct
	 * @return
	 * @throws ThetaExceptionExc
	 */
	private boolean addDBInfoToFromDB(ArrayList<OptionCompare> fromDB, Set<Spread> allOpenSpreadsForAcct, StrategyAccountDAO strategyAccountDAO)
	throws ThetaExceptionExc {
		Integer DBId = ThetaConstants.ZERO_INT;
		Iterator<Spread> allOpenSpreadsForAcctIter = allOpenSpreadsForAcct.iterator();
		while (allOpenSpreadsForAcctIter.hasNext()){
			Spread spr = (Spread) allOpenSpreadsForAcctIter.next();
			//log.info("Spread: " + spr.toString());

			boolean addMMComponent = true;
			boolean addInsComponent = true;
			
			if ( spr.getOpenOrClosed().equals(com.theta.enums.OpenOrClosedCode.HAILMARY.toString()) ){
				log.info("addDBInfoToFromDB: in Hail Mary! - addMMComponent is being set to false.");
				addMMComponent = false;
				// Exit Insurance Date is filled in - do not add - HailMary is now closed
				if (null != spr.getExitInsuranceDate()){
					log.info("addDBInfoToFromDB: in Hail Mary! - exit insurance date is null - add InsComponent is being set to false.");
					addInsComponent = false;
				}
			}
			
			Integer positionId = spr.getPositionId();
			Position position = positionDAO.findPositionByPositionId(positionId);
			
			StrategyAccount sa = strategyAccountDAO.findStrategyAccountByPrimaryKey(position.getStrategyAccountId());

			/**
			 * MoneyMaker 		
			 */
			if (addMMComponent) {
				OptionCompare optMM = new OptionCompare();
				optMM.setOptionCompareId(DBId);
				DBId++;

				optMM.setAccountName(sa.getIbAccount().getIbAccountIdExt());
				optMM.setSymbol(position.getSymbol());
				optMM.setExpMonth(position.getExpiryMonth().toString());
				optMM.setExpYear(position.getExpiryYear().toString());

				optMM.setStrikePrice(spr.getStrikeMoneymkr());
				optMM.setRight(position.getOptRight());
				optMM.setConId(spr.getMoneymkrPositionId());
				optMM.setPosition(ThetaConstants.NEGATIVE_ONE_INT);

				optMM.setIntMmOrIns("MM");
				optMM.setIntSpreadId(spr.getSpreadId());
				optMM.setIntOpenOrClosed(spr.getOpenOrClosed());

				// Internal (per position!)
				optMM.setIntEnterSecPrice(spr.getEnterSecurityPrice());
				optMM.setIntEnterPrice(spr.getEnterMoneymkrPrice());
				optMM.setIntCurrentPrice(spr.getCurrentMoneymkrPrice());
				optMM.setIntCommission(spr.getEnterCommission());
				optMM.setIntEnterTriggerDt(spr.getEnterTriggerDate());
				fromDB.add(optMM);
			}
			
			/**
			 * Insurance		
			 */
			if (addInsComponent) {
				OptionCompare optINS = new OptionCompare();
				optINS.setOptionCompareId(DBId);
				DBId++;
				optINS.setAccountName(sa.getIbAccount().getIbAccountIdExt());
				optINS.setSymbol(position.getSymbol());
				optINS.setExpMonth(position.getExpiryMonth().toString());
				optINS.setExpYear(position.getExpiryYear().toString());

				optINS.setStrikePrice(spr.getStrikeInsurance());
				optINS.setRight(position.getOptRight());
				optINS.setConId(spr.getInsurancePositionId());
				optINS.setPosition(ThetaConstants.ONE_INT);

				optINS.setIntMmOrIns("INS");
				optINS.setIntSpreadId(spr.getSpreadId());
				optINS.setIntOpenOrClosed(spr.getOpenOrClosed());

				// Internal (per position!)
				optINS.setIntEnterSecPrice(spr.getEnterSecurityPrice());
				optINS.setIntEnterPrice(spr.getEnterInsurancePrice());
				optINS.setIntCurrentPrice(spr.getCurrentInsurancePrice());
				optINS.setIntCommission(spr.getEnterCommission());
				optINS.setIntEnterTriggerDt(spr.getEnterTriggerDate());
				fromDB.add(optINS);
			}
			
		} // while (allOpenSpreadsForAcctIter.hasNext()){
		return true;
	} // private boolean addDBInfoToFromDB(ArrayList<OptionCompare> fromDB, Set<Spread> allOpenSpreadsForAcct)

	
	private List<OptionCompare> balanceFromIBWithFromDB(ArrayList<OptionCompare> fromIB, ArrayList<OptionCompare> fromDB)
	throws ThetaExceptionExc {

		log.info(OptionCompare.headerString());
		thetaUtil.enterRecordIntoHeartbeat(OptionCompare.headerString());

		fromDB = eliminateOpposites(fromDB);
		
		log.info("In balanceFromIBWithFromDB.  Here is fromDB size after eliminateOpposites: " + fromDB.size());
		
		Iterator<OptionCompare> fromDBIter = fromDB.iterator();
		while (fromDBIter.hasNext()){
			OptionCompare fromDBOpt = (OptionCompare) fromDBIter.next();
			log.info("Spread (from DB): " + fromDBOpt.toString());
			
			Iterator<OptionCompare> fromIBIter = fromIB.iterator();
			while (fromIBIter.hasNext()){
				OptionCompare fromIBOpt = (OptionCompare) fromIBIter.next();
				
				if (fromDBOpt.getConId().equals(fromIBOpt.getConId())){
					if ( !fromIBOpt.isMatch() && !fromDBOpt.isMatch() ){
						log.info("MATCH!");
						fromDBOpt.setMatch(true);
						fromDBOpt.setMatchOptionCompareId(fromIBOpt.getOptionCompareId());
						
						fromIBOpt.setMatch(true);
						fromIBOpt.setMatchOptionCompareId(fromDBOpt.getOptionCompareId());
						
						fromDBOpt.setExtAverageCost(fromIBOpt.getExtAverageCost());
						fromDBOpt.setExtMarketPrice(fromIBOpt.getExtMarketPrice());
						//fromDBOpt.setExtMarketValuePerPosn(fromIBOpt.getExtMarketValuePerPosn());
						//fromDBOpt.setExtRealizedPNLPerPosn(fromIBOpt.getExtRealizedPNLPerPosn());
						fromDBOpt.setExtUnrealizedPNLPerPosn(fromIBOpt.getExtUnrealizedPNLPerPosn());
						//log.info("MATCH: " + fromDBOpt.toString());
						//thetaUtil.enterRecordIntoHeartbeat("MATCH, " + fromDBOpt.toString());
					} // if (!fromIBOpt.isMatch()){
				} // if (fromDBOpt.getConId().equals(fromIBOpt.getConId())){
			} // while (fromIBIter.hasNext()){
		} // while (fromDBIter.hasNext()){

		fromDBIter = fromDB.iterator();
		//log.info("NO MATCH - Iterate through DB Array!");
		while (fromDBIter.hasNext()){
			OptionCompare fromDBOpt = (OptionCompare) fromDBIter.next();
			log.info("ORIG (DB): " + fromDBOpt.toString());
			
			if (!fromDBOpt.isMatch()){
				log.info("NO MATCH (DB): " + fromDBOpt.toString());
				thetaUtil.enterRecordIntoHeartbeat("NO MATCH (DB)," + fromDBOpt.toString());
				fromDBOpt.setPrintToFile();
			}
		} // while (fromDBIter.hasNext()){

		
		Iterator<OptionCompare> fromIBIter = fromIB.iterator();
		log.info("NO MATCH - Iterate through IB Array!");
		List<OptionCompare> IBOptNoMatch = new ArrayList<OptionCompare>();
		while (fromIBIter.hasNext()){
			OptionCompare fromIBOpt = (OptionCompare) fromIBIter.next();
			log.info("ORIG (IB): " + fromIBOpt.toString());

			if (!fromIBOpt.isMatch()){
				log.info("IB Array - NO MATCH: " + fromIBOpt.toString());
				thetaUtil.enterRecordIntoHeartbeat("NO MATCH (IB)," + fromIBOpt.toString());
				IBOptNoMatch.add(fromIBOpt);
				fromIBOpt.setPrintToFile();
			}
		} // while (fromIBIter.hasNext()){

		thetaUtil.enterRecordIntoHeartbeat(OptionCompare.headerString());
		
		return IBOptNoMatch;
		// Last piece : update Spread with Actual Average Entry Prices
		
	} // private void balanceFromIBWithFromDB(ArrayList<OptionCompare> fromIB, ArrayList<OptionCompare> fromDB)
	

	private ArrayList<OptionCompare> eliminateOpposites(ArrayList<OptionCompare> fromDB)
	throws ThetaExceptionExc {
		
		ArrayList<OptionCompare> fromDBRet = new ArrayList<OptionCompare>();

		ArrayList<OptionCompare> fromDBClone = (ArrayList<OptionCompare>) fromDB.clone();

		boolean isMatch = false;
		for (OptionCompare opt : fromDB){
			isMatch = false;
			for (OptionCompare clone : fromDBClone){
				if ( opt.isCounterPosition(clone) ) {
					clone.setMatch(true);
					isMatch = true;
					break;
				}
			}
			if (!isMatch)
				fromDBRet.add(opt);
		}
		
		return fromDBRet;
	}

	
    public static void main(String[] args) {
		ArrayList<OptionCompare> testArr = new ArrayList<OptionCompare>();

    	OptionCompare comp1 = new OptionCompare();
    	comp1.setSymbol("SPY");
    	comp1.setStrikePrice(140);
    	comp1.setExpMonth("5");
    	comp1.setExpYear("2012");
    	comp1.setRight("P");
    	comp1.setIntOpenOrClosed("OPEN");
    	comp1.setPosition(1);
    	testArr.add(comp1);

    	OptionCompare comp2 = new OptionCompare();
    	comp2.setSymbol("SPY");
    	comp2.setStrikePrice(139);
    	comp2.setExpMonth("5");
    	comp2.setExpYear("2012");
    	comp2.setRight("P");
    	comp2.setIntOpenOrClosed("OPEN");
    	comp2.setPosition(1);
    	testArr.add(comp2);
    	
    	OptionCompare comp3 = new OptionCompare();
    	comp3.setSymbol("SPY");
    	comp3.setStrikePrice(139);
    	comp3.setExpMonth("5");
    	comp3.setExpYear("2012");
    	comp3.setRight("P");
    	comp3.setIntOpenOrClosed("OPEN");
    	comp3.setPosition(-1);
    	testArr.add(comp3);

    	OptionCompare comp4 = new OptionCompare();
    	comp4.setSymbol("SPY");
    	comp4.setStrikePrice(139);
    	comp4.setExpMonth("5");
    	comp4.setExpYear("2012");
    	comp4.setRight("P");
    	comp4.setIntOpenOrClosed("OPEN");
    	comp4.setPosition(-1);
    	testArr.add(comp4);

    	OptionCompare comp5 = new OptionCompare();
    	comp5.setSymbol("SPY");
    	comp5.setStrikePrice(139);
    	comp5.setExpMonth("5");
    	comp5.setExpYear("2012");
    	comp5.setRight("P");
    	comp5.setIntOpenOrClosed("OPEN");
    	comp5.setPosition(-1);
    	testArr.add(comp5);

    	BalanceIntVsExt bi = new BalanceIntVsExt();
    	ArrayList<OptionCompare> outArr = null;
    	
    	try {
			outArr = bi.eliminateOpposites(testArr);
		} catch (ThetaExceptionExc e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	for (OptionCompare ta : testArr){
    		System.out.println("IN: " + ta.toString());
    	}
    	System.out.println("======================");
    	for (OptionCompare oa : outArr){
    		System.out.println("OUT: " + oa.toString());
    	}
    	
    }

	protected BalanceIntVsExt(){}

}
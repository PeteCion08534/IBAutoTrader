package com.theta.process;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ib.client.ContractDetails;
import com.theta.client.DateUtil;
import com.theta.client.PositionExecutor;
import com.theta.client.PriceRetriever;
import com.theta.client.ResponseInterface;
import com.theta.client.ThetaClientInterface;
import com.theta.client.enums.EnterOrExitCode;
import com.theta.dao.PositionDAO;
import com.theta.dao.RequestSeqDAO;
import com.theta.dao.SecurityPriceDAO;
import com.theta.dao.SpreadDAO;
import com.theta.domain.IbAccount;
import com.theta.domain.Position;
import com.theta.domain.SecurityPrice;
import com.theta.domain.Spread;
import com.theta.domain.Strategy;
import com.theta.domain.StrategyAccount;
import com.theta.enums.ApprovalCode;
import com.theta.enums.BuyOrSellCode;
import com.theta.enums.LmtOrStpLmtCode;
import com.theta.enums.LoMidHiCode;
import com.theta.enums.LongOrShortCode;
import com.theta.enums.MinOrMaxCode;
import com.theta.enums.MovingSpeedCode;
import com.theta.enums.OpenOrClosedCode;
import com.theta.enums.OptRightCode;
import com.theta.enums.UpOrDownCode;

public class PositionHandler implements Runnable {

	private static Logger log = Logger.getLogger(PositionHandler.class);

	MovingAverages movingAverages;
	IbAccount ibAccount;
	Position position;
	PositionDAO positionDAO;
	RequestSeqDAO requestSeqDAO;
	SecurityPriceDAO securityPriceDAO;
	SpreadDAO spreadDAO;
	StrategyAccount strategyAccount;
	Strategy strategy;
	ThetaClientInterface client;
	ThetaUtil thetaUtil;
	PriceRetriever priceRetriever;
	PositionExecutor positionExecutor;
	ThetaMutex dbAccessMutex;
	
	String stratName;
	
	public PositionHandler (
			//MovingAverages movingAverages,
			IbAccount ibAccount,
			Position position,
			PositionDAO positionDAO,
			RequestSeqDAO requestSeqDAO,
			SecurityPriceDAO securityPriceDAO,
			SpreadDAO spreadDAO,
			StrategyAccount strategyAccount,
			Strategy strategy,
			ThetaClientInterface client,
			ThetaUtil thetaUtil,
			PriceRetriever priceRetriever,
			PositionExecutor positionExecutor,
			ThetaMutex dbAccessMutex
			) {
		//this.movingAverages = movingAverages;
		this.ibAccount = ibAccount;
		this.position = position;
		this.positionDAO = positionDAO;
		this.requestSeqDAO = requestSeqDAO;
		this.securityPriceDAO = securityPriceDAO;
		this.spreadDAO = spreadDAO;
		this.strategyAccount = strategyAccount;
		this.strategy = strategy;
		this.client = client;
		this.thetaUtil = thetaUtil;
		this.priceRetriever = priceRetriever;
		this.positionExecutor = positionExecutor;
		this.dbAccessMutex = dbAccessMutex;
		
		this.stratName = strategy.getStrategyName() + ": ";
	}
	
	
	public void run() {

		
		Integer underlyingSecurityPrice;
		try {
			
			if (ThetaUtil.isPositionInPast(position)){return;}

			/*********************************************************************************************/
			/***** VERIFY FUNDS **************************************************************************/
			/*********************************************************************************************/
			String ibAccountId;
			Integer stopAtMarginPct;
			synchronized(dbAccessMutex) {
				ibAccountId = ibAccount.getIbAccountIdExt();
				stopAtMarginPct = ibAccount.getStopAtMarginPct();
			}

			client.reqAccountUpdates(ibAccountId);
			ThetaUtil.secondsToSleepNoThrow(ThetaConstants.FIVE_INT);

			boolean areEnoughFundsToEnterPosition = enoughFundsToEnterPosition(client.getResp().getNetLiquidation(),
					client.getResp().getLookAheadAvailableFunds(),
					stopAtMarginPct );

			if (client.getGlobalCanEnterFlag()) {
				client.setGlobalCanEnterFlag(areEnoughFundsToEnterPosition);
			}

			log.info(stratName + "AFTER REQ ACCOUNT VALUES (liquidation, margin, available funds, enough funds?) : " + client.getResp().getNetLiquidation() + ", " 
	    	                                + client.getResp().getLookAheadInitMarginReq() + ", " 
					                        + client.getResp().getLookAheadAvailableFunds() + ", "
					                        + areEnoughFundsToEnterPosition);

			/*********************************************************************************************/
			
			Calendar now = Calendar.getInstance();
			
			position.setInitialValuesIfEmpty();

			log.info(stratName + "Here is position: " + position.toStringShort());
			thetaUtil.millisToSleep(200);

			
			Integer VIXPrice = ThetaConstants.FIVE_THOUSAND;

			/*********************************************************************************************/
			/***** GET UNDERLYING SECURITY PRICE *********************************************************/
			/*********************************************************************************************/
			try {
				underlyingSecurityPrice = priceRetriever.getCurrentSecurityPrice(strategy.getSymbol(), position.getPositionId(), client, dbAccessMutex);
			} catch (ThetaExceptionExc ex) {
				log.info(stratName + "Caught an exception checking prices - PAUSE, RETRY then throw..." + ex.toString() );
				ThetaUtil.secondsToSleepNoThrow(ThetaConstants.TEN_INT);

				try {
					underlyingSecurityPrice = priceRetriever.getCurrentSecurityPrice(strategy.getSymbol(), position.getPositionId(), client, dbAccessMutex);
				} catch (ThetaExceptionExc ex2) {
					log.info(stratName + "Caught another exception - setting securityPrice to null - to be obtained through other channels." );
					underlyingSecurityPrice = -1;
				}
			}
			
			/*********************************************************************************************/
			/***** GET/CALCULATE MOVING AVERAGES *********************************************************/
			/*********************************************************************************************/
			MovingAverages movingAverages = new MovingAverages(securityPriceDAO, dbAccessMutex);
			MovingAverages.AllMovingAverages allMovAvgs = null;	
			if (underlyingSecurityPrice != null) {
				allMovAvgs = movingAverages.getAllMovingAverages(position.getSymbol(), 
						strategy.getMovingAvg1(), 
						strategy.getMovingAvg2(), 
						strategy.getMovingAvgRange(), 
						underlyingSecurityPrice, 
						strategy.getMovingAvgGracePoints(),
						strategy.getMovingAvgTolerancePct());

				if (allMovAvgs == null) {
					insertIntoSecurityPrice(strategy.getSymbol(), underlyingSecurityPrice, "IB", false, 0, 0, 0, null, strategy.getLongOrShortCode().getValue());
					throw new ThetaExceptionExc("allMovAvgs is null - Quitting.");
				}
				
				client.setCanEnterMap(position.getPositionId(), true);
				if (allMovAvgs.movingAverageOutOfRange && !underlyingSecurityPrice.equals(-1)) {
					client.setCanEnterMap(position.getPositionId(), false);
					log.info("allMovAvgs.movingAverageOutOfRange is true.  Do not allow entry into a stock.");
				} 
				
				log.info(stratName + "Before insert with Moving Averages.  Ticker, allMovAvgs: " + position.getSymbol() + ", " + allMovAvgs);


				if (allMovAvgs.hiAvg != null && allMovAvgs.hiAvg != 0 &&
						allMovAvgs.loAvg != null && allMovAvgs.loAvg != 0) {
					try {
						Integer pctSecPriceOfMovAvgsHi = (underlyingSecurityPrice * 100)/allMovAvgs.hiAvg;
						Integer pctSecPriceOfMovAvgsLo = (underlyingSecurityPrice * 100)/allMovAvgs.loAvg;

						log.info(stratName + "here are percentages of sec price to mov avg - hi and low: " + pctSecPriceOfMovAvgsHi + " and " + pctSecPriceOfMovAvgsLo );
						if ( !underlyingSecurityPrice.equals(-1) && (pctSecPriceOfMovAvgsHi.compareTo(55) < 0 || pctSecPriceOfMovAvgsLo.compareTo(55) < 0 )) {
							strategy.setWarning(now.getTime() + "-SecPrice < 55% movAvg!. pr,hi,lo :" + underlyingSecurityPrice + " : " + allMovAvgs.hiAvg + " : " + allMovAvgs.loAvg);
						}
					}
					catch (ArithmeticException ae) {
						log.error(stratName + "Arithmetic Exception : ", ae);
					}

				}

			}

			/*********************************************************************************************/
			/***** GET/CALCULATE SLOPES  *****************************************************************/
			/*********************************************************************************************/

			Integer NUM_MINUTES_TO_CHECK_DUP_QUOTES = 35;
			Calendar now2 = Calendar.getInstance();
			Calendar checkDupQuotesFrom = Calendar.getInstance();
			checkDupQuotesFrom.add(Calendar.MINUTE, -NUM_MINUTES_TO_CHECK_DUP_QUOTES);
			Set<SecurityPrice> secPrices = securityPriceDAO.findSecurityPricesByTickerAndSourceSinceTime(position.getSymbol(), "IB", checkDupQuotesFrom, now2);

			Slope slopeMain = new Slope(securityPriceDAO, now, underlyingSecurityPrice, strategy, dbAccessMutex);
			if (underlyingSecurityPrice == null || underlyingSecurityPrice.equals(-1) ||
					( (secPrices.size() > 1) && thetaUtil.areAllPricesIdentical(secPrices, underlyingSecurityPrice)) ) {
				Integer yahooPrice = null;
				try {
					yahooPrice = thetaUtil.getCurrentSecurityPriceFromYahoo(strategy.getSymbol(), client);
				} catch (ThetaExceptionExc ex3){
					log.error(stratName + "Exception in getting Price from Yahoo (First time) - sleeping 3 seconds and re-trying ...");
					Thread.sleep(3000);
					try {
						yahooPrice = thetaUtil.getCurrentSecurityPriceFromYahoo(strategy.getSymbol(), client);
					} catch (ThetaExceptionExc ex4){
						insertIntoSecurityPrice(strategy.getSymbol(), underlyingSecurityPrice, "IB", false, allMovAvgs.loAvg, allMovAvgs.hiAvg, allMovAvgs.avgDailyRange, slopeMain, strategy.getLongOrShortCode().getValue());
						log.error(stratName + "Exception in getting Price from Yahoo (second time): ", ex3);
						return;
					}
				} 

				Slope slopeYahoo = new Slope(securityPriceDAO, now, yahooPrice, strategy, dbAccessMutex);

				try {
					insertIntoSecurityPrice(strategy.getSymbol(), yahooPrice, "YAHOO", true, allMovAvgs.loAvg, allMovAvgs.hiAvg, allMovAvgs.avgDailyRange, slopeYahoo, strategy.getLongOrShortCode().getValue());
					insertIntoSecurityPrice(strategy.getSymbol(), underlyingSecurityPrice, "IB", false, allMovAvgs.loAvg, allMovAvgs.hiAvg, allMovAvgs.avgDailyRange, slopeMain, strategy.getLongOrShortCode().getValue());
				} catch (NullPointerException npe) {
					log.error(stratName + "null pointer: ", npe);
				}
				if (yahooPrice == null) {
					log.error(stratName + "Yahoo price is null (probably BID and ASK are too far apart) - cannot continue ...");
					return;
				}
				underlyingSecurityPrice = yahooPrice;
				log.info(stratName + "SlopeMain = slopeYahoo = ");
				slopeMain = slopeYahoo;
			} else {
				insertIntoSecurityPrice(strategy.getSymbol(), underlyingSecurityPrice, "IB", true, allMovAvgs.loAvg, allMovAvgs.hiAvg, allMovAvgs.avgDailyRange, slopeMain, strategy.getLongOrShortCode().getValue());
			}


			log.info(stratName + "SlopeMain: " + slopeMain.getSlope1() + "," + slopeMain.getSlope2() + "," + slopeMain.getSlope3());
			Integer NUM_DAYS_TO_CHECK_XTREME = 2;
			Calendar checkXtremeFrom = Calendar.getInstance();
			checkXtremeFrom.add(Calendar.DATE, -NUM_DAYS_TO_CHECK_XTREME);
			if ( (position.getUpdatedDate().compareTo(checkXtremeFrom) < 0) && (strategyAccount.getStkInvestAmtPct() == 0) ) {
				log.info(stratName + "Position last updated more than " + NUM_DAYS_TO_CHECK_XTREME + " days ago! Ticker: " + strategy.getSymbol());
				Integer numDaysAgoXtreme = 14;
				Integer numDaysAgoCounterXtreme = 4;
				position.calcAndSetStkLocalXtremePrices(securityPriceDAO, strategy, numDaysAgoXtreme, numDaysAgoCounterXtreme, dbAccessMutex);
			}

			/*********************************************************************************************/
			/***** EXIT **********************************************************************************/
			/*********************************************************************************************/

			if (client.getCanExitFlag()) {
				if (strategy.isOption())
					runAndExecuteOptExitRules(position, strategy, underlyingSecurityPrice, VIXPrice, movingAverages);
				else if (strategy.isStock())
					runAndExecuteStkExitRules(position, strategy, underlyingSecurityPrice, movingAverages, slopeMain);
				else
					throw new ThetaExceptionExc(stratName + "Strategy instrument must be OPT or STK!. Actual Value: " + strategy.getInstrument());
			}

			/*********************************************************************************************/
			/***** ENTER *********************************************************************************/
			/*********************************************************************************************/
			Calendar today = Calendar.getInstance();
			boolean isEarningsReportDay = false;
			if (DateUtil.isSameDay(today, strategy.getEarningsReportNext()) ||
					DateUtil.isPreviousBusDay(today, strategy.getEarningsReportNext()) ){
				log.info(stratName + "Today is or yesterday was earnings Report Day - DO NOT ENTER!");
				log.info("********************************************");
				isEarningsReportDay = true;;
			}
			
			log.info(stratName + "About to getSpreadsToEnter and Enter if called for");
			if ( client.getCanEnterMap(position.getPositionId()) && client.getGlobalCanEnterFlag() && !isEarningsReportDay){
				Set<Spread> spreadsToEnter = getSpreadsToEnter(position, strategy, underlyingSecurityPrice, VIXPrice, movingAverages, strategyAccount.getStkInvestAmtPct(), client.getResp().getNetLiquidation(), slopeMain);
				if (spreadsToEnter == null) {
					log.info(stratName + "spreadsToEnter is null - not entering and continuing to end of loop.");
				} else {
					log.info(stratName + "About to enterSpreads");
					for(Spread spread : spreadsToEnter) {
						log.info(stratName + "Entering spreads.  Here is the spread info: " + spread.toString() );
						positionExecutor.verifySpreadVersion(spread, dbAccessMutex);
						synchronized(dbAccessMutex) {spreadDAO.synchronizedStore(spread);}
					}
					position.setUpdatedDate(Calendar.getInstance());
					synchronized(dbAccessMutex) {positionDAO.synchronizedStoreAndFlush(position);}
				}	
				// Loop through all spreads for this position and enter the appropriate ones
				enterSpreads(position, strategy, underlyingSecurityPrice);
			}

		} catch (Throwable th){
			log.info(" ");
			log.info(stratName + "===============THROWABLE CAUGHT IN LOOP   ===================================");
			log.info(stratName + " Investigate this and/or discontinue this stock");
			log.info(stratName + "=========================================================================================");
			log.info(" ");
			log.error(stratName + "Throwable caught in strategy: " + strategy.toString(), th);
			//synchronized(dbAccessMutex) {spreadDAO.synchronizedFlush();}
		}


	}

	
	
	/**
	 * insertIntoSecurityPrice
	 * @param theTicker
	 * @param thePrice
	 */
	protected void insertIntoSecurityPrice(String theTicker, Integer thePrice, String sourceOfQuote, boolean used, Integer ma1, Integer ma2, Integer maRange, Slope slope, String longOrShortFlag)
	throws ThetaExceptionExc {

		if ( (thePrice == null) || (thePrice == 0)){
			return;
		}
		
		SecurityPrice theSecurityPrice = new SecurityPrice();
		theSecurityPrice.setSecurityPriceId(ThetaConstants.INIT_ID);
		theSecurityPrice.setPrice(thePrice);
		theSecurityPrice.setTicker(theTicker);
		theSecurityPrice.setSource(sourceOfQuote);
		theSecurityPrice.setCreatedDate(Calendar.getInstance());
		String isUsed = (used) ? "Y" : "N";
		theSecurityPrice.setUsed(isUsed);
		theSecurityPrice.setMovAvg1(ma1);
		theSecurityPrice.setMovAvg2(ma2);
		theSecurityPrice.setMovAvgRange(maRange);
		theSecurityPrice.setLongOrShortFlag(longOrShortFlag);
		if (slope != null) {
			theSecurityPrice.setSlope1(slope.getSlope1());
			theSecurityPrice.setSlope2(slope.getSlope2());
			theSecurityPrice.setSlope3(slope.getSlope3());
		}
		
		synchronized(dbAccessMutex) {securityPriceDAO.synchronizedStoreAndFlush(theSecurityPrice);}
	}


	/************************************************************************************************************/
	/************************************************************************************************************/
	/************************************************************************************************************/
	/*                                     STOCK EXIT                                                           */
	/************************************************************************************************************/
	/************************************************************************************************************/
	/************************************************************************************************************/

	// Stock Exit Rules
	private void runAndExecuteStkExitRules(Position position, Strategy strategy, Integer underlyingSymbolPrice, MovingAverages movAvgs, Slope slope)
		throws ThetaExceptionExc {
		
		log.info(stratName + "Top of runAndExecuteStkExitRules");
		Set<Spread> spreads;
		synchronized(dbAccessMutex) {spreads = spreadDAO.findSpreadByPositionId(position.getPositionId());}

		if (!strategy.chkLongOrShort()){
			log.info(stratName + "Stock strategy.longOrShortFlag MUST BE LONG or SHORT!  Strategy: " + strategy.toString());
			throw new ThetaExceptionExc(stratName + "Stock Strategy MUST BE LONG or SHORT!");
		}
		

		for (Spread spread : spreads) {
			
			// Flush flushes TO the database
			//log.info("Top of runAndExecuteStkExitRules - before flush. " + strategy.getStrategyName() + " : " + spread.toString());
			//synchronized(dbAccessMutex) {spreadDAO.flush();}
			//log.info("Top of runAndExecuteStkExitRules - after flush. " + strategy.getStrategyName() + " : " + spread.toString());

			log.info("Top of runAndExecuteStkExitRules." + spread.getCreatedBy() + " :version: " + spread.getVersion() );

			boolean exitFlag = false;

			boolean isOpen = ( spread.getOpenOrClosed().equals(OpenOrClosedCode.OPEN.toString()) ||
					           spread.getOpenOrClosed().equals(OpenOrClosedCode.ADVISECLS.toString()) );
			if (!isOpen) continue;
		    
			
		    LongOrShortCode longOrShort = (strategy.isLong()) ? LongOrShortCode.LONG : LongOrShortCode.SHORT;
			Calendar now = Calendar.getInstance();
		    
			spread.setCurrentSecurityPrice(underlyingSymbolPrice);
			spread.setCurrentMoneymkrPrice(underlyingSymbolPrice);
			spread.setCurrentDate(now);
			spread.setCurrentVixPrice(0);
			spread.setUpdatedDate(now);
			spread.setCurrentDate(now);
			spread.setUpdatedBy("runAndExecuteStkExitRules - top");
			spread.setEarningsReportNext(strategy.getEarningsReportNext());
			spread.parseCoveredOptionsDesc();
			
			log.info(stratName + "runAndExecuteStkExitRules.  Spread: " + spread.toStringShort());
			MovingAverages.AllMovingAverages allMovAvgs = 
					movAvgs.getAllMovingAverages(position.getSymbol(), 
							                     strategy.getMovingAvg1(), 
							                     strategy.getMovingAvg2(), 
							                     strategy.getMovingAvgRange(), 
							                     underlyingSymbolPrice, 
							                     strategy.getMovingAvgGracePoints(),
							                     strategy.getMovingAvgTolerancePct());

			if (allMovAvgs == null) {
				log.info(stratName + "*************Moving average is more than 25% off - this could be an exception or just a price (up or down) breakout ***************** ");
				
				synchronized(dbAccessMutex) {
					spreadDAO.synchronizedStoreAndFlush(spread);
					positionDAO.synchronizedStoreAndFlush(position);
				}
				
				return;
			}

			/**
			 * Earnings Date - either After Market Close (AMC) today or Before Market Open (BMO) tomorrow
			 */
			Calendar threeOClockToday = Calendar.getInstance();
			threeOClockToday.set(Calendar.HOUR_OF_DAY, 15);
			threeOClockToday.set(Calendar.MINUTE, 0);
			if (DateUtil.isSameDay(now, strategy.getEarningsReportNext()) &&
					now.after(threeOClockToday)) {

				String longOrShortStr = strategy.isLong() ? "LONG" : "SHORT";
				spread.setExitReason(longOrShortStr + ". Earnings Date get out, " + strategy.getSymbol() + "," + now.getTime() + ", Price: " + underlyingSymbolPrice + 
						", xtremePrice: " + position.getStkLocalXtremePrice() + 
						", bpTimesRange: " + strategy.getStkTrailingStopBpTimesRange() +
						", longOrShort: ");
				log.info(stratName + "EXITING due to EARNINGS DATE " + longOrShort + " symbolPrice, spread " + underlyingSymbolPrice + ": " + spread.toStringNS());
				position.resetLoMidHi(underlyingSymbolPrice, allMovAvgs, stratName);

				exitFlag = true;
			}
			
			
			boolean isEndOfBearForStkExit = (underlyingSymbolPrice.compareTo(allMovAvgs.loAvgMinus) > 0);
			boolean isEndOfBullForStkExit = (underlyingSymbolPrice.compareTo(allMovAvgs.hiAvgPlus) < 0);
			
			
			if (strategy.getStkStrategyType() == null) {
				throw new ThetaExceptionExc(stratName + "strategy.getStkStrategyType is null!");
			}
				
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss");
			String dtNow = sdf.format(dt);

			Integer stopIfUnderHi = position.getStopOverEntryByRange(allMovAvgs.avgDailyRange, spread, LongOrShortCode.LONG, 14);
			Integer stopIfUnderLo = position.getStopOverEntryByRange(allMovAvgs.avgDailyRange, spread, LongOrShortCode.LONG, -15);
			Integer stopIfOverHi = position.getStopOverEntryByRange(allMovAvgs.avgDailyRange, spread, LongOrShortCode.SHORT, 14);
			Integer stopIfOverLo = position.getStopOverEntryByRange(allMovAvgs.avgDailyRange, spread, LongOrShortCode.SHORT, -15);
			boolean slopeIsSufficientWithinTimeOfSlope1UP = slope.isSufficientWithinTimeOfSlope1(strategy, UpOrDownCode.UP);
			boolean slopeIsSufficientWithinTimeOfSlope1DOWN = slope.isSufficientWithinTimeOfSlope1(strategy, UpOrDownCode.DOWN);
		
			
			
			
			if (strategy.getStkStrategyType().equalsIgnoreCase(ThetaConstants.MOVING_AVERAGES)
					|| strategy.getStkStrategyType().equalsIgnoreCase(ThetaConstants.SLOPES)) {

				if (strategy.isLong()){
					/*
					 * Confirmation - check that Slope is sufficient for exit 
					 */
					boolean slopeIsSufficient = slope.isSufficient(strategy, UpOrDownCode.DOWN);
					log.info("slopeIsSufficient: " + slopeIsSufficient);
										
					if (slopeIsSufficient && isEndOfBullForStkExit) {
						spread.setExitReason("LONG:- endOfbull-belowHighMovAvgPlus, " + strategy.getSymbol() + "," + dtNow + ", Price: " + underlyingSymbolPrice + 
								", hiMovAvg: " + allMovAvgs.hiAvg + 
								", loMovAvg: " + allMovAvgs.loAvg + 
								", hiMovAvgPlus: " + allMovAvgs.hiAvgPlus + 
								", avgDailyRange: " + allMovAvgs.avgDailyRange + 
								", gracePointsXAvgDailyRange: " + allMovAvgs.gracePointsXAvgDailyRange );
						log.info(stratName + "EXITING - in MOVINGAVERAGES - isLong() and EndOfBullForStkExit - symbolPrice, spread " + underlyingSymbolPrice + ": " + spread.toStringNS());
						exitFlag = true;
						position.setStkCanReenterAtXtreme(false);
						position.setStkLocalXtremePrice(allMovAvgs.hiAvgPlus);
						position.setXtremeUpdatedDate(now);
					}

					Integer trailingStop = position.getTrailingStopByRange(allMovAvgs.avgDailyRange, strategy, spread.getEnterMoneymkrPrice(),  LongOrShortCode.LONG);
					log.info(stratName + "STOCK LONG: symbol, underlyingSymbolPrice, trailingStop, xtremePrice, avgDailyRange, bpTimesRange, underlyingSymbolPrice: " 
							+ strategy.getSymbol() + ", " + underlyingSymbolPrice + ", " 
								+ trailingStop + ", " + position.getStkLocalXtremePrice() + ", " 
								+ allMovAvgs.avgDailyRange + ", " + strategy.getStkTrailingStopBpTimesRange() );

		
					// Trailing stop
					if (slopeIsSufficient && underlyingSymbolPrice.compareTo(trailingStop) <= 0 ) {
						String xtremeUpdatedDateStr = position.getXtremeUpdatedDateStr();
						spread.setExitReason("LONG: - Trailing Stop," + strategy.getSymbol() + "," + dtNow + ",  Price: " + underlyingSymbolPrice + 
								", trailingStop: " + trailingStop + 
								", xtremePrice: " + position.getStkLocalXtremePrice() + 
								", dateXtremePrice: " + xtremeUpdatedDateStr + 
								", avgDailyRange: " + allMovAvgs.avgDailyRange + 
								", bpTimesRange: " + strategy.getStkTrailingStopBpTimesRange() +
								", hiMovAvg: " + allMovAvgs.hiAvg + 
								", loMovAvg: " + allMovAvgs.loAvg + 
								", gracePointsXAvgDailyRange: " +  allMovAvgs.gracePointsXAvgDailyRange );
						log.info("EXITING - In MOVINGAVERAGES - isLong() - exiting at trailing stop.");
						position.setStkReEnterAtPrice(allMovAvgs.avgDailyRange, strategy.getStkReenterBpTimesRange(), LongOrShortCode.LONG);
						position.setStkCanReenterAtXtreme(true);
						exitFlag = true;
					}

					
					// Quick Exit if stock starts going the wrong way
					//boolean slopeIsSufficientWithinTimeOfSlope1 = slope.isSufficientWithinTimeOfSlope1(strategy, UpOrDownCode.DOWN);
					if ( 
							(spread.getEnterReason().contains("PriceAboveXTremePrice") || spread.getEnterReason().contains("PriceAboveReEnterAtPrice"))
							&&
							(
									( slopeIsSufficientWithinTimeOfSlope1DOWN && underlyingSymbolPrice.compareTo(stopIfUnderHi) <= 0) 
									||
									(underlyingSymbolPrice.compareTo(stopIfUnderLo) <= 0) 
							)
						){
						String xtremeUpdatedDateStr = position.getXtremeUpdatedDateStr();
						spread.setExitReason("LONG: - Quick Exit," + strategy.getSymbol() + "," + dtNow + ",  Price: " + underlyingSymbolPrice + 
								", stopIfUnderHi: " + stopIfUnderHi + 
								", stopIfUnderLo: " + stopIfUnderLo + 
								", xtremePrice: " + position.getStkLocalXtremePrice() + 
								", dateXtremePrice: " + xtremeUpdatedDateStr + 
								", avgDailyRange: " + allMovAvgs.avgDailyRange + 
								", bpTimesRange: " + strategy.getStkTrailingStopBpTimesRange() +
								", hiMovAvg: " + allMovAvgs.hiAvg + 
								", loMovAvg: " + allMovAvgs.loAvg + 
								", gracePointsXAvgDailyRange: " +  allMovAvgs.gracePointsXAvgDailyRange );
						log.info(stratName + "EXITING - In MOVINGAVERAGES - isLong() - exiting quickly - price is turning against the trade.");
						position.setStkReEnterAtPrice(stopIfUnderHi);
						position.setStkCanReenterAtXtreme(true);
						exitFlag = true;
					}
					
					
					/*
					 * We are now in the position, and the stock price goes over the previous local extreme price.  
					 * Reset the local extreme price.
					 */
					if ( underlyingSymbolPrice.compareTo(position.getStkLocalXtremePrice()) > 0 ) {
						log.info(stratName + "MovingAverages.long.underlyingSymbolPrices < localXtreme. ticker, price, now: " + strategy.getSymbol() + ", " + underlyingSymbolPrice + ", " + now.getTime());
						position.setStkLocalXtremePrice(underlyingSymbolPrice);
						position.setXtremeUpdatedDate(now);
					}
					
					
				} else if (strategy.isShort()){
					
					boolean slopeIsSufficient = slope.isSufficient(strategy, UpOrDownCode.UP);
					// **** TODO: Exit day of earnings if appropriate!!!

					if (slopeIsSufficient && isEndOfBearForStkExit) {
						spread.setExitReason("SHORT: - endOfbull-aboveLoMovAvgMinus " + strategy.getSymbol() + "," + dtNow + ", Price: " + underlyingSymbolPrice + 
		        			                 ", hiMovAvg: " + allMovAvgs.hiAvg + 
                                             ", loMovAvg: " + allMovAvgs.loAvg + 
					                         ", loMovAvgMinus: " + allMovAvgs.loAvgMinus +
					                         ", avgDailyRange: " + allMovAvgs.avgDailyRange + 
								             ", gracePointsXAvgDailyRange: " + allMovAvgs.gracePointsXAvgDailyRange );
						log.info(stratName + "EXITING - in MOVINGAVERAGES isShort() and endOfBearForStkExit - symbolPrice, spread " + underlyingSymbolPrice + ": " + spread.toStringNS());
						exitFlag = true;
						position.setStkCanReenterAtXtreme(false);
						position.setStkLocalXtremePrice(allMovAvgs.loAvgMinus);
						position.setXtremeUpdatedDate(now);
					}
					Integer trailingStop = position.getTrailingStopByRange(allMovAvgs.avgDailyRange, strategy, spread.getEnterMoneymkrPrice(), LongOrShortCode.SHORT);
					log.info(stratName + "STOCK SHORT: symbol, underlyingSymbolPrice, trailingStop, xtremePrice, avgDailyRange, bpTimesRange: " 
							+ strategy.getSymbol() + ", " + underlyingSymbolPrice + ", " 
								+ trailingStop + ", " + position.getStkLocalXtremePrice() + ", " 
								+ allMovAvgs.avgDailyRange + ", " + strategy.getStkTrailingStopBpTimesRange() );

					if (slopeIsSufficient && underlyingSymbolPrice.compareTo(trailingStop) >= 0 ) {
						String xtremeUpdatedDateStr = position.getXtremeUpdatedDateStr();
						spread.setExitReason("SHORT: - Trailing Stop. " + strategy.getSymbol() + "," + dtNow + ", Price: " + underlyingSymbolPrice + 
		                         ", trailingStop: " + trailingStop + 
					             ", xtremePrice: " + position.getStkLocalXtremePrice() + 
								 ", dateXtremePrice: " + xtremeUpdatedDateStr + 
		                         ", avgDailyRange: " + allMovAvgs.avgDailyRange + 
					             ", bpTimesRange: " + strategy.getStkTrailingStopBpTimesRange() +
								 ", hiMovAvg: " + allMovAvgs.hiAvg + 
								 ", loMovAvg: " + allMovAvgs.loAvg + 
					             ", gracePointsXAvgDailyRange: " + allMovAvgs.gracePointsXAvgDailyRange );
						log.info(stratName + "EXITING - In MOVINGAVERAGES - isShort() - exiting at trailing stop.");
						position.setStkReEnterAtPrice(allMovAvgs.avgDailyRange, strategy.getStkReenterBpTimesRange(), LongOrShortCode.SHORT);
						position.setStkCanReenterAtXtreme(true);
						exitFlag = true;
					}
					

					// Quick exit if stock starts going the wrong way
					//boolean slopeIsSufficientWithinTimeOfSlope1 = slope.isSufficientWithinTimeOfSlope1(strategy, UpOrDownCode.UP);
					if ( 
							(spread.getEnterReason().contains("PriceBelowXTremePrice") || spread.getEnterReason().contains("PriceBelowReEnterAtPrice"))
							&&
							(
									( slopeIsSufficientWithinTimeOfSlope1UP && underlyingSymbolPrice.compareTo(stopIfOverHi) >= 0) 
									||
									(underlyingSymbolPrice.compareTo(stopIfOverLo) >= 0) 
							)
						){
						String xtremeUpdatedDateStr = position.getXtremeUpdatedDateStr();
						spread.setExitReason("SHORT: - Quick Exit," + strategy.getSymbol() + "," + dtNow + ",  Price: " + underlyingSymbolPrice + 
								", stopIfOverHi: " + stopIfOverHi + 
								", stopIfOverLo: " + stopIfOverLo + 
								", xtremePrice: " + position.getStkLocalXtremePrice() + 
								", dateXtremePrice: " + xtremeUpdatedDateStr + 
								", avgDailyRange: " + allMovAvgs.avgDailyRange + 
								", bpTimesRange: " + strategy.getStkTrailingStopBpTimesRange() +
								", hiMovAvg: " + allMovAvgs.hiAvg + 
								", loMovAvg: " + allMovAvgs.loAvg + 
								", gracePointsXAvgDailyRange: " +  allMovAvgs.gracePointsXAvgDailyRange );
						log.info(stratName + "EXITING - In MOVINGAVERAGES - isShort() - exiting quickly - price is turning against the trade.");
						position.setStkReEnterAtPrice(stopIfOverHi);
						position.setStkCanReenterAtXtreme(true);
						exitFlag = true;
					}

					/*
					 * We are now in the position, and the stock price goes under the previous local extreme price.  
					 * Reset the local extreme price.
					 */
					if ( underlyingSymbolPrice.compareTo(position.getStkLocalXtremePrice()) < 0 ) {
						log.info(stratName + "MovingAverages.short.underlyingSymbolPrices < localXtreme. ticker, price, now: " + strategy.getSymbol() + ", " + underlyingSymbolPrice + ", " + now.getTime());
						position.setStkLocalXtremePrice(underlyingSymbolPrice);
						position.setXtremeUpdatedDate(now);
					}
				} 

				
			} else if (strategy.getStkStrategyType().equalsIgnoreCase(ThetaConstants.MOVING_AVERAGES_2) ) {
				
				/*
				 * 1. Set LoMidHi
				 */
				LoMidHiCode loMidHi = position.setLoMidHi(underlyingSymbolPrice, allMovAvgs, stratName);
				
				UpOrDownCode upOrDownCode = ( strategy.isLong() ) ? UpOrDownCode.DOWN : UpOrDownCode.UP;
				
				/*
				 * 2. Determine if it is time to get in
				 *    If so, reset the LoMidHi flags in Position
				 */
				boolean change = Decider.changePosition (
						upOrDownCode, 
						position.isStkHasBeenLo(), 
						position.isStkHasBeenMid(),
						position.isStkHasBeenHi(),
						loMidHi
						);
				log.info(stratName + "Decider result: " + change);
				/*
				 * Get out if Decider says so:
				 */
				Integer movAvgNum = 1;  // Fast Moving Average
				Integer targetSlope = 3;  // Longest increment (i.e. 1 day)

				if (
					 (strategy.isLong() && change && slope.isSufficient(strategy, upOrDownCode))
				    ||
				    (strategy.isShort() && getOutRulesForShort(change, underlyingSymbolPrice, strategy, upOrDownCode, slope, allMovAvgs))
				   ) {
					
					log.info(stratName + "Advise to get out of position - MovingAverages2");
					spread.setExitReason("MovingAverages2 - ADVISECLS: " + upOrDownCode.getValue() + 
							", Symbol: " + strategy.getSymbol() + 
							" , " + dtNow +
							", AdvicePrice: " + underlyingSymbolPrice + 
							", LO:MID:HI: " + position.isStkHasBeenLo() + ":" + position.isStkHasBeenMid() + ":" + position.isStkHasBeenHi() + 
							", LOMIDHI Now: " + loMidHi.getValue() +
							", TargetSlope: " + targetSlope);
					
					
					spread.setOpenOrClosed(OpenOrClosedCode.ADVISECLS.toString());
		
					// spread.getExitApproval is set to "HOLD" when spread is created ...
					
				} // if 

				// Checker Step - Get out when APPROVED by user
				if (exitOnUserApproval(spread, underlyingSymbolPrice, upOrDownCode)) {
				
					spread.setExitReason(spread.getExitReason().trim() + "MovingAverages2 - CLOSE: " +
							" , " + dtNow +
							", ClosePrice: " + underlyingSymbolPrice + 
							", MovingAverages Lo,Hi: " + allMovAvgs.loAvg + ":" + allMovAvgs.hiAvg +
							", MovAvgNum: " + movAvgNum +
							", TargetSlope: " + targetSlope);
					
					// And reset position once out...
					position.resetLoMidHi(underlyingSymbolPrice, allMovAvgs, stratName);
					exitFlag = true;
				}

				
				
			} else 	if (strategy.getStkStrategyType().equalsIgnoreCase(ThetaConstants.MANUAL)) {
				if (strategy.isLong()){
					Integer trailingStop = position.getTrailingStopByRange(allMovAvgs.avgDailyRange, strategy.getStkTrailingStopBpTimesRange(), LongOrShortCode.LONG);
					log.info(stratName + "STOCK LONG (MANUAL): trailingStop, xtremePrice, percentTrailingStop, underlyingSymbolPrice: " + trailingStop + ", " + position.getStkLocalXtremePrice() + 
							", " + strategy.getPctTrailingStop() + ", " + underlyingSymbolPrice );
					if (underlyingSymbolPrice.compareTo(trailingStop) <= 0 ) {
						log.info("In MANUAL - isLong() - exiting at trailing stop.");
						exitFlag = true;
					}
				} else if (strategy.isShort()){
					Integer trailingStop = position.getTrailingStopByRange(allMovAvgs.avgDailyRange, strategy.getStkTrailingStopBpTimesRange(), LongOrShortCode.SHORT);
					log.info(stratName + "STOCK SHORT (MANUAL): trailingStop, xtremePrice, percentTrailingStop, underlyingSymbolPrice: " + trailingStop + ", " + position.getStkLocalXtremePrice() + 
							", " + strategy.getPctTrailingStop() + ", " + underlyingSymbolPrice );

					if (underlyingSymbolPrice.compareTo(trailingStop) >= 0 ) {
						log.info(stratName + "In MANUAL - isShort() - exiting at trailing stop.");
						exitFlag = true;
					}
				} 
			} else throw new ThetaExceptionExc("Stocks must have a pre-defined strategy type! (Strategy STK_STRATEGY_TYPE).  Current:" + strategy.getStkStrategyType());
			

			if (exitFlag) {
				
				position.setStkCounterXtremePrice(underlyingSymbolPrice);
				position.setXtremeUpdatedDate(now);
				
				spread.parseCoveredOptionsDesc();
				positionExecutor.exitSingleSpread(spread, position, strategy, MovingSpeedCode.SLOW, "Exit Stock Position", client, dbAccessMutex);
				
				//if (spread.getCoveredOption() != null) {
				//}
				
			}
			synchronized(dbAccessMutex) {
				spreadDAO.synchronizedStore(spread);
				positionDAO.synchronizedStoreAndFlush(position);
			}
		}		
	}


	private boolean exitCoveredOption(Spread spread) {
		return false;
	}
	
	private boolean exitOnUserApproval(Spread spread, Integer underlyingSymbolPrice, UpOrDownCode upOrDownCode) throws ThetaExceptionExc {

		//Note - this is for Exiting a spread:
		//	UpOrDownCode upOrDownCode = ( strategy.isLong() ) ? UpOrDownCode.DOWN : UpOrDownCode.UP;

		
		// Cases to get out:
		// Exit Approval:
		String openOrClosed = spread.getOpenOrClosed().trim().toUpperCase();
		String exitApproval = spread.getExitApproval().trim().toUpperCase();
		
		String OPEN = OpenOrClosedCode.OPEN.toString();
		String ADVISECLS = OpenOrClosedCode.ADVISECLS.toString();

		String APPROVE = ApprovalCode.APPROVE.toString();
		String CLOSE = ApprovalCode.CLOSE.toString();
		String KILL = ApprovalCode.KILL.toString();
		String HOLD = ApprovalCode.HOLD.toString();  
		
		if ( 
				(exitApproval.equals(APPROVE) && openOrClosed.equals(ADVISECLS))  ||
				exitApproval.equals(CLOSE) ||
				exitApproval.equals(KILL) ||
				( exitApproval.equals(HOLD) && !spread.isWithinLimits(upOrDownCode, EnterOrExitCode.EXIT, underlyingSymbolPrice) )
			){
			return true;
		} else {
			return false;
		}
	}

		
		
	private boolean getOutRulesForShort(boolean change, Integer underlyingSymbolPrice, Strategy strategy, UpOrDownCode upOrDownCode, Slope slope, MovingAverages.AllMovingAverages allMovAvgs) {	
	
		if (change) return true;
		
		Double adr15 = allMovAvgs.avgDailyRange * 1.5;
		log.info(stratName + "In extraGetOutForShort. upOrDown, price, loAvg, avgDailyRange, adr15: " + upOrDownCode + ", " + underlyingSymbolPrice + ", " + allMovAvgs.loAvg + ", " + allMovAvgs.avgDailyRange + ", " + adr15);

		if (
		     (underlyingSymbolPrice.compareTo(allMovAvgs.loAvg) <= 0) 
		     && 
		     (underlyingSymbolPrice.compareTo(allMovAvgs.loAvg - adr15.intValue()) >= 0 )
		     &&
		     slope.isSufficientForExit(strategy, upOrDownCode)
		    ) {
			return true;
		}
		
		if (slope.isSufficient(strategy, upOrDownCode.UP)) {
			return true;
		}
		
		return false;
	}
	
	
	/************************************************************************************************************/
	/************************************************************************************************************/
	/************************************************************************************************************/
	/*                                     STOCK ENTRANCE                                                       */
	/************************************************************************************************************/
	/************************************************************************************************************/
	/************************************************************************************************************/
	
	/**
	 * From the current state of the spreads, get the spreads that
	 * must be purchased at the current time.
	 * 
	 * @return Set<Spread>
	 */

	/**
	 * 
	 * This gets the Set of Spreads to enter for a given (internal) Position.<br>
	 * Remember: An (internal) Position uniquely defines the following:<br>
	 *		A strategy<br>
	 * 		An expiry date<br>
	 * 		A right (PUT or CALL)<br>
	 * 		The number of Spreads to be in for this Position<br>
	 *
	 * #3C. ENTRY/REENTRY RULES:
	 *
	 *  Initial Entry Rules:
	 *  --------------------
	 *	 POSITION:NUM_WINS or POSITION:NUM_LOSSES is more than “0”.
	 *	 Keep entering/re-entering spreads rules while NUM_OPEN_SPREADS is less than GOAL_NUM_SPREADS
	 *	 VIX Check (see above)
	 *
	 *		Obtain the TOTAL_RISK of each SPREAD from the STRATEGY table
	 *		ENTER the spread AT between PERCENT_ENTER_LOW (i.e. 15%) and PERCENT_ENTER_HIGH (i.e. 25%) of “total loss”.  
	 *			This is done by starting with the spreads closest to the strike and moving away until from this strike price 
	 *          until the PERCENT_ENTER (the percent of TOTAL_RISK) is within this range.  That is – if there is more than 
	 *          one spread between these ranges – pick the one closest to the PERCENT_ENTER_HIGH without going over it.
	 *		ONLY ENTER/REENTER contracts that expire between DAYS_ENTER_LOW (i.e. 28 days) and DAYS_ENTER_HIGH (i.e. 70 days).
	 *		ENTRY TIME: WAIT STRAGETY: MINUTES_DELAY_FROM_TRADING_START – this is the number of minutes form start of trading 
	 *          to do an initial entry into the spread.
	 *  
	 *	Re-Entry Rules:
	 *  ---------------
	 *	 Can re-enter if POSITION:NUM_LOSSES is more than “0” and STRATEGY:SET_REENTER_ON_LOSS is true
	 *	 Can re-enter if POSITION:NUM_WINS is more than “0” and STRATEGY:SET_REENTER_ON_WIN is true
	 *	 CALL SPREAD: Re-enter ONLY if Security price is above POSITION:REENTRY_SEC_PRICE_CALL_ABOVE 
	 *	 PUT SPREAD: Re-enter ONLY if Security price is below POSITION:REENTRY_SEC_PRICE_PUT_BELOW 
	 *
	 * 
	 * @param position
	 * @param strategy
	 * @param VIXPrice
	 * @param underlyingSymbolPrice
	 * @return Set<Spread>
	 *
	 */
	private Set<Spread> getSpreadsToEnter(Position position, Strategy strategy, Integer underlyingSymbolPrice, Integer VIXPrice, MovingAverages movAvgs, Integer stkInvestAmtPct, String netLiquidation, Slope slope)
	throws ThetaExceptionExc {

		Set<Spread> spreadsToEnter = new HashSet<Spread>();

		/**
		 *	Re-Entry Rules:
		 *  ---------------
		 *	 Can re-enter if POSITION:NUM_LOSSES is more than “0” and STRATEGY:SET_REENTER_ON_LOSS is true
		 *	 Can re-enter if POSITION:NUM_WINS is more than “0” and STRATEGY:SET_REENTER_ON_WIN is true
		 */
		
		boolean hasWins = !(ThetaUtil.isNullOrZero(position.getNumWins()));
		boolean canReenterOnWin = ThetaUtil.trueFalseToBoolean(strategy.getSetReenterOnWin());
		boolean hasLosses = !(ThetaUtil.isNullOrZero(position.getNumLosses()));
		boolean canReenterOnLoss = ThetaUtil.trueFalseToBoolean(strategy.getSetReenterOnLoss());
		
		if ((hasWins) && (!canReenterOnWin)) {
			log.info(stratName + "This position has wins - BUT the strategy is set to not reenter when there are already wins.");
			log.info(stratName + "********************************************");
			return null;
		}
		
		log.info(stratName + "Calling getAllMovingAverages - with: " + position.getSymbol() + ", " + strategy.getMovingAvg1() + ", " + strategy.getMovingAvg2() + ", " + underlyingSymbolPrice + ", " + strategy.getMovingAvgGracePoints());
		MovingAverages.AllMovingAverages allMovAvgs = 
				movAvgs.getAllMovingAverages(position.getSymbol(), 
						                     strategy.getMovingAvg1(), 
						                     strategy.getMovingAvg2(), 
						                     strategy.getMovingAvgRange(), 
						                     underlyingSymbolPrice, 
						                     strategy.getMovingAvgGracePoints(),
						                     strategy.getMovingAvgTolerancePct());

		if (allMovAvgs.movingAverageOutOfRange) {
			log.info(stratName + "*************Moving average is more than 15% off - this could be an exception or just a price (up or down) breakout.");
			return null;
		}
		
		boolean isBullForOptEnter = (underlyingSymbolPrice.compareTo(allMovAvgs.loAvgPlus) > 0);
		boolean isBearForOptEnter = (underlyingSymbolPrice.compareTo(allMovAvgs.hiAvgMinus) < 0);

		
		if(position.isCall() && isBullForOptEnter) {
			log.info(stratName + "Call spreads cannot be entered into in a bull market.");
			log.info("********************************************");
			return null;
		}

		if(position.isPut() && isBearForOptEnter) {
			log.info(stratName + "Put spreads cannot be entered into in a bear market.");
			log.info("********************************************");
 			return null;
		}

		/*
		 * Moved to top of ENTER 
		 */
		//Calendar today = Calendar.getInstance();
		//if (DateUtil.isSameDay(today, strategy.getEarningsReportNext()) ||
		//		DateUtil.isPreviousBusDay(today, strategy.getEarningsReportNext()) ){
		//	log.info(stratName + "Today is or yesterday was earnings Report Day - DO NOT ENTER!");
		//	log.info("********************************************");
 		//	return null;
		//}
		
		/**
		 * Get the number of spreads that SHOULD be open or pending from 
		 * the POSITION record.
		 * Then compare this with the actual.  
		 * 
		 * If it's the same - we're good.  Do nothing.
		 * If less - add until the same.
		 * If more - do nothing - the actuals will attrit down eventually.
		 * 
		 */
		Set<Spread> openOrPendSpreads = getOpenOrPendSpreads(position);
		int actualNumSpreadsForPosition;
		if (null == openOrPendSpreads) {actualNumSpreadsForPosition = 0;}
		else {actualNumSpreadsForPosition = openOrPendSpreads.size();}

		position.setNumOpenSpreads(actualNumSpreadsForPosition);
		//int actualNumSpreadsForPosition = position.getNumOpenSpreads();
		int goalNumSpreadsForPosition = position.getGoalNumSpreads();

		int numSpreadsToEnter = goalNumSpreadsForPosition - actualNumSpreadsForPosition;

		log.info(stratName + "Here is ID:numSpreads:goal:actual - " + position.getPositionId() + ":" + numSpreadsToEnter + " : " 
				+ goalNumSpreadsForPosition + " : " + actualNumSpreadsForPosition);
				
		
			for (int i=0; i < numSpreadsToEnter; i++){
				/** 
				 * IF there are wins or losses, check to see if we can re-enter
				 *	   CALL SPREAD: Re-enter ONLY if Security price is above POSITION:REENTRY_SEC_PRICE_CALL_ABOVE 
				 *	   PUT SPREAD: Re-enter ONLY if Security price is below POSITION:REENTRY_SEC_PRICE_PUT_BELOW 
				 */
				if (hasWins || hasLosses){
					/**
					 * CALL:
					 * The Underlying Symbol Price IS NOT NULL 
					 * AND
					 * The Underlying Symbol Price is BELOW/NOT ABOVE the reentry Security Price - 
					 * RETURN NULL
					 * 
					 * (NOTE: IF the underlying symbol price is NULL or 0 - no restrictions - reenter as necessary)
					 */
					
					if ((hasLosses) && (!canReenterOnLoss)) {
						if (position.isCall()) {
							Integer reentrySecPriceAbove = position.getReentrySecPriceCallAbove();
							if ( (!ThetaUtil.isNullOrZero(reentrySecPriceAbove)  && 
									(underlyingSymbolPrice < reentrySecPriceAbove))){
								log.info(stratName + "For CALL: position.getReentrySecPriceCallAbove is blocking entry.  Value, underlyingSymbolPrice: " + reentrySecPriceAbove + ", " + underlyingSymbolPrice);
								return null;
							}
						} 
						/**
						 * PUT:
						 * The Underlying Symbol Price IS NOT NULL 
						 * AND
						 * The Underlying Symbol Price is ABOVE/NOT BELOW the reentry Security Price - 
						 * RETURN NULL
						 */
						if (position.isPut()) {
							Integer reentrySecPriceBelow = position.getReentrySecPricePutBelow();
							if ((!ThetaUtil.isNullOrZero(reentrySecPriceBelow)  && 
									(underlyingSymbolPrice > reentrySecPriceBelow))){
								log.info(stratName + "For PUT: position.getReentrySecPricePutBelow is blocking entry.  Value, underlyingSymbolPrice: " + reentrySecPriceBelow + ", " + underlyingSymbolPrice);
								return null;
							}
						} // if (position.isPut()) 
					}

				} // if (hasWins || hasLosses)
				
				// Create a new Spread
				Spread spread = new Spread();
				spread.init(strategy);			
				spread.setPositionId(position.getPositionId());
				spread.setCurrentSecurityPrice(underlyingSymbolPrice);
				log.info("Here is the INITIAL spread to add: " + spread.toStringNS());

				try {
					Spread returnSpread = null;
					if (position.isOption()) {
						returnSpread = insertOptionEnterPricesIntoSpread(spread, position, strategy);
					}
						
					/**
					 * Return Null if no spread to enter.
					 * ALSO: Set next enterAt price
					 * RULES (Example for LONG positions - short is opposite):
					 * - Only enter one stock spread at a time
					 * - Upon entry of a stock spread, set StkEnterAtPrice 
					 * - As price goes up, continuously increase StkEnterAtPrice
					 * - As price goes down, do not change StkEnterAtPrice
					 * - Upon exit of a stock spread, set StkEnterAtPrice 
					 */
					if (position.isStock()) {
						returnSpread = insertStockEnterPricesIntoSpread(spread, position, strategy, allMovAvgs, stkInvestAmtPct, netLiquidation, slope);
					}

					if (returnSpread != null) {
						/*
						 *  Adding the spread!
						 */
						log.info(stratName + "insertEnterPricesIntoSpread was successful.");
						boolean didItAdd = spreadsToEnter.add(returnSpread);
						log.info(stratName + "Entering spread into array for purchase.  DidItAdd? " + didItAdd);
						log.info(stratName + "IN getSpreadsToEnter. *** Here is the size of spreadsToEnter: " + spreadsToEnter.size());
					} else {
						log.info("NOT entering spread into array for purchase - see previous notes for details");
					}
				} catch (Exception e){
					log.info(stratName + "CAUGHT EXCEPTION in getSpreadsToEnter - entering prices. " + e.toString());
					throw new ThetaExceptionExc(e);
				}

				// Stocks only enter once per iteration!
				if (position.isStock() && i==0)
					break;
				
			} // for (int i=0; i < numSpreadsToEnter; i++){

		log.info(stratName + "IN getSpreadsToEnter. Here is numSpreadsToEnter: " + spreadsToEnter.size());

		// Get all relevant positions
		return spreadsToEnter;
	}


	/**
	 * insertStockEnterPricesIntoSpread
	 * 
	 * Returns the correct Stock Enter Price 
	 * 
	 * @param spread
	 * @param position
	 * @param strategy
	 * @return Spread
	 */
	private Spread insertStockEnterPricesIntoSpread(Spread spread, Position position, Strategy strategy, MovingAverages.AllMovingAverages allMovAvgs, Integer stkInvestAmtPct, String netLiquidation, Slope slope)
			throws ThetaExceptionExc {

		Integer currentSecurityPrice = spread.getCurrentSecurityPrice();

		log.info(stratName + "Here is stk spread: " + spread.getSpreadId() );
		log.info(stratName + "currentSecurityPrice: " + currentSecurityPrice);

		spread.setEnterCommission(strategy.getComissionPerTrade());
		spread.setExitCommission(strategy.getComissionPerTrade());
		
		spread.setCurrentMoneymkrPrice(currentSecurityPrice);
		spread.setCurrentDate(Calendar.getInstance());

		log.info(stratName + "here is netLiquidation : " + netLiquidation);
		log.info(stratName + "here is stkInvestAmtPct: " + stkInvestAmtPct);
		Float netLiquidationF = new Float(netLiquidation);
		Float stkInvestAmtDollarF = (netLiquidationF * stkInvestAmtPct) / 100; 
		log.info(stratName + "Here is stkInvestAmtDollarF: " + stkInvestAmtDollarF);
		
		Float currentSecurityPriceF = new Float(currentSecurityPrice);
		
		Float numSharesF = ( (stkInvestAmtDollarF / currentSecurityPriceF) * 100);
		log.info(stratName + "Here is numSharesF: " + numSharesF);

		Integer numShares = numSharesF.intValue();
		
		log.info(stratName + "from strategyAccount.  stkInvestAmtPct, currentSecurityPrice, numshares: " + stkInvestAmtPct + ", " + currentSecurityPrice + ", " + numShares);
		spread.setStkNumShares(numShares);
		
		if (strategy.isStock()) {
			if (stkInvestAmtPct == null || stkInvestAmtPct <= 0) {
				return null;
			}
		}
		
		/**
		 * New Position?
		 */
		if (position.getStkEnterAtPrice() == 0) {
			position.setStkEnterAtPrice(currentSecurityPrice);
			position.setUpdatedBy("insertStockEnterPricesIntoSpread-newPosition");
		}
		
		
		if (strategy.getStkStrategyType() == null) {
			throw new ThetaExceptionExc("strategy.getStkStrategyType is null!");
		}

		if (strategy.chkLongOrShort() == false) {
			throw new ThetaExceptionExc("Strategy MUST BE LONG or SHORT!");
		}
		
		if (currentSecurityPrice == null 
				|| allMovAvgs.hiAvgPlus == null 
				|| allMovAvgs.loAvgMinus == null ) {
			log.error(stratName + "*** Exception - one of the following is null!  currentSecurityPrice, allMovAvgs.hiAvgPlus, allMovAvgs.loAvgMinus ********** ");
		}

		Calendar now = Calendar.getInstance();
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss");
		String dtNow = sdf.format(dt);
		UpOrDownCode upOrDownCode = null;
		LongOrShortCode longOrShort = null;
		if (strategy.isLong()) {
			upOrDownCode = UpOrDownCode.UP;
			longOrShort = LongOrShortCode.LONG;
		} else if (strategy.isShort()) {
			upOrDownCode = UpOrDownCode.DOWN;
			longOrShort = LongOrShortCode.SHORT;
		}

		/*
		 * 1. Set LoMidHi
		 */
		LoMidHiCode loMidHi = position.setLoMidHi(currentSecurityPrice, allMovAvgs, stratName);

		/*
		 * Confirmation - check that Slope for time periods are over target slopes 
		 */
		if (!slope.isSufficient(strategy, upOrDownCode)) {
			return null;
		}

		if (strategy.getStkStrategyType().equalsIgnoreCase(ThetaConstants.MOVING_AVERAGES)) {

			if (strategy.isLong()) {
				/*
				 * *******************************************
				 * LONG
				 * *******************************************
				 */
				/* 
				 * Case #1: currentSecurityPrice above allMovAvg.loAvg but below XTreme Price (and canReenterAtXtreme is true)
				 *    canReenterAtXtreme = true (same)
				 *    XtremePrice = XtremePrice (same)
				 *    Do not enter position
				 *    nothing changes ...
				 */

				/* Case #2: currentSecurityPrice is above XTremePrice (and canReenterAtXtreme is true) plus gracePointsXAvgDailyRange
				 *    canReenterAtXtreme = false
				 *    XtremePrice = currentSecurityPrice
				 *    Enter spread immediately
				 *    NOTE: This is likely redundant - if canReenterAtExtreme is true, then we can get in lower at stkReEnterAtPrice
				 */
				if ( (currentSecurityPrice.compareTo(position.getStkLocalXtremePrice() + allMovAvgs.gracePointsXAvgDailyRange) > 0) && position.isStkCanReenterAtXtreme() ) {
					log.info(stratName + "Enter LONG: Case #2: currentSecurityPrice is above XTremePrice (and canReenterAtXtreme is true)" + spread.toStringShort());
					spread.setEnterReason("LONG: PriceAboveXTremePrice, " + strategy.getSymbol() + "," + dtNow + ", canReenterAtXtreme: TRUE, Price: " + currentSecurityPrice + 
	                                      ", LIMIT_ORDER_KICKER: " + ThetaConstants.LIMIT_ORDER_KICKER + 
	                                      ", MovAvgLengths: " + strategy.getMovingAvg1() + " : " + strategy.getMovingAvg2() + 
	                                      ", averageDailyRange: " + allMovAvgs.avgDailyRange +
							              ", XtremePrice: " + position.getStkLocalXtremePrice() +
							              ", dateXtremePrice: " + position.getXtremeUpdatedDateStr() + 
							              ", gracePointsXAvgDailyRange: " +  allMovAvgs.gracePointsXAvgDailyRange );
					position.setStkCanReenterAtXtreme(false);
					position.setStkLocalXtremePrice(currentSecurityPrice);
					position.setXtremeUpdatedDate(now);
					spread.setEnterMoneymkrPrice(currentSecurityPrice + ThetaConstants.LIMIT_ORDER_KICKER);
					spread.setUpdatedBy("insertEnterPrices - 2. MOVINGAVERAGES");
					spread.setUpdatedDate(now);
					return spread;
				}

				/* Case #2A: currentSecurityPrice is above reEnterAtPrice 
				 *           and this is above allMovAvg.loAvg   
				 *           (and canReenterAtXtreme is true)
				 *    canReenterAtXtreme = false
				 *    XtremePrice = currentSecurityPrice
				 *    Enter spread immediately
				 */
				Integer reEnterPrice = position.getStkReEnterAtPrice();
				if (  reEnterPrice != null && reEnterPrice.compareTo(0) > 0 
					  && currentSecurityPrice.compareTo(reEnterPrice) > 0
					  && reEnterPrice.compareTo(allMovAvgs.loAvg) > 0  
					  && position.isStkCanReenterAtXtreme() ) {
					log.info(stratName + "Enter LONG: Case #2A: currentSecurityPrice is above position.reEnterPrice (and canReenterAtXtreme is true)" + spread.toStringShort());
					spread.setEnterReason("LONG: PriceAboveReEnterAtPrice, " + strategy.getSymbol() + "," + dtNow + ", Price: " + currentSecurityPrice + 
										  ", MovAvgLengths: " + strategy.getMovingAvg1() + " : " + strategy.getMovingAvg2() + 
  	                                      ", CanReenterAtXtreme: " + position.isStkCanReenterAtXtreme() + 
							              ", XtremePrice: " + position.getStkLocalXtremePrice() +
							              ", dateXtremePrice: " + position.getXtremeUpdatedDateStr() + 
				                          ", LIMIT_ORDER_KICKER: " + ThetaConstants.LIMIT_ORDER_KICKER + 
				                          ", reEnterPrice: " + reEnterPrice + 
				                          ", averageDailyRange: " + allMovAvgs.avgDailyRange +
				                          ", reenter under xtreme (adr x): " + strategy.getStkReenterBpTimesRange() +
				                          ", loMovAvg: " + allMovAvgs.loAvg +
				                          ", hiMovAvg: " + allMovAvgs.hiAvg +
          		                          ", gracePointsXAvgDailyRange: " + allMovAvgs.gracePointsXAvgDailyRange );
					position.setStkCanReenterAtXtreme(false);
					position.setStkLocalXtremePrice(currentSecurityPrice);
					position.setXtremeUpdatedDate(now);
					position.setStkReEnterAtPrice(ThetaConstants.ZERO_INT);
					spread.setEnterMoneymkrPrice(currentSecurityPrice + ThetaConstants.LIMIT_ORDER_KICKER);
					spread.setUpdatedBy("insertEnterPrices - Case 2A LONG ");
					spread.setUpdatedDate(now);
					return spread;
				}
				
				
				/* 
				 * Case #3: currentSecurityPrice is now below allMovAvgs.hiAvgPlus
				 *    canReenterAtXtreme = false
				 *    XtremePrice = allMovAvgs.hiAvgPlus
				 *    Do not enter position - Reenter at xtreme is dead at this point
				 */
				else if (currentSecurityPrice.compareTo(allMovAvgs.loAvg) < 0) {
					log.info(stratName + "Don't enter LONG: Case #3: currentSecurityPrice is now below allMovAvgs.loAvg - can no longer reenter at XTreme.");
					position.setStkCanReenterAtXtreme(false);
					position.setStkLocalXtremePrice(allMovAvgs.loAvg);
					position.setXtremeUpdatedDate(now);
					return null;
				}
				 /* 
				 * Case #4: currentSecurityPrice is above allMovAvg.loAvg (and canReenterAtXtreme is false)
				 *    It looks at this point that the currentSecurityPrice has dipped below allMovAvgs.loAvg
				 *      so we no longer enter at XTreme price.
				 *    Enter at allMovAvg.loAvg instead.
				 *    
				 *    canReenterAtXtreme = false (same)
				 *    XtremePrice = currentSecurityPrice
				 *    Enter Position
				 */
				else if ( (currentSecurityPrice.compareTo(allMovAvgs.loAvg) > 0 ) && !position.isStkCanReenterAtXtreme() ) {
					log.info(stratName + "Enter LONG: Case #4: currentSecurityPrice is above allMovAvg.loAvg (and canReenterAtXtreme is false)" + spread.toStringShort() );
					spread.setEnterReason("LONG: PriceAboveLoMovAvg, " + strategy.getSymbol() + "," + dtNow + ", canReenterAtXtrme: FALSE, Price: " + currentSecurityPrice + 
										  ", LIMIT_ORDER_KICKER: " + ThetaConstants.LIMIT_ORDER_KICKER + 
										  ", MovAvgLengths: " + strategy.getMovingAvg1() + " : " + strategy.getMovingAvg2() + 
     				                      ", hiMovAvg: " + allMovAvgs.hiAvg + 
					                      ", loMovAvg: " + allMovAvgs.loAvg + 
				                          ", averageDailyRange: " + allMovAvgs.avgDailyRange +
							             ", gracePointsXAvgDailyRange: " + allMovAvgs.gracePointsXAvgDailyRange );
					position.setStkCanReenterAtXtreme(false);
					position.setStkLocalXtremePrice(currentSecurityPrice);
					position.setXtremeUpdatedDate(now);
					spread.setEnterMoneymkrPrice(currentSecurityPrice + ThetaConstants.LIMIT_ORDER_KICKER);
					spread.setUpdatedBy("insertEnterPrices - MOVINGAVERAGES");
					spread.setUpdatedDate(now);
					return spread;
				}  else {
					log.info(stratName + "Don't Enter LONG: no action.");
					return null;
				}
			} else if (strategy.isShort()) {

				/*
				 * *******************************************
				 * SHORT
				 * *******************************************
				 */

				/* 
				 * Case #1: currentSecurityPrice below allMovAvg.hiAvg but above XTreme Price (and canReenterAtXtreme is true)
				 *    canReenterAtXtreme = true (same)
				 *    XtremePrice = XtremePrice (same)
				 *    Do not enter position
				 *    nothing changes ...
				 */

				/* Case #2: currentSecurityPrice is below XTremePrice (and canReenterAtXtreme is true) minus gracePointsXAvgDailyRange
				 *    canReenterAtXtreme = false
				 *    XtremePrice = currentSecurityPrice
				 *    Enter spread immediately
				 *    NOTE: This is likely redundant - if canReenterAtExtreme is true, then we can get in higher at stkReEnterAtPrice
				 */
				if ( (currentSecurityPrice.compareTo(position.getStkLocalXtremePrice() - allMovAvgs.gracePointsXAvgDailyRange) < 0) && position.isStkCanReenterAtXtreme() ) {
					log.info(stratName + "Enter SHORT: Case #2: currentSecurityPrice is below XTremePrice (and canReenterAtXtreme is true)" + spread.toStringShort());
					position.setStkCanReenterAtXtreme(false);
					spread.setEnterReason("SHORT: PriceBelowXTremePrice, " + strategy.getSymbol() + "," + dtNow + ", canReenterAtXtrme: TRUE, Price: " + currentSecurityPrice + 
										  ", MovAvgLengths: " + strategy.getMovingAvg1() + " : " + strategy.getMovingAvg2() + 
										  ", LIMIT_ORDER_KICKER: " + ThetaConstants.LIMIT_ORDER_KICKER + 
	                                      ", averageDailyRange: " + allMovAvgs.avgDailyRange +
                                          ", XtremePrice: " + position.getStkLocalXtremePrice() +
							              ", dateXtremePrice: " + position.getXtremeUpdatedDateStr() + 
	                                      ", gracePointsXAvgDailyRange: " +  allMovAvgs.gracePointsXAvgDailyRange );
					position.setStkLocalXtremePrice(currentSecurityPrice);
					position.setXtremeUpdatedDate(now);
					spread.setEnterMoneymkrPrice(currentSecurityPrice - ThetaConstants.LIMIT_ORDER_KICKER);
					spread.setUpdatedBy("insertEnterPrices - MOVINGAVERAGES");
					spread.setUpdatedDate(now);
					return spread;
				}

				
				/* Case #2A: currentSecurityPrice is below reEnterAtPrice 
				 *           and this is below allMovAvg.hiAvg   
				 *           (and canReenterAtXtreme is true)
				 *    canReenterAtXtreme = false
				 *    XtremePrice = currentSecurityPrice
				 *    Enter spread immediately
				 */
				Integer reEnterPrice = position.getStkReEnterAtPrice();
				if (  reEnterPrice != null && reEnterPrice.compareTo(0) > 0 
					  && currentSecurityPrice.compareTo(reEnterPrice) < 0
					  && reEnterPrice.compareTo(allMovAvgs.hiAvg) < 0  
					  && position.isStkCanReenterAtXtreme() ) {
					log.info(stratName + "Enter SHORT: Case #2A: currentSecurityPrice is below position.reEnterPrice (and canReenterAtXtreme is true)" + spread.toStringShort());
					spread.setEnterReason("SHORT: PriceBelowReEnterAtPrice, " + strategy.getSymbol() + "," + dtNow + ", Price: " + currentSecurityPrice + 
										  ", MovAvgLengths: " + strategy.getMovingAvg1() + " : " + strategy.getMovingAvg2() + 
										  ", LIMIT_ORDER_KICKER: " + ThetaConstants.LIMIT_ORDER_KICKER + 
	                                      ", averageDailyRange: " + allMovAvgs.avgDailyRange +
							              ", reEnterPrice: " + reEnterPrice + 
	                                      ", loMovAvg: " + allMovAvgs.loAvg +
	                                      ", hiMovAvg: " + allMovAvgs.hiAvg +
	                                      ", gracePointsXAvgDailyRange: " + allMovAvgs.gracePointsXAvgDailyRange );
					position.setStkCanReenterAtXtreme(false);
					position.setStkLocalXtremePrice(currentSecurityPrice);
					position.setXtremeUpdatedDate(now);
					position.setStkReEnterAtPrice(ThetaConstants.ZERO_INT);
					spread.setEnterMoneymkrPrice(currentSecurityPrice - ThetaConstants.LIMIT_ORDER_KICKER);
					spread.setUpdatedBy("insertEnterPrices - Case 2A SHORT");
					spread.setUpdatedDate(now);
					return spread;
				}
				
				/* 
				 * Case #3: currentSecurityPrice is now above allMovAvgs.loAvgMinus
				 *    canReenterAtXtreme = false
				 *    XtremePrice = allMovAvgs.loAvgMinus
				 *    Do not enter position - Reenter at xtreme is dead at this point
				 */
				else if (currentSecurityPrice.compareTo(allMovAvgs.hiAvg) > 0) {
					log.info(stratName + "Don't enter SHORT: Case #3: currentSecurityPrice is now above allMovAvgs.hiAvg - can no longer reenter at XTreme.");
					position.setStkCanReenterAtXtreme(false);
					position.setStkLocalXtremePrice(allMovAvgs.hiAvg);
					position.setXtremeUpdatedDate(now);
					return null;
				}
				 /* 
				 * Case #4: currentSecurityPrice is below allMovAvg.loAvgMinus (and canReenterAtXtreme is false)
				 *    It looks at this point that the currentSecurityPrice has dipped above allMovAvgs.loAvgMinus
				 *      so we no longer enter at XTreme price.
				 *    Enter at allMovAvg.loAvgMinus instead.
				 *    
				 *    canReenterAtXtreme = false (same)
				 *    XtremePrice = currentSecurityPrice
				 *    Enter Position
				 */
				else if ( (currentSecurityPrice.compareTo(allMovAvgs.hiAvg) < 0 ) && !position.isStkCanReenterAtXtreme() ) {
					log.info(stratName + "Enter SHORT: Case #4: currentSecurityPrice is below allMovAvg.hiAvg (and canReenterAtXtreme is false)" + spread.toStringShort() );
					position.setStkCanReenterAtXtreme(false);
					position.setStkLocalXtremePrice(currentSecurityPrice);
					position.setXtremeUpdatedDate(now);
					spread.setEnterReason("SHORT: PriceBelowHiAvg, " + strategy.getSymbol() + "," + dtNow + ", canReenterAtXtrme: FALSE, Price: " + currentSecurityPrice + 
	                          ", LIMIT_ORDER_KICKER: " + ThetaConstants.LIMIT_ORDER_KICKER + 
	                          ", MovAvgLengths: " + strategy.getMovingAvg1() + " : " + strategy.getMovingAvg2() + 
							  ", loMovAvg: " + allMovAvgs.loAvg + 
		                      ", hiMovAvg: " + allMovAvgs.hiAvg + 
	                          ", averageDailyRange: " + allMovAvgs.avgDailyRange +
				              ", gracePointsXAvgDailyRange: " + allMovAvgs.gracePointsXAvgDailyRange );
					spread.setEnterMoneymkrPrice(currentSecurityPrice - ThetaConstants.LIMIT_ORDER_KICKER);
					spread.setUpdatedBy("insertEnterPrices - MOVINGAVERAGES");
					spread.setUpdatedDate(now);
					return spread;
				}  else {
					log.info(stratName + "Don't Enter SHORT: no action.");
					return null;
				}

			}  // isLong() or isShort()

		} else if (strategy.getStkStrategyType().equalsIgnoreCase(ThetaConstants.MOVING_AVERAGES_2)) {
		
			/*
			 * Check if the signal is dead - reset if so
			 */
			Integer ADRBPToKillSignal = 100;
			if (position.isSignalDead(currentSecurityPrice, ADRBPToKillSignal, allMovAvgs, upOrDownCode)) {
				position.resetLoMidHi(currentSecurityPrice, allMovAvgs, stratName);
				position.setDeadSignalDt(now);
			}
			
			/*
			 * 1. Set LoMidHi
			 */
			//LoMidHiCode loMidHi = position.setLoMidHi(currentSecurityPrice, allMovAvgs);
			
			/*
			 * 2. Determine if it is time to get in
			 *    If so, reset the LoMidHi flags in Position
			 */
			boolean change = Decider.changePosition (
					upOrDownCode, 
					position.isStkHasBeenLo(), 
					position.isStkHasBeenMid(),
					position.isStkHasBeenHi(),
					loMidHi
					);
			log.info(stratName + "Decider result: " + change);

			
			/*
			 * Get in
			 */
			//Integer movAvgNum = 1;  // Fast Moving Average
			//Integer targetSlope = 3;  // Longest increment (i.e. 1 day)
			//log.info(stratName + "isMASufficient: " + slope.isMASufficient(strategy, upOrDownCode, movAvgNum, targetSlope));
			//if (change && slope.isMASufficient(strategy, upOrDownCode, movAvgNum, targetSlope) ) {
			//	log.info(stratName + "Get in!  Decider gave the OK!");

			
            Integer movAvgNumUp = 1;  // Fast Moving Average
            Integer movAvgNumDown = 2;  // Fast Moving Average
            Integer targetSlope = 3;  // Longest increment (i.e. 1 day)
            log.info(stratName + "isMASufficientUp: " + slope.isMASufficient(strategy, upOrDownCode, movAvgNumUp, targetSlope));
            log.info(stratName + "isMASufficientDown: " + slope.isMASufficient(strategy, upOrDownCode, movAvgNumDown, targetSlope));
            if (change &&
                    (
                            (upOrDownCode.equals(UpOrDownCode.UP) && slope.isMASufficient(strategy, upOrDownCode, movAvgNumUp, targetSlope))
                            ||
                            (upOrDownCode.equals(UpOrDownCode.DOWN) && slope.isMASufficient(strategy, upOrDownCode, movAvgNumDown, targetSlope))
                    )
                ) {
                log.info(stratName + "Get in!  Decider gave the OK!");
				spread.setEnterReason("MovingAverages2: " + upOrDownCode.getValue() + 
						", Symbol: " + strategy.getSymbol() + 
						" , " + dtNow +
						", Price: " + currentSecurityPrice + 
						", LO:MID:HI: " + position.isStkHasBeenLo() + ":" + position.isStkHasBeenMid() + ":" + position.isStkHasBeenHi() + 
						", LOMIDHI Now: " + loMidHi.getValue() +
						", MovAvgLengths: " + strategy.getMovingAvg1() + " : " + strategy.getMovingAvg2() + 
						", MovingAverages Lo,Hi: " + allMovAvgs.loAvg + ":" + allMovAvgs.hiAvg +
						", Direction: " +  upOrDownCode.getValue() +
						", TargetSlope: " + targetSlope);

				spread.setEnterMoneymkrPrice(currentSecurityPrice);
				spread.setUpdatedBy("MovingAverages2");
				spread.setUpdatedDate(now);
				
				// And reset position once in...
				position.resetLoMidHi(currentSecurityPrice, allMovAvgs, stratName);
				return spread;
			} else {
				return null;
			}
			
			
		} else if (strategy.getStkStrategyType().equalsIgnoreCase(ThetaConstants.SLOPES)) {
		
			
			if (strategy.isLong()) {
				/*
				 * *******************************************
				 * LONG
				 * *******************************************
				 */
			
				/*
				 * Case #1 - is above or equal to counterXtreme, but hasn't exceeded counterXtreme yet
				 * Do nothing
				 */
				
				/*
				 * Case #2 - is below counterXtreme 
				 * Set counterXtreme price to current price
				 * Set hitCounterXtreme to true
				 */
				Integer counterXtreme = position.getStkCounterXtremePrice();
				if (counterXtreme == null || currentSecurityPrice.compareTo(counterXtreme) < 0 ) {
					position.setStkHitInitialCounterXTreme(true);
					position.setStkCounterXtremePrice(currentSecurityPrice);
					position.setXtremeUpdatedDate(now);
				}	
				
				/*
				 * Case #3 - is between counterXtreme and counterXtreme plus amount needed to get out
				 * Do nothing
				 */
				
				/*
				 * Case #4 - is enough above counterXtreme, and has already hit counterXtreme
				 * Get in
				 * Set StkLocalXtremePrice
				 */
				
				//Integer getInAmountAboveCounterXTreme = (allMovAvgs.avgDailyRange * strategy.getStkTrailingStopBpTimesRange())/ThetaConstants.ONE_HUNDRED_INT;
				Integer getInAmountAboveCounterXTreme = (allMovAvgs.avgDailyRange * 20)/ThetaConstants.ONE_HUNDRED_INT;

				if ( (currentSecurityPrice.compareTo(position.getStkCounterXtremePrice() + getInAmountAboveCounterXTreme) > 0 ) 
						&& position.isStkHitInitialCounterXTreme()) {
					log.info(stratName + "Enter LONG: SLOPE: PriceEnoughAboveCounterXTreme" + spread.toStringShort());
					spread.setEnterReason("LONG: SLOPE: PriceEnoughAboveCounterXTreme, " + strategy.getSymbol() + "," + dtNow + ", isStkHitInitialCounterXTreme: TRUE, Price: " + currentSecurityPrice + 
							  ", MovAvgLengths: " + strategy.getMovingAvg1() + " : " + strategy.getMovingAvg2() + 
				              ", StkCounterXtremePrice: " + position.getStkCounterXtremePrice() +
                              ", LIMIT_ORDER_KICKER: " + ThetaConstants.LIMIT_ORDER_KICKER + 
	                          ", averageDailyRange: " + allMovAvgs.avgDailyRange +
				              ", XtremePrice: " + position.getStkLocalXtremePrice() +
				              ", dateXtremePrice: " + position.getXtremeUpdatedDateStr() + 
   				              ", bpTimesRange: 10" +
				              ", getInAmountAboveCounterXTreme: " + getInAmountAboveCounterXTreme +
	                          ", gracePointsXAvgDailyRange: " +  allMovAvgs.gracePointsXAvgDailyRange );
					
					position.setStkLocalXtremePrice(currentSecurityPrice);
					position.setXtremeUpdatedDate(now);
					spread.setEnterMoneymkrPrice(currentSecurityPrice + ThetaConstants.LIMIT_ORDER_KICKER);
					spread.setUpdatedBy("Get in LONG:SLOPES");
					spread.setUpdatedDate(now);
					return spread;
				} else {
					log.info("Don't Enter SLOPES LONG : no action.");
					return null;
				}
				
				
			} else if (strategy.isShort()) {
				
				/*
				 * *******************************************
				 * SHORT
				 * *******************************************
				 */
				/*
				 * Case #1 - is below or equal to counterXtreme, but hasn't exceeded counterXtreme yet
				 * Do nothing
				 */
				
				/*
				 * Case #2 - is above counterXtreme
				 * Set counterXtreme price to current price
				 * Set hitCounterXtreme to true
				 */
				Integer counterXtreme = position.getStkCounterXtremePrice();
				if (counterXtreme == null || currentSecurityPrice.compareTo(counterXtreme) > 0 ) {
					position.setStkHitInitialCounterXTreme(true);
					position.setStkCounterXtremePrice(currentSecurityPrice);
					position.setXtremeUpdatedDate(now);
				}	
				
				/*
				 * Case #3 - is between counterXtreme and counterXtreme minus amount needed to get out
				 * Do nothing
				 */
				
				/*
				 * Case #4 - is enough below counterXtreme, and has already hit counterXtreme
				 * Get in
				 * Set StkLocalXtremePrice
				 */
				
				//Integer getInAmountBelowCounterXTreme = (allMovAvgs.avgDailyRange * strategy.getStkTrailingStopBpTimesRange())/ThetaConstants.ONE_HUNDRED_INT;
				Integer getInAmountBelowCounterXTreme = (allMovAvgs.avgDailyRange * 20)/ThetaConstants.ONE_HUNDRED_INT;

				if ( (currentSecurityPrice.compareTo(position.getStkCounterXtremePrice() - getInAmountBelowCounterXTreme) < 0 ) 
						&& position.isStkHitInitialCounterXTreme()) {
					log.info(stratName + "Enter SHORT: SLOPE: PriceEnoughBelowCounterXTreme" + spread.toStringShort());
					spread.setEnterReason("SHORT: SLOPE: PriceEnoughBelowCounterXTreme, " + strategy.getSymbol() + "," + dtNow + ", isStkHitInitialCounterXTreme: TRUE, Price: " + currentSecurityPrice + 
				              ", StkCounterXtremePrice: " + position.getStkCounterXtremePrice() +
				              ", MovAvgLengths: " + strategy.getMovingAvg1() + " : " + strategy.getMovingAvg2() + 
				              ", LIMIT_ORDER_KICKER: " + ThetaConstants.LIMIT_ORDER_KICKER + 
	                          ", averageDailyRange: " + allMovAvgs.avgDailyRange +
				              ", XtremePrice: " + position.getStkLocalXtremePrice() +
				              ", dateXtremePrice: " + position.getXtremeUpdatedDateStr() + 
   				              ", bpTimesRange: 10" +
				              ", getInAmountAboveCounterXTreme: " + getInAmountBelowCounterXTreme +
	                          ", gracePointsXAvgDailyRange: " +  allMovAvgs.gracePointsXAvgDailyRange );
					
					position.setStkLocalXtremePrice(currentSecurityPrice);
					position.setXtremeUpdatedDate(now);
					spread.setEnterMoneymkrPrice(currentSecurityPrice + ThetaConstants.LIMIT_ORDER_KICKER);
					spread.setUpdatedBy("Get in SHORT:SLOPES");
					spread.setUpdatedDate(now);
					return spread;
					} else {
						log.info("Don't Enter SLOPES SHORT : no action.");
						return null;
					}
				
			}			
		
		} else if (strategy.getStkStrategyType().equalsIgnoreCase(ThetaConstants.MANUAL_GET_IN)) {
			//Calendar now = Calendar.getInstance();

			if (position.getStkLocalXtremePrice() == null) {
				position.setStkLocalXtremePrice(currentSecurityPrice);
			}

			if (strategy.isLong()) {
				log.info(stratName + "Enter MANUAL - LONG: " + spread.toStringShort());
				spread.setEnterMoneymkrPrice(currentSecurityPrice + ThetaConstants.LIMIT_ORDER_KICKER);
			} else if (strategy.isShort()) {
				log.info(stratName + "Enter MANUAL - SHORT: " + spread.toStringShort());
				spread.setEnterMoneymkrPrice(currentSecurityPrice - ThetaConstants.LIMIT_ORDER_KICKER);
			}

			position.setStkCanReenterAtXtreme(false);
			position.setStkLocalXtremePrice(currentSecurityPrice);

			spread.setEnterReason("MANUAL_GET_IN. Price: " + currentSecurityPrice);
			
			spread.setUpdatedBy("insertEnterPrices - MANUAL_GET_IN");
			spread.setUpdatedDate(now);
			strategy.setStkStrategyType(ThetaConstants.MANUAL);
			return spread;
		} else if (strategy.getStkStrategyType().equalsIgnoreCase(ThetaConstants.MANUAL)) {
			//Calendar now = Calendar.getInstance();
			spread.setEnterReason(stratName + "MANUAL. Price: " + currentSecurityPrice);
	
			if (strategy.isLong() && (currentSecurityPrice.compareTo(position.getStkLocalXtremePrice()) > 0)) {
				log.info(stratName + "MANUAL - LONG: currentSecurityPrice is above XTremePrice: " + spread.toStringShort());
				position.setStkLocalXtremePrice(currentSecurityPrice);
				spread.setUpdatedBy("insertEnterPrices - MANUAL");
				spread.setUpdatedDate(now);					
				return spread;
			} else if (strategy.isShort() && (currentSecurityPrice.compareTo(position.getStkLocalXtremePrice()) < 0)) {
				log.info(stratName + "MANUAL - SHORT: currentSecurityPrice is below XTremePrice: " + spread.toStringShort());
				position.setStkLocalXtremePrice(currentSecurityPrice);
				spread.setUpdatedBy("insertEnterPrices - MANUAL");
				spread.setUpdatedDate(now);					
				return spread;
			} else throw new ThetaExceptionExc("Stock Strategy must be either LONG or SHORT");
		} //endif ...  else if (strategy.getStkStrategyType().equalsIgnoreCase(ThetaConstants.MANUAL)) {
		
		log.info(stratName + "Bottom of insertStockEnterPricesIntoSpread");

		return spread;
	}


//	private void storeSpreads(Set<Spread> spreadsToEnter, Position position, Strategy strategy, Integer currentSecurityPrice) 
//	throws ThetaExceptionExc {
//		
//		Iterator<Spread> spreadsToEnterIter = spreadsToEnter.iterator();
//		
//		if (spreadsToEnter.size() > 0)
//			log.info(stratName + "Inside enterSpreads.  Here are spreadsToEnter: " + spreadsToEnter.size() );
//		else
//			log.info(stratName + "Spreads to enter size is zero (or less!) - NO ENTRY!: " + spreadsToEnter.size() );
//			
//		
//		while (spreadsToEnterIter.hasNext()){
//			Spread spread = (Spread) spreadsToEnterIter.next();
//			log.info(stratName + "IN enterSpreads.  Here is the spread info: " + spread.toString() );
//			synchronized(dbAccessMutex) {spreadDAO.synchronizedStoreAndFlush(spread);}
//		}
//		//log.info("Bottom of enterSpreads.");
//	}


	
	/**
	 * @param spreadsToEnter
	 * @param positions
	 * 
	 * 6.	ENTRY and REENTRY:
	 *   a.	See entry and reentry rules
	 *   b.	Check STRATEGY:SET_REENTER_ON_WIN and STRATEGY:SET_REENTER_ON_LOSS
	 *
	 */
	private void enterSpreads(Position position, Strategy strategy, Integer currentSecurityPrice) 
	throws ThetaExceptionExc {

		log.info(stratName + "Top of enterSpreads. PostionId: " + position.getPositionId());
		String ADVISEOPEN = OpenOrClosedCode.ADVISEOPEN.toString();
		String APPROVE = ApprovalCode.APPROVE.toString();
		String HOLD = ApprovalCode.HOLD.toString();  
		
		Set<Spread> spreads;
		synchronized(dbAccessMutex) {spreads = spreadDAO.findSpreadByPositionId(position.getPositionId());}

		for (Spread spread : spreads) {
			log.info("Top of loop in EnterSpreads." + spread.getCreatedBy() + " :version: " + spread.getVersion() );
			
			
			if (!spread.getOpenOrClosed().equals(OpenOrClosedCode.ADVISEOPEN.toString())) {continue;}
			
			if ( spread.getEnterApproval().equals(ApprovalCode.CLOSE.toString()) ||
					spread.getEnterApproval().equals(ApprovalCode.KILL.toString() )) {
				
				spread.setOpenOrClosed(OpenOrClosedCode.CLOSED.toString() );
				position.setLoMidHiToFalse();
				synchronized(dbAccessMutex) {
					spreadDAO.synchronizedStore(spread);
					positionDAO.store(position);
					if (spread.getEnterApproval().equals(ApprovalCode.KILL.toString())) {
						strategy.setActiveFlag("F");
					}
				}
				return;
			}
			
			
			if (!strategy.chkLongOrShort()){
				log.info(stratName + "Stock strategy.longOrShortFlag MUST BE LONG or SHORT!  Strategy: " + strategy.toString());
				throw new ThetaExceptionExc(stratName + "Stock Strategy MUST BE LONG or SHORT!");
			}
			
			UpOrDownCode upOrDownCode = ( strategy.isLong() ) ? UpOrDownCode.UP : UpOrDownCode.DOWN;
			
			String openOrClosed = spread.getOpenOrClosed().trim().toUpperCase();
			String enterApproval = spread.getEnterApproval().trim().toUpperCase();
			
			log.info("Here is enterApproval, openOrClosed: " + enterApproval + ", " + openOrClosed);
			if ( 
					(enterApproval.equals(APPROVE) && openOrClosed.equals(ADVISEOPEN))  ||
					( enterApproval.equals(HOLD) && !spread.isWithinLimits(upOrDownCode, EnterOrExitCode.ENTER, currentSecurityPrice) )
				){
				
				//log.info(stratName + "About to enter spread.  Here is the spread info: " + spread.toString() );
				//synchronized(dbAccessMutex) {spreadDAO.flush();}
				log.info(stratName + "About to enter spread.  Here is the spread info: " + spread.toString() );
				spread.setOpenOrClosed(OpenOrClosedCode.PENDOPEN.toString());
				log.info("Before enterSingleSpread." + spread.getCreatedBy() + " :version: " + spread.getVersion() );
				positionExecutor.enterSingleSpread(spread, position, strategy, client, dbAccessMutex);

			}
		}
	}

	
	/************************************************************************************************************/
	/************************************************************************************************************/
	/************************************************************************************************************/
	/*                                     COMMUNICATION WITH CLIENT                                            */
	/************************************************************************************************************/
	/************************************************************************************************************/
	/************************************************************************************************************/
	
	
	

	
	/**
    * 
    * @param position
    * @return
    */
	private Set<Spread> getOpenOrPendSpreads(Position position){

		Set<Spread> openOrPendSpreads =  new HashSet<Spread>();
		
		Set<Spread> allSpreads;
		
		synchronized(dbAccessMutex) {allSpreads = spreadDAO.findSpreadByPositionId(position.getPositionId());}
		
		Iterator<Spread> spreadIter = allSpreads.iterator();
		while (spreadIter.hasNext()){		
			Spread spread = (Spread) spreadIter.next();
			//spread.setPosition(position);
			
			String openOrClosed = spread.getOpenOrClosed();
			String exitApproval = spread.getExitApproval();
			if (openOrClosed.equalsIgnoreCase(OpenOrClosedCode.OPEN.toString()) ||
					(openOrClosed.equalsIgnoreCase(OpenOrClosedCode.PENDOPNCNF.toString())) ||
					(openOrClosed.equalsIgnoreCase(OpenOrClosedCode.ADVISEOPEN.toString())) ||
					(openOrClosed.equalsIgnoreCase(OpenOrClosedCode.PENDOPEN.toString())) ||
					(openOrClosed.equalsIgnoreCase(OpenOrClosedCode.PENDCLSCNF.toString())) ||
					(openOrClosed.equalsIgnoreCase(OpenOrClosedCode.PENDCLOSE.toString())) ||
					(openOrClosed.equalsIgnoreCase(OpenOrClosedCode.ADVISECLS.toString()) && 
							exitApproval.equalsIgnoreCase(ApprovalCode.HOLD.toString())) 

			){
				openOrPendSpreads.add(spread);
			}
			
		}
	
		return openOrPendSpreads;
	}

	
	private HashMap<String, OptionPrice> optPriceCache = new HashMap<String, OptionPrice>();
	private OptionPrice getOptionPrice(String symbol, Integer month, Integer year, Integer strike, String putOrCall)
	throws ThetaExceptionExc {

		// Caching contract details
		String detailKey = symbol + year + month + strike + putOrCall;

		ContractDetails det;
		det = client.getDetailCache().get(detailKey);
		if (det == null) {
			//thetaUtil.enterRecordIntoHeartbeat("CACHE: adding to detailCache: " + detailKey);
		
			//Integer nextSeq = ThetaUtil.getNextRequestSeqNo(requestSeqDAO);
			//client.resp.setAllPricesToZero();

			client.reqOptionDetails(symbol, month, year, strike, putOrCall);
			// This must be here - to get the details of the Options
			ThetaUtil.secondsToSleepNoThrow(ThetaConstants.ONE_INT);

			det = client.getResp().getContractDetails();		
			client.getDetailCache().put(detailKey, det);
		} else {
			//thetaUtil.enterRecordIntoHeartbeat("CACHE: found key - not adding to cache: " + detailKey);
			thetaUtil.millisToSleep(150);
		}
		
		OptionPrice optionPrice;
		optionPrice = optPriceCache.get(detailKey);

		if ( (optionPrice == null)
			||
			(optionPrice != null) && (optionPrice.isStale()) ){
			
			//thetaUtil.enterRecordIntoHeartbeat("CACHE: Option Price is null or stale  - refilling!  detailKey: " + detailKey);
			
			int uniqueIdForMktData = ThetaUtil.getNextRequestSeqNo(requestSeqDAO);
			ResponseInterface resp = client.getResp();
			resp.setAllPricesToZero(uniqueIdForMktData);
			
			client.reqOptionPrice(det, uniqueIdForMktData);		
			
			String recordIntoHeartbeat = "";
			for (int i3=0; i3 < ThetaConstants.NUM_TIMES_TO_CHECK_PRICE; i3++){

				// This pause must be here
				ThetaUtil.secondsToSleepNoThrow(ThetaConstants.SHORT_SLEEP_TIME);

				recordIntoHeartbeat = "Option: " + symbol
				+ " : " + month + year + " : " + strike + " : " + putOrCall
				+ " : " 
				+ (resp.getPriceAsk(uniqueIdForMktData) * ThetaConstants.ONE_HUNDRED_INT) 
				+ " : " + (resp.getPriceBid(uniqueIdForMktData) * ThetaConstants.ONE_HUNDRED_INT);
				
				thetaUtil.enterRecordIntoHeartbeat( recordIntoHeartbeat );

				if (resp.getPriceBid(uniqueIdForMktData) != 0){
					break;
				} 
			}
			
			log.info(recordIntoHeartbeat);

			optionPrice = new OptionPrice();
			optionPrice.setAsk(resp.getPriceAsk(uniqueIdForMktData) * ThetaConstants.ONE_HUNDRED_INT);
			optionPrice.setBid(resp.getPriceBid(uniqueIdForMktData) * ThetaConstants.ONE_HUNDRED_INT);
//			optionPrice.setLast(client.resp.getPriceLast(uniqueIdForMktData) * ThetaConstants.ONE_HUNDRED_INT);
//			optionPrice.setHigh(client.resp.getPriceHigh(uniqueIdForMktData) * ThetaConstants.ONE_HUNDRED_INT);
//			optionPrice.setLow(client.resp.getPriceLow(uniqueIdForMktData) * ThetaConstants.ONE_HUNDRED_INT);
			optionPrice.setClose(resp.getPriceClose(uniqueIdForMktData) * ThetaConstants.ONE_HUNDRED_INT);
			
			optionPrice.setTtlMs(ThetaConstants.OPTIONPRICE_TTL_MS);
			optionPrice.setUpdated(Calendar.getInstance());
			optPriceCache.put(detailKey, optionPrice);

			insertIntoSecurityPrice(detailKey, optionPrice.getBidAskHalf(),"IB", true, 0, 0, 0, null, "NA");

			client.cancelMktData(uniqueIdForMktData);

		} else {
			//thetaUtil.enterRecordIntoHeartbeat("CACHE: Option Price is NOT stale - using from cache:!  detailKey: " + detailKey);
		}

		log.info(stratName + "key, op: " + detailKey + optionPrice.toString());

		return optionPrice;
	}


		
	
	
	/************************************************************************************************************/
	/************************************************************************************************************/
	/************************************************************************************************************/
	/*                                     OPTIONS                                                              */
	/************************************************************************************************************/
	/************************************************************************************************************/
	/************************************************************************************************************/
	private void runAndExecuteOptExitRules(Position position, Strategy strategy, Integer underlyingSymbolPrice, Integer VIXPrice, MovingAverages movAvgs) 
	throws ThetaExceptionExc {

		Set<Spread> spreads = spreadDAO.findSpreadByPositionId(position.getPositionId());
		
		Iterator<Spread> spreadIter = spreads.iterator();
		while (spreadIter.hasNext()){
			Spread spread  = (Spread) spreadIter.next();
			
			if (spread.getOpenOrClosed().equals(OpenOrClosedCode.OPEN.toString())
					|| 
				    ( spread.getOpenOrClosed().equals(OpenOrClosedCode.HAILMARY.toString())
				      && (spread.getHmInsExitActualIb().equals(ThetaConstants.ZERO_INT)) 
				    )
				    ||
				    (spread.getOpenOrClosed().equals(OpenOrClosedCode.KILLED.toString()))
				) 
				log.info("OPEN or HAILMARY OPEN or KILLED");
			else
				continue;

			spread.setCurrentSecurityPrice(underlyingSymbolPrice);
			spread.setCurrentVixPrice(VIXPrice);

			log.info("runAndExecuteOptExitRules.  Spread: " + spread.toStringShort());
			
			/**
			 * Once we’re into a spread, the value that all exit rules are pegged to is the GROSS_ENTRY_REVENUE.  
			 */
			Integer entryRevenue = (spread.getEnterMoneymkrPrice() - spread.getEnterInsurancePrice() - spread.getEnterCommission());

			OptionPrice currentInsurancePriceObj = getOptionPrice(position.getSymbol(), 
					position.getExpiryMonth(), 
					position.getExpiryYear(), 
					spread.getStrikeInsurance(), 
					position.getOptRight());
			Integer currentInsurancePrice = currentInsurancePriceObj.getBidPlus();
			spread.setCurrentInsurancePrice(currentInsurancePrice);
			
			
			OptionPrice currentMoneymkrPriceObj = getOptionPrice(position.getSymbol(), 
					position.getExpiryMonth(), 
					position.getExpiryYear(), 
					spread.getStrikeMoneymkr(), 
					position.getOptRight());

			/**
			 * Use the following - as we want a relatively quick sale:
			 * MoneyMkr - buy back - "buy low" - ASK MINUS 
			 * Insurance - sell back - "sell high" - BID PLUS 
			 * now - getLastIfBetween() - can go back to this
			 */
			Integer currentMoneymkrPrice = currentMoneymkrPriceObj.getAskMinus();
			spread.setCurrentMoneymkrPrice(currentMoneymkrPrice);

			// OK - continue to next spread if HAILMARY Open
			if (spread.getOpenOrClosed().equals(OpenOrClosedCode.HAILMARY.toString()))
				continue;


			if ((currentMoneymkrPrice <= ThetaConstants.ZERO_INT) ||
					(currentInsurancePrice <= ThetaConstants.ZERO_INT)){
				log.info("One of the prices is ZERO or negative - exiting loop: " + currentMoneymkrPrice + " : " + currentInsurancePrice);
				thetaUtil.enterRecordIntoHeartbeat("One of the prices is ZERO or negative - exiting loop: " + currentMoneymkrPrice + " : " + currentInsurancePrice);
				continue;
			}
			
			/**
			 * Set Profit Loss Unrealized 
			 * ENTER: Moneymaker: 150 Insurance: 75
			 * EXIT: Moneymaker: 100 Insurance 50
			 * Profit here is 150-75-100-50 = 25
			 */
			// NOW in overallRecordKeeping
			//Integer plUnrealized = (spread.getEnterMoneymkrPrice()
			//						- spread.getEnterInsurancePrice()
			//						- spread.getCurrentMoneymkrPrice()
			//						+ spread.getCurrentInsurancePrice()
			//						- spread.getEnterCommission() - spread.getExitCommission());
						
			Integer currentSpreadPrice = currentMoneymkrPrice - currentInsurancePrice - spread.getExitCommission();

			/**
			 * Calculate the percentage of the intial gross revenue that it would
			 * cost to get out of the spread.
			 * Example:  
			 */ 
			Integer pctToExit = (currentSpreadPrice * ThetaConstants.ONE_HUNDRED_INT ) / entryRevenue;

			log.info("In runAndExecuteExitRules.  spread price | pctToExit : " + currentSpreadPrice + " | " + pctToExit);
			
			spread.setUpdatedBy("runAndExecuteExitRules");
			spread.setUpdatedDate(Calendar.getInstance());
			spread.setCurrentDate(Calendar.getInstance());

			spreadDAO.synchronizedStoreAndFlush(spread);
			//spreadDAO.store(spread);
			//spreadDAO.flush();
			
			/***************************************************
			 *  DING! DING! DING!  WIN!!!!
			 ***************************************************
			 */

			log.info("Checking - exit where pctToExit is less than getPctExitWin: " + pctToExit + "|" + strategy.getPctExitWin());
			/**
			 * So, the exitToEnterPercent (i.e. 28%) is less than percent to exit
			 * to win (i.e. 30%) - EXIT! All Good!
			 */
			if (pctToExit < strategy.getPctExitWin()){
				log.info("Bingo.  WIN!" + spread.toStringShort());
				thetaUtil.enterRecordIntoHeartbeat("EXITING: pctToExit<getPctExitWin()");
				spread = positionExecutor.exitSingleSpread(spread, position, strategy, MovingSpeedCode.SLOW, "Hit winning percent-WIN", client, dbAccessMutex);
				spreadDAO.synchronizedStoreAndFlush(spread);
				//spreadDAO.store(spread);
				//spreadDAO.flush();
				continue;
			}


			log.info("Checking - exitWithTrailingStops");
			/***************************************************
			 *  DING! DING! DING!  WIN!!!!
			 ***************************************************
			 */
			spread = setUpTrailingStops(strategy, spread, entryRevenue, currentSpreadPrice); 
			Spread returnSpread;
			returnSpread = exitWithTrailingStops(strategy, spread, position);
			if (returnSpread != null){
				spread = returnSpread;
				spreadDAO.synchronizedStoreAndFlush(spread);
				//spreadDAO.store(spread);
				//spreadDAO.flush();
				continue;
			}
			
			
			/**
			 * Killed by hand
			 */
		    if (spread.getOpenOrClosed().equals(OpenOrClosedCode.KILLED.toString())) {
				log.info("Killed by hand!" + spread.toStringShort());
				thetaUtil.enterRecordIntoHeartbeat("EXITING: Killed by hand");
				spread = positionExecutor.exitSingleSpread(spread, position, strategy, MovingSpeedCode.SLOW, "Killed by hand", client, dbAccessMutex);
				spreadDAO.synchronizedStoreAndFlush(spread);
				//spreadDAO.store(spread);
				//spreadDAO.flush();
				continue;
			}

		    /**
		     * In a bull market - sell all Bear (i.e. Call) Spreads
		     */
		    log.info("Exit Call in Bull Market");
			//returnSpread = exitAtInsurance(strategy, spread, position, underlyingSymbolPrice);
            if (exitCallInBullMarket(strategy, position, underlyingSymbolPrice, movAvgs)){
				log.info("Exiting the CALL - as this is now a bull market: " + spread.toStringShort());
				thetaUtil.enterRecordIntoHeartbeat("EXITING: exitCallInBullMarket");
				spread = positionExecutor.exitSingleSpread(spread, position, strategy, MovingSpeedCode.FAST, "ExitCall-BullMkt", client, dbAccessMutex);
				spreadDAO.synchronizedStoreAndFlush(spread);
				//spreadDAO.store(spread);
				//spreadDAO.flush();
				continue;
			}

		    /**
		     * In a bear market - sell all Bull (i.e. Put) Spreads
		     */
		    log.info("Exit Put in Bear Market");
			//returnSpread = exitAtInsurance(strategy, spread, position, underlyingSymbolPrice);
            if (exitPutInBearMarket(strategy, position, underlyingSymbolPrice, movAvgs)){
				log.info("Exiting the PUT - as this is now a bear market: " + spread.toStringShort());
				thetaUtil.enterRecordIntoHeartbeat("EXITING: exitCallInBearMarket");
				spread = positionExecutor.exitSingleSpread(spread, position, strategy, MovingSpeedCode.FAST, "ExitCall-BearMkt", client, dbAccessMutex);
				spreadDAO.synchronizedStoreAndFlush(spread);
				//spreadDAO.store(spread);
				//spreadDAO.flush();
				continue;
			}

            
			/**
			 * 
			 * #3F. LOSS EXIT RULES:
			 * ---------------------
			 *   NOTE: Note that there is NOT a “TOTAL LOSS” value at which to ditch this position.  
			 *   The reason is that the TOTAL LOSS of this position is capped when the spread is entered.  
			 *   In worst case (i.e. a highly trending market) this is the most that can be lost.
			 * 
			 *  1. EXIT AT INSURANCE
			 *    (Execute if STRATEGY:SET_EXIT_AT_INSURANCE is True)
			 *    EXIT when the value of the UNDERLYING SECURITY is directly under INSURANCE Option.
			 *    
			 *  2. EXIT AT BREAKEVEN 
			 *    (Execute if STRATEGY:SET_BREAKEVEN_WHEN_INS_HIT is True)
			 *     Once the value of the UNDERLYING SECURITY is directly under INSURANCE Option, 
			 *     set the value of SPREAD: EXIT_ABOVE_SPREAD_VALUE to a breakeven value.
			 * 
			 */

			if ((ThetaUtil.trueFalseToBoolean(strategy.getSetExitAtInsPrice())) &&
					(ThetaUtil.trueFalseToBoolean(strategy.getSetBreakevenWhenInsHit()))){
				log.info("Throwing exception: strategy - set exit at insurance price AND set breakeven when ins hit can't both be TRUE!");
				throw new ThetaExceptionExc("strategy - set exit at insurance price AND set breakeven when ins hit can't both be TRUE!");
			}

			//if (!( (ThetaUtil.trueFalseToBoolean(strategy.getSetExitAtInsPrice())) ||
			//		(ThetaUtil.trueFalseToBoolean(strategy.getSetBreakevenWhenInsHit())) )){
			//	log.info("Throwing exception: strategy - set exit at insurance price OR set breakeven when ins hit - one must be TRUE and the other FALSE!");
			//	throw new ThetaExceptionExc("strategy - set exit at insurance price OR set breakeven when ins hit - one must be TRUE and the other FALSE!");
			//}

			/**
			 * OK - absolute stop 70% of total risked!
			 * ONLY if setBreakevenWhenInsHit...
			 */
			//if (ThetaUtil.trueFalseToBoolean(strategy.getSetBreakevenWhenInsHit())){
				//Integer totalRisked = strategy.getAmountTotalRisk();
				//if ( currentSpreadPrice > ( (totalRisked * ThetaConstants.GET_OUT_PERCENT) / ThetaConstants.ONE_HUNDRED_INT )){
				//	log.info("BIG LOSS!" + spread.toStringShort());
				//	thetaUtil.enterRecordIntoHeartbeat("EXITING: currentSpreadPrice > large pct of totalRisked");
				//	spread = exitSingleSpread(spread);
				//	spreadDAO.store(spread);
				//	spreadDAO.flush();
				//	continue;
				//}
			//}
			

			// Hail Mary!
			if (strategy.getHmTurnOn()) {
				returnSpread = hailMaryExit(strategy, spread, position, underlyingSymbolPrice);
				if (returnSpread != null){
					spread = returnSpread;
					spreadDAO.synchronizedStoreAndFlush(spread);
					//spreadDAO.store(spread);
					//spreadDAO.flush();
					continue;
				}
			}
			
			log.info("Checking - exitAtInsurance");
			returnSpread = exitAtInsurance(strategy, spread, position, underlyingSymbolPrice);
			if (returnSpread != null){
				spread = returnSpread;
				spreadDAO.synchronizedStoreAndFlush(spread);
				//spreadDAO.store(spread);
				//spreadDAO.flush();
				continue;
			}

			/**
			 *  2. EXIT AT BREAKEVEN 
			 *    (Execute if STRATEGY:SET_BREAKEVEN_WHEN_INS_HIT is True)
			 *     Once the value of the UNDERLYING SECURITY is directly under INSURANCE Option, 
			 *     set the value of SPREAD: EXIT_BELOW_SPREAD_VALUE to a breakeven value.
			 *     
			 *     Example: Initial Moneymkr: $1.50 Insurance: $0.75 - Entry Revenue: $0.75
			 *              Then underlying Security hits Insurance: $$ to get out: $1.25
			 *              Now, want to get out when $$ to get out is BELOW $0.75 
			 *     
			 */
			log.info("Checking - exitAtBreakeven");

			returnSpread = exitAtBreakeven(strategy, spread, position, 
					currentInsurancePrice, underlyingSymbolPrice, currentSpreadPrice, entryRevenue);
			if (returnSpread != null){
				spread = returnSpread;
				spreadDAO.synchronizedStoreAndFlush(spread);
				//spreadDAO.store(spread);
				//spreadDAO.flush();
				continue;
			}
			
			

			/**
			 * #3F. CATCH-ALL EXIT RULES:
			 * ---------------------
			 *   EXIT at 12:00 NOON on DAYS_EXIT_BEFORE_EXPIRY before the expiry date of the spread.  
			 *   That is, if the Expiry date is Friday, September 17th, and the DAYS_EXIT_BEFORE_EXPIRY 
			 *   is set to 4, exit at 12:00 NOON on Monday, September 13th.
			 */
			log.info("Checking - CatchAllExit");
			returnSpread =  catchAllExit(strategy, spread, position);
			if (returnSpread != null){
				spread = returnSpread;
				spreadDAO.synchronizedStoreAndFlush(spread);
				//spreadDAO.store(spread);
				//spreadDAO.flush();
				continue;
			}
			
		} // while (spreadIter.hasNext()){
		
		//return position;
	} // private void runAndExecuteExitRules(Position position, Strategy strategy, Integer underlyingSymbolPrice) 

	
	
	/**
	 * 
	 * @param strategy
	 * @param spread
	 * @param entryRevenue
	 * @param currentSpreadPrice
	 * @return
	 * @throws ThetaExceptionExc
	 */
	private Spread setUpTrailingStops(Strategy strategy, Spread spread, Integer entryRevenue, Integer currentSpreadPrice) 
	throws ThetaExceptionExc {

		/**
		 * pctSetTrailingStop - this is where the stop gets set (i.e. at 50%)
		 * pctTrailingStop - this is the trailing stop percentage (i.e. 10%)
		 * triggerPoint - where the trailing stop is put on
		 */
		Integer pctSetTrailingStop = strategy.getPctSetTrailingStop();
		Integer pctTrailingStop = strategy.getPctTrailingStop();
		Integer triggerPoint = (entryRevenue * pctSetTrailingStop)/ThetaConstants.TEN_THOUSAND_INT;

		log.info("In exitWithTrailingStops.  Here is spreadPrice | triggerPoint: " + currentSpreadPrice + "|" + triggerPoint);
		/**
		 * OK!  Hit trigger point!
		 */
		if (currentSpreadPrice <= triggerPoint){
			spread.setTrailingStopIsSet(ThetaConstants.TRUE);
			/**
			 * Calculate absolute amount over which to exit
			 * And set - if initial!
			 */
			Integer proposedExitPoint = currentSpreadPrice + ((currentSpreadPrice * pctTrailingStop)/ThetaConstants.ONE_HUNDRED_INT); 
			if (spread.getExitAboveSpreadValue() == 0 ){
				spread.setExitAboveSpreadValue(proposedExitPoint);
			} else {
				Integer currentExitPoint = spread.getExitAboveSpreadValue();
				if (proposedExitPoint < currentExitPoint){
					spread.setExitAboveSpreadValue(proposedExitPoint);
				}//if (proposedExitPoint < currentExitPoint){
				
			}//if (spread.getExitAboveSpreadValue() == 0 ){
		
			//spreadDAO.store(spread);
			//spreadDAO.flush();
		} else { 
			spread.setTrailingStopIsSet(ThetaConstants.FALSE);
		}// if (currentSpreadPrice <= triggerPoint){
		
		return spread;
	} // private Spread setUpTrailingStops(Strategy strategy, Spread spread, Integer entryRevenue, Integer currentSpreadPrice) 


	/**
	 * 2. EXIT WITH TRAILING STOPS
	 *   (Execute if STRATEGY:SET_WIN_TRAILING_STOP is True)
	 *   
	 *   Set trailing stops when the SPREAD is at PERCENT_SET_TRAILING_STOP of GROSS_ENTRY_REVENUE.  
	 *   The SPREAD:TRAILING_STOP_IS_SET value is set to True and the EXIT_ABOVE_SPREAD_VALUE is set and 
	 *   adjusted down where appropriate (never adjusted up).  SELL if the spread value raises to 
	 *   EXIT_ABOVE_SPREAD_VALUE price. 
	 *	 
	 *   For Example: The AMOUNT_TOTAL_RISK is $300, and the GROSS_ENTRY_REVENUE is 20% of this or $60.  
	 *   
	 *   PERCENT_SET_TRAILING_STOP is 50% or $30.  So, when the value of the spread is $30, 
	 *   set the trailing stop to PERCENT_TRAILING_STOP (this is 10% or $6, so set the trailing stop to $36 – 
	 *   and exit if the spread value exceeds $36).
	 */
	private Spread exitWithTrailingStops(Strategy strategy, Spread spread, Position position) 
	throws ThetaExceptionExc {

		if (ThetaUtil.trueFalseToBoolean(strategy.getSetWinTrailingStop())){
			// entryRevenue 

			/** **********************************************************
			 * DING DING DING!  WIN!
			 * Hit trailing stop!
			 * ***********************************************************
			 */
			if (spread.getExitAboveSpreadValue() > 0){
				Integer dontExitAbovePrice = ((spread.getExitAboveSpreadValue() * ThetaConstants.ONE_HUNDRED_TWENTY ) / ThetaConstants.ONE_HUNDRED_INT);

				Integer currentSpreadPrice = spread.getCurrentMoneymkrPrice() - spread.getCurrentInsurancePrice();
				if ( (currentSpreadPrice > spread.getExitAboveSpreadValue()) &&
				    (currentSpreadPrice < dontExitAbovePrice) ) {
					thetaUtil.enterRecordIntoHeartbeat("EXITING: exitWithTrailingStops.  getExitAboveSpreadValue: " + spread.getExitAboveSpreadValue());
					log.info("EXITING: exitWithTrailingStops.  getExitAboveSpreadValue: " + spread.getExitAboveSpreadValue());
					spread = positionExecutor.exitSingleSpread(spread, position, strategy, MovingSpeedCode.SLOW, "ExitWithTrailingStops", client, dbAccessMutex);
					return spread;
				} //if (spread.getProfitLossUnrealized() > spread.getExitAboveSpreadValue())

				// OK - set back to zero if way off the charts - try again later ...
				// (this could happen first thing in the morning for instance)
				if (currentSpreadPrice >= dontExitAbovePrice){
					spread.setExitAboveSpreadValue(ThetaConstants.ZERO_INT);
					spreadDAO.synchronizedStoreAndFlush(spread);

					//spreadDAO.store(spread);
					//spreadDAO.flush();
					return null;
				}

			} //if (spread.getExitAboveSpreadValue() > 0){
		} //if (ThetaUtil.trueFalseToBoolean(strategy.getSetWinTrailingStop())){
		return null;
	}	//private Spread exitWithTrailingStops(Strategy strategy, Spread spread) 		
	

	/**
	 *  1. (LOSS) Exit Call/Bear Spread - due to a Bull Market
	 *  
	 *  The definition of a Bull Market is set in the Strategy record:
	 *  BullMktTrigger
	 *  BullMarketFlag
	 *  
	 *  Once we're in a bull market, all Call/Bear Spreads will be sold and not 
	 *  re-entered until the bull market is complete.
	 *  
	 */
	private boolean exitCallInBullMarket(Strategy strategy, Position position, Integer underlyingSymbolPrice, MovingAverages movAvgs) 
	throws ThetaExceptionExc {

		// This is not applicable to Puts - only calls
		if(position.isPut()) return false;

		MovingAverages.AllMovingAverages allMovAvgs = 
				movAvgs.getAllMovingAverages(position.getSymbol(), 
						                     strategy.getMovingAvg1(), 
						                     strategy.getMovingAvg2(), 
						                     strategy.getMovingAvgRange(), 
						                     underlyingSymbolPrice, 
						                     strategy.getMovingAvgGracePoints(),
						                     strategy.getMovingAvgTolerancePct());
		if (allMovAvgs == null) return false;

		if (underlyingSymbolPrice.compareTo(allMovAvgs.hiAvgPlus) > 0) {
			log.info("exiting call spread due to bull market.  underlyingSymbolPrice, hiAvgPlus: " + underlyingSymbolPrice + ", " + allMovAvgs.hiAvgPlus);
			return true;
		} else {
			log.info("NOT exiting call spread due to bull market.  underlyingSymbolPrice, hiAvgPlus: " + underlyingSymbolPrice + ", " + allMovAvgs.hiAvgPlus);
			return false;
		}
		
		//log.info("In exitCallInBullMarket.  underlyingSymbolPrice = " + underlyingSymbolPrice + ". BullMarketTrigger: = " + strategy.getBullMktTrigger());
		//return (strategy.isBullMkt(underlyingSymbolPrice, ticker));		
	}


	/**
	 *  1. (LOSS) Exit Put (i.e. Bull)  Spread - due to a Bear Market
	 *  
	 *  The definition of a Bear Market is set in the Strategy record:
	 *  BearMktTrigger
	 *  BearMarketFlag
	 *  
	 *  Once we're in a bear market, all Put/Bull Spreads will be sold and not 
	 *  re-entered until the bear market is complete.
	 *  
	 */
	private boolean exitPutInBearMarket(Strategy strategy, Position position, Integer underlyingSymbolPrice, MovingAverages movAvgs) 
	throws ThetaExceptionExc {

		// This is not applicable to Calls - only Puts
		if(position.isCall())
			return false;
		
		MovingAverages.AllMovingAverages allMovAvgs = 
				movAvgs.getAllMovingAverages(position.getSymbol(), 
						                     strategy.getMovingAvg1(), 
						                     strategy.getMovingAvg2(), 
						                     strategy.getMovingAvgRange(), 
						                     underlyingSymbolPrice, 
						                     strategy.getMovingAvgGracePoints(),
						                     strategy.getMovingAvgTolerancePct());
		if (allMovAvgs == null) return false;

		if (underlyingSymbolPrice.compareTo(allMovAvgs.loAvgMinus) < 0) {
			log.info("exiting put spread due to bear market.  underlyingSymbolPrice, loAvgMinus: " + underlyingSymbolPrice + ", " + allMovAvgs.loAvgMinus);
			return true;
		} else {
			log.info("NOT exiting put spread due to bear market.  underlyingSymbolPrice, loAvgMinus: " + underlyingSymbolPrice + ", " + allMovAvgs.loAvgMinus);
			return false;
		}
		
		
		//log.info("In exitPutInBearMarket.  underlyingSymbolPrice = " + underlyingSymbolPrice + ". BearMarketTrigger: = " + strategy.getBearMktTrigger());
		//return (strategy.isBearMkt(underlyingSymbolPrice, ticker));		
	}


	/**
	 * insertOptionEnterPricesIntoSpread
	 * 
	 * Iterates through the Option Chain and returns the correct Enter Prices into the spread
	 * 
	 * @param spread
	 * @param strategy
	 * @return Spread
	 */
	private Spread insertOptionEnterPricesIntoSpread(Spread spread, Position position, Strategy strategy)
	throws ThetaExceptionExc {
		
		String symbol = position.getSymbol();
		String putOrCall = position.getOptRight();
		Integer month = position.getExpiryMonth();
		Integer year = position.getExpiryYear();
		Integer currentSecurityPrice = spread.getCurrentSecurityPrice();

		log.info(stratName + "Here is spread: " + spread.getSpreadId() );
		log.info(stratName + "insertEnterPricesIntoSpread - Here is the expiry month: " + month);
				
		Integer distanceMultiplier = strategy.getDistanceBetOptions();
		log.info(stratName + "here is distanceMultiplier: " + distanceMultiplier);
		// TODO: Handle situations where the option prices are NOT 1 point
		// removed
		if (!(distanceMultiplier.equals(ThetaConstants.ONE_HUNDRED_CENTS)) || 
				(distanceMultiplier.equals(ThetaConstants.ONE_THOUSAND_CENTS))) {
			log.info(stratName + "The code is built for options with the distance between them of 100 or 1000 cents (strategy.distance_bet_options = 100 or 1000)");
			throw new ThetaExceptionExc(
			stratName + "Options must have a distance between them of 100 or 1000 cents");
		}

		// for PUTS - start just below the current security price - 
		// for CALLS - start just above the current security price
		// (out of the money options only)
		// The dividing and multiplying rounds DOWN
		log.info(stratName + "currentSecurityPrice: " + currentSecurityPrice);
		
		Integer initialPutPrice = (currentSecurityPrice/distanceMultiplier)*distanceMultiplier;
		Integer initialCallPrice = initialPutPrice + distanceMultiplier;
		log.info(stratName + "currentSecurityPrice : initialPut : initialCall : " + currentSecurityPrice + " : " + initialPutPrice + " : " + initialCallPrice );

		Integer totalRisk = strategy.getAmountTotalRisk();
		Integer riskPerPoint = strategy.getAmountRiskPerPoint();

		if (!riskPerPoint.equals(ThetaConstants.TEN_THOUSAND_CENTS)) {
			log.error(stratName + "strategy.risk_per_point must be 10,000 (cents).  Value: " + riskPerPoint);
			throw new ThetaExceptionExc(stratName + "strategy.risk_per_point must be 10,000 (cents).  Value: " + riskPerPoint);
		}
		
		if ( totalRisk.compareTo(riskPerPoint) < 0 ) {
			log.error(stratName + "Total Risk (" + totalRisk + ") is LESS than Risk Per Point (" + riskPerPoint + ").  Must be more.");
			throw new ThetaExceptionExc(stratName + "Total Risk (" + totalRisk + ") is LESS than Risk Per Point (" + riskPerPoint + ").  Must be more.");
		}
		
		
		int optPricesToGet = strategy.getOptPricesToGet().intValue();
		OptionPrice[] optPricesArray = new OptionPrice[optPricesToGet];
		Integer[] contractIdArray = new Integer[optPricesToGet];
		
		// iterate through strike prices
		for (Integer i = 0; i < optPricesToGet; i++) {

			Integer strike;
			if (putOrCall.equalsIgnoreCase(ThetaConstants.CALL)) {
				strike = (i * distanceMultiplier) + initialCallPrice;
			} else if (putOrCall.equalsIgnoreCase(ThetaConstants.PUT)) {
				strike = initialPutPrice - (i * distanceMultiplier);
			} else {
				String errMsg = stratName + "position - getOptRight must be P (PUT) or C (CALL).  It is currently: " + putOrCall + ".";
				log.error(errMsg);
				throw new ThetaExceptionExc(errMsg);
			}

			log.info(stratName + "Here is strike: " + strike);
			
			// Caching contract details
			String detailKey = symbol + month + year + strike + putOrCall;
			//client.reqOptionDetails(symbol, month, year, strike, putOrCall);

			ContractDetails det;
			det = client.getDetailCache().get(detailKey);
			if (det == null) {

				//thetaUtil.enterRecordIntoHeartbeat("CACHE: detailCache: adding:  " + detailKey);
				//Integer nextSeq = ThetaUtil.getNextRequestSeqNo(requestSeqDAO);
				client.reqOptionDetails(symbol, month, year, strike, putOrCall);

				// This must be here - to get the details of the Options
				ThetaUtil.secondsToSleepNoThrow(ThetaConstants.LONGER_SLEEP_TIME);

				det = client.getResp().getContractDetails();		
				client.getDetailCache().put(detailKey, det);

				log.info(stratName + "Here is ContractDetails: " + det.toString());
				
			} else {
				//thetaUtil.enterRecordIntoHeartbeat("CACHE: detailCache: found key - using: " + detailKey);
			}

			
			/*
			 * 
			 * 2011-04-19 16:34:13,853 INFO [pool-1-thread-1] ThetaMain - HEre is ContractDetails: ContractDetails [m_callable=false, m
			 * _category=Equity Fund, m_contractMonth=201107, m_convertible=false, m_coupon=0.0, m_industry=Funds, m_liquidHours=201104
			 * 19:0930-1615;20110420:0930-1615, m_longName=SPDR S&P 500 ETF TRUST, m_marketName=SPY, m_minTick=0.01, m_nextOptionPartia
			 * l=false, m_orderTypes=ACTIVETIM,ADJUST,ALERT,ALGO,ALLOC,AON,AVGCOST,BASKET,COND,CONDORDER,DAY,DEACT,DEACTDIS,DEACTEOD,FO
			 * K,GAT,GTC,GTD,GTT,HID,HPENNY,ICE,IOC,LIT,LMT,MIT,MKT,MTL,NONALGO,OCA,PAON,POSTONLY,RELSTK,SCALE,SCALERST,SMARTSTG,STP,ST
			 * PLMT,TRAIL,TRAILLIT,TRAILLMT,TRAILMIT,VOLAT,WHATIF,, m_priceMagnifier=1, m_putable=false, m_subcategory=Growth-Large Cap
			 * , m_summary=
			 * Contract [m_comboLegs=[]
			 * m_comboLegsDescrip=null
			 * ==> m_conId=85897652
			 * m_currency=USD
			 * m_exchange=SMART
			 * m_expiry=20110715
			 * m_includeExpired=false
			 * m_localSymbol=SPY   110716P00127000
			 * m_multiplier=100
			 * m_primaryExch=null
			 * m_right=P
			 * m_secId=null
			 * m_secIdType=null
			 * m_secType=OPT
			 * m_strike=127.0
			 * m_symbol=SPY
			 * m_underComp=null
			 * ], m_timeZoneId=EST, m_tradingClass=SPY, m_tradingHours=20110419:0930-1615;20110420:0930-1615, m_underConId=756733, m_va
			 * lidExchanges=SMART,AMEX,BATS,BOX,CBOE,CBOE2,IBSX,ISE,MIBSX,NASDAQOM,PHLX,PSE]
  			 */
			//private boolean isWithinDateAndTimeLimits(ContractDetails det, Strategy strategy, Integer minDelayFromStart, Integer minDelayFromEnd, boolean isToday)

			
			//log.info("insertEnterPricesIntoSpread - Here is the expiry date: " + det.m_summary.m_expiry);
			boolean timeAndDateOK = thetaUtil.isWithinDateAndTimeLimits(det, strategy, strategy.getMinDelayFromStart(), strategy.getMinDelayFromEnd(), false);
			if (!timeAndDateOK) {
				log.info(stratName + "Time and/or Date incorrect - cannot enter position");
				// TODO - REMOVE THIS!
				if (!ThetaConstants.DEBUG_FLAG){
					return null;
				} else {
					log.info(stratName + " *** This would have been stopped as TimeAndDate is wrong.  HOWEVER - DEBUG overrides! " );
				}
			}

			/**
			 * ENTER 
			 * TRY THREE TIMES
			 */
			OptionPrice optionPriceObj = new OptionPrice();
			
			
			for (int i2=0; i2 < ThetaConstants.NUM_TRIES_TO_GET_OPTION_PRICE; i2++){
				optionPriceObj = getOptionPrice(symbol, month, year, strike, putOrCall);
				
				if ( (optionPriceObj.getBid() <= ThetaConstants.ZERO_INT) || 
						(optionPriceObj.getAsk() <= ThetaConstants.ZERO_INT) || 
						(optionPriceObj.getLast() <= ThetaConstants.ZERO_INT) ){ 
					//thetaUtil.enterRecordIntoHeartbeat("ZERO came back for one of these: (BID|ASK|LAST): " + optionPriceObj.getBid() + "|" + optionPriceObj.getAsk() + "|" + optionPriceObj.getLast() );
					log.info(stratName + "NO price came back from the IB Client. Retrying...");
				} else { 
					//thetaUtil.enterRecordIntoHeartbeat("Price (BID|ASK|LAST): " + optionPriceObj.getBid() + "|" + optionPriceObj.getAsk() + "|" + optionPriceObj.getLast() );
					optPricesArray[i] = optionPriceObj;
					contractIdArray[i] = det.m_summary.m_conId;
					break;
				}
			}
			// Tried Three times - FAIL!

			if ( (optionPriceObj.getBid() <= ThetaConstants.ZERO_INT) || 
					(optionPriceObj.getAsk() <= ThetaConstants.ZERO_INT) || 
					(optionPriceObj.getLast() <= ThetaConstants.ZERO_INT) ){ 
				log.info(stratName + "A ZERO or negative price came back from the IB Client. Returning FALSE.");
				return null;
			}
						
			// i starts with 0, pointsDiffInt is absolute. That is, if
			// pointsDiffInt is 3, start checking when i is 2
			// i.e. total risk = 30000 ($300), risk per point = 10000($100) - points diff = 3
			Integer pointsDiff = totalRisk/riskPerPoint;
			log.info(stratName + "In insertEnterPricesIntoSpread. Here is pointsDiffInt:  " + pointsDiff);
			
			if (i > pointsDiff - 1) {
			//if (i > 1) {
				log.info(stratName + "In insertEnterPricesIntoSpread.  Here is i: " + i);
				
				Integer percentEnterHigh = strategy.getPercentEnterHigh();
				Integer percentEnterLow = strategy.getPercentEnterLow();

				Integer targetHigh = (totalRisk * percentEnterHigh) / ThetaConstants.ONE_HUNDRED_INT;
				Integer targetLow = (totalRisk * percentEnterLow) / ThetaConstants.ONE_HUNDRED_INT;

				log.info(stratName + "percentEnter High / Low : " + percentEnterHigh + "/" + percentEnterLow );
				log.info(stratName + "totalRisk / Target High / Low : " + totalRisk + " / " + targetHigh + " / " + targetLow);
				
				Integer candidateStrikeInsuranceCounter = i;
				Integer candidateStrikeMoneyMakerCounter = i - pointsDiff;
				
				log.info(stratName + "candidateStrikeMoneyMakerCounter = " + candidateStrikeMoneyMakerCounter);
				log.info(stratName + "candidateStrikeInsuranceCounter = " + candidateStrikeInsuranceCounter);
				log.info(stratName + "optPricesArray[candidateStrikeMoneyMakerCounter] = " + optPricesArray[candidateStrikeMoneyMakerCounter]);
				log.info(stratName + "optPricesArray[candidateStrikeInsuranceCounter] = " + optPricesArray[candidateStrikeInsuranceCounter]);
				
				/**
				 * SELL MoneyMaker - SELL High - SELL at ASK
				 * BUY Insurance - BUY Low - BUY at BID
				 */
				
				Integer optPriceDiff = optPricesArray[candidateStrikeMoneyMakerCounter].getAsk() - optPricesArray[candidateStrikeInsuranceCounter].getBid();
				
				log.info(stratName + "optPriceDiff: " + optPriceDiff
						+ ". Target High/Low: " + targetHigh + "/" + targetLow);

				// between high and low! Goldilocks. Bingo!
				if ( (optPriceDiff <= targetHigh) && (optPriceDiff >= targetLow)) {
					log.info(stratName + "Bingo - in goldilocks range.");

					if (putOrCall.equalsIgnoreCase(ThetaConstants.CALL)) {
						spread.setStrikeMoneymkr((candidateStrikeMoneyMakerCounter * ThetaConstants.ONE_HUNDRED_INT) + initialCallPrice);
						spread.setStrikeInsurance((candidateStrikeInsuranceCounter * ThetaConstants.ONE_HUNDRED_INT) + initialCallPrice);
					} else if (putOrCall.equalsIgnoreCase(ThetaConstants.PUT)) {
						spread.setStrikeMoneymkr(initialPutPrice - (candidateStrikeMoneyMakerCounter * ThetaConstants.ONE_HUNDRED_INT));
						spread.setStrikeInsurance(initialPutPrice - (candidateStrikeInsuranceCounter * ThetaConstants.ONE_HUNDRED_INT));
					} else {
						log.info(stratName + "putOrCall MUST be P or C.  Here it is: " + putOrCall);
						throw new ThetaExceptionExc(stratName + "putOrCall MUST be P or C.  Here it is: " + putOrCall);
					}
					
					spread.setEnterCommission(strategy.getComissionPerTrade());
					spread.setExitCommission(strategy.getComissionPerTrade());

					// Getting in now: SELL moneymaker (SELL HIGH), BUY insurance (BUY LOW)
					// Be stringent - don't need the purchase (we'll be less stringent with the exit)
					// Use ASK for the moneymaker - SELL HIGH
					// Use BID for the insurance - BUY LOW
										
					
					spread.setEnterMoneymkrPrice(optPricesArray[candidateStrikeMoneyMakerCounter].getBidPlus());
					//spread.setEnterMoneymkrDate(Calendar.getInstance());

					// TODO: Reset Insurance Price - We're BUYING insurance - ASKMINUS!
					spread.setEnterInsurancePrice(optPricesArray[candidateStrikeInsuranceCounter].getAskMinus());
					//spread.setEnterInsuranceDate(Calendar.getInstance());
					
					spread.setCurrentMoneymkrPrice(optPricesArray[candidateStrikeMoneyMakerCounter].getBidPlus());
					spread.setCurrentInsurancePrice(optPricesArray[candidateStrikeInsuranceCounter].getAskMinus());
					spread.setCurrentDate(Calendar.getInstance());
										
					spread.setMoneymkrPositionId(contractIdArray[candidateStrikeMoneyMakerCounter].toString());
					spread.setInsurancePositionId(contractIdArray[candidateStrikeInsuranceCounter].toString());
					
					spread.setExitAboveSpreadValue(0);
					spread.setExitBelowSpreadValue(0);

					//thetaUtil.enterRecordIntoHeartbeat("AT end of setting up the spread.");
					// TODO: set stops - absolute and trailing
					// setAbsoluteStops
					// setTrailingStops

					return spread;
				}  //if ( (optPriceDiff <= targetHigh) && (optPriceDiff >= targetLow)) {
				// below the low - never got into the "goldilocks" range - try
				// again ...
				if (optPriceDiff.compareTo(targetLow) < ThetaConstants.ZERO_FL) {
					log.info(stratName + "Fail - never made it to goldilocks range.");
					return null;
				}
			} // if (i > pointsDiff - 1) {
		} // for (Integer i = 0; i < optPricesToGet; i++) {
		return spread;
	}

	/**
	 * Hail Mary Exit
	 * Rules:
	 * 1. SPREAD Qualifies if:
	 * - Expires within one month
	 * - is a PUT
	 * - Is at 75% or more of total loss
	 * 
	 * 2. MARKET qualifies if:
	 * - Down at least 2% from yesterday's closing
	 * - Down at least 0.5% in last half hour
	 * - Before 1:00 PM
	 * 
	 * 3. Exit MONEYMAKER
	 * 4. INSURANCE:
	 * - Put stop-limit at 4 (1/3 over total loss)
	 * - Put limit at breakeven (+ a little to show gain)
	 * 
	 */

	protected Spread hailMaryExit(Strategy strategy, Spread spread, Position position, Integer currPrice)
	throws ThetaExceptionExc {
		log.info("In hailMaryExit");

		thetaUtil.enterRecordIntoHeartbeat("HAILMARY - At top. Spread ID: " + spread.getSpreadId() );
		/**
		 * Hail Mary:
		 * - Check that it's down "N1" percent from yesterday morning
		 * - Check that it's down "N2" percent from this morning
		 * - Check that it's down "N3" percent within the last 90 minutes
		 * - Get rid of Moneymaker immediately
		 * - For Insurance - 1 point up, 3 point down
		 * 
		 */

		String theTicker = strategy.getSymbol();
		Integer targetDownFromYest = strategy.getHmPctBpDownFromYest();
		Integer targetDownFromToday = strategy.getHmPctBpDownFromToday();
		Integer targetDownInLast90 = strategy.getHmPctBpDownInLast90();
		Integer daysToExpiry = strategy.getHmDaysToExpiry();
		Integer bpLimitUp = strategy.getHmBpLimitUp(); 
		// CLUDGE - to get around bug in Strategy class!
		//Integer bpLimitUp = 300; 
		Integer bpStopLimitDown = strategy.getHmBpStopLimitDown();

		/*
		 * Make sure constants are OK
		 */
		if ((targetDownFromYest.equals(ThetaConstants.ZERO_INT) 
				|| targetDownFromToday.equals(ThetaConstants.ZERO_INT)
				|| targetDownInLast90.equals(ThetaConstants.ZERO_INT)
				|| daysToExpiry.equals(ThetaConstants.ZERO_INT)
				|| bpLimitUp.equals(ThetaConstants.ZERO_INT)
				|| bpStopLimitDown.equals(ThetaConstants.ZERO_INT))){
			log.info("one of the strategy params is zero or null - can't check for a Hail Mary Pass!");
			return null;
		}
		/*
		 * PUTS ONLY!
		 */
		if (position.getOptRight().equalsIgnoreCase(OptRightCode.C.getValue())){
			log.info("For a Hail Mary Pass, is MUST be a PUT BULL SPREAD - no CALLS!");
			return null;
		}
		/*
		 * Correct dates
		 */
		Calendar hailMaryPassAfter = position.getExpiryDate();
		hailMaryPassAfter.add(Calendar.DAY_OF_YEAR, -daysToExpiry);
		Calendar today = Calendar.getInstance();
		if (hailMaryPassAfter.compareTo(today) >= 0){
			log.info("Date is not within range for Hail Mary Pass.  After Date: " + hailMaryPassAfter.getTime());
			return null;
		}

		
		// No Hail Mary 3rd Friday of the month or day before!
		Calendar cal = Calendar.getInstance();
		// grrrr - Calendar cludge - must add a month (January is 0!!)
		Integer theMonth = cal.get(Calendar.MONTH) + ThetaConstants.ONE_INT;
		Integer theYear = cal.get(Calendar.YEAR);
		Calendar thirdFriday = DateUtil.getThirdFridayCal(theMonth, theYear);
		Calendar thirdFridayMinusOne = DateUtil.getThirdFridayCal(theMonth, theYear);
		thirdFridayMinusOne.add(Calendar.DAY_OF_YEAR, -1);
		if (DateUtil.isSameDay(today, thirdFriday) || (DateUtil.isSameDay(today, thirdFridayMinusOne))) {
			log.info("Third friday or day before that.  DO NOT HAIL MARY TODAY!");
			return  null;
		}
		
		//if (DateUtil.isSameDay(today, thirdFriday) || (DateUtil.isSameDay(today, thirdFridayMinusOne))) {
		//	log.info("Third friday or day before that.  DO NOT HAIL MARY TODAY!");
		//	return  null;
		//}
		
		/**
		 * Check down from yesterday morning, this morning, high and low of last 90 minutes 
		 */
		Calendar yesterday = securityPriceDAO.findLastBusinessDay().getCreatedDate();
		Integer priceYest = securityPriceDAO.findDayOpenPriceByDateAndTicker(yesterday, theTicker).getPrice();
		Integer priceToday = securityPriceDAO.findDayOpenPriceByDateAndTicker(Calendar.getInstance(), theTicker).getPrice();

		Calendar last90 = Calendar.getInstance();
		last90.add(Calendar.MINUTE, -90);
		Integer minPriceLast90 = securityPriceDAO.findMinOrMaxPriceSinceDateTime(last90, theTicker, MinOrMaxCode.MIN);
		Integer maxPriceLast90 = securityPriceDAO.findMinOrMaxPriceSinceDateTime(last90, theTicker, MinOrMaxCode.MAX);

		log.info("About to check percent down from Yesterday morning, this morning, and last 90 minutes");
		/**
		 *  Percent down from yesterday, today, high/low of last 90 minutes (in basis points)
		 *  
		 *  example:
		 *  yesterday = 100
		 *  current = 97.5
		 *  target percent (bp) = 200 bp
		 *  (100 - 97.5)/100 = 2.50 = 250 bp ==> passes
		 */
		thetaUtil.enterRecordIntoHeartbeat("HAILMARY: priceYest | priceToday | maxLast90 | minLast90 | targets:" + priceYest + " | " + priceToday + " | " + maxPriceLast90 + " | " + minPriceLast90 +
				" | " + targetDownFromYest + " | " + targetDownFromToday + " | " + targetDownInLast90);
		log.info("HAILMARY: priceYest | priceToday | maxLast90 | minLast90 | targets:" + priceYest + " | " + priceToday + " | " + maxPriceLast90 + " | " + minPriceLast90 +
				" | " + targetDownFromYest + " | " + targetDownFromToday + " | " + targetDownInLast90);

		thetaUtil.millisToSleep(200);
		

		Calendar now1 = Calendar.getInstance();
		Calendar todayEleven = Calendar.getInstance();
		todayEleven.set(now1.get(Calendar.YEAR), now1.get(Calendar.MONTH), now1.get(Calendar.DAY_OF_MONTH),11,0,0);
		Calendar todayNoon = Calendar.getInstance();
		todayNoon.set(now1.get(Calendar.YEAR), now1.get(Calendar.MONTH), now1.get(Calendar.DAY_OF_MONTH),12,0,0);
				
		if (!thetaUtil.isActualDiffMoreThanTarget(priceYest, minPriceLast90, targetDownFromYest, "Down past limit from yeterday morning."))
			return null;
		if (!thetaUtil.isActualDiffMoreThanTarget(priceToday, minPriceLast90, targetDownFromToday, "Down past limit from this morning."))
			return null;
		if (!thetaUtil.isActualDiffMoreThanTarget(maxPriceLast90, minPriceLast90, targetDownInLast90, "Down past limit between min and max of last 90 minutes."))
			return null;
		
		Calendar now = Calendar.getInstance();
		Calendar todayMidnight = Calendar.getInstance();
		todayMidnight.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH),0,0,0);
		Calendar spreadMoneymkrDate = spread.getEnterMoneymkrDate();

		if (spreadMoneymkrDate == null) {
			log.error("Can't HailMary enterMoneymkrDate is NULL!");
			return null;
		}
		
		if (spreadMoneymkrDate.after(todayMidnight)){
			log.info("Can't HailMary enterMoneymkrDate is today: " + spread.getEnterMoneymkrDate().getTime());
			return null;
		}
		
		thetaUtil.enterRecordIntoHeartbeat("HAILMARY!  The sky is falling - executing ..." + spread.getSpreadId());

		spread.setOpenOrClosed(OpenOrClosedCode.HAILMARY.getValue());

		spread.setExitSecurityPrice(spread.getCurrentSecurityPrice());
		spread.setExitSecurityDate(Calendar.getInstance());

		spread.setExitMoneymkrPrice(spread.getCurrentMoneymkrPrice());
		spread.setExitInsurancePrice(spread.getCurrentInsurancePrice());
		spread.setExitTriggerDate(Calendar.getInstance());

		/**
		 * Set Request Sequence Numbers (3 orders - 3 sequence numbers0
		 */
		// original - set to 0
		spread.setRequestSeqNo(ThetaConstants.ZERO_INT);

		Integer hmMmReqno = client.getResp().getNextValidId();
		spread.setHmMmReqno(hmMmReqno);

		Integer hmInsReqnoLimitUp = hmMmReqno + ThetaConstants.ONE_INT;
		spread.setHmInsReqnoLimitUp(hmInsReqnoLimitUp);

		Integer hmInsReqnoStplimDown = hmInsReqnoLimitUp + ThetaConstants.ONE_INT;		
		spread.setHmInsReqnoStplimDown(hmInsReqnoStplimDown);

		log.info("request numbers (reqno, limitup, limDown): " + hmMmReqno + ":" + hmInsReqnoLimitUp + ":" + hmInsReqnoStplimDown );
		
		client.getResp().setNextValidId(hmInsReqnoStplimDown + ThetaConstants.ONE_INT);
		/*
		 *  OK - HERE GOES - HOLD YOUR BREATH AND THROW ...
		 */

		// OCA = One Cancels All
		String ocaGroupMM = "OCA" + hmMmReqno.toString();
		String ocaGroupIns = "OCA" + hmInsReqnoLimitUp.toString();

		try {
			/*
			 * STEP 1 - GET RID OF MONEYMAKER AT MARKET
			 */
			// SELL LOW in this case - we want to get rid of the moneymkr right away...
			OptionPrice currentMoneymkrPriceObj = getOptionPrice(position.getSymbol(), 
					position.getExpiryMonth(), 
					position.getExpiryYear(), 
					spread.getStrikeMoneymkr(), 
					position.getOptRight());

			Double moneymkrSalePrice = (double) currentMoneymkrPriceObj.getAskPlus();
			log.info("HAIL MARY! - Here is moneymkrSalePrice: " + moneymkrSalePrice);
			// Get rid of moneymaker right away!
			boolean mmReturn = client.excerciseIndividualOption(
					position.getSymbol(), 
					LmtOrStpLmtCode.LMT,    
					BuyOrSellCode.BUY, 
					position.getExpiryMonth(), 
					position.getExpiryYear(), 
					spread.getStrikeMoneymkr(), 
					position.getOptRight(), 
					moneymkrSalePrice, 
					ThetaConstants.ZERO_DOUBLE, 
					ocaGroupMM, 
					hmMmReqno);


			if (mmReturn){
				log.info("HAIL MARY! - mmReturn is TRUE");
				// Set LOW (Stop-Limit) price on Insurance
				OptionPrice currentInsurancePriceObj = getOptionPrice(position.getSymbol(), 
						position.getExpiryMonth(), 
						position.getExpiryYear(), 
						spread.getStrikeInsurance(), 
						position.getOptRight());
				Integer currentInsurancePrice = currentInsurancePriceObj.getBidAskHalf();

				/*
				 * STEP 2 - SET HIGH LIMIT (UP) ON INSURANCE - THIS IS WHERE THE
				 *  $$ COMES IN!
				 */
				Double insSaleLimitHi = (double) (currentInsurancePrice +  bpLimitUp);
				log.info("HAIL MARY! - Here is insSaleLimitHi: " + insSaleLimitHi);
				client.excerciseIndividualOption(
						position.getSymbol(), 
						LmtOrStpLmtCode.LMT, 
						BuyOrSellCode.SELL, 
						position.getExpiryMonth(), 
						position.getExpiryYear(), 
						spread.getStrikeInsurance(), 
						position.getOptRight(), 
						insSaleLimitHi, 
						ThetaConstants.ZERO_DOUBLE, 
						ocaGroupIns, 
						hmInsReqnoLimitUp);

				/*
				 * STEP 3 - SET LOW LIMIT (DOWN) ON INSURANCE - PROTECTION
				 * 
				 * Example:
				 * --------
				 * current price - 140  bp
				 * 
				 * set stopLimit - 70 bp 
				 * once hit, 
				 * limit is      - 60 bp (i.e. SELL ABOVE THIS once stopLimit is hit)
				 */
				Double insSaleStopLimitLo = (double) (currentInsurancePrice - bpStopLimitDown);
				Double insSaleLimitLo = insSaleStopLimitLo - ThetaConstants.BP_BET_STOP_AND_LIMIT_DOUBLE;
				log.info("HAIL MARY! - Here is insSaleLimitLo: " + insSaleLimitLo);
				log.info("HAIL MARY! - Here is insSaleStopLimitLo: " + insSaleStopLimitLo);
				spread.setHmMmInsHiLoExitPrices("MMSALE: " + moneymkrSalePrice.intValue() + "; INSSALEHI: " + insSaleLimitHi.intValue() + ", INSSALELO: " + insSaleLimitLo.intValue() );
				
				if (insSaleStopLimitLo > 0) {
					client.excerciseIndividualOption(
						position.getSymbol(), 
						LmtOrStpLmtCode.STPLMT, 
						BuyOrSellCode.SELL, 
						position.getExpiryMonth(), 
						position.getExpiryYear(), 
						spread.getStrikeInsurance(), 
						position.getOptRight(), 
						insSaleLimitLo, 
						insSaleStopLimitLo, 
						ocaGroupIns, 
						hmInsReqnoStplimDown);
				}
			} else {
				log.error("HAIL MARY PASS Unsuccessful.  Problem getting rid of MoneyMaker!  NOTHING done." );
			}

			/*
			 * STEP 4 - VERIFICATION!
			 */

			/*
			 * 
			 * NO VERIFICATION -
			 * For spreads, if they don't go through, they can be rolled back (at a high level).
			 * For the Hail Mary Pass, a rollback is much more difficult, and could end up with a 
			 * race condition - one piece doesn't go through immediately, it's rolled back, then 
			 * it goes through, etc.  
			 * 
			 * So, no immediate verification.  The code will, however, get the exit prices.
			 */

		} catch (Exception e) {
			log.info("In enterSpreads: THROWING EXCEPTION!");
			throw new ThetaExceptionExc(e);
		} // try catch

		// May already be in order status - that is, execution is confirmed.
		// if so - this will set the spread to CLOSED 
		ThetaUtil.secondsToSleepNoThrow(ThetaConstants.TEN_INT);
		
		thetaUtil.setLastFillPriceFromOrderStatus(spread, position, spreadDAO, client);
		
		spread.setUpdatedBy("HailMaryPass");
		spread.setUpdatedDate(Calendar.getInstance());

		return spread;
	}

	
	

	
	
	/**
	 *  1. (LOSS) EXIT AT INSURANCE
	 *  CALL Spread:
	 *  	Underlying Price is ABOVE Insurance (Moneymkr: 130, Insurance: 133, Underlying MORE THAN 133)
	 *  PUT Spread:
	 *  	Underlying Price is ABOVE Insurance (Moneymkr: 127, Insurance: 124, Underlying LESS THAN 124)
	 *  
	 */
	private Spread exitAtInsurance(Strategy strategy, Spread spread, Position position, Integer underlyingSymbolPrice) 
	throws ThetaExceptionExc {

		if (ThetaUtil.trueFalseToBoolean(strategy.getSetExitAtInsPrice())){

			if (position.getOptRight().equals(OptRightCode.C.toString())){
				if (underlyingSymbolPrice >= spread.getStrikeInsurance()){
					log.info("Bingo.  LOSS AT INSURANCE!" + spread.toStringShort());
					thetaUtil.enterRecordIntoHeartbeat("EXITING: underlyingSimbolPrice >= currentInsurancePrice and CALL");
					spread = positionExecutor.exitSingleSpread(spread, position, strategy, MovingSpeedCode.SLOW, "exitAtInsurance-LOSS", client, dbAccessMutex);
					return spread;
				}
			} else if (position.getOptRight().equals(OptRightCode.P.toString())){
				if (underlyingSymbolPrice <= spread.getStrikeInsurance()){
					log.info("Bingo.  LOSS AT INSURANCE!" + spread.toStringShort());
					thetaUtil.enterRecordIntoHeartbeat("EXITING: underlyingSimbolPrice <= currentInsurancePrice and PUT");
					spread = positionExecutor.exitSingleSpread(spread, position, strategy, MovingSpeedCode.SLOW, "exitAtInsurance-LOSS", client, dbAccessMutex);
					return spread;
				}
			} else {
				throw new ThetaExceptionExc("position.getOptRight MUST be C or P.  It is instead:" + position.getOptRight());
			}

		} // if (ThetaUtil.trueFalseToBoolean(strategy.getSetExitAtInsPrice())){
		return null;
	}
	

	/**
	 *  2. EXIT AT BREAKEVEN 
	 *    (Execute if STRATEGY:SET_BREAKEVEN_WHEN_INS_HIT is True)
	 *     Once the value of the UNDERLYING SECURITY is directly under INSURANCE Option, 
	 *     set the value of SPREAD: EXIT_BELOW_SPREAD_VALUE to a breakeven value.
	 */     
	private Spread exitAtBreakeven(Strategy strategy, Spread spread, Position position, 
			Integer currentInsurancePrice, Integer underlyingSymbolPrice, Integer currentSpreadPrice, Integer entryRevenue) 
	throws ThetaExceptionExc {

		if (ThetaUtil.trueFalseToBoolean(strategy.getSetBreakevenWhenInsHit())){

			/**
			 * Insurance has been hit.  Price is coming down.  Cut losses
			 * and get out.
			 */
			if (spread.getExitBelowSpreadValue() > 0) {
				if (currentSpreadPrice <= spread.getExitBelowSpreadValue()){
					thetaUtil.enterRecordIntoHeartbeat("EXITING: currentSpreadPrice <= spread.getExitBelowSpreadValue()");
					spread = positionExecutor.exitSingleSpread(spread, position, strategy, MovingSpeedCode.SLOW, "exitAtBreakeven - TIE",  client, dbAccessMutex);
					return spread;
				}
			}

			/**
			 * At this point, the underlying symbol price is equal to or exceeding
			 * the Insurance price.  SET UP the exit below spread value field.
			 */
			if (position.getOptRight().equals(OptRightCode.C.toString())){
				if (underlyingSymbolPrice >= currentInsurancePrice){
					spread.setExitBelowSpreadValue(entryRevenue);
				}
			} else if (position.getOptRight().equals(OptRightCode.P.toString())){
				if (underlyingSymbolPrice <= currentInsurancePrice){
					spread.setExitBelowSpreadValue(entryRevenue);
				}
			} else {
				throw new ThetaExceptionExc("position.getOptRight MUST be C (CALL) or P (PUT) .  It is instead:" + position.getOptRight());
			}
			spread.setUpdatedBy("exitAtBreakeven");
			spread.setUpdatedDate(Calendar.getInstance());
			//spreadDAO.store(spread);
			//spreadDAO.flush();
		} // if (ThetaUtil.trueFalseToBoolean(strategy.getSetBreakevenWhenInsHit())){
		return null;
	}
	
	/**
     * #3F. CATCH-ALL EXIT RULES:
     * ---------------------
     *   EXIT at 12:00 NOON on DAYS_EXIT_BEFORE_EXPIRY before the expiry date of the spread.  
     *   That is, if the Expiry date is Friday, September 17th, and the DAYS_EXIT_BEFORE_EXPIRY 
     *   is set to 4, exit at 12:00 NOON on Monday, September 13th.
	 */
	private Spread catchAllExit(Strategy strategy, Spread spread, Position position)
	throws ThetaExceptionExc {

		log.info("Top of catchAllExit!");
		Integer daysBeforeExpiry = strategy.getDaysExitBeforeExpiry();
		if ((daysBeforeExpiry < ThetaConstants.ONE_INT) || (daysBeforeExpiry > ThetaConstants.TEN_INT)) {
			throw new ThetaExceptionExc("strategy:daysExitBeforeExpiry MUST be between 1 and 10 days!!!");
		}
		Integer expiryMonth = position.getExpiryMonth();
		// grrr - have to subtract one - grrrr
		// JANUARY is 0, DECEMBER is 11
		Integer expiryMonthCalNotation = expiryMonth - ThetaConstants.ONE_INT;
		if ( (expiryMonthCalNotation < Calendar.JANUARY) || (expiryMonthCalNotation > Calendar.DECEMBER) ) {
			throw new ThetaExceptionExc("ExpiryMonth MUST be between 1 (JANUARY) and 12 (DECEMBER).  It is: " + expiryMonth);
		}

		Integer expiryDay = position.getExpiryDay();
		Integer sellByDay = expiryDay - daysBeforeExpiry; 
		if ( sellByDay < ThetaConstants.ONE_INT ){
			throw new ThetaExceptionExc("ExpiryDay minus DaysBeforeExpiry must be 1 or more.  Here they are: Day | DaysBefore : " + expiryDay + " | " + daysBeforeExpiry);
		}
		
		Calendar sellByDateTime = Calendar.getInstance();
		sellByDateTime.set(position.getExpiryYear(), 
						expiryMonthCalNotation, 
						sellByDay, 
						ThetaConstants.NOON_INT,
						ThetaConstants.ZERO_INT,
						ThetaConstants.ZERO_INT);

		Calendar now = Calendar.getInstance();
		log.info("Now | SellByDateTime: " + now.getTime() + " | " + sellByDateTime.getTime());
		
		if (now.after(sellByDateTime)){
			thetaUtil.enterRecordIntoHeartbeat("EXITING: now | after(sellByDateTime) : " + now.getTime() + " : " + sellByDateTime.getTime());
			log.info("EXITING: now | after(sellByDateTime) : " + now.getTime() + " : " + sellByDateTime.getTime());
			spread = positionExecutor.exitSingleSpread(spread, position, strategy, MovingSpeedCode.SLOW, "afterSellByDate", client, dbAccessMutex);
			return spread;
		} // if (now.after(sellByDateTime)){

		/**
		 * OK - lastly.  The expiry date has passed but the option did not sell.  CLOSE - assume current price.
		 */
		if ( (spread.getOpenOrClosed().equals(OpenOrClosedCode.OPEN.toString())) ||
				(spread.getOpenOrClosed().equals(OpenOrClosedCode.PENDCLOSE.toString())) ||
				(spread.getOpenOrClosed().equals(OpenOrClosedCode.PENDCLSCNF.toString()))){
			
			Calendar expiryDate = Calendar.getInstance();
			expiryDate.set(position.getExpiryYear(), 
					expiryMonthCalNotation, 
					expiryDay, 
					ThetaConstants.NOON_INT,
					ThetaConstants.ZERO_INT,
					ThetaConstants.ZERO_INT);
			boolean isWithinTimeLimits = thetaUtil.isWithinTimeLimits(spread, position, client);
			// At this point, we are set to OPEN, PENDCLOSE, PENDCLSCNF AND
			// it's the expiry (drop dead!) date and without time limits
			if (now.after(expiryDate) && !isWithinTimeLimits) {

				spread.setOpenOrClosed(OpenOrClosedCode.CLOSED.toString());
				spread.setExitSecurityPrice(spread.getCurrentSecurityPrice());
				spread.setExitSecurityDate(Calendar.getInstance());
				spread.setProfitLossRealized(spread.getProfitLossUnrealized());
				//spread.setProfitLossUnrealized(ThetaConstants.ZERO_INT);
				spread.setExitMoneymkrPrice(spread.getCurrentMoneymkrPrice());
				spread.setExitInsurancePrice(spread.getCurrentInsurancePrice());
				spread.setExitConfirmDate(Calendar.getInstance());
				spread.setExitTriggerDate(Calendar.getInstance());

				thetaUtil.enterRecordIntoHeartbeat("EXITING MONEYMKR: " + position.getOptRight() + ":" + spread.getStrikeMoneymkr() + ":" + spread.getCurrentMoneymkrPrice());
				thetaUtil.enterRecordIntoHeartbeat("EXITING INSURANCE: " + position.getOptRight() + ":" + spread.getStrikeInsurance() + ":" + spread.getCurrentInsurancePrice() );
				
				spread.setUpdatedBy("catchAllExit");
				spread.setUpdatedDate(Calendar.getInstance());
				//spreadDAO.store(spread);
				//spreadDAO.flush();
				return spread;
			}
		}
		
		return null;
	}

	/**
	 * Check if there are enough funds in the account to continue to enter positions.
	 * 
	 * @param netLiquidation
	 * @param availableFunds
	 * @param stopAtMarginPct
	 * @return boolean
	 * 
	 * For example:
	 * NetLiquidation, 91995.20
	 * LookAheadAvailableFunds, 32350.67
	 * 
	 */
	private boolean enoughFundsToEnterPosition(String netLiquidation, String availableFunds, Integer stopAtMarginPct) {
		
		log.info("In enoughRundsToEnterPosition: netLiquidation, availableFunds, stopAtMarinPct: " + netLiquidation + ", " + availableFunds + ", " + stopAtMarginPct);
		Float netLiquidationF = new Float(netLiquidation); 
		Float availableFundsF = new Float(availableFunds);
		Float stopAtMarginPctF = new Float(stopAtMarginPct);
		Float marginPct = (availableFundsF * ThetaConstants.ONE_HUNDRED_FLOAT) / netLiquidationF;
		
		return (marginPct.compareTo(stopAtMarginPctF) > 0 ); 
	}
	
	
}

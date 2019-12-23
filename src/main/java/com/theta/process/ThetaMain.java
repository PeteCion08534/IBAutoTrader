package com.theta.process;

import java.io.File;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.theta.client.DateUtil;
import com.theta.client.OpenOrder;
import com.theta.client.OrderStatus;
import com.theta.client.PortfolioItem;
import com.theta.client.PositionExecutor;
import com.theta.client.PriceRetriever;
import com.theta.client.ThetaClientInterface;
import com.theta.dao.IbAccountDAO;
import com.theta.dao.PositionDAO;
import com.theta.dao.ProfitLossDAO;
import com.theta.dao.RequestSeqDAO;
import com.theta.dao.SecurityPriceDAO;
import com.theta.dao.SpreadDAO;
import com.theta.dao.StrategyAccountDAO;
import com.theta.dao.StrategyDAO;
import com.theta.domain.IbAccount;
import com.theta.domain.Position;
import com.theta.domain.ProfitLoss;
import com.theta.domain.Spread;
import com.theta.domain.Strategy;
import com.theta.domain.StrategyAccount;
import com.theta.enums.InstrumentCode;
import com.theta.enums.OpenOrClosedCode;
import com.theta.externaldata.BackPrices;
import com.theta.externaldata.EarningsDate;

/**
 * @author pcion
 *
 */
@Service
public class ThetaMain {

	private static Logger log = Logger.getLogger(ThetaMain.class);
	private ThetaClientInterface client;
	private ThetaUtil thetaUtil;
	private PositionDAO positionDAO;
	private SpreadDAO spreadDAO;
	private StrategyAccountDAO strategyAccountDAO;
	private StrategyDAO strategyDAO;
	private ProfitLossDAO profitLossDAO;
	private IbAccountDAO ibAccountDAO;
	private RequestSeqDAO requestSeqDAO;
	private SecurityPriceDAO securityPriceDAO;
	//private MovingAverages movingAverages;
	private EarningsDate earningsDate;
	//private Slope slope;
	private List<Calendar> holidayList;
	private PriceRetriever priceRetriever;
	private PositionExecutor positionExecutor;
	private ExecutorService executorService;
	private ThetaMutex dbAccessMutex;
	
	protected ThetaMain(){}
	
	protected ThetaMain(
			PositionDAO positionDAO,
			SpreadDAO spreadDAO,
			StrategyAccountDAO strategyAccountDAO,
			StrategyDAO strategyDAO,
			RequestSeqDAO requestSeqDAO,
			ProfitLossDAO profitLossDAO,
			IbAccountDAO ibAccountDAO,
			SecurityPriceDAO securityPriceDAO,
			ThetaUtil thetaUtil,
			ThetaClientInterface client,
			EarningsDate earningsDate,
			List<Calendar> holidayList,
			PriceRetriever priceRetriever,
			PositionExecutor positionExecutor,
			ExecutorService executorService
			) {
		
		this.positionDAO  = positionDAO;
		this.spreadDAO  = spreadDAO;
		this.strategyAccountDAO  = strategyAccountDAO;
		this.strategyDAO = strategyDAO;
		this.requestSeqDAO = requestSeqDAO;
		this.profitLossDAO = profitLossDAO;
		this.ibAccountDAO = ibAccountDAO;
		this.securityPriceDAO = securityPriceDAO;
		this.thetaUtil = thetaUtil;
		this.client = client;
		this.earningsDate = earningsDate;
		this.holidayList = holidayList;
		this.priceRetriever = priceRetriever;
		this.positionExecutor = positionExecutor;
		this.executorService = executorService;
	}
	
	/**
	 * run
	 * @param thetaUtil
	 * @return boolean
	 * @throws ThetaExceptionExc
	 */
	protected boolean main()
	throws ThetaExceptionExc, DataAccessException {
		
		log.info("TOP of ThetaMain - run()");
		
		//Calendar runStart = Calendar.getInstance();
		
		Calendar now = Calendar.getInstance();
		Calendar runStartTime = Calendar.getInstance();
		Calendar runEndTime = Calendar.getInstance();

		Calendar tradeStartTime = Calendar.getInstance();
		Calendar tradeEndTime = Calendar.getInstance();

		runStartTime.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), ThetaConstants.NINE_OCLOCK_AM, new Integer(30));
		runEndTime.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), ThetaConstants.THREE_OCLOCK_PM, new Integer(59));
		//runEndTime.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 23, new Integer(59));
		
		/**
		 * Trade Window
		 * start at 9:47 - after the initial ups and downs of the day
		 * End promptly at 3:57
		 */
		tradeStartTime.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), ThetaConstants.NINE_OCLOCK_AM, new Integer(47));
		tradeEndTime.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), ThetaConstants.THREE_OCLOCK_PM, new Integer(55));

		if (!client.isProd()) {
			runStartTime.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), ThetaConstants.ONE_OCLOCK_AM, ThetaConstants.ZERO_INT);
			runEndTime.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), ThetaConstants.ELEVEN_OCLOCK_PM, ThetaConstants.THIRTY_INT);
			tradeStartTime.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), ThetaConstants.ONE_OCLOCK_AM, ThetaConstants.ZERO_INT);
			tradeEndTime.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), ThetaConstants.ELEVEN_OCLOCK_PM, ThetaConstants.THIRTY_INT);
		}

		if (!(now.after(runStartTime) && now.before(runEndTime))){
			thetaUtil.enterRecordIntoHeartbeat("Too early or late - pausing returning.  Time: " + now.getTime() );
			for (int i=30; i>0; i--){
				log.info("Seconds to pause: " + i);
				thetaUtil.secondsToSleep(ThetaConstants.ONE_INT);
			}

			return false;
		}
				
		// *********************************************************************************************/
		// ******************* SET UP MUTEX ************************************************************/
		// *********************************************************************************************/
		// DB entityManager is not threadsafe
		dbAccessMutex = new ThetaMutex();
		/**
		 * Iterate through ALL active strategy-accounts
		 */
		Set<StrategyAccount> strategyAccounts = strategyAccountDAO.findAllStrategyAccounts();
		for(StrategyAccount strategyAccount : strategyAccounts){


			IbAccount ibAccount = (IbAccount) strategyAccount.getIbAccount();
			Strategy strategy = (Strategy) strategyAccount.getStrategy();
			
			String stratName = strategy.getStrategyName() + ": ";
			
			if ( now.after(tradeStartTime) && now.before(tradeEndTime) ){
				client.setGlobalCanEnterFlag(true);
				client.setCanExitFlag(true);
				log.info(stratName + "Running and within trading window.");
			} else {
				client.setGlobalCanEnterFlag(false);
				client.setCanExitFlag(false);
				log.info(stratName + "Running - but OUTSIDE trading window.");
			}

			// **************************************************************************
			// ********** EARNINGS DATE *************************************************
			// **************************************************************************
			// Work on earningsDate before trading is active so as not to impact trading
			if (now.before(tradeStartTime)) {
				Calendar earningsReportCal = strategy.getEarningsReportNext();
				log.info(stratName + "EarningsReport Date: [" + strategy.getSymbol() + "]  " + earningsReportCal.getTime());
					try {
						Calendar futureEarningsDate = earningsDate.getFutureEarningsDateForSymbol(strategy.getSymbol(), holidayList);
						
						log.info(stratName + "Symbol, futureEarningsDate: " + strategy.getSymbol() + ", " + futureEarningsDate);
						if (futureEarningsDate != null) {
							strategy.setEarningsReportNext(futureEarningsDate);
							
							synchronized(dbAccessMutex) {strategyDAO.synchronizedStoreAndFlush(strategy);}
						}
					} catch (Exception ex) {
						log.error(stratName + "Caught error in earningsDate: ", ex);
					}
			}

			
			try{
				log.info(stratName + "Top of strategyAccount loop. ");

				/**
				 * Record Keeping - at top.
				 * Roll up profit and loss - realized and unrealized from all spreads through to position.
				 * At the top of each hour - write to the profit_loss table
				 */
				try {
					rollUpRecordKeeping(strategyAccount, dbAccessMutex);
				} catch (NullPointerException e2){
					log.error(stratName + "CAUGHT nullPointerExcepton in rollUpRecordKeeping : ", e2);
				}

				ThetaUtil.secondsToSleepNoThrow(1);

				Set<Position> positions = null;

				if (strategy.getActiveFlag().equals(ThetaConstants.TRUE)){

					ThetaConstants.STRATEGY_SLEEP_TIME_MS = strategy.getMsWaitForIbResponse();

					if (!connectClient(ibAccount)){
						log.info(stratName + "Can't connect to the following account. Breaking loop: " + ibAccount.getIbAccountIdExt());
						ThetaUtil.secondsToSleepNoThrow(ThetaConstants.THREE_INT);
						throw new ThetaExceptionExc(stratName + "Can't connect to client: " + ibAccount.getIbAccountIdExt());
					} else {
						log.info(stratName + "Connected to IB Client.");
						ThetaUtil.secondsToSleepNoThrow(ThetaConstants.TWO_INT);
					}

					Set<OrderStatus> orderStatusCopy = new HashSet<OrderStatus>(client.orderStatuses); 
					for(OrderStatus orderStatus : orderStatusCopy){
						log.info(stratName + "Here is the ORDER STATUS: " + orderStatus.toString());
					}
					synchronized (dbAccessMutex) {positions = positionDAO.findPositionsByStrategyAcctId(strategyAccount.getStrategyAccountId());}

					/*********************************************************************************************/
					/***** ADD NEW POSITIONS AND BACKFILL PRICES *************************************************/
					/*********************************************************************************************/
					if (strategy.isStock() && positions.size() != 0) {
						// don't add new positions - only one item in positions are allowed for a stock
						// per strategyAccount
						log.info(stratName + "Is stock and there are already positions!");
					} else {
						addNewPositionsForStrategy(positions, strategy, strategyAccount, dbAccessMutex);
						BackPrices bp = new BackPrices();
						bp.fillBackPrices(strategy.getSymbol(), 90,  securityPriceDAO, null, dbAccessMutex);
					}

					ThetaUtil.secondsToSleepNoThrow(ThetaConstants.SHORT_SLEEP_TIME);
					
					synchronized(dbAccessMutex) {
						positionDAO.synchronizedFlush();
						positions = positionDAO.findPositionsByStrategyAcctId(strategyAccount.getStrategyAccountId());
					}
					
					updateOpenOrClosedStatus(positions, strategy, client, dbAccessMutex);
					
					for (Position position: positions){	

						log.info(stratName + "Calling executorService." );
						PositionHandler positionHandler = new PositionHandler(
								ibAccount,
				 				position,
				 				positionDAO,
				 				requestSeqDAO,
				 				securityPriceDAO,
				 				spreadDAO,
				 				strategyAccount,
				 				strategy,
				 				client,
				 				thetaUtil,
				 				priceRetriever,
				 				positionExecutor,
				 				dbAccessMutex);
						
						executorService.execute(positionHandler);
						log.info(stratName + "Called executorService." );
					} 
						
					synchronized(dbAccessMutex) {strategyDAO.synchronizedStoreAndFlush(strategy);}

				} // if (strategy.getActiveFlag().equals(ThetaConstants.TRUE)){
				else {log.info("The following strategy is NOT ACTIVE.  Going to next. : " + strategy.getStrategyName());}

			} catch (Throwable th){
				log.info(" ");
				log.info(stratName + "===============THROWABLE CAUGHT IN LOOP FOR SYMBOL : " + strategy.getSymbol() + " ================");
				log.info(stratName + " Please investigate this and/or discontinue this stock");
				log.info(stratName + "=========================================================================================");
				log.info(" ");
				log.error(stratName + "Throwable caught in strategy: " + strategy.toString(), th);
				//spreadDAO.synchronizedFlush();
			}
			//strategyAccountDAO.flush();
		} // End of FOR loop - for(StrategyAccount strategyAccount : strategyAccounts){

		executorService.shutdown();
		while (!executorService.isTerminated()) {
			log.info("Threadpool not yet finished.");
			ThetaUtil.secondsToSleepNoThrow(10);
		} // End of for loop for (Position position: positions){	

		log.info("Finished all threads");
		
		boolean ftpResult = ThetaUtil.ftpToHostmonster(ThetaConstants.END_OF_CYCLE_FILE, "endOfCycle");
		log.info("results of ftpResult : " + ThetaConstants.END_OF_CYCLE_FILE + ":" + ftpResult);
		
		if (thetaUtil.isTopOfHour()) {
			log.info("IS TOP OF HOUR.");
			
			for (PortfolioItem pi : client.portfolioItems){
				log.info("PI: " + pi);
			}
			
			
			ftpResult = ThetaUtil.ftpToHostmonster(ThetaConstants.PL_SNAPSHOT_FILE, ThetaConstants.PL_SNAPSHOT_FILE_PLAIN);
			File outFile = new File(ThetaConstants.PL_SNAPSHOT_FILE);
			boolean deleteFileResult = outFile.delete();

			log.info("Results of ftp and delete file : " + ThetaConstants.PL_SNAPSHOT_FILE  + ":" + ftpResult + ":" + deleteFileResult);
			
			ftpResult = ThetaUtil.ftpToHostmonster(ThetaConstants.SPREAD_SNAPSHOT_FILE, ThetaConstants.SPREAD_SNAPSHOT_FILE_PLAIN);
			outFile = new File(ThetaConstants.SPREAD_SNAPSHOT_FILE);
			thetaUtil.secondsToSleep(2);
			deleteFileResult = outFile.delete();

			log.info("Results of ftp and delete file : " + ThetaConstants.SPREAD_SNAPSHOT_FILE  + ":" + ftpResult + ":" + deleteFileResult);

			
			ftpResult = ThetaUtil.ftpToHostmonster(ThetaConstants.MATCH_FILE, ThetaConstants.MATCH_FILE_PLAIN);
			outFile = new File(ThetaConstants.MATCH_FILE);
			thetaUtil.secondsToSleep(2);
			deleteFileResult = outFile.delete();
			log.info("Results of ftp and delete file : " + ThetaConstants.MATCH_FILE  + ":" + ftpResult + ":" + deleteFileResult);
						

			ftpResult = ThetaUtil.ftpToHostmonster(ThetaConstants.PL_SNAPSHOT_FILE_ABBREV, ThetaConstants.PL_SNAPSHOT_FILE_ABBREV_PLAIN);
			outFile = new File(ThetaConstants.PL_SNAPSHOT_FILE_ABBREV);
			thetaUtil.secondsToSleep(2);
			deleteFileResult = outFile.delete();
			log.info("Results of pl_snapshot_file_abbrev : " + ThetaConstants.PL_SNAPSHOT_FILE_ABBREV + ":" + ftpResult + ":" + deleteFileResult);

			
			//log.info("BALANCE AGAINST REALITY!");
			//Calendar startBalanceAgainstReality = Calendar.getInstance();
			//log.info("TOP OF HOUR is TRUE!");
			//Set<IbAccount> ibAccounts = ibAccountDAO.findAllIbAccounts();
			//Iterator<IbAccount> ibAccountIter = ibAccounts.iterator();
			//while (ibAccountIter.hasNext()){
			//	IbAccount ibAccount = (IbAccount) ibAccountIter.next();
			//	BalanceIntVsExt balance = new BalanceIntVsExt(spreadDAO, positionDAO, client, thetaUtil);
			//	balance.balanceAgainstReality(ibAccount.getIbAccountIdExt(), strategyAccountDAO);
			//}

			//thetaUtil.enterMetricsIntoHeartbeat( "balanceAgainstReality", 0, startBalanceAgainstReality, Calendar.getInstance() );

		}

		
		spreadDAO.flush();
		log.info("Graceful finish");
		//thetaUtil.enterMetricsIntoHeartbeat( "Entire Run: ", 0, runStart, Calendar.getInstance());
		return(true);
	} // run()


	/**
	 *  This rolls up all record keeping - for a single strategyAccount - and also writes to 
	 *  a profit_loss record at the top of each hour.
	 */
	protected void rollUpRecordKeeping(StrategyAccount strategyAccount, ThetaMutex dbAccessMutex)
	throws ThetaExceptionExc {

		Strategy strategy = (Strategy) strategyAccount.getStrategy();
		if (strategy.getActiveFlag().equalsIgnoreCase(ThetaConstants.FALSE)){
			return;
		}
		String stratName = strategy.getStrategyName() + ": ";
		
		Integer stratAcctPlRealized = ThetaConstants.ZERO_INT;
		Integer stratAcctPlUnrealized = ThetaConstants.ZERO_INT;
		Integer stratAcctNumWins = ThetaConstants.ZERO_INT;
		Integer stratAcctNumLosses = ThetaConstants.ZERO_INT;
		Integer stratAcctTotalRisked = ThetaConstants.ZERO_INT;
		Integer stratAcctSecPrice = ThetaConstants.ZERO_INT;
		Integer stratAcctVixPrice = ThetaConstants.ZERO_INT;
		Integer currentStockPrice = ThetaConstants.ZERO_INT;
		
		// get NumWins, NumLosses, PLRealized, PLUnrealized
		// for PositionsInPast.
		ProfitLoss statsForExpiredPos = positionDAO.findStatsForExpiredPositions(strategyAccount.getStrategyAccountId());

		if (statsForExpiredPos != null){
			stratAcctPlRealized = statsForExpiredPos.getPlRealized();
			stratAcctPlUnrealized = statsForExpiredPos.getPlUnrealized();
			stratAcctNumWins = statsForExpiredPos.getNumWins();
			stratAcctNumLosses = statsForExpiredPos.getNumLosses();
			
			log.info(stratName + "stratAcctPlRealized:Unrealized: " + stratAcctPlRealized + " : " + stratAcctPlUnrealized);
		}
		
		/**
		 * Iterate through all positions - not in the past/expired
		 */
		// Get only positions that are NOT in the past:
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_YEAR, -1);
		Set<Position> presentPositions = positionDAO.findPositionsNonExpiredByStrategyAcctId(yesterday, strategyAccount.getStrategyAccountId());

		for (Position position: presentPositions) {
			
			log.info(stratName + "In rollUpRecordKeeping.  Position: " + position.getPositionId() + ":" + position.getStrategyName());
			
			Integer positionProfitLossRealized = ThetaConstants.ZERO_INT;
			Integer positionProfitLossUnrealized = ThetaConstants.ZERO_INT;
			Integer positionNumWins = ThetaConstants.ZERO_INT;
			Integer positionNumLosses = ThetaConstants.ZERO_INT;
			Integer positionNumOpenSpreads = ThetaConstants.ZERO_INT;
			Integer positionTotalRisked = ThetaConstants.ZERO_INT;
			Integer positionLastExitSecurityPrice = ThetaConstants.ZERO_INT;
			Integer currSecurityPrice = ThetaConstants.ZERO_INT;
			
			/**
			 * Now - iterate through all spreads
			 */

			//BUGBUGBUG!!!!!
			//Set<Spread> allSpreads = spreadDAO.findSpreadByPositionId(position.getPositionId());
			Set<Spread> allSpreads;
			synchronized (dbAccessMutex) {allSpreads = spreadDAO.findSpreadByPositionId(position.getPositionId());}

			for (Spread spread: allSpreads){

				log.info(stratName + "In rollUpRecordKeeping.  Spread: " + spread.getSpreadId());
				String spreadStatus = spread.getOpenOrClosed();
				
				SpreadSnapshot spreadSnapshot = new SpreadSnapshot(spread, position,strategy.getLongOrShortCode());
				String outFileName = ThetaConstants.SPREAD_SNAPSHOT_FILE;
				spreadSnapshot.writeToFile(outFileName);
				
				/**
				 * If the spread is OPENING PENDOPEN, PENDOPENCNF - ignore
				 * If the spread is NOTEXEC, FAILCLOSE  - ignore
				 *
				 * CLOSED, KILLED - REALIZED
				 * PENDCLOSE, PENDCLSCNF, OPEN - UNREALIZED (like OPEN)
				 */
				if ( (spreadStatus.equals(OpenOrClosedCode.PENDOPEN.toString())) ||
					 (spreadStatus.equals(OpenOrClosedCode.PENDOPNCNF.toString())) ||
					 (spreadStatus.equals(OpenOrClosedCode.NOTEXEC.toString())) ||
					 (spreadStatus.equals(OpenOrClosedCode.FAILCLOSE.toString()))){
					continue;
				}
				
				//log.info("Here is spread: " + spread.toStringShort()); 

				Integer enterSpreadPrice;
				if (!ThetaUtil.isNullOrZero(spread.getEnterActualIb())) {
					enterSpreadPrice = spread.getEnterActualIb();
					log.info(stratName + "Here is enterSpreadPrice (Actual): " + enterSpreadPrice);
				}
				else if ( !ThetaUtil.isNullOrZero(spread.getEnterMoneymkrPrice()) 
						&& 
						!ThetaUtil.isNullOrZero(spread.getEnterInsurancePrice()) ){
					enterSpreadPrice = spread.getEnterMoneymkrPrice() - spread.getEnterInsurancePrice();
					log.info(stratName + "Here is enterSpreadPrice (Calculated): " + enterSpreadPrice);
				}
				else
					continue;
					
				/**
				 * Set Profit Loss Unrealized 
				 * ENTER: Moneymaker: 150 Insurance: 75
				 * EXIT: Moneymaker: 100 Insurance 50
				 * Profit here is 150-75-100-50 = 25 - enter and exit commission
				 */
				Integer spreadProfitLoss = 0;
				if (strategy.isOption()) {
					spreadProfitLoss = (enterSpreadPrice
						- spread.getCurrentMoneymkrPrice()
						+ spread.getCurrentInsurancePrice()
						- spread.getEnterCommission() - spread.getExitCommission());
				}
				if (strategy.isStock() && strategy.isLong()) {
					Integer basePrice = (spread.getCurrentMoneymkrPrice() - enterSpreadPrice) * spread.getStkNumShares(); 
					spreadProfitLoss = ( basePrice - spread.getEnterCommission() - spread.getExitCommission());
					currentStockPrice = spread.getCurrentMoneymkrPrice();
				}
				if (strategy.isStock() && strategy.isShort()) {
					Integer basePrice = (enterSpreadPrice - spread.getCurrentMoneymkrPrice()) * spread.getStkNumShares(); 
					spreadProfitLoss = ( basePrice - spread.getEnterCommission() - spread.getExitCommission());
					currentStockPrice = spread.getCurrentMoneymkrPrice();
				}
				
				
				
				log.info(stratName + "ID : STATUS : SpreadProfitLoss: " + spread.getSpreadId() + " : " + spreadStatus + " : " + spreadProfitLoss);
				

				// PENDCLOSE, PENDCLSCNF, OPEN, UNREALIZED (like OPEN)
				if ( (spreadStatus.equals(OpenOrClosedCode.OPEN.toString())) ||
						(spreadStatus.equals(OpenOrClosedCode.PENDCLOSE.toString())) || 
						(spreadStatus.equals(OpenOrClosedCode.PENDCLSCNF.toString())) ) { 

					spread.setProfitLossUnrealized(spreadProfitLoss);  
					spread.setProfitLossRealized(ThetaConstants.ZERO_INT);
					positionNumOpenSpreads++;
					positionProfitLossUnrealized += spreadProfitLoss;
				
					currSecurityPrice = spread.getCurrentSecurityPrice();
					if (currSecurityPrice > 0) {
						stratAcctSecPrice = currSecurityPrice; 
					}

					if (position.isStock()) {
						positionTotalRisked += (currSecurityPrice * spread.getStkNumShares());
					}

					Integer currVixPrice = spread.getCurrentVixPrice();
					if (currVixPrice != null && currVixPrice > 0){
						stratAcctVixPrice = currVixPrice; 
					} else {
						stratAcctVixPrice = 0;
					}
				} // if ( spreadStatus.equals(OpenOrClosedCode.OPEN.toString()) || isSpreadPending(spread) ) 
	
				if ( (spreadStatus.equals(OpenOrClosedCode.CLOSED.toString()))){ 
//						||
//						(spreadStatus.equals(OpenOrClosedCode.KILLED.toString()))) ){
					//Integer spreadProfitLossUnrealized = spread.getProfitLossUnrealized();

					// NOTE: exitSpreadPrice is a POSITIVE number - it will be subtracted later
					Integer exitSpreadPrice;
					if (!ThetaUtil.isNullOrZero(spread.getExitActualIb())) {
						exitSpreadPrice = spread.getExitActualIb();
						log.info(stratName + "Here is exitSpreadPrice (from getExitActualIb()): " + exitSpreadPrice);
					} else if ( !ThetaUtil.isNullOrZero(spread.getExitMoneymkrPrice()) 
								&& 
								!ThetaUtil.isNullOrZero(spread.getExitInsurancePrice()) ) {
						exitSpreadPrice =  spread.getExitMoneymkrPrice() - spread.getExitInsurancePrice();
						log.info(stratName + "Here is exitSpreadPrice (Calculated): " + exitSpreadPrice);
					}
					else {
						continue;
					}
					
					if (strategy.isOption()){
						spreadProfitLoss = (enterSpreadPrice - exitSpreadPrice
											- spread.getEnterCommission() - spread.getExitCommission());
					}
					if (strategy.isStock() && strategy.isLong()) {
						Integer basePrice = (exitSpreadPrice - enterSpreadPrice) * spread.getStkNumShares(); 
						spreadProfitLoss = ( basePrice - spread.getEnterCommission() - spread.getExitCommission());
					}
					if (strategy.isStock() && strategy.isShort()) {
						Integer basePrice = (enterSpreadPrice - exitSpreadPrice) * spread.getStkNumShares(); 
						spreadProfitLoss = ( basePrice - spread.getEnterCommission() - spread.getExitCommission());
					}
					
					
					spread.setProfitLossRealized(spreadProfitLoss);
					spread.setProfitLossUnrealized(ThetaConstants.ZERO_INT);
					positionProfitLossRealized += spreadProfitLoss;
				} // if ( spreadStatus.equals(OpenOrClosedCode.CLOSED.toString()) etc.

				//NUM WINS AND NUM LOSSES ONLY!
				// If pending closure, can count as a WIN or LOSS.  If it goes back to an OPEN
				// state - this will reset...
				if ( spreadStatus.equals(OpenOrClosedCode.CLOSED.toString()) ||
						spreadStatus.equals(OpenOrClosedCode.PENDCLOSE.toString()) ||
						spreadStatus.equals(OpenOrClosedCode.PENDCLSCNF.toString()) ) {
					
					//Integer spreadProfitLossUnrealized = spread.getProfitLossRealized();
					Integer totalCommission = spread.getEnterCommission() + spread.getExitCommission();
					if ( spreadProfitLoss > ThetaConstants.ZERO_INT - totalCommission ) {
						positionNumWins++;
					} else {
						positionNumLosses++;
					}
					
				}

				/**
				 * A HAIL MARY is a loss - even if we actually make money on it.
				 * That is, we don't want to get back into this option.
				 * 
				 */
				if ( spreadStatus.equals(OpenOrClosedCode.HAILMARY.toString())){
					positionNumLosses++;

					Integer exitPriceMM = 0;
					if (!ThetaUtil.isNullOrZero(spread.getHmMmExitActualIb())) {
						exitPriceMM = spread.getHmMmExitActualIb();
					} 
					Integer exitPriceINS = 0;
					if (!ThetaUtil.isNullOrZero(spread.getHmInsExitActualIb())) {
						exitPriceINS = spread.getHmInsExitActualIb();
					} 
					
					// Closed!
					if (!ThetaUtil.isNullOrZero(exitPriceMM) && !ThetaUtil.isNullOrZero(exitPriceINS)){
						Integer exitSpreadPrice = exitPriceMM - exitPriceINS; 
						spreadProfitLoss = (enterSpreadPrice - exitSpreadPrice
								- spread.getEnterCommission() - spread.getExitCommission());
		
						spread.setProfitLossRealized(spreadProfitLoss);
						spread.setProfitLossUnrealized(ThetaConstants.ZERO_INT);
						positionProfitLossRealized += spreadProfitLoss;
					} else {
					// Still Open!
						if (exitPriceINS.equals(0)) exitPriceINS =  spread.getCurrentInsurancePrice();
						if (exitPriceMM.equals(0)) exitPriceMM = spread.getCurrentMoneymkrPrice();
						
						spreadProfitLoss =
							(spread.getEnterMoneymkrPrice() - exitPriceMM) 
							- (spread.getEnterInsurancePrice() - exitPriceINS)
							- spread.getEnterCommission() - spread.getExitCommission();

						spread.setProfitLossRealized(ThetaConstants.ZERO_INT);
						spread.setProfitLossUnrealized(spreadProfitLoss);
						positionProfitLossUnrealized += spreadProfitLoss;
					}
				}
				
				synchronized(dbAccessMutex) {spreadDAO.store(spread);}
			} // for (Spread spread: allSpreads){

			position.setProfitLossRealized(positionProfitLossRealized);
			position.setProfitLossUnrealized(positionProfitLossUnrealized);
			position.setNumWins(positionNumWins);
			position.setNumLosses(positionNumLosses);
			position.setNumOpenSpreads(positionNumOpenSpreads);

			if (position.isOption()) {
				positionTotalRisked = ( positionNumOpenSpreads * strategy.getAmountTotalRisk() );
			}
			
			//if (position.isStock()) {
			//	positionTotalRisked = ( positionNumOpenSpreads * currSecurityPrice * strategy.getStkShareBlockSize());
			//}
			
			position.setTotalRisked(positionTotalRisked);
			
			position.setLastExitSecurityPrice(positionLastExitSecurityPrice);

			log.info(stratName + "positionProfitLoss - realized : unrealized : " + positionProfitLossRealized + " : " + positionProfitLossUnrealized );
			log.info(stratName + "stratAcctPl - realized : unrealized : " + stratAcctPlRealized + " : " + stratAcctPlUnrealized  );

			stratAcctPlRealized += positionProfitLossRealized;
			stratAcctPlUnrealized += positionProfitLossUnrealized;
			stratAcctNumWins += positionNumWins;
			stratAcctNumLosses += positionNumLosses;
			stratAcctTotalRisked += positionTotalRisked;
			//stratAcctSecPrice = positionLastExitSecurityPrice;
			
			// and persist ...
			synchronized(dbAccessMutex) {positionDAO.synchronizedStoreAndFlush(position);}
			ThetaUtil.secondsToSleepNoThrow(2);
		} // while (positionIter.hasNext()){		


		if (thetaUtil.isTopOfHour(strategyAccount.getStrategyAccountId(), profitLossDAO)){

			ProfitLoss profitLoss = new ProfitLoss();
			
			profitLoss.setProfitLossId(ThetaConstants.INIT_ID);
			Calendar now = Calendar.getInstance();
			//profitLoss.setProfitOrLoss("PL");
			//profitLoss.setProfitLossStartDate(now);
			//profitLoss.setProfitLossDate(now);
			profitLoss.setStrategyAccount(strategyAccount);
			profitLoss.setCreatedDate(now);
			
			log.info(stratName + "TOP OF HOUR: realized, unrealized" + now.getTime() + " : " + stratAcctPlRealized + " : " + stratAcctPlUnrealized);

			profitLoss.setPlRealized(stratAcctPlRealized);
			profitLoss.setPlUnrealized(stratAcctPlUnrealized);
			profitLoss.setTotalRisked(stratAcctTotalRisked);
			profitLoss.setNumWins(stratAcctNumWins);
			profitLoss.setNumLosses(stratAcctNumLosses);
			
			Calendar nowMinusNinety = Calendar.getInstance();
			nowMinusNinety.add(Calendar.MINUTE, -90);
			
			profitLoss.setSymbolPrice(currentStockPrice);
			profitLoss.setVixPrice(stratAcctVixPrice);

			if (!stratAcctTotalRisked.equals(ThetaConstants.ZERO_INT)){
				//Integer scorePct = ((stratAcctPlRealized + stratAcctPlUnrealized) * ThetaConstants.TEN_THOUSAND_INT) / stratAcctTotalRisked;
				profitLoss.setScorePct(((stratAcctPlRealized + stratAcctPlUnrealized) * ThetaConstants.TEN_THOUSAND_INT) / stratAcctTotalRisked);
			}
			
			synchronized(dbAccessMutex) {profitLossDAO.synchronizedStoreAndFlush(profitLoss);}
			
			ProfitLossSnapshot plSnapshot = new ProfitLossSnapshot(profitLoss);
			String outFileName = ThetaConstants.PL_SNAPSHOT_FILE;
			plSnapshot.writeToFile(outFileName);

			String outFileNameAbbrev = ThetaConstants.PL_SNAPSHOT_FILE_ABBREV;
			
			ProfitLossSnapshot plSnapshotPortfolio = new ProfitLossSnapshot();
			plSnapshotPortfolio.strategyAccountId = 2;
			plSnapshotPortfolio.strategyName = "PORTFOLIO-Paper";
			plSnapshotPortfolio.symbol = "GOLDI";
			Float netLiquidationF = 0F;
			if (client != null) {netLiquidationF = new Float(client.getResp().getNetLiquidation());}
			plSnapshotPortfolio.PLRealized = Math.round(netLiquidationF) * 100;
			plSnapshotPortfolio.writeToFile(outFileNameAbbrev);

			ProfitLossSnapshot plSnapshotSPY = new ProfitLossSnapshot();
			plSnapshotSPY.strategyAccountId = 1;
			plSnapshotSPY.strategyName = "SPY";
			plSnapshotSPY.symbol = "SPY";
			plSnapshotSPY.PLRealized = priceRetriever.getCurrentSecurityPrice(ThetaConstants.SPY_TICKER, 0, client, dbAccessMutex);
			plSnapshotSPY.writeToFile(outFileNameAbbrev);

			
		} // if (thetaUtil.isTopOfHour(strategyAccount.getStrategyAccountId(), profitLossDAO))
		
	} // private void overallRecordKeeping(StrategyAccount strategyAccount){

	
	/**
	 * Add new Positions where necessary
	 * @param positions
	 * @return
	 * 
	 * 
	 * Add for this:
	 *    STRATEGY_ACCOUNT_ID
	 * Make sure that there are at least three months in the future.  
	 * In March 2011, there needs to be 
	 *   April 2011
	 *   May 2011
	 *   June 2011
	 * Iterate through the array - now that it's sorted
	 * POSITION_ID;STRATEGY_ACCOUNT_ID;STRATEGY_NAME;SYMBOL;OPT_RIGHT;EXPIRY_YEAR;EXPIRY_MONTH;EXPIRY_DAY;EXPIRY_TIMEFRAME;GOAL_NUM_SPREADS;NUM_OPEN_SPREADS;NUM_WINS;NUM_LOSSES;PROFIT_LOSS_UNREALIZED;PROFIT_LOSS_REALIZED;LAST_EXIT_SECURITY_PRICE;REENTRY_SEC_PRICE_CALL_ABOVE;REENTRY_SEC_PRICE_PUT_BELOW;TOTAL_RISKED;CREATED_BY;CREATED_DATE;UPDATED_BY;UPDATED_DATE;
	 * 4;1;STANDARD_THETA;SPY;C;2011;3;18;<NULL>;3;0;0;0;0;0;0;0;0;300;PCION;2011-02-08;<NULL>;<NULL>;
	 */
	private void addNewPositionsForStrategy(Set<Position> positions, Strategy strategy, StrategyAccount strategyAccount, ThetaMutex dbAccessMutex)
	throws ThetaExceptionExc {
		
		String stratName = strategy.getStrategyName() + ": ";
		//OPTIONS ONLY (For STOCK - see below)
		/**
		 * STEP 1: Set up main Set of all positions that need to be entered
		 * 
		 */
		// Only start adding from NEXT month (even from Sep 1 - start adding Oct)
		if (strategy.isOption()) {
			for (int i=ThetaConstants.ONE_INT; i < ThetaConstants.MONTHS_LOOKAHEAD; i++){
				for(int j=0; j<2; j++){
					Position posToAdd = new Position();
					posToAdd.init();
					posToAdd.setPositionId(ThetaConstants.INIT_ID);
					posToAdd.setStrategyAccountId(strategyAccount.getStrategyAccountId()); 
					posToAdd.setStrategyName(strategy.getStrategyName());

					posToAdd.setInstrument(InstrumentCode.OPT.toString());
					if (j==0){
						posToAdd.setOptRight(ThetaConstants.CALL);
						posToAdd.setGoalNumSpreads(strategy.getInitNumSpreadsCall());
					} else if (j==1) {
						posToAdd.setOptRight(ThetaConstants.PUT);
						posToAdd.setGoalNumSpreads(strategy.getInitNumSpreadsPut());
					} else {
						throw new ThetaExceptionExc("j must be 0 (CALL) or 1 (PUT) - nothing else.  ERROR!");
					}

					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.MONTH, i);
					// grrrr - Calendar cludge - must add a month (January is 0!!)
					Integer theMonth = cal.get(Calendar.MONTH) + ThetaConstants.ONE_INT;
					Integer theYear = cal.get(Calendar.YEAR);
					Calendar theDayCal = DateUtil.getThirdFridayCal(theMonth, theYear);
					posToAdd.setExpiryDate(theDayCal);
					posToAdd.setExpiryYear(theYear);
					posToAdd.setExpiryMonth(theMonth);
					posToAdd.setExpiryDay(theDayCal.get(Calendar.DAY_OF_MONTH));
					posToAdd.setCanAddToDb(true);
					posToAdd.setLoMidHiToFalse();
					
					log.info(stratName + "Here is the position to add: " + posToAdd.toStringShort());
					//thetaUtil.secondsToSleep(1);

					/**
					 * STEP 2: Step through the Position Set and see if this Position
					 * is already there.  If so, move on.  If NOT, then mark
					 * this Position for addition to the database.
					 */
					Iterator<Position> positionIter = positions.iterator();
					while (positionIter.hasNext()){

						Position posToCheck = (Position) positionIter.next();
						log.info(stratName + "Here is position TO CHECK: " + posToCheck.toStringShort());
						//thetaUtil.secondsToSleep(1);


						/**
						 * For Options, only Expiry Date and Option Right must be checked
						 * As this is for a single StrategyAccount, other information will be the same by definition
						 * For Stocks, there is a single Position per StrategyAccount.  Do not
						 * re-add if it's already there.
						 */
						if ( posToAdd.getExpiryYear().equals(posToCheck.getExpiryYear()) &&
								posToAdd.getExpiryMonth().equals(posToCheck.getExpiryMonth()) &&
								posToAdd.getExpiryDay().equals(posToCheck.getExpiryDay()) &&
								posToAdd.getOptRight().equals(posToCheck.getOptRight())){
							posToAdd.setCanAddToDb(false);
							break;
						}

					} // while (positionIter.hasNext()){


					/**
					 * STEP 3: Add to database where called for
					 */
					if (posToAdd.isCanAddToDb()){

						posToAdd.setTotalRisked(strategy.getAmountTotalRisk() * posToAdd.getGoalNumSpreads());

						posToAdd.setSymbol(strategy.getSymbol());
						posToAdd.setCreatedBy("addNewPositions - OPT");

						log.info(stratName + "ADDING THIS POSITION: " + posToAdd.toStringShort());

						posToAdd.setUpdatedBy("addNewPositions - OPT");
						posToAdd.setUpdatedDate(Calendar.getInstance());

						synchronized(dbAccessMutex) {positionDAO.store(posToAdd);}
					} // if (posToAdd.isCanAddToDb()){
				} // for(int j=0; j<2; j++){
			} // for (int i=ThetaConstants.ONE_INT; i < ThetaConstants.MONTHS_LOOKAHEAD; i++){

		} else if (strategy.isStock()){
			Position posToAdd = new Position();
			posToAdd.init();

			posToAdd.setPositionId(ThetaConstants.INIT_ID);
			posToAdd.setStrategyAccountId(strategyAccount.getStrategyAccountId()); 
			posToAdd.setStrategyName(strategy.getStrategyName());
			posToAdd.setOptRight(ThetaConstants.NA);
			posToAdd.setInstrument(InstrumentCode.STK.toString());

			posToAdd.setGoalNumSpreads(strategy.getStkGoalNumSpreads());

			Calendar cal = Calendar.getInstance();
			cal.set(2200,1,1);
			posToAdd.setExpiryDate(cal);
			posToAdd.setExpiryYear(2200);
			posToAdd.setExpiryMonth(1);
			posToAdd.setExpiryDay(1);
			posToAdd.setSymbol(strategy.getSymbol());
			posToAdd.setCreatedBy("addNewPositions - STK");
			posToAdd.setUpdatedBy("addNewPositions - STK");
			posToAdd.setUpdatedDate(Calendar.getInstance());
			posToAdd.setStkEnterAtPrice(0);
			posToAdd.setStkHitInitialCounterXTreme(false);
			posToAdd.setStkCanReenterAtXtreme(true);
			
			Integer numDaysAgoXtreme = 14;
			Integer numDaysAgoCounterXtreme = 4;
			posToAdd.calcAndSetStkLocalXtremePrices(securityPriceDAO, strategy, numDaysAgoXtreme, numDaysAgoCounterXtreme, dbAccessMutex);
			Calendar now = Calendar.getInstance();
			posToAdd.setXtremeUpdatedDate(now);
			
			log.info(stratName + "ADDING THIS POSITION: " + posToAdd);

			synchronized(dbAccessMutex) {positionDAO.store(posToAdd);}
		} else {
			log.error("Must be STOCK or an OPTION! Instrument: " + strategy.getInstrument());
		}

		synchronized(dbAccessMutex) {positionDAO.synchronizedFlush();}
	} // private void addNewPositions(Set<Position> positions, Strategy strategy, StrategyAccount strategyAccount){


	/**
	 * connectClient
	 * @param ibAccount
	 * @return
	 * @throws ThetaExceptionExc
	 */
	protected boolean connectClient(IbAccount ibAccount) 
	 throws ThetaExceptionExc {
	
		if (client.isConnected()) {
			//client.disconnect();
			log.info("********** Already connected! ***********");
			ThetaUtil.secondsToSleepNoThrow(1);
			return true;
		}

		log.info("********** NOT connected - about to connect ***********");
		ThetaUtil.secondsToSleepNoThrow(2);
		client.connect(ibAccount.getPort());

		if (client.isConnected()) {
			//client.disconnect();
			log.info("********** NOW connected! ***********");
			ThetaUtil.secondsToSleepNoThrow(2);
			return true;
		}

		return false;

	}

	/**
	 * 
	 * The order status is determined mostly from the OrderStatus Set - called
	 * from this method.
	 * 
	 * IF the item is in OpenOrders and it is of status PENDOPEN or PENDCLOSE, the
	 * status will be set to PENOPNCNF or PENDCLSCNF.
	 * 
	 * However, the OrderStatus Set is consulted, and the item status is set from here
	 * also:
	 * 
	 * OrderStatus Status:
	 * Filled - Order is Filled - If OPENING, Spread is now OPEN, If CLOSING, Spread is now CLOSED
	 * PreSubmitted, Submitted - Confirmed that the item has been submitted (as above, 
	 *       PENDOPEN becomes PENDOPNCNF, PENDCLOSE becomes PENDCLSCNF)
	 * Cancelled, Inactive - Order is no longer valid - PENDCLS or PENDCLSCNF goes back to OPEN.
	 *         PENDOPEN or PENDOPNCNF goes to NOTEXEC
	 * 
	 */
	private void updateOpenOrClosedStatus(Set<Position> positions, Strategy strategy, ThetaClientInterface client, ThetaMutex dbAccessMutex) 
	throws ThetaExceptionExc {
		
		String stratName = strategy.getStrategyName() + ": ";

		Set<OpenOrder> openOrdersIB = client.getOpenOrders();
		openOrdersIB.clear();
		
		client.reqOpenOrders();
		String changeReason = "";

		// Must be here
		thetaUtil.secondsToSleep(2);
		openOrdersIB = client.getOpenOrders();

		/**
		 * Iterate through all Positions in this Strategy/Account
		 * For each Position - iterate through all Spreads
		 */
		log.info(stratName + "ABOUT TO ITERATE THROUGH POSITIONS AND SPREADS");
		//Iterator<Position> positionIter = positions.iterator();
		//while (positionIter.hasNext()){
		//	Position position  = (Position) positionIter.next();

		for(Position position : positions){

			if (ThetaUtil.isPositionInPast(position)){continue;}
			Set<Spread> spreads;
			synchronized(dbAccessMutex) {spreads = spreadDAO.findSpreadByPositionId(position.getPositionId());}

			for(Spread spread : spreads){
				
				// Handle when the spread was killed by hand - change to NOTEXEC
				//if (isSpreadKilled(spread)){
				//	spread.setOpenOrClosed(OpenOrClosedCode.NOTEXEC.toString());
				//	spread.setUpdatedBy("KILLED by hand");
				//	spread.setUpdatedDate(Calendar.getInstance());
				//	spreadDAO.store(spread);
				//	spreadDAO.flush();
				//	continue;
				//}

				// Will set spread to OPEN or CLOSED if appropriate
				//  this will fail on the spread.isPending() check next 
				//  (desired behavior)
				boolean isSpreadOrderFilled = thetaUtil.setLastFillPriceFromOrderStatus(spread, position, spreadDAO, client);
				if (isSpreadOrderFilled) {
					spread.setUpdatedBy("setLastFillPriceFromOrderStatus");
					spread.setUpdatedDate(Calendar.getInstance());
					
					log.info(stratName + "In updateOpenOrClosedStatus.  Here is spread: " + spread.toString());
					synchronized(dbAccessMutex) {spreadDAO.synchronizedStoreAndFlush(spread);}
				}
				
				/**
				 * If the item is Closed and has not been accounted for (i.e. closed by hand)
				 * set it now
				 */
				if (spread.getOpenOrClosed().equals(OpenOrClosedCode.CLOSED.toString())
						&& spread.getProfitLossRealized().equals(ThetaConstants.ZERO_INT)){
					spread.setProfitLossRealized(spread.getProfitLossUnrealized());
				}				
				
				if (!spread.isPending()){continue;}
				
				if (spread.getRequestSeqNo() == null) {
					spread.setRequestSeqNo(0);
					spread.setOpenOrClosed(OpenOrClosedCode.CLOSED.toString());
					continue;
				}
				
				/**
				 * IF this is found in OrderStatus, then the execution was successful!
				 */
								
				/**
				 * Is this Spread found in Open Orders?
				 */
				if (thetaUtil.verifyRequestSeqInOpenOrders(spread.getRequestSeqNo(), openOrdersIB, spread, "ENTER", client)){

					/** It's in Open orders 
					 * PENDOPEN to PENDOPNCNF
					 * PENDCLOSE to PENDCLSCNF
					 */
					if (spread.getOpenOrClosed().equals(OpenOrClosedCode.PENDOPEN.toString())){
						spread.setOpenOrClosed(OpenOrClosedCode.PENDOPNCNF.toString());
						changeReason="PENDOPEN to PENDOPENCNF";
						spread.setEnterConfirmDate(Calendar.getInstance());
						//position.increaseOpenSpreadsByOne();
					}
					if (spread.getOpenOrClosed().equals(OpenOrClosedCode.PENDCLOSE.toString())){
						spread.setOpenOrClosed(OpenOrClosedCode.PENDCLSCNF.toString());
						changeReason="PENDCLOSE to PENDCLSCNF";
						spread.setExitConfirmDate(Calendar.getInstance());
					}
				} 
				
				/**
				 * OK - if we are CLOSED at this point (and spread HAD to be pending to enter this loop)
				 * it's time for final bookkeeping 
				 */
				if (spread.getOpenOrClosed().equals(OpenOrClosedCode.CLOSED.toString())){
					spread.setProfitLossRealized(spread.getProfitLossUnrealized());
				}

				spread.setUpdatedBy("updateOpenOrClosedStatus " + changeReason);
				spread.setUpdatedDate(Calendar.getInstance());

				// Check if killed - if so - no change
				if (!isSpreadKilled(spread)){
					synchronized(dbAccessMutex) {spreadDAO.synchronizedStoreAndFlush(spread);}
				}
				
				
			} // for(Spread spread : spreads){  
		}  // for(Position position : positions){
		
	} // private boolean updateOpenOrClosedStatus(Set<Position> positions) 
		
	/**
	 * 
	 * @param spread
	 * @return
	 */
	public boolean isSpreadKilled(Spread spread){

		/**
		 * 	Check to see if the SPREAD Open Or Closed Code has been changed by hand.
		 */
		Spread spreadToCheck = spreadDAO.findSpreadByPrimaryKey(spread.getSpreadId());
		if (spreadToCheck.getOpenOrClosed().equals(OpenOrClosedCode.KILLED.toString())){
			return true;
		} else {
			return false;
		}
	}
	
	
	
}
	
	
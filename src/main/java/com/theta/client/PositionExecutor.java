package com.theta.client;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.theta.client.enums.EnterOrExitCode;
import com.theta.dao.SpreadDAO;
import com.theta.domain.CoveredOption;
import com.theta.domain.Position;
import com.theta.domain.Spread;
import com.theta.domain.Strategy;
import com.theta.enums.ApprovalCode;
import com.theta.enums.BuyOrSellCode;
import com.theta.enums.LongOrShortCode;
import com.theta.enums.MovingSpeedCode;
import com.theta.enums.OpenOrClosedCode;
import com.theta.enums.OrderTypeCode;
import com.theta.process.ThetaConstants;
import com.theta.process.ThetaExceptionExc;
import com.theta.process.ThetaMutex;
import com.theta.process.ThetaUtil;

public class PositionExecutor {

	ThetaUtil thetaUtil;
	SpreadDAO spreadDAO;
	
	private static Logger log = Logger.getLogger(PositionExecutor.class);

	public PositionExecutor(ThetaUtil thetaUtil, SpreadDAO spreadDAO) {
		this.thetaUtil = thetaUtil;
		this.spreadDAO = spreadDAO;
	}
	

	/**
	 * Get unique ID from Client
	 * 
	 * @param client
	 * @param stratName
	 * @return
	 * @throws ThetaExceptionExc
	 */
	protected Integer getUniqueIdForOrder(ThetaClientInterface client, String stratName) throws ThetaExceptionExc {

        client.getResp().setNextValidId(ThetaConstants.ZERO_INT);
        Integer uniqueIdForOrder = ThetaConstants.ZERO_INT;
        for (int i=1; i<ThetaConstants.SIX_INT; i++) {
            client.reqId();
            thetaUtil.secondsToSleep(i * ThetaConstants.TWO_INT);
            uniqueIdForOrder = client.getResp().getNextValidId();
            log.info(stratName + "RequestSeqNo - after client.reqId() in EnterSingleSpread.  Count, Next valid ID: " + i + ", " + uniqueIdForOrder);
            if (uniqueIdForOrder.compareTo(ThetaConstants.ZERO_INT) > 0 ) {
                log.info(stratName + "All ok - uniqueId has been reset by the IB Client: " + uniqueIdForOrder);
                break;
            }
            log.info(stratName + "uniqueId is less than or equal to zero: " + uniqueIdForOrder);
        }
        if (uniqueIdForOrder.compareTo(ThetaConstants.ZERO_INT) <= 0) {
            throw new ThetaExceptionExc(stratName + "uniqueIdForOrder is still zero after a number of tries!!");
        }
		return uniqueIdForOrder;
	
	}
	
	
	/**
	 * 
	 * @param spread
	 */
	public synchronized Spread exitSingleSpread(Spread spread, Position position, Strategy strategy, MovingSpeedCode movingSpeed, String updatedBy, ThetaClientInterface client, ThetaMutex dbAccessMutex)
	throws ThetaExceptionExc {

		String stratName = strategy.getStrategyName();
		
		if (position.isOption() && !thetaUtil.isWithinTimeLimits(spread, position, client)){
			log.info(stratName + "exitSingleSpread - AFTER HOURS - not exiting! Time: " + Calendar.getInstance().getTime());
			return spread;
		}
		
		spread.setExitSecurityPrice(spread.getCurrentSecurityPrice());
		spread.setExitSecurityDate(Calendar.getInstance());
		
		spread.setExitMoneymkrPrice(spread.getCurrentMoneymkrPrice());
		spread.setExitInsurancePrice(spread.getCurrentInsurancePrice());

		Integer uniqueIdForOrder = getUniqueIdForOrder(client, strategy.getStrategyName());
						
		spread.setExitTriggerDate(Calendar.getInstance());
		spread.setRequestSeqNo(uniqueIdForOrder);
		
		Integer limitPrice;
		boolean clientCallReturn = false;
		try {
			if (position.isOption()) {
				Integer kicker = 0;
				if (movingSpeed.isSlow()) kicker = new Integer(2);
				if (movingSpeed.isMedium()) kicker = new Integer(3);
				if (movingSpeed.isFast()) kicker = new Integer(4);
				limitPrice = (((spread.getCurrentMoneymkrPrice() - spread.getCurrentInsurancePrice()  + kicker)/ThetaConstants.ONE_HUNDRED_INT));
				// OK - we're now getting out of the OPTION spread
				// BUY the moneymkr, SELL the Insurance
				//thetaUtil.enterRecordIntoHeartbeat("EXITING MONEYMKR: " + position.getOptRight() + ":" + spread.getStrikeMoneymkr() + ":" + spread.getCurrentMoneymkrPrice());
				//thetaUtil.enterRecordIntoHeartbeat("EXITING INSURANCE: " + position.getOptRight() + ":" + spread.getStrikeInsurance() + ":" + spread.getCurrentInsurancePrice() + "LIMIT: " + limitPrice);
				clientCallReturn = (client.enterOrExitSpread(
					position.getSymbol(),
					EnterOrExitCode.EXIT,
					position.getExpiryMonth(), position.getExpiryYear(),
					spread.getStrikeMoneymkr(), spread.getStrikeInsurance(),
					position.getOptRight(),
					limitPrice,
					uniqueIdForOrder));
			} else 	if (position.isStock()) {
				BuyOrSellCode buyOrSell;
				Double stkLimitPrice = new Double(0);
				if (strategy.getLongOrShortCode().equals(LongOrShortCode.LONG)) {
					buyOrSell = BuyOrSellCode.SELL;
					stkLimitPrice = new Double(spread.getCurrentMoneymkrPrice() - ThetaConstants.STK_KICKER_TO_EXIT)/ThetaConstants.ONE_HUNDRED_INT; 
				} else if (strategy.getLongOrShortCode().equals(LongOrShortCode.SHORT)) {
					buyOrSell = BuyOrSellCode.BUY;
					stkLimitPrice = new Double(spread.getCurrentMoneymkrPrice() + ThetaConstants.STK_KICKER_TO_EXIT)/ThetaConstants.ONE_HUNDRED_INT; 
				} else throw new ThetaExceptionExc("strategy.getLongOrShortFlag() must be LONG or SHORT."); 
				log.info(stratName + "EXITING STOCK POSITION: " +  spread.getStrikeMoneymkr() + ":" + spread.getCurrentMoneymkrPrice() + "LIMIT: " + stkLimitPrice);
				spread.setExitReason(spread.getExitReason() + 
						            ", Market Order");
				
				CoveredOption co = spread.parseCoveredOptionsDesc();
				if (co != null) {
					uniqueIdForOrder = getUniqueIdForOrder(client, strategy.getStrategyName());
					client.buyOptionsAtMarket(position.getSymbol(), spread.getCoveredOptionsNum(), co.monthInt, co.yearInt, co.strikePrice, co.putOrCall, uniqueIdForOrder);
				}

				verifySpreadVersion(spread, dbAccessMutex);
				clientCallReturn = client.placeStockOrder(position.getSymbol(), buyOrSell, spread.getStkNumShares(), stkLimitPrice, uniqueIdForOrder, OrderTypeCode.MKT);
				
			} else throw new ThetaExceptionExc(stratName + "position.instrument MUST BE 'STK' or 'OPT'!");
			

			if (clientCallReturn) {
				log.info("Successfully entered the order - not sure if it's been executed yet!");
				/**
				 * OK - it's in OPEN ORDERS - all is OK
				 */
				if (thetaUtil.verifyRequestSeqInOpenOrders(uniqueIdForOrder, spread, "EXIT", client)){
					log.info(stratName + "It's in OPEN ORDERS - all is OK");
					
					spread.setOpenOrClosed(OpenOrClosedCode.CLOSED.toString());
					spread.setExitConfirmDate(Calendar.getInstance());
	
					// set re-entry price
					//position.setStkReEnterAtPrice(strategy);
				} else {
					log.info(stratName + "NOT in OPEN ORDERS - give benefit of the doubt, however (Stay in PENDING state)...");
					spread.setOpenOrClosed(OpenOrClosedCode.PENDCLSCNF.toString());
				}
			} else {
				spread.setOpenOrClosed(OpenOrClosedCode.OPEN.toString());
				log.info(stratName + "DID NOT exit the spread");
			}  // if else
		} catch (Exception e) {
			log.info(stratName + "In enterSpreads: THROWING EXCEPTION!");
			throw new ThetaExceptionExc(e);
		} // try catch

		// May already be in order status - that is, execution is confirmed.
		// if so - this will set the spread to CLOSED 
		thetaUtil.secondsToSleep(ThetaConstants.LONGER_SLEEP_TIME);
		thetaUtil.setLastFillPriceFromOrderStatus(spread, position, spreadDAO, client);
		
		spread.setUpdatedBy("ExitSingleSpread: " + updatedBy);
		spread.setUpdatedDate(Calendar.getInstance());

		return spread;
	} // private void exitSingleSpread(Spread spread){

	
	
	
	/**
	 * @param spread
	 */
	public synchronized void enterSingleSpread(Spread spread, Position position, Strategy strategy, ThetaClientInterface client, ThetaMutex dbAccessMutex)
	throws ThetaExceptionExc {

		String stratName = strategy.getStrategyName() + ": ";

		spread.setEnterSecurityPrice(spread.getCurrentSecurityPrice());
		spread.setEnterSecurityDate(Calendar.getInstance());
		spread.setProfitLossUnrealized(ThetaConstants.ZERO_INT);
		String singleSpreadReason = null;

		Integer uniqueIdForOrder = getUniqueIdForOrder(client, strategy.getStrategyName());
		
		spread.setEnterTriggerDate(Calendar.getInstance());
		spread.setRequestSeqNo(uniqueIdForOrder);
		spread.setEnterRequestSeqNo(uniqueIdForOrder);
		
		// TODO: Use a limit price using the BID - this will allow for a quick execution

		Integer limitPrice = 0;
		if (position.isOption()) {
			if ( (!spread.getCurrentMoneymkrPrice().equals(ThetaConstants.ZERO_INT)) &&
					(!spread.getCurrentInsurancePrice().equals(ThetaConstants.ZERO_INT)) ){
				limitPrice = ((spread.getCurrentMoneymkrPrice() - spread.getCurrentInsurancePrice())/ThetaConstants.ONE_HUNDRED_INT);
			} else {
				log.info (stratName + "*** current moneymaker price AND current insurance price are 0 - returning!");
				//thetaUtil.enterRecordIntoHeartbeat(stratName + "*** current moneymaker price AND current insurance price are 0 - returning!");
				return;
			}
		}	
		
		try {

			boolean placeOrder = false;
			if (position.isStock()) {
				BuyOrSellCode buyOrSell = (strategy.isLong() ? BuyOrSellCode.BUY : BuyOrSellCode.SELL);
				spread.setEnterReason(spread.getEnterReason() + 
						             ", numShares: " + spread.getStkNumShares() + 
						             ", enterPrice (LMT): " + spread.getEnterMoneymkrPrice() );
				if (spread.getStkNumShares() != null && spread.getStkNumShares() > 0) {
					verifySpreadVersion(spread, dbAccessMutex);
					placeOrder = client.placeStockOrder(position.getSymbol(), buyOrSell, spread.getStkNumShares(), (spread.getEnterMoneymkrPrice().doubleValue()/100), uniqueIdForOrder, OrderTypeCode.MKT);
				} else {
					log.info(stratName + "Num shares for " + position.getStrategyName() + " is null or zero");
				}
			}

			if (position.isOption()){
				//thetaUtil.enterRecordIntoHeartbeat("ENTERING MONEYMKR: " + position.getOptRight() + ":" + spread.getStrikeMoneymkr() + ":" + spread.getCurrentMoneymkrPrice());
				//thetaUtil.enterRecordIntoHeartbeat("ENTERING INSURANCE: " + position.getOptRight() + ":" + spread.getStrikeInsurance() + ":" + spread.getCurrentInsurancePrice() + "LIMIT: " + limitPrice);

				placeOrder = client.enterOrExitSpread(
						position.getSymbol(),
						EnterOrExitCode.ENTER,
						position.getExpiryMonth(), position.getExpiryYear(),
						spread.getStrikeMoneymkr(), spread.getStrikeInsurance(),
						position.getOptRight(),
						limitPrice,
						uniqueIdForOrder);
			}

			if (placeOrder){
				log.info(stratName + "Successfully entered the order - not sure if it's been executed yet!");
				
				/**
				 * OK - it's in OPEN ORDERS - all is OK
				 */
				if (thetaUtil.verifyRequestSeqInOpenOrders(uniqueIdForOrder, spread, "ENTER", client)){
					log.info(stratName + "It's in OPEN ORDERS - all is OK");
					spread.setOpenOrClosed(OpenOrClosedCode.OPEN.toString());
					spread.setEnterConfirmDate(Calendar.getInstance());
					//position.setStkReEnterAtPrice(strategy);
					
					log.info(stratName + "In verifyRequestSeqInOpenOrders.  Here is position: " + position.getPositionId()  );
					
				} else {
					// TO DO: WAIT a few seconds and then check again.  If not in OPEN ORDERS, set to NOTEXEC
					log.info(stratName + "NOT in OPEN ORDERS - WAIT " + ThetaConstants.TEN_INT + " seconds. If still not there, change to NOTEXEC...");
					thetaUtil.secondsToSleep(ThetaConstants.TEN_INT);
					
					if (!thetaUtil.verifyRequestSeqInOpenOrders(uniqueIdForOrder, spread, "NEITHER", client)){
						spread.setOpenOrClosed(OpenOrClosedCode.OPEN.toString());
						spread.setEnterApproval(ApprovalCode.APPROVE.toString());
						spread.setExitReason(spread.getExitReason() + ", NotInOpenOrders - opening anyway.");
						log.info("NOT in open orders after a few seconds - DID NOT enter into the spread");
					} else {
						log.info(stratName + "It's in OPEN ORDERS - all is OK");
						spread.setOpenOrClosed(OpenOrClosedCode.OPEN.toString());
						spread.setEnterApproval(ApprovalCode.APPROVE.toString());
						spread.setEnterConfirmDate(Calendar.getInstance());
						//position.setStkReEnterAtPrice(strategy);
					}
				} // if/else (verifyRequestSeqInOpenOrders(uniqueIdForOrder, spread, "ENTER")){
			} else {
				spread.setOpenOrClosed(OpenOrClosedCode.NOTEXEC.toString());
				spread.setExitReason(spread.getExitReason() + ", PlaceOrderReturnsFalse. NOTEXEC. END.");
				log.info(stratName + "DID NOT enter into the spread");
			}  // if else (client.enterOrExitSpread(
		} catch (Exception e) {
			//thetaUtil.enterRecordIntoHeartbeat("In enterSpreads: THROWING EXCEPTION!" + e.getMessage());
			log.error(stratName + "In enterSpreads: THROWING EXCEPTION!", e);

			// TODO - Examine this rollback.  Probably not good to rollback position here - especially
			// if position has already been entered.
			//position.rollbackToPreviousValues();
			throw new ThetaExceptionExc(e);
		} // try catch

		// May already be in order status - that is, execution is confirmed.
		// if so - this will set the spread to OPEN 
		thetaUtil.secondsToSleep(ThetaConstants.LONGER_SLEEP_TIME);
		thetaUtil.setLastFillPriceFromOrderStatus(spread, position, spreadDAO, client);
		
		spread.setUpdatedBy("enterSingleSpread: " + spread.getUpdatedBy() + ": " + singleSpreadReason);
		spread.setUpdatedDate(Calendar.getInstance());
		
		// if spread is STILL NOTEXEC, then rollback position values
		if ( spread.getOpenOrClosed().equals(OpenOrClosedCode.NOTEXEC.toString()) ) {
			position.rollbackToPreviousValues();
		}
		
		verifySpreadVersion(spread, dbAccessMutex);
		log.info("Before store. " + spread.getCreatedBy() + " :version: " + spread.getVersion() );
		synchronized(dbAccessMutex) {spreadDAO.synchronizedStore(spread);}
		log.info("After store . " + spread.getCreatedBy() + " :version: " + spread.getVersion() );
		//verifySpreadVersion(spread, dbAccessMutex);
		
		return;
	} // private void enterSingleSpread(Spread spread){

	/**
	 * Final check that the version is correct.
	 * @param spread
	 */
	public void verifySpreadVersion(Spread targetSpread, ThetaMutex dbAccessMutex) throws ThetaExceptionExc {
		log.info("top of verifySpreadVersion.");
		Spread testSpread;
		synchronized(dbAccessMutex) {testSpread = spreadDAO.findSpreadBySpreadId(targetSpread.getSpreadId());}

		if ( testSpread != null && !testSpread.getVersion().equals(targetSpread.getVersion()) ) {
			log.info("Spread versions: test, target: " + testSpread.getVersion() + ", " + targetSpread.getVersion() );
			log.info("**** Versions are not equal.  Throwing Exception.");
			throw new ThetaExceptionExc("Versions are not equal.");
		}
		//spreadDAO.synchronizedStoreAndFlush(targetSpread);
		
	}
	
}

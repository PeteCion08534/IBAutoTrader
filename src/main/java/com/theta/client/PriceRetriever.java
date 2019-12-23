package com.theta.client;

import org.apache.log4j.Logger;

import com.theta.dao.RequestSeqDAO;
import com.theta.process.ThetaConstants;
import com.theta.process.ThetaExceptionExc;
import com.theta.process.ThetaMutex;
import com.theta.process.ThetaUtil;

public class PriceRetriever {

	private static Logger log = Logger.getLogger(PriceRetriever.class);

	ThetaUtil thetaUtil;
	RequestSeqDAO requestSeqDAO;
	
	public PriceRetriever(ThetaUtil thetaUtil, RequestSeqDAO requestSeqDAO) {
		this.thetaUtil = thetaUtil;
		this.requestSeqDAO = requestSeqDAO;
		
		log.info("IN PriceRetriever - constructor.");
		if (requestSeqDAO == null) {
			log.info("requestSeqDAO is null.");
		} else {
			log.info("requestSeqDAO is NOT null.");
		}
	}
	
	/**
	 * @param symbol
	 * @return Float
	 * @throws ThetaExceptionExc
	 */
	public synchronized Integer getCurrentSecurityPrice(String symbol, Integer positionId, ThetaClientInterface client, ThetaMutex dbAccessMutex)
	throws ThetaExceptionExc {
		
		ResponseInterface resp = client.getResp();
		resp.setErrorCode(0);
		resp.setErrorId(0);
		resp.setErrorMsg("NO ERROR");
		
		Integer uniqueIdForMktData;
		synchronized(dbAccessMutex) {uniqueIdForMktData = ThetaUtil.getNextRequestSeqNo(requestSeqDAO);}
		resp.setAllPricesToZero(uniqueIdForMktData);
		log.info(symbol + ": Calling reqMktDataStk: " + uniqueIdForMktData );
		client.reqMktDataStk(symbol, uniqueIdForMktData);

    	for (int i=0; i<ThetaConstants.NUM_TIMES_TO_CHECK_PRICE; i++){
    		thetaUtil.secondsToSleep(ThetaConstants.ONE_INT);
    		if ( !client.getResp().getPriceBid(uniqueIdForMktData).equals(ThetaConstants.ZERO_INT) 
    				&& !client.getResp().getPriceAsk(uniqueIdForMktData).equals(ThetaConstants.ZERO_INT) ) {
    			break;
    		}
    	}
    	
    	Integer priceBid = client.getResp().getPriceBid(uniqueIdForMktData);
    	Integer priceAsk = client.getResp().getPriceAsk(uniqueIdForMktData);

    	resp.clearPrices(uniqueIdForMktData);
    	
    	log.info(symbol + ": In getCurrentSecurityPrice - here are BID and ASK prices: " + priceBid + ", " + priceAsk);
    	Integer priceBidAskHalf = 0;

    	priceBidAskHalf = thetaUtil.checkAndReturnPriceBidAskHalf(symbol, positionId, priceBid, priceAsk, client);
    	return priceBidAskHalf;
	}
	
}

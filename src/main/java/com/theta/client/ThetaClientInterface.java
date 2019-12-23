package com.theta.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.ib.client.ContractDetails;
import com.theta.client.enums.EnterOrExitCode;
import com.theta.enums.BuyOrSellCode;
import com.theta.enums.LmtOrStpLmtCode;
import com.theta.enums.OrderTypeCode;
import com.theta.process.ThetaExceptionExc;

public interface ThetaClientInterface {

	public Set<PortfolioItem> portfolioItems = new HashSet<PortfolioItem>();
    public Set<OrderStatus> orderStatuses = new HashSet<OrderStatus>(); 
	
	public abstract boolean isConnected();

	public abstract boolean connect(Integer thePort);

	public abstract void disconnect();

	//client.reqMktDataStk(symbol, requestSeqNo);
	public abstract void reqMktDataStk(String stockTicker, Integer uniqueId);

	// Requests option details
	//client.reqOptionDetails(stockTicker, 4, 2011, 13000, "C");
	public abstract String reqOptionDetails(String symbol, Integer month,
			Integer year, Integer strike, String putOrCall)
	throws ThetaExceptionExc;

	// These are the PENDING orders
	public abstract void reqOpenOrders();

	// This is the entire account
	public abstract void reqAccountUpdates(String accountCode);

	public abstract String reqOptionPrice(ContractDetails det, Integer requestId);

	public abstract void sleepABit();

	public abstract boolean enterOrExitSpread(
			String symbol,
			EnterOrExitCode enterOrExit, 
			Integer month, Integer year,
			Integer strikeMoneymaker, Integer strikeInsurance,
			String putOrCall, 
			Integer limitPrice, 
			int uniqueIdForOrder);
	
    public abstract boolean placeStockOrder(String symbol, 
    		BuyOrSellCode buyOrSell,
    		Integer quantity,
    		Double limitPrice,
    		int uniqueNumericId,
    		OrderTypeCode orderTypeCode);
	
	public abstract void reqId();
	
    //public static Response	resp = new Response();
    public ResponseInterface getResp();
    public Boolean getCanEnterMap(Integer key);
    public Boolean setCanEnterMap(Integer key, Boolean canEnterFlag);
	public Boolean clearCanEnterMap(Integer key);
	public void setGlobalCanEnterFlag(boolean canEnter);
	public boolean getGlobalCanEnterFlag() ;		
	public boolean getCanExitFlag();
	public void setCanExitFlag(boolean canExitFlag);	
	//public HashMap<String, ContractDetails> detailCache = new HashMap<String, ContractDetails>();
	//public HashMap<String, ContractDetails> detailCache = null;
	public HashMap<String, ContractDetails> getDetailCache();
	
    public void cancelMktData(Integer i);
    //public Set<OpenOrder> openOrders = new HashSet<OpenOrder>();

    //public Set<OpenOrder> openOrders = null;
	public Set<OpenOrder> getOpenOrders();

    public boolean excerciseIndividualOption(
    		String symbol, 
    		LmtOrStpLmtCode lmtOrStpLmtCode,
    		BuyOrSellCode buyOrSellCode,
    		Integer month, Integer year, 
    		Integer strikePrice, 
    		String putOrCall,
    		Double limitPrice,
    		Double stopLimitPrice,
    		String ocaGroup,
    		Integer uniqueIdForOrder);
    public boolean isProd();
    
    public boolean buyOptionsAtMarket (
    		String symbol, 
    		Integer quantity,
    		Integer month, Integer year, 
    		Integer strikePrice, 
    		String putOrCall,
    		Integer uniqueIdForOrder);
    
}
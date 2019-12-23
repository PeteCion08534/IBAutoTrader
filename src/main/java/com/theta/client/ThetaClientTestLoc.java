/*
 * ThetaClientTest.java
 *  This is based on the original file from IB: TestJavaClient.SampleFrame.java
 *  There is a lot of vestigial code in here - this is left here on purpose
 *  to allow expandability.
 *  
 *  Additionally - most of the original IB code has been kept around.  To test functionality,
 *  Run: TestJavaClient.Main.java - this will bring up the sample frame application.
 */
package com.theta.client;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;

import com.ib.client.ComboLeg;
import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.EWrapperMsgGenerator;
import com.ib.client.Order;
import com.theta.client.enums.EnterOrExitCode;
import com.theta.enums.BuyOrSellCode;
import com.theta.enums.LmtOrStpLmtCode;
import com.theta.enums.OrderTypeCode;
import com.theta.process.ThetaConstants;
import com.theta.process.ThetaExceptionExc;

import org.mockito.Mock;
import org.mockito.Mockito;


public class ThetaClientTestLoc implements ThetaClientInterface  
{

    private Set<OpenOrder> openOrders;
    public ResponseInterface resp;
	public ThetaClientTestLoc() {
		//super();
		// TODO Auto-generated constructor stub
	    resp =  new ResponseLoc();
	    openOrders = new HashSet<OpenOrder>();
	}

	private static Logger log = Logger.getLogger("ThetaClientTestLoc");

	private boolean isConnected = false;
	private String threeSpaces = "   ";
	//private Integer SPYPrice = new Integer(13021);
	private Integer SPYPrice = ThetaConstants.TEST_INITIAL_SPY_PRICE;
	private Integer VIXPrice = new Integer(3434);
	
	/**
	 * 
	 */
    
    
    public Set<PortfolioItem> portfolioItems = new HashSet<PortfolioItem>();
   
    public boolean isConnected(){
    	if (isConnected)
    		return true;
    	else
    		return false;
    }
    
    
	public boolean connect(Integer thePort) {

		log.info("TEST: Connect to IB");
        
		if (thePort.equals(7496)){
			isConnected = true;
			return true;
		} else {
			isConnected = false;
			return false;
		}
		
    }

    public void disconnect() {
    	log.info("TEST: Disconnect from IB");
    	// disconnect from TWS
       isConnected = false;
    }

	//client.reqMktDataStk(symbol, requestSeqNo);
    public void reqMktDataStk(String stockTicker, Integer requestSeqNo) {
    	log.info("**** TOP OF ThetaClientTest:ReqMktDataStk - here is stock ticker: " + stockTicker);

    	if ("SPY".equalsIgnoreCase(stockTicker)){
    		//resp.setPriceLow(requestSeqNo, SPYPrice);
    		//resp.setPriceHigh(requestSeqNo, SPYPrice);
    		resp.setPriceClose(requestSeqNo, SPYPrice);
    		//resp.setPriceLast(requestSeqNo, SPYPrice);
    		resp.setPriceBid(requestSeqNo, SPYPrice);
    		resp.setPriceAsk(requestSeqNo, SPYPrice);
    	} else if ("VIX".equalsIgnoreCase(stockTicker)){
    		//resp.setPriceLow(requestSeqNo, VIXPrice);
    		//resp.setPriceHigh(requestSeqNo, VIXPrice);
    		resp.setPriceClose(requestSeqNo, VIXPrice);
    		//resp.setPriceLast(requestSeqNo, VIXPrice);
    		resp.setPriceBid(requestSeqNo, VIXPrice);
    		resp.setPriceAsk(requestSeqNo, VIXPrice);
    	} else {
    		Integer base = getStockPrice(stockTicker);
    		resp.setPriceBid(requestSeqNo, base - 2);
    		resp.setPriceAsk(requestSeqNo, base + 2);
    		resp.setPriceClose(requestSeqNo, base);
    	}
     	
    	log.info("BOTTOM OF ThetaClientTest:onReqMktDataStk");
    }

    
    private Integer getStockPrice(String ticker) {
    	
    	if (ticker.equals("ADM")) {return (5351 + random2()); }
    	 else if (ticker.equals("ALK")){return(6025 + random2());}
    	 else if (ticker.equals("APL")){return(2643 + random2());}
    	 else if (ticker.equals("BABY")){return(3648 + random2());}
    	 else if (ticker.equals("BANR")){return(4314 + random2());}
    	 else if (ticker.equals("BGFV")){return(1433 + random2());}
    	 else if (ticker.equals("BHE")){return(2569 + random2());}
    	 else if (ticker.equals("BIDU")){return(23498 + random2());}
    	 else if (ticker.equals("CRTO")){return(4100 + random2());}
    	 else if (ticker.equals("CW")){return(7034 + random2());}
    	 else if (ticker.equals("DANG")){return(916 + random2());}
    	 else if (ticker.equals("DCOM")){return(1621 + random2());}
    	 else if (ticker.equals("DEPO")){return(1567 + random2());}
    	 else if (ticker.equals("DPZ")){return(9538 + random2());}
    	 else if (ticker.equals("ENPH")){return(1483 + random2());}
    	 else if (ticker.equals("FB")){return(8079 + random2());}
    	 else if (ticker.equals("FFIV")){return(13497 + random2());}
    	 else if (ticker.equals("GILD")){return(9120 + random2());}
    	 else if (ticker.equals("HA")){return(2467 + random2());}
    	 else if (ticker.equals("HAL")){return(3963 + random2());}
    	 else if (ticker.equals("HRL")){return(5437 + random2());}
    	 else if (ticker.equals("INFN")){return(1489 + random2());}
    	 else if (ticker.equals("KEP")){return(1982 + random2());}
    	 else if (ticker.equals("KW")){return(2580 + random2());}
    	 else if (ticker.equals("LCI")){return(4239 + random2());}
    	 else if (ticker.equals("ORCL")){return(4121 + random2());}
    	 else if (ticker.equals("PLOW")){return(2250 + random2());}
    	 else if (ticker.equals("RAD")){return(744 + random2());}
    	 else if (ticker.equals("REX")){return(6464 + random2());}
    	 else if (ticker.equals("SHLD")){return(3257 + random2());}
    	 else if (ticker.equals("SKX")){return(5595 + random2());}
    	 else if (ticker.equals("SNDK")){return(10061 + random2());}
    	 else if (ticker.equals("STLD")){return(1929 + random2());}
    	 else if (ticker.equals("UA")){return(6844 + random2());}
    	 else if (ticker.equals("UTHR")){return(13207 + random2());}
    	 else if (ticker.equals("VIPS")){return(2001 + random2());}
    	 else if (ticker.equals("VNCE")){return(2800 + random2());}
    	 else if (ticker.equals("VSAT")){return(6532 + random2());}
    	
    	return 5000;
    }
    
    
    private Integer random2() {
		Double d =  ((Math.random()*400) - 200);
		return d.intValue();
    }
    
    // Requests option detail
	//client.reqOptionDetails(stockTicker, 4, 2011, 13000, "C");
    public String reqOptionDetails( String symbol, Integer month, Integer year, Integer strike, String putOrCall, Integer uniqueId) {

    	
    	if ("PUT".equalsIgnoreCase(putOrCall) || "P".equalsIgnoreCase(putOrCall)){
    		putOrCall="P";
    	} else if ("CALL".equalsIgnoreCase(putOrCall) || "C".equalsIgnoreCase(putOrCall) || "CAL".equalsIgnoreCase(putOrCall)) {
    		putOrCall="C";
    	} else { 
    		log.info("parameter putOrCall has an illegal value: " + putOrCall);
    		return "NOT OK";	
    	}

    	String strikeStr = strike.toString().replace(".", "");
    	
    	int reqId = 2;
       	resp.setOutgoingId(reqId);

    	Contract contract = new Contract();
    	contract.m_conId = 82167858;
       	contract.m_symbol = symbol;
       	contract.m_secType = "OPT";
       	contract.m_expiry = DateUtil.getThirdFriday(month, year);
       	contract.m_strike = new Double(strike)/100;
       	contract.m_right = putOrCall;
       	contract.m_multiplier = "100";
       	contract.m_exchange = "SMART";
       	contract.m_primaryExch = null;
       	contract.m_currency = "USD";
       	contract.m_localSymbol = symbol + threeSpaces + DateUtil.getThirdFriday(month,year) + putOrCall + "00" + strikeStr + "00";

       	ContractDetails contractDetails = new ContractDetails();
       	contractDetails.m_summary = contract;

       	contractDetails.m_marketName = symbol;
       	contractDetails.m_tradingClass = symbol;
       	contractDetails.m_minTick = 0.01;
       	contractDetails.m_priceMagnifier = 1;
       	contractDetails.m_orderTypes = "ACTIVETIM,ADJUST,ALERT,ALGO,ALLOC,AON,AVGCOST,BASKET,COND,CONDORDER,DAY,DEACT,DEACTDIS,DEACTEOD,FOK,GAT,GTC" +
       	",GTD,GTT,HID,HPENNY,ICE,IOC,LIT,LMT,MIT,MKT,MTL,NONALGO,OCA,PAON,POSTONLY,RELSTK,SCALE,SCALERST,SMARTSTG,STP,STPLMT,TRAI" +
       	"L,TRAILLIT,TRAILLMT,TRAILMIT,VOLAT,WHATIF,";
       	contractDetails.m_validExchanges = "SMART,AMEX,BATS,BOX,CBOE,CBOE2,IBSX,ISE,MIBSX,NASDAQOM,PHLX,PSE";
       	contractDetails.m_underConId = 756733;
       	contractDetails.m_longName = "SPDR S&P 500 ETF TRUST";
       	contractDetails.m_contractMonth = new Integer(year).toString() + new Integer(month).toString();
       	contractDetails.m_industry = "Funds";
       	contractDetails.m_category = "Equity Fund";
       	contractDetails.m_subcategory = "Growth-Large Cap";
       	contractDetails.m_timeZoneId = "EST";
       	
	    Date today = Calendar.getInstance().getTime();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    String formattedToday = formatter.format(today);
       	contractDetails.m_tradingHours = formattedToday + ":0330-2315;20110225:0830-2315";
       	//contractDetails.m_liquidHours = "20110301:0330-2215;20110225:0830-1615";
       	
       	resp.setContractDetails(contractDetails);

    	String msg = EWrapperMsgGenerator.contractDetails( reqId, contractDetails);

    	resp.setContractDetails(contractDetails);
    	resp.setContractDetailsStr(msg);
    	return "OK";
    }

    
    // These are the PENDING orders
    public void reqOpenOrders() {
       // m_client.reqOpenOrders();
    	log.info("IN TEST:reqOpenOrders");

    	portfolioItems.clear();
    	
    	// 132 CALL SPY - SHORT
    	Contract contract = new Contract();
    	contract.m_comboLegsDescrip=null;
    	contract.m_conId=82167111;
    	contract.m_currency="USD";
    	contract.m_exchange=null;
    	contract.m_expiry="20110415";
    	contract.m_includeExpired=false;
    	contract.m_localSymbol="SPY   110416C00132000";
    	contract.m_multiplier="100";
    	contract.m_primaryExch="AMEX";
    	contract.m_right="C";
    	contract.m_secId=null;
    	contract.m_secIdType=null;
    	contract.m_secType="OPT";
    	contract.m_strike=132.0;
    	contract.m_symbol="SPY";
    	contract.m_underComp=null;
  
    	double averageCost = 118.9855;
    	double marketPrice = 1.230;
    	double marketValue = -123.00;
    	int position = -1;
    	double realizedPNL = 0.0;
    	double unrealizedPNL = -4.02;
    	String accountName = "DU71402";
    	
    	PortfolioItem theItem = new PortfolioItem(contract, position, marketPrice,
    						marketValue, averageCost, unrealizedPNL, realizedPNL, accountName);
    	
    	portfolioItems.add(theItem);
    	log.info("Here is the item: " + theItem.toString());
    	
    	// 135 CALL SPY LONG
    	contract = new Contract();

    	contract.m_comboLegsDescrip=null;
    	contract.m_conId=82167222;
    	contract.m_currency="USD";
    	contract.m_exchange=null;
    	contract.m_expiry="20110415";
    	contract.m_includeExpired=false;
    	contract.m_localSymbol="SPY   110416C00138000";
    	contract.m_multiplier="100";
    	contract.m_primaryExch="AMEX";
    	contract.m_right="C";
    	contract.m_secId=null;
    	contract.m_secIdType=null;
    	contract.m_secType="OPT";
    	contract.m_strike=138.0;
    	contract.m_symbol="SPY";
    	contract.m_underComp=null;
    	
    	averageCost = 53.9855;
    	marketPrice = .553;
    	marketValue = 55.3;
    	position = 1;
    	realizedPNL = 0.0;
    	unrealizedPNL = 1.40;
    	accountName = "DU71402";
    	
    	theItem = new PortfolioItem(contract, position, marketPrice,
    						marketValue, averageCost, unrealizedPNL, realizedPNL, accountName);
    	
    	portfolioItems.add(theItem);
    	log.info("Here is the item: " + theItem.toString());

    }

    
    // This is the entire account
    public void reqAccountUpdates(String accountCode) {

    	resp.setNetLiquidation("75000");
    	resp.setLookAheadAvailableFunds("10000");
    	
    	//m_client.reqAccountUpdates(true, "pcion659");
    	log.info("In TEST:reqAccountUpdates");

    	portfolioItems.clear();
    	
    	// 134 CALL SPY - SHORT
    	// May 2011
    	Contract contract = new Contract();
    	contract.m_comboLegsDescrip=null;
    	contract.m_conId=82167333;
    	contract.m_currency="USD";
    	contract.m_exchange=null;
    	contract.m_expiry="20110415";
    	contract.m_includeExpired=false;
    	contract.m_localSymbol="SPY   110416C00135000";
    	contract.m_multiplier="100";
    	contract.m_primaryExch="AMEX";
    	contract.m_right="C";
    	contract.m_secId=null;
    	contract.m_secIdType=null;
    	contract.m_secType="OPT";
    	contract.m_strike=134.0;
    	contract.m_symbol="SPY";
    	contract.m_underComp=null;
  
    	double averageCost = 118.9855;
    	double marketPrice = 2.470;
    	double marketValue = -247.00;
    	int position = -2;
    	double realizedPNL = 0.0;
    	double unrealizedPNL = -9.02;
    	String accountName = "DU71402";
    	
    	PortfolioItem theItem = new PortfolioItem(contract, position, marketPrice,
    						marketValue, averageCost, unrealizedPNL, realizedPNL, accountName);
    	
    	portfolioItems.add(theItem);
    	log.info("Here is the item: " + theItem.toString());
    	
    	// 138 CALL SPY LONG
    	contract = new Contract();

    	contract.m_comboLegsDescrip=null;
    	contract.m_conId=82167444;
    	contract.m_currency="USD";
    	contract.m_exchange=null;
    	contract.m_expiry="20110415";
    	contract.m_includeExpired=false;
    	contract.m_localSymbol="SPY   110416C00138000";
    	contract.m_multiplier="100";
    	contract.m_primaryExch="AMEX";
    	contract.m_right="C";
    	contract.m_secId=null;
    	contract.m_secIdType=null;
    	contract.m_secType="OPT";
    	contract.m_strike=138.0;
    	contract.m_symbol="SPY";
    	contract.m_underComp=null;
    	
    	averageCost = 53.9855;
    	marketPrice = 1.016;
    	marketValue = 101.6;
    	position = 2;
    	realizedPNL = 0.0;
    	unrealizedPNL = -6.02;
    	accountName = "DU71402";
    	
    	theItem = new PortfolioItem(contract, position, marketPrice,
    						marketValue, averageCost, unrealizedPNL, realizedPNL, accountName);
    	
    	portfolioItems.add(theItem);
    	log.info("Here is the item: " + theItem.toString());


    	// 128 PUT SPY SHORT
    	contract = new Contract();

    	contract.m_comboLegsDescrip=null;
    	contract.m_conId=82167555;
    	contract.m_currency="USD";
    	contract.m_exchange=null;
    	contract.m_expiry="20110415";
    	contract.m_includeExpired=false;
    	contract.m_localSymbol="SPY   110416P00128000";
    	contract.m_multiplier="100";
    	contract.m_primaryExch="AMEX";
    	contract.m_right="P";
    	contract.m_secId=null;
    	contract.m_secIdType=null;
    	contract.m_secType="OPT";
    	contract.m_strike=128.0;
    	contract.m_symbol="SPY";
    	contract.m_underComp=null;

    	averageCost = 118.9855;
    	marketPrice = 1.234;
    	marketValue = -123.50;
    	position = -1;
    	realizedPNL = 0.0;
    	unrealizedPNL = -4.51;
    	accountName = "DU71402";
    	
    	theItem = new PortfolioItem(contract, position, marketPrice,
    						marketValue, averageCost, unrealizedPNL, realizedPNL, accountName);
    	
    	portfolioItems.add(theItem);
    	log.info("Here is the item: " + theItem.toString());

    	// 125 PUT SPY LONG
    	contract = new Contract();

    	contract.m_comboLegsDescrip=null;
    	contract.m_conId=82167666;
    	contract.m_currency="USD";
    	contract.m_exchange=null;
    	contract.m_expiry="20110415";
    	contract.m_includeExpired=false;
    	contract.m_localSymbol="SPY   110416P00125000";
    	contract.m_multiplier="100";
    	contract.m_primaryExch="AMEX";
    	contract.m_right="P";
    	contract.m_secId=null;
    	contract.m_secIdType=null;
    	contract.m_secType="OPT";
    	contract.m_strike=125.0;
    	contract.m_symbol="SPY";
    	contract.m_underComp=null;
    	
    	averageCost = 53.9855;
    	marketPrice = 0.505;
    	marketValue = 50.8;
    	position = 1;
    	realizedPNL = 0.0;
    	unrealizedPNL = -3.01;
    	accountName = "DU71402";
    	
    	theItem = new PortfolioItem(contract, position, marketPrice,
    						marketValue, averageCost, unrealizedPNL, realizedPNL, accountName);
    	
    	portfolioItems.add(theItem);
    	log.info("Here is the item: " + theItem.toString());

    }

    
    public String reqOptionPrice(ContractDetails det, Integer requestId) {
    	System.out.println("IN TEST:reqOptionPrice. strike = " + det.m_summary.m_strike);

    	String right = det.m_summary.m_right;
    	Double strike = det.m_summary.m_strike;
    	Integer price = 0;

    	//private Integer SPYPrice = new Integer(13021);

    	if (SPYPrice.equals(new Integer(13021))){
    		if (right.equalsIgnoreCase("C")){ // CALLS
    			switch (strike.intValue()){
    			case 131: price = new Integer(226); break;
    			case 132: price = new Integer(168); break;
    			case 133: price = new Integer(121); break;
    			case 134: price = new Integer(84); break;
    			case 135: price = new Integer(55); break;
    			case 136: price = new Integer(36); break;
    			default: price = new Integer(23) ; break;
    			}
    		} else {  // PUTS
    			switch (strike.intValue()){
    			case 130: price = new Integer(213); break;
    			case 129: price = new Integer(177); break;
    			case 128: price = new Integer(144); break;
    			case 127: price = new Integer(120); break;
    			case 126: price = new Integer(99); break;
    			case 125: price = new Integer(84); break;
    			case 124: price = new Integer(71); break;
    			case 123: price = new Integer(59); break;
    			default: price = new Integer(51) ; break;
    			}
    		}
    	}

    	if (SPYPrice.equals(new Integer(12721))){
    		if (right.equalsIgnoreCase("C")){ // CALLS
    			switch (strike.intValue()){
    			case 128: price = new Integer(226); break;
    			case 129: price = new Integer(168); break;
    			case 130: price = new Integer(121); break;
    			case 131: price = new Integer(84); break;
    			case 132: price = new Integer(55); break;
    			case 133: price = new Integer(36); break;
    			case 134: price = new Integer(20); break;
    			case 135: price = new Integer(10); break;
    			case 136: price = new Integer(5); break;
    			case 137: price = new Integer(2); break;
    			default: price = new Integer(2) ; break;
    			}
    		} else {  // PUTS
    			switch (strike.intValue()){
    			case 128: price = new Integer(260); break;
    			case 127: price = new Integer(213); break;
    			case 126: price = new Integer(177); break;
    			case 125: price = new Integer(144); break;
    			case 124: price = new Integer(120); break;
    			case 123: price = new Integer(99); break;
    			case 122: price = new Integer(84); break;
    			case 121: price = new Integer(71); break;
    			case 120: price = new Integer(59); break;
    			default: price = new Integer(51) ; break;
    			}
    		}
    	}
    	
    	System.out.println("IN TEST:reqOptionPrice.  price: "  + price );
    	//resp.setPriceLow(requestId, price);
    	//resp.setPriceHigh(requestId,price);
    	resp.setPriceClose(requestId,price);
    	resp.setPriceAsk(requestId,price);
    	resp.setPriceBid(requestId,price);
    	//resp.setPriceLast(requestId,price);

    	/*
    	Put Options	Expire at close Wednesday, March 30, 2011	0	0	0	0	0	0
    	Strike	Symbol			Last	Chg		Bid		Ask		Vol		Open Int
    	122	SPY110319P00122000	0.35	0.02	0.34	0.35	14916	228588
    	123	SPY110319P00123000	0.38	0.03	0.38	0.4		6241	154348
    	124	SPY110319P00124000	0.44	0.05	0.44	0.46	3835	81106
    	125	SPY110319P00125000	0.51	0.05	0.51	0.53	15714	233538
    	126	SPY110319P00126000	0.59	0.07	0.6		0.61	3120	72555
    	127	SPY110319P00127000	0.71	0.08	0.71	0.72	14362	94494
    	128	SPY110319P00128000	0.84	0.08	0.84	0.86	22400	166572
    	129	SPY110319P00129000	0.99	0.09	1.01	1.02	4863	153555
    	130	SPY110319P00130000	1.2		0.13	1.21	1.23	23010	122992
    	131	SPY110319P00131000	1.44	0.15	1.46	1.48	14391	100839
    	132	SPY110319P00132000	1.77	0.12	1.77	1.8		20524	52898
    	133	SPY110319P00133000	2.13	0.21	2.16	2.18	31134	53918
    	 */
    	
    	/*
    	Strike	CALL Symbol		Last	Chg		Bid		Ask		Vol		Open Int
    	133	SPY110319C00133000	2.26	0.11	2.23	2.25	12634	62735
    	134	SPY110319C00134000	1.68	0.08	1.66	1.68	5733	69216
    	135	SPY110319C00135000	1.21	0.07	1.18	1.21	16182	172086
    	136	SPY110319C00136000	0.84	0.05	0.81	0.83	11674	35823
    	137	SPY110319C00137000	0.55	0.05	0.54	0.55	7299	25392
    	138	SPY110319C00138000	0.36	0.04	0.35	0.36	6702	35341
    	139	SPY110319C00139000	0.23	0.04	0.22	0.23	4183	25475
    	140	SPY110319C00140000	0.14	0		0.14	0.15	948		16644
    	141	SPY110319C00141000	0.09	0.01	0.09	0.1		320		9903
    	142	SPY110319C00142000	0.07	0.01	0.06	0.07	750		4876
    	143	SPY110319C00143000	0.05	0.01	0.04	0.05	493		5258
    	*/							
    	 
       	return "OK";
    }
    
    public void sleepABit(){
    	try {
    		Thread.sleep(500);
    	} catch (Exception e ) {
    		System.out.println("IN THREAD.SLEEP Exception: e: "  + e);
    	}
    }


    
    public boolean enterOrExitSpread(String symbol, 
    		EnterOrExitCode enterOrExit,
    		Integer month, Integer year, 
    		Integer strikeMoneymaker, Integer strikeInsurance, 
    		String putOrCall,
   	    	Integer limitPrice,
    		int unique1, int unique2, int uniqueNumericId) {

    	if ("PUT".equalsIgnoreCase(putOrCall) || "P".equalsIgnoreCase(putOrCall)){
    		putOrCall="P";
    		if (strikeMoneymaker < strikeInsurance){
    			log.info("For a PUT SPREAD, the moneymaker leg must be MORE than the insurance leg. moneymaker : insurance " + strikeMoneymaker + " : " + strikeInsurance);
    			return false;
    		}	
    	} else if ("CALL".equalsIgnoreCase(putOrCall) || "C".equalsIgnoreCase(putOrCall) || "CAL".equalsIgnoreCase(putOrCall)) {
    		putOrCall="C";
    		if (strikeMoneymaker > strikeInsurance){
    			log.info("For a CALL SPREAD, the moneymaker leg must be LESS than the insurance leg. moneymaker : insurance " + strikeMoneymaker + " : " + strikeInsurance);
    			return false;
    		}	
    	} else { 
    		log.info("parameter putOrCall has an illegal value: " + putOrCall);
    		return false;	
    	}

    	// TODO: Check month/date - must be between now and 1 year in the future
    	
    	
    	//"SPY", 02, 2011, 130.0, "C"
        	// TODO: Check input parameters (See ReqOptionDetails above)
        	// symbol must be 1 to 8 alphanumeric (can have a dot)
    	
    	
    	//First leg
    	Contract con1 = new Contract();
    	con1.m_symbol = symbol;
    	con1.m_secType = "OPT";
     	con1.m_expiry = DateUtil.getThirdFriday(month, year);
    	con1.m_strike = strikeMoneymaker;
    	con1.m_right = putOrCall;
    	con1.m_multiplier = "100";
    	con1.m_exchange = "SMART";
    	con1.m_currency = "USD";
      	
    	//m_client.reqContractDetails(1, con1);
    	sleepABit();
    	int Leg1_conId = resp.getContractDetails().m_summary.m_conId;
    	
    	//Second leg
    	Contract con2 = new Contract();
    	con2.m_symbol = symbol;
    	con2.m_secType = "OPT";
    	con2.m_expiry = DateUtil.getThirdFriday(month, year);
    	con2.m_strike = strikeInsurance;
    	con2.m_right = putOrCall;
    	con2.m_multiplier = "100";
    	con2.m_exchange = "SMART";
    	con2.m_currency = "USD";
    	
    	//m_client.reqContractDetails(2, con2);
    	sleepABit();
    	int Leg2_conId = resp.getContractDetails().m_summary.m_conId;

    	//All conId numbers are delivered by the ContractDetail()

    	ComboLeg leg1 = new ComboLeg(); // for the first leg
    	ComboLeg leg2 = new ComboLeg(); // for the second leg

    	leg1.m_conId = Leg1_conId;
    	leg1.m_ratio = 1;
    	if (enterOrExit.equals(EnterOrExitCode.ENTER)){
    		leg1.m_action = "SELL";
    	} else {
    		leg1.m_action = "BUY";
    	}
    	leg1.m_exchange = "SMART";
    	leg1.m_openClose = 0;
    	leg1.m_shortSaleSlot = 0;
    	leg1.m_designatedLocation = "";

    	leg2.m_conId = Leg2_conId;
    	leg2.m_ratio = 1;
    	if (enterOrExit.equals(EnterOrExitCode.ENTER)){
    		leg2.m_action = "BUY";
    	} else {
    		leg2.m_action = "SELL";
    	}
    	leg2.m_exchange = "SMART";
    	leg2.m_openClose = 0;
    	leg2.m_shortSaleSlot = 0;
    	leg2.m_designatedLocation = "";

    	Vector addAllLegs = new Vector();
    	addAllLegs.add(leg1);
    	addAllLegs.add(leg2);

    	Contract contract = new Contract();
    	contract.m_symbol = "USD"; // For combo order use “USD” as the symbol value all the time
    	contract.m_secType = "BAG"; // BAG is the security type for COMBO order
    	contract.m_exchange = "SMART";
    	contract.m_currency = "USD";
    	contract.m_comboLegs = addAllLegs; //including combo order in contract object

    	Order order = new Order();
    	order.m_action = "BUY";
    	order.m_totalQuantity = 1;
    	order.m_orderType = "LMT";
    	order.m_lmtPrice = limitPrice;
    	order.m_tif = "DAY";
    	
    	//m_client.placeOrder(uniqueNumericId, contract, order);
    	
    	return true;
    }


	/* (non-Javadoc)
	 * @see com.theta.client.ThetaClientInterface#reqId()
	 */
	public void reqId() {
		// TODO Auto-generated method stub
		
	}

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
    		Integer uniqueNumericId) {
    	
    	log.info("IN exerciseIndividualOption");
    	
    	if ("PUT".equalsIgnoreCase(putOrCall) || "P".equalsIgnoreCase(putOrCall)){
    		putOrCall="P";
    	} else if ("CALL".equalsIgnoreCase(putOrCall) || "C".equalsIgnoreCase(putOrCall) || "CAL".equalsIgnoreCase(putOrCall)) {
    		putOrCall="C";
    	} else { 
    		return false;	
    	}

    	Contract contract = new Contract();
    	contract.m_symbol = symbol;
    	contract.m_secType = "OPT";
    	contract.m_expiry = DateUtil.getThirdFriday(month, year);
    	contract.m_strike = strikePrice.doubleValue();
    	contract.m_right = putOrCall;
    	contract.m_multiplier = "100";
    	contract.m_exchange = "SMART";
    	contract.m_currency = "USD";
      	
    	log.info("Contract: " + contract.toString());
    	
    	//m_client.reqContractDetails(uniqueNumericId, contract);
    	sleepABit();
    	
    	ContractDetails cd = resp.getContractDetails();
    	Contract summary = cd.m_summary;
    	Integer conId = summary.m_conId;
    	contract.m_conId = conId;

    	Order order = new Order();
    	order.m_action = buyOrSellCode.getValue();
    	order.m_totalQuantity = 1;
    	order.m_orderType = lmtOrStpLmtCode.getValue();
    	order.m_lmtPrice = (limitPrice.doubleValue()/ThetaConstants.ONE_HUNDRED_DOUBLE);
    	order.m_auxPrice = ThetaConstants.ZERO_DOUBLE;

    	if (lmtOrStpLmtCode.getValue().equals(LmtOrStpLmtCode.STPLMT.getValue())) {
			if ((stopLimitPrice == null) || (stopLimitPrice.compareTo(ThetaConstants.ZERO_DOUBLE)==0)){
				log.info("IF a STPLMT order - there must be a stopLimitPrice given!");
				return false;
			} else {
	       		order.m_auxPrice = (stopLimitPrice.doubleValue()/ThetaConstants.ONE_HUNDRED_DOUBLE);
			}
	       	if ((ocaGroup == null) || (ocaGroup.equals(ThetaConstants.NULL_STRING))){
				log.info("IF a STPLMT order - there must be an ocaGroup given!");
				return false;
	       	}
    	}

       	order.m_tif = "GTC";

       	// oca = One Cancels All group
       	if ((ocaGroup != null) && (!ocaGroup.equals(ThetaConstants.NULL_STRING))){
       		order.m_ocaGroup = ocaGroup;
       		order.m_ocaType = 1;  // Cancel all remaining orders with block
       	}
       	
    	log.info("in excerciseIndividualOption.  Here is contract: " + contract.toString());
    	log.info("in excerciseIndividualOption.  Here is order: " + order.toString());
    	
    	//m_client.placeOrder(uniqueNumericId, contract, order);
    	
    	log.info("End of excerciseIndividualOption");
    	return true;
    }


	@Override
	public String reqOptionDetails(String symbol, Integer month, Integer year,
			Integer strike, String putOrCall) throws ThetaExceptionExc {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean enterOrExitSpread(String symbol,
			EnterOrExitCode enterOrExit, Integer month, Integer year,
			Integer strikeMoneymaker, Integer strikeInsurance,
			String putOrCall, Integer limitPrice, int uniqueIdForOrder) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean placeStockOrder(String symbol, BuyOrSellCode buyOrSell,
			Integer quantity, Double limitPrice, int uniqueNumericId,
			OrderTypeCode orderTypeCode) {
		// TODO Auto-generated method stub
		return false;
	}



	private boolean globalCanEnterFlag;
	private boolean canExitFlag;
	private Map<Integer,Boolean> canEnterMap = new HashMap<Integer, Boolean>();
	
	public Boolean getCanEnterMap(Integer key) {
		return canEnterMap.get(key);
	}
	public Boolean setCanEnterMap(Integer key, Boolean canEnterFlag) {
		return this.canEnterMap.put(key, canEnterFlag);
	}
	public Boolean clearCanEnterMap(Integer key) {
		return this.canEnterMap.remove(key);
	}
	public void setGlobalCanEnterFlag(boolean canEnter) {
		this.globalCanEnterFlag = canEnter;
	}
	public boolean getGlobalCanEnterFlag() {
		return this.globalCanEnterFlag;
	}
	public boolean getCanExitFlag() {
		return canExitFlag;
	}
	public void setCanExitFlag(boolean canExitFlag) {
		this.canExitFlag = canExitFlag;
	}
	public void cancelMktData(Integer i) {
	}
	@Override
	public ResponseInterface getResp() {
		// TODO Auto-generated method stub
		return this.resp;
	}

	@Override
	public Set<OpenOrder> getOpenOrders() {
		// TODO Auto-generated method stub
		return this.openOrders;
	}

	public HashMap<String, ContractDetails> detailCache = new HashMap<String, ContractDetails>();
	public HashMap<String, ContractDetails> getDetailCache() {
		return detailCache;
	}

	public boolean isProd() { return false; }


	@Override
	public boolean buyOptionsAtMarket(String symbol, Integer quantity,
			Integer month, Integer year, Integer strikePrice, String putOrCall,
			Integer uniqueIdForOrder) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

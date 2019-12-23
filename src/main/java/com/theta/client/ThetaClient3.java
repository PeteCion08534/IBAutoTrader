/*
 * ThetaClient3.java
 *  This is based on the original file from IB: TestJavaClient.SampleFrame.java
 *  There is a lot of vestigial code in here - this is left here on purpose
 *  to allow expandability.
 *  
 *  Additionally - most of the original IB code has been kept around.  To test functionality,
 *  Run: TestJavaClient.Main.java - this will bring up the sample frame application.
 */
package com.theta.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;

import TestJavaClient.AccountDlg;
import TestJavaClient.ExecFilterDlg;
import TestJavaClient.FinancialAdvisorDlg;
import TestJavaClient.IBTextPanel;
import TestJavaClient.LogConfigDlg;
import TestJavaClient.MktDepthDlg;
import TestJavaClient.SampleFrame;

import com.ib.client.ComboLeg;
import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.EClientSocket;
import com.ib.client.EWrapperMsgGenerator;
import com.ib.client.Execution;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.client.UnderComp;
import com.theta.client.enums.EnterOrExitCode;
import com.theta.enums.BuyOrSellCode;
import com.theta.enums.LmtOrStpLmtCode;
import com.theta.enums.OrderTypeCode;
import com.theta.process.ThetaConstants;
import com.theta.process.ThetaExceptionExc;

@Service
public class ThetaClient3 extends SampleFrame implements ThetaClientInterface {

	public ThetaClient3() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Logger log = Logger.getLogger("ThetaClient3");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int NOT_AN_FA_ACCOUNT_ERROR = 321 ;
    private int faErrorCodes[] = { 503, 504, 505, 522, 1100, NOT_AN_FA_ACCOUNT_ERROR } ;
    private boolean faError ;

    EClientSocket   m_client = new EClientSocket( this);
    IBTextPanel     m_tickers = new IBTextPanel("Market and Historical Data", false);
    IBTextPanel     m_TWS = new IBTextPanel("TWS Server Responses", false);
    IBTextPanel     m_errors = new IBTextPanel("Errors and Messages", false);

    AccountDlg      m_acctDlg = new AccountDlg(this);
    MktDepthDlg     m_mktDepthDlg = new MktDepthDlg(this);
    
    public ResponseInterface resp = new Response();
    public ResponseInterface getResp() {
		return resp;
	}

	public Set<PortfolioItem> portfolioItems = new HashSet<PortfolioItem>();
    public Set<OrderStatus> orderStatuses = new HashSet<OrderStatus>(); 
    private Set<OpenOrder> openOrders = new HashSet<OpenOrder>();
    public Set<OpenOrder> getOpenOrders() {
    	return this.openOrders;
    }
    //static SnapshotOption snapshotOption = new SnapshotOption();

	// For caching
	public HashMap<String, ContractDetails> detailCache = new HashMap<String, ContractDetails>();
	public HashMap<String, ContractDetails> getDetailCache() {
		return detailCache;
	}

    static final int TICK_PRICE		= 1;
    static final int START_ID_FOR_CONTRACT_DETAILS = 900000;
    private int uniqueIdForContractDetails = START_ID_FOR_CONTRACT_DETAILS;
    
    String faGroupXML ;
    String faProfilesXML ;
    String faAliasesXML ;
    public String   m_FAAcctCodes;
    public boolean  m_bIsFAAccount = false;
    
    private boolean m_disconnectInProgress = false;
    //private boolean canEnterFlag = false;
    private Map<Integer, Boolean> canEnterMap = new HashMap<Integer, Boolean>();
    private boolean globalCanEnterFlag;
    
    private boolean canExitFlag = false;
   
    public boolean isConnected(){
    	if (m_client.isConnected())
    		return true;
    	else
    		return false;
    }
    
    
	public boolean connect(Integer thePort) {
        m_bIsFAAccount = false;

        // connect to TWS
        m_disconnectInProgress = false;
        
        String 	m_retIpAddress = "";
        int 	m_retPort = thePort;
        int 	m_retClientId = 0;

        m_client.eConnect( m_retIpAddress, m_retPort, m_retClientId);
        if (m_client.isConnected()) {
            m_TWS.add("Connected to Tws server version " +
                       m_client.serverVersion() + " at " +
                       m_client.TwsConnectionTime());
            return true;
        } else {
        	return false;
        }
    }

    public void disconnect() {
        // disconnect from TWS
        m_disconnectInProgress = true;
        try {
        	m_client.eDisconnect();
        } catch ( Exception e) {
        	System.out.println("Problem with disconnecting from TWS");
        }
       }

	
    // Requests option details
    public String reqOptionDetails( String symbol, Integer month, Integer year, Integer strikeInt, String putOrCall)
    throws ThetaExceptionExc {

    	// TODO: Check input parameters
    	// symbol must be 1 to 8 alphanumeric (can have a dot)
    	// month must be between 1 and 12
    	// year must be between this year and 5 years in the future
    	// putOrCall must be "P" or "C"
    	
    	if (putOrCall.equals("C") || (putOrCall.equals("P"))){
    		// OK
    	} else {
    		throw new ThetaExceptionExc("putOrCall MUST be C or P! Instead of: " + putOrCall) ;
    	}
    	double strikeDouble = strikeInt.doubleValue()/100;
    	
    	Contract m_optContract = new Contract();
    	
    	m_optContract.m_symbol     = symbol;
    	m_optContract.m_expiry     = DateUtil.getThirdFriday(month, year);
    	m_optContract.m_strike     = strikeDouble;
    	m_optContract.m_right      = putOrCall;

    	m_optContract.m_secType    = "OPT";
    	m_optContract.m_exchange   = "SMART";
    	m_optContract.m_currency   = "USD";
    	m_optContract.m_multiplier = "100";    	
    	
    	ContractDetails initDetails = new ContractDetails();
    	initDetails.m_notes = "INIT";
    	resp.setContractDetails(initDetails);

    	m_client.reqContractDetails(this.getNextUniqueIdForContractDetails(), m_optContract);
    	
    	System.out.println("End of reqActionDetails.  Here is contract:" + m_optContract.toString() );
    	return "OK";
    }

    public void reqAccountUpdates(String accountCode) {
    	m_client.reqAccountUpdates(true, accountCode);
    }

    public void reqId() {
    	m_client.reqIds(ThetaConstants.ONE_INT);
    }
    
    public String reqOptionPrice(ContractDetails det, Integer uniqueIdForMktData) {
    	System.out.println("IN reqOptionPrice");
    	//int m_id = uniqueId;
    	String m_genericTicks = "";
    	boolean m_snapshotMktData = true;

    	// m_opt2 is the m.summary return from the previous
    	Contract m_opt2 = new Contract();
    	
    	m_opt2.m_comboLegsDescrip=null;
    	m_opt2.m_currency="USD";
    	m_opt2.m_exchange="SMART";
    	m_opt2.m_includeExpired=false;
    	m_opt2.m_secId=null;
    	m_opt2.m_secIdType=null;
    	m_opt2.m_secType="OPT";
    	m_opt2.m_underComp=null;

    	m_opt2.m_conId = det.m_summary.m_conId;
    	m_opt2.m_expiry=det.m_summary.m_expiry;
    	m_opt2.m_localSymbol=det.m_summary.m_localSymbol;
    	m_opt2.m_right=det.m_summary.m_right;
    	m_opt2.m_strike=det.m_summary.m_strike;
    	m_opt2.m_symbol=det.m_summary.m_symbol;
    	m_opt2.m_multiplier=det.m_summary.m_multiplier;
    	m_opt2.m_primaryExch=det.m_summary.m_primaryExch;
    	
    	m_client.reqMktData( uniqueIdForMktData, m_opt2, m_genericTicks, m_snapshotMktData);
 
       	return "OK";
    }

    public void cancelMktData(Integer uniqueIdForMktData) {
    	m_client.cancelMktData(uniqueIdForMktData);
    }
    
    public void sleepABit(){
    	try {
    		Thread.sleep(1000);
    	} catch (Exception e ) {
    		System.out.println("IN THREAD.SLEEP Exception: e: "  + e);
    	}
    }

    /**
     * 
     * @param symbol
     * @param enterOrExitCode
     * @param month
     * @param year
     * @param strikeMoneymaker
     * @param strikeInsurance
     * @param putOrCall
     * @param limitPrice
     * @param uniqueNumericId
     * @return boolean
     */
    public boolean enterOrExitSpread(
    		String symbol, 
    		EnterOrExitCode enterOrExitCode,
    		Integer month, Integer year, 
    		Integer strikeMoneymaker, Integer strikeInsurance, 
    		String putOrCall,
    		Integer limitPrice,
    		int uniqueIdForOrder) {
    	
    	log.info("in EnterOrExitSpread: Id: " + uniqueIdForOrder);
    	
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
    	// TODO: Should this just be YYYYMM?
    	con1.m_expiry = DateUtil.getThirdFriday(month, year);
    	con1.m_strike = strikeMoneymaker.doubleValue()/100;
    	con1.m_right = putOrCall;
    	con1.m_multiplier = "100";
    	con1.m_exchange = "SMART";
    	con1.m_currency = "USD";
      	
    	m_client.reqContractDetails(this.getNextUniqueIdForContractDetails(), con1);
    	sleepABit();
    	int Leg1_conId = resp.getContractDetails().m_summary.m_conId;
    	
    	//Second leg
    	Contract con2 = new Contract();
    	con2.m_symbol = symbol;
    	con2.m_secType = "OPT";
    	con2.m_expiry = DateUtil.getThirdFriday(month, year);
    	con2.m_strike = strikeInsurance.doubleValue()/100;
    	con2.m_right = putOrCall;
    	con2.m_multiplier = "100";
    	con2.m_exchange = "SMART";
    	con2.m_currency = "USD";
    	
    	m_client.reqContractDetails(this.getNextUniqueIdForContractDetails(), con2);
    	sleepABit();
    	int Leg2_conId = resp.getContractDetails().m_summary.m_conId;

    	//All conId numbers are delivered by the ContractDetail()

    	ComboLeg leg1 = new ComboLeg(); // for the first leg
    	ComboLeg leg2 = new ComboLeg(); // for the second leg

    	leg1.m_conId = Leg1_conId;
    	leg1.m_ratio = 1;
    	if (enterOrExitCode.equals(EnterOrExitCode.ENTER)){
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
    	if (enterOrExitCode.equals(EnterOrExitCode.ENTER)){
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
    	
    	// ENTER POSITION - limit is NEGATIVE (we're making money on this leg!) 
    	if (enterOrExitCode.equals(EnterOrExitCode.ENTER)){
    		order.m_lmtPrice = ThetaConstants.NEGATIVE_ONE_DOUBLE * (limitPrice.doubleValue()/ThetaConstants.ONE_HUNDRED_DOUBLE);
    	} else {  // EXIT POSITION - BUY IT BACK - limit is POSITIVE 
    		order.m_lmtPrice = (limitPrice.doubleValue()/ThetaConstants.ONE_HUNDRED_DOUBLE);
    	}
    	order.m_tif = "DAY";
    	
    	log.info("in enterOrExitSpread.  Here is contract: " + contract.toString());
    	log.info("in enterOrExitSpread.  Here is order: " + order.toString());
    	
    	m_client.placeOrder(uniqueIdForOrder, contract, order);
    	
    	return true;
    }

    /**
     * 
     */
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
    		Integer uniqueIdForOrder) {
    	
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
    	contract.m_strike = (strikePrice/ThetaConstants.ONE_HUNDRED_DOUBLE);
    	contract.m_right = putOrCall;
    	contract.m_multiplier = "100";
    	contract.m_exchange = "SMART";
    	contract.m_currency = "USD";
      	
    	log.info("Contract: " + contract.toString());
    	
    	m_client.reqContractDetails(this.getNextUniqueIdForContractDetails(), contract);
    	sleepABit();
    	
    	ContractDetails cd = resp.getContractDetails();
    	Contract summary = cd.m_summary;
    	Integer conId = summary.m_conId;
    	contract.m_conId = conId;

    	Order order = new Order();
    	order.m_action = buyOrSellCode.getValue();
    	order.m_totalQuantity = 1;
    	order.m_orderType = lmtOrStpLmtCode.getValue();
    	
    	Double limitFloor = Math.floor(limitPrice/ThetaConstants.ONE_HUNDRED_DOUBLE);
    	order.m_lmtPrice = (limitFloor/ThetaConstants.ONE_HUNDRED_DOUBLE);
    	order.m_auxPrice = ThetaConstants.ZERO_DOUBLE;

    	if (lmtOrStpLmtCode.getValue().equals(LmtOrStpLmtCode.STPLMT.getValue())) {
			if ((stopLimitPrice == null) || (stopLimitPrice.compareTo(ThetaConstants.ZERO_DOUBLE)==0)){
				log.info("IF a STPLMT order - there must be a stopLimitPrice given!");
				return false;
			} else {
				Double stopLimitFloor = Math.floor(stopLimitPrice.doubleValue()/ThetaConstants.ONE_HUNDRED_DOUBLE);
				order.m_auxPrice = (stopLimitFloor/ThetaConstants.ONE_HUNDRED_DOUBLE);
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
    	
    	m_client.placeOrder(uniqueIdForOrder, contract, order);
    	
    	log.info("End of excerciseIndividualOption");
    	return true;
    }


    /**
     * 
     */
    public boolean buyOptionsAtMarket (
    		String symbol, 
    		Integer quantity,
    		Integer month, Integer year, 
    		Integer strikePrice, 
    		String putOrCall,
    		Integer uniqueIdForOrder) {
    	
    	log.info("Top of buyOptionsAtMarket");
    	
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
    	contract.m_strike = (strikePrice/ThetaConstants.ONE_HUNDRED_DOUBLE);
    	contract.m_right = putOrCall;
    	contract.m_multiplier = "100";
    	contract.m_exchange = "SMART";
    	contract.m_currency = "USD";
      	
    	log.info("Contract: " + contract.toString());
    	
    	m_client.reqContractDetails(this.getNextUniqueIdForContractDetails(), contract);
    	
    	ContractDetails cd = null;
    	for (int i=0; i<7; i++) {
        	cd = resp.getContractDetails();
        	if (cd == null) {
        		sleepABit();
        	} else {
        		break;
        	}
    	}
    	
    	Contract summary = cd.m_summary;
    	Integer conId = summary.m_conId;
    	contract.m_conId = conId;

    	Order order = new Order();
    	order.m_action = "BUY";
    	order.m_totalQuantity = quantity;
    	order.m_orderType = "MKT";    	
       	order.m_tif = "GTC";

    	log.info("in excerciseIndividualOption.  Here is contract: " + contract.toString());
    	log.info("in excerciseIndividualOption.  Here is order: " + order.toString());
    	
    	m_client.placeOrder(uniqueIdForOrder, contract, order);
    	
    	log.info("End of buyOptionsAtMarket");
    	return true;
    }

       
    
    public boolean enterSpreadOrig(String symbol, 
    		int month, int year, 
    		Double strikeMoneymaker, Double strikeInsurance,
    		String putOrCall,
    		Double limitPrice,
    		int uniqueNumericId) {

    	//"SPY", 02, 2011, 130.0, "C"
        	// TODO: Check input parameters (See ReqOptionDetails above)
        	// symbol must be 1 to 8 alphanumeric (can have a dot)
        	// month must be between 1 and 12
        	// year must be between this year and 5 years in the future
        	// putOrCall must be "P" or "C"
    	
    	//First leg
    	Contract con1 = new Contract();
    	con1.m_symbol = "SPY";
    	con1.m_secType = "OPT";
     	con1.m_expiry = "201104";
    	con1.m_strike = 139.0;
    	con1.m_right = "C";
    	con1.m_multiplier = "100";
    	con1.m_exchange = "SMART";
    	con1.m_currency = "USD";
      	
    	m_client.reqContractDetails(this.getNextUniqueIdForContractDetails(), con1);
    	sleepABit();
    	int Leg1_conId = resp.getContractDetails().m_summary.m_conId;
    	
    	//Second leg
    	Contract con2 = new Contract();
    	con2.m_symbol = "SPY";
    	con2.m_secType = "OPT";
    	con2.m_expiry = "201104";
    	con2.m_strike = 142.0;
    	con2.m_right = "C";
    	con2.m_multiplier = "100";
    	con2.m_exchange = "SMART";
    	con2.m_currency = "USD";
    	
    	m_client.reqContractDetails(this.getNextUniqueIdForContractDetails(), con2);
    	sleepABit();
    	int Leg2_conId = resp.getContractDetails().m_summary.m_conId;

    	//All conId numbers are delivered by the ContractDetail()

    	ComboLeg leg1 = new ComboLeg(); // for the first leg
    	ComboLeg leg2 = new ComboLeg(); // for the second leg

    	leg1.m_conId = Leg1_conId;
    	leg1.m_ratio = 1;
    	leg1.m_action = "SELL";
    	leg1.m_exchange = "SMART";
    	leg1.m_openClose = 0;
    	leg1.m_shortSaleSlot = 0;
    	leg1.m_designatedLocation = "";

    	leg2.m_conId = Leg2_conId;
    	leg2.m_ratio = 1;
    	leg2.m_action = "BUY";
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

    	log.info("in enterSpread.  Here is contract: " + contract.toString());
    	log.info("in enterSpread.  Here is order: " + order.toString());
    	
    	m_client.placeOrder(uniqueNumericId, contract, order);

    	return true;
    }
    

    public void reqMktDataStk(String stockTicker, Integer uniqueIdForMktData) {
    	System.out.println("TOP OF ThetaClient:ReqMktDataStk");
    	System.out.println("Here is the stock ticker: " + stockTicker);

    	
    	Contract m_contract = new Contract();
    	m_contract.m_conId=0; 
    	m_contract.m_strike=0.0; 
    	m_contract.m_includeExpired=false; 
    	m_contract.m_symbol=stockTicker;
    	m_contract.m_secType="STK"; 
    	m_contract.m_exchange="SMART"; 
    	m_contract.m_currency="USD"; 
    	//m_contract.m_comboLegsDescrip=null; 
    	//m_contract.m_expiry=""; 
    	//m_contract.m_localSymbol=""; 
    	//m_contract.m_multiplier=""; 
    	//m_contract.m_primaryExch="ISLAND"; 
    	//m_contract.m_right=""; 
    	//m_contract.m_secId=""; 
    	//m_contract.m_secIdType=""; 
    	//m_contract.m_underComp=null;
    	
    	int m_id = uniqueIdForMktData;
    	String m_genericTicks = "";
    	boolean m_snapshotMktData = true;
    	
    	m_client.reqMktData( m_id, m_contract, m_genericTicks, m_snapshotMktData);
    	
    	System.out.println("BOTTOM OF ThetaClient:onReqMktDataStk");
    }

    private int getNextUniqueIdForContractDetails(){
    	this.uniqueIdForContractDetails++;
    	return this.uniqueIdForContractDetails;
    }
    

    
    public boolean placeStockOrder(String symbol, 
    		BuyOrSellCode buyOrSell,
    		Integer quantity,
    		Double limitPrice,
    		int uniqueNumericId,
    		OrderTypeCode orderTypeCode) {

    	Contract m_contract = new Contract();
    	//m_contract.m_comboLegsDescrip=null; 
    	//m_contract.m_conId=0; 
    	m_contract.m_currency="USD"; 
    	m_contract.m_exchange="SMART"; 
    	//m_contract.m_expiry=""; 
    	//m_contract.m_includeExpired=false; 
    	//m_contract.m_localSymbol=""; 
    	//m_contract.m_multiplier=""; 
    	//m_contract.m_primaryExch="ISLAND"; 
    	//m_contract.m_right=""; 
    	//m_contract.m_secId=""; 
    	//m_contract.m_secIdType=""; 
    	m_contract.m_secType="STK"; 
    	//m_contract.m_strike=0.0; 
    	m_contract.m_symbol=symbol;
    	//m_contract.m_underComp=null;
    	

    	//OpenOrClosedCode.PENDCLOSE.toString()
    	Order m_order = new Order();
    	m_order.m_action = buyOrSell.toString();
    	m_order.m_totalQuantity = quantity;

    	if (orderTypeCode.equals(OrderTypeCode.LMT)) {
    		m_order.m_orderType = "LMT";
    		m_order.m_lmtPrice = limitPrice;
    	} else {
    		m_order.m_orderType = "MKT";
    	}
    	
    	
    	m_order.m_tif = "GTC";

    	m_client.placeOrder(uniqueNumericId, m_contract, m_order);

    	log.info("in placeStockOrder.  Here is contract: " + m_contract.toString());
    	log.info("in placeStockOrder.  Here is order: " + m_order.toString());

    	return true;
    }
    

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

	
	
    
    /* ***************** END THETA-CUSTOM METHODS ******************** */    
    
    

	void onReqCurrentTime() {
    	m_client.reqCurrentTime();
	}
    

    void onReqContractData() {
        // run m_orderDlg
        //m_orderDlg.show();
        //if( !m_orderDlg.m_rc ) {
        //    return;
        ///}

        // req mkt data
        //m_client.reqContractDetails( m_orderDlg.m_id, m_orderDlg.m_contract );
    }


    void onReqOpenOrders() {
        m_client.reqOpenOrders();
    }

    public void reqOpenOrders() {
        m_client.reqOpenOrders();
    }

    
    void onPlaceOrder() {
    	placeOrder(false);
    }
    
    void placeOrder(int orderId, Contract contract, Order order){
    	System.out.println("In ThetaClient:placeOrder. OrderId: " + orderId);
    	System.out.println("Here is contract: " + contract.toString() );
    	System.out.println("Here is order: " + order.toString() );
    	
    	m_client.placeOrder ( orderId, contract, order );
    }

    
    void placeOrder(boolean whatIf) {
        // run m_orderDlg
        //m_orderDlg.show();
        //if( !m_orderDlg.m_rc ) {
        //    return;
        //}

        //Order order = m_orderDlg.m_order;
        
        // save old and set new value of whatIf attribute
        //boolean savedWhatIf = order.m_whatIf;
        //order.m_whatIf = whatIf;
        
        // place order
        //m_client.placeOrder( m_orderDlg.m_id, m_orderDlg.m_contract, order );
        
        // restore whatIf attribute
        //order.m_whatIf = savedWhatIf;
    }

    void onExerciseOptions() {
       // m_orderDlg.show();
        //if( !m_orderDlg.m_rc ) {
       //     return;
       // }

        // cancel order
        //m_client.exerciseOptions( m_orderDlg.m_id, m_orderDlg.m_contract,
        //                         m_orderDlg.m_exerciseAction, m_orderDlg.m_exerciseQuantity,
        //                          m_orderDlg.m_order.m_account, m_orderDlg.m_override);
    }

    void onCancelOrder() {
        // run m_orderDlg
        //m_orderDlg.show();
        //if( !m_orderDlg.m_rc ) {
        //    return;
        //}

        // cancel order
        //m_client.cancelOrder( m_orderDlg.m_id );
    }


    void  onServerLogging() {
        // get server logging level
        LogConfigDlg dlg = new LogConfigDlg( this);
        dlg.show();
        if( !dlg.m_rc) {
            return;
        }

        // connect to TWS
        m_client.setServerLogLevel( dlg.m_serverLogLevel);
    }

    void  onReqAllOpenOrders() {
        // request list of all open orders
        m_client.reqAllOpenOrders();
    }

    void  onReqAutoOpenOrders() {
        // request to automatically bind any newly entered TWS orders
        // to this API client. NOTE: TWS orders can only be bound to
        // client's with clientId=0.
        m_client.reqAutoOpenOrders( true);
    }

    void  onReqManagedAccts() {
        // request the list of managed accounts
        m_client.reqManagedAccts();
    }

    void onClear() {
        m_tickers.clear();
        m_TWS.clear();
        m_errors.clear();
    }

    void onClose() {
        System.exit(1);
    }

    void onReqExecutions() {
        ExecFilterDlg dlg = new ExecFilterDlg(this);

        dlg.show();
        if ( dlg.m_rc ) {
            // request execution reports based on the supplied filter criteria
            m_client.reqExecutions( dlg.m_reqId, dlg.m_execFilter);
        }
    }


    public void tickPrice( int tickerId, int field, double price, int canAutoExecute) {
        // received price tick
    	String msg = EWrapperMsgGenerator.tickPrice( tickerId, field, price, canAutoExecute);
    	//log.info("In ThetaClient.tickPrice. msg: " + msg);
    	log.info("msg : Parms: tickerId, field, price: " + msg + " : " + tickerId + " : " + field + " : " + price );
    	//System.out.println("Key: BID=1; ASK=2; LAST=4; CLOSE=9");
    	
    	
    	// tickerId == 1 is an OPTION
    	if (tickerId == 1){
    		//switch (field) {
    		//case 6: snapshotOption.setBidPrice(price);
    		//case 7: snapshotOption.setAskPrice(price);

    		//case 9: snapshotOption.setLastPrice(price);
    		//default: ;
    		//}
    		
    	}
    	
    	final int BID        = 1;
        final int ASK        = 2;
        final int LAST       = 4;

        final int HIGH       = 6;
        final int LOW        = 7;
        final int CLOSE      = 9;
        final int OPEN       = 14;

        
		//System.out.println("*** IN ThetaClient.tickPrice.  CLOSE PRICE is: " + price);
		log.info("*** IN ThetaClient.tickPrice.  PRICE is: " + price);
		
		Integer priceInt = (new Float(price * ThetaConstants.ONE_HUNDRED_FLOAT)).intValue();
        if (field == BID){
        	log.info("This is a BID price");
        	resp.setPriceBid(tickerId, priceInt);
		}
        if (field == ASK){
        	log.info("This is an ASK price");
        	resp.setPriceAsk(tickerId, priceInt);
		}
//        if (field == LAST){
//        	log.info("This is a LAST price");
//        	resp.setPriceLast(priceInt);
//		}
//        if (field == HIGH){
//        	log.info("This is a HIGH price");
//        	resp.setPriceHigh(priceInt);
//		}
//        if (field == LOW){
//        	log.info("This is a LOW price");
//        	resp.setPriceLow(priceInt);
//		}
        if (field == CLOSE){
        	log.info("This is a CLOSE price");
        	resp.setPriceClose(tickerId, priceInt);
		}


    	//System.out.println("Here is price: " + price);
    	//m_tickers.add( msg );
    }

    /*
    public void tickOptionComputation( int tickerId, int field, double impliedVol, double delta, double modelPrice, double pvDividend) {
        // received computation tick
    	String msg = EWrapperMsgGenerator.tickOptionComputation( tickerId, field, impliedVol, delta, modelPrice, pvDividend);
    	System.out.println("In ThetaClient.tickOptionComputation. msg: " + msg);
    	m_tickers.add( msg );
    }
    */
    
    public void tickSize( int tickerId, int field, int size) {
        // received size tick
    	String msg = EWrapperMsgGenerator.tickSize( tickerId, field, size);
        m_tickers.add( msg);
    }

    public void tickGeneric( int tickerId, int tickType, double value) {
        // received generic tick
    	String msg = EWrapperMsgGenerator.tickGeneric(tickerId, tickType, value);
        m_tickers.add( msg);
    }

    public void tickString( int tickerId, int tickType, String value) {
        // received String tick
    	String msg = EWrapperMsgGenerator.tickString(tickerId, tickType, value);
        m_tickers.add( msg);
    }
    
    public void tickSnapshotEnd(int tickerId) {
    	String msg = EWrapperMsgGenerator.tickSnapshotEnd(tickerId);
    	m_tickers.add( msg) ;
    }
    
    public void tickEFP(int tickerId, int tickType, double basisPoints, String formattedBasisPoints,
    					double impliedFuture, int holdDays, String futureExpiry, double dividendImpact,
    					double dividendsToExpiry) {
        // received EFP tick
    	String msg = EWrapperMsgGenerator.tickEFP(tickerId, tickType, basisPoints, formattedBasisPoints,
				impliedFuture, holdDays, futureExpiry, dividendImpact, dividendsToExpiry);
        m_tickers.add(msg);
    }

    public void orderStatus( int orderId, String status, int filled, int remaining,
    		double avgFillPrice, int permId, int parentId,
    		double lastFillPrice, int clientId, String whyHeld) {
    	// received order status
    	String msg = EWrapperMsgGenerator.orderStatus( orderId, status, filled, remaining,
    			avgFillPrice, permId, parentId, lastFillPrice, clientId, whyHeld);
    	m_TWS.add(  msg);
    	System.out.println("***** IN ORDERSTATUS *****");
    	System.out.println("here is the info: " + msg);


    	//String msg = EWrapperMsgGenerator.orderStatus( orderId, status, filled, remaining,
    	//        avgFillPrice, permId, parentId, lastFillPrice, clientId, whyHeld);
    	//m_TWS.add(  msg);
    	OrderStatus orderStatus = new OrderStatus();
    	orderStatus.setOrderId(orderId);
    	orderStatus.setStatus(status);
    	orderStatus.setFilled(filled);
    	orderStatus.setRemaining(remaining);
    	orderStatus.setAvgFillPrice(avgFillPrice);
    	orderStatus.setPermId(permId);
    	orderStatus.setParentId(parentId);
    	orderStatus.setLastFillPrice(lastFillPrice);
    	orderStatus.setClientId(clientId);
    	orderStatus.setWhyHeld(whyHeld);

    	log.info("Removing earlier orderStatus (with same orderId) and re-adding: " + orderStatus.toString());
    	orderStatuses.remove(orderStatus);
    	orderStatuses.add(orderStatus);
    	// make sure id for next order is at least orderId+1
    	//m_orderDlg.setIdAtLeast( orderId + 1);
    }

        
    public void openOrder( int orderId, Contract contract, Order order, OrderState orderState) {
    	// received open order

    	OpenOrder openOrder = new OpenOrder(orderId, contract, order, orderState);
    	openOrders.add(openOrder);
    	
    	String openOrderStr = openOrder.toString();
    	System.out.println("In openOrder. " + openOrderStr);
    	
    	String msg = EWrapperMsgGenerator.openOrder( orderId, contract, order, orderState);
    	//System.out.println("Open Order: " + msg);
    	//System.out.println("In ThetaClient.openOrder.  " + msg);
    	//System.out.println("orderId: " + orderId);
    	//System.out.println("contract: " + contract.toString() );
    	//System.out.println("order: " + order.toString() );
    	//System.out.println("orderState: " + orderState);
    	//System.out.println("***ThetaClient.openOrder - END");
    	m_TWS.add( msg) ;
    }
    
    public void openOrderEnd() {
        System.out.println("In ThetaClient.openOrderEnd");
        // received open order end
    	String msg = EWrapperMsgGenerator.openOrderEnd();
    	System.out.println("In ThetaClient.openOrderEnd.  Here is msg: " + msg);
        m_TWS.add( msg) ;
    }

    public void contractDetails(int reqId, ContractDetails contractDetails) {
    	System.out.println("IN ThetaClient.contractDetails");
    	//System.out.println("BEFORE: " + contractDetails.toString() );
    	
    	String msg = EWrapperMsgGenerator.contractDetails( reqId, contractDetails);

    	//System.out.println("AFTER: " + contractDetails.toString() );

    	//System.out.println("IN ThetaClient.contractDetails. msg: " + msg);
    	//m_TWS.add(msg);
    	resp.setContractDetails(contractDetails);
    	resp.setContractDetailsStr(msg);
    	
    	// TODO: Add this in??
    	//snapshotOption.setConId(contractDetails.m_summary.m_conId) ;
    	//snapshotOption.setStrike(contractDetails.m_summary.m_strike);
    	//snapshotOption.setLocalSymbol(contractDetails.m_summary.m_localSymbol);
    }
    
	public void contractDetailsEnd(int reqId) {
		String msg = EWrapperMsgGenerator.contractDetailsEnd(reqId);
		m_TWS.add(msg);
	}

    public void scannerData(int reqId, int rank, ContractDetails contractDetails,
                            String distance, String benchmark, String projection, String legsStr) {
    	String msg = EWrapperMsgGenerator.scannerData(reqId, rank, contractDetails, distance,
    			benchmark, projection, legsStr);
        m_tickers.add(msg);
    }
    
    public void scannerDataEnd(int reqId) {
    	String msg = EWrapperMsgGenerator.scannerDataEnd(reqId);
    	m_tickers.add(msg);
    }

    public void bondContractDetails(int reqId, ContractDetails contractDetails)
    {
    	String msg = EWrapperMsgGenerator.bondContractDetails( reqId, contractDetails);
    	m_TWS.add(msg);
    }

    public void execDetails(int reqId, Contract contract, Execution execution)
    {
    	String msg = EWrapperMsgGenerator.execDetails(reqId, contract, execution);
    	m_TWS.add(msg);
    }
    
    public void execDetailsEnd(int reqId)
    {
    	String msg = EWrapperMsgGenerator.execDetailsEnd(reqId);
    	m_TWS.add(msg);
    }

    public void updateMktDepth( int tickerId, int position, int operation,
                    int side, double price, int size) {
        m_mktDepthDlg.updateMktDepth( tickerId, position, "", operation, side, price, size);
    }

    public void updateMktDepthL2( int tickerId, int position, String marketMaker,
                    int operation, int side, double price, int size) {
        m_mktDepthDlg.updateMktDepth( tickerId, position, marketMaker, operation, side, price, size);
    }

    public void nextValidId( int orderId) {
        // received next valid order id
    	
    	resp.setNextValidId(orderId);
    	String msg = EWrapperMsgGenerator.nextValidId( orderId);
        m_TWS.add(msg) ;
        //m_orderDlg.setIdAtLeast( orderId);
    }

    public void error(Exception ex) {
    	// do not report exceptions if we initiated disconnect
        if (!m_disconnectInProgress) { 
            String msg = EWrapperMsgGenerator.error(ex);
            resp.setErrorMsg(msg);
            //Main.inform( this, msg);            
        }
    }

    public void error( String str) {
    	resp.setErrorMsg(str);
    	String msg = EWrapperMsgGenerator.error(str);
        m_errors.add( msg);
    }

    public void error( int id, int errorCode, String errorMsg) {
   
    	resp.setErrorId(id);
    	resp.setErrorCode(errorCode);
    	resp.setErrorMsg(errorMsg);
    	
    	//resp.setError(errorCode, errorMsg);
    	// received error
    	String msg = EWrapperMsgGenerator.error(id, errorCode, errorMsg);
        m_errors.add( msg);


        for (int ctr=0; ctr < faErrorCodes.length; ctr++) {
            faError |= (errorCode == faErrorCodes[ctr]);
        }
        if (errorCode == MktDepthDlg.MKT_DEPTH_DATA_RESET) {
            m_mktDepthDlg.reset();
        }
    }

    public void connectionClosed() {
        String msg = EWrapperMsgGenerator.connectionClosed();
        //Main.inform( this, msg);
    }

    public void updateAccountValue(String key, String value,
                                   String currency, String accountName) {
    	log.info("IN ThetaClient3.updateAccountValue. log key, value, currency, accountName: " + key + ", " + value + ", " + currency + ", " + accountName);
    	//NetLiquidation, 91995.20, USD, DU71402
    	//LookAheadInitMarginReq, 59644.53, USD, DU71402
    	//LookAheadAvailableFunds, 32350.67, USD, DU71402
		if (key.equals("NetLiquidation")) {
			resp.setNetLiquidation(value);
		} else if (key.equals("LookAheadInitMarginReq")) {
			resp.setLookAheadInitMarginReq(value);
		} else if (key.equals("LookAheadAvailableFunds")){
			resp.setLookAheadAvailableFunds(value);
		}
    	
    	//System.out.println("IN ThetaClient3.updateAccountValue. System.out key, value, currency, accountName: " + key + ", " + value + ", " + currency + ", " + accountName);
        m_acctDlg.updateAccountValue(key, value, currency, accountName);
    }

    public void updatePortfolio(Contract contract, int position, double marketPrice,
        double marketValue, double averageCost, double unrealizedPNL, double realizedPNL,
        String accountName) {

    	PortfolioItem theItem = new PortfolioItem(contract, position, marketPrice,
    						marketValue, averageCost, unrealizedPNL, realizedPNL, accountName);
       	portfolioItems.add(theItem);
    	
       	String theItemStr = theItem.toString();
       	System.out.println("System.out: IN update Portfolio. " + theItemStr);
       	log.info("log.info: IN update Portfolio. " + theItemStr);
       	
    	//System.out.println("IN ThetaClient2.updatePortfolio: position | contract: " + position + " | " + contract.toString() ); 	
    	m_acctDlg.updatePortfolio(contract, position, marketPrice, marketValue,
            averageCost, unrealizedPNL, realizedPNL, accountName);
    }

    public void updateAccountTime(String timeStamp) {
        m_acctDlg.updateAccountTime(timeStamp);
    }
    
    public void accountDownloadEnd(String accountName) {
    	m_acctDlg.accountDownloadEnd( accountName);
    	
    	String msg = EWrapperMsgGenerator.accountDownloadEnd( accountName);
        m_TWS.add( msg);
    }

    public void updateNewsBulletin( int msgId, int msgType, String message, String origExchange) {
        String msg = EWrapperMsgGenerator.updateNewsBulletin(msgId, msgType, message, origExchange);
        JOptionPane.showMessageDialog(this, msg, "IB News Bulletin", JOptionPane.INFORMATION_MESSAGE);
    }

    public void managedAccounts( String accountsList) {
        System.out.println("IN ThetaClient.managedAccounts");
        m_bIsFAAccount = true;
        m_FAAcctCodes = accountsList;
        String msg = EWrapperMsgGenerator.managedAccounts(accountsList);
        m_TWS.add( msg);
    }

    public void historicalData(int reqId, String date, double open, double high, double low,
                               double close, int volume, int count, double WAP, boolean hasGaps) {
        String msg = EWrapperMsgGenerator.historicalData(reqId, date, open, high, low, close, volume, count, WAP, hasGaps);
    	m_tickers.add( msg );
    }
	public void realtimeBar(int reqId, long time, double open, double high, double low, double close, long volume, double wap, int count) {
		String msg = EWrapperMsgGenerator.realtimeBar(reqId, time, open, high, low, close, volume, wap, count);
        m_tickers.add( msg );
	}
    public void scannerParameters(String xml) {
        displayXML(EWrapperMsgGenerator.SCANNER_PARAMETERS, xml);
    }
    
	public void currentTime(long time) {
		String msg = EWrapperMsgGenerator.currentTime(time);
    	m_TWS.add(msg);
	}
	public void fundamentalData(int reqId, String data) {
		String msg = EWrapperMsgGenerator.fundamentalData(reqId, data);
		m_tickers.add(msg);
	}
	public void deltaNeutralValidation(int reqId, UnderComp underComp) {
		String msg = EWrapperMsgGenerator.deltaNeutralValidation(reqId, underComp);
		m_TWS.add(msg);
	}

    void displayXML(String title, String xml) {
        m_TWS.add(title);
        m_TWS.addText(xml);
    }

    public void receiveFA(int faDataType, String xml) {
        displayXML(EWrapperMsgGenerator.FINANCIAL_ADVISOR + " " + EClientSocket.faMsgTypeName(faDataType), xml);
        System.out.println("ThetaClient.In receiveFA");
        switch (faDataType) {
        case EClientSocket.GROUPS:
          faGroupXML = xml ;
          break ;
        case EClientSocket.PROFILES:
          faProfilesXML = xml ;
          break ;
        case EClientSocket.ALIASES:
          faAliasesXML = xml ;
          break ;
      }

      if (!faError &&
          !(faGroupXML == null || faProfilesXML == null || faAliasesXML == null)) {
          FinancialAdvisorDlg dlg = new FinancialAdvisorDlg(this);
          dlg.receiveInitialXML(faGroupXML, faProfilesXML, faAliasesXML);
          dlg.show();

          if (!dlg.m_rc) {
            return;
          }

          m_client.replaceFA( EClientSocket.GROUPS, dlg.groupsXML );
          m_client.replaceFA( EClientSocket.PROFILES, dlg.profilesXML );
          m_client.replaceFA( EClientSocket.ALIASES, dlg.aliasesXML );

      }
    }

    
    
    private void copyExtendedOrderDetails( Order destOrder, Order srcOrder) {
        destOrder.m_tif = srcOrder.m_tif;
        destOrder.m_ocaGroup = srcOrder.m_ocaGroup;
        destOrder.m_ocaType = srcOrder.m_ocaType;
        destOrder.m_openClose = srcOrder.m_openClose;
        destOrder.m_origin = srcOrder.m_origin;
        destOrder.m_orderRef = srcOrder.m_orderRef;
        destOrder.m_transmit = srcOrder.m_transmit;
        destOrder.m_parentId = srcOrder.m_parentId;
        destOrder.m_blockOrder = srcOrder.m_blockOrder;
        destOrder.m_sweepToFill = srcOrder.m_sweepToFill;
        destOrder.m_displaySize = srcOrder.m_displaySize;
        destOrder.m_triggerMethod = srcOrder.m_triggerMethod;
        destOrder.m_outsideRth = srcOrder.m_outsideRth;
        destOrder.m_hidden = srcOrder.m_hidden;
        destOrder.m_discretionaryAmt = srcOrder.m_discretionaryAmt;
        destOrder.m_goodAfterTime = srcOrder.m_goodAfterTime;
        destOrder.m_shortSaleSlot = srcOrder.m_shortSaleSlot;
        destOrder.m_designatedLocation = srcOrder.m_designatedLocation;
        destOrder.m_ocaType = srcOrder.m_ocaType;
        destOrder.m_rule80A = srcOrder.m_rule80A;
        destOrder.m_allOrNone = srcOrder.m_allOrNone;
        destOrder.m_minQty = srcOrder.m_minQty;
        destOrder.m_percentOffset = srcOrder.m_percentOffset;
        destOrder.m_eTradeOnly = srcOrder.m_eTradeOnly;
        destOrder.m_firmQuoteOnly = srcOrder.m_firmQuoteOnly;
        destOrder.m_nbboPriceCap = srcOrder.m_nbboPriceCap;
        destOrder.m_auctionStrategy = srcOrder.m_auctionStrategy;
        destOrder.m_startingPrice = srcOrder.m_startingPrice;
        destOrder.m_stockRefPrice = srcOrder.m_stockRefPrice;
        destOrder.m_delta = srcOrder.m_delta;
        destOrder.m_stockRangeLower = srcOrder.m_stockRangeLower;
        destOrder.m_stockRangeUpper = srcOrder.m_stockRangeUpper;
        destOrder.m_overridePercentageConstraints = srcOrder.m_overridePercentageConstraints;
        destOrder.m_volatility = srcOrder.m_volatility;
        destOrder.m_volatilityType = srcOrder.m_volatilityType;
        destOrder.m_deltaNeutralOrderType = srcOrder.m_deltaNeutralOrderType;
        destOrder.m_deltaNeutralAuxPrice = srcOrder.m_deltaNeutralAuxPrice;
        destOrder.m_continuousUpdate = srcOrder.m_continuousUpdate;
        destOrder.m_referencePriceType = srcOrder.m_referencePriceType;
        destOrder.m_trailStopPrice = srcOrder.m_trailStopPrice;
        destOrder.m_scaleInitLevelSize = srcOrder.m_scaleInitLevelSize;
        destOrder.m_scaleSubsLevelSize = srcOrder.m_scaleSubsLevelSize;
        destOrder.m_scalePriceIncrement = srcOrder.m_scalePriceIncrement;
        destOrder.m_account = srcOrder.m_account;
        destOrder.m_settlingFirm = srcOrder.m_settlingFirm;
        destOrder.m_clearingAccount = srcOrder.m_clearingAccount;
        destOrder.m_clearingIntent = srcOrder.m_clearingIntent;
    }


	@Override
	public void tickOptionComputation(int tickerId, int field,
			double impliedVol, double delta, double optPrice,
			double pvDividend, double gamma, double vega, double theta,
			double undPrice) {
		// TODO Auto-generated method stub
		
	}


	public boolean isProd() { return true; }
	//@Override
	//public void reqMktDataStk(String stockTicker) {
	//	// TODO Auto-generated method stub
	//	
	//}
    
}

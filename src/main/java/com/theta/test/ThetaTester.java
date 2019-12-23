/**
 * 
 */
package com.theta.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.theta.dao.HeartbeatDAO;
import com.theta.db.ThetaSQLIF;
import com.theta.domain.Heartbeat;
import com.theta.domain.Strategy;
import com.theta.enums.ApprovalCode;
import com.theta.enums.LongOrShortCode;
import com.theta.enums.MinOrMaxCode;
import com.theta.enums.OpenOrClosedCode;
import com.theta.enums.OrderTypeCode;
import com.theta.enums.UpOrDownCode;
import com.theta.process.ThetaConstants;
import com.theta.process.ThetaExceptionExc;
import com.theta.process.ThetaUtil;
//import com.theta.dao.HeartbeatDAO;

/**
 * @author pcion
 *
 */
public class ThetaTester {

	Logger logger = Logger.getLogger(this.getClass());
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		ThetaTester it = new ThetaTester();
		it.testEnum();
		//it.testAbs();
		//it.testinlineif();
		//it.testTradeWindow();
		//it.testDateFormat();
		//it.testStrategy();
		//System.out.println("Return val: " + it.testEnoughFunds("89123.12", "20000.34", 16)); 
		//it.testTimeBetween();
		
		//it.testDate2();
		//it.testEnum2();
		//it.testnull();
		//it.testMinMax();
		//it.testHttp();
		//it.testGetNextId();
		//it.testCompare();
		//it.testIntMath();
		//it.testLongOrShort();

		//it.testCal();
		
		//it.testLogging();
		//it.testDoubleToInt();
		//it.testOpenOrClosed();
		//it.testDoubleToInt();
		//it.testSubst();
		//it.testmap();
		//it.testDiv();
		//it.testFtp("C:\\JavaApps\\testftp.txt", "testftp.20120825.txt");
		//it.testCal2();
		//it.testSecurityPrice();
		//	Calendar start = Calendar.getInstance();
	//	Thread.sleep(500);
	//	Calendar end = Calendar.getInstance();
		
	//	it.writeMetrics("theString", start, end );
		//it.intToString();
		
		//boolean retval = it.testSerDeser();
		//boolean retval = it.testDateAfter(400);
		//boolean retval = it.isActualDiffMoreThanTarget(100, 102, 199, "Here is the logStatementIfTrue");
		//System.out.println("Here is retval: " + retval);
			
		//it.testBreak();
		//it.readFile();
		//it.testDateComp();
		//it.testCal();
		//it.dateMinus();
		//it.floatToInt();
		//it.testInteger();
		//it.fiveMinPast();
		//it.enterRecordIntoHeartbeat();
		//it.tester();
		//it.testTime();
		//it.testDateCal();
		//it.testSwitch();
		//it.testBDNull();
		//it.testRound();
		//Calendar cal = Calendar.getInstance();
		//String fmtCal = it.formatCal(cal);
		//System.out.println("Here is formatCal : " + fmtCal);
		//it.getFooToEnter();
		//it.testEnum();
		//it.testWhile();
		System.out.println("END");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void testAbs() {
		Integer foo = -2;
		Integer baar = -1;
		System.out.println("Here is abs foo: " + Math.abs(foo) );
		Integer absFoo = Math.abs(foo);
		Integer absBaar = Math.abs(baar);
		if (absFoo.compareTo(absBaar) > 0 ){
			System.out.println("absFoo is more than absBaar." + absFoo + ":" + absBaar);
		}
		
	}
	
	public void testMap() {
		Map<String, Integer> testMap = new HashMap<String, Integer>();
		testMap.put("foo", 1);
		Integer i = testMap.get("foo");
		System.out.println("Here is i: " + i);
		i = testMap.get("foobb");
		System.out.println("Here is i: " + i);
	}
	

	public void testinlineif() {
		
		boolean foo = true;
		UpOrDownCode upOrDown = (foo) ? UpOrDownCode.UP : UpOrDownCode.DOWN;
		System.out.println("foo, UpOrDownCode: " + foo + ", " + upOrDown.getValue());

		foo = false;
		upOrDown = (foo) ? UpOrDownCode.UP : UpOrDownCode.DOWN;
		System.out.println("foo, UpOrDownCode: " + foo + ", " + upOrDown.getValue());
		
	}
	
	public void testTradeWindow() {
		Calendar now = Calendar.getInstance();
		Calendar tradeStartTime = Calendar.getInstance();
		Calendar tradeEndTime = Calendar.getInstance();

		/**
		 * Trade Window start at 9:47 - after the initial ups and downs of the
		 * day End promptly at 4:02
		 */
		tradeStartTime.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH),
				now.get(Calendar.DATE), ThetaConstants.NINE_OCLOCK_AM,
				new Integer(47));
		tradeEndTime.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH),
				now.get(Calendar.DATE), ThetaConstants.FOUR_OCLOCK_PM,
				new Integer(2));


		System.out.println("now: " + now.getTime());
		System.out.println("start time: " + tradeStartTime.getTime());
		System.out.println("end time: " + tradeEndTime.getTime());
		
		if ((now.after(tradeStartTime) && now.before(tradeEndTime))) {
			System.out.println("Within trading window.");
		} else {
			System.out.println("Running - but OUTSIDE trading window.");
		}
	}
	
	public void testDateFormat() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss");
		String dtFormat = sdf.format(dt);
		System.out.println("Here is dt: " + dtFormat);
	}
	
	
	public void testDateOnly() {
		Calendar earlier = Calendar.getInstance();
		//earlier.set(2014,3,10,12,0,0);
		Calendar later = (Calendar) earlier.clone();
		
		Integer minutesDiff = 70;
		earlier.add(Calendar.MINUTE, -minutesDiff);
		System.out.println("earlier, later: " + earlier.getTime() + ", " + later.getTime() );
		
		Assert.assertFalse(earlier.equals(later));
		
		Calendar testDt = Calendar.getInstance();
		Calendar testDt2 = Calendar.getInstance();
		testDt.set(later.get(Calendar.YEAR), later.get(Calendar.MONTH), later.get(Calendar.DATE),0,0,0);
		testDt2.set(earlier.get(Calendar.YEAR), earlier.get(Calendar.MONTH), earlier.get(Calendar.DATE),0,0,0);

		Assert.assertTrue(testDt.equals(testDt2));
		
		System.out.println("testDt: " + testDt.getTime() );
		
	
	}
	

	public void testTimeBetween() {
		
		Calendar earlier = Calendar.getInstance();
		//earlier.set(2014,3,10,12,0,0);
		Calendar later = (Calendar) earlier.clone();
		
		Integer minutesDiff = 70;
		earlier.add(Calendar.MINUTE, -minutesDiff);
		System.out.println("earlier, later: " + earlier.getTime() + ", " + later.getTime() );
		
		long earlyMillis = earlier.getTimeInMillis();
		long laterMillis = later.getTimeInMillis();
		System.out.println("diff: " + (laterMillis - earlyMillis)/(60 * 1000)  );
		
		//Integer minBetween =  slope.getMarketMinutesBetween(later, earlier);
		//System.out.println("here is min:" + minBetween);
		//Assert.assertTrue(minBetween.equals(minutesDiff));
	}

	
	private boolean testEnoughFunds(String netLiquidation, String availableFunds, Integer stopAtMarginPct) {
		// Ratio of availableFunds/NetLiquidation must be above stopAtMarginPct
		Float netLiquidationF = new Float(netLiquidation); 
		Float availableFundsF = new Float(availableFunds);
		Float stopAtMarginPctF = new Float(stopAtMarginPct);
		
		System.out.println("liquidation (str,int), available (str,int) : " + netLiquidation + " : " + netLiquidationF + " : " + availableFunds + " : " + availableFundsF);

		Float marginPct = (availableFundsF * ThetaConstants.ONE_HUNDRED_INT) / netLiquidationF;
		
		System.out.println("marginPct: " + marginPct);
		System.out.println("stopAtMarginPctF: " + stopAtMarginPctF);
		
		return (marginPct.compareTo(stopAtMarginPctF) > 0 ); 
	}
		
	
	private void testStrategy() {
		Strategy str = new Strategy();
		if ( LongOrShortCode.LONG.equals(LongOrShortCode.LONG) ) {
			System.out.println("Equals long");
		} else {
			System.out.println("Doesn't Equal long");
		}
			
		
		//if ( strategy.getLongOrShortCode() != null && strategy.getLongOrShortCode().equals(LongOrShortCode.LONG) ) {

	}
	
		
	private void testDate2() {
		
		Calendar testDate = Calendar.getInstance();
		System.out.println("Before: " + testDate.getTime());
		testDate.add(Calendar.DATE, -60);
		System.out.println("After: " + testDate.getTime());
	}
	
	private void testEnum2() {
		
		OrderTypeCode orderTypeCode = OrderTypeCode.MKT;
		
		if (orderTypeCode.equals(OrderTypeCode.LMT)) {
			System.out.println("IS LIMIT!");
    	} else {
			System.out.println("IS NOT LIMIT!");
    	}
	}
	
	
/*
	private void testYahooCutover() {
		
		Calendar twentyFiveMinutesAgo = Calendar.getInstance();
		twentyFiveMinutesAgo.set(Calendar.MINUTE, -25);
		Set<SecurityPrice> secPrices = securityPriceDAO.findSecurityPricesByTickerAndSourceSinceTime(position.getSymbol(), "IB", twentyFiveMinutesAgo);

		if ((secPrices.size() > 2) && thetaUtil.areAllPricesIdentical(secPrices)) {
			try {
				Integer yahooPrice = thetaUtil.getCurrentSecurityPriceFromYahoo(strategy.getSymbol());
				insertIntoSecurityPrice(strategy.getSymbol(), yahooPrice, "YAHOO", true, allMovAvgs.loAvgPlus, allMovAvgs.hiAvgMinus);
				underlyingSecurityPrice = yahooPrice;
			} catch (ThetaExceptionExc ex3){
				log.error("Exception in getting Price from Yahoo: ", ex3);
				continue;
			}
		}
	}
*/
	
    private void testHttp() throws IOException {
    	
    	try {

    		String[] stockQuote = new String[6];
    		String theUrl = "http://finance.yahoo.com/d/quotes.csv?s=MSFT&f=sb2b3";
    		System.out.println("Here is the URL: " + theUrl);
    		URL yahoo = new URL(theUrl);
    		URLConnection yc = yahoo.openConnection();
    		BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
    		String inputLine;
    		while ((inputLine = in.readLine()) != null) {
    			System.out.println(inputLine);
    			stockQuote = inputLine.split(",");
    		}
        	in.close();

        	String tickerName = stockQuote[0];
        	String bid = stockQuote[1];
        	String ask = stockQuote[2];
        	Float bidF = Float.parseFloat(bid);
        	Float askF = Float.parseFloat(ask);
        	
        	System.out.println("Here is length: " + stockQuote.length);
        	//System.out.println("Here is 4th element: " + stockQuote[3]);
        	
        	//Long bidPrice = new Long(stockQuote[1]);
        	//Long askPrice = new Long(stockQuote[2]);
        	System.out.println("ticker:bid:ask: " + tickerName.replace("\"", "") + ":" + bidF + ":" + askF);
        	
        	
    	} catch (Exception ex){
    		ex.printStackTrace();
    	}
    }

	

	private void testMinMax() {
		
		String code = "FOO";
		System.out.println("Start");
		try {
		MinOrMaxCode mm = MinOrMaxCode.valueOf(code);
		System.out.println("here is mm: " + mm);
		System.out.println("here is mm - value: " + mm.getValue());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		//if ( strategy.getLongOrShortCode().equals(MinOrMaxCode.))
		
	}
	
	private void testInt() {

		Integer stkInvestAmtDollar = 20000;
		Integer currentSecurityPrice = 595;
		
		Float stkInvestAmtDollarL = new Float(stkInvestAmtDollar);
		Float currentSecurityPriceL = new Float(currentSecurityPrice);
		Float numSharesL = ( (stkInvestAmtDollarL / currentSecurityPriceL) * 100);
		System.out.println("Numshares: " + numSharesL);
		Integer numShares = numSharesL.intValue();
		System.out.println("Numshares: " + numShares);
	}
	

	
	private void testGetNextId() throws ThetaExceptionExc  {
		ThetaUtil thetaUtil = new ThetaUtil();
		Integer previousId = 22;
		Integer uniqueIdForOrder = ThetaConstants.ZERO_INT;
		for (int i=1; i<ThetaConstants.SIX_INT; i++) {
			System.out.println("Here is i: " + i + ". Starting sleep. " + (new Date()));
			thetaUtil.secondsToSleep(i * ThetaConstants.TWO_INT);
			System.out.println("Ending sleep. " + (new Date()) );
			uniqueIdForOrder = 22;
			if (i==4) {
				uniqueIdForOrder=23;
			}
			System.out.println("RequestSeqNo - after client.reqId() in EnterSingleSpread.  Count, Next valid ID: " + i + ", " + uniqueIdForOrder);
			if (uniqueIdForOrder.compareTo(previousId) != 0 ){
				System.out.println("Previous and uniqueId are not the same the same - breaking out!! " + previousId);
				break;
			}
			System.out.println("Previous and uniqueId are the same - trying again with a longer wait!! PreviousId: " + previousId);
		}

		if (previousId.compareTo(uniqueIdForOrder) == 0 ) {
			System.out.println("previousID and uniqueIdForOrder are STILL identical after many tries!!");
		}

		
	}
	
	private void testnull() {
		
		Integer foo = 0;
		
		if (foo == null || foo <= 0) {
			System.out.println("null or less than or equal to zero!");
		}
	}
	
	private void testCompare() {
		
		final Long one = new Long(1);
		final Long two = new Long(2);
		
		if (one.compareTo(one) == 0 )
			System.out.println("one is equal to  one.");
		else
			System.out.println("Fail!");
		
	}
	
	
	private void testLongOrShort(){
		LongOrShortCode lsc = getCode();
		System.out.println("here is lsc: " + lsc.toString());
		if (lsc.equals(LongOrShortCode.LONG)){
			System.out.println("Equals LONG!");
		} else {
			System.out.println("Doesn't equal LONG!");
		}
	
	}
	
	
	private LongOrShortCode getCode(){
		return LongOrShortCode.LONG;
	}
	
	private void testLogging() {
		String theStr = "the quick brown fox";
		logger.info("Here is the str: " + theStr + "\n Byte Array: " + convertByteArrayToStr(theStr.getBytes()));
		
	}

	private String convertByteArrayToStr(byte[] data) {
	    StringBuilder sb = new StringBuilder(data.length);
	    for (int i = 0; i < data.length; ++ i) {
	        if (data[i] < 0) throw new IllegalArgumentException();
	        sb.append(data[i] + ",");
	    }
	    return sb.toString();
	}
	
	
	
	private void testOpenOrClosed() {
		
		OpenOrClosedCode o = OpenOrClosedCode.CLOSED;

		if (o.equals(OpenOrClosedCode.CLOSED)){
			System.out.println("Yes - equals CLOSED!");
		} else
			System.out.println("No - does NOT equal CLOSED");
		
	}
	
	private void testDoubleToInt() {
		
		//BigDecimal bd = new BigDecimal(346.56589);
		//System.out.println("Here is initial bd: " + bd);
		//BigDecimal newBD = bd.round(new MathContext(2));

//		Double commissionDouble = (openOrderIB.getOrderState().m_commission * ThetaConstants.ONE_HUNDRED_DOUBLE) + 1;
//		Integer commissionInt = commissionDouble.intValue();
//		orderState=OrderState [m_commission=1.7976931348623157E308
		
		Double ONE_TOTHE_8TH_DOUBLE = new Double(100000000);
		Double m_commiss = new Double(1.7976931348623157E308);

		Double m_commissx = (m_commiss / ONE_TOTHE_8TH_DOUBLE);
		
		Float commFloat = m_commissx.floatValue();
		System.out.println("Here is commFloat: " + commFloat);
		
		//Double commissionDouble = (m_commiss * ONE_HUNDRED_DOUBLE) + 1;
		//Double commissionDouble = (m_commiss/100000000);
		Integer commissionInt = m_commissx.intValue();

		//		//DecimalFormat twoDForm = new DecimalFormat("#.##");
//		Double doub = (1.7999 * 100);		
//		Integer i = doub.intValue() + 1;
		
		//System.out.println("here is m_commiss: " + m_commiss);
		System.out.println("Here is m_commiss: " + m_commiss);
		System.out.println("here is m_commissx " + m_commissx);
		System.out.println("Here is int: " + commissionInt);
		//System.out.println("Here is max int: " + Integer.MAX_VALUE);
		
//		BigDecimal newBdBD = new BigDecimal(newBdDouble);
//		
//		System.out.println("Here is initial bd: " + bd);
//		System.out.println("Here is newBDDouble: " + newBdDouble);
//		System.out.println("Here is newBD - BD: " + newBdBD);
//		
//		BigDecimal bdTimes100 = bd.multiply(new BigDecimal(100));
		//Math.round(new Float(bdTimes100));

	}

	
	private void testSubst() {

		
		try {
		String theString = "abcdefg";
		//String trunc = theString.substring(1,10);
		//System.out.println("here is trunc: " + trunc);
		} catch (Exception ex) {
			System.out.println("Here is the exception: ");
			ex.printStackTrace();
		}
		
		System.out.println("At bottom of testSubst");
	}
	
	private void testmap() {
		
		Map<Integer, String> testMap = new HashMap<Integer, String>(10);
		
		testMap.put(1, "foo");
		testMap.put(2, "foobaar");
		testMap.put(3, "foobee");
		
		String getTestStr = (String) testMap.get(1);
		System.out.println("Here is getTestStr (foo) " + getTestStr);
		testMap.put(1, "baaaaaar");
		
		getTestStr = (String) testMap.get(1);
		System.out.println("Here is getTestStr (baaaar) " + getTestStr);
		
		testMap.remove(2);
		getTestStr = (String) testMap.get(2);
		System.out.println("Here is getTestStr (null) " + getTestStr);
		
		testMap.put(2,  "fooooobaaaaarrr");
		getTestStr = (String) testMap.get(2);
		System.out.println("Here is getTestStr (foooobaaarrrr) " + getTestStr);
		
		
	}
	private void f() {
		
		boolean f = true;
		boolean g = true;
		boolean h = true;
		
		System.out.println("here is f,g,h: " + f + ":" + g + ":" + h);
	}
	
	private void testFtp(String localFile, String remoteFile){

		boolean storeFile = true, binaryTransfer = false, error = false;
		String server, username, password;
		FTPClient ftp;

		server = "ftp.systemtheta.com";
		username = "theta2@systemtheta.com";
		password = "L1fel0ck89!";

		ftp = new FTPClient();

		try
		{
			int reply;
			ftp.connect(server);
			System.out.println("Connected to " + server + ".");

			// After connection attempt, you should check the reply code to verify
			// success.
			reply = ftp.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply))
			{
				ftp.disconnect();
				System.err.println("FTP server refused connection.");
				System.exit(1);
			}
		}
		catch (IOException e)
		{
			if (ftp.isConnected())
			{
				try
				{
					ftp.disconnect();
				}
				catch (IOException f)
				{
					// do nothing
				}
			}
			System.err.println("Could not connect to server.");
			e.printStackTrace();
			System.exit(1);
		}

		__main:

			try
		{
				System.out.println("top of __main");
				if (!ftp.login(username, password))
				{
					ftp.logout();
					error = true;
					break __main;
				}

				System.out.println("Remote system is " + ftp.getRemoteAddress());

				if (binaryTransfer)
					ftp.setFileType(FTP.BINARY_FILE_TYPE);

				// Use passive mode as default because most of us are
				// behind firewalls these days.
				ftp.enterLocalPassiveMode();

				System.out.println("Here is storeFile: " + storeFile);
				if (storeFile)
				{
					InputStream input;

					//ftp.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
					//ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);

					input = new FileInputStream(localFile);

					System.out.println("About to store file ...");
					ftp.storeFile(remoteFile, input);

					//ftp.storeUniqueFile(input);

					input.close();

				}
				else
				{
					OutputStream output;

					output = new FileOutputStream(localFile);

					ftp.retrieveFile(remoteFile, output);

					output.close();
				}

				ftp.logout();
		}
		catch (FTPConnectionClosedException e)
		{
			error = true;
			System.err.println("Server closed connection.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			error = true;
			e.printStackTrace();
		}
		finally
		{
			if (ftp.isConnected())
			{
				try
				{
					ftp.disconnect();
				}
				catch (IOException f)
				{
					// do nothing
				}
			}
		}

		System.out.println("end of ftp process");
		System.exit(error ? 1 : 0);
		
	} // end main


	private void testDiv() {
		
		int FIRST = 9;
		int SECOND = 2;
		
		int foo = FIRST/SECOND;

		if (FIRST/SECOND < 5)
			System.out.println("LEss than four!: ");
		
	}
	
	private void testFtpOrig(String localFile, String remoteFile){

		boolean storeFile = true, binaryTransfer = false, error = false;
		String server, username, password;
		FTPClient ftp;

		server = "ftp.systemtheta.com";
		username = "theta2@systemtheta.com";
		password = "L1fel0ck89!";

		ftp = new FTPClient();

		try
		{
			int reply;
			ftp.connect(server);
			System.out.println("Connected to " + server + ".");

			// After connection attempt, you should check the reply code to verify
			// success.
			reply = ftp.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply))
			{
				ftp.disconnect();
				System.err.println("FTP server refused connection.");
				System.exit(1);
			}
		}
		catch (IOException e)
		{
			if (ftp.isConnected())
			{
				try
				{
					ftp.disconnect();
				}
				catch (IOException f)
				{
					// do nothing
				}
			}
			System.err.println("Could not connect to server.");
			e.printStackTrace();
			System.exit(1);
		}

		__main:

			try
		{
				System.out.println("top of __main");
				if (!ftp.login(username, password))
				{
					ftp.logout();
					error = true;
					break __main;
				}

				System.out.println("Remote system is " + ftp.getRemoteAddress());

				if (binaryTransfer)
					ftp.setFileType(FTP.BINARY_FILE_TYPE);

				// Use passive mode as default because most of us are
				// behind firewalls these days.
				ftp.enterLocalPassiveMode();

				System.out.println("Here is storeFile: " + storeFile);
				if (storeFile)
				{
					InputStream input;

					//ftp.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
					//ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);

					input = new FileInputStream(localFile);

					System.out.println("About to store file ...");
					ftp.storeFile(remoteFile, input);

					//ftp.storeUniqueFile(input);

					input.close();

				}
				else
				{
					OutputStream output;

					output = new FileOutputStream(localFile);

					ftp.retrieveFile(remoteFile, output);

					output.close();
				}

				ftp.logout();
		}
		catch (FTPConnectionClosedException e)
		{
			error = true;
			System.err.println("Server closed connection.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			error = true;
			e.printStackTrace();
		}
		finally
		{
			if (ftp.isConnected())
			{
				try
				{
					ftp.disconnect();
				}
				catch (IOException f)
				{
					// do nothing
				}
			}
		}

		System.out.println("end of ftp process");
		System.exit(error ? 1 : 0);
		
	} // end main
	
	 
	private void testCal2() {	
		Calendar now = Calendar.getInstance();
		Calendar todayMidnight = Calendar.getInstance();
		todayMidnight.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH),0,0,0);
		System.out.println("Here is todayMid: " + todayMidnight.getTime());
		System.out.println("Here is NOW: " + now.getTime());

		
		if (now.after(todayMidnight)){
			System.out.println("NOW is after midnight");
		} else
			System.out.println("NOW is NOT after midnight");
			
	}
	
	public void writeMetrics(String message, Calendar startTime, Calendar endTime){

	    //Date today = Calendar.getInstance().getTime();
	    // (2) create our "formatter" (our custom format)
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss.SSS");
	    // (3) create a new String in the format we want
	    String startFmt = formatter.format(startTime.getTime());
	    String endFmt = formatter.format(endTime.getTime());
	    // (4) this prints "Folder Name = 2009-09-06-08.23.23"
	    Long diff = endTime.getTimeInMillis() - startTime.getTimeInMillis();
	    System.out.println("METRICS: " + message + "|" + startFmt + "|" + endFmt + "|" + diff);

	   // System.out.println("Here is diff: " + diff);
	}
	
	
	public void testSecurityPrice(){
		
		
		Calendar last90 = Calendar.getInstance();
		last90.add(Calendar.MINUTE, -90);
		Calendar theDateTime = last90;
		String theTicker = "SPY";
		MinOrMaxCode minOrMax = MinOrMaxCode.MIN;
		
		ResultSet rs = null;
		Integer minOrMaxPrice = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(ThetaSQLIF.DB_URL, ThetaSQLIF.DB_USERNAME, ThetaSQLIF.DB_PASSWORD);
			//float price =0;
			//String stratAcctId = "4";

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			String theTimeyyyyMMddHHmm = sdf.format(theDateTime.getTime());

			String theSql;
			if (minOrMax.equals(MinOrMaxCode.MIN))
				theSql = "select min(price) as min_price from security_price where TO_CHAR(created_date, 'YYYYMMDDHHMM') > ? and ticker = ?";
			else 
				theSql = "select max(price) as min_price from security_price where TO_CHAR(created_date, 'YYYYMMDDHHMM') > ? and ticker = ?";

			preparedStatement = connection.prepareStatement(theSql);
			preparedStatement.setString(1, theTimeyyyyMMddHHmm);
			preparedStatement.setString(2, theTicker);
			//statement.setString(3,prod_id);
			rs = preparedStatement.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					minOrMaxPrice = rs.getInt("min_or_max_price");
					System.out.println("Here it is: " + minOrMaxPrice);
				}
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();
			}
		}catch(SQLException e){
			System.out.println("Caught exeption!");
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();

			}catch(Exception e)
			{
				System.out.println("Caught exeption!");
				e.printStackTrace();
			}
		}
		System.out.println("Here is minOrMaxPrice: " + minOrMaxPrice);

	}

	
	
	public void intToString(){
		int theInt = 5;
		String theStr = (new Integer(theInt)).toString();
		System.out.println("theInt, theStr: " + theInt + ", " + theStr);
	
		String str2 = "abcdefghijk";
		String str3 = null;
		if (str2.length() > 10) str2= str2.substring(0,10); 
		
		System.out.println("str2, str3: " + str2 + " : " + str3);
	}
	public boolean testSerDeser(){
	
		TestClass tc = new TestClass();
		tc.setDt(Calendar.getInstance());
		tc.setId("THISISID");
		String outStr = tc.serializeTestClass();
		System.out.println("Here is st: " + outStr);
	
		String payload = null;
		StringTokenizer st = new StringTokenizer(outStr, "][");
		while(st.hasMoreTokens()) {
			String header = st.nextToken();
			payload = st.nextToken();
			System.out.println("Here is header * payload : " + header + "*" + payload);
		} 
		
		StringTokenizer st2 = new StringTokenizer(payload, "|");
		while (st2.hasMoreTokens()){
			//String 
		}
		
		
		return true;
	}

	
	
	public boolean testDateAfter (Integer daysToExpiry) {
		Calendar hailMaryPassAfter = Calendar.getInstance();
		hailMaryPassAfter.set(2011,11,15);
		System.out.println("Here is hailMaryPassAfter: " + hailMaryPassAfter.getTime());

		hailMaryPassAfter.add(Calendar.DAY_OF_YEAR, -daysToExpiry);
		System.out.println("Here is the date to compare: " + hailMaryPassAfter.getTime());
		
		Calendar today = Calendar.getInstance();
		System.out.println("Here is today: " + today.getTime());
		if (hailMaryPassAfter.compareTo(today) >= 0){
			System.out.println("Date is not within range for Hail Mary Pass.  After Date: " + hailMaryPassAfter.getTime());
			return false;
		}
		return true;
	}
	
	
	public boolean isActualDiffMoreThanTarget(Integer actualHigh, Integer actualLow, Integer targetPercentInBP, String logStatementIfTrue){
		
		Float highLowDiff = (float) (actualHigh - actualLow);
		Float actualBPFloat = (highLowDiff / (float) actualHigh) * ThetaConstants.TEN_THOUSAND_FLOAT;
		Integer actualBPInt = actualBPFloat.intValue();
		
		//Integer actualBP = ( ThetaConstants.ONE_HUNDRED_INT * (actualHigh - actualLow))/ actualHigh;
		System.out.println("Here is actualBP: " + actualBPInt);
		System.out.println("Here is target: " + targetPercentInBP);
		if (targetPercentInBP.compareTo(actualBPInt) >=0 ){
			return false;
		}
		System.out.println(logStatementIfTrue + ". Down % (bp): " + actualBPInt + ". Target: " + targetPercentInBP);
		return true;
	}

	
	private void sleep10(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void testBreak(){
		Set<String> strings = new HashSet<String>();
		strings.add("BAAR");
		strings.add("FOO");
		strings.add("BAAR");
		strings.add("BEE");

		for(String str : strings){
			System.out.println("Checking HASHSET: " + str);
		}
		
		for(String str : strings){
			System.out.println("TOP of FOR LOOP. Here is the string: " + str);
			if (str.equals("BEE")){
				System.out.println("Str is BEE - BREAKING!");
				if (str.equals("BEE")){
					System.out.println("Second! Str is BEE - BREAKING!");
					break;
				}
			} else {
			System.out.println("Str is NOT BEE!");
			}
			System.out.println("END of FOR LOOP.  String: " + str);
		}
		System.out.println("OUT OF LOOP!");
	}

	
	
	private void readFile(){
		System.out.println("readFile()");
		try{
			// Open the file that is the first 
			// command line parameter
			FileInputStream fstream = new FileInputStream("C:\\JavaApps\\ThetaFiles\\statement.20110620.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				// Print the content on the console
				
				System.out.println(" ************************************ ");
				System.out.println (strLine);
				System.out.println("     -------------------------        ");
				StringTokenizer st = new StringTokenizer (strLine,"\t"); 
				  while (st.hasMoreTokens ()) {
				    System.out.println (st.nextToken ());
				  }
				System.out.println(" ************************************ ");
				
			}
			//Close the input stream
			in.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	
	private void testDateComp(){
		Calendar sellByDate = Calendar.getInstance();
		sellByDate.set(2011, 5 - 1, 20-2, 12,0,0);
		System.out.println("Sell By Date: " + sellByDate.getTime());
		sleep10();
		Calendar now = Calendar.getInstance();
		System.out.println("Now: " + now.getTime());
		
		if (now.after(sellByDate)){
			System.out.println("NOW IS AFTER SellByDate");
		} else {
			System.out.println("NOW IS NOT AFTER SellByDate");
		}
	}
		
	/**
	 * 
	 */
	private void dateMinus(){
		Calendar foo = Calendar.getInstance();
		foo.set(2011,1,10);
		foo.add(Calendar.DATE, -2);
		System.out.println("Here is the new date: " + foo.getTime());
	}
	/**
	 * 
	 */

	private void testCal(){

		Calendar now = Calendar.getInstance();
		Calendar lastHour = Calendar.getInstance();
		lastHour.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), (now.get(Calendar.HOUR_OF_DAY)-1), ThetaConstants.ZERO_INT, ThetaConstants.ZERO_INT);
		Calendar lastDay = Calendar.getInstance();
		lastDay.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), (now.get(Calendar.DATE)-1), now.get(Calendar.HOUR_OF_DAY), ThetaConstants.ZERO_INT, ThetaConstants.ZERO_INT);
		Calendar lastMonth = Calendar.getInstance();
		lastMonth.set(now.get(Calendar.YEAR), (now.get(Calendar.MONTH)-1), now.get(Calendar.DATE), now.get(Calendar.HOUR_OF_DAY), ThetaConstants.ZERO_INT, ThetaConstants.ZERO_INT);

		System.out.println("hour, day, month: " + lastHour.getTime() + " : " + lastDay.getTime() + " : " + lastMonth.getTime());
		
/*		
		Calendar yesterday = Calendar.getInstance();
		yesterday.set(2012,(1-1),1);
		System.out.println("Here is today: " + yesterday.getTime());
		yesterday.set(Calendar.DAY_OF_MONTH, -1);
		System.out.println("Here is yesterday: " + yesterday.getTime());
*/		
			
		//		Calendar positionExpires = Calendar.getInstance();
//		positionExpires.set(2011, (3-1), 21);
//		Calendar today = Calendar.getInstance();
//
//		System.out.println("Here is positionExpires: " + positionExpires.getTime());
//		System.out.println("Here is today: " + today.getTime());
//
//		if (positionExpires.before(today)) {
//			System.out.println("Position expires BEFORE today! ");
//		} else {
//			System.out.println("Position expires AFTER today! ");
//		}

	}

	
	private void floatToInt(){
	
		Integer foo = new Integer(3499);
		Integer baar = 100;
		Integer fee = (foo/baar)*baar;
		
		System.out.println("Here is the fee; " + fee);
		
		//Float tf = new Float(1.678);
		//Integer ti = (tf.intValue() * 100);

		//System.out.println("Here is the float and the int: " + tf + " : " + ti);

		/*
		BigDecimal bd = new BigDecimal(2.59876);
		Integer i2 = 10000;
		BigDecimal bd2 = bd.multiply(new BigDecimal(i2));
		System.out.println("Here is bd2: " + bd2);
		BigDecimal bd3 = bd2.round(new MathContext(4));
		System.out.println("Here is bd3: " + bd3);
		
		System.out.println("Here is the bd2 precision: " + bd2.precision());
		System.out.println("Here is the bd2 precision: " + bd3.precision());
		
		Integer i3 = bd3.intValue(); 
		System.out.println("Here is i3: " + i3);
		*/
	}
	
	
	
	private void testInteger(){
		Integer foo = new Integer(1);
		System.out.println("HEre is foo max value:" + foo.MAX_VALUE);
		System.out.println("HEre is foo min value:" + foo.MIN_VALUE);
		//HEre is foo max value:21,474,836.47
		//HEre is foo min value:-21,474,836.48
	
	}
	
	private void fiveMinPast(){
		
		Integer minutes = new Integer(5);

		Calendar first = Calendar.getInstance();
		System.out.println("About to sleep ...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar now = Calendar.getInstance();
		
		long diffMills = now.getTimeInMillis() - first.getTimeInMillis();
		System.out.println("Here is diff Mills: " + diffMills);

		long millisToBeat = minutes.longValue();
		
		
	}

	private void testDateCal(){
		
		Calendar day1 = Calendar.getInstance();
		System.out.println("About to sleep ...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if (ThetaUtil.isToday(day1)){ 
			System.out.println("IT IS TODAY");
		} else {
			System.out.println("NOT TODAY");
		}
			
		
	}
	
	private void testWhile(){

		System.out.println("AT TOP");
		
		int i = 0;
		while (i<10){
			System.out.println("in loop.  i is : " + i);
			i++;
			if (i==6){
				System.out.println("i is 6! - about to break - next is AT BOTTOM??");
				break;
			}
		}
		
		System.out.println("AT BOTTOM");
	}
	
	private void testEnum(){

		System.out.println("Top of testEnum.");
		
//		FooCode fooCode = FooCode.DOWNSTR;
//		System.out.println("After setting fooCode.");
//		
//		System.out.println("Here is DOWNSTR String: " + FooCode.DOWNSTR);
		
		//String fooValue = fooCode.getValue();
		//System.out.println("Here is the value: " + fooValue);
		//Assert.assertEquals("DOWNVAL", fooValue);
		
//		if (fooCode.equals(FooCode.DOWNSTR)) {
//			System.out.println("It equals!");
//		}
//		
		if ("APPROVE".equals(ApprovalCode.APPROVE.toString() )) {
			System.out.println("it equals");
		} else {
			System.out.println("it doesnt equal");			
		}
		

		
		
	}
	
	
	
	private void getFooToEnter() {

		Set<Foo> fooToEnter = new HashSet<Foo>();

		for (int i=0; i < 10; i++){
			Foo foo = new Foo();
			foo.setFirst(i + ": this is first");
			foo.setSecond((10-i) + ": this is second");
			System.out.println("Here is foo: " + foo.toString() );

			boolean didItAdd = fooToEnter.add(foo);
			System.out.println("Here is didItAdd: "  + didItAdd);
			System.out.println("Here is number of items: " + fooToEnter.size());
		}

		Foo[] fooArr = fooToEnter.toArray(new Foo[0]);

		//Arrays.sort(fooArr, New Comparator);
		//Sorting array on the basis of employee Name by passing NameComparator
		Arrays.sort(fooArr, new FooComparator());

		System.out.println("\n\nOrder of foo after sorting by foo2 is");    
		for(int i=0; i < fooArr.length; i++){
			System.out.println( "Foo " + (i+1) + " foo2 :: " + fooArr[i].getSecond() );
		}
	}

	
	
	
	private String formatCal(Calendar cal){
		
		return (cal.get(Calendar.YEAR) + "-" 
				+ ( cal.get(Calendar.MONTH) + 1 ) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH) + " "
				+ cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ cal.get(Calendar.MINUTE) + ":"
				+ cal.get(Calendar.SECOND));
	}
	
	private void testRound() {
		
		BigDecimal bd = new BigDecimal(346.56589);
		//System.out.println("Here is initial bd: " + bd);
		//BigDecimal newBD = bd.round(new MathContext(2));
		
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		Double newBdDouble = Double.valueOf(twoDForm.format(bd));		
		BigDecimal newBdBD = new BigDecimal(newBdDouble);
		
		System.out.println("Here is initial bd: " + bd);
		System.out.println("Here is newBDDouble: " + newBdDouble);
		System.out.println("Here is newBD - BD: " + newBdBD);
		
		BigDecimal bdTimes100 = bd.multiply(new BigDecimal(100));
		//Math.round(new Float(bdTimes100));

	}
	
	private void testBDNull(){
		
		BigDecimal bd = null;
		//bd = new BigDecimal(0);
		
		if (bd == null) {
			System.out.println("bd is null - ALL IS OK");
		} else {
			if (bd.equals(new BigDecimal(0))){
				System.out.println("bd is 0 - ALL IS OK");
			} else {
				System.out.println("bd is NOT 0 (AND NOT NULL) - IT'S BEEN SET!");
			}
		}
		
		if ((bd != null) && !(bd.equals(new BigDecimal(0)))){
			System.out.println("IS NOT NULL and NOT ZERO ");
		} else {
			System.out.println("IS OTHER THAN NOT null and NOT ZERO");
		}
		
		
	}
	
	private void testSwitch() {

		Double strike = new Double(133.0);
		int strikeInt = strike.intValue();
		System.out.println("Here is strikeInt: " + strikeInt);
		Float price;

			switch (strikeInt){
				case 131: price = new Float(2.26); break;
				case 132: price = new Float(1.68); break;
				case 133: price = new Float(1.21); break;
				case 134: price = new Float(0.84); break;
				case 135: price = new Float(0.55); break;
				case 136: price = new Float(0.36); break;
				default: price = new Float(0.23) ; break;
			}

			System.out.println("Here is price: " + price);
	}
	
	private void testDate(){
		
	}
	
	/**
	 * 
	 */
	/**
	 * 
	 */
	private void testTime() {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -60);
		System.out.println("Here is cal minus 60: " + cal.get(Calendar.DAY_OF_MONTH) + cal.get(Calendar.MONTH)+cal.get(Calendar.YEAR) );
		
		/*
		 // (1) get today's date
	    Date today = Calendar.getInstance().getTime();

	    // (2) create our "formatter" (our custom format)
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");

	    // (3) create a new String in the format we want
	    String folderName = formatter.format(today);
	    
	    // (4) this prints "Folder Name = 2009-09-06-08.23.23"
	    System.out.println("Folder Name = " + folderName);
		*/
		

	    Date today = Calendar.getInstance().getTime();
	    // (2) create our "formatter" (our custom format)
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    // (3) create a new String in the format we want
	    String formattedDate = formatter.format(today);
	    // (4) this prints "Folder Name = 2009-09-06-08.23.23"
	    System.out.println("Folder Name = " + formattedDate);

		
		
		/*
       	String myString = "20110203:0930-1615;20110204:0930-1615";

       	String[] dateTimes = myString.split(";");

       	System.out.println("Here is dateTimes[1]: " + dateTimes[1]);

       	System.out.println("todayOpen:");
       	Calendar todayOpen = getCal(dateTimes[0], "O");
       	System.out.println("todayClose:");
       	Calendar todayClose = getCal(dateTimes[0], "C");
       	System.out.println("nextOpen:");
       	Calendar nextOpen = getCal(dateTimes[1], "O");
       	System.out.println("nextClose:");
       	Calendar nextClose = getCal(dateTimes[1], "C");
       	*/
		
	}

	
	Calendar getCal(String inString, String openOrClose){
		Calendar cal = Calendar.getInstance();
		try {

			String dateString = null;
			String[] dp = inString.split("-");
			if (openOrClose.equals("O")){
				dateString = dp[0];
			} else if (openOrClose.equals("C")){
				String[] dp2 = dp[0].split(":");
				dateString = dp2[0] + ":" + dp[1]; 
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd:hhmm");
			Date d1 = df.parse(dateString);
			cal.setTime(d1);

			System.out.println("Here is cal: " + cal.get(Calendar.YEAR) + ":" + cal.get(Calendar.MONTH) + ":" +
					cal.get(Calendar.DAY_OF_MONTH) + ":" + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
		} catch (Exception e){
			System.out.println("Caught excpetion");
		}
		return cal;
	}
	
	
	public Integer enterRecordIntoHeartbeat( ) {
			
		System.out.println("Enter Record Into Heartbeat!");
		Heartbeat heartbeatRet = null;
		Integer maxHeartbeatIdPlusOne;

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		HeartbeatDAO heartbeatDAO = (HeartbeatDAO) ctx.getBean("HeartbeatDAO");
		
		try {
			Heartbeat heartbeat = new Heartbeat();
			//Heartbeat maxHeartbeat = heartbeatDAO.findMaxHeartbeat();
			
			//maxHeartbeatIdPlusOne = maxHeartbeat.getHeartbeatId() + ThetaConstants.ONE_INT;
			//heartbeat.setHeartbeatId(ThetaConstants.INIT_ID);

			Calendar rightNow = Calendar.getInstance();
			heartbeat.setHeartbeatDate(rightNow);
			heartbeat.setInProcess(ThetaConstants.YES);

			heartbeat.setThreadId(ThetaConstants.ONE_INT);
			
			long timeInMillis = rightNow.getTimeInMillis();			
			heartbeat.setCreatedBy(new Long(timeInMillis).toString());
			heartbeat.setCreatedDate(rightNow);
		
			heartbeatRet = heartbeatDAO.store(heartbeat);
			heartbeatDAO.flush();
			
		} catch (Exception e) {
			System.out.println("Exception in enterRecordIntoHeartbeat: " + e.getMessage() );
		}		

		return heartbeatRet.getHeartbeatId();
	}

	
	public void tester()
	{
		System.out.println("At top of TESTER");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		HeartbeatDAO heartbeatDAO = (HeartbeatDAO) ctx.getBean("HeartbeatDAO");
		
		Heartbeat heartbeat = new Heartbeat();
		heartbeat.setHeartbeatId(ThetaConstants.INIT_ID);
		Calendar rightNow = Calendar.getInstance();
		System.out.println("Here is rightNOW: " + rightNow );
		
		rightNow.getTime();
		
		heartbeat.setHeartbeatDate(rightNow);
		heartbeat.setInProcess("Y");
		heartbeat.setThreadId(1);
		heartbeat.setCreatedBy("thetaSystem");
		heartbeat.setCreatedDate(rightNow);
		
		Heartbeat heartbeatRet = heartbeatDAO.store(heartbeat);
		heartbeatDAO.flush();
		
/*
		boolean areExistingLiveProcesses_b;

		Set<Heartbeat> heartbeats = heartbeatDAO.findHeartbeatByInProcess("Y");

		System.out.println("Here is heartbeats length: " + heartbeats.toArray().length );

		Object[] heartbeatArr = heartbeats.toArray();
		Integer heartbeatId;
		int idToSetBack = 0;
		
		for (int i = 0 ; i < heartbeatArr.length; i++ ){
			Heartbeat hb = (Heartbeat) heartbeatArr[i];
			if ( ThetaConstants.YES.equalsIgnoreCase(hb.getInProcess()) ) {
				heartbeatId = hb.getHeartbeatId();
				System.out.println("There are processes in process. i = " + i);
				System.out.println("Heartbeat ID: " + heartbeatId);
				idToSetBack = heartbeatId;
				break;
			}
		}

		System.out.println("Out of the loop!");
		if ( idToSetBack != 0 ) {
			//heartbeatDAO.executeQueryByName("update heartbeat set in_processs='N' where heartbeat_id = " + idToSetBack );
			//heartbeatDAO.executeQueryByName("updateHeartbeatSetInProcess", idToSetBack);
			Heartbeat hb = heartbeatDAO.findHeartbeatByHeartbeatId(idToSetBack);
			hb.setInProcess(ThetaConstants.NO);		
			heartbeatDAO.store(hb);
			heartbeatDAO.flush();
		}
*/		
		
//		Iterator heartbeatIter = heartbeats.iterator();
		
		//for (int i=0; i < heartbeats.toArray().length; i++) {

		//	Heartbeat hb = heartbeats.toArray().
		//	System.out.println("In heartbeats for loop: "+ heartbeats.getClass() )			
		//}
		
		//Iterator heartbeatIter = heartbeats.iterator();
		
//		while (heartbeatIter.hasNext()){
//			System.out.println( "In heartbeatIter: " + heartbeatIter.toString() + heartbeatIter.getClass() );
//		}

		
		
		//String[] beans = ctx.getBeanDefinitionNames();
		
		//System.out.println("At middle of Context Example");
		
		//HeartbeatDAO heartbeatDAO = (HeartbeatDAO) ctx.getBean("HeartbeatDAO");
		//HeartbeatService heartbeatService = (HeartbeatService) ctx.getBean("heartbeatService");
		
		//Heartbeat heartbeat = new Heartbeat();
		//heartbeat.setHeartbeatId(21);
		//Calendar heartbeatDate = Calendar.getInstance();
		//heartbeatDate.set(2011,1,15);		
		//heartbeat.setHeartbeatDate(heartbeatDate);
		//heartbeat.setInProcess("Y");
		//heartbeat.setThreadId(2);
		//heartbeat.setCreatedBy("pcion");
		//Calendar rightNow = Calendar.getInstance();
		//heartbeat.setCreatedDate(rightNow);

		//heartbeatService.saveHeartbeat(heartbeat);
		
		//Heartbeat heartbeatRet = heartbeatDAO.store(heartbeat);
		//heartbeatDAO.flush();
		System.out.println("At bottom of Context Example");
	}


	
	/* 
 
	   1.       package examples;
	   2.        
	   3.       /*
	   4.       Java Comparator example.    
	   5.       This Java Comparator example describes how java.util.Comparator
	   6.       interface is implemented to compare Java custom class objects.    
	   7.        
	   8.       This Java Comparator is passed to Collection's sorting method
	   9.       (for example Collections.sort method) to perform sorting of Java custom
	  10.       class objects.   11.        
	  12.       /
	  13.        
	  14.       import java.util.*;
	  15.        
	  16.       /*
	  17.       java.util.Comparator interface declares two methods,
	  18.       1) public int compare(Object object1, Object object2) and
	  19.       2) boolean equals(Object object)
	  20.       /
	  21.        
	  22.       /*
	  23.       We will compare objects of the Employee class using custom comparators
	  24.       on the basis of employee age and name.   
	  25.       /
	  26.        
	  27.       class Employee{
	  28.          
	  29.           private int age;    
	  30.           private String name;
	  31.          
	  32.           public void setAge(int age){
	  33.               this.age=age;    
	  34.           }
	  35.          
	  36.           public int getAge(){
	  37.               return this.age;    
	  38.           }
	  39.          
	  40.           public void setName(String name){
	  41.               this.name=name;    
	  42.           }
	  43.          
	  44.           public String getName(){    
	  45.               return this.name;    
	  46.           }
	  47.       }
	  48.        
	  49.       /*
	  50.       User defined Java comparator.   
	  51.       To create custom java comparator, implement Comparator interface and
	  52.       define compare method.   
	  53.        
	  54.       The below given comparator compares employees on the basis of their age.   
	  55.       /
	  56.        
	  57.       class AgeComparator implements Comparator{
	  58.          
	  59.           public int compare(Object emp1, Object emp2){
	  60.          
	  61.               /*
	  62.                * parameter are of type Object, so we have to downcast it
	  63.                * to Employee objects
	  64.                /
	  65.              
	  66.               int emp1Age = ((Employee)emp1).getAge();        
	  67.               int emp2Age = ((Employee)emp2).getAge();
	  68.              
	  69.               if(emp1Age > emp2Age)
	  70.                   return 1;
	  71.               else if(emp1Age < emp2Age)
	  72.                   return -1;
	  73.               else
	  74.                   return 0;    
	  75.           }
	  76.          
	  77.       }
	  78.        
	  79.       /*
	  80.       The below given comparator compares employees on the basis of their name.   
	  81.       /
	  82.        
	  83.       class NameComparator implements Comparator{
	  84.        
	  85.           public int compare(Object emp1, Object emp2){    
	  86.        
	  87.               //parameter are of type Object, so we have to downcast it to Employee objects
	  88.              
	  89.               String emp1Name = ((Employee)emp1).getName();        
	  90.               String emp2Name = ((Employee)emp2).getName();
	  91.              
	  92.               //uses compareTo method of String class to compare names of the employee
	  93.               return emp1Name.compareTo(emp2Name);
	  94.          
	  95.           }
	  96.        
	  97.       }
	  98.        
	  99.       /*
	 100.       This Java comparator example compares employees on the basis of
	 101.       their age and name and sort them in that order.  
	 102.       /
	 103.        
	 104.       public class JavaComparatorExample{
	 105.          
	 106.           public static void main(String args[]){
	 107.              
	 108.               //Employee array which will hold employees
	 109.               Employee employee[] = new Employee[2];
	 110.              
	 111.               //set different attributes of the individual employee.  112.               employee[0] = new Employee();
	 113.               employee[0].setAge(40);
	 114.               employee[0].setName("Joe");
	 115.              
	 116.               employee[1] = new Employee();
	 117.               employee[1].setAge(20);
	 118.               employee[1].setName("Mark");
	 119.              
	 120.               System.out.println("Order of employee before sorting is");
	 121.               //print array as is.  
	 122.               for(int i=0; i < employee.length; i++){
	 123.                   System.out.println( "Employee " + (i+1) + " name :: " + employee[i].getName() + ", Age :: " + employee[i].getAge());
	 124.               }
	 125.              
	 126.               /*
	 127.               Sort method of the Arrays class sorts the given array.        
	 128.               Signature of the sort method is,        
	 129.               static void sort(Object[] object, Comparator comparator)
	 130.              
	 131.               IMPORTANT: All methods defined by Arrays class are static. Arrays class        
	 132.               serves as a utility class.  
	 133.               /
	 134.              
	 135.               //Sorting array on the basis of employee age by passing AgeComparator
	 136.               Arrays.sort(employee, new AgeComparator());
	 137.               System.out.println("\n\nOrder of employee after sorting by employee age is");
	 138.              
	 139.               for(int i=0; i < employee.length; i++){
	 140.                   System.out.println( "Employee " + (i+1) + " name :: " + employee[i].getName() + ", Age :: " + employee[i].getAge());
	 141.               }
	 142.              
	 143.               //Sorting array on the basis of employee Name by passing NameComparator
	 144.               Arrays.sort(employee, new NameComparator());
	 145.              
	 146.               System.out.println("\n\nOrder of employee after sorting by employee name is");    
	 147.               for(int i=0; i < employee.length; i++){
	 148.                   System.out.println( "Employee " + (i+1) + " name :: " + employee[i].getName() + ", Age :: " + employee[i].getAge());
	 149.               }
	 150.          
	 151.           }
	 152.        
	 153.       }
	 154.        
	 155.       /*
	 156.       OUTPUT of the above given Java Comparable Example would be :
	 157.       Order of employee before sorting is
	 158.       Employee 1 name :: Joe, Age :: 40
	 159.       Employee 2 name :: Mark, Age :: 20
	 160.       Order of employee after sorting by employee age is
	 161.       Employee 1 name :: Mark, Age :: 20
	 162.       Employee 2 name :: Joe, Age :: 40
	 163.       Order of employee after sorting by employee name is
	 164.       Employee 1 name :: Joe, Age :: 40
	 165.       Employee 2 name :: Mark, Age :: 20
	 166.       */

	
}

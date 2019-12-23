package com.theta.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ib.client.ContractDetails;
import com.theta.client.DateUtil;
import com.theta.client.OpenOrder;
import com.theta.client.OrderStatus;
import com.theta.client.ThetaClientInterface;
import com.theta.dao.HeartbeatDAO;
import com.theta.dao.ProfitLossDAO;
import com.theta.dao.RequestSeqDAO;
import com.theta.dao.SpreadDAO;
import com.theta.dao.ThetaExceptionDAO;
import com.theta.domain.CoveredOption;
import com.theta.domain.Heartbeat;
import com.theta.domain.Position;
import com.theta.domain.ProfitLoss;
import com.theta.domain.RequestSeq;
import com.theta.domain.SecurityPrice;
import com.theta.domain.Spread;
import com.theta.domain.Strategy;
import com.theta.domain.ThetaException;
import com.theta.enums.MktOpenOrClosedCode;
import com.theta.enums.OpenOrClosedCode;


/**
 * @author pcion
 *
 */

@Service
public class ThetaUtil {


	ThetaExceptionDAO thetaExceptionDAO;
	HeartbeatDAO heartbeatDAO;
	
	public ThetaUtil(ThetaExceptionDAO _thetaExceptionDAO, HeartbeatDAO _heartbeatDAO){
		thetaExceptionDAO = _thetaExceptionDAO;
		heartbeatDAO = _heartbeatDAO;
	}

	public ThetaUtil(){
		
	}
	
	/**
	 * The Logger
	 *
	 */
	private static Logger log = Logger.getLogger(ThetaUtil.class);
	
	public void setAllHeartbeatsToNo() 
	throws ThetaExceptionExc {
		try {

			Set<Heartbeat> heartbeats = heartbeatDAO.findHeartbeatByInProcess(ThetaConstants.YES);

			Object[] heartbeatArr = heartbeats.toArray();
			
			for (int i = 0 ; i < heartbeatArr.length; i++ ){
				Heartbeat hb = (Heartbeat) heartbeatArr[i];
				log.info("Setting this process back to N.  In process: " + hb.getHeartbeatId() + ". ");
	
				hb.setInProcess(ThetaConstants.NO);		
				hb.setUpdatedBy("setAllHeartbeatsToNo");
				hb.setUpdatedDate(Calendar.getInstance());
				heartbeatDAO.store(hb);
				heartbeatDAO.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception in setAllHeartbeatsToNo (within exception): " + e.getMessage() );
			throw new ThetaExceptionExc(e);
		}		
	}
	
	public static boolean isNullOrZero(Integer myInt) {
		if (myInt == null){
			return true;
		}
		if (myInt.equals(new Integer(ThetaConstants.ZERO_INT))){
			return true;
		}
		return false;
	}

	
	public static boolean isNullOrZero(BigDecimal myInt) {
		if (myInt == null){
			return true;
		}
		if (myInt.equals(new BigDecimal(ThetaConstants.ZERO_INT))){
			return true;
		}
		return false;
	}

	
	public void recordThetaException( ThetaExceptionExc e )
	throws ThetaExceptionExc {

		try {
			StringBuffer buf = new StringBuffer();

			ThetaException thetaException = new ThetaException();

			StackTraceElement[] ste = e.getStackTrace();
			for ( int i=0; i < ste.length; i++){
				buf.append(ste[i].getClassName() + " " + ste[i].getMethodName() + "\n" );
				System.out.println("EXC LINE: " + ste[i].getClassName() + " " + ste[i].getMethodName() + "\n" );
			}
			thetaException.setStackTrace( buf.toString().getBytes() );

			thetaException.setCreatedBy("thetaUser");
			Calendar rightNow = Calendar.getInstance();
			thetaException.setCreatedDate(rightNow);
			thetaException.setExceptionText( e.getMessage() );
			thetaException.setThetaExceptionId( ThetaConstants.INIT_ID );		

			thetaExceptionDAO.store(thetaException);
			thetaExceptionDAO.flush();
		} catch (Exception e2) {
			e2.printStackTrace();
			log.info("Exception in record ThetaException: " + e2.getMessage() );
			throw new ThetaExceptionExc(e2);
		}		
	}
	
	public Integer enterRecordIntoHeartbeat( ) 
	throws ThetaExceptionExc {

		return enterRecordIntoHeartbeat("");
	}

	
	/**
	 */
	public Integer enterMetricsIntoHeartbeat(String message, Integer stratAcctId, Calendar startCal, Calendar endCal ) 
	throws ThetaExceptionExc {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss.SSS");

		String startFmt = formatter.format(startCal.getTime());
		String endFmt = formatter.format(endCal.getTime());

		Long diff = endCal.getTimeInMillis() - startCal.getTimeInMillis();
		return enterRecordIntoHeartbeat("METRICS: " + message + "|" + stratAcctId + "|" + startFmt + "|" + endFmt + "|" + diff);
	}


	/**
	 * @param ctx
	 * @throws ThetaExceptionExc
	 * @returns Integer - record ID
	 */
	public Integer enterRecordIntoHeartbeat( String heartbeatLog ) 
	throws ThetaExceptionExc {

		
/*		
		Heartbeat heartbeatRet;
		//Integer maxHeartbeatIdPlusOne;
		
		try {
			Heartbeat heartbeat = new Heartbeat();
			//Heartbeat maxHeartbeat = heartbeatDAO.findHeartbeatByHeartbeatId(3084,1,10);
			
			//maxHeartbeatIdPlusOne = maxHeartbeat.getHeartbeatId() + ThetaConstants.ONE_INT;
			heartbeat.setHeartbeatId(ThetaConstants.INIT_ID);

			Calendar rightNow = Calendar.getInstance();
			heartbeat.setHeartbeatDate(rightNow);
			heartbeat.setInProcess(ThetaConstants.YES);

			heartbeat.setThreadId(ThetaConstants.ONE_INT);
			heartbeat.setHeartbeatLog(heartbeatLog);
			
			long timeInMillis = rightNow.getTimeInMillis();			
			heartbeat.setCreatedBy(new Long(timeInMillis).toString());
			heartbeat.setCreatedDate(rightNow);
		
			
			heartbeatRet = heartbeatDAO.store(heartbeat);
			heartbeatDAO.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception in enterRecordIntoHeartbeat: " + e.getMessage() );
			throw new ThetaExceptionExc(e);
		}		

		return heartbeatRet.getHeartbeatId();
*/
		return 0;
	}

	
	public static long secondsInPast( Calendar cal ){
		
		Calendar rightNow = Calendar.getInstance();
		rightNow.getTimeInMillis();
		
		long rightNowMilli = rightNow.getTimeInMillis();
		long calMilli = cal.getTimeInMillis();
		long diffMilli = rightNowMilli - calMilli;
		long diffSeconds = diffMilli/1000;
			
		//System.out.println("Millis - now, cal, diff, diffSeconds: " + rightNowMilli + " , " + calMilli + " , " + diffMilli + " , " + diffSeconds);
		
		return diffSeconds;
	}

	
	public static long daysInPast( Calendar cal ) {
		
		Integer SECONDS_IN_HOUR = 3600;
		Integer HOURS_IN_DAY = 24;
		
		long secondsInPast = secondsInPast(cal);
		return (secondsInPast / SECONDS_IN_HOUR / HOURS_IN_DAY);
		
	}
	
	public boolean areExistingLiveProcesses( ) 
	throws ThetaExceptionExc {

		Set<Heartbeat> heartbeats = heartbeatDAO.findHeartbeatByInProcess(ThetaConstants.YES);

		Object[] heartbeatArr = heartbeats.toArray();
		
		for (int i = 0 ; i < heartbeatArr.length; i++){
			Heartbeat hb = (Heartbeat) heartbeatArr[i];
			if (ThetaConstants.YES.equalsIgnoreCase(hb.getInProcess())) {
				log.info("There are processes running - quitting this one.  In process: " + hb.getHeartbeatId() + "TIME: " + hb.getCreatedDate() );				
				// TODO: Check if it's been more than 5 minutes since the last live process
				//  IF SO - turn it to "N" and move on...
				long secondsAgo = secondsInPast( hb.getHeartbeatDate() );
				if ( secondsAgo > ThetaConstants.secondsToWaitToOverride ){
					log.info("Overriding - setting all to 0");
				}
				
				return(true);
			}
		}
		return(false);
	}

	
	/**
	 * @param secondsToSleep
	 * @throws ThetaExceptionExc
	 */
	public void secondsToSleep(long seconds)
	throws ThetaExceptionExc {
		
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			throw new ThetaExceptionExc(e);
		}
	}

	/**
	 * @param secondsToSleep
	 * @throws ThetaExceptionExc
	 */
	public static void secondsToSleepNoThrow(long seconds)
	{
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			System.out.println("SLEEP interrupted. CONTINUING ...");
			log.error("Sleep interrupted. CONTINUING ..." , e);
		}
	}

	
	/**
	 * @param millisToSleep
	 * @throws ThetaExceptionExc
	 */
	public void millisToSleep(long millis)
	throws ThetaExceptionExc {
		
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new ThetaExceptionExc(e);
		}
		
	}

	public static boolean trueFalseToBoolean(String trueOrFalse) 
	throws ThetaExceptionExc {

		if (trueOrFalse == null) {
			throw new ThetaExceptionExc("Parameter must be either 'T' or 'F'");
		}

		if (trueOrFalse.equalsIgnoreCase(ThetaConstants.TRUE)){
			return true;
		}
		
		if (trueOrFalse.equalsIgnoreCase(ThetaConstants.FALSE)){
			return false;
		}

		throw new ThetaExceptionExc("Parameter must be either 'T' or 'F'");
		
	}
	
	
	public static String formatCalToSecond(Calendar cal){

		if (cal == null) {
			return ("NULL Date");
		}
		
	    Date toFormat = cal.getTime();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = formatter.format(toFormat);

	    return formattedDate;
	}

	public static String formatCalToDay(Calendar cal){

		if (cal == null) {
			return ("NULL Date");
		}
		
	    Date toFormat = cal.getTime();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String formattedDate = formatter.format(toFormat);

	    return formattedDate;
	}
	
	
	
	/**
	 * Return true if the date is the same as today
	 * otherwiser false
	 * @param cal
	 * @return
	 */
	public static boolean isToday(Calendar cal){
		if (cal == null) {
			return false;
		}

		String calDate = formatCalToDay(cal);
		String todayDate = formatCalToDay(Calendar.getInstance());
		if (calDate.equals(todayDate)){
			return true;
		} else {
			return false;
		}
	}

	public static boolean isWithinLastTwoMinutes(Calendar cal){
		
		if (cal == null) {
			return false;
		}
		
		Calendar now = Calendar.getInstance();
		
		long diffMillis = now.getTimeInMillis() - cal.getTimeInMillis();

		if (diffMillis < ThetaConstants.TWO_MINUTES_IN_MILLIS) {
			return true;
		} else {
			return false;
		}
	}
	

	/**
	 * Initialize the next Request Sequence ID from the response from IB
	 * @param requestSeqDAO
	 * @return
	 */
	public static int initializeNextRequestSeqNo(RequestSeqDAO requestSeqDAO, Integer nextRequestSeqNo)
	throws ThetaExceptionExc {
		
		
		log.debug("RequestSeqNo - in initializeNextRequestSeqNo: " + nextRequestSeqNo );
		try {
			RequestSeq requestSeq = requestSeqDAO.findRequestSeqByRequestSeqId(ThetaConstants.ONE_INT);
			
			requestSeq.setRequestSeqNo(nextRequestSeqNo);
			requestSeq.setUpdatedBy("ThetaUtil - initialize");
			requestSeq.setUpdatedDate(Calendar.getInstance());

			requestSeqDAO.store(requestSeq);
			requestSeqDAO.flush();
		} catch (DataAccessException e){
			throw new ThetaExceptionExc("Data Access Exception" + e);
		}
		
		return nextRequestSeqNo;
	}
	
	/**
	 * Get the next Request Sequence ID from the Data Access Object
	 * And update this with the new one (add one to this)
	 * @param requestSeqDAO
	 * @return
	 */
	public static int getNextRequestSeqNo(RequestSeqDAO requestSeqDAO)
	throws ThetaExceptionExc {
		
		Integer requestSeqNo;
		try {
			RequestSeq requestSeq = requestSeqDAO.findRequestSeqByRequestSeqId(ThetaConstants.ONE_INT);
			requestSeqNo = requestSeq.getRequestSeqNo();
			Integer nextSeqNo = requestSeqNo + ThetaConstants.ONE_INT;

			if (nextSeqNo.equals(99999)) {
				nextSeqNo = 1000;
			}
			
			requestSeq.setRequestSeqNo(nextSeqNo);
			requestSeq.setUpdatedBy("ThetaUtil - getNext");
			requestSeq.setUpdatedDate(Calendar.getInstance());

			requestSeqDAO.store(requestSeq);
			requestSeqDAO.flush();
		} catch (DataAccessException e){
			throw new ThetaExceptionExc("Data Access Exception" + e);
		}
		
		return requestSeqNo;
	}
	
	/**
	 * Get the CURRENT Request Sequence ID from the Data Access Object
	 * No updates
	 * @param requestSeqDAO
	 * @return
	 */
	public static int getCurrentRequestSeqNo(RequestSeqDAO requestSeqDAO)
	throws ThetaExceptionExc {
		
		Integer requestSeqNo;
		try {
			RequestSeq requestSeq = requestSeqDAO.findRequestSeqByRequestSeqId(ThetaConstants.ONE_INT);
			requestSeqNo = requestSeq.getRequestSeqNo();
		} catch (DataAccessException e){
			throw new ThetaExceptionExc("Data Access Exception" + e);
		}
		
		return requestSeqNo;
	}

	/**
	 * 
	 * Sample string: "20110203:0930-1615"
	 *   OPEN is 0930
	 *   CLOSE is 1615
	 *   if nothing - just yyyyMMdd (20110203)
	 * 
	 * @param inString
	 * @param openOrClosed
	 * @return
	 * @throws ThetaExceptionExc
	 */
	public static Calendar getCal(String inString, MktOpenOrClosedCode mktOpenOrClosedCode)
	throws ThetaExceptionExc {


		Calendar cal = Calendar.getInstance();

		try {

			String dateString = null;
			String[] dp = inString.split("-");

			if (mktOpenOrClosedCode.equals(MktOpenOrClosedCode.OPEN)){
				dateString = dp[0];
			} else if (mktOpenOrClosedCode.equals(MktOpenOrClosedCode.CLOSED)){
				String[] dp2 = dp[0].split(":");
				dateString = dp2[0] + ":" + dp[1]; 
			} else {
				dateString = dp[0] + ":0000";
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd:hhmm");
			Date d1 = df.parse(dateString);
			cal.setTime(d1);
		} catch (ParseException pe) {
			throw new ThetaExceptionExc(pe);
		} catch (Exception e){
			throw new ThetaExceptionExc(e);
		}
		return cal;
	}

	boolean topOfHourBool = false;
	public boolean isTopOfHour(){
		return topOfHourBool;
	}
	
	public boolean isTopOfHour(Integer strategyAccountId, ProfitLossDAO profitLossDAO){

		log.info("IN isTopOfHour.  StrategyAccountId: " + strategyAccountId);
		Calendar now = Calendar.getInstance();
		Calendar startWindow = Calendar.getInstance();
		Calendar endWindow = Calendar.getInstance();
		Calendar topOfHour = Calendar.getInstance();

		startWindow.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), ThetaConstants.NINE_OCLOCK_AM, ThetaConstants.ZERO_INT);
		endWindow.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), ThetaConstants.SIX_OCLOCK_PM, ThetaConstants.THIRTY_INT);
		topOfHour.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), now.get(Calendar.HOUR_OF_DAY), ThetaConstants.ZERO_INT, ThetaConstants.ZERO_INT);
		
		if (!(now.after(startWindow) && now.before(endWindow))){
			return false;
		}

		
		// TEST
		Calendar lastHour = Calendar.getInstance();
		lastHour.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), (now.get(Calendar.HOUR_OF_DAY)-1), ThetaConstants.ZERO_INT, ThetaConstants.ZERO_INT);
		Set<ProfitLoss> plHour = profitLossDAO.findProfitLossByCreatedDateAfter(lastHour);

		Calendar lastDay = Calendar.getInstance();
		lastDay.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), (now.get(Calendar.DATE)-1), now.get(Calendar.HOUR_OF_DAY), ThetaConstants.ZERO_INT, ThetaConstants.ZERO_INT);
		Set<ProfitLoss> plDay = profitLossDAO.findProfitLossByCreatedDateAfter(lastDay);

		Calendar lastMonth = Calendar.getInstance();
		lastMonth.set(now.get(Calendar.YEAR), (now.get(Calendar.MONTH)-1), now.get(Calendar.DATE), now.get(Calendar.HOUR_OF_DAY), ThetaConstants.ZERO_INT, ThetaConstants.ZERO_INT);
		Set<ProfitLoss> plMonth = profitLossDAO.findProfitLossByCreatedDateAfter(lastMonth);

		log.info("*** TEST *** Size - plHour, plDay, plMonth: " + plHour.size() +  " : " + plDay.size() + " : " + plMonth.size());
		// TEST
		
		
		log.info("Here is NOW and topOfHour: " + now.getTime() + " : " + topOfHour.getTime());
		profitLossDAO.flush();
		//Set<ProfitLoss> profitLosses = profitLossDAO.findProfitLossByProfitLossDate(Calendar.getInstance());
		Set<ProfitLoss> profitLosses = profitLossDAO.findProfitLossByCreatedDateAfter(topOfHour);

		log.info("Here is the return of findProfitLossByCreatedDateAfter: " + profitLosses.size());
		log.info("Here is strategyAccountID - as sent in: " + strategyAccountId);

		if (!profitLosses.isEmpty()){
			Iterator<ProfitLoss> profitLossesIter = profitLosses.iterator();
			while (profitLossesIter.hasNext()){
				ProfitLoss profitLoss = (ProfitLoss) profitLossesIter.next();
				log.info("Here is FOUND profitLoss ID/datetime: " + profitLoss.getProfitLossId() + " : " + profitLoss.getCreatedDate().getTime());
				if ( (profitLoss.getStrategyAccount().getStrategyAccountId().equals(strategyAccountId))
						&& (profitLoss.getCreatedDate().after(topOfHour)) )
				{
					topOfHourBool = false;
					return false;
				}
			} // while (profitLossesIter.hasNext()){
		} // if (!profitLosses.isEmpty()){

		topOfHourBool = true;
		return true;
	
	}
			
	/**
	 * @param actualHigh
	 * @param actualLow
	 * @param targetPercentInBP
	 * @param logStatement
	 * @return boolean
	 * 
	 *  Check the diff between actualHigh and actualLow against
	 *  the targetPercentInBP
	 *  
	 *  Return TRUE if is the actual diff is more than the target
	 * 
	 *  actualHigh = 100
	 *  actualLow = 97.5
	 *  targetPercentInBP = 200 bp
	 *  (100 * (100 - 97.5))/100 = 250 bp ==> passes
	 *
	 */
	public boolean isActualDiffMoreThanTarget(Integer actualHigh, Integer actualLow, Integer targetPercentInBP, String logStatementIfTrue){
		
		
		
		if ( (actualHigh == null) || 
				(actualHigh.equals(new Integer(0))) ||
				(actualLow == null) ||
				(actualLow.equals(new Integer(0))) )
			return false;
		
		
		Float highLowDiff = (float) (actualHigh - actualLow);
		Float actualBPFloat = (highLowDiff / (float) actualHigh) * ThetaConstants.TEN_THOUSAND_FLOAT;
		Integer actualBPInt = actualBPFloat.intValue();
		
		if (targetPercentInBP.compareTo(actualBPInt) >=0 ){
			return false;
		}
		
		// Sometimes there's an odd value which is way off.  If this is down too much - forget it - it's a fluke:
		Integer DOWN_TOO_MUCH_FLUKE = 750;
		if(DOWN_TOO_MUCH_FLUKE.compareTo(actualBPInt) <= 0 ){
			log.info("Down too much - returning false. actualBPInt: " + actualBPInt + " DOWN_TOO_MUCH_FLUKE: " + DOWN_TOO_MUCH_FLUKE);
			return false;
		}
		

		//2012-04-20 10:17:17,528 INFO ThetaUtil - 
		// Down past limit from yeterday morning.. Down % (bp): 9974. Target: 200 actual high : actual low 13841 : 35
		// Down past limit from this morning.. Down % (bp): 9974. Target: 100 actual high : actual low 13834 : 35
		// Down past limit between min and max of last 90 minutes.. Down % (bp): 9974. Target: 50 actual high : actual low 13850 : 35

		log.info(logStatementIfTrue + ". Down % (bp): " + actualBPInt + ". Target: " + targetPercentInBP + " actual high : actual low " + actualHigh + " : " + actualLow );
		return true;
	}

	public static void debugWarningMessage(){
		if (ThetaConstants.DEBUG_FLAG){
			System.out.println("**********************************************************************************");
			System.out.println("  _______   _______          __________________ _______  _                       *");
			System.out.println(" (  ____   (  ___  )|      /| __   __/ __   __/(  ___  )( (     |                *");  
			System.out.println(" | (       | (   ) || )   ( |   ) (      ) (   | (   ) ||  (  ( |                *");
			System.out.println(" | |       | (___) || |   | |   | |      | |   | |   | ||   ( | |                *");
			System.out.println(" | |       |  ___  || |   | |   | |      | |   | |   | || (( () |                *");
			System.out.println(" | |       | (   ) || |   | |   | |      | |   | |   | || | (   |                *");
			System.out.println(" | (____/) | )   ( || (___) |   | |   ___) (___| (___) || )  (  |                *");
			System.out.println(" (_______/ |/     (|(_______)   )_(   (_______/(_______)|/    )_)                *");
			System.out.println("                                                                                 *");
			System.out.println("                                                                                 *");
			System.out.println("                                                                                 *");
			System.out.println("     IN DEBUG MODE - ONLY CONTINUE IF YOU ARE PRESENT                            *");
			System.out.println("                                                                                 *");
			System.out.println("      REALIZE ANYTHING CAN HAPPEN IN DEBUG MODE!!!!!!                            *");
			System.out.println("                                                                                 *");
			System.out.println("                                                                                 *");
			System.out.println("     (SLEEPING FOR 20 SECONDS BEFORE CONTINUING...)                              *");
			System.out.println("                                                                                 *");
			System.out.println("**********************************************************************************");
			secondsToSleepNoThrow(20);
		} else {
			System.out.println("NOT IN DEBUG MODE - Continuing ...");
		}
	}

	
	public static boolean writeToFile(String toWrite, String fileName){

		File outFile = new File(fileName);
		FileOutputStream fop = null;
		try {
		    fop = new FileOutputStream(outFile, true);
		    String toWriteWithLF = toWrite + "\n";
		    byte[] contentByte = toWriteWithLF.getBytes();
		    fop.write(contentByte);
		    fop.flush();
		    fop.close();
		} catch (Exception ex){
			return false;
		}
		return true;
	}
	
	
	public static boolean ftpToHostmonster(String theFile, String remoteFile){

	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmm");
	    Calendar now = Calendar.getInstance();
	    String dateTimeString = formatter.format(now.getTime());
		String remoteFileWithDateTime = remoteFile + dateTimeString;
		boolean sendWithDateTime = ftpFile(theFile, remoteFileWithDateTime, "ftp.systemtheta.com","theta2@systemtheta.com", "L1fel0ck89!");
		boolean sendPlain = ftpFile(theFile, remoteFile, "ftp.systemtheta.com","theta2@systemtheta.com", "L1fel0ck89!");

		if (sendWithDateTime && sendPlain)
			return true;
		else
			return false;
	}
	
	
	public static boolean ftpFile(String localFile, String remoteFile,
			String server, String username, String password){

		
		
		boolean storeFile = true, binaryTransfer = false, error = false;
		FTPClient ftp;

		ftp = new FTPClient();
		ftp.setDefaultTimeout(10000);

		try
		{
			int reply;
			ftp.connect(server);
			log.info("Connected to " + server + ".");

			// After connection attempt, check the reply code to verify success.
			reply = ftp.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply))
			{
				ftp.disconnect();
				log.error("FTP server refused connection!");
				return false;
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
			log.error("Could not connect to server.", e);
			return false;
		}

			try
		{
				if (!ftp.login(username, password))
				{
					ftp.logout();
					error = true;
				}

				log.info("Remote system is " + ftp.getRemoteAddress());

				if (binaryTransfer)
					ftp.setFileType(FTP.BINARY_FILE_TYPE);

				// Use passive mode as default because most of us are
				// behind firewalls these days.
				ftp.enterLocalPassiveMode();
				

				if (storeFile)
				{
					InputStream input;

					//ftp.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
					//ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);

					input = new FileInputStream(localFile);

					log.info("About to store file: local, remote: " + localFile + " : " + remoteFile );
					ftp.setSoTimeout(20000);
					ftp.storeFile(remoteFile, input);

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
			log.error("Server closed connection.",e);
		}
		catch (IOException e)
		{
			error = true;
			log.error("IO Exception: ", e);
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
		log.info("end of ftp process");
		return error;
	} // end main

	
	
	
	public  Integer getCurrentSecurityPriceFromYahoo(String symbol, ThetaClientInterface client) throws ThetaExceptionExc {

		if (symbol.isEmpty()) {
			throw new ThetaExceptionExc("symbol is empty!");
		}

		String[] stockQuote = new String[3];

		try {
			URL theUrl = new URL("http://finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=sb2b3");
			URLConnection urlConnection = theUrl.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				//System.out.println("String from Yahoo: " + inputLine);
				log.info("String from Yahoo: " + inputLine);
				stockQuote = inputLine.split(",");
			}

			if (stockQuote.length < 3) {
				log.error("Didn't get a complete stock quote.  Return value: " + inputLine);
				throw new ThetaExceptionExc("Didn't get a complete stock quote.  Return value :" + inputLine);
			}

			in.close();

		} catch (MalformedURLException e) {
			throw new ThetaExceptionExc("Malformed URL: " + e);
		} catch (IOException e) {
			throw new ThetaExceptionExc("IO Problem: " + e);
		}

		String symbolOut = stockQuote[0];
		if (symbolOut == null) {
			throw new ThetaExceptionExc("symbol coming back from Yahoo is null.");
		}
		
		symbolOut = symbolOut.replace("\"", "");
		if (!symbolOut.equals(symbol)){
			throw new ThetaExceptionExc("symbol back from Yahoo doesn't match.  SymbolOut, symbol: " + symbolOut + "," + symbol);
		}
		
		
		String bid = stockQuote[1];
		String ask = stockQuote[2];
		Float bidCentsF = Float.parseFloat(bid) * 100;
		Float askCentsF = Float.parseFloat(ask) * 100;

		Integer bidI = bidCentsF.intValue();
		Integer askI = askCentsF.intValue();
		
		return checkAndReturnPriceBidAskHalf(symbol, -2, bidI, askI, client);
		
		//Long bidPrice = new Long(stockQuote[1]);
		//Long askPrice = new Long(stockQuote[2]);
		//System.out.println("ticker:bid:ask: " + tickerName + ":" + bidF + ":" + askF);
		//return 0;
	}

	
	/**
	 * 
	 * @param symbol
	 * @param positionId
	 * @param priceBid
	 * @param priceAsk
	 * @param client
	 * @return
	 * @throws ThetaExceptionExc
	 */
	public Integer checkAndReturnPriceBidAskHalf(String symbol, Integer positionId, Integer priceBid, Integer priceAsk, ThetaClientInterface client) throws ThetaExceptionExc {

		Integer priceBidAskHalf;

		if (!priceAsk.equals(ThetaConstants.ZERO_INT) && !priceBid.equals(ThetaConstants.ZERO_INT)){
			priceBidAskHalf = (priceAsk + priceBid)/2;

			// Price MUST BE correct - this is crucial.  If these two are off - DANGER WILL ROBINSON!
			Double priceAskDouble = new Double(priceAsk);
			Double priceBidDouble = new Double(priceBid);
			Double askPlusFourPercent = priceAskDouble * 1.06;
			Double askMinusFourPercent = priceAskDouble * 0.94;

			Double askPlusTenPercent = priceAskDouble * 1.10;
			Double askMinusTenPercent = priceAskDouble * 0.90;

			if ( (priceBidDouble < askMinusFourPercent) ||
					(priceBidDouble > askPlusFourPercent)){
				String errMsg = "priceBid and priceAsk are more than 4% off - error - careful - BLOCKING ENTRY!  Symbol, priceBid, priceAsk " + symbol + " | " + priceBid + " | " + priceAsk;
				log.info(errMsg);
				client.setCanEnterMap(positionId, false);
			}
			if ( (priceBidDouble < askMinusTenPercent) ||
					(priceBidDouble > askPlusTenPercent)){
				String errMsg = "priceBid and priceAsk are more than 10% off - error - careful - RETURNING NULL!  Symbol, priceBid, priceAsk " + symbol + " | " + priceBid + " | " + priceAsk;
				log.info(errMsg);
				return null;
			}
			
			
		} else {
			throw new ThetaExceptionExc("Either BID or ASK or both are not available!");
		}

		return priceBidAskHalf;
	}

	
	// Are all prices identical
	public boolean areAllPricesIdentical(Set<SecurityPrice> prices){
		Integer STARTER = -1;
		Integer priceOne = STARTER;
		Integer priceNext;
				
		for (SecurityPrice sp : prices) {
			priceNext = sp.getPrice();
			//System.out.println("priceOne, priceNext: " + priceOne + ", " + priceNext);
			if (!priceNext.equals(priceOne)) {
				if (!priceOne.equals(STARTER)) {
					return false;
				}
			}
			priceOne = priceNext;
		}
		return true;
	}

	public boolean areAllPricesIdentical(Set<SecurityPrice> prices, Integer lastPrice){
		SecurityPrice sp = new SecurityPrice();
		sp.setSecurityPriceId(-10);
		sp.setPrice(lastPrice);

		Set<SecurityPrice> pricesWithLastPrice = new LinkedHashSet<SecurityPrice>(prices);
		pricesWithLastPrice.add(sp);
		return areAllPricesIdentical(pricesWithLastPrice);
	}
	
	
	public static void main(String[] args){
		ThetaUtil util = new ThetaUtil();
		//System.out.println("small (should be false): " + util.isActualDiffMoreThanTarget(101, 100, 250, "test log statement"));
		//System.out.println("medium (should be true): " + util.isActualDiffMoreThanTarget(104, 100, 250, "test log statement"));
		//System.out.println("large (should be false): " + util.isActualDiffMoreThanTarget(110, 100, 250, "test log statement"));
		//public boolean isActualDiffMoreThanTarget(Integer actualHigh, Integer actualLow, Integer targetPercentInBP, String logStatementIfTrue){
	}
	
	
	/**
	 * @param position
	 * @return
	 */
	public static boolean isPositionInPast(Position position)
	throws ThetaExceptionExc{

		Calendar today = Calendar.getInstance();
		Calendar positionExpires = Calendar.getInstance();
		positionExpires.set(position.getExpiryYear(), position.getExpiryMonth() - ThetaConstants.ONE_INT, position.getExpiryDay());
		
		log.info(position.getStrategyName() + ": Here is position: " + position.toStringShort());
		
		if (positionExpires.before(today)) {
			log.info(position.getStrategyName() + ": Position expires BEFORE today! ");
			return true;
		} else {
			log.info(position.getStrategyName() + ": Position expires AFTER today! ");
			//thetaUtil.secondsToSleep(1);
			return false;
		}
		
	}




	
	
	
	/************************************************************************************************************/
	/************************************************************************************************************/
	/************************************************************************************************************/
	/*                                     TIME LIMITS                                                         */
	/************************************************************************************************************/
	/************************************************************************************************************/
	/************************************************************************************************************/
	
	/**
	 * @param spread
	 * @return
	 */
	public boolean isWithinTimeLimits(Spread spread, Position position, ThetaClientInterface client)
	throws ThetaExceptionExc{

		// Caching contract details
		String detailKey = position.getSymbol() + position.getExpiryMonth() + position.getExpiryYear() + spread.getStrikeMoneymkr() + position.getOptRight();

		ContractDetails det;
		det = client.getDetailCache().get(detailKey);
		if (det == null) {

			//thetaUtil.enterRecordIntoHeartbeat("CACHE: detailCache: adding:  " + detailKey);
			//Integer nextSeq = ThetaUtil.getNextRequestSeqNo(requestSeqDAO);
			client.reqOptionDetails(position.getSymbol(), position.getExpiryMonth(), position.getExpiryYear(),  spread.getStrikeMoneymkr(), position.getOptRight());

			// This must be here - to get the details of the Options
			ThetaUtil.secondsToSleepNoThrow(ThetaConstants.LONGER_SLEEP_TIME);

			det = client.getResp().getContractDetails();		
			client.getDetailCache().put(detailKey, det);

			log.info("Here is ContractDetails: " + det.toString());
			
		} else {
			//thetaUtil.enterRecordIntoHeartbeat("CACHE: detailCache: found key - using: " + detailKey);
		}

		return (isWithinTimeLimits(det));
	}



	private boolean isWithinTimeLimits(ContractDetails det)
	throws ThetaExceptionExc {
		Strategy strat  = new Strategy();
		return ( isWithinDateAndTimeLimits(det, strat, ThetaConstants.ZERO_INT, ThetaConstants.NEGATIVE_TWO_INT, true) );
	}

	
	/**
	 * @param det
	 * @param strategy
	 * @param minDelayFromStart
	 * @param minDelayFromEnd
	 * @param ignoreDate - ignoreDate
	 * @return
	 * @throws ThetaExceptionExc
	 */
	public boolean isWithinDateAndTimeLimits(ContractDetails det, Strategy strategy, Integer minDelayFromStart, Integer minDelayFromEnd, boolean ignoreDate)
	throws ThetaExceptionExc {

		if ( (det.m_timeZoneId == null) || (det.m_tradingHours == null)){
			log.info("timeZoneId or trading Hours is null.");
			return false;
		}

		// Sample tradingHours string: "20110203:0930-1615;20110204:0930-1615"

		if (!det.m_timeZoneId.equalsIgnoreCase(ThetaConstants.EasternStandardTime)){
			throw new ThetaExceptionExc("Incorrect Timezone - must be EST");
		}
		String tradingHours = det.m_tradingHours;
		log.info("Here are trading hours: " + tradingHours);
		
		String[] hrs = tradingHours.split(";");
		
		// 20110612:CLOSED; etc.
		String[] checkClosed = hrs[0].split(":");
		if (checkClosed[1].equals("CLOSED")){
			log.info("Here is trading hours: " + tradingHours);
			log.info("MARKETS CLOSED TODAY");
			return false;
		}
		
       	// Get start of trading - from the DetailRecord plus the strategy.minDelayFromStart()
		Calendar startTrading = getCal(hrs[0], MktOpenOrClosedCode.OPEN);
       	startTrading.add(Calendar.MINUTE, minDelayFromStart);

       	// Get end of trading
       	Calendar endTrading = getCal(hrs[0], MktOpenOrClosedCode.CLOSED);
       	endTrading.add(Calendar.MINUTE, -minDelayFromEnd);
       	
       	// Is right now between start and end of trading?
       	// ALSO - correct day? (eliminates weekends and holidays)
       	Calendar now = Calendar.getInstance();       	
       	if (!(now.after(startTrading) && now.before(endTrading))){
       		log.info("Not between start and end of trading - with " + minDelayFromEnd + " minutes Delay from End!");
       		log.info("now : startTrading : endTrading " + now.getTime() + " : " + startTrading.getTime() + " : " + endTrading.getTime() );
       		
       		return false;
       	}

       	// Don't go on to Date section - return true if we get here.
       	if (ignoreDate){
       		return true;
       	}
       	
       	// Don't need yet - but good to have
       	//Calendar nextOpen = getCalfromIBDate(hrs[1], ThetaConstants.OPEN);
       	//Calendar nextClose = getCalfromIBDate(hrs[1], ThetaConstants.CLOSED);

       	// Check that the expiry date is within range
       	Calendar expDateCal = getCal(det.m_summary.m_expiry, MktOpenOrClosedCode.ALLDAY);

       	Calendar mustBeAfter = (Calendar) expDateCal.clone();
       	Calendar mustBeBefore = (Calendar) expDateCal.clone();
       	
       	mustBeAfter.add(Calendar.DATE, -strategy.getDaysEnterHigh());
       	mustBeBefore.add(Calendar.DATE, -strategy.getDaysEnterLow());
       	
       	Calendar today = Calendar.getInstance();
   		log.info("today : expiry : mustBeAfter : mustBeBefore: " + today.getTime() + " : " + expDateCal.getTime() + " : " +
   				mustBeAfter.getTime() + " : " + mustBeBefore.getTime() );
   		
   		if (!(today.after(mustBeAfter) && today.before(mustBeBefore))){
       		log.info("Expiry date of this spread is not within limits.");
       		return false;
       	} else {
       		log.info("Expiry date of this spread is within limits.");
       	}
       	
		return true;
	}

	
	
	/************************************************************************************************************/
	/************************************************************************************************************/
	/************************************************************************************************************/
	/*                                VERIFY ORDERS                                                             */
	/************************************************************************************************************/
	/************************************************************************************************************/
	/************************************************************************************************************/

	/**
	 * 
	 * @param spread
	 * @param spreadDAO
	 */
	public boolean setLastFillPriceFromOrderStatus (Spread spread, Position position, SpreadDAO spreadDAO, ThetaClientInterface client) {
		Set<OrderStatus> orderStatuses = client.orderStatuses;
		Set<OrderStatus> orderStatusCopy = new HashSet<OrderStatus>(orderStatuses); 
		OrderStatus orderStatusToDelete = null;
		boolean isSpreadOrderFilled = false;
		boolean isHmMmClosed = false;
		boolean isHmInsClosed = false;
		
		if (spread.getRequestSeqNo() == null) {
			spread.setRequestSeqNo(0);
			spread.setOpenOrClosed(OpenOrClosedCode.CLOSED.toString());
			return false;
		}
		
		// Iterate through the orderStatusCopy rather than the original.  This prevents a 
		// ConcurrentModificationException - when the client adds to the original.
		for(OrderStatus orderStatus : orderStatusCopy){
			log.info("orderStatus (during): " + orderStatus.toString());
			if ( !spread.isHailMary() && spread.getRequestSeqNo().equals(orderStatus.getOrderId()) ){
				
				log.info("orderStatus(during). SpreadID, requestSeqNo: " + spread.getSpreadId() + ", " + spread.getRequestSeqNo() );
								
				String currentStatus = orderStatus.getStatus();
				if (currentStatus != null &&
						currentStatus.equalsIgnoreCase("Filled")) {

					if (spread.isOpening()){
						log.info("in isOpening. requestSeqNo: " + spread.getRequestSeqNo() );
						spread.setOpenOrClosed(OpenOrClosedCode.OPEN.toString());
						spread.setEnterPermIdIb(orderStatus.getPermIdStr());
						spread.setEnterRequestSeqNo(spread.getRequestSeqNo());
						if (position.isOption()) {
							spread.setEnterActualIb(orderStatus.getLastFillPriceXTenThou());
						} else {
							spread.setEnterActualIb(orderStatus.getLastFillPriceXOneHundred());
							spread.setEnterReason(spread.getEnterReason() + 
									             ", actualFill: " + orderStatus.getLastFillPriceXOneHundred() + ", END.");
						} 
						spread.setEnterMoneymkrDate(Calendar.getInstance());
						spread.setEnterInsuranceDate(Calendar.getInstance());
						spread.setEnterConfirmDate(Calendar.getInstance());

					}
					if (spread.isClosing()){
						log.info("in isClosing. requestSeqNo: " + spread.getRequestSeqNo() );
						spread.setOpenOrClosed(OpenOrClosedCode.CLOSED.toString());
						spread.setExitPermIdIb(orderStatus.getPermIdStr());
						spread.setExitRequestSeqNo(spread.getRequestSeqNo());
						if (position.isOption()) {
							spread.setExitActualIb(orderStatus.getLastFillPriceXTenThou());
						} else {
							spread.setExitActualIb(orderStatus.getLastFillPriceXOneHundred());
							spread.setExitReason(spread.getExitReason() + 
									            ", actualFill: " + orderStatus.getLastFillPriceXOneHundred() + ", END.");
						} 

						// and final bookkeeping:
						spread.setProfitLossRealized(spread.getProfitLossUnrealized());
					
						spread.setExitMoneymkrDate(Calendar.getInstance());
						spread.setExitInsuranceDate(Calendar.getInstance());
						spread.setExitConfirmDate(Calendar.getInstance());

					}
					
					spread.setUpdatedBy("setLastFillPriceFromOrderStatus: in orderStatus.");
					spread.setUpdatedDate(Calendar.getInstance());
 
					isSpreadOrderFilled = true;
					orderStatusToDelete = orderStatus;
					// Done! - leave now ...
					break;
					//orderStatuses.remove(orderStatus);
				} // if (currentStatus != null && currentStatus.equalsIgnoreCase("Filled"))


				if (currentStatus != null &&
						(currentStatus.equalsIgnoreCase("Cancelled") || currentStatus.equalsIgnoreCase("Inactive"))){
					if (spread.isPendOpening()){
						spread.setOpenOrClosed(OpenOrClosedCode.NOTEXEC.toString());
					}
					if (spread.isPendClosing()){
						spread.setOpenOrClosed(OpenOrClosedCode.OPEN.toString());

						Spread failOpenSpread = new Spread();
						failOpenSpread.copy(spread);
						failOpenSpread.setSpreadId(ThetaConstants.INIT_ID);
						failOpenSpread.setOpenOrClosed(OpenOrClosedCode.FAILCLOSE.toString());
						try {
							spreadDAO.synchronizedStoreAndFlush(failOpenSpread);
						} catch (Exception ex){
							log.error("Exception in storing to spreadDAO!.", ex);
							log.info("Here is failOpenSpread: " + failOpenSpread);
						}

					}
					isSpreadOrderFilled = true;
					orderStatusToDelete = orderStatus;
					
					break;
				}

				if (currentStatus != null &&
						(currentStatus.equalsIgnoreCase("Submitted") || currentStatus.equalsIgnoreCase("PreSubmitted"))){
					if (spread.getOpenOrClosed().equals(OpenOrClosedCode.PENDOPEN)){
						spread.setOpenOrClosed(OpenOrClosedCode.PENDOPNCNF.toString());
					}
					if (spread.getOpenOrClosed().equals(OpenOrClosedCode.PENDCLOSE)){
						spread.setOpenOrClosed(OpenOrClosedCode.PENDCLSCNF.toString());
					}

					isSpreadOrderFilled = true;
					break;
				}
				
				
				
			} // if ( spread.getRequestSeqNo().equals(orderStatus.getOrderId()) ){

			if (spread.isHailMary()){
				log.info("Checking Hail Mary: orderStatus (during): " + orderStatus.toString());
				if (orderStatus.getLastFillPrice() > ThetaConstants.POSITIVE_SMALL_DOUBLE 
						|| orderStatus.getLastFillPrice() < ThetaConstants.NEGATIVE_SMALL_DOUBLE){

					Integer lastFillPriceXTenThou = orderStatus.getLastFillPriceXTenThou();

					if (spread.getHmMmReqno().equals(orderStatus.getOrderId())){
						log.info("Checking Hail Mary - in getHmMmReqno(). last fill price: " + lastFillPriceXTenThou);
						spread.setHmMmExitActualIb(lastFillPriceXTenThou);
						spread.setHmMmExitPermIdIb(orderStatus.getPermIdStr());
						spread.setExitMoneymkrDate(Calendar.getInstance());
						spread.setExitMoneymkrPrice(lastFillPriceXTenThou);
						isHmMmClosed = true;
					}

					if (spread.getHmInsReqnoLimitUp().equals(orderStatus.getOrderId())){
						log.info("Checking Hail Mary - in getHmInsReqnoLimitUp() last fill price: " + lastFillPriceXTenThou);
						spread.setHmInsExitActualIb(lastFillPriceXTenThou);
						spread.setHmInsExitPermIdIb(orderStatus.getPermIdStr());
						spread.setExitInsuranceDate(Calendar.getInstance());
						spread.setExitInsurancePrice(lastFillPriceXTenThou);
						isHmInsClosed = true;
					}

					if (spread.getHmInsReqnoStplimDown().equals(orderStatus.getOrderId())){
						log.info("Checking Hail Mary - in getHmInsReqnoStplimDown() last fill price: " + lastFillPriceXTenThou);
						spread.setHmInsExitActualIb(lastFillPriceXTenThou);
						spread.setHmInsExitPermIdIb(orderStatus.getPermIdStr());
						spread.setExitInsuranceDate(Calendar.getInstance());
						spread.setExitInsurancePrice(lastFillPriceXTenThou);
						isHmInsClosed = true;
					}
				}
				
				
				/**
				 * MM is re-bought - this is a COST and positive
				 * INS is sold - this is a SALE and negative
				 */
				Integer hmMmExitActualIb = spread.getHmMmExitActualIb();
				Integer hmInsExitActualIb = spread.getHmInsExitActualIb();
				
				// Spread closed - final accounting
				if ( (hmMmExitActualIb != null) && (hmInsExitActualIb != null) &&
						(hmMmExitActualIb.compareTo(ThetaConstants.ZERO_INT) != 0) &&
						(hmInsExitActualIb.compareTo(ThetaConstants.ZERO_INT) != 0)){

					log.info("Checking Hail Mary - final Accounting");
					
					//  Close the HAILMARY 
					spread.setOpenOrClosed(OpenOrClosedCode.CLOSED.toString());
					String exitPermIdIb = spread.getHmMmExitPermIdIb() + ":" + spread.getHmInsExitPermIdIb();
					if (exitPermIdIb.length() > 20) exitPermIdIb= exitPermIdIb.substring(0,20); 
					spread.setExitPermIdIb(exitPermIdIb);
					
					Integer hmExitActualIb = hmInsExitActualIb - hmMmExitActualIb;
					spread.setExitActualIb(hmExitActualIb);
				
					Integer hmProfitLossRealized = spread.getEnterActualIb() - hmExitActualIb;
					// and final bookkeeping:
					spread.setProfitLossRealized(hmProfitLossRealized);
					spread.setProfitLossUnrealized(ThetaConstants.ZERO_INT);
				}
				
				if(isHmMmClosed || isHmInsClosed){
					isSpreadOrderFilled = true;
					orderStatusToDelete = orderStatus;
					// Done! - leave now ...
					break;
				}

				
			}
		
		} // for(OrderStatus orderStatus : orderStatuses){
		log.info("orderStatus (after):");

		if (isSpreadOrderFilled)  { orderStatuses.remove(orderStatusToDelete); }

		/*
		 * Ah - it's not it orderStatus - BUT it's in a PENDCLSCNF state and the ExitTriggerDate
		 * is not today (in the past ...).  Go ahead and close it - it's likely that is closed over night
		 * and never made into orderStatus.  This is a better assumption than leaving it in PENDCCLSNF status
		 * (i.e. Stock orders are now MKT/GTC - so they will eventually close)
		 */
		Calendar now = Calendar.getInstance();
		if ( spread.getOpenOrClosed().equals(OpenOrClosedCode.PENDCLSCNF.toString()) && (spread.getExitConfirmDate() != null) &&
			( spread.getExitTriggerDate().get(Calendar.DATE) != (now.get(Calendar.DATE)) ) )
		{
			log.info("in setLastFillPriceFromOrderStatus. requestSeqNo: " + spread.getRequestSeqNo() );
			log.info("in setLastFillPriceFromOrderStatus. Was PENDCLSCNF but the ExitConfirmDate is another day. CLOSING." );
			spread.setOpenOrClosed(OpenOrClosedCode.CLOSED.toString());
			spread.setExitRequestSeqNo(spread.getRequestSeqNo());
			
			// Adjust position class variables
			// FROM PERSISTENCE
			//else if (currentSecurityPrice.compareTo(allMovAvgs.hiAvgPlus) < 0) {
			//	log.info("Don't enter LONG: Case #3: currentSecurityPrice is now below allMovAvgs.hiAvgPlus - can no longer reenter at XTreme.");
			//	position.setStkCanReenterAtXtreme(false);
			//	position.setStkLocalXtremePrice(allMovAvgs.hiAvgPlus);
			//} else {
			//	position.setStkCanReenterAtXtreme(false);
			//}

			//position.rollbackToPreviousValues();
			// and final bookkeeping:
			spread.setProfitLossRealized(spread.getProfitLossUnrealized());
			spread.setExitReason(spread.getExitReason() + ", PENDCLSCNF to CLOSED. Past Exit Trigger Date. END.");
			
			spread.setExitMoneymkrDate(Calendar.getInstance());
			spread.setExitInsuranceDate(Calendar.getInstance());
			spread.setExitConfirmDate(Calendar.getInstance());
			return true;
		}
		
		// Ditto for PENDOPENCNF
		if ( spread.getOpenOrClosed().equals(OpenOrClosedCode.PENDOPNCNF.toString()) && (spread.getEnterConfirmDate() != null) &&
				( spread.getEnterTriggerDate().get(Calendar.DATE) != (now.get(Calendar.DATE)) ) )
			{
				log.info("in setLastFillPriceFromOrderStatus. requestSeqNo: " + spread.getRequestSeqNo() );
				log.info("in setLastFillPriceFromOrderStatus. Was PENDOPENCNF but the EnterConfirmDate is another day. OPENING." );
				spread.setOpenOrClosed(OpenOrClosedCode.OPEN.toString());
				spread.setEnterRequestSeqNo(spread.getRequestSeqNo());

				spread.setEnterReason(spread.getEnterReason() + ", PENDOPNCNF to OPEN. Past Enter Trigger Date. END.");
				//position.rollbackToPreviousValues();
				spread.setEnterMoneymkrDate(Calendar.getInstance());
				spread.setEnterInsuranceDate(Calendar.getInstance());
				spread.setEnterConfirmDate(Calendar.getInstance());
				return true;
			}

		
		//for(OrderStatus orderStatus : orderStatuses){
		//	log.info("orderStatus (after): " + orderStatus.toString());
		//}
		log.info("--- orderStatus END ----");
		return isSpreadOrderFilled;
	} // private void setLastFillPriceFromOrderStatus(Spread spread, SpreadDAO spreadDAO){

	
	/**
`	 * Verify that the given Request Sequence number is in the Open Orders that
	 * come from IB.
	 * 
	 * @param requestSeqNo
	 * @return
	 * @throws ThetaExceptionExc
	 */
	public boolean verifyRequestSeqInOpenOrders(Integer requestSeqNo, Spread spread, String enterOrExit, ThetaClientInterface client) 
	throws ThetaExceptionExc {

		// give it a moment to execute - then check
		//thetaUtil.secondsToSleep(1);
		Set<OpenOrder> openOrdersIB = client.getOpenOrders();
		openOrdersIB.clear();
		
		client.reqOpenOrders();
		ThetaUtil.secondsToSleepNoThrow(ThetaConstants.ONE_INT);
		openOrdersIB = client.getOpenOrders();
		log.info("Iterating through IB Open Orders. Number open: " + openOrdersIB.size());

		/**
		 * Iterate on open orders - match by OrderId (from RequestSeqNo)
		 */

		return verifyRequestSeqInOpenOrders(requestSeqNo, openOrdersIB, spread, enterOrExit, client);
	}



	/**
	 * 
	 * @param requestSeqNo
	 * @param openOrdersIB
	 * @return
	 * @throws ThetaExceptionExc
	 */
	public boolean verifyRequestSeqInOpenOrders(int requestSeqNo, Set<OpenOrder> openOrdersIB, Spread spread, String enterOrExit, ThetaClientInterface client) 
	throws ThetaExceptionExc {

		if (ThetaConstants.DEBUG_FLAG) {
			log.info("MATCHING against open orders - AS DEBUG IS TRUE.");
			return ThetaConstants.FORCE_IN_OPEN_ORDERS;
		}
		
		log.info("Iterating through IB Open Orders. Number open: " + openOrdersIB.size());

		/**
		 * Iterate on open orders - match by OrderId (from RequestSeqNo)
		 */
		Iterator<OpenOrder> openOrderIBIter = openOrdersIB.iterator();
		while (openOrderIBIter.hasNext()){
			OpenOrder openOrderIB  = (OpenOrder) openOrderIBIter.next();
			int orderIdFromIB = openOrderIB.getOrderId();
			log.info("Open Order ID:" + orderIdFromIB + ": requestSeqNo: " + requestSeqNo + ":");
			if (orderIdFromIB == requestSeqNo) {
				log.info("Bingo. Found it! " + openOrderIB.toString());
				
				return true;
			}
		}
		return false;
	}

	public static String leftmostChars(String str, Integer numChars) {
		
		if (str == null) {return null;}
		
		if (str.length() >= numChars) {
			return str.substring(0, numChars);
		} else {
			return str;
		}
	}
		
	public static CoveredOption parseCoveredOptionsDesc(String coveredOptionsDesc, Integer currentSecurityPrice, Calendar currentDate){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		
		if (coveredOptionsDesc == null || coveredOptionsDesc.trim().equals("") ) {
			return null;
		}

		String sampleString = "P|C-MM-YYYY-99999";
		String[] descArr = coveredOptionsDesc.split("-");
		
		if (descArr.length < 4) {
			throw new InvalidParameterException("There must be at least 4 parts to the string divided by dashes: " + sampleString );
		}
		
		if ( !(descArr[0].equals("C") || descArr[0].equals("P")) ) {
			throw new InvalidParameterException ("The first character must be P (Put) or C (Call) instead of: " + descArr[0] );
		}
		
		Calendar expDate = DateUtil.getThirdFridayCal(new Integer(descArr[1]), new Integer(descArr[2]));
		if (currentDate.compareTo(expDate) > 0){
			throw new InvalidParameterException ("the third friday of the month MM-YYYY must be in the future.  Current: " + sdf.format(currentDate.getTime()) + ". Expiry: " + sdf.format(expDate.getTime()) );			
		}
		
		String[] priceArr = descArr[3].split(":");
		String strikePriceStr = priceArr[0].trim();
		Integer strikePrice = new Integer(strikePriceStr);
		
		if ( (strikePrice.compareTo((int)(currentSecurityPrice * .75)) < 0) ||
				(strikePrice.compareTo((int)(currentSecurityPrice * 1.25)) >0 ) ){
			throw new InvalidParameterException ("strike price must be within + or - 25% of currentSecurityPrice. strikePrice, currSecPrice : " + strikePrice + ", " + currentSecurityPrice);
		}

		CoveredOption coveredOption = new CoveredOption();
		coveredOption.putOrCall = descArr[0];
		coveredOption.MMYYYY = descArr[1] + descArr[2];
		coveredOption.monthInt = new Integer(descArr[1]);
		coveredOption.yearInt = new Integer(descArr[2]);
		coveredOption.expDate = expDate;
		coveredOption.strikePrice = new Integer(strikePriceStr);

		return coveredOption;
	}

}


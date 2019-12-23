/**
 * 
 */
package com.theta.process;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author pcion
 * @version
 * 
 * Theta Constants
 * 
 */
public class ThetaConstants {
	
	public static int INIT_ID = -1;
	public static Integer secondsToWaitToOverride = 300;
	public static Integer STRATEGY_SLEEP_TIME_MS;

	public static final String YES = "Y";
	public static final String NO = "N";
	public static final String CALL = "C";
	public static final String PUT = "P";
	public static final String NA = "NA";
	public static final String FALSE = "F";
	public static final String TRUE = "T";
	public static final BigDecimal ONE_BD = new BigDecimal(1);
	public static final BigDecimal ONE_HUNDRED_BD = new BigDecimal(100);
	public static final String EasternStandardTime = "EST";
	public static final String OTHER = "OTHER";
	public static final Integer ONE_INT = new Integer(1);
	public static final Float ZERO_FL = new Float(0);
	public static final BigDecimal ZERO_BD = new BigDecimal(0);
	public static final Integer ZERO_INT = new Integer(0);
	public static final String START = "START";
	public static final Calendar EARLY_DATE = new GregorianCalendar(1964, Calendar.NOVEMBER, 26);
	public static final int TWO_MINUTES_IN_MILLIS = 120000;
	public static final int MONTHS_LOOKAHEAD = 4;
	public static final Integer INITIAL_GOAL_NUM_SPREADS = new Integer(1);
	
	public static final Integer ONE_HUNDRED_INT = new Integer(100);
	public static final Integer TWO_HUNDRED_INT = new Integer(200);
	public static final Integer THREE_HUNDRED_INT = new Integer(300);

	public static final Integer ONE_THOUSAND_INT = new Integer(1000);
	public static final Integer TEN_THOUSAND_INT = new Integer(10000);

	public static final Integer ONE_HUNDRED_CENTS = new Integer(100);
	public static final Integer ONE_THOUSAND_CENTS = new Integer(1000);
	public static final Integer TEN_THOUSAND_CENTS = new Integer(10000);
	public static final Float ONE_HUNDRED_FLOAT = new Float(100);
	public static final Double ONE_HUNDRED_DOUBLE = new Double(100);
	public static final Double NEGATIVE_ONE_DOUBLE = new Double(-1);
	public static final Integer NEGATIVE_ONE_INT = new Integer(-1);

	public static final Double POSITIVE_SMALL_DOUBLE = new Double(0.00001);
	public static final Double NEGATIVE_SMALL_DOUBLE = new Double(-0.00001);

	public static final Integer TWO_INT = new Integer(2);
	public static final Integer THREE_INT = new Integer(3);
	public static final Integer TEN_INT = new Integer(10);
	public static final Integer FIFTEEN_INT = new Integer(15);
	public static final Integer TWENTY_INT = new Integer(20);
	public static final Integer SIX_INT = new Integer(6);
	public static final Integer NUM_TRIES_TO_GET_OPTION_PRICE = new Integer(5);
	public static final long SHORT_SLEEP_TIME = 1;
	public static final long LONGER_SLEEP_TIME = 3;
	public static final long SECONDS_IN_HOUR_LONG=3600;
	public static final int NUM_TIMES_TO_CHECK_PRICE = 3;
	public static final String VIX_TICKER = "VXZ";
	public static final String SPY_TICKER = "SPY";
	public static final Integer LIMIT_ORDER_KICKER = new Integer(2);
	
	// TEST
	//public static final String FORCE_IN_OPEN_ORDERS = "PreSubmitted";
	public static final boolean FORCE_IN_OPEN_ORDERS = true;
	public static final String NO_ORDER_STATUS = "NO_ORDER_STATUS";
	public static final Integer TEST_INITIAL_SPY_PRICE = 13021;
	//public static final Integer TEST_INITIAL_SPY_PRICE = 12721;
	public static final Integer FIVE_OCLOCK = new Integer(17);
	public static final Integer THIRTY_INT = new Integer(30);
	public static final Integer NOON_INT = new Integer(12);
	public static final Integer NEGATIVE_TWO_INT = new Integer(-2);
	public static final Integer NINE_OCLOCK_AM = new Integer(9);
	public static final Integer SIX_OCLOCK_PM = new Integer(18);
	public static final Integer GET_OUT_PERCENT = new Integer (70);
	public static final Integer ONE_HUNDRED_TWENTY = new Integer(120);
	public static final double TEN_THOUSAND_DOUBLE = new Double(10000);
	public static final String NULL_STRING = "";
	public static final double ZERO_DOUBLE = new Double(0);
	public static final Integer OPT_KICKER_TO_SELL = new Integer(2);
	public static final Integer STK_KICKER_TO_EXIT = new Integer(1);

	public static final boolean DEBUG_FLAG = false;
	public static final Integer ONE_OCLOCK_AM = new Integer(1);
	public static final Integer ELEVEN_OCLOCK_PM = new Integer(23);
	public static final Integer FOUR_OCLOCK_PM = new Integer(16);
	public static final Integer THREE_OCLOCK_PM = new Integer(15);
	public static final float TEN_THOUSAND_FLOAT = new Float(10000);
	public static final Double BP_BET_STOP_AND_LIMIT_DOUBLE = new Double(400);  // This is 4 cents - $1.07 to $1.03
	public static final String ZERO_STRING = new String("0");
	public static final Integer OPTIONPRICE_TTL_MS = 25000;
	
	
	public static final String OS_PREFIX = (System.getProperty("os.name").startsWith("Windows")) ? "C:\\JavaApps\\" : "/tmp/";
	
	public static final String SPREAD_SNAPSHOT_FILE = OS_PREFIX + "spreadSnapshot.csv";
	public static final String PL_SNAPSHOT_FILE = OS_PREFIX + "plSnapshot.csv";
	public static final String PL_SNAPSHOT_FILE_ABBREV = OS_PREFIX + "plSnapshotAbbrev.csv";
	public static final String END_OF_CYCLE_FILE = OS_PREFIX + "testftp.txt";
	public static final String MATCH_FILE = OS_PREFIX + "match.csv";
	
	//public static final String SPREAD_SNAPSHOT_FILE = "C:\\JavaApps\\spreadSnapshot.csv";
	public static final String SPREAD_SNAPSHOT_FILE_PLAIN = "spreadSnapshot.csv";
	//public static final String PL_SNAPSHOT_FILE = "C:\\JavaApps\\plSnapshot.csv";
	public static final String PL_SNAPSHOT_FILE_PLAIN = "plSnapshot.csv";
	//public static final String PL_SNAPSHOT_FILE_ABBREV = "C:\\JavaApps\\plSnapshotAbbrev.csv";
	public static final String PL_SNAPSHOT_FILE_ABBREV_PLAIN = "plSnapshotAbbrev.csv";
	//public static final String MATCH_FILE = "C:\\JavaApps\\match.csv";
	public static final String MATCH_FILE_PLAIN = "match.csv";
	//public static final String END_OF_CYCLE_FILE = "C:\\JavaApps\\testftp.txt";
	public static final String SUBMITTED = "Submitted";
	public static final String PRE_SUBMITTED = "PreSubmitted";
	public static final String ONE_UP_ONE_DOWN = "OneUpOneDown";
	public static final String MOVING_AVERAGES = "MovingAverages";
	public static final String MOVING_AVERAGES_2 = "MovingAverages2";
	public static final String SLOPES = "Slopes";
	public static final String MANUAL = "Manual";
	public static final String MANUAL_GET_IN = "Manual-GetIn";
	public static final Integer TWENTY_FIVE_INT = new Integer(25);
	public static final Integer MINUTES_IN_TRADING_DAY = 390; // 6 1/2 hours (9:30 AM to 4:00 PM) x 60 minutes
	public static final Integer MINUTES_BETWEEN_TRADING_DAYS = 1050; // 17 1/2 hours (4:00 PM to 9:30 AM) x 60 minutes
	
	public static Calendar now = Calendar.getInstance();
	public static final Calendar RUN_START_TIME = getTimeOnDay(now,9,30);
	public static final Calendar RUN_END_TIME = getTimeOnDay(now,16,15);
	public static final Calendar RUN_START_TIME_DEBUG = getTimeOnDay(now,1,0);
	public static final Calendar RUN_END_TIME_DEBUG = getTimeOnDay(now,23,30);
	public static final Calendar TRADE_START_TIME = getTimeOnDay(now,9,47);
	public static final Calendar TRADE_END_TIME = getTimeOnDay(now,16,2);
	public static final Calendar EARNINGS_DECISION_TIME = getTimeOnDay(now,15,30);
	
	public static final Integer MARKET_START_TIME_HOUR = 9;
	public static final Integer MARKET_START_TIME_MINUTE = 30;
	public static final Integer MARKET_END_TIME_HOUR = 16;
	public static final Integer MARKET_END_TIME_MINUTE = 0;
	public static final Calendar MARKET_START_TIME = getTimeOnDay(now,MARKET_START_TIME_HOUR,MARKET_START_TIME_MINUTE);
	public static final Calendar MARKET_END_TIME = getTimeOnDay(now,MARKET_END_TIME_HOUR,MARKET_END_TIME_MINUTE);
	public static final Integer FIVE_THOUSAND = 5000;
	public static final Integer FIVE_INT = new Integer(5);

	public static Calendar getTimeOnDay(Calendar onDay, Integer hour, Integer minute) {
		//Calendar now = Calendar.getInstance();
		Calendar returnTime = Calendar.getInstance();
		returnTime.set(onDay.get(Calendar.YEAR), onDay.get(Calendar.MONTH), onDay.get(Calendar.DATE), hour, minute);
		return returnTime;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Here is getRunEndTime: " + RUN_END_TIME.getTime());
		
	}

}

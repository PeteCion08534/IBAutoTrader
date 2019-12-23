package com.theta.process;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.theta.dao.SecurityPriceDAO;
import com.theta.domain.SecurityPrice;
import com.theta.domain.Strategy;
import com.theta.enums.UpOrDownCode;

public class Slope {

	private static Logger logger = Logger.getLogger(Slope.class);

	private static final Integer HUNDRED_THOUSAND = 100000;
	private static final Integer MS_IN_ONE_MINUTE = 60 * 1000;

	private Integer slope1;
	private Integer slope2;
	private Integer slope3;

	private ThetaMutex dbAccessMutex;
	//private Map<String, Integer> slopeMAMap;
	
	SecurityPriceDAO securityPriceDAO;

	// Constructors
	public Slope(SecurityPriceDAO securityPriceDAO, ThetaMutex dbAccessMutex) {
		this.securityPriceDAO = securityPriceDAO;
		this.dbAccessMutex = dbAccessMutex;
	}
	public Slope(SecurityPriceDAO securityPriceDAO, Calendar now, Integer currentPrice, Strategy strategy, ThetaMutex dbAccessMutex) {
		this.securityPriceDAO = securityPriceDAO;
		this.dbAccessMutex = dbAccessMutex;
		slope1 = getSlopeInAdrTbpPerMinute(now, currentPrice, strategy.getSymbol(), strategy.getSlopeTimeMin1());
		slope2 = getSlopeInAdrTbpPerMinute(now, currentPrice, strategy.getSymbol(), strategy.getSlopeTimeMin2());
		slope3 = getSlopeInAdrTbpPerMinute(now, currentPrice, strategy.getSymbol(), strategy.getSlopeTimeMin3());
	}
	public Integer getSlope1() {
		return slope1;
	}
	public Integer getSlope2() {
		return slope2;
	}
	public Integer getSlope3() {
		return slope3;
	}
	
	public boolean isSufficient(Strategy strategy, UpOrDownCode upOrDown) {

		String stratName = strategy.getStrategyName() + ": ";
		
		logger.info(stratName + "Top of isSufficient.");
		Integer targetSlope1 = strategy.getSlopeAdrTbpPerMin1(); 
		Integer targetSlope2 = strategy.getSlopeAdrTbpPerMin2(); 
		Integer targetSlope3 = strategy.getSlopeAdrTbpPerMin3(); 
		logger.info(stratName + "slope 1, 2, 3: " + slope1 + ", " + slope2 + ", " + slope3);

		if (upOrDown.equals(UpOrDownCode.UP))  {
			logger.info(stratName + "target slope 1, 2, 3: " + targetSlope1 + ", " + targetSlope2 + ", " + targetSlope3);
			if ( (slope1 != null) && (slope2 != null) && (slope3 != null) && 
					(slope1.compareTo(targetSlope1) > 0 ) && (slope2.compareTo(targetSlope2) > 0 ) && (slope3.compareTo(targetSlope3) > 0 ) ) {
				return true;
			} else {
				return false;
			}
		} else if (upOrDown.equals(UpOrDownCode.DOWN)) {
			logger.info(stratName + "target slope 1, 2, 3: -" + targetSlope1 + ", -" + targetSlope2 + ", -" + targetSlope3);
			if ( (slope1 != null) && (slope2 != null) && (slope3 != null) && 
  			        (slope1.compareTo(-targetSlope1) < 0 ) && (slope2.compareTo(-targetSlope2) < 0 ) && (slope3.compareTo(-targetSlope3) < 0 ) ) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new RuntimeException(stratName + "UpOrDownCode must be either UP or DOWN!");
		}
		
	}

	
	public boolean isSufficientForExit(Strategy strategy, UpOrDownCode upOrDown) {
		String stratName = strategy.getStrategyName() + ": ";

		logger.info(stratName + "Top of isSufficientForExit.");
		Integer targetSlope1 = strategy.getSlopeAdrTbpPerMin1(); 
		Integer targetSlope2 = strategy.getSlopeAdrTbpPerMin2(); 
		//Integer targetSlope3 = strategy.getSlopeAdrTbpPerMin3(); 
		logger.info(stratName + "slope 1, 2: " + slope1 + ", " + slope2 );
		logger.info(stratName + "target slope 1, 2: " + targetSlope1 + ", " + targetSlope2);

		if (upOrDown.equals(UpOrDownCode.UP))  {
			if ( (slope1 != null) && (slope2 != null) && 
					(slope1.compareTo(targetSlope1) > 0 ) && (slope2.compareTo(targetSlope2) > 0 ) ) {
				return true;
			} else {
				return false;
			}
		} else if (upOrDown.equals(UpOrDownCode.DOWN)) {
			if ( (slope1 != null) && (slope2 != null) &&  
  			        (slope1.compareTo(-targetSlope1) < 0 ) && (slope2.compareTo(-targetSlope2) < 0 ) ) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new RuntimeException(stratName + "UpOrDownCode must be either UP or DOWN!");
		}
		
	}

	
	public boolean isMASufficient(Strategy strategy, UpOrDownCode upOrDown, Integer movAvgNum, Integer targetSlope) throws ThetaExceptionExc {
		
		String stratName = strategy.getStrategyName() + ": ";

		Calendar now = Calendar.getInstance();
		Integer slopeMA = getMASlopeInAdrTbpPerMinute(now, movAvgNum, strategy.getSymbol(), strategy.getSlopeTimeMin3() );

		logger.info(stratName + "isMASufficient.  strategy, upOrDown, movAvgNum, targetSlope, slopeMA: " + strategy + ", " + upOrDown + ", " + movAvgNum + ", " + targetSlope + ", " + slopeMA);
		
		if (null == slopeMA) return false;

		boolean isUp = (upOrDown.equals(UpOrDownCode.UP));
		boolean isDown = (upOrDown.equals(UpOrDownCode.DOWN));

		if (isUp && slopeMA.compareTo(3) > 0) {
			return true;
		} else if (isDown && slopeMA.compareTo(-5) < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	

	public boolean isSufficientWithinTimeOfSlope1(Strategy strategy, UpOrDownCode upOrDown) {

		// Note - slope1 is usually set to "60" 
		
		logger.info("Top of isSufficientWithinTimeOfSlope1.");
		Integer targetSlope1 = strategy.getSlopeAdrTbpPerMin1(); 
		logger.info("slope 1 (value, target, time): (" + slope1 + ", " + targetSlope1 + ", " + strategy.getSlopeTimeMin1() + ")" );

		if (upOrDown.equals(UpOrDownCode.UP))  {
			if ( (slope1 != null)  && (slope1.compareTo(targetSlope1) > 0 ) ) {
				return true;
			} else {
				return false;
			}
		} else if (upOrDown.equals(UpOrDownCode.DOWN)) {
			if ( (slope1 != null) && (slope1.compareTo(-targetSlope1) < 0 ) ) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new RuntimeException("UpOrDownCode must be either UP or DOWN!");
		}
		
	}

	
	public Integer getSlopeInAdrTbpPerMinute(Calendar now, Integer currentPrice, String ticker, Integer minutesAgo) {
		
		if (currentPrice == null) return null;
		
		SecurityPrice sp = getEarliestPriceSP(now, minutesAgo, ticker);
		// can't get a price - 	
		if (sp == null) return null;
		
		Calendar earlierDate = sp.getCreatedDate();
		Integer avgDailyRange = sp.getMovAvgRange();
		Integer earlierPrice = sp.getPrice();
		Integer diffPriceTimesHundredThou = (currentPrice - earlierPrice) * HUNDRED_THOUSAND;
		Integer minutesDiff = getMarketMinutesBetween(earlierDate, now);
		
		if (minutesDiff.equals(0) || avgDailyRange.equals(0)) {
			logger.info("minutesDiff: " + minutesDiff + ". avgDailyRange: " + avgDailyRange + ". Returning null");
			return null;
		}
		
		
		return ( ( diffPriceTimesHundredThou / avgDailyRange ) / minutesDiff ) ;
	}

	
	public Integer getMASlopeInAdrTbpPerMinute(Calendar now, Integer whichAverage, String ticker, Integer minutesAgo) {

		SecurityPrice spEarly = getEarliestPriceSP(now, minutesAgo, ticker);
		SecurityPrice spLate;

		synchronized(dbAccessMutex) {spLate = securityPriceDAO.findLatestRecordBefore(now, ticker);}

		// can't get a price - 	
		if ( spEarly == null || spLate == null ) {
			logger.info("in getMASlopeInAdrTbpPerMinute.  spEarly or spLate is null.");
			return null;
		}
		
		Calendar earlyDate = spEarly.getCreatedDate();
		Calendar lateDate = spLate.getCreatedDate();
		Integer avgDailyRange = spEarly.getMovAvgRange();

		Integer earlyMovingAverage;
		Integer lateMovingAverage;
		if ( whichAverage.equals(1)) {
			earlyMovingAverage = spEarly.getMovAvg1();
			lateMovingAverage = spLate.getMovAvg1();
		} else if (whichAverage.equals(2)){
			earlyMovingAverage = spEarly.getMovAvg2();
			lateMovingAverage = spLate.getMovAvg2();
		} else {
			logger.info("in getMASlopeInAdrTbpPerMinute.  whichAverage is not 1 or 2.");
			return null;
		}
	
		Integer diffPriceTimesHundredThou = (lateMovingAverage - earlyMovingAverage) * HUNDRED_THOUSAND;
		Integer minutesDiff = getMarketMinutesBetween(earlyDate, lateDate);
		
		logger.info("diffPrice, minutes: " + diffPriceTimesHundredThou + ", " + minutesDiff );
		
		return ( ( diffPriceTimesHundredThou / avgDailyRange ) / minutesDiff ) ;
	}

	
	public Integer getEarliestPriceInt(Calendar now, Integer minutesAgo, String ticker) throws DataAccessException {
		SecurityPrice ret = getEarliestPriceSP(now, minutesAgo, ticker);
		if (ret == null) return null;
		return ret.getPrice();
	}
		
	public SecurityPrice getEarliestPriceSP(Calendar now, Integer minutesAgo, String ticker) throws DataAccessException {
		
		if (minutesAgo > ThetaConstants.MINUTES_IN_TRADING_DAY) {
			throw new RuntimeException("This method only allows up to : " + ThetaConstants.MINUTES_IN_TRADING_DAY + "minutes back.  Sent in: " + minutesAgo + ".");
		}
		Calendar fromDateTime = (Calendar) now.clone();
		fromDateTime.add(Calendar.MINUTE, -minutesAgo);
				
		Calendar marketStartTimeOnDay = ThetaConstants.getTimeOnDay(now, ThetaConstants.MARKET_START_TIME.get(Calendar.HOUR), ThetaConstants.MARKET_START_TIME.get(Calendar.MINUTE));
		
		// Target is before start today - subtract the overnight time (MINUTES_BETWEEN_TRADING_DAYS)
		if ( fromDateTime.compareTo(marketStartTimeOnDay) < 0 ){
			fromDateTime.add(Calendar.MINUTE, -ThetaConstants.MINUTES_BETWEEN_TRADING_DAYS);
		}

		// Keep going back one day until there is a day where there are prices
		Integer pricesOnDay;
		Integer count = 0;
		while ( (pricesOnDay = getPricesOnDay(fromDateTime, ticker)).compareTo(2) < 0 ) {
			count++;
			fromDateTime.add(Calendar.DATE,  -1);
			if (count.compareTo(5) > 0 ) {
				throw new RuntimeException("Too many days without Prices for ticker: " + ticker);
			}
		}
		
		SecurityPrice securityPrice;
		synchronized(dbAccessMutex) {securityPrice = securityPriceDAO.findEarliestRecordSince(fromDateTime, ticker);}
		return securityPrice;
	}

	
	/*
	 * Gets the minutes between two dates when the market is open only.
	 * *** CAVEAT - as this function has as its maximum one market day between
	 * *** two dates, this works ONLY when the two dates are at most one full "market"
	 * *** day apart.
	 */
	protected Integer getMarketMinutesBetween(Calendar earlier, Calendar later) {

		if (later.compareTo(earlier) <= 0 ) {
			throw new RuntimeException("The Calendar object later must be AFTER earler.  later, earlier: " + later.getTime() + ", " + earlier.getTime());
		}
		
		// Same date - elapsed time only
		if (getDateOnly(later).equals(getDateOnly(earlier))) {
			return safeLongToInt( (later.getTimeInMillis() - earlier.getTimeInMillis()) / MS_IN_ONE_MINUTE );
		} else {
			long millisAtStartOfLaterDay = ThetaConstants.getTimeOnDay(later, ThetaConstants.MARKET_START_TIME_HOUR, ThetaConstants.MARKET_START_TIME_MINUTE).getTimeInMillis();
			long millisAtEndOfEarlierDay = ThetaConstants.getTimeOnDay(earlier, ThetaConstants.MARKET_END_TIME_HOUR, ThetaConstants.MARKET_END_TIME_MINUTE).getTimeInMillis();
			return safeLongToInt( ((later.getTimeInMillis() - millisAtStartOfLaterDay) + (millisAtEndOfEarlierDay - earlier.getTimeInMillis())) / MS_IN_ONE_MINUTE );
		}
	}

	private Calendar getDateOnly(Calendar dt) {
		return ThetaConstants.getTimeOnDay(dt, 0, 0);
	}
	
	public Integer getPricesOnDay(Calendar theDate, String ticker) throws DataAccessException {
		synchronized(dbAccessMutex) {
			return securityPriceDAO.findNumRecordsOnDate(theDate, ticker);
		}
	}	

	
	public static int safeLongToInt(long l) {
	    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException
	            (l + " cannot be cast to int without changing its value.");
	    }
	    return (int) l;
	}
	
}

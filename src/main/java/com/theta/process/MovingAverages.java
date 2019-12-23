package com.theta.process;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.theta.dao.SecurityPriceDAO;
import com.theta.enums.MovingAverageType;

public class MovingAverages {
		
	private static Logger log = Logger.getLogger(MovingAverages.class);

	Map<String, MovingAverage> movingAverageMap = new HashMap<String, MovingAverage>(50);
	SecurityPriceDAO securityPriceDAO;
	ThetaMutex dbAccessMutex;
	
	public MovingAverages(SecurityPriceDAO securityPriceDAO, ThetaMutex dbAccessMutex) {
		this.securityPriceDAO = securityPriceDAO; 
		this.dbAccessMutex = dbAccessMutex;
	}

	public Integer getMovingAverage(String ticker, Integer daysAgo, boolean forceLookup, MovingAverageType maType) {

		MovingAverage mavg = findOrCreateMovingAverageInitial(ticker, daysAgo, maType);
		if ( mavg.updatedDate != null && isWithinLastHour(mavg.updatedDate) && !forceLookup) {
			log.info("Found it - returning cached value.");
			return mavg.movingAverage;
		}
		
		mavg.ticker = ticker;
		mavg.daysAgo = daysAgo;
		mavg.maType = maType.getValue();
		Calendar fromDate = Calendar.getInstance();
		fromDate.add(Calendar.DAY_OF_YEAR, -daysAgo);
		fromDate.set(Calendar.HOUR_OF_DAY,0);
		fromDate.set(Calendar.MINUTE,0);
		fromDate.set(Calendar.SECOND,0);
		
		Calendar toDate = Calendar.getInstance();
		
		//Integer movingAverage = 0;
		try {
			log.info("Calling securityPriceDAO.findMovingAverage(from,to,ticker,type): " + fromDate.getTime() + ", " + toDate.getTime() + ", " + ticker + ", " + maType.getValue());
			if (maType.equals(MovingAverageType.DAILYPRICE)) {				
				synchronized(dbAccessMutex) {mavg.movingAverage = securityPriceDAO.findMovingAverage(fromDate, toDate, ticker);}
			} else if (maType.equals(MovingAverageType.DAILYRANGE)) {
				synchronized(dbAccessMutex) {mavg.movingAverage = securityPriceDAO.findMovingAverageDailyRange(fromDate, toDate, ticker);}
			} else {
				log.error("Moving Average Type must be : DAILYPRICE or DAILYRANGE.  Type: " + maType.getValue() );
			}
			log.info("RESULT: " + mavg.movingAverage);
			mavg.updatedDate = Calendar.getInstance();
		} catch (Exception ex){
			log.error("Exception in finding moving average: ", ex);
			System.out.println("******* Error after looking up moving average! ****** ");
			ex.printStackTrace();
		}
		
		log.info("ticker, movingAverage: " + mavg.ticker + " , " + mavg.movingAverage);
		log.info("ALL MOVING AVERAGES: " + this.toString());
		//System.out.println("Here is movingAverage: " + mavg.movingAverage);
		
		return mavg.movingAverage;
	}
	
	private Integer getPercentDiff(Integer int1, Integer int2) {
		if (int1 == null || int1.equals(new Integer(0)) || int2 == null || int2.equals(new Integer(0)) )
			return null;
		
		if (int1.compareTo(int2) > 0){
			return (((int1 - int2) * 100 )/int1);
		} else {
			return (((int2 - int1) * 100)/int2);
		}
	}
	
	public boolean areMAvgsWithinTolerancePercent(String ticker, Integer daysAgoAvg1, Integer daysAgoAvg2, Integer currentPrice, Integer tolerancePct) {
		Integer percentDiff1 = getPercentDiff(daysAgoAvg1, currentPrice);
		Integer percentDiff2 = getPercentDiff(daysAgoAvg2, currentPrice);
		
		if (percentDiff1 == null || percentDiff2 == null){
			log.info("percentDiff 1 or 2 is null.  daysAgoAvg1, daysAgoAvg2: " + daysAgoAvg1 + ", " + daysAgoAvg2);
			return false;
		}
		
		if ( (percentDiff1.compareTo(tolerancePct) < 0) && (percentDiff2.compareTo(tolerancePct) < 0) ) {
			return true;
		} else {
			log.info("Moving Averages are NOT within " + tolerancePct + " percent.");
			log.info("currentPrice: " + currentPrice);
			log.info("Average1, Average2: " + daysAgoAvg1 + ", " + daysAgoAvg2 );
			log.info("percentDiff1, percentDiff2: " + percentDiff1 + ", " + percentDiff2);
			return false;
		}
	}

	public boolean isRangeLessThanTwentyFivePercentOfPrice(String ticker, Integer averageDailyRange, Integer currentPrice) {
		Integer percentDiff = getPercentDiff(averageDailyRange, currentPrice);
		
		if (percentDiff == null ){
			log.info("percentDiff is null.  averageDailyRange, currentPrice: " + averageDailyRange + ", " + currentPrice);
			return false;
		}
		
		if ( percentDiff.compareTo(75) > 0 ) {
			return true;
		} else {
			log.info("Average Daily Range less than 75 percent of currrent Price.");
			log.info("currentPrice: " + currentPrice);
			log.info("AverageDailyRange: " + averageDailyRange );
			log.info("percentDiff: " + percentDiff);
			return false;
		}
	}
	
	private String movingAverageKey(String ticker, Integer daysAgo, MovingAverageType type) {
		return (ticker + "-" + daysAgo + "-" + type.getValue());
	}
	
	private MovingAverage findOrCreateMovingAverageInitial(String ticker, Integer daysAgo, MovingAverageType maType) {
		String movingAverageKey = movingAverageKey(ticker, daysAgo, maType);
		MovingAverage ma = movingAverageMap.get(movingAverageKey);

		if (null == ma) {
			MovingAverage maNew = new MovingAverage();
			maNew.ticker = ticker;
			maNew.daysAgo = daysAgo;
			maNew.maType = maType.getValue();
			movingAverageMap.put(movingAverageKey, maNew);
			return maNew;
		} else {
			return ma;
		}
	}

	public AllMovingAverages getAllMovingAverages(String ticker, Integer daysAgo1, Integer daysAgo2, Integer daysAgoRange, Integer currentPrice, Integer gracePts, Integer tolerancePct) {
		AllMovingAverages allAvg = new AllMovingAverages();
		
		if (ticker == null || ticker.isEmpty()){
			log.info("Ticker is null or empty.  Returning null.");
			return null;
		}
		if (daysAgo1 == null || daysAgo1.equals(0) || daysAgo2 == null || daysAgo2.equals(0) || daysAgoRange == null || daysAgoRange.equals(0) ){
			log.info("daysAgo1, daysAgo2 or daysAgoRange is null or zero.  Returning null.");
			return null;
		}
		if (currentPrice == null || currentPrice.equals(0)) {
			log.info("currentPrice is null or zero. Returning null.");
			return null;
		}
		if (gracePts== null || gracePts.equals(0)){
			log.info("gracePts is null or zero.  returning null.");
			return null;
		}
		if (tolerancePct== null || tolerancePct.equals(0)){
			log.info("tolerancePct is null or zero.  returning null.");
			return null;
		}
		
		
		Integer daysAgoAvg1 = getMovingAverage(ticker, daysAgo1, false, MovingAverageType.DAILYPRICE);
		Integer daysAgoAvg2 = getMovingAverage(ticker, daysAgo2, false, MovingAverageType.DAILYPRICE);
		Integer averageDailyRange = getMovingAverage(ticker, daysAgoRange, false, MovingAverageType.DAILYRANGE);
		if (!areMAvgsWithinTolerancePercent(ticker, daysAgoAvg1, daysAgoAvg2, currentPrice, tolerancePct)) {
			log.info("Averages are NOT within " + tolerancePct + " percent - setting movingAverageOutOfRange to TRUE.");
			allAvg.movingAverageOutOfRange = true;
		}
		if (!isRangeLessThanTwentyFivePercentOfPrice(ticker, averageDailyRange, currentPrice)) {
			log.info("Range is too large - setting movingAverageOutofRange to TRUE.");
			allAvg.movingAverageOutOfRange = true;
		}
		
		
		Integer hiAvg = null;
		Integer loAvg = null;
		if (daysAgoAvg1.compareTo(daysAgoAvg2) > 0) {
			hiAvg = daysAgoAvg1;
			loAvg = daysAgoAvg2;
		} else {
			hiAvg = daysAgoAvg2;
			loAvg = daysAgoAvg1;
		}
		
		Integer gracePointsXAvgDailyRange = (gracePts * averageDailyRange) / 100;
		if (gracePointsXAvgDailyRange < 2) gracePointsXAvgDailyRange = 2;
		
		allAvg.gracePointsXAvgDailyRange = gracePointsXAvgDailyRange;
		allAvg.hiAvg = hiAvg;
		allAvg.hiAvgPlus = hiAvg + gracePointsXAvgDailyRange;
		allAvg.hiAvgMinus = hiAvg - gracePointsXAvgDailyRange;
		allAvg.loAvg = loAvg;
		allAvg.loAvgPlus = loAvg + gracePointsXAvgDailyRange;
		allAvg.loAvgMinus = loAvg - gracePointsXAvgDailyRange;
		allAvg.avgDailyRange = averageDailyRange;
		
		if (allAvg.hiAvgMinus.compareTo(allAvg.loAvgPlus) < 0 ) {
			Integer midAvg = (allAvg.hiAvgPlus + allAvg.loAvgMinus)/2;
			allAvg.hiAvgMinus = midAvg;
			allAvg.loAvgPlus = midAvg;
		}
		
		return allAvg;
	}
	
	private boolean isWithinLastHour(Calendar targetDate) {
		Long secondsInPast = ThetaUtil.secondsInPast(targetDate);
		if (secondsInPast.compareTo(ThetaConstants.SECONDS_IN_HOUR_LONG) < 0 ) {
			return true;
		}
		return false;
	}
	
	
	
	public String toString() {

		StringBuilder ret = new StringBuilder();
		for( Map.Entry<String, MovingAverage>  entry : movingAverageMap.entrySet()) {
			String key = entry.getKey();
			MovingAverage ma = entry.getValue();
			ret.append(key + ": " + ma + "\n");
		}
		
		return ret.toString();
	}
	
	private class MovingAverage {
		private String ticker = null;
		private Integer daysAgo = null;
		private Integer movingAverage = null;
		private Calendar updatedDate = null;
		public String maType = null;
		
		@Override
		public String toString() {
			String updatedTime = null;
			if (updatedDate != null) {
				updatedTime = updatedDate.getTime().toString();
			}
			
			return "MovingAverage " +
					"[ticker=" + ticker + 
					", daysAgo=" + daysAgo + 
					", maType=" + maType + 
					", movingAverage=" + movingAverage +
					", updatedDate=" + updatedTime + "]";
		}
	}

/*
	private class AverageDailyRange {
		private String ticker = null;
		private Integer daysAgo = null;
		private Integer averageDailyRange = null;
		private Calendar updatedDate = null;
		
		@Override
		public String toString() {
			return "averageDailyRange " +
					"[ticker=" + ticker + 
					", daysAgo=" + daysAgo + 
					", averageDailyRange=" + averageDailyRange +
					", updatedDate=" + updatedDate.getTime() + "]";
		}
	}
*/
	
	public class AllMovingAverages {
		public Integer hiAvg;
		public Integer hiAvgPlus;
		public Integer hiAvgMinus;
		public Integer loAvg;
		public Integer loAvgPlus;
		public Integer loAvgMinus;
		public Integer avgDailyRange;
		public Integer gracePointsXAvgDailyRange;
		public boolean movingAverageOutOfRange = false;
		
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("AllMovingAverages [hiAvg=");
			builder.append(hiAvg);
			builder.append(", hiAvgPlus=");
			builder.append(hiAvgPlus);
			builder.append(", hiAvgMinus=");
			builder.append(hiAvgMinus);
			builder.append(", loAvg=");
			builder.append(loAvg);
			builder.append(", loAvgPlus=");
			builder.append(loAvgPlus);
			builder.append(", loAvgMinus=");
			builder.append(loAvgMinus);
			builder.append(", avgDailyRange=");
			builder.append(avgDailyRange);
			builder.append(", gracePointsXAvgDailyRange=");
			builder.append(gracePointsXAvgDailyRange);
			builder.append(", movingAverageOutOfRange=");
			builder.append(movingAverageOutOfRange);
			builder.append("]");
			return builder.toString();
		}
	}
	
	
	/*
	 * For testing
	 */
	public void setUpdatedDate(String ticker, Integer daysAgo, Calendar updatedDate){
		synchronized(dbAccessMutex) {
			MovingAverage ma = movingAverageMap.get(movingAverageKey(ticker,daysAgo,MovingAverageType.DAILYPRICE));
			ma.updatedDate = updatedDate;
		}
	}
	public void setNewMovingAverage(String ticker, Integer daysAgo, Integer newMovingAverage){
		String maKey = movingAverageKey(ticker, daysAgo, MovingAverageType.DAILYPRICE);
		MovingAverage ma = new MovingAverage();
		ma.daysAgo = daysAgo;
		ma.ticker = ticker;
		ma.movingAverage = newMovingAverage;
		movingAverageMap.put(maKey, ma);
	}
	public Calendar getUpdatedDate(String ticker, Integer daysAgo){
		synchronized(dbAccessMutex) {
			MovingAverage ma = movingAverageMap.get(movingAverageKey(ticker,daysAgo,MovingAverageType.DAILYPRICE));
			return(ma.updatedDate);
		}
	}
	public String getTicker(String ticker, Integer daysAgo){
		synchronized(dbAccessMutex) {
			MovingAverage ma = movingAverageMap.get(movingAverageKey(ticker,daysAgo,MovingAverageType.DAILYPRICE));
			return(ma.ticker);
		}
	}
	public Integer getDaysAgo(String ticker, Integer daysAgo){
		synchronized(dbAccessMutex) {
			MovingAverage ma = movingAverageMap.get(movingAverageKey(ticker,daysAgo,MovingAverageType.DAILYPRICE));
			return(ma.daysAgo);
		}
	}

}

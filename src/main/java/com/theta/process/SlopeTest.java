package com.theta.process;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.theta.dao.SecurityPriceDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./src/main/resources/theta11-generated-dao-context.xml" })
@Transactional
public class SlopeTest {

	private static Logger log = Logger.getLogger(SlopeTest.class);

	/**
	 * The Spring application context.
	 *
	 */
	@SuppressWarnings("unused")
	private ApplicationContext context;

	/**
	 * The service being tested, injected by Spring.
	 *
	 */
	@Autowired
	private SecurityPriceDAO securityPriceDAO;
	
	Slope slope;
	Calendar now ;
	
	@Before
	public void startUp() {
		ThetaMutex dbAccessMutex = new ThetaMutex();
		slope = new Slope(securityPriceDAO, dbAccessMutex);
		//slope = new Slope();
		now = Calendar.getInstance();
	}
	
	@Test
	public void testSecurityPriceClass() {
		
		Calendar fromDate = Calendar.getInstance();
		fromDate.add(Calendar.DATE, -20);
		Calendar toDate = Calendar.getInstance();
		String ticker = "UA";

		Integer ma = securityPriceDAO.findMovingAverage(fromDate, toDate, ticker);
		System.out.println("here is ma: " + ma);
		
	}

    /*
	insert into security_price values (security_price_seq.nextval, 'TST', 1006, to_date('20140319-1115', 'YYYYMMDD-HH24MI'), 'Y',0,0,10,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1005, to_date('20140319-1215', 'YYYYMMDD-HH24MI'), 'Y',0,0,11,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1004, to_date('20140319-1300', 'YYYYMMDD-HH24MI'), 'Y',0,0,12,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1003, to_date('20140319-1400', 'YYYYMMDD-HH24MI'), 'Y',0,0,13,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1009, to_date('20140319-1540', 'YYYYMMDD-HH24MI'), 'Y',0,0,14,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1008, to_date('20140319-1547', 'YYYYMMDD-HH24MI'), 'Y',0,0,15,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1007, to_date('20140319-1555', 'YYYYMMDD-HH24MI'), 'Y',0,0,16,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1000, to_date('20140320-1030', 'YYYYMMDD-HH24MI'), 'Y',0,0,17,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1001, to_date('20140320-1103', 'YYYYMMDD-HH24MI'), 'Y',0,0,18,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1002, to_date('20140320-1130', 'YYYYMMDD-HH24MI'), 'Y',0,0,19,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1012, to_date('20140321-1540', 'YYYYMMDD-HH24MI'), 'Y',0,0,20,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1011, to_date('20140321-1547', 'YYYYMMDD-HH24MI'), 'Y',0,0,21,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1010, to_date('20140321-1555', 'YYYYMMDD-HH24MI'), 'Y',0,0,22,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1016, to_date('20140325-1130', 'YYYYMMDD-HH24MI'), 'Y',0,0,23,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1015, to_date('20140325-1205', 'YYYYMMDD-HH24MI'), 'Y',0,0,24,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1014, to_date('20140325-1255', 'YYYYMMDD-HH24MI'), 'Y',0,0,25,'HAND');
	insert into security_price values (security_price_seq.nextval, 'TST', 1013, to_date('20140325-1300', 'YYYYMMDD-HH24MI'), 'Y',0,0,26,'HAND');
	*/

	
	/**
	 *  Symbol = "TST"
	 *  
	 *  1. Test one hour ago (happy case)
	 *    Now = Thursday, March 20, 2014 12:00 noon
	 *    10:30 AM 3/20 - 1000   (17)
	 *    11:03 AM 3/20 - 1001 * (18)
	 *    11:30 AM 3/20 - 1002   (19)
	 */
	@Test
	public void testOneHourAgo() {
		now.set(2014, 2, 20, 12, 0, 0);
		Integer earliestPrice = slope.getEarliestPriceInt(now, 60, "TST");
		Assert.assertEquals(new Integer(1001), earliestPrice);
	}
	
	/**
	 * Price between now and an an hour ago: 180 points (cents)
	 * Minutes between now and and an hour ago: 60
	 * (Actual) minutes ago: 57
	 * 
	 * Average Daily Range: 18
	 * Price * 10000 / 18 / 57 = 1754 
	 * 
	 */
	@Test
	public void testSlopeOneHourAgo() {
		now.set(2014, 2, 20, 12, 0, 0);
		System.out.println("NOW: " + now.getTime());
		Integer theSlope = slope.getSlopeInAdrTbpPerMinute(now, (1001 + 180), "TST", 60);
		System.out.println("Hre is the slope: " + theSlope);
		
		Assert.assertEquals(new Integer(17543), theSlope);
	}

	
	
	/**    
	 *  2. Test one day ago (happy case)
	 *    Now = Thursday, March 20, 2014 12:00 noon
	 *    Wednesday 11:15 3/19 - 1006 
	 *    Wednesday 12:15 3/19 - 1005 * (11)
	 *    Wednesday 13:00 3/19 - 1004
	 *    Wednesday 14:00 3/19-  1003
	 */
	@Test
	public void testOneDayAgo() {
		now.set(2014, 2, 20, 12, 0, 0);
		Integer earliestPrice = slope.getEarliestPriceInt(now, ThetaConstants.MINUTES_IN_TRADING_DAY, "TST");
		Assert.assertEquals(new Integer(1005), earliestPrice);
	}

	/**
	 * Price between now and an an hour ago: 180 points (cents)
	 * Minutes between now and and a day ago: 390
	 * (Actual) minutes ago: 375
	 * Average Daily Range: 11
	 * Price * 10000 / 11 / 375 = 266 
	 * 
	 */
	@Test
	public void testSlopeOneDayAgo() {
		now.set(2014, 2, 20, 12, 0, 0);
		Integer theSlope = slope.getSlopeInAdrTbpPerMinute(now, (1005 + 110), "TST", ThetaConstants.MINUTES_IN_TRADING_DAY);
		System.out.println("Hre is the slope: " + theSlope);
		
		Assert.assertEquals(new Integer(2666), theSlope);

	}

	@Test
	public void testNegativeSlopeOneDayAgo() {
		now.set(2014, 2, 20, 12, 0, 0);
		Integer theSlope = slope.getSlopeInAdrTbpPerMinute(now, (1005 - 110), "TST", ThetaConstants.MINUTES_IN_TRADING_DAY);
		System.out.println("Hre is the slope: " + theSlope);
		
		Assert.assertEquals(new Integer(-2666), theSlope);

	}

	/**
	 *  3. Test one hour ago (beginning of day Weekday)
	 *    Now = Thursday, March 20, 2014 10:15 AM 
	 *	  Wednesday 15:40 3/19 - 1009     
	 *    Wednesday 15:47 3/19 - 1008 *
	 *    Wednesday 15:55 3/19 - 1007
	 */	
	@Test
	public void testOneHourAgoBeginningOfDayWeekday() {
		now.set(2014, 2, 20, 10, 15, 0);
		Integer earliestPrice = slope.getEarliestPriceInt(now, 60, "TST");
		Assert.assertEquals(new Integer(1008), earliestPrice);
	}

	/**    
	 *  4. Test one hour ago (beginning of day Monday)
	 *    Now = Monday, March 24, 2014 10:15 AM 
	 *	  Friday 15:40 3/19 - 1012     
	 *    Friday 15:47 3/19 - 1011 *
	 *    Friday 15:55 3/19 - 1010
	 */
	@Test
	public void testOneHourAgoBeginningOfDayMonday() {
		now.set(2014, 2, 24, 10, 15, 0);
		Integer earliestPrice = slope.getEarliestPriceInt(now, 60, "TST");
		Assert.assertEquals(new Integer(1011), earliestPrice);
	}
	 /**    
	 *  5. Test more than one day ago
	 *    throw an error
	 */    
	@Test (expected = RuntimeException.class)
	public void testMoreThanOneDayAgo() {
		now.set(2014, 2, 24, 9, 45, 0);
		Integer earliestPrice = slope.getEarliestPriceInt(now, ThetaConstants.MINUTES_IN_TRADING_DAY + 100, "TST");
	}
	/**    
	 *    
	 *  6. Test one hour ago - Tuesday AM, Monday holiday
	 *    Now = Tuesday, March 25, 2014 10:15 AM 
	 *    NO TRAFFIC ON MONDAY
	 *	  Friday 15:40 3/21 - 1012     
	 *    Friday 15:47 3/21 - 1011 *
	 *    Friday 15:55 3/21 - 1010
	 */    
	@Test
	public void testOneHourAgoBeginningOfDayTuesdayNoTrafficMonday() {
		now.set(2014, 2, 25, 10, 15, 0);
		Integer earliestPrice = slope.getEarliestPriceInt(now, 60, "TST");
		Assert.assertEquals(new Integer(1011), earliestPrice);
	}
	 /**    
	 *  7. Test one day ago - Wednesday off
	 *    Now = Thursday March 27, 2014 12:00 Noon
	 *    a. NO TRAFFIC on Wednesaday March 26
	 *    Tuesday 11:30 3/25 - 1016
	 *    Tuesday 12:05 3/25 - 1015 *
	 *    Tuesday 12:55 3/25 - 1014
	 *    Tuesday 13:00 3/25 - 1013
	 */
	@Test
	public void testOneDayAgoWedensdayOff() {
		now.set(2014, 2, 27, 12, 0, 0);
		Integer earliestPrice = slope.getEarliestPriceInt(now, ThetaConstants.MINUTES_IN_TRADING_DAY, "TST");
		Assert.assertEquals(new Integer(1015), earliestPrice);
	}
	
	@Test
	public void testGetAllPricesOnWeekendAndWeekday() {
		Calendar theDay= Calendar.getInstance();
		theDay.set(2014,2,29);
		Assert.assertEquals(theDay.DAY_OF_WEEK, Calendar.SATURDAY);
		Integer pricesOnDay = slope.getPricesOnDay(theDay, "UA");
		System.out.println("pricesOnDay: day: " + theDay.getTime() + ". Num: " + pricesOnDay);
		Assert.assertEquals(new Integer(0), pricesOnDay);
		
		theDay.set(2014,3,1);
		pricesOnDay = slope.getPricesOnDay(theDay, "UA");
		System.out.println("pricesOnDay: day: " + theDay.getTime() + ". Num: " + pricesOnDay);
		Assert.assertTrue(pricesOnDay.compareTo(0) > 0);
	}
	
	@Test
	public void testSlopeBasic() {
		
		//slope.getEarliestPriceInt(securityPriceDAO, new Integer(1440) );
	}

	@Test
	public void testGetMinutesBetweenSameDay() {
		
		Calendar earlier = Calendar.getInstance();
		earlier.set(2014,3,7,12,0,0);
		Calendar later = (Calendar) earlier.clone();
		
		Integer minutesDiff = 80;
		earlier.add(Calendar.MINUTE, -minutesDiff);
		System.out.println("earlier, later: " + earlier.getTime() + ", " + later.getTime() );
				
		Integer minBetween =  slope.getMarketMinutesBetween(earlier, later);
		System.out.println("here is min:" + minBetween);
		Assert.assertTrue(minBetween.equals(minutesDiff));
	}
	
	@Test
	public void testGetMinutesBetweenPreviousDay() {
		
		Calendar earlier = Calendar.getInstance();
		earlier.set(2014,3,4,14,0,0);
		// Time from earlier to EOD: 2:30 to 4:30 = 120 minutes

		Calendar later = Calendar.getInstance();
		later.set(2014,3,7,12,0,0);
		// Time from BOD to later: 9:30 to 12:00 = 150 minues
		
		// Total = 270 minutes
		Integer expectedMinutesDiff = 270;
						
		Integer minBetween =  slope.getMarketMinutesBetween(earlier, later);
		Assert.assertTrue(minBetween.equals(expectedMinutesDiff));
	}
	
	
	@Test (expected = RuntimeException.class)
	//@Test
	public void testEarlierAfterLater() {
		Calendar earlier = Calendar.getInstance();
		earlier.set(2014,3,4,14,0,0);

		Calendar later = Calendar.getInstance();
		earlier.set(2014,3,7,12,0,0);
		
		slope.getMarketMinutesBetween(later, earlier);
	}
	
}

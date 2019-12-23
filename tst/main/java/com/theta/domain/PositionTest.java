package com.theta.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.theta.enums.LongOrShortCode;

public class PositionTest {

	Position position = null;
	Strategy strategy = null;
	LongOrShortCode longOrShort;
	Integer avgDailyRange = null;
	Integer enterPrice = null;
	
	@Before
	public void setUp() {
		position = new Position();
		strategy = new Strategy();
		strategy.stkTrailingStopBpTimesRange = 30;
		strategy.stkTrailingStopBpTimesRange2 = 50;
		strategy.stkTrailingStopBpCutpoint = 300;
		avgDailyRange = 100;
		enterPrice = 3000;
	}

	/**
	 * Test #1 - LONG Below cutpoint but get out at trailingStop #1
	 * 
	 * AverageDailyRange = 100
	 * stkTrailingStopPbTimesRange = 30
	 * 
	 * Cutpoint: Purchase Price + 3 * AverageDailyRange (100) = 3300
	 * 
	 * XTreme:            3150
	 * ==> Trailing Stop: 3120
	 * Purchase price :   3000
	 */
	
	@Test
	public void testGetTrailingStopByRangeLong() {
		longOrShort = LongOrShortCode.LONG;
		position.stkLocalXtremePrice  = 3150;
		
		Integer trailingStop = position.getTrailingStopByRange(avgDailyRange, strategy, enterPrice, longOrShort) ;
		Assert.assertEquals(new Integer(3120), trailingStop);
	}
	
	/**
	 * Test #2 LONG - Above cutpoint - get out at trailingStop #2
	 * 
	 * AverageDailyRange = 100
	 * stkTrailingStopPbTimesRange = 30
	 * stkTrailingStopPbTimesRange = 50
	 * 
	 * Cutpoint: Purchase Price + 3 * AverageDailyRange (100) = 3300
	 * 
	 * XTreme:              3400
	 * ==> Trailing Stop:   3350
	 * Cutpoint:            3300
\	 * Purchase price :     3000
	 */
	@Test
	public void testGetTrailingStopByRangeAboveCutpointLong() {

		longOrShort = LongOrShortCode.LONG;
		position.stkLocalXtremePrice  = 3400;
		Integer enterPrice = 3000;
		
		Integer trailingStop = position.getTrailingStopByRange(avgDailyRange, strategy, enterPrice, longOrShort) ;
		Assert.assertEquals(new Integer(3350), trailingStop);
	}
	
	/**
	 * Test #3 LONG - cutpoint in middle- get out at trailingStop #2
	 * 
	 * AverageDailyRange = 100
	 * stkTrailingStopPbTimesRange = 30
	 * stkTrailingStopPbTimesRange = 50
	 * 
	 * Cutpoint: Purchase Price + 3 * AverageDailyRange (100) = 3300
	 * 
	 * XTreme:              3340
	 * Cutpoint:            3300
	 * ==> Trailing Stop:   3290
\	 * Purchase price :     3000
	 */
	
	@Test
	public void testGetTrailingStopByRangeMidCutpointLong() {

		longOrShort = LongOrShortCode.LONG;
		position.stkLocalXtremePrice  = 3340;
		Integer enterPrice = 3000;
		
		Integer trailingStop = position.getTrailingStopByRange(avgDailyRange, strategy, enterPrice, longOrShort) ;
		Assert.assertEquals(new Integer(3290), trailingStop);
	}

	

	
	/**
	 * Test #1A - SHORT Below cutpoint but get out at trailingStop #1
	 * 
	 * AverageDailyRange = 100
	 * stkTrailingStopPbTimesRange = 30
	 * 
	 * Cutpoint: Purchase Price - 3 * AverageDailyRange (100) = 2700
	 * 
	 * Purchase price :   3000
	 * ==> Trailing Stop: 2880
	 * XTeme:             2850
	 * 
	 */
	@Test
	public void testGetTrailingStopByRangeShort() {
		longOrShort = LongOrShortCode.SHORT;
		position.stkLocalXtremePrice  = 2850;
		
		Integer trailingStop = position.getTrailingStopByRange(avgDailyRange, strategy, enterPrice, longOrShort) ;
		Assert.assertEquals(new Integer(2880), trailingStop);
	}
	
	/**
	 * Test #2A SHORT - Above cutpoint - get out at trailingStop #2
	 * 
	 * AverageDailyRange = 100
	 * stkTrailingStopPbTimesRange = 30
	 * stkTrailingStopPbTimesRange = 50
	 * 
	 * Cutpoint: Purchase Price + 3 * AverageDailyRange (100) = 2700
	 * 
	 * Purchase price :     3000
	 * Cutpoint:            2700
	 * == Trailing Stop:    2450
	 * XTreme:              2400
	 */
	@Test
	public void testGetTrailingStopByRangeAboveCutpointShort() {

		longOrShort = LongOrShortCode.SHORT;
		position.stkLocalXtremePrice  = 2400;
		Integer enterPrice = 3000;
		
		Integer trailingStop = position.getTrailingStopByRange(avgDailyRange, strategy, enterPrice, longOrShort) ;
		Assert.assertEquals(new Integer(2450), trailingStop);
	}
	
	/**
	 * Test #3A SHORT - cutpoint in middle- get out at trailingStop #2
	 * 
	 * AverageDailyRange = 100
	 * stkTrailingStopPbTimesRange = 30
	 * stkTrailingStopPbTimesRange = 50
	 * 
	 * Cutpoint: Purchase Price + 3 * AverageDailyRange (100) = 3300
	 * 
	 * Purchase price :     3000
	 * ==> Trailing Stop:   2710
	 * Cutpoint:            2700
	 * XTreme:              2660
	 */
	
	@Test
	public void testGetTrailingStopByRangeMidCutpointShort() {

		longOrShort = LongOrShortCode.SHORT;
		position.stkLocalXtremePrice  = 2660;
		Integer enterPrice = 3000;
		
		Integer trailingStop = position.getTrailingStopByRange(avgDailyRange, strategy, enterPrice, longOrShort) ;
		Assert.assertEquals(new Integer(2710), trailingStop);
	}


	
}

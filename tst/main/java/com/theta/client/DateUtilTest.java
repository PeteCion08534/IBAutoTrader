package com.theta.client;

import java.util.Calendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class DateUtilTest {

	DateUtil dateUtil = new DateUtil();
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testPrevBusDayHappyCase() {
		Calendar today = Calendar.getInstance();
		today.set(2015, 3, 30);
		Calendar yesterday = Calendar.getInstance();
		yesterday.set(2015, 3, 29);
		Assert.assertTrue(dateUtil.isPreviousBusDay(today,  yesterday));
	}
	@Test
	public void testPrevBusDaySadCase() {
		Calendar today = Calendar.getInstance();
		today.set(2015, 3, 30);
		Calendar yesterday = Calendar.getInstance();
		yesterday.set(2015, 3, 27);
		Assert.assertFalse(dateUtil.isPreviousBusDay(today,  yesterday));
	}
	@Test
	public void testPrevBusDayTodayMonday() {
		Calendar today = Calendar.getInstance();
		today.set(2015, 3, 27);
		Calendar yesterday = Calendar.getInstance();
		yesterday.set(2015, 3, 24);
		Assert.assertTrue(dateUtil.isPreviousBusDay(today,  yesterday));
	}
	@Test
	public void testPrevBusDayTodayMondayNoMatch() {
		Calendar today = Calendar.getInstance();
		today.set(2015, 3, 27);
		Calendar yesterday = Calendar.getInstance();
		yesterday.set(2015, 3, 20);
		Assert.assertFalse(dateUtil.isPreviousBusDay(today,  yesterday));
	}
	
	@Test (expected = NullPointerException.class)
	public void testNull()
	{
		dateUtil.isPreviousBusDay(null, null);
		
	}
	
	
	
	
}

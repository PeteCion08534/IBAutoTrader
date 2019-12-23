package com.theta.externaldata;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.theta.client.DateUtil;
import com.theta.externaldata.EarningsDate;
import com.theta.process.ThetaExceptionExc;

public class EarningsDateTest {

	EarningsDate ed = null;

	Calendar jan31_2014 = Calendar.getInstance();
	Calendar may1_2014 = Calendar.getInstance();

	Calendar jul29_2014 = Calendar.getInstance();
	Calendar jul30_2014 = Calendar.getInstance();
	Calendar jul31_2014 = Calendar.getInstance();
	Calendar aug1_2014 = Calendar.getInstance();
	Calendar aug4_2014 = Calendar.getInstance();
	Calendar oct29_2014 = Calendar.getInstance();
	Calendar dec5_2014 = Calendar.getInstance();

	Calendar jan2_2015 = Calendar.getInstance();
	Calendar jan5_2015 = Calendar.getInstance();
	Calendar jan6_2015 = Calendar.getInstance();
	Calendar jan7_2015 = Calendar.getInstance();
	Calendar jan29_2015 = Calendar.getInstance();
	
	List<Calendar> holidayList = new ArrayList<Calendar>();
	
	@Before
	public void setUp() {
		ed = new EarningsDate();

		jan31_2014.set(2014, 0, 31);
		may1_2014.set(2014, 4, 1);
		jul29_2014.set(2014, 6, 29);
		jul30_2014.set(2014, 6, 30);
		jul31_2014.set(2014, 6, 31);
		aug1_2014.set(2014, 7, 1);
		aug4_2014.set(2014, 7, 4);
		oct29_2014.set(2014, 9, 29);
		dec5_2014.set(2014, 11, 5);
		jan2_2015.set(2015, 0, 2);
		jan5_2015.set(2015, 0, 5);
		jan6_2015.set(2015, 0, 6);
		jan7_2015.set(2015, 0, 7);
		jan29_2015.set(2015, 0, 29);
	}
	
	
	@Test
	public void testMonthToInt() {
		Assert.assertEquals(new Integer(0), ed.monthToInt("Jan"));
		Assert.assertEquals(new Integer(3), ed.monthToInt("aPr"));
		Assert.assertEquals(new Integer(11), ed.monthToInt("deC"));
	}

	@Test
	public void testExpectedNextEarningsDateBeforeSep() {
		Calendar expected = ed.getCalculatedNextDate(jul29_2014, holidayList);
		Assert.assertTrue (DateUtil.isSameDay(oct29_2014, expected));

		// And make sure input date does not change
		Calendar expect_jul29_2014 = Calendar.getInstance();
		expect_jul29_2014.set(2014, 6, 29);
		Assert.assertTrue(DateUtil.isSameDay(expect_jul29_2014, jul29_2014));
	}

	@Test
	public void testExpectedNextEarningsDateAfterSep() {
		Calendar expected = ed.getCalculatedNextDate(oct29_2014, holidayList);
		Assert.assertTrue(DateUtil.isSameDay(jan29_2015, expected));
	}

	@Test
	public void testExpectedNextEarningsDateDay31() {
		Calendar expected = ed.getCalculatedNextDate(jan31_2014, holidayList);
		Assert.assertTrue(DateUtil.isSameDay(may1_2014, expected));
	}
	
	@Test
	public void testExpectedNextEarningsDateWeekend() {
		Calendar may16 = Calendar.getInstance();
		may16.set(2014, 4, 16);
		Calendar may17 = Calendar.getInstance();
		may17.set(2014, 4, 17);
		Calendar may18 = Calendar.getInstance();
		may18.set(2014, 4, 18);
		Calendar aug15Friday = Calendar.getInstance();
		aug15Friday.set(2014, 7, 15);
		Calendar aug18HolidayMonday = Calendar.getInstance();
		aug18HolidayMonday.set(2014, 7, 18);

		holidayList.add(aug18HolidayMonday);
		
		// Saturday
		Calendar actual = ed.getCalculatedNextDate(may16, holidayList);
		Assert.assertTrue(DateUtil.isSameDay(aug15Friday, actual));
		
		//Sunday
		actual = ed.getCalculatedNextDate(may17, holidayList);
		Assert.assertTrue(DateUtil.isSameDay(aug15Friday, actual));

		//Monday - holiday
		//Sunday
		actual = ed.getCalculatedNextDate(may18, holidayList);
		Assert.assertTrue(DateUtil.isSameDay(aug15Friday, actual));
		
		// Wednesday - holiday
		Calendar aug20HolidayWednesday = Calendar.getInstance();
		aug20HolidayWednesday.set(2014, 7, 20);
		holidayList.add(aug20HolidayWednesday);

		Calendar may20 = Calendar.getInstance();
		may20.set(2014, 4, 20);
		
		Calendar aug19Tuesday = Calendar.getInstance();
		aug19Tuesday.set(2014, 7, 19);

		actual = ed.getCalculatedNextDate(may20, holidayList);
		Assert.assertTrue(DateUtil.isSameDay(aug19Tuesday, actual));
	}
	
	
	
	
	@Test
	public void testGetYear() {
		// Same year
		Assert.assertEquals(new Integer(2014), ed.getYear(11, dec5_2014));

		// Future: "Now" is December 2014, month is 0 (January)
		Assert.assertEquals(new Integer(2015), ed.getYear(0, dec5_2014));
		
		// Past: "Now" is January 2015, month is 10 (November)
		Assert.assertEquals(new Integer(2014), ed.getYear(10, jan7_2015));
		
		// Null check - null defaults to "now"
		Assert.assertNotNull(ed.getYear(10, null));
	}
	
	@Test
	public void testConvertToCal() {
		String dateString = "Jun 20 BMO";
		Calendar expected = Calendar.getInstance();
		expected.set(2014, 5, 20);

		Calendar ret = ed.convertToCal(dateString, aug4_2014);
		Assert.assertTrue(DateUtil.isSameDay(expected, ret));
	}

	@Test
	public void testConvertToCalFuture() {
		String dateString = "Jan 20 BMO";
		Calendar expected = Calendar.getInstance();
		expected.set(2015, 0, 20);

		Calendar ret = ed.convertToCal(dateString, dec5_2014);
		Assert.assertTrue(DateUtil.isSameDay(expected, ret));
	}

	@Test
	public void testConvertToCalBadDateString() {
		String dateString = "Jun20";
		Calendar ret = ed.convertToCal(dateString, aug4_2014);

		Assert.assertNull(ret);
	}

	@Test
	public void testConvertToCalBadMonth() {
		String dateString = "Jux 20";
		Calendar ret = ed.convertToCal(dateString, aug4_2014);

		Assert.assertNull(ret);
	}
	
	@Test
	public void testConvertToCalBadDay() {
		String dateString = "Jun 32";
		Calendar ret = ed.convertToCal(dateString, aug4_2014);

		Assert.assertNull(ret);
	}

	
	@Test
	public void testInHolidayList() {
		holidayList.add(jan6_2015);
		holidayList.add(aug1_2014);
		holidayList.add(aug4_2014);
		Assert.assertTrue(ed.inHolidayList(aug1_2014, holidayList));
	}

	@Test
	public void testInHolidayListNotInList() {
		holidayList.add(jan6_2015);
		holidayList.add(aug4_2014);
		Assert.assertFalse(ed.inHolidayList(aug1_2014, holidayList));
	}

	@Test
	public void testInHolidayListNullList() {
		Assert.assertFalse(ed.inHolidayList(aug1_2014, null));
	}
	
	
	@Test
	public void testGetBusinessDayBeforeWeekday() {
		Calendar ret = ed.getBusinessDayBefore(jan7_2015, null);
		Assert.assertTrue(DateUtil.isSameDay(ret, jan6_2015));
	}

	@Test
	public void testGetBusinessDayBeforeWeekend() {
		Calendar ret = ed.getBusinessDayBefore(aug4_2014, null);
		Assert.assertTrue(DateUtil.isSameDay(ret, aug1_2014));
	}

	@Test
	public void testGetBusinessDayBeforeWeekendTodayisSunday() {
		Calendar sundayAug3 = Calendar.getInstance();
		sundayAug3.set(2014, 7, 3);
		Calendar ret = ed.getBusinessDayBefore(sundayAug3, null);
		Assert.assertTrue(DateUtil.isSameDay(ret, aug1_2014));
	}
	
	
	@Test
	public void testGetBusinessDayBeforeHoliday() {
		holidayList.add(jan6_2015);
		holidayList.add(aug1_2014);
		holidayList.add(aug4_2014);

		Calendar ret = ed.getBusinessDayBefore(jan7_2015, holidayList);
		Assert.assertTrue(DateUtil.isSameDay(ret, jan5_2015));
	}

	@Test
	public void testGetBusinessDayBeforeFridayHoliday() {
		holidayList.add(jan6_2015);
		holidayList.add(aug1_2014);

		Calendar ret = ed.getBusinessDayBefore(aug4_2014, holidayList);
		Assert.assertTrue(DateUtil.isSameDay(ret, jul31_2014));
	}
	
	@Test
	public void testGetBusinessDayBeforeMondayHoliday() {
		holidayList.add(jan5_2015);
		holidayList.add(aug1_2014);

		Calendar ret = ed.getBusinessDayBefore(jan6_2015, holidayList);
		Assert.assertTrue(DateUtil.isSameDay(ret, jan2_2015));
	}

	@Test
	public void testConvertEarningsStringToCalendarAMC() {
		String earningsStr = "fooobaaaray=[300]\">Earnings</td><td width=\"8%\" class=\"snapshot-td2\" align=\"left\"><b>Aug 1 AMC</b></td>";
		Calendar ret = ed.convertEarningsStringToCalendar(earningsStr, null, this.aug4_2014);
		Assert.assertTrue(DateUtil.isSameDay(ret, aug1_2014));
	}
	
	@Test
	public void testConvertEarningsStringToCalendarBMO() {
		String earningsStr = "fooobaaaray=[300]\">Earnings</td><td width=\"8%\" class=\"snapshot-td2\" align=\"left\"><b>Aug 4 BMO</b></td>";
		Calendar ret = ed.convertEarningsStringToCalendar(earningsStr, null, this.aug4_2014);
		Assert.assertTrue(DateUtil.isSameDay(ret, aug1_2014));
	}
	
	@Test
	public void testConvertEarningsStringToCalendarBMOHoldayList() {
		holidayList.add(aug1_2014);
		String earningsStr = "fooobaaaray=[300]\">Earnings</td><td width=\"8%\" class=\"snapshot-td2\" align=\"left\"><b>Aug 4 BMO</b></td>";
		Calendar ret = ed.convertEarningsStringToCalendar(earningsStr, holidayList, this.aug4_2014);
		Assert.assertTrue(DateUtil.isSameDay(ret, jul31_2014));
	}

	@Test
	public void testConvertEarningsStringToCalendarNoAMCorBMO() {
		String earningsStr = "fooobaaaray=[300]\">Earnings</td><td width=\"8%\" class=\"snapshot-td2\" align=\"left\"><b>Aug 1 AMXX</b></td>";
		Calendar ret = ed.convertEarningsStringToCalendar(earningsStr, null, this.aug4_2014);
		Assert.assertNull(ret);
	}

	@Test
	public void testGetEarningsStringFromFinViz() throws ThetaExceptionExc {
		String ret = ed.getEarningsStringFromFinViz("SYMB", holidayList, "tst/main/java/com/theta/externaldata/test.infile.amc.txt");
		Assert.assertEquals("<td width=\"7%\" class=\"snapshot-td2-cp\" align=\"left\" title=\"cssbody=[tooltip_short_bdy] cssheader=[tooltip_short_hdr] body=[Earnings date<br><br>BMO = Before Market Open<br>AMC = After Market Close] offsetx=[10] offsety=[20] delay=[300]\">Earnings</td><td width=\"8%\" class=\"snapshot-td2\" align=\"left\"><b>Jul 31 AMC</b></td>", ret);
	}
	
	@Test (expected = ThetaExceptionExc.class)
	public void testGetEarningsStringFromFinVizThrows() throws ThetaExceptionExc {
		ed.getEarningsStringFromFinViz("SYMB", holidayList, "EXC");
	}
	
	@Test
	public void testGetEarningsDateFromFinVizAMC() throws ThetaExceptionExc {
		Calendar ret = ed.getEarningsDateForSymbol("SYMB", null, "tst/main/java/com/theta/externaldata/test.infile.amc.txt", this.aug4_2014);
		Assert.assertTrue(DateUtil.isSameDay(jul31_2014, ret));
	}

	@Test
	public void testGetEarningsDateFromFinVizBMO() throws ThetaExceptionExc {
		Calendar ret = ed.getEarningsDateForSymbol("SYMB", null, "tst/main/java/com/theta/externaldata/test.infile.bmo.txt", this.aug4_2014);
		Assert.assertTrue(DateUtil.isSameDay(jul30_2014, ret));
	}
	
	@Test
	public void testGetEarningsDateFromFinVizBMOHolidayList() throws ThetaExceptionExc {
		holidayList.add(jul30_2014);
		Calendar ret = ed.getEarningsDateForSymbol("SYMB", holidayList, "tst/main/java/com/theta/externaldata/test.infile.bmo.txt", this.aug4_2014);
		Assert.assertTrue(DateUtil.isSameDay(jul29_2014, ret));
	}
	
	@Test
	public void testIsMoreThanThirtyDaysAgo() {
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DATE, 1);
		Calendar twentyNineDaysAgo = Calendar.getInstance();
		twentyNineDaysAgo.add(Calendar.DATE, -29);
		Calendar thirtyOneDaysAgo = Calendar.getInstance();
		thirtyOneDaysAgo.add(Calendar.DATE, -31);

		Assert.assertTrue(ed.isMoreThan30DaysAgo(null));
		Assert.assertFalse(ed.isMoreThan30DaysAgo(tomorrow));
		Assert.assertFalse(ed.isMoreThan30DaysAgo(twentyNineDaysAgo));
		Assert.assertTrue(ed.isMoreThan30DaysAgo(thirtyOneDaysAgo));
	}
	
}

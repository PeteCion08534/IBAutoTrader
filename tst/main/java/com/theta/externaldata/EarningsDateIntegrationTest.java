package com.theta.externaldata;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.theta.process.ThetaExceptionExc;

public class EarningsDateIntegrationTest {

	EarningsDate ed = null;
	Calendar jul29_2014 = Calendar.getInstance();
	List<Calendar> holidayList = new ArrayList<Calendar>();
	
	@Ignore
	@Before
	public void setUp() {
		ed = new EarningsDate();
		jul29_2014.set(2014, 6, 29);
	}

	@Ignore
	@Test
	public void testEarningsDate() throws ThetaExceptionExc {
		System.out.println("Date for MSFT: " + ed.getEarningsDateForSymbol("MSFT", holidayList, "URL", null).getTime());
	}
	
}

package com.theta.process;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.theta.dao.SecurityPriceDAO;
import com.theta.domain.SecurityPrice;

public class SlopeTest2 {

	//@Mock
	SecurityPriceDAO securityPriceDAO;
	
	Slope slope;
	
	@Before
	public void setUp() {
		
		securityPriceDAO = mock(SecurityPriceDAO.class);
		Calendar crDate1 = Calendar.getInstance();
		crDate1.set(2014, 9, 1, 10, 0, 0);
		Calendar crDate2 = Calendar.getInstance();
		crDate2.set(2014, 9, 2, 10, 0, 0);
		
		SecurityPrice sp1 = new SecurityPrice();
		sp1.setPrice(1200);
		sp1.setMovAvg1(1050);
		sp1.setMovAvg2(1250);
		sp1.setMovAvgRange(100);
		sp1.setCreatedDate(crDate1);
		
		SecurityPrice sp2 = new SecurityPrice();
		sp2.setPrice(1300);
		sp2.setMovAvg1(1250);
		sp2.setMovAvg2(1350);
		sp2.setMovAvgRange(120);
		sp2.setCreatedDate(crDate2);
		
		when(securityPriceDAO.findEarliestRecordSince(any(Calendar.class), 
				any(String.class))).thenReturn(sp1);
		when(securityPriceDAO.findLatestRecordBefore(any(Calendar.class), 
				any(String.class))).thenReturn(sp2);
		when(securityPriceDAO.findNumRecordsOnDate(any(Calendar.class), 
				any(String.class))).thenReturn(10);
		
		ThetaMutex dbAccessMutex = new ThetaMutex();
		slope = new Slope(securityPriceDAO, dbAccessMutex);
		
	}
	
	@Test
	public void testGetMASlopeInAdrTbpPerMinute() {
		Calendar now = Calendar.getInstance();
		
		Integer ret1 = slope.getMASlopeInAdrTbpPerMinute(now, 1, "TEST", 100);
		Integer ret2 = slope.getMASlopeInAdrTbpPerMinute(now, 2, "TEST", 100);
		Assert.assertEquals(new Integer(512), ret1);
		Assert.assertEquals(new Integer(256), ret2);
	}
	
}

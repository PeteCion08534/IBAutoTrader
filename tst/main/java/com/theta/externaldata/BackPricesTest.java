package com.theta.externaldata;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.theta.dao.SecurityPriceDAO;
import com.theta.externaldata.BackPrices.Price;
import com.theta.process.ThetaExceptionExc;
import com.theta.process.ThetaMutex;

public class BackPricesTest {

	BackPrices bp = null;
	String expectedRequestStr = "http://real-chart.finance.yahoo.com/table.csv?s=TEST&d=8&e=23&f=2014&g=d&a=5&b=25&c=2014&ignore=.csv";
	
	@Before
	public void setUp(){
		bp = new BackPrices();
	}
	
	@Test
	public void testFillRequestStr() {
		Calendar testDate = Calendar.getInstance();
		testDate.set(2014, 8, 23);
		String resp = bp.fillRequestStr("TEST", 90, testDate);
		Assert.assertEquals(expectedRequestStr, resp);
	}

	@Test
	public void testGetAndParsePrices() throws ThetaExceptionExc {
		List<Price> prices = bp.getAndParsePrices(expectedRequestStr, "tst/main/java/com/theta/externaldata/test.backprices.TEST.txt");
		Assert.assertEquals(9, prices.size());
		Calendar aug_22_2014 = Calendar.getInstance();
		aug_22_2014.set(2014, 7, 22);
		for (Price pr : prices) {
			if (pr.theDate.equals(aug_22_2014)) {
				String expectStr = "Price [theDate=Fri Aug 22 00:00:00 EDT 2014, theOpen=33299, theHigh=33408, theLow=33080, theClose=33159, theVolume=2211200, theAdjClose=33159]";
				Assert.assertEquals(expectStr, pr.toString());
			}
		}
	}
	
	@Test (expected = NumberFormatException.class)
	public void testGetAndParsePricesBadPrice() throws ThetaExceptionExc {
		List<Price> prices = bp.getAndParsePrices(expectedRequestStr, "tst/main/java/com/theta/externaldata/test.backprices.TEST.BAD.txt");
	}
		
	@Test
	public void testFillListFromDB() throws ThetaExceptionExc {
		SecurityPriceDAO spDAO = Mockito.mock(SecurityPriceDAO.class);
		List<Price> prices = bp.getAndParsePrices(expectedRequestStr, "tst/main/java/com/theta/externaldata/test.backprices.TEST.txt");
		ThetaMutex dbAccessMutex = new ThetaMutex();
		bp.fillDBFromList(spDAO, prices, "TEST", dbAccessMutex);
	}
	
	
	@Test
	public void testStringWithDecToInt() {
		Integer ret = BackPrices.dollarStringToIntCents("345.67");
		Assert.assertEquals(new Integer(34567), ret);

		ret = BackPrices.dollarStringToIntCents("345.678");
		Assert.assertEquals(new Integer(34567), ret);

		ret = BackPrices.dollarStringToIntCents("345.6");
		Assert.assertEquals(new Integer(34560), ret);

		ret = BackPrices.dollarStringToIntCents("340");
		Assert.assertEquals(new Integer(34000), ret);
	}
	
}

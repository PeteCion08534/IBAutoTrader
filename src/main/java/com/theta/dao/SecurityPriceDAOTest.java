package com.theta.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.theta.client.ThetaClient3;
import com.theta.domain.SecurityPrice;
import com.theta.enums.MinOrMaxCode;
import com.theta.process.ThetaExceptionExc;
import com.theta.process.ThetaUtil;

/**
 * Class used to test the basic Data Store Functionality
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners( {
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = {
		"file:./src/main/resources/theta11-generated-security-context.xml",
		"file:./src/main/resources/theta11-security-context.xml",
		"file:./src/main/resources/theta11-generated-service-context.xml",
		"file:./src/main/resources/theta11-service-context.xml",
		"file:./src/main/resources/theta11-generated-dao-context.xml",
		"file:./src/main/resources/theta11-dao-context.xml",
		"file:./src/main/resources/theta11-generated-web-context.xml",
		"file:./src/main/resources/theta11-web-context.xml" })
@Transactional
public class SecurityPriceDAOTest {
	/**
	 * The DAO being tested, injected by Spring
	 *
	 */
	private SecurityPriceDAO dataStore;

	/**
	 * Instantiates a new SecurityPriceDAOTest.
	 *
	 */
	public SecurityPriceDAOTest() {
	}

	
	@Rollback(false)
	@Test
	public void testFindMovingAverage() {
		//SecurityPrice instance = new SecurityPrice();
		
		System.out.println("************ START ********** ");
		Calendar fromDate = Calendar.getInstance();
		//fromDate.set(Calendar.MONTH, 3);
		fromDate.add(Calendar.DATE, -26);
		Calendar toDate = Calendar.getInstance();
		Integer movingAverage = 0;
		try {
		    movingAverage = dataStore.findMovingAverage(fromDate, toDate, "UTHR");
		} catch (Exception ex) {
			System.out.println("Exception!");
			ex.printStackTrace();
		}
		System.out.println("************ Here is moving Average: " + movingAverage + " *******");
	}

	
	@Rollback(false)
	@Test
	public void testFindMovingAverageDailyRange() {
		//SecurityPrice instance = new SecurityPrice();
		
		System.out.println("************ START ********** ");
		Calendar fromDate = Calendar.getInstance();
		//fromDate.set(Calendar.MONTH, 3);
		fromDate.add(Calendar.DATE, -26);
		Calendar toDate = Calendar.getInstance();
		Integer movingAverage = 0;
		try {
		    movingAverage = dataStore.findMovingAverageDailyRange(fromDate, toDate, "VIPS");
		} catch (Exception ex) {
			System.out.println("Exception!");
			ex.printStackTrace();
		}
		System.out.println("************ Here is moving Average: " + movingAverage + " *******");
	}

	
	@Rollback(false)
	@Test
	public void testFindMinOrMaxPrices() {
		//SecurityPrice instance = new SecurityPrice();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR,-14);
		Integer minVal = dataStore.findMinOrMaxPriceSinceDateTime(cal, "HPQ", MinOrMaxCode.MIN);
		System.out.println("Here is minVal: " + minVal);

		Integer maxVal = dataStore.findMinOrMaxPriceSinceDateTime(cal, "HPQ", MinOrMaxCode.MAX);
		System.out.println("Here is maxVal: " + maxVal);
	}

	@Rollback(false)
	@Test
	public void testFindLastPrice() {
		//SecurityPrice instance = new SecurityPrice();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR,-14);
		Integer lastVal = dataStore.findMinOrMaxPriceSinceDateTime(cal, "HPQ", MinOrMaxCode.LAST);
		System.out.println("Here is lastVal: " + lastVal);

	}
	
	
	@Rollback(false)
	@Test
	public void testFindDateOfMinOrMaxPrice() throws Exception {
		//SecurityPrice instance = new SecurityPrice();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR,-71);
				
		Date minDt = dataStore.findDateOfMinOrMaxPrice(cal, "SPY", MinOrMaxCode.MIN);
		System.out.println("Here is minDate: " + minDt);

		Date maxDt = dataStore.findDateOfMinOrMaxPrice(cal, "SPY", MinOrMaxCode.MAX);
		System.out.println("Here is maxDate: " + maxDt);

		Calendar min = Calendar.getInstance();
		min.setTime(minDt);
		System.out.println("here is min(Calendar): " + min.getTime());
		
		Calendar today = Calendar.getInstance();
		
	}

	@Rollback(false)
	@Test
	public void testGetByTickerAndSourceSinceTime() throws Exception {
		
		Calendar now = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR,-2);
				
		Set<SecurityPrice> pricesSince = dataStore.findSecurityPricesByTickerAndSourceSinceTime("BABY", "IB", cal, now);
		
		for (SecurityPrice price : pricesSince) {
			System.out.println("Price: " + price.getCreatedDate().getTime() + " : " + price.getPrice() + " : " + price.getSource());
		}
		
	}

	@Rollback(false)
	@Test
	public void testGetByTickerSinceTime() throws Exception {
		
		Calendar now = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR,-2);
				
		Set<SecurityPrice> pricesSince = dataStore.findSecurityPricesByTickerSinceTime("BABY", cal, now);
		
		for (SecurityPrice price : pricesSince) {
			System.out.println("Price: " + price.getCreatedDate().getTime() + " : " + price.getPrice() + " : " + price.getSource());
		}
		
	}
	
	
	@Test
	public void testAreAllPricesIdentical(){
		Set<SecurityPrice> spSet = new HashSet<SecurityPrice>();
		// 0 size
		Assert.assertTrue(areAllPricesIdentical(spSet));
				
		// True case
		SecurityPrice sp = new SecurityPrice();
		sp.setPrice(1234);
		sp.setSecurityPriceId(1);
		spSet.add(sp);

		sp.setSecurityPriceId(2);
		spSet.add(sp);

		sp.setSecurityPriceId(3);
		spSet.add(sp);
		System.out.println("Size of spSet: " + spSet.size());
		Assert.assertTrue(areAllPricesIdentical(spSet));
		
		// False case - many
		SecurityPrice sp2 = new SecurityPrice();
		sp2.setPrice(1235);
		sp2.setSecurityPriceId(4);
		spSet.add(sp2);

		Assert.assertFalse(areAllPricesIdentical(spSet));

		// False case - two
		SecurityPrice sp3 = new SecurityPrice();
		sp3.setPrice(1235);
		sp3.setSecurityPriceId(5);
		SecurityPrice sp4 = new SecurityPrice();
		sp4.setPrice(1236);
		sp4.setSecurityPriceId(6);
		spSet = new HashSet<SecurityPrice>();
		spSet.add(sp3);
		spSet.add(sp4);
		Assert.assertFalse(areAllPricesIdentical(spSet));

	}
	
	
	private boolean areAllPricesIdentical(Set<SecurityPrice> prices){
		
		System.out.println("Start of procedure. Size of prices: " + prices.size());
		Integer STARTER = -1;
		Integer priceOne = STARTER;
		Integer priceNext;
		
		for (SecurityPrice sp : prices) {
			priceNext = sp.getPrice();
			System.out.println("priceOne, priceNext: " + priceOne + ", "  + priceNext);
			
			if ( (priceNext != priceOne) && (priceOne != STARTER) ) {
				System.out.println("RETURNING FALSE!");
				return false;
			}
			priceOne = priceNext;
		}
		
		System.out.println("RETURNING TRUE!");
		return true;
	}
	
	@Mock
	ThetaClient3 client;
	
	@Test
	public void testYahooCutover() {
		String theSymbol = "ALK";
		ThetaUtil thetaUtil = new ThetaUtil();
		Calendar toTime = Calendar.getInstance();
		toTime.set(2013,11,24,13,51);
		System.out.println("Here is toTime: " + toTime.getTime());

		Calendar fromTime = Calendar.getInstance();
		fromTime.set(2013,11,24,13,51);

		//Calendar twentyFiveMinutesAgo = Calendar.getInstance();
		System.out.println("Here is fromTime (START): " + fromTime.getTime());
		fromTime.add(Calendar.MINUTE, -25);
		System.out.println("Here is twentyFiveMinutesAgo (AFTER): " + fromTime.getTime());
		Set<SecurityPrice> secPrices = dataStore.findSecurityPricesByTickerAndSourceSinceTime(theSymbol, "IB", fromTime, toTime);
		System.out.println("Here is secPrices.size(): " + secPrices.size());
		for (SecurityPrice sp : secPrices) {
			System.out.println("secPrice: " + sp);
		}
		
		
		if ((secPrices.size() > 2) && thetaUtil.areAllPricesIdentical(secPrices)) {
			try {
				Integer yahooPrice = thetaUtil.getCurrentSecurityPriceFromYahoo(theSymbol, client);
				System.out.println("Here is yahoo price: " + yahooPrice);
			} catch (ThetaExceptionExc ex3){
				System.out.println("Exception in getting Price from Yahoo: ");
				ex3.printStackTrace();
			}
		}
	}

	
	/**
	 * Method to test SecurityPrice domain object.
	 *
	 */
	@Rollback(false)
	@Test
	public void SecurityPrice() {
		SecurityPrice instance = new SecurityPrice();

		// Test create				
		// TODO: Populate instance for create.  The store will fail if the primary key fields are blank.				
		instance.setPrice(234);
		instance.setTicker("SPY");
		instance.setSecurityPriceId(-1);
		
		// store the object
		dataStore.store(instance);

		// Test update
		// TODO: Modify non-key domain object values for update

		// update the object
		dataStore.store(instance);

		// Test delete
		dataStore.remove(instance);

	}

	/**
	 * Method to allow Spring to inject the DAO that will be tested
	 *
	 */
	@Autowired
	public void setDataStore(SecurityPriceDAO dataStore) {
		this.dataStore = dataStore;
	}


	/**
	 * Autowired to set the Spring application context.
	 *
	 */
	//@Autowired
	//public void setContext(ApplicationContext context) {
	//	this.context = context;
	//	((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("session", new SessionScope());
	//	((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("request", new RequestScope());
	//}

	/**
	 * Sets Up the Request context
	 *
	 */
	private void setupRequestContext() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attributes);
	}

	
}

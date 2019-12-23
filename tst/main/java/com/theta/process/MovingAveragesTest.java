package com.theta.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.theta.dao.SecurityPriceDAO;
import com.theta.enums.MovingAverageType;
import com.theta.process.MovingAverages.AllMovingAverages;

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
public class MovingAveragesTest {

	@Autowired
	private SecurityPriceDAO securityPriceDAO;
	
	@Mock
	SecurityPriceDAO securityPriceDAOMock;
	
	Calendar now = null;
	Calendar tenSecondsAgo = null;
	Calendar halfAnHourAgo = null;
	Calendar twoHoursAgo = null;
	Calendar testUpdatedDate = null;
	
	MovingAverages ma = null;
	
	@Before
	public void setUp() {

		
		securityPriceDAOMock = Mockito.mock(SecurityPriceDAO.class);

		/*
		if (securityPriceDAO == null) {
			System.out.println("************** SecurityPriceDAO is null! ************");
			System.exit(0);
		}
		*/
		//System.out.println("IN before!!");

		now = Calendar.getInstance();
		
		tenSecondsAgo = Calendar.getInstance();
		tenSecondsAgo.set(Calendar.SECOND, now.get(Calendar.SECOND) - 10);
		
		halfAnHourAgo = Calendar.getInstance();
		halfAnHourAgo.set(Calendar.MINUTE, now.get(Calendar.MINUTE) - 30);
		
		twoHoursAgo = Calendar.getInstance();
		twoHoursAgo.set(Calendar.HOUR, now.get(Calendar.HOUR) - 2);
		
		//when(securityPriceDAOMock.findMovingAverage(any(Calendar.class), any(Calendar.class), any(String.class))).thenReturn(15500);
		//when(securityPriceDAOMock.findMovingAverage(any(Calendar.class), any(Calendar.class), eq("SPY"))).thenReturn(15500);
		Mockito.when(securityPriceDAOMock.findMovingAverage(Matchers.any(Calendar.class), Matchers.any(Calendar.class), Matchers.eq("SPY"))).thenReturn(15500);
		Mockito.when(securityPriceDAOMock.findMovingAverage(Matchers.any(Calendar.class), Matchers.any(Calendar.class), Matchers.eq("ORCL"))).thenReturn(3100);
		Mockito.when(securityPriceDAOMock.findMovingAverage(Matchers.any(Calendar.class), Matchers.any(Calendar.class), Matchers.eq("MSFT"))).thenReturn(3340);

		//System.out.println("now, tenSecondsAgo, halfAnHourAgo, twoHoursAgo: " + now.getTime() + ", " + tenSecondsAgo.getTime() + ", " + halfAnHourAgo.getTime() + ", " + twoHoursAgo.getTime());
		//ma = new MovingAverages(securityPriceDAOMock);
		ThetaMutex dbAccessMutex = new ThetaMutex();
		ma = new MovingAverages(securityPriceDAOMock, dbAccessMutex);

		Calendar twentyDaysAgo = Calendar.getInstance();
		twentyDaysAgo.add(Calendar.DAY_OF_YEAR, -20);
		twentyDaysAgo.set(Calendar.HOUR_OF_DAY,0);
		twentyDaysAgo.set(Calendar.MINUTE,0);
		twentyDaysAgo.set(Calendar.SECOND,0);

		//System.out.println("****** IN TEST - Here is twentyDaysAgo: " + twentyDaysAgo.getTime() + " ***********");
		
		Calendar fiftyDaysAgo = Calendar.getInstance();
		twentyDaysAgo.add(Calendar.DAY_OF_YEAR, -50);
		fiftyDaysAgo.set(Calendar.HOUR_OF_DAY,0);
		fiftyDaysAgo.set(Calendar.MINUTE,0);
		fiftyDaysAgo.set(Calendar.SECOND,0);
		
		ma.getMovingAverage("SPY", 20, false, MovingAverageType.DAILYPRICE);
		ma.getMovingAverage("SPY", 50, false, MovingAverageType.DAILYPRICE);
		ma.getMovingAverage("ORCL", 20, false, MovingAverageType.DAILYPRICE);
		ma.getMovingAverage("MSFT", 20, false, MovingAverageType.DAILYPRICE);
		System.out.println("Here is ma: \n" + ma.toString());
	}
	
	
	@Test
	public void testInitialDateIsCurrent(){
				
		// 1. Initial date - less than 10 seconds ago
		testUpdatedDate = ma.getUpdatedDate("SPY", 20);
		assertTrue(testUpdatedDate.compareTo(tenSecondsAgo) > 0 );
		assertEquals(ma.getTicker("SPY", 20), "SPY");
		System.out.println("In testIntitialDateIsCurrent: \n" + ma.toString());
	}
	
	@Test
	public void testUpdatedDateHalfHourAgo(){
		// 2. updated date is half an hour ago - updated date should be the same as fed in
		ma.setUpdatedDate("SPY", 20, halfAnHourAgo);
		Integer testMa = ma.getMovingAverage("SPY", 20, false, MovingAverageType.DAILYPRICE);
		
		System.out.println("Here is testMa: " + testMa);
		assertTrue((testMa != null) && (testMa > 0));

		testUpdatedDate = ma.getUpdatedDate("SPY", 20);
		assertEquals(testUpdatedDate, halfAnHourAgo);
		System.out.println("In testUpdatedDateHalfHourAgo: \n" + ma.toString());
	}

	@Test
	public void testUpdatedDateHalfHourAgoWithForceLookup(){
		// 3. updated date is half an hour ago - but forceLookup is true - updated date should be within a few seconds 
		ma.setUpdatedDate("SPY", 20, halfAnHourAgo);
		Integer testMa = ma.getMovingAverage("SPY", 20, true, MovingAverageType.DAILYPRICE);
		assertTrue((testMa != null) && (testMa > 0));

		testUpdatedDate = ma.getUpdatedDate("SPY", 20);
		assertTrue(testUpdatedDate.compareTo(tenSecondsAgo) > 0 );
		System.out.println("In testUpdatedDateHalfHourAgoWithForceLookup: \n" + ma.toString());
	}	

	
	@Test
	public void testUpdatedTwoHoursAgo(){
		// 4 . updated date is two hours ago - updated date should be within a few seconds
		ma.setUpdatedDate("SPY", 20, twoHoursAgo);
		System.out.println("In testUpdatedTwoHoursAgo - before getting moving average: \n" + ma.toString());
		Integer testMa = ma.getMovingAverage("SPY", 20, false, MovingAverageType.DAILYPRICE);
		assertTrue((testMa != null) && (testMa > 0));
		
		testUpdatedDate = ma.getUpdatedDate("SPY", 20);
		assertTrue(testUpdatedDate.compareTo(tenSecondsAgo) > 0 );
		System.out.println("In testUpdatedTwoHoursAgo: \n" + ma.toString());
	}	


	@Test
	public void testGetAllMovingAverages() {
		System.out.println("*****Here is MovingAverage *** \n" + ma.toString());
		ma.setNewMovingAverage("SPY", 20, 15300);
		ma.setNewMovingAverage("SPY", 50, 15500);
		AllMovingAverages ama = ma.getAllMovingAverages("SPY", 20, 50, 20, 15200, 50, 25); 
		System.out.println(ama.toString());
		assertEquals((Integer)15502, (Integer)ama.hiAvgPlus );
		assertEquals((Integer)15500, (Integer)ama.hiAvgMinus);
		assertEquals((Integer)15500, (Integer)ama.loAvgPlus );
		assertEquals((Integer)15498, (Integer)ama.loAvgMinus);
	}

	@Test
	public void testGetAllMovingAveragesClose() {
		ma.setNewMovingAverage("SPY", 20, 15550);
		ma.setNewMovingAverage("SPY", 50, 15500);
		AllMovingAverages ama = ma.getAllMovingAverages("SPY", 20, 50, 20, 15200, 100, 25); 
		System.out.println(ama.toString());
		assertEquals((Integer)15502, (Integer)ama.hiAvgPlus);
		assertEquals((Integer)15500, (Integer)ama.hiAvgMinus);
		assertEquals((Integer)15500, (Integer)ama.loAvgPlus);
		assertEquals((Integer)15498, (Integer)ama.loAvgMinus);
	}

	@Ignore
	@Test
	public void testGetAllMovingAveragesOutlyer() {
		System.out.println("*****Here is MovingAverage *** \n" + ma.toString());
		ma.setNewMovingAverage("SPY", 20, 15550);
		ma.setNewMovingAverage("SPY", 50, 15500);
		AllMovingAverages ama = ma.getAllMovingAverages("SPY", 20, 50, 20, 12000, 100, 25); 
		
		System.out.println("Here is ama: " + ama);
		assertTrue(ama == null);
		if (ama == null) {
			System.out.println("ama is null.  OK");
		} 
	}
	
}




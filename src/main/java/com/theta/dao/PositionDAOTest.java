package com.theta.dao;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.theta.client.DateUtil;
import com.theta.domain.Position;
import com.theta.domain.ProfitLoss;
import com.theta.enums.InstrumentCode;
import com.theta.process.ThetaConstants;

/**
 * Class used to test the basic Data Store Functionality
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners( {
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@Transactional
@ContextConfiguration(locations = {
		"file:./src/main/resources/theta11-generated-security-context.xml",
		"file:./src/main/resources/theta11-security-context.xml",
		"file:./src/main/resources/theta11-generated-service-context.xml",
		"file:./src/main/resources/theta11-service-context.xml",
		"file:./src/main/resources/theta11-generated-dao-context.xml",
		"file:./src/main/resources/theta11-dao-context.xml",
		"file:./src/main/resources/theta11-generated-web-context.xml",
		"file:./src/main/resources/theta11-web-context.xml" })
public class PositionDAOTest {
	/**
	 * The DAO being tested, injected by Spring
	 *
	 */
	private PositionDAO dataStore;

	/**
	 * Instantiates a new PositionDAOTest.
	 *
	 */
	public PositionDAOTest() {
	}


	private Position positionFactory() {

		Position posToAdd = new Position();
		posToAdd.setPositionId(ThetaConstants.INIT_ID);
		posToAdd.setInstrument(InstrumentCode.OPT.toString());
		posToAdd.setOptRight(ThetaConstants.CALL);
		posToAdd.setGoalNumSpreads(2);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		// grrrr - Calendar cludge - must add a month (January is 0!!)
		Integer theMonth = cal.get(Calendar.MONTH) + ThetaConstants.ONE_INT;
		Integer theYear = cal.get(Calendar.YEAR);
		Calendar theDayCal = DateUtil.getThirdFridayCal(theMonth, theYear);
		posToAdd.setExpiryDate(theDayCal);
		posToAdd.setExpiryYear(theYear);
		posToAdd.setExpiryMonth(theMonth);
		posToAdd.setExpiryDay(theDayCal.get(Calendar.DAY_OF_MONTH));
		posToAdd.setCanAddToDb(true);
		posToAdd.setNumOpenSpreads(ThetaConstants.ZERO_INT);
		posToAdd.setNumWins(ThetaConstants.ZERO_INT);
		posToAdd.setNumLosses(ThetaConstants.ZERO_INT);
		posToAdd.setProfitLossUnrealized(ThetaConstants.ZERO_INT);
		posToAdd.setProfitLossRealized(ThetaConstants.ZERO_INT);
		posToAdd.setLastExitSecurityPrice(ThetaConstants.ZERO_INT);
		posToAdd.setReentrySecPriceCallAbove(ThetaConstants.ZERO_INT);
		posToAdd.setReentrySecPricePutBelow(ThetaConstants.ZERO_INT);

		posToAdd.setTotalRisked(10000);

		posToAdd.setStrategyAccountId(1); 
		posToAdd.setStrategyName("TEST STRATEGY");
		posToAdd.setSymbol("TEST");
		posToAdd.setCreatedBy("addNewPositions");
		posToAdd.setCreatedDate(Calendar.getInstance());

		posToAdd.setUpdatedBy("addNewPositions");
		posToAdd.setUpdatedDate(Calendar.getInstance());

		return posToAdd;

	}
	
	/**
	 * Method to test Position domain object.
	 *
	 */
	@Rollback(false)
	@Test
	public void testPosition() {
		Position instance = positionFactory();
		
		// Test create				
		// TODO: Populate instance for create.  The store will fail if the primary key fields are blank.				

		System.out.println("top of Position test");
		
		// store the object
		dataStore.store(instance);

		//ProfitLoss pl = dataStore.findStatsForExpiredPositions();
		
		instance.setUpdatedBy("Test222");
		// Test update
		// TODO: Modify non-key domain object values for update

		// update the object
		dataStore.store(instance);

		// Test delete
		dataStore.remove(instance);

	}

	
	/**
	 * Method to test findStatsForExpiredPositions
	 *
	 */
	@Rollback(false)
	@Test
	public void testFindStatsForExpired() {
		//Position instance = positionFactory();

		System.out.println("top of Position - findStatsForExpired");
		
		ProfitLoss pl = dataStore.findStatsForExpiredPositions(1);
		
		System.out.println("Here is pl: " + pl);

		assertTrue(pl != null);
		assertTrue(pl.getNumWins() > 0);
		assertTrue(pl.getNumLosses() > 0);
		assertTrue(pl.getPlRealized() != 0);
		assertTrue(pl.getPlUnrealized() != 0);
	}

	/**
	 * Method to test findAllCurrentPositions
	 *
	 */
	@Rollback(false)
	@Test
	public void testFindAllCurrentPositionsByStratAcct() {
		//Position instance = positionFactory();

		System.out.println("top of Position - findAllCurrentPositionsByStratAcct");
		
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_YEAR, -1);
		Calendar today = Calendar.getInstance();
		Set<Position> allPos = dataStore.findPositionsNonExpiredByStrategyAcctId(today, 2);
		
		assertTrue(allPos != null);

		for (Position pos : allPos){
			System.out.println("POS: " + pos.toString());
		}
		
	}

	
	/**
	 * Method to allow Spring to inject the DAO that will be tested
	 *
	 */
	@Autowired
	public void setDataStore(PositionDAO dataStore) {
		this.dataStore = dataStore;
	}
}

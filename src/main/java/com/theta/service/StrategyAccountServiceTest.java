package com.theta.service;

import com.theta.domain.IbAccount;
import com.theta.domain.Position;
import com.theta.domain.ProfitLoss;
import com.theta.domain.Strategy;
import com.theta.domain.StrategyAccount;

import java.util.Set;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.mock.web.MockHttpServletRequest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.SessionScope;

/**
 * Class to run the service as a JUnit test. Each operation in the service is a separate test.
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
public class StrategyAccountServiceTest {

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
	protected StrategyAccountService service;

	/**
	 * Instantiates a new StrategyAccountServiceTest.
	 *
	 */
	public StrategyAccountServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Strategy entity
	 * 
	 */
	@Test
	public void deleteStrategyAccountStrategy() {
		// TODO: JUnit - Populate test inputs for operation: deleteStrategyAccountStrategy 
		Integer strategyaccount_strategyAccountId = null;
		Integer strategy_strategyId = null;
		StrategyAccount response = null;
		response = service.deleteStrategyAccountStrategy(strategyaccount_strategyAccountId, strategy_strategyId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteStrategyAccountStrategy
	}

	/**
	 * Operation Unit Test
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	@Test
	public void deleteStrategyAccount() {
		// TODO: JUnit - Populate test inputs for operation: deleteStrategyAccount 
		StrategyAccount strategyaccount = null;
		service.deleteStrategyAccount(strategyaccount);
	}

	/**
	 * Operation Unit Test
	 * Save an existing StrategyAccount entity
	 * 
	 */
	@Test
	public void saveStrategyAccount() {
		// TODO: JUnit - Populate test inputs for operation: saveStrategyAccount 
		StrategyAccount strategyaccount_1 = null;
		service.saveStrategyAccount(strategyaccount_1);
	}

	/**
	 * Operation Unit Test
	 * Save an existing Strategy entity
	 * 
	 */
	@Test
	public void saveStrategyAccountStrategy() {
		// TODO: JUnit - Populate test inputs for operation: saveStrategyAccountStrategy 
		Integer strategyAccountId = null;
		Strategy strategy = null;
		StrategyAccount response = null;
		response = service.saveStrategyAccountStrategy(strategyAccountId, strategy);
		// TODO: JUnit - Add assertions to test outputs of operation: saveStrategyAccountStrategy
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Position entity
	 * 
	 */
	//@Test
	//public void deleteStrategyAccountPositions() {
	//	// TODO: JUnit - Populate test inputs for operation: deleteStrategyAccountPositions 
	//	Integer position_positionId = null;
	//	Integer strategyaccount_strategyAccountId_1 = null;
	//	StrategyAccount response = null;
	//	response = service.deleteStrategyAccountPositions(position_positionId, strategyaccount_strategyAccountId_1);
	//	// TODO: JUnit - Add assertions to test outputs of operation: deleteStrategyAccountPositions
	//}

	/**
	 * Operation Unit Test
	 * Save an existing Position entity
	 * 
	 */
	@Test
	public void saveStrategyAccountPositions() {
		// TODO: JUnit - Populate test inputs for operation: saveStrategyAccountPositions 
		Integer strategyAccountId_1 = null;
		Position position = null;
		StrategyAccount response = null;
		response = service.saveStrategyAccountPositions(strategyAccountId_1, position);
		// TODO: JUnit - Add assertions to test outputs of operation: saveStrategyAccountPositions
	}

	/**
	 * Operation Unit Test
	 * Save an existing IbAccount entity
	 * 
	 */
	@Test
	public void saveStrategyAccountIbAccount() {
		// TODO: JUnit - Populate test inputs for operation: saveStrategyAccountIbAccount 
		Integer strategyAccountId_2 = null;
		IbAccount ibaccount = null;
		StrategyAccount response = null;
		response = service.saveStrategyAccountIbAccount(strategyAccountId_2, ibaccount);
		// TODO: JUnit - Add assertions to test outputs of operation: saveStrategyAccountIbAccount
	}

	/**
	 * Operation Unit Test
	 * Load an existing StrategyAccount entity
	 * 
	 */
	@Test
	public void loadStrategyAccounts() {
		Set<StrategyAccount> response = null;
		response = service.loadStrategyAccounts();
		// TODO: JUnit - Add assertions to test outputs of operation: loadStrategyAccounts
	}

	/**
	 * Operation Unit Test
	 * Delete an existing IbAccount entity
	 * 
	 */
	@Test
	public void deleteStrategyAccountIbAccount() {
		// TODO: JUnit - Populate test inputs for operation: deleteStrategyAccountIbAccount 
		Integer strategyaccount_strategyAccountId_2 = null;
		Integer ibaccount_ibAccountId = null;
		StrategyAccount response = null;
		response = service.deleteStrategyAccountIbAccount(strategyaccount_strategyAccountId_2, ibaccount_ibAccountId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteStrategyAccountIbAccount
	}

	/**
	 * Autowired to set the Spring application context.
	 *
	 */
	@Autowired
	public void setContext(ApplicationContext context) {
		this.context = context;
		((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("session", new SessionScope());
		((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("request", new RequestScope());
	}

	/**
	 * Sets Up the Request context
	 *
	 */
	private void setupRequestContext() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attributes);
	}

	/**
	 * Operation Unit Test
	 * Save an existing ProfitLoss entity
	 * Delete an existing ProfitLoss entity
	 */
	/*
	 * MOVED FROM StrategyServiceTest
	 */
/*
	@Test
	public void saveStrategyAccountProfitLosses() {
		// TODO: JUnit - Populate test inputs for operation: saveStrategyProfitLosses 
		Integer strategyAccountId = null;
		ProfitLoss profitloss = null;
		StrategyAccount response = null;
		response = service.saveStrategyAccountProfitLosses(strategyAccountId, profitloss);
		// TODO: JUnit - Add assertions to test outputs of operation: saveStrategyProfitLosses
	}
	@Test
	public void deleteStrategyAccountProfitLosses() {
		// TODO: JUnit - Populate test inputs for operation: deleteStrategyProfitLosses 
		Integer profitloss_profitLossId = null;
		Integer strategyAccount_strategyAccountId_1 = null;
		StrategyAccount response = null;
		response = service.deleteStrategyAccountProfitLosses(profitloss_profitLossId, strategyAccount_strategyAccountId_1);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteStrategyProfitLosses
	}
*/
	
}

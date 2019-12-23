package com.theta.service;

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
public class StrategyServiceTest {

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
	protected StrategyService service;

	/**
	 * Instantiates a new StrategyServiceTest.
	 *
	 */
	public StrategyServiceTest() {
		setupRequestContext();
	}


	/**
	 * Operation Unit Test
	 * Save an existing ProfitLoss entity
	 * Delete an existing ProfitLoss entity
	 */
	/*
	 * MOVED TO StrategyAccountServiceTest
	 */
	/*
	@Test
	public void saveStrategyProfitLosses() {
		// TODO: JUnit - Populate test inputs for operation: saveStrategyProfitLosses 
		Integer strategyId = null;
		ProfitLoss profitloss = null;
		Strategy response = null;
		response = service.saveStrategyProfitLosses(strategyId, profitloss);
		// TODO: JUnit - Add assertions to test outputs of operation: saveStrategyProfitLosses
	}
	@Test
	public void deleteStrategyProfitLosses() {
		// TODO: JUnit - Populate test inputs for operation: deleteStrategyProfitLosses 
		Integer profitloss_profitLossId = null;
		Integer strategy_strategyId_1 = null;
		Strategy response = null;
		response = service.deleteStrategyProfitLosses(profitloss_profitLossId, strategy_strategyId_1);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteStrategyProfitLosses
	}
	*/

	/**
	 * Operation Unit Test
	 * Save an existing StrategyAccount entity
	 * 
	 */
	@Test
	public void saveStrategyStrategyAccounts() {
		// TODO: JUnit - Populate test inputs for operation: saveStrategyStrategyAccounts 
		Integer strategyId_1 = null;
		StrategyAccount strategyaccount = null;
		Strategy response = null;
		response = service.saveStrategyStrategyAccounts(strategyId_1, strategyaccount);
		// TODO: JUnit - Add assertions to test outputs of operation: saveStrategyStrategyAccounts
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Strategy entity
	 * 
	 */
	@Test
	public void deleteStrategy() {
		// TODO: JUnit - Populate test inputs for operation: deleteStrategy 
		Strategy strategy = null;
		service.deleteStrategy(strategy);
	}

	/**
	 * Operation Unit Test
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	@Test
	public void deleteStrategyStrategyAccounts() {
		// TODO: JUnit - Populate test inputs for operation: deleteStrategyStrategyAccounts 
		Integer strategyaccount_strategyAccountId = null;
		Integer strategy_strategyId = null;
		Strategy response = null;
		response = service.deleteStrategyStrategyAccounts(strategyaccount_strategyAccountId, strategy_strategyId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteStrategyStrategyAccounts
	}

	/**
	 * Operation Unit Test
	 * Load an existing Strategy entity
	 * 
	 */
	@Test
	public void loadStrategys() {
		Set<Strategy> response = null;
		response = service.loadStrategys();
		// TODO: JUnit - Add assertions to test outputs of operation: loadStrategys
	}

	/**
	 * Operation Unit Test
	 * Save an existing Strategy entity
	 * 
	 */
	@Test
	public void saveStrategy() {
		// TODO: JUnit - Populate test inputs for operation: saveStrategy 
		Strategy strategy_1 = null;
		service.saveStrategy(strategy_1);
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
}

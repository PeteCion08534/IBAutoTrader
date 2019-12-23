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
public class ProfitLossServiceTest {

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
	protected ProfitLossService service;

	/**
	 * Instantiates a new ProfitLossServiceTest.
	 *
	 */
	public ProfitLossServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Strategy entity
	 * 
	 */
/*
	@Test
	public void deleteProfitLossStrategy() {
		// TODO: JUnit - Populate test inputs for operation: deleteProfitLossStrategy 
		Integer profitloss_profitLossId = null;
		Integer strategyAccount_strategyAccountId = null;
		ProfitLoss response = null;
		response = service.deleteProfitLossStrategyAccount(profitloss_profitLossId, strategyAccount_strategyAccountId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteProfitLossStrategy
	}
*/
	
	/**
	 * Operation Unit Test
	 * Save an existing Strategy entity
	 * 
	 */
/*
	@Test
	public void saveProfitLossStrategy() {
		// TODO: JUnit - Populate test inputs for operation: saveProfitLossStrategy 
		Integer profitLossId = null;
		StrategyAccount strategyAccount = null;
		ProfitLoss response = null;
		response = service.saveProfitLossStrategyAccount(profitLossId, strategyAccount);
		// TODO: JUnit - Add assertions to test outputs of operation: saveProfitLossStrategy
	}
*/
	/**
	 * Operation Unit Test
	 * Save an existing ProfitLoss entity
	 * 
	 */
	@Test
	public void saveProfitLoss() {
		// TODO: JUnit - Populate test inputs for operation: saveProfitLoss 
		ProfitLoss profitloss = null;
		service.saveProfitLoss(profitloss);
	}

	/**
	 * Operation Unit Test
	 * Load an existing ProfitLoss entity
	 * 
	 */
	@Test
	public void loadProfitLosss() {
		Set<ProfitLoss> response = null;
		response = service.loadProfitLosss();
		// TODO: JUnit - Add assertions to test outputs of operation: loadProfitLosss
	}

	/**
	 * Operation Unit Test
	 * Delete an existing ProfitLoss entity
	 * 
	 */
	@Test
	public void deleteProfitLoss() {
		// TODO: JUnit - Populate test inputs for operation: deleteProfitLoss 
		ProfitLoss profitloss_1 = null;
		service.deleteProfitLoss(profitloss_1);
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

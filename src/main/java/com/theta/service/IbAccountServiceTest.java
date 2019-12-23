package com.theta.service;

import com.theta.domain.IbAccount;
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
public class IbAccountServiceTest {

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
	protected IbAccountService service;

	/**
	 * Instantiates a new IbAccountServiceTest.
	 *
	 */
	public IbAccountServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Delete an existing IbAccount entity
	 * 
	 */
	@Test
	public void deleteIbAccount() {
		// TODO: JUnit - Populate test inputs for operation: deleteIbAccount 
		IbAccount ibaccount = null;
		service.deleteIbAccount(ibaccount);
	}

	/**
	 * Operation Unit Test
	 * Save an existing IbAccount entity
	 * 
	 */
	@Test
	public void saveIbAccount() {
		// TODO: JUnit - Populate test inputs for operation: saveIbAccount 
		IbAccount ibaccount_1 = null;
		service.saveIbAccount(ibaccount_1);
	}

	/**
	 * Operation Unit Test
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	@Test
	public void deleteIbAccountStrategyAccounts() {
		// TODO: JUnit - Populate test inputs for operation: deleteIbAccountStrategyAccounts 
		Integer strategyaccount_strategyAccountId = null;
		Integer ibaccount_ibAccountId = null;
		IbAccount response = null;
		response = service.deleteIbAccountStrategyAccounts(strategyaccount_strategyAccountId, ibaccount_ibAccountId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteIbAccountStrategyAccounts
	}

	/**
	 * Operation Unit Test
	 * Load an existing IbAccount entity
	 * 
	 */
	@Test
	public void loadIbAccounts() {
		Set<IbAccount> response = null;
		response = service.loadIbAccounts();
		// TODO: JUnit - Add assertions to test outputs of operation: loadIbAccounts
	}

	/**
	 * Operation Unit Test
	 * Save an existing StrategyAccount entity
	 * 
	 */
	@Test
	public void saveIbAccountStrategyAccounts() {
		// TODO: JUnit - Populate test inputs for operation: saveIbAccountStrategyAccounts 
		Integer ibAccountId = null;
		StrategyAccount strategyaccount = null;
		IbAccount response = null;
		response = service.saveIbAccountStrategyAccounts(ibAccountId, strategyaccount);
		// TODO: JUnit - Add assertions to test outputs of operation: saveIbAccountStrategyAccounts
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

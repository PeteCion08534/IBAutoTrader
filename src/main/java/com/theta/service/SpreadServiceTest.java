package com.theta.service;

import com.theta.domain.Position;
import com.theta.domain.Spread;

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
public class SpreadServiceTest {

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
	protected SpreadService service;

	/**
	 * Instantiates a new SpreadServiceTest.
	 *
	 */
	public SpreadServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Spread entity
	 * 
	 */
	@Test
	public void deleteSpread() {
		// TODO: JUnit - Populate test inputs for operation: deleteSpread 
		Spread spread = null;
		service.deleteSpread(spread);
	}

	/**
	 * Operation Unit Test
	 * Load an existing Spread entity
	 * 
	 */
	@Test
	public void loadSpreads() {
		Set<Spread> response = null;
		response = service.loadSpreads();
		// TODO: JUnit - Add assertions to test outputs of operation: loadSpreads
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Position entity
	 * 
	 */
/*
	@Test
	public void deleteSpreadPosition() {
		// TODO: JUnit - Populate test inputs for operation: deleteSpreadPosition 
		Integer spread_spreadId = null;
		Integer position_positionId = null;
		Spread response = null;
		response = service.deleteSpreadPosition(spread_spreadId, position_positionId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteSpreadPosition
	}
*/
	/**
	 * Operation Unit Test
	 * Save an existing Position entity
	 * 
	 */
	@Test
	public void saveSpreadPosition() {
		// TODO: JUnit - Populate test inputs for operation: saveSpreadPosition 
		Integer spreadId = null;
		Position position = null;
		Spread response = null;
		response = service.saveSpreadPosition(spreadId, position);
		// TODO: JUnit - Add assertions to test outputs of operation: saveSpreadPosition
	}

	/**
	 * Operation Unit Test
	 * Save an existing Spread entity
	 * 
	 */
	@Test
	public void saveSpread() {
		// TODO: JUnit - Populate test inputs for operation: saveSpread 
		Spread spread_1 = null;
		service.saveSpread(spread_1);
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

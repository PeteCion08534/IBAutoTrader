package com.theta.service;

import com.theta.domain.Position;
import com.theta.domain.Spread;
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
public class PositionServiceTest {

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
	protected PositionService service;

	/**
	 * Instantiates a new PositionServiceTest.
	 *
	 */
	public PositionServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Spread entity
	 * 
	 */
/*
	@Test
	public void deletePositionSpreads() {
		// TODO: JUnit - Populate test inputs for operation: deletePositionSpreads 
		Integer spread_spreadId = null;
		Integer position_positionId = null;
		Position response = null;
		response = service.deletePositionSpreads(spread_spreadId, position_positionId);
		// TODO: JUnit - Add assertions to test outputs of operation: deletePositionSpreads
	}
*/
	
	/**
	 * Operation Unit Test
	 * Save an existing Spread entity
	 * 
	 */
	@Test
	public void savePositionSpreads() {
		// TODO: JUnit - Populate test inputs for operation: savePositionSpreads 
		Integer positionId = null;
		Spread spread = null;
		Position response = null;
		response = service.savePositionSpreads(positionId, spread);
		// TODO: JUnit - Add assertions to test outputs of operation: savePositionSpreads
	}

	/**
	 * Operation Unit Test
	 * Save an existing Position entity
	 * 
	 */
	@Test
	public void savePosition() {
		// TODO: JUnit - Populate test inputs for operation: savePosition 
		Position position = null;
		service.savePosition(position);
	}

	/**
	 * Operation Unit Test
	 * Save an existing StrategyAccount entity
	 * 
	 */
	@Test
	public void savePositionStrategyAccount() {
		// TODO: JUnit - Populate test inputs for operation: savePositionStrategyAccount 
		Integer positionId_1 = null;
		StrategyAccount strategyaccount = null;
		Position response = null;
		response = service.savePositionStrategyAccount(positionId_1, strategyaccount);
		// TODO: JUnit - Add assertions to test outputs of operation: savePositionStrategyAccount
	}

	/**
	 * Operation Unit Test
	 * Load an existing Position entity
	 * 
	 */
	@Test
	public void loadPositions() {
		Set<Position> response = null;
		response = service.loadPositions();
		// TODO: JUnit - Add assertions to test outputs of operation: loadPositions
	}

	/**
	 * Operation Unit Test
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	@Test
	public void deletePositionStrategyAccount() {
		// TODO: JUnit - Populate test inputs for operation: deletePositionStrategyAccount 
		Integer position_positionId_1 = null;
		Integer strategyaccount_strategyAccountId = null;
		Position response = null;
		response = service.deletePositionStrategyAccount(position_positionId_1, strategyaccount_strategyAccountId);
		// TODO: JUnit - Add assertions to test outputs of operation: deletePositionStrategyAccount
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Position entity
	 * 
	 */
	@Test
	public void deletePosition() {
		// TODO: JUnit - Populate test inputs for operation: deletePosition 
		Position position_1 = null;
		service.deletePosition(position_1);
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

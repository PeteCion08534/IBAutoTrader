package com.theta.service;

import com.theta.domain.Heartbeat;

import java.util.Calendar;
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
public class HeartbeatServiceTest {

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
	protected HeartbeatService service;

	/**
	 * Instantiates a new HeartbeatServiceTest.
	 *
	 */
	public HeartbeatServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Load an existing Heartbeat entity
	 * 
	 */
	@Test
	public void loadHeartbeats() {
		Set<Heartbeat> response = null;
		response = service.loadHeartbeats();
		// TODO: JUnit - Add assertions to test outputs of operation: loadHeartbeats
	}

	/**
	 * Operation Unit Test
	 * Save an existing Heartbeat entity
	 * 
	 */
	@Test
	public void saveHeartbeat() {
		// TODO: JUnit - Populate test inputs for operation: saveHeartbeat 
		Heartbeat heartbeat = new Heartbeat();
		heartbeat.setHeartbeatId(21);
		Calendar heartbeatDate = Calendar.getInstance();
		heartbeatDate.set(2011,1,15);		
		heartbeat.setHeartbeatDate(heartbeatDate);
		heartbeat.setInProcess("Y");
		heartbeat.setThreadId(2);
		heartbeat.setCreatedBy("pcion");
		Calendar rightNow = Calendar.getInstance();
		heartbeat.setCreatedDate(rightNow);
		
		System.out.println("Here is context: " + context.getDisplayName() );
		
		service.saveHeartbeat(heartbeat);
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Heartbeat entity
	 * 
	 */
	@Test
	public void deleteHeartbeat() {
		// TODO: JUnit - Populate test inputs for operation: deleteHeartbeat 
		Heartbeat heartbeat_1 = null;
		service.deleteHeartbeat(heartbeat_1);
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

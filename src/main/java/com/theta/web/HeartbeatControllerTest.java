package com.theta.web;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.SessionScope;

/**
 * Unit test for the <code>HeartbeatController</code> controller.
 *
 * @see com.theta.web.HeartbeatController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./src/main/resources/theta11-generated-security-context.xml",
		"file:./src/main/resources/theta11-security-context.xml",
		"file:./src/main/resources/theta11-generated-service-context.xml",
		"file:./src/main/resources/theta11-service-context.xml",
		"file:./src/main/resources/theta11-generated-dao-context.xml",
		"file:./src/main/resources/theta11-dao-context.xml",
		"file:./src/main/resources/theta11-generated-web-context.xml",
		"file:./src/main/resources/theta11-web-context.xml" })
public class HeartbeatControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>indexHeartbeat()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void indexHeartbeat() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexHeartbeat");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HeartbeatController controller = (HeartbeatController) context.getBean("HeartbeatController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectHeartbeat()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectHeartbeat() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectHeartbeat");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HeartbeatController controller = (HeartbeatController) context.getBean("HeartbeatController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editHeartbeat()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editHeartbeat() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editHeartbeat");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HeartbeatController controller = (HeartbeatController) context.getBean("HeartbeatController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveHeartbeat()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveHeartbeat() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveHeartbeat");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HeartbeatController controller = (HeartbeatController) context.getBean("HeartbeatController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newHeartbeat()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newHeartbeat() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newHeartbeat");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HeartbeatController controller = (HeartbeatController) context.getBean("HeartbeatController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteHeartbeat()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteHeartbeat() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteHeartbeat");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HeartbeatController controller = (HeartbeatController) context.getBean("HeartbeatController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteHeartbeat()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteHeartbeat() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteHeartbeat");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HeartbeatController controller = (HeartbeatController) context.getBean("HeartbeatController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>heartbeatControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void heartbeatControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/heartbeatController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HeartbeatController controller = (HeartbeatController) context.getBean("HeartbeatController");

		// TODO Invoke method and Assert return values

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
	 * Returns a mock HttpServletRequest object.
	 *
	 */
	private MockHttpServletRequest getMockHttpServletRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attributes);
		return request;
	}

	/**
	 * Returns a mock HttpServletResponse object.
	 *
	 */
	private MockHttpServletResponse getMockHttpServletResponse() {
		return new MockHttpServletResponse();
	}
}
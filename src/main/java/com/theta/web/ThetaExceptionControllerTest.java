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
 * Unit test for the <code>ThetaExceptionController</code> controller.
 *
 * @see com.theta.web.ThetaExceptionController
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
public class ThetaExceptionControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>indexThetaException()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void indexThetaException() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexThetaException");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ThetaExceptionController controller = (ThetaExceptionController) context.getBean("ThetaExceptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectThetaException()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectThetaException() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectThetaException");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ThetaExceptionController controller = (ThetaExceptionController) context.getBean("ThetaExceptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editThetaException()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editThetaException() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editThetaException");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ThetaExceptionController controller = (ThetaExceptionController) context.getBean("ThetaExceptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveThetaException()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveThetaException() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveThetaException");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ThetaExceptionController controller = (ThetaExceptionController) context.getBean("ThetaExceptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newThetaException()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newThetaException() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newThetaException");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ThetaExceptionController controller = (ThetaExceptionController) context.getBean("ThetaExceptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteThetaException()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteThetaException() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteThetaException");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ThetaExceptionController controller = (ThetaExceptionController) context.getBean("ThetaExceptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteThetaException()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteThetaException() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteThetaException");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ThetaExceptionController controller = (ThetaExceptionController) context.getBean("ThetaExceptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>thetaexceptionControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void thetaexceptionControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/thetaexceptionController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ThetaExceptionController controller = (ThetaExceptionController) context.getBean("ThetaExceptionController");

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
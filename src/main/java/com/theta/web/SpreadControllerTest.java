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
 * Unit test for the <code>SpreadController</code> controller.
 *
 * @see com.theta.web.SpreadController
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
public class SpreadControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editSpreadPosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editSpreadPosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editSpreadPosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newSpreadPosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newSpreadPosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newSpreadPosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveSpreadPosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveSpreadPosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveSpreadPosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteSpreadPosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteSpreadPosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteSpreadPosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteSpreadPosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteSpreadPosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteSpreadPosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectSpreadPosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectSpreadPosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectSpreadPosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listSpreadPosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void listSpreadPosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listSpreadPosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexSpread()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void indexSpread() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexSpread");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectSpread()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectSpread() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectSpread");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editSpread()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editSpread() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editSpread");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveSpread()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveSpread() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveSpread");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newSpread()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newSpread() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newSpread");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteSpread()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteSpread() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteSpread");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteSpread()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteSpread() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteSpread");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>spreadControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void spreadControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/spreadController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SpreadController controller = (SpreadController) context.getBean("SpreadController");

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
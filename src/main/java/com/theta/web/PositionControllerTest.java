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
 * Unit test for the <code>PositionController</code> controller.
 *
 * @see com.theta.web.PositionController
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
public class PositionControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editPositionStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editPositionStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editPositionStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newPositionStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newPositionStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newPositionStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>savePositionStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void savePositionStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/savePositionStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeletePositionStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeletePositionStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeletePositionStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deletePositionStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deletePositionStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deletePositionStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectPositionStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectPositionStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectPositionStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listPositionStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void listPositionStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listPositionStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editPositionSpreads()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editPositionSpreads() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editPositionSpreads");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newPositionSpreads()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newPositionSpreads() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newPositionSpreads");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>savePositionSpreads()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void savePositionSpreads() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/savePositionSpreads");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeletePositionSpreads()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeletePositionSpreads() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeletePositionSpreads");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deletePositionSpreads()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deletePositionSpreads() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deletePositionSpreads");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectPositionSpreads()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectPositionSpreads() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectPositionSpreads");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listPositionSpreads()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void listPositionSpreads() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listPositionSpreads");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexPosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void indexPosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexPosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectPosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectPosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectPosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editPosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editPosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editPosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>savePosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void savePosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/savePosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newPosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newPosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newPosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeletePosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeletePosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeletePosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deletePosition()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deletePosition() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deletePosition");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>positionControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void positionControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/positionController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		PositionController controller = (PositionController) context.getBean("PositionController");

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
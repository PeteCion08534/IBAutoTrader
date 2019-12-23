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
 * Unit test for the <code>StrategyAccountController</code> controller.
 *
 * @see com.theta.web.StrategyAccountController
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
public class StrategyAccountControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editStrategyAccountStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editStrategyAccountStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editStrategyAccountStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newStrategyAccountStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newStrategyAccountStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newStrategyAccountStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveStrategyAccountStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveStrategyAccountStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveStrategyAccountStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteStrategyAccountStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteStrategyAccountStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteStrategyAccountStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteStrategyAccountStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteStrategyAccountStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteStrategyAccountStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectStrategyAccountStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectStrategyAccountStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectStrategyAccountStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listStrategyAccountStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void listStrategyAccountStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listStrategyAccountStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editStrategyAccountIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editStrategyAccountIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editStrategyAccountIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newStrategyAccountIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newStrategyAccountIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newStrategyAccountIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveStrategyAccountIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveStrategyAccountIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveStrategyAccountIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteStrategyAccountIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteStrategyAccountIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteStrategyAccountIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteStrategyAccountIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteStrategyAccountIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteStrategyAccountIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectStrategyAccountIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectStrategyAccountIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectStrategyAccountIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listStrategyAccountIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void listStrategyAccountIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listStrategyAccountIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editStrategyAccountPositions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editStrategyAccountPositions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editStrategyAccountPositions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newStrategyAccountPositions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newStrategyAccountPositions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newStrategyAccountPositions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveStrategyAccountPositions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveStrategyAccountPositions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveStrategyAccountPositions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteStrategyAccountPositions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteStrategyAccountPositions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteStrategyAccountPositions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteStrategyAccountPositions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteStrategyAccountPositions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteStrategyAccountPositions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectStrategyAccountPositions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectStrategyAccountPositions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectStrategyAccountPositions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listStrategyAccountPositions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void listStrategyAccountPositions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listStrategyAccountPositions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void indexStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteStrategyAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteStrategyAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteStrategyAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>strategyaccountControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void strategyaccountControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/strategyaccountController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyAccountController controller = (StrategyAccountController) context.getBean("StrategyAccountController");

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
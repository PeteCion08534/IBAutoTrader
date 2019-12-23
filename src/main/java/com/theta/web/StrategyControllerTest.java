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
 * Unit test for the <code>StrategyController</code> controller.
 *
 * @see com.theta.web.StrategyController
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
public class StrategyControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editStrategyStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editStrategyStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editStrategyStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newStrategyStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newStrategyStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newStrategyStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveStrategyStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveStrategyStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveStrategyStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteStrategyStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteStrategyStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteStrategyStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteStrategyStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteStrategyStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteStrategyStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectStrategyStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectStrategyStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectStrategyStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listStrategyStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void listStrategyStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listStrategyStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editStrategyProfitLosses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editStrategyProfitLosses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editStrategyProfitLosses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newStrategyProfitLosses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newStrategyProfitLosses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newStrategyProfitLosses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveStrategyProfitLosses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveStrategyProfitLosses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveStrategyProfitLosses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteStrategyProfitLosses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteStrategyProfitLosses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteStrategyProfitLosses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteStrategyProfitLosses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteStrategyProfitLosses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteStrategyProfitLosses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectStrategyProfitLosses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectStrategyProfitLosses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectStrategyProfitLosses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listStrategyProfitLosses()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void listStrategyProfitLosses() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listStrategyProfitLosses");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void indexStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>strategyControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void strategyControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/strategyController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		StrategyController controller = (StrategyController) context.getBean("StrategyController");

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
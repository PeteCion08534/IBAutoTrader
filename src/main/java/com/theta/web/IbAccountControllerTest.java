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
 * Unit test for the <code>IbAccountController</code> controller.
 *
 * @see com.theta.web.IbAccountController
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
public class IbAccountControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editIbAccountStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editIbAccountStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editIbAccountStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newIbAccountStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newIbAccountStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newIbAccountStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveIbAccountStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveIbAccountStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveIbAccountStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteIbAccountStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteIbAccountStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteIbAccountStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteIbAccountStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteIbAccountStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteIbAccountStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectIbAccountStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectIbAccountStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectIbAccountStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listIbAccountStrategyAccounts()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void listIbAccountStrategyAccounts() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listIbAccountStrategyAccounts");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void indexIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteIbAccount()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteIbAccount() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteIbAccount");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>ibaccountControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void ibaccountControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/ibaccountController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		IbAccountController controller = (IbAccountController) context.getBean("IbAccountController");

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
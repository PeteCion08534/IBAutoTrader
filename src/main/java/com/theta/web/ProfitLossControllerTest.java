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
 * Unit test for the <code>ProfitLossController</code> controller.
 *
 * @see com.theta.web.ProfitLossController
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
public class ProfitLossControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editProfitLossStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editProfitLossStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editProfitLossStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newProfitLossStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newProfitLossStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newProfitLossStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveProfitLossStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveProfitLossStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveProfitLossStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteProfitLossStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteProfitLossStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteProfitLossStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteProfitLossStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteProfitLossStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteProfitLossStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectProfitLossStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectProfitLossStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectProfitLossStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listProfitLossStrategy()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void listProfitLossStrategy() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listProfitLossStrategy");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexProfitLoss()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void indexProfitLoss() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexProfitLoss");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectProfitLoss()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectProfitLoss() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectProfitLoss");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editProfitLoss()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editProfitLoss() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editProfitLoss");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveProfitLoss()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveProfitLoss() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveProfitLoss");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newProfitLoss()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newProfitLoss() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newProfitLoss");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteProfitLoss()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteProfitLoss() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteProfitLoss");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteProfitLoss()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteProfitLoss() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteProfitLoss");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>profitlossControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void profitlossControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/profitlossController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		ProfitLossController controller = (ProfitLossController) context.getBean("ProfitLossController");

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
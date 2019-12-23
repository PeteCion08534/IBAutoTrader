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
 * Unit test for the <code>SnapshotController</code> controller.
 *
 * @see com.theta.web.SnapshotController
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
public class SnapshotControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editSnapshotSnapshotOptions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editSnapshotSnapshotOptions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editSnapshotSnapshotOptions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newSnapshotSnapshotOptions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newSnapshotSnapshotOptions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newSnapshotSnapshotOptions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveSnapshotSnapshotOptions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveSnapshotSnapshotOptions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveSnapshotSnapshotOptions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteSnapshotSnapshotOptions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteSnapshotSnapshotOptions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteSnapshotSnapshotOptions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteSnapshotSnapshotOptions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteSnapshotSnapshotOptions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteSnapshotSnapshotOptions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectSnapshotSnapshotOptions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectSnapshotSnapshotOptions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectSnapshotSnapshotOptions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listSnapshotSnapshotOptions()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void listSnapshotSnapshotOptions() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listSnapshotSnapshotOptions");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void indexSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>snapshotControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void snapshotControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/snapshotController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotController controller = (SnapshotController) context.getBean("SnapshotController");

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
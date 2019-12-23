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
 * Unit test for the <code>SnapshotOptionController</code> controller.
 *
 * @see com.theta.web.SnapshotOptionController
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
public class SnapshotOptionControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>editSnapshotOptionSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editSnapshotOptionSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editSnapshotOptionSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newSnapshotOptionSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newSnapshotOptionSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newSnapshotOptionSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveSnapshotOptionSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveSnapshotOptionSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveSnapshotOptionSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteSnapshotOptionSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteSnapshotOptionSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteSnapshotOptionSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteSnapshotOptionSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteSnapshotOptionSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteSnapshotOptionSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectSnapshotOptionSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectSnapshotOptionSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectSnapshotOptionSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>listSnapshotOptionSnapshot()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void listSnapshotOptionSnapshot() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/listSnapshotOptionSnapshot");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>indexSnapshotOption()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void indexSnapshotOption() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexSnapshotOption");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectSnapshotOption()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectSnapshotOption() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectSnapshotOption");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editSnapshotOption()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editSnapshotOption() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editSnapshotOption");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveSnapshotOption()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveSnapshotOption() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveSnapshotOption");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newSnapshotOption()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newSnapshotOption() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newSnapshotOption");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteSnapshotOption()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteSnapshotOption() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteSnapshotOption");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteSnapshotOption()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteSnapshotOption() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteSnapshotOption");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>snapshotoptionControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void snapshotoptionControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/snapshotoptionController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		SnapshotOptionController controller = (SnapshotOptionController) context.getBean("SnapshotOptionController");

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
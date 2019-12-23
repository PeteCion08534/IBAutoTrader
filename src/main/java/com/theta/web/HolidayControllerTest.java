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
 * Unit test for the <code>HolidayController</code> controller.
 *
 * @see com.theta.web.HolidayController
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
public class HolidayControllerTest {
	/**
	 * The Spring application context.
	 *
	 */
	private ApplicationContext context;

	/**
	 * Test <code>indexHoliday()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void indexHoliday() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/indexHoliday");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HolidayController controller = (HolidayController) context.getBean("HolidayController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>selectHoliday()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void selectHoliday() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/selectHoliday");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HolidayController controller = (HolidayController) context.getBean("HolidayController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>editHoliday()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void editHoliday() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/editHoliday");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HolidayController controller = (HolidayController) context.getBean("HolidayController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>saveHoliday()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void saveHoliday() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/saveHoliday");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HolidayController controller = (HolidayController) context.getBean("HolidayController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>newHoliday()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void newHoliday() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/newHoliday");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HolidayController controller = (HolidayController) context.getBean("HolidayController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>confirmDeleteHoliday()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void confirmDeleteHoliday() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/confirmDeleteHoliday");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HolidayController controller = (HolidayController) context.getBean("HolidayController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>deleteHoliday()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void deleteHoliday() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/deleteHoliday");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HolidayController controller = (HolidayController) context.getBean("HolidayController");

		// TODO Invoke method and Assert return values

	}

	/**
	 * Test <code>holidayControllerbinaryaction()</code>.
	 */
	@Test
	@SuppressWarnings("unused")
	public void holidayControllerbinaryaction() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest();
		request.setRequestURI("/holidayController/binary.action");
		MockHttpServletResponse response = getMockHttpServletResponse();

		// Get the singleton controller instance
		HolidayController controller = (HolidayController) context.getBean("HolidayController");

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
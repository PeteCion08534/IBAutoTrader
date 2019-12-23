package com.theta.service;

import com.theta.domain.Snapshot;
import com.theta.domain.SnapshotOption;

import java.util.Set;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.mock.web.MockHttpServletRequest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.SessionScope;

/**
 * Class to run the service as a JUnit test. Each operation in the service is a separate test.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners( {
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = {
		"file:./src/main/resources/theta11-generated-security-context.xml",
		"file:./src/main/resources/theta11-security-context.xml",
		"file:./src/main/resources/theta11-generated-service-context.xml",
		"file:./src/main/resources/theta11-service-context.xml",
		"file:./src/main/resources/theta11-generated-dao-context.xml",
		"file:./src/main/resources/theta11-dao-context.xml",
		"file:./src/main/resources/theta11-generated-web-context.xml",
		"file:./src/main/resources/theta11-web-context.xml" })
@Transactional
public class SnapshotOptionServiceTest {

	/**
	 * The Spring application context.
	 *
	 */
	@SuppressWarnings("unused")
	private ApplicationContext context;

	/**
	 * The service being tested, injected by Spring.
	 *
	 */
	@Autowired
	protected SnapshotOptionService service;

	/**
	 * Instantiates a new SnapshotOptionServiceTest.
	 *
	 */
	public SnapshotOptionServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Load an existing SnapshotOption entity
	 * 
	 */
	@Test
	public void loadSnapshotOptions() {
		Set<SnapshotOption> response = null;
		response = service.loadSnapshotOptions();
		// TODO: JUnit - Add assertions to test outputs of operation: loadSnapshotOptions
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Snapshot entity
	 * 
	 */
	@Test
	public void deleteSnapshotOptionSnapshot() {
		// TODO: JUnit - Populate test inputs for operation: deleteSnapshotOptionSnapshot 
		Integer snapshotoption_snapshotOptionId = null;
		Integer snapshot_snapshotId = null;
		SnapshotOption response = null;
		response = service.deleteSnapshotOptionSnapshot(snapshotoption_snapshotOptionId, snapshot_snapshotId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteSnapshotOptionSnapshot
	}

	/**
	 * Operation Unit Test
	 * Save an existing SnapshotOption entity
	 * 
	 */
	@Test
	public void saveSnapshotOption() {
		// TODO: JUnit - Populate test inputs for operation: saveSnapshotOption 
		SnapshotOption snapshotoption = null;
		service.saveSnapshotOption(snapshotoption);
	}

	/**
	 * Operation Unit Test
	 * Delete an existing SnapshotOption entity
	 * 
	 */
	@Test
	public void deleteSnapshotOption() {
		// TODO: JUnit - Populate test inputs for operation: deleteSnapshotOption 
		SnapshotOption snapshotoption_1 = null;
		service.deleteSnapshotOption(snapshotoption_1);
	}

	/**
	 * Operation Unit Test
	 * Save an existing Snapshot entity
	 * 
	 */
	@Test
	public void saveSnapshotOptionSnapshot() {
		// TODO: JUnit - Populate test inputs for operation: saveSnapshotOptionSnapshot 
		Integer snapshotOptionId = null;
		Snapshot snapshot = null;
		SnapshotOption response = null;
		response = service.saveSnapshotOptionSnapshot(snapshotOptionId, snapshot);
		// TODO: JUnit - Add assertions to test outputs of operation: saveSnapshotOptionSnapshot
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
	 * Sets Up the Request context
	 *
	 */
	private void setupRequestContext() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attributes);
	}
}

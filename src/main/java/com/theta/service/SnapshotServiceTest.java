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
public class SnapshotServiceTest {

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
	protected SnapshotService service;

	/**
	 * Instantiates a new SnapshotServiceTest.
	 *
	 */
	public SnapshotServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Save an existing SnapshotOption entity
	 * 
	 */
	@Test
	public void saveSnapshotSnapshotOptions() {
		// TODO: JUnit - Populate test inputs for operation: saveSnapshotSnapshotOptions 
		Integer snapshotId = null;
		SnapshotOption snapshotoption = null;
		Snapshot response = null;
		response = service.saveSnapshotSnapshotOptions(snapshotId, snapshotoption);
		// TODO: JUnit - Add assertions to test outputs of operation: saveSnapshotSnapshotOptions
	}

	/**
	 * Operation Unit Test
	 * Save an existing Snapshot entity
	 * 
	 */
	@Test
	public void saveSnapshot() {
		// TODO: JUnit - Populate test inputs for operation: saveSnapshot 
		Snapshot snapshot = null;
		service.saveSnapshot(snapshot);
	}

	/**
	 * Operation Unit Test
	 * Load an existing Snapshot entity
	 * 
	 */
	@Test
	public void loadSnapshots() {
		Set<Snapshot> response = null;
		response = service.loadSnapshots();
		// TODO: JUnit - Add assertions to test outputs of operation: loadSnapshots
	}

	/**
	 * Operation Unit Test
	 * Delete an existing Snapshot entity
	 * 
	 */
	@Test
	public void deleteSnapshot() {
		// TODO: JUnit - Populate test inputs for operation: deleteSnapshot 
		Snapshot snapshot_1 = null;
		service.deleteSnapshot(snapshot_1);
	}

	/**
	 * Operation Unit Test
	 * Delete an existing SnapshotOption entity
	 * 
	 */
	@Test
	public void deleteSnapshotSnapshotOptions() {
		// TODO: JUnit - Populate test inputs for operation: deleteSnapshotSnapshotOptions 
		Integer snapshotoption_snapshotOptionId = null;
		Integer snapshot_snapshotId = null;
		Snapshot response = null;
		response = service.deleteSnapshotSnapshotOptions(snapshotoption_snapshotOptionId, snapshot_snapshotId);
		// TODO: JUnit - Add assertions to test outputs of operation: deleteSnapshotSnapshotOptions
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

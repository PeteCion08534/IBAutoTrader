package com.theta.service;

import com.theta.dao.HeartbeatDAO;

import com.theta.domain.Heartbeat;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Heartbeat entities
 * 
 */

@Service("HeartbeatService")
@Transactional
public class HeartbeatServiceImpl implements HeartbeatService {

	/**
	 * DAO injected by Spring that manages Heartbeat entities
	 * 
	 */
	@Autowired
	private HeartbeatDAO heartbeatDAO;

	/**
	 * Instantiates a new HeartbeatServiceImpl.
	 *
	 */
	public HeartbeatServiceImpl() {
	}

	/**
	 * Load an existing Heartbeat entity
	 * 
	 */
	@Transactional
	public Set<Heartbeat> loadHeartbeats() {
		return heartbeatDAO.findAllHeartbeats();
	}

	/**
	 * Save an existing Heartbeat entity
	 * 
	 */
	@Transactional
	public void saveHeartbeat(Heartbeat heartbeat) {
		Heartbeat existingHeartbeat = heartbeatDAO.findHeartbeatByPrimaryKey(heartbeat.getHeartbeatId());

		if (existingHeartbeat != null) {
			existingHeartbeat.setHeartbeatId(heartbeat.getHeartbeatId());
			existingHeartbeat.setHeartbeatDate(heartbeat.getHeartbeatDate());
			existingHeartbeat.setInProcess(heartbeat.getInProcess());
			existingHeartbeat.setThreadId(heartbeat.getThreadId());
			existingHeartbeat.setTerminationStatus(heartbeat.getTerminationStatus());
			existingHeartbeat.setSnapshot(heartbeat.getSnapshot());
			existingHeartbeat.setHeartbeatLog(heartbeat.getHeartbeatLog());
			existingHeartbeat.setCreatedBy(heartbeat.getCreatedBy());
			existingHeartbeat.setCreatedDate(heartbeat.getCreatedDate());
			existingHeartbeat.setUpdatedBy(heartbeat.getUpdatedBy());
			existingHeartbeat.setUpdatedDate(heartbeat.getUpdatedDate());
			heartbeat = heartbeatDAO.store(existingHeartbeat);
		} else {
			heartbeat = heartbeatDAO.store(heartbeat);
		}
		heartbeatDAO.flush();
	}

	/**
	 * Delete an existing Heartbeat entity
	 * 
	 */
	@Transactional
	public void deleteHeartbeat(Heartbeat heartbeat) {
		heartbeatDAO.remove(heartbeat);
		heartbeatDAO.flush();
	}
}

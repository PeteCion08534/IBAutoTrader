package com.theta.service;

import com.theta.domain.Heartbeat;

import java.util.Set;

/**
 * Spring service that handles CRUD requests for Heartbeat entities
 * 
 */
public interface HeartbeatService {

	/**
	 * Load an existing Heartbeat entity
	 * 
	 */
	public Set<Heartbeat> loadHeartbeats();

	/**
	 * Save an existing Heartbeat entity
	 * 
	 */
	public void saveHeartbeat(Heartbeat heartbeat);

	/**
	 * Delete an existing Heartbeat entity
	 * 
	 */
	public void deleteHeartbeat(Heartbeat heartbeat_1);
}
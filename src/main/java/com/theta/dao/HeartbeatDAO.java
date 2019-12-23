package com.theta.dao;

import com.theta.domain.Heartbeat;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Heartbeat entities.
 * 
 */
public interface HeartbeatDAO extends JpaDao {

	/**
	 * JPQL Query - findHeartbeatByUpdatedBy
	 *
	 */
	public Set<Heartbeat> findHeartbeatByUpdatedBy(String updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByUpdatedBy
	 *
	 */
	public Set<Heartbeat> findHeartbeatByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByHeartbeatId
	 *
	 */
	public Heartbeat findHeartbeatByHeartbeatId(Integer heartbeatId) throws DataAccessException;

	/**
	 * JPQL Query - findMaxHeartbeat
	 *
	 */
	//public Heartbeat findMaxHeartbeat() throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByHeartbeatId
	 *
	 */
	public Heartbeat findHeartbeatByHeartbeatId(Integer heartbeatId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByCreatedDate
	 *
	 */
	public Set<Heartbeat> findHeartbeatByCreatedDate(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByCreatedDate
	 *
	 */
	public Set<Heartbeat> findHeartbeatByCreatedDate(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByCreatedByContaining
	 *
	 */
	public Set<Heartbeat> findHeartbeatByCreatedByContaining(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByCreatedByContaining
	 *
	 */
	public Set<Heartbeat> findHeartbeatByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByInProcess
	 *
	 */
	public Set<Heartbeat> findHeartbeatByInProcess(String inProcess) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByInProcess
	 *
	 */
	public Set<Heartbeat> findHeartbeatByInProcess(String inProcess, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByThreadId
	 *
	 */
	public Set<Heartbeat> findHeartbeatByThreadId(Integer threadId) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByThreadId
	 *
	 */
	public Set<Heartbeat> findHeartbeatByThreadId(Integer threadId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllHeartbeats
	 *
	 */
	public Set<Heartbeat> findAllHeartbeats() throws DataAccessException;

	/**
	 * JPQL Query - findAllHeartbeats
	 *
	 */
	public Set<Heartbeat> findAllHeartbeats(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByUpdatedDate
	 *
	 */
	public Set<Heartbeat> findHeartbeatByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByUpdatedDate
	 *
	 */
	public Set<Heartbeat> findHeartbeatByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByInProcessContaining
	 *
	 */
	public Set<Heartbeat> findHeartbeatByInProcessContaining(String inProcess_1) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByInProcessContaining
	 *
	 */
	public Set<Heartbeat> findHeartbeatByInProcessContaining(String inProcess_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByUpdatedDateBefore
	 *
	 */
	public Set<Heartbeat> findHeartbeatByUpdatedDateBefore(java.util.Calendar updatedDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByUpdatedDateBefore
	 *
	 */
	public Set<Heartbeat> findHeartbeatByUpdatedDateBefore(Calendar updatedDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByHeartbeatLog
	 *
	 */
	public Set<Heartbeat> findHeartbeatByHeartbeatLog(String heartbeatLog) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByHeartbeatLog
	 *
	 */
	public Set<Heartbeat> findHeartbeatByHeartbeatLog(String heartbeatLog, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByCreatedDateAfter
	 *
	 */
	public Set<Heartbeat> findHeartbeatByCreatedDateAfter(java.util.Calendar createdDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByCreatedDateAfter
	 *
	 */
	public Set<Heartbeat> findHeartbeatByCreatedDateAfter(Calendar createdDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByHeartbeatLogContaining
	 *
	 */
	public Set<Heartbeat> findHeartbeatByHeartbeatLogContaining(String heartbeatLog_1) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByHeartbeatLogContaining
	 *
	 */
	public Set<Heartbeat> findHeartbeatByHeartbeatLogContaining(String heartbeatLog_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByCreatedDateBefore
	 *
	 */
	public Set<Heartbeat> findHeartbeatByCreatedDateBefore(java.util.Calendar createdDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByCreatedDateBefore
	 *
	 */
	public Set<Heartbeat> findHeartbeatByCreatedDateBefore(Calendar createdDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByHeartbeatDateAfter
	 *
	 */
	public Set<Heartbeat> findHeartbeatByHeartbeatDateAfter(java.util.Calendar heartbeatDate) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByHeartbeatDateAfter
	 *
	 */
	public Set<Heartbeat> findHeartbeatByHeartbeatDateAfter(Calendar heartbeatDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByHeartbeatDate
	 *
	 */
	public Set<Heartbeat> findHeartbeatByHeartbeatDate(java.util.Calendar heartbeatDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByHeartbeatDate
	 *
	 */
	public Set<Heartbeat> findHeartbeatByHeartbeatDate(Calendar heartbeatDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByUpdatedDateAfter
	 *
	 */
	public Set<Heartbeat> findHeartbeatByUpdatedDateAfter(java.util.Calendar updatedDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByUpdatedDateAfter
	 *
	 */
	public Set<Heartbeat> findHeartbeatByUpdatedDateAfter(Calendar updatedDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByHeartbeatDateBefore
	 *
	 */
	public Set<Heartbeat> findHeartbeatByHeartbeatDateBefore(java.util.Calendar heartbeatDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByHeartbeatDateBefore
	 *
	 */
	public Set<Heartbeat> findHeartbeatByHeartbeatDateBefore(Calendar heartbeatDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByPrimaryKey
	 *
	 */
	public Heartbeat findHeartbeatByPrimaryKey(Integer heartbeatId_1) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByPrimaryKey
	 *
	 */
	public Heartbeat findHeartbeatByPrimaryKey(Integer heartbeatId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByCreatedBy
	 *
	 */
	public Set<Heartbeat> findHeartbeatByCreatedBy(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByCreatedBy
	 *
	 */
	public Set<Heartbeat> findHeartbeatByCreatedBy(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByTerminationStatus
	 *
	 */
	public Set<Heartbeat> findHeartbeatByTerminationStatus(String terminationStatus) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByTerminationStatus
	 *
	 */
	public Set<Heartbeat> findHeartbeatByTerminationStatus(String terminationStatus, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByUpdatedByContaining
	 *
	 */
	public Set<Heartbeat> findHeartbeatByUpdatedByContaining(String updatedBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByUpdatedByContaining
	 *
	 */
	public Set<Heartbeat> findHeartbeatByUpdatedByContaining(String updatedBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByTerminationStatusContaining
	 *
	 */
	public Set<Heartbeat> findHeartbeatByTerminationStatusContaining(String terminationStatus_1) throws DataAccessException;

	/**
	 * JPQL Query - findHeartbeatByTerminationStatusContaining
	 *
	 */
	public Set<Heartbeat> findHeartbeatByTerminationStatusContaining(String terminationStatus_1, int startResult, int maxRows) throws DataAccessException;

	public Heartbeat findMaxHeartbeat();

}
package com.theta.dao;

import com.theta.domain.Heartbeat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Heartbeat entities.
 * 
 */
@Repository("HeartbeatDAO")
@Transactional
public class HeartbeatDAOImpl extends AbstractJpaDao implements HeartbeatDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Heartbeat.class }));

	/**
	 * EntityManager injected by Spring for persistence unit LocOracleTheta
	 *
	 */
	@PersistenceContext(unitName = "LocOracleTheta")
	private EntityManager entityManager;

	/**
	 * Instantiates a new HeartbeatDAOImpl
	 *
	 */
	public HeartbeatDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit LocOracleTheta
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findHeartbeatByUpdatedBy
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByUpdatedBy(String updatedBy) throws DataAccessException {

		return findHeartbeatByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByHeartbeatId
	 *
	 */
	@Transactional
	public Heartbeat findHeartbeatByHeartbeatId(Integer heartbeatId) throws DataAccessException {

		return findHeartbeatByHeartbeatId(heartbeatId, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByHeartbeatId
	 *
	 */

	@Transactional
	public Heartbeat findHeartbeatByHeartbeatId(Integer heartbeatId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findHeartbeatByHeartbeatId", heartbeatId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findMaxHeartbeat
	 *
	 */

	@Transactional
	public Heartbeat findMaxHeartbeat() throws DataAccessException {
		try {
			Query query = createQuery("select max(heartbeat_id) from heartbeat",1,1);
			return (Heartbeat) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findHeartbeatByCreatedDate
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findHeartbeatByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByCreatedByContaining(String createdBy) throws DataAccessException {

		return findHeartbeatByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByInProcess
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByInProcess(String inProcess) throws DataAccessException {

		return findHeartbeatByInProcess(inProcess, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByInProcess
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByInProcess(String inProcess, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByInProcess", startResult, maxRows, inProcess);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByThreadId
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByThreadId(Integer threadId) throws DataAccessException {

		return findHeartbeatByThreadId(threadId, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByThreadId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByThreadId(Integer threadId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByThreadId", startResult, maxRows, threadId);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllHeartbeats
	 *
	 */
	@Transactional
	public Set<Heartbeat> findAllHeartbeats() throws DataAccessException {

		return findAllHeartbeats(-1, -1);
	}

	/**
	 * JPQL Query - findAllHeartbeats
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findAllHeartbeats(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllHeartbeats", startResult, maxRows);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByUpdatedDate
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findHeartbeatByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByInProcessContaining
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByInProcessContaining(String inProcess) throws DataAccessException {

		return findHeartbeatByInProcessContaining(inProcess, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByInProcessContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByInProcessContaining(String inProcess, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByInProcessContaining", startResult, maxRows, inProcess);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByUpdatedDateBefore
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException {

		return findHeartbeatByUpdatedDateBefore(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByUpdatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByUpdatedDateBefore(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByUpdatedDateBefore", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByHeartbeatLog
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByHeartbeatLog(String heartbeatLog) throws DataAccessException {

		return findHeartbeatByHeartbeatLog(heartbeatLog, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByHeartbeatLog
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByHeartbeatLog(String heartbeatLog, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByHeartbeatLog", startResult, maxRows, heartbeatLog);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByCreatedDateAfter
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException {

		return findHeartbeatByCreatedDateAfter(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByCreatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByCreatedDateAfter(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByCreatedDateAfter", startResult, maxRows, createdDate);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByHeartbeatLogContaining
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByHeartbeatLogContaining(String heartbeatLog) throws DataAccessException {

		return findHeartbeatByHeartbeatLogContaining(heartbeatLog, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByHeartbeatLogContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByHeartbeatLogContaining(String heartbeatLog, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByHeartbeatLogContaining", startResult, maxRows, heartbeatLog);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByCreatedDateBefore
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByCreatedDateBefore(java.util.Calendar createdDate) throws DataAccessException {

		return findHeartbeatByCreatedDateBefore(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByCreatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByCreatedDateBefore(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByCreatedDateBefore", startResult, maxRows, createdDate);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByHeartbeatDateAfter
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByHeartbeatDateAfter(java.util.Calendar heartbeatDate) throws DataAccessException {

		return findHeartbeatByHeartbeatDateAfter(heartbeatDate, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByHeartbeatDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByHeartbeatDateAfter(java.util.Calendar heartbeatDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByHeartbeatDateAfter", startResult, maxRows, heartbeatDate);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByHeartbeatDate
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByHeartbeatDate(java.util.Calendar heartbeatDate) throws DataAccessException {

		return findHeartbeatByHeartbeatDate(heartbeatDate, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByHeartbeatDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByHeartbeatDate(java.util.Calendar heartbeatDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByHeartbeatDate", startResult, maxRows, heartbeatDate);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByUpdatedDateAfter
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByUpdatedDateAfter(java.util.Calendar updatedDate) throws DataAccessException {

		return findHeartbeatByUpdatedDateAfter(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByUpdatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByUpdatedDateAfter(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByUpdatedDateAfter", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByHeartbeatDateBefore
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByHeartbeatDateBefore(java.util.Calendar heartbeatDate) throws DataAccessException {

		return findHeartbeatByHeartbeatDateBefore(heartbeatDate, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByHeartbeatDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByHeartbeatDateBefore(java.util.Calendar heartbeatDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByHeartbeatDateBefore", startResult, maxRows, heartbeatDate);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByPrimaryKey
	 *
	 */
	@Transactional
	public Heartbeat findHeartbeatByPrimaryKey(Integer heartbeatId) throws DataAccessException {

		return findHeartbeatByPrimaryKey(heartbeatId, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByPrimaryKey
	 *
	 */

	@Transactional
	public Heartbeat findHeartbeatByPrimaryKey(Integer heartbeatId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findHeartbeatByPrimaryKey", heartbeatId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findHeartbeatByCreatedBy
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByCreatedBy(String createdBy) throws DataAccessException {

		return findHeartbeatByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByTerminationStatus
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByTerminationStatus(String terminationStatus) throws DataAccessException {

		return findHeartbeatByTerminationStatus(terminationStatus, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByTerminationStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByTerminationStatus(String terminationStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByTerminationStatus", startResult, maxRows, terminationStatus);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByUpdatedByContaining
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByUpdatedByContaining(String updatedBy) throws DataAccessException {

		return findHeartbeatByUpdatedByContaining(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByUpdatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByUpdatedByContaining", startResult, maxRows, updatedBy);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

	/**
	 * JPQL Query - findHeartbeatByTerminationStatusContaining
	 *
	 */
	@Transactional
	public Set<Heartbeat> findHeartbeatByTerminationStatusContaining(String terminationStatus) throws DataAccessException {

		return findHeartbeatByTerminationStatusContaining(terminationStatus, -1, -1);
	}

	/**
	 * JPQL Query - findHeartbeatByTerminationStatusContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Heartbeat> findHeartbeatByTerminationStatusContaining(String terminationStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHeartbeatByTerminationStatusContaining", startResult, maxRows, terminationStatus);
		return new LinkedHashSet<Heartbeat>(query.getResultList());
	}

}

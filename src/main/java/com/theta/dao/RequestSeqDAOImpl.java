package com.theta.dao;

import com.theta.domain.RequestSeq;

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
 * DAO to manage RequestSeq entities.
 * 
 */
@Repository("RequestSeqDAO")
@Transactional
public class RequestSeqDAOImpl extends AbstractJpaDao implements RequestSeqDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { RequestSeq.class }));

	/**
	 * EntityManager injected by Spring for persistence unit LocOracleTheta
	 *
	 */
	@PersistenceContext(unitName = "LocOracleTheta")
	private EntityManager entityManager;

	/**
	 * Instantiates a new RequestSeqDAOImpl
	 *
	 */
	public RequestSeqDAOImpl() {
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
	 * JPQL Query - findRequestSeqByRequestSeqId
	 *
	 */
	@Transactional
	public RequestSeq findRequestSeqByRequestSeqId(Integer requestSeqId) throws DataAccessException {

		return findRequestSeqByRequestSeqId(requestSeqId, -1, -1);
	}

	/**
	 * JPQL Query - findRequestSeqByRequestSeqId
	 *
	 */

	@Transactional
	public RequestSeq findRequestSeqByRequestSeqId(Integer requestSeqId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findRequestSeqByRequestSeqId", requestSeqId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findRequestSeqByUpdatedDateAfter
	 *
	 */
	@Transactional
	public Set<RequestSeq> findRequestSeqByUpdatedDateAfter(java.util.Calendar updatedDate) throws DataAccessException {

		return findRequestSeqByUpdatedDateAfter(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findRequestSeqByUpdatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<RequestSeq> findRequestSeqByUpdatedDateAfter(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findRequestSeqByUpdatedDateAfter", startResult, maxRows, updatedDate);
		return new LinkedHashSet<RequestSeq>(query.getResultList());
	}

	/**
	 * JPQL Query - findRequestSeqByRequestSeqNo
	 *
	 */
	@Transactional
	public Set<RequestSeq> findRequestSeqByRequestSeqNo(Integer requestSeqNo) throws DataAccessException {

		return findRequestSeqByRequestSeqNo(requestSeqNo, -1, -1);
	}

	/**
	 * JPQL Query - findRequestSeqByRequestSeqNo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<RequestSeq> findRequestSeqByRequestSeqNo(Integer requestSeqNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findRequestSeqByRequestSeqNo", startResult, maxRows, requestSeqNo);
		return new LinkedHashSet<RequestSeq>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllRequestSeqs
	 *
	 */
	@Transactional
	public Set<RequestSeq> findAllRequestSeqs() throws DataAccessException {

		return findAllRequestSeqs(-1, -1);
	}

	/**
	 * JPQL Query - findAllRequestSeqs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<RequestSeq> findAllRequestSeqs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllRequestSeqs", startResult, maxRows);
		return new LinkedHashSet<RequestSeq>(query.getResultList());
	}

	/**
	 * JPQL Query - findRequestSeqByUpdatedBy
	 *
	 */
	@Transactional
	public Set<RequestSeq> findRequestSeqByUpdatedBy(String updatedBy) throws DataAccessException {

		return findRequestSeqByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findRequestSeqByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<RequestSeq> findRequestSeqByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findRequestSeqByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<RequestSeq>(query.getResultList());
	}

	/**
	 * JPQL Query - findRequestSeqByPrimaryKey
	 *
	 */
	@Transactional
	public RequestSeq findRequestSeqByPrimaryKey(Integer requestSeqId) throws DataAccessException {

		return findRequestSeqByPrimaryKey(requestSeqId, -1, -1);
	}

	/**
	 * JPQL Query - findRequestSeqByPrimaryKey
	 *
	 */

	@Transactional
	public RequestSeq findRequestSeqByPrimaryKey(Integer requestSeqId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findRequestSeqByPrimaryKey", requestSeqId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findRequestSeqByUpdatedByContaining
	 *
	 */
	@Transactional
	public Set<RequestSeq> findRequestSeqByUpdatedByContaining(String updatedBy) throws DataAccessException {

		return findRequestSeqByUpdatedByContaining(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findRequestSeqByUpdatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<RequestSeq> findRequestSeqByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findRequestSeqByUpdatedByContaining", startResult, maxRows, updatedBy);
		return new LinkedHashSet<RequestSeq>(query.getResultList());
	}

	/**
	 * JPQL Query - findRequestSeqByUpdatedDate
	 *
	 */
	@Transactional
	public Set<RequestSeq> findRequestSeqByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findRequestSeqByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findRequestSeqByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<RequestSeq> findRequestSeqByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findRequestSeqByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<RequestSeq>(query.getResultList());
	}

	/**
	 * JPQL Query - findRequestSeqByUpdatedDateBefore
	 *
	 */
	@Transactional
	public Set<RequestSeq> findRequestSeqByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException {

		return findRequestSeqByUpdatedDateBefore(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findRequestSeqByUpdatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<RequestSeq> findRequestSeqByUpdatedDateBefore(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findRequestSeqByUpdatedDateBefore", startResult, maxRows, updatedDate);
		return new LinkedHashSet<RequestSeq>(query.getResultList());
	}

}

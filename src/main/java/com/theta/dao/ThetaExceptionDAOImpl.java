package com.theta.dao;

import com.theta.domain.ThetaException;

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
 * DAO to manage ThetaException entities.
 * 
 */
@Repository("ThetaExceptionDAO")
@Transactional
public class ThetaExceptionDAOImpl extends AbstractJpaDao implements
		ThetaExceptionDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { ThetaException.class }));

	/**
	 * EntityManager injected by Spring for persistence unit LocOracleTheta
	 *
	 */
	@PersistenceContext(unitName = "LocOracleTheta")
	private EntityManager entityManager;

	/**
	 * Instantiates a new ThetaExceptionDAOImpl
	 *
	 */
	public ThetaExceptionDAOImpl() {
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
	 * JPQL Query - findThetaExceptionByExceptionText
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByExceptionText(String exceptionText) throws DataAccessException {

		return findThetaExceptionByExceptionText(exceptionText, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByExceptionText
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByExceptionText(String exceptionText, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByExceptionText", startResult, maxRows, exceptionText);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByOrderDetailText
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByOrderDetailText(String orderDetailText) throws DataAccessException {

		return findThetaExceptionByOrderDetailText(orderDetailText, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByOrderDetailText
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByOrderDetailText(String orderDetailText, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByOrderDetailText", startResult, maxRows, orderDetailText);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByContractDetailTextContaining
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByContractDetailTextContaining(String contractDetailText) throws DataAccessException {

		return findThetaExceptionByContractDetailTextContaining(contractDetailText, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByContractDetailTextContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByContractDetailTextContaining(String contractDetailText, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByContractDetailTextContaining", startResult, maxRows, contractDetailText);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByUpdatedByContaining
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByUpdatedByContaining(String updatedBy) throws DataAccessException {

		return findThetaExceptionByUpdatedByContaining(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByUpdatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByUpdatedByContaining", startResult, maxRows, updatedBy);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByUpdatedDateBefore
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException {

		return findThetaExceptionByUpdatedDateBefore(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByUpdatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByUpdatedDateBefore(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByUpdatedDateBefore", startResult, maxRows, updatedDate);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByOrderDetailTextContaining
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByOrderDetailTextContaining(String orderDetailText) throws DataAccessException {

		return findThetaExceptionByOrderDetailTextContaining(orderDetailText, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByOrderDetailTextContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByOrderDetailTextContaining(String orderDetailText, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByOrderDetailTextContaining", startResult, maxRows, orderDetailText);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByContractDetailText
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByContractDetailText(String contractDetailText) throws DataAccessException {

		return findThetaExceptionByContractDetailText(contractDetailText, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByContractDetailText
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByContractDetailText(String contractDetailText, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByContractDetailText", startResult, maxRows, contractDetailText);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByUpdatedDate
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findThetaExceptionByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByThetaExceptionId
	 *
	 */
	@Transactional
	public ThetaException findThetaExceptionByThetaExceptionId(Integer thetaExceptionId) throws DataAccessException {

		return findThetaExceptionByThetaExceptionId(thetaExceptionId, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByThetaExceptionId
	 *
	 */

	@Transactional
	public ThetaException findThetaExceptionByThetaExceptionId(Integer thetaExceptionId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findThetaExceptionByThetaExceptionId", thetaExceptionId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findThetaExceptionByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByCreatedByContaining(String createdBy) throws DataAccessException {

		return findThetaExceptionByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByExceptionTextContaining
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByExceptionTextContaining(String exceptionText) throws DataAccessException {

		return findThetaExceptionByExceptionTextContaining(exceptionText, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByExceptionTextContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByExceptionTextContaining(String exceptionText, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByExceptionTextContaining", startResult, maxRows, exceptionText);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByCreatedDateAfter
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException {

		return findThetaExceptionByCreatedDateAfter(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByCreatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByCreatedDateAfter(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByCreatedDateAfter", startResult, maxRows, createdDate);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByUpdatedDateAfter
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByUpdatedDateAfter(java.util.Calendar updatedDate) throws DataAccessException {

		return findThetaExceptionByUpdatedDateAfter(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByUpdatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByUpdatedDateAfter(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByUpdatedDateAfter", startResult, maxRows, updatedDate);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllThetaExceptions
	 *
	 */
	@Transactional
	public Set<ThetaException> findAllThetaExceptions() throws DataAccessException {

		return findAllThetaExceptions(-1, -1);
	}

	/**
	 * JPQL Query - findAllThetaExceptions
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findAllThetaExceptions(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllThetaExceptions", startResult, maxRows);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByPrimaryKey
	 *
	 */
	@Transactional
	public ThetaException findThetaExceptionByPrimaryKey(Integer thetaExceptionId) throws DataAccessException {

		return findThetaExceptionByPrimaryKey(thetaExceptionId, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByPrimaryKey
	 *
	 */

	@Transactional
	public ThetaException findThetaExceptionByPrimaryKey(Integer thetaExceptionId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findThetaExceptionByPrimaryKey", thetaExceptionId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findThetaExceptionByCreatedDate
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findThetaExceptionByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByCreatedDateBefore
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByCreatedDateBefore(java.util.Calendar createdDate) throws DataAccessException {

		return findThetaExceptionByCreatedDateBefore(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByCreatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByCreatedDateBefore(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByCreatedDateBefore", startResult, maxRows, createdDate);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByUpdatedBy
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByUpdatedBy(String updatedBy) throws DataAccessException {

		return findThetaExceptionByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

	/**
	 * JPQL Query - findThetaExceptionByCreatedBy
	 *
	 */
	@Transactional
	public Set<ThetaException> findThetaExceptionByCreatedBy(String createdBy) throws DataAccessException {

		return findThetaExceptionByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findThetaExceptionByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ThetaException> findThetaExceptionByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findThetaExceptionByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<ThetaException>(query.getResultList());
	}

}

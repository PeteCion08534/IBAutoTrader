package com.theta.dao;

import com.theta.domain.Snapshot;

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
 * DAO to manage Snapshot entities.
 * 
 */
@Repository("SnapshotDAO")
@Transactional
public class SnapshotDAOImpl extends AbstractJpaDao implements SnapshotDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Snapshot.class }));

	/**
	 * EntityManager injected by Spring for persistence unit LocOracleTheta
	 *
	 */
	@PersistenceContext(unitName = "LocOracleTheta")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SnapshotDAOImpl
	 *
	 */
	public SnapshotDAOImpl() {
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
	 * JPQL Query - findSnapshotByExpirtyDay
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotByExpirtyDay(Integer expirtyDay) throws DataAccessException {

		return findSnapshotByExpirtyDay(expirtyDay, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByExpirtyDay
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotByExpirtyDay(Integer expirtyDay, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotByExpirtyDay", startResult, maxRows, expirtyDay);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotBySnapshotDate
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotBySnapshotDate(java.util.Calendar snapshotDate) throws DataAccessException {

		return findSnapshotBySnapshotDate(snapshotDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotBySnapshotDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotBySnapshotDate(java.util.Calendar snapshotDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotBySnapshotDate", startResult, maxRows, snapshotDate);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotBySnapshotDateBefore
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotBySnapshotDateBefore(java.util.Calendar snapshotDate) throws DataAccessException {

		return findSnapshotBySnapshotDateBefore(snapshotDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotBySnapshotDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotBySnapshotDateBefore(java.util.Calendar snapshotDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotBySnapshotDateBefore", startResult, maxRows, snapshotDate);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotByCreatedByContaining(String createdBy) throws DataAccessException {

		return findSnapshotByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotByUpdatedDate
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findSnapshotByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotByCreatedDateBefore
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotByCreatedDateBefore(java.util.Calendar createdDate) throws DataAccessException {

		return findSnapshotByCreatedDateBefore(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByCreatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotByCreatedDateBefore(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotByCreatedDateBefore", startResult, maxRows, createdDate);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotByPrimaryKey
	 *
	 */
	@Transactional
	public Snapshot findSnapshotByPrimaryKey(Integer snapshotId) throws DataAccessException {

		return findSnapshotByPrimaryKey(snapshotId, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByPrimaryKey
	 *
	 */

	@Transactional
	public Snapshot findSnapshotByPrimaryKey(Integer snapshotId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findSnapshotByPrimaryKey", snapshotId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSnapshotByExpiryYear
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotByExpiryYear(Integer expiryYear) throws DataAccessException {

		return findSnapshotByExpiryYear(expiryYear, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByExpiryYear
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotByExpiryYear(Integer expiryYear, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotByExpiryYear", startResult, maxRows, expiryYear);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotBySymbolContaining
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotBySymbolContaining(String symbol) throws DataAccessException {

		return findSnapshotBySymbolContaining(symbol, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotBySymbolContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotBySymbolContaining(String symbol, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotBySymbolContaining", startResult, maxRows, symbol);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotByUpdatedByContaining
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotByUpdatedByContaining(String updatedBy) throws DataAccessException {

		return findSnapshotByUpdatedByContaining(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByUpdatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotByUpdatedByContaining", startResult, maxRows, updatedBy);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotBySnapshotDateAfter
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotBySnapshotDateAfter(java.util.Calendar snapshotDate) throws DataAccessException {

		return findSnapshotBySnapshotDateAfter(snapshotDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotBySnapshotDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotBySnapshotDateAfter(java.util.Calendar snapshotDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotBySnapshotDateAfter", startResult, maxRows, snapshotDate);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotByCreatedDateAfter
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException {

		return findSnapshotByCreatedDateAfter(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByCreatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotByCreatedDateAfter(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotByCreatedDateAfter", startResult, maxRows, createdDate);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotBySnapshotId
	 *
	 */
	@Transactional
	public Snapshot findSnapshotBySnapshotId(Integer snapshotId) throws DataAccessException {

		return findSnapshotBySnapshotId(snapshotId, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotBySnapshotId
	 *
	 */

	@Transactional
	public Snapshot findSnapshotBySnapshotId(Integer snapshotId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findSnapshotBySnapshotId", snapshotId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSnapshotByCreatedDate
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findSnapshotByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotByUpdatedDateBefore
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException {

		return findSnapshotByUpdatedDateBefore(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByUpdatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotByUpdatedDateBefore(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotByUpdatedDateBefore", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSnapshots
	 *
	 */
	@Transactional
	public Set<Snapshot> findAllSnapshots() throws DataAccessException {

		return findAllSnapshots(-1, -1);
	}

	/**
	 * JPQL Query - findAllSnapshots
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findAllSnapshots(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSnapshots", startResult, maxRows);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotByCreatedBy
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotByCreatedBy(String createdBy) throws DataAccessException {

		return findSnapshotByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotBySymbolPrice
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotBySymbolPrice(java.math.BigDecimal symbolPrice) throws DataAccessException {

		return findSnapshotBySymbolPrice(symbolPrice, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotBySymbolPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotBySymbolPrice(java.math.BigDecimal symbolPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotBySymbolPrice", startResult, maxRows, symbolPrice);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotByUpdatedBy
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotByUpdatedBy(String updatedBy) throws DataAccessException {

		return findSnapshotByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotByExpiryMonth
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotByExpiryMonth(Integer expiryMonth) throws DataAccessException {

		return findSnapshotByExpiryMonth(expiryMonth, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByExpiryMonth
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotByExpiryMonth(Integer expiryMonth, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotByExpiryMonth", startResult, maxRows, expiryMonth);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotByUpdatedDateAfter
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotByUpdatedDateAfter(java.util.Calendar updatedDate) throws DataAccessException {

		return findSnapshotByUpdatedDateAfter(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotByUpdatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotByUpdatedDateAfter(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotByUpdatedDateAfter", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotBySymbol
	 *
	 */
	@Transactional
	public Set<Snapshot> findSnapshotBySymbol(String symbol) throws DataAccessException {

		return findSnapshotBySymbol(symbol, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotBySymbol
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snapshot> findSnapshotBySymbol(String symbol, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotBySymbol", startResult, maxRows, symbol);
		return new LinkedHashSet<Snapshot>(query.getResultList());
	}

}

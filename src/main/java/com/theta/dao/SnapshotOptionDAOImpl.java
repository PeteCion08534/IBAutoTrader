package com.theta.dao;

import com.theta.domain.SnapshotOption;

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
 * DAO to manage SnapshotOption entities.
 * 
 */
@Repository("SnapshotOptionDAO")
@Transactional
public class SnapshotOptionDAOImpl extends AbstractJpaDao implements
		SnapshotOptionDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SnapshotOption.class }));

	/**
	 * EntityManager injected by Spring for persistence unit LocOracleTheta
	 *
	 */
	@PersistenceContext(unitName = "LocOracleTheta")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SnapshotOptionDAOImpl
	 *
	 */
	public SnapshotOptionDAOImpl() {
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
	 * JPQL Query - findSnapshotOptionByOptRight
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByOptRight(String optRight) throws DataAccessException {

		return findSnapshotOptionByOptRight(optRight, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByOptRight
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByOptRight(String optRight, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByOptRight", startResult, maxRows, optRight);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeOpenTodayBefore
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeOpenTodayBefore(java.util.Calendar tradingTimeOpenToday) throws DataAccessException {

		return findSnapshotOptionByTradingTimeOpenTodayBefore(tradingTimeOpenToday, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeOpenTodayBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeOpenTodayBefore(java.util.Calendar tradingTimeOpenToday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByTradingTimeOpenTodayBefore", startResult, maxRows, tradingTimeOpenToday);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByCreatedBy
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByCreatedBy(String createdBy) throws DataAccessException {

		return findSnapshotOptionByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByOptRightContaining
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByOptRightContaining(String optRight) throws DataAccessException {

		return findSnapshotOptionByOptRightContaining(optRight, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByOptRightContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByOptRightContaining(String optRight, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByOptRightContaining", startResult, maxRows, optRight);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSnapshotOptions
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findAllSnapshotOptions() throws DataAccessException {

		return findAllSnapshotOptions(-1, -1);
	}

	/**
	 * JPQL Query - findAllSnapshotOptions
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findAllSnapshotOptions(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSnapshotOptions", startResult, maxRows);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedDate
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findSnapshotOptionByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeCloseToday
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeCloseToday(java.util.Calendar tradingTimeCloseToday) throws DataAccessException {

		return findSnapshotOptionByTradingTimeCloseToday(tradingTimeCloseToday, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeCloseToday
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeCloseToday(java.util.Calendar tradingTimeCloseToday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByTradingTimeCloseToday", startResult, maxRows, tradingTimeCloseToday);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByStrike
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByStrike(java.math.BigDecimal strike) throws DataAccessException {

		return findSnapshotOptionByStrike(strike, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByStrike
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByStrike(java.math.BigDecimal strike, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByStrike", startResult, maxRows, strike);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByBidPrice
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByBidPrice(java.math.BigDecimal bidPrice) throws DataAccessException {

		return findSnapshotOptionByBidPrice(bidPrice, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByBidPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByBidPrice(java.math.BigDecimal bidPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByBidPrice", startResult, maxRows, bidPrice);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByCreatedDateAfter
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException {

		return findSnapshotOptionByCreatedDateAfter(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByCreatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByCreatedDateAfter(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByCreatedDateAfter", startResult, maxRows, createdDate);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByLocalSymbol
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByLocalSymbol(String localSymbol) throws DataAccessException {

		return findSnapshotOptionByLocalSymbol(localSymbol, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByLocalSymbol
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByLocalSymbol(String localSymbol, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByLocalSymbol", startResult, maxRows, localSymbol);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByAskPrice
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByAskPrice(java.math.BigDecimal askPrice) throws DataAccessException {

		return findSnapshotOptionByAskPrice(askPrice, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByAskPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByAskPrice(java.math.BigDecimal askPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByAskPrice", startResult, maxRows, askPrice);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByLocalSymbolContaining
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByLocalSymbolContaining(String localSymbol) throws DataAccessException {

		return findSnapshotOptionByLocalSymbolContaining(localSymbol, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByLocalSymbolContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByLocalSymbolContaining(String localSymbol, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByLocalSymbolContaining", startResult, maxRows, localSymbol);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedDateAfter
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByUpdatedDateAfter(java.util.Calendar updatedDate) throws DataAccessException {

		return findSnapshotOptionByUpdatedDateAfter(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByUpdatedDateAfter(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByUpdatedDateAfter", startResult, maxRows, updatedDate);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeOpenToday
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeOpenToday(java.util.Calendar tradingTimeOpenToday) throws DataAccessException {

		return findSnapshotOptionByTradingTimeOpenToday(tradingTimeOpenToday, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeOpenToday
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeOpenToday(java.util.Calendar tradingTimeOpenToday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByTradingTimeOpenToday", startResult, maxRows, tradingTimeOpenToday);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedBy
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByUpdatedBy(String updatedBy) throws DataAccessException {

		return findSnapshotOptionByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeCloseTodayBefore
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeCloseTodayBefore(java.util.Calendar tradingTimeCloseToday) throws DataAccessException {

		return findSnapshotOptionByTradingTimeCloseTodayBefore(tradingTimeCloseToday, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeCloseTodayBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeCloseTodayBefore(java.util.Calendar tradingTimeCloseToday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByTradingTimeCloseTodayBefore", startResult, maxRows, tradingTimeCloseToday);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByCreatedDate
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findSnapshotOptionByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeOpenTodayAfter
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeOpenTodayAfter(java.util.Calendar tradingTimeOpenToday) throws DataAccessException {

		return findSnapshotOptionByTradingTimeOpenTodayAfter(tradingTimeOpenToday, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeOpenTodayAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeOpenTodayAfter(java.util.Calendar tradingTimeOpenToday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByTradingTimeOpenTodayAfter", startResult, maxRows, tradingTimeOpenToday);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByPrimaryKey
	 *
	 */
	@Transactional
	public SnapshotOption findSnapshotOptionByPrimaryKey(Integer snapshotOptionId) throws DataAccessException {

		return findSnapshotOptionByPrimaryKey(snapshotOptionId, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByPrimaryKey
	 *
	 */

	@Transactional
	public SnapshotOption findSnapshotOptionByPrimaryKey(Integer snapshotOptionId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findSnapshotOptionByPrimaryKey", snapshotOptionId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeCloseTodayAfter
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeCloseTodayAfter(java.util.Calendar tradingTimeCloseToday) throws DataAccessException {

		return findSnapshotOptionByTradingTimeCloseTodayAfter(tradingTimeCloseToday, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeCloseTodayAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeCloseTodayAfter(java.util.Calendar tradingTimeCloseToday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByTradingTimeCloseTodayAfter", startResult, maxRows, tradingTimeCloseToday);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByCreatedDateBefore
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByCreatedDateBefore(java.util.Calendar createdDate) throws DataAccessException {

		return findSnapshotOptionByCreatedDateBefore(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByCreatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByCreatedDateBefore(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByCreatedDateBefore", startResult, maxRows, createdDate);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByLastPrice
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByLastPrice(java.math.BigDecimal lastPrice) throws DataAccessException {

		return findSnapshotOptionByLastPrice(lastPrice, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByLastPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByLastPrice(java.math.BigDecimal lastPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByLastPrice", startResult, maxRows, lastPrice);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByCreatedByContaining(String createdBy) throws DataAccessException {

		return findSnapshotOptionByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedByContaining
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByUpdatedByContaining(String updatedBy) throws DataAccessException {

		return findSnapshotOptionByUpdatedByContaining(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByUpdatedByContaining", startResult, maxRows, updatedBy);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnapshotOptionBySnapshotOptionId
	 *
	 */
	@Transactional
	public SnapshotOption findSnapshotOptionBySnapshotOptionId(Integer snapshotOptionId) throws DataAccessException {

		return findSnapshotOptionBySnapshotOptionId(snapshotOptionId, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionBySnapshotOptionId
	 *
	 */

	@Transactional
	public SnapshotOption findSnapshotOptionBySnapshotOptionId(Integer snapshotOptionId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findSnapshotOptionBySnapshotOptionId", snapshotOptionId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedDateBefore
	 *
	 */
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException {

		return findSnapshotOptionByUpdatedDateBefore(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnapshotOption> findSnapshotOptionByUpdatedDateBefore(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnapshotOptionByUpdatedDateBefore", startResult, maxRows, updatedDate);
		return new LinkedHashSet<SnapshotOption>(query.getResultList());
	}

}

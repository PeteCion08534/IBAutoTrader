package com.theta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.theta.db.ThetaSQLIF;
import com.theta.domain.Position;
import com.theta.domain.ProfitLoss;

/**
 * DAO to manage Position entities.
 * 
 */
@Repository("PositionDAO")
@Transactional
public class PositionDAOImpl extends AbstractJpaDao implements PositionDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Position.class }));

	/**
	 * EntityManager injected by Spring for persistence unit LocOracleTheta
	 *
	 */
	@PersistenceContext(unitName = "LocOracleTheta")
	private EntityManager entityManager;

	/**
	 * Instantiates a new PositionDAOImpl
	 *
	 */
	public PositionDAOImpl() {
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
	 * JPQL Query - findPositionByExpiryMonth
	 *
	 */
	@Transactional
	public Set<Position> findPositionByExpiryMonth(Integer expiryMonth) throws DataAccessException {

		return findPositionByExpiryMonth(expiryMonth, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByExpiryMonth
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByExpiryMonth(Integer expiryMonth, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByExpiryMonth", startResult, maxRows, expiryMonth);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByReentrySecPricePutBelow
	 *
	 */
	@Transactional
	public Set<Position> findPositionByReentrySecPricePutBelow(java.math.BigDecimal reentrySecPricePutBelow) throws DataAccessException {

		return findPositionByReentrySecPricePutBelow(reentrySecPricePutBelow, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByReentrySecPricePutBelow
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByReentrySecPricePutBelow(java.math.BigDecimal reentrySecPricePutBelow, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByReentrySecPricePutBelow", startResult, maxRows, reentrySecPricePutBelow);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByStrategyNameContaining
	 *
	 */
	@Transactional
	public Set<Position> findPositionByStrategyNameContaining(String strategyName) throws DataAccessException {

		return findPositionByStrategyNameContaining(strategyName, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByStrategyNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByStrategyNameContaining(String strategyName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByStrategyNameContaining", startResult, maxRows, strategyName);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByUpdatedByContaining
	 *
	 */
	@Transactional
	public Set<Position> findPositionByUpdatedByContaining(String updatedBy) throws DataAccessException {

		return findPositionByUpdatedByContaining(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByUpdatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByUpdatedByContaining", startResult, maxRows, updatedBy);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllPositions
	 *
	 */
	@Transactional
	public Set<Position> findAllPositions() throws DataAccessException {

		return findAllPositions(-1, -1);
	}

	/**
	 * JPQL Query - findAllPositions
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findAllPositions(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllPositions", startResult, maxRows);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByCreatedBy
	 *
	 */
	@Transactional
	public Set<Position> findPositionByCreatedBy(String createdBy) throws DataAccessException {

		return findPositionByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByExpiryDay
	 *
	 */
	@Transactional
	public Set<Position> findPositionByExpiryDay(Integer expiryDay) throws DataAccessException {

		return findPositionByExpiryDay(expiryDay, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByExpiryDay
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByExpiryDay(Integer expiryDay, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByExpiryDay", startResult, maxRows, expiryDay);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByNumOpenSpreads
	 *
	 */
	@Transactional
	public Set<Position> findPositionByNumOpenSpreads(Integer numOpenSpreads) throws DataAccessException {

		return findPositionByNumOpenSpreads(numOpenSpreads, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByNumOpenSpreads
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByNumOpenSpreads(Integer numOpenSpreads, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByNumOpenSpreads", startResult, maxRows, numOpenSpreads);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByUpdatedDate
	 *
	 */
	@Transactional
	public Set<Position> findPositionByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findPositionByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<Position> findPositionByCreatedByContaining(String createdBy) throws DataAccessException {

		return findPositionByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByTotalRisked
	 *
	 */
	@Transactional
	public Set<Position> findPositionByTotalRisked(java.math.BigDecimal totalRisked) throws DataAccessException {

		return findPositionByTotalRisked(totalRisked, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByTotalRisked
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByTotalRisked(java.math.BigDecimal totalRisked, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByTotalRisked", startResult, maxRows, totalRisked);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByLastExitSecurityPrice
	 *
	 */
	@Transactional
	public Set<Position> findPositionByLastExitSecurityPrice(java.math.BigDecimal lastExitSecurityPrice) throws DataAccessException {

		return findPositionByLastExitSecurityPrice(lastExitSecurityPrice, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByLastExitSecurityPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByLastExitSecurityPrice(java.math.BigDecimal lastExitSecurityPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByLastExitSecurityPrice", startResult, maxRows, lastExitSecurityPrice);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByStrategyName
	 *
	 */
	@Transactional
	public Set<Position> findPositionByStrategyName(String strategyName) throws DataAccessException {

		return findPositionByStrategyName(strategyName, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByStrategyName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByStrategyName(String strategyName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByStrategyName", startResult, maxRows, strategyName);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByProfitLossUnrealized
	 *
	 */
	@Transactional
	public Set<Position> findPositionByProfitLossUnrealized(java.math.BigDecimal profitLossUnrealized) throws DataAccessException {

		return findPositionByProfitLossUnrealized(profitLossUnrealized, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByProfitLossUnrealized
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByProfitLossUnrealized(java.math.BigDecimal profitLossUnrealized, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByProfitLossUnrealized", startResult, maxRows, profitLossUnrealized);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByPositionId
	 *
	 */
	@Transactional
	public Position findPositionByPositionId(Integer positionId) throws DataAccessException {

		return findPositionByPositionId(positionId, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByPositionId
	 *
	 */

	@Transactional
	public Position findPositionByPositionId(Integer positionId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findPositionByPositionId", positionId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findPositionByUpdatedBy
	 *
	 */
	@Transactional
	public Set<Position> findPositionByUpdatedBy(String updatedBy) throws DataAccessException {

		return findPositionByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByCreatedDateAfter
	 *
	 */
	@Transactional
	public Set<Position> findPositionByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException {

		return findPositionByCreatedDateAfter(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByCreatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByCreatedDateAfter(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByCreatedDateAfter", startResult, maxRows, createdDate);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByExpiryYear
	 *
	 */
	@Transactional
	public Set<Position> findPositionByExpiryYear(Integer expiryYear) throws DataAccessException {

		return findPositionByExpiryYear(expiryYear, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByExpiryYear
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByExpiryYear(Integer expiryYear, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByExpiryYear", startResult, maxRows, expiryYear);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionBySymbolContaining
	 *
	 */
	@Transactional
	public Set<Position> findPositionBySymbolContaining(String symbol) throws DataAccessException {

		return findPositionBySymbolContaining(symbol, -1, -1);
	}

	/**
	 * JPQL Query - findPositionBySymbolContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionBySymbolContaining(String symbol, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionBySymbolContaining", startResult, maxRows, symbol);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByOptRight
	 *
	 */
	@Transactional
	public Set<Position> findPositionByOptRight(String optRight) throws DataAccessException {

		return findPositionByOptRight(optRight, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByOptRight
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByOptRight(String optRight, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByOptRight", startResult, maxRows, optRight);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByGoalNumSpreads
	 *
	 */
	@Transactional
	public Set<Position> findPositionByGoalNumSpreads(Integer goalNumSpreads) throws DataAccessException {

		return findPositionByGoalNumSpreads(goalNumSpreads, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByGoalNumSpreads
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByGoalNumSpreads(Integer goalNumSpreads, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByGoalNumSpreads", startResult, maxRows, goalNumSpreads);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByPrimaryKey
	 *
	 */
	@Transactional
	public Position findPositionByPrimaryKey(Integer positionId) throws DataAccessException {

		return findPositionByPrimaryKey(positionId, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByPrimaryKey
	 *
	 */

	@Transactional
	public Position findPositionByPrimaryKey(Integer positionId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findPositionByPrimaryKey", positionId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findPositionBySymbol
	 *
	 */
	@Transactional
	public Set<Position> findPositionBySymbol(String symbol) throws DataAccessException {

		return findPositionBySymbol(symbol, -1, -1);
	}

	/**
	 * JPQL Query - findPositionBySymbol
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionBySymbol(String symbol, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionBySymbol", startResult, maxRows, symbol);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByOptRightContaining
	 *
	 */
	@Transactional
	public Set<Position> findPositionByOptRightContaining(String optRight) throws DataAccessException {

		return findPositionByOptRightContaining(optRight, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByOptRightContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByOptRightContaining(String optRight, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByOptRightContaining", startResult, maxRows, optRight);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByCreatedDate
	 *
	 */
	@Transactional
	public Set<Position> findPositionByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findPositionByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByNumWins
	 *
	 */
	@Transactional
	public Set<Position> findPositionByNumWins(Integer numWins) throws DataAccessException {

		return findPositionByNumWins(numWins, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByNumWins
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByNumWins(Integer numWins, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByNumWins", startResult, maxRows, numWins);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByUpdatedDateAfter
	 *
	 */
	@Transactional
	public Set<Position> findPositionByUpdatedDateAfter(java.util.Calendar updatedDate) throws DataAccessException {

		return findPositionByUpdatedDateAfter(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByUpdatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByUpdatedDateAfter(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByUpdatedDateAfter", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByReentrySecPriceCallAbove
	 *
	 */
	@Transactional
	public Set<Position> findPositionByReentrySecPriceCallAbove(java.math.BigDecimal reentrySecPriceCallAbove) throws DataAccessException {

		return findPositionByReentrySecPriceCallAbove(reentrySecPriceCallAbove, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByReentrySecPriceCallAbove
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByReentrySecPriceCallAbove(java.math.BigDecimal reentrySecPriceCallAbove, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByReentrySecPriceCallAbove", startResult, maxRows, reentrySecPriceCallAbove);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByProfitLossRealized
	 *
	 */
	@Transactional
	public Set<Position> findPositionByProfitLossRealized(java.math.BigDecimal profitLossRealized) throws DataAccessException {

		return findPositionByProfitLossRealized(profitLossRealized, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByProfitLossRealized
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByProfitLossRealized(java.math.BigDecimal profitLossRealized, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByProfitLossRealized", startResult, maxRows, profitLossRealized);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByCreatedDateBefore
	 *
	 */
	@Transactional
	public Set<Position> findPositionByCreatedDateBefore(java.util.Calendar createdDate) throws DataAccessException {

		return findPositionByCreatedDateBefore(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByCreatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByCreatedDateBefore(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByCreatedDateBefore", startResult, maxRows, createdDate);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByNumLosses
	 *
	 */
	@Transactional
	public Set<Position> findPositionByNumLosses(Integer numLosses) throws DataAccessException {

		return findPositionByNumLosses(numLosses, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByNumLosses
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByNumLosses(Integer numLosses, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByNumLosses", startResult, maxRows, numLosses);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByUpdatedDateBefore
	 *
	 */
	@Transactional
	public Set<Position> findPositionByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException {

		return findPositionByUpdatedDateBefore(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByUpdatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByUpdatedDateBefore(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByUpdatedDateBefore", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByExpiryTimeframe
	 *
	 */
	@Transactional
	public Set<Position> findPositionByExpiryTimeframe(String expiryTimeframe) throws DataAccessException {

		return findPositionByExpiryTimeframe(expiryTimeframe, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByExpiryTimeframe
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByExpiryTimeframe(String expiryTimeframe, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByExpiryTimeframe", startResult, maxRows, expiryTimeframe);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/**
	 * JPQL Query - findPositionByExpiryTimeframeContaining
	 *
	 */
	@Transactional
	public Set<Position> findPositionByExpiryTimeframeContaining(String expiryTimeframe) throws DataAccessException {

		return findPositionByExpiryTimeframeContaining(expiryTimeframe, -1, -1);
	}

	/**
	 * JPQL Query - findPositionByExpiryTimeframeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionByExpiryTimeframeContaining(String expiryTimeframe, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPositionByExpiryTimeframeContaining", startResult, maxRows, expiryTimeframe);
		return new LinkedHashSet<Position>(query.getResultList());
	}

	/*****************************************************************************************************
	 * USER-DEFINED Methods
	 */
	

	@Transactional
	public Set<Position> findPositionsNonExpiredByStrategyAcctId(java.util.Calendar today, Integer strategyAcctId) throws DataAccessException {
		return findPositionsNonExpiredByStrategyAcctId(today, strategyAcctId, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionsNonExpiredByStrategyAcctId(java.util.Calendar today, Integer strategyAcctId, int startResult, int maxRows) throws DataAccessException {
		
		Query query = createNamedQuery("findPositionsNonExpiredByStrategyAcctId", startResult, maxRows, today, (int) strategyAcctId);
		//Query query = createNamedQuery("findPositionsNonExpiredByStrategyAcctId", startResult, maxRows, today);
		return new LinkedHashSet<Position>(query.getResultList());
	}	
	
	@Transactional
	public Set<Position> findPositionsByStrategyAcctId(Integer strategyAcctId) throws DataAccessException {
		return findPositionsByStrategyAcctId(strategyAcctId, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Position> findPositionsByStrategyAcctId(Integer strategyAcctId, int startResult, int maxRows) throws DataAccessException {
		
		Query query = createNamedQuery("findPositionsByStrategyAcctId", startResult, maxRows, (int) strategyAcctId);
		//Query query = createNamedQuery("findPositionsNonExpiredByStrategyAcctId", startResult, maxRows, today);
		return new LinkedHashSet<Position>(query.getResultList());
	}	

	
	@Transactional
	public ProfitLoss findStatsForExpiredPositions(Integer strategyAccountId) throws DataAccessException {
		
		ProfitLoss pl = new ProfitLoss();
		
		ResultSet rs = null;
		//Integer minPrice = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(ThetaSQLIF.DB_URL, ThetaSQLIF.DB_USERNAME, ThetaSQLIF.DB_PASSWORD);

			String theSql = "select sum(profit_loss_unrealized) as sum_unrealized, " +
					"sum(profit_loss_realized) as sum_realized, " +
					"sum(num_wins) as sum_wins, " +
					"sum(num_losses) as sum_losses " +
					"from position where expiry_date < sysdate and strategy_account_id = " + strategyAccountId ;
			
			System.out.println("Hre is the theSql: " + theSql);
			preparedStatement = connection.prepareStatement(theSql);
			//statement.setString(3,prod_id);
			rs = preparedStatement.executeQuery();
			if (rs!=null) {
				while (rs.next()) {

					Integer i = rs.getInt("sum_unrealized");
					System.out.println("Here is sum_unrealized: " + i);
					
					pl.setPlRealized(rs.getInt("sum_unrealized"));
					pl.setPlUnrealized(rs.getInt("sum_realized"));
					pl.setNumWins(rs.getInt("sum_wins"));
					pl.setNumLosses(rs.getInt("sum_losses"));
				}
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();
			}
		}catch(SQLException e){
			System.out.println("in first catch.");
			e.printStackTrace();
			return null;
		}
		finally{
			try{
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();

			}catch(Exception e){
				System.out.println("in 2nd catch.");
				e.printStackTrace();
				return null;
			}
		}
		return pl;
	}

	public synchronized void synchronizedFlush() {
		this.flush();
	}
	
	public synchronized void synchronizedStoreAndFlush(Position position) {
		this.store(position);
		this.flush();
	}
	
}

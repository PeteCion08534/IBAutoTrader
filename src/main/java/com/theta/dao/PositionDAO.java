package com.theta.dao;

import com.theta.domain.Position;
import com.theta.domain.ProfitLoss;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Query;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Position entities.
 * 
 */
public interface PositionDAO extends JpaDao {

	/**
	 * JPQL Query - findPositionByExpiryMonth
	 *
	 */
	public Set<Position> findPositionByExpiryMonth(Integer expiryMonth) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByExpiryMonth
	 *
	 */
	public Set<Position> findPositionByExpiryMonth(Integer expiryMonth, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByReentrySecPricePutBelow
	 *
	 */
	public Set<Position> findPositionByReentrySecPricePutBelow(java.math.BigDecimal reentrySecPricePutBelow) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByReentrySecPricePutBelow
	 *
	 */
	public Set<Position> findPositionByReentrySecPricePutBelow(BigDecimal reentrySecPricePutBelow, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByStrategyNameContaining
	 *
	 */
	public Set<Position> findPositionByStrategyNameContaining(String strategyName) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByStrategyNameContaining
	 *
	 */
	public Set<Position> findPositionByStrategyNameContaining(String strategyName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByUpdatedByContaining
	 *
	 */
	public Set<Position> findPositionByUpdatedByContaining(String updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByUpdatedByContaining
	 *
	 */
	public Set<Position> findPositionByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllPositions
	 *
	 */
	public Set<Position> findAllPositions() throws DataAccessException;

	/**
	 * JPQL Query - findAllPositions
	 *
	 */
	public Set<Position> findAllPositions(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByCreatedBy
	 *
	 */
	public Set<Position> findPositionByCreatedBy(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByCreatedBy
	 *
	 */
	public Set<Position> findPositionByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByExpiryDay
	 *
	 */
	public Set<Position> findPositionByExpiryDay(Integer expiryDay) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByExpiryDay
	 *
	 */
	public Set<Position> findPositionByExpiryDay(Integer expiryDay, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByNumOpenSpreads
	 *
	 */
	public Set<Position> findPositionByNumOpenSpreads(Integer numOpenSpreads) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByNumOpenSpreads
	 *
	 */
	public Set<Position> findPositionByNumOpenSpreads(Integer numOpenSpreads, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByUpdatedDate
	 *
	 */
	public Set<Position> findPositionByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByUpdatedDate
	 *
	 */
	public Set<Position> findPositionByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByCreatedByContaining
	 *
	 */
	public Set<Position> findPositionByCreatedByContaining(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByCreatedByContaining
	 *
	 */
	public Set<Position> findPositionByCreatedByContaining(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByTotalRisked
	 *
	 */
	public Set<Position> findPositionByTotalRisked(java.math.BigDecimal totalRisked) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByTotalRisked
	 *
	 */
	public Set<Position> findPositionByTotalRisked(BigDecimal totalRisked, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByLastExitSecurityPrice
	 *
	 */
	public Set<Position> findPositionByLastExitSecurityPrice(java.math.BigDecimal lastExitSecurityPrice) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByLastExitSecurityPrice
	 *
	 */
	public Set<Position> findPositionByLastExitSecurityPrice(BigDecimal lastExitSecurityPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByStrategyName
	 *
	 */
	public Set<Position> findPositionByStrategyName(String strategyName_1) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByStrategyName
	 *
	 */
	public Set<Position> findPositionByStrategyName(String strategyName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByProfitLossUnrealized
	 *
	 */
	public Set<Position> findPositionByProfitLossUnrealized(java.math.BigDecimal profitLossUnrealized) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByProfitLossUnrealized
	 *
	 */
	public Set<Position> findPositionByProfitLossUnrealized(BigDecimal profitLossUnrealized, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByPositionId
	 *
	 */
	public Position findPositionByPositionId(Integer positionId) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByPositionId
	 *
	 */
	public Position findPositionByPositionId(Integer positionId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByUpdatedBy
	 *
	 */
	public Set<Position> findPositionByUpdatedBy(String updatedBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByUpdatedBy
	 *
	 */
	public Set<Position> findPositionByUpdatedBy(String updatedBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByCreatedDateAfter
	 *
	 */
	public Set<Position> findPositionByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByCreatedDateAfter
	 *
	 */
	public Set<Position> findPositionByCreatedDateAfter(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByExpiryYear
	 *
	 */
	public Set<Position> findPositionByExpiryYear(Integer expiryYear) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByExpiryYear
	 *
	 */
	public Set<Position> findPositionByExpiryYear(Integer expiryYear, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionBySymbolContaining
	 *
	 */
	public Set<Position> findPositionBySymbolContaining(String symbol) throws DataAccessException;

	/**
	 * JPQL Query - findPositionBySymbolContaining
	 *
	 */
	public Set<Position> findPositionBySymbolContaining(String symbol, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByOptRight
	 *
	 */
	public Set<Position> findPositionByOptRight(String optRight) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByOptRight
	 *
	 */
	public Set<Position> findPositionByOptRight(String optRight, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByGoalNumSpreads
	 *
	 */
	public Set<Position> findPositionByGoalNumSpreads(Integer goalNumSpreads) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByGoalNumSpreads
	 *
	 */
	public Set<Position> findPositionByGoalNumSpreads(Integer goalNumSpreads, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByPrimaryKey
	 *
	 */
	public Position findPositionByPrimaryKey(Integer positionId_1) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByPrimaryKey
	 *
	 */
	public Position findPositionByPrimaryKey(Integer positionId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionBySymbol
	 *
	 */
	public Set<Position> findPositionBySymbol(String symbol_1) throws DataAccessException;

	/**
	 * JPQL Query - findPositionBySymbol
	 *
	 */
	public Set<Position> findPositionBySymbol(String symbol_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByOptRightContaining
	 *
	 */
	public Set<Position> findPositionByOptRightContaining(String optRight_1) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByOptRightContaining
	 *
	 */
	public Set<Position> findPositionByOptRightContaining(String optRight_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByCreatedDate
	 *
	 */
	public Set<Position> findPositionByCreatedDate(java.util.Calendar createdDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByCreatedDate
	 *
	 */
	public Set<Position> findPositionByCreatedDate(Calendar createdDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByNumWins
	 *
	 */
	public Set<Position> findPositionByNumWins(Integer numWins) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByNumWins
	 *
	 */
	public Set<Position> findPositionByNumWins(Integer numWins, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByUpdatedDateAfter
	 *
	 */
	public Set<Position> findPositionByUpdatedDateAfter(java.util.Calendar updatedDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByUpdatedDateAfter
	 *
	 */
	public Set<Position> findPositionByUpdatedDateAfter(Calendar updatedDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByReentrySecPriceCallAbove
	 *
	 */
	public Set<Position> findPositionByReentrySecPriceCallAbove(java.math.BigDecimal reentrySecPriceCallAbove) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByReentrySecPriceCallAbove
	 *
	 */
	public Set<Position> findPositionByReentrySecPriceCallAbove(BigDecimal reentrySecPriceCallAbove, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByProfitLossRealized
	 *
	 */
	public Set<Position> findPositionByProfitLossRealized(java.math.BigDecimal profitLossRealized) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByProfitLossRealized
	 *
	 */
	public Set<Position> findPositionByProfitLossRealized(BigDecimal profitLossRealized, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByCreatedDateBefore
	 *
	 */
	public Set<Position> findPositionByCreatedDateBefore(java.util.Calendar createdDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByCreatedDateBefore
	 *
	 */
	public Set<Position> findPositionByCreatedDateBefore(Calendar createdDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByNumLosses
	 *
	 */
	public Set<Position> findPositionByNumLosses(Integer numLosses) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByNumLosses
	 *
	 */
	public Set<Position> findPositionByNumLosses(Integer numLosses, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByUpdatedDateBefore
	 *
	 */
	public Set<Position> findPositionByUpdatedDateBefore(java.util.Calendar updatedDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByUpdatedDateBefore
	 *
	 */
	public Set<Position> findPositionByUpdatedDateBefore(Calendar updatedDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByExpiryTimeframe
	 *
	 */
	public Set<Position> findPositionByExpiryTimeframe(String expiryTimeframe) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByExpiryTimeframe
	 *
	 */
	public Set<Position> findPositionByExpiryTimeframe(String expiryTimeframe, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByExpiryTimeframeContaining
	 *
	 */
	public Set<Position> findPositionByExpiryTimeframeContaining(String expiryTimeframe_1) throws DataAccessException;

	/**
	 * JPQL Query - findPositionByExpiryTimeframeContaining
	 *
	 */
	public Set<Position> findPositionByExpiryTimeframeContaining(String expiryTimeframe_1, int startResult, int maxRows) throws DataAccessException;

	
	/*****************************************************************************************************
	 * USER-DEFINED Methods
	 */
	public Set<Position> findPositionsNonExpiredByStrategyAcctId(java.util.Calendar today, Integer strategyId) throws DataAccessException;
	public Set<Position> findPositionsNonExpiredByStrategyAcctId(java.util.Calendar today, Integer strategyId, int startResult, int maxRows) throws DataAccessException;
	
	public Set<Position> findPositionsByStrategyAcctId(Integer strategyAcctId) throws DataAccessException;
	public Set<Position> findPositionsByStrategyAcctId(Integer strategyAcctId, int startResult, int maxRows) throws DataAccessException;

	public ProfitLoss findStatsForExpiredPositions(Integer strategyAccountId) throws DataAccessException;
	
	public void synchronizedStoreAndFlush(Position position);
	public void synchronizedFlush();

}
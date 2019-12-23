package com.theta.dao;

import com.theta.domain.Spread;
import com.theta.domain.Strategy;

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
 * DAO to manage Strategy entities.
 * 
 */
@Repository("StrategyDAO")
@Transactional
public class StrategyDAOImpl extends AbstractJpaDao implements StrategyDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Strategy.class }));

	/**
	 * EntityManager injected by Spring for persistence unit LocOracleTheta
	 *
	 */
	@PersistenceContext(unitName = "LocOracleTheta")
	private EntityManager entityManager;

	/**
	 * Instantiates a new StrategyDAOImpl
	 *
	 */
	public StrategyDAOImpl() {
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
	 * JPQL Query - findStrategyByTimezone
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByTimezone(String timezone) throws DataAccessException {

		return findStrategyByTimezone(timezone, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByTimezone
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByTimezone(String timezone, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByTimezone", startResult, maxRows, timezone);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByDaysEnterLow
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByDaysEnterLow(Integer daysEnterLow) throws DataAccessException {

		return findStrategyByDaysEnterLow(daysEnterLow, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByDaysEnterLow
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByDaysEnterLow(Integer daysEnterLow, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByDaysEnterLow", startResult, maxRows, daysEnterLow);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByActiveFlag
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByActiveFlag(String activeFlag) throws DataAccessException {

		return findStrategyByActiveFlag(activeFlag, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByActiveFlag
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByActiveFlag(String activeFlag, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByActiveFlag", startResult, maxRows, activeFlag);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByInitNumSpreadsPut
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByInitNumSpreadsPut(Integer initNumSpreadsPut) throws DataAccessException {

		return findStrategyByInitNumSpreadsPut(initNumSpreadsPut, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByInitNumSpreadsPut
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByInitNumSpreadsPut(Integer initNumSpreadsPut, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByInitNumSpreadsPut", startResult, maxRows, initNumSpreadsPut);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySetBreakevenWhenInsHitContaining
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySetBreakevenWhenInsHitContaining(String setBreakevenWhenInsHit) throws DataAccessException {

		return findStrategyBySetBreakevenWhenInsHitContaining(setBreakevenWhenInsHit, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySetBreakevenWhenInsHitContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySetBreakevenWhenInsHitContaining(String setBreakevenWhenInsHit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySetBreakevenWhenInsHitContaining", startResult, maxRows, setBreakevenWhenInsHit);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByOptPricesToGet
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByOptPricesToGet(Integer optPricesToGet) throws DataAccessException {

		return findStrategyByOptPricesToGet(optPricesToGet, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByOptPricesToGet
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByOptPricesToGet(Integer optPricesToGet, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByOptPricesToGet", startResult, maxRows, optPricesToGet);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByAmountRiskPerPoint
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByAmountRiskPerPoint(java.math.BigDecimal amountRiskPerPoint) throws DataAccessException {

		return findStrategyByAmountRiskPerPoint(amountRiskPerPoint, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByAmountRiskPerPoint
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByAmountRiskPerPoint(java.math.BigDecimal amountRiskPerPoint, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByAmountRiskPerPoint", startResult, maxRows, amountRiskPerPoint);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByDistanceBetOptions
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByDistanceBetOptions(java.math.BigDecimal distanceBetOptions) throws DataAccessException {

		return findStrategyByDistanceBetOptions(distanceBetOptions, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByDistanceBetOptions
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByDistanceBetOptions(java.math.BigDecimal distanceBetOptions, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByDistanceBetOptions", startResult, maxRows, distanceBetOptions);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySetCheckVix
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySetCheckVix(String setCheckVix) throws DataAccessException {

		return findStrategyBySetCheckVix(setCheckVix, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySetCheckVix
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySetCheckVix(String setCheckVix, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySetCheckVix", startResult, maxRows, setCheckVix);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllStrategys
	 *
	 */
	@Transactional
	public Set<Strategy> findAllStrategys() throws DataAccessException {

		return findAllStrategys(-1, -1);
	}

	/**
	 * JPQL Query - findAllStrategys
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findAllStrategys(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllStrategys", startResult, maxRows);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByLiveOrTestFlagContaining
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByLiveOrTestFlagContaining(String liveOrTestFlag) throws DataAccessException {

		return findStrategyByLiveOrTestFlagContaining(liveOrTestFlag, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByLiveOrTestFlagContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByLiveOrTestFlagContaining(String liveOrTestFlag, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByLiveOrTestFlagContaining", startResult, maxRows, liveOrTestFlag);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySetReenterOnWinContaining
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySetReenterOnWinContaining(String setReenterOnWin) throws DataAccessException {

		return findStrategyBySetReenterOnWinContaining(setReenterOnWin, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySetReenterOnWinContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySetReenterOnWinContaining(String setReenterOnWin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySetReenterOnWinContaining", startResult, maxRows, setReenterOnWin);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByUpdatedByContaining
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByUpdatedByContaining(String updatedBy) throws DataAccessException {

		return findStrategyByUpdatedByContaining(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByUpdatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByUpdatedByContaining", startResult, maxRows, updatedBy);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByStrategyName
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByStrategyName(String strategyName) throws DataAccessException {

		return findStrategyByStrategyName(strategyName, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByStrategyName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByStrategyName(String strategyName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByStrategyName", startResult, maxRows, strategyName);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByPrimaryKey
	 *
	 */
	@Transactional
	public Strategy findStrategyByPrimaryKey(Integer strategyId) throws DataAccessException {

		return findStrategyByPrimaryKey(strategyId, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByPrimaryKey
	 *
	 */

	@Transactional
	public Strategy findStrategyByPrimaryKey(Integer strategyId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findStrategyByPrimaryKey", strategyId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findStrategyByDaysEnterHigh
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByDaysEnterHigh(Integer daysEnterHigh) throws DataAccessException {

		return findStrategyByDaysEnterHigh(daysEnterHigh, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByDaysEnterHigh
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByDaysEnterHigh(Integer daysEnterHigh, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByDaysEnterHigh", startResult, maxRows, daysEnterHigh);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByInitNumSpreadsCall
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByInitNumSpreadsCall(Integer initNumSpreadsCall) throws DataAccessException {

		return findStrategyByInitNumSpreadsCall(initNumSpreadsCall, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByInitNumSpreadsCall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByInitNumSpreadsCall(Integer initNumSpreadsCall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByInitNumSpreadsCall", startResult, maxRows, initNumSpreadsCall);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByUpdatedDate
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findStrategyByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByMsWaitForIbResponse
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByMsWaitForIbResponse(Integer msWaitForIbResponse) throws DataAccessException {

		return findStrategyByMsWaitForIbResponse(msWaitForIbResponse, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByMsWaitForIbResponse
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByMsWaitForIbResponse(Integer msWaitForIbResponse, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByMsWaitForIbResponse", startResult, maxRows, msWaitForIbResponse);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySymbolContaining
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySymbolContaining(String symbol) throws DataAccessException {

		return findStrategyBySymbolContaining(symbol, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySymbolContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySymbolContaining(String symbol, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySymbolContaining", startResult, maxRows, symbol);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySetExitAtInsPrice
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySetExitAtInsPrice(String setExitAtInsPrice) throws DataAccessException {

		return findStrategyBySetExitAtInsPrice(setExitAtInsPrice, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySetExitAtInsPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySetExitAtInsPrice(String setExitAtInsPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySetExitAtInsPrice", startResult, maxRows, setExitAtInsPrice);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByComissionPerTrade
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByComissionPerTrade(java.math.BigDecimal comissionPerTrade) throws DataAccessException {

		return findStrategyByComissionPerTrade(comissionPerTrade, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByComissionPerTrade
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByComissionPerTrade(java.math.BigDecimal comissionPerTrade, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByComissionPerTrade", startResult, maxRows, comissionPerTrade);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByAmountTotalRisk
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByAmountTotalRisk(java.math.BigDecimal amountTotalRisk) throws DataAccessException {

		return findStrategyByAmountTotalRisk(amountTotalRisk, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByAmountTotalRisk
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByAmountTotalRisk(java.math.BigDecimal amountTotalRisk, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByAmountTotalRisk", startResult, maxRows, amountTotalRisk);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByCreatedDateBefore
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByCreatedDateBefore(java.util.Calendar createdDate) throws DataAccessException {

		return findStrategyByCreatedDateBefore(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByCreatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByCreatedDateBefore(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByCreatedDateBefore", startResult, maxRows, createdDate);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySetReenterOnWin
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySetReenterOnWin(String setReenterOnWin) throws DataAccessException {

		return findStrategyBySetReenterOnWin(setReenterOnWin, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySetReenterOnWin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySetReenterOnWin(String setReenterOnWin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySetReenterOnWin", startResult, maxRows, setReenterOnWin);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByLiveOrTestFlag
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByLiveOrTestFlag(String liveOrTestFlag) throws DataAccessException {

		return findStrategyByLiveOrTestFlag(liveOrTestFlag, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByLiveOrTestFlag
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByLiveOrTestFlag(String liveOrTestFlag, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByLiveOrTestFlag", startResult, maxRows, liveOrTestFlag);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySetBreakevenWhenInsHit
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySetBreakevenWhenInsHit(String setBreakevenWhenInsHit) throws DataAccessException {

		return findStrategyBySetBreakevenWhenInsHit(setBreakevenWhenInsHit, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySetBreakevenWhenInsHit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySetBreakevenWhenInsHit(String setBreakevenWhenInsHit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySetBreakevenWhenInsHit", startResult, maxRows, setBreakevenWhenInsHit);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByTimezoneContaining
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByTimezoneContaining(String timezone) throws DataAccessException {

		return findStrategyByTimezoneContaining(timezone, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByTimezoneContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByTimezoneContaining(String timezone, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByTimezoneContaining", startResult, maxRows, timezone);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByPctSetTrailingStop
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByPctSetTrailingStop(java.math.BigDecimal pctSetTrailingStop) throws DataAccessException {

		return findStrategyByPctSetTrailingStop(pctSetTrailingStop, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByPctSetTrailingStop
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByPctSetTrailingStop(java.math.BigDecimal pctSetTrailingStop, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByPctSetTrailingStop", startResult, maxRows, pctSetTrailingStop);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByDaysExitBeforeExpiry
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByDaysExitBeforeExpiry(Integer daysExitBeforeExpiry) throws DataAccessException {

		return findStrategyByDaysExitBeforeExpiry(daysExitBeforeExpiry, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByDaysExitBeforeExpiry
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByDaysExitBeforeExpiry(Integer daysExitBeforeExpiry, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByDaysExitBeforeExpiry", startResult, maxRows, daysExitBeforeExpiry);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByStrategyNameContaining
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByStrategyNameContaining(String strategyName) throws DataAccessException {

		return findStrategyByStrategyNameContaining(strategyName, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByStrategyNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByStrategyNameContaining(String strategyName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByStrategyNameContaining", startResult, maxRows, strategyName);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByActiveFlagContaining
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByActiveFlagContaining(String activeFlag) throws DataAccessException {

		return findStrategyByActiveFlagContaining(activeFlag, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByActiveFlagContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByActiveFlagContaining(String activeFlag, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByActiveFlagContaining", startResult, maxRows, activeFlag);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySetReenterOnLoss
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySetReenterOnLoss(String setReenterOnLoss) throws DataAccessException {

		return findStrategyBySetReenterOnLoss(setReenterOnLoss, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySetReenterOnLoss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySetReenterOnLoss(String setReenterOnLoss, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySetReenterOnLoss", startResult, maxRows, setReenterOnLoss);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByCreatedBy
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByCreatedBy(String createdBy) throws DataAccessException {

		return findStrategyByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByUpdatedDateBefore
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException {

		return findStrategyByUpdatedDateBefore(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByUpdatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByUpdatedDateBefore(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByUpdatedDateBefore", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByNoEntryIfVixOver
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByNoEntryIfVixOver(java.math.BigDecimal noEntryIfVixOver) throws DataAccessException {

		return findStrategyByNoEntryIfVixOver(noEntryIfVixOver, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByNoEntryIfVixOver
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByNoEntryIfVixOver(java.math.BigDecimal noEntryIfVixOver, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByNoEntryIfVixOver", startResult, maxRows, noEntryIfVixOver);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByCreatedDateAfter
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException {

		return findStrategyByCreatedDateAfter(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByCreatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByCreatedDateAfter(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByCreatedDateAfter", startResult, maxRows, createdDate);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySetReenterOnLossContaining
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySetReenterOnLossContaining(String setReenterOnLoss) throws DataAccessException {

		return findStrategyBySetReenterOnLossContaining(setReenterOnLoss, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySetReenterOnLossContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySetReenterOnLossContaining(String setReenterOnLoss, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySetReenterOnLossContaining", startResult, maxRows, setReenterOnLoss);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByPointDiffForReentry
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByPointDiffForReentry(java.math.BigDecimal pointDiffForReentry) throws DataAccessException {

		return findStrategyByPointDiffForReentry(pointDiffForReentry, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByPointDiffForReentry
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByPointDiffForReentry(java.math.BigDecimal pointDiffForReentry, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByPointDiffForReentry", startResult, maxRows, pointDiffForReentry);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByUpdatedBy
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByUpdatedBy(String updatedBy) throws DataAccessException {

		return findStrategyByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByCreatedDate
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findStrategyByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByStrategyId
	 *
	 */
	@Transactional
	public Strategy findStrategyByStrategyId(Integer strategyId) throws DataAccessException {

		return findStrategyByStrategyId(strategyId, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByStrategyId
	 *
	 */

	@Transactional
	public Strategy findStrategyByStrategyId(Integer strategyId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findStrategyByStrategyId", strategyId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findStrategyByPercentEnterLow
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByPercentEnterLow(java.math.BigDecimal percentEnterLow) throws DataAccessException {

		return findStrategyByPercentEnterLow(percentEnterLow, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByPercentEnterLow
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByPercentEnterLow(java.math.BigDecimal percentEnterLow, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByPercentEnterLow", startResult, maxRows, percentEnterLow);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByCreatedByContaining(String createdBy) throws DataAccessException {

		return findStrategyByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByPctExitWin
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByPctExitWin(java.math.BigDecimal pctExitWin) throws DataAccessException {

		return findStrategyByPctExitWin(pctExitWin, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByPctExitWin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByPctExitWin(java.math.BigDecimal pctExitWin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByPctExitWin", startResult, maxRows, pctExitWin);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySetWinTrailingStop
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySetWinTrailingStop(String setWinTrailingStop) throws DataAccessException {

		return findStrategyBySetWinTrailingStop(setWinTrailingStop, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySetWinTrailingStop
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySetWinTrailingStop(String setWinTrailingStop, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySetWinTrailingStop", startResult, maxRows, setWinTrailingStop);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySetCheckVixContaining
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySetCheckVixContaining(String setCheckVix) throws DataAccessException {

		return findStrategyBySetCheckVixContaining(setCheckVix, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySetCheckVixContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySetCheckVixContaining(String setCheckVix, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySetCheckVixContaining", startResult, maxRows, setCheckVix);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySetWinTrailingStopContaining
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySetWinTrailingStopContaining(String setWinTrailingStop) throws DataAccessException {

		return findStrategyBySetWinTrailingStopContaining(setWinTrailingStop, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySetWinTrailingStopContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySetWinTrailingStopContaining(String setWinTrailingStop, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySetWinTrailingStopContaining", startResult, maxRows, setWinTrailingStop);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByMinDelayFromStart
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByMinDelayFromStart(Integer minDelayFromStart) throws DataAccessException {

		return findStrategyByMinDelayFromStart(minDelayFromStart, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByMinDelayFromStart
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByMinDelayFromStart(Integer minDelayFromStart, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByMinDelayFromStart", startResult, maxRows, minDelayFromStart);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySymbol
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySymbol(String symbol) throws DataAccessException {

		return findStrategyBySymbol(symbol, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySymbol
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySymbol(String symbol, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySymbol", startResult, maxRows, symbol);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByPctTrailingStop
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByPctTrailingStop(java.math.BigDecimal pctTrailingStop) throws DataAccessException {

		return findStrategyByPctTrailingStop(pctTrailingStop, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByPctTrailingStop
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByPctTrailingStop(java.math.BigDecimal pctTrailingStop, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByPctTrailingStop", startResult, maxRows, pctTrailingStop);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByPercentEnterHigh
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByPercentEnterHigh(java.math.BigDecimal percentEnterHigh) throws DataAccessException {

		return findStrategyByPercentEnterHigh(percentEnterHigh, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByPercentEnterHigh
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByPercentEnterHigh(java.math.BigDecimal percentEnterHigh, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByPercentEnterHigh", startResult, maxRows, percentEnterHigh);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyByUpdatedDateAfter
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyByUpdatedDateAfter(java.util.Calendar updatedDate) throws DataAccessException {

		return findStrategyByUpdatedDateAfter(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyByUpdatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyByUpdatedDateAfter(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyByUpdatedDateAfter", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	/**
	 * JPQL Query - findStrategyBySetExitAtInsPriceContaining
	 *
	 */
	@Transactional
	public Set<Strategy> findStrategyBySetExitAtInsPriceContaining(String setExitAtInsPrice) throws DataAccessException {

		return findStrategyBySetExitAtInsPriceContaining(setExitAtInsPrice, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyBySetExitAtInsPriceContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Strategy> findStrategyBySetExitAtInsPriceContaining(String setExitAtInsPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStrategyBySetExitAtInsPriceContaining", startResult, maxRows, setExitAtInsPrice);
		return new LinkedHashSet<Strategy>(query.getResultList());
	}

	public synchronized void synchronizedStoreAndFlush(Strategy strategy) {
		this.store(strategy);
		this.flush();
	}

	
}

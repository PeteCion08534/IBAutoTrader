package com.theta.dao;

import com.theta.domain.Spread;
import com.theta.domain.Strategy;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Strategy entities.
 * 
 */
public interface StrategyDAO extends JpaDao {

	/**
	 * JPQL Query - findStrategyByTimezone
	 *
	 */
	public Set<Strategy> findStrategyByTimezone(String timezone) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByTimezone
	 *
	 */
	public Set<Strategy> findStrategyByTimezone(String timezone, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByDaysEnterLow
	 *
	 */
	public Set<Strategy> findStrategyByDaysEnterLow(Integer daysEnterLow) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByDaysEnterLow
	 *
	 */
	public Set<Strategy> findStrategyByDaysEnterLow(Integer daysEnterLow, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByActiveFlag
	 *
	 */
	public Set<Strategy> findStrategyByActiveFlag(String activeFlag) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByActiveFlag
	 *
	 */
	public Set<Strategy> findStrategyByActiveFlag(String activeFlag, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByInitNumSpreadsPut
	 *
	 */
	public Set<Strategy> findStrategyByInitNumSpreadsPut(Integer initNumSpreadsPut) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByInitNumSpreadsPut
	 *
	 */
	public Set<Strategy> findStrategyByInitNumSpreadsPut(Integer initNumSpreadsPut, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetBreakevenWhenInsHitContaining
	 *
	 */
	public Set<Strategy> findStrategyBySetBreakevenWhenInsHitContaining(String setBreakevenWhenInsHit) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetBreakevenWhenInsHitContaining
	 *
	 */
	public Set<Strategy> findStrategyBySetBreakevenWhenInsHitContaining(String setBreakevenWhenInsHit, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByOptPricesToGet
	 *
	 */
	public Set<Strategy> findStrategyByOptPricesToGet(Integer optPricesToGet) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByOptPricesToGet
	 *
	 */
	public Set<Strategy> findStrategyByOptPricesToGet(Integer optPricesToGet, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByAmountRiskPerPoint
	 *
	 */
	public Set<Strategy> findStrategyByAmountRiskPerPoint(java.math.BigDecimal amountRiskPerPoint) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByAmountRiskPerPoint
	 *
	 */
	public Set<Strategy> findStrategyByAmountRiskPerPoint(BigDecimal amountRiskPerPoint, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByDistanceBetOptions
	 *
	 */
	public Set<Strategy> findStrategyByDistanceBetOptions(java.math.BigDecimal distanceBetOptions) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByDistanceBetOptions
	 *
	 */
	public Set<Strategy> findStrategyByDistanceBetOptions(BigDecimal distanceBetOptions, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetCheckVix
	 *
	 */
	public Set<Strategy> findStrategyBySetCheckVix(String setCheckVix) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetCheckVix
	 *
	 */
	public Set<Strategy> findStrategyBySetCheckVix(String setCheckVix, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllStrategys
	 *
	 */
	public Set<Strategy> findAllStrategys() throws DataAccessException;

	/**
	 * JPQL Query - findAllStrategys
	 *
	 */
	public Set<Strategy> findAllStrategys(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByLiveOrTestFlagContaining
	 *
	 */
	public Set<Strategy> findStrategyByLiveOrTestFlagContaining(String liveOrTestFlag) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByLiveOrTestFlagContaining
	 *
	 */
	public Set<Strategy> findStrategyByLiveOrTestFlagContaining(String liveOrTestFlag, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetReenterOnWinContaining
	 *
	 */
	public Set<Strategy> findStrategyBySetReenterOnWinContaining(String setReenterOnWin) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetReenterOnWinContaining
	 *
	 */
	public Set<Strategy> findStrategyBySetReenterOnWinContaining(String setReenterOnWin, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByUpdatedByContaining
	 *
	 */
	public Set<Strategy> findStrategyByUpdatedByContaining(String updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByUpdatedByContaining
	 *
	 */
	public Set<Strategy> findStrategyByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByStrategyName
	 *
	 */
	public Set<Strategy> findStrategyByStrategyName(String strategyName) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByStrategyName
	 *
	 */
	public Set<Strategy> findStrategyByStrategyName(String strategyName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPrimaryKey
	 *
	 */
	public Strategy findStrategyByPrimaryKey(Integer strategyId) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPrimaryKey
	 *
	 */
	public Strategy findStrategyByPrimaryKey(Integer strategyId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByDaysEnterHigh
	 *
	 */
	public Set<Strategy> findStrategyByDaysEnterHigh(Integer daysEnterHigh) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByDaysEnterHigh
	 *
	 */
	public Set<Strategy> findStrategyByDaysEnterHigh(Integer daysEnterHigh, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByInitNumSpreadsCall
	 *
	 */
	public Set<Strategy> findStrategyByInitNumSpreadsCall(Integer initNumSpreadsCall) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByInitNumSpreadsCall
	 *
	 */
	public Set<Strategy> findStrategyByInitNumSpreadsCall(Integer initNumSpreadsCall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByUpdatedDate
	 *
	 */
	public Set<Strategy> findStrategyByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByUpdatedDate
	 *
	 */
	public Set<Strategy> findStrategyByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByMsWaitForIbResponse
	 *
	 */
	public Set<Strategy> findStrategyByMsWaitForIbResponse(Integer msWaitForIbResponse) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByMsWaitForIbResponse
	 *
	 */
	public Set<Strategy> findStrategyByMsWaitForIbResponse(Integer msWaitForIbResponse, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySymbolContaining
	 *
	 */
	public Set<Strategy> findStrategyBySymbolContaining(String symbol) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySymbolContaining
	 *
	 */
	public Set<Strategy> findStrategyBySymbolContaining(String symbol, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetExitAtInsPrice
	 *
	 */
	public Set<Strategy> findStrategyBySetExitAtInsPrice(String setExitAtInsPrice) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetExitAtInsPrice
	 *
	 */
	public Set<Strategy> findStrategyBySetExitAtInsPrice(String setExitAtInsPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByComissionPerTrade
	 *
	 */
	public Set<Strategy> findStrategyByComissionPerTrade(java.math.BigDecimal comissionPerTrade) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByComissionPerTrade
	 *
	 */
	public Set<Strategy> findStrategyByComissionPerTrade(BigDecimal comissionPerTrade, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByAmountTotalRisk
	 *
	 */
	public Set<Strategy> findStrategyByAmountTotalRisk(java.math.BigDecimal amountTotalRisk) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByAmountTotalRisk
	 *
	 */
	public Set<Strategy> findStrategyByAmountTotalRisk(BigDecimal amountTotalRisk, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByCreatedDateBefore
	 *
	 */
	public Set<Strategy> findStrategyByCreatedDateBefore(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByCreatedDateBefore
	 *
	 */
	public Set<Strategy> findStrategyByCreatedDateBefore(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetReenterOnWin
	 *
	 */
	public Set<Strategy> findStrategyBySetReenterOnWin(String setReenterOnWin_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetReenterOnWin
	 *
	 */
	public Set<Strategy> findStrategyBySetReenterOnWin(String setReenterOnWin_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByLiveOrTestFlag
	 *
	 */
	public Set<Strategy> findStrategyByLiveOrTestFlag(String liveOrTestFlag_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByLiveOrTestFlag
	 *
	 */
	public Set<Strategy> findStrategyByLiveOrTestFlag(String liveOrTestFlag_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetBreakevenWhenInsHit
	 *
	 */
	public Set<Strategy> findStrategyBySetBreakevenWhenInsHit(String setBreakevenWhenInsHit_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetBreakevenWhenInsHit
	 *
	 */
	public Set<Strategy> findStrategyBySetBreakevenWhenInsHit(String setBreakevenWhenInsHit_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByTimezoneContaining
	 *
	 */
	public Set<Strategy> findStrategyByTimezoneContaining(String timezone_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByTimezoneContaining
	 *
	 */
	public Set<Strategy> findStrategyByTimezoneContaining(String timezone_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPctSetTrailingStop
	 *
	 */
	public Set<Strategy> findStrategyByPctSetTrailingStop(java.math.BigDecimal pctSetTrailingStop) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPctSetTrailingStop
	 *
	 */
	public Set<Strategy> findStrategyByPctSetTrailingStop(BigDecimal pctSetTrailingStop, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByDaysExitBeforeExpiry
	 *
	 */
	public Set<Strategy> findStrategyByDaysExitBeforeExpiry(Integer daysExitBeforeExpiry) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByDaysExitBeforeExpiry
	 *
	 */
	public Set<Strategy> findStrategyByDaysExitBeforeExpiry(Integer daysExitBeforeExpiry, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByStrategyNameContaining
	 *
	 */
	public Set<Strategy> findStrategyByStrategyNameContaining(String strategyName_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByStrategyNameContaining
	 *
	 */
	public Set<Strategy> findStrategyByStrategyNameContaining(String strategyName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByActiveFlagContaining
	 *
	 */
	public Set<Strategy> findStrategyByActiveFlagContaining(String activeFlag_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByActiveFlagContaining
	 *
	 */
	public Set<Strategy> findStrategyByActiveFlagContaining(String activeFlag_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetReenterOnLoss
	 *
	 */
	public Set<Strategy> findStrategyBySetReenterOnLoss(String setReenterOnLoss) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetReenterOnLoss
	 *
	 */
	public Set<Strategy> findStrategyBySetReenterOnLoss(String setReenterOnLoss, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByCreatedBy
	 *
	 */
	public Set<Strategy> findStrategyByCreatedBy(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByCreatedBy
	 *
	 */
	public Set<Strategy> findStrategyByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByUpdatedDateBefore
	 *
	 */
	public Set<Strategy> findStrategyByUpdatedDateBefore(java.util.Calendar updatedDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByUpdatedDateBefore
	 *
	 */
	public Set<Strategy> findStrategyByUpdatedDateBefore(Calendar updatedDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByNoEntryIfVixOver
	 *
	 */
	public Set<Strategy> findStrategyByNoEntryIfVixOver(java.math.BigDecimal noEntryIfVixOver) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByNoEntryIfVixOver
	 *
	 */
	public Set<Strategy> findStrategyByNoEntryIfVixOver(BigDecimal noEntryIfVixOver, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByCreatedDateAfter
	 *
	 */
	public Set<Strategy> findStrategyByCreatedDateAfter(java.util.Calendar createdDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByCreatedDateAfter
	 *
	 */
	public Set<Strategy> findStrategyByCreatedDateAfter(Calendar createdDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetReenterOnLossContaining
	 *
	 */
	public Set<Strategy> findStrategyBySetReenterOnLossContaining(String setReenterOnLoss_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetReenterOnLossContaining
	 *
	 */
	public Set<Strategy> findStrategyBySetReenterOnLossContaining(String setReenterOnLoss_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPointDiffForReentry
	 *
	 */
	public Set<Strategy> findStrategyByPointDiffForReentry(java.math.BigDecimal pointDiffForReentry) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPointDiffForReentry
	 *
	 */
	public Set<Strategy> findStrategyByPointDiffForReentry(BigDecimal pointDiffForReentry, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByUpdatedBy
	 *
	 */
	public Set<Strategy> findStrategyByUpdatedBy(String updatedBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByUpdatedBy
	 *
	 */
	public Set<Strategy> findStrategyByUpdatedBy(String updatedBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByCreatedDate
	 *
	 */
	public Set<Strategy> findStrategyByCreatedDate(java.util.Calendar createdDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByCreatedDate
	 *
	 */
	public Set<Strategy> findStrategyByCreatedDate(Calendar createdDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByStrategyId
	 *
	 */
	public Strategy findStrategyByStrategyId(Integer strategyId_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByStrategyId
	 *
	 */
	public Strategy findStrategyByStrategyId(Integer strategyId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPercentEnterLow
	 *
	 */
	public Set<Strategy> findStrategyByPercentEnterLow(java.math.BigDecimal percentEnterLow) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPercentEnterLow
	 *
	 */
	public Set<Strategy> findStrategyByPercentEnterLow(BigDecimal percentEnterLow, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByCreatedByContaining
	 *
	 */
	public Set<Strategy> findStrategyByCreatedByContaining(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByCreatedByContaining
	 *
	 */
	public Set<Strategy> findStrategyByCreatedByContaining(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPctExitWin
	 *
	 */
	public Set<Strategy> findStrategyByPctExitWin(java.math.BigDecimal pctExitWin) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPctExitWin
	 *
	 */
	public Set<Strategy> findStrategyByPctExitWin(BigDecimal pctExitWin, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetWinTrailingStop
	 *
	 */
	public Set<Strategy> findStrategyBySetWinTrailingStop(String setWinTrailingStop) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetWinTrailingStop
	 *
	 */
	public Set<Strategy> findStrategyBySetWinTrailingStop(String setWinTrailingStop, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetCheckVixContaining
	 *
	 */
	public Set<Strategy> findStrategyBySetCheckVixContaining(String setCheckVix_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetCheckVixContaining
	 *
	 */
	public Set<Strategy> findStrategyBySetCheckVixContaining(String setCheckVix_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetWinTrailingStopContaining
	 *
	 */
	public Set<Strategy> findStrategyBySetWinTrailingStopContaining(String setWinTrailingStop_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetWinTrailingStopContaining
	 *
	 */
	public Set<Strategy> findStrategyBySetWinTrailingStopContaining(String setWinTrailingStop_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByMinDelayFromStart
	 *
	 */
	public Set<Strategy> findStrategyByMinDelayFromStart(Integer minDelayFromStart) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByMinDelayFromStart
	 *
	 */
	public Set<Strategy> findStrategyByMinDelayFromStart(Integer minDelayFromStart, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySymbol
	 *
	 */
	public Set<Strategy> findStrategyBySymbol(String symbol_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySymbol
	 *
	 */
	public Set<Strategy> findStrategyBySymbol(String symbol_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPctTrailingStop
	 *
	 */
	public Set<Strategy> findStrategyByPctTrailingStop(java.math.BigDecimal pctTrailingStop) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPctTrailingStop
	 *
	 */
	public Set<Strategy> findStrategyByPctTrailingStop(BigDecimal pctTrailingStop, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPercentEnterHigh
	 *
	 */
	public Set<Strategy> findStrategyByPercentEnterHigh(java.math.BigDecimal percentEnterHigh) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByPercentEnterHigh
	 *
	 */
	public Set<Strategy> findStrategyByPercentEnterHigh(BigDecimal percentEnterHigh, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByUpdatedDateAfter
	 *
	 */
	public Set<Strategy> findStrategyByUpdatedDateAfter(java.util.Calendar updatedDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyByUpdatedDateAfter
	 *
	 */
	public Set<Strategy> findStrategyByUpdatedDateAfter(Calendar updatedDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetExitAtInsPriceContaining
	 *
	 */
	public Set<Strategy> findStrategyBySetExitAtInsPriceContaining(String setExitAtInsPrice_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyBySetExitAtInsPriceContaining
	 *
	 */
	public Set<Strategy> findStrategyBySetExitAtInsPriceContaining(String setExitAtInsPrice_1, int startResult, int maxRows) throws DataAccessException;

	public void synchronizedStoreAndFlush(Strategy strategy);

	
}
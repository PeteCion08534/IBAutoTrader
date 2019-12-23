package com.theta.dao;

import com.theta.domain.Spread;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Spread entities.
 * 
 */
public interface SpreadDAO extends JpaDao {

	/**
	 * JPQL Query - findSpreadByStrikeInsurance
	 *
	 */
	public Set<Spread> findSpreadByStrikeInsurance(java.math.BigDecimal strikeInsurance) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByStrikeInsurance
	 *
	 */
	public Set<Spread> findSpreadByStrikeInsurance(BigDecimal strikeInsurance, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCreatedDateAfter
	 *
	 */
	public Set<Spread> findSpreadByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCreatedDateAfter
	 *
	 */
	public Set<Spread> findSpreadByCreatedDateAfter(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCreatedByContaining
	 *
	 */
	public Set<Spread> findSpreadByCreatedByContaining(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCreatedByContaining
	 *
	 */
	public Set<Spread> findSpreadByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCreatedBy
	 *
	 */
	public Set<Spread> findSpreadByCreatedBy(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCreatedBy
	 *
	 */
	public Set<Spread> findSpreadByCreatedBy(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentDate
	 *
	 */
	public Set<Spread> findSpreadByCurrentDate(java.util.Calendar currentDate) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentDate
	 *
	 */
	public Set<Spread> findSpreadByCurrentDate(Calendar currentDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitInsurancePrice
	 *
	 */
	public Set<Spread> findSpreadByExitInsurancePrice(java.math.BigDecimal exitInsurancePrice) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitInsurancePrice
	 *
	 */
	public Set<Spread> findSpreadByExitInsurancePrice(BigDecimal exitInsurancePrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentDateAfter
	 *
	 */
	public Set<Spread> findSpreadByCurrentDateAfter(java.util.Calendar currentDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentDateAfter
	 *
	 */
	public Set<Spread> findSpreadByCurrentDateAfter(Calendar currentDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentMoneymkrPrice
	 *
	 */
	public Set<Spread> findSpreadByCurrentMoneymkrPrice(java.math.BigDecimal currentMoneymkrPrice) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentMoneymkrPrice
	 *
	 */
	public Set<Spread> findSpreadByCurrentMoneymkrPrice(BigDecimal currentMoneymkrPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByUpdatedByContaining
	 *
	 */
	public Set<Spread> findSpreadByUpdatedByContaining(String updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByUpdatedByContaining
	 *
	 */
	public Set<Spread> findSpreadByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitMoneymkrDate
	 *
	 */
	public Set<Spread> findSpreadByExitMoneymkrDate(java.util.Calendar exitMoneymkrDate) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitMoneymkrDate
	 *
	 */
	public Set<Spread> findSpreadByExitMoneymkrDate(Calendar exitMoneymkrDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByUpdatedBy
	 *
	 */
	public Set<Spread> findSpreadByUpdatedBy(String updatedBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByUpdatedBy
	 *
	 */
	public Set<Spread> findSpreadByUpdatedBy(String updatedBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitSecurityDateBefore
	 *
	 */
	public Set<Spread> findSpreadByExitSecurityDateBefore(java.util.Calendar exitSecurityDate) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitSecurityDateBefore
	 *
	 */
	public Set<Spread> findSpreadByExitSecurityDateBefore(Calendar exitSecurityDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitCommission
	 *
	 */
	public Set<Spread> findSpreadByExitCommission(java.math.BigDecimal exitCommission) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitCommission
	 *
	 */
	public Set<Spread> findSpreadByExitCommission(BigDecimal exitCommission, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByUpdatedDateBefore
	 *
	 */
	public Set<Spread> findSpreadByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByUpdatedDateBefore
	 *
	 */
	public Set<Spread> findSpreadByUpdatedDateBefore(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByNumSpreads
	 *
	 */
	//public Set<Spread> findSpreadByNumSpreads(Integer numSpreads) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByNumSpreads
	 *
	 */
	//public Set<Spread> findSpreadByNumSpreads(Integer numSpreads, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterCommission
	 *
	 */
	public Set<Spread> findSpreadByEnterCommission(java.math.BigDecimal enterCommission) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterCommission
	 *
	 */
	public Set<Spread> findSpreadByEnterCommission(BigDecimal enterCommission, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitMoneymkrPrice
	 *
	 */
	public Set<Spread> findSpreadByExitMoneymkrPrice(java.math.BigDecimal exitMoneymkrPrice) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitMoneymkrPrice
	 *
	 */
	public Set<Spread> findSpreadByExitMoneymkrPrice(BigDecimal exitMoneymkrPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitBelowSpreadValue
	 *
	 */
	public Set<Spread> findSpreadByExitBelowSpreadValue(java.math.BigDecimal exitBelowSpreadValue) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitBelowSpreadValue
	 *
	 */
	public Set<Spread> findSpreadByExitBelowSpreadValue(BigDecimal exitBelowSpreadValue, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterSecurityDateBefore
	 *
	 */
	public Set<Spread> findSpreadByEnterSecurityDateBefore(java.util.Calendar enterSecurityDate) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterSecurityDateBefore
	 *
	 */
	public Set<Spread> findSpreadByEnterSecurityDateBefore(Calendar enterSecurityDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrDate
	 *
	 */
	public Set<Spread> findSpreadByEnterMoneymkrDate(java.util.Calendar enterMoneymkrDate) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrDate
	 *
	 */
	public Set<Spread> findSpreadByEnterMoneymkrDate(Calendar enterMoneymkrDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByPrimaryKey
	 *
	 */
	public Spread findSpreadByPrimaryKey(Integer spreadId) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByPrimaryKey
	 *
	 */
	public Spread findSpreadByPrimaryKey(Integer spreadId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByUpdatedDate
	 *
	 */
	public Set<Spread> findSpreadByUpdatedDate(java.util.Calendar updatedDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByUpdatedDate
	 *
	 */
	public Set<Spread> findSpreadByUpdatedDate(Calendar updatedDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterSecurityPrice
	 *
	 */
	public Set<Spread> findSpreadByEnterSecurityPrice(java.math.BigDecimal enterSecurityPrice) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterSecurityPrice
	 *
	 */
	public Set<Spread> findSpreadByEnterSecurityPrice(BigDecimal enterSecurityPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitSecurityDateAfter
	 *
	 */
	public Set<Spread> findSpreadByExitSecurityDateAfter(java.util.Calendar exitSecurityDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitSecurityDateAfter
	 *
	 */
	public Set<Spread> findSpreadByExitSecurityDateAfter(Calendar exitSecurityDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByOpenOrClosedContaining
	 *
	 */
	public Set<Spread> findSpreadByOpenOrClosedContaining(String openOrClosed) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByOpenOrClosedContaining
	 *
	 */
	public Set<Spread> findSpreadByOpenOrClosedContaining(String openOrClosed, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterSecurityDate
	 *
	 */
	public Set<Spread> findSpreadByEnterSecurityDate(java.util.Calendar enterSecurityDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterSecurityDate
	 *
	 */
	public Set<Spread> findSpreadByEnterSecurityDate(Calendar enterSecurityDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrPrice
	 *
	 */
	public Set<Spread> findSpreadByEnterMoneymkrPrice(java.math.BigDecimal enterMoneymkrPrice) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrPrice
	 *
	 */
	public Set<Spread> findSpreadByEnterMoneymkrPrice(BigDecimal enterMoneymkrPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitSecurityPrice
	 *
	 */
	public Set<Spread> findSpreadByExitSecurityPrice(java.math.BigDecimal exitSecurityPrice) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitSecurityPrice
	 *
	 */
	public Set<Spread> findSpreadByExitSecurityPrice(BigDecimal exitSecurityPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterInsuranceDateAfter
	 *
	 */
	public Set<Spread> findSpreadByEnterInsuranceDateAfter(java.util.Calendar enterInsuranceDate) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterInsuranceDateAfter
	 *
	 */
	public Set<Spread> findSpreadByEnterInsuranceDateAfter(Calendar enterInsuranceDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterInsuranceDateBefore
	 *
	 */
	public Set<Spread> findSpreadByEnterInsuranceDateBefore(java.util.Calendar enterInsuranceDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterInsuranceDateBefore
	 *
	 */
	public Set<Spread> findSpreadByEnterInsuranceDateBefore(Calendar enterInsuranceDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByUpdatedDateAfter
	 *
	 */
	public Set<Spread> findSpreadByUpdatedDateAfter(java.util.Calendar updatedDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByUpdatedDateAfter
	 *
	 */
	public Set<Spread> findSpreadByUpdatedDateAfter(Calendar updatedDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentVixPrice
	 *
	 */
	public Set<Spread> findSpreadByCurrentVixPrice(java.math.BigDecimal currentVixPrice) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentVixPrice
	 *
	 */
	public Set<Spread> findSpreadByCurrentVixPrice(BigDecimal currentVixPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByProfitLossRealized
	 *
	 */
	public Set<Spread> findSpreadByProfitLossRealized(java.math.BigDecimal profitLossRealized) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByProfitLossRealized
	 *
	 */
	public Set<Spread> findSpreadByProfitLossRealized(BigDecimal profitLossRealized, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByProfitLossUnrealized
	 *
	 */
	public Set<Spread> findSpreadByProfitLossUnrealized(java.math.BigDecimal profitLossUnrealized) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByProfitLossUnrealized
	 *
	 */
	public Set<Spread> findSpreadByProfitLossUnrealized(BigDecimal profitLossUnrealized, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterInsurancePrice
	 *
	 */
	public Set<Spread> findSpreadByEnterInsurancePrice(java.math.BigDecimal enterInsurancePrice) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterInsurancePrice
	 *
	 */
	public Set<Spread> findSpreadByEnterInsurancePrice(BigDecimal enterInsurancePrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadBySpreadId
	 *
	 */
	public Spread findSpreadBySpreadId(Integer spreadId_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadBySpreadId
	 *
	 */
	public Spread findSpreadBySpreadId(Integer spreadId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByTrailingDaysAndHours
	 *
	 */
	public Set<Spread> findSpreadByTrailingDaysAndHours(String trailingDaysAndHours) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByTrailingDaysAndHours
	 *
	 */
	public Set<Spread> findSpreadByTrailingDaysAndHours(String trailingDaysAndHours, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterInsuranceDate
	 *
	 */
	public Set<Spread> findSpreadByEnterInsuranceDate(java.util.Calendar enterInsuranceDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterInsuranceDate
	 *
	 */
	public Set<Spread> findSpreadByEnterInsuranceDate(Calendar enterInsuranceDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitInsuranceDateAfter
	 *
	 */
	public Set<Spread> findSpreadByExitInsuranceDateAfter(java.util.Calendar exitInsuranceDate) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitInsuranceDateAfter
	 *
	 */
	public Set<Spread> findSpreadByExitInsuranceDateAfter(Calendar exitInsuranceDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitInsuranceDate
	 *
	 */
	public Set<Spread> findSpreadByExitInsuranceDate(java.util.Calendar exitInsuranceDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitInsuranceDate
	 *
	 */
	public Set<Spread> findSpreadByExitInsuranceDate(Calendar exitInsuranceDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByTrailingStopIsSet
	 *
	 */
	public Set<Spread> findSpreadByTrailingStopIsSet(String trailingStopIsSet) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByTrailingStopIsSet
	 *
	 */
	public Set<Spread> findSpreadByTrailingStopIsSet(String trailingStopIsSet, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSpreads
	 *
	 */
	public Set<Spread> findAllSpreads() throws DataAccessException;

	/**
	 * JPQL Query - findAllSpreads
	 *
	 */
	public Set<Spread> findAllSpreads(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitInsuranceDateBefore
	 *
	 */
	public Set<Spread> findSpreadByExitInsuranceDateBefore(java.util.Calendar exitInsuranceDate_2) throws DataAccessException;

	//public Set<Spread> findOpenOrPendSpreadsByPosition() throws DataAccessException;
	
	/**
	 * JPQL Query - findSpreadByExitInsuranceDateBefore
	 *
	 */
	public Set<Spread> findSpreadByExitInsuranceDateBefore(Calendar exitInsuranceDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCreatedDate
	 *
	 */
	public Set<Spread> findSpreadByCreatedDate(java.util.Calendar createdDate_1) throws DataAccessException;

	/**
	 * ADDED BY PC - findSpreadByReopenDate
	 *
	 */
	public Set<Spread> findSpreadByReopenDate(java.util.Calendar reopenDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCreatedDate
	 *
	 */
	public Set<Spread> findSpreadByCreatedDate(Calendar createdDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentDateBefore
	 *
	 */
	public Set<Spread> findSpreadByCurrentDateBefore(java.util.Calendar currentDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentDateBefore
	 *
	 */
	public Set<Spread> findSpreadByCurrentDateBefore(Calendar currentDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByOpenOrClosed
	 *
	 */
	public Set<Spread> findSpreadByOpenOrClosed(String openOrClosed_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByOpenOrClosed
	 *
	 */
	public Set<Spread> findSpreadByOpenOrClosed(String openOrClosed_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterSecurityDateAfter
	 *
	 */
	public Set<Spread> findSpreadByEnterSecurityDateAfter(java.util.Calendar enterSecurityDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterSecurityDateAfter
	 *
	 */
	public Set<Spread> findSpreadByEnterSecurityDateAfter(Calendar enterSecurityDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitMoneymkrDateAfter
	 *
	 */
	public Set<Spread> findSpreadByExitMoneymkrDateAfter(java.util.Calendar exitMoneymkrDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitMoneymkrDateAfter
	 *
	 */
	public Set<Spread> findSpreadByExitMoneymkrDateAfter(Calendar exitMoneymkrDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrDateAfter
	 *
	 */
	public Set<Spread> findSpreadByEnterMoneymkrDateAfter(java.util.Calendar enterMoneymkrDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrDateAfter
	 *
	 */
	public Set<Spread> findSpreadByEnterMoneymkrDateAfter(Calendar enterMoneymkrDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByTrailingStopIsSetContaining
	 *
	 */
	public Set<Spread> findSpreadByTrailingStopIsSetContaining(String trailingStopIsSet_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByTrailingStopIsSetContaining
	 *
	 */
	public Set<Spread> findSpreadByTrailingStopIsSetContaining(String trailingStopIsSet_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCreatedDateBefore
	 *
	 */
	public Set<Spread> findSpreadByCreatedDateBefore(java.util.Calendar createdDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCreatedDateBefore
	 *
	 */
	public Set<Spread> findSpreadByCreatedDateBefore(Calendar createdDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentInsurancePrice
	 *
	 */
	public Set<Spread> findSpreadByCurrentInsurancePrice(java.math.BigDecimal currentInsurancePrice) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentInsurancePrice
	 *
	 */
	public Set<Spread> findSpreadByCurrentInsurancePrice(BigDecimal currentInsurancePrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitAboveSpreadValue
	 *
	 */
	public Set<Spread> findSpreadByExitAboveSpreadValue(java.math.BigDecimal exitAboveSpreadValue) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitAboveSpreadValue
	 *
	 */
	public Set<Spread> findSpreadByExitAboveSpreadValue(BigDecimal exitAboveSpreadValue, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentSecurityPrice
	 *
	 */
	public Set<Spread> findSpreadByCurrentSecurityPrice(java.math.BigDecimal currentSecurityPrice) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByCurrentSecurityPrice
	 *
	 */
	public Set<Spread> findSpreadByCurrentSecurityPrice(BigDecimal currentSecurityPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByMoneymkrPositionId
	 *
	 */
	public Set<Spread> findSpreadByMoneymkrPositionId(String moneymkrPositionId) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByMoneymkrPositionId
	 *
	 */
	public Set<Spread> findSpreadByMoneymkrPositionId(String moneymkrPositionId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByMoneymkrPositionIdContaining
	 *
	 */
	public Set<Spread> findSpreadByMoneymkrPositionIdContaining(String moneymkrPositionId_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByMoneymkrPositionIdContaining
	 *
	 */
	public Set<Spread> findSpreadByMoneymkrPositionIdContaining(String moneymkrPositionId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByStrikeMoneymkr
	 *
	 */
	public Set<Spread> findSpreadByStrikeMoneymkr(java.math.BigDecimal strikeMoneymkr) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByStrikeMoneymkr
	 *
	 */
	public Set<Spread> findSpreadByStrikeMoneymkr(BigDecimal strikeMoneymkr, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByInsurancePositionIdContaining
	 *
	 */
	public Set<Spread> findSpreadByInsurancePositionIdContaining(String insurancePositionId) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByInsurancePositionIdContaining
	 *
	 */
	public Set<Spread> findSpreadByInsurancePositionIdContaining(String insurancePositionId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByInsurancePositionId
	 *
	 */
	public Set<Spread> findSpreadByInsurancePositionId(String insurancePositionId_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByInsurancePositionId
	 *
	 */
	public Set<Spread> findSpreadByInsurancePositionId(String insurancePositionId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitSecurityDate
	 *
	 */
	public Set<Spread> findSpreadByExitSecurityDate(java.util.Calendar exitSecurityDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitSecurityDate
	 *
	 */
	public Set<Spread> findSpreadByExitSecurityDate(Calendar exitSecurityDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitMoneymkrDateBefore
	 *
	 */
	public Set<Spread> findSpreadByExitMoneymkrDateBefore(java.util.Calendar exitMoneymkrDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByExitMoneymkrDateBefore
	 *
	 */
	public Set<Spread> findSpreadByExitMoneymkrDateBefore(Calendar exitMoneymkrDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByTrailingDaysAndHoursContaining
	 *
	 */
	public Set<Spread> findSpreadByTrailingDaysAndHoursContaining(String trailingDaysAndHours_1) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByTrailingDaysAndHoursContaining
	 *
	 */
	public Set<Spread> findSpreadByTrailingDaysAndHoursContaining(String trailingDaysAndHours_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrDateBefore
	 *
	 */
	public Set<Spread> findSpreadByEnterMoneymkrDateBefore(java.util.Calendar enterMoneymkrDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrDateBefore
	 *
	 */
	public Set<Spread> findSpreadByEnterMoneymkrDateBefore(Calendar enterMoneymkrDate_2, int startResult, int maxRows) throws DataAccessException;


	/********************************************************************************************************
	 * USER DEFINED
	 *
	 */
	public Set<Spread> findSpreadByPositionId(int positionId) throws DataAccessException;
	public Set<Spread> findSpreadByPositionId(int positionId, int startResult, int maxRows) throws DataAccessException;
	public void synchronizedStoreAndFlush(Spread spread);
	public void synchronizedStore(Spread spread);
	public void synchronizedFlush();
	
}
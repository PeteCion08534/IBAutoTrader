package com.theta.dao;

import com.theta.domain.SnapshotOption;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SnapshotOption entities.
 * 
 */
public interface SnapshotOptionDAO extends JpaDao {

	/**
	 * JPQL Query - findSnapshotOptionByOptRight
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByOptRight(String optRight) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByOptRight
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByOptRight(String optRight, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeOpenTodayBefore
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeOpenTodayBefore(java.util.Calendar tradingTimeOpenToday) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeOpenTodayBefore
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeOpenTodayBefore(Calendar tradingTimeOpenToday, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByCreatedBy
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByCreatedBy(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByCreatedBy
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByOptRightContaining
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByOptRightContaining(String optRight_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByOptRightContaining
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByOptRightContaining(String optRight_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSnapshotOptions
	 *
	 */
	public Set<SnapshotOption> findAllSnapshotOptions() throws DataAccessException;

	/**
	 * JPQL Query - findAllSnapshotOptions
	 *
	 */
	public Set<SnapshotOption> findAllSnapshotOptions(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedDate
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedDate
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeCloseToday
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeCloseToday(java.util.Calendar tradingTimeCloseToday) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeCloseToday
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeCloseToday(Calendar tradingTimeCloseToday, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByStrike
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByStrike(java.math.BigDecimal strike) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByStrike
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByStrike(BigDecimal strike, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByBidPrice
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByBidPrice(java.math.BigDecimal bidPrice) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByBidPrice
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByBidPrice(BigDecimal bidPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByCreatedDateAfter
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByCreatedDateAfter
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByCreatedDateAfter(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByLocalSymbol
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByLocalSymbol(String localSymbol) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByLocalSymbol
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByLocalSymbol(String localSymbol, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByAskPrice
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByAskPrice(java.math.BigDecimal askPrice) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByAskPrice
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByAskPrice(BigDecimal askPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByLocalSymbolContaining
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByLocalSymbolContaining(String localSymbol_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByLocalSymbolContaining
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByLocalSymbolContaining(String localSymbol_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedDateAfter
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByUpdatedDateAfter(java.util.Calendar updatedDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedDateAfter
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByUpdatedDateAfter(Calendar updatedDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeOpenToday
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeOpenToday(java.util.Calendar tradingTimeOpenToday_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeOpenToday
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeOpenToday(Calendar tradingTimeOpenToday_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedBy
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByUpdatedBy(String updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedBy
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeCloseTodayBefore
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeCloseTodayBefore(java.util.Calendar tradingTimeCloseToday_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeCloseTodayBefore
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeCloseTodayBefore(Calendar tradingTimeCloseToday_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByCreatedDate
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByCreatedDate(java.util.Calendar createdDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByCreatedDate
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByCreatedDate(Calendar createdDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeOpenTodayAfter
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeOpenTodayAfter(java.util.Calendar tradingTimeOpenToday_2) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeOpenTodayAfter
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeOpenTodayAfter(Calendar tradingTimeOpenToday_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByPrimaryKey
	 *
	 */
	public SnapshotOption findSnapshotOptionByPrimaryKey(Integer snapshotOptionId) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByPrimaryKey
	 *
	 */
	public SnapshotOption findSnapshotOptionByPrimaryKey(Integer snapshotOptionId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeCloseTodayAfter
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeCloseTodayAfter(java.util.Calendar tradingTimeCloseToday_2) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByTradingTimeCloseTodayAfter
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByTradingTimeCloseTodayAfter(Calendar tradingTimeCloseToday_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByCreatedDateBefore
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByCreatedDateBefore(java.util.Calendar createdDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByCreatedDateBefore
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByCreatedDateBefore(Calendar createdDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByLastPrice
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByLastPrice(java.math.BigDecimal lastPrice) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByLastPrice
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByLastPrice(BigDecimal lastPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByCreatedByContaining
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByCreatedByContaining(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByCreatedByContaining
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByCreatedByContaining(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedByContaining
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByUpdatedByContaining(String updatedBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedByContaining
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByUpdatedByContaining(String updatedBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionBySnapshotOptionId
	 *
	 */
	public SnapshotOption findSnapshotOptionBySnapshotOptionId(Integer snapshotOptionId_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionBySnapshotOptionId
	 *
	 */
	public SnapshotOption findSnapshotOptionBySnapshotOptionId(Integer snapshotOptionId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedDateBefore
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByUpdatedDateBefore(java.util.Calendar updatedDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotOptionByUpdatedDateBefore
	 *
	 */
	public Set<SnapshotOption> findSnapshotOptionByUpdatedDateBefore(Calendar updatedDate_2, int startResult, int maxRows) throws DataAccessException;

}
package com.theta.dao;

import com.theta.domain.SecurityPrice;
import com.theta.enums.MinOrMaxCode;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.NoResultException;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SecurityPrice entities.
 * 
 */
//public interface SecurityPriceDAO extends JpaDao<SecurityPrice> {
public interface SecurityPriceDAO extends JpaDao {

	/**
	 * JPQL Query - findAllSecurityPrices
	 *
	 */
	public Set<SecurityPrice> findAllSecurityPrices() throws DataAccessException;

	/**
	 * JPQL Query - findAllSecurityPrices
	 *
	 */
	public Set<SecurityPrice> findAllSecurityPrices(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSecurityPriceByTicker
	 *
	 */
	public Set<SecurityPrice> findSecurityPriceByTicker(String ticker) throws DataAccessException;

	/**
	 * JPQL Query - findSecurityPriceByTicker
	 *
	 */
	public Set<SecurityPrice> findSecurityPriceByTicker(String ticker, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSecurityPriceByPrimaryKey
	 *
	 */
	public SecurityPrice findSecurityPriceByPrimaryKey(Integer securityPriceId) throws DataAccessException;

	/**
	 * JPQL Query - findSecurityPriceByPrimaryKey
	 *
	 */
	public SecurityPrice findSecurityPriceByPrimaryKey(Integer securityPriceId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSecurityPriceByTickerContaining
	 *
	 */
	public Set<SecurityPrice> findSecurityPriceByTickerContaining(String ticker_1) throws DataAccessException;

	/**
	 * JPQL Query - findSecurityPriceByTickerContaining
	 *
	 */
	public Set<SecurityPrice> findSecurityPriceByTickerContaining(String ticker_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSecurityPriceBySecurityPriceId
	 *
	 */
	public SecurityPrice findSecurityPriceBySecurityPriceId(Integer securityPriceId_1) throws DataAccessException;

	/**
	 * JPQL Query - findSecurityPriceBySecurityPriceId
	 *
	 */
	public SecurityPrice findSecurityPriceBySecurityPriceId(Integer securityPriceId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSecurityPriceByPrice
	 *
	 */
	public Set<SecurityPrice> findSecurityPriceByPrice(Integer price) throws DataAccessException;

	/**
	 * JPQL Query - findSecurityPriceByPrice
	 *
	 */
	public Set<SecurityPrice> findSecurityPriceByPrice(Integer price, int startResult, int maxRows) throws DataAccessException;

	/**
	 * User-defined functions:
	 */
	
	public Date findDateOfMinOrMaxPrice(Calendar sinceDateTime, String theTicker, MinOrMaxCode minOrMax) throws DataAccessException, Exception;

	public SecurityPrice findDayOpenPriceByDateAndTicker(Calendar cal, String theTicker) throws DataAccessException;

	public Set<SecurityPrice> findSecurityPricesByTickerAndSourceSinceTime(String theTicker, String theSource, Calendar sinceTime, Calendar toTime) throws DataAccessException;

	public Set<SecurityPrice> findSecurityPricesByTickerSinceTime(String theTicker, Calendar sinceTime, Calendar toTime) throws DataAccessException;
	
	public Integer findMinOrMaxPriceSinceDateTime(Calendar cal, String theTicker, MinOrMaxCode minOrMax) throws DataAccessException;
	
	public SecurityPrice findLastBusinessDay() throws DataAccessException;

	public Integer findMovingAverage(Calendar fromDate, Calendar toDate, String ticker) throws DataAccessException;

	public Integer findMovingAverageDailyRange(Calendar fromDate, Calendar toDate, String theTicker) throws DataAccessException;

	public SecurityPrice findEarliestRecordSince(Calendar fromDate, String ticker) throws DataAccessException;
	public SecurityPrice findLatestRecordBefore(Calendar fromDate, String ticker) throws DataAccessException;

	public Integer findNumRecordsOnDate(Calendar theDate, String ticker) throws DataAccessException;
	public void synchronizedStoreAndFlush(SecurityPrice securityPrice);
	
}
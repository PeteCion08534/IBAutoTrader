package com.theta.dao;

import com.theta.db.ThetaSQLIF;
import com.theta.domain.ProfitLoss;
import com.theta.domain.SecurityPrice;
import com.theta.enums.MinOrMaxCode;
import com.theta.process.ThetaMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityResult;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;

import org.apache.log4j.Logger;
import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SecurityPrice entities.
 * 
 */
@Repository("SecurityPriceDAO")
@Transactional
public class SecurityPriceDAOImpl extends AbstractJpaDao implements SecurityPriceDAO {
	
	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SecurityPrice.class }));

	/**
	 * EntityManager injected by Spring for persistence unit ThetaLocal
	 *
	 */
	@PersistenceContext(unitName = "LocOracleTheta")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SecurityPriceDAOImpl
	 *
	 */
	public SecurityPriceDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit 
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
	 * JPQL Query - findAllSecurityPrices
	 *
	 */
	@Transactional
	public Set<SecurityPrice> findAllSecurityPrices() throws DataAccessException {

		return findAllSecurityPrices(-1, -1);
	}

	/**
	 * JPQL Query - findAllSecurityPrices
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SecurityPrice> findAllSecurityPrices(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSecurityPrices", startResult, maxRows);
		return new LinkedHashSet<SecurityPrice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSecurityPriceByTicker
	 *
	 */
	@Transactional
	public Set<SecurityPrice> findSecurityPriceByTicker(String ticker) throws DataAccessException {

		return findSecurityPriceByTicker(ticker, -1, -1);
	}

	/**
	 * JPQL Query - findSecurityPriceByTicker
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SecurityPrice> findSecurityPriceByTicker(String ticker, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSecurityPriceByTicker", startResult, maxRows, ticker);
		return new LinkedHashSet<SecurityPrice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSecurityPriceByPrimaryKey
	 *
	 */
	@Transactional
	public SecurityPrice findSecurityPriceByPrimaryKey(Integer securityPriceId) throws DataAccessException {

		return findSecurityPriceByPrimaryKey(securityPriceId, -1, -1);
	}

	/**
	 * JPQL Query - findSecurityPriceByPrimaryKey
	 *
	 */

	@Transactional
	public SecurityPrice findSecurityPriceByPrimaryKey(Integer securityPriceId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSecurityPriceByPrimaryKey", startResult, maxRows, securityPriceId);
			return (com.theta.domain.SecurityPrice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSecurityPriceByTickerContaining
	 *
	 */
	@Transactional
	public Set<SecurityPrice> findSecurityPriceByTickerContaining(String ticker) throws DataAccessException {

		return findSecurityPriceByTickerContaining(ticker, -1, -1);
	}

	/**
	 * JPQL Query - findSecurityPriceByTickerContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SecurityPrice> findSecurityPriceByTickerContaining(String ticker, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSecurityPriceByTickerContaining", startResult, maxRows, ticker);
		return new LinkedHashSet<SecurityPrice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSecurityPriceBySecurityPriceId
	 *
	 */
	@Transactional
	public SecurityPrice findSecurityPriceBySecurityPriceId(Integer securityPriceId) throws DataAccessException {

		return findSecurityPriceBySecurityPriceId(securityPriceId, -1, -1);
	}

	/**
	 * JPQL Query - findSecurityPriceBySecurityPriceId
	 *
	 */

	@Transactional
	public SecurityPrice findSecurityPriceBySecurityPriceId(Integer securityPriceId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSecurityPriceBySecurityPriceId", startResult, maxRows, securityPriceId);
			return (com.theta.domain.SecurityPrice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSecurityPriceByPrice
	 *
	 */
	@Transactional
	public Set<SecurityPrice> findSecurityPriceByPrice(Integer price) throws DataAccessException {

		return findSecurityPriceByPrice(price, -1, -1);
	}

	/**
	 * JPQL Query - findSecurityPriceByPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SecurityPrice> findSecurityPriceByPrice(Integer price, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSecurityPriceByPrice", startResult, maxRows, price);
		return new LinkedHashSet<SecurityPrice>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SecurityPrice entity) {
		return true;
	}
	
	/******************************************************************************
	 *    USER DEFINED
	 */
	@Transactional
	public SecurityPrice findDayOpenPriceByDateAndTicker(Calendar cal, String theTicker) throws DataAccessException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String theDateyyyyMMdd = sdf.format(cal.getTime());
		
		try {
			return executeQueryByNameSingleResult("findDayOpenPriceByDateAndTicker", theDateyyyyMMdd, theTicker);
		} catch (NoResultException nre) {
			return null;
		}
	}
	
	@Transactional
	public SecurityPrice findEarliestRecordSince(Calendar cal, String theTicker) throws DataAccessException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmm");
		String theDateyyyyMMddHHmm = sdf.format(cal.getTime());
				
		//System.out.println("Here is theDate to get the earliest since: " + theDateyyyyMMddHHmm);
		try {
			return executeQueryByNameSingleResult("findEarliestRecordSince", theDateyyyyMMddHHmm, theTicker, theTicker);
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public SecurityPrice findLatestRecordBefore(Calendar cal, String theTicker) throws DataAccessException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmm");
		String theDateyyyyMMddHHmm = sdf.format(cal.getTime());
				
		//System.out.println("Here is theDate to get the latest before: " + theDateyyyyMMddHHmm);
		try {
			return executeQueryByNameSingleResult("findLatestRecordBefore", theDateyyyyMMddHHmm, theTicker, theTicker);
		} catch (NoResultException nre) {
			return null;
		}
	}
	
	private static Logger log = Logger.getLogger(SecurityPriceDAOImpl.class);
	
	@Transactional
	public Set<SecurityPrice> findSecurityPricesByTickerAndSourceSinceTime(String ticker, String source, Calendar sinceTime, Calendar toTime) throws DataAccessException {
		Query query = createNamedQuery("findSecurityPricesByTickerAndSourceSinceTime", -1, -1, ticker, source, sinceTime, toTime);
		return new LinkedHashSet<SecurityPrice>(query.getResultList());
	}
	
	@Transactional
	public Set<SecurityPrice> findSecurityPricesByTickerSinceTime(String ticker, Calendar sinceTime, Calendar toTime) throws DataAccessException {
		Query query = createNamedQuery("findSecurityPricesByTickerSinceTime", -1, -1, ticker, sinceTime, toTime);
		return new LinkedHashSet<SecurityPrice>(query.getResultList());
	}
		
	Connection connection1 = null;
	@Transactional
	public synchronized Date findDateOfMinOrMaxPrice(Calendar sinceDateTime, String theTicker, MinOrMaxCode minOrMax) throws DataAccessException, Exception {
					
		ResultSet rs = null;
		Date latestMinOrMaxDate = null;
		PreparedStatement preparedStatement = null;
		//Connection connection = null;
		try{
			if (connection1 == null) {
				connection1 = DriverManager.getConnection(ThetaSQLIF.DB_URL, ThetaSQLIF.DB_USERNAME, ThetaSQLIF.DB_PASSWORD);
			}
			//float price =0;
			//String stratAcctId = "4";
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			String theTimeyyyyMMddHHmm = sdf.format(sinceDateTime.getTime());
			
			String theSql = "";
			if (minOrMax.equals(MinOrMaxCode.MIN))
			   theSql = "select max(created_date) as latest_min_or_max_date from security_price where TO_CHAR(created_date, 'YYYYMMDDHH24mi') > ? and ticker = ? and price = " +
			          "(select min(price) from security_price where TO_CHAR(created_date, 'YYYYMMDDHH24mi') > ? and ticker = ? and price > 0 and used = 'Y' )";
			else if (minOrMax.equals(MinOrMaxCode.MAX)) 
				   theSql = "select max(created_date) as latest_min_or_max_date from security_price where TO_CHAR(created_date, 'YYYYMMDDHH24mi') > ? and ticker = ? and price = " +
					          "(select max(price) from security_price where TO_CHAR(created_date, 'YYYYMMDDHH24mi') > ? and ticker = ? and price > 0 and used = 'Y')";
			else 
				throw new Exception("minOrMax must be MIN or MAX!");

			//System.out.println("Here is theSql: " + theSql);
			
			preparedStatement = connection1.prepareStatement(theSql);
			preparedStatement.setString(1, theTimeyyyyMMddHHmm);
			preparedStatement.setString(2, theTicker);
			preparedStatement.setString(3, theTimeyyyyMMddHHmm);
			preparedStatement.setString(4, theTicker);
			//statement.setString(3,prod_id);
			rs = preparedStatement.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					latestMinOrMaxDate = rs.getDate("latest_min_or_max_date");
					log.info("Here it is: " + latestMinOrMaxDate);
				}
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();
			}
		}catch(SQLException e){
			connection1 = null;
			return null;
		}
		finally{
			try{
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();

			}catch(Exception e)
			{
				return null;
			}
		}
		return latestMinOrMaxDate;

	}

	
	
	@Transactional
	public synchronized Integer findMinOrMaxPriceSinceDateTime(Calendar theDateTime, String theTicker, MinOrMaxCode minOrMax) throws DataAccessException {
		//System.out.println("Top of findMinOrMaxPriceSinceDateTime. Ticker: " + theTicker + ". MinOrMaxCode: " + minOrMax.getValue());
		log.info("Top of findMinOrMaxPriceSinceDateTime. Ticker: " + theTicker + ". MinOrMaxCode: " + minOrMax.getValue());
		ResultSet rs = null;
		Integer minOrMaxPrice = null;
		PreparedStatement preparedStatement = null;
		//Connection connection = null;
		try{
			if (connection1 == null) {
				connection1 = DriverManager.getConnection(ThetaSQLIF.DB_URL, ThetaSQLIF.DB_USERNAME, ThetaSQLIF.DB_PASSWORD);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			String theTimeyyyyMMddHHmm = sdf.format(theDateTime.getTime());
			
			log.info("Middle of findMinOrMaxPriceSinceDateTime. Ticker: " + theTicker + ". MinOrMaxCode: " + minOrMax.getValue());
			String theSql = "";
			if (minOrMax.equals(MinOrMaxCode.MIN)) {
				log.info("minOrMax is MIN");
				theSql = "select min(price) as min_or_max_price from security_price where TO_CHAR(created_date, 'YYYYMMDDHH24mi') > ? and ticker = ? and price > 0 and used = 'Y' ";
			} else if (minOrMax.equals(MinOrMaxCode.MAX)) { 
				log.info("minOrMax is MAX");
				theSql = "select max(price) as min_or_max_price from security_price where TO_CHAR(created_date, 'YYYYMMDDHH24mi') > ? and ticker = ? and price > 0 and used = 'Y' ";
			} else if (minOrMax.equals(MinOrMaxCode.LAST)) {	
				log.info("minOrMax is LAST");
				theSql = "select price as min_or_max_price from security_price where security_price_id = (select max(security_price_id) from security_price where TO_CHAR(created_date, 'YYYYMMDDHH24mi') > ? and ticker = ? and used = 'Y')";
			} else {
				log.info("minOrMax is not in range.  Returning null.  minOrMax: " + minOrMax.getValue());
				return null;
			}
				
			//System.out.println("MinOrMaxPrice.  Sql, Time, Ticker: " + theSql + ", " + theTimeyyyyMMddHHmm + ", " + theTicker);
			log.info("MinOrMaxPrice.  Sql, Time, Ticker: " + theSql + ", " + theTimeyyyyMMddHHmm + ", " + theTicker);
			preparedStatement = connection1.prepareStatement(theSql);
			preparedStatement.setString(1, theTimeyyyyMMddHHmm);
			preparedStatement.setString(2, theTicker);
			rs = preparedStatement.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					minOrMaxPrice = rs.getInt("min_or_max_price");
					//System.out.println("MinOrMaxPrice for " + theTicker + ": " + minOrMaxPrice);
					log.info("MinOrMaxPrice for " + theTicker + ": " + minOrMaxPrice);
				}
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();
			}
		}catch(SQLException e){
			//e.printStackTrace();
			log.error("Problem getting minOrMax price. Ticker: " + theTicker, e);
			connection1 = null;
			return null;
		}
		finally{
			try{
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();

			}catch(Exception e)
			{
				return null;
			}
		}
		return minOrMaxPrice;

	}
	
	@Transactional
	public SecurityPrice findLastBusinessDay() throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findLastBusinessDay");
		} catch (NoResultException nre) {
			return null;
		}
	}

	
	@Transactional
	public synchronized Integer findMovingAverage(Calendar fromDate, Calendar toDate, String theTicker) throws DataAccessException {

		ResultSet rs = null;
		Integer movingAverage = null;
		PreparedStatement preparedStatement = null;
		//Connection connection = null;
		try{
			if (connection1 == null) {
				connection1 = DriverManager.getConnection(ThetaSQLIF.DB_URL, ThetaSQLIF.DB_USERNAME, ThetaSQLIF.DB_PASSWORD);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String fromDateyyyyMMdd = sdf.format(fromDate.getTime());
			String toDateyyyyMMdd = sdf.format(toDate.getTime());
			
			String theSql = "select trunc(avg(price)) as average_price from " +
	                        "(" +
	                        "select max(ticker), max(price) as price, to_char(created_date, 'YYYYMMDD') " +
	                        "from security_price " +
	                        "where " +
	                        "to_char(created_date,'YYYYMMDD') > ? " +
	                        "and " +
	                        "to_char(created_date, 'YYYYMMDD') < ? " +
	                        "and ticker = ? " +
	                        "and price > 0 " +
	                        "group by to_char(created_date,'YYYYMMDD') " +
	                        "union " +
	                        "select min(ticker), min(price) as price, to_char(created_date, 'YYYYMMDD') " +
	                        "from security_price " +
	                        "where " +
	                        "to_char(created_date,'YYYYMMDD') > ? " +
	                        "and " +
	                        "to_char(created_date,'YYYYMMDD') <  ? " +
	                        "and ticker = ? " +
	                        "and price > 0 and used = 'Y' " +
	                        "group by to_char(created_date,'YYYYMMDD') " +
	                        ")";
			
			System.out.println("Here is the SQL statement: " + theSql + ".  Parms 1,2,3: " + fromDateyyyyMMdd + " : " + toDateyyyyMMdd + " : " + theTicker);

			preparedStatement = connection1.prepareStatement(theSql);
			preparedStatement.setString(1, fromDateyyyyMMdd);
			preparedStatement.setString(2, toDateyyyyMMdd);
			preparedStatement.setString(3, theTicker);
			preparedStatement.setString(4, fromDateyyyyMMdd);
			preparedStatement.setString(5, toDateyyyyMMdd);
			preparedStatement.setString(6, theTicker);
			
			rs = preparedStatement.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					movingAverage = rs.getInt("average_price");
					System.out.println("Here is the movingAverage in SecurityPriceDAOImpl: " + movingAverage);
				}
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();
			}
		}catch(SQLException e){
			log.error("Here is SQLException", e);
			connection1 = null;
			return null;
		}
		finally{
			try{
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();
				//if(connection!=null) connection.close();
			}catch(Exception e)
			{
				return null;
			}
		}
		return movingAverage;
	}


	@Transactional
	public synchronized Integer findMovingAverageDailyRange(Calendar fromDate, Calendar toDate, String theTicker) throws DataAccessException {

		ResultSet rs = null;
		Integer movingAverageDailyRange = null;
		PreparedStatement preparedStatement = null;
		//Connection connection = null;
		try{
			if (connection1 == null) {
				connection1 = DriverManager.getConnection(ThetaSQLIF.DB_URL, ThetaSQLIF.DB_USERNAME, ThetaSQLIF.DB_PASSWORD);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String fromDateyyyyMMdd = sdf.format(fromDate.getTime());
			String toDateyyyyMMdd = sdf.format(toDate.getTime());
			
			String theSql = 
					"select trunc(avg(dailyRange)) as average_daily_range from " +
					"(" +
					"select max(ticker), (max(price) - min(price)) as dailyRange, to_char(created_date, 'YYYYMMDD') " +
					"from security_price " +
					"where " +
					"to_char(created_date,'YYYYMMDD') > ? " +
					"and " +
					"to_char(created_date, 'YYYYMMDD') < ? " +
					"and ticker = ? " +
					"and price > 0 and used = 'Y' " +
					"group by to_char(created_date,'YYYYMMDD')" +
					")" ;
			
			log.info("Here is the SQL statement: " + theSql + ".  Parms 1,2,3: " + fromDateyyyyMMdd + " : " + toDateyyyyMMdd + " : " + theTicker);

			preparedStatement = connection1.prepareStatement(theSql);
			preparedStatement.setString(1, fromDateyyyyMMdd);
			preparedStatement.setString(2, toDateyyyyMMdd);
			preparedStatement.setString(3, theTicker);
			
			rs = preparedStatement.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					movingAverageDailyRange = rs.getInt("average_daily_range");
					System.out.println("Here is the movingAverageDailyRange in SecurityPriceDAOImpl: " + movingAverageDailyRange);
				}
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();
			}
		}catch(SQLException e){
			log.error("Here is SQLException", e);
			connection1 = null;
			return null;
		}
		finally{
			try{
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();
				//if(connection!=null) connection.close();
			}catch(Exception e)
			{
				return null;
			}
		}
		return movingAverageDailyRange;
	}

	@Transactional
	public synchronized Integer findNumRecordsOnDate(Calendar theDate, String theTicker) throws DataAccessException {
		
		ResultSet rs = null;
		Integer numRecs = null;
		PreparedStatement preparedStatement = null;
		try{

			if (connection1 == null) {
				connection1 = DriverManager.getConnection(ThetaSQLIF.DB_URL, ThetaSQLIF.DB_USERNAME, ThetaSQLIF.DB_PASSWORD);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String theDateyyyyMMdd = sdf.format(theDate.getTime());
			
			String theSql = "select count(*) as num_recs from security_price " + 
				"where TO_CHAR(created_date, 'YYYYMMDD')=?1  and ticker=?2";
			
			System.out.println("Here is the SQL statement: " + theSql + ".  Parms 1,2: " + theDateyyyyMMdd + " : " + theTicker);

			preparedStatement = connection1.prepareStatement(theSql);
			preparedStatement.setString(1, theDateyyyyMMdd);
			preparedStatement.setString(2, theTicker);
			
			rs = preparedStatement.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					numRecs = rs.getInt("num_recs");
				}
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();
			}
		}catch(SQLException e){
			log.error("Here is SQLException", e);
			connection1 = null;
			return null;
		}
		finally{
			try{
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();
				//if(connection!=null) connection.close();
			}catch(Exception e)
			{
				return null;
			}
		}
		return numRecs;
	}

	public synchronized void synchronizedStoreAndFlush(SecurityPrice securityPrice){
		this.store(securityPrice);
		this.flush();
	}

	
}

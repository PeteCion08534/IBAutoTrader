package com.theta.dao;

import com.theta.db.ThetaSQLIF;
import com.theta.domain.ProfitLoss;
import com.theta.domain.StrategyAccount;
import com.theta.enums.MinOrMaxCode;
import com.theta.process.ThetaConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
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
 * DAO to manage ProfitLoss entities.
 * 
 */
@Repository("ProfitLossDAO")
@Transactional
public class ProfitLossDAOImpl extends AbstractJpaDao implements ProfitLossDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { ProfitLoss.class }));

	/**
	 * EntityManager injected by Spring for persistence unit LocOracleTheta
	 *
	 */
	@PersistenceContext(unitName = "LocOracleTheta")
	private EntityManager entityManager;

	/**
	 * Instantiates a new ProfitLossDAOImpl
	 *
	 */
	public ProfitLossDAOImpl() {
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
	 * JPQL Query - findProfitLossByUpdatedByContaining
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByUpdatedByContaining(String updatedBy) throws DataAccessException {

		return findProfitLossByUpdatedByContaining(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByUpdatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByUpdatedByContaining", startResult, maxRows, updatedBy);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossStartDate
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitLossStartDate(java.util.Calendar profitLossStartDate) throws DataAccessException {

		return findProfitLossByProfitLossStartDate(profitLossStartDate, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossStartDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitLossStartDate(java.util.Calendar profitLossStartDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByProfitLossStartDate", startResult, maxRows, profitLossStartDate);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossStartDateBefore
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitLossStartDateBefore(java.util.Calendar profitLossStartDate) throws DataAccessException {

		return findProfitLossByProfitLossStartDateBefore(profitLossStartDate, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossStartDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitLossStartDateBefore(java.util.Calendar profitLossStartDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByProfitLossStartDateBefore", startResult, maxRows, profitLossStartDate);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByCreatedByContaining(String createdBy) throws DataAccessException {

		return findProfitLossByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByPrimaryKey
	 *
	 */
	@Transactional
	public ProfitLoss findProfitLossByPrimaryKey(Integer profitLossId) throws DataAccessException {

		return findProfitLossByPrimaryKey(profitLossId, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByPrimaryKey
	 *
	 */

	@Transactional
	public ProfitLoss findProfitLossByPrimaryKey(Integer profitLossId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findProfitLossByPrimaryKey", profitLossId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllProfitLosss
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findAllProfitLosss() throws DataAccessException {

		return findAllProfitLosss(-1, -1);
	}

	/**
	 * JPQL Query - findAllProfitLosss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findAllProfitLosss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllProfitLosss", startResult, maxRows);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossStartDateAfter
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitLossStartDateAfter(java.util.Calendar profitLossStartDate) throws DataAccessException {

		return findProfitLossByProfitLossStartDateAfter(profitLossStartDate, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossStartDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitLossStartDateAfter(java.util.Calendar profitLossStartDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByProfitLossStartDateAfter", startResult, maxRows, profitLossStartDate);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByCreatedDate
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findProfitLossByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByCreatedBy
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByCreatedBy(String createdBy) throws DataAccessException {

		return findProfitLossByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossDateAfter
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitLossDateAfter(java.util.Calendar profitLossDate) throws DataAccessException {

		return findProfitLossByProfitLossDateAfter(profitLossDate, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitLossDateAfter(java.util.Calendar profitLossDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByProfitLossDateAfter", startResult, maxRows, profitLossDate);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByUpdatedDate
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findProfitLossByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByProfitOrLossContaining
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitOrLossContaining(String profitOrLoss) throws DataAccessException {

		return findProfitLossByProfitOrLossContaining(profitOrLoss, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByProfitOrLossContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitOrLossContaining(String profitOrLoss, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByProfitOrLossContaining", startResult, maxRows, profitOrLoss);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossId
	 *
	 */
	@Transactional
	public ProfitLoss findProfitLossByProfitLossId(Integer profitLossId) throws DataAccessException {

		return findProfitLossByProfitLossId(profitLossId, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossId
	 *
	 */

	@Transactional
	public ProfitLoss findProfitLossByProfitLossId(Integer profitLossId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findProfitLossByProfitLossId", profitLossId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findProfitLossByCreatedDateAfter
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException {

		return findProfitLossByCreatedDateAfter(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByCreatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByCreatedDateAfter(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByCreatedDateAfter", startResult, maxRows, createdDate);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByProfitOrLossAmt
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitOrLossAmt(java.math.BigDecimal profitOrLossAmt) throws DataAccessException {

		return findProfitLossByProfitOrLossAmt(profitOrLossAmt, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByProfitOrLossAmt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitOrLossAmt(java.math.BigDecimal profitOrLossAmt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByProfitOrLossAmt", startResult, maxRows, profitOrLossAmt);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossDateBefore
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitLossDateBefore(java.util.Calendar profitLossDate) throws DataAccessException {

		return findProfitLossByProfitLossDateBefore(profitLossDate, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitLossDateBefore(java.util.Calendar profitLossDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByProfitLossDateBefore", startResult, maxRows, profitLossDate);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByUpdatedBy
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByUpdatedBy(String updatedBy) throws DataAccessException {

		return findProfitLossByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossDate
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitLossDate(java.util.Calendar profitLossDate) throws DataAccessException {

		return findProfitLossByProfitLossDate(profitLossDate, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByProfitLossDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitLossDate(java.util.Calendar profitLossDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByProfitLossDate", startResult, maxRows, profitLossDate);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByProfitOrLoss
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitOrLoss(String profitOrLoss) throws DataAccessException {

		return findProfitLossByProfitOrLoss(profitOrLoss, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByProfitOrLoss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByProfitOrLoss(String profitOrLoss, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByProfitOrLoss", startResult, maxRows, profitOrLoss);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByUpdatedDateAfter
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByUpdatedDateAfter(java.util.Calendar updatedDate) throws DataAccessException {

		return findProfitLossByUpdatedDateAfter(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByUpdatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByUpdatedDateAfter(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByUpdatedDateAfter", startResult, maxRows, updatedDate);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}

	/**
	 * JPQL Query - findProfitLossByUpdatedDateBefore
	 *
	 */
	//@Transactional
	//public Set<ProfitLoss> findProfitLossByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException {

	//	return findProfitLossByUpdatedDateBefore(updatedDate, -1, -1);
	//}

	/**
	 * JPQL Query - findProfitLossByUpdatedDateBefore
	 *
	 */

	//@SuppressWarnings("unchecked")
	//@Transactional
	//public Set<ProfitLoss> findProfitLossByUpdatedDateBefore(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
	//	Query query = createNamedQuery("findProfitLossByUpdatedDateBefore", startResult, maxRows, updatedDate);
	//	return new LinkedHashSet<ProfitLoss>(query.getResultList());
	//}

	/**
	 * JPQL Query - findProfitLossByCreatedDateBefore
	 *
	 */
	@Transactional
	public Set<ProfitLoss> findProfitLossByCreatedDateBefore(java.util.Calendar createdDate) throws DataAccessException {

		return findProfitLossByCreatedDateBefore(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findProfitLossByCreatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ProfitLoss> findProfitLossByCreatedDateBefore(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProfitLossByCreatedDateBefore", startResult, maxRows, createdDate);
		return new LinkedHashSet<ProfitLoss>(query.getResultList());
	}
	
	
	
	/**
	 * ********************** USER DEFINED ***************************************************
	 */
	
	public synchronized void synchronizedStoreAndFlush(ProfitLoss profitLoss) {
		this.store(profitLoss);
		this.flush();
	}
	
	@Transactional
	public ProfitLoss rollUpDirect(StrategyAccount strategyAccount) throws DataAccessException {
					
		ResultSet rs = null;
		ProfitLoss profitLoss = new ProfitLoss();
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(ThetaSQLIF.DB_URL, ThetaSQLIF.DB_USERNAME, ThetaSQLIF.DB_PASSWORD);
			
			String theSql = "select strat, " + 
			"sum(real) + sum(unreal) + sum(enter) + sum(exit) as tot, " +
			"sum(real) sumreal, " +
			"sum(unreal) sumunreal, " +
			"sum(enter) sumenter, " +
			"sum(exit) sumexit, " +
			"sum(risk) sumrisk " +
			"from " +
			"(select " + 
			"sp.spread_id id, " +
			"sp.open_or_closed open, " +
			"pos.strategy_account_id strat," + 
			"pos.total_risked risk, " +
			"sp.profit_loss_unrealized unreal, " +
			"sp.profit_loss_realized real, " +
			"sp.enter_commission enter, " +
			"sp.exit_commission exit " +
			"sp.current_security_price security " +
			"from spread sp, position pos " +
			"where sp.position_id = pos.position_id " +
			"and strategy_account_id = ? )" +
			"group by strat " +
			"order by strat";
					
			preparedStatement = connection.prepareStatement(theSql);
			preparedStatement.setString(1, Integer.toString(strategyAccount.getStrategyAccountId()));
			rs = preparedStatement.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					profitLoss.setProfitLossId(ThetaConstants.INIT_ID);
					
					profitLoss.setStrategyAccount(strategyAccount);

					Integer sumReal = rs.getInt("sumreal");
					Integer sumUnreal = rs.getInt("sumunreal");
					Integer sumRisk = rs.getInt("sumrisk");
					profitLoss.setPlRealized(sumReal);
					profitLoss.setPlUnrealized(sumUnreal);
					profitLoss.setTotalRisked(sumRisk);
					profitLoss.setCreatedDate(Calendar.getInstance());

					if (sumRisk != 0){
						profitLoss.setScorePct(((sumReal + sumUnreal) * ThetaConstants.TEN_THOUSAND_INT) / sumRisk);
					}
				}
				if(rs!=null)rs.close();
				if(preparedStatement!=null) preparedStatement.close();
			}
		}catch(SQLException e){
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
		return profitLoss;
	}
}
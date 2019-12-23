package com.theta.dao;

import com.theta.domain.ProfitLoss;
import com.theta.domain.StrategyAccount;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage ProfitLoss entities.
 * 
 */
public interface ProfitLossDAO extends JpaDao {

	/**
	 * JPQL Query - findProfitLossByUpdatedByContaining
	 *
	 */
	public Set<ProfitLoss> findProfitLossByUpdatedByContaining(String updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByUpdatedByContaining
	 *
	 */
	public Set<ProfitLoss> findProfitLossByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossStartDate
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitLossStartDate(java.util.Calendar profitLossStartDate) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossStartDate
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitLossStartDate(Calendar profitLossStartDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossStartDateBefore
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitLossStartDateBefore(java.util.Calendar profitLossStartDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossStartDateBefore
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitLossStartDateBefore(Calendar profitLossStartDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByCreatedByContaining
	 *
	 */
	public Set<ProfitLoss> findProfitLossByCreatedByContaining(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByCreatedByContaining
	 *
	 */
	public Set<ProfitLoss> findProfitLossByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByPrimaryKey
	 *
	 */
	public ProfitLoss findProfitLossByPrimaryKey(Integer profitLossId) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByPrimaryKey
	 *
	 */
	public ProfitLoss findProfitLossByPrimaryKey(Integer profitLossId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllProfitLosss
	 *
	 */
	public Set<ProfitLoss> findAllProfitLosss() throws DataAccessException;

	/**
	 * JPQL Query - findAllProfitLosss
	 *
	 */
	public Set<ProfitLoss> findAllProfitLosss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossStartDateAfter
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitLossStartDateAfter(java.util.Calendar profitLossStartDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossStartDateAfter
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitLossStartDateAfter(Calendar profitLossStartDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByCreatedDate
	 *
	 */
	public Set<ProfitLoss> findProfitLossByCreatedDate(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByCreatedDate
	 *
	 */
	public Set<ProfitLoss> findProfitLossByCreatedDate(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByCreatedBy
	 *
	 */
	public Set<ProfitLoss> findProfitLossByCreatedBy(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByCreatedBy
	 *
	 */
	public Set<ProfitLoss> findProfitLossByCreatedBy(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossDateAfter
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitLossDateAfter(java.util.Calendar profitLossDate) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossDateAfter
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitLossDateAfter(Calendar profitLossDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByUpdatedDate
	 *
	 */
	public Set<ProfitLoss> findProfitLossByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByUpdatedDate
	 *
	 */
	public Set<ProfitLoss> findProfitLossByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitOrLossContaining
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitOrLossContaining(String profitOrLoss) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitOrLossContaining
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitOrLossContaining(String profitOrLoss, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossId
	 *
	 */
	public ProfitLoss findProfitLossByProfitLossId(Integer profitLossId_1) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossId
	 *
	 */
	public ProfitLoss findProfitLossByProfitLossId(Integer profitLossId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByCreatedDateAfter
	 *
	 */
	public Set<ProfitLoss> findProfitLossByCreatedDateAfter(java.util.Calendar createdDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByCreatedDateAfter
	 *
	 */
	public Set<ProfitLoss> findProfitLossByCreatedDateAfter(Calendar createdDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitOrLossAmt
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitOrLossAmt(java.math.BigDecimal profitOrLossAmt) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitOrLossAmt
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitOrLossAmt(BigDecimal profitOrLossAmt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossDateBefore
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitLossDateBefore(java.util.Calendar profitLossDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossDateBefore
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitLossDateBefore(Calendar profitLossDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByUpdatedBy
	 *
	 */
	public Set<ProfitLoss> findProfitLossByUpdatedBy(String updatedBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByUpdatedBy
	 *
	 */
	public Set<ProfitLoss> findProfitLossByUpdatedBy(String updatedBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossDate
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitLossDate(java.util.Calendar profitLossDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitLossDate
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitLossDate(Calendar profitLossDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitOrLoss
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitOrLoss(String profitOrLoss_1) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByProfitOrLoss
	 *
	 */
	public Set<ProfitLoss> findProfitLossByProfitOrLoss(String profitOrLoss_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByUpdatedDateAfter
	 *
	 */
	public Set<ProfitLoss> findProfitLossByUpdatedDateAfter(java.util.Calendar updatedDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByUpdatedDateAfter
	 *
	 */
	public Set<ProfitLoss> findProfitLossByUpdatedDateAfter(Calendar updatedDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByUpdatedDateBefore
	 *
	 */
	//public Set<ProfitLoss> findProfitLossByUpdatedDateBefore(java.util.Calendar updatedDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByUpdatedDateBefore
	 *
	 */
	//public Set<ProfitLoss> findProfitLossByUpdatedDateBefore(Calendar updatedDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByCreatedDateBefore
	 *
	 */
	public Set<ProfitLoss> findProfitLossByCreatedDateBefore(java.util.Calendar createdDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findProfitLossByCreatedDateBefore
	 *
	 */
	public Set<ProfitLoss> findProfitLossByCreatedDateBefore(Calendar createdDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * ********************** USER DEFINED ***************************************************
	 */
	public ProfitLoss rollUpDirect(StrategyAccount strategyAccount) throws DataAccessException;
	
	public  void synchronizedStoreAndFlush(ProfitLoss profitLoss);

}
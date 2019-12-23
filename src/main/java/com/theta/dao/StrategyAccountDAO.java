package com.theta.dao;

import com.theta.domain.StrategyAccount;

import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage StrategyAccount entities.
 * 
 */
public interface StrategyAccountDAO extends JpaDao {

	/**
	 * JPQL Query - findStrategyAccountByStrategyAccountId
	 *
	 */
	public StrategyAccount findStrategyAccountByStrategyAccountId(Integer strategyAccountId) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyAccountByStrategyAccountId
	 *
	 */
	public StrategyAccount findStrategyAccountByStrategyAccountId(Integer strategyAccountId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyAccountByPrimaryKey
	 *
	 */
	public StrategyAccount findStrategyAccountByPrimaryKey(Integer strategyAccountId_1) throws DataAccessException;

	/**
	 * JPQL Query - findStrategyAccountByPrimaryKey
	 *
	 */
	public StrategyAccount findStrategyAccountByPrimaryKey(Integer strategyAccountId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllStrategyAccounts
	 *
	 */
	public Set<StrategyAccount> findAllStrategyAccounts() throws DataAccessException;

	/**
	 * JPQL Query - findAllStrategyAccounts
	 *
	 */
	public Set<StrategyAccount> findAllStrategyAccounts(int startResult, int maxRows) throws DataAccessException;

}
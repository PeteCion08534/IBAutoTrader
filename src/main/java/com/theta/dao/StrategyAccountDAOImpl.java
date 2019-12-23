package com.theta.dao;

import com.theta.domain.StrategyAccount;

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
 * DAO to manage StrategyAccount entities.
 * 
 */
@Repository("StrategyAccountDAO")
@Transactional
public class StrategyAccountDAOImpl extends AbstractJpaDao implements
		StrategyAccountDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { StrategyAccount.class }));

	/**
	 * EntityManager injected by Spring for persistence unit LocOracleTheta
	 *
	 */
	@PersistenceContext(unitName = "LocOracleTheta")
	private EntityManager entityManager;

	/**
	 * Instantiates a new StrategyAccountDAOImpl
	 *
	 */
	public StrategyAccountDAOImpl() {
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
	 * JPQL Query - findStrategyAccountByStrategyAccountId
	 *
	 */
	@Transactional
	public StrategyAccount findStrategyAccountByStrategyAccountId(Integer strategyAccountId) throws DataAccessException {

		return findStrategyAccountByStrategyAccountId(strategyAccountId, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyAccountByStrategyAccountId
	 *
	 */

	@Transactional
	public StrategyAccount findStrategyAccountByStrategyAccountId(Integer strategyAccountId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findStrategyAccountByStrategyAccountId", strategyAccountId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findStrategyAccountByPrimaryKey
	 *
	 */
	@Transactional
	public StrategyAccount findStrategyAccountByPrimaryKey(Integer strategyAccountId) throws DataAccessException {

		return findStrategyAccountByPrimaryKey(strategyAccountId, -1, -1);
	}

	/**
	 * JPQL Query - findStrategyAccountByPrimaryKey
	 *
	 */

	@Transactional
	public StrategyAccount findStrategyAccountByPrimaryKey(Integer strategyAccountId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findStrategyAccountByPrimaryKey", strategyAccountId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllStrategyAccounts
	 *
	 */
	@Transactional
	public Set<StrategyAccount> findAllStrategyAccounts() throws DataAccessException {

		return findAllStrategyAccounts(-1, -1);
	}

	/**
	 * JPQL Query - findAllStrategyAccounts
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<StrategyAccount> findAllStrategyAccounts(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllStrategyAccounts", startResult, maxRows);
		return new LinkedHashSet<StrategyAccount>(query.getResultList());
	}

}

package com.theta.dao;

import com.theta.domain.Position;
import com.theta.domain.Spread;

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
 * DAO to manage Spread entities.
 * 
 */
@Repository("SpreadDAO")
@Transactional
public class SpreadDAOImpl extends AbstractJpaDao implements SpreadDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Spread.class }));

	/**
	 * EntityManager injected by Spring for persistence unit LocOracleTheta
	 *
	 */
	@PersistenceContext(unitName = "LocOracleTheta")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SpreadDAOImpl
	 *
	 */
	public SpreadDAOImpl() {
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
	 * JPQL Query - findSpreadByStrikeInsurance
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByStrikeInsurance(java.math.BigDecimal strikeInsurance) throws DataAccessException {

		return findSpreadByStrikeInsurance(strikeInsurance, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByStrikeInsurance
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByStrikeInsurance(java.math.BigDecimal strikeInsurance, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByStrikeInsurance", startResult, maxRows, strikeInsurance);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByCreatedDateAfter
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException {

		return findSpreadByCreatedDateAfter(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByCreatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByCreatedDateAfter(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByCreatedDateAfter", startResult, maxRows, createdDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByCreatedByContaining(String createdBy) throws DataAccessException {

		return findSpreadByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByCreatedBy
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByCreatedBy(String createdBy) throws DataAccessException {

		return findSpreadByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByCurrentDate
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByCurrentDate(java.util.Calendar currentDate) throws DataAccessException {

		return findSpreadByCurrentDate(currentDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByCurrentDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByCurrentDate(java.util.Calendar currentDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByCurrentDate", startResult, maxRows, currentDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitInsurancePrice
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitInsurancePrice(java.math.BigDecimal exitInsurancePrice) throws DataAccessException {

		return findSpreadByExitInsurancePrice(exitInsurancePrice, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitInsurancePrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitInsurancePrice(java.math.BigDecimal exitInsurancePrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitInsurancePrice", startResult, maxRows, exitInsurancePrice);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByCurrentDateAfter
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByCurrentDateAfter(java.util.Calendar currentDate) throws DataAccessException {

		return findSpreadByCurrentDateAfter(currentDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByCurrentDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByCurrentDateAfter(java.util.Calendar currentDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByCurrentDateAfter", startResult, maxRows, currentDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByCurrentMoneymkrPrice
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByCurrentMoneymkrPrice(java.math.BigDecimal currentMoneymkrPrice) throws DataAccessException {

		return findSpreadByCurrentMoneymkrPrice(currentMoneymkrPrice, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByCurrentMoneymkrPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByCurrentMoneymkrPrice(java.math.BigDecimal currentMoneymkrPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByCurrentMoneymkrPrice", startResult, maxRows, currentMoneymkrPrice);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByUpdatedByContaining
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByUpdatedByContaining(String updatedBy) throws DataAccessException {

		return findSpreadByUpdatedByContaining(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByUpdatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByUpdatedByContaining", startResult, maxRows, updatedBy);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitMoneymkrDate
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitMoneymkrDate(java.util.Calendar exitMoneymkrDate) throws DataAccessException {

		return findSpreadByExitMoneymkrDate(exitMoneymkrDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitMoneymkrDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitMoneymkrDate(java.util.Calendar exitMoneymkrDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitMoneymkrDate", startResult, maxRows, exitMoneymkrDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByUpdatedBy
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByUpdatedBy(String updatedBy) throws DataAccessException {

		return findSpreadByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitSecurityDateBefore
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitSecurityDateBefore(java.util.Calendar exitSecurityDate) throws DataAccessException {

		return findSpreadByExitSecurityDateBefore(exitSecurityDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitSecurityDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitSecurityDateBefore(java.util.Calendar exitSecurityDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitSecurityDateBefore", startResult, maxRows, exitSecurityDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitCommission
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitCommission(java.math.BigDecimal exitCommission) throws DataAccessException {

		return findSpreadByExitCommission(exitCommission, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitCommission
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitCommission(java.math.BigDecimal exitCommission, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitCommission", startResult, maxRows, exitCommission);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByUpdatedDateBefore
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException {

		return findSpreadByUpdatedDateBefore(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByUpdatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByUpdatedDateBefore(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByUpdatedDateBefore", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByNumSpreads
	 *
	 */
	//@Transactional
	//public Set<Spread> findSpreadByNumSpreads(Integer numSpreads) throws DataAccessException {

	//	return findSpreadByNumSpreads(numSpreads, -1, -1);
	//}

	/**
	 * JPQL Query - findSpreadByNumSpreads
	 *
	 */

	//@SuppressWarnings("unchecked")
	//@Transactional
	//public Set<Spread> findSpreadByNumSpreads(Integer numSpreads, int startResult, int maxRows) throws DataAccessException {
	//	Query query = createNamedQuery("findSpreadByNumSpreads", startResult, maxRows, numSpreads);
	//	return new LinkedHashSet<Spread>(query.getResultList());
	//}

	/**
	 * JPQL Query - findSpreadByEnterCommission
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByEnterCommission(java.math.BigDecimal enterCommission) throws DataAccessException {

		return findSpreadByEnterCommission(enterCommission, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByEnterCommission
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByEnterCommission(java.math.BigDecimal enterCommission, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByEnterCommission", startResult, maxRows, enterCommission);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitMoneymkrPrice
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitMoneymkrPrice(java.math.BigDecimal exitMoneymkrPrice) throws DataAccessException {

		return findSpreadByExitMoneymkrPrice(exitMoneymkrPrice, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitMoneymkrPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitMoneymkrPrice(java.math.BigDecimal exitMoneymkrPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitMoneymkrPrice", startResult, maxRows, exitMoneymkrPrice);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitBelowSpreadValue
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitBelowSpreadValue(java.math.BigDecimal exitBelowSpreadValue) throws DataAccessException {

		return findSpreadByExitBelowSpreadValue(exitBelowSpreadValue, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitBelowSpreadValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitBelowSpreadValue(java.math.BigDecimal exitBelowSpreadValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitBelowSpreadValue", startResult, maxRows, exitBelowSpreadValue);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByEnterSecurityDateBefore
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByEnterSecurityDateBefore(java.util.Calendar enterSecurityDate) throws DataAccessException {

		return findSpreadByEnterSecurityDateBefore(enterSecurityDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByEnterSecurityDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByEnterSecurityDateBefore(java.util.Calendar enterSecurityDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByEnterSecurityDateBefore", startResult, maxRows, enterSecurityDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrDate
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByEnterMoneymkrDate(java.util.Calendar enterMoneymkrDate) throws DataAccessException {

		return findSpreadByEnterMoneymkrDate(enterMoneymkrDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByEnterMoneymkrDate(java.util.Calendar enterMoneymkrDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByEnterMoneymkrDate", startResult, maxRows, enterMoneymkrDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByPrimaryKey
	 *
	 */
	@Transactional
	public Spread findSpreadByPrimaryKey(Integer spreadId) throws DataAccessException {

		return findSpreadByPrimaryKey(spreadId, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByPrimaryKey
	 *
	 */

	@Transactional
	public Spread findSpreadByPrimaryKey(Integer spreadId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findSpreadByPrimaryKey", spreadId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSpreadByUpdatedDate
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findSpreadByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByEnterSecurityPrice
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByEnterSecurityPrice(java.math.BigDecimal enterSecurityPrice) throws DataAccessException {

		return findSpreadByEnterSecurityPrice(enterSecurityPrice, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByEnterSecurityPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByEnterSecurityPrice(java.math.BigDecimal enterSecurityPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByEnterSecurityPrice", startResult, maxRows, enterSecurityPrice);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitSecurityDateAfter
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitSecurityDateAfter(java.util.Calendar exitSecurityDate) throws DataAccessException {

		return findSpreadByExitSecurityDateAfter(exitSecurityDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitSecurityDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitSecurityDateAfter(java.util.Calendar exitSecurityDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitSecurityDateAfter", startResult, maxRows, exitSecurityDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByOpenOrClosedContaining
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByOpenOrClosedContaining(String openOrClosed) throws DataAccessException {

		return findSpreadByOpenOrClosedContaining(openOrClosed, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByOpenOrClosedContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByOpenOrClosedContaining(String openOrClosed, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByOpenOrClosedContaining", startResult, maxRows, openOrClosed);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByEnterSecurityDate
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByEnterSecurityDate(java.util.Calendar enterSecurityDate) throws DataAccessException {

		return findSpreadByEnterSecurityDate(enterSecurityDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByEnterSecurityDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByEnterSecurityDate(java.util.Calendar enterSecurityDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByEnterSecurityDate", startResult, maxRows, enterSecurityDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrPrice
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByEnterMoneymkrPrice(java.math.BigDecimal enterMoneymkrPrice) throws DataAccessException {

		return findSpreadByEnterMoneymkrPrice(enterMoneymkrPrice, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByEnterMoneymkrPrice(java.math.BigDecimal enterMoneymkrPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByEnterMoneymkrPrice", startResult, maxRows, enterMoneymkrPrice);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitSecurityPrice
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitSecurityPrice(java.math.BigDecimal exitSecurityPrice) throws DataAccessException {

		return findSpreadByExitSecurityPrice(exitSecurityPrice, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitSecurityPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitSecurityPrice(java.math.BigDecimal exitSecurityPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitSecurityPrice", startResult, maxRows, exitSecurityPrice);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByEnterInsuranceDateAfter
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByEnterInsuranceDateAfter(java.util.Calendar enterInsuranceDate) throws DataAccessException {

		return findSpreadByEnterInsuranceDateAfter(enterInsuranceDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByEnterInsuranceDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByEnterInsuranceDateAfter(java.util.Calendar enterInsuranceDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByEnterInsuranceDateAfter", startResult, maxRows, enterInsuranceDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByEnterInsuranceDateBefore
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByEnterInsuranceDateBefore(java.util.Calendar enterInsuranceDate) throws DataAccessException {

		return findSpreadByEnterInsuranceDateBefore(enterInsuranceDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByEnterInsuranceDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByEnterInsuranceDateBefore(java.util.Calendar enterInsuranceDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByEnterInsuranceDateBefore", startResult, maxRows, enterInsuranceDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByUpdatedDateAfter
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByUpdatedDateAfter(java.util.Calendar updatedDate) throws DataAccessException {

		return findSpreadByUpdatedDateAfter(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByUpdatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByUpdatedDateAfter(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByUpdatedDateAfter", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByCurrentVixPrice
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByCurrentVixPrice(java.math.BigDecimal currentVixPrice) throws DataAccessException {

		return findSpreadByCurrentVixPrice(currentVixPrice, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByCurrentVixPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByCurrentVixPrice(java.math.BigDecimal currentVixPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByCurrentVixPrice", startResult, maxRows, currentVixPrice);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByProfitLossRealized
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByProfitLossRealized(java.math.BigDecimal profitLossRealized) throws DataAccessException {

		return findSpreadByProfitLossRealized(profitLossRealized, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByProfitLossRealized
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByProfitLossRealized(java.math.BigDecimal profitLossRealized, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByProfitLossRealized", startResult, maxRows, profitLossRealized);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByProfitLossUnrealized
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByProfitLossUnrealized(java.math.BigDecimal profitLossUnrealized) throws DataAccessException {

		return findSpreadByProfitLossUnrealized(profitLossUnrealized, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByProfitLossUnrealized
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByProfitLossUnrealized(java.math.BigDecimal profitLossUnrealized, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByProfitLossUnrealized", startResult, maxRows, profitLossUnrealized);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByEnterInsurancePrice
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByEnterInsurancePrice(java.math.BigDecimal enterInsurancePrice) throws DataAccessException {

		return findSpreadByEnterInsurancePrice(enterInsurancePrice, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByEnterInsurancePrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByEnterInsurancePrice(java.math.BigDecimal enterInsurancePrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByEnterInsurancePrice", startResult, maxRows, enterInsurancePrice);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadBySpreadId
	 *
	 */
	@Transactional
	public Spread findSpreadBySpreadId(Integer spreadId) throws DataAccessException {

		return findSpreadBySpreadId(spreadId, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadBySpreadId
	 *
	 */

	@Transactional
	public Spread findSpreadBySpreadId(Integer spreadId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findSpreadBySpreadId", spreadId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSpreadByTrailingDaysAndHours
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByTrailingDaysAndHours(String trailingDaysAndHours) throws DataAccessException {

		return findSpreadByTrailingDaysAndHours(trailingDaysAndHours, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByTrailingDaysAndHours
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByTrailingDaysAndHours(String trailingDaysAndHours, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByTrailingDaysAndHours", startResult, maxRows, trailingDaysAndHours);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByEnterInsuranceDate
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByEnterInsuranceDate(java.util.Calendar enterInsuranceDate) throws DataAccessException {

		return findSpreadByEnterInsuranceDate(enterInsuranceDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByEnterInsuranceDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByEnterInsuranceDate(java.util.Calendar enterInsuranceDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByEnterInsuranceDate", startResult, maxRows, enterInsuranceDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitInsuranceDateAfter
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitInsuranceDateAfter(java.util.Calendar exitInsuranceDate) throws DataAccessException {

		return findSpreadByExitInsuranceDateAfter(exitInsuranceDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitInsuranceDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitInsuranceDateAfter(java.util.Calendar exitInsuranceDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitInsuranceDateAfter", startResult, maxRows, exitInsuranceDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitInsuranceDate
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitInsuranceDate(java.util.Calendar exitInsuranceDate) throws DataAccessException {

		return findSpreadByExitInsuranceDate(exitInsuranceDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitInsuranceDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitInsuranceDate(java.util.Calendar exitInsuranceDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitInsuranceDate", startResult, maxRows, exitInsuranceDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByTrailingStopIsSet
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByTrailingStopIsSet(String trailingStopIsSet) throws DataAccessException {

		return findSpreadByTrailingStopIsSet(trailingStopIsSet, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByTrailingStopIsSet
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByTrailingStopIsSet(String trailingStopIsSet, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByTrailingStopIsSet", startResult, maxRows, trailingStopIsSet);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSpreads
	 *
	 */
	@Transactional
	public Set<Spread> findAllSpreads() throws DataAccessException {

		return findAllSpreads(-1, -1);
	}

	/**
	 * JPQL Query - findAllSpreads
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findAllSpreads(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSpreads", startResult, maxRows);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitInsuranceDateBefore
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitInsuranceDateBefore(java.util.Calendar exitInsuranceDate) throws DataAccessException {

		return findSpreadByExitInsuranceDateBefore(exitInsuranceDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitInsuranceDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitInsuranceDateBefore(java.util.Calendar exitInsuranceDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitInsuranceDateBefore", startResult, maxRows, exitInsuranceDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByCreatedDate
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findSpreadByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * PC ADDED - JPQL Query - findSpreadByReopenDate
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByReopenDate(java.util.Calendar reopenDate) throws DataAccessException {

		return findSpreadByReopenDate(reopenDate, -1, -1);
	}

	
	/**
	 * PC ADDED - JPQL Query - findSpreadByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByReopenDate(java.util.Calendar reopenDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByReopenDate", startResult, maxRows, reopenDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	
	/**
	 * JPQL Query - findSpreadByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByCurrentDateBefore
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByCurrentDateBefore(java.util.Calendar currentDate) throws DataAccessException {

		return findSpreadByCurrentDateBefore(currentDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByCurrentDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByCurrentDateBefore(java.util.Calendar currentDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByCurrentDateBefore", startResult, maxRows, currentDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByOpenOrClosed
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByOpenOrClosed(String openOrClosed) throws DataAccessException {

		return findSpreadByOpenOrClosed(openOrClosed, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByOpenOrClosed
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByOpenOrClosed(String openOrClosed, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByOpenOrClosed", startResult, maxRows, openOrClosed);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByEnterSecurityDateAfter
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByEnterSecurityDateAfter(java.util.Calendar enterSecurityDate) throws DataAccessException {

		return findSpreadByEnterSecurityDateAfter(enterSecurityDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByEnterSecurityDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByEnterSecurityDateAfter(java.util.Calendar enterSecurityDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByEnterSecurityDateAfter", startResult, maxRows, enterSecurityDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitMoneymkrDateAfter
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitMoneymkrDateAfter(java.util.Calendar exitMoneymkrDate) throws DataAccessException {

		return findSpreadByExitMoneymkrDateAfter(exitMoneymkrDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitMoneymkrDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitMoneymkrDateAfter(java.util.Calendar exitMoneymkrDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitMoneymkrDateAfter", startResult, maxRows, exitMoneymkrDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrDateAfter
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByEnterMoneymkrDateAfter(java.util.Calendar enterMoneymkrDate) throws DataAccessException {

		return findSpreadByEnterMoneymkrDateAfter(enterMoneymkrDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByEnterMoneymkrDateAfter(java.util.Calendar enterMoneymkrDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByEnterMoneymkrDateAfter", startResult, maxRows, enterMoneymkrDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByTrailingStopIsSetContaining
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByTrailingStopIsSetContaining(String trailingStopIsSet) throws DataAccessException {

		return findSpreadByTrailingStopIsSetContaining(trailingStopIsSet, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByTrailingStopIsSetContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByTrailingStopIsSetContaining(String trailingStopIsSet, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByTrailingStopIsSetContaining", startResult, maxRows, trailingStopIsSet);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByCreatedDateBefore
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByCreatedDateBefore(java.util.Calendar createdDate) throws DataAccessException {

		return findSpreadByCreatedDateBefore(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByCreatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByCreatedDateBefore(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByCreatedDateBefore", startResult, maxRows, createdDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByCurrentInsurancePrice
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByCurrentInsurancePrice(java.math.BigDecimal currentInsurancePrice) throws DataAccessException {

		return findSpreadByCurrentInsurancePrice(currentInsurancePrice, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByCurrentInsurancePrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByCurrentInsurancePrice(java.math.BigDecimal currentInsurancePrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByCurrentInsurancePrice", startResult, maxRows, currentInsurancePrice);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitAboveSpreadValue
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitAboveSpreadValue(java.math.BigDecimal exitAboveSpreadValue) throws DataAccessException {

		return findSpreadByExitAboveSpreadValue(exitAboveSpreadValue, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitAboveSpreadValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitAboveSpreadValue(java.math.BigDecimal exitAboveSpreadValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitAboveSpreadValue", startResult, maxRows, exitAboveSpreadValue);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByCurrentSecurityPrice
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByCurrentSecurityPrice(java.math.BigDecimal currentSecurityPrice) throws DataAccessException {

		return findSpreadByCurrentSecurityPrice(currentSecurityPrice, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByCurrentSecurityPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByCurrentSecurityPrice(java.math.BigDecimal currentSecurityPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByCurrentSecurityPrice", startResult, maxRows, currentSecurityPrice);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByMoneymkrPositionId
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByMoneymkrPositionId(String moneymkrPositionId) throws DataAccessException {

		return findSpreadByMoneymkrPositionId(moneymkrPositionId, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByMoneymkrPositionId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByMoneymkrPositionId(String moneymkrPositionId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByMoneymkrPositionId", startResult, maxRows, moneymkrPositionId);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByMoneymkrPositionIdContaining
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByMoneymkrPositionIdContaining(String moneymkrPositionId) throws DataAccessException {

		return findSpreadByMoneymkrPositionIdContaining(moneymkrPositionId, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByMoneymkrPositionIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByMoneymkrPositionIdContaining(String moneymkrPositionId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByMoneymkrPositionIdContaining", startResult, maxRows, moneymkrPositionId);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByStrikeMoneymkr
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByStrikeMoneymkr(java.math.BigDecimal strikeMoneymkr) throws DataAccessException {

		return findSpreadByStrikeMoneymkr(strikeMoneymkr, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByStrikeMoneymkr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByStrikeMoneymkr(java.math.BigDecimal strikeMoneymkr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByStrikeMoneymkr", startResult, maxRows, strikeMoneymkr);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByInsurancePositionIdContaining
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByInsurancePositionIdContaining(String insurancePositionId) throws DataAccessException {

		return findSpreadByInsurancePositionIdContaining(insurancePositionId, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByInsurancePositionIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByInsurancePositionIdContaining(String insurancePositionId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByInsurancePositionIdContaining", startResult, maxRows, insurancePositionId);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByInsurancePositionId
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByInsurancePositionId(String insurancePositionId) throws DataAccessException {

		return findSpreadByInsurancePositionId(insurancePositionId, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByInsurancePositionId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByInsurancePositionId(String insurancePositionId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByInsurancePositionId", startResult, maxRows, insurancePositionId);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitSecurityDate
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitSecurityDate(java.util.Calendar exitSecurityDate) throws DataAccessException {

		return findSpreadByExitSecurityDate(exitSecurityDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitSecurityDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitSecurityDate(java.util.Calendar exitSecurityDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitSecurityDate", startResult, maxRows, exitSecurityDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByExitMoneymkrDateBefore
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByExitMoneymkrDateBefore(java.util.Calendar exitMoneymkrDate) throws DataAccessException {

		return findSpreadByExitMoneymkrDateBefore(exitMoneymkrDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByExitMoneymkrDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByExitMoneymkrDateBefore(java.util.Calendar exitMoneymkrDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByExitMoneymkrDateBefore", startResult, maxRows, exitMoneymkrDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByTrailingDaysAndHoursContaining
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByTrailingDaysAndHoursContaining(String trailingDaysAndHours) throws DataAccessException {

		return findSpreadByTrailingDaysAndHoursContaining(trailingDaysAndHours, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByTrailingDaysAndHoursContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByTrailingDaysAndHoursContaining(String trailingDaysAndHours, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByTrailingDaysAndHoursContaining", startResult, maxRows, trailingDaysAndHours);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrDateBefore
	 *
	 */
	@Transactional
	public Set<Spread> findSpreadByEnterMoneymkrDateBefore(java.util.Calendar enterMoneymkrDate) throws DataAccessException {

		return findSpreadByEnterMoneymkrDateBefore(enterMoneymkrDate, -1, -1);
	}

	/**
	 * JPQL Query - findSpreadByEnterMoneymkrDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByEnterMoneymkrDateBefore(java.util.Calendar enterMoneymkrDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByEnterMoneymkrDateBefore", startResult, maxRows, enterMoneymkrDate);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	/*****************************************************************************************
	 * USER DEFINED
	 *
	 */
	
	@Transactional
	public Set<Spread> findSpreadByPositionId(int positionId) throws DataAccessException {
		return findSpreadByPositionId(positionId, -1, -1);
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Spread> findSpreadByPositionId(int positionId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSpreadByPositionId", startResult, maxRows, positionId);
		return new LinkedHashSet<Spread>(query.getResultList());
	}

	public synchronized void synchronizedStoreAndFlush(Spread spread) {
		this.store(spread);
		this.flush();
	}

	public synchronized void synchronizedFlush() {
		this.flush();
	}

	public synchronized void synchronizedStore(Spread spread) {
		this.store(spread);
	}

}

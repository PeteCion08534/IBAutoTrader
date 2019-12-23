package com.theta.dao;

import com.theta.domain.Holiday;

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
 * DAO to manage Holiday entities.
 * 
 */
@Repository("HolidayDAO")
@Transactional
public class HolidayDAOImpl extends AbstractJpaDao implements HolidayDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Holiday.class }));

	/**
	 * EntityManager injected by Spring for persistence unit LocOracleTheta
	 *
	 */
	@PersistenceContext(unitName = "LocOracleTheta")
	private EntityManager entityManager;

	/**
	 * Instantiates a new HolidayDAOImpl
	 *
	 */
	public HolidayDAOImpl() {
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
	 * JPQL Query - findHolidayByHolidayId
	 *
	 */
	@Transactional
	public Holiday findHolidayByHolidayId(Integer holidayId) throws DataAccessException {

		return findHolidayByHolidayId(holidayId, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByHolidayId
	 *
	 */

	@Transactional
	public Holiday findHolidayByHolidayId(Integer holidayId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findHolidayByHolidayId", holidayId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findHolidayByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByCreatedByContaining(String createdBy) throws DataAccessException {

		return findHolidayByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByHolidayName
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByHolidayName(String holidayName) throws DataAccessException {

		return findHolidayByHolidayName(holidayName, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByHolidayName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByHolidayName(String holidayName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByHolidayName", startResult, maxRows, holidayName);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByUpdatedDateBefore
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException {

		return findHolidayByUpdatedDateBefore(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByUpdatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByUpdatedDateBefore(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByUpdatedDateBefore", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByHolidayDateBefore
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByHolidayDateBefore(java.util.Calendar holidayDate) throws DataAccessException {

		return findHolidayByHolidayDateBefore(holidayDate, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByHolidayDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByHolidayDateBefore(java.util.Calendar holidayDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByHolidayDateBefore", startResult, maxRows, holidayDate);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByPrimaryKey
	 *
	 */
	@Transactional
	public Holiday findHolidayByPrimaryKey(Integer holidayId) throws DataAccessException {

		return findHolidayByPrimaryKey(holidayId, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByPrimaryKey
	 *
	 */

	@Transactional
	public Holiday findHolidayByPrimaryKey(Integer holidayId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findHolidayByPrimaryKey", holidayId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findHolidayByHolidayYear
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByHolidayYear(Integer holidayYear) throws DataAccessException {

		return findHolidayByHolidayYear(holidayYear, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByHolidayYear
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByHolidayYear(Integer holidayYear, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByHolidayYear", startResult, maxRows, holidayYear);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByHolidayDateAfter
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByHolidayDateAfter(java.util.Calendar holidayDate) throws DataAccessException {

		return findHolidayByHolidayDateAfter(holidayDate, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByHolidayDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByHolidayDateAfter(java.util.Calendar holidayDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByHolidayDateAfter", startResult, maxRows, holidayDate);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByUpdatedDateAfter
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByUpdatedDateAfter(java.util.Calendar updatedDate) throws DataAccessException {

		return findHolidayByUpdatedDateAfter(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByUpdatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByUpdatedDateAfter(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByUpdatedDateAfter", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByUpdatedBy
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByUpdatedBy(String updatedBy) throws DataAccessException {

		return findHolidayByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByCreatedBy
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByCreatedBy(String createdBy) throws DataAccessException {

		return findHolidayByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllHolidays
	 *
	 */
	@Transactional
	public Set<Holiday> findAllHolidays() throws DataAccessException {

		return findAllHolidays(-1, -1);
	}

	/**
	 * JPQL Query - findAllHolidays
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findAllHolidays(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllHolidays", startResult, maxRows);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByUpdatedByContaining
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByUpdatedByContaining(String updatedBy) throws DataAccessException {

		return findHolidayByUpdatedByContaining(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByUpdatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByUpdatedByContaining", startResult, maxRows, updatedBy);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByCreatedDateBefore
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByCreatedDateBefore(java.util.Calendar createdDate) throws DataAccessException {

		return findHolidayByCreatedDateBefore(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByCreatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByCreatedDateBefore(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByCreatedDateBefore", startResult, maxRows, createdDate);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByHolidayNameContaining
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByHolidayNameContaining(String holidayName) throws DataAccessException {

		return findHolidayByHolidayNameContaining(holidayName, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByHolidayNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByHolidayNameContaining(String holidayName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByHolidayNameContaining", startResult, maxRows, holidayName);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByCreatedDate
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findHolidayByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByCreatedDateAfter
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException {

		return findHolidayByCreatedDateAfter(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByCreatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByCreatedDateAfter(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByCreatedDateAfter", startResult, maxRows, createdDate);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByUpdatedDate
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findHolidayByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

	/**
	 * JPQL Query - findHolidayByHolidayDate
	 *
	 */
	@Transactional
	public Set<Holiday> findHolidayByHolidayDate(java.util.Calendar holidayDate) throws DataAccessException {

		return findHolidayByHolidayDate(holidayDate, -1, -1);
	}

	/**
	 * JPQL Query - findHolidayByHolidayDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Holiday> findHolidayByHolidayDate(java.util.Calendar holidayDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findHolidayByHolidayDate", startResult, maxRows, holidayDate);
		return new LinkedHashSet<Holiday>(query.getResultList());
	}

}

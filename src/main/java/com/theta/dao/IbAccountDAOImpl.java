package com.theta.dao;

import com.theta.domain.IbAccount;

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
 * DAO to manage IbAccount entities.
 * 
 */
@Repository("IbAccountDAO")
@Transactional
public class IbAccountDAOImpl extends AbstractJpaDao implements IbAccountDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { IbAccount.class }));

	/**
	 * EntityManager injected by Spring for persistence unit LocOracleTheta
	 *
	 */
	@PersistenceContext(unitName = "LocOracleTheta")
	private EntityManager entityManager;

	/**
	 * Instantiates a new IbAccountDAOImpl
	 *
	 */
	public IbAccountDAOImpl() {
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
	 * JPQL Query - findIbAccountByIbAccountIdExt
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByIbAccountIdExt(String ibAccountIdExt) throws DataAccessException {

		return findIbAccountByIbAccountIdExt(ibAccountIdExt, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByIbAccountIdExt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByIbAccountIdExt(String ibAccountIdExt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByIbAccountIdExt", startResult, maxRows, ibAccountIdExt);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByCreatedDateAfter
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException {

		return findIbAccountByCreatedDateAfter(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByCreatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByCreatedDateAfter(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByCreatedDateAfter", startResult, maxRows, createdDate);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByCreatedByContaining(String createdBy) throws DataAccessException {

		return findIbAccountByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByCreatedDate
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findIbAccountByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByCreatedDateBefore
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByCreatedDateBefore(java.util.Calendar createdDate) throws DataAccessException {

		return findIbAccountByCreatedDateBefore(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByCreatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByCreatedDateBefore(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByCreatedDateBefore", startResult, maxRows, createdDate);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByUpdatedDate
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findIbAccountByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByUpdatedByContaining
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByUpdatedByContaining(String updatedBy) throws DataAccessException {

		return findIbAccountByUpdatedByContaining(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByUpdatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByUpdatedByContaining", startResult, maxRows, updatedBy);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByUpdatedDateBefore
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException {

		return findIbAccountByUpdatedDateBefore(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByUpdatedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByUpdatedDateBefore(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByUpdatedDateBefore", startResult, maxRows, updatedDate);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByUpdatedBy
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByUpdatedBy(String updatedBy) throws DataAccessException {

		return findIbAccountByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByIbAccountIdExtContaining
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByIbAccountIdExtContaining(String ibAccountIdExt) throws DataAccessException {

		return findIbAccountByIbAccountIdExtContaining(ibAccountIdExt, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByIbAccountIdExtContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByIbAccountIdExtContaining(String ibAccountIdExt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByIbAccountIdExtContaining", startResult, maxRows, ibAccountIdExt);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByIbAccountId
	 *
	 */
	@Transactional
	public IbAccount findIbAccountByIbAccountId(Integer ibAccountId) throws DataAccessException {

		return findIbAccountByIbAccountId(ibAccountId, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByIbAccountId
	 *
	 */

	@Transactional
	public IbAccount findIbAccountByIbAccountId(Integer ibAccountId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findIbAccountByIbAccountId", ibAccountId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findIbAccountByClientId
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByClientId(String clientId) throws DataAccessException {

		return findIbAccountByClientId(clientId, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByClientId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByClientId(String clientId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByClientId", startResult, maxRows, clientId);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByPrimaryKey
	 *
	 */
	@Transactional
	public IbAccount findIbAccountByPrimaryKey(Integer ibAccountId) throws DataAccessException {

		return findIbAccountByPrimaryKey(ibAccountId, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByPrimaryKey
	 *
	 */

	@Transactional
	public IbAccount findIbAccountByPrimaryKey(Integer ibAccountId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findIbAccountByPrimaryKey", ibAccountId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findIbAccountByCreatedBy
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByCreatedBy(String createdBy) throws DataAccessException {

		return findIbAccountByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByIpAddress
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByIpAddress(String ipAddress) throws DataAccessException {

		return findIbAccountByIpAddress(ipAddress, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByIpAddress
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByIpAddress(String ipAddress, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByIpAddress", startResult, maxRows, ipAddress);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByPort
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByPort(Integer port) throws DataAccessException {

		return findIbAccountByPort(port, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByPort
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByPort(Integer port, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByPort", startResult, maxRows, port);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByUpdatedDateAfter
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByUpdatedDateAfter(java.util.Calendar updatedDate) throws DataAccessException {

		return findIbAccountByUpdatedDateAfter(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByUpdatedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByUpdatedDateAfter(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByUpdatedDateAfter", startResult, maxRows, updatedDate);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByClientIdContaining
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByClientIdContaining(String clientId) throws DataAccessException {

		return findIbAccountByClientIdContaining(clientId, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByClientIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByClientIdContaining(String clientId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByClientIdContaining", startResult, maxRows, clientId);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllIbAccounts
	 *
	 */
	@Transactional
	public Set<IbAccount> findAllIbAccounts() throws DataAccessException {

		return findAllIbAccounts(-1, -1);
	}

	/**
	 * JPQL Query - findAllIbAccounts
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findAllIbAccounts(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllIbAccounts", startResult, maxRows);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

	/**
	 * JPQL Query - findIbAccountByIpAddressContaining
	 *
	 */
	@Transactional
	public Set<IbAccount> findIbAccountByIpAddressContaining(String ipAddress) throws DataAccessException {

		return findIbAccountByIpAddressContaining(ipAddress, -1, -1);
	}

	/**
	 * JPQL Query - findIbAccountByIpAddressContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IbAccount> findIbAccountByIpAddressContaining(String ipAddress, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIbAccountByIpAddressContaining", startResult, maxRows, ipAddress);
		return new LinkedHashSet<IbAccount>(query.getResultList());
	}

}

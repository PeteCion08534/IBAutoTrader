package com.theta.dao;

import com.theta.domain.IbAccount;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage IbAccount entities.
 * 
 */
public interface IbAccountDAO extends JpaDao {

	/**
	 * JPQL Query - findIbAccountByIbAccountIdExt
	 *
	 */
	public Set<IbAccount> findIbAccountByIbAccountIdExt(String ibAccountIdExt) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByIbAccountIdExt
	 *
	 */
	public Set<IbAccount> findIbAccountByIbAccountIdExt(String ibAccountIdExt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByCreatedDateAfter
	 *
	 */
	public Set<IbAccount> findIbAccountByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByCreatedDateAfter
	 *
	 */
	public Set<IbAccount> findIbAccountByCreatedDateAfter(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByCreatedByContaining
	 *
	 */
	public Set<IbAccount> findIbAccountByCreatedByContaining(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByCreatedByContaining
	 *
	 */
	public Set<IbAccount> findIbAccountByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByCreatedDate
	 *
	 */
	public Set<IbAccount> findIbAccountByCreatedDate(java.util.Calendar createdDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByCreatedDate
	 *
	 */
	public Set<IbAccount> findIbAccountByCreatedDate(Calendar createdDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByCreatedDateBefore
	 *
	 */
	public Set<IbAccount> findIbAccountByCreatedDateBefore(java.util.Calendar createdDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByCreatedDateBefore
	 *
	 */
	public Set<IbAccount> findIbAccountByCreatedDateBefore(Calendar createdDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByUpdatedDate
	 *
	 */
	public Set<IbAccount> findIbAccountByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByUpdatedDate
	 *
	 */
	public Set<IbAccount> findIbAccountByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByUpdatedByContaining
	 *
	 */
	public Set<IbAccount> findIbAccountByUpdatedByContaining(String updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByUpdatedByContaining
	 *
	 */
	public Set<IbAccount> findIbAccountByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByUpdatedDateBefore
	 *
	 */
	public Set<IbAccount> findIbAccountByUpdatedDateBefore(java.util.Calendar updatedDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByUpdatedDateBefore
	 *
	 */
	public Set<IbAccount> findIbAccountByUpdatedDateBefore(Calendar updatedDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByUpdatedBy
	 *
	 */
	public Set<IbAccount> findIbAccountByUpdatedBy(String updatedBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByUpdatedBy
	 *
	 */
	public Set<IbAccount> findIbAccountByUpdatedBy(String updatedBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByIbAccountIdExtContaining
	 *
	 */
	public Set<IbAccount> findIbAccountByIbAccountIdExtContaining(String ibAccountIdExt_1) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByIbAccountIdExtContaining
	 *
	 */
	public Set<IbAccount> findIbAccountByIbAccountIdExtContaining(String ibAccountIdExt_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByIbAccountId
	 *
	 */
	public IbAccount findIbAccountByIbAccountId(Integer ibAccountId) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByIbAccountId
	 *
	 */
	public IbAccount findIbAccountByIbAccountId(Integer ibAccountId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByClientId
	 *
	 */
	public Set<IbAccount> findIbAccountByClientId(String clientId) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByClientId
	 *
	 */
	public Set<IbAccount> findIbAccountByClientId(String clientId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByPrimaryKey
	 *
	 */
	public IbAccount findIbAccountByPrimaryKey(Integer ibAccountId_1) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByPrimaryKey
	 *
	 */
	public IbAccount findIbAccountByPrimaryKey(Integer ibAccountId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByCreatedBy
	 *
	 */
	public Set<IbAccount> findIbAccountByCreatedBy(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByCreatedBy
	 *
	 */
	public Set<IbAccount> findIbAccountByCreatedBy(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByIpAddress
	 *
	 */
	public Set<IbAccount> findIbAccountByIpAddress(String ipAddress) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByIpAddress
	 *
	 */
	public Set<IbAccount> findIbAccountByIpAddress(String ipAddress, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByPort
	 *
	 */
	public Set<IbAccount> findIbAccountByPort(Integer port) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByPort
	 *
	 */
	public Set<IbAccount> findIbAccountByPort(Integer port, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByUpdatedDateAfter
	 *
	 */
	public Set<IbAccount> findIbAccountByUpdatedDateAfter(java.util.Calendar updatedDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByUpdatedDateAfter
	 *
	 */
	public Set<IbAccount> findIbAccountByUpdatedDateAfter(Calendar updatedDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByClientIdContaining
	 *
	 */
	public Set<IbAccount> findIbAccountByClientIdContaining(String clientId_1) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByClientIdContaining
	 *
	 */
	public Set<IbAccount> findIbAccountByClientIdContaining(String clientId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllIbAccounts
	 *
	 */
	public Set<IbAccount> findAllIbAccounts() throws DataAccessException;

	/**
	 * JPQL Query - findAllIbAccounts
	 *
	 */
	public Set<IbAccount> findAllIbAccounts(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByIpAddressContaining
	 *
	 */
	public Set<IbAccount> findIbAccountByIpAddressContaining(String ipAddress_1) throws DataAccessException;

	/**
	 * JPQL Query - findIbAccountByIpAddressContaining
	 *
	 */
	public Set<IbAccount> findIbAccountByIpAddressContaining(String ipAddress_1, int startResult, int maxRows) throws DataAccessException;

}
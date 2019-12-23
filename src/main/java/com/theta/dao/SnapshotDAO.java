package com.theta.dao;

import com.theta.domain.Snapshot;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Snapshot entities.
 * 
 */
public interface SnapshotDAO extends JpaDao {

	/**
	 * JPQL Query - findSnapshotByExpirtyDay
	 *
	 */
	public Set<Snapshot> findSnapshotByExpirtyDay(Integer expirtyDay) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByExpirtyDay
	 *
	 */
	public Set<Snapshot> findSnapshotByExpirtyDay(Integer expirtyDay, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySnapshotDate
	 *
	 */
	public Set<Snapshot> findSnapshotBySnapshotDate(java.util.Calendar snapshotDate) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySnapshotDate
	 *
	 */
	public Set<Snapshot> findSnapshotBySnapshotDate(Calendar snapshotDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySnapshotDateBefore
	 *
	 */
	public Set<Snapshot> findSnapshotBySnapshotDateBefore(java.util.Calendar snapshotDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySnapshotDateBefore
	 *
	 */
	public Set<Snapshot> findSnapshotBySnapshotDateBefore(Calendar snapshotDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByCreatedByContaining
	 *
	 */
	public Set<Snapshot> findSnapshotByCreatedByContaining(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByCreatedByContaining
	 *
	 */
	public Set<Snapshot> findSnapshotByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByUpdatedDate
	 *
	 */
	public Set<Snapshot> findSnapshotByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByUpdatedDate
	 *
	 */
	public Set<Snapshot> findSnapshotByUpdatedDate(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByCreatedDateBefore
	 *
	 */
	public Set<Snapshot> findSnapshotByCreatedDateBefore(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByCreatedDateBefore
	 *
	 */
	public Set<Snapshot> findSnapshotByCreatedDateBefore(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByPrimaryKey
	 *
	 */
	public Snapshot findSnapshotByPrimaryKey(Integer snapshotId) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByPrimaryKey
	 *
	 */
	public Snapshot findSnapshotByPrimaryKey(Integer snapshotId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByExpiryYear
	 *
	 */
	public Set<Snapshot> findSnapshotByExpiryYear(Integer expiryYear) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByExpiryYear
	 *
	 */
	public Set<Snapshot> findSnapshotByExpiryYear(Integer expiryYear, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySymbolContaining
	 *
	 */
	public Set<Snapshot> findSnapshotBySymbolContaining(String symbol) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySymbolContaining
	 *
	 */
	public Set<Snapshot> findSnapshotBySymbolContaining(String symbol, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByUpdatedByContaining
	 *
	 */
	public Set<Snapshot> findSnapshotByUpdatedByContaining(String updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByUpdatedByContaining
	 *
	 */
	public Set<Snapshot> findSnapshotByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySnapshotDateAfter
	 *
	 */
	public Set<Snapshot> findSnapshotBySnapshotDateAfter(java.util.Calendar snapshotDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySnapshotDateAfter
	 *
	 */
	public Set<Snapshot> findSnapshotBySnapshotDateAfter(Calendar snapshotDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByCreatedDateAfter
	 *
	 */
	public Set<Snapshot> findSnapshotByCreatedDateAfter(java.util.Calendar createdDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByCreatedDateAfter
	 *
	 */
	public Set<Snapshot> findSnapshotByCreatedDateAfter(Calendar createdDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySnapshotId
	 *
	 */
	public Snapshot findSnapshotBySnapshotId(Integer snapshotId_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySnapshotId
	 *
	 */
	public Snapshot findSnapshotBySnapshotId(Integer snapshotId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByCreatedDate
	 *
	 */
	public Set<Snapshot> findSnapshotByCreatedDate(java.util.Calendar createdDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByCreatedDate
	 *
	 */
	public Set<Snapshot> findSnapshotByCreatedDate(Calendar createdDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByUpdatedDateBefore
	 *
	 */
	public Set<Snapshot> findSnapshotByUpdatedDateBefore(java.util.Calendar updatedDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByUpdatedDateBefore
	 *
	 */
	public Set<Snapshot> findSnapshotByUpdatedDateBefore(Calendar updatedDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSnapshots
	 *
	 */
	public Set<Snapshot> findAllSnapshots() throws DataAccessException;

	/**
	 * JPQL Query - findAllSnapshots
	 *
	 */
	public Set<Snapshot> findAllSnapshots(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByCreatedBy
	 *
	 */
	public Set<Snapshot> findSnapshotByCreatedBy(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByCreatedBy
	 *
	 */
	public Set<Snapshot> findSnapshotByCreatedBy(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySymbolPrice
	 *
	 */
	public Set<Snapshot> findSnapshotBySymbolPrice(java.math.BigDecimal symbolPrice) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySymbolPrice
	 *
	 */
	public Set<Snapshot> findSnapshotBySymbolPrice(BigDecimal symbolPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByUpdatedBy
	 *
	 */
	public Set<Snapshot> findSnapshotByUpdatedBy(String updatedBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByUpdatedBy
	 *
	 */
	public Set<Snapshot> findSnapshotByUpdatedBy(String updatedBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByExpiryMonth
	 *
	 */
	public Set<Snapshot> findSnapshotByExpiryMonth(Integer expiryMonth) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByExpiryMonth
	 *
	 */
	public Set<Snapshot> findSnapshotByExpiryMonth(Integer expiryMonth, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByUpdatedDateAfter
	 *
	 */
	public Set<Snapshot> findSnapshotByUpdatedDateAfter(java.util.Calendar updatedDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotByUpdatedDateAfter
	 *
	 */
	public Set<Snapshot> findSnapshotByUpdatedDateAfter(Calendar updatedDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySymbol
	 *
	 */
	public Set<Snapshot> findSnapshotBySymbol(String symbol_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnapshotBySymbol
	 *
	 */
	public Set<Snapshot> findSnapshotBySymbol(String symbol_1, int startResult, int maxRows) throws DataAccessException;

}
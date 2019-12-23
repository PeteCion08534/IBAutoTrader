package com.theta.dao;

import com.theta.domain.ThetaException;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage ThetaException entities.
 * 
 */
public interface ThetaExceptionDAO extends JpaDao {

	/**
	 * JPQL Query - findThetaExceptionByExceptionText
	 *
	 */
	public Set<ThetaException> findThetaExceptionByExceptionText(String exceptionText) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByExceptionText
	 *
	 */
	public Set<ThetaException> findThetaExceptionByExceptionText(String exceptionText, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByOrderDetailText
	 *
	 */
	public Set<ThetaException> findThetaExceptionByOrderDetailText(String orderDetailText) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByOrderDetailText
	 *
	 */
	public Set<ThetaException> findThetaExceptionByOrderDetailText(String orderDetailText, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByContractDetailTextContaining
	 *
	 */
	public Set<ThetaException> findThetaExceptionByContractDetailTextContaining(String contractDetailText) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByContractDetailTextContaining
	 *
	 */
	public Set<ThetaException> findThetaExceptionByContractDetailTextContaining(String contractDetailText, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByUpdatedByContaining
	 *
	 */
	public Set<ThetaException> findThetaExceptionByUpdatedByContaining(String updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByUpdatedByContaining
	 *
	 */
	public Set<ThetaException> findThetaExceptionByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByUpdatedDateBefore
	 *
	 */
	public Set<ThetaException> findThetaExceptionByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByUpdatedDateBefore
	 *
	 */
	public Set<ThetaException> findThetaExceptionByUpdatedDateBefore(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByOrderDetailTextContaining
	 *
	 */
	public Set<ThetaException> findThetaExceptionByOrderDetailTextContaining(String orderDetailText_1) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByOrderDetailTextContaining
	 *
	 */
	public Set<ThetaException> findThetaExceptionByOrderDetailTextContaining(String orderDetailText_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByContractDetailText
	 *
	 */
	public Set<ThetaException> findThetaExceptionByContractDetailText(String contractDetailText_1) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByContractDetailText
	 *
	 */
	public Set<ThetaException> findThetaExceptionByContractDetailText(String contractDetailText_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByUpdatedDate
	 *
	 */
	public Set<ThetaException> findThetaExceptionByUpdatedDate(java.util.Calendar updatedDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByUpdatedDate
	 *
	 */
	public Set<ThetaException> findThetaExceptionByUpdatedDate(Calendar updatedDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByThetaExceptionId
	 *
	 */
	public ThetaException findThetaExceptionByThetaExceptionId(Integer thetaExceptionId) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByThetaExceptionId
	 *
	 */
	public ThetaException findThetaExceptionByThetaExceptionId(Integer thetaExceptionId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByCreatedByContaining
	 *
	 */
	public Set<ThetaException> findThetaExceptionByCreatedByContaining(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByCreatedByContaining
	 *
	 */
	public Set<ThetaException> findThetaExceptionByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByExceptionTextContaining
	 *
	 */
	public Set<ThetaException> findThetaExceptionByExceptionTextContaining(String exceptionText_1) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByExceptionTextContaining
	 *
	 */
	public Set<ThetaException> findThetaExceptionByExceptionTextContaining(String exceptionText_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByCreatedDateAfter
	 *
	 */
	public Set<ThetaException> findThetaExceptionByCreatedDateAfter(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByCreatedDateAfter
	 *
	 */
	public Set<ThetaException> findThetaExceptionByCreatedDateAfter(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByUpdatedDateAfter
	 *
	 */
	public Set<ThetaException> findThetaExceptionByUpdatedDateAfter(java.util.Calendar updatedDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByUpdatedDateAfter
	 *
	 */
	public Set<ThetaException> findThetaExceptionByUpdatedDateAfter(Calendar updatedDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllThetaExceptions
	 *
	 */
	public Set<ThetaException> findAllThetaExceptions() throws DataAccessException;

	/**
	 * JPQL Query - findAllThetaExceptions
	 *
	 */
	public Set<ThetaException> findAllThetaExceptions(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByPrimaryKey
	 *
	 */
	public ThetaException findThetaExceptionByPrimaryKey(Integer thetaExceptionId_1) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByPrimaryKey
	 *
	 */
	public ThetaException findThetaExceptionByPrimaryKey(Integer thetaExceptionId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByCreatedDate
	 *
	 */
	public Set<ThetaException> findThetaExceptionByCreatedDate(java.util.Calendar createdDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByCreatedDate
	 *
	 */
	public Set<ThetaException> findThetaExceptionByCreatedDate(Calendar createdDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByCreatedDateBefore
	 *
	 */
	public Set<ThetaException> findThetaExceptionByCreatedDateBefore(java.util.Calendar createdDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByCreatedDateBefore
	 *
	 */
	public Set<ThetaException> findThetaExceptionByCreatedDateBefore(Calendar createdDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByUpdatedBy
	 *
	 */
	public Set<ThetaException> findThetaExceptionByUpdatedBy(String updatedBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByUpdatedBy
	 *
	 */
	public Set<ThetaException> findThetaExceptionByUpdatedBy(String updatedBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByCreatedBy
	 *
	 */
	public Set<ThetaException> findThetaExceptionByCreatedBy(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findThetaExceptionByCreatedBy
	 *
	 */
	public Set<ThetaException> findThetaExceptionByCreatedBy(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

}
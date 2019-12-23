package com.theta.dao;

import com.theta.domain.RequestSeq;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage RequestSeq entities.
 * 
 */
public interface RequestSeqDAO extends JpaDao {

	/**
	 * JPQL Query - findRequestSeqByRequestSeqId
	 *
	 */
	public RequestSeq findRequestSeqByRequestSeqId(Integer requestSeqId) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByRequestSeqId
	 *
	 */
	public RequestSeq findRequestSeqByRequestSeqId(Integer requestSeqId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByUpdatedDateAfter
	 *
	 */
	public Set<RequestSeq> findRequestSeqByUpdatedDateAfter(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByUpdatedDateAfter
	 *
	 */
	public Set<RequestSeq> findRequestSeqByUpdatedDateAfter(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByRequestSeqNo
	 *
	 */
	public Set<RequestSeq> findRequestSeqByRequestSeqNo(Integer requestSeqNo) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByRequestSeqNo
	 *
	 */
	public Set<RequestSeq> findRequestSeqByRequestSeqNo(Integer requestSeqNo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllRequestSeqs
	 *
	 */
	public Set<RequestSeq> findAllRequestSeqs() throws DataAccessException;

	/**
	 * JPQL Query - findAllRequestSeqs
	 *
	 */
	public Set<RequestSeq> findAllRequestSeqs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByUpdatedBy
	 *
	 */
	public Set<RequestSeq> findRequestSeqByUpdatedBy(String updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByUpdatedBy
	 *
	 */
	public Set<RequestSeq> findRequestSeqByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByPrimaryKey
	 *
	 */
	public RequestSeq findRequestSeqByPrimaryKey(Integer requestSeqId_1) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByPrimaryKey
	 *
	 */
	public RequestSeq findRequestSeqByPrimaryKey(Integer requestSeqId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByUpdatedByContaining
	 *
	 */
	public Set<RequestSeq> findRequestSeqByUpdatedByContaining(String updatedBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByUpdatedByContaining
	 *
	 */
	public Set<RequestSeq> findRequestSeqByUpdatedByContaining(String updatedBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByUpdatedDate
	 *
	 */
	public Set<RequestSeq> findRequestSeqByUpdatedDate(java.util.Calendar updatedDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByUpdatedDate
	 *
	 */
	public Set<RequestSeq> findRequestSeqByUpdatedDate(Calendar updatedDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByUpdatedDateBefore
	 *
	 */
	public Set<RequestSeq> findRequestSeqByUpdatedDateBefore(java.util.Calendar updatedDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findRequestSeqByUpdatedDateBefore
	 *
	 */
	public Set<RequestSeq> findRequestSeqByUpdatedDateBefore(Calendar updatedDate_2, int startResult, int maxRows) throws DataAccessException;

}
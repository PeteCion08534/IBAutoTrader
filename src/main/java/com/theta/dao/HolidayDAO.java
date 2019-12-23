package com.theta.dao;

import com.theta.domain.Holiday;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Holiday entities.
 * 
 */
public interface HolidayDAO extends JpaDao {

	/**
	 * JPQL Query - findHolidayByHolidayId
	 *
	 */
	public Holiday findHolidayByHolidayId(Integer holidayId) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByHolidayId
	 *
	 */
	public Holiday findHolidayByHolidayId(Integer holidayId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByCreatedByContaining
	 *
	 */
	public Set<Holiday> findHolidayByCreatedByContaining(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByCreatedByContaining
	 *
	 */
	public Set<Holiday> findHolidayByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByHolidayName
	 *
	 */
	public Set<Holiday> findHolidayByHolidayName(String holidayName) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByHolidayName
	 *
	 */
	public Set<Holiday> findHolidayByHolidayName(String holidayName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByUpdatedDateBefore
	 *
	 */
	public Set<Holiday> findHolidayByUpdatedDateBefore(java.util.Calendar updatedDate) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByUpdatedDateBefore
	 *
	 */
	public Set<Holiday> findHolidayByUpdatedDateBefore(Calendar updatedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByHolidayDateBefore
	 *
	 */
	public Set<Holiday> findHolidayByHolidayDateBefore(java.util.Calendar holidayDate) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByHolidayDateBefore
	 *
	 */
	public Set<Holiday> findHolidayByHolidayDateBefore(Calendar holidayDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByPrimaryKey
	 *
	 */
	public Holiday findHolidayByPrimaryKey(Integer holidayId_1) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByPrimaryKey
	 *
	 */
	public Holiday findHolidayByPrimaryKey(Integer holidayId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByHolidayYear
	 *
	 */
	public Set<Holiday> findHolidayByHolidayYear(Integer holidayYear) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByHolidayYear
	 *
	 */
	public Set<Holiday> findHolidayByHolidayYear(Integer holidayYear, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByHolidayDateAfter
	 *
	 */
	public Set<Holiday> findHolidayByHolidayDateAfter(java.util.Calendar holidayDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByHolidayDateAfter
	 *
	 */
	public Set<Holiday> findHolidayByHolidayDateAfter(Calendar holidayDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByUpdatedDateAfter
	 *
	 */
	public Set<Holiday> findHolidayByUpdatedDateAfter(java.util.Calendar updatedDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByUpdatedDateAfter
	 *
	 */
	public Set<Holiday> findHolidayByUpdatedDateAfter(Calendar updatedDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByUpdatedBy
	 *
	 */
	public Set<Holiday> findHolidayByUpdatedBy(String updatedBy) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByUpdatedBy
	 *
	 */
	public Set<Holiday> findHolidayByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByCreatedBy
	 *
	 */
	public Set<Holiday> findHolidayByCreatedBy(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByCreatedBy
	 *
	 */
	public Set<Holiday> findHolidayByCreatedBy(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllHolidays
	 *
	 */
	public Set<Holiday> findAllHolidays() throws DataAccessException;

	/**
	 * JPQL Query - findAllHolidays
	 *
	 */
	public Set<Holiday> findAllHolidays(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByUpdatedByContaining
	 *
	 */
	public Set<Holiday> findHolidayByUpdatedByContaining(String updatedBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByUpdatedByContaining
	 *
	 */
	public Set<Holiday> findHolidayByUpdatedByContaining(String updatedBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByCreatedDateBefore
	 *
	 */
	public Set<Holiday> findHolidayByCreatedDateBefore(java.util.Calendar createdDate) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByCreatedDateBefore
	 *
	 */
	public Set<Holiday> findHolidayByCreatedDateBefore(Calendar createdDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByHolidayNameContaining
	 *
	 */
	public Set<Holiday> findHolidayByHolidayNameContaining(String holidayName_1) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByHolidayNameContaining
	 *
	 */
	public Set<Holiday> findHolidayByHolidayNameContaining(String holidayName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByCreatedDate
	 *
	 */
	public Set<Holiday> findHolidayByCreatedDate(java.util.Calendar createdDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByCreatedDate
	 *
	 */
	public Set<Holiday> findHolidayByCreatedDate(Calendar createdDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByCreatedDateAfter
	 *
	 */
	public Set<Holiday> findHolidayByCreatedDateAfter(java.util.Calendar createdDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByCreatedDateAfter
	 *
	 */
	public Set<Holiday> findHolidayByCreatedDateAfter(Calendar createdDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByUpdatedDate
	 *
	 */
	public Set<Holiday> findHolidayByUpdatedDate(java.util.Calendar updatedDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByUpdatedDate
	 *
	 */
	public Set<Holiday> findHolidayByUpdatedDate(Calendar updatedDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByHolidayDate
	 *
	 */
	public Set<Holiday> findHolidayByHolidayDate(java.util.Calendar holidayDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findHolidayByHolidayDate
	 *
	 */
	public Set<Holiday> findHolidayByHolidayDate(Calendar holidayDate_2, int startResult, int maxRows) throws DataAccessException;

}
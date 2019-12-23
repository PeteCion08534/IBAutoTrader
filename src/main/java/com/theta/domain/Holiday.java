package com.theta.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries( {
		@NamedQuery(name = "findAllHolidays", query = "select myHoliday from Holiday myHoliday"),
		@NamedQuery(name = "findHolidayByCreatedBy", query = "select myHoliday from Holiday myHoliday where myHoliday.createdBy = ?1"),
		@NamedQuery(name = "findHolidayByCreatedByContaining", query = "select myHoliday from Holiday myHoliday where myHoliday.createdBy like ?1"),
		@NamedQuery(name = "findHolidayByCreatedDate", query = "select myHoliday from Holiday myHoliday where myHoliday.createdDate = ?1"),
		@NamedQuery(name = "findHolidayByCreatedDateAfter", query = "select myHoliday from Holiday myHoliday where myHoliday.createdDate > ?1"),
		@NamedQuery(name = "findHolidayByCreatedDateBefore", query = "select myHoliday from Holiday myHoliday where myHoliday.createdDate < ?1"),
		@NamedQuery(name = "findHolidayByHolidayDate", query = "select myHoliday from Holiday myHoliday where myHoliday.holidayDate = ?1"),
		@NamedQuery(name = "findHolidayByHolidayDateAfter", query = "select myHoliday from Holiday myHoliday where myHoliday.holidayDate > ?1"),
		@NamedQuery(name = "findHolidayByHolidayDateBefore", query = "select myHoliday from Holiday myHoliday where myHoliday.holidayDate < ?1"),
		@NamedQuery(name = "findHolidayByHolidayId", query = "select myHoliday from Holiday myHoliday where myHoliday.holidayId = ?1"),
		@NamedQuery(name = "findHolidayByHolidayName", query = "select myHoliday from Holiday myHoliday where myHoliday.holidayName = ?1"),
		@NamedQuery(name = "findHolidayByHolidayNameContaining", query = "select myHoliday from Holiday myHoliday where myHoliday.holidayName like ?1"),
		@NamedQuery(name = "findHolidayByHolidayYear", query = "select myHoliday from Holiday myHoliday where myHoliday.holidayYear = ?1"),
		@NamedQuery(name = "findHolidayByPrimaryKey", query = "select myHoliday from Holiday myHoliday where myHoliday.holidayId = ?1"),
		@NamedQuery(name = "findHolidayByUpdatedBy", query = "select myHoliday from Holiday myHoliday where myHoliday.updatedBy = ?1"),
		@NamedQuery(name = "findHolidayByUpdatedByContaining", query = "select myHoliday from Holiday myHoliday where myHoliday.updatedBy like ?1"),
		@NamedQuery(name = "findHolidayByUpdatedDate", query = "select myHoliday from Holiday myHoliday where myHoliday.updatedDate = ?1"),
		@NamedQuery(name = "findHolidayByUpdatedDateAfter", query = "select myHoliday from Holiday myHoliday where myHoliday.updatedDate > ?1"),
		@NamedQuery(name = "findHolidayByUpdatedDateBefore", query = "select myHoliday from Holiday myHoliday where myHoliday.updatedDate < ?1") })
@Table(schema = "THETA", name = "HOLIDAY")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "theta11/com/theta/domain", name = "Holiday")
public class Holiday implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "HOLIDAY_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer holidayId;
	/**
	 */

	@Column(name = "HOLIDAY_NAME", length = 20, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String holidayName;
	/**
	 */

	@Column(name = "HOLIDAY_YEAR", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer holidayYear;
	/**
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "HOLIDAY_DATE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar holidayDate;
	/**
	 */

	@Column(name = "CREATED_BY", length = 50, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String createdBy;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdDate;
	/**
	 */

	@Column(name = "UPDATED_BY", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String updatedBy;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar updatedDate;

	/**
	 */
	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}

	/**
	 */
	public Integer getHolidayId() {
		return this.holidayId;
	}

	/**
	 */
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	/**
	 */
	public String getHolidayName() {
		return this.holidayName;
	}

	/**
	 */
	public void setHolidayYear(Integer holidayYear) {
		this.holidayYear = holidayYear;
	}

	/**
	 */
	public Integer getHolidayYear() {
		return this.holidayYear;
	}

	/**
	 */
	public void setHolidayDate(Calendar holidayDate) {
		this.holidayDate = holidayDate;
	}

	/**
	 */
	public Calendar getHolidayDate() {
		return this.holidayDate;
	}

	/**
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 */
	public String getCreatedBy() {
		return this.createdBy;
	}

	/**
	 */
	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 */
	public Calendar getCreatedDate() {
		return this.createdDate;
	}

	/**
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 */
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 */
	public void setUpdatedDate(Calendar updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 */
	public Calendar getUpdatedDate() {
		return this.updatedDate;
	}

	/**
	 */
	public Holiday() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Holiday that) {
		setHolidayId(that.getHolidayId());
		setHolidayName(that.getHolidayName());
		setHolidayYear(that.getHolidayYear());
		setHolidayDate(that.getHolidayDate());
		setCreatedBy(that.getCreatedBy());
		setCreatedDate(that.getCreatedDate());
		setUpdatedBy(that.getUpdatedBy());
		setUpdatedDate(that.getUpdatedDate());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("holidayId=[").append(holidayId).append("] ");
		buffer.append("holidayName=[").append(holidayName).append("] ");
		buffer.append("holidayYear=[").append(holidayYear).append("] ");
		buffer.append("holidayDate=[").append(holidayDate).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createdDate=[").append(createdDate).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updatedDate=[").append(updatedDate).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((holidayId == null) ? 0 : holidayId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Holiday))
			return false;
		Holiday equalCheck = (Holiday) obj;
		if ((holidayId == null && equalCheck.holidayId != null) || (holidayId != null && equalCheck.holidayId == null))
			return false;
		if (holidayId != null && !holidayId.equals(equalCheck.holidayId))
			return false;
		return true;
	}
}

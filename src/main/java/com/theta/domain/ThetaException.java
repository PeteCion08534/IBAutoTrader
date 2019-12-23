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
		@NamedQuery(name = "findAllThetaExceptions", query = "select myThetaException from ThetaException myThetaException"),
		@NamedQuery(name = "findThetaExceptionByContractDetailText", query = "select myThetaException from ThetaException myThetaException where myThetaException.contractDetailText = ?1"),
		@NamedQuery(name = "findThetaExceptionByContractDetailTextContaining", query = "select myThetaException from ThetaException myThetaException where myThetaException.contractDetailText like ?1"),
		@NamedQuery(name = "findThetaExceptionByCreatedBy", query = "select myThetaException from ThetaException myThetaException where myThetaException.createdBy = ?1"),
		@NamedQuery(name = "findThetaExceptionByCreatedByContaining", query = "select myThetaException from ThetaException myThetaException where myThetaException.createdBy like ?1"),
		@NamedQuery(name = "findThetaExceptionByCreatedDate", query = "select myThetaException from ThetaException myThetaException where myThetaException.createdDate = ?1"),
		@NamedQuery(name = "findThetaExceptionByCreatedDateAfter", query = "select myThetaException from ThetaException myThetaException where myThetaException.createdDate > ?1"),
		@NamedQuery(name = "findThetaExceptionByCreatedDateBefore", query = "select myThetaException from ThetaException myThetaException where myThetaException.createdDate < ?1"),
		@NamedQuery(name = "findThetaExceptionByExceptionText", query = "select myThetaException from ThetaException myThetaException where myThetaException.exceptionText = ?1"),
		@NamedQuery(name = "findThetaExceptionByExceptionTextContaining", query = "select myThetaException from ThetaException myThetaException where myThetaException.exceptionText like ?1"),
		@NamedQuery(name = "findThetaExceptionByOrderDetailText", query = "select myThetaException from ThetaException myThetaException where myThetaException.orderDetailText = ?1"),
		@NamedQuery(name = "findThetaExceptionByOrderDetailTextContaining", query = "select myThetaException from ThetaException myThetaException where myThetaException.orderDetailText like ?1"),
		@NamedQuery(name = "findThetaExceptionByPrimaryKey", query = "select myThetaException from ThetaException myThetaException where myThetaException.thetaExceptionId = ?1"),
		@NamedQuery(name = "findThetaExceptionByThetaExceptionId", query = "select myThetaException from ThetaException myThetaException where myThetaException.thetaExceptionId = ?1"),
		@NamedQuery(name = "findThetaExceptionByUpdatedBy", query = "select myThetaException from ThetaException myThetaException where myThetaException.updatedBy = ?1"),
		@NamedQuery(name = "findThetaExceptionByUpdatedByContaining", query = "select myThetaException from ThetaException myThetaException where myThetaException.updatedBy like ?1"),
		@NamedQuery(name = "findThetaExceptionByUpdatedDate", query = "select myThetaException from ThetaException myThetaException where myThetaException.updatedDate = ?1"),
		@NamedQuery(name = "findThetaExceptionByUpdatedDateAfter", query = "select myThetaException from ThetaException myThetaException where myThetaException.updatedDate > ?1"),
		@NamedQuery(name = "findThetaExceptionByUpdatedDateBefore", query = "select myThetaException from ThetaException myThetaException where myThetaException.updatedDate < ?1") })
@Table(schema = "THETA", name = "THETA_EXCEPTION")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "theta11/com/theta/domain", name = "ThetaException")
public class ThetaException implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "THETA_EXCEPTION_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer thetaExceptionId;
	/**
	 */

	@Column(name = "STACK_TRACE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	byte[] stackTrace;
	/**
	 */

	@Column(name = "EXCEPTION_TEXT", length = 50, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String exceptionText;
	/**
	 */

	@Column(name = "CONTRACT_DETAIL_TEXT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String contractDetailText;
	/**
	 */

	@Column(name = "ORDER_DETAIL_TEXT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String orderDetailText;
	/**
	 */

	@Column(name = "CREATED_BY", length = 50, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String createdBy;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", nullable = false)
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
	public void setThetaExceptionId(Integer thetaExceptionId) {
		this.thetaExceptionId = thetaExceptionId;
	}

	/**
	 */
	public Integer getThetaExceptionId() {
		return this.thetaExceptionId;
	}

	/**
	 */
	public void setStackTrace(byte[] stackTrace) {
		this.stackTrace = stackTrace;
	}

	/**
	 */
	public byte[] getStackTrace() {
		return this.stackTrace;
	}

	/**
	 */
	public void setExceptionText(String exceptionText) {
		this.exceptionText = exceptionText;
	}

	/**
	 */
	public String getExceptionText() {
		return this.exceptionText;
	}

	/**
	 */
	public void setContractDetailText(String contractDetailText) {
		this.contractDetailText = contractDetailText;
	}

	/**
	 */
	public String getContractDetailText() {
		return this.contractDetailText;
	}

	/**
	 */
	public void setOrderDetailText(String orderDetailText) {
		this.orderDetailText = orderDetailText;
	}

	/**
	 */
	public String getOrderDetailText() {
		return this.orderDetailText;
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
	public ThetaException() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(ThetaException that) {
		setThetaExceptionId(that.getThetaExceptionId());
		setStackTrace(that.getStackTrace());
		setExceptionText(that.getExceptionText());
		setContractDetailText(that.getContractDetailText());
		setOrderDetailText(that.getOrderDetailText());
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

		buffer.append("thetaExceptionId=[").append(thetaExceptionId).append("] ");
		buffer.append("stackTrace=[").append(stackTrace).append("] ");
		buffer.append("exceptionText=[").append(exceptionText).append("] ");
		buffer.append("contractDetailText=[").append(contractDetailText).append("] ");
		buffer.append("orderDetailText=[").append(orderDetailText).append("] ");
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
		result = (int) (prime * result + ((thetaExceptionId == null) ? 0 : thetaExceptionId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof ThetaException))
			return false;
		ThetaException equalCheck = (ThetaException) obj;
		if ((thetaExceptionId == null && equalCheck.thetaExceptionId != null) || (thetaExceptionId != null && equalCheck.thetaExceptionId == null))
			return false;
		if (thetaExceptionId != null && !thetaExceptionId.equals(equalCheck.thetaExceptionId))
			return false;
		return true;
	}
}

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
		@NamedQuery(name = "findAllRequestSeqs", query = "select myRequestSeq from RequestSeq myRequestSeq"),
		@NamedQuery(name = "findRequestSeqByPrimaryKey", query = "select myRequestSeq from RequestSeq myRequestSeq where myRequestSeq.requestSeqId = ?1"),
		@NamedQuery(name = "findRequestSeqByRequestSeqId", query = "select myRequestSeq from RequestSeq myRequestSeq where myRequestSeq.requestSeqId = ?1"),
		@NamedQuery(name = "findRequestSeqByRequestSeqNo", query = "select myRequestSeq from RequestSeq myRequestSeq where myRequestSeq.requestSeqNo = ?1"),
		@NamedQuery(name = "findRequestSeqByUpdatedBy", query = "select myRequestSeq from RequestSeq myRequestSeq where myRequestSeq.updatedBy = ?1"),
		@NamedQuery(name = "findRequestSeqByUpdatedByContaining", query = "select myRequestSeq from RequestSeq myRequestSeq where myRequestSeq.updatedBy like ?1"),
		@NamedQuery(name = "findRequestSeqByUpdatedDate", query = "select myRequestSeq from RequestSeq myRequestSeq where myRequestSeq.updatedDate = ?1"),
		@NamedQuery(name = "findRequestSeqByUpdatedDateAfter", query = "select myRequestSeq from RequestSeq myRequestSeq where myRequestSeq.updatedDate > ?1"),
		@NamedQuery(name = "findRequestSeqByUpdatedDateBefore", query = "select myRequestSeq from RequestSeq myRequestSeq where myRequestSeq.updatedDate < ?1") })
@Table(schema = "THETA", name = "REQUEST_SEQ")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "req_seq_proj/com/theta/domain", name = "RequestSeq")
public class RequestSeq implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "REQUEST_SEQ_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer requestSeqId;
	/**
	 */

	@Column(name = "REQUEST_SEQ_NO", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer requestSeqNo;
	/**
	 */

	@Column(name = "UPDATED_BY", length = 50)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String updatedBy;
	/**
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar updatedDate;

	/**
	 */
	public void setRequestSeqId(Integer requestSeqId) {
		this.requestSeqId = requestSeqId;
	}

	/**
	 */
	public Integer getRequestSeqId() {
		return this.requestSeqId;
	}

	/**
	 */
	public void setRequestSeqNo(Integer requestSeqNo) {
		this.requestSeqNo = requestSeqNo;
	}

	/**
	 */
	public Integer getRequestSeqNo() {
		return this.requestSeqNo;
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
	public RequestSeq() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(RequestSeq that) {
		setRequestSeqId(that.getRequestSeqId());
		setRequestSeqNo(that.getRequestSeqNo());
		setUpdatedBy(that.getUpdatedBy());
		setUpdatedDate(that.getUpdatedDate());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("requestSeqId=[").append(requestSeqId).append("] ");
		buffer.append("requestSeqNo=[").append(requestSeqNo).append("] ");
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
		result = (int) (prime * result + ((requestSeqId == null) ? 0 : requestSeqId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof RequestSeq))
			return false;
		RequestSeq equalCheck = (RequestSeq) obj;
		if ((requestSeqId == null && equalCheck.requestSeqId != null) || (requestSeqId != null && equalCheck.requestSeqId == null))
			return false;
		if (requestSeqId != null && !requestSeqId.equals(equalCheck.requestSeqId))
			return false;
		return true;
	}
}

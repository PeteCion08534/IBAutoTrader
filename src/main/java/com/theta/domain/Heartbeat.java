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
		@NamedQuery(name = "findAllHeartbeats", query = "select myHeartbeat from Heartbeat myHeartbeat"),
		@NamedQuery(name = "findMaxHeartbeat", query = "select myHeartbeat from Heartbeat myHeartbeat"),
		@NamedQuery(name = "findHeartbeatByCreatedBy", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.createdBy = ?1"),
		@NamedQuery(name = "findHeartbeatByCreatedByContaining", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.createdBy like ?1"),
		@NamedQuery(name = "findHeartbeatByCreatedDate", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.createdDate = ?1"),
		@NamedQuery(name = "findHeartbeatByCreatedDateAfter", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.createdDate > ?1"),
		@NamedQuery(name = "findHeartbeatByCreatedDateBefore", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.createdDate < ?1"),
		@NamedQuery(name = "findHeartbeatByHeartbeatDate", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.heartbeatDate = ?1"),
		@NamedQuery(name = "findHeartbeatByHeartbeatDateAfter", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.heartbeatDate > ?1"),
		@NamedQuery(name = "findHeartbeatByHeartbeatDateBefore", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.heartbeatDate < ?1"),
		@NamedQuery(name = "findHeartbeatByHeartbeatId", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.heartbeatId = ?1"),
		@NamedQuery(name = "findHeartbeatByHeartbeatLog", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.heartbeatLog = ?1"),
		@NamedQuery(name = "findHeartbeatByHeartbeatLogContaining", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.heartbeatLog like ?1"),
		@NamedQuery(name = "findHeartbeatByInProcess", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.inProcess = ?1"),
		@NamedQuery(name = "findHeartbeatByInProcessContaining", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.inProcess like ?1"),
		@NamedQuery(name = "findHeartbeatByPrimaryKey", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.heartbeatId = ?1"),
		@NamedQuery(name = "findHeartbeatByTerminationStatus", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.terminationStatus = ?1"),
		@NamedQuery(name = "findHeartbeatByTerminationStatusContaining", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.terminationStatus like ?1"),
		@NamedQuery(name = "findHeartbeatByThreadId", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.threadId = ?1"),
		@NamedQuery(name = "findHeartbeatByUpdatedBy", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.updatedBy = ?1"),
		@NamedQuery(name = "findHeartbeatByUpdatedByContaining", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.updatedBy like ?1"),
		@NamedQuery(name = "findHeartbeatByUpdatedDate", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.updatedDate = ?1"),
		@NamedQuery(name = "findHeartbeatByUpdatedDateAfter", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.updatedDate > ?1"),
		@NamedQuery(name = "findHeartbeatByUpdatedDateBefore", query = "select myHeartbeat from Heartbeat myHeartbeat where myHeartbeat.updatedDate < ?1") })
@Table(schema = "THETA", name = "HEARTBEAT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "theta11/com/theta/domain", name = "Heartbeat")
public class Heartbeat implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "HEARTBEAT_ID", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer heartbeatId;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HEARTBEAT_DATE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar heartbeatDate;
	/**
	 */

	@Column(name = "IN_PROCESS", length = 1, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String inProcess;
	/**
	 */

	@Column(name = "THREAD_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer threadId;
	/**
	 */

	@Column(name = "TERMINATION_STATUS", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String terminationStatus;
	/**
	 */

	@Column(name = "SNAPSHOT")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	byte[] snapshot;
	/**
	 */

	@Column(name = "HEARTBEAT_LOG")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String heartbeatLog;
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
	public void setHeartbeatId(Integer heartbeatId) {
		this.heartbeatId = heartbeatId;
	}

	/**
	 */
	public Integer getHeartbeatId() {
		return this.heartbeatId;
	}

	/**
	 */
	public void setHeartbeatDate(Calendar heartbeatDate) {
		this.heartbeatDate = heartbeatDate;
	}

	/**
	 */
	public Calendar getHeartbeatDate() {
		return this.heartbeatDate;
	}

	/**
	 */
	public void setInProcess(String inProcess) {
		this.inProcess = inProcess;
	}

	/**
	 */
	public String getInProcess() {
		return this.inProcess;
	}

	/**
	 */
	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
	}

	/**
	 */
	public Integer getThreadId() {
		return this.threadId;
	}

	/**
	 */
	public void setTerminationStatus(String terminationStatus) {
		this.terminationStatus = terminationStatus;
	}

	/**
	 */
	public String getTerminationStatus() {
		return this.terminationStatus;
	}

	/**
	 */
	public void setSnapshot(byte[] snapshot) {
		this.snapshot = snapshot;
	}

	/**
	 */
	public byte[] getSnapshot() {
		return this.snapshot;
	}

	/**
	 */
	public void setHeartbeatLog(String heartbeatLog) {
		this.heartbeatLog = heartbeatLog;
	}

	/**
	 */
	public String getHeartbeatLog() {
		return this.heartbeatLog;
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
	public Heartbeat() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Heartbeat that) {
		setHeartbeatId(that.getHeartbeatId());
		setHeartbeatDate(that.getHeartbeatDate());
		setInProcess(that.getInProcess());
		setThreadId(that.getThreadId());
		setTerminationStatus(that.getTerminationStatus());
		setSnapshot(that.getSnapshot());
		setHeartbeatLog(that.getHeartbeatLog());
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

		buffer.append("heartbeatId=[").append(heartbeatId).append("] ");
		buffer.append("heartbeatDate=[").append(heartbeatDate).append("] ");
		buffer.append("inProcess=[").append(inProcess).append("] ");
		buffer.append("threadId=[").append(threadId).append("] ");
		buffer.append("terminationStatus=[").append(terminationStatus).append("] ");
		buffer.append("snapshot=[").append(snapshot).append("] ");
		buffer.append("heartbeatLog=[").append(heartbeatLog).append("] ");
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
		result = (int) (prime * result + ((heartbeatId == null) ? 0 : heartbeatId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Heartbeat))
			return false;
		Heartbeat equalCheck = (Heartbeat) obj;
		if ((heartbeatId == null && equalCheck.heartbeatId != null) || (heartbeatId != null && equalCheck.heartbeatId == null))
			return false;
		if (heartbeatId != null && !heartbeatId.equals(equalCheck.heartbeatId))
			return false;
		return true;
	}
}

package com.theta.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries( {
		@NamedQuery(name = "findAllSnapshots", query = "select mySnapshot from Snapshot mySnapshot"),
		@NamedQuery(name = "findSnapshotByCreatedBy", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.createdBy = ?1"),
		@NamedQuery(name = "findSnapshotByCreatedByContaining", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.createdBy like ?1"),
		@NamedQuery(name = "findSnapshotByCreatedDate", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.createdDate = ?1"),
		@NamedQuery(name = "findSnapshotByCreatedDateAfter", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.createdDate > ?1"),
		@NamedQuery(name = "findSnapshotByCreatedDateBefore", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.createdDate < ?1"),
		@NamedQuery(name = "findSnapshotByExpirtyDay", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.expirtyDay = ?1"),
		@NamedQuery(name = "findSnapshotByExpiryMonth", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.expiryMonth = ?1"),
		@NamedQuery(name = "findSnapshotByExpiryYear", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.expiryYear = ?1"),
		@NamedQuery(name = "findSnapshotByPrimaryKey", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.snapshotId = ?1"),
		@NamedQuery(name = "findSnapshotBySnapshotDate", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.snapshotDate = ?1"),
		@NamedQuery(name = "findSnapshotBySnapshotDateAfter", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.snapshotDate > ?1"),
		@NamedQuery(name = "findSnapshotBySnapshotDateBefore", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.snapshotDate < ?1"),
		@NamedQuery(name = "findSnapshotBySnapshotId", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.snapshotId = ?1"),
		@NamedQuery(name = "findSnapshotBySymbol", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.symbol = ?1"),
		@NamedQuery(name = "findSnapshotBySymbolContaining", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.symbol like ?1"),
		@NamedQuery(name = "findSnapshotBySymbolPrice", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.symbolPrice = ?1"),
		@NamedQuery(name = "findSnapshotByUpdatedBy", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.updatedBy = ?1"),
		@NamedQuery(name = "findSnapshotByUpdatedByContaining", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.updatedBy like ?1"),
		@NamedQuery(name = "findSnapshotByUpdatedDate", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.updatedDate = ?1"),
		@NamedQuery(name = "findSnapshotByUpdatedDateAfter", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.updatedDate > ?1"),
		@NamedQuery(name = "findSnapshotByUpdatedDateBefore", query = "select mySnapshot from Snapshot mySnapshot where mySnapshot.updatedDate < ?1") })

		
@Table(schema = "THETA", name = "SNAPSHOT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "theta11/com/theta/domain", name = "Snapshot")
@XmlRootElement(namespace = "theta11/com/theta/domain")
public class Snapshot implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SNAPSHOT_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer snapshotId;
	/**
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "SNAPSHOT_DATE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar snapshotDate;
	/**
	 */

	@Column(name = "SYMBOL", length = 10, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String symbol;
	/**
	 */

	@Column(name = "SYMBOL_PRICE", scale = 2, precision = 6)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal symbolPrice;
	/**
	 */

	@Column(name = "EXPIRY_YEAR")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer expiryYear;
	/**
	 */

	@Column(name = "EXPIRY_MONTH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer expiryMonth;
	/**
	 */

	@Column(name = "EXPIRTY_DAY")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer expirtyDay;
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
	@OneToMany(mappedBy = "snapshot", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<com.theta.domain.SnapshotOption> snapshotOptions;

	/**
	 */
	public void setSnapshotId(Integer snapshotId) {
		this.snapshotId = snapshotId;
	}

	/**
	 */
	public Integer getSnapshotId() {
		return this.snapshotId;
	}

	/**
	 */
	public void setSnapshotDate(Calendar snapshotDate) {
		this.snapshotDate = snapshotDate;
	}

	/**
	 */
	public Calendar getSnapshotDate() {
		return this.snapshotDate;
	}

	/**
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 */
	public String getSymbol() {
		return this.symbol;
	}

	/**
	 */
	public void setSymbolPrice(BigDecimal symbolPrice) {
		this.symbolPrice = symbolPrice;
	}

	/**
	 */
	public BigDecimal getSymbolPrice() {
		return this.symbolPrice;
	}

	/**
	 */
	public void setExpiryYear(Integer expiryYear) {
		this.expiryYear = expiryYear;
	}

	/**
	 */
	public Integer getExpiryYear() {
		return this.expiryYear;
	}

	/**
	 */
	public void setExpiryMonth(Integer expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	/**
	 */
	public Integer getExpiryMonth() {
		return this.expiryMonth;
	}

	/**
	 */
	public void setExpirtyDay(Integer expirtyDay) {
		this.expirtyDay = expirtyDay;
	}

	/**
	 */
	public Integer getExpirtyDay() {
		return this.expirtyDay;
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
	public void setSnapshotOptions(Set<SnapshotOption> snapshotOptions) {
		this.snapshotOptions = snapshotOptions;
	}

	/**
	 */
	public Set<SnapshotOption> getSnapshotOptions() {
		if (snapshotOptions == null) {
			snapshotOptions = new java.util.LinkedHashSet<com.theta.domain.SnapshotOption>();
		}
		return snapshotOptions;
	}

	/**
	 */
	public Snapshot() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Snapshot that) {
		setSnapshotId(that.getSnapshotId());
		setSnapshotDate(that.getSnapshotDate());
		setSymbol(that.getSymbol());
		setSymbolPrice(that.getSymbolPrice());
		setExpiryYear(that.getExpiryYear());
		setExpiryMonth(that.getExpiryMonth());
		setExpirtyDay(that.getExpirtyDay());
		setCreatedBy(that.getCreatedBy());
		setCreatedDate(that.getCreatedDate());
		setUpdatedBy(that.getUpdatedBy());
		setUpdatedDate(that.getUpdatedDate());
		setSnapshotOptions(new java.util.LinkedHashSet<com.theta.domain.SnapshotOption>(that.getSnapshotOptions()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snapshotId=[").append(snapshotId).append("] ");
		buffer.append("snapshotDate=[").append(snapshotDate).append("] ");
		buffer.append("symbol=[").append(symbol).append("] ");
		buffer.append("symbolPrice=[").append(symbolPrice).append("] ");
		buffer.append("expiryYear=[").append(expiryYear).append("] ");
		buffer.append("expiryMonth=[").append(expiryMonth).append("] ");
		buffer.append("expirtyDay=[").append(expirtyDay).append("] ");
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
		result = (int) (prime * result + ((snapshotId == null) ? 0 : snapshotId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Snapshot))
			return false;
		Snapshot equalCheck = (Snapshot) obj;
		if ((snapshotId == null && equalCheck.snapshotId != null) || (snapshotId != null && equalCheck.snapshotId == null))
			return false;
		if (snapshotId != null && !snapshotId.equals(equalCheck.snapshotId))
			return false;
		return true;
	}
}

package com.theta.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Calendar;
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
		@NamedQuery(name = "findAllSnapshotOptions", query = "select mySnapshotOption from SnapshotOption mySnapshotOption"),
		@NamedQuery(name = "findSnapshotOptionByAskPrice", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.askPrice = ?1"),
		@NamedQuery(name = "findSnapshotOptionByBidPrice", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.bidPrice = ?1"),
		@NamedQuery(name = "findSnapshotOptionByCreatedBy", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.createdBy = ?1"),
		@NamedQuery(name = "findSnapshotOptionByCreatedByContaining", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.createdBy like ?1"),
		@NamedQuery(name = "findSnapshotOptionByCreatedDate", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.createdDate = ?1"),
		@NamedQuery(name = "findSnapshotOptionByCreatedDateAfter", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.createdDate > ?1"),
		@NamedQuery(name = "findSnapshotOptionByCreatedDateBefore", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.createdDate < ?1"),
		@NamedQuery(name = "findSnapshotOptionByLastPrice", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.lastPrice = ?1"),
		@NamedQuery(name = "findSnapshotOptionByLocalSymbol", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.localSymbol = ?1"),
		@NamedQuery(name = "findSnapshotOptionByLocalSymbolContaining", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.localSymbol like ?1"),
		@NamedQuery(name = "findSnapshotOptionByOptRight", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.optRight = ?1"),
		@NamedQuery(name = "findSnapshotOptionByOptRightContaining", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.optRight like ?1"),
		@NamedQuery(name = "findSnapshotOptionByPrimaryKey", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.snapshotOptionId = ?1"),
		@NamedQuery(name = "findSnapshotOptionBySnapshotOptionId", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.snapshotOptionId = ?1"),
		@NamedQuery(name = "findSnapshotOptionByStrike", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.strike = ?1"),
		@NamedQuery(name = "findSnapshotOptionByTradingTimeCloseToday", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.tradingTimeCloseToday = ?1"),
		@NamedQuery(name = "findSnapshotOptionByTradingTimeCloseTodayAfter", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.tradingTimeCloseToday > ?1"),
		@NamedQuery(name = "findSnapshotOptionByTradingTimeCloseTodayBefore", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.tradingTimeCloseToday < ?1"),
		@NamedQuery(name = "findSnapshotOptionByTradingTimeOpenToday", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.tradingTimeOpenToday = ?1"),
		@NamedQuery(name = "findSnapshotOptionByTradingTimeOpenTodayAfter", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.tradingTimeOpenToday > ?1"),
		@NamedQuery(name = "findSnapshotOptionByTradingTimeOpenTodayBefore", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.tradingTimeOpenToday < ?1"),
		@NamedQuery(name = "findSnapshotOptionByUpdatedBy", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.updatedBy = ?1"),
		@NamedQuery(name = "findSnapshotOptionByUpdatedByContaining", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.updatedBy like ?1"),
		@NamedQuery(name = "findSnapshotOptionByUpdatedDate", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.updatedDate = ?1"),
		@NamedQuery(name = "findSnapshotOptionByUpdatedDateAfter", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.updatedDate > ?1"),
		@NamedQuery(name = "findSnapshotOptionByUpdatedDateBefore", query = "select mySnapshotOption from SnapshotOption mySnapshotOption where mySnapshotOption.updatedDate < ?1") })
@Table(schema = "THETA", name = "SNAPSHOT_OPTION")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "theta11/com/theta/domain", name = "SnapshotOption")
public class SnapshotOption implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SNAPSHOT_OPTION_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer snapshotOptionId;
	/**
	 */

	@Column(name = "OPT_RIGHT", length = 3, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String optRight;
	/**
	 */

	@Column(name = "LOCAL_SYMBOL", length = 20, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String localSymbol;
	/**
	 */

	@Column(name = "STRIKE", scale = 2, precision = 10, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal strike;
	/**
	 */

	@Column(name = "BID_PRICE", scale = 2, precision = 10, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal bidPrice;
	/**
	 */

	@Column(name = "ASK_PRICE", scale = 2, precision = 10, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal askPrice;
	/**
	 */

	@Column(name = "LAST_PRICE", scale = 2, precision = 10, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal lastPrice;
	/**
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "TRADING_TIME_OPEN_TODAY")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar tradingTimeOpenToday;
	/**
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "TRADING_TIME_CLOSE_TODAY")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar tradingTimeCloseToday;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( { @JoinColumn(name = "SNAPSHOT_ID", referencedColumnName = "SNAPSHOT_ID", nullable = false) })
	@XmlTransient
	Snapshot snapshot;

	/**
	 */
	public void setSnapshotOptionId(Integer snapshotOptionId) {
		this.snapshotOptionId = snapshotOptionId;
	}

	/**
	 */
	public Integer getSnapshotOptionId() {
		return this.snapshotOptionId;
	}

	/**
	 */
	public void setOptRight(String optRight) {
		this.optRight = optRight;
	}

	/**
	 */
	public String getOptRight() {
		return this.optRight;
	}

	/**
	 */
	public void setLocalSymbol(String localSymbol) {
		this.localSymbol = localSymbol;
	}

	/**
	 */
	public String getLocalSymbol() {
		return this.localSymbol;
	}

	/**
	 */
	public void setStrike(BigDecimal strike) {
		this.strike = strike;
	}

	/**
	 */
	public BigDecimal getStrike() {
		return this.strike;
	}

	/**
	 */
	public void setBidPrice(BigDecimal bidPrice) {
		this.bidPrice = bidPrice;
	}

	/**
	 */
	public BigDecimal getBidPrice() {
		return this.bidPrice;
	}

	/**
	 */
	public void setAskPrice(BigDecimal askPrice) {
		this.askPrice = askPrice;
	}

	/**
	 */
	public BigDecimal getAskPrice() {
		return this.askPrice;
	}

	/**
	 */
	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	/**
	 */
	public BigDecimal getLastPrice() {
		return this.lastPrice;
	}

	/**
	 */
	public void setTradingTimeOpenToday(Calendar tradingTimeOpenToday) {
		this.tradingTimeOpenToday = tradingTimeOpenToday;
	}

	/**
	 */
	public Calendar getTradingTimeOpenToday() {
		return this.tradingTimeOpenToday;
	}

	/**
	 */
	public void setTradingTimeCloseToday(Calendar tradingTimeCloseToday) {
		this.tradingTimeCloseToday = tradingTimeCloseToday;
	}

	/**
	 */
	public Calendar getTradingTimeCloseToday() {
		return this.tradingTimeCloseToday;
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
	public void setSnapshot(Snapshot snapshot) {
		this.snapshot = snapshot;
	}

	/**
	 */
	public Snapshot getSnapshot() {
		return snapshot;
	}

	/**
	 */
	public SnapshotOption() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SnapshotOption that) {
		setSnapshotOptionId(that.getSnapshotOptionId());
		setOptRight(that.getOptRight());
		setLocalSymbol(that.getLocalSymbol());
		setStrike(that.getStrike());
		setBidPrice(that.getBidPrice());
		setAskPrice(that.getAskPrice());
		setLastPrice(that.getLastPrice());
		setTradingTimeOpenToday(that.getTradingTimeOpenToday());
		setTradingTimeCloseToday(that.getTradingTimeCloseToday());
		setCreatedBy(that.getCreatedBy());
		setCreatedDate(that.getCreatedDate());
		setUpdatedBy(that.getUpdatedBy());
		setUpdatedDate(that.getUpdatedDate());
		setSnapshot(that.getSnapshot());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snapshotOptionId=[").append(snapshotOptionId).append("] ");
		buffer.append("optRight=[").append(optRight).append("] ");
		buffer.append("localSymbol=[").append(localSymbol).append("] ");
		buffer.append("strike=[").append(strike).append("] ");
		buffer.append("bidPrice=[").append(bidPrice).append("] ");
		buffer.append("askPrice=[").append(askPrice).append("] ");
		buffer.append("lastPrice=[").append(lastPrice).append("] ");
		buffer.append("tradingTimeOpenToday=[").append(tradingTimeOpenToday).append("] ");
		buffer.append("tradingTimeCloseToday=[").append(tradingTimeCloseToday).append("] ");
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
		result = (int) (prime * result + ((snapshotOptionId == null) ? 0 : snapshotOptionId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SnapshotOption))
			return false;
		SnapshotOption equalCheck = (SnapshotOption) obj;
		if ((snapshotOptionId == null && equalCheck.snapshotOptionId != null) || (snapshotOptionId != null && equalCheck.snapshotOptionId == null))
			return false;
		if (snapshotOptionId != null && !snapshotOptionId.equals(equalCheck.snapshotOptionId))
			return false;
		return true;
	}
}

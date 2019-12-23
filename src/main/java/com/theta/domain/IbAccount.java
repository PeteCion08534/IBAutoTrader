package com.theta.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllIbAccounts", query = "select myIbAccount from IbAccount myIbAccount"),
		@NamedQuery(name = "findIbAccountByClientId", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.clientId = ?1"),
		@NamedQuery(name = "findIbAccountByClientIdContaining", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.clientId like ?1"),
		@NamedQuery(name = "findIbAccountByCreatedBy", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.createdBy = ?1"),
		@NamedQuery(name = "findIbAccountByCreatedByContaining", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.createdBy like ?1"),
		@NamedQuery(name = "findIbAccountByCreatedDate", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.createdDate = ?1"),
		@NamedQuery(name = "findIbAccountByCreatedDateAfter", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.createdDate > ?1"),
		@NamedQuery(name = "findIbAccountByCreatedDateBefore", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.createdDate < ?1"),
		@NamedQuery(name = "findIbAccountByIbAccountId", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.ibAccountId = ?1"),
		@NamedQuery(name = "findIbAccountByIbAccountIdExt", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.ibAccountIdExt = ?1"),
		@NamedQuery(name = "findIbAccountByIbAccountIdExtContaining", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.ibAccountIdExt like ?1"),
		@NamedQuery(name = "findIbAccountByIpAddress", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.ipAddress = ?1"),
		@NamedQuery(name = "findIbAccountByIpAddressContaining", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.ipAddress like ?1"),
		@NamedQuery(name = "findIbAccountByPort", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.port = ?1"),
		@NamedQuery(name = "findIbAccountByPrimaryKey", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.ibAccountId = ?1"),
		@NamedQuery(name = "findIbAccountByUpdatedBy", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.updatedBy = ?1"),
		@NamedQuery(name = "findIbAccountByUpdatedByContaining", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.updatedBy like ?1"),
		@NamedQuery(name = "findIbAccountByUpdatedDate", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.updatedDate = ?1"),
		@NamedQuery(name = "findIbAccountByUpdatedDateAfter", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.updatedDate > ?1"),
		@NamedQuery(name = "findIbAccountByUpdatedDateBefore", query = "select myIbAccount from IbAccount myIbAccount where myIbAccount.updatedDate < ?1") })
@Table(schema = "THETA", name = "IB_ACCOUNT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "theta11/com/theta/domain", name = "IbAccount")
@XmlRootElement(namespace = "theta11/com/theta/domain")
public class IbAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "IB_ACCOUNT_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer ibAccountId;
	/**
	 */

	@Column(name = "IB_ACCOUNT_ID_EXT", length = 20, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String ibAccountIdExt;
	/**
	 */

	@Column(name = "IP_ADDRESS", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String ipAddress;
	/**
	 */

	@Column(name = "PORT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer port;
	/**
	 */

	@Column(name = "CLIENT_ID", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String clientId;
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
	@Column(name = "STOP_AT_MARGIN_PCT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stopAtMarginPct;
	/**
	 */

	/**
	 */
	@OneToMany(mappedBy = "ibAccount", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<com.theta.domain.StrategyAccount> strategyAccounts;

	/**
	 */
	public void setIbAccountId(Integer ibAccountId) {
		this.ibAccountId = ibAccountId;
	}

	/**
	 */
	public Integer getIbAccountId() {
		return this.ibAccountId;
	}

	/**
	 */
	public void setIbAccountIdExt(String ibAccountIdExt) {
		this.ibAccountIdExt = ibAccountIdExt;
	}

	/**
	 */
	public String getIbAccountIdExt() {
		return this.ibAccountIdExt;
	}

	/**
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 */
	public String getIpAddress() {
		return this.ipAddress;
	}

	/**
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 */
	public Integer getPort() {
		return this.port;
	}

	/**
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 */
	public String getClientId() {
		return this.clientId;
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
	public void setStrategyAccounts(Set<StrategyAccount> strategyAccounts) {
		this.strategyAccounts = strategyAccounts;
	}

	/**
	 */
	public Set<StrategyAccount> getStrategyAccounts() {
		if (strategyAccounts == null) {
			strategyAccounts = new java.util.LinkedHashSet<com.theta.domain.StrategyAccount>();
		}
		return strategyAccounts;
	}

	public Integer getStopAtMarginPct() {
		return stopAtMarginPct;
	}

	/**
	 */
	public IbAccount() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(IbAccount that) {
		setIbAccountId(that.getIbAccountId());
		setIbAccountIdExt(that.getIbAccountIdExt());
		setIpAddress(that.getIpAddress());
		setPort(that.getPort());
		setClientId(that.getClientId());
		setCreatedBy(that.getCreatedBy());
		setCreatedDate(that.getCreatedDate());
		setUpdatedBy(that.getUpdatedBy());
		setUpdatedDate(that.getUpdatedDate());
		setStrategyAccounts(new java.util.LinkedHashSet<com.theta.domain.StrategyAccount>(that.getStrategyAccounts()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("ibAccountId=[").append(ibAccountId).append("] ");
		buffer.append("ibAccountIdExt=[").append(ibAccountIdExt).append("] ");
		buffer.append("ipAddress=[").append(ipAddress).append("] ");
		buffer.append("port=[").append(port).append("] ");
		buffer.append("clientId=[").append(clientId).append("] ");
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
		result = (int) (prime * result + ((ibAccountId == null) ? 0 : ibAccountId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof IbAccount))
			return false;
		IbAccount equalCheck = (IbAccount) obj;
		if ((ibAccountId == null && equalCheck.ibAccountId != null) || (ibAccountId != null && equalCheck.ibAccountId == null))
			return false;
		if (ibAccountId != null && !ibAccountId.equals(equalCheck.ibAccountId))
			return false;
		return true;
	}
}

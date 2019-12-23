package com.theta.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

import org.apache.log4j.Logger;

import com.theta.process.ThetaMain;

/**
 */

@Entity
@NamedQueries( {
		@NamedQuery(name = "findAllStrategyAccounts", query = "select myStrategyAccount from StrategyAccount myStrategyAccount"),
		@NamedQuery(name = "findStrategyAccountByPrimaryKey", query = "select myStrategyAccount from StrategyAccount myStrategyAccount where myStrategyAccount.strategyAccountId = ?1"),
		@NamedQuery(name = "findStrategyAccountByStrategyAccountId", query = "select myStrategyAccount from StrategyAccount myStrategyAccount where myStrategyAccount.strategyAccountId = ?1") })
@Table(schema = "THETA", name = "STRATEGY_ACCOUNT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "theta11/com/theta/domain", name = "StrategyAccount")
@XmlRootElement(namespace = "theta11/com/theta/domain")
public class StrategyAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(StrategyAccount.class);

	/**
	 */

	@Column(name = "STRATEGY_ACCOUNT_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer strategyAccountId;

	/**
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( { @JoinColumn(name = "STRATEGY_ID", referencedColumnName = "STRATEGY_ID", nullable = false) })
	@XmlTransient
	Strategy strategy;
	/**
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( { @JoinColumn(name = "IB_ACCOUNT_ID", referencedColumnName = "IB_ACCOUNT_ID", nullable = false) })
	@XmlTransient
	IbAccount ibAccount;
	/**
	 */
	@Column(name = "STK_INVEST_AMT_DOLLAR")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stkInvestAmtDollar;
	
	/**
	 */
	@Column(name = "STK_INVEST_AMT_PCT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stkInvestAmtPct;
	//@OneToMany(mappedBy = "strategyAccount", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	//@XmlElement(name = "", namespace = "")
	//java.util.Set<com.theta.domain.Position> positions;

	/*
	 * MOVED From Strategy
	 */
	//@OneToMany(mappedBy = "strategyAccount", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	//@XmlElement(name = "", namespace = "")
	//java.util.Set<com.theta.domain.ProfitLoss> profitLosses;

	
	/**
	 */
	public void setStrategyAccountId(Integer strategyAccountId) {
		this.strategyAccountId = strategyAccountId;
	}

	/**
	 */
	public Integer getStrategyAccountId() {
		return this.strategyAccountId;
	}

	/**
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 */
	public Strategy getStrategy() {
		return strategy;
	}

	/**
	 */
	public void setIbAccount(IbAccount ibAccount) {
		this.ibAccount = ibAccount;
	}

	/**
	 */
	public IbAccount getIbAccount() {
		return ibAccount;
	}

	public Integer getStkInvestAmtDollar() {
		log.error("*** DEPRECATED - strategyAccount.getStkInvestAmtDollar ***");
		return stkInvestAmtDollar;
	}

//	public void setStkInvestAmtDollar(Integer stkInvestAmtDollar) {
//		this.stkInvestAmtDollar = stkInvestAmtDollar;
//	}
	

	public Integer getStkInvestAmtPct() {
		return stkInvestAmtPct;
	}

	
	
	/**
	 */
	//public void setPositions(Set<Position> positions) {
	//	this.positions = positions;
	//}

	/**
	 */
	/*
	 * MOVED FROM Strategy:
	 */
/*
	public void setProfitLosses(Set<ProfitLoss> profitLosses) {
		this.profitLosses = profitLosses;
	}

	public Set<ProfitLoss> getProfitLosses() {
		if (profitLosses == null) {
			profitLosses = new java.util.LinkedHashSet<com.theta.domain.ProfitLoss>();
		}
		return profitLosses;
	}
*/
	
	/**
	 */
	//public Set<Position> getPositions() {
	//	if (positions == null) {
	//		positions = new java.util.LinkedHashSet<com.theta.domain.Position>();
	//	}
	//	return positions;
	//}


	/**
	 */
	public StrategyAccount() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(StrategyAccount that) {
		setStrategyAccountId(that.getStrategyAccountId());
		setStrategy(that.getStrategy());
		setIbAccount(that.getIbAccount());
		//setPositions(new java.util.LinkedHashSet<com.theta.domain.Position>(that.getPositions()));
		//setProfitLosses(new java.util.LinkedHashSet<com.theta.domain.ProfitLoss>(that.getProfitLosses()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("strategyAccountId=[").append(strategyAccountId).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((strategyAccountId == null) ? 0 : strategyAccountId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof StrategyAccount))
			return false;
		StrategyAccount equalCheck = (StrategyAccount) obj;
		if ((strategyAccountId == null && equalCheck.strategyAccountId != null) || (strategyAccountId != null && equalCheck.strategyAccountId == null))
			return false;
		if (strategyAccountId != null && !strategyAccountId.equals(equalCheck.strategyAccountId))
			return false;
		return true;
	}
}

package com.theta.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries( {
		@NamedQuery(name = "findAllProfitLosss", query = "select myProfitLoss from ProfitLoss myProfitLoss"),
		//@NamedQuery(name = "findProfitLossByCreatedBy", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.createdBy = ?1"),
		//@NamedQuery(name = "findProfitLossByCreatedByContaining", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.createdBy like ?1"),
		@NamedQuery(name = "findProfitLossByCreatedDate", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.createdDate = ?1"),
		@NamedQuery(name = "findProfitLossByCreatedDateAfter", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.createdDate > ?1"),
		@NamedQuery(name = "findProfitLossByCreatedDateBefore", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.createdDate < ?1"),
		@NamedQuery(name = "findProfitLossByPrimaryKey", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.profitLossId = ?1"),
		//@NamedQuery(name = "findProfitLossByProfitLossDate", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.profitLossDate = ?1"),
		//@NamedQuery(name = "findProfitLossByProfitLossDateAfter", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.profitLossDate > ?1"),
		//@NamedQuery(name = "findProfitLossByProfitLossDateBefore", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.profitLossDate < ?1"),
		@NamedQuery(name = "findProfitLossByProfitLossId", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.profitLossId = ?1")})

		// Unused code:
		//@NamedQuery(name = "findProfitLossByProfitLossStartDate", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.profitLossStartDate = ?1"),		//@NamedQuery(name = "findProfitLossByProfitLossStartDateAfter", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.profitLossStartDate > ?1"),
		//@NamedQuery(name = "findProfitLossByProfitLossStartDateBefore", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.profitLossStartDate < ?1"),
		//@NamedQuery(name = "findProfitLossByProfitOrLoss", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.profitOrLoss = ?1"),
		//@NamedQuery(name = "findProfitLossByProfitOrLossAmt", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.profitOrLossAmt = ?1"),
		//@NamedQuery(name = "findProfitLossByProfitOrLossContaining", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.profitOrLoss like ?1"),
		//@NamedQuery(name = "findProfitLossByUpdatedBy", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.updatedBy = ?1"),
		//@NamedQuery(name = "findProfitLossByUpdatedByContaining", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.updatedBy like ?1"),
		//@NamedQuery(name = "findProfitLossByUpdatedDate", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.updatedDate = ?1"),
		//@NamedQuery(name = "findProfitLossByUpdatedDateAfter", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.updatedDate > ?1"),
		//@NamedQuery(name = "findProfitLossByUpdatedDateBefore", query = "select myProfitLoss from ProfitLoss myProfitLoss where myProfitLoss.updatedDate < ?1") 

		
@Table(schema = "THETA", name = "PROFIT_LOSS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "theta11/com/theta/domain", name = "ProfitLoss")
public class ProfitLoss implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	
	@Column(name = "PROFIT_LOSS_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROFIT_LOSS_SEQ")
    @SequenceGenerator(name="PROFIT_LOSS_SEQ", sequenceName = "PROFIT_LOSS_SEQ")
	@XmlElement
	Integer profitLossId;
	/**
	 */
	//@Temporal(TemporalType.DATE)
	//@Column(name = "PROFIT_LOSS_START_DATE", nullable = false)
	//@Basic(fetch = FetchType.EAGER)
	//@XmlElement
	//Calendar profitLossStartDate;
	/**
	 */
	//@Temporal(TemporalType.DATE)
	//@Column(name = "PROFIT_LOSS_DATE", nullable = false)
	//@Basic(fetch = FetchType.EAGER)
	//@XmlElement
	//Calendar profitLossDate;
	/**
	 */

	//@Column(name = "PROFIT_OR_LOSS", length = 10)
	//@Basic(fetch = FetchType.EAGER)
	//@XmlElement
	//String profitOrLoss;
	/**
	 */

	//@Column(name = "PROFIT_OR_LOSS_AMT")
	//@Basic(fetch = FetchType.EAGER)
	//@XmlElement
	//Integer profitOrLossAmt;
	/**
	 */

	//@Column(name = "CREATED_BY", length = 50, nullable = false)
	//@Basic(fetch = FetchType.EAGER)
	//@XmlElement
	//String createdBy;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdDate;
	/**
	 */

	//@Column(name = "UPDATED_BY", length = 50)
	//@Basic(fetch = FetchType.EAGER)
	//@XmlElement
	//String updatedBy;
	/**
	 */
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "UPDATED_DATE")
	//@Basic(fetch = FetchType.EAGER)
	//@XmlElement
	//Calendar updatedDate;

	
	@Column(name = "PL_REALIZED")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer plRealized;
	
	@Column(name = "PL_UNREALIZED")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer plUnrealized;
	
	@Column(name = "TOTAL_RISKED")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer totalRisked;

	@Column(name = "NUM_WINS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer numWins;
	
	@Column(name = "NUM_LOSSES")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer numLosses;

	@Column(name = "SYMBOL_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer symbolPrice;

	@Column(name = "VIX_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer vixPrice;

	@Column(name = "SCORE_PCT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer scorePct;

	
	/**
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( { @JoinColumn(name = "STRATEGY_ACCOUNT_ID", referencedColumnName = "STRATEGY_ACCOUNT_ID", nullable = false) })
	@XmlTransient
	StrategyAccount strategyAccount;

	/**
	 */
	public void setProfitLossId(Integer profitLossId) {
		this.profitLossId = profitLossId;
	}

	/**
	 */
	public Integer getProfitLossId() {
		return this.profitLossId;
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
	//public void setUpdatedBy(String updatedBy) {
	//	this.updatedBy = updatedBy;
	//}

	/**
	 */
	//public String getUpdatedBy() {
	//	return this.updatedBy;
	//}

	/**
	 */
	//public void setUpdatedDate(Calendar updatedDate) {
	//	this.updatedDate = updatedDate;
	//}

	/**
	 */
	//public Calendar getUpdatedDate() {
	//	return this.updatedDate;
	//}

	/**
	 */
	public void setStrategyAccount(StrategyAccount strategyAccount) {
		this.strategyAccount = strategyAccount;
	}

	/**
	 */
	public StrategyAccount getStrategyAccount() {
		return strategyAccount;
	}

	/**
	 * @return the plRealized
	 */
	public Integer getPlRealized() {
		return plRealized;
	}

	/**
	 * @param plRealized the plRealized to set
	 */
	public void setPlRealized(Integer plRealized) {
		this.plRealized = plRealized;
	}

	/**
	 * @return the plUnrealized
	 */
	public Integer getPlUnrealized() {
		return plUnrealized;
	}

	/**
	 * @param plUnrealized the plUnrealized to set
	 */
	public void setPlUnrealized(Integer plUnrealized) {
		this.plUnrealized = plUnrealized;
	}

	/**
	 * @return the totalRisked
	 */
	public Integer getTotalRisked() {
		return totalRisked;
	}

	/**
	 * @param totalRisked the totalRisked to set
	 */
	public void setTotalRisked(Integer totalRisked) {
		this.totalRisked = totalRisked;
	}

	/**
	 * @return the numWins
	 */
	public Integer getNumWins() {
		return numWins;
	}

	/**
	 * @param numWins the numWins to set
	 */
	public void setNumWins(Integer numWins) {
		this.numWins = numWins;
	}

	/**
	 * @return the numLosses
	 */
	public Integer getNumLosses() {
		return numLosses;
	}

	/**
	 * @param numLosses the numLosses to set
	 */
	public void setNumLosses(Integer numLosses) {
		this.numLosses = numLosses;
	}

	public Integer getSymbolPrice() {
		return symbolPrice;
	}

	public void setSymbolPrice(Integer symbolPrice) {
		this.symbolPrice = symbolPrice;
	}

	public Integer getVixPrice() {
		return vixPrice;
	}

	public void setVixPrice(Integer vixPrice) {
		this.vixPrice = vixPrice;
	}

	public Integer getScorePct() {
		return scorePct;
	}

	public void setScorePct(Integer scorePct) {
		this.scorePct = scorePct;
	}

	/**
	 */
	public ProfitLoss() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(ProfitLoss that) {
		setProfitLossId(that.getProfitLossId());
		setCreatedDate(that.getCreatedDate());
		//setUpdatedBy(that.getUpdatedBy());
		//setUpdatedDate(that.getUpdatedDate());
		setStrategyAccount(that.getStrategyAccount());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", numLosses=");
		builder.append(numLosses);
		builder.append(", numWins=");
		builder.append(numWins);
		builder.append(", plRealized=");
		builder.append(plRealized);
		builder.append(", plUnrealized=");
		builder.append(plUnrealized);
		builder.append(", profitLossId=");
		builder.append(profitLossId);
		builder.append(", strategyAccount=");
		builder.append(strategyAccount);
		builder.append(", totalRisked=");
		builder.append(totalRisked);
		//builder.append(", updatedBy=");
		//builder.append(updatedBy);
		//builder.append(", updatedDate=");
		//builder.append(updatedDate);
		builder.append("]");
		return builder.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((profitLossId == null) ? 0 : profitLossId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof ProfitLoss))
			return false;
		ProfitLoss equalCheck = (ProfitLoss) obj;
		if ((profitLossId == null && equalCheck.profitLossId != null) || (profitLossId != null && equalCheck.profitLossId == null))
			return false;
		if (profitLossId != null && !profitLossId.equals(equalCheck.profitLossId))
			return false;
		return true;
	}
}

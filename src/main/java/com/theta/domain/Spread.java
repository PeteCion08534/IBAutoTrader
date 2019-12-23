package com.theta.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

import com.theta.client.DateUtil;
import com.theta.client.enums.EnterOrExitCode;
import com.theta.enums.ApprovalCode;
import com.theta.enums.OpenOrClosedCode;
import com.theta.enums.UpOrDownCode;
import com.theta.process.ThetaConstants;
import com.theta.process.ThetaExceptionExc;
import com.theta.process.ThetaUtil;

/**
 */

@Entity
@NamedQueries( {
		@NamedQuery(name = "findAllSpreads", query = "select mySpread from Spread mySpread"),
		//@NamedQuery(name = "findOpenOrPendSpreadsByPosition", query = "select mySpread from Spread mySpread where (mySpread.openOrClosed = 'OPEN' or mySpread.openOrClosed = 'PENDING') and mySpread.positionId = ?1"),
		//@NamedQuery(name = "findOpenOrPendSpreadsByPosition", query = "select mySpread from Spread mySpread where (mySpread.openOrClosed = 'PENDING' or mySpread.openOrClosed = 'OPEN') and mySpread.positionId = ?1"),
		@NamedQuery(name = "findSpreadByCreatedBy", query = "select mySpread from Spread mySpread where mySpread.createdBy = ?1"),
		@NamedQuery(name = "findSpreadByCreatedByContaining", query = "select mySpread from Spread mySpread where mySpread.createdBy like ?1"),
		@NamedQuery(name = "findSpreadByCreatedDate", query = "select mySpread from Spread mySpread where mySpread.createdDate = ?1"),
		@NamedQuery(name = "findSpreadByCreatedDateAfter", query = "select mySpread from Spread mySpread where mySpread.createdDate > ?1"),
		@NamedQuery(name = "findSpreadByCreatedDateBefore", query = "select mySpread from Spread mySpread where mySpread.createdDate < ?1"),
		@NamedQuery(name = "findSpreadByCurrentDate", query = "select mySpread from Spread mySpread where mySpread.currentDate = ?1"),
		@NamedQuery(name = "findSpreadByCurrentDateAfter", query = "select mySpread from Spread mySpread where mySpread.currentDate > ?1"),
		@NamedQuery(name = "findSpreadByCurrentDateBefore", query = "select mySpread from Spread mySpread where mySpread.currentDate < ?1"),
		@NamedQuery(name = "findSpreadByCurrentInsurancePrice", query = "select mySpread from Spread mySpread where mySpread.currentInsurancePrice = ?1"),
		@NamedQuery(name = "findSpreadByCurrentMoneymkrPrice", query = "select mySpread from Spread mySpread where mySpread.currentMoneymkrPrice = ?1"),
		@NamedQuery(name = "findSpreadByCurrentSecurityPrice", query = "select mySpread from Spread mySpread where mySpread.currentSecurityPrice = ?1"),
		@NamedQuery(name = "findSpreadByCurrentVixPrice", query = "select mySpread from Spread mySpread where mySpread.currentVixPrice = ?1"),
		@NamedQuery(name = "findSpreadByEnterCommission", query = "select mySpread from Spread mySpread where mySpread.enterCommission = ?1"),
		@NamedQuery(name = "findSpreadByEnterInsuranceDate", query = "select mySpread from Spread mySpread where mySpread.enterInsuranceDate = ?1"),
		@NamedQuery(name = "findSpreadByEnterInsuranceDateAfter", query = "select mySpread from Spread mySpread where mySpread.enterInsuranceDate > ?1"),
		@NamedQuery(name = "findSpreadByEnterInsuranceDateBefore", query = "select mySpread from Spread mySpread where mySpread.enterInsuranceDate < ?1"),
		@NamedQuery(name = "findSpreadByEnterInsurancePrice", query = "select mySpread from Spread mySpread where mySpread.enterInsurancePrice = ?1"),
		@NamedQuery(name = "findSpreadByEnterMoneymkrDate", query = "select mySpread from Spread mySpread where mySpread.enterMoneymkrDate = ?1"),
		@NamedQuery(name = "findSpreadByEnterMoneymkrDateAfter", query = "select mySpread from Spread mySpread where mySpread.enterMoneymkrDate > ?1"),
		@NamedQuery(name = "findSpreadByEnterMoneymkrDateBefore", query = "select mySpread from Spread mySpread where mySpread.enterMoneymkrDate < ?1"),
		@NamedQuery(name = "findSpreadByEnterMoneymkrPrice", query = "select mySpread from Spread mySpread where mySpread.enterMoneymkrPrice = ?1"),
		@NamedQuery(name = "findSpreadByEnterSecurityDate", query = "select mySpread from Spread mySpread where mySpread.enterSecurityDate = ?1"),
		@NamedQuery(name = "findSpreadByEnterSecurityDateAfter", query = "select mySpread from Spread mySpread where mySpread.enterSecurityDate > ?1"),
		@NamedQuery(name = "findSpreadByEnterSecurityDateBefore", query = "select mySpread from Spread mySpread where mySpread.enterSecurityDate < ?1"),
		@NamedQuery(name = "findSpreadByEnterSecurityPrice", query = "select mySpread from Spread mySpread where mySpread.enterSecurityPrice = ?1"),
		@NamedQuery(name = "findSpreadByExitAboveSpreadValue", query = "select mySpread from Spread mySpread where mySpread.exitAboveSpreadValue = ?1"),
		@NamedQuery(name = "findSpreadByExitBelowSpreadValue", query = "select mySpread from Spread mySpread where mySpread.exitBelowSpreadValue = ?1"),
		@NamedQuery(name = "findSpreadByExitCommission", query = "select mySpread from Spread mySpread where mySpread.exitCommission = ?1"),
		@NamedQuery(name = "findSpreadByExitInsuranceDate", query = "select mySpread from Spread mySpread where mySpread.exitInsuranceDate = ?1"),
		@NamedQuery(name = "findSpreadByExitInsuranceDateAfter", query = "select mySpread from Spread mySpread where mySpread.exitInsuranceDate > ?1"),
		@NamedQuery(name = "findSpreadByExitInsuranceDateBefore", query = "select mySpread from Spread mySpread where mySpread.exitInsuranceDate < ?1"),
		@NamedQuery(name = "findSpreadByExitInsurancePrice", query = "select mySpread from Spread mySpread where mySpread.exitInsurancePrice = ?1"),
		@NamedQuery(name = "findSpreadByExitMoneymkrDate", query = "select mySpread from Spread mySpread where mySpread.exitMoneymkrDate = ?1"),
		@NamedQuery(name = "findSpreadByExitMoneymkrDateAfter", query = "select mySpread from Spread mySpread where mySpread.exitMoneymkrDate > ?1"),
		@NamedQuery(name = "findSpreadByExitMoneymkrDateBefore", query = "select mySpread from Spread mySpread where mySpread.exitMoneymkrDate < ?1"),
		@NamedQuery(name = "findSpreadByExitMoneymkrPrice", query = "select mySpread from Spread mySpread where mySpread.exitMoneymkrPrice = ?1"),
		@NamedQuery(name = "findSpreadByExitSecurityDate", query = "select mySpread from Spread mySpread where mySpread.exitSecurityDate = ?1"),
		@NamedQuery(name = "findSpreadByExitSecurityDateAfter", query = "select mySpread from Spread mySpread where mySpread.exitSecurityDate > ?1"),
		@NamedQuery(name = "findSpreadByExitSecurityDateBefore", query = "select mySpread from Spread mySpread where mySpread.exitSecurityDate < ?1"),
		@NamedQuery(name = "findSpreadByExitSecurityPrice", query = "select mySpread from Spread mySpread where mySpread.exitSecurityPrice = ?1"),
		@NamedQuery(name = "findSpreadByInsurancePositionId", query = "select mySpread from Spread mySpread where mySpread.insurancePositionId = ?1"),
		@NamedQuery(name = "findSpreadByInsurancePositionIdContaining", query = "select mySpread from Spread mySpread where mySpread.insurancePositionId like ?1"),
		@NamedQuery(name = "findSpreadByMoneymkrPositionId", query = "select mySpread from Spread mySpread where mySpread.moneymkrPositionId = ?1"),
		@NamedQuery(name = "findSpreadByMoneymkrPositionIdContaining", query = "select mySpread from Spread mySpread where mySpread.moneymkrPositionId like ?1"),
		//@NamedQuery(name = "findSpreadByNumSpreads", query = "select mySpread from Spread mySpread where mySpread.numSpreads = ?1"),
		@NamedQuery(name = "findSpreadByOpenOrClosed", query = "select mySpread from Spread mySpread where mySpread.openOrClosed = ?1"),
		@NamedQuery(name = "findSpreadByOpenOrClosedContaining", query = "select mySpread from Spread mySpread where mySpread.openOrClosed like ?1"),
		@NamedQuery(name = "findSpreadByPrimaryKey", query = "select mySpread from Spread mySpread where mySpread.spreadId = ?1"),
		@NamedQuery(name = "findSpreadByProfitLossRealized", query = "select mySpread from Spread mySpread where mySpread.profitLossRealized = ?1"),
		@NamedQuery(name = "findSpreadByProfitLossUnrealized", query = "select mySpread from Spread mySpread where mySpread.profitLossUnrealized = ?1"),
		@NamedQuery(name = "findSpreadBySpreadId", query = "select mySpread from Spread mySpread where mySpread.spreadId = ?1"),
		@NamedQuery(name = "findSpreadByStrikeInsurance", query = "select mySpread from Spread mySpread where mySpread.strikeInsurance = ?1"),
		@NamedQuery(name = "findSpreadByStrikeMoneymkr", query = "select mySpread from Spread mySpread where mySpread.strikeMoneymkr = ?1"),
		//@NamedQuery(name = "findSpreadByTrailingDaysAndHours", query = "select mySpread from Spread mySpread where mySpread.trailingDaysAndHours = ?1"),
		//@NamedQuery(name = "findSpreadByTrailingDaysAndHoursContaining", query = "select mySpread from Spread mySpread where mySpread.trailingDaysAndHours like ?1"),
		@NamedQuery(name = "findSpreadByTrailingStopIsSet", query = "select mySpread from Spread mySpread where mySpread.trailingStopIsSet = ?1"),
		@NamedQuery(name = "findSpreadByTrailingStopIsSetContaining", query = "select mySpread from Spread mySpread where mySpread.trailingStopIsSet like ?1"),
		@NamedQuery(name = "findSpreadByUpdatedBy", query = "select mySpread from Spread mySpread where mySpread.updatedBy = ?1"),
		@NamedQuery(name = "findSpreadByUpdatedByContaining", query = "select mySpread from Spread mySpread where mySpread.updatedBy like ?1"),
		@NamedQuery(name = "findSpreadByUpdatedDate", query = "select mySpread from Spread mySpread where mySpread.updatedDate = ?1"),
		@NamedQuery(name = "findSpreadByReopenDate", query = "select mySpread from Spread mySpread where mySpread.reopenDate = ?1"),
		@NamedQuery(name = "findSpreadByUpdatedDateAfter", query = "select mySpread from Spread mySpread where mySpread.updatedDate > ?1"),
		@NamedQuery(name = "findSpreadByUpdatedDateBefore", query = "select mySpread from Spread mySpread where mySpread.updatedDate < ?1"),
		@NamedQuery(name = "findSpreadByPositionId", query = "select mySpread from Spread mySpread where mySpread.positionId = ?1")
})

//@SqlResultSetMapping(name="implicit",
//          entities=@EntityResult(entityClass=Spread.class))
//@NamedNativeQueries( {
//@NamedNativeQuery(name="findSpreadByPositionId", 
//				query="select * from spread where position_id = ?1",
//				resultSetMapping="implicit")})
		
@Table(schema = "THETA", name = "SPREAD")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "theta11/com/theta/domain", name = "Spread")
public class Spread implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(Spread.class);

	/**
	 */


	
	@Column(name = "SPREAD_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SPREAD_SEQ")
    @SequenceGenerator(name="SPREAD_SEQ", sequenceName = "SPREAD_SEQ")
	@XmlElement
	Integer spreadId;
	/**
	 */

	/**
	 */
	@Column(name = "OPEN_OR_CLOSED", length = 10, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String openOrClosed;
	/**
	 */

	/*
	@Column(name = "NUM_SPREADS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer numSpreads;
	*/
	
	/**
	 */

	@Column(name = "MONEYMKR_POSITION_ID", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String moneymkrPositionId;
	/**
	 */

	@Column(name = "INSURANCE_POSITION_ID", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String insurancePositionId;
	/**
	 */

	@Column(name = "STRIKE_MONEYMKR")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer strikeMoneymkr;
	/**
	 */

	@Column(name = "STRIKE_INSURANCE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer strikeInsurance;
	/**
	 */

	@Column(name = "ENTER_MONEYMKR_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer enterMoneymkrPrice;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENTER_MONEYMKR_DATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar enterMoneymkrDate;
	/**
	 */

	@Column(name = "ENTER_INSURANCE_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer enterInsurancePrice;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENTER_INSURANCE_DATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar enterInsuranceDate;
	/**
	 */

	@Column(name = "ENTER_SECURITY_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer enterSecurityPrice;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENTER_SECURITY_DATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar enterSecurityDate;
	/**
	 */

	@Column(name = "ENTER_COMMISSION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer enterCommission;
	/**
	 */

	@Column(name = "CURRENT_MONEYMKR_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer currentMoneymkrPrice;
	/**
	 */

	@Column(name = "CURRENT_INSURANCE_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer currentInsurancePrice;
	/**
	 */

	@Column(name = "CURRENT_VIX_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer currentVixPrice;
	/**
	 */

	@Column(name = "CURRENT_SECURITY_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer currentSecurityPrice;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CURRENT_DATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar currentDate;
	/**
	 */

	@Column(name = "EXIT_MONEYMKR_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer exitMoneymkrPrice;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXIT_MONEYMKR_DATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar exitMoneymkrDate;
	/**
	 */

	@Column(name = "EXIT_INSURANCE_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer exitInsurancePrice;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXIT_INSURANCE_DATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar exitInsuranceDate;
	/**
	 */

	@Column(name = "EXIT_SECURITY_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer exitSecurityPrice;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXIT_SECURITY_DATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar exitSecurityDate;
	/**
	 */

	@Column(name = "EXIT_COMMISSION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer exitCommission;
	/**
	 */

	@Column(name = "TRAILING_STOP_IS_SET", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String trailingStopIsSet;
	/**
	 */

	@Column(name = "EXIT_ABOVE_SPREAD_VALUE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer exitAboveSpreadValue;
	/**
	 */

	@Column(name = "EXIT_BELOW_SPREAD_VALUE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer exitBelowSpreadValue;
	/**
	 */

	/**
	@Column(name = "TRAILING_DAYS_AND_HOURS", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String trailingDaysAndHours;
	*/
	
	/**
	 */
	@Column(name = "PROFIT_LOSS_UNREALIZED")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer profitLossUnrealized;
	/**
	 */

	@Column(name = "PROFIT_LOSS_REALIZED")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer profitLossRealized;
	/**
	 */

	@Column(name = "CREATED_BY", length = 50, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String createdBy;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", nullable = true)
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
	@Column(name = "UPDATED_DATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar updatedDate;

	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REOPEN_DATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar reopenDate;
	/**
	 */
	@Column(name = "REQUEST_SEQ_NO", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer requestSeqNo;

	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENTER_TRIGGER_DATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar enterTriggerDate;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENTER_CONFIRM_DATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar enterConfirmDate;

	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXIT_TRIGGER_DATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar exitTriggerDate;
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXIT_CONFIRM_DATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar exitConfirmDate;
	
	/**
	 */
	@Column(name = "ENTER_ACTUAL_IB")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer enterActualIb;

	/**
	 */
	@Column(name = "EXIT_ACTUAL_IB")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer exitActualIb;
	
	/**
	 */
	@Column(name = "ENTER_PERM_ID_IB")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String enterPermIdIb;
	
	/**
	 */
	@Column(name = "EXIT_PERM_ID_IB")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String exitPermIdIb;
	
	
	/**
	 */
	@Column(name = "ENTER_REQUEST_SEQ_NO")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer enterRequestSeqNo;

	/**
	 */
	@Column(name = "EXIT_REQUEST_SEQ_NO")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer exitRequestSeqNo;
	/**
	 */
	@Column(name = "HM_INS_REQNO_LIMIT_UP") 
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer hmInsReqnoLimitUp;
	/**
	 */
	@Column(name = "HM_INS_REQNO_STPLIM_DOWN")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer hmInsReqnoStplimDown;
	/**
	 */
	@Column(name = "HM_MM_REQNO")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer hmMmReqno;
	/**
	 */
	@Column(name = "HM_INS_EXIT_ACTUAL_IB")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer hmInsExitActualIb;
	/**
	 */
	@Column(name = "HM_MM_EXIT_ACTUAL_IB")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer HmMmExitActualIb;
	/**
	 */
	@Column(name = "HM_INS_EXIT_PERM_ID_IB")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String hmInsExitPermIdIb;
	/**
	 */
	@Column(name = "HM_MM_EXIT_PERM_ID_IB")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String hmMmExitPermIdIb;
	/**
	 */
	@Column(name = "HM_MM_INSHILO_EXIT_PRICES")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String hmMmInsHiLoExitPrices;
	/**
	 */
	@Column(name = "STK_NUM_SHARES")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stkNumShares;
	/**
	 */
	@Column(name = "ENTER_REASON")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String enterReason;
	/**
	 */
	@Column(name = "EXIT_REASON")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String exitReason;
	/**
	 */
	@Column(name = "ENTER_APPROVAL")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String enterApproval;
	/**
	 */
	@Column(name = "ENTER_LIMIT_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer enterLimitPrice;
	/**
	 */
	@Column(name = "ENTER_STOP_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer enterStopPrice;
	/**
	 */
	@Column(name = "EXIT_APPROVAL")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String exitApproval;
	/**
	 */
	@Column(name = "EXIT_LIMIT_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer exitLimitPrice;
	/**
	 */
	@Column(name = "EXIT_STOP_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer exitStopPrice;
	/**
	 */
	@Column(name = "ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer id;
	/**
	 */
	@Version
	@Column(name = "VERSION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer version;
	
	/**
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EARNINGS_REPORT_NEXT", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar earningsReportNext;
	/**
	 */
	@Column(name = "COVERED_OPTIONS_NUM")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer coveredOptionsNum;
	/**
	 */
	@Column(name = "COVERED_OPTIONS_DESC")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String coveredOptionsDesc;
	
	
	
	/**
	 */
	//@ManyToOne(fetch = FetchType.EAGER)
	//@JoinColumns( { @JoinColumn(name = "POSITION_ID", referencedColumnName = "POSITION_ID", nullable = false) })
	//@XmlTransient
	//Position position;

	//Caused by: org.hibernate.MappingException: 
	//	Repeated column in mapping for entity: com.theta.domain.Spread column: 
	//		POSITION_ID (should be mapped with insert="false" update="false")
	
	@Column(name = "POSITION_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer positionId;

	//Position position;

	/**
	 */
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	/**
	 */
	public Integer getPositionId() {
		return positionId;
	}
	
	
	/**
	 */
	public void setSpreadId(Integer spreadId) {
		this.spreadId = spreadId;
	}

	/**
	 */
	public Integer getSpreadId() {
		return this.spreadId;
	}

	/**
	 */
	public void setOpenOrClosed(String openOrClosed) {
		this.openOrClosed = openOrClosed;
	}

	/**
	 */
	public String getOpenOrClosed() {
		return this.openOrClosed;
	}

	/**
	 */
	//public void setNumSpreads(Integer numSpreads) {
	//	this.numSpreads = numSpreads;
	//}

	/**
	 */
	//public Integer getNumSpreads() {
	//	return this.numSpreads;
	//}

	/**
	 */
	public void setMoneymkrPositionId(String moneymkrPositionId) {
		this.moneymkrPositionId = moneymkrPositionId;
	}

	/**
	 */
	public String getMoneymkrPositionId() {
		return this.moneymkrPositionId;
	}

	/**
	 */
	public void setInsurancePositionId(String insurancePositionId) {
		this.insurancePositionId = insurancePositionId;
	}

	/**
	 */
	public String getInsurancePositionId() {
		return this.insurancePositionId;
	}

	/**
	 */
	public void setStrikeMoneymkr(Integer strikeMoneymkr) {
		this.strikeMoneymkr = strikeMoneymkr;
	}

	/**
	 */
	public Integer getStrikeMoneymkr() {
		return this.strikeMoneymkr;
	}

	/**
	 */
	public void setStrikeInsurance(Integer strikeInsurance) {
		this.strikeInsurance = strikeInsurance;
	}

	/**
	 */
	public Integer getStrikeInsurance() {
		return this.strikeInsurance;
	}

	/**
	 */
	public void setEnterMoneymkrPrice(Integer enterMoneymkrPrice) {
		this.enterMoneymkrPrice = enterMoneymkrPrice;
	}

	/**
	 */
	public Integer getEnterMoneymkrPrice() {
		return this.enterMoneymkrPrice;
	}

	/**
	 */
	public void setEnterMoneymkrDate(Calendar enterMoneymkrDate) {
		this.enterMoneymkrDate = enterMoneymkrDate;
	}

	/**
	 */
	public Calendar getEnterMoneymkrDate() {
		return this.enterMoneymkrDate;
	}

	/**
	 */
	public void setEnterInsurancePrice(Integer enterInsurancePrice) {
		this.enterInsurancePrice = enterInsurancePrice;
	}

	/**
	 */
	public Integer getEnterInsurancePrice() {
		return this.enterInsurancePrice;
	}

	/**
	 */
	public void setEnterInsuranceDate(Calendar enterInsuranceDate) {
		this.enterInsuranceDate = enterInsuranceDate;
	}

	/**
	 */
	public Calendar getEnterInsuranceDate() {
		return this.enterInsuranceDate;
	}

	/**
	 */
	public void setEnterSecurityPrice(Integer enterSecurityPrice) {
		this.enterSecurityPrice = enterSecurityPrice;
	}

	/**
	 */
	public Integer getEnterSecurityPrice() {
		return this.enterSecurityPrice;
	}

	/**
	 */
	public void setEnterSecurityDate(Calendar enterSecurityDate) {
		this.enterSecurityDate = enterSecurityDate;
	}

	/**
	 */
	public Calendar getEnterSecurityDate() {
		return this.enterSecurityDate;
	}

	/**
	 */
	public void setEnterCommission(Integer enterCommission) {
		this.enterCommission = enterCommission;
	}

	/**
	 */
	public Integer getEnterCommission() {
		return this.enterCommission;
	}

	/**
	 */
	public void setCurrentMoneymkrPrice(Integer currentMoneymkrPrice) {
		this.currentMoneymkrPrice = currentMoneymkrPrice;
	}

	/**
	 */
	public Integer getCurrentMoneymkrPrice() {
		return this.currentMoneymkrPrice;
	}

	/**
	 */
	public void setCurrentInsurancePrice(Integer currentInsurancePrice) {
		this.currentInsurancePrice = currentInsurancePrice;
	}

	/**
	 */
	public Integer getCurrentInsurancePrice() {
		return this.currentInsurancePrice;
	}

	/**
	 */
	public void setCurrentVixPrice(Integer currentVixPrice) {
		this.currentVixPrice = currentVixPrice;
	}

	/**
	 */
	public Integer getCurrentVixPrice() {
		return this.currentVixPrice;
	}

	/**
	 */
	public void setCurrentSecurityPrice(Integer currentSecurityPrice) {
		this.currentSecurityPrice = currentSecurityPrice;
	}

	/**
	 */
	public Integer getCurrentSecurityPrice() {
		return this.currentSecurityPrice;
	}

	/**
	 */
	public void setCurrentDate(Calendar currentDate) {
		this.currentDate = currentDate;
	}

	/**
	 */
	public Calendar getCurrentDate() {
		return this.currentDate;
	}

	/**
	 */
	public void setExitMoneymkrPrice(Integer exitMoneymkrPrice) {
		this.exitMoneymkrPrice = exitMoneymkrPrice;
	}

	/**
	 */
	public Integer getExitMoneymkrPrice() {
		return this.exitMoneymkrPrice;
	}

	/**
	 */
	public void setExitMoneymkrDate(Calendar exitMoneymkrDate) {
		this.exitMoneymkrDate = exitMoneymkrDate;
	}

	/**
	 */
	public Calendar getExitMoneymkrDate() {
		return this.exitMoneymkrDate;
	}

	/**
	 */
	public void setExitInsurancePrice(Integer exitInsurancePrice) {
		this.exitInsurancePrice = exitInsurancePrice;
	}

	/**
	 */
	public Integer getExitInsurancePrice() {
		return this.exitInsurancePrice;
	}

	/**
	 */
	public void setExitInsuranceDate(Calendar exitInsuranceDate) {
		this.exitInsuranceDate = exitInsuranceDate;
	}

	/**
	 */
	public Calendar getExitInsuranceDate() {
		return this.exitInsuranceDate;
	}

	/**
	 */
	public void setExitSecurityPrice(Integer exitSecurityPrice) {
		this.exitSecurityPrice = exitSecurityPrice;
	}

	/**
	 */
	public Integer getExitSecurityPrice() {
		return this.exitSecurityPrice;
	}

	/**
	 */
	public void setExitSecurityDate(Calendar exitSecurityDate) {
		this.exitSecurityDate = exitSecurityDate;
	}

	/**
	 */
	public Calendar getExitSecurityDate() {
		return this.exitSecurityDate;
	}

	/**
	 */
	public void setExitCommission(Integer exitCommission) {
		this.exitCommission = exitCommission;
	}

	/**
	 */
	public Integer getExitCommission() {
		return this.exitCommission;
	}

	/**
	 */
	public void setTrailingStopIsSet(String trailingStopIsSet) {
		this.trailingStopIsSet = trailingStopIsSet;
	}

	/**
	 */
	public String getTrailingStopIsSet() {
		return this.trailingStopIsSet;
	}

	/**
	 */
	public void setExitBelowSpreadValue(Integer exitBelowSpreadValue) {
		this.exitBelowSpreadValue = exitBelowSpreadValue;
	}

	/**
	 */
	public Integer getExitBelowSpreadValue() {
		return this.exitBelowSpreadValue;
	}

	/**
	 */
	public void setExitAboveSpreadValue(Integer exitAboveSpreadValue) {
		this.exitAboveSpreadValue = exitAboveSpreadValue;
	}

	/**
	 */
	public Integer getExitAboveSpreadValue() {
		return this.exitAboveSpreadValue;
	}

	/**
	 * THE FOLLOWING ITEMS ARE TO BE REMOVED:
	 */
	/**
	 */
	//public void setTrailingDaysAndHours(String trailingDaysAndHours) {
	//	this.trailingDaysAndHours = trailingDaysAndHours;
	//}

	/**
	 */
	//public String getTrailingDaysAndHours() {
	//	return this.trailingDaysAndHours;
	//}

	/**
	 */
	public void setProfitLossUnrealized(Integer profitLossUnrealized) {
		this.profitLossUnrealized = profitLossUnrealized;
	}

	/**
	 */
	public Integer getProfitLossUnrealized() {
		return this.profitLossUnrealized;
	}

	/**
	 */
	public void setProfitLossRealized(Integer profitLossRealized) {
		this.profitLossRealized = profitLossRealized;
	}

	/**
	 */
	public Integer getProfitLossRealized() {
		return this.profitLossRealized;
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
		this.updatedBy = ThetaUtil.leftmostChars(updatedBy, 145);
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
	public void setReopenDate(Calendar reopenDate) {
		this.reopenDate = reopenDate;
	}

	/**
	 */
	public Calendar getReopenDate() {
		return this.reopenDate;
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
	 * @return the triggerDate
	 */
	public Calendar getEnterTriggerDate() {
		return enterTriggerDate;
	}

	/**
	 * @param triggerDate the triggerDate to set
	 */
	public void setEnterTriggerDate(Calendar enterTriggerDate) {
		this.enterTriggerDate = enterTriggerDate;
	}

	/**
	 * @return the confirmDate
	 */
	public Calendar getEnterConfirmDate() {
		return enterConfirmDate;
	}

	/**
	 * @param confirmDate the confirmDate to set
	 */
	public void setEnterConfirmDate(Calendar enterConfirmDate) {
		this.enterConfirmDate = enterConfirmDate;
	}
	/**
	 * @return the triggerDate
	 */
	public Calendar getExitTriggerDate() {
		return exitTriggerDate;
	}

	/**
	 * @param triggerDate the triggerDate to set
	 */
	public void setExitTriggerDate(Calendar exitTriggerDate) {
		this.exitTriggerDate = exitTriggerDate;
	}

	/**
	 * @return the confirmDate
	 */
	public Calendar getExitConfirmDate() {
		return exitConfirmDate;
	}

	/**
	 * @param confirmDate the confirmDate to set
	 */
	public void setExitConfirmDate(Calendar exitConfirmDate) {
		this.exitConfirmDate = exitConfirmDate;
	}

	/**
	 * @return the enterActualIb
	 */
	public Integer getEnterActualIb() {
		return enterActualIb;
	}

	/**
	 * @param enterActualIb the enterActualIb to set
	 */
	public void setEnterActualIb(Integer enterActualIb) {
		this.enterActualIb = enterActualIb;
	}

	/**
	 * @return the exitActualIb
	 */
	public Integer getExitActualIb() {
		return exitActualIb;
	}

	/**
	 * @param exitActualIb the exitActualIb to set
	 */
	public void setExitActualIb(Integer exitActualIb) {
		this.exitActualIb = exitActualIb;
	}

	/**
	 * @return the permIdIb
	 */
	public String getEnterPermIdIb() {
		return enterPermIdIb;
	}

	/**
	 * @param permIdIb the permIdIb to set
	 */
	public void setEnterPermIdIb(String enterPermIdIb) {
		this.enterPermIdIb = enterPermIdIb;
	}

	/**
	 * @return the permIdIb
	 */
	public String getExitPermIdIb() {
		return exitPermIdIb;
	}

	/**
	 * @param permIdIb the permIdIb to set
	 */
	public void setExitPermIdIb(String exitPermIdIb) {
		this.exitPermIdIb = exitPermIdIb;
	}

	public Integer getEnterRequestSeqNo() {
		return enterRequestSeqNo;
	}

	public void setEnterRequestSeqNo(Integer enterRequestSeqNo) {
		this.enterRequestSeqNo = enterRequestSeqNo;
	}

	public Integer getExitRequestSeqNo() {
		return exitRequestSeqNo;
	}

	public void setExitRequestSeqNo(Integer exitRequestSeqNo) {
		this.exitRequestSeqNo = exitRequestSeqNo;
	}
	
	public Integer getHmInsReqnoLimitUp() {
		log.info("*** DEPRECATED: Spread.getHmInsReqnoLimitUp ");
		return hmInsReqnoLimitUp;
	}

	public void setHmInsReqnoLimitUp(Integer hmInsReqnoLimitUp) {
		log.info("*** DEPRECATED: Spread.setHmInsReqnoLimitUp ");
		this.hmInsReqnoLimitUp = hmInsReqnoLimitUp;
	}

	public Integer getHmInsReqnoStplimDown() {
		log.info("*** DEPRECATED: Spread.getHmInsReqnoStplimDown ");
		return hmInsReqnoStplimDown;
	}

	public void setHmInsReqnoStplimDown(Integer hmInsReqnoStplimDown) {
		log.info("*** DEPRECATED: Spread.setHmInsReqnoStplimDown ");
		this.hmInsReqnoStplimDown = hmInsReqnoStplimDown;
	}

	public Integer getHmMmReqno() {
		log.info("*** DEPRECATED: Spread.getHmMmReqno ");
		return hmMmReqno;
	}

	public void setHmMmReqno(Integer hmMmReqno) {
		log.info("*** DEPRECATED: Spread.setHmMmReqno ");
		this.hmMmReqno = hmMmReqno;
	}

	public Integer getHmInsExitActualIb() {
		log.info("*** DEPRECATED: Spread.getHmInsExitActualIb ");
		return hmInsExitActualIb;
	}

	public void setHmInsExitActualIb(Integer hmInsExitActualIb) {
		log.info("*** DEPRECATED: Spread.setHmInsExitActualIb ");
		this.hmInsExitActualIb = hmInsExitActualIb;
	}

	public Integer getHmMmExitActualIb() {
		log.info("*** DEPRECATED: Spread.getHmMmExitActualIb ");
		return HmMmExitActualIb;
	}

	public void setHmMmExitActualIb(Integer hmMmExitActualIb) {
		log.info("*** DEPRECATED: Spread.setHmMmExitActualIb ");
		HmMmExitActualIb = hmMmExitActualIb;
	}

	public String getHmInsExitPermIdIb() {
		log.info("*** DEPRECATED: Spread.getHmInsExitPermIdIb ");
		return hmInsExitPermIdIb;
	}

	public void setHmInsExitPermIdIb(String hmInsExitPermIdIb) {
		log.info("*** DEPRECATED: Spread.setHmInsExitPermIdIb ");
		this.hmInsExitPermIdIb = hmInsExitPermIdIb;
	}

	public String getHmMmExitPermIdIb() {
		log.info("*** DEPRECATED: Spread.getHmMmExitPermIdIb ");
		return hmMmExitPermIdIb;
	}

	public void setHmMmExitPermIdIb(String hmMmExitPermIdIb) {
		log.info("*** DEPRECATED: Spread.setHmMmExitPermIdIb ");
		this.hmMmExitPermIdIb = hmMmExitPermIdIb;
	}
	
	public Integer getStkNumShares() {
		return stkNumShares;
	}

	public void setStkNumShares(Integer stkNumShares) {
		this.stkNumShares = stkNumShares;
	}

	public String getEnterApproval() {
		return enterApproval;
	}

	public void setEnterApproval(String enterApproval) {
		this.enterApproval = enterApproval;
	}

	public String getExitApproval() {
		return exitApproval;
	}

	public void setExitApproval(String exitApproval) {
		this.exitApproval = exitApproval;
	}

	public Integer getEnterLimitPrice() {
		return enterLimitPrice;
	}

	public void setEnterLimitPrice(Integer enterLimitPrice) {
		this.enterLimitPrice = enterLimitPrice;
	}

	public Integer getExitLimitPrice() {
		return exitLimitPrice;
	}

	public void setExitLimitPrice(Integer exitLimitPrice) {
		this.exitLimitPrice = exitLimitPrice;
	}

	public Integer getEnterStopPrice() {
		return enterStopPrice;
	}

	public void setEnterStopPrice(Integer enterStopPrice) {
		this.enterStopPrice = enterStopPrice;
	}

	public Integer getExitStopPrice() {
		return exitStopPrice;
	}

	public void setExitStopPrice(Integer exitStopPrice) {
		this.exitStopPrice = exitStopPrice;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		log.info("in setversion. version: " + version + ". spread: " + this.createdBy);
		this.version = version;
	}
	public Integer getVersion() {
		return version;
	}

	public boolean isWithinLimits(UpOrDownCode upOrDownCode, EnterOrExitCode enterOrExitCode, Integer underlyingSymbolPrice) throws ThetaExceptionExc {
		
		if (underlyingSymbolPrice == null) {
			throw new ThetaExceptionExc("UnderlyingSymbolPrice must not be null.");
		}
			
		Integer limitPrice = 0;
		Integer stopPrice = 0;
		if (enterOrExitCode.equals(EnterOrExitCode.ENTER)) {
			limitPrice = this.enterLimitPrice;
			stopPrice = this.enterStopPrice;
			if (limitPrice == null) {this.enterLimitPrice = 0; limitPrice = 0;}
			if (stopPrice == null) {this.enterStopPrice = 0; stopPrice = 0;}
		}
		if (enterOrExitCode.equals(EnterOrExitCode.EXIT)) {
			limitPrice = this.exitLimitPrice;
			stopPrice = this.exitStopPrice;
			if (limitPrice == null) {this.exitLimitPrice = 0; limitPrice = 0;}
			if (stopPrice == null) {this.exitStopPrice = 0; stopPrice = 0;}
		}

		if (upOrDownCode.equals(UpOrDownCode.UP)) {
			
			if ( ( stopPrice > 0 ) && (limitPrice > 0 ) && (stopPrice.compareTo(limitPrice) < 0) ) {
				throw new ThetaExceptionExc("For UP - stopPrice must be more than limitPrice. Stop, Limit:  " + stopPrice + ", " + limitPrice);
			}
			
			return (  
					   ((stopPrice == 0) || underlyingSymbolPrice.compareTo(stopPrice) < 0)
			               && 
			           ((limitPrice == 0) || underlyingSymbolPrice.compareTo(limitPrice) > 0)
			       );
		} else if (upOrDownCode.equals(UpOrDownCode.DOWN)) {

			if ( ( stopPrice > 0 ) && (limitPrice > 0 ) && (stopPrice.compareTo(limitPrice) > 0) ) {
				throw new ThetaExceptionExc("For DOWN - stopPrice must be less than limitPrice. Stop, Limit:  " + stopPrice + ", " + limitPrice);
			}

			return (  
					  ((stopPrice == 0) || underlyingSymbolPrice.compareTo(stopPrice) > 0)
  					  &&
					  ((limitPrice == 0) || underlyingSymbolPrice.compareTo(limitPrice) < 0)
			       );
		} else {
			throw new ThetaExceptionExc("upOrDownCode must be UP or DOWN rather than: " + upOrDownCode.getValue() );
		}

	}

	
	public String getHmMmInsHiLoExitPrices() {
		log.info("*** DEPRECATED: Spread.getHmMmInsHiLoExitPrices ");
		return hmMmInsHiLoExitPrices;
	}

	public void setHmMmInsHiLoExitPrices(String hmMmInsHiLoExitPrices) {
		log.info("*** DEPRECATED: Spread.setHmMmInsHiLoExitPrices ");
		String ret = hmMmInsHiLoExitPrices;
		if (ret.length() > 60) ret= ret.substring(0,60); 
		
		this.hmMmInsHiLoExitPrices = ret;
	}
	
	public String getEnterReason() {
		if (enterReason != null ) {
			return enterReason.trim();
		} else {
			return "";
			
		}
	}
	public void setEnterReason(String enterReason) {
		if (enterReason.length() > 400){
			enterReason = enterReason.substring(0,400);
		}
		this.enterReason = enterReason;
	}
	public String getExitReason() {
		if (exitReason != null) {
			return exitReason.trim();
		} else {
			return "";
		}
	}
	public void setExitReason(String exitReason) {
		if (exitReason.length() > 400){
			exitReason = exitReason.substring(0,400);
		}
		this.exitReason = exitReason;
	}

	public Calendar getEarningsReportNext() {
		return earningsReportNext;
	}

	public void setEarningsReportNext(Calendar earningsReportNext) {
		this.earningsReportNext = earningsReportNext;
	}

	public Integer getCoveredOptionsNum() {
		return coveredOptionsNum;
	}

	public void setCoveredOptionsNum(Integer coveredOptionsNum) {
		this.coveredOptionsNum = coveredOptionsNum;
	}

	public String getCoveredOptionsDesc() {
		return coveredOptionsDesc;
	}

	public void setCoveredOptionsDesc(String coveredOptionsDesc) {
		this.coveredOptionsDesc = coveredOptionsDesc;
	}

	/**
	 * Must be of the form:
	 * P-MM-YYYY-99999
	 * @param coveredOptionsDesc
	 * @return
	 */
	public CoveredOption parseCoveredOptionsDesc(){
				
		try {
			Calendar now = Calendar.getInstance();
			return (ThetaUtil.parseCoveredOptionsDesc(this.coveredOptionsDesc, this.currentSecurityPrice, now));
		} catch (Exception ex) {
			String actual = ex.getMessage();
			String[] parts = coveredOptionsDesc.split(":");
			this.coveredOptionsDesc = ThetaUtil.leftmostChars(parts[0] + ":" + actual, 40);
			return null;
		} 
		
	}

	
	
	/**
	 */
	public Spread() {
	}

	public void init(Strategy strategy) {
		this.setCreatedBy(strategy.getStrategyName());
		this.setCreatedDate(Calendar.getInstance());
		this.setUpdatedBy(strategy.getStrategyName());
		this.setUpdatedDate(Calendar.getInstance());

		this.setStrikeMoneymkr(ThetaConstants.ZERO_INT);
		this.setStrikeInsurance(ThetaConstants.ZERO_INT);
		this.setEnterMoneymkrPrice(ThetaConstants.ZERO_INT);
		this.setEnterInsurancePrice(ThetaConstants.ZERO_INT);
		this.setEnterCommission(ThetaConstants.ZERO_INT);
		this.setExitCommission(ThetaConstants.ZERO_INT);
		this.setExitSecurityPrice(ThetaConstants.ZERO_INT);
		
		this.setCurrentMoneymkrPrice(ThetaConstants.ZERO_INT);
		this.setCurrentInsurancePrice(ThetaConstants.ZERO_INT);
		this.setProfitLossRealized(ThetaConstants.ZERO_INT);
		this.setProfitLossUnrealized(ThetaConstants.ZERO_INT);

		this.setExitMoneymkrPrice(ThetaConstants.ZERO_INT);
		this.setExitInsurancePrice(ThetaConstants.ZERO_INT);
		this.setEnterActualIb(ThetaConstants.ZERO_INT);
		this.setExitActualIb(ThetaConstants.ZERO_INT);
		this.setEnterRequestSeqNo(ThetaConstants.ZERO_INT);
		this.setExitRequestSeqNo(ThetaConstants.ZERO_INT);
		this.setEnterPermIdIb(ThetaConstants.ZERO_STRING);
		this.setExitPermIdIb(ThetaConstants.ZERO_STRING);
				
		this.setSpreadId(ThetaConstants.INIT_ID);
		this.setOpenOrClosed(OpenOrClosedCode.ADVISEOPEN.toString());
		this.setTrailingStopIsSet(strategy.getSetWinTrailingStop());
		
		this.setEnterApproval(ApprovalCode.HOLD.toString());
		this.setEnterLimitPrice(0);
		this.setEnterStopPrice(0);
		this.setExitApproval(ApprovalCode.HOLD.toString());
		this.setExitLimitPrice(0);
		this.setExitStopPrice(0);
		this.setVersion(100);
		
		this.setEarningsReportNext(strategy.getEarningsReportNext());
		this.setCoveredOptionsNum(0);
		this.setCoveredOptionsDesc("");
	}
	
	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	*/
	public void copy(Spread that) {
		setSpreadId(that.getSpreadId());
		//setPositionId(that.getPositionId());
		setOpenOrClosed(that.getOpenOrClosed());
		setMoneymkrPositionId(that.getMoneymkrPositionId());
		setInsurancePositionId(that.getInsurancePositionId());
		setStrikeMoneymkr(that.getStrikeMoneymkr());
		setStrikeInsurance(that.getStrikeInsurance());
		setEnterMoneymkrPrice(that.getEnterMoneymkrPrice());
		setEnterMoneymkrDate(that.getEnterMoneymkrDate());
		setEnterInsurancePrice(that.getEnterInsurancePrice());
		setEnterInsuranceDate(that.getEnterInsuranceDate());
		setEnterSecurityPrice(that.getEnterSecurityPrice());
		setEnterSecurityDate(that.getEnterSecurityDate());
		setEnterTriggerDate(that.getEnterTriggerDate());
		setEnterConfirmDate(that.getEnterConfirmDate());
		setEnterCommission(that.getEnterCommission());
		setCurrentMoneymkrPrice(that.getCurrentMoneymkrPrice());
		setCurrentInsurancePrice(that.getCurrentInsurancePrice());
		setCurrentVixPrice(that.getCurrentVixPrice());
		setCurrentSecurityPrice(that.getCurrentSecurityPrice());
		setCurrentDate(that.getCurrentDate());
		setExitMoneymkrPrice(that.getExitMoneymkrPrice());
		setExitMoneymkrDate(that.getExitMoneymkrDate());
		setExitInsurancePrice(that.getExitInsurancePrice());
		setExitInsuranceDate(that.getExitInsuranceDate());
		setExitSecurityPrice(that.getExitSecurityPrice());
		setExitSecurityDate(that.getExitSecurityDate());
		setExitTriggerDate(that.getExitTriggerDate());
		setExitConfirmDate(that.getExitConfirmDate());
		setExitCommission(that.getExitCommission());
		setTrailingStopIsSet(that.getTrailingStopIsSet());
		setExitAboveSpreadValue(that.getExitAboveSpreadValue());
		setExitBelowSpreadValue(that.getExitBelowSpreadValue());
		setProfitLossUnrealized(that.getProfitLossUnrealized());
		setProfitLossRealized(that.getProfitLossRealized());
		setRequestSeqNo(that.getRequestSeqNo());
		setReopenDate(that.getReopenDate());
		setCreatedBy(that.getCreatedBy());
		setCreatedDate(that.getCreatedDate());
		setUpdatedBy(that.getUpdatedBy());
		setUpdatedDate(that.getUpdatedDate());
		setEnterActualIb(that.getEnterActualIb());
		setExitActualIb(that.getExitActualIb());
		setExitRequestSeqNo(that.getExitRequestSeqNo());
		setEnterRequestSeqNo(that.getEnterRequestSeqNo());
		setEnterPermIdIb(that.getEnterPermIdIb());
		setExitPermIdIb(that.getExitPermIdIb());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("\n spreadId=[").append(spreadId).append("] \n");
		buffer.append("openOrClosed=[").append(openOrClosed).append("] \n");
		//buffer.append("numSpreads=[").append(numSpreads).append("] \n");
		buffer.append("moneymkrPositionId=[").append(moneymkrPositionId).append("] \n");
		buffer.append("insurancePositionId=[").append(insurancePositionId).append("] \n");
		buffer.append("strikeMoneymkr=[").append(strikeMoneymkr).append("] \n");
		buffer.append("strikeInsurance=[").append(strikeInsurance).append("] \n");
		buffer.append("enterMoneymkrPrice=[").append(enterMoneymkrPrice).append("] \n");
		buffer.append("enterMoneymkrDate=[").append(ThetaUtil.formatCalToSecond(enterMoneymkrDate)).append("] \n");
		buffer.append("enterInsurancePrice=[").append(enterInsurancePrice).append("] \n");
		buffer.append("enterInsuranceDate=[").append(ThetaUtil.formatCalToSecond(enterInsuranceDate)).append("] \n");
		buffer.append("enterSecurityPrice=[").append(enterSecurityPrice).append("] \n");
		buffer.append("enterSecurityDate=[").append(ThetaUtil.formatCalToSecond(enterSecurityDate)).append("] \n");
		buffer.append("enterCommission=[").append(enterCommission).append("] \n");
		buffer.append("currentMoneymkrPrice=[").append(currentMoneymkrPrice).append("] \n");
		buffer.append("currentInsurancePrice=[").append(currentInsurancePrice).append("] \n");
		buffer.append("currentVixPrice=[").append(currentVixPrice).append("] \n");
		buffer.append("currentSecurityPrice=[").append(currentSecurityPrice).append("] \n");
		buffer.append("currentDate=[").append(ThetaUtil.formatCalToSecond(currentDate)).append("] \n");
		buffer.append("exitMoneymkrPrice=[").append(exitMoneymkrPrice).append("] \n");
		buffer.append("exitMoneymkrDate=[").append(ThetaUtil.formatCalToSecond(exitMoneymkrDate)).append("] \n");
		buffer.append("exitInsurancePrice=[").append(exitInsurancePrice).append("] \n");
		buffer.append("exitInsuranceDate=[").append(ThetaUtil.formatCalToSecond(exitInsuranceDate)).append("] \n");
		buffer.append("exitSecurityPrice=[").append(exitSecurityPrice).append("] \n");
		buffer.append("exitSecurityDate=[").append(ThetaUtil.formatCalToSecond(exitSecurityDate)).append("] \n");
		buffer.append("exitCommission=[").append(exitCommission).append("] \n");
		buffer.append("trailingStopIsSet=[").append(trailingStopIsSet).append("] \n");
		buffer.append("profitLossUnrealized=[").append(profitLossUnrealized).append("] \n");
		buffer.append("profitLossRealized=[").append(profitLossRealized).append("] \n");
		buffer.append("createdBy=[").append(createdBy).append("] \n");
		buffer.append("createdDate=[").append(ThetaUtil.formatCalToSecond(createdDate)).append("] \n");
		buffer.append("updatedBy=[").append(updatedBy).append("] \n");
		buffer.append("updatedDate=[").append(ThetaUtil.formatCalToSecond(updatedDate)).append("] \n");
		buffer.append("reopenDate=[").append(ThetaUtil.formatCalToSecond(reopenDate)).append("] \n");
		buffer.append("requestSeqNo=[").append(requestSeqNo).append("] \n");
		buffer.append("enterTriggerDate=[").append(ThetaUtil.formatCalToSecond(enterTriggerDate)).append("] \n");
		buffer.append("enterCconfirmDate=[").append(ThetaUtil.formatCalToSecond(enterConfirmDate)).append("] \n");
		buffer.append("exitTriggerDate=[").append(ThetaUtil.formatCalToSecond(exitTriggerDate)).append("] \n");
		buffer.append("exitConfirmDate=[").append(ThetaUtil.formatCalToSecond(exitConfirmDate)).append("] \n");
		buffer.append("enterApproval=");
		buffer.append(enterApproval);
		buffer.append(", enterLimitPrice=");
		buffer.append(enterLimitPrice);
		buffer.append(", enterStopPrice=");
		buffer.append(enterStopPrice);
		buffer.append(", exitApproval=");
		buffer.append(exitApproval);
		buffer.append(", exitLimitPrice=");
		buffer.append(exitLimitPrice);
		buffer.append(", exitStopPrice=");
		buffer.append(exitStopPrice);
		buffer.append(", id=");
		buffer.append(id);
		buffer.append(", version=");
		buffer.append(version);
		buffer.append(", positionId=");
		buffer.append(positionId);
		buffer.append("]");

		
		return buffer.toString();
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toStringShort() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("\n openOrClosed=[").append(openOrClosed).append("] \n");
		//buffer.append("right=[").append(this.getPosition().getOptRight()).append("] \n");
		buffer.append("moneymkrPositionId=[").append(moneymkrPositionId).append("] \n");
		buffer.append("strikeMoneymkr=[").append(strikeMoneymkr).append("] \n");
		buffer.append("enterMoneymkrPrice=[").append(enterMoneymkrPrice).append("] \n");

		buffer.append("insurancePositionId=[").append(insurancePositionId).append("] \n");
		buffer.append("strikeInsurance=[").append(strikeInsurance).append("] \n");
		buffer.append("enterInsurancePrice=[").append(enterInsurancePrice).append("] \n");

		buffer.append("updatedDate=[").append(ThetaUtil.formatCalToSecond(updatedDate)).append("] \n");
		buffer.append("RequestSeqNo=[").append(this.requestSeqNo).append("] \n");
		//buffer.append("Expiry Month:Year=[").append(this.position.expiryMonth).append(":").append(this.position.expiryYear).append("] \n"); 

		return buffer.toString();
	}

	public String toStringTest() {
		StringBuilder builder = new StringBuilder();
		builder.append("Spread [enterApproval=");
		builder.append(enterApproval);
		builder.append(", openOrClosed=");
		builder.append(openOrClosed);
		builder.append(", enterLimitPrice=");
		builder.append(enterLimitPrice);
		builder.append(", enterStopPrice=");
		builder.append(enterStopPrice);
		builder.append(", exitApproval=");
		builder.append(exitApproval);
		builder.append(", exitLimitPrice=");
		builder.append(exitLimitPrice);
		builder.append(", exitStopPrice=");
		builder.append(exitStopPrice);
		builder.append(", id=");
		builder.append(id);
		builder.append(", version=");
		builder.append(version);
		builder.append(", positionId=");
		builder.append(positionId);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Returns a textual representation of a bean.
	 *  NO SPACE
	 */
	public String toStringNS() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("\n openOrClosed=[").append(openOrClosed).append("] ");
		//buffer.append("right=[").append(this.getPosition().getOptRight()).append("] ");
		buffer.append("moneymkrPositionId=[").append(moneymkrPositionId).append("] ");
		buffer.append("strikeMoneymkr=[").append(strikeMoneymkr).append("] ");
		buffer.append("enterMoneymkrPrice=[").append(enterMoneymkrPrice).append("] ");

		buffer.append("insurancePositionId=[").append(insurancePositionId).append("] ");
		buffer.append("strikeInsurance=[").append(strikeInsurance).append("] ");
		buffer.append("enterInsurancePrice=[").append(enterInsurancePrice).append("] ");

		buffer.append("updatedDate=[").append(ThetaUtil.formatCalToSecond(updatedDate)).append("] ");
		buffer.append("RequestSeqNo=[").append(this.requestSeqNo).append("] ");
		//buffer.append("Expiry Month:Year=[").append(this.position.expiryMonth).append(":").append(this.position.expiryYear).append("] "); 

		return buffer.toString();
	}
	
	
	/**
	 * 
	 * @param spread
	 * @return
	 */
	public boolean isPending(){

		if (openOrClosed.equals(OpenOrClosedCode.PENDOPEN.toString()) ||
				openOrClosed.equals(OpenOrClosedCode.PENDOPNCNF.toString()) ||
				openOrClosed.equals(OpenOrClosedCode.PENDCLOSE.toString()) ||
				openOrClosed.equals(OpenOrClosedCode.PENDCLSCNF.toString())){
			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * 
	 * @param spread
	 * @return
	 */
	public boolean isHailMary(){

		if (openOrClosed.equals(OpenOrClosedCode.HAILMARY.toString())){
			return true;
		} else {
			return false;
		}
		
	}

	
	/**
	 * 
	 * @return
	 */
	public boolean isOpening() {
		if (openOrClosed.equals(OpenOrClosedCode.PENDOPEN.toString()) ||
				openOrClosed.equals(OpenOrClosedCode.PENDOPNCNF.toString()) ||
				openOrClosed.equals(OpenOrClosedCode.OPEN.toString())){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isClosing() {
		if (openOrClosed.equals(OpenOrClosedCode.PENDCLOSE.toString()) ||
				openOrClosed.equals(OpenOrClosedCode.PENDCLSCNF.toString()) ||
				openOrClosed.equals(OpenOrClosedCode.CLOSED.toString())){
			return true;
		} else {
			return false;
		}
	}
	

	
	/**
	 * 
	 * @return
	 */
	public boolean isPendOpening() {
		if (openOrClosed.equals(OpenOrClosedCode.PENDOPEN.toString()) ||
				openOrClosed.equals(OpenOrClosedCode.PENDOPNCNF.toString())){
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	/**
	 * 
	 * @return
	 */
	public boolean isPendClosing() {
		if (openOrClosed.equals(OpenOrClosedCode.PENDCLOSE.toString()) ||
				openOrClosed.equals(OpenOrClosedCode.PENDCLSCNF.toString())){
			return true;
		} else {
			return false;
		}
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((spreadId == null) ? 0 : spreadId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Spread))
			return false;
		Spread equalCheck = (Spread) obj;
		if ((spreadId == null && equalCheck.spreadId != null) || (spreadId != null && equalCheck.spreadId == null))
			return false;
		if (spreadId != null && !spreadId.equals(equalCheck.spreadId))
			return false;
		return true;
	}




}

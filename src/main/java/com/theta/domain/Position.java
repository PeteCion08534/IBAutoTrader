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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

import com.theta.dao.SecurityPriceDAO;
import com.theta.enums.LoMidHiCode;
import com.theta.enums.LongOrShortCode;
import com.theta.enums.MinOrMaxCode;
import com.theta.enums.UpOrDownCode;
import com.theta.process.MovingAverages;
import com.theta.process.ThetaConstants;
import com.theta.process.ThetaExceptionExc;
import com.theta.process.ThetaMutex;

/**
 */

@Entity
@NamedQueries( {
		@NamedQuery(name = "findAllPositions", query = "select myPosition from Position myPosition"),
		@NamedQuery(name = "findPositionByCreatedBy", query = "select myPosition from Position myPosition where myPosition.createdBy = ?1"),
		@NamedQuery(name = "findPositionByCreatedByContaining", query = "select myPosition from Position myPosition where myPosition.createdBy like ?1"),
		@NamedQuery(name = "findPositionByCreatedDate", query = "select myPosition from Position myPosition where myPosition.createdDate = ?1"),
		@NamedQuery(name = "findPositionByCreatedDateAfter", query = "select myPosition from Position myPosition where myPosition.createdDate > ?1"),
		@NamedQuery(name = "findPositionByCreatedDateBefore", query = "select myPosition from Position myPosition where myPosition.createdDate < ?1"),
		@NamedQuery(name = "findPositionByExpiryDay", query = "select myPosition from Position myPosition where myPosition.expiryDay = ?1"),
		@NamedQuery(name = "findPositionByExpiryMonth", query = "select myPosition from Position myPosition where myPosition.expiryMonth = ?1"),
		@NamedQuery(name = "findPositionByExpiryTimeframe", query = "select myPosition from Position myPosition where myPosition.expiryTimeframe = ?1"),
		@NamedQuery(name = "findPositionByExpiryTimeframeContaining", query = "select myPosition from Position myPosition where myPosition.expiryTimeframe like ?1"),
		@NamedQuery(name = "findPositionByExpiryYear", query = "select myPosition from Position myPosition where myPosition.expiryYear = ?1"),
		@NamedQuery(name = "findPositionByGoalNumSpreads", query = "select myPosition from Position myPosition where myPosition.goalNumSpreads = ?1"),
		@NamedQuery(name = "findPositionByLastExitSecurityPrice", query = "select myPosition from Position myPosition where myPosition.lastExitSecurityPrice = ?1"),
		@NamedQuery(name = "findPositionByNumLosses", query = "select myPosition from Position myPosition where myPosition.numLosses = ?1"),
		@NamedQuery(name = "findPositionByNumOpenSpreads", query = "select myPosition from Position myPosition where myPosition.numOpenSpreads = ?1"),
		@NamedQuery(name = "findPositionByNumWins", query = "select myPosition from Position myPosition where myPosition.numWins = ?1"),
		@NamedQuery(name = "findPositionByOptRight", query = "select myPosition from Position myPosition where myPosition.optRight = ?1"),
		@NamedQuery(name = "findPositionByOptRightContaining", query = "select myPosition from Position myPosition where myPosition.optRight like ?1"),
		@NamedQuery(name = "findPositionByPositionId", query = "select myPosition from Position myPosition where myPosition.positionId = ?1"),
		@NamedQuery(name = "findPositionByPrimaryKey", query = "select myPosition from Position myPosition where myPosition.positionId = ?1"),
		@NamedQuery(name = "findPositionByProfitLossRealized", query = "select myPosition from Position myPosition where myPosition.profitLossRealized = ?1"),
		@NamedQuery(name = "findPositionByProfitLossUnrealized", query = "select myPosition from Position myPosition where myPosition.profitLossUnrealized = ?1"),
		@NamedQuery(name = "findPositionByReentrySecPriceCallAbove", query = "select myPosition from Position myPosition where myPosition.reentrySecPriceCallAbove = ?1"),
		@NamedQuery(name = "findPositionByReentrySecPricePutBelow", query = "select myPosition from Position myPosition where myPosition.reentrySecPricePutBelow = ?1"),
		@NamedQuery(name = "findPositionByStrategyName", query = "select myPosition from Position myPosition where myPosition.strategyName = ?1"),
		@NamedQuery(name = "findPositionByStrategyNameContaining", query = "select myPosition from Position myPosition where myPosition.strategyName like ?1"),
		@NamedQuery(name = "findPositionBySymbol", query = "select myPosition from Position myPosition where myPosition.symbol = ?1"),
		@NamedQuery(name = "findPositionBySymbolContaining", query = "select myPosition from Position myPosition where myPosition.symbol like ?1"),
		@NamedQuery(name = "findPositionByTotalRisked", query = "select myPosition from Position myPosition where myPosition.totalRisked = ?1"),
		@NamedQuery(name = "findPositionByUpdatedBy", query = "select myPosition from Position myPosition where myPosition.updatedBy = ?1"),
		@NamedQuery(name = "findPositionByUpdatedByContaining", query = "select myPosition from Position myPosition where myPosition.updatedBy like ?1"),
		@NamedQuery(name = "findPositionByUpdatedDate", query = "select myPosition from Position myPosition where myPosition.updatedDate = ?1"),
		@NamedQuery(name = "findPositionByUpdatedDateAfter", query = "select myPosition from Position myPosition where myPosition.updatedDate > ?1"),
		@NamedQuery(name = "findPositionByUpdatedDateBefore", query = "select myPosition from Position myPosition where myPosition.updatedDate < ?1"),
		@NamedQuery(name = "findPositionsByStrategyAcctId", query = "select myPosition from Position myPosition where myPosition.strategyAccountId = ?1" ),
		@NamedQuery(name = "findPositionsNonExpiredByStrategyAcctId", query = "select myPosition from Position myPosition where myPosition.expiryDate > ?1 and myPosition.strategyAccountId = ?2" )
})         		

		
@Table(schema = "THETA", name = "POSITION")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "theta11/com/theta/domain", name = "Position")
@XmlRootElement(namespace = "theta11/com/theta/domain")
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(Position.class);

	/**
	 */
	
	@Column(name = "POSITION_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POSITION_SEQ")
    @SequenceGenerator(name="POSITION_SEQ", sequenceName = "POSITION_SEQ")
	@XmlElement
	Integer positionId;
	/**
	 */

	@Column(name = "STRATEGY_NAME", length = 20, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String strategyName;
	/**
	 */

	@Column(name = "SYMBOL", length = 20, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String symbol;
	/**
	 */

	@Column(name = "OPT_RIGHT", length = 3, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String optRight;
	/**
	 */

	@Column(name = "EXPIRY_YEAR", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer expiryYear;
	/**
	 */

	@Column(name = "EXPIRY_MONTH", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer expiryMonth;
	/**
	 */

	@Column(name = "EXPIRY_DAY", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer expiryDay;
	/**
	 */

	@Column(name = "EXPIRY_TIMEFRAME", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String expiryTimeframe;
	/**
	 */

	@Column(name = "GOAL_NUM_SPREADS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer goalNumSpreads;
	/**
	 */

	@Column(name = "NUM_OPEN_SPREADS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer numOpenSpreads;
	/**
	 */

	@Column(name = "NUM_WINS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer numWins;
	/**
	 */

	@Column(name = "NUM_LOSSES")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer numLosses;
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

	@Column(name = "LAST_EXIT_SECURITY_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer lastExitSecurityPrice;
	/**
	 */

	@Column(name = "REENTRY_SEC_PRICE_CALL_ABOVE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer reentrySecPriceCallAbove;
	/**
	 */

	@Column(name = "REENTRY_SEC_PRICE_PUT_BELOW")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer reentrySecPricePutBelow;
	/**
	 */

	@Column(name = "TOTAL_RISKED")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer totalRisked;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "XTREME_UPDATED_DATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar xtremeUpdatedDate;
	/**
	 * 
	 */
	@Column(name = "CAN_ADD_TO_DB")
	@Basic(fetch = FetchType.LAZY)
	@XmlElement
	boolean canAddToDb;

	/**
	 * 
	 */
	@Column(name = "EXPIRY_DATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar expiryDate;
	/**
	 */
	@Column(name = "INSTRUMENT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String instrument;
	
	@Column(name = "STK_ENTER_AT_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer stkEnterAtPrice;
	/**
	 */
	
	@Column(name = "STRATEGY_ACCOUNT_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer strategyAccountId;
		
	
	/**
	 * 
	 * MOVING AVERAGE: REENTER AT PRICE
	 * 
	 */
	@Column(name = "STK_REENTER_AT_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stkReEnterAtPrice;

	@Column(name = "STK_REENTER_AT_PRICE_BAK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stkReEnterAtPriceBak;

	
	/**
	 * 
	 * MOVING AVERAGE: LOCAL EXTREME PRICE
	 * 
	 */
	@Column(name = "STK_LOCAL_XTREME_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stkLocalXtremePrice;
	
	
	@Column(name = "STK_LOCAL_XTREME_PRICE_BAK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
    Integer stkLocalXtremePriceBak;

	
	/**
	 * 
	 * MOVING AVERAGE: CAN REENTER AT EXTREME
	 * 
	 */
	@Column(name = "STK_CAN_REENTER_AT_XTREME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	boolean stkCanReenterAtXtreme;

	@Column(name = "STK_CAN_REENTER_XTREME_BAK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String stkCanReenterAtXtremeBak;
	

	
	
	/**
	 * 
	 * SLOPES: COUNTER XTREME
	 * 
	 */
	@Column(name = "STK_COUNTER_XTREME_PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stkCounterXtremePrice;

	@Column(name = "STK_COUNTER_XTREME_PRICE_BAK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
    Integer stkCounterXtremePriceBak;


	/**
	 * 
	 * SLOPES: HIT INITIAL COUNTER EXTREME
	 * 
	 */
	@Column(name = "STK_HIT_INIT_COUNTER_XTREME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	boolean stkHitInitialCounterXTreme;

	@Column(name = "STK_HIT_INIT_COUNTER_XT_BAK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String stkHitInitialCounterXTremeBak;

	
	/**
	 * 
	 * MA2: HAS been LO, MID, HI
	 * 
	 */
	@Column(name = "STK_HAS_BEEN_LO")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	boolean stkHasBeenLo;

	@Column(name = "STK_HAS_BEEN_MID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	boolean stkHasBeenMid;

	@Column(name = "STK_HAS_BEEN_HI")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	boolean stkHasBeenHi;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HAS_BEEN_HI_SET_DT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar hasBeenHiSetDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HAS_BEEN_MID_SET_DT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar hasBeenMidSetDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HAS_BEEN_LO_SET_DT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar hasBeenLoSetDt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEAD_SIGNAL_DT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar deadSignalDt;
	
	/**
	 */
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	/**
	 */
	public Integer getPositionId() {
		return this.positionId;
	}

	/**
	 */
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	/**
	 */
	public String getStrategyName() {
		return this.strategyName;
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
	public void setExpiryDay(Integer expiryDay) {
		this.expiryDay = expiryDay;
	}

	/**
	 */
	public Integer getExpiryDay() {
		return this.expiryDay;
	}

	/**
	 */
	public void setExpiryTimeframe(String expiryTimeframe) {
		this.expiryTimeframe = expiryTimeframe;
	}

	/**
	 */
	public String getExpiryTimeframe() {
		return this.expiryTimeframe;
	}

	/**
	 */
	public void setGoalNumSpreads(Integer goalNumSpreads) {
		this.goalNumSpreads = goalNumSpreads;
	}

	/**
	 */
	public Integer getGoalNumSpreads() {
		return this.goalNumSpreads;
	}

	/**
	 */
	public void setNumOpenSpreads(Integer numOpenSpreads) {
		this.numOpenSpreads = numOpenSpreads;
	}

	/**
	 */
	public Integer getNumOpenSpreads() {
		return this.numOpenSpreads;
	}

	/**
	 */
	public void setNumWins(Integer numWins) {
		this.numWins = numWins;
	}

	/**
	 */
	public Integer getNumWins() {
		return this.numWins;
	}

	/**
	 */
	public void setNumLosses(Integer numLosses) {
		this.numLosses = numLosses;
	}

	/**
	 */
	public Integer getNumLosses() {
		return this.numLosses;
	}

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
	public void setLastExitSecurityPrice(Integer lastExitSecurityPrice) {
		this.lastExitSecurityPrice = lastExitSecurityPrice;
	}

	/**
	 */
	public Integer getLastExitSecurityPrice() {
		return this.lastExitSecurityPrice;
	}

	/**
	 */
	public void setReentrySecPriceCallAbove(Integer reentrySecPriceCallAbove) {
		this.reentrySecPriceCallAbove = reentrySecPriceCallAbove;
	}

	/**
	 */
	public Integer getReentrySecPriceCallAbove() {
		return this.reentrySecPriceCallAbove;
	}

	/**
	 */
	public void setReentrySecPricePutBelow(Integer reentrySecPricePutBelow) {
		this.reentrySecPricePutBelow = reentrySecPricePutBelow;
	}

	/**
	 */
	public Integer getReentrySecPricePutBelow() {
		return this.reentrySecPricePutBelow;
	}

	/**
	 */
	public void setTotalRisked(Integer totalRisked) {
		this.totalRisked = totalRisked;
	}

	/**
	 */
	public Integer getTotalRisked() {
		return this.totalRisked;
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
	public void setXtremeUpdatedDate(Calendar xtremeUpdatedDate) {
		this.xtremeUpdatedDate = xtremeUpdatedDate;
	}

	/**
	 */
//	public Calendar getXtremeUpdatedDate() {
//		return this.xtremeUpdatedDate;
//	}
	
	/**
	 */
	public String getXtremeUpdatedDateStr() {
		if (xtremeUpdatedDate != null) 
			return xtremeUpdatedDate.getTime().toString();
		else
			return "";
	}
	
	
	/**
	 * @return the canOmit
	 */
	public boolean isCanAddToDb() {
		return canAddToDb;
	}

	/**
	 * @param canOmit the canOmit to set
	 */
	public void setCanAddToDb(boolean canAddToDb) {
		this.canAddToDb = canAddToDb;
	}

	/**
	 */
	public void setExpiryDate(Calendar expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 */
	public Calendar getExpiryDate() {
		return this.expiryDate;
	}

	/**
	 */
	public void setStrategyAccountId(Integer strategyAccountId) {
		this.strategyAccountId = strategyAccountId;
	}

	/**
	 */
	public Integer getStrategyAccountId() {
		return strategyAccountId;
	}

	/**
	 */
	public String getInstrument() {
		return instrument;
	}
	/**
	 */
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public Integer getStkEnterAtPrice() {
		return stkEnterAtPrice;
	}

	public void setStkEnterAtPrice(Integer stkEnterAtPrice) {
		this.stkEnterAtPrice = stkEnterAtPrice;
	}
  
	
	public boolean isCall(){
		if ((optRight.equalsIgnoreCase("C")) ||
			(optRight.equalsIgnoreCase("CAL")) ||
			(optRight.equalsIgnoreCase("CALL")))  {
			return true;
		} else return false;
	}

	public boolean isPut(){
		if ((optRight.equalsIgnoreCase("P")) ||
			(optRight.equalsIgnoreCase("PUT"))) {
			return true;
		} else return false;
	}

	public boolean isOption(){
		if (instrument==null) return false;
		
		if (instrument.equalsIgnoreCase("OPT")) return true;
		else return false;
	}
	
	public boolean isStock(){
		if (instrument==null) return false;

		if (instrument.equalsIgnoreCase("STK")) return true;
		else return false;
	}

	public Integer deriveStkLocalXtremePrice(Integer underlyingSymbolPrice, LongOrShortCode longOrShort) {
		
		if ( stkLocalXtremePrice == null
			||
			( longOrShort.equals(LongOrShortCode.LONG) && (underlyingSymbolPrice.compareTo(stkLocalXtremePrice) > 0) )
			||
			(longOrShort.equals(LongOrShortCode.SHORT) && (underlyingSymbolPrice.compareTo(stkLocalXtremePrice) < 0) ) ) {
			stkLocalXtremePrice = underlyingSymbolPrice;
		}
		return stkLocalXtremePrice;
	}

	public Integer getStkLocalXtremePrice() {
		return stkLocalXtremePrice;
	}
	
	public void setStkLocalXtremePrice(Integer stkLocalXtremePrice) {
		if (this.stkLocalXtremePrice != null)
			this.stkLocalXtremePriceBak = this.stkLocalXtremePrice;
		this.stkLocalXtremePrice = stkLocalXtremePrice;
	}
		
	public Integer getStkReEnterAtPrice() {
		return stkReEnterAtPrice;
	}

	
	public boolean setStkReEnterAtPrice(Integer avgDailyRange, Integer stkReenterBpTimesRange, LongOrShortCode longOrShort) throws ThetaExceptionExc {

		if ( (avgDailyRange == null) ||  (avgDailyRange.compareTo(0) <= 0)){
			//Log.info("avgDailyRange is null or 0 - throwing.");
			throw new ThetaExceptionExc("position.setStkReEnterAtPrice - avgDailyRange is null, 0 or less than 0");
		}
		if ( (stkReenterBpTimesRange == null)){
			//Log.info("stkReenterBpTimesRange is null or 0 - throwing.");
			throw new ThetaExceptionExc("position.setStkReEnterAtPrice - stkReenterBpTimesRange is null");
		}
		
		this.stkReEnterAtPriceBak = this.stkReEnterAtPrice;
		if (longOrShort.equals(LongOrShortCode.LONG)) {
			this.stkReEnterAtPrice = this.stkLocalXtremePrice -  ((avgDailyRange * stkReenterBpTimesRange)/ThetaConstants.ONE_HUNDRED_INT);
		} else if (longOrShort.equals(LongOrShortCode.SHORT)) {
			this.stkReEnterAtPrice = this.stkLocalXtremePrice + ((avgDailyRange * stkReenterBpTimesRange)/ThetaConstants.ONE_HUNDRED_INT);
		}
		return true;
		
	}

	public void setStkReEnterAtPrice(Integer stkReEnterAtPrice) {
		this.stkReEnterAtPriceBak = this.stkReEnterAtPrice;
		this.stkReEnterAtPrice = stkReEnterAtPrice;
	}

	
	public void calcAndSetStkLocalXtremePrices(SecurityPriceDAO securityPriceDAO, Strategy strategy, Integer numDaysAgoXtreme, Integer numDaysAgoCounterXtreme, ThetaMutex dbAccessMutex) {
		Calendar fromDateXtreme = Calendar.getInstance();
		fromDateXtreme.add(Calendar.DATE, -numDaysAgoXtreme);
		Calendar fromDateCounterXtreme = Calendar.getInstance();
		fromDateCounterXtreme.add(Calendar.DATE, -numDaysAgoXtreme);

		String stratName = strategy.getStrategyName() + ": ";
		
		log.info(stratName + "In calcAndSetStkLocalXtremePrice.  Strategy.getLongOrShortCode(): " + strategy.getLongOrShortCode());
		this.stkLocalXtremePriceBak = this.stkLocalXtremePrice;
		this.stkCounterXtremePriceBak = this.stkCounterXtremePrice;
		
		synchronized(dbAccessMutex){
			if ( strategy.getLongOrShortCode() != null && strategy.getLongOrShortCode().equals(LongOrShortCode.LONG) ) {
				this.stkLocalXtremePrice = securityPriceDAO.findMinOrMaxPriceSinceDateTime(fromDateXtreme, strategy.getSymbol(), MinOrMaxCode.MAX);
				this.stkCounterXtremePrice = securityPriceDAO.findMinOrMaxPriceSinceDateTime(fromDateCounterXtreme, strategy.getSymbol(), MinOrMaxCode.MIN);
			} else if ( strategy.getLongOrShortCode() != null && strategy.getLongOrShortCode().equals(LongOrShortCode.SHORT) ) {
				this.stkLocalXtremePrice = securityPriceDAO.findMinOrMaxPriceSinceDateTime(fromDateXtreme, strategy.getSymbol(), MinOrMaxCode.MIN);
				this.stkCounterXtremePrice = securityPriceDAO.findMinOrMaxPriceSinceDateTime(fromDateCounterXtreme, strategy.getSymbol(), MinOrMaxCode.MAX);
			} else {
				log.info(stratName + "Strategy long or short code is either NULL or not LONG or SHORT! (enum): " + strategy.getLongOrShortCode());
				log.info(stratName + "Strategy long or short code is either NULL or not LONG or SHORT! (enum.getValue()): " + strategy.getLongOrShortCode().getValue());
			}
		}
		
		this.stkCanReenterAtXtremeBak = (this.stkCanReenterAtXtreme == true) ? "T" : "F";
		this.stkHitInitialCounterXTremeBak = (this.stkHitInitialCounterXTreme == true) ? "T" : "F";
		
		this.stkCanReenterAtXtreme = true;
		this.stkHitInitialCounterXTreme = false;
		this.updatedDate = Calendar.getInstance();
	}
	

	
	
	public boolean isStkCanReenterAtXtreme() {
		return stkCanReenterAtXtreme;
	}

	public void setStkCanReenterAtXtreme(boolean stkCanReenterAtXtreme) {
		this.stkCanReenterAtXtremeBak = (this.stkCanReenterAtXtreme == true) ? "T" : "F";
		this.stkCanReenterAtXtreme = stkCanReenterAtXtreme;
	}

	public Integer getStkCounterXtremePrice() {
		return stkCounterXtremePrice;
	}

	public void setStkCounterXtremePrice(Integer stkCounterXtremePrice) {
		this.stkCounterXtremePriceBak = this.stkCounterXtremePrice;
		this.stkCounterXtremePrice = stkCounterXtremePrice;
	}

	public boolean isStkHitInitialCounterXTreme() {
		return stkHitInitialCounterXTreme;
	}

	public void setStkHitInitialCounterXTreme(boolean stkHitInitialCounterXTreme) {
		this.stkHitInitialCounterXTremeBak = (this.stkHitInitialCounterXTreme == true) ? "T" : "F";
		
		this.stkHitInitialCounterXTreme = stkHitInitialCounterXTreme;
	}


	/*
	 * Position class variables to rollback in case of a NOTEXEC:
	 * 
	 * 	stkCanReenterAtXtreme
	 *  stkCounterXtremePrice
	 *  stkHitInitialCounterXTreme
	 *  stkLocalXtremePrice
	 *  stkReEnterAtPrice
	 */
	public void setInitialValuesIfEmpty() {
		setIfEmpty(stkCanReenterAtXtremeBak, stkCanReenterAtXtreme);
		setIfEmpty(stkCounterXtremePriceBak, stkCounterXtremePrice);
		setIfEmpty(stkHitInitialCounterXTremeBak, stkHitInitialCounterXTreme);
		setIfEmpty(stkLocalXtremePriceBak, stkLocalXtremePrice);
		setIfEmpty(stkReEnterAtPriceBak, stkReEnterAtPrice);
	}
	public void rollbackToPreviousValues() {
		stkCanReenterAtXtreme = stringToBoolean(stkCanReenterAtXtremeBak);
	    stkCounterXtremePrice = stkCounterXtremePriceBak;
	    stkHitInitialCounterXTreme = stringToBoolean(stkHitInitialCounterXTremeBak);
	    stkLocalXtremePrice = stkLocalXtremePriceBak;
	    stkReEnterAtPrice = stkReEnterAtPriceBak;
	}
	private void setIfEmpty(Integer bak, Integer value) {
		if ( (bak == null) || bak.equals(0) )  bak = value;
	}
	private void setIfEmpty(String bak, boolean value) {
		if ( (bak == null) || bak.isEmpty() ) {
			if (value) bak = "T";
			if (!value) bak = "F";
		}
	}
	private boolean stringToBoolean (String bak) {
		if (bak == null)
			return false;
		
		if (bak.equalsIgnoreCase("T")) return true;
		return false;
	}
	
	
	public Integer getTrailingStopByPercent(Integer pctTrailingStop, LongOrShortCode longOrShort) {
		Integer trailingStop = null;
		if ( (pctTrailingStop == null) || (pctTrailingStop.compareTo(0) <= 0)){
			//Log.info("pctTrailingStop is null or 0 - returning null.");
			return null;
		}
		if ( (stkLocalXtremePrice == null) || (stkLocalXtremePrice.compareTo(0) <= 0)){
			//Log.info("stkLocalXtremePrice is null or 0 - returning null.");
			return null;
		}
		
		if (longOrShort.equals(LongOrShortCode.LONG)) {
			trailingStop = stkLocalXtremePrice - ((stkLocalXtremePrice * pctTrailingStop)/ThetaConstants.TEN_THOUSAND_INT);
		} else if (longOrShort.equals(LongOrShortCode.SHORT)) {
			trailingStop = stkLocalXtremePrice + ((stkLocalXtremePrice * pctTrailingStop)/ThetaConstants.TEN_THOUSAND_INT);
		}
		return trailingStop;
	}

	public Integer getTrailingStopByRange(Integer avgDailyRange, Integer stkTrailingStopBpTimesRange, LongOrShortCode longOrShort) {
		Integer trailingStop = null;
		if ( (stkTrailingStopBpTimesRange == null) || (stkTrailingStopBpTimesRange.compareTo(0) <= 0)){
			//Log.info("stkTrailingStopBpTimesRange is null or 0 - returning null.");
			return null;
		}
		if ( (stkLocalXtremePrice == null) || (stkLocalXtremePrice.compareTo(0) <= 0)){
			//Log.info("stkLocalXtremePrice is null or 0 - returning null.");
			return null;
		}
		
		if (longOrShort.equals(LongOrShortCode.LONG)) {
			trailingStop = stkLocalXtremePrice -  ((avgDailyRange * stkTrailingStopBpTimesRange)/ThetaConstants.ONE_HUNDRED_INT);
		} else if (longOrShort.equals(LongOrShortCode.SHORT)) {
			trailingStop = stkLocalXtremePrice + ((avgDailyRange * stkTrailingStopBpTimesRange)/ThetaConstants.ONE_HUNDRED_INT);
		}
		return trailingStop;
	}
	
	
	
	public Integer getTrailingStopByRange(Integer avgDailyRange, Strategy strategy, Integer enterPrice, LongOrShortCode longOrShort) {
		Integer trailingStop = null;
		
		Integer bpTimesRange = strategy.getStkTrailingStopBpTimesRange();
		Integer bpTimesRange2 = strategy.getStkTrailingStopBpTimesRange2();
		Integer stopBpCutpoint  = strategy.getStkTrailingStopBpCutpoint();
		
		if (longOrShort.equals(LongOrShortCode.LONG)) {
			Integer cutpointActual = enterPrice + ((avgDailyRange * stopBpCutpoint)/ThetaConstants.ONE_HUNDRED_INT);		
			trailingStop = stkLocalXtremePrice -  ((avgDailyRange * bpTimesRange)/ThetaConstants.ONE_HUNDRED_INT);
			if (trailingStop.compareTo(cutpointActual) > 0 ) {
				// trailing stop is above cutpoint - use trailingStopFar
				trailingStop = stkLocalXtremePrice -  ((avgDailyRange * bpTimesRange2)/ThetaConstants.ONE_HUNDRED_INT);
			}
		} else if (longOrShort.equals(LongOrShortCode.SHORT)) {
			Integer cutpointActual = enterPrice - ((avgDailyRange * stopBpCutpoint)/ThetaConstants.ONE_HUNDRED_INT);		
			trailingStop = stkLocalXtremePrice +  ((avgDailyRange * bpTimesRange)/ThetaConstants.ONE_HUNDRED_INT);
			if (trailingStop.compareTo(cutpointActual) < 0 ) {
				// trailing stop is above cutpoint - use trailingStopFar
				trailingStop = stkLocalXtremePrice +  ((avgDailyRange * bpTimesRange2)/ThetaConstants.ONE_HUNDRED_INT);
			}
		}
		return trailingStop;
	}
	
	
	public Integer getStopOverEntryByRange(Integer avgDailyRange, Spread spread, LongOrShortCode longOrShort, Integer percentFromEntryToSetStop) {
		Integer enterActual = spread.getEnterActualIb();
		//Integer PERCENT_FROM_ENTRY_TO_SET_STOP = 12;
		if (enterActual == null || enterActual.compareTo(0) <= 0 ) {
			enterActual = spread.getEnterMoneymkrPrice();
			if (enterActual == null || enterActual.compareTo(0) <= 0 ) {
				log.error("Both EnterActualIb and EnterMoneymkrPrice are null or less than zero for stock: " + this.getSymbol() );
				return null;
			}
		}
		if ( (avgDailyRange == null) || (avgDailyRange.compareTo(0) <= 0)){
			log.info("avgDailyRange is null or 0 - returning null.");
			return null;
		}
		
		
		Integer stopAt = null;
		if (longOrShort.equals(LongOrShortCode.LONG)) {
			stopAt = enterActual + ((avgDailyRange * percentFromEntryToSetStop)/ThetaConstants.ONE_HUNDRED_INT);
		} else if (longOrShort.equals(LongOrShortCode.SHORT)) {
			stopAt = enterActual - ((avgDailyRange * percentFromEntryToSetStop)/ThetaConstants.ONE_HUNDRED_INT);
		}
		return stopAt;
	}
	
	
	public boolean isStkHasBeenLo() {
		return stkHasBeenLo;
	}

//	public void setStkHasBeenLo(boolean stkHasBeenLo) {
//		this.stkHasBeenLo = stkHasBeenLo;
//	}

	public boolean isStkHasBeenMid() {
		return stkHasBeenMid;
	}

	public void setStkHasBeenMid(boolean stkHasBeenMid) {
		this.stkHasBeenMid = stkHasBeenMid;
	}

	public boolean isStkHasBeenHi() {
		return stkHasBeenHi;
	}

//	public void setStkHasBeenHi(boolean stkHasBeenHi) {
//		this.stkHasBeenHi = stkHasBeenHi;
//	}

	public void setLoMidHiToFalse() {
		this.stkHasBeenLo = false;
		this.stkHasBeenMid = false;
		this.stkHasBeenHi = false;
		Calendar now = Calendar.getInstance();
		this.hasBeenLoSetDt = now;
		this.hasBeenMidSetDt = now;
		this.hasBeenHiSetDt = now;
	}
	
	public LoMidHiCode resetLoMidHi(Integer currentSecurityPrice,  MovingAverages.AllMovingAverages allMovAvgs, String stratName) throws ThetaExceptionExc {
		this.stkHasBeenLo = false;
		this.stkHasBeenMid = false;
		this.stkHasBeenHi = false;

		Calendar now = Calendar.getInstance();
		this.hasBeenLoSetDt = now;
		this.hasBeenMidSetDt = now;
		this.hasBeenHiSetDt = now;

		LoMidHiCode loMidHi = setLoMidHi(currentSecurityPrice, allMovAvgs, stratName);
		return loMidHi;
	}
		
	public LoMidHiCode setLoMidHi(Integer currentSecurityPrice,  MovingAverages.AllMovingAverages allMovAvgs, String stratName) throws ThetaExceptionExc {
		Calendar now = Calendar.getInstance();
		if (currentSecurityPrice.compareTo(allMovAvgs.loAvg) < 0) {
			this.stkHasBeenLo = true;
			this.hasBeenLoSetDt = now;
			this.stkHasBeenHi = false;
			this.hasBeenHiSetDt = now;
			return LoMidHiCode.LO;
		} else if ( currentSecurityPrice.compareTo(allMovAvgs.loAvg) >= 0 &&  currentSecurityPrice.compareTo(allMovAvgs.hiAvg) <= 0 ){
			this.stkHasBeenMid = true;
			this.hasBeenMidSetDt = now;
			return LoMidHiCode.MID;
		} else if ( currentSecurityPrice.compareTo(allMovAvgs.hiAvg) > 0 ) {
			this.stkHasBeenHi = true;
			this.hasBeenHiSetDt = now;
			this.stkHasBeenLo = false;
			this.hasBeenLoSetDt = now;
			return LoMidHiCode.HI;
		} else {
			log.error(stratName + "SecurityPrices is neither LO, MID or HI");
			log.error(stratName + "SecurityPrice : " + currentSecurityPrice + ". Lo, Hi: " + allMovAvgs.loAvg + ", " + allMovAvgs.hiAvg);
			throw new ThetaExceptionExc(stratName + "Security price is neither LO, MID or HI!");
		}
	}
	
	/*
	 * Decides if the signal is dead:
	 *  - This is the first time the stock has risen more than "N" times ADR over the 
	 *    Moving Average high (LONG) or under the low (SHORT).
	 *    
	 *  In this case, momentum is no longer there for example:
	 *    - Stock is above the Moving Average but the Moving Average is still trending the wrong way
	 *    - Another stock has been exited and Margin has opened up
	 */
	public boolean isSignalDead(
			Integer currentSecurityPrice, 
			Integer ADRBPToKillSignal, 
			MovingAverages.AllMovingAverages allMovAvgs, 
			UpOrDownCode upOrDownCode) {
		
		Integer amountOffset = (allMovAvgs.avgDailyRange * ADRBPToKillSignal) / 100 ;
				
		if (upOrDownCode.equals(UpOrDownCode.UP)){
			if ( (this.stkHasBeenLo || this.stkHasBeenMid) && 
			(currentSecurityPrice.compareTo(allMovAvgs.hiAvg + amountOffset) > 0) ) {
				return true;
			}
		} else if (upOrDownCode.equals(UpOrDownCode.DOWN)) {
			if ( (this.stkHasBeenHi || this.stkHasBeenMid) && 
			(currentSecurityPrice.compareTo(allMovAvgs.loAvg - amountOffset) < 0) ) {
				return true;
			}
		}
		return false;
	}
	
	public void setDeadSignalDt(Calendar deadSignalDt) {
		this.deadSignalDt = deadSignalDt;
	}

	/**
	 */
	public Position() {
	}

	public void init() {
		this.setNumOpenSpreads(ThetaConstants.ZERO_INT);
		this.setNumWins(ThetaConstants.ZERO_INT);
		this.setNumLosses(ThetaConstants.ZERO_INT);
		this.setProfitLossUnrealized(ThetaConstants.ZERO_INT);
		this.setProfitLossRealized(ThetaConstants.ZERO_INT);
		this.setLastExitSecurityPrice(ThetaConstants.ZERO_INT);
		this.setReentrySecPriceCallAbove(ThetaConstants.ZERO_INT);
		this.setReentrySecPricePutBelow(ThetaConstants.ZERO_INT);
		this.setStkReEnterAtPrice(ThetaConstants.ZERO_INT);
		this.setCreatedDate(Calendar.getInstance());
		this.setStkCanReenterAtXtreme(false);
	}
		
	
	
	
	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Position that) {
		setPositionId(that.getPositionId());
		setStrategyName(that.getStrategyName());
		setSymbol(that.getSymbol());
		setOptRight(that.getOptRight());
		setExpiryYear(that.getExpiryYear());
		setExpiryMonth(that.getExpiryMonth());
		setExpiryDay(that.getExpiryDay());
		setExpiryTimeframe(that.getExpiryTimeframe());
		setGoalNumSpreads(that.getGoalNumSpreads());
		setNumOpenSpreads(that.getNumOpenSpreads());
		setNumWins(that.getNumWins());
		setNumLosses(that.getNumLosses());
		setProfitLossUnrealized(that.getProfitLossUnrealized());
		setProfitLossRealized(that.getProfitLossRealized());
		setLastExitSecurityPrice(that.getLastExitSecurityPrice());
		setReentrySecPriceCallAbove(that.getReentrySecPriceCallAbove());
		setReentrySecPricePutBelow(that.getReentrySecPricePutBelow());
		setTotalRisked(that.getTotalRisked());
		setCreatedBy(that.getCreatedBy());
		setCreatedDate(that.getCreatedDate());
		setUpdatedBy(that.getUpdatedBy());
		setUpdatedDate(that.getUpdatedDate());
		setExpiryDate(that.getExpiryDate());
		setStrategyAccountId(that.getStrategyAccountId());
		//setSpreads(new java.util.LinkedHashSet<com.theta.domain.Spread>(that.getSpreads()));
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Position [positionId=");
		builder.append(positionId);
		builder.append(", strategyName=");
		builder.append(strategyName);
		builder.append(", symbol=");
		builder.append(symbol);
		builder.append(", optRight=");
		builder.append(optRight);
		builder.append(", expiryYear=");
		builder.append(expiryYear);
		builder.append(", expiryMonth=");
		builder.append(expiryMonth);
		builder.append(", expiryDay=");
		builder.append(expiryDay);
		builder.append(", expiryTimeframe=");
		builder.append(expiryTimeframe);
		builder.append(", goalNumSpreads=");
		builder.append(goalNumSpreads);
		builder.append(", numOpenSpreads=");
		builder.append(numOpenSpreads);
		builder.append(", numWins=");
		builder.append(numWins);
		builder.append(", numLosses=");
		builder.append(numLosses);
		builder.append(", profitLossUnrealized=");
		builder.append(profitLossUnrealized);
		builder.append(", profitLossRealized=");
		builder.append(profitLossRealized);
		builder.append(", lastExitSecurityPrice=");
		builder.append(lastExitSecurityPrice);
		builder.append(", reentrySecPriceCallAbove=");
		builder.append(reentrySecPriceCallAbove);
		builder.append(", reentrySecPricePutBelow=");
		builder.append(reentrySecPricePutBelow);
		builder.append(", totalRisked=");
		builder.append(totalRisked);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append(", updatedDate=");
		builder.append(updatedDate);
		builder.append(", xtremeUpdatedDate=");
		builder.append(xtremeUpdatedDate);
		builder.append(", canAddToDb=");
		builder.append(canAddToDb);
		builder.append(", expiryDate=");
		builder.append(expiryDate);
		builder.append(", instrument=");
		builder.append(instrument);
		builder.append(", stkEnterAtPrice=");
		builder.append(stkEnterAtPrice);
		builder.append(", strategyAccountId=");
		builder.append(strategyAccountId);
		builder.append(", stkReEnterAtPrice=");
		builder.append(stkReEnterAtPrice);
		builder.append(", stkReEnterAtPriceBak=");
		builder.append(stkReEnterAtPriceBak);
		builder.append(", stkLocalXtremePrice=");
		builder.append(stkLocalXtremePrice);
		builder.append(", stkLocalXtremePriceBak=");
		builder.append(stkLocalXtremePriceBak);
		builder.append(", stkCanReenterAtXtreme=");
		builder.append(stkCanReenterAtXtreme);
		builder.append(", stkCanReenterAtXtremeBak=");
		builder.append(stkCanReenterAtXtremeBak);
		builder.append(", stkCounterXtremePrice=");
		builder.append(stkCounterXtremePrice);
		builder.append(", stkCounterXtremePriceBak=");
		builder.append(stkCounterXtremePriceBak);
		builder.append(", stkHitInitialCounterXTreme=");
		builder.append(stkHitInitialCounterXTreme);
		builder.append(", stkHitInitialCounterXTremeBak=");
		builder.append(stkHitInitialCounterXTremeBak);
		builder.append(", stkHasBeenLo=");
		builder.append(stkHasBeenLo);
		builder.append(", stkHasBeenMid=");
		builder.append(stkHasBeenMid);
		builder.append(", stkHasBeenHi=");
		builder.append(stkHasBeenHi);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toStringShort() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("\npositionId=[").append(positionId).append("] ");
		buffer.append("strategyName=[").append(strategyName).append("] ");
		buffer.append("symbol=[").append(symbol).append("] ");
		buffer.append("optRight=[").append(optRight).append("] ");

		if (expiryDate != null) {
			buffer.append("expiryDate=[").append(expiryDate.getTime()).append("] ");
		}
		
		return buffer.toString();
	}

	
	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((positionId == null) ? 0 : positionId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Position))
			return false;
		Position equalCheck = (Position) obj;
		if ((positionId == null && equalCheck.positionId != null) || (positionId != null && equalCheck.positionId == null))
			return false;
		if (positionId != null && !positionId.equals(equalCheck.positionId))
			return false;
		return true;
	}


}

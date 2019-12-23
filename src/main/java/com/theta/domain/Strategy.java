package com.theta.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

import com.theta.enums.LongOrShortCode;
import com.theta.process.ThetaConstants;
import com.theta.process.ThetaExceptionExc;

/*
 * March 30, 2014 - deprecate these fields.  
 * FIRST: LOG if these items are called via a getter.
 * 
AMOUNT_RISK_PER_POINT
AMOUNT_TOTAL_RISK
BEAR_MKT_DAYS_TO_END - already commented out
BEAR_MKT_FLAG - already commented out
BEAR_MKT_POINTS_TO_SUBTRACT - already commented out
BEAR_MKT_TRIGGER - already commented out
BULL_MKT_DAYS_TO_END - already commented out
BULL_MKT_FLAG - already commented out
BULL_MKT_POINTS_TO_ADD - already commented out
BULL_MKT_TRIGGER - already commented out
DAYS_EXIT_BEFORE_EXPIRY
DISTANCE_BET_OPTIONS
HM_BP_LIMIT_UP2
HM_BP_STOP_LIMIT_DOWN
HM_DAYS_TO_EXPIRY
HM_PCT_BP_DOWN_FROM_TODAY
HM_PCT_BP_DOWN_FROM_YEST
HM_PCT_BP_DOWN_IN_LAST_90
HM_TURNON - OK
INIT_NUM_SPREADS_CALL
INIT_NUM_SPREADS_PUT
MIN_DELAY_FROM_START
MS_WAIT_FOR_IB_RESPONSE
NO_ENTRY_IF_VIX_OVER
OPT_PRICES_TO_GET
PCT_EXIT_WIN
PCT_SET_TRAILING_STOP
PCT_TRAILING_STOP
PERCENT_ENTER_HIGH
PERCENT_ENTER_LOW
POINT_DIFF_FOR_REENTRY
SET_BREAKEVEN_WHEN_INS_HIT
SET_CHECK_VIX
STK_ENTER_POINT_DIFF
STK_EXIT_POINT_DIFF
STK_POINTS_FOR_STOP
STK_SHARE_BLOCK_SIZE
 */


/**
 */

@Entity
@NamedQueries( {
		@NamedQuery(name = "findAllStrategys", query = "select myStrategy from Strategy myStrategy"),
		@NamedQuery(name = "findStrategyByActiveFlag", query = "select myStrategy from Strategy myStrategy where myStrategy.activeFlag = ?1"),
		@NamedQuery(name = "findStrategyByActiveFlagContaining", query = "select myStrategy from Strategy myStrategy where myStrategy.activeFlag like ?1"),
		@NamedQuery(name = "findStrategyByAmountRiskPerPoint", query = "select myStrategy from Strategy myStrategy where myStrategy.amountRiskPerPoint = ?1"),
		@NamedQuery(name = "findStrategyByAmountTotalRisk", query = "select myStrategy from Strategy myStrategy where myStrategy.amountTotalRisk = ?1"),
		@NamedQuery(name = "findStrategyByComissionPerTrade", query = "select myStrategy from Strategy myStrategy where myStrategy.comissionPerTrade = ?1"),
		@NamedQuery(name = "findStrategyByCreatedBy", query = "select myStrategy from Strategy myStrategy where myStrategy.createdBy = ?1"),
		@NamedQuery(name = "findStrategyByCreatedByContaining", query = "select myStrategy from Strategy myStrategy where myStrategy.createdBy like ?1"),
		@NamedQuery(name = "findStrategyByCreatedDate", query = "select myStrategy from Strategy myStrategy where myStrategy.createdDate = ?1"),
		@NamedQuery(name = "findStrategyByCreatedDateAfter", query = "select myStrategy from Strategy myStrategy where myStrategy.createdDate > ?1"),
		@NamedQuery(name = "findStrategyByCreatedDateBefore", query = "select myStrategy from Strategy myStrategy where myStrategy.createdDate < ?1"),
		@NamedQuery(name = "findStrategyByDaysEnterHigh", query = "select myStrategy from Strategy myStrategy where myStrategy.daysEnterHigh = ?1"),
		@NamedQuery(name = "findStrategyByDaysEnterLow", query = "select myStrategy from Strategy myStrategy where myStrategy.daysEnterLow = ?1"),
		@NamedQuery(name = "findStrategyByDaysExitBeforeExpiry", query = "select myStrategy from Strategy myStrategy where myStrategy.daysExitBeforeExpiry = ?1"),
		@NamedQuery(name = "findStrategyByDistanceBetOptions", query = "select myStrategy from Strategy myStrategy where myStrategy.distanceBetOptions = ?1"),
		@NamedQuery(name = "findStrategyByInitNumSpreadsCall", query = "select myStrategy from Strategy myStrategy where myStrategy.initNumSpreadsCall = ?1"),
		@NamedQuery(name = "findStrategyByInitNumSpreadsPut", query = "select myStrategy from Strategy myStrategy where myStrategy.initNumSpreadsPut = ?1"),
		@NamedQuery(name = "findStrategyByLiveOrTestFlag", query = "select myStrategy from Strategy myStrategy where myStrategy.liveOrTestFlag = ?1"),
		@NamedQuery(name = "findStrategyByLiveOrTestFlagContaining", query = "select myStrategy from Strategy myStrategy where myStrategy.liveOrTestFlag like ?1"),
		@NamedQuery(name = "findStrategyByMinDelayFromStart", query = "select myStrategy from Strategy myStrategy where myStrategy.minDelayFromStart = ?1"),
		@NamedQuery(name = "findStrategyByMsWaitForIbResponse", query = "select myStrategy from Strategy myStrategy where myStrategy.msWaitForIbResponse = ?1"),
		@NamedQuery(name = "findStrategyByNoEntryIfVixOver", query = "select myStrategy from Strategy myStrategy where myStrategy.noEntryIfVixOver = ?1"),
		@NamedQuery(name = "findStrategyByOptPricesToGet", query = "select myStrategy from Strategy myStrategy where myStrategy.optPricesToGet = ?1"),
		@NamedQuery(name = "findStrategyByPctExitWin", query = "select myStrategy from Strategy myStrategy where myStrategy.pctExitWin = ?1"),
		@NamedQuery(name = "findStrategyByPctSetTrailingStop", query = "select myStrategy from Strategy myStrategy where myStrategy.pctSetTrailingStop = ?1"),
		@NamedQuery(name = "findStrategyByPctTrailingStop", query = "select myStrategy from Strategy myStrategy where myStrategy.pctTrailingStop = ?1"),
		@NamedQuery(name = "findStrategyByPercentEnterHigh", query = "select myStrategy from Strategy myStrategy where myStrategy.percentEnterHigh = ?1"),
		@NamedQuery(name = "findStrategyByPercentEnterLow", query = "select myStrategy from Strategy myStrategy where myStrategy.percentEnterLow = ?1"),
		@NamedQuery(name = "findStrategyByPointDiffForReentry", query = "select myStrategy from Strategy myStrategy where myStrategy.pointDiffForReentry = ?1"),
		@NamedQuery(name = "findStrategyByPrimaryKey", query = "select myStrategy from Strategy myStrategy where myStrategy.strategyId = ?1"),
		@NamedQuery(name = "findStrategyBySetBreakevenWhenInsHit", query = "select myStrategy from Strategy myStrategy where myStrategy.setBreakevenWhenInsHit = ?1"),
		@NamedQuery(name = "findStrategyBySetBreakevenWhenInsHitContaining", query = "select myStrategy from Strategy myStrategy where myStrategy.setBreakevenWhenInsHit like ?1"),
		@NamedQuery(name = "findStrategyBySetCheckVix", query = "select myStrategy from Strategy myStrategy where myStrategy.setCheckVix = ?1"),
		@NamedQuery(name = "findStrategyBySetCheckVixContaining", query = "select myStrategy from Strategy myStrategy where myStrategy.setCheckVix like ?1"),
		@NamedQuery(name = "findStrategyBySetExitAtInsPrice", query = "select myStrategy from Strategy myStrategy where myStrategy.setExitAtInsPrice = ?1"),
		@NamedQuery(name = "findStrategyBySetExitAtInsPriceContaining", query = "select myStrategy from Strategy myStrategy where myStrategy.setExitAtInsPrice like ?1"),
		@NamedQuery(name = "findStrategyBySetReenterOnLoss", query = "select myStrategy from Strategy myStrategy where myStrategy.setReenterOnLoss = ?1"),
		@NamedQuery(name = "findStrategyBySetReenterOnLossContaining", query = "select myStrategy from Strategy myStrategy where myStrategy.setReenterOnLoss like ?1"),
		@NamedQuery(name = "findStrategyBySetReenterOnWin", query = "select myStrategy from Strategy myStrategy where myStrategy.setReenterOnWin = ?1"),
		@NamedQuery(name = "findStrategyBySetReenterOnWinContaining", query = "select myStrategy from Strategy myStrategy where myStrategy.setReenterOnWin like ?1"),
		@NamedQuery(name = "findStrategyBySetWinTrailingStop", query = "select myStrategy from Strategy myStrategy where myStrategy.setWinTrailingStop = ?1"),
		@NamedQuery(name = "findStrategyBySetWinTrailingStopContaining", query = "select myStrategy from Strategy myStrategy where myStrategy.setWinTrailingStop like ?1"),
		@NamedQuery(name = "findStrategyByStrategyId", query = "select myStrategy from Strategy myStrategy where myStrategy.strategyId = ?1"),
		@NamedQuery(name = "findStrategyByStrategyName", query = "select myStrategy from Strategy myStrategy where myStrategy.strategyName = ?1"),
		@NamedQuery(name = "findStrategyByStrategyNameContaining", query = "select myStrategy from Strategy myStrategy where myStrategy.strategyName like ?1"),
		@NamedQuery(name = "findStrategyBySymbol", query = "select myStrategy from Strategy myStrategy where myStrategy.symbol = ?1"),
		@NamedQuery(name = "findStrategyBySymbolContaining", query = "select myStrategy from Strategy myStrategy where myStrategy.symbol like ?1"),
		@NamedQuery(name = "findStrategyByTimezone", query = "select myStrategy from Strategy myStrategy where myStrategy.timezone = ?1"),
		@NamedQuery(name = "findStrategyByTimezoneContaining", query = "select myStrategy from Strategy myStrategy where myStrategy.timezone like ?1"),
		@NamedQuery(name = "findStrategyByUpdatedBy", query = "select myStrategy from Strategy myStrategy where myStrategy.updatedBy = ?1"),
		@NamedQuery(name = "findStrategyByUpdatedByContaining", query = "select myStrategy from Strategy myStrategy where myStrategy.updatedBy like ?1"),
		@NamedQuery(name = "findStrategyByUpdatedDate", query = "select myStrategy from Strategy myStrategy where myStrategy.updatedDate = ?1"),
		@NamedQuery(name = "findStrategyByUpdatedDateAfter", query = "select myStrategy from Strategy myStrategy where myStrategy.updatedDate > ?1"),
		@NamedQuery(name = "findStrategyByUpdatedDateBefore", query = "select myStrategy from Strategy myStrategy where myStrategy.updatedDate < ?1") })
@Table(schema = "THETA", name = "STRATEGY")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "theta11/com/theta/domain", name = "Strategy")
@XmlRootElement(namespace = "theta11/com/theta/domain")
public class Strategy implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(Strategy.class);
	/**
	 */

	@Column(name = "STRATEGY_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer strategyId;
	/**
	 */

	@Column(name = "STRATEGY_NAME", length = 25)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String strategyName;
	/**
	 */

	@Column(name = "SYMBOL", length = 25)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String symbol;
	/**
	 */

	@Column(name = "ACTIVE_FLAG", length = 1, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String activeFlag;
	/**
	 */

	@Column(name = "INIT_NUM_SPREADS_CALL", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer initNumSpreadsCall;
	/**
	 */

	@Column(name = "INIT_NUM_SPREADS_PUT", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer initNumSpreadsPut;
	/**
	 */

	@Column(name = "PERCENT_ENTER_LOW")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer percentEnterLow;
	/**
	 */

	@Column(name = "PERCENT_ENTER_HIGH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer percentEnterHigh;
	/**
	 */

	@Column(name = "DAYS_ENTER_LOW")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer daysEnterLow;
	/**
	 */

	@Column(name = "DAYS_ENTER_HIGH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer daysEnterHigh;
	/**
	 */

	@Column(name = "AMOUNT_TOTAL_RISK", scale = 0, precision = 9, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer amountTotalRisk;
	/**
	 */

	@Column(name = "AMOUNT_RISK_PER_POINT", scale = 0, precision = 9, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer amountRiskPerPoint;
	/**
	 */

	@Column(name = "DISTANCE_BET_OPTIONS", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer distanceBetOptions;
	/**
	 */

	@Column(name = "OPT_PRICES_TO_GET", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer optPricesToGet;
	/**
	 */

	@Column(name = "PCT_SET_TRAILING_STOP", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer pctSetTrailingStop;
	/**
	 */

	@Column(name = "PCT_TRAILING_STOP", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer pctTrailingStop;
	/**
	 */

	@Column(name = "PCT_EXIT_WIN", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer pctExitWin;
	/**
	 */

	@Column(name = "SET_WIN_TRAILING_STOP", length = 1, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String setWinTrailingStop;
	/**
	 */

	@Column(name = "SET_EXIT_AT_INS_PRICE", length = 1, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String setExitAtInsPrice;
	/**
	 */

	@Column(name = "SET_REENTER_ON_WIN", length = 1, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String setReenterOnWin;
	/**
	 */

	@Column(name = "SET_REENTER_ON_LOSS", length = 1, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String setReenterOnLoss;
	/**
	 */

	@Column(name = "POINT_DIFF_FOR_REENTRY", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer pointDiffForReentry;
	/**
	 */

	@Column(name = "DAYS_EXIT_BEFORE_EXPIRY", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer daysExitBeforeExpiry;
	/**
	 */

	@Column(name = "SET_BREAKEVEN_WHEN_INS_HIT", length = 1, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String setBreakevenWhenInsHit;
	/**
	 */

	@Column(name = "MS_WAIT_FOR_IB_RESPONSE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer msWaitForIbResponse;
	/**
	 */

	@Column(name = "MIN_DELAY_FROM_START", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer minDelayFromStart;
	/**
	 */

	@Column(name = "SET_CHECK_VIX", length = 1, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String setCheckVix;
	/**
	 */

	@Column(name = "NO_ENTRY_IF_VIX_OVER", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer noEntryIfVixOver;
	/**
	 */

	@Column(name = "TIMEZONE", length = 3, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String timezone;
	/**
	 */

	@Column(name = "LIVE_OR_TEST_FLAG", length = 4, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String liveOrTestFlag;
	/**
	 */

	@Column(name = "COMISSION_PER_TRADE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer comissionPerTrade;
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
	@Column(name = "HM_PCT_BP_DOWN_FROM_YEST")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer hmPctBpDownFromYest;
	/**
	 */
	@Column(name = "HM_PCT_BP_DOWN_FROM_TODAY")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer hmPctBpDownFromToday;
	/**
	 */
	@Column(name = "HM_PCT_BP_DOWN_IN_LAST_90")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer hmPctBpDownInLast90;
	/**
	 */
	@Column(name = "HM_DAYS_TO_EXPIRY")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer hmDaysToExpiry;
	/**
	 */
	@Column(name = "HM_BP_LIMIT_UP2")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer hmBpLimitUp; 
	/**
	 */
	@Column(name = "HM_BP_STOP_LIMIT_DOWN")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer hmBpStopLimitDown;
	/**
	 */
	@Column(name = "HM_TURNON")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	String hmTurnOn;
	/**
	 */
	@Column(name = "BULL_MKT_TRIGGER")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer bullMktTrigger;

	@Column(name = "BULL_MKT_FLAG")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	String bullMktFlag;

	@Column(name = "BULL_MKT_DAYS_TO_END")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer bullMktDaysToEnd;
	
	@Column(name = "BULL_MKT_POINTS_TO_ADD")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer bullMktPointsToAdd;
	
	
	
	@Column(name = "BEAR_MKT_TRIGGER")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer bearMktTrigger;

	@Column(name = "BEAR_MKT_FLAG")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	String bearMktFlag;

	@Column(name = "BEAR_MKT_DAYS_TO_END")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer bearMktDaysToEnd;

	@Column(name = "BEAR_MKT_POINTS_TO_SUBTRACT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer bearMktPointsToSubtract;
	
	
	/**
	 * Add the following columns for Stock-only strategies - April 2013:
	 *	instrument - STK vs OPT
	 *	long_or_short_flag
	 *	stk_share_block_size
	 *	stk_enter_point_diff
	 *	stk_exit_point_diff
	 *	stk_pts_for_stop
	 *	stk_goal_num_positions
	 */
	@Column(name = "INSTRUMENT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	String instrument;
	
	@Column(name = "LONG_OR_SHORT_FLAG")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	String longOrShortFlag;

	@Column(name = "STK_SHARE_BLOCK_SIZE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer stkShareBlockSize;

	@Column(name = "STK_ENTER_POINT_DIFF")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer stkEnterPointDiff;

	@Column(name = "STK_EXIT_POINT_DIFF")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer stkExitPointDiff;

	@Column(name = "STK_POINTS_FOR_STOP")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer stkPointsForStop;

	@Column(name = "STK_GOAL_NUM_SPREADS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer stkGoalNumSpreads;
	
	@Column(name = "MOVING_AVG_1")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer movingAvg1;

	@Column(name = "MOVING_AVG_2")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer movingAvg2;

	@Column(name = "MOVING_AVG_GRACE_POINTS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer movingAvgGracePoints;
	
	@Column(name = "STK_STRATEGY_TYPE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	String stkStrategyType;

	@Column(name = "MOVING_AVG_RANGE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer movingAvgRange;

	@Column(name = "MOVING_AVG_TOLERANCE_PCT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer movingAvgTolerancePct;

	@Column(name = "STK_TRAILING_STOP_BP_X_RANGE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer stkTrailingStopBpTimesRange;
	
	@Column(name = "STK_REENTER_BP_TIMES_RANGE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer stkReenterBpTimesRange;
	
	@Column(name = "SLOPE_TIME_MIN_1")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer slopeTimeMin1;

	@Column(name = "SLOPE_ADR_TBP_PER_MIN_1")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer slopeAdrTbpPerMin1;

	@Column(name = "SLOPE_TIME_MIN_2")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer slopeTimeMin2;

	@Column(name = "SLOPE_ADR_TBP_PER_MIN_2")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer slopeAdrTbpPerMin2;

	@Column(name = "SLOPE_TIME_MIN_3")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer slopeTimeMin3;

	@Column(name = "SLOPE_ADR_TBP_PER_MIN_3")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer slopeAdrTbpPerMin3;
	
	@Column(name = "EARNINGS_REPORT_NEXT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Calendar earningsReportNext;

	@Column(name = "EARNINGS_REPORT_ACTION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	String earningsReportAction;

	@Column(name = "HONOR_MOVING_AVG")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	String honorMovingAvg;

	@Column(name = "WARNING")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	String warning;

	@Column(name = "STK_TRAILING_STOP_BP_X_RANGE2")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer stkTrailingStopBpTimesRange2;
	
	@Column(name = "STK_TRAILING_STOP_BP_CUTPOINT")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement	
	Integer stkTrailingStopBpCutpoint;
	
		
//	@Column(name = "STK_ENTER_AT_PRICE")
//	@Basic(fetch = FetchType.EAGER)
//	@XmlElement	
//	Integer stkEnterAtPrice;

	/**
	 */
	@OneToMany(mappedBy = "strategy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<com.theta.domain.StrategyAccount> strategyAccounts;
	/**
	 */

	//Calendar TimeTradesEndToday;
	
	/*
	 * MOVED TO STRATEGYACCOUNT
	 */
	/*
	@OneToMany(mappedBy = "strategy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<com.theta.domain.ProfitLoss> profitLosses;
	*/
	
	/**
	 */
	public void setStrategyId(Integer strategyId) {
		this.strategyId = strategyId;
	}

	/**
	 */
	public Integer getStrategyId() {
		return this.strategyId;
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
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 */
	public String getActiveFlag() {
		return this.activeFlag;
	}

	/**
	 */
	public void setInitNumSpreadsCall(Integer initNumSpreadsCall) {
		this.initNumSpreadsCall = initNumSpreadsCall;
	}

	/**
	 */
	public Integer getInitNumSpreadsCall() {
		log.error("*** DEPRECATED - getInitNumSpreadsCall");
		//return this.initNumSpreadsCall;
		return null;
	}

	/**
	 */
	public void setInitNumSpreadsPut(Integer initNumSpreadsPut) {
		log.error("*** DEPRECATED - getInitNumSpreadsPut");
		//this.initNumSpreadsPut = initNumSpreadsPut;
	}

	/**
	 */
	public Integer getInitNumSpreadsPut() {
		return this.initNumSpreadsPut;
	}

	/**
	 */
	public void setPercentEnterLow(Integer percentEnterLow) {
		this.percentEnterLow = percentEnterLow;
	}

	/**
	 */
	public Integer getPercentEnterLow() {
		log.error("*** DEPRECATED - getPercentEnterLow");
		//return this.percentEnterLow;
		return null;
	}

	/**
	 */
	public void setPercentEnterHigh(Integer percentEnterHigh) {
		log.error("*** DEPRECATED - getPercentEnterHigh");
		//this.percentEnterHigh = percentEnterHigh;
	}

	/**
	 */
	public Integer getPercentEnterHigh() {
		return this.percentEnterHigh;
	}

	/**
	 */
	public void setDaysEnterLow(Integer daysEnterLow) {
		this.daysEnterLow = daysEnterLow;
	}

	/**
	 */
	public Integer getDaysEnterLow() {
		return this.daysEnterLow;
	}

	/**
	 */
	public void setDaysEnterHigh(Integer daysEnterHigh) {
		this.daysEnterHigh = daysEnterHigh;
	}

	/**
	 */
	public Integer getDaysEnterHigh() {
		return this.daysEnterHigh;
	}

	/**
	 */
	public void setAmountTotalRisk(Integer amountTotalRisk) {
		this.amountTotalRisk = amountTotalRisk;
	}

	/**
	 */
	public Integer getAmountTotalRisk() {
		log.error("***DEPRECATED - getAmountTotalRisk.");
		//return this.amountTotalRisk;
		return null;
	}

	/**
	 */
	public void setAmountRiskPerPoint(Integer amountRiskPerPoint) {
		this.amountRiskPerPoint = amountRiskPerPoint;
	}

	/**
	 */
	public Integer getAmountRiskPerPoint() {
		log.error("*** DEPRECATED - getAmountRiskPerPoint ***");
		//return this.amountRiskPerPoint;
		return null;
	}

	/**
	 */
	public void setDistanceBetOptions(Integer distanceBetOptions) {
		this.distanceBetOptions = distanceBetOptions;
	}

	/**
	 */
	public Integer getDistanceBetOptions() {
		log.error("*** DEPRECATED - getDistanceBetweenOptinos() ");
		//return this.distanceBetOptions;
		return null;
	}

	/**
	 */
	public void setOptPricesToGet(Integer optPricesToGet) {
		this.optPricesToGet = optPricesToGet;
	}

	/**
	 */
	public Integer getOptPricesToGet() {
		log.error("*** DEPRECATED - getOptPricesToGet");
		//return this.optPricesToGet;
		return null;
	}

	/**
	 */
	public void setPctSetTrailingStop(Integer pctSetTrailingStop) {
		this.pctSetTrailingStop = pctSetTrailingStop;
	}

	/**
	 */
	public Integer getPctSetTrailingStop() {
		log.error("*** DEPRECATED - getPctSetTrailingStop");
		//return this.pctSetTrailingStop;
		return null;
	}

	/**
	 */
	public void setPctTrailingStop(Integer pctTrailingStop) {
		this.pctTrailingStop = pctTrailingStop;
	}

	/**
	 */
	public Integer getPctTrailingStop() {
		log.error("*** DEPRECATED - getPctTrailingStop");

		if (this.pctTrailingStop == null || this.pctTrailingStop.equals(0))
			return 0;

		// Trailing stop must be in BASIS POINTS
		if (this.pctTrailingStop.compareTo(30) < 0)
			log.info("****** WARNING - pctTrailingStop is under .3% - it is in 1/100 of a percent - careful ***********" + this.pctTrailingStop);

		if (this.pctTrailingStop.compareTo(5000) > 0)
			log.info("****** WARNING - pctTrailingStop is over 50% - it is in 1/100 of a percent - careful ***********" + this.pctTrailingStop);
		
		return this.pctTrailingStop;
	}

	/**
	 */
	public void setPctExitWin(Integer pctExitWin) {
		this.pctExitWin = pctExitWin;
	}

	/**
	 */
	public Integer getPctExitWin() {
		log.error("*** DEPRECATED - getPctExitWin");
		return this.pctExitWin;
	}

	/**
	 */
	public void setSetWinTrailingStop(String setWinTrailingStop) {
		this.setWinTrailingStop = setWinTrailingStop;
	}

	/**
	 */
	public String getSetWinTrailingStop() {
		return this.setWinTrailingStop;
	}

	/**
	 */
	public void setSetExitAtInsPrice(String setExitAtInsPrice) {
		this.setExitAtInsPrice = setExitAtInsPrice;
	}

	/**
	 */
	public String getSetExitAtInsPrice() {
		return this.setExitAtInsPrice;
	}

	/**
	 */
	public void setSetReenterOnWin(String setReenterOnWin)  {
		this.setReenterOnWin = setReenterOnWin;
	}

	/**
	 */
	public String getSetReenterOnWin() {
		return this.setReenterOnWin;
	}

	/**
	 */
	public void setSetReenterOnLoss(String setReenterOnLoss) {
		this.setReenterOnLoss = setReenterOnLoss;
	}

	/**
	 */
	public String getSetReenterOnLoss() {
		return this.setReenterOnLoss;
	}

	/**
	 */
	public void setPointDiffForReentry(Integer pointDiffForReentry) {
		this.pointDiffForReentry = pointDiffForReentry;
	}

	/**
	 */
	public Integer getPointDiffForReentry() {
		log.error("*** DEPRECATED - getPointDiffForReentry");
		return this.pointDiffForReentry;
	}

	/**
	 */
	public void setDaysExitBeforeExpiry(Integer daysExitBeforeExpiry) {
		this.daysExitBeforeExpiry = daysExitBeforeExpiry;
	}

	/**
	 */
	public Integer getDaysExitBeforeExpiry() {
		log.error("*** DEPRECATED - getDaysExitBeforeExpiry");
		//return this.daysExitBeforeExpiry;
		return null;
	}

	/**
	 */
	public void setSetBreakevenWhenInsHit(String setBreakevenWhenInsHit) {
		this.setBreakevenWhenInsHit = setBreakevenWhenInsHit;
	}

	/**
	 */
	public String getSetBreakevenWhenInsHit() {
		log.error("*** DEPRECATED - getSetBreakevenWhenInsHit");
		//return this.setBreakevenWhenInsHit;
		return null;
	}

	/**
	 */
	public void setMsWaitForIbResponse(Integer msWaitForIbResponse) {
		this.msWaitForIbResponse = msWaitForIbResponse;
	}

	/**
	 */
	public Integer getMsWaitForIbResponse() {
		return this.msWaitForIbResponse;
	}

	/**
	 */
	public void setMinDelayFromStart(Integer minDelayFromStart) {
		this.minDelayFromStart = minDelayFromStart;
	}

	/**
	 */
	public Integer getMinDelayFromStart() {
		log.error("*** DEPRECATED - getMinDelayFromStart");
		//return this.minDelayFromStart;
		return null;
	}

	/**
	 * TODO: fill this in in the database and model
	 * @return
	 */
	public Integer getMinDelayFromEnd() {
		return new Integer(15);
	}
	
	
	/**
	 */
	public void setSetCheckVix(String setCheckVix) {
		this.setCheckVix = setCheckVix;
	}

	/**
	 */
	public String getSetCheckVix() {
		log.error("*** DEPRECATED - getSetCheckVix");
		//return this.setCheckVix;
		return null;
	}

	/**
	 */
	public void setNoEntryIfVixOver(Integer noEntryIfVixOver) {
		this.noEntryIfVixOver = noEntryIfVixOver;
	}

	/**
	 */
	public Integer getNoEntryIfVixOver() {
		log.error("*** DEPRECATED - getNoEntryIfVixOver");
		//return this.noEntryIfVixOver;
		return null;
	}

	/**
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	/**
	 */
	public String getTimezone() {
		return this.timezone;
	}

	/**
	 */
	public void setLiveOrTestFlag(String liveOrTestFlag) {
		this.liveOrTestFlag = liveOrTestFlag;
	}

	/**
	 */
	public String getLiveOrTestFlag() {
		return this.liveOrTestFlag;
	}

	/**
	 */
	public void setComissionPerTrade(Integer comissionPerTrade) {
		this.comissionPerTrade = comissionPerTrade;
	}

	/**
	 */
	public Integer getComissionPerTrade() {
		return this.comissionPerTrade;
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

	public void setHmTurnOn(boolean hmTurnOn){
		if (hmTurnOn)
			this.hmTurnOn = "T";
		else
			this.hmTurnOn = "F";
	}
	public boolean getHmTurnOn(){
		log.error("*** DEPRECATED - getHmTurnOn");
		if (this.hmTurnOn.equalsIgnoreCase("T"))
			return true;
		else
			return false;
	}
	
	public Integer getHmPctBpDownFromYest() {
		log.error("*** DEPRECATED - getHmPctBpDownFromYest");
		if (hmPctBpDownFromYest == null){
			return ThetaConstants.ZERO_INT;
		}
		
		return hmPctBpDownFromYest;
	}

	public void setHmPctBpDownFromYest(Integer hmPctBpDownFromYest) {
		this.hmPctBpDownFromYest = hmPctBpDownFromYest;
	}

	public Integer getHmPctBpDownFromToday() {
		log.error("*** DEPRECATED - getHmPctBpDownFromToday");
		if (hmPctBpDownFromToday == null){
			return ThetaConstants.ZERO_INT;
		}
		
		return hmPctBpDownFromToday;
	}

	public void setHmPctBpDownFromToday(Integer hmPctBpDownFromToday) {
		this.hmPctBpDownFromToday = hmPctBpDownFromToday;
	}

	public Integer getHmPctBpDownInLast90() {
		log.error("*** DEPRECATED - getHmPctBpDownInLast90");
		if (hmPctBpDownInLast90 == null){
			return ThetaConstants.ZERO_INT;
		}
		return hmPctBpDownInLast90;
	}

	public void setHmPctBpDownInLast90(Integer hmPctBpDownInLast90) {
		this.hmPctBpDownInLast90 = hmPctBpDownInLast90;
	}

	public Integer getHmDaysToExpiry() {
		log.error("*** DEPRECATED - getHmDaysToExpiry");
		//if (hmDaysToExpiry == null){
		//	return ThetaConstants.ZERO_INT;
		//}
		//return hmDaysToExpiry;
		return null;
	}

	public void setHmDaysToExpiry(Integer hmDaysToExpiry) {
		this.hmDaysToExpiry = hmDaysToExpiry;
	}

	public Integer getHmBpLimitUp() {
		log.error("*** DEPRECATED - getHmBpLimitUp");
		//if (hmBpLimitUp == null){
		//	return ThetaConstants.ZERO_INT;
		//}
		//return (hmBpLimitUp * 100);
		return null;
	}

	//public void setHmBpLimitUp(Integer hmBpLimitUp) {
	//	this.hmBpLimitUp = hmBpLimitUp;
	//}

	public Integer getHmBpStopLimitDown() {
		log.error("*** DEPRECATED - getHmBpStopLimitDown");
		//if (hmBpStopLimitDown == null){
		//	return ThetaConstants.ZERO_INT;
		//}
		//return (hmBpStopLimitDown * 100);
		return null;
	}

	public void setHmBpStopLimitDown(Integer hmBpStopLimitDown) {
		this.hmBpStopLimitDown = hmBpStopLimitDown;
	}

	/**
	 */
	public Set<StrategyAccount> getStrategyAccounts() {
		if (strategyAccounts == null) {
			strategyAccounts = new java.util.LinkedHashSet<com.theta.domain.StrategyAccount>();
		}
		return strategyAccounts;
	}

/*
	public Integer getBullMktTrigger() {
		return bullMktTrigger;
	}

	public void setBullMktTrigger(Integer bullMktTrigger) {
		this.bullMktTrigger = bullMktTrigger;
	}

	public void setBullMktFlag(String bullMktFlag) {
		this.bullMktFlag = bullMktFlag;
	}

	public String getBullMktFlag() {
		return bullMktFlag;
	}
	
	public boolean isBullMktFlagSet() {
		if (bullMktFlag.equalsIgnoreCase("T"))
			return true;
		else
			return false;
	}

	public boolean isBullMkt(Integer currentTickerPrice, String _symbol) 
	throws ThetaExceptionExc {
		if (!_symbol.equalsIgnoreCase(this.symbol))
			throw new ThetaExceptionExc("Symbols don't match.  Given: " + _symbol + ". In strategy: " + this.symbol);

		if ( (bullMktTrigger == null) || (bullMktTrigger.equals(0)) ) {
			bullMktFlag = "F";
			return false;
		}

		if (bullMktFlag == null)
			bullMktFlag = "F";
		
		if (bullMktFlag.equalsIgnoreCase("T"))
			return true;
			
		// The current ticker price is greater than the trigger point
		// set the bullMktFlag to True and return true
		if(currentTickerPrice.compareTo(bullMktTrigger) >= 0 ) {
			bullMktFlag = "T";
			Calendar now = Calendar.getInstance();
			updatedDate = now;
			return true;
		}
		
		return false;
	}
	
	public void endBullMarket() {
		bullMktFlag = "F";
		bullMktTrigger = bullMktTrigger + bullMktPointsToAdd;
		Calendar now = Calendar.getInstance();
		updatedDate = now;
	}
	
	public Integer getBullMktDaysToEnd() {
		return bullMktDaysToEnd;
	}

	public Integer getBullMktPointsToAdd() {
		return bullMktPointsToAdd;
	}


	public Integer getBearMktTrigger() {
		return bearMktTrigger;
	}

	public void setBearMktTrigger(Integer bearMktTrigger) {
		this.bearMktTrigger = bearMktTrigger;
	}

	public String getBearMktFlag() {
		log.error("***DEPRECATED - getBearMktFlag.");
		return bearMktFlag;
	}

	public void setBearMktFlag(String bearMktFlag) {
		this.bearMktFlag = bearMktFlag;
	}

	public boolean isBearMktFlagSet() {
		
		log.info("In isBearMktFlagSet - Here is bearMktFlag: " + bearMktFlag);
		log.info("In isBearMktFlagSet - Here is strategy: " + this.toString());
		
		if (bearMktFlag.equalsIgnoreCase("T")){
			log.info("in isBearMktFlagSet - returning TRUE");
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isBearMkt(Integer currentTickerPrice, String _symbol) 
	throws ThetaExceptionExc {
		if (!_symbol.equalsIgnoreCase(this.symbol))
			throw new ThetaExceptionExc("Symbols don't match.  Given: " + _symbol + ". In strategy: " + this.symbol);

		if ( (bearMktTrigger == null) || (bearMktTrigger.equals(0)) ) {
			bearMktFlag = "F";
			bearMktTrigger = 0;
			return false;
		}

		if (bearMktFlag == null)
			bearMktFlag = "F";
		
		if (bearMktFlag.equalsIgnoreCase("T"))
			return true;
			
		// The current ticker price is less than the trigger point
		// set the bearMktFlag to True and return true
		if(currentTickerPrice.compareTo(bearMktTrigger) < 0 ) {
			bearMktFlag = "T";
			Calendar now = Calendar.getInstance();
			updatedDate = now;
			return true;
		}
		
		return false;
	}

	public void endBearMarket() {
		bearMktFlag = "F";
		bearMktTrigger = bearMktTrigger - bearMktPointsToSubtract;
		Calendar now = Calendar.getInstance();
		updatedDate = now;
	}
	
	public Integer getBearMktDaysToEnd() {
 		log.error("***DEPRECATED - getBearMktDaysToEnd.");
		return bearMktDaysToEnd;
	}

	public Integer getBearMktPointsToSubtract() {
		return bearMktPointsToSubtract;
	}
*/

	// Getters and Setters - Added April 2013 for Stock Strategies
	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public LongOrShortCode getLongOrShortCode() {
		if (longOrShortFlag == null){
			return null;
		}
		
		if (longOrShortFlag.equalsIgnoreCase(LongOrShortCode.LONG.toString()))
			return LongOrShortCode.LONG;
		else if (longOrShortFlag.equalsIgnoreCase(LongOrShortCode.SHORT.toString()))
			return LongOrShortCode.SHORT;
		else
			return null;
	}

	public boolean chkLongOrShort() {
		if (longOrShortFlag == null){
			return false;
		}
		
		if (longOrShortFlag.equalsIgnoreCase(LongOrShortCode.LONG.toString())
				||
				longOrShortFlag.equalsIgnoreCase(LongOrShortCode.SHORT.toString())){
			return true;
		}

		return false;
	}
	
	public boolean isLong() {
		if (longOrShortFlag == null)
			return false;
		
		if (longOrShortFlag.equalsIgnoreCase(LongOrShortCode.LONG.toString()))
			return true;
		else
			return false;
	}

	public boolean isShort() {
		if (longOrShortFlag == null)
			return false;

		if (longOrShortFlag.equalsIgnoreCase(LongOrShortCode.SHORT.toString()))
			return true;
		else
			return false;
	}
	
	//public void setLongOrShortFlag(String longOrShortFlag) {
	//	this.longOrShortFlag = longOrShortFlag;
	//}

	public Integer getStkShareBlockSize() {
		log.error("*** DEPRECATED - getStkShareBlockSize");
		//return stkShareBlockSize;
		return null;
	}

	public void setStkShareBlockSize(Integer stkShareBlockSize) {
		log.error("*** DEPRECATED - setStkShareBlockSize");
		//this.stkShareBlockSize = stkShareBlockSize;
	}

	public Integer getStkEnterPointDiff() {
		log.error("*** DEPRECATED - getStkEnterPointDiff");
		//return stkEnterPointDiff;
		return null;
	}

	public void setStkEnterPointDiff(Integer stkEnterPointDiff) {
		this.stkEnterPointDiff = stkEnterPointDiff;
	}

	public Integer getStkExitPointDiff() {
		log.error("*** DEPRECATED - getStkExitPointDiff");
		//return stkExitPointDiff;
		return null;
	}

	public void setStkExitPointDiff(Integer stkExitPointDiff) {
		this.stkExitPointDiff = stkExitPointDiff;
	}

	public Integer getStkPointsForStop() {
		log.error("*** DEPRECATED - getStkPointsForStop");
		//return stkPointsForStop;
		return null;
	}

	public void setStkPtsForStop(Integer stkPointsForStop) {
		this.stkPointsForStop = stkPointsForStop;
	}

	public Integer getStkGoalNumSpreads() {
		return stkGoalNumSpreads;
	}

	public void setStkGoalNumSpreads(Integer stkGoalNumSpreads) {
		this.stkGoalNumSpreads = stkGoalNumSpreads;
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

	public Integer getMovingAvg1() {
		return movingAvg1;
	}

	public void setMovingAvg1(Integer movingAvg1) {
		this.movingAvg1 = movingAvg1;
	}

	public Integer getMovingAvg2() {
		return movingAvg2;
	}

	public void setMovingAvg2(Integer movingAvg2) {
		this.movingAvg2 = movingAvg2;
	}

	public Integer getMovingAvgGracePoints() {
		return movingAvgGracePoints;
	}
	
	public void setMovingAvgGracePoints(Integer movingAvgGracePoints) {
		this.movingAvgGracePoints = movingAvgGracePoints;
	}


	public String getStkStrategyType() {
		return stkStrategyType;
	}

	public void setStkStrategyType(String stkStrategyType) {
		this.stkStrategyType = stkStrategyType;
	}
	
	public Integer getMovingAvgRange() {
		return movingAvgRange;
	}

	public void setMovingAvgRange(Integer movingAvgRange) {
		this.movingAvgRange = movingAvgRange;
	}

	public Integer getStkTrailingStopBpTimesRange() {
		return stkTrailingStopBpTimesRange;
	}

	public void setStkTrailingStopBpTimesRange(Integer stkTrailingStopBpTimesRange) {
		this.stkTrailingStopBpTimesRange = stkTrailingStopBpTimesRange;
	}

	public Integer getStkReenterBpTimesRange() {
		return stkReenterBpTimesRange;
	}

	public void setStkReenterBpTimesRange(Integer stkReenterBpTimesRange) {
		this.stkReenterBpTimesRange = stkReenterBpTimesRange;
	}

	public Integer getMovingAvgTolerancePct() {
		return movingAvgTolerancePct;
	}

	public Integer getSlopeTimeMin1() {
		return slopeTimeMin1;
	}

	public Integer getSlopeAdrTbpPerMin1() {
		return slopeAdrTbpPerMin1;
	}

	public Integer getSlopeTimeMin2() {
		return slopeTimeMin2;
	}

	public Integer getSlopeAdrTbpPerMin2() {
		return slopeAdrTbpPerMin2;
	}
	public Integer getSlopeTimeMin3() {
		return slopeTimeMin3;
	}
	public Integer getSlopeAdrTbpPerMin3() {
		return slopeAdrTbpPerMin3;
	}
	public Calendar getEarningsReportNext() {
		if (earningsReportNext == null) {
			return ThetaConstants.EARLY_DATE;
		} else {
			return earningsReportNext;
		}
	}
	public void setEarningsReportNext(Calendar earningsReportNext){
		this.earningsReportNext = earningsReportNext;
	}
	
	public String getEarningsReportAction() {
		return this.earningsReportAction;
	}

	public boolean honorMovingAvg() {
		if (this.honorMovingAvg == null) return true;
		if (this.honorMovingAvg.equalsIgnoreCase("F")) return false;
		return true;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		if (warning.length() > 150){
			warning = warning.substring(0,150);
		}
		this.warning = warning;
	}

	public Integer getStkTrailingStopBpTimesRange2() {
		return stkTrailingStopBpTimesRange2;
	}

	public Integer getStkTrailingStopBpCutpoint() {
		return stkTrailingStopBpCutpoint;
	}
	

	//	public Integer getStkEnterAtPrice() {
//		return stkEnterAtPrice;
//	}
//
//	public void setStkEnterAtPrice(Integer stkEnterAtPrice) {
//		this.stkEnterAtPrice = stkEnterAtPrice;
//	}

	
	/**
	 * @return the timeTradesEndToday
	 */
	//public Calendar getTimeTradesEndToday() {
	//	return TimeTradesEndToday;
	//}

	/**
	 * @param timeTradesEndToday the timeTradesEndToday to set
	 * This does not persist
	 */
	//public void setTimeTradesEndToday(Calendar timeTradesEndToday) {
	//	TimeTradesEndToday = timeTradesEndToday;
	//}

	/**
	 */
	/*
	 * MOVED TO STRATEGYACCOUNT:
	 */
	/*
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
	public Strategy() {
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Strategy [strategyId=");
		builder.append(strategyId);
		builder.append(", strategyName=");
		builder.append(strategyName);
		builder.append(", symbol=");
		builder.append(symbol);
		builder.append(", activeFlag=");
		builder.append(activeFlag);
		builder.append(", initNumSpreadsCall=");
		builder.append(initNumSpreadsCall);
		builder.append(", initNumSpreadsPut=");
		builder.append(initNumSpreadsPut);
		builder.append(", percentEnterLow=");
		builder.append(percentEnterLow);
		builder.append(", percentEnterHigh=");
		builder.append(percentEnterHigh);
		builder.append(", daysEnterLow=");
		builder.append(daysEnterLow);
		builder.append(", daysEnterHigh=");
		builder.append(daysEnterHigh);
		builder.append(", amountTotalRisk=");
		builder.append(amountTotalRisk);
		builder.append(", amountRiskPerPoint=");
		builder.append(amountRiskPerPoint);
		builder.append(", distanceBetOptions=");
		builder.append(distanceBetOptions);
		builder.append(", optPricesToGet=");
		builder.append(optPricesToGet);
		builder.append(", pctSetTrailingStop=");
		builder.append(pctSetTrailingStop);
		builder.append(", pctTrailingStop=");
		builder.append(pctTrailingStop);
		builder.append(", pctExitWin=");
		builder.append(pctExitWin);
		builder.append(", setWinTrailingStop=");
		builder.append(setWinTrailingStop);
		builder.append(", setExitAtInsPrice=");
		builder.append(setExitAtInsPrice);
		builder.append(", setReenterOnWin=");
		builder.append(setReenterOnWin);
		builder.append(", setReenterOnLoss=");
		builder.append(setReenterOnLoss);
		builder.append(", pointDiffForReentry=");
		builder.append(pointDiffForReentry);
		builder.append(", daysExitBeforeExpiry=");
		builder.append(daysExitBeforeExpiry);
		builder.append(", setBreakevenWhenInsHit=");
		builder.append(setBreakevenWhenInsHit);
		builder.append(", msWaitForIbResponse=");
		builder.append(msWaitForIbResponse);
		builder.append(", minDelayFromStart=");
		builder.append(minDelayFromStart);
		builder.append(", setCheckVix=");
		builder.append(setCheckVix);
		builder.append(", noEntryIfVixOver=");
		builder.append(noEntryIfVixOver);
		builder.append(", timezone=");
		builder.append(timezone);
		builder.append(", liveOrTestFlag=");
		builder.append(liveOrTestFlag);
		builder.append(", comissionPerTrade=");
		builder.append(comissionPerTrade);
		builder.append(", createdBy=");
		builder.append(createdBy );
		builder.append(", createdDate=");
		builder.append(createdDate.getTime());
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append(", updatedDate=");
		builder.append(updatedDate.getTime());
		builder.append(", hmPctBpDownFromYest=");
		builder.append(hmPctBpDownFromYest);
		builder.append(", hmPctBpDownFromToday=");
		builder.append(hmPctBpDownFromToday);
		builder.append(", hmPctBpDownInLast90=");
		builder.append(hmPctBpDownInLast90);
		builder.append(", hmDaysToExpiry=");
		builder.append(hmDaysToExpiry);
		builder.append(", hmBpLimitUp=");
		builder.append(hmBpLimitUp);
		builder.append(", hmBpStopLimitDown=");
		builder.append(hmBpStopLimitDown);
		builder.append(", bullMktTrigger=");
		builder.append(bullMktTrigger);
		builder.append(", bullMktFlag=");
		builder.append(bullMktFlag);
		builder.append(", bearMktTrigger=");
		builder.append(bearMktTrigger);
		builder.append(", bearMktFlag=");
		builder.append(bearMktFlag);
		builder.append(", instrument=");
		builder.append(instrument);
		builder.append(", longOrShortFlag=");
		builder.append(longOrShortFlag);
		builder.append(", stkShareBlockSize=");
		builder.append(stkShareBlockSize);
		builder.append(", stkEnterPointDiff=");
		builder.append(stkEnterPointDiff);
		builder.append(", stkExitPointDiff=");
		builder.append(stkExitPointDiff);
		builder.append(", stkPointsForStop=");
		builder.append(stkPointsForStop);
		builder.append(", stkGoalNumSpreads=");
		builder.append(stkGoalNumSpreads);
		builder.append(", movingAvg1=");
		builder.append(movingAvg1);
		builder.append(", movingAvg2=");
		builder.append(movingAvg2);
		builder.append(", movingAvgGracePoints=");
		builder.append(movingAvgGracePoints);
		builder.append(", stkStrategyType=");
		builder.append(stkStrategyType);
		builder.append(", movingAvgRange=");
		builder.append(movingAvgRange);
		builder.append(", stkTrailingStopBpTimesRange=");
		builder.append(stkTrailingStopBpTimesRange);
		builder.append(", stkReenterBpTimesRange=");
		builder.append(stkReenterBpTimesRange);
		builder.append(", earningsReportNext=");
		if (earningsReportNext != null) builder.append(earningsReportNext.getTime());
		builder.append("]");
		return builder.toString();
	}
	
	
	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((strategyId == null) ? 0 : strategyId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Strategy))
			return false;
		Strategy equalCheck = (Strategy) obj;
		if ((strategyId == null && equalCheck.strategyId != null) || (strategyId != null && equalCheck.strategyId == null))
			return false;
		if (strategyId != null && !strategyId.equals(equalCheck.strategyId))
			return false;
		return true;
	}
}

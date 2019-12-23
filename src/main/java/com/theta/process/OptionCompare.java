package com.theta.process;

import java.util.Calendar;

import com.ib.client.Contract;

public class OptionCompare {
	
	// Matching
	private boolean match = false;

	private Integer optionCompareId;
	private Integer matchOptionCompareId;
	
	// Identification (both)
	private boolean printToFile = false;
	private String accountName;
	private String symbol;
	private String expMonth;
	private String expYear;
	private Integer strikePrice;
	private String right;
	private String conId;
	private Integer position;
	
	// Internal Identification (only)
	private String intMmOrIns;
	private Integer intSpreadId;
	private String intOpenOrClosed;

	// External (per position!)
	/*
	double averageCost = 118.9855;
	double marketPrice = 2.470;
	double marketValue = -247.00;
	int position = -2;
	double realizedPNL = 0.0;
	double unrealizedPNL = -9.02;
	String accountName = "DU71402";
	*/
	private Integer extAverageCost = ThetaConstants.ZERO_INT;
	private Integer extMarketPrice = ThetaConstants.ZERO_INT;
	//private double extMarketValuePerPosn = ThetaConstants.ZERO_INT;
	//private double extRealizedPNLPerPosn = ThetaConstants.ZERO_INT;
	private Integer extUnrealizedPNLPerPosn = ThetaConstants.ZERO_INT;

	// Internal (per position!)
	private Integer intEnterSecPrice = ThetaConstants.ZERO_INT;
	private Integer intEnterPrice = ThetaConstants.ZERO_INT;
	private Integer intCurrentPrice = ThetaConstants.ZERO_INT;
	private Integer intCommission = ThetaConstants.ZERO_INT;
	private Calendar intEnterTriggerDt;
	
	// Matching/Diff Fields
	//private Integer diffCurrentPrice = ThetaConstants.ZERO_INT;
	//private Integer diffEnterPrice = ThetaConstants.ZERO_INT;
	//private Integer diffUnrealizedPNL = ThetaConstants.ZERO_INT;
	//private Integer extEnterPriceI = ThetaConstants.ZERO_INT;
	//private Integer extMarketPricePerPosnI = ThetaConstants.ZERO_INT;
	//private Integer intUnrealizedPNL = ThetaConstants.ZERO_INT;
	//private Integer extUnrealizedPNLI = ThetaConstants.ZERO_INT;
		
	/**
	 * @return the match
	 */
	public boolean isMatch() {
		return match;
	}
	/**
	 * @param match the match to set
	 */
	public void setMatch(boolean match) {
		this.match = match;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	public void setPrintToFile() {
		this.printToFile = true;
	}
	public boolean getPrintToFile(){
		return printToFile;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * @return the expMonth
	 */
	public String getExpMonth() {
		return expMonth;
	}
	/**
	 * @param expMonth the expMonth to set
	 */
	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}
	/**
	 * @return the expYear
	 */
	public String getExpYear() {
		return expYear;
	}
	/**
	 * @param expYear the expYear to set
	 */
	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}
	/**
	 * @return the strikePrice
	 */
	public Integer getStrikePrice() {
		return strikePrice;
	}
	/**
	 * @param strikePrice the strikePrice to set
	 */
	public void setStrikePrice(Integer strikePrice) {
		this.strikePrice = strikePrice;
	}
	/**
	 * @return the right
	 */
	public String getRight() {
		return right;
	}
	/**
	 * @param right the right to set
	 */
	public void setRight(String right) {
		this.right = right;
	}
	/**
	 * @return the conId
	 */
	public String getConId() {
		return conId;
	}
	/**
	 * @param conId the conId to set
	 */
	public void setConId(String conId) {
		this.conId = conId;
	}
	/**
	 * @return the position
	 */
	public Integer getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}
	/**
	 * @return the intMmOrIns
	 */
	public String getIntMmOrIns() {
		return intMmOrIns;
	}
	/**
	 * @param intMmOrIns the intMmOrIns to set
	 */
	public void setIntMmOrIns(String intMmOrIns) {
		this.intMmOrIns = intMmOrIns;
	}
	/**
	 * @return the intSpreadId
	 */
	public Integer getIntSpreadId() {
		return intSpreadId;
	}
	/**
	 * @param intSpreadId the intSpreadId to set
	 */
	public void setIntSpreadId(Integer intSpreadId) {
		this.intSpreadId = intSpreadId;
	}
	/**
	 * @return the intOpenOrClosed
	 */
	public String getIntOpenOrClosed() {
		return intOpenOrClosed;
	}
	/**
	 * @param intOpenOrClosed the intOpenOrClosed to set
	 */
	public void setIntOpenOrClosed(String intOpenOrClosed) {
		this.intOpenOrClosed = intOpenOrClosed;
	}
	/**
	 * @return the extAverageCost
	 */
	public Integer getExtAverageCost() {
		return extAverageCost;
	}
	/**
	 * @param extAverageCost the extAverageCost to set
	 */
	public void setExtAverageCost(Integer extAverageCost) {
		this.extAverageCost = extAverageCost;
	}
	/**
	 * @return the extMarketPricePerPosn
	 */
	public Integer getExtMarketPrice() {
		return extMarketPrice;
	}
	/**
	 * @param extMarketPricePerPosn the extMarketPricePerPosn to set
	 */
	public void setExtMarketPrice(Integer extMarketPrice) {
		this.extMarketPrice = extMarketPrice;
	}
	/**
	 * @return the extMarketValuePerPosn
	 */
	//public double getExtMarketValuePerPosn() {
	//	return extMarketValuePerPosn;
	//}
	/**
	 * @param extMarketValuePerPosn the extMarketValuePerPosn to set
	 */
	//public void setExtMarketValuePerPosn(double extMarketValuePerPosn) {
	//	this.extMarketValuePerPosn = extMarketValuePerPosn;
	//}
	/**
	 * @return the extRealizedPNLPerPosn
	 */
	//public double getExtRealizedPNLPerPosn() {
	//	return extRealizedPNLPerPosn;
	//}
	/**
	 * @param extRealizedPNLPerPosn the extRealizedPNLPerPosn to set
	 */
	//public void setExtRealizedPNLPerPosn(double extRealizedPNLPerPosn) {
	//	this.extRealizedPNLPerPosn = extRealizedPNLPerPosn;
	//}
	/**
	 * @return the extUnrealizedPNLPerPosn
	 */
	public Integer getExtUnrealizedPNLPerPosn() {
		return extUnrealizedPNLPerPosn;
	}
	/**
	 * @param extUnrealizedPNLPerPosn the extUnrealizedPNLPerPosn to set
	 */
	public void setExtUnrealizedPNLPerPosn(Integer extUnrealizedPNLPerPosn) {
		this.extUnrealizedPNLPerPosn = extUnrealizedPNLPerPosn;
	}
	/**
	 * @return the intEnterSecPrice
	 */
	public Integer getIntEnterSecPrice() {
		return intEnterSecPrice;
	}
	/**
	 * @param intEnterSecPrice the intEnterSecPrice to set
	 */
	public void setIntEnterSecPrice(Integer intEnterSecPrice) {
		this.intEnterSecPrice = intEnterSecPrice;
	}
	/**
	 * @return the intEnterPrice
	 */
	public Integer getIntEnterPrice() {
		return intEnterPrice;
	}
	/**
	 * @param intEnterPrice the intEnterPrice to set
	 */
	public void setIntEnterPrice(Integer intEnterPrice) {
		this.intEnterPrice = intEnterPrice;
	}
	/**
	 * @return the intCurrentPrice
	 */
	public Integer getIntCurrentPrice() {
		return intCurrentPrice;
	}
	/**
	 * @param intCurrentPrice the intCurrentPrice to set
	 */
	public void setIntCurrentPrice(Integer intCurrentPrice) {
		this.intCurrentPrice = intCurrentPrice;
	}
	/**
	 * @return the intCommission
	 */
	public Integer getIntCommission() {
		return intCommission;
	}
	/**
	 * @param intCommission the intCommission to set
	 */
	public void setIntCommission(Integer intCommission) {
		this.intCommission = intCommission;
	}
	/**
	 * @return the intEnterTriggerDt
	 */
	public Calendar getIntEnterTriggerDt() {
		return intEnterTriggerDt;
	}
	/**
	 * @param intEnterTriggerDt the intEnterTriggerDt to set
	 */
	public void setIntEnterTriggerDt(Calendar intEnterTriggerDt) {
		this.intEnterTriggerDt = intEnterTriggerDt;
	}

	public Integer getOptionCompareId() {
		return optionCompareId;
	}
	public void setOptionCompareId(Integer optionCompareId) {
		this.optionCompareId = optionCompareId;
	}
	public Integer getMatchOptionCompareId() {
		return matchOptionCompareId;
	}
	public void setMatchOptionCompareId(Integer matchOptionCompareId) {
		this.matchOptionCompareId = matchOptionCompareId;
	}
	
	public static String headerString() {
		return("MATCH-IB-DB,id,matchId,match,accountName,symbol,conId,expMonth,expYear,strike,right,position,MMorINS,spreadID,openOrClosed,DBEnter,IBAvgCost,DBCurrent,IBMarketPrice,DBPNL,IBUnrealizedPNL,DIFFPNL");
	}
	
	
	public boolean isCounterPosition(OptionCompare that){
		if (this.symbol.equals(that.getSymbol()) &&
				this.strikePrice.equals(that.getStrikePrice()) &&
				this.expMonth.equals(that.getExpMonth()) &&
				this.expYear.equals(that.getExpYear()) &&
				this.right.equals(that.getRight()) &&
				this.intOpenOrClosed.equals(that.getIntOpenOrClosed()) &&
				this.position.equals(-1 * that.getPosition()) &&
				(that.match == false) )
			return true;
		else
			return false;	
	}
	
	
	@Override
	public String toString() {

		/*
		*** BALANCE AGAINST REALITY ***
		NO MATCH - FROM IB:
		NO MATCH - FROM DB:
		MATCHES:
		accountName,symbol,expMonth,expYear,strike,right,position,MMorINS,spreadID,openOrClosed,DBEnter,IBAvgCost,DBCurrent,IBMarketPrice,DBPNL,IBUnrealizedPNL,DIFFPNL
		DU3454,SPY,07,2011,130,P,-1,MM,14500,OPEN,247,250,300,302,50,53
		*/
		
		/**
		 * + is more $$ in the bank
		 * - is less $$ in the bank
		 * 
		 * IF the position is -1 (short) - then this is reversed
		 */
		Integer intUnrealizedPNL = ( position * (intCurrentPrice - intEnterPrice) );	
		Integer diffUnrealizedPNL = intUnrealizedPNL - extUnrealizedPNLPerPosn;
		
		//(",id,matchId,match,accountName,symbol,conId,expMonth,expYear,strike,right,position,MMorINS,spreadID,openOrClosed,DBEnter,IBEnter,DBCurrent,IBCurrent,DBPNL,IBPNL,DIFFPNL");
		StringBuilder builder = new StringBuilder();
		builder.append(optionCompareId + ",");
		builder.append(matchOptionCompareId + ",");
		if (match){builder.append("TRUE" + ",");} else {builder.append("FALSE" + ",");} 
		builder.append(accountName + ",");
		builder.append(symbol + ",");
		builder.append(conId + ",");
		builder.append(expMonth + ",");
		builder.append(expYear + ",");
		builder.append(strikePrice + ",");
		builder.append(right + ",");
		builder.append(position + ",");
		builder.append(intMmOrIns + ",");
		builder.append(intSpreadId + ",");
		builder.append(intOpenOrClosed + ",");
		builder.append(intEnterPrice + ",");
		builder.append(extAverageCost + ",");
		builder.append(intCurrentPrice + ",");
		builder.append(extMarketPrice + ",");
		builder.append(intUnrealizedPNL + ",");
		builder.append(extUnrealizedPNLPerPosn + ",");
		builder.append(diffUnrealizedPNL);
		return builder.toString();
	}

}

package com.theta.process;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.theta.enums.LongOrShortCode;

import org.apache.log4j.Logger;

import com.theta.domain.Position;
import com.theta.domain.Spread;

public class SpreadSnapshot {

	private static Logger logger = Logger.getLogger(SpreadSnapshot.class);

	String spreadId;
	String symbol;
	String strategyName;
	Integer expMonth;
	Integer expYear;
	String mmIns;
	String optRight;
	String openOrClosed;
	Integer enterCost;
	Integer enterMMPrice;
	Integer enterInsPrice;
	Integer currCost;
	Integer currMMPrice;
	Integer currInsPrice;
	Integer exitCost;
	Integer exitMMPrice;
	Integer exitInsPrice;
	Calendar enterDate;
	Calendar exitMMDate;
	Calendar exitInsDate;
	Integer enterSecPrice;
	Integer currSecPrice;
	Integer exitSecPrice;
	Integer stkNumShares;
	LongOrShortCode longOrShortCode;
	String instrument;
	Date createdDate = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm");


	public SpreadSnapshot() {}

	public SpreadSnapshot(Spread spread, Position position, LongOrShortCode longOrShortCode){

		if (position.isStock()){
			stkNumShares = spread.getStkNumShares();
		} else {
			stkNumShares = 1;
		}
		
		spreadId = spread.getSpreadId().toString();
		strategyName = position.getStrategyName();
		expMonth = position.getExpiryMonth();
		expYear = position.getExpiryYear();
		Double strikeMoneymkr = new Double(spread.getStrikeMoneymkr())/100;
		Double strikeInsurance = new Double(spread.getStrikeInsurance())/100;
		mmIns = strikeMoneymkr.intValue() + "-"  + strikeInsurance.intValue();
		optRight = position.getOptRight();
		openOrClosed = spread.getOpenOrClosed();
		enterMMPrice = (spread.getEnterMoneymkrPrice() * stkNumShares)/100;
		enterInsPrice = (spread.getEnterInsurancePrice()* stkNumShares)/100;
		enterCost = enterMMPrice - enterInsPrice;

		currMMPrice = (spread.getCurrentMoneymkrPrice() * stkNumShares)/100;
		currInsPrice = (spread.getCurrentInsurancePrice() * stkNumShares)/100;
		currCost = currMMPrice - currInsPrice;

		exitMMPrice = (spread.getExitMoneymkrPrice() * stkNumShares)/100;
		exitInsPrice = (spread.getExitInsurancePrice() * stkNumShares)/100;
		exitCost = exitMMPrice - exitInsPrice; 

		enterDate = spread.getEnterMoneymkrDate();
		exitMMDate = spread.getExitTriggerDate();
		exitInsDate = spread.getExitTriggerDate();
		
		enterSecPrice = spread.getEnterSecurityPrice();
		currSecPrice = spread.getCurrentSecurityPrice();
		exitSecPrice = spread.getExitSecurityPrice();
		this.longOrShortCode = longOrShortCode;
		createdDate = new Date();
		instrument = position.getInstrument();
		
		if (position.isStock()){
			if (longOrShortCode.equals(LongOrShortCode.LONG)) {
				enterCost = -enterCost;
				currCost = -currCost;
				exitCost = -exitCost;
			} else if (longOrShortCode.equals(LongOrShortCode.SHORT)){
			}
			optRight = "STK";
			mmIns = "--";
		}
	}

	public boolean writeToFile(String theFileName){
		//String theFileName = "SpreadSnapshot.csv";
		boolean ret = ThetaUtil.writeToFile(this.toCSV(), theFileName);
		if (!ret) {
			logger.error("Problem writing to file: " + theFileName);
			return false;
		}
		return true;
	}

	public static String toHeaderCSV() {
		StringBuffer sb = new StringBuffer();

		sb.append("spreadId,strategyName, expMonth,expYear,mmIns,optRight,openOrClosed,");
		sb.append("enterCost,enterMMPrice,enterInsPrice,currCost,currMMPrice,currInsPrice,");
		sb.append("exitCost,exitMMPrice,exitInsPrice,enterSecPrice,currSecPrice,exitSecPrice,createdDate\n");
		return sb.toString();
	}

	public String toCSV() {
		StringBuffer sb = new StringBuffer();
		sb.append(spreadId + ",");
		sb.append(strategyName + ",");
		sb.append(expMonth + ",");
		sb.append(expYear + ",");
		sb.append(mmIns + ",");
		sb.append(optRight + ",");
		sb.append(openOrClosed + ",");
		sb.append(toDollars(enterCost) + ",");
		sb.append(toDollars(enterMMPrice) + ",");
		sb.append(toDollars(enterInsPrice) + ",");
		sb.append(toDollars(currCost) + ",");
		sb.append(toDollars(currMMPrice) + ",");
		sb.append(toDollars(currInsPrice) + ",");
		sb.append(toDollars(exitCost) + ",");
		sb.append(toDollars(exitMMPrice) + ",");
		sb.append(toDollars(exitInsPrice) + ",");

		String dummyDate = "1980.01.01-12:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm");
		if (enterDate != null)
			sb.append(sdf.format(enterDate.getTime()) + ",");
		else
			sb.append(dummyDate + ",");
		
		if (exitMMDate != null)
			sb.append(sdf.format(exitMMDate.getTime()) + ",");
		else
			sb.append(dummyDate + ",");
		
		if (exitInsDate != null)
			sb.append(sdf.format(exitInsDate.getTime()) + ",");
		else
			sb.append(dummyDate + ",");

		sb.append(toDollars(enterSecPrice) + ",");
		sb.append(toCents(currSecPrice) + ",");
		sb.append(toDollars(exitSecPrice) + ",");
		sb.append(sdf.format(createdDate) + ",");
		sb.append(longOrShortCode.toString() + ",");
		sb.append(instrument);
		
		return sb.toString();
	}

	public String toCents(Integer theInt){
		if (theInt == null)
			return "null";
		
		Double theDoub = new Double(theInt);
		theDoub = theDoub / 100;
		return(theDoub.toString());
	}

	public String toDollars(Integer theInt){
		if (theInt == null)
			return "null";
		
		return(theInt.toString());
	}

	@Override
	public String toString() {
		return "SpreadSnapshot [spreadId=" + spreadId + ", strategyName="
				+ strategyName + ", expMonth=" + expMonth + ", expYear="
				+ expYear + ", mmIns=" + mmIns + ", optRight=" + optRight
				+ ", openOrClosed=" + openOrClosed + ", enterCost=" + enterCost
				+ ", enterMMPrice=" + enterMMPrice + ", enterInsPrice="
				+ enterInsPrice + ", currCost=" + currCost + ", currMMPrice="
				+ currMMPrice + ", currInsPrice=" + currInsPrice
				+ ", exitCost=" + exitCost + ", exitMMPrice=" + exitMMPrice
				+ ", exitInsPrice=" + exitInsPrice + ", enterSecPrice="
				+ enterSecPrice + ", currSecPrice=" + currSecPrice
				+ ", exitSecPrice=" + exitSecPrice + ", createdDate="
				+ sdf.format(createdDate) + 
				"longOrShortCode =" + longOrShortCode +
				"instrument =" + instrument + "]";
	}

	public static void main(String[] args) {
		SpreadSnapshot ss = new SpreadSnapshot();
		Integer in = new Integer(567);
		System.out.println(ss.toCents(in));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm");
		System.out.println(sdf.format(new Date()));

		System.out.println(ss.toHeaderCSV());
	}


}

package com.theta.process;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.theta.domain.ProfitLoss;
import com.theta.domain.Strategy;

public class ProfitLossSnapshot {
	
	private static Logger logger = Logger.getLogger(ProfitLossSnapshot.class);
	
	String strategyName = "";
	String symbol = "";
	Integer strategyAccountId = 0;
	Integer PLRealized = 0;
	Integer PLUnrealized = 0;
	Integer totalRisked = 0;
	Integer symbolPrice = 0;
	Integer vixPrice = 0;
	Date createdDate = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm");
	
	public ProfitLossSnapshot(){}
	
	public ProfitLossSnapshot(ProfitLoss pl) {
		try {
			strategyAccountId = pl.getStrategyAccount().getStrategyAccountId();
			PLRealized = pl.getPlRealized();
			PLUnrealized = pl.getPlUnrealized();
			totalRisked = pl.getTotalRisked();
			symbolPrice = pl.getSymbolPrice();
			vixPrice = pl.getVixPrice();
			createdDate = pl.getCreatedDate().getTime();
	
			Strategy st = pl.getStrategyAccount().getStrategy();
			strategyName = st.getStrategyName();
			symbol = st.getSymbol();
		} catch (Exception ex) {
			logger.error("Problem filling this object (ProfitLossSnapshot)", ex);
		}
	}
	
	public boolean writeToFile(String theFileName){
		//String theFileName = "ProfitLossSnapshot.csv";
		boolean ret = ThetaUtil.writeToFile(this.toCSV(), theFileName);
		if (!ret) {
			logger.error("Problem writing to file: " + theFileName);
			return false;
		}
		return true;
	}
	
	public static String toHeaderCSV() {
		StringBuffer sb = new StringBuffer();
		sb.append("strategyName,symbol,strategyAccountId,PLRealized,PLUnrealized," +
				"totalRisked,symbolPrice,vixPrice,createdDate\n");
		return sb.toString();
	}
	
	public String toCSV() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(strategyAccountId + ",");
		sb.append(strategyName + ",");
		sb.append(symbol + ",");
		sb.append(toCents(PLRealized) + ",");
		sb.append(toCents(PLUnrealized) + ",");
		sb.append(toCents(totalRisked) + ",");
		sb.append(symbolPrice + ",");
		sb.append(vixPrice + ",");
		sb.append(sdf.format(createdDate) + ",");
		return sb.toString();
	}

	public String toCents(Integer theInt){
		Double theDoub = new Double(theInt);
		theDoub = theDoub / 100;
		return(theDoub.toString());
	}

	
	@Override
	public String toString() {
		return "ProfitLossSnapshot [strategyName=" + strategyName
				+ ", strategyAccountId=" + strategyAccountId
				+ ", PLRealized=" + PLRealized + ", PLUnrealized="
				+ PLUnrealized + ", totalRisked=" + totalRisked
				+ ", symbolPrice=" + symbolPrice + ", vixPrice=" + vixPrice
				+ ", createdDate=" + sdf.format(createdDate)  + "]";
	}

	public static void main(String[] args) {
		ProfitLossSnapshot ss = new ProfitLossSnapshot();
		Integer in = new Integer(567);
		System.out.println(ss.toCents(in));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm");
		System.out.println(sdf.format(new Date()));
		
		System.out.println(ss.toHeaderCSV());
	}

	
}

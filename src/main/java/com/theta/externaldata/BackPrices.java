package com.theta.externaldata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.theta.dao.SecurityPriceDAO;
import com.theta.domain.SecurityPrice;
import com.theta.process.ThetaExceptionExc;
import com.theta.process.ThetaMutex;

/**
 * 
 * @author pcion Retrieves back prices from an external source (i.e. Yahoo
 *         restful service)
 */

public class BackPrices {

	private static Logger log = Logger.getLogger(BackPrices.class);

	private String baseStr = "http://real-chart.finance.yahoo.com/table.csv?s=";

	public void fillBackPrices(String symbol, Integer numDaysToFill, SecurityPriceDAO spDAO, String testFile, ThetaMutex dbAccessMutex) throws ThetaExceptionExc {
		
		log.info("in fillBackPrices. Symbol, daysToFill: " + symbol + ", " + numDaysToFill);
		if (testFile == null) testFile = "URL";
		String requestStr = fillRequestStr(symbol, numDaysToFill, null);
		log.info("requestStr: " + requestStr);
		List<Price> priceList = getAndParsePrices(requestStr, testFile);
		fillDBFromList(spDAO, priceList, symbol, dbAccessMutex);
	}
	
	protected String fillRequestStr(String symbol, Integer numDaysToFill, Calendar now) {

		if (now == null) {
			now = Calendar.getInstance();
		}
		Integer nowYear = now.get(Calendar.YEAR);
		Integer nowMonth = now.get(Calendar.MONTH);
		Integer nowDate = now.get(Calendar.DATE);

		Calendar past = (Calendar) now.clone();
		past.add(Calendar.DATE, -numDaysToFill);
		Integer pastYear = past.get(Calendar.YEAR);
		Integer pastMonth = past.get(Calendar.MONTH);
		Integer pastDate = past.get(Calendar.DATE);

		// Sample:
		// http://real-chart.finance.yahoo.com/table.csv?s=TEST&d=7&e=27&f=2014&g=d&a=4&b=16&c=2014&ignore=.csv
		String requestStr = baseStr + symbol + "&d=" + nowMonth + "&e="
				+ nowDate + "&f=" + nowYear + "&g=d&a=" + pastMonth + "&b="
				+ pastDate + "&c=" + pastYear + "&ignore=.csv";

		return requestStr;
	}

	protected List<Price> getAndParsePrices(String requestStr, String testFile) throws ThetaExceptionExc {
		List<Price> listOfPrices = new ArrayList<Price>();
		try {
			BufferedReader in = getReaderFromUrl(requestStr, testFile);
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				String[] inputStrArr = inputLine.split(",");
				// Date,Open,High,Low,Close,Volume,Adj Close
				// 2014-08-26,337.00,344.36,334.55,341.83,3638200,341.83
				if (!inputStrArr[0].equalsIgnoreCase("Date")) {
					log.info("Prices: " + inputLine);
					Price p = new Price();
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cal.setTime(sdf.parse(inputStrArr[0]));
					p.theDate = cal;
					p.theOpen =  dollarStringToIntCents(inputStrArr[1]);
					p.theHigh = dollarStringToIntCents(inputStrArr[2]);
					p.theLow = dollarStringToIntCents(inputStrArr[3]);
					p.theClose = dollarStringToIntCents(inputStrArr[4]);
					p.theVolume = new Integer(inputStrArr[5]);
					p.theAdjClose = dollarStringToIntCents(inputStrArr[6]);
					listOfPrices.add(p);
				}
			}
			in.close();
		} catch (MalformedURLException e) {
			log.error("Malformed URL.", e);
			throw new ThetaExceptionExc("Malformed URL: " + e);
		} catch (ParseException pex) {
			log.error("ParseException.", pex);
			throw new ThetaExceptionExc("Parse Excpetion: " + pex);
		} catch (IOException ioe) {
			log.error("IOException.", ioe);
			throw new ThetaExceptionExc("IO Problem: " + ioe);
		}

		return listOfPrices;
	}

	protected BufferedReader getReaderFromUrl(String urlString, String testFile)
			throws IOException {
		// For Testing
		if (!testFile.equalsIgnoreCase("URL")) {
			if (testFile.equalsIgnoreCase("EXC")) {
				throw new MalformedURLException("test MalformedURLException");
			}

			BufferedReader in = new BufferedReader(new FileReader(testFile));
			return in;
		}

		URL theUrl = new URL(urlString);
		URLConnection urlConnection = theUrl.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		return in;
	}

	protected void fillDBFromList(SecurityPriceDAO spDAO, List<Price> priceList, String symbol, ThetaMutex dbAccessMutex) {

		for (Price price : priceList) {
			log.info("filling SecurityPriceDAO with : " + price);
			SecurityPrice spHi = new SecurityPrice();
			spHi.setCreatedDate(price.theDate);
			spHi.setTicker(symbol);
			spHi.setPrice(price.theHigh);
			spHi.setUsed("Y");
			spHi.setSource("YAHOO");
			synchronized(dbAccessMutex) {spDAO.store(spHi);};
			
			SecurityPrice spLo = new SecurityPrice();
			spLo.setCreatedDate(price.theDate);
			spLo.setTicker(symbol);
			spLo.setPrice(price.theLow);
			spLo.setUsed("Y");
			spLo.setSource("YAHOO");
			synchronized(dbAccessMutex) {spDAO.store(spLo);}
		}

		synchronized(dbAccessMutex) {spDAO.flush();}
	}


	protected static Integer dollarStringToIntCents(String dollarString) {
		Float theFloat = new Float(dollarString); 
		Float theFloatTimesHundred = theFloat * 100;
		Integer theInt = theFloatTimesHundred.intValue();
		return (theInt);
	}
	
	
	protected class Price {
		// Date,Open,High,Low,Close,Volume,Adj Close
		// 2014-08-26,337.00,344.36,334.55,341.83,3638200,341.83
		Calendar theDate;
		Integer theOpen;
		Integer theHigh;
		Integer theLow;
		Integer theClose;
		Integer theVolume;
		Integer theAdjClose;
		
		@Override
		public String toString() {
			return "Price [theDate=" + theDate.getTime() + ", theOpen=" + theOpen
					+ ", theHigh=" + theHigh + ", theLow=" + theLow
					+ ", theClose=" + theClose + ", theVolume=" + theVolume
					+ ", theAdjClose=" + theAdjClose + "]";
		}
		
		
	}

}

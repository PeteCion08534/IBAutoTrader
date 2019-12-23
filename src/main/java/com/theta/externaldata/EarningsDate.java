package com.theta.externaldata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

import com.theta.process.ThetaExceptionExc;


public class EarningsDate {

	private static Logger log = Logger.getLogger(EarningsDate.class);
	private static final String finVizString = "http://finviz.com/quote.ashx?t=";
	
	public Calendar getFutureEarningsDateForSymbol(String symbol, List<Calendar> holidayList) throws ThetaExceptionExc {
		Calendar ret = getEarningsDateForSymbol(symbol, holidayList, "URL", null);
		if (ret==null) return null;
		log.info("Here is ret in getFuture: " + symbol + "," + ret.getTime());

		Calendar todayEOD = Calendar.getInstance(); 
		todayEOD.set(Calendar.HOUR_OF_DAY, 23);
		todayEOD.set(Calendar.MINUTE, 59);
		if (ret.after(todayEOD)) {
			return ret;
		} else {
			return null;
		}
	}
	
	public boolean isMoreThan30DaysAgo(Calendar askDate) {
		if (askDate == null) return true;
		Calendar thirtyDaysAgo = Calendar.getInstance();
		thirtyDaysAgo.add(Calendar.DATE, -30);
		
		if (askDate.before(thirtyDaysAgo)) return true;
		return false;
	}
	
	public Calendar getEarningsDateForSymbol(String symbol, List<Calendar> holidayList, String testFile, Calendar refDate) throws ThetaExceptionExc {
		String earningsString = getEarningsStringFromFinViz(symbol, holidayList, testFile);

		if (symbol == null) return null;
		if (earningsString == null) return null;
		if (refDate == null) refDate = Calendar.getInstance();

		return convertEarningsStringToCalendar(earningsString, holidayList, refDate);
	}


	public boolean isMoreThan85DaysAgo(Calendar askDate) {
		if (askDate == null) return true;
		Calendar eightyFiveDaysAgo = Calendar.getInstance();
		eightyFiveDaysAgo.add(Calendar.DATE, -85);
		
		if (askDate.before(eightyFiveDaysAgo)) return true;
		return false;
	}
	
	public Calendar getCalculatedNextDateIfInFuture(Calendar askDate, List<Calendar> holidayList) {
		Calendar calculatedNextDate = getCalculatedNextDate(askDate, holidayList);
		
		Calendar todayEOD = Calendar.getInstance(); 
		todayEOD.set(Calendar.HOUR_OF_DAY, 23);
		todayEOD.set(Calendar.MINUTE, 59);
		if (calculatedNextDate.after(todayEOD)) {
			return calculatedNextDate;
		} else {
			return null;
		}
	}
	
	// Expected next earnings date is 3 months from last date (same day of month)
	//  If this is a weekend or holiday, back up until a business day
	public Calendar getCalculatedNextDate(Calendar askDate, List<Calendar> holidayList) {
		if (askDate == null) return null;
		Integer newMonth = askDate.get(Calendar.MONTH) + 3;
		Integer newYear = askDate.get(Calendar.YEAR);
		
		if (newMonth > 11) {
			newMonth = newMonth - 12;
			newYear = newYear + 1;
		}
		Calendar calculatedNextDate = (Calendar) askDate.clone();
		calculatedNextDate.set(Calendar.MONTH, newMonth);
		calculatedNextDate.set(Calendar.YEAR, newYear);
				
		Integer dayOfWeek = calculatedNextDate.get(Calendar.DAY_OF_WEEK);
		while (dayOfWeek == 7 || dayOfWeek == 1 || inHolidayList(calculatedNextDate, holidayList)) {
			calculatedNextDate.add(Calendar.DATE, -1);
			dayOfWeek = calculatedNextDate.get(Calendar.DAY_OF_WEEK);
		}

		return calculatedNextDate;
		
	}
		
	
	protected String getEarningsStringFromFinViz(String symbol, List<Calendar> holidayList, String testFile) throws ThetaExceptionExc {
		String targetLine = null;
		try {			
			BufferedReader in = getReaderFromUrl(finVizString + symbol.toLowerCase(), testFile);
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.matches("^.*>Earnings<.*")) {
					targetLine = inputLine;
					break;
				}
			}
			in.close();
		} catch (MalformedURLException e) {
			throw new ThetaExceptionExc("Malformed URL: " + e);
		} catch (IOException e) {
			throw new ThetaExceptionExc("IO Problem: " + e);
		}
		log.info("symbol, targetline: " + symbol + ", " + targetLine);
		return targetLine;
	}
	
	protected BufferedReader getReaderFromUrl(String urlString, String testFile) throws IOException  {
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
	
	// Example Earnings String:
	//<td width="7%" class="snapshot-td2-cp" align="left" title="cssbody=[tooltip_short_bdy] cssheader=[tooltip_short_hdr] body=[Earnings date<br><br>BMO = Before Market Open<br>AMC = After Market Close] offsetx=[10] offsety=[20] delay=[300]">Earnings</td><td width="8%" class="snapshot-td2" align="left"><b>Jul 30 AMC</b></td>
	//>Earnings</td><td width="8%" class="snapshot-td2" align="left"><b>Jul 30 AMC</b></td>
	protected Calendar convertEarningsStringToCalendar(String earningsStr, List<Calendar> holidayList, Calendar refDate) {
		
		if (earningsStr == null)  return null;
		
		String pattern = "(^.*>Earnings<.*)(<b>)(.*)(AMC|BMO)(.*)";
		String dateStr = earningsStr.replaceAll(pattern, "$3");
		String AMCorBMO = earningsStr.replaceAll(pattern, "$4");

		if (dateStr == null || dateStr == "" || AMCorBMO == null || AMCorBMO == "") return null;
		
		Calendar dateCal = convertToCal(dateStr, refDate);

		if (AMCorBMO.equals("AMC")) {
			return dateCal;
		} else if (AMCorBMO.equals("BMO")){
			return getBusinessDayBefore(dateCal, holidayList);
		}
		
		return null;
	}

	

	protected Calendar getBusinessDayBefore(Calendar dateCal, List<Calendar> holidayList) {

		Calendar retDate = (Calendar) dateCal.clone();
		retDate.add(Calendar.DATE, -1);

		Integer dayOfWeek = retDate.get(Calendar.DAY_OF_WEEK);
		while (dayOfWeek == 7 || dayOfWeek == 1 || inHolidayList(retDate, holidayList)) {
			retDate.add(Calendar.DATE, -1);
			dayOfWeek = retDate.get(Calendar.DAY_OF_WEEK);
		}
		
		return retDate;
	}


	protected boolean inHolidayList(Calendar dateCal, List<Calendar> holidayList) {
		if (holidayList == null) return false;
		for (Calendar holiday : holidayList) {
			if (DateUtils.isSameDay(dateCal, holiday)) {
				return true;
			}
		}
		return false;
	}
	
	// Example of dateStr: Jun 20 BMO
	protected Calendar convertToCal(String dateString, Calendar refDate) {
		if (dateString == null){
			log.info("dateString is null.  Returning null.");
			return null;
		}
		
		String[] dateArr = dateString.trim().split(" ");
		if (dateArr.length < 2) {
			log.info("dateArr has length less than 2. dateString :" + dateString + ".");
			return null;
		}

		Integer monthInt = monthToInt(dateArr[0]);
		if (monthInt == null) {
			log.info("monthInt is null - non-standard Month name.  dateString :" + dateString + ".");
			return null;
		}
		
		Integer yearInt = getYear(monthInt, refDate);
		Integer dayInt = new Integer(dateArr[1]);
		if (dayInt < 1 || dayInt > 31) {
			log.info("dayInt is not a day of the month.  dateString :" + dateString + ".");
			return null;
		}
		
		Calendar targetDate = Calendar.getInstance();
		targetDate.set(yearInt,  monthInt, dayInt);
		
		return targetDate;
	}

	
	protected Integer getYear(Integer targetMonth, Calendar refDate) {
		if (refDate == null) {
			refDate = Calendar.getInstance();
		}
		Integer yearNow = refDate.get(Calendar.YEAR);
		Integer monthNow = refDate.get(Calendar.MONTH);
		if (
			(monthNow.equals(0) || monthNow.equals(1) || monthNow.equals(2)) &&
			(targetMonth.equals(9) || targetMonth.equals(10) || targetMonth.equals(11)) 
			)
		{
			return (yearNow - 1);
		}
		
		if (
			(monthNow.equals(9) || monthNow.equals(10) || monthNow.equals(11)) &&
			(targetMonth.equals(0) || targetMonth.equals(1) || targetMonth.equals(2)) 
			)
		{
			return (yearNow + 1);
		}
		
		return yearNow;
	}
	
	protected Integer monthToInt(String monthStr) {
		if (monthStr.equalsIgnoreCase("JAN")) return 0;
		if (monthStr.equalsIgnoreCase("FEB")) return 1;
		if (monthStr.equalsIgnoreCase("MAR")) return 2;
		if (monthStr.equalsIgnoreCase("APR")) return 3;
		if (monthStr.equalsIgnoreCase("MAY")) return 4;
		if (monthStr.equalsIgnoreCase("JUN")) return 5;
		if (monthStr.equalsIgnoreCase("JUL")) return 6;
		if (monthStr.equalsIgnoreCase("AUG")) return 7;
		if (monthStr.equalsIgnoreCase("SEP")) return 8;
		if (monthStr.equalsIgnoreCase("OCT")) return 9;
		if (monthStr.equalsIgnoreCase("NOV")) return 10;
		if (monthStr.equalsIgnoreCase("DEC")) return 11;
		return null;
	}
	
	
//	public static void main(String[] args) throws IOException {
//		EarningsDate ed = new EarningsDate();
//	}

}

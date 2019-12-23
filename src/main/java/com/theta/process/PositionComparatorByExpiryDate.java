package com.theta.process;

import java.util.Calendar;
import java.util.Comparator;
import com.theta.domain.Position;

public class PositionComparatorByExpiryDate implements Comparator {

	public int compare(Object position1, Object position2){
		/*
		 * parameters are of type Object, so we have to downcast them
		 * to Position objects
		 */
		Integer day1 = ((Position)position1).getExpiryDay();
		Integer month1 = ((Position)position1).getExpiryMonth();
		Integer year1 = ((Position)position1).getExpiryYear();
		Calendar cal1 = Calendar.getInstance();
		cal1.set(year1, month1, day1);
		
		Integer day2 = ((Position)position2).getExpiryDay();
		Integer month2 = ((Position)position2).getExpiryMonth();
		Integer year2 = ((Position)position2).getExpiryYear();
		Calendar cal2 = Calendar.getInstance();
		cal2.set(year2, month2, day2);

		if (cal1.after(cal2))
			return 1;
		else if (cal1.before(cal2))
			return -1;
		else
			return 0;
	}
}

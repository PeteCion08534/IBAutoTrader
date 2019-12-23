package com.theta.process;

import java.util.Calendar;
import java.util.Comparator;
import com.theta.domain.Spread;

public class SpreadComparatorByUpdatedDate implements Comparator {

	public int compare(Object spread1, Object spread2){
		/*
		 * parameters are of type Object, so we have to downcast them
		 * to Spread objects
		 */
		Calendar cal1 = ((Spread)spread1).getUpdatedDate();
		Calendar cal2 = ((Spread)spread2).getUpdatedDate();

		if (cal1.after(cal2))
			return 1;
		else if (cal1.before(cal2))
			return -1;
		else
			return 0;
	}
}

package com.theta.process;

import org.apache.log4j.Logger;

import com.theta.enums.LoMidHiCode;
import com.theta.enums.UpOrDownCode;

public class Decider {

	private static Logger log = Logger.getLogger(Decider.class);
	
	public static boolean changePosition(
			UpOrDownCode upOrDown,
			Boolean beenBelow,
			Boolean beenMid,
			Boolean beenHi,
			LoMidHiCode lowMidHi
			) {
		
		String chkString =  upOrDown.getValue() + "-" + beenBelow + "-" + beenMid + "-" + beenHi + "-" + lowMidHi.getValue();
		log.info("Decider result: " + chkString);
		
		// ********************************************//
		// *************** LO - UP ********************//
		// ********************************************//
		// ***** Going UP - buy in LONG position, sell in SHORT position ****
		// Now Low - do not get in
		if (chkString.equals("UP-false-false-false-LO")) {return false;}
		else if (chkString.equals("UP-false-false-true-LO")) {return false;}
		else if (chkString.equals("UP-false-true-false-LO")) {return false;}
		else if (chkString.equals("UP-false-true-true-LO")) {return false;}
		else if (chkString.equals("UP-true-false-false-LO")) {return false;}
		else if (chkString.equals("UP-true-false-true-LO")) {return false;}
		else if (chkString.equals("UP-true-true-false-LO")) {return false;}
		else if (chkString.equals("UP-true-true-true-LO")) {return false;}
		
		// ********************************************//
		// *************** MID - UP ********************//
		// ********************************************//
		// Now MID - get in if the price has been LO
		else if (chkString.equals("UP-true-false-false-MID")) {return true;}
		else if (chkString.equals("UP-true-false-true-MID")) {return true;}
		else if (chkString.equals("UP-true-true-false-MID")) {return true;}
		else if (chkString.equals("UP-true-true-true-MID")) {return true;}

		// Do not get in the the price has not been LO
		else if (chkString.equals("UP-false-false-false-MID")) {return false;}
		else if (chkString.equals("UP-false-false-true-MID")) {return false;}
		else if (chkString.equals("UP-false-true-false-MID")) {return false;}
		else if (chkString.equals("UP-false-true-true-MID")) {return false;}

		// ********************************************//
		// *************** HI - UP ********************//
		// ********************************************//
		// Now HI - get in if the price has been LO or MID
		else if (chkString.equals("UP-false-true-false-HI")) {return true;}
		else if (chkString.equals("UP-false-true-true-HI")) {return true;}
		else if (chkString.equals("UP-true-false-false-HI")) {return true;}
		else if (chkString.equals("UP-true-false-true-HI")) {return true;}
		else if (chkString.equals("UP-true-true-false-HI")) {return true;}
		else if (chkString.equals("UP-true-true-true-HI")) {return true;}

		// Do not get in if the prices has not been LO or MID
		else if (chkString.equals("UP-false-false-false-HI")) {return false;}
		else if (chkString.equals("UP-false-false-true-HI")) {return false;}

		
		// **********************************************//
		// *************** LO - DOWN ********************//
		// **********************************************//
		// Get out when the price is below LO
		else if (chkString.equals("DOWN-false-false-true-LO")) {return true;}
		else if (chkString.equals("DOWN-false-true-false-LO")) {return true;}
		else if (chkString.equals("DOWN-false-true-true-LO")) {return true;}
		else if (chkString.equals("DOWN-true-false-true-LO")) {return true;}
		else if (chkString.equals("DOWN-true-true-false-LO")) {return true;}
		else if (chkString.equals("DOWN-true-true-true-LO")) {return true;}

		// Do not get out if the price has not been MID or HI
		else if (chkString.equals("DOWN-false-false-false-LO")) {return false;}
		else if (chkString.equals("DOWN-true-false-false-LO")) {return false;}

		// ***********************************************//
		// *************** MID - DOWN ********************//
		// ***********************************************//
		// Get out when the price has been HI
		else if (chkString.equals("DOWN-false-false-true-MID")) {return true;}
		else if (chkString.equals("DOWN-false-true-true-MID")) {return true;}
		else if (chkString.equals("DOWN-true-false-true-MID")) {return true;}
		else if (chkString.equals("DOWN-true-true-true-MID")) {return true;}

		// Dont get out when the price has not been HI
		else if (chkString.equals("DOWN-false-false-false-MID")) {return false;}
		else if (chkString.equals("DOWN-false-true-false-MID")) {return false;}
		else if (chkString.equals("DOWN-true-false-false-MID")) {return false;}
		else if (chkString.equals("DOWN-true-true-false-MID")) {return false;}

		// **********************************************//
		// *************** HI - DOWN ********************//
		// **********************************************//
		else if (chkString.equals("DOWN-false-false-false-HI")) {return false;}
		else if (chkString.equals("DOWN-false-false-true-HI")) {return false;}
		else if (chkString.equals("DOWN-false-true-false-HI")) {return false;}
		else if (chkString.equals("DOWN-false-true-true-HI")) {return false;}
		else if (chkString.equals("DOWN-true-false-false-HI")) {return false;}
		else if (chkString.equals("DOWN-true-false-true-HI")) {return false;}
		else if (chkString.equals("DOWN-true-true-false-HI")) {return false;}
		else if (chkString.equals("DOWN-true-true-true-HI")) {return false;}

		
		return true;
	}
	
}

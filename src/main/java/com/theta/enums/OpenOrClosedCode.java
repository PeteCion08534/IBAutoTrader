package com.theta.enums;

import com.theta.enums.types.StringValuedEnum;


/**
 * OPEN - Active spread
 * CLOSED - Non-active spread
 * PENDOPEN/PENDCLOSE - Entered, but not found (yet!) in OPEN ORDERS
 * PENDOPNCNF/PENDCLSCNF - Confirmed in OPEN ORDERS
 * NOTEXEC - Entered, but died on the vine - never executed
 * KILLED - Killed by hand (also cancelled in the IB trading software!)
 *
 */
public enum OpenOrClosedCode implements StringValuedEnum{
	OPEN("OPEN"),
	CLOSED("CLOSED"),
	ADVISEOPEN("ADVISEOPEN"),
	ADVISECLS("ADVISECLS"),
	PENDOPEN("PENDOPEN"),
	PENDOPNCNF("PENDOPNCNF"),
	PENDCLOSE("PENDCLOSE"),
	PENDCLSCNF("PENDCLSCNF"),
	NOTEXEC("NOTEXEC"),
	FAILCLOSE("FAILCLOSE"),
	KILLED("KILLED"), 
	HAILMARY("HAILMARY");
	
	private String state;
	
	OpenOrClosedCode(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
}
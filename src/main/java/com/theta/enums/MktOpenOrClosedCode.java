package com.theta.enums;

import com.theta.enums.types.StringValuedEnum;


/**
 * OPEN - Market Open
 * CLOSED - Market Closed
 * ALLDAY - Don't include time - date only
 *
 */
public enum MktOpenOrClosedCode implements StringValuedEnum{
	OPEN("OPEN"),
	CLOSED("CLOSED"),
	ALLDAY("ALLDAY");
	
	private String state;
	
	MktOpenOrClosedCode(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
}
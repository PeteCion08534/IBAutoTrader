package com.theta.enums;

import com.theta.enums.types.StringValuedEnum;


/**
 * OPT - Option
 * STK - Stock
 *
 */
public enum InstrumentCode implements StringValuedEnum{
	OPT("OPT"),
	STK("STK");
	
	
	private String state;
	
	InstrumentCode(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
}
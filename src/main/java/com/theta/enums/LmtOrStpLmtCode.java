package com.theta.enums;

import com.theta.enums.types.StringValuedEnum;


/**
 * LMT - Limit Order
 * STPLMT - Stop Limit Order
 *
 */
public enum LmtOrStpLmtCode implements StringValuedEnum{
	LMT("LMT"),
	STPLMT("STPLMT");
	
	private String state;
	
	LmtOrStpLmtCode(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
}
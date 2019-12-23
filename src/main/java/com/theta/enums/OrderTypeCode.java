package com.theta.enums;

import com.theta.enums.types.StringValuedEnum;


/**
 * MKT - Market
 * LMT - Limit
 *
 */
public enum OrderTypeCode implements StringValuedEnum{
	MKT("MKT"),
	LMT("LMT");
	
	
	private String state;
	
	OrderTypeCode(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
}
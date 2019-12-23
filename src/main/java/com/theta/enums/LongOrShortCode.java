package com.theta.enums;

import com.theta.client.enums.types.StringValuedEnum;


/**
 * LONG
 * SHORT
 *
 */
public enum LongOrShortCode implements StringValuedEnum{
	LONG("LONG"),
	SHORT("SHORT");
	
	private String state;
	
	LongOrShortCode(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
}
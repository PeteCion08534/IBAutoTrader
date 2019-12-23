package com.theta.enums;

import com.theta.client.enums.types.StringValuedEnum;


/**
 * MIN - Min
 * MAX - Max
 *
 */
public enum MinOrMaxCode implements StringValuedEnum{
	MIN("MIN"),
	MAX("MAX"),
	LAST("LAST");
	
	private String state;
	
	MinOrMaxCode(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
}
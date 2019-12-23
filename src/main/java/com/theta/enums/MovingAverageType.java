package com.theta.enums;

import com.theta.enums.types.StringValuedEnum;


/**
 *  "DAILYPRICE"
 *  "DAILYRANGE"
 *
 */
public enum MovingAverageType implements StringValuedEnum{
	DAILYPRICE("DAILYPRICE"),
	DAILYRANGE("DAILYRANGE");
		
	private String state;
	
	MovingAverageType(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
}
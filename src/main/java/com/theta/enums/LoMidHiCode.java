package com.theta.enums;

import com.theta.enums.types.StringValuedEnum;

public enum LoMidHiCode implements StringValuedEnum{
	LO("LO"),
	MID("MID"),
	HI("HI");
	
	private String state;
	
	LoMidHiCode(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
}
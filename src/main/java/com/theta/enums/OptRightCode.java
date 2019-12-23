package com.theta.enums;

import com.theta.enums.types.StringValuedEnum;

public enum OptRightCode implements StringValuedEnum{
	P("P"),
	C("C");
	
	private String state;
	
	OptRightCode(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
}
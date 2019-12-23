package com.theta.enums;

import com.theta.client.enums.types.StringValuedEnum;


/**
 * UP
 * DOWN
 *
 */
public enum UpOrDownCode implements StringValuedEnum{
	UP("UP"),
	DOWN("DOWN");
	
	private String state;
	
	UpOrDownCode(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
}
package com.theta.enums;



/**
 * UP
 * DOWN
 *
 */
public enum FooCode {
	UPSTR,
	DOWNSTR;
	//UPSTR("UPVAL"),
	//DOWNSTR("DOWNVAL");
	
	//private String state;
	
	FooCode(){
		System.out.println("In constructor");
		//this.state = state;
	}

	//public String getValue() {
	//	return this;
	//}
}
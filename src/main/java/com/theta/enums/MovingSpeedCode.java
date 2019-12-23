package com.theta.enums;

import com.theta.enums.types.StringValuedEnum;


/**
 * SLOW
 * MEDIUM
 * FAST 
 *
 */
public enum MovingSpeedCode implements StringValuedEnum{
	SLOW("SLOW"),
	MEDIUM("MEDIUM"),
	FAST("FAST");
	
	private String state;
	
	MovingSpeedCode(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
	
	public boolean isSlow() {
		if (this.state.equalsIgnoreCase(MovingSpeedCode.SLOW.toString())) return true;
		else return false;
	}
	public boolean isMedium() {
		if (this.state.equalsIgnoreCase(MovingSpeedCode.MEDIUM.toString())) return true;
		else return false;
	}
	public boolean isFast() {
		if (this.state.equalsIgnoreCase(MovingSpeedCode.FAST.toString())) return true;
		else return false;
	}
	
}
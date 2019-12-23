package com.theta.enums;

import com.theta.client.enums.types.StringValuedEnum;


/**
 * BUY - Buy
 * SELL - Sell
 *
 */
public enum BuyOrSellCode implements StringValuedEnum{
	BUY("BUY"),
	SELL("SELL");
	
	private String state;
	
	BuyOrSellCode(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
}
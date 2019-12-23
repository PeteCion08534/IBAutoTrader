package com.theta.enums;

import com.theta.enums.types.StringValuedEnum;


/**
 * BID - Bid
 * ASK - Ask
 * LAST - Last
 * HIGH - High
 * LOW - Low
 * CLOSE - Close
 * BIDPLUS - A "little more" than Bid
 * ASKMINUS - A "little less" than Ask
 *
 */
public enum BidOrAskCode implements StringValuedEnum{
	BID("BID"),
	ASK("ASK"),
	LAST("LAST"),
	HIGH("HIGH"),
	LOW("LOW"),
	CLOSE("CLOSE"),
	BIDPLUS("BIDPLUS"),
	ASKMINUS("ASKMINUS");
	
	
	private String state;
	
	BidOrAskCode(String state){
		this.state = state;
	}

	public String getValue() {
		return this.state;
	}
}
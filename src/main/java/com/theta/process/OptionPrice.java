/**
 * 
 */
package com.theta.process;

import java.util.Calendar;

/**
 * @author pcion
 *
 */
public class OptionPrice {

	private Integer ask = 0;
	private Integer askMinus = 0;
	private Integer bid = 0;
	private Integer bidPlus = 0;
	private Integer last = 0;
	private Integer high = 0;
	private Integer low = 0;
	private Integer close = 0;
	private Calendar updated;
	private Integer ttlMs = 0;
	private boolean isStale = true;
	
	/**
	 * @return the ask
	 */
	public Integer getAsk() {
		return ask;
	}
	/**
	 * @param ask the ask to set
	 */
	public void setAsk(Integer ask) {
		this.ask = ask;
	}
	/**
	 * @return the askMinus
	 */
	public Integer getAskMinus() {
		Integer diff = ask - bid;
		// One apart - just send back ASK
		if ( diff.equals(ThetaConstants.ONE_HUNDRED_INT )) {
			return ask;
		} 
		if ( diff.equals(ThetaConstants.TWO_HUNDRED_INT ) || diff.equals(ThetaConstants.THREE_HUNDRED_INT)) {
			return (ask - ThetaConstants.ONE_HUNDRED_INT);
		} 
		return (ask - (diff/4));
	}
	
	public Integer getAskPlus() {
		return (ask + ThetaConstants.ONE_HUNDRED_INT);
	}

	
	/**
	 * @param askMinus the askMinus to set
	 */
	//public void setAskMinus(Integer askMinus) {
	//	this.askMinus = askMinus;
	//}
	/**
	 * @return the bid
	 */
	public Integer getBid() {
		return bid;
	}
	/**
	 * @param bid the bid to set
	 */
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	/**
	 * @return the bidPlus
	 */
	public Integer getBidPlus() {
		Integer diff = ask - bid;

		if ( diff.equals(ThetaConstants.ONE_HUNDRED_INT )) {
			return bid;
		} 
		if ( diff.equals(ThetaConstants.TWO_HUNDRED_INT ) || diff.equals(ThetaConstants.THREE_HUNDRED_INT)) {
			return (bid + ThetaConstants.ONE_HUNDRED_INT);
		} 
		return (bid + (diff/4));
	}

	public Integer getBidMinus() {
		return (bid - ThetaConstants.ONE_HUNDRED_INT);
	}
	
	public Integer getBidAskHalf() {
		Integer diff = ask - bid;
		// One apart - just send back ASK
		return (bid + (diff/2));
	}
	/*
	 * This is the main getter - 
	 * Return the LAST price if it's between (or including) BID and ASK
	 * Return BidAskHalf otherwise
	 */
	public Integer getLastIfBetween(){
		if ((bid <= last) && (last <= ask )){
			return last;
		} else {
			return this.getBidAskHalf();
		}
	}
	/**
	 * @param bidPlus the bidPlus to set
	 */
	//public void setBidPlus(Integer bidPlus) {
	//	this.bidPlus = bidPlus;
	//}
	/**
	 * @return the last
	 */
	public Integer getLast() {
		return last;
	}
	/**
	 * @param last the last to set
	 */
	public void setLast(Integer last) {
		this.last = last;
	}
	/**
	 * @return the high
	 */
	public Integer getHigh() {
		return high;
	}
	/**
	 * @param high the high to set
	 */
	public void setHigh(Integer high) {
		this.high = high;
	}
	/**
	 * @return the low
	 */
	public Integer getLow() {
		return low;
	}
	/**
	 * @param low the low to set
	 */
	public void setLow(Integer low) {
		this.low = low;
	}
	/**
	 * @return the close
	 */
	public Integer getClose() {
		return close;
	}
	/**
	 * @param close the close to set
	 */
	public void setClose(Integer close) {
		this.close = close;
	}
	
	public Calendar getUpdated() {
		return updated;
	}
	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}

	
	public Integer getTtlMs() {
		return ttlMs;
	}
	public void setTtlMs(Integer ttlMs) {
		this.ttlMs = ttlMs;
	}
	/*
	 * Returns TRUE if this is "stale"
	 */
	public boolean isStale(){
		
		long ageMs = (Calendar.getInstance().getTimeInMillis() - this.updated.getTimeInMillis());
		Integer ageMsInt = new Integer((new Long(ageMs)).intValue());
		
		if (ageMsInt.compareTo(this.ttlMs) < 0){
			isStale = false;
		} else {
			isStale = true;
		}

		return isStale;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OptionPrice [");
		if (ask != null) {
			builder.append("ask=");
			builder.append(ask);
			builder.append(", ");
		}
		if (bid != null) {
			builder.append("bid=");
			builder.append(bid);
			builder.append(", ");
		}
		if (close != null) {
			builder.append("close=");
			builder.append(close);
			builder.append(", ");
		}
		if (high != null) {
			builder.append("high=");
			builder.append(high);
			builder.append(", ");
		}
		if (last != null) {
			builder.append("last=");
			builder.append(last);
			builder.append(", ");
		}
		if (low != null) {
			builder.append("low=");
			builder.append(low);
			builder.append(", ");
		}
		if (updated != null) {
			builder.append("updated=");
			builder.append(updated.getTime());
			builder.append(", ");
			builder.append("ttlMs=");
			builder.append(ttlMs);
			builder.append(", ");
			builder.append("isStale=");
			builder.append(this.isStale());
		}
		builder.append("]");
		return builder.toString();
	}
	
}

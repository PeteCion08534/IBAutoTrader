package com.theta.client;

import com.theta.process.ThetaConstants;

public class OrderStatus {
	
	Integer orderId = ThetaConstants.ZERO_INT;
    String status = ThetaConstants.NULL_STRING;
    Integer filled = ThetaConstants.ZERO_INT;
    Integer remaining  = ThetaConstants.ZERO_INT;
	Integer avgFillPrice = ThetaConstants.ZERO_INT;
	Integer permId = ThetaConstants.ZERO_INT;
	Integer parentId = ThetaConstants.ZERO_INT;
	double lastFillPrice = ThetaConstants.ZERO_DOUBLE;
	Integer lastFillPriceXTenThou = ThetaConstants.ZERO_INT;
	Integer clientId = ThetaConstants.ZERO_INT;
	String whyHeld = ThetaConstants.NULL_STRING;
	//double avgFillPrice;
	//double lastFillPrice;

	
    /**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the filled
	 */
	public int getFilled() {
		return filled;
	}
	/**
	 * @param filled the filled to set
	 */
	public void setFilled(int filled) {
		this.filled = filled;
	}
	/**
	 * @return the remaining
	 */
	public int getRemaining() {
		return remaining;
	}
	/**
	 * @param remaining the remaining to set
	 */
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	/**
	 * @return the avgFillPrice
	 */
	public Integer getAvgFillPrice() {
		return avgFillPrice;
	}
	/**
	 * @param avgFillPrice the avgFillPrice to set
	 */
	public void setAvgFillPrice(double avgFillPrice) {
		this.avgFillPrice = (int) (avgFillPrice * ThetaConstants.ONE_HUNDRED_DOUBLE);
	}
	/**
	 * @return the permId
	 */
	public int getPermId() {
		return permId;
	}

	public String getPermIdStr() {
		return (new Integer(permId)).toString();
	}

	/**
	 * @param permId the permId to set
	 */
	public void setPermId(int permId) {
		this.permId = permId;
	}
	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the lastFillPrice
	 */
	public double getLastFillPrice() {
		return lastFillPrice;
	}
	/**
	 * @param lastFillPrice the lastFillPrice to set
	 */
	public void setLastFillPrice(double lastFillPrice) {
		this.lastFillPrice = lastFillPrice;
	}
	/**
	 * @return the lastFillPriceXTenThou
	 */
	public Integer getLastFillPriceXTenThou() {
		return (int) (Math.abs(lastFillPrice * ThetaConstants.TEN_THOUSAND_DOUBLE));
	}

	public Integer getLastFillPriceXOneHundred() {
		return (int) (Math.abs(lastFillPrice * ThetaConstants.ONE_HUNDRED_DOUBLE));
	}

	
	//public void setLastFillPriceX100(Integer lastFillPriceX100) {
	//	this.lastFillPriceX100 = lastFillPriceX100;
	//}
	
	/**
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the whyHeld
	 */
	public String getWhyHeld() {
		return whyHeld;
	}
	/**
	 * @param whyHeld the whyHeld to set
	 */
	public void setWhyHeld(String whyHeld) {
		this.whyHeld = whyHeld;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderStatus [avgFillPrice=");
		builder.append(avgFillPrice);
		builder.append(", clientId=");
		builder.append(clientId);
		builder.append(", filled=");
		builder.append(filled);
		builder.append(", remaining=");
		builder.append(remaining);
		builder.append(", lastFillPrice=");
		builder.append(lastFillPrice);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", parentId=");
		builder.append(parentId);
		builder.append(", permId=");
		builder.append(permId);
		builder.append(", remaining=");
		builder.append(remaining);
		builder.append(", status=");
		builder.append(status);
		builder.append(", whyHeld=");
		builder.append(whyHeld);
		builder.append("]");
		return builder.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toStringShort() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderStatus [avgFillPrice=");
		builder.append(avgFillPrice);
		builder.append(", lastFillPrice=");
		builder.append(lastFillPriceXTenThou);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", permId=");
		builder.append(permId);
		builder.append("]");
		return builder.toString();
	}
	
	/**
	 */
	@Override
	public int hashCode() {
		return orderId;
	}

	
	/**
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (obj == this)
			return true;
		
		if (!(obj instanceof OrderStatus))
			return false;
		
		OrderStatus equalCheck = (OrderStatus) obj;

		if (orderId.equals(equalCheck.orderId))
			return true;

		return false;
	}
	
	
}

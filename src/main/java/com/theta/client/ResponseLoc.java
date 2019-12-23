
package com.theta.client;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.ib.client.Contract;
import com.ib.client.ContractDetails;

// PC: Created for the serialization of ThetaClient
public class ResponseLoc implements ResponseInterface {

	private int reqId;

	private String security;

	private Map<Integer, Integer> priceBidMap = new HashMap<Integer, Integer>();
	private Map<Integer, Integer>  priceAskMap = new HashMap<Integer, Integer>();
	//private Integer priceLast;
	//private Integer priceHigh;
	//private Integer priceLow;
	private Map<Integer, Integer> priceCloseMap = new HashMap<Integer, Integer>();
	private Integer volume;

	private Contract contract;
	private int nextValidId;

	private ContractDetails contractDetails;
	private String contractDetailsStr;


	private int errorId = 0;
	private int errorCode= 0;
	private String errorMsg = "";

	//NetLiquidation, 91995.20
	//LookAheadInitMarginReq, 59644.53
	//LookAheadAvailableFunds, 32350.67
	private String NetLiquidation;
	private String LookAheadInitMarginReq;
	private String LookAheadAvailableFunds;

	private static Logger log = Logger.getLogger("Response");


	public void setAllPricesToZero(Integer key){
		Integer ZERO = new Integer(0);
		setPriceBid(key, ZERO);
		setPriceAsk(key, ZERO);
		//			setPriceLast(ZERO);
		//			setPriceHigh(ZERO);
		//			setPriceLow(ZERO);
		setPriceClose(key, ZERO);
	}

	public void clearPrices(Integer key) {
		clearPriceBid(key);
		clearPriceAsk(key);
		clearPriceClose(key);
	}

	public int getReqId() {
		return reqId;
	}
	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public Integer getPriceBid(Integer key) {
		return priceBidMap.get(key);
	}
	public Integer getPriceAsk(Integer key) {
		return priceAskMap.get(key);
	}
	public Integer getPriceClose(Integer key) {
		return priceCloseMap.get(key);
	}
	public void setPriceBid(Integer key, Integer priceBid) {
		this.priceBidMap.put(key, priceBid);
	}
	public void setPriceAsk(Integer key, Integer priceAsk) {
		this.priceAskMap.put(key, priceAsk);
	}
	public void setPriceClose(Integer key, Integer priceClose) {
		this.priceCloseMap.put(key,  priceClose);
	}
	public void clearPriceBid(Integer key) {
		priceBidMap.remove(key);
	}
	public void clearPriceAsk(Integer key) {
		priceAskMap.remove(key);
	}
	public void clearPriceClose(Integer key) {
		priceCloseMap.remove(key);
	}
	/**
	 * @return the priceLast
	 */
	 //		public Integer getPriceLast() {
	//			return priceLast;
	//		}
	/**
	 * @param priceLast the priceLast to set
	 */
	//		public void setPriceLast(Integer priceLast) {
	//			this.priceLast = priceLast;
	//		}
	/**
	 * @return the priceHigh
	 */
	//		public Integer getPriceHigh() {
	//			return priceHigh;
	//		}
	/**
	 * @param priceHigh the priceHigh to set
	 */
	//		public void setPriceHigh(Integer priceHigh) {
	//			this.priceHigh = priceHigh;
	//		}
	/**
	 * @return the priceLow
	 */
	//		public Integer getPriceLow() {
	//			return priceLow;
	//		}
	/**
	 * @param priceLow the priceLow to set
	 */
	//		public void setPriceLow(Integer priceLow) {
	//			this.priceLow = priceLow;
	//		}


	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}


	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public int getNextValidId() {
		return nextValidId;
	}

	public void setNextValidId(int nextValidId) {
		this.nextValidId = nextValidId;
	}

	public ContractDetails getContractDetails() {
		return contractDetails;
	}

	public void setContractDetails(ContractDetails contractDetails) {
		log.info("IN Response:setContractDetails: " + contractDetails.toString());
		this.contractDetails = contractDetails;
	}


	public String getContractDetailsStr() {
		return contractDetailsStr;
	}

	public void setContractDetailsStr(String contractDetailsStr) {
		this.contractDetailsStr = contractDetailsStr;
	}


	public int getErrorId() {
		return errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getNetLiquidation() {
		return "100000";
		//return NetLiquidation;
	}

	public void setNetLiquidation(String netLiquidation) {
		NetLiquidation = netLiquidation;
	}

	public String getLookAheadInitMarginReq() {
		return "100000";
		//return LookAheadInitMarginReq;
	}

	public void setLookAheadInitMarginReq(String lookAheadInitMarginReq) {
		LookAheadInitMarginReq = lookAheadInitMarginReq;
	}

	public String getLookAheadAvailableFunds() {
		return "75000";
		//return LookAheadAvailableFunds;
	}

	public void setLookAheadAvailableFunds(String lookAheadAvailableFunds) {
		LookAheadAvailableFunds = lookAheadAvailableFunds;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	//		@Override
	//		public String toString() {
	//			StringBuilder builder = new StringBuilder();
	//			builder.append("Response [");
	//			if (contract != null) {
	//				builder.append("contract=");
	//				builder.append(contract);
	//				builder.append(", ");
	//			}
	//			if (contractDetails != null) {
	//				builder.append("contractDetails=");
	//				builder.append(contractDetails);
	//				builder.append(", ");
	//			}
	//			if (contractDetailsStr != null) {
	//				builder.append("contractDetailsStr=");
	//				builder.append(contractDetailsStr);
	//				builder.append(", ");
	//			}
	//			builder.append("errorCode=");
	//			builder.append(errorCode);
	//			builder.append(", errorId=");
	//			builder.append(errorId);
	//			builder.append(", ");
	//			if (errorMsg != null) {
	//				builder.append("errorMsg=");
	//				builder.append(errorMsg);
	//				builder.append(", ");
	//			}
	//			builder.append("nextValidId=");
	//			builder.append(nextValidId);
	//			builder.append(", ");
	//			if (priceBid != null) {
	//				builder.append("priceBid=");
	//				builder.append(priceBid);
	//				builder.append(", ");
	//			}
	//			if (priceAsk != null) {
	//				builder.append("priceAsk=");
	//				builder.append(priceAsk);
	//				builder.append(", ");
	//			}
	//			if (priceLast != null) {
	//				builder.append("priceLast=");
	//				builder.append(priceLast);
	//				builder.append(", ");
	//			}
	//			if (priceClose != null) {
	//				builder.append("priceClose=");
	//				builder.append(priceClose);
	//				builder.append(", ");
	//			}
	//			builder.append("reqId=");
	//			builder.append(reqId);
	//			builder.append(", ");
	//			if (security != null) {
	//				builder.append("security=");
	//				builder.append(security);
	//			}
	//			builder.append("]");
	//			return builder.toString();
	//		}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Response [reqId=");
		builder.append(reqId);
		builder.append(", security=");
		builder.append(security);
		builder.append(", priceBidMap=");
		builder.append(priceBidMap);
		builder.append(", priceAskMap=");
		builder.append(priceAskMap);
		builder.append(", priceCloseMap=");
		builder.append(priceCloseMap);
		builder.append(", volume=");
		builder.append(volume);
		builder.append(", contract=");
		builder.append(contract);
		builder.append(", nextValidId=");
		builder.append(nextValidId);
		builder.append(", contractDetails=");
		builder.append(contractDetails);
		builder.append(", contractDetailsStr=");
		builder.append(contractDetailsStr);
		builder.append(", errorId=");
		builder.append(errorId);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorMsg=");
		builder.append(errorMsg);
		builder.append(", NetLiquidation=");
		builder.append(NetLiquidation);
		builder.append(", LookAheadInitMarginReq=");
		builder.append(LookAheadInitMarginReq);
		builder.append(", LookAheadAvailableFunds=");
		builder.append(LookAheadAvailableFunds);
		builder.append("]");
		return builder.toString();
	}

	public void setOutgoingId(int reqId2) {
		// TODO Auto-generated method stub

	}





}

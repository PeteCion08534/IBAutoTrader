package com.theta.client;

import com.ib.client.Contract;
import com.ib.client.ContractDetails;

// PC: Created for the serialization of ThetaClient
public class ResponseLocOLD {

	private int outgoingId;
	private int incomingId;
	
	private String security;

	private Float price;
	private Contract contract;
	private int nextValidId;

	private ContractDetails contractDetails;
	private String contractDetailsStr;
	

	private int errorId = 0;
	private int errorCode= 0;
	private String errorMsg = "";


	public int getOutgoingId() {
		return outgoingId;
	}
	public void setOutgoingId(int outgoingId) {
		this.outgoingId = outgoingId;
	}

	public int getIncomingId() {
		return incomingId;
	}
	public void setIncomingId(int incomingId) {
		this.incomingId = incomingId;
	}
	
	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
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
		System.out.println("IN Response:setContractDetails");
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
	public void setPriceBid(Integer requestSeqNo, int i) {
		// TODO Auto-generated method stub
		
	}
	
}

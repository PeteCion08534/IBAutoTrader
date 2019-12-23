package com.theta.process;

import java.util.Iterator;
import java.util.Set;

import com.theta.client.PortfolioItem;
import com.theta.client.ThetaClient3;
import com.theta.client.ThetaClientInterface;

public class OpenPositions {

	ThetaClientInterface client;
	ThetaUtil thetaUtil;
	String accountCode;
	
	protected OpenPositions(ThetaClientInterface _client, String accountCode){
		client = _client;
		thetaUtil = new ThetaUtil();
		this.accountCode = accountCode;
	}
	
	public void getOpenPositions() throws ThetaExceptionExc {

		ThetaClient3 client3 = (ThetaClient3) client;
		
		client3.portfolioItems.clear();
		client3.reqAccountUpdates(accountCode);
		thetaUtil.secondsToSleep(ThetaConstants.TEN_INT);
		//Set<PortfolioItem> allPortfolioItems = new HashSet<PortfolioItem>(client3.portfolioItems);
		Set<PortfolioItem> allPortfolioItems = client3.portfolioItems;
		
		Iterator<PortfolioItem> allPortfolioItemsIter = allPortfolioItems.iterator();
		Integer IbId = ThetaConstants.ZERO_INT;
		while (allPortfolioItemsIter.hasNext()){
			PortfolioItem pfItem = (PortfolioItem) allPortfolioItemsIter.next();
			System.out.println("Portfolio Item: " + pfItem);
		}

	}
	
	
	
	
	
}

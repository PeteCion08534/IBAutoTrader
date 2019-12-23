package com.theta.client;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.theta.process.ThetaExceptionExc;
import com.theta.process.ThetaUtil;


public class ThetaClient3Test {

	ThetaClient3 client = null;
	ThetaUtil util = null;
	
	@Before
	public void setUp() {
		
		client = new ThetaClient3();
		util = new ThetaUtil();
		
	}

	@Ignore
	@Test
	public void testRand() {
		Double d;
		while(true) {
			d =  ((Math.random()*400) - 200);
			System.out.println("Here is d: " + d.intValue() );
			ThetaUtil.secondsToSleepNoThrow(1);
		}
	}
	
	@Test
	public void testConnect() {
		
		client.disconnect();
		
		boolean ret = client.connect(7496);
		System.out.println("Here is ret: " + ret);
	}
	
	@Test
	public void canEnterMapTest() {
		Integer key = 123;
		client.setCanEnterMap(key, true);
		Boolean canEnter = client.getCanEnterMap(key);
		Assert.assertTrue(canEnter);
		
		Integer key2 = 234;
		canEnter = client.getCanEnterMap(key2);
		System.out.println("here is canEnter: " + canEnter);
		
		Integer key3 = 45678;
		canEnter = client.clearCanEnterMap(key3);
		System.out.println("here is canEnter: " + canEnter);
	}
	
	@Test
	public void mapTest() {
		
		Map<Integer, Integer> fooMap = new HashMap<Integer, Integer>();
		fooMap.put(1,2);
		fooMap.put(3,4);
		System.out.println("here is foomap: " + fooMap);
		Integer ret = fooMap.remove(3);
		System.out.println("here is foomap: " + fooMap + ". ret: " + ret);
		ret = fooMap.remove(99);
		System.out.println("END. here is foomap: " + fooMap + ". ret: " + ret);
		
	}
	
	
	@Test
	public void clientTest() throws ThetaExceptionExc {
		
		Integer uniqueIdAmzn = 12346;
		Integer uniqueIdOrcl = 12348;
		Integer uniqueIdFB = 12350;
		client.connect(7496);
		
		client.getResp().setErrorCode(0);
		client.getResp().setErrorId(0);
		client.getResp().setErrorMsg("NO ERROR");
		client.getResp().setAllPricesToZero(uniqueIdAmzn);
		client.getResp().setAllPricesToZero(uniqueIdOrcl);
		client.getResp().setAllPricesToZero(uniqueIdFB);
		
		//log.info("Calling reqMktDataStk: " + symbol + " : " + uniqueIdForMktData );
		client.reqMktDataStk("AMZN", uniqueIdAmzn);
		client.reqMktDataStk("ORCL", uniqueIdOrcl);
		client.reqMktDataStk("FB", uniqueIdFB);

    	for (int i=0; i<10; i++){
    		//util.secondsToSleep(ThetaConstants.ONE_INT);
    		
    		try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		System.out.println("Amzn Close: " + client.getResp().getPriceClose(uniqueIdAmzn));
    		System.out.println("Orcl Close: " + client.getResp().getPriceClose(uniqueIdOrcl));
    		System.out.println("FB Close: " + client.getResp().getPriceClose(uniqueIdFB));
    		System.out.println("resp: " + client.getResp());
    		
    		//System.out.println("Waiting: " + i);
    		//if ( !client.resp.getPriceBid(uniqueId).equals(ThetaConstants.ZERO_INT) && !client.resp.getPriceAsk(uniqueId).equals(ThetaConstants.ZERO_INT) ) {
    		//	break;
    		//}
    	}

    	client.getResp().clearPriceClose(uniqueIdAmzn);
		System.out.println("resp: " + client.getResp());
    	client.getResp().clearPriceClose(uniqueIdOrcl);
		System.out.println("resp: " + client.getResp());
    	client.getResp().clearPriceClose(uniqueIdFB);
		System.out.println("resp: " + client.getResp());
    	client.getResp().clearPriceClose(uniqueIdFB = 100);
		System.out.println("resp: " + client.getResp());
    	
    	//Integer priceBid = client.resp.getPriceBid(uniqueId);
    	//Integer priceAsk = client.resp.getPriceAsk(uniqueId);

    	//System.out.println("PriceBid, priceAsk: " + priceBid + ", " + priceAsk);
		
	}
	
	
}

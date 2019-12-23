package com.theta.process;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.theta.domain.SecurityPrice;

/**
 * Class to run the service as a JUnit test. Each operation in the service is a separate test.
 *
 */

public class ThetaUtilTestSimple {

	private static Logger log = Logger.getLogger(ThetaUtilTestSimple.class);
	
	@Test
	public void testAreAllPricesWithLastPriceIdentical(){
		ThetaUtil thetaUtil = new ThetaUtil();
		Set<SecurityPrice> spSet = new HashSet<SecurityPrice>();

		// 0 size
		Assert.assertTrue(thetaUtil.areAllPricesIdentical(spSet,0));
				
		// True case
		Integer REFERENCE_PRICE = 1234;
		Integer REFERENCE_PRICE_2 = 1235;

		SecurityPrice sp = new SecurityPrice();
		sp.setSecurityPriceId(1);
		sp.setPrice(REFERENCE_PRICE);
		spSet.add(sp);

		SecurityPrice sp2 = new SecurityPrice();
		sp2.setSecurityPriceId(2);
		sp2.setPrice(REFERENCE_PRICE);
		spSet.add(sp2);

		SecurityPrice sp3 = new SecurityPrice();
		sp3.setSecurityPriceId(3);
		sp3.setPrice(REFERENCE_PRICE);
		spSet.add(sp3);

		System.out.println("Size of spSet: " + spSet.size());
		
		for (SecurityPrice spx :  spSet) {
			System.out.println("--> " + spx.getPrice());
		}
		
		Assert.assertTrue(thetaUtil.areAllPricesIdentical(spSet, REFERENCE_PRICE));
	}
	
	
	@Test
	public void testAreAllPricesWithLastPriceDiff(){
		ThetaUtil thetaUtil = new ThetaUtil();
		Set<SecurityPrice> spSet = new HashSet<SecurityPrice>();

		// 0 size
		Assert.assertTrue(thetaUtil.areAllPricesIdentical(spSet,0));
				
		// True case
		Integer REFERENCE_PRICE = 1234;
		Integer REFERENCE_PRICE_2 = 1235;

		SecurityPrice sp = new SecurityPrice();
		sp.setSecurityPriceId(1);
		sp.setPrice(REFERENCE_PRICE);
		spSet.add(sp);

		SecurityPrice sp2 = new SecurityPrice();
		sp2.setSecurityPriceId(2);
		sp2.setPrice(REFERENCE_PRICE);
		spSet.add(sp2);

		SecurityPrice sp3 = new SecurityPrice();
		sp3.setSecurityPriceId(3);
		sp3.setPrice(REFERENCE_PRICE);
		spSet.add(sp3);

		System.out.println("Size of spSet: " + spSet.size());
		
		for (SecurityPrice spx :  spSet) {
			System.out.println("--> " + spx.getPrice());
		}
		
		Assert.assertFalse(thetaUtil.areAllPricesIdentical(spSet, REFERENCE_PRICE_2));
	
	}
	
	@Test
	public void testAreAllPricesDiff(){
		ThetaUtil thetaUtil = new ThetaUtil();
		Set<SecurityPrice> spSet = new HashSet<SecurityPrice>();

		// 0 size
		Assert.assertTrue(thetaUtil.areAllPricesIdentical(spSet,0));
				
		// False case
		Integer REFERENCE_PRICE = 1234;
		Integer REFERENCE_PRICE_2 = 1235;
		Integer REFERENCE_PRICE_3 = 1236;

		SecurityPrice sp = new SecurityPrice();
		sp.setSecurityPriceId(1);
		sp.setPrice(REFERENCE_PRICE);
		spSet.add(sp);

		SecurityPrice sp2 = new SecurityPrice();
		sp2.setSecurityPriceId(2);
		sp2.setPrice(REFERENCE_PRICE_2);
		spSet.add(sp2);

		System.out.println("Size of spSet: " + spSet.size());
		
		for (SecurityPrice spx :  spSet) {
			System.out.println("--> " + spx.getPrice());
		}
		
		Assert.assertFalse(thetaUtil.areAllPricesIdentical(spSet, REFERENCE_PRICE_3));
	
	}
	
	
	@Test
	public void testLeftmostChars() {
	
		Assert.assertEquals("abcd", ThetaUtil.leftmostChars("abcde", 4));
		Assert.assertEquals("abcde", ThetaUtil.leftmostChars("abcde", 5));
		Assert.assertEquals("abcde", ThetaUtil.leftmostChars("abcde", 6));
		Assert.assertEquals("abcde", ThetaUtil.leftmostChars("abcde", 10));
	}
	
}


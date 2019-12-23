package com.theta.process;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.theta.client.ThetaClient3;
import com.theta.domain.SecurityPrice;
//import com.theta.client.ThetaClientTestLoc;
//import com.theta.service.MailService;

/**
 * Class to run the service as a JUnit test. Each operation in the service is a separate test.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners( {
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = {
		"file:./src/main/resources/theta11-generated-security-context.xml",
		"file:./src/main/resources/theta11-security-context.xml",
		"file:./src/main/resources/theta11-generated-service-context.xml",
		"file:./src/main/resources/theta11-service-context.xml",
		"file:./src/main/resources/theta11-generated-dao-context.xml",
		"file:./src/main/resources/theta11-dao-context.xml",
		"file:./src/main/resources/theta11-generated-web-context.xml",
		"file:./src/main/resources/theta11-web-context.xml" })
@Transactional
public class ThetaUtilTest {

	private static Logger log = Logger.getLogger(ThetaUtilTest.class);
	
	@Mock
	ThetaClient3 client;
	
	//@Autowired
	//private ThetaUtil thetaUtil;
	
	@Test
	public void testCheckAndReturnPriceBidAskHalfOK() throws Exception {
		ThetaUtil thetaUtil = new ThetaUtil();
		Integer ret = thetaUtil.checkAndReturnPriceBidAskHalf("Foo",-1,2700,2704,client);
		Assert.assertTrue(ret.equals(2702));
	}

	@Test(expected=NullPointerException.class)
	public void testCheckAndReturnPriceBidAskHalfPricesOff() throws Exception {
		System.out.println("START");
		ThetaUtil thetaUtil = new ThetaUtil();
		Integer ret = thetaUtil.checkAndReturnPriceBidAskHalf("Foo",-1,2701,2901, client);
		System.out.println("Here is ret: " + ret);
	}
	
	@Test(expected=ThetaExceptionExc.class)
	public void testCheckAndReturnPriceBidAskHalfPricesOffZero() throws Exception {
		System.out.println("START");
		ThetaUtil thetaUtil = new ThetaUtil();
		Integer ret = thetaUtil.checkAndReturnPriceBidAskHalf("Foo",-1,0,2901, client);
		System.out.println("Here is ret: " + ret);
	}

	@Test
	public void testAreAllPricesIdentical(){
		ThetaUtil thetaUtil = new ThetaUtil();
		Set<SecurityPrice> spSet = new HashSet<SecurityPrice>();
		// 0 size
		Assert.assertTrue(thetaUtil.areAllPricesIdentical(spSet));
				
		// True case
		SecurityPrice sp = new SecurityPrice();
		sp.setPrice(1234);
		sp.setSecurityPriceId(1);
		spSet.add(sp);

		sp.setSecurityPriceId(2);
		spSet.add(sp);

		sp.setSecurityPriceId(3);
		spSet.add(sp);
		System.out.println("Size of spSet: " + spSet.size());
		Assert.assertTrue(thetaUtil.areAllPricesIdentical(spSet));
		
		// False case - many
		SecurityPrice sp2 = new SecurityPrice();
		sp2.setPrice(1235);
		sp2.setSecurityPriceId(4);
		spSet.add(sp2);

		Assert.assertFalse(thetaUtil.areAllPricesIdentical(spSet));

		// False case - two
		SecurityPrice sp3 = new SecurityPrice();
		sp3.setPrice(1235);
		sp3.setSecurityPriceId(5);
		SecurityPrice sp4 = new SecurityPrice();
		sp4.setPrice(1236);
		sp4.setSecurityPriceId(6);
		spSet = new HashSet<SecurityPrice>();
		spSet.add(sp3);
		spSet.add(sp4);
		Assert.assertFalse(thetaUtil.areAllPricesIdentical(spSet));

	}

	
	@Test
	public void testAreAllPricesIdenticalTwoParms(){
		ThetaUtil thetaUtil = new ThetaUtil();
		Set<SecurityPrice> spSet = new HashSet<SecurityPrice>();
		// 0 size
		Assert.assertTrue(thetaUtil.areAllPricesIdentical(spSet));
				
		// True case
		SecurityPrice sp = new SecurityPrice();
		sp.setPrice(1234);
		sp.setSecurityPriceId(1);
		spSet.add(sp);

		sp.setSecurityPriceId(2);
		spSet.add(sp);

		sp.setSecurityPriceId(3);
		spSet.add(sp);
		System.out.println("Size of spSet: " + spSet.size());
		Assert.assertTrue(thetaUtil.areAllPricesIdentical(spSet,1234));
		
		// False case - many
		SecurityPrice sp2 = new SecurityPrice();
		sp2.setPrice(1235);
		sp2.setSecurityPriceId(4);
		spSet.add(sp2);
		Assert.assertFalse(thetaUtil.areAllPricesIdentical(spSet,5));

		// False case - many with diff extra
		SecurityPrice sp3 = new SecurityPrice();
		sp3.setPrice(1235);
		sp3.setSecurityPriceId(4);
		spSet.add(sp3);
		Assert.assertFalse(thetaUtil.areAllPricesIdentical(spSet,5));
	
	}

	
}


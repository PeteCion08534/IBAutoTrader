package com.theta.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.theta.client.enums.EnterOrExitCode;
import com.theta.enums.UpOrDownCode;
import com.theta.process.ThetaExceptionExc;

public class SpreadTest  {

	Spread spread = null;
	
	@Before
	public void setUp() {
		spread = new Spread();
	}
	
	//public boolean isWithinLimits(UpOrDownCode upOrDownCode, EnterOrExitCode enterOrExitCode, Integer underlyingSymbolPrice) {

	@Test
	public void testIsNotWithinLimitsUpEnter() throws ThetaExceptionExc  {
		spread.setEnterStopPrice(3000);
		spread.setEnterLimitPrice(2500);
		Assert.assertFalse(spread.isWithinLimits(UpOrDownCode.UP, EnterOrExitCode.ENTER, 3001));
	}
	@Test
	public void testIsNotWithinLimitsUpStopZeroEnter() throws ThetaExceptionExc  {
		spread.setEnterStopPrice(0);
		spread.setEnterLimitPrice(2500);
		Assert.assertFalse(spread.isWithinLimits(UpOrDownCode.UP, EnterOrExitCode.ENTER, 2499));
	}
	@Test
	public void testIsWithinLimitsUpLimitZeroEnter() throws ThetaExceptionExc  {
		spread.setEnterStopPrice(3000);
		spread.setEnterLimitPrice(0);
		Assert.assertTrue(spread.isWithinLimits(UpOrDownCode.UP, EnterOrExitCode.ENTER, 2999));
	}
	@Test
	public void testIsNotWithinLimitsDownEnter() throws ThetaExceptionExc  {
		spread.setEnterLimitPrice(3000);
		spread.setEnterStopPrice(2500);
		Assert.assertFalse(spread.isWithinLimits(UpOrDownCode.DOWN, EnterOrExitCode.ENTER, 2499));
	}
	@Test
	public void testIsNotWithinLimitsUpExit() throws ThetaExceptionExc  {
		spread.setExitStopPrice(3000);
		spread.setExitLimitPrice(2500);
		Assert.assertFalse(spread.isWithinLimits(UpOrDownCode.UP, EnterOrExitCode.EXIT, 3001));
	}
	@Test
	public void testIsNotWithinLimitsUpStopZeroExit() throws ThetaExceptionExc  {
		spread.setExitStopPrice(0);
		spread.setExitLimitPrice(2500);
		Assert.assertFalse(spread.isWithinLimits(UpOrDownCode.UP, EnterOrExitCode.EXIT, 2499));
	}
	public void testIsNotWithinLimitsUpLimitZeroExit() throws ThetaExceptionExc  {
		spread.setExitStopPrice(3000);
		spread.setExitLimitPrice(0);
		Assert.assertFalse(spread.isWithinLimits(UpOrDownCode.UP, EnterOrExitCode.EXIT, 3001));
	}
	@Test
	public void testIsNotWithinLimitsDownExit() throws ThetaExceptionExc  {
		spread.setExitLimitPrice(3000);
		spread.setExitStopPrice(2500);
		Assert.assertFalse(spread.isWithinLimits(UpOrDownCode.DOWN, EnterOrExitCode.EXIT, 2499));
	}
	@Test
	public void testIsWithinLimitsUpEnter() throws ThetaExceptionExc {
		spread.setEnterStopPrice(3000);
		spread.setEnterLimitPrice(2500);
		Assert.assertTrue(spread.isWithinLimits(UpOrDownCode.UP, EnterOrExitCode.ENTER, 2999));
	}
	@Test
	public void testIsWithinLimitsDownEnter() throws ThetaExceptionExc {
		spread.setEnterLimitPrice(3000);
		spread.setEnterStopPrice(2500);
		Assert.assertTrue(spread.isWithinLimits(UpOrDownCode.DOWN, EnterOrExitCode.ENTER, 2501));
	}
	@Test
	public void testIsWithinLimitsUpExit() throws ThetaExceptionExc {
		spread.setExitStopPrice(3000);
		spread.setExitLimitPrice(2500);
		Assert.assertTrue(spread.isWithinLimits(UpOrDownCode.UP, EnterOrExitCode.EXIT, 2501));
	}
	@Test
	public void testIsWithinLimitsDownExit() throws ThetaExceptionExc {
		spread.setExitLimitPrice(3000);
		spread.setExitStopPrice(2500);
		Assert.assertTrue(spread.isWithinLimits(UpOrDownCode.DOWN, EnterOrExitCode.EXIT, 2501));
	}

	@Test (expected = ThetaExceptionExc.class)
	public void testUpStopLTLimit() throws ThetaExceptionExc {
		spread.setExitLimitPrice(3100);
		spread.setExitStopPrice(3000);
		spread.isWithinLimits(UpOrDownCode.UP, EnterOrExitCode.EXIT, 3001);
	}

	@Test (expected = ThetaExceptionExc.class)
	public void testDownStopGTLimit() throws ThetaExceptionExc {
		spread.setExitStopPrice(5001);
		spread.setExitLimitPrice(5000);
		spread.isWithinLimits(UpOrDownCode.DOWN, EnterOrExitCode.EXIT, 4501);
	}
	
	
	@Test (expected = ThetaExceptionExc.class)
	public void testIsNotWithinLimitsNullLimit() throws ThetaExceptionExc  {
		Assert.assertTrue(spread.isWithinLimits(UpOrDownCode.DOWN, EnterOrExitCode.EXIT, null));
	}

	@Test (expected = ThetaExceptionExc.class)
	public void testIsNotWithinLimitsNullPrice() throws ThetaExceptionExc  {
		Assert.assertTrue(spread.isWithinLimits(UpOrDownCode.DOWN, EnterOrExitCode.ENTER, null));
	}

	
}

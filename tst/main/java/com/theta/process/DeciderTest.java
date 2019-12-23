package com.theta.process;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.theta.enums.LoMidHiCode;
import com.theta.enums.UpOrDownCode;

public class DeciderTest {

	Decider decider;
	
	@Before
	public void setUp() {
		decider = new Decider();
	}
	
	@Test
	public void testChangePosition() {
		
		// which way, hasbeenLO, hasbeenMID, hasbeenHI, currently:
		// Going UP:
		// LO and has been MID and HI
		Assert.assertFalse(decider.changePosition(UpOrDownCode.UP, true, true, true, LoMidHiCode.LO));
		
		// MID and has been LO
		Assert.assertTrue(decider.changePosition(UpOrDownCode.UP, true, true, false, LoMidHiCode.MID));
	
		// MID and has NOT been LO
		Assert.assertFalse(decider.changePosition(UpOrDownCode.UP, false, true, false, LoMidHiCode.MID));
		
		// HI and has been MID
		Assert.assertTrue(decider.changePosition(UpOrDownCode.UP, false, true, true, LoMidHiCode.HI));
		
		// HI and has NOT been MID
		Assert.assertFalse(decider.changePosition(UpOrDownCode.UP, false, false, true, LoMidHiCode.HI));
		

		// Going DOWN:
		// HI and has been MID and LO
		Assert.assertFalse(decider.changePosition(UpOrDownCode.DOWN, true, true, true, LoMidHiCode.HI));
		
		// MID and has been HI
		Assert.assertTrue(decider.changePosition(UpOrDownCode.DOWN, false, true, true, LoMidHiCode.MID));
	
		// MID and has NOT been HI
		Assert.assertFalse(decider.changePosition(UpOrDownCode.DOWN, false, true, false, LoMidHiCode.MID));
		
		// LO and has been MID
		Assert.assertTrue(decider.changePosition(UpOrDownCode.DOWN, true, true, false, LoMidHiCode.LO));
		
		// LO and has NOT been MID
		Assert.assertFalse(decider.changePosition(UpOrDownCode.DOWN, true, false, false, LoMidHiCode.LO));
		
	}
	
}

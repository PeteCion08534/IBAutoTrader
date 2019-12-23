package com.theta.domain;

import static org.mockito.Mockito.mock;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.theta.dao.SecurityPriceDAO;
import com.theta.enums.MovingAverageType;
import com.theta.enums.UpOrDownCode;
import com.theta.process.MovingAverages;
import com.theta.process.MovingAverages.AllMovingAverages;
import com.theta.process.ThetaMutex;

public class IsSignalDeadTest {

	Position position = null;
	Integer currentSecurityPrice = 3000;
	Integer averageDailyRange = 150;
	SecurityPriceDAO securityPriceDAOMock = null;
	AllMovingAverages allMovAvgs = null;
	
	@Before
	public void setUp() {
		position = new Position();
		position.setStkHasBeenMid(true);
		securityPriceDAOMock = mock(SecurityPriceDAO.class);

		ThetaMutex dbAccessMutex = new ThetaMutex();
		MovingAverages ma = new MovingAverages(securityPriceDAOMock, dbAccessMutex);
		Mockito.when(securityPriceDAOMock.findMovingAverage(Matchers.any(Calendar.class), Matchers.any(Calendar.class), Matchers.eq("TEST"))).thenReturn(currentSecurityPrice);
		Mockito.when(securityPriceDAOMock.findMovingAverageDailyRange(Matchers.any(Calendar.class), Matchers.any(Calendar.class), Matchers.eq("TEST"))).thenReturn(averageDailyRange);
		ma.getMovingAverage("TEST", 20, false, MovingAverageType.DAILYPRICE);
		ma.getMovingAverage("TEST", 50, false, MovingAverageType.DAILYPRICE);
		allMovAvgs = ma.getAllMovingAverages("TEST", 28, 68, 20, currentSecurityPrice, 1, 1);
		System.out.println("allMovAvgs: " + allMovAvgs);
	}

	@Test
	public void testIsSignalDeadUpNotDead() {
		Integer ADRBPToKillSignal = 100;
		currentSecurityPrice = 3000;
		UpOrDownCode upOrDownCode = UpOrDownCode.UP;
		boolean signalDead = position.isSignalDead(currentSecurityPrice, ADRBPToKillSignal, allMovAvgs, upOrDownCode);
		Assert.assertFalse(signalDead);
	}
	@Test
	public void testIsSignalDeadUpDead() {
		Integer ADRBPToKillSignal = 100;
		currentSecurityPrice = 3200;
		UpOrDownCode upOrDownCode = UpOrDownCode.UP;
		boolean signalDead = position.isSignalDead(currentSecurityPrice, ADRBPToKillSignal, allMovAvgs, upOrDownCode);
		Assert.assertTrue(signalDead);
	}
	@Test
	public void testIsSignalDeadUpDeadMidIsZero() {
		Integer ADRBPToKillSignal = 100;
		currentSecurityPrice = 3200;
		position.setStkHasBeenMid(false);
		UpOrDownCode upOrDownCode = UpOrDownCode.UP;
		boolean signalDead = position.isSignalDead(currentSecurityPrice, ADRBPToKillSignal, allMovAvgs, upOrDownCode);
		Assert.assertFalse(signalDead);
	}
	@Test
	public void testIsSignalDeadDownNotDead() {
		Integer ADRBPToKillSignal = 100;
		currentSecurityPrice = 2950;
		UpOrDownCode upOrDownCode = UpOrDownCode.DOWN;
		boolean signalDead = position.isSignalDead(currentSecurityPrice, ADRBPToKillSignal, allMovAvgs, upOrDownCode);
		Assert.assertFalse(signalDead);
	}
	@Test
	public void testIsSignalDeadDownDead() {
		Integer ADRBPToKillSignal = 100;
		currentSecurityPrice = 2800;
		UpOrDownCode upOrDownCode = UpOrDownCode.DOWN;
		boolean signalDead = position.isSignalDead(currentSecurityPrice, ADRBPToKillSignal, allMovAvgs, upOrDownCode);
		Assert.assertTrue(signalDead);
	}
}

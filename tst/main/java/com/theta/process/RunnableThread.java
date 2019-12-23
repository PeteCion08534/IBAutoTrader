package com.theta.process;

import java.util.Calendar;
import java.util.Set;

import com.theta.client.DateUtil;
import com.theta.dao.PositionDAO;
import com.theta.domain.Position;
import com.theta.enums.InstrumentCode;

import com.theta.process.SyncTest;

public class RunnableThread implements Runnable {

	
	Thread runner;
	String threadName;
	PositionDAO positionDAO = null;
	Position pos;
	SyncTest syncTest;
	ThetaMutex mutex;
	
	//public RunnableThread() {
	//	System.out.println("In RunnableThread() Constructor ");
	//}
	
	//public RunnableThread(String threadName, PositionDAO positionDAO) {
	public RunnableThread(String threadName, SyncTest syncTest, ThetaMutex mutex) {
		System.out.println("RunnableThread constructor. ThreadName: " + threadName);
		this.threadName = threadName;
		this.syncTest = syncTest;
		this.mutex = mutex;
		//this.positionDAO = positionDAO;
		//runner = new Thread(this, threadName); // (1) Create a new thread.
		//System.out.println("RunnableThread constructor: " + runner.getName());
		//runner.start(); // (2) Start the thread.
	}

	@Override
	public void run() {
		//Display info about this particular thread
		System.out.println("Start of run: " + threadName);

//		if (
//				this.threadName.equals("Threadname:1") ||
//				this.threadName.equals("Threadname:3") ||
//				this.threadName.equals("Threadname:5") ){
//			syncTest.test(this.threadName);
//			syncTest.testOther(this.threadName);
//		} else {
//			syncTest.testOther(this.threadName);
//			syncTest.test(this.threadName);
//		}
		this.syncTestLocal(this.threadName);
		
		System.out.println("After run: " + threadName);
		
		//for (int i = 0; i<3; i++) {
		//	System.out.println("In CountOut - BEFORE findPositionsByStrategyAcctId - Thread: " + threadName + ": " + i);
			
			//pos = positionFactory("thread-" + threadName + ".num-" + i + "A");

			//positionDAO.store(pos);/
			//for (int j = 0;j<1000;j++ ){}
			//try {
				//System.out.println("BEFORE thread.sleep()");
				//Thread.sleep(200);
				//System.out.println("AFTER thread.sleep()");
				
				//Set<Position> positions = positionDAO.findPositionsByStrategyAcctId(15);
				//for (Position p : positions) {
				//	System.out.println("p - thread : " + threadName + " : " + i + "" + p);
				//}
				
			
				//try {
				//	Thread.sleep(10000);
				//} catch (InterruptedException e) {
				//	// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
				//pos.setStrategyName("thread-" + threadName + ".num-" + i + "B");
				//positionDAO.store(pos);
				
			//	try {
			//		Thread.sleep(2000);
			//	} catch (InterruptedException e) {
			//		// TODO Auto-generated catch block
			//		e.printStackTrace();
			//	}

			//} catch (Exception e) {
			//	System.out.println("IN Exception!");
			//	e.printStackTrace();
			//}
			//System.out.println("In CountOut - AFTER findPositionsByStrategyAcctId - Thread: " + threadName + ": " + i);
		//}
	}
	
	
	private synchronized void syncTestLocal(String threadname) {
		
		synchronized(this.mutex) {
		for (int i=0;i<5;i++) {
			System.out.println("In testSync LOCAL: " + threadname + ". cycle: " + i);
			
			ThetaUtil.secondsToSleepNoThrow(2);
			//try {
			//	Thread.sleep(2000);
			//} catch (InterruptedException e) {
			//	// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
		}
		}

	}
	
	private Position positionFactory(String name) {

		Position posToAdd = new Position();
		posToAdd.setPositionId(ThetaConstants.INIT_ID);
		posToAdd.setInstrument(InstrumentCode.OPT.toString());
		posToAdd.setOptRight(ThetaConstants.CALL);
		posToAdd.setGoalNumSpreads(2);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		// grrrr - Calendar cludge - must add a month (January is 0!!)
		Integer theMonth = cal.get(Calendar.MONTH) + ThetaConstants.ONE_INT;
		Integer theYear = cal.get(Calendar.YEAR);
		Calendar theDayCal = DateUtil.getThirdFridayCal(theMonth, theYear);
		posToAdd.setExpiryDate(theDayCal);
		posToAdd.setExpiryYear(theYear);
		posToAdd.setExpiryMonth(theMonth);
		posToAdd.setExpiryDay(theDayCal.get(Calendar.DAY_OF_MONTH));
		posToAdd.setCanAddToDb(true);
		posToAdd.setNumOpenSpreads(ThetaConstants.ZERO_INT);
		posToAdd.setNumWins(ThetaConstants.ZERO_INT);
		posToAdd.setNumLosses(ThetaConstants.ZERO_INT);
		posToAdd.setProfitLossUnrealized(ThetaConstants.ZERO_INT);
		posToAdd.setProfitLossRealized(ThetaConstants.ZERO_INT);
		posToAdd.setLastExitSecurityPrice(ThetaConstants.ZERO_INT);
		posToAdd.setReentrySecPriceCallAbove(ThetaConstants.ZERO_INT);
		posToAdd.setReentrySecPricePutBelow(ThetaConstants.ZERO_INT);

		posToAdd.setTotalRisked(10000);

		posToAdd.setStrategyAccountId(1); 
		posToAdd.setStrategyName(name);
		posToAdd.setSymbol("TEST");
		posToAdd.setCreatedBy("addNewPositions");
		posToAdd.setCreatedDate(Calendar.getInstance());

		posToAdd.setUpdatedBy("addNewPositions");
		posToAdd.setUpdatedDate(Calendar.getInstance());

		return posToAdd;

	}


}

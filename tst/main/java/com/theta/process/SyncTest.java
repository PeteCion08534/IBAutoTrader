package com.theta.process;

public class SyncTest {

	public SyncTest() {
		
	}
	
	//public synchronized void testSync(String message) {
	public void test(String message) {
		
		for (int i=0;i<5;i++) {
			System.out.println("In testSync.test: " + message + ". cycle: " + i);
		
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void testOther(String message) {
		for (int i=0;i<5;i++) {
			System.out.println("In testSync.testOther: " + message + ". cycle: " + i);
		
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}

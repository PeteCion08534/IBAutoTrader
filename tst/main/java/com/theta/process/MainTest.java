package com.theta.process;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;

import com.theta.dao.PositionDAO;
import com.theta.process.SyncTest;


//@RunWith(SpringJUnit4ClassRunner.class)
//@TestExecutionListeners( {
//	DependencyInjectionTestExecutionListener.class,
//	DirtiesContextTestExecutionListener.class,
//	TransactionalTestExecutionListener.class })
//@Transactional
//@ContextConfiguration(locations = {
//		"file:./src/main/resources/theta11-generated-dao-context.xml"
//})
public class MainTest {

	//InitialContext ic = new InitialContext();
	@Autowired
	static PositionDAO positionDAO;
	
	
	//SimpleNamingContextBuilder cb = null;
	//@Autowired

	//@Test
	//public void mainTest() {
	public static void main(String[] args) {
		
		System.out.println("Top of main");
		for (int meta=0; meta<10; meta++) {
			
		
		SyncTest syncTest = new SyncTest();
		//com.theta.process.SyncTest syncTest = new com.theta.process.SyncTest();
		System.out.println("**** Top of meta: " + meta);
		ExecutorService executor = Executors.newFixedThreadPool(6);
	
		ThetaMutex mutex = new ThetaMutex();
		
		for (int i = 0; i<6; i++) {
			Runnable worker = new com.theta.process.RunnableThread("Threadname:" + i, syncTest, mutex);
			executor.execute(worker);
			System.out.println("in main loop: " + i);
		}
		System.out.println("End of loop");
		//System.out.println("Before shutdown: " + executor.isShutdown());
		//executor.shutdown();
		//System.out.println("AFTER shutdown: " + executor.isShutdown());
		
		//while (!executor.isTerminated()) {
		//	System.out.println("Executor is NOT terminated YET - waiting 500 MS to retry");
		//	try {
		//		Thread.sleep(500);
		//	} catch (InterruptedException e) {
		//		// TODO Auto-generated catch block
		//		e.printStackTrace();
		//	}
		//} 
		System.out.println("Finished all threads");
		}

//		System.out.println("Hello world!");
//		String contextFile = "file:./src/main/resources/theta11-generated-dao-context.xml";
//		//File contextFile = new File(ctx);
//
//		ContextBuilder cb = new ContextBuilder().withFile(contextFile);
//		GenericApplicationContext context = cb.getContext();
//		positionDAO = (PositionDAO) context.getBean("PositionDAO");
		
//		Set<Position> positions = positionDAO.findPositionsByStrategyAcctId(15);
//		for (Position p : positions) {
//			System.out.println("MAIN: " + p);
//		}
		
		//Thread thread1 = new Thread(new RunnableThread("thread1", positionDAO), "thread1");
		//Thread thread2 = new Thread(new RunnableThread("thread2", positionDAO), "thread2");
//		Thread thread1 = new Thread(new RunnableThread("thread1", positionDAO), "thread1");
//		Thread thread2 = new Thread(new RunnableThread("thread2", positionDAO), "thread2");
//		System.out.println("in mainTest. STEP A");
//		thread1.start();
//		System.out.println("in mainTest. STEP B");
//		thread2.start();
//		System.out.println("in mainTest. STEP C");
//
//		while (true) {
//			if (thread1.isAlive()){
//				System.out.println("Thread 1 is alive.");
//				try {
//					Thread.sleep(200);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			} else {
//				System.out.println("Thread 1 is not alive");
//				break;
//			}
//		}
//		System.out.println("after while loop.");
//		
//		try {
//			thread1.sleep(200);
//			thread2.sleep(200);
		
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		positionDAO.store(instance);
//		positionDAO.flush();

		
//		try {
//			//delay for one second
//			System.out.println("thread1 about to sleep:");
//			thread1.sleep(200);
//			System.out.println("thread2 about to sleep:");
//			thread2.sleep(200);
////		} catch (Exception e) {
//		} catch (InterruptedException e) {
//			System.out.println("InterruptedException!");
//			e.printStackTrace();
//		}
//		//Display info about the main thread
//		System.out.println(Thread.currentThread());

//		for (int i = 0; i<2; i++) {
//			Runnable c = new RunnableThread("num" + i);
//			Thread theThread = new Thread(new RunnableThread("num" + i), "thread" + i);
//			theThread.start();
//		}
//
//		Set<Position> positions = positionDAO.findPositionsByStrategyAcctId(15);
//		for (Position p : positions) {
//			System.out.println("p: " + p);
//		}

	}



}

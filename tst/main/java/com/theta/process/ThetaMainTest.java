package com.theta.process;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.SessionScope;

import com.theta.client.PositionExecutor;
import com.theta.client.PriceRetriever;
import com.theta.client.ThetaClientTestLoc;
import com.theta.dao.HeartbeatDAO;
import com.theta.dao.IbAccountDAO;
import com.theta.dao.PositionDAO;
import com.theta.dao.ProfitLossDAO;
import com.theta.dao.RequestSeqDAO;
import com.theta.dao.SecurityPriceDAO;
import com.theta.dao.SpreadDAO;
import com.theta.dao.StrategyAccountDAO;
import com.theta.dao.StrategyDAO;
import com.theta.dao.ThetaExceptionDAO;
import com.theta.enums.MinOrMaxCode;
import com.theta.externaldata.EarningsDate;

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
		"file:./src/main/resources/theta11-generated-dao-context.xml",
		"file:./src/main/resources/theta11-dao-context.xml",
		"file:./src/main/resources/theta11-generated-web-context.xml",
		"file:./src/main/resources/theta11-web-context.xml",
		"file:./src/main/resources/theta11-service-context.xml"})
@Transactional
public class ThetaMainTest {

	private static Logger log = Logger.getLogger(ThetaMainTest.class);

	private ExecutorService executorService = Executors.newFixedThreadPool(2);

	/**
	 * The Spring application context.
	 *
	 */
	@SuppressWarnings("unused")
	private ApplicationContext context;

	/**
	 * The service being tested, injected by Spring.
	 *
	 */
	//@Autowired
	//protected PositionService service;

	@Autowired
	private HeartbeatDAO heartbeatDAO;
	//@Autowired
	//private HolidayDAO holidayDAO;
	@Autowired
	private IbAccountDAO ibAccountDAO;
	@Autowired
	private PositionDAO positionDAO;
	@Autowired
	private ProfitLossDAO profitLossDAO;
	//@Autowired
	//private SnapshotDAO snapshotDAO;
	//@Autowired
	//private SnapshotOptionDAO snapshotOptionDAO;	
	@Autowired
	private SpreadDAO spreadDAO;
	@Autowired
	private StrategyAccountDAO strategyAccountDAO;
	@Autowired
	private StrategyDAO strategyDAO;
	@Autowired
	private ThetaExceptionDAO thetaExceptionDAO;
	@Autowired
	private RequestSeqDAO requestSeqDAO;
	@Autowired
	private SecurityPriceDAO securityPriceDAO;
	//@Autowired
	//private ThetaClient3 client;
	private ThetaClientTestLoc client;
	@Autowired
	private EarningsDate earningsDate;
	
	private MovingAverages movingAverages;
	private ThetaMain main;
	//@Autowired
	//private ThetaUtil thetaUtil;
	//@Autowired
	//private PriceRetriever priceRetriever;
	//@Autowired
	//private PositionExecutor positionExecutor;
	
	private List<Calendar> holidayList = new ArrayList<Calendar>();
	
	
	@Test
	public void testThetaMain() throws Exception {
		log.info("in testTheTest!");
		System.out.println("In testTheTest!");
		ThetaClientTestLoc client = new ThetaClientTestLoc();
		//setupRequestContext();

		
		ThetaUtil thetaUtil = new ThetaUtil(thetaExceptionDAO, heartbeatDAO);
		for (int i=30; i>0; i--){
			log.info("Seconds to start: " + i);
			ThetaUtil.secondsToSleepNoThrow(1);
		}

		PriceRetriever priceRetriever = new PriceRetriever(thetaUtil, requestSeqDAO);
		PositionExecutor positionExecutor = new PositionExecutor(thetaUtil, spreadDAO);
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		//MovingAverages movingAverages = new MovingAverages(securityPriceDAO);
		//try {
		//	thetaUtil = new ThetaUtil(thetaExceptionDAO, heartbeatDAO);
		//} catch (Exception e){
		//	e.printStackTrace();
		//	return;
		//}
		//Slope slope = new Slope(securityPriceDAO);
		main = null;
		try {	
			main = new ThetaMain
			 (positionDAO,
			  spreadDAO,
			  strategyAccountDAO,
			  strategyDAO,
			  requestSeqDAO,
			  profitLossDAO,
			  ibAccountDAO,
			  securityPriceDAO,
			  thetaUtil,
			  client,
			  earningsDate,
			 holidayList,
			 priceRetriever,
			 positionExecutor,
			 executorService) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			log.info("ABOUT to insert into security price!");
			//main.insertIntoSecurityPrice("SPYY", 121);

			Calendar last90 = Calendar.getInstance();
			last90.add(Calendar.MINUTE, -90);
			String theTicker =  "SPY";
			Integer minOrMax = securityPriceDAO.findMinOrMaxPriceSinceDateTime(last90, theTicker, MinOrMaxCode.MIN);
			System.out.println("here is minOrMax price: " + minOrMax);
			//main.rollUpRecordKeeping(strategyAccount);
			//log.info("************* END OF strategyAccount: " + strategyAccount.toString());
			thetaUtil.secondsToSleep(6);
		} catch (Exception e2){
			e2.printStackTrace();
		}

		
		
		//IbAccount ib = new IbAccount();
		//ib.setPort(7496);
		//try {
		//	main.connectClient(ib);
		//} catch (Exception e){
		//	e.printStackTrace();
		//}
		
		//BalanceIntVsExt bal = new BalanceIntVsExt(spreadDAO, client, thetaUtil);
		//ArrayList<OptionCompare> fromIB = new ArrayList<OptionCompare>();
		//bal.addIBInfoToFromIB(fromIB, "DU71402");
		
		//main.testHailMary();
		
		log.info("About to sleep!");
		try {
			thetaUtil.secondsToSleep(3);
		} catch (ThetaExceptionExc e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("JUST SLEPT!");
		
	}
	
	/**
	 * Instantiates a new PositionServiceTest.
	 *
	 */
	//@Ignore
	//public void testThetaMain() {


		//Set<StrategyAccount> strategyAccounts = strategyAccountDAO.findAllStrategyAccounts();
		//for(StrategyAccount strategyAccount : strategyAccounts){
			//log.info("************* Here is strategyAccount: " + strategyAccount.toString());
			/**
			 * Do the record keeping at the top!  This rolls up profit and loss - realized and unrealized
			 * from spread through to position.
			 * Then, at the top of each hour writes to the profit_loss table
			 */

			//try {
				//main.insertIntoSecurityPrice("SPYY", 121,"TEST", true, 119, 125, 10, null);
				//main.rollUpRecordKeeping(strategyAccount);
				//log.info("************* END OF strategyAccount: " + strategyAccount.toString());
			//	thetaUtil.secondsToSleep(6);
			//} catch (Exception e2){
			//	e2.printStackTrace();
			//}
	//}		

	/**
	 * Autowired to set the Spring application context.
	 *
	 */
	@Autowired
	public void setContext(ApplicationContext context) {
		this.context = context;
		((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("session", new SessionScope());
		((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("request", new RequestScope());
	}

	/**
	 * Sets Up the Request context
	 *
	 */
	private void setupRequestContext() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attributes);
	}
}


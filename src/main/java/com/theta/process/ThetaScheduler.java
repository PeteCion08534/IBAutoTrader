package com.theta.process;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.theta.client.PositionExecutor;
import com.theta.client.PriceRetriever;
import com.theta.client.ThetaClientInterface;
import com.theta.dao.HeartbeatDAO;
import com.theta.dao.HolidayDAO;
import com.theta.dao.IbAccountDAO;
import com.theta.dao.PositionDAO;
import com.theta.dao.ProfitLossDAO;
import com.theta.dao.RequestSeqDAO;
import com.theta.dao.SecurityPriceDAO;
import com.theta.dao.SpreadDAO;
import com.theta.dao.StrategyAccountDAO;
import com.theta.dao.StrategyDAO;
import com.theta.dao.ThetaExceptionDAO;
import com.theta.domain.Holiday;
import com.theta.externaldata.EarningsDate;


/**
 * @author pcion
 *
 */
@Service
public class ThetaScheduler {
	
	/**
	 * Objects injected by Spring 
	 * 
	 */

	@Autowired
	private HeartbeatDAO heartbeatDAO;
	@Autowired
	private HolidayDAO holidayDAO;
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
	@Autowired
	private ThetaClientInterface client;
	@Autowired
	private EarningsDate earningsDate;
	//private ThetaClientTestLoc client;
	//private MovingAverages movingAverages;
		
	//@Autowired
	//private MailService mail;

//	private ThetaUtil thetaUtil;
//	private PriceRetriever priceRetriever;
//	private PositionExecutor positionExecutor;
//	private ExecutorService executorService;	
	
	/**
	 * The Logger
	 *
	 */
	private static Logger log = Logger.getLogger(ThetaScheduler.class);

	private static boolean hasQueueStarted = false;
	//private List<Calendar> holidayList = new ArrayList<Calendar>();
	private List<Calendar> holidayList = null;

//	public ThetaScheduler() {
//		executorService = Executors.newFixedThreadPool(5);
//		thetaUtil = new ThetaUtil(thetaExceptionDAO, heartbeatDAO);
//		priceRetriever = new PriceRetriever(thetaUtil, requestSeqDAO);
//		positionExecutor = new PositionExecutor(thetaUtil, spreadDAO);
//		
//		log.info("In ThetaScheduler - constructor");
//		if (requestSeqDAO == null) {
//			log.info("requestSeqDAO is null");
//		} else {
//			log.info("requestSeqDAO is NOT null");
//		}
//		
//	}
	
	
	/**
	 * 
	 * triggerTheata triggers the process each second.
	 * Please note that as this is SINGLE THREADED, it will not trigger as long as 
	 * there is an existing process that is running.
	 * 
	 * @throws ThetaExceptionExc
	 */
	//				 s m h dom mon dow
	@Scheduled(cron="* * * *   *   *")
	public void triggerTheta() throws ThetaExceptionExc {
		log.info("TOP of Trigger Theta");

		ThetaUtil.debugWarningMessage();
		
		//BackPrices bp = new BackPrices();
		//bp.fillBackPrices("NHTC",90,securityPriceDAO,null);
		//log.info("Called BackPrices.");
		
		
		//if (movingAverages == null) {
		//	log.info("MovingAverages is null - instantiating!");
		//	movingAverages = new MovingAverages(securityPriceDAO);
		//} else {
		//	log.info("MovingAverages is not null - NOT re-instantiating!");
		//}
		
		if (holidayList == null) {
			Set<Holiday> holidays = holidayDAO.findAllHolidays();
			holidayList = new ArrayList<Calendar>();
			for (Holiday h : holidays) {
				holidayList.add(h.getHolidayDate());
			}
		}
		
		try {	

			ThetaUtil thetaUtil = new ThetaUtil(thetaExceptionDAO, heartbeatDAO);
			for (int i=30; i>0; i--){
				log.info("Seconds to start: " + i);
				ThetaUtil.secondsToSleepNoThrow(1);
			}

			/// TODO: Inject via Spring
			PriceRetriever priceRetriever = new PriceRetriever(thetaUtil, requestSeqDAO);
			PositionExecutor positionExecutor = new PositionExecutor(thetaUtil, spreadDAO);
			ExecutorService executorService = Executors.newFixedThreadPool(5);
			
			//SpreadTest spreadTest = new SpreadTest(spreadDAO);
			//spreadTest.run();
			
			ThetaMain main = new ThetaMain
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
			  executorService);
			
			  main.main();			
			  
		} catch (ThetaExceptionExc e) {
			log.error("in MAIN: Exception: ", e);
			e.printStackTrace();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			log.error("in MAIN: Caught DataAccessException", e);
			e.printStackTrace();
		} catch (Throwable th){
			log.error("in MAIN: Caught Throwable", th);
			th.printStackTrace();
		}
		log.info("Bottom of Trigger Theta");
	}		
}


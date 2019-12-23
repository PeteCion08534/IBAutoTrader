package com.theta.queue;

import java.util.Calendar;

import org.apache.log4j.Logger;

public class ServiceQueue implements Runnable {

	private static Logger log = Logger.getLogger(ServiceQueue.class);

	public ServiceQueue ()
	{
	}

	@Override
	public void run() {

		JmsClient jmsClientRequest = new JmsClient();
		jmsClientRequest.connect("quoteRequestQueue");		

		JmsClient jmsClientResponse = new JmsClient();

		while (true) {
			log.info("in ServiceQueue.run() - top of while loop.");
			
			try {
				
				/**
				 * Read off quote request queue
				 */
				String msg = null;
				try {
					msg = jmsClientRequest.receiveMessage(10);
				} catch (Exception receiveEx) {
					log.error("Receive exception.  Re-connecting ...", receiveEx);
					jmsClientRequest.closeConnection();
					jmsClientRequest.connect("quoteRequestQueue");		
				}
					
				if (msg != null) {
					log.info("Here is the message received: " + msg);
					Calendar now = Calendar.getInstance();
					String responseMsg = "SystemTheta [" + now.getTime() + "] " + msg;
					/**
					 * Write to quote response queue
					 */
					log.info("Here is the message to respond: " + responseMsg);
					jmsClientResponse.connect("quoteResponseQueue");
					jmsClientResponse.sendMessage(responseMsg);
					//jmsClientResponse.sendMessage("");
					jmsClientResponse.closeConnection();

					/*
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
					//jmsClientResponse.closeConnection();
					//log.info("Closing ...");
					//jmsClientResponse.closeConnection();
				} else {
					log.info("message received is null.");
				}

				//jmsClientRequest.closeConnection();

			} catch (Throwable th) {
				log.error("Caught throwable in queue loop: ", th);
			}
		
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

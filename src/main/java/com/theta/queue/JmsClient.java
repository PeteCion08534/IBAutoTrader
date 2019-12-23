package com.theta.queue;

import java.util.Properties;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.NamingException;

public class JmsClient {
    private static final Logger log = Logger.getLogger(JmsClient.class.getName());
    
    private ConnectionFactory connectionFactory = null;
    private Connection connection = null;
    private Session session = null;
    private MessageProducer producer = null;
    private MessageConsumer consumer = null;
    private Destination destination = null;
    private Context context = null;
    
    private static final String username = "jmsAppUser";
    private static final String password = "M1k3Tys0n";
    private static final String url = "remote://sanhut.com:4447";
  	private static final String connection_factory = "jms/RemoteConnectionFactory";
  	private static final String initial_context_factory = "org.jboss.naming.remote.client.InitialContextFactory";
    
    /**
     * Provides connection to a single queue.  This queue is for both the producer and consumer.  Lick it!
     * @return boolean success
     */
    
    public boolean connect(String queueName) {
    	boolean retVal = false;
    	
    	String destinationQueue = "jms/queue/" + queueName;
    	
    	Properties properties = new Properties();
    	
    	System.out.println("Top of JmsClient.connect()");

    	try {
    		// Set up the context for the JNDI lookup
            Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, initial_context_factory);
            env.put(Context.PROVIDER_URL, url);
            env.put(Context.SECURITY_PRINCIPAL, username);
            env.put(Context.SECURITY_CREDENTIALS, password);
            //env.put("jboss.naming.client.ejb.context", true);
            context = new javax.naming.InitialContext(env);
            
            // Perform the JNDI lookups
            String connectionFactoryString = connection_factory;
            log.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
            System.out.println("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
            connectionFactory = (ConnectionFactory) context.lookup(connectionFactoryString);
            log.info("Found connection factory \"" + connectionFactoryString + "\" in JNDI");
            System.out.println("Found connection factory \"" + connectionFactoryString + "\" in JNDI");
 
            String destinationString = destinationQueue;
            log.info("Attempting to acquire destination \"" + destinationString + "\"");
            destination = (Destination) context.lookup(destinationString);
            log.info("Found destination \"" + destinationString + "\" in JNDI");

            System.out.println("Found destination \"" + destinationString + "\" in JNDI");

            // Create the JMS connection, session, producer, and consumer
            connection = connectionFactory.createConnection(username, password);
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(destination);
            consumer = session.createConsumer(destination);
            connection.start();
            retVal = true;
    	} catch (Exception e) {
            log.severe(e.getMessage());
            System.out.println("Here is exception: " + e.getMessage());
            e.printStackTrace();
            closeConnection();
            retVal = false;
    	}
    	
    	return retVal;
    }
    
    
    public boolean sendMessage(String content) {
    	boolean retVal = false;
    	TextMessage message = null;
    	
    	try {
			message = session.createTextMessage(content);
	        producer.send(message);
	        retVal = true;
		} catch (JMSException e) {
			log.severe(e.getMessage());
			closeConnection();
            retVal = false;
		}

    	return retVal;
    }
    
    /**
     * Returns null if there is no message on the queue.  
     * The default timeout is 5 seconds.  
     * Passing a long in milliseconds will override the default.
     * @return
     * The message.
     */
    public String receiveMessage() {
    	String content = null;
    	TextMessage message = null;
    	    	
    	try {
			message = (TextMessage) consumer.receive(5000);
			if (message != null) {
				content = message.getText();
			}			
		} catch (JMSException e) {
			log.severe(e.getMessage());
			closeConnection();
		}
        
    	return content;
    }
    
    /**
     * Returns null if there is no message on the queue.  
     * The default timeout is 5 seconds.  
     * Passing a long in milliseconds will override the default.
     * @return
     * The message.
     */
    
    public String receiveMessage(long timeoutInMillis) {
    	String content = null;
    	TextMessage message = null;
    	    	
    	try {
			message = (TextMessage) consumer.receive(timeoutInMillis);
			if (message != null) {
				content = message.getText();
			}			
		} catch (JMSException e) {
			log.severe(e.getMessage());
			closeConnection();
		}
        
    	return content;
    }
    
    
    public void closeConnection() {
    	/*
    	if (context != null) {
            try {
				context.close();
			} catch (NamingException e) {
				log.severe(e.getMessage());
			}
        }
    	*/
    	
        if (connection != null) {
            try {
				connection.close();
			} catch (JMSException e) {
				log.severe(e.getMessage());
			}
        }
    }
}
package com.theta.process;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class ThetaEmail {

	public static void main(String[] args) {
		System.out.println("FOOO!");
	}
	
	public static void send(String subject, String body)
	{    
		System.out.println("Start Email!");
		// Sender's email ID needs to be mentioned
		String from = "pete_cion@yahoo.com";
		String pass ="Rid3r14!";
		// Recipient's email ID needs to be mentioned.
		String to = "pete_cion@yahoo.com";
		String host = "smtp.mail.yahoo.com";

		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", from);
		properties.put("mail.smtp.password", pass);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try{
			System.out.println("Start of Try");
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(body);

			// Send message
			Transport transport = session.getTransport("smtp");
			System.out.println("About to connect");

			transport.connect(host, from, pass);
			System.out.println("About to send");
			transport.sendMessage(message, message.getAllRecipients());
			System.out.println("About to close");
			transport.close();
			System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}	


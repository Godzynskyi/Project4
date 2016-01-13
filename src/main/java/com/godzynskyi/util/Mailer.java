package com.godzynskyi.util;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	Session session; // session
	Properties properties; // properties
	Transport transport; // transport
	MimeMessage message; // message


	public Mailer() {
		properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		session = Session.getDefaultInstance(properties, null);

		message = new MimeMessage(session);
	}

	public void sendEmail(String toEmail, String subject, String body)
			throws MessagingException {

		/* set parameters for connection to gmail SMTP */
		String fromUser = "ivan.godzynskyi@gmail.com";
		String fromUserEmailPassword = "Verachka73";
		String emailHost = "smtp.gmail.com";
		/* get parameters for message from message.properties */
	

		message.setSubject(subject);
		message.setFrom(new InternetAddress("support@renter.com"));
		message.setText(body);
		
		message.addRecipient(javax.mail.Message.RecipientType.TO, 
                new InternetAddress(toEmail));


		/* create transport and send prepared message */
		transport = session.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	
	}
}

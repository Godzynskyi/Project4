package com.godzynskyi.util;

import com.godzynskyi.model.Order;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;

public class EmailService {
    private static final Logger logger = Logger.getLogger(EmailService.class);

    public static void sendEmail(final Order order) {
        // Recipient's email ID needs to be mentioned.
        String to = order.getClient().getEmail();
        if (to == null) return;
        String theme = "TRA TA TA";
        String message = "This is a text of message to client!";
        //TODO Need to add confirm link
        try {
            new Mailer().sendEmail(to, theme, message);
        } catch (MessagingException e) {
            logger.warn(e);
        }
    }
}

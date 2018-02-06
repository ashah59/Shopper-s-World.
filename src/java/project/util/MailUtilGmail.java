package project.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailUtilGmail {

    public static void sendMail(String toName, String to, String fromName, String from,
            String subject, String body, boolean bodyIsHTML)
            throws MessagingException, UnsupportedEncodingException {

        // 1 - get a mail session
        Properties props = new Properties();
        /*props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", 465);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");*/

        /*props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtps.quitwait", "false");*/
 
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        //Session session = Session.getDefaultInstance(props);
        //session.setDebug(true);
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ashah59@uncc.edu", "Myfl16!165");
            }
        });
        session.setDebug(true);

        // 2 - create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }

        // 3 - address the message
        Address fromAddress = new InternetAddress(from , fromName);
        Address toAddress = new InternetAddress(to, toName);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        message.setRecipient(Message.RecipientType.CC, fromAddress);

        // 4 - send the message
        /*Transport transport = session.getTransport();
        transport.connect("analshah1605@gmail.com", "xxxxx");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();*/
        
        
        /*Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", 465, "analshah1605@gmail.com", "xxxxx");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();*/
        
        Transport.send(message);
    }
}

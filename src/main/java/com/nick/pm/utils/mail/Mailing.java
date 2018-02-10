package com.nick.pm.utils.mail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class Mailing{

    private String HOST;

    private static final String username = "fetissov.n@gmail.com";
    private static final String password = "trivium1341341";
    static Properties props;

    public Mailing(String host) {
        this.HOST = host;
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "888");
        props.put("mail.smtp.connectiontimeout", "5000");
        props.put("mail.smtp.timeout", "4000");
        props.put("mail.smtp.port", "465");
    }

    private void send(String subject, String text, String toEmail) throws MailingException {
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

        } catch (MessagingException e) {

            throw new MailingException();
//            throw new RuntimeException(e);

        }
    }

    public void sendMailWithConfirmationLink(String toEmail, String email, long id) throws MailingException {
        org.springframework.security.crypto.password.PasswordEncoder encoder
                = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        String encodedEmail = encoder.encode(email);
        String link = makeLinkFromEncodedEmail(encodedEmail,id);
        String text = "Please click to confirmation link " + link;
        send("Task Builder",text,toEmail);
    }

    private String makeLinkFromEncodedEmail(String encodedEmail, long id){
        return HOST + "/confirm/" + id + "/" + encodedEmail;
    }


}
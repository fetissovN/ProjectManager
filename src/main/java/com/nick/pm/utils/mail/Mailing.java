package com.nick.pm.utils.mail;

import com.nick.pm.entity.Project;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
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
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.fallback", "false");
    }

//    private void send() throws IOException, MessagingException {
//        final Properties properties = new Properties();
//        properties.load(Mailing.class.getClassLoader().getResourceAsStream("mail.properties"));
//
//        Session mailSession = Session.getDefaultInstance(properties);
//        MimeMessage mimeMessage = new MimeMessage(mailSession);
//        mimeMessage.setFrom(new InternetAddress("fetissov.n@gmail.com"));
//        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("fetissov.n@gmail.com"));
//        mimeMessage.setSubject("hello");
//        mimeMessage.setText("asd");
//
//        Transport tr = mailSession.getTransport();
//        tr.connect(null,"trivium1341341");
//        tr.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
//        tr.close();
//
//    }

    private void sendOld(String subject, String text, String toEmail) throws MailingException {
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
        }
    }

    public void sendMailWithConfirmationLink(String toEmail,long id) throws MailingException {
        org.springframework.security.crypto.password.PasswordEncoder encoder
                = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        String encodedEmail = encoder.encode(toEmail);
        String link = makeLinkFromEncodedEmail(encodedEmail,id);
        String text = "Please click to confirmation link " + link;
        sendOld("Task Builder",text,toEmail);
    }

    private String makeLinkFromEncodedEmail(String encodedEmail, long id){
        return HOST + "/confirm/" + id + "/" + encodedEmail;
    }


}
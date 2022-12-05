package logic;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    private String sendTo;
    private final String sendFrom = "gps.aacrugby@gmail.com";
    private final String host = "localhost";
    public SendEmail(String sendTo){
        this.sendTo = sendTo;
    }

    public boolean sendEmail(String password){
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sendFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
            message.setSubject("Account Validation");
            message.setText("You have been added to the AACRugby Team App.\n Please login with this email and the password "+ password +".");
            Transport.send(message);
            return true;
        }catch (MessagingException mex){
            return false;
        }
    }
}

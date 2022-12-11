package logic;

import com.aspose.email.MailAddress;
import com.aspose.email.MailMessage;
import com.aspose.email.SmtpClient;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Properties;

public class SendEmail {
    private String sendTo;
    private final String sendFrom = "gps.aacrugby@hotmail.com";
    public SendEmail(String sendTo){
        this.sendTo = sendTo;
    }

    public boolean sendEmail(String password) throws MessagingException {
        MailMessage message = new MailMessage();

// Set subject of the message, body and sender information
        message.setFrom(new MailAddress(sendFrom, "AACRugby", false));
        message.setSubject("Account Validation");
        message.setBody("You have been added to the AACRugby Team App.\n Please login with this email and the password "+ password +".");

// Specify encoding
        message.setBodyEncoding(Charset.forName("US-ASCII"));

// Add To recipients and CC recipients
        message.getTo().addItem(new MailAddress(sendTo, "Recipient 1", false));

// Create an instance of SmtpClient Class
        SmtpClient client = new SmtpClient();

// Specify your mailing host server, Username, Password, Port
        client.setHost("smtp-mail.outlook.com");
        client.setUsername(sendFrom);
        client.setPassword("aac.TEAM13");
        client.setPort(587);

        try
        {
            // Client.Send will send this message
            client.send(message);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) throws MessagingException {
        SendEmail sendEmail = new SendEmail("mariacarrilho.05@gmail.com");
        sendEmail.sendEmail("allo");
    }
}

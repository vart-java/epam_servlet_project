package com.artuhin.project.notification;

import com.artuhin.project.model.EMailData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;

public class MailSender {

    private static MailSender ourInstance = new MailSender();
    private static Logger LOGGER = LogManager.getLogger(MailSender.class);

    public static MailSender getInstance() {
        return ourInstance;
    }

    private MailSender() {
    }

    public void sendMail(EMailData EMailData) {
        Properties properties = new Properties();
        try (InputStream is = getClass().getResourceAsStream("/properties/mail.properties")) {
            properties.load(is);
            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(properties.getProperty("mail.smtps.user"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(EMailData.getUserLogin()));
            message.setSubject("Feedback about the procedure " + EMailData.getProcedureName());
            message.setText("Hello, " + EMailData.getSimpleName(EMailData.getUserLogin()) + "! You underwent the procedure " + EMailData.getProcedureName() +
                    " in our salon on "+EMailData.getTimestamp().toLocalDateTime()+" by the master "+ EMailData.getSimpleName(EMailData.getMasterLogin())+
                    ". Please rate our master for the performed procedure by clicking on the link below " + System.lineSeparator() +
                    "http://localhost:8080/main?command=getRecall&id="+EMailData.getAppointmentId());
            Transport transport = mailSession.getTransport();
            transport.connect("user.notification.beauty.saloon@gmail.com", "beautysaloon1");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            LOGGER.error("exc", e);
        }
    }
}

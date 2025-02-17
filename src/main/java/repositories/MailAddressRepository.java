/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import models.IAlert;

/**
 *
 * @author geova
 */
public class MailAddressRepository {
    private Properties props = new Properties();
    private final Session mailSession;
    private final String originEmail = "nnyezg@gmail.com";

    public MailAddressRepository() {

        final String fromEmail = "nnyezg@gmail.com";
        final String password = "ubhzjxdsrztkvdqw";

        System.out.println("TLSEmail Start");
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", fromEmail);
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.setProperty("mail.smtp.auth", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        mailSession = Session.getInstance(props, auth);

    }

    public boolean sendEmail(String email, IAlert alert) {
        Message msg = new MimeMessage(mailSession);

        try {
            msg.setSubject(alert.getHeader());
            msg.setFrom(new InternetAddress(originEmail, "Sistema de Alerta"));
            msg.addRecipients(Message.RecipientType.TO,
                    new InternetAddress[] { new InternetAddress(email) });

            DataHandler dh = new DataHandler(alert.getMsg(), "text/plain");
            msg.setDataHandler(dh);

            Transport.send(msg);
            return true;

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

}

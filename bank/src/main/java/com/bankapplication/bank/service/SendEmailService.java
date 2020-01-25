package com.bankapplication.bank.service;

import com.bankapplication.bank.model.Account;
import com.bankapplication.bank.model.Transfer;
import com.bankapplication.bank.model.TransferStatus;

import java.io.*;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailService {

    private final static String host = "smtp.gmail.com";
    private static Properties emailProperties;
    private static Properties properties;

    private final static Session initSession() {
        emailProperties = getProperties("src/main/resources/email.cfg");
        properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProperties.getProperty("sender"), emailProperties.getProperty("password"));
            }
        });
        session.setDebug(true);
        return session;
    }

    public static void sendEmail(String emailTo) {
        sendEmail(emailTo, null, TransferStatus.UNSUCCESSFUL, null, null);
    }

    public static void sendEmail(String emailTo, Transfer transfer, TransferStatus transferStatus, Account accountFrom, Account accountTo) {
        try {
            MimeMessage message = new MimeMessage(initSession());
            message.setFrom(new InternetAddress(emailProperties.getProperty("sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject("Transfer details");
            if (transferStatus == TransferStatus.SUCCESS) {
                message.setText("Transfer from account " + accountFrom.getNrb() + " to account " + accountTo.getNrb() + " for the amount of " + transfer.getAmount() + " PLN has been sent");
            } else {
                message.setText("Sorry, something wrong with your transfer. Try again");
            }
            Transport.send(message);
            System.out.println("email has been sent");
        } catch (MessagingException mex) {
            System.out.println("email hasn't been sent");
        }
    }

    private static Properties getProperties(String filePath) {
        Properties properties = new Properties();
        try {
            FileReader file = new FileReader(filePath);
            properties.load(file);
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return properties;
    }
}
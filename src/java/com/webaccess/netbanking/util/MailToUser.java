/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.util;

import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Jay Prakash Started on: oct 15 2013 Ended on: oct 2013
 */
public class MailToUser {

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;
    String mailIDOFEmployee;

    /*setting server mail properties*/
    private void setMailServerProperties() {

        String emailPort = "587";//gmail's smtp port

        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");

    }
    
    /*creating the mail message to send*/
    public void createEmailMessage(LoginInfoBean loginBean, UserInfoBean userBean) throws AddressException,
            MessagingException {
        setMailServerProperties();
        String[] toEmails = {userBean.getEmailId()};
        String emailBody = "";
        String emailSubject = "About Registration";
        if (loginBean.getLoginType().equals("View")) {
            emailBody = "Hi " + userBean.getName() + " <br/>Thank you for registration.<br/>"
                    + "Your User Id :" + loginBean.getUserId()
                    + "<br/> Login Password:" + loginBean.getLoginPassword();
        } else {
            emailBody = "Hi " + userBean.getName() + " <br/>Thank you for registration.<br/>"
                    + "Your User Id :" + loginBean.getUserId()
                    + "<br/> Login Password:" + loginBean.getLoginPassword()
                    + "<br/> Transaction Password:" + loginBean.getTransactionPassword();
        }

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        for (int i = 0; i < toEmails.length; i++) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");//for a html email
        sendEmail();
    }

    /*sending the mail */
    private void sendEmail() throws AddressException, MessagingException {

        String emailHost = "smtp.gmail.com";
        String fromUser = "jayprakash0701";//just the id alone without @gmail.com
        String fromUserEmailPassword = "bettiahbihar";//enter your gmail password

        Transport transport = mailSession.getTransport("smtp");

        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();

    }

    //sending opt number
    public int sendOPTNumber(String emailId) throws AddressException, MessagingException {
        setMailServerProperties();
        int optNumber = 0;
        PasswordEncrypt passwordEncrypt = new PasswordEncrypt();
        optNumber = passwordEncrypt.generateOPTNumber();
        String[] toEmails = {emailId};

        // JOptionPane.showMessageDialog(null, acivationKey);
        String emailBody = "";
        String emailSubject = "About Password Reset";

        emailBody = "Hi <br/>"
                + "Your opt number :" + optNumber
                + "<br/> Please enter it into form";


        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        for (int i = 0; i < toEmails.length; i++) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");//for a html email
        sendEmail();

        return optNumber;
    }

    //sending URN number
    public void sendURNNumber(String emailId, String messageBody) throws AddressException, MessagingException {
        setMailServerProperties();
        String[] toEmails = {emailId};
        String emailBody = "";
        String emailSubject = "About Add new Beneficiary";

        emailBody = "Hi <br/>"
                + "Your URN number :" + messageBody
                + "<br/> Please enter it into form";


        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        for (int i = 0; i < toEmails.length; i++) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");//for a html email
        sendEmail();
    }
    
    //sending  transaction detail 
    public void sendTransactionDetail(String emailId, String messageBody) throws AddressException, MessagingException {
        setMailServerProperties();
        String[] toEmails = {emailId};
        String emailBody = "";
        String emailSubject = "About Online Transaction";

        emailBody =messageBody ;


        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        for (int i = 0; i < toEmails.length; i++) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");//for a html email
        sendEmail();
    }
   
}

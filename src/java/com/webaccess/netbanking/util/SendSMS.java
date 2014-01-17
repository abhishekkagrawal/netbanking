/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.util;
// import java.io.*;
//import java.net.InetAddress;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import java.util.Properties;
//import java.util.Date;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.activation.*;
/**
 *
 * @author Jay Prakash
 * 
 */
public class SendSMS {

    public SendSMS() {
    }
    //create an account on ipipi.com with the given username and password

    //sending sms on mobile
    public void msgsend(String message, long mobileNumber) {
        String username = "jay0701";
        //Your Credentials 
        String password = "jayprakash1988";
        String smtphost = "ipipi.com";
        //Ip/Name of Server 
        String compression = "None";
        //I dont want any compression 
        String from = "jay0701@ipipi.com";
        //ur userid@ipipi.com 
        //This mobile number need not be registered with ipipi.com 
        String to = "91" + mobileNumber + "@sms.ipipi.com";//mobile number where u want to send sms
        String body = message;

        Transport tr = null;
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.auth", "true");
            // Get a Session object 
            Session mailSession =
                    Session.getDefaultInstance(props, null);
            // construct the message 
            Message msg = new MimeMessage(mailSession);
            //Set message attributes
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(compression);
            msg.setText(body);
            msg.setSentDate(new Date());
            tr = mailSession.getTransport("smtp");
            //try to connect 
            tr.connect(smtphost, username, password);
            msg.saveChanges();
            //send msg to all recipients 
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] argv) {
//        SendSMS sms = new SendSMS();
//        // sms.msgsend("asas",213); 
//        System.out.println("Successfull");
//    }
}

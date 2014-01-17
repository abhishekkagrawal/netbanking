/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.util;

import com.webaccess.netbanking.dao.NewRegistrationDao;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;

/**
 *
 * @author Jay Prakash Started on: oct 15 2013 Ended on: oct 15 2013
 *
 * this class is for encrypt password
 */
public class PasswordEncrypt {

    //function for encrytping password
    public static String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("MD5");
            //  MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Random number is : " + " " + generateOPTNumber());
        System.out.println("Login password is : " + " " + generatePassword());
        System.out.println("Transaction password is : " + " " + generatePassword());
        String abc = generatePassword();
        System.out.println(abc);
        System.out.println("Transaction password is : " + " " + encryptPassword(abc));

    }

    //function for generating new user id
    public static String generateUserId() {
        Random randomNum = new Random();
        char[] text = new char[3];
        String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 3; i++) {
            text[i] = string.charAt(randomNum.nextInt(string.length()));
        }
        return new String(text) + randomNum.nextInt(1000000);
    }

    //function for generating login and transaction password
    public static String generatePassword() {
        Random randomNum = new Random();
        char[] text = new char[5];
        String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 5; i++) {
            text[i] = string.charAt(randomNum.nextInt(string.length()));
        }
        return new String(text) + randomNum.nextInt(10000);
    }

    //generating unique user id
    public static String getUserId() {
        NewRegistrationDao dao = new NewRegistrationDao();
        boolean result = true;
        String userId = "";
        ArrayList list = new ArrayList();
        list = dao.getUserId();
        System.out.println(list);
        while (result) {
            userId = generateUserId();
            result = checkUserId(list, userId);
        }
        return userId;
    }

    //checking user id
    private static boolean checkUserId(ArrayList list, String userId) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(userId)) {
                return true;
            }
        }
        return false;
    }

    //generating OPT Number
    public static int generateOPTNumber() {
        Random randomNum = new Random();
        int optNumber = 0;
        optNumber=randomNum.nextInt(1000000);
        return optNumber;

    }
    
    //generating credit card number
     public static long randomDigits(Random random, int length) {
        char[] digits = new char[length];
        // Make sure the leading digit isn't 0.
        digits[0] = (char) ('1' + random.nextInt(9));
        for (int i = 1; i < length; i++) {
            digits[i] = (char) ('0' + random.nextInt(10));
        }
        return Long.parseLong(new String(digits));
    }
    
     public static long generateCreditCardNumber() {
        Random randomNum = new Random();
        long cardNumber = 0;
        cardNumber=randomDigits(randomNum, 16);
        return cardNumber;

    }
}
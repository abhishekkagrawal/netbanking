/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.bean;

/**
 *
 * @author Jay Prakash
 * Started on: Oct 17 2013
 * Ended On: Oct 17 2013
 */
public class PasswordResetBean {
    
    private String userId;
    private long mobileNumber;
    private String panCardNumber;
    private int optNumber;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

   

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public int getOptNumber() {
        return optNumber;
    }

    public void setOptNumber(int optNumber) {
        this.optNumber = optNumber;
    }
    
    
    
}

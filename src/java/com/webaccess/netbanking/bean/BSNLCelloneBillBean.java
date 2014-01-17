/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.bean;

import java.util.Date;

/**
 * Started On: Nov 05 2013
 *
 * Ended On: Nov 05 2013
 *
 * @author Jay Prakash
 */
public class BSNLCelloneBillBean {

    private int bsnlCelloneId;
    private String nickName;
    private long mobileNumber;
    private int stateId;
    private int accountNumber;
    private String stateName;
    private String dateOfCreation;
    private String dateOfModification;
    private String status;
    private double amount;
    private boolean paidStatus;
    private Date lastPaidDate;

    public Date getLastPaidDate() {
        return lastPaidDate;
    }

    public void setLastPaidDate(Date lastPaidDate) {
        this.lastPaidDate = lastPaidDate;
    }

    public boolean isPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(boolean paidStatus) {
        this.paidStatus = paidStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getDateOfModification() {
        return dateOfModification;
    }

    public void setDateOfModification(String dateOfModification) {
        this.dateOfModification = dateOfModification;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getBsnlCelloneId() {
        return bsnlCelloneId;
    }

    public void setBsnlCelloneId(int bsnlCelloneId) {
        this.bsnlCelloneId = bsnlCelloneId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}

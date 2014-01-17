/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.bean;

import java.util.Date;

/**
 * Started on: Nov 05 2013
 *
 * Ended on: Nov 05 2013
 *
 * @author Jay Prakash
 */
public class ElectricityBillBean {

    private int electricityBillId;
    private String nickName;
    private int billingUnit;
    private int processingCycle;
    private int consumerNumber;
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

    public int getElectricityBillId() {
        return electricityBillId;
    }

    public void setElectricityBillId(int electricityBillId) {
        this.electricityBillId = electricityBillId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getBillingUnit() {
        return billingUnit;
    }

    public void setBillingUnit(int billingUnit) {
        this.billingUnit = billingUnit;
    }

    public int getProcessingCycle() {
        return processingCycle;
    }

    public void setProcessingCycle(int processingCycle) {
        this.processingCycle = processingCycle;
    }

    public int getConsumerNumber() {
        return consumerNumber;
    }

    public void setConsumerNumber(int consumerNumber) {
        this.consumerNumber = consumerNumber;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.bean;

import java.util.Date;

/**
 *
 * @author Jay Prakash
 */
public class TransactionDetailBean {

    int transactionId;
    int accountNumber;
    String transactionType;
    Date transactionDate;
    double transactionAmount;
    long chequeNumber;
    long cardNumber;
    String dateOfCreation;
    String dateOfModification;
    String particulars;

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public long getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(long chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
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
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.bean;

/**
 * Started on: Oct 01 2013
 *
 * Ended on: Oct 01 2013
 *
 * @author Jay Prakash
 */
public class TransferBean {

    int transferAccount;
    double transactionAmount;
    int accountNumber;
    String particulars;
    String userId;
    String transactionPassword;

    public int getTransferAccount() {
        return transferAccount;
    }

    public void setTransferAccount(int transferAccount) {
        this.transferAccount = transferAccount;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionPassword() {
        return transactionPassword;
    }

    public void setTransactionPassword(String transactionPassword) {
        this.transactionPassword = transactionPassword;
    }
}

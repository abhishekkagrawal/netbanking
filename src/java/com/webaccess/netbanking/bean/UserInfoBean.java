/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.bean;

import java.util.Date;

/**
 *
 * @author Jay Prakash Started on: oct 15 2013 ended on: oct 15 2013
 */
public class UserInfoBean {

    int accountNumber;
    String name;
    String address;
    String gender;
    long number;
    Date dateOfBirth;
    String emailId;
    double availBalance;
    Date dateOfCreation;
    Date dateOfModification;
    int noOfCardIssue;

    public int getNoOfCardIssue() {
        return noOfCardIssue;
    }

    public void setNoOfCardIssue(int noOfCardIssue) {
        this.noOfCardIssue = noOfCardIssue;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfModification() {
        return dateOfModification;
    }

    public void setDateOfModification(Date dateOfModification) {
        this.dateOfModification = dateOfModification;
    }

    public double getAvailBalance() {
        return availBalance;
    }

    public void setAvailBalance(double availBalance) {
        this.availBalance = availBalance;
    }
    String panCardNumber;
    int accountId;
    String accountType;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}

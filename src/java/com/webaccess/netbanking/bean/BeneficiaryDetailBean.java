/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.bean;

/**
 *
 * @author Jay prakash Started on: Oct 29 2013 Ended On: Oct 29 2013
 *
 */
public class BeneficiaryDetailBean {

    int beneficiaryId;
    int accountNumber;
    String beneficiaryName;
    String beneficiaryBankName;
    String IFSCCode;
    int beneficiaryAccountNumber;
    int reEnterAccuontNumber;
    String beneficiaryAccountType;
    String transactionType;
    int URNNumber;
    String status;
    String dateOfCreation;

    public int getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(int beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryBankName() {
        return beneficiaryBankName;
    }

    public void setBeneficiaryBankName(String beneficiaryBankName) {
        this.beneficiaryBankName = beneficiaryBankName;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }

    public int getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public void setBeneficiaryAccountNumber(int beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }

    public int getReEnterAccuontNumber() {
        return reEnterAccuontNumber;
    }

    public void setReEnterAccuontNumber(int reEnterAccuontNumber) {
        this.reEnterAccuontNumber = reEnterAccuontNumber;
    }

    public String getBeneficiaryAccountType() {
        return beneficiaryAccountType;
    }

    public void setBeneficiaryAccountType(String beneficiaryAccountType) {
        this.beneficiaryAccountType = beneficiaryAccountType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getURNNumber() {
        return URNNumber;
    }

    public void setURNNumber(int URNNumber) {
        this.URNNumber = URNNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

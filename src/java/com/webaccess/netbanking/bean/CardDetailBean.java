/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.bean;

/**
 * Started On:Nov 11 2013
 *
 * Ended On :Nov 11 2013
 *
 * @author Jay Prakash
 */
public class CardDetailBean {

    private int cardId;
    private long creditCardNumber;
    private String nameOnCard;
    private int creditCardId;
    private double unbilledAmount;
    private String dateOfCreation;
    private String dateOfModification;
    private int accountNumber;
    private String cardTypeName;
    private double maxLimit;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    public double getUnbilledAmount() {
        return unbilledAmount;
    }

    public void setUnbilledAmount(double unbilledAmount) {
        this.unbilledAmount = unbilledAmount;
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

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public double getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(double maxLimit) {
        this.maxLimit = maxLimit;
    }
}

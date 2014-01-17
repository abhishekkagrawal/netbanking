/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.bean;

/**
 * Started on: Nov 11 2013
 *
 * Ended On: Nov 11 2013
 *
 * @author Jay Prakash
 */
public class CardTypeBean {

    private int creditCardId;
    private String cardTypeName;
    private double maxLimit;

    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
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

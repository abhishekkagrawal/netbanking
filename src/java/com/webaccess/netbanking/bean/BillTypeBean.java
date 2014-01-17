/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.bean;

/**
 * Started On: Nov 04 2013
 *
 * Ended On: Nov 04 2013
 *
 * @author Jay Prakash
 */
public class BillTypeBean {
    
    private int billerId;
    private String billerName;
    private int stateId;

    public int getBillerId() {
        return billerId;
    }

    public void setBillerId(int billerId) {
        this.billerId = billerId;
    }

    public String getBillerName() {
        return billerName;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public String toString() {
        return "BillTypeBean{" + "billerId=" + billerId + ", billerName=" + billerName + ", stateId=" + stateId + '}';
    }
    
    
}

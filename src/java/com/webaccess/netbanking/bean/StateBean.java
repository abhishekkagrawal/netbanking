/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.bean;

/**
 * Started on: Nov 04 2013
 *
 * Ended on:Nov 04 2013
 *
 * @author Jay prakash
 */
public class StateBean {
    
    private int stateId;
    private String stateName;

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "stateBean{" + "stateId=" + stateId + ", stateName=" + stateName + '}';
    }
    
    
}

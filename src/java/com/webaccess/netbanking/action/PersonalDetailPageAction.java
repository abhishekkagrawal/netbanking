/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.BillDelegate;
import com.webaccess.netbanking.delegate.CreditCardDelegate;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Started on:
 *
 * @author Jay Prakash
 */
public class PersonalDetailPageAction extends ActionSupport implements SessionAware {

    ArrayList stateList = new ArrayList();
    ArrayList billerList = new ArrayList();
    CreditCardDelegate creditCardDelegate = new CreditCardDelegate();
    ArrayList creditCardDetailList = new ArrayList();
    Map<String, Object> session;

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public ArrayList getCreditCardDetailList() {
        return creditCardDetailList;
    }

    public void setCreditCardDetailList(ArrayList creditCardDetailList) {
        this.creditCardDetailList = creditCardDetailList;
    }

    public ArrayList getStateList() {
        return stateList;
    }

    public void setStateList(ArrayList stateList) {
        this.stateList = stateList;
    }

    public ArrayList getBillerList() {
        return billerList;
    }

    public void setBillerList(ArrayList billerList) {
        this.billerList = billerList;
    }

    //forwarding to home page
    public String myAccount() {
        return "myAccount";
    }

    //forwarding to transfer page
    public String transfer() {
        return "transfer";
    }

    // forwarding to bill payment page with state and biller list
    public String billPayment() {
        BillDelegate billDelegate = new BillDelegate();
        billerList = billDelegate.getBiller();
        stateList = billDelegate.getState();
        return "billPayment";
    }

    //forwarding to card detail page with card list
    public String cardDetail() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountNumber = userInfoBean.getAccountNumber();
            creditCardDetailList = creditCardDelegate.getCreditCardetailList(accountNumber);
            return "cardDetail";
        }
    }

    // forwarding to personal detail page
    public String personalDetail() {
        return "personalDetail";
    }

    //forwarding to my profile page
    public String myProfile() {
        return "myProfile";
    }

    //forwarding to change login password page
    public String changeLoginPassword() {
        return "changeLoginPassword";
    }

    // forwarding to change transaction password  page
    public String changeTransactionPassword() {
        return "changeTransactionPassword";
    }
}

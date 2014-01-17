/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.BillDelegate;
import com.webaccess.netbanking.delegate.CreditCardDelegate;
import com.webaccess.netbanking.delegate.HomePageDelegate;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Started on : Oct 15 2013
 *
 * Ended On: Oct 17 2013
 *
 * @author Jay Prakash
 */

//performing all action present home page  
public class HomeAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    ArrayList transactionDeatailList = new ArrayList();
    HomePageDelegate delegate = new HomePageDelegate();
    CreditCardDelegate creditCardDelegate = new CreditCardDelegate();
    ArrayList stateList = new ArrayList();
    ArrayList billerList = new ArrayList();
    ArrayList creditCardDetailList = new ArrayList();

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

    public ArrayList getTransactionDeatailList() {
        return transactionDeatailList;
    }

    public void setTransactionDeatailList(ArrayList transactionDeatailList) {
        this.transactionDeatailList = transactionDeatailList;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    //forwarding to home page
    public String myAccount() {

        return "myAccount";
    }

    //forwarding to transfer page
    public String transfer() {
        return "transfer";
    }

    
    //forwarding to bill payment page with state and biller list
    public String billPayment() {
        BillDelegate billDelegate = new BillDelegate();
        billerList = billDelegate.getBiller();
        stateList = billDelegate.getState();
        return "billPayment";
    }

    //forwarding to card detail page with credit card list
    public String cardDetail() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountNumber = userInfoBean.getAccountNumber();
            //getting card detail from database
            creditCardDetailList = creditCardDelegate.getCreditCardetailList(accountNumber);
            return "cardDetail";
        }
    }

    // forwarding to personal detail page
    public String personalDetail() {
        return "personalDetail";
    }

    //getting transaction detail and forwarding to mini statement page 
    public String getTransaction() {
        UserInfoBean bean = new UserInfoBean();
        bean = (UserInfoBean) session.get("userInfo");
        if (bean == null) {
            return "error";
        } else {
            //getting transaction dtail
            transactionDeatailList = delegate.getTransactionDetail(bean.getAccountNumber());
            session.put("transactionList", transactionDeatailList);//putting transaction detail into session
            return "miniStatement";
        }
    }
}

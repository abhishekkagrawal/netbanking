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
import com.webaccess.netbanking.util.SaveInPDF;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 *
 * @author Jay Prakash
 */
//This action class  is used for showing the account detail page jsp.
public class AccountDetailPageAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    HomePageDelegate delegate = new HomePageDelegate();
    ArrayList stateList = new ArrayList();
    CreditCardDelegate creditCardDelegate = new CreditCardDelegate();
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
    ArrayList billerList = new ArrayList();
    ArrayList transactionDeatailList = new ArrayList();

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

    //back to 
    public String back() {
        return "back";
    }

    // forwarding page to home page jsp
    public String myAccount() {
        return "myAccount";
    }

    // forwarding to transfer page jsp..
    public String transfer() {
        return "transfer";
    }

    //forwarding to billpage jsp with state list and biller list 
    public String billPayment() {
        BillDelegate billDelegate = new BillDelegate();
        billerList = billDelegate.getBiller();
        stateList = billDelegate.getState();
        return "billPayment";
    }

    //forwarding to credit card detail page
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

    //forwarding to  personal detail page
    public String personalDetail() {

        return "personalDetail";
    }

    // forwarding to mini statement page
    public String miniStatement() {
        UserInfoBean bean = new UserInfoBean();
        bean = (UserInfoBean) session.get("userInfo");
        if (bean == null) {
            return "error";
        } else {
            transactionDeatailList = delegate.getTransactionDetail(bean.getAccountNumber());
            session.put("transactionList", transactionDeatailList);
            return "miniStatement";
        }
    }

    //this function  will save transaction detail in pdf format
    public String save() {
        ArrayList list = new ArrayList();
        list = (ArrayList) session.get("transactionList");
        UserInfoBean bean = new UserInfoBean();
        bean = (UserInfoBean) session.get("userInfo");
        if (bean == null) {
            return "error";
        } else {
            SaveInPDF test1 = new SaveInPDF();
            test1.savePDF(bean, list);
            return "myAccount";

        }

    }

    //forwarding to view and save page jsp
    public String viewAndSave() {
        return "viewAndSave";
    }
}
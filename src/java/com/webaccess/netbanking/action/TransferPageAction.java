/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.BeneficiaryDelegate;
import com.webaccess.netbanking.delegate.BillDelegate;
import com.webaccess.netbanking.delegate.CreditCardDelegate;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Started On: Oct 16 2013
 * 
 *
 * @author Jay Prakash
 */
public class TransferPageAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    BeneficiaryDelegate beneficiaryDelegate = new BeneficiaryDelegate();
    ArrayList activeBeneficiaryList = new ArrayList();
    ArrayList stateList = new ArrayList();
    ArrayList billerList = new ArrayList();
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

    public ArrayList getActiveBeneficiaryList() {
        return activeBeneficiaryList;
    }

    public void setActiveBeneficiaryList(ArrayList activeBeneficiaryList) {
        this.activeBeneficiaryList = activeBeneficiaryList;
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

    //forwarding to bill page with state
    public String billPayment() {
        BillDelegate billDelegate = new BillDelegate();
        billerList = billDelegate.getBiller();
        stateList = billDelegate.getState();

        return "billPayment";
    }

    //forwarding to  card detail page with card list
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

   //forwarding to personal detail page
    public String personalDetail() {
        return "personalDetail";
    }

    //forwarding to transfer page
    public String neftTransfer() {
        LoginInfoBean loginInfoBean = new LoginInfoBean();
        loginInfoBean = (LoginInfoBean) session.get("loginInfo");
        if (loginInfoBean == null) {
            return "error";
        } else {
            int accountNumber = loginInfoBean.getAccountNumber();
            String transactionType = "NEFT";
            activeBeneficiaryList = beneficiaryDelegate.getActiveBeneficiary(accountNumber, transactionType);
            return "neftTransfer";
        }
    }
}

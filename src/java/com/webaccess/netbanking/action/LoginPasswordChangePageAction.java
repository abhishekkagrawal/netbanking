/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.BillDelegate;
import com.webaccess.netbanking.delegate.ChangePasswordDelegate;
import com.webaccess.netbanking.delegate.CreditCardDelegate;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Started on: Oct 28 2013
 *
 * Ended on: Oct 28 2013
 *
 * @author Jay Prakash
 *
 */
public class LoginPasswordChangePageAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    String oldLoginPassword;
    String newLoginPassword;
    String conformNewLoginPassword;
    ChangePasswordDelegate delegate = new ChangePasswordDelegate();
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

    public String getOldLoginPassword() {
        return oldLoginPassword;
    }

    public void setOldLoginPassword(String oldLoginPassword) {
        this.oldLoginPassword = oldLoginPassword;
    }

    public String getNewLoginPassword() {
        return newLoginPassword;
    }

    public void setNewLoginPassword(String newLoginPassword) {
        this.newLoginPassword = newLoginPassword;
    }

    public String getConformNewLoginPassword() {
        return conformNewLoginPassword;
    }

    public void setConformNewLoginPassword(String conformNewLoginPassword) {
        this.conformNewLoginPassword = conformNewLoginPassword;
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

    //forwarding to transfer oage 
    public String transfer() {
        return "transfer";
    }

    //forwarding to bill page with state nad biller list
    public String billPayment() {
        BillDelegate billDelegate = new BillDelegate();
        billerList = billDelegate.getBiller();
        stateList = billDelegate.getState();
        return "billPayment";
    }

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

    //forwarding to personal detail page 
    public String myProfile() {
        return "myProfile";
    }

    //forwarding to change login password page
    public String changeLoginPassword() {
        return "changeLoginPassword";
    }

    //forwarding to change transaction password page
    public String changeTransactionPassword() {
        return "changeTransactionPassword";
    }

    //method  for changing login password
    public String execute() {
        boolean result = true;
        LoginInfoBean loginInfoBean = new LoginInfoBean();
        LoginInfoBean loginInfoBean1 = new LoginInfoBean();
        loginInfoBean = (LoginInfoBean) session.get("loginInfo");
        if (loginInfoBean == null) {
            return "error";
        } else {
            loginInfoBean1.setUserId(loginInfoBean.getUserId());
            loginInfoBean1.setAccountNumber(loginInfoBean.getAccountNumber());
            loginInfoBean1.setLoginPassword(newLoginPassword);
            result = delegate.changeLoginPassword(loginInfoBean1, oldLoginPassword);
            if (result) {
                return SUCCESS;
            } else {
                addActionError("*password should not be old password or you entered wrong login password");
                return INPUT;
            }
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
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
public class TransactionPasswordChangePageAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    String oldTransactionPassword;
    String newTransactionPassword;
    String conformNewTransactionPassword;
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

    public String getOldTransactionPassword() {
        return oldTransactionPassword;
    }

    public void setOldTransactionPassword(String oldTransactionPassword) {
        this.oldTransactionPassword = oldTransactionPassword;
    }

    public String getNewTransactionPassword() {
        return newTransactionPassword;
    }

    public void setNewTransactionPassword(String newTransactionPassword) {
        this.newTransactionPassword = newTransactionPassword;
    }

    public String getConformNewTransactionPassword() {
        return conformNewTransactionPassword;
    }

    public void setConformNewTransactionPassword(String conformNewTransactionPassword) {
        this.conformNewTransactionPassword = conformNewTransactionPassword;
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

    //forwarding to bill payment page with biller and state list
    public String billPayment() {
        BillDelegate billDelegate = new BillDelegate();
        billerList = billDelegate.getBiller();
        stateList = billDelegate.getState();
        return "billPayment";
    }

    //forwarding to card detail page with card information
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

    //fgorwarding to change login password page
    public String changeLoginPassword() {
        return "changeLoginPassword";
    }
    
    //forwarding to change transaction password page
    public String changeTransactionPassword() {
        return "changeTransactionPassword";
    }

    //changing transaction password
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
            loginInfoBean1.setTransactionPassword(newTransactionPassword);
            result = delegate.changeTransactionPassword(loginInfoBean1, oldTransactionPassword);
            if (result) {
                return SUCCESS;
            } else {
                addActionError("*password should not be old password or you entered wrong login password");
                return INPUT;
            }
        }
    }
}

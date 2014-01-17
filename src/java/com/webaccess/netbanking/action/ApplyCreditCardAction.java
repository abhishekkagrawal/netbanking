/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.BillDelegate;
import com.webaccess.netbanking.delegate.CreditCardDelegate;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * apply new card
 * 
 * Started On:Nov 11 2013
 *
 * Ended on: Nov 11 2013
 *
 * @author Jay Prakash
 */
public class ApplyCreditCardAction extends ActionSupport implements SessionAware {

    ArrayList stateList = new ArrayList();
    ArrayList billerList = new ArrayList();
    ArrayList creditCardDetailList = new ArrayList();
    BillDelegate billDelegate = new BillDelegate();
    CreditCardDelegate creditCardDelegate = new CreditCardDelegate();
    Map<String, Object> session;
    private int cardTpe;
    private String nameOnCard;
    ArrayList cardTypeList = new ArrayList();

    public ArrayList getCreditCardDetailList() {
        return creditCardDetailList;
    }

    public void setCreditCardDetailList(ArrayList creditCardDetailList) {
        this.creditCardDetailList = creditCardDetailList;
    }

    public ArrayList getCardTypeList() {
        return cardTypeList;
    }

    public void setCardTypeList(ArrayList cardTypeList) {
        this.cardTypeList = cardTypeList;
    }

    public int getCardTpe() {
        return cardTpe;
    }

    public void setCardTpe(int cardTpe) {
        this.cardTpe = cardTpe;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
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

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    //forward to home page jsp
    public String myAccount() {

        return "myAccount";
    }

    //forwarding to transfer page jsp
    public String transfer() {
        return "transfer";
    }

    //forwarding to add bill page jsp
    public String billPayment() {
        billerList = billDelegate.getBiller();
        stateList = billDelegate.getState();
        return "billPayment";
    }

    //getting card detail
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

    //forwarding to personal detail page jsp 
    public String personalDetail() {
        return "personalDetail";
    }

    //forwarding to personal detail page jsp 
    public String applyCard() {
        cardTypeList = creditCardDelegate.getCardType();
        return "applyCard";
    }

    //forwarding to personal detail page jsp 
    public String addCreditCard() {
        return "addCreditCard";
    }

    //forwarding to personal detail page jsp 
    public String payCreditCardBill() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountNumber = userInfoBean.getAccountNumber();
            creditCardDetailList = creditCardDelegate.getCreditCardetailList(accountNumber);
            return "payCreditCardBill";
        }
    }

    //apply new card
    public String execute() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountNumber = userInfoBean.getAccountNumber();
            String result = creditCardDelegate.applyNewCreditCard(accountNumber, cardTpe, nameOnCard);
            if (result.equals("success")) {
                return SUCCESS;
            }
            else{
                cardTypeList = creditCardDelegate.getCardType();
                addActionError("* Maximim No of card issued");
                 return "input";
            }
        }
    }
}

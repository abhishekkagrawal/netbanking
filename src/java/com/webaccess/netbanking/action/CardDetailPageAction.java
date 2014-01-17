/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.CardDetailBean;
import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.BillDelegate;
import com.webaccess.netbanking.delegate.CreditCardDelegate;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Started On: Nov 11 2013
 *
 * Ended On : Nov 13 2013
 *
 * @author Jay Prakash
 */
public class CardDetailPageAction extends ActionSupport implements SessionAware {

    BillDelegate billDelegate = new BillDelegate();
    CreditCardDelegate creditCardDelegate = new CreditCardDelegate();
    Map<String, Object> session;
    ArrayList stateList = new ArrayList();
    ArrayList billerList = new ArrayList();
    ArrayList cardTypeList = new ArrayList();
    ArrayList creditCardDetailList = new ArrayList();
    int cardId;
    String nameOnCard;
    double unbilledAmount;
    String remark;
    String userId;
    String transactionPassword;
    long creditCardNumber;

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public double getUnbilledAmount() {
        return unbilledAmount;
    }

    public void setUnbilledAmount(double unbilledAmount) {
        this.unbilledAmount = unbilledAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionPassword() {
        return transactionPassword;
    }

    public void setTransactionPassword(String transactionPassword) {
        this.transactionPassword = transactionPassword;
    }

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

    //forwarding to home page
    public String myAccount() {
        return "myAccount";
    }

    //forwarding to transfer page
    public String transfer() {
        return "transfer";
    }

    // forwarding to bill page with sate list and biller list
    public String billPayment() {

        billerList = billDelegate.getBiller();
        stateList = billDelegate.getState();
        return "billPayment";
    }

    // forwarding personal detail page
    public String personalDetail() {

        return "personalDetail";
    }

    //forwarding to cardeetail page with card list
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

    //forwarding to apply card page with card type...
    public String applyCard() {
        cardTypeList = creditCardDelegate.getCardType();
        return "applyCard";
    }

    // forwarding to paycard detail page 
    public String payCardBill() {
        return "payCardBill";
    }

    //forwarding to add credit card page
    public String addCreditCard() {
        return "addCreditCard";
    }

    // fuction to add new card detail into database
    public String execute() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        LoginInfoBean loginInfoBean = new LoginInfoBean();
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountNumber = userInfoBean.getAccountNumber();
            CardDetailBean cardDetailBean = new CardDetailBean();
            cardDetailBean.setAccountNumber(accountNumber);
            cardDetailBean.setCardId(cardId);
            cardDetailBean.setCreditCardNumber(creditCardNumber);
            cardDetailBean.setNameOnCard(nameOnCard);
            cardDetailBean.setUnbilledAmount(unbilledAmount);
            loginInfoBean.setUserId(userId);
            loginInfoBean.setTransactionPassword(transactionPassword);
            String result = creditCardDelegate.payCreditCardBill(loginInfoBean, cardDetailBean, remark);
            if (result.equals("success")) {
                return "success";
            }
            if (result.equals("transactionPasswordError")) {
                addActionError("*Invalid Password");
                //getting credit card detail
                creditCardDetailList = creditCardDelegate.getCreditCardetailList(accountNumber);
                return "input";
            }
            if (result.equals("balanceError")) {
                addActionError("*Insufficient Balance");
                //getting credit card detail
                creditCardDetailList = creditCardDelegate.getCreditCardetailList(accountNumber);
                return "input";
            }
            if (result.equals("technicalError")) {
                addActionError("*Sorry unable to process try again");
                //getting credit card detail
                creditCardDetailList = creditCardDelegate.getCreditCardetailList(accountNumber);
                return "input";
            }
            return SUCCESS;
        }


    }
}

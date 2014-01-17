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
import com.webaccess.netbanking.delegate.ViewAndSavePageDelegate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 *
 * @author Jay Prakash
 */
public class ViewAndSavePageAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    HomePageDelegate delegate = new HomePageDelegate();
    ViewAndSavePageDelegate viewAndSavePageDelegate = new ViewAndSavePageDelegate();
    ArrayList transactionDeatailList = new ArrayList();
    String noOfTransaction;
    String sortBy;
    Date startDate;
    Date endDate;
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

    public String getNoOfTransaction() {
        return noOfTransaction;
    }

    public void setNoOfTransaction(String noOfTransaction) {
        this.noOfTransaction = noOfTransaction;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    //getting transaction detail from database  by no of transaction or date wise
    public String execute() {
        UserInfoBean bean = new UserInfoBean();
        bean = (UserInfoBean) session.get("userInfo");
        if (bean == null) {
            return "error";
        } else {
            int accountNumber = bean.getAccountNumber();
            if (!noOfTransaction.equals("")) {
                transactionDeatailList = viewAndSavePageDelegate.getTransactionByNumber(Integer.parseInt(noOfTransaction), accountNumber);
            } else {
                transactionDeatailList = viewAndSavePageDelegate.getTransactionByDate(startDate, endDate, accountNumber);
            }
            session.put("transactionList", transactionDeatailList);
            return "monthTransactionDetail";
        }
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

    // forwarding to transaction home page
    public String back() {
        return "back";
    }

    //forwarding to home page 
    public String myAccount() {
        return "myAccount";
    }

    //forwarding to transfer page
    public String transfer() {
        BillDelegate billDelegate = new BillDelegate();
        billerList = billDelegate.getBiller();
        stateList = billDelegate.getState();
        return "transfer";
    }

    //forwarding to bill page
    public String billPayment() {
        return "billPayment";
    }

    //forwarding to card detail page
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

    //forwarding to ministatement page with 10 transaction detail
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

    //forwarding to account detail page
    public String accountDetail() {
        return "accountDetail";
    }

    //forwarding to view transaction page with one month transaction detail
    public String oneMonthTransactionDetail() {
        // Map session = ActionContext.getContext().getSession();
        UserInfoBean bean = new UserInfoBean();
        bean = (UserInfoBean) session.get("userInfo");
        transactionDeatailList = viewAndSavePageDelegate.oneMonthTransactionDetail(bean.getAccountNumber());
        session.put("transactionList", transactionDeatailList);
        return "monthTransactionDetail";
    }

    //forwarding to view transaction page with two month transaction detail
    public String twoMonthTransactionDetail() {

        // Map session = ActionContext.getContext().getSession();
        UserInfoBean bean = new UserInfoBean();
        bean = (UserInfoBean) session.get("userInfo");
        if (bean == null) {
            return "error";
        } else {
            transactionDeatailList = viewAndSavePageDelegate.twoMonthTransactionDetail(bean.getAccountNumber());
            session.put("transactionList", transactionDeatailList);
            return "monthTransactionDetail";
        }
    }
}

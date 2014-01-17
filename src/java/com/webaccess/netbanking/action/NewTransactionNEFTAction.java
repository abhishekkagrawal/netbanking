/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import static com.opensymphony.xwork2.Action.INPUT;
import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.TransferBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.BeneficiaryDelegate;
import com.webaccess.netbanking.delegate.BillDelegate;
import com.webaccess.netbanking.delegate.CreditCardDelegate;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Started on: Oct 29 2013
 *
 * Ended On : Oct 30 2013
 * 
 * @author Jay Prakash
 */
public class NewTransactionNEFTAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    ArrayList deactiveBeneficiaryList = new ArrayList();
    ArrayList activeBeneficiaryList = new ArrayList();
    BeneficiaryDelegate beneficiaryDelegate = new BeneficiaryDelegate();
    String transferAccount;
    double transactionAmount;
    String accountNumber;
    String particulars;
    String userId;
    String transactionPassword;
    ArrayList stateList = new ArrayList();
    ArrayList billerList = new ArrayList();
    CreditCardDelegate creditCardDelegate = new CreditCardDelegate();
    ArrayList creditCardDetailList = new ArrayList();
    ArrayList bankList = new ArrayList();

    public ArrayList getBankList() {
        return bankList;
    }

    public void setBankList(ArrayList bankList) {
        this.bankList = bankList;
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

    public String getTransferAccount() {
        return transferAccount;
    }

    public void setTransferAccount(String transferAccount) {
        this.transferAccount = transferAccount;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public ArrayList getActiveBeneficiaryList() {
        return activeBeneficiaryList;
    }

    public void setActiveBeneficiaryList(ArrayList activeBeneficiaryList) {
        this.activeBeneficiaryList = activeBeneficiaryList;
    }

    public ArrayList getDeactiveBeneficiaryList() {
        return deactiveBeneficiaryList;
    }

    public void setDeactiveBeneficiaryList(ArrayList deactiveBeneficiaryList) {
        this.deactiveBeneficiaryList = deactiveBeneficiaryList;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String myAccount() {

        return "myAccount";
    }

    public String transfer() {

        return "transfer";
    }

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

    public String funds() {

        return "funds";
    }

    public String personalDetail() {

        return "personalDetail";
    }

    public String registerBeneficary() {
        bankList = beneficiaryDelegate.getBankDetail();
        return "registerBeneficary";
    }

    public String confirAndRejectBeneficiary() {
        LoginInfoBean loginInfoBean = new LoginInfoBean();
        loginInfoBean = (LoginInfoBean) session.get("loginInfo");
        if (loginInfoBean == null) {
            return "error";
        } else {
            int accountNumber = loginInfoBean.getAccountNumber();
            String transactionType = "NEFT";
            deactiveBeneficiaryList = beneficiaryDelegate.getDeactiveBeneficiary(accountNumber, transactionType);
            return "confirAndRejectBeneficiary";
        }
    }

    public String newTransaction() {
        LoginInfoBean loginInfoBean = new LoginInfoBean();
        loginInfoBean = (LoginInfoBean) session.get("loginInfo");
        if (loginInfoBean == null) {
            return "error";
        } else {
            int accountNumber = loginInfoBean.getAccountNumber();
            String transactionType = "NEFT";
            activeBeneficiaryList = beneficiaryDelegate.getActiveBeneficiary(accountNumber, transactionType);
            return "newTransaction";
        }
    }

    public String registeredBeneficiary() {

        LoginInfoBean loginInfoBean = new LoginInfoBean();
        loginInfoBean = (LoginInfoBean) session.get("loginInfo");
        int accountNumber = loginInfoBean.getAccountNumber();
        String transactionType = "NEFT";
        activeBeneficiaryList = beneficiaryDelegate.getActiveBeneficiary(accountNumber, transactionType);
        return "registeredBeneficiary";
    }

    public String execute() {
        TransferBean transferBean = new TransferBean();
        String transferAccount1 = transferAccount;
        double transactionAmount1 = transactionAmount;
        String particulars1 = particulars;
        String[] accountNumber1 = accountNumber.split("-");
        transferBean.setAccountNumber(Integer.parseInt(accountNumber1[1]));
        transferBean.setParticulars(particulars);
        transferBean.setTransactionAmount(transactionAmount);
        transferBean.setTransactionPassword(transactionPassword);
        transferBean.setTransferAccount(Integer.parseInt(transferAccount));
        transferBean.setUserId(userId);
        LoginInfoBean loginInfoBean = new LoginInfoBean();
        loginInfoBean = (LoginInfoBean) session.get("loginInfo");
        if (loginInfoBean == null) {
            return "error";
        } else {
            String result = beneficiaryDelegate.validateTransaction(transferBean);
            if (result.equals("success")) {
                return "transfer";
            } else if (result.equals("technicalError")) {
                addActionError("Some error in transaction");
                String transactionType = "NEFT";
                activeBeneficiaryList = beneficiaryDelegate.getActiveBeneficiary(Integer.parseInt(accountNumber1[1]), transactionType);
                return INPUT;
            } else if (result.equals("transactionPasswordError")) {
                addActionError("*Invalid Password");
                String transactionType = "NEFT";
                activeBeneficiaryList = beneficiaryDelegate.getActiveBeneficiary(Integer.parseInt(accountNumber1[1]), transactionType);
                return INPUT;
            } else {
                addActionError("Insufficient Balance");
                String transactionType = "NEFT";
                activeBeneficiaryList = beneficiaryDelegate.getActiveBeneficiary(Integer.parseInt(accountNumber1[1]), transactionType);
                return INPUT;
            }
        }

    }
}

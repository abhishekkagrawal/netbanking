/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import static com.opensymphony.xwork2.Action.SUCCESS;
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
 * Ended on:
 *
 * Started on: Oct 29 2013
 *
 * @author Jay Prakash
 */
public class ConfirmAndRejectBeneficiaryNEFTAction extends ActionSupport implements SessionAware {

    ArrayList deactiveBeneficiaryList = new ArrayList();
    ArrayList activeBeneficiaryList = new ArrayList();
    BeneficiaryDelegate beneficiaryDelegate = new BeneficiaryDelegate();
    Map<String, Object> session;
    int URNNumber;
    int beneficiaryId;
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

    public int getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(int beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public int getURNNumber() {
        return URNNumber;
    }

    public void setURNNumber(int URNNumber) {
        this.URNNumber = URNNumber;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public ArrayList getDeactiveBeneficiaryList() {
        return deactiveBeneficiaryList;
    }

    public void setDeactiveBeneficiaryList(ArrayList deactiveBeneficiaryList) {
        this.deactiveBeneficiaryList = deactiveBeneficiaryList;
    }

    public ArrayList getActiveBeneficiaryList() {
        return activeBeneficiaryList;
    }

    public void setActiveBeneficiaryList(ArrayList activeBeneficiaryList) {
        this.activeBeneficiaryList = activeBeneficiaryList;
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

    //forwarding to card detail page with  credit card detail list
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

    // forwarding to register bebeficiary page with bank name list
    public String registerBeneficary() {
        bankList = beneficiaryDelegate.getBankDetail();
        return "registerBeneficary";
    }

    // forwarding to confirm and reject beneficiary page with deactive beneficiary list for validate beneficiary 
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

    //forwardting to transaction page with active beneficiary list
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

    //forwarding to registred beneficiary page with active beneficiary list
    public String registeredBeneficiary() {
        LoginInfoBean loginInfoBean = new LoginInfoBean();
        loginInfoBean = (LoginInfoBean) session.get("loginInfo");
        if (loginInfoBean == null) {
            return "error";
        } else {
            int accountNumber = loginInfoBean.getAccountNumber();
            String transactionType = "NEFT";
            activeBeneficiaryList = beneficiaryDelegate.getActiveBeneficiary(accountNumber, transactionType);
            return "registeredBeneficiary";
        }
    }

    //function to confirm and delete beneficiary
    public String execute() {
        boolean result = false;
        LoginInfoBean loginInfoBean = new LoginInfoBean();
        loginInfoBean = (LoginInfoBean) session.get("loginInfo");
        if (loginInfoBean == null) {
            return "error";
        } else {
            int accountNumber = loginInfoBean.getAccountNumber();
            result = beneficiaryDelegate.confirmBeneficiary(accountNumber, beneficiaryId, URNNumber);
            if (result) {
                return "transfer";
            } else {
                //int accountNumber = loginInfoBean.getAccountNumber();
                String transactionType = "NEFT";
                deactiveBeneficiaryList = beneficiaryDelegate.getDeactiveBeneficiary(accountNumber, transactionType);
                addActionError("*invalid URN number");
                return INPUT;
            }
        }
    }
}

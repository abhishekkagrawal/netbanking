/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.webaccess.netbanking.bean.BeneficiaryDetailBean;
import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.BeneficiaryDelegate;
import com.webaccess.netbanking.delegate.BillDelegate;
import com.webaccess.netbanking.delegate.CreditCardDelegate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Started on: Oct 29 2013
 *
 * Ended On: Nov 10 2013
 *
 * @author jay prakash
 */
public class RegisterBeneficiaryNEFTAction extends ActionSupport implements SessionAware, ModelDriven<BeneficiaryDetailBean> {

    BeneficiaryDetailBean beneficiaryDetail = new BeneficiaryDetailBean();
    ArrayList deactiveBeneficiaryList = new ArrayList();
    ArrayList activeBeneficiaryList = new ArrayList();
    Map<String, Object> session;
    BeneficiaryDelegate delegate = new BeneficiaryDelegate();
    List bankName = new ArrayList();
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

    public BeneficiaryDetailBean getBeneficiaryDetail() {
        return beneficiaryDetail;
    }

    public void setBeneficiaryDetail(BeneficiaryDetailBean beneficiaryDetail) {
        this.beneficiaryDetail = beneficiaryDetail;
    }

    public List getBankName() {
        return bankName;
    }

    public void setBankName(List bankName) {
        this.bankName = bankName;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    //forwarding t home page
    public String myAccount() {
        return "myAccount";
    }

    //fprwarding to transfer page
    public String transfer() {
        return "transfer";
    }

    //forwarding to bill page jsp with state list and biller list
    public String billPayment() {
        BillDelegate billDelegate = new BillDelegate();
        billerList = billDelegate.getBiller();
        stateList = billDelegate.getState();
        return "billPayment";
    }

    //forwarding to card detail page jsp with all card detail
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

    //forwarding to  new register beneficiary page with bank list
    public String registerBeneficary() {
        bankList = delegate.getBankDetail();
        return "registerBeneficary";
    }

    //forwarding to confirm and reject beneficiary page jsp with deactive beneficiary
    public String confirAndRejectBeneficiary() {

        LoginInfoBean loginInfoBean = new LoginInfoBean();
        loginInfoBean = (LoginInfoBean) session.get("loginInfo");
        if (loginInfoBean == null) {
            return "error";
        } else {
            int accountNumber = loginInfoBean.getAccountNumber();
            String transactionType = "NEFT";
            deactiveBeneficiaryList = delegate.getDeactiveBeneficiary(accountNumber, transactionType);
            return "confirAndRejectBeneficiary";
        }
    }

    //forwarding to new transaction page jsp with active beneficiary
    public String newTransaction() {
        LoginInfoBean loginInfoBean = new LoginInfoBean();
        loginInfoBean = (LoginInfoBean) session.get("loginInfo");
        if (loginInfoBean == null) {
            return "error";
        } else {
            int accountNumber = loginInfoBean.getAccountNumber();
            String transactionType = "NEFT";
            activeBeneficiaryList = delegate.getActiveBeneficiary(accountNumber, transactionType);
            return "newTransaction";
        }
    }

    //forwarding to registered beneficiary page jsp with active beneficiary
    public String registeredBeneficiary() {
        LoginInfoBean loginInfoBean = new LoginInfoBean();
        loginInfoBean = (LoginInfoBean) session.get("loginInfo");
        if (loginInfoBean == null) {
            return "error";
        } else {
            int accountNumber = loginInfoBean.getAccountNumber();
            String transactionType = "NEFT";
            activeBeneficiaryList = delegate.getActiveBeneficiary(accountNumber, transactionType);
            return "registeredBeneficiary";
        }
    }

    //forwarding to transfer page
    public String back() {
        return "back";
    }

    //inserting new beneficiary into database
    public String execute() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            beneficiaryDetail.setAccountNumber(userInfoBean.getAccountNumber());
            String result = delegate.addBeneficiary(beneficiaryDetail);
            if (result.equals("success")) {
                return "transfer";
            } else {
                addActionError("This account is already added..");
                bankList = delegate.getBankDetail();
                return INPUT;
            }
        }
    }

    @Override
    public BeneficiaryDetailBean getModel() {
        return beneficiaryDetail;
    }
}

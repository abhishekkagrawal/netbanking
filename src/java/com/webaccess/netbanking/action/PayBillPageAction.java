/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.BSNLCelloneBillBean;
import com.webaccess.netbanking.bean.ElectricityBillBean;
import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.SBILifeInsurenceBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.BillDelegate;
import com.webaccess.netbanking.delegate.CreditCardDelegate;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Started on:Nov 06 2013
 *
 * Ended On: Nov 06 2013
 *
 * @author Jay Prakash
 */
public class PayBillPageAction extends ActionSupport implements SessionAware {

    BillDelegate billDelegate = new BillDelegate();
    ArrayList stateList = new ArrayList();
    ArrayList billerList = new ArrayList();
    Map<String, Object> session;
    ArrayList electricityBillList = new ArrayList();
    ArrayList sbiLifeInsurenceList = new ArrayList();
    ArrayList bsnlCelloneBillList = new ArrayList();
    private int electricityBillId;
    private double electricityBillAmount;
    private String electricityBillRemark;
    private int bsnlCelloneBillId;
    private double bsnlCelloneBillAmount;
    private String bsnlCelloneBillRemark;
    private int sbiLifeInsurenceBillId;
    private double sbiLifeInsurenceBillAmount;
    private String sbiLifeInsurenceBillRemark;
    private String electricityBillUserId;
    private String electricityBillTransactionPassword;
    private String BSNLCelloneUserId;
    private String BSNLCelloneTransactionPassword;
    private String SBILifeUserId;
    private String SBILifeTransactionPassword;
    CreditCardDelegate creditCardDelegate = new CreditCardDelegate();
    ArrayList creditCardDetailList = new ArrayList();

    public ArrayList getCreditCardDetailList() {
        return creditCardDetailList;
    }

    public void setCreditCardDetailList(ArrayList creditCardDetailList) {
        this.creditCardDetailList = creditCardDetailList;
    }

    public String getElectricityBillUserId() {
        return electricityBillUserId;
    }

    public void setElectricityBillUserId(String electricityBillUserId) {
        this.electricityBillUserId = electricityBillUserId;
    }

    public String getElectricityBillTransactionPassword() {
        return electricityBillTransactionPassword;
    }

    public void setElectricityBillTransactionPassword(String electricityBillTransactionPassword) {
        this.electricityBillTransactionPassword = electricityBillTransactionPassword;
    }

    public String getBSNLCelloneUserId() {
        return BSNLCelloneUserId;
    }

    public void setBSNLCelloneUserId(String BSNLCelloneUserId) {
        this.BSNLCelloneUserId = BSNLCelloneUserId;
    }

    public String getBSNLCelloneTransactionPassword() {
        return BSNLCelloneTransactionPassword;
    }

    public void setBSNLCelloneTransactionPassword(String BSNLCelloneTransactionPassword) {
        this.BSNLCelloneTransactionPassword = BSNLCelloneTransactionPassword;
    }

    public String getSBILifeUserId() {
        return SBILifeUserId;
    }

    public void setSBILifeUserId(String SBILifeUserId) {
        this.SBILifeUserId = SBILifeUserId;
    }

    public String getSBILifeTransactionPassword() {
        return SBILifeTransactionPassword;
    }

    public void setSBILifeTransactionPassword(String SBILifeTransactionPassword) {
        this.SBILifeTransactionPassword = SBILifeTransactionPassword;
    }

    public int getElectricityBillId() {
        return electricityBillId;
    }

    public void setElectricityBillId(int electricityBillId) {
        this.electricityBillId = electricityBillId;
    }

    public double getElectricityBillAmount() {
        return electricityBillAmount;
    }

    public void setElectricityBillAmount(double electricityBillAmount) {
        this.electricityBillAmount = electricityBillAmount;
    }

    public String getElectricityBillRemark() {
        return electricityBillRemark;
    }

    public void setElectricityBillRemark(String electricityBillRemark) {
        this.electricityBillRemark = electricityBillRemark;
    }

    public double getBsnlCelloneBillAmount() {
        return bsnlCelloneBillAmount;
    }

    public void setBsnlCelloneBillAmount(double bsnlCelloneBillAmount) {
        this.bsnlCelloneBillAmount = bsnlCelloneBillAmount;
    }

    public String getBsnlCelloneBillRemark() {
        return bsnlCelloneBillRemark;
    }

    public void setBsnlCelloneBillRemark(String bsnlCelloneBillRemark) {
        this.bsnlCelloneBillRemark = bsnlCelloneBillRemark;
    }

    public int getBsnlCelloneBillId() {
        return bsnlCelloneBillId;
    }

    public void setBsnlCelloneBillId(int bsnlCelloneBillId) {
        this.bsnlCelloneBillId = bsnlCelloneBillId;
    }

    public int getSbiLifeInsurenceBillId() {
        return sbiLifeInsurenceBillId;
    }

    public void setSbiLifeInsurenceBillId(int sbiLifeInsurenceBillId) {
        this.sbiLifeInsurenceBillId = sbiLifeInsurenceBillId;
    }

    public double getSbiLifeInsurenceBillAmount() {
        return sbiLifeInsurenceBillAmount;
    }

    public void setSbiLifeInsurenceBillAmount(double sbiLifeInsurenceBillAmount) {
        this.sbiLifeInsurenceBillAmount = sbiLifeInsurenceBillAmount;
    }

    public String getSbiLifeInsurenceBillRemark() {
        return sbiLifeInsurenceBillRemark;
    }

    public void setSbiLifeInsurenceBillRemark(String sbiLifeInsurenceBillRemark) {
        this.sbiLifeInsurenceBillRemark = sbiLifeInsurenceBillRemark;
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

    public ArrayList getElectricityBillList() {
        return electricityBillList;
    }

    public void setElectricityBillList(ArrayList electricityBillList) {
        this.electricityBillList = electricityBillList;
    }

    public ArrayList getSbiLifeInsurenceList() {
        return sbiLifeInsurenceList;
    }

    public void setSbiLifeInsurenceList(ArrayList sbiLifeInsurenceList) {
        this.sbiLifeInsurenceList = sbiLifeInsurenceList;
    }

    public ArrayList getBsnlCelloneBillList() {
        return bsnlCelloneBillList;
    }

    public void setBsnlCelloneBillList(ArrayList bsnlCelloneBillList) {
        this.bsnlCelloneBillList = bsnlCelloneBillList;
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

    // forwarding to transfer page 
    public String transfer() {
        return "transfer";
    }

    //forwarding to card detail page with credit card list
    public String cardDetail() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountNumber = userInfoBean.getAccountNumber();
            //getting credit card list
            creditCardDetailList = creditCardDelegate.getCreditCardetailList(accountNumber);
            return "cardDetail";
        }
    }

    //forwarding to bill payment page with state and biller list
    public String billPayment() {
        billerList = billDelegate.getBiller();
        stateList = billDelegate.getState();
        return "billPayment";
    }

    //forwarding to personal detail page
    public String personalDetail() {

        return "personalDetail";
    }

    //forwarding to add billpage with biller and state list
    public String addBill() {
        billerList = billDelegate.getBiller();
        stateList = billDelegate.getState();
        return "billPayment";
    }

    @Override
    public String execute() throws Exception {
        return "success";
    }

    //forwarding to modify bill page with electric ,bsnlcellone and sbi lifr insurece list
    public String modifyBill() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountnumber = userInfoBean.getAccountNumber();
            //getting electric bill list
            electricityBillList = billDelegate.getElectricityBillList(accountnumber);
            //getting bsnl bill list
            bsnlCelloneBillList = billDelegate.getBSNLCelloneBillList(accountnumber);
            //getting sbi life insurence bill list
            sbiLifeInsurenceList = billDelegate.getSBILifeInsurenceList(accountnumber);
            return "modifyBill";
        }
    }

    //fowarding to pay bill page 
    public String payBill() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountnumber = userInfoBean.getAccountNumber();
            //getting electric bill list
            electricityBillList = billDelegate.getElectricityBillList(accountnumber);
            //getting bsnl bill list
            bsnlCelloneBillList = billDelegate.getBSNLCelloneBillList(accountnumber);
            //getting sbi life insurence bill list
            sbiLifeInsurenceList = billDelegate.getSBILifeInsurenceList(accountnumber);
            return "payBill";
        }
    }

    //method will work for paying electric bill
    public String payElectricityBill() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountnumber = userInfoBean.getAccountNumber();
            ElectricityBillBean electricityBillBean = new ElectricityBillBean();
            LoginInfoBean loginInfoBean = new LoginInfoBean();
            loginInfoBean.setUserId(getElectricityBillUserId());
            loginInfoBean.setTransactionPassword(getElectricityBillTransactionPassword());
            loginInfoBean.setAccountNumber(accountnumber);
            electricityBillBean.setAccountNumber(accountnumber);
            electricityBillBean.setElectricityBillId(getElectricityBillId());
            electricityBillBean.setAmount(getElectricityBillAmount());
            String result = billDelegate.payElectricityBill(electricityBillBean, loginInfoBean, getElectricityBillRemark());
            if (result.equals("success")) {
                return "success";
            }
            if (result.equals("transactionPasswordError")) {
                addActionError("*Invalid Password");
                electricityBillList = billDelegate.getElectricityBillList(accountnumber);
                bsnlCelloneBillList = billDelegate.getBSNLCelloneBillList(accountnumber);
                sbiLifeInsurenceList = billDelegate.getSBILifeInsurenceList(accountnumber);
                return "input";
            }
            if (result.equals("balanceError")) {
                addActionError("*Insufficient Balance");
                electricityBillList = billDelegate.getElectricityBillList(accountnumber);
                bsnlCelloneBillList = billDelegate.getBSNLCelloneBillList(accountnumber);
                sbiLifeInsurenceList = billDelegate.getSBILifeInsurenceList(accountnumber);
                return "input";
            }
            if (result.equals("technicalError")) {
                addActionError("*Sorry unable to process try again");
                electricityBillList = billDelegate.getElectricityBillList(accountnumber);
                bsnlCelloneBillList = billDelegate.getBSNLCelloneBillList(accountnumber);
                sbiLifeInsurenceList = billDelegate.getSBILifeInsurenceList(accountnumber);
                return "input";
            }
            return SUCCESS;
        }

    }

    //paying BSNL bill
    public String payBSNLCelloneBill() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountnumber = userInfoBean.getAccountNumber();
            BSNLCelloneBillBean bSNLCelloneBillBean = new BSNLCelloneBillBean();
            LoginInfoBean loginInfoBean = new LoginInfoBean();
            loginInfoBean.setUserId(getBSNLCelloneUserId());
            loginInfoBean.setTransactionPassword(getBSNLCelloneTransactionPassword());
            loginInfoBean.setAccountNumber(accountnumber);
            bSNLCelloneBillBean.setAccountNumber(accountnumber);
            bSNLCelloneBillBean.setAmount(getBsnlCelloneBillAmount());
            bSNLCelloneBillBean.setBsnlCelloneId(getBsnlCelloneBillId());
            String result = billDelegate.payBSNLCelloneBill(bSNLCelloneBillBean, loginInfoBean, getBsnlCelloneBillRemark());
            if (result.equals("success")) {
                return "success";
            }
            if (result.equals("transactionPasswordError")) {
                addActionError("*Invalid Password");
                electricityBillList = billDelegate.getElectricityBillList(accountnumber);
                bsnlCelloneBillList = billDelegate.getBSNLCelloneBillList(accountnumber);
                sbiLifeInsurenceList = billDelegate.getSBILifeInsurenceList(accountnumber);
                return "input";
            }
            if (result.equals("balanceError")) {
                addActionError("*Insufficient Balance");
                electricityBillList = billDelegate.getElectricityBillList(accountnumber);
                bsnlCelloneBillList = billDelegate.getBSNLCelloneBillList(accountnumber);
                sbiLifeInsurenceList = billDelegate.getSBILifeInsurenceList(accountnumber);
                return "input";
            }
            if (result.equals("technicalError")) {
                addActionError("*Sorry unable to process try again");
                electricityBillList = billDelegate.getElectricityBillList(accountnumber);
                bsnlCelloneBillList = billDelegate.getBSNLCelloneBillList(accountnumber);
                sbiLifeInsurenceList = billDelegate.getSBILifeInsurenceList(accountnumber);
                return "input";
            }
            return SUCCESS;
        }

    }

    //pay SBI life insurence bill
    public String paySBILifeInsurenceBill() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountnumber = userInfoBean.getAccountNumber();
            SBILifeInsurenceBean sBILifeInsurenceBean = new SBILifeInsurenceBean();
            LoginInfoBean loginInfoBean = new LoginInfoBean();
            loginInfoBean.setUserId(getSBILifeUserId());
            loginInfoBean.setTransactionPassword(getSBILifeTransactionPassword());
            loginInfoBean.setAccountNumber(accountnumber);
            sBILifeInsurenceBean.setAccountNumber(accountnumber);
            sBILifeInsurenceBean.setAmount(getSbiLifeInsurenceBillAmount());
            sBILifeInsurenceBean.setSbiLifeinsurenceId(getSbiLifeInsurenceBillId());
            String result = billDelegate.paySBILifeInsurenceBill(sBILifeInsurenceBean, loginInfoBean, getSbiLifeInsurenceBillRemark());
            if (result.equals("success")) {
                return "success";
            }
            if (result.equals("transactionPasswordError")) {
                addActionError("*Invalid Password");
                electricityBillList = billDelegate.getElectricityBillList(accountnumber);
                bsnlCelloneBillList = billDelegate.getBSNLCelloneBillList(accountnumber);
                sbiLifeInsurenceList = billDelegate.getSBILifeInsurenceList(accountnumber);
                return "input";
            }
            if (result.equals("balanceError")) {
                addActionError("*Insufficient Balance");
                electricityBillList = billDelegate.getElectricityBillList(accountnumber);
                bsnlCelloneBillList = billDelegate.getBSNLCelloneBillList(accountnumber);
                sbiLifeInsurenceList = billDelegate.getSBILifeInsurenceList(accountnumber);
                return "input";
            }
            if (result.equals("technicalError")) {
                addActionError("*Sorry unable to process try again");
                electricityBillList = billDelegate.getElectricityBillList(accountnumber);
                bsnlCelloneBillList = billDelegate.getBSNLCelloneBillList(accountnumber);
                sbiLifeInsurenceList = billDelegate.getSBILifeInsurenceList(accountnumber);
                return "input";
            }
            return SUCCESS;
        }

    }
}

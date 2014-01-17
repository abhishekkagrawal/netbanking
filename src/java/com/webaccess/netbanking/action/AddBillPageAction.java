/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.BSNLCelloneBillBean;
import com.webaccess.netbanking.bean.ElectricityBillBean;
import com.webaccess.netbanking.bean.SBILifeInsurenceBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.BillDelegate;
import com.webaccess.netbanking.delegate.CreditCardDelegate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * this action class is for adding new bill into database and forwarding to
 * different pages
 *
 * Started On:Nov 04 2013
 *
 * Ended On: Nov 06 2013
 *
 *
 * @author Jay Prakash
 */
public class AddBillPageAction extends ActionSupport implements SessionAware {

    ArrayList stateList = new ArrayList();
    ArrayList billerList = new ArrayList();
    BillDelegate billDelegate = new BillDelegate();
    Map<String, Object> session;
    private String electricityNickName;
    private int billingUnit;
    private int processingCycle;
    private int consumerNumber;
    private String bsnlCelloneNickName;
    private long mobilenumber;
    private String sbiLifeInsurenceNickName;
    private int policyNumber;
    Date dateOfBirth;
    private int state;
    private int biller;
    ArrayList electricityBillList = new ArrayList();
    ArrayList sbiLifeInsurenceList = new ArrayList();
    ArrayList bsnlCelloneBillList = new ArrayList();
    CreditCardDelegate creditCardDelegate = new CreditCardDelegate();
    ArrayList creditCardDetailList = new ArrayList();

    public ArrayList getCreditCardDetailList() {
        return creditCardDetailList;
    }

    public void setCreditCardDetailList(ArrayList creditCardDetailList) {
        this.creditCardDetailList = creditCardDetailList;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getBiller() {
        return biller;
    }

    public void setBiller(int biller) {
        this.biller = biller;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getElectricityNickName() {
        return electricityNickName;
    }

    public void setElectricityNickName(String electricityNickName) {
        this.electricityNickName = electricityNickName;
    }

    public int getBillingUnit() {
        return billingUnit;
    }

    public void setBillingUnit(int billingUnit) {
        this.billingUnit = billingUnit;
    }

    public int getProcessingCycle() {
        return processingCycle;
    }

    public void setProcessingCycle(int processingCycle) {
        this.processingCycle = processingCycle;
    }

    public int getConsumerNumber() {
        return consumerNumber;
    }

    public void setConsumerNumber(int consumerNumber) {
        this.consumerNumber = consumerNumber;
    }

    public String getBsnlCelloneNickName() {
        return bsnlCelloneNickName;
    }

    public void setBsnlCelloneNickName(String bsnlCelloneNickName) {
        this.bsnlCelloneNickName = bsnlCelloneNickName;
    }

    public long getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(long mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getSbiLifeInsurenceNickName() {
        return sbiLifeInsurenceNickName;
    }

    public void setSbiLifeInsurenceNickName(String sbiLifeInsurenceNickName) {
        this.sbiLifeInsurenceNickName = sbiLifeInsurenceNickName;
    }

    public int getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(int policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    //getting card detail from database
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

    //forwarding to add bill page jsp with all biller list and state list
    public String addBill() {

        billerList = billDelegate.getBiller();//getting biller list from database
        stateList = billDelegate.getState();//getting state list from database
        return "billPayment";
    }

    //forwarding to modify bill page jsp with biller detail  
    public String modifyBill() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");//getting session values
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountnumber = userInfoBean.getAccountNumber();
            //getting total electric bill added for account number
            electricityBillList = billDelegate.getElectricityBillList(accountnumber);
            // getting total bsnl  bill list added for account number
            bsnlCelloneBillList = billDelegate.getBSNLCelloneBillList(accountnumber);
            // getting total sbi life insurence added wtih account number
            sbiLifeInsurenceList = billDelegate.getSBILifeInsurenceList(accountnumber);
            return "modifyBill";
        }
    }

    //forwarding to paybill page jsp
    public String payBill() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountnumber = userInfoBean.getAccountNumber();
            //getting total electric bill added for account number
            electricityBillList = billDelegate.getElectricityBillList(accountnumber);
            // getting total bsnl  bill list added for account number
            bsnlCelloneBillList = billDelegate.getBSNLCelloneBillList(accountnumber);
            // getting total sbi life insurence added wtih account number
            sbiLifeInsurenceList = billDelegate.getSBILifeInsurenceList(accountnumber);
            return "payBill";
        }
    }

    //saving bill detail and forwarding to home page jsp
    public String electricity() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountnumber = userInfoBean.getAccountNumber();
            ElectricityBillBean electricityBillBean = new ElectricityBillBean();
            electricityBillBean.setNickName(getElectricityNickName());
            electricityBillBean.setAccountNumber(accountnumber);
            electricityBillBean.setConsumerNumber(getConsumerNumber());
            electricityBillBean.setBillingUnit(getBillingUnit());
            electricityBillBean.setProcessingCycle(getProcessingCycle());
            electricityBillBean.setStateId(getState());
            String result = billDelegate.insertBillInformation(electricityBillBean);
            if (result.equals("success")) {
                return "electricity";
            } else {
                billerList = billDelegate.getBiller();//getting biller list from database
                stateList = billDelegate.getState();//getting state list from database
                addActionError("You have already added this consumer number");
                return "input";
            }
        }
    }

    //saving life insurence detail and forwarding to home page jsp
    public String sbiLifeInsurence() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountnumber = userInfoBean.getAccountNumber();
            SBILifeInsurenceBean sBILifeInsurenceBean = new SBILifeInsurenceBean();
            sBILifeInsurenceBean.setNickName(getSbiLifeInsurenceNickName());
            sBILifeInsurenceBean.setAccountNumber(accountnumber);
            sBILifeInsurenceBean.setDobOfpolicyHolder(getDateOfBirth());
            sBILifeInsurenceBean.setPolicynumber(getPolicyNumber());
            sBILifeInsurenceBean.setStateId(getState());
            String result = billDelegate.insertSBILifeInsurenceInformation(sBILifeInsurenceBean);
            if (result.equals("success")) {
                return "electricity";
            } else {
                billerList = billDelegate.getBiller();//getting biller list from database
                stateList = billDelegate.getState();//getting state list from database
                addActionError("You have already added this policy number");
                return "input";
            }
        }
    }

    //saving bsnl bill detail and forwarding to home page jsp
    public String bsnlCellone() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        if (userInfoBean == null) {
            return "error";
        } else {
            int accountnumber = userInfoBean.getAccountNumber();
            BSNLCelloneBillBean bSNLCelloneBillBean = new BSNLCelloneBillBean();
            bSNLCelloneBillBean.setAccountNumber(accountnumber);
            bSNLCelloneBillBean.setMobileNumber(getMobilenumber());
            bSNLCelloneBillBean.setNickName(getBsnlCelloneNickName());
            bSNLCelloneBillBean.setStateId(getState());
            String result = billDelegate.insertbSNLCelloneBillInformation(bSNLCelloneBillBean);
            if (result.equals("success")) {
                return "electricity";
            } else {
                billerList = billDelegate.getBiller();//getting biller list from database
                stateList = billDelegate.getState();//getting state list from database
                addActionError("You have already added this mobile number");
                return "input";
            }
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.NewRegistrationBean;
import com.webaccess.netbanking.delegate.NewRegistrationdelegate;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Started on : Nov 01 2013
 *
 * Ended on Nov 03 2013
 *
 * @author Jay prakash
 */
public class NewRegistrationAction extends ActionSupport {

    String accountNumber;
    String mobileNumber;
    String date;
    String panCardNumber;
    String loginType;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    //registering new user
    public String regitserUSer() {
        String result = "";
        try {
            NewRegistrationBean registrationBean = new NewRegistrationBean();
            NewRegistrationdelegate registrationdelegate = new NewRegistrationdelegate();
            registrationBean.setAccountNumber(Integer.parseInt(accountNumber));
            registrationBean.setMobileNumber(Long.parseLong(mobileNumber));
            registrationBean.setLoginType(loginType);
            registrationBean.setPanCardNumber(panCardNumber);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date date1 = dateFormat.parse(date);
            registrationBean.setDate(date1);
            result = registrationdelegate.newUser(registrationBean);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result.equals("wrongInfo")) {
            addActionError("you have entered wrong informaton");
            return "asd";
        } else if (result.equals("alreadyPresent")) {
            addActionError("your user id is generated before please contact to back");
            return "asd";
        } else if (result.equals("success")) {
            return "success";
        }
        return "aaa";

    }

    //forwarding to start page
    public String back() {
        return "back";
    }
}

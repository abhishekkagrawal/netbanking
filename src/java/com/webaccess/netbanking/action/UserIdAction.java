/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.delegate.StartPageDelegate;

/**
 * Started On:Oct 15 2013
 * 
 * Ended On :Oct 16 2013
 *
 * @author Jay prakash
 */
public class UserIdAction extends ActionSupport {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    //validating user Id
    public String execute() {
        StartPageDelegate delegate = new StartPageDelegate();
        boolean result = false;
        result = delegate.checkUserIdDelegate(userId);
        if (result) {
            return SUCCESS;
        } else {
            addFieldError("userId", "*Invalid UserId");
            return INPUT;
        }
    }

    //forwarding to new user page
    public String newUser() {
        return "newuser";
    }

    //forwarding to forgot password page
    public String forgotPassword() {
        return "forgotPassword";
    }

    //forwarding to start page
    public String back() {
        return "back";
    }
}

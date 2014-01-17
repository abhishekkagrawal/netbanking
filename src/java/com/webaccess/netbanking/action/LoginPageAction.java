/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.delegate.LoginPageDelegate;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Started on: Oct 15 2013
 * 
 * Ended on: Oct 15 2013
 *
 * @author Jay Prakash
 */

//action for all action present into login form
public class LoginPageAction extends ActionSupport implements SessionAware {

    private String userId;
    private String password;
    private Map session;

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //validating login password i putting into session
    public String execute() {
        boolean result = false;
        LoginPageDelegate delegate = new LoginPageDelegate();
        result = delegate.checkUserDelegate(userId, password);
        if (result) {
            session.put("userId", getUserId());
            session.put("login", true);
            return SUCCESS;
        } else {
            addActionMessage("* Invalid password");
            addActionError("* Invalid password");
            return "input";
        }
    }
    
    //forwarding to new registration page
    public String newUser() {
        return "newuser";
    }

    
    //forwarding to forgot password page 
    public String forgotPassword() {
        return "forgotPassword";
    }

    // forwarding to start page
    public String differentUser() {
        return "differentUser";
    }
}

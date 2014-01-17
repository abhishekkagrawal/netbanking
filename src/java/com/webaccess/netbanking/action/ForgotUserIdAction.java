/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.delegate.ForgotUserIdDelegate;

/**
 * Started On: Oct 15 2013
 * 
 * Ended On: Oct 16 2013
 *
 * @author Jay Prakash
 */
public class ForgotUserIdAction extends ActionSupport {

    String userId;
    

   
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    //validating vser Id and getting opt number
    public String execute() {
        ForgotUserIdDelegate delegate = new ForgotUserIdDelegate();
        boolean result = false;   
        int optNumber;
        result = delegate.checkUserId(userId);
        if (result) {
            optNumber = delegate.getOPTNumber(userId);
            return SUCCESS;
        } else {
            addFieldError("userId", "*Invalid UserId");
            return INPUT;
        }
    }
   
}

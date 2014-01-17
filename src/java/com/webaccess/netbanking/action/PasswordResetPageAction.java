/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.webaccess.netbanking.bean.PasswordResetBean;
import com.webaccess.netbanking.delegate.PasswordResetDelegate;

/**
 * Started on: Oct 17 2013
 *
 * Ended on: Oct 17 2013
 *
 * @author Jay Prakash
 *
 */
public class PasswordResetPageAction extends ActionSupport implements ModelDriven {

    PasswordResetBean passwordResetBean = new PasswordResetBean();
    PasswordResetDelegate delegate = new PasswordResetDelegate();

    public PasswordResetBean getPasswordResetBean() {
        return passwordResetBean;
    }

    public void setPasswordResetBean(PasswordResetBean passwordResetBean) {
        this.passwordResetBean = passwordResetBean;
    }

    //checking password
    public String execute() {
        String result = delegate.checkPasswordReset(passwordResetBean);
        if (result.equals("success")) {
            return SUCCESS;
        } else {
            addActionError("you have entered wrong information..");
            return INPUT;
        }
    }

    @Override
    public Object getModel() {
        return passwordResetBean;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.LogoutDelegate;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Jay Prakash Started on: Oct 19 2013
 *
 */
public class LogoutAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    //method for logout
    @Override
    public String execute() throws Exception {
        LogoutDelegate delegate = new LogoutDelegate();
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean = (UserInfoBean) session.get("userInfo");
        String userId = session.get("userId").toString();
        if (userInfoBean == null || userId.equals("")) {
            return "error";
        } else {
            delegate.insertLogoutTime(userId);
            session.remove("userId");
            session.remove("login");
            session.clear();
            return SUCCESS;
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.delegate.GetUserInfoDelegate;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Administrator
 */
public class GetUserInformation extends ActionSupport implements SessionAware{
    
    LoginInfoBean loginInfoBean;
    UserInfoBean userInfoBean;
    Map<String, Object> session;

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public LoginInfoBean getLoginInfoBean() {
        return loginInfoBean;
    }

    public void setLoginInfoBean(LoginInfoBean loginInfoBean) {
        this.loginInfoBean = loginInfoBean;
    }

    public UserInfoBean getUserInfoBean() {
        return userInfoBean;
    }

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }
    
    //getting user information and puting into session
    public String execute(){    
        GetUserInfoDelegate delegate=new GetUserInfoDelegate();
        String userId=session.get("userId").toString();
        //getting user information
        userInfoBean=delegate.getUserInformation(userId);
        //getting login information
        loginInfoBean=delegate.getLoginInformation(userId);
        session.put("userInfo", userInfoBean);
        session.put("loginInfo", loginInfoBean);
        return SUCCESS; 
        
    }

  
    
}

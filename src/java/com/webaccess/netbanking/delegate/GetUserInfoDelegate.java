/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;

import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.dao.GetUserInfoDao;

/**
 *
 * @author Administrator
 */
public class GetUserInfoDelegate {
    
    private GetUserInfoDao dao;
    
    public GetUserInfoDelegate(){
        dao=new GetUserInfoDao();
    }
    
    public UserInfoBean getUserInformation(String userId){
    
       return dao.getUserInformation(userId);
    
    }
    public LoginInfoBean getLoginInformation(String userId){
    
       return dao.getLoginInformation(userId);
    
    }
    
    
}

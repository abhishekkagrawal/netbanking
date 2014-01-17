/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;

import com.webaccess.netbanking.dao.LogoutDao;

/**
 *
 * @author Administrator
 */
public class LogoutDelegate {
    
    LogoutDao dao;
    
   public LogoutDelegate(){
       dao=new LogoutDao();
    }
   
    public void  insertLogoutTime(String userId){
         dao.insertLogoutTime(userId);
    }
}

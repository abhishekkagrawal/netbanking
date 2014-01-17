/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;

import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.dao.ChangePasswordDao;

/**
 *
 * @author Jay Prakash
 * Started on: Oct 28 2013
 * Ended on: Oct 28 2013
 * 
 */
public class ChangePasswordDelegate {
    
    ChangePasswordDao dao;

    public ChangePasswordDelegate() {
        dao=new ChangePasswordDao();
    }
    
   public boolean changeLoginPassword(LoginInfoBean loginInfoBean,String oldLoginPassword){
       return dao.changeLoginPassword(loginInfoBean,oldLoginPassword);
    }
    
    public boolean changeTransactionPassword(LoginInfoBean loginInfoBean,String oldTransactionPassword){
       return dao.changeTransactionPassword(loginInfoBean,oldTransactionPassword);
    }
     
    
    
    
}

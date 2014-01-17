/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;

import com.webaccess.netbanking.bean.PasswordResetBean;
import com.webaccess.netbanking.dao.PasswordResetDao;

/**
 *
 * @author Jay Prakash 
 * Started on: Oct 17 2013
 * Ended on: Oct 17 2013
 */
public class PasswordResetDelegate {
    PasswordResetDao dao=null;
     public PasswordResetDelegate(){
         dao=new  PasswordResetDao();
     }
    
    public String checkPasswordReset(PasswordResetBean passwordResetBean){
        return dao.checkPasswordReset(passwordResetBean);
    
    }
    
}

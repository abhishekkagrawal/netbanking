/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;

import com.webaccess.netbanking.dao.ForgotUserIdDao;

/**
 *
 * @author Administrator
 */
public class ForgotUserIdDelegate {
    ForgotUserIdDao dao=null;
    public ForgotUserIdDelegate(){
        dao=new ForgotUserIdDao();
    }
       public boolean checkUserId(String userId) {
           return dao.checkUserId(userId);
       }
         public int getOPTNumber(String useId) {
            return dao.getOPTNumber(useId);
         }
    
}

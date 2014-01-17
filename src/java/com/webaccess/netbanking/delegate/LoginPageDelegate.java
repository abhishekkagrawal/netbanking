/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;

import com.webaccess.netbanking.dao.LoginPageDao;

/**
 *
 * @author Jay Prakash
 * Started on: oct 03 2013
 * Ended on: oct 03 2013
 * 
 */
public class LoginPageDelegate {

    LoginPageDao dao = null;

    public LoginPageDelegate() {
        dao = new LoginPageDao();
    }

    public boolean checkUserDelegate(String userId, String password) {
        return dao.checkUser(userId, password);
    }
}

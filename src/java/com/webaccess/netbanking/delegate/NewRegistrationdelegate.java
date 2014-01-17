/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;


import com.webaccess.netbanking.bean.NewRegistrationBean;
import com.webaccess.netbanking.dao.NewRegistrationDao;

/**
 *
 * @author Jay Prakash
 * Started on: Oct 07 2013
 */
public class NewRegistrationdelegate {

    NewRegistrationDao dao = null;

    public NewRegistrationdelegate() {
        dao = new NewRegistrationDao();

    }

    public String newUser(NewRegistrationBean bean) {

        return dao.newUser(bean);
    }
}

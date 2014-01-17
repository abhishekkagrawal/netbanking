/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;

import com.webaccess.netbanking.dao.StartPageDao;

/**
 *
 * @author jay Prakash
 * Started on: oct 03 2013
 * Ended on: oct 03 2013
 */
public class StartPageDelegate {

    StartPageDao dao = null;

    public StartPageDelegate() {
        dao = new StartPageDao();
    }

    public boolean checkUserIdDelegate(String userId) {

        return dao.checkUserId(userId);
    }
}

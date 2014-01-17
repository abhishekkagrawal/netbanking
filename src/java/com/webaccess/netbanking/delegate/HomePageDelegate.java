/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;

import com.webaccess.netbanking.dao.HomePageDao;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class HomePageDelegate {

    HomePageDao dao;

    public HomePageDelegate() {
        dao = new HomePageDao();
    }

    public ArrayList getTransactionDetail(int accountNumber) {

        return dao.getTransactionDetail(accountNumber);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;

import com.webaccess.netbanking.dao.ViewAndSavePageDao;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jay Prakash
 */
public class ViewAndSavePageDelegate {
    
    ViewAndSavePageDao dao;

    public ViewAndSavePageDelegate() {
        dao=new ViewAndSavePageDao();
    }
    
    public ArrayList oneMonthTransactionDetail(int accountNumber){
    
        return dao.oneMonthTransactionDetail(accountNumber);
    }
    
     public ArrayList twoMonthTransactionDetail(int accountNumber){
    
        return dao.twoMonthTransactionDetail(accountNumber);
    }
    public ArrayList getTransactionByNumber(int noOftransaction,int accountNumber){
    
        return dao.getTransactionByNumber(noOftransaction,accountNumber);
    }
    
     public ArrayList getTransactionByDate(Date startDate,Date endDate,int accountNumber){
    
        return dao.getTransactionByDate(startDate,endDate,accountNumber);
    }
    
    
    
}

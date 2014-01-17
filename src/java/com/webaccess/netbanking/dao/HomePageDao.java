/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.dao;

import com.webaccess.netbanking.bean.TransactionDetailBean;
import com.webaccess.netbanking.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Started On: Oct 15 2013
 * 
 * Ended on Oct 17 2013
 *
 * @author Jay prakash
 */
public class HomePageDao {

    DBUtil dBUtil = null;
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;

    public HomePageDao() {
        dBUtil=new DBUtil();
    }
    

    //getting last ten transaction detail
    public ArrayList getTransactionDetail(int accountNumber) {
        ArrayList transactionDetailList = new ArrayList();
        try {
            con = dBUtil.getconnection();
            String query = "SELECT * FROM transactiondetail WHERE accountNumber=? ORDER BY dateOfCreation DESC LIMIT 10";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TransactionDetailBean bean = new TransactionDetailBean();
                bean.setTransactionId(rs.getInt("transactionId"));
                bean.setAccountNumber(rs.getInt("accountNumber"));
                bean.setCardNumber(rs.getLong("cardNumber"));
                bean.setChequeNumber(rs.getLong("chequeNumber"));
                bean.setDateOfCreation(rs.getString("dateOfcreation"));
                bean.setDateOfModification(rs.getString("dateOfModification"));
                bean.setTransactionAmount(rs.getDouble("transactionAmount"));
                bean.setTransactionDate(rs.getDate("transactionDate"));
                bean.setTransactionId(rs.getInt("transactionId"));
                bean.setTransactionType(rs.getString("transactionType"));
                bean.setParticulars(rs.getString("particulars"));
                transactionDetailList.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return transactionDetailList;

    }
}

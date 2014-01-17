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
import java.util.Date;

/**
 *
 * @author Jay Prakash
 */
public class ViewAndSavePageDao {

    DBUtil dBUtil = null;
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;

    public ViewAndSavePageDao() {
        dBUtil = new DBUtil();
    }

    //getting one month transaction detail
    public ArrayList oneMonthTransactionDetail(int accountNumber) {
        ArrayList oneMonthTransactionDeatailList = new ArrayList();
        try {
            con = dBUtil.getconnection();
            String query = "SELECT * FROM transactiondetail WHERE accountNumber=? and dateOfCreation BETWEEN DATE_ADD( NOW( ) , INTERVAL -1 MONTH ) AND NOW() ORDER BY dateOfCreation desc";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TransactionDetailBean transactionDetailBean = new TransactionDetailBean();
                transactionDetailBean.setTransactionId(rs.getInt("transactionId"));
                transactionDetailBean.setAccountNumber(rs.getInt("accountNumber"));
                transactionDetailBean.setCardNumber(rs.getLong("cardNumber"));
                transactionDetailBean.setChequeNumber(rs.getLong("chequeNumber"));
                transactionDetailBean.setDateOfCreation(rs.getString("dateOfcreation"));
                transactionDetailBean.setDateOfModification(rs.getString("dateOfModification"));
                transactionDetailBean.setTransactionAmount(rs.getDouble("transactionAmount"));
                transactionDetailBean.setTransactionDate(rs.getDate("transactionDate"));
                transactionDetailBean.setTransactionId(rs.getInt("transactionId"));
                transactionDetailBean.setTransactionType(rs.getString("transactionType"));
                transactionDetailBean.setParticulars(rs.getString("particulars"));
                oneMonthTransactionDeatailList.add(transactionDetailBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return oneMonthTransactionDeatailList;
    }

    //getting two month transaction detail
    public ArrayList twoMonthTransactionDetail(int accountNumber) {
        ArrayList twoMonthTransactionDeatailList = new ArrayList();
        try {
            con = dBUtil.getconnection();
            String query = "SELECT * FROM transactiondetail WHERE  accountNumber=? and dateOfCreation BETWEEN DATE_ADD( NOW( ) , INTERVAL -2 MONTH ) AND NOW() ORDER BY dateOfCreation DESC";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TransactionDetailBean transactionDetailBean = new TransactionDetailBean();
                transactionDetailBean.setTransactionId(rs.getInt("transactionId"));
                transactionDetailBean.setAccountNumber(rs.getInt("accountNumber"));
                transactionDetailBean.setCardNumber(rs.getLong("cardNumber"));
                transactionDetailBean.setChequeNumber(rs.getLong("chequeNumber"));
                transactionDetailBean.setDateOfCreation(rs.getString("dateOfcreation"));
                transactionDetailBean.setDateOfModification(rs.getString("dateOfModification"));
                transactionDetailBean.setTransactionAmount(rs.getDouble("transactionAmount"));
                transactionDetailBean.setTransactionDate(rs.getDate("transactionDate"));
                transactionDetailBean.setTransactionId(rs.getInt("transactionId"));
                transactionDetailBean.setTransactionType(rs.getString("transactionType"));
                transactionDetailBean.setParticulars(rs.getString("particulars"));
                twoMonthTransactionDeatailList.add(transactionDetailBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return twoMonthTransactionDeatailList;
    }

    //getting number of transaction given by user
    public ArrayList getTransactionByNumber(int noOftransaction, int accountNumber) {
        ArrayList transactionDetailList = new ArrayList();
        try {
            con = dBUtil.getconnection();
            String query = "SELECT * FROM transactiondetail WHERE  accountNumber=? ORDER BY dateOfCreation DESC LIMIT ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            stmt.setInt(2, noOftransaction);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TransactionDetailBean transactionDetailBean = new TransactionDetailBean();
                transactionDetailBean.setTransactionId(rs.getInt("transactionId"));
                transactionDetailBean.setAccountNumber(rs.getInt("accountNumber"));
                transactionDetailBean.setCardNumber(rs.getLong("cardNumber"));
                transactionDetailBean.setChequeNumber(rs.getLong("chequeNumber"));
                transactionDetailBean.setDateOfCreation(rs.getString("dateOfcreation"));
                transactionDetailBean.setDateOfModification(rs.getString("dateOfModification"));
                transactionDetailBean.setTransactionAmount(rs.getDouble("transactionAmount"));
                transactionDetailBean.setTransactionDate(rs.getDate("transactionDate"));
                transactionDetailBean.setTransactionId(rs.getInt("transactionId"));
                transactionDetailBean.setTransactionType(rs.getString("transactionType"));
                transactionDetailBean.setParticulars(rs.getString("particulars"));
                transactionDetailList.add(transactionDetailBean);
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

    //getting transaction between 2 date
    public ArrayList getTransactionByDate(Date startDate, Date endDate, int accountNumber) {
        ArrayList transactionDetailList = new ArrayList();
        try {
            con = dBUtil.getconnection();
            java.sql.Date startDate1 = new java.sql.Date(startDate.getTime());
            java.sql.Date endDate1 = new java.sql.Date(endDate.getTime());
            String query = "select * from transactiondetail where  accountNumber=? and dateOfCreation between '" + startDate1 + "' and '" + endDate1 + "' ORDER BY dateOfCreation DESC";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TransactionDetailBean transactionDetailBean = new TransactionDetailBean();
                transactionDetailBean.setTransactionId(rs.getInt("transactionId"));
                transactionDetailBean.setAccountNumber(rs.getInt("accountNumber"));
                transactionDetailBean.setCardNumber(rs.getLong("cardNumber"));
                transactionDetailBean.setChequeNumber(rs.getLong("chequeNumber"));
                transactionDetailBean.setDateOfCreation(rs.getString("dateOfcreation"));
                transactionDetailBean.setDateOfModification(rs.getString("dateOfModification"));
                transactionDetailBean.setTransactionAmount(rs.getDouble("transactionAmount"));
                transactionDetailBean.setTransactionDate(rs.getDate("transactionDate"));
                transactionDetailBean.setTransactionId(rs.getInt("transactionId"));
                transactionDetailBean.setTransactionType(rs.getString("transactionType"));
                transactionDetailBean.setParticulars(rs.getString("particulars"));
                transactionDetailList.add(transactionDetailBean);
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

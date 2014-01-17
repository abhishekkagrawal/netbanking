/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.dao;

import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.util.DBUtil;
import com.webaccess.netbanking.util.PasswordEncrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Started on: Oct 28 2013
 * 
 * Ended on: Oct 28 2013
 *
 * @author Jay Prakash 
 *
 */
public class ChangePasswordDao {

    DBUtil dBUtil;
    PreparedStatement stmt;
    ResultSet rs;
    Connection con;

    public ChangePasswordDao() {
        dBUtil = new DBUtil();
    }

    
    //change login password 
    public boolean changeLoginPassword(LoginInfoBean loginInfoBean, String oldLoginPassword1) {
        try {
            PasswordEncrypt encrypt = new PasswordEncrypt();
            con = dBUtil.getconnection();
            String query = "Select * from login where userId=? and loginPassword=?";
            String password = encrypt.encryptPassword(loginInfoBean.getLoginPassword());
            String oldLoginPassword = encrypt.encryptPassword(oldLoginPassword1);
            stmt = con.prepareStatement(query);
            stmt.setString(1, loginInfoBean.getUserId());
            stmt.setString(2, oldLoginPassword);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (password.equals(rs.getString("loginPassword")) || password.equals(rs.getString("oldLoginPassword"))) {
                    return false;
                } else {
                    query = "update login set loginPassword=?,oldLoginPassword=?,dateOfModification=now() where userId=?";
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, password);
                    stmt.setString(2, oldLoginPassword);
                    stmt.setString(3, loginInfoBean.getUserId());
                    stmt.executeUpdate();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return false;

    }

    //changing transaction password
    public boolean changeTransactionPassword(LoginInfoBean loginInfoBean, String oldTransactionPassword1) {
        try {
            PasswordEncrypt encrypt = new PasswordEncrypt();
            con = dBUtil.getconnection();
            String oldTransactionPassword = encrypt.encryptPassword(oldTransactionPassword1);
            String query = "Select * from login where userId=? and transactionPassword=? ";
            String password = encrypt.encryptPassword(loginInfoBean.getTransactionPassword());
            stmt = con.prepareStatement(query);
            stmt.setString(1, loginInfoBean.getUserId());
            stmt.setString(2, oldTransactionPassword);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (password.equals(rs.getString("transactionPassword")) || password.equals(rs.getString("oldTransactionPassword"))) {
                    return false;
                } else {
                    query = "update login set transactionPassword=?,oldTransactionPassword=?,dateOfModification=now() where userId=?";
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, password);
                    stmt.setString(2, oldTransactionPassword);
                    stmt.setString(3, loginInfoBean.getUserId());
                    stmt.executeUpdate();
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return false;
    }
}

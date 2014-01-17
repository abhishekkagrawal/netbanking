/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.dao;

import com.webaccess.netbanking.util.DBUtil;
import com.webaccess.netbanking.util.MailToUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Administrator
 */
public class ForgotUserIdDao {

    DBUtil db = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement stmt = null,stmt1;

    public ForgotUserIdDao() {
        db = new DBUtil();
    }
    
    //checking userId is present in database or not and returning true and false
    public boolean checkUserId(String userId) {
        StringBuffer query = new StringBuffer();
        con = db.getconnection();
        try {
            query.append("select UserId from login where userId=? and active=true");
            stmt = con.prepareStatement(query.toString());
            stmt.setString(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.freeResultSet(rs);
            db.freeStatement(stmt);
            db.freeConnection(con);

        }
        return false;
    }

    //getting OPT Number and sending to user
    public int getOPTNumber(String useId) {
        int otpNumber = 0;
        String emailId = "";
        StringBuffer query = new StringBuffer();
        MailToUser mail = new MailToUser();
        try {
            con = db.getconnection();
            query.append("SELECT u.emailId FROM clientdetail u,login l WHERE u.accountNumber=l.accountNumber AND l.userId=?");
            stmt = con.prepareStatement(query.toString());
            stmt.setString(1, useId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                emailId = rs.getString("emailId");
                otpNumber = mail.sendOPTNumber(emailId);
                String query1="update login set optNumber=? where userId=?";
                stmt1=con.prepareStatement(query1);
                stmt1.setInt(1, otpNumber);
                stmt1.setString(2, useId);
                stmt1.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.freeConnection(con);
            db.freeResultSet(rs);
            db.freeStatement(stmt);
            query = null;
        }

        return otpNumber;
    }
}

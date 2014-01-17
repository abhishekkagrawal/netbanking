/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.dao;

import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.PasswordResetBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.util.DBUtil;
import com.webaccess.netbanking.util.MailToUser;
import com.webaccess.netbanking.util.PasswordEncrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Started on: oct 17 2013
 *
 * Ended on: Oct 17 2013
 *
 * @author Jay Prakash
 */
public class PasswordResetDao {

    Connection con;
    ResultSet rs;
    PreparedStatement stmt, stmt1;
    DBUtil dBUtil = null;

    public PasswordResetDao() {
        dBUtil = new DBUtil();
    }

    //checking password is correct or not and returning  string
    public String checkPasswordReset(PasswordResetBean passwordResetBean) {
        try {
            con = dBUtil.getconnection();
            StringBuffer query = new StringBuffer();
            query.append("SELECT l.userId,l.optNumber,c.panCardNumber,c.number,c.emailId,c.name,l.loginType FROM clientdetail c,login l WHERE l.accountNumber=c.accountNumber AND l.userId=?");
            stmt = con.prepareStatement(query.toString());
            stmt.setString(1, passwordResetBean.getUserId());
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    if (passwordResetBean.getMobileNumber() == rs.getLong("number") && passwordResetBean.getOptNumber() == rs.getInt("optNumber") && passwordResetBean.getPanCardNumber().equals(rs.getString("panCardNumber"))) {
                        UserInfoBean userInfoBean = new UserInfoBean();
                        LoginInfoBean loginInfoBean = new LoginInfoBean();
                        userInfoBean.setName(rs.getString("name"));
                        userInfoBean.setEmailId(rs.getString("emailId"));
                        userInfoBean.setPanCardNumber(rs.getString("panCardNumber"));
                        loginInfoBean.setUserId(passwordResetBean.getUserId());
                        loginInfoBean.setLoginType(rs.getString("loginType"));
                        String loginPassword = "";
                        String encryptedLoginPassword = "";
                        String transactionPassword = "";
                        String encryptedTransactionPassword = "";
                        PasswordEncrypt getPass = new PasswordEncrypt();
                        loginPassword = getPass.generatePassword();
                        encryptedLoginPassword = getPass.encryptPassword(loginPassword);
                        if (loginInfoBean.getLoginType().equals("View And transaction")) {
                            transactionPassword = getPass.generatePassword();
                            encryptedTransactionPassword = getPass.encryptPassword(transactionPassword);
                        }

                        loginInfoBean.setLoginPassword(loginPassword);
                        loginInfoBean.setTransactionPassword(transactionPassword);
                        String query1 = "update login set loginPassword=?, transactionPassword=? where userId=?";
                        stmt1 = con.prepareStatement(query1);
                        stmt1.setString(1, encryptedLoginPassword);
                        stmt1.setString(2, encryptedTransactionPassword);
                        stmt1.setString(3, loginInfoBean.getUserId());
                        stmt1.executeUpdate();
                        MailToUser mail = new MailToUser();
                        mail.createEmailMessage(loginInfoBean, userInfoBean);
                        return "success";
                    } else {
                        return "wrongInfo";
                    }
                }
            } else {
                return "wrongInfo";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return "success";
    }
}

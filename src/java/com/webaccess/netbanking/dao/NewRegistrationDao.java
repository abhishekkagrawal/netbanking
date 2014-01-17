/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.dao;

import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.NewRegistrationBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.util.DBUtil;
import com.webaccess.netbanking.util.MailToUser;
import com.webaccess.netbanking.util.PasswordEncrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Started on: Oct 07 2013
 *
 * @author Jay Prakash
 */
public class NewRegistrationDao {

    DBUtil dBUtil = null;
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    StringBuffer query = new StringBuffer();

    public NewRegistrationDao() {
        dBUtil = new DBUtil();
    }

    //registering new user and sent mail on email id
    public String newUser(NewRegistrationBean bean) {
        try {

            PreparedStatement stmt1, stmt2;
            ResultSet rs1;
            String query1;
            conn = dBUtil.getconnection();
            java.sql.Date date1 = new java.sql.Date(bean.getDate().getTime());
            query.append("select * from clientdetail where accountNumber=? and number=? and panCardNumber=? and dateOfBirth=?");
            stmt = conn.prepareStatement(query.toString());
            stmt.setInt(1, bean.getAccountNumber());
            stmt.setLong(2, bean.getMobileNumber());
            stmt.setString(3, bean.getPanCardNumber());
            stmt.setDate(4, date1);
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UserInfoBean userInfoBean = new UserInfoBean();
                    userInfoBean.setAccountNumber(rs.getInt("accountNumber"));
                    userInfoBean.setAccountId(rs.getInt("accountId"));
                    userInfoBean.setAddress(rs.getString("address"));
                    userInfoBean.setAvailBalance(rs.getDouble("availBalance"));
                    userInfoBean.setDateOfBirth(rs.getDate("dateOfbirth"));
                    userInfoBean.setEmailId(rs.getString("emailId"));
                    userInfoBean.setGender(rs.getString("gender"));
                    userInfoBean.setName(rs.getString("name"));
                    userInfoBean.setNumber(rs.getLong("number"));
                    userInfoBean.setPanCardNumber(rs.getString("panCardNumber"));
                    query1 = "select * from login where accountNumber=?";
                    stmt1 = conn.prepareStatement(query1);
                    stmt1.setInt(1, bean.getAccountNumber());
                    rs1 = stmt1.executeQuery();
                    if (rs1.next()) {
                        return "alreadyPresent";
                    } else {
                        String UserId = "";
                        String loginPassword = "";
                        String encryptedLoginPassword = "";
                        String transactionPassword = "";
                        String encryptedTransactionPassword = "";
                        PasswordEncrypt getPass = new PasswordEncrypt();
                        UserId = getPass.getUserId();
                        loginPassword = getPass.generatePassword();
                        encryptedLoginPassword = getPass.encryptPassword(loginPassword);
                        if (bean.getLoginType().equals("View And transaction")) {
                            transactionPassword = getPass.generatePassword();
                            encryptedTransactionPassword = getPass.encryptPassword(transactionPassword);
                        }
                        String query2 = "insert into login(accountnumber,userId,loginPassword,active,loginType,transactionPassword) values(?,?,?,?,?,?)";
                        stmt2 = conn.prepareStatement(query2);
                        stmt2.setInt(1, bean.getAccountNumber());
                        stmt2.setString(2, UserId);
                        stmt2.setString(3, encryptedLoginPassword);
                        stmt2.setBoolean(4, true);
                        stmt2.setString(5, bean.getLoginType());
                        stmt2.setString(6, encryptedTransactionPassword);
                        stmt2.executeUpdate();
                        LoginInfoBean loginInfoBean = new LoginInfoBean();
                        loginInfoBean.setAccountNumber(bean.getAccountNumber());
                        loginInfoBean.setUserId(UserId);
                        loginInfoBean.setLoginPassword(loginPassword);
                        loginInfoBean.setLoginType(bean.getLoginType());
                        loginInfoBean.setTransactionPassword(transactionPassword);
                        MailToUser mailToUser = new MailToUser();
                        mailToUser.createEmailMessage(loginInfoBean, userInfoBean);
                        return "success";
                    }
                }
            } else {
                return "wrongInfo";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(conn);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return "";
    }

    //checking user id is present or not
    public ArrayList getUserId() {

        ArrayList userId = new ArrayList();
        try {
            conn = dBUtil.getconnection();
            query.append("select userId from login");
            stmt = conn.prepareStatement(query.toString());
            rs = stmt.executeQuery();
            while (rs.next()) {
                userId.add(rs.getString("userId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(conn);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return userId;
    }
}

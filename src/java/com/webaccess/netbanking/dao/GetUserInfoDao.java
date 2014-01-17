/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.dao;

import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.UserInfoBean;
import com.webaccess.netbanking.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 * Started on: Oct 16 2013
 *
 * Ended On: Oct 18 2013
 *
 * @author Jay Prakash
 */
public class GetUserInfoDao {

    Connection con;
    ResultSet rs, rs1;
    PreparedStatement stmt, stmt1;
    DBUtil dBUtil;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

    public GetUserInfoDao() {
        dBUtil = new DBUtil();
    }

    //getting user information from database returning user info bean
    public UserInfoBean getUserInformation(String userId) {

        StringBuffer query = new StringBuffer();
        UserInfoBean userInfoBean = new UserInfoBean();
        try {
            con = dBUtil.getconnection();
            query.append("SELECT c.* FROM clientdetail c,login l WHERE c.accountNumber=l.accountNumber AND l.userId=?");
            stmt = con.prepareStatement(query.toString());
            stmt.setString(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                userInfoBean.setAccountNumber(rs.getInt("accountNumber"));
                userInfoBean.setAccountId(rs.getInt("accountId"));
                userInfoBean.setName(rs.getString("name"));
                userInfoBean.setAddress(rs.getString("address"));
                userInfoBean.setGender(rs.getString("gender"));
                userInfoBean.setNumber(rs.getLong("number"));
                userInfoBean.setDateOfBirth(rs.getDate("dateOfBirth"));
                userInfoBean.setEmailId(rs.getString("emailId"));
                userInfoBean.setAvailBalance(rs.getDouble("availBalance"));
                userInfoBean.setPanCardNumber(rs.getString("panCardNumber"));
                userInfoBean.setDateOfCreation(rs.getDate("dateOfCreation"));
                userInfoBean.setDateOfModification(rs.getDate("dateOfModification"));
                userInfoBean.setNoOfCardIssue(rs.getInt("noOfCardIssue"));
                String query1 = "select accountType from account where accountId=?";
                stmt1 = con.prepareStatement(query1);
                stmt1.setInt(1, userInfoBean.getAccountId());
                rs1 = stmt1.executeQuery();
                while (rs1.next()) {
                    userInfoBean.setAccountType(rs1.getString("accountType"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeResultSet(rs1);
            dBUtil.freeStatement(stmt1);
            dBUtil.freeStatement(stmt);
        }
        return userInfoBean;
    }

    //getting login information from database and returning login info bean
    public LoginInfoBean getLoginInformation(String userId) {
        con = dBUtil.getconnection();
        StringBuffer query = new StringBuffer();
        LoginInfoBean loginInfoBean = new LoginInfoBean();

        try {
            query.append("SELECT * FROM login WHERE userId=?");
            stmt = con.prepareStatement(query.toString());
            stmt.setString(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                loginInfoBean.setAccountNumber(rs.getInt("accountNumber"));
                loginInfoBean.setActive(rs.getBoolean("active"));
                loginInfoBean.setLastLogin(rs.getString("lastlogin"));
                loginInfoBean.setLoginType(rs.getString("loginType"));
                loginInfoBean.setLoginId(rs.getInt("loginId"));
                loginInfoBean.setUserId(userId);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeResultSet(rs1);
            dBUtil.freeStatement(stmt1);
            dBUtil.freeStatement(stmt);
        }

        return loginInfoBean;

    }
}

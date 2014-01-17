/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.dao;

import com.webaccess.netbanking.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Administrator
 */
public class LogoutDao {

    DBUtil dBUtil;
    PreparedStatement stmt;
    Connection con;

    public LogoutDao() {
        dBUtil = new DBUtil();
    }

    // inserting logout time
    public void insertLogoutTime(String userId) {

        String query;
        try {
            con = dBUtil.getconnection();
            query = "UPDATE login SET lastLogin=NOW() where userId=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, userId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeStatement(stmt);

        }

    }
}

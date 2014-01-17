/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.dao;

import com.webaccess.netbanking.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Jay Prakash Started On: Oct 03 2013 Ended On: Oct 03 2013 checking
 * userId is present in database or not and returning true and false
 */
public class StartPageDao {

    DBUtil db = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement stmt = null;
    StringBuffer query = new StringBuffer();

    public StartPageDao() {
        db = new DBUtil();
    }

    //checking userId is present in database or not and returning true and false
    public boolean checkUserId(String userId) {

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
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Started on: Oct,14,2013 10:10 AM 
 * Ended on: Oct,14,2013  10:10 AM
 *
 * @author Jay Prakash 
 */
public class DBUtil {

    private static String userName = "root";
    private static String password = "root";
    private static String driverClassName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/netbanking";
    Connection con;

    static {
        try {
            Class c = Class.forName(driverClassName);
            Driver driver = (Driver) c.newInstance();
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //opening connection
    public Connection getconnection() {

        try {
            con = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    //closing connection
    public void freeConnection(Connection con) {

        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //closing result set
    public void freeResultSet(ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //closing statement
    public void freeStatement(PreparedStatement stmt) {

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

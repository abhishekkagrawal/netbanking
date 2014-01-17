/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.dao;


import com.webaccess.netbanking.util.DBUtil;
import com.webaccess.netbanking.util.PasswordEncrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Jay Prakash
 * Started On: Oct 03 2013
 * Ended On: Oct 03 2013
 * checking userId and password is present in database or not and returning true and false
 * 
 */
public class LoginPageDao {
    
    DBUtil db=null;
    Connection con=null;
    ResultSet rs=null;
    PreparedStatement stmt;
    StringBuffer query=new StringBuffer();
    PasswordEncrypt passwordEncrypt=null;
    
    public LoginPageDao(){
        db=new DBUtil();
        passwordEncrypt=new PasswordEncrypt();
    }
    
    //checking user id is present or not
    public boolean checkUser(String userId,String password){
        
        try{
            con=db.getconnection();
            String pass=passwordEncrypt.encryptPassword(password);
            query.append("Select userId,loginPassword from login where userId=?");
            stmt=con.prepareStatement(query.toString());
            stmt.setString(1, userId);
            rs=stmt.executeQuery();
            while(rs.next()){
                if(userId.equals(rs.getString("userId")) && pass.equals(rs.getString("loginPassword"))){
                    return true;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.freeResultSet(rs);
            db.freeStatement(stmt);
            db.freeConnection(con);            
        }
        
        return false;
    }
    
    
}

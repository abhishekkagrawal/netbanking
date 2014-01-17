/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.dao;

import com.webaccess.netbanking.bean.CardDetailBean;
import com.webaccess.netbanking.bean.CardTypeBean;
import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.util.DBUtil;
import com.webaccess.netbanking.util.MailToUser;
import com.webaccess.netbanking.util.PasswordEncrypt;
import com.webaccess.netbanking.util.SendSMS;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Started On: Nov 11 2013
 *
 * Ended On: Nov 14 2013
 *
 * this class is created for all type of getting and inserting information of
 * card
 *
 * @author Jay Prakash
 */
public class CreditCardDao {

    DBUtil dBUtil;
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;

    public CreditCardDao() {
        dBUtil = new DBUtil();
    }

    //getting card type
    public ArrayList getCardType() {
        ArrayList cardTypeList = new ArrayList();
        try {
            con = dBUtil.getconnection();
            String query = "SELECT * FROM creditcardtype";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                CardTypeBean cardTypeBean = new CardTypeBean();
                cardTypeBean.setCreditCardId(rs.getInt("creditCardId"));
                cardTypeBean.setCardTypeName(rs.getString("cardTypeName"));
                cardTypeBean.setMaxLimit(rs.getDouble("maxLimit"));
                cardTypeList.add(cardTypeBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return cardTypeList;
    }

    // apply new card and inserting information into database
    public String applyNewCreditCard(int accountNumber, int cardTpe, String nameOnCard) {
        try {
            con = dBUtil.getconnection();
            int noOfCardIssue = 0;
            String query1 = "SELECT noOfCardIssue FROM clientdetail WHERE accountNumber=?";
            stmt = con.prepareStatement(query1);
            stmt.setInt(1, accountNumber);
            rs = stmt.executeQuery();
            while (rs.next()) {
                noOfCardIssue = rs.getInt("noOfCardIssue");
            }
            if (noOfCardIssue > 0) {
                PasswordEncrypt passwordEncrypt = new PasswordEncrypt();
                long cardNumber = passwordEncrypt.generateCreditCardNumber();
                String query = "Insert into creditcarddetail (creditCardNumber,accountNumber,nameOnCard,creditCardId,dateOfCreation,dateOfModification) values (?,?,?,?,now(),now())";
                stmt = con.prepareStatement(query);
                stmt.setLong(1, cardNumber);
                stmt.setInt(2, accountNumber);
                stmt.setString(3, nameOnCard);
                stmt.setInt(4, cardTpe);
                stmt.executeUpdate();
                query = "update clientdetail set noOfCardIssue=noOfCardIssue-1 where accountNumber=?";
                stmt = con.prepareStatement(query);
                stmt.setInt(1, accountNumber);
                stmt.executeUpdate();
                return "success";
            } else {
                return "maxCardIssued";
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

    //getting card detail from database
    public ArrayList getCreditCardetailList(int accountNumber) {
        ArrayList creditCardDetail = new ArrayList();
        try {
            con = dBUtil.getconnection();
            double unBilledAmount = 0;
            String query = "SELECT ct.cardTypeName,ct.maxLimit,c.* FROM creditcarddetail c, creditcardtype ct WHERE ct.creditCardid=c.creditCardId AND c.accountnumber=?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            rs = stmt.executeQuery();
            while (rs.next()) {
                CardDetailBean cardDetailBean = new CardDetailBean();
                cardDetailBean.setAccountNumber(accountNumber);
                cardDetailBean.setCardId(rs.getInt("cardId"));
                cardDetailBean.setCardTypeName(rs.getString("cardTypeName"));
                cardDetailBean.setCreditCardId(rs.getInt("creditCardId"));
                cardDetailBean.setCreditCardNumber(rs.getLong("creditCardNumber"));
                cardDetailBean.setDateOfCreation(rs.getString("dateOfCreation"));
                cardDetailBean.setDateOfModification(rs.getString("dateOfModification"));
                cardDetailBean.setMaxLimit(rs.getDouble("maxLimit"));
                cardDetailBean.setNameOnCard(rs.getString("nameOnCard"));
                unBilledAmount = rs.getDouble("unbilledAmount");
                if (unBilledAmount > 0) {
                    cardDetailBean.setUnbilledAmount(unBilledAmount);
                } else {
                    Random generator = new Random();
                    double randomIndex = generator.nextInt(10000);
                    String query1 = "update  creditcarddetail set unbilledAmount=? where cardId=?";
                    stmt = con.prepareStatement(query1);
                    stmt.setDouble(1, randomIndex);
                    stmt.setInt(2, cardDetailBean.getCardId());
                    stmt.executeUpdate();
                    cardDetailBean.setUnbilledAmount(randomIndex);
                }
                creditCardDetail.add(cardDetailBean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return creditCardDetail;
    }

    //getting maxlimit for user to apply card
    public double getMaxLimit(int cardName) {
        double maxLimit = 0;
        try {
            con = dBUtil.getconnection();
            String query = "select maxLimit from creditcardtype where creditCardId=?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, cardName);
            rs = stmt.executeQuery();
            while (rs.next()) {
                maxLimit = rs.getDouble("maxLimit");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return maxLimit;
    }

    //pay card bill and inserting transaction detail into database
    public String payCreditCardBill(LoginInfoBean loginInfoBean, CardDetailBean cardDetailBean, String BillRemark) {

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            java.util.Date date1 = new java.util.Date();
            double minBalance;
            String name;
            long mobileNumber;
            double availBalance;
            String emailId;
            Date date = new Date(new java.util.Date().getTime());
            con = dBUtil.getconnection();
            PasswordEncrypt passwordEncrypt = new PasswordEncrypt();
            String pass = passwordEncrypt.encryptPassword(loginInfoBean.getTransactionPassword());
            String query = "SELECT a.minBalance,c.name,c.number,c.accountId,c.availBalance,c.emailId,l.userId,l.transactionPassword FROM account a,clientdetail c,login l WHERE c.accountId=a.accountId AND l.accountNumber=c.accountNumber AND l.userId=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, loginInfoBean.getUserId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (pass.equals(rs.getString("transactionPassword"))) {
                    minBalance = rs.getDouble("minBalance");
                    name = rs.getString("name");
                    mobileNumber = rs.getLong("number");
                    availBalance = rs.getDouble("availBalance");
                    emailId = rs.getString("emailId");
                    double remainingBalance = (availBalance - cardDetailBean.getUnbilledAmount());
                    if (remainingBalance > minBalance) {
                        String query1 = "insert into transactiondetail (accountNumber,transactionType,transactionDate,transactionAmount,dateOfCreation,dateOfModification,particulars) values(?,?,?,?,now(),now(),?)";
                        stmt = con.prepareStatement(query1);
                        stmt.setInt(1, cardDetailBean.getAccountNumber());
                        stmt.setString(2, "DR");
                        stmt.setDate(3, date);
                        stmt.setDouble(4, cardDetailBean.getUnbilledAmount());
                        stmt.setString(5, "cardbillpay/" + BillRemark);
                        int rowUpdate = stmt.executeUpdate();
                        if (rowUpdate > 0) {
                            String query2 = "update clientdetail set availBalance=? where accountNumber=?";
                            stmt = con.prepareStatement(query2);
                            stmt.setDouble(1, remainingBalance);
                            stmt.setInt(2, cardDetailBean.getAccountNumber());
                            stmt.executeUpdate();
                            String query3 = "update creditcarddetail set unbilledAmount=?,lastPaid=? where cardId=?";
                            stmt = con.prepareStatement(query3);
                            stmt.setDouble(1, 0);
                            stmt.setDate(2, date);
                            stmt.setInt(3, cardDetailBean.getCardId());
                            stmt.executeUpdate();
                            String msg = "Hi " + name + " Account Number " + cardDetailBean.getAccountNumber() + " Debited with INR " + cardDetailBean.getUnbilledAmount() + " on " + date1 + " thru Internet Banking. Aval Bal INR " + remainingBalance + " CR. Thank you banking with us.";
                            SendSMS sendSMS = new SendSMS();
                            sendSMS.msgsend(msg, mobileNumber);
                            MailToUser mailToUser = new MailToUser();
                            mailToUser.sendTransactionDetail(emailId, msg);
                            return "success";
                        } else {
                            return "technicalError";
                        }
                    } else {
                        return "balanceError";
                    }
                } else {
                    return "transactionPasswordError";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }

        return "";
    }
}

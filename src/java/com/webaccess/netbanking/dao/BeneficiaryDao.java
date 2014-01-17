/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.webaccess.netbanking.bean.BankBean;
import com.webaccess.netbanking.bean.BeneficiaryDetailBean;
import com.webaccess.netbanking.bean.TransferBean;
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

/**
 * Started on: Oct 29 2013
 *
 * Ended on: Oct 29 2013
 *
 * @author Jay Prakash
 *
 */
public class BeneficiaryDao {

    DBUtil dBUtil;
    PreparedStatement stmt;
    ResultSet rs;
    Connection con;

    public BeneficiaryDao() {
        dBUtil = new DBUtil();
    }

    //adding beneficiary detail into database
    public String addBeneficiary(BeneficiaryDetailBean beneficiaryDetail) {
        try {
            con = dBUtil.getconnection();
            PasswordEncrypt passwordEncrypt = new PasswordEncrypt();
            SendSMS sendSMS = new SendSMS();
            MailToUser mailToUser = new MailToUser();
            beneficiaryDetail.setURNNumber(passwordEncrypt.generateOPTNumber());
            String query = "Insert into beneficiarydetail (accountNumber,beneficiaryName,beneficiaryAccountNumber,bankName,IFSCCode,accountType,transactionType,URNNumber,status,dateOfCreation,dateOfModification) values(?,?,?,?,?,?,?,?,?,now(),now())";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, beneficiaryDetail.getAccountNumber());
            stmt.setString(2, beneficiaryDetail.getBeneficiaryName());
            stmt.setInt(3, beneficiaryDetail.getBeneficiaryAccountNumber());
            stmt.setString(4, beneficiaryDetail.getBeneficiaryBankName());
            stmt.setString(5, beneficiaryDetail.getIFSCCode());
            stmt.setString(6, beneficiaryDetail.getBeneficiaryAccountType());
            stmt.setString(7, beneficiaryDetail.getTransactionType());
            stmt.setInt(8, beneficiaryDetail.getURNNumber());
            stmt.setString(9, "Deactive");
            int rowUpdate = stmt.executeUpdate();
            if (rowUpdate > 0) {
                query = "select * from clientdetail where accountNumber=?";
                stmt = con.prepareStatement(query);
                stmt.setInt(1, beneficiaryDetail.getAccountNumber());
                rs = stmt.executeQuery();
                long mobileNumber = 0;
                String emailId = "";
                while (rs.next()) {
                    mobileNumber = rs.getLong("number");
                    emailId = rs.getString("emailId");
                }
                String messageBody = beneficiaryDetail.getBeneficiaryName() + "is added as NEFT peyee from your internet account. confirm with URN " + beneficiaryDetail.getURNNumber() + " . If you did not add payee, report to customer care as FRAUD.";
                sendSMS.msgsend(messageBody, mobileNumber);
                mailToUser.sendURNNumber(emailId, messageBody);
                return "success";
            }else{
                return "alreadyPresent";
            }
        } catch (MySQLIntegrityConstraintViolationException e) {
            return "alreadyPresent";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return "success";
    }

    //getting deactivated  benificiary from database of particular account
    public ArrayList getDeactiveBeneficiary(int accountNumber, String transactionType) {


        ArrayList deactiveBeneficiaryList = new ArrayList();
        try {
            con = dBUtil.getconnection();
            String query = "SELECT * FROM beneficiarydetail WHERE accountNumber=? AND STATUS=? AND transactionType=? ";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            stmt.setString(2, "Deactive");
            stmt.setString(3, transactionType);
            rs = stmt.executeQuery();
            while (rs.next()) {
                BeneficiaryDetailBean bean = new BeneficiaryDetailBean();
                bean.setAccountNumber(rs.getInt("accountNumber"));
                bean.setBeneficiaryAccountNumber(rs.getInt("beneficiaryAccountNumber"));
                bean.setBeneficiaryAccountType(rs.getString("accountType"));
                bean.setBeneficiaryBankName(rs.getString("bankName"));
                bean.setBeneficiaryName(rs.getString("beneficiaryName"));
                bean.setIFSCCode(rs.getString("IFSCCode"));
                bean.setStatus(rs.getString("status"));
                bean.setTransactionType(rs.getString("transactionType"));
                bean.setURNNumber(rs.getInt("URNNumber"));
                bean.setDateOfCreation(rs.getString("dateOfCreation"));
                bean.setBeneficiaryId(rs.getInt("beneficiaryId"));
                deactiveBeneficiaryList.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }

        return deactiveBeneficiaryList;
    }

    // changing the status to rejected
    public void deleteBeneficiary(int beneficiartId, int accountNumber) {

        try {
            con = dBUtil.getconnection();
            String query = "delete from beneficiarydetail  where accountNumber=? and beneficiaryId=?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            stmt.setInt(2, beneficiartId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
    }

    //getting all active beneficiary detail
    public ArrayList getActiveBeneficiary(int accountNumber, String transactionType) {

        ArrayList deactiveBeneficiaryList = new ArrayList();
        try {
            con = dBUtil.getconnection();
            String query = "SELECT * FROM beneficiarydetail WHERE accountNumber=? AND STATUS=? AND transactionType=? ";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            stmt.setString(2, "Active");
            stmt.setString(3, transactionType);
            rs = stmt.executeQuery();
            while (rs.next()) {
                BeneficiaryDetailBean bean = new BeneficiaryDetailBean();
                bean.setAccountNumber(rs.getInt("accountNumber"));
                bean.setBeneficiaryAccountNumber(rs.getInt("beneficiaryAccountNumber"));
                bean.setBeneficiaryAccountType(rs.getString("accountType"));
                bean.setBeneficiaryBankName(rs.getString("bankName"));
                bean.setBeneficiaryName(rs.getString("beneficiaryName"));
                bean.setIFSCCode(rs.getString("IFSCCode"));
                bean.setStatus(rs.getString("status"));
                bean.setTransactionType(rs.getString("transactionType"));
                bean.setURNNumber(rs.getInt("URNNumber"));
                bean.setDateOfCreation(rs.getString("dateOfCreation"));
                bean.setBeneficiaryId(rs.getInt("beneficiaryId"));
                deactiveBeneficiaryList.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }

        return deactiveBeneficiaryList;
    }

    //confirming deactive bebeficiary
    public boolean confirmBeneficiary(int accountNumber, int beneficiaryId, int URNNumber) {
        try {
            con = dBUtil.getconnection();
            boolean result = false;
            String query = "select * from beneficiarydetail where accountNumber=? and beneficiaryId=?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            stmt.setInt(2, beneficiaryId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (URNNumber == rs.getInt("URNNumber")) {
                    result = true;
                }
            }
            if (result) {
                query = "update beneficiarydetail set status=? where accountNumber=? and beneficiaryId=?";
                stmt = con.prepareStatement(query);
                stmt.setString(1, "Active");
                stmt.setInt(2, accountNumber);
                stmt.setInt(3, beneficiaryId);
                int rowUpdated = stmt.executeUpdate();
                if (rowUpdated > 0) {
                    return true;
                } else {
                    return false;
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

    //validating transaction and inserting to database
    public String validateTransaction(TransferBean transferBean) {
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
            String pass = passwordEncrypt.encryptPassword(transferBean.getTransactionPassword());
            String query = "SELECT a.minBalance,c.name,c.number,c.accountId,c.availBalance,c.emailId,l.userId,l.transactionPassword FROM account a,clientdetail c,login l WHERE c.accountId=a.accountId AND l.accountNumber=c.accountNumber AND l.userId=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, transferBean.getUserId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (pass.equals(rs.getString("transactionPassword"))) {
                    minBalance = rs.getDouble("minBalance");
                    name = rs.getString("name");
                    mobileNumber = rs.getLong("number");
                    availBalance = rs.getDouble("availBalance");
                    emailId = rs.getString("emailId");
                    double remainingBalance = (availBalance - transferBean.getTransactionAmount());
                    if (remainingBalance > minBalance) {
                        String query1 = "insert into transactiondetail (accountNumber,transactionType,transactionDate,transactionAmount,dateOfCreation,dateOfModification,particulars,transferAccountNumber) values(?,?,?,?,now(),now(),?,?)";
                        stmt = con.prepareStatement(query1);
                        stmt.setInt(1, transferBean.getAccountNumber());
                        stmt.setString(2, "DR");
                        stmt.setDate(3, date);
                        stmt.setDouble(4, transferBean.getTransactionAmount());
                        stmt.setString(5, transferBean.getParticulars());
                        stmt.setInt(6, transferBean.getTransferAccount());
                        int rowUpdate = stmt.executeUpdate();
                        if (rowUpdate > 0) {
                            String query2 = "update clientdetail set availBalance=? where accountNumber=?";
                            stmt = con.prepareStatement(query2);
                            stmt.setDouble(1, remainingBalance);
                            stmt.setInt(2, transferBean.getAccountNumber());
                            stmt.executeUpdate();
                            String msg = "Hi " + name + " Account Number " + transferBean.getAccountNumber() + " Debited with INR " + transferBean.getTransactionAmount() + " on " + date1 + " thru Internet Banking. Aval Bal INR " + remainingBalance + " CR. Thank you banking with us.";
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
            return "";
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return "";
    }

    //getting bank list
    public ArrayList getBankDetail() {

        ArrayList bankList = new ArrayList();
        try {
            con = dBUtil.getconnection();
            String query = "SELECT * FROM bankdetail";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                BankBean bankBean = new BankBean();
                bankBean.setBankId(rs.getInt("bankId"));
                bankBean.setBankName(rs.getString("bankName"));
                bankList.add(bankBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return bankList;

    }
}

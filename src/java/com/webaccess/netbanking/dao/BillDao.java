/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.webaccess.netbanking.bean.BSNLCelloneBillBean;
import com.webaccess.netbanking.bean.BillTypeBean;
import com.webaccess.netbanking.bean.ElectricityBillBean;
import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.SBILifeInsurenceBean;
import com.webaccess.netbanking.bean.StateBean;
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
import java.util.Calendar;
import java.util.Random;

/**
 * Started on: Nov 04 2013
 *
 * Ended on: Nov 07 2013
 *
 * @author Jay Prakash
 */
public class BillDao {

    DBUtil dBUtil = null;
    ResultSet rs;
    PreparedStatement stmt;
    Connection con;

    public BillDao() {
        dBUtil = new DBUtil();
    }

    //getting state list from database
    public ArrayList getState() {
        ArrayList stateList = new ArrayList();

        try {
            con = dBUtil.getconnection();
            String query = "SELECT * FROM  states ORDER BY stateName";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                StateBean stateBean = new StateBean();
                stateBean.setStateId(rs.getInt("stateId"));
                stateBean.setStateName(rs.getString("stateName"));
                stateList.add(stateBean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return stateList;
    }

    //getting biller list 
    public ArrayList getBiller() {

        ArrayList billerList = new ArrayList();
        try {
            con = dBUtil.getconnection();
            String query = "SELECT * FROM billtypes ORDER BY billerName ";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                BillTypeBean billTypeBean = new BillTypeBean();
                billTypeBean.setBillerId(rs.getInt("billerId"));
                billTypeBean.setBillerName(rs.getString("billerName"));
                billerList.add(billTypeBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return billerList;
    }

    //inserting electric bill information 
    public String insertBillInformation(ElectricityBillBean electricityBillBean) {
        try {
            con = dBUtil.getconnection();
            String query = "insert into electricitybill (nickName,billingUnit,processingCycle,consumerNumber,stateId,accountNumber,dateOfCreation,dateOfModification,status,lastPaidDate) values(?,?,?,?,?,?,now(),now(),?,DATE_ADD(CURDATE(),INTERVAL -1 MONTH))";
            stmt = con.prepareStatement(query);
            stmt.setString(1, electricityBillBean.getNickName());
            stmt.setInt(2, electricityBillBean.getBillingUnit());
            stmt.setInt(3, electricityBillBean.getProcessingCycle());
            stmt.setInt(4, electricityBillBean.getConsumerNumber());
            stmt.setInt(5, electricityBillBean.getStateId());
            stmt.setInt(6, electricityBillBean.getAccountNumber());
            stmt.setString(7, "Active");
            int result = stmt.executeUpdate();
            return "success";
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

    //inserting SBI Life Insurence information and generating amount for this
    public String insertSBILifeInsurenceInformation(SBILifeInsurenceBean sBILifeInsurenceBean) {
        try {
            con = dBUtil.getconnection();
            Random generator = new Random();
            int randomIndex = generator.nextInt(1000);
            java.util.Date date1 = sBILifeInsurenceBean.getDobOfpolicyHolder();
            java.sql.Date date = new Date(date1.getTime());
            String query = "insert into sbilifeinsurence (nickName,policyNumber,dobOfPolicyHolder,stateId,accountNumber,dateOfCreation,dateOfModification,status,amount,lastPaidDate) values(?,?,?,?,?,now(),now(),?,?,DATE_ADD(CURDATE(),INTERVAL -1 MONTH))";
            stmt = con.prepareStatement(query);
            stmt.setString(1, sBILifeInsurenceBean.getNickName());
            stmt.setInt(2, sBILifeInsurenceBean.getPolicynumber());
            stmt.setDate(3, date);
            stmt.setInt(4, sBILifeInsurenceBean.getStateId());
            stmt.setInt(5, sBILifeInsurenceBean.getAccountNumber());
            stmt.setString(6, "Active");
            stmt.setInt(7, randomIndex);
            int result = stmt.executeUpdate();
            return "success";
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

    //inserting BSNL Cellone bill detail and returning
    public String insertbSNLCelloneBillInformation(BSNLCelloneBillBean bSNLCelloneBillBean) {
        try {
            con = dBUtil.getconnection();
            String query = "insert into bsnlcellonebill (nickName,mobileNumber,stateId,accountNumber,dateOfCreation,dateOfModification,status,lastPaidDate) values(?,?,?,?,now(),now(),?,DATE_ADD(CURDATE(),INTERVAL -1 MONTH))";
            stmt = con.prepareStatement(query);
            stmt.setString(1, bSNLCelloneBillBean.getNickName());
            stmt.setLong(2, bSNLCelloneBillBean.getMobileNumber());
            stmt.setInt(3, bSNLCelloneBillBean.getStateId());
            stmt.setInt(4, bSNLCelloneBillBean.getAccountNumber());
            stmt.setString(5, "Active");
            int result = stmt.executeUpdate();
            return "success";
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

    //getting electric bill list for perticular account number
    public ArrayList getElectricityBillList(int accountnumber) {
        ArrayList electricityBillList = new ArrayList();
        try {
            con = dBUtil.getconnection();
            String query = "SELECT s.stateName,e.* FROM electricitybill e,states s WHERE accountNumber=? AND STATUS='Active' AND e.stateId=s.stateId";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountnumber);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ElectricityBillBean electricityBillBean = new ElectricityBillBean();
                electricityBillBean.setAccountNumber(accountnumber);
                electricityBillBean.setBillingUnit(rs.getInt("billingUnit"));
                electricityBillBean.setConsumerNumber(rs.getInt("consumerNumber"));
                electricityBillBean.setElectricityBillId(rs.getInt("electricityBillID"));
                electricityBillBean.setNickName(rs.getString("nickName"));
                electricityBillBean.setProcessingCycle(rs.getInt("processingCycle"));
                electricityBillBean.setStateId(rs.getInt("stateId"));
                electricityBillBean.setStateName(rs.getString("stateName"));
                electricityBillBean.setStatus(rs.getString("status"));
                electricityBillBean.setDateOfCreation(rs.getString("dateOfCreation"));
                electricityBillBean.setDateOfModification(rs.getString("dateOfModification"));
                double amount = rs.getDouble("amount");
                if (amount > 0) {
                    electricityBillBean.setAmount(amount);
                } else {
                    Random generator = new Random();
                    double randomIndex = generator.nextInt(1000);
                    String query1 = "update electricitybill set amount=? where electricityBillID=?";
                    stmt = con.prepareStatement(query1);
                    stmt.setDouble(1, randomIndex);
                    stmt.setInt(2, electricityBillBean.getElectricityBillId());
                    stmt.executeUpdate();
                    electricityBillBean.setAmount(randomIndex);
                }
                electricityBillBean.setLastPaidDate(rs.getDate("lastPaidDate"));
                java.util.Date date = new java.util.Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int currMonth = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                java.util.Date date1 = electricityBillBean.getLastPaidDate();
                cal.setTime(date1);
                int year1 = cal.get(Calendar.YEAR);
                int paidMonth = cal.get(Calendar.MONTH);
                int day1 = cal.get(Calendar.DAY_OF_MONTH);
                if (currMonth > paidMonth) {
                    String query1 = "update electricitybill set paidStatus=? where electricityBillID=?";
                    stmt = con.prepareStatement(query1);
                    stmt.setBoolean(1, false);
                    stmt.setInt(2, electricityBillBean.getElectricityBillId());
                    stmt.executeUpdate();
                    electricityBillBean.setPaidStatus(false);
                } else {
                    electricityBillBean.setPaidStatus(rs.getBoolean("paidStatus"));
                }
                electricityBillList.add(electricityBillBean);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return electricityBillList;


    }

    //getting SBI life insurence bill list for perticular account number
    public ArrayList getSBILifeInsurenceList(int accountnumber) {

        ArrayList sbiLifeInsurenceList = new ArrayList();
        try {
            con = dBUtil.getconnection();
            String query = "SELECT s.stateName,e.* FROM sbilifeinsurence e,states s WHERE accountNumber=? AND STATUS='Active' AND e.stateId=s.stateId";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountnumber);
            rs = stmt.executeQuery();
            while (rs.next()) {
                SBILifeInsurenceBean sBILifeInsurenceBean = new SBILifeInsurenceBean();
                sBILifeInsurenceBean.setAccountNumber(accountnumber);
                sBILifeInsurenceBean.setDateOfCreation(rs.getString("dateOfCreation"));
                sBILifeInsurenceBean.setDateOfModification(rs.getString("dateOfModification"));
                sBILifeInsurenceBean.setDobOfpolicyHolder(rs.getDate("dobOfPolicyHolder"));
                sBILifeInsurenceBean.setNickName(rs.getString("nickName"));
                sBILifeInsurenceBean.setPolicynumber(rs.getInt("policyNumber"));
                sBILifeInsurenceBean.setSbiLifeinsurenceId(rs.getInt("sbiLifeInsurenceId"));
                sBILifeInsurenceBean.setStateId(rs.getInt("stateId"));
                sBILifeInsurenceBean.setStateName(rs.getString("stateName"));
                sBILifeInsurenceBean.setStatus(rs.getString("status"));
                sBILifeInsurenceBean.setAmount(rs.getDouble("amount"));
                sBILifeInsurenceBean.setLastPaidDate(rs.getDate("lastPaidDate"));

                java.util.Date date = new java.util.Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int currMonth = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                java.util.Date date1 = sBILifeInsurenceBean.getLastPaidDate();
                cal.setTime(date1);
                int year1 = cal.get(Calendar.YEAR);
                int paidMonth = cal.get(Calendar.MONTH);
                int day1 = cal.get(Calendar.DAY_OF_MONTH);
                if (currMonth > paidMonth) {
                    String query1 = "update sbilifeinsurence set paidStatus=? where sbiLifeInsurenceId=?";
                    stmt = con.prepareStatement(query1);
                    stmt.setBoolean(1, false);
                    stmt.setInt(2, sBILifeInsurenceBean.getSbiLifeinsurenceId());
                    stmt.executeUpdate();
                    sBILifeInsurenceBean.setPaidStatus(false);
                } else {
                    sBILifeInsurenceBean.setPaidStatus(rs.getBoolean("paidStatus"));
                }

                sbiLifeInsurenceList.add(sBILifeInsurenceBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return sbiLifeInsurenceList;
    }

    //getting BSNL Cellone bill list for perticular account number
    public ArrayList getBSNLCelloneBillList(int accountnumber) {
        ArrayList bsnlCelloneBillList = new ArrayList();
        try {
            con = dBUtil.getconnection();
            String query = "SELECT s.stateName,e.* FROM bsnlcellonebill e,states s WHERE accountNumber=? AND STATUS='Active' AND e.stateId=s.stateId";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountnumber);
            rs = stmt.executeQuery();
            while (rs.next()) {
                BSNLCelloneBillBean bSNLCelloneBillBean = new BSNLCelloneBillBean();
                bSNLCelloneBillBean.setAccountNumber(accountnumber);
                bSNLCelloneBillBean.setBsnlCelloneId(rs.getInt("bsnlCelloneId"));
                bSNLCelloneBillBean.setDateOfCreation(rs.getString("dateOfCreation"));
                bSNLCelloneBillBean.setDateOfModification(rs.getString("dateOfModification"));
                bSNLCelloneBillBean.setMobileNumber(rs.getLong("mobileNumber"));
                bSNLCelloneBillBean.setNickName(rs.getString("nickName"));
                bSNLCelloneBillBean.setStateId(rs.getInt("stateId"));
                bSNLCelloneBillBean.setStateName(rs.getString("stateName"));
                bSNLCelloneBillBean.setStatus(rs.getString("status"));
                double amount = rs.getDouble("amount");
                if (amount > 0) {
                    bSNLCelloneBillBean.setAmount(amount);
                } else {
                    Random generator = new Random();
                    double randomIndex = generator.nextInt(1000);
                    String query1 = "update bsnlcellonebill set amount=? where bsnlCelloneId=?";
                    stmt = con.prepareStatement(query1);
                    stmt.setDouble(1, randomIndex);
                    stmt.setInt(2, bSNLCelloneBillBean.getBsnlCelloneId());
                    stmt.executeUpdate();
                    bSNLCelloneBillBean.setAmount(amount);
                }
                bSNLCelloneBillBean.setLastPaidDate(rs.getDate("lastPaidDate"));
                java.util.Date date = new java.util.Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int currMonth = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                java.util.Date date1 = bSNLCelloneBillBean.getLastPaidDate();
                cal.setTime(date1);
                int year1 = cal.get(Calendar.YEAR);
                int paidMonth = cal.get(Calendar.MONTH);
                int day1 = cal.get(Calendar.DAY_OF_MONTH);
                if (currMonth > paidMonth) {
                    String query1 = "update bsnlcellonebill set paidStatus=? where bsnlCelloneId=?";
                    stmt = con.prepareStatement(query1);
                    stmt.setBoolean(1, false);
                    stmt.setInt(2, bSNLCelloneBillBean.getBsnlCelloneId());
                    stmt.executeUpdate();
                    bSNLCelloneBillBean.setPaidStatus(false);
                } else {
                    bSNLCelloneBillBean.setPaidStatus(rs.getBoolean("paidStatus"));
                }
                bsnlCelloneBillList.add(bSNLCelloneBillBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
        return bsnlCelloneBillList;
    }

    //deleting electric bill for perticular account number
    public void deleteElectricityBilldetail(int electricitybillId, int accountNumber) {
        try {
            con = dBUtil.getconnection();
            String query = "delete from electricitybill  where accountNumber=? and electricityBillID=?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            stmt.setInt(2, electricitybillId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }

    }

    //deleting BSNL Cellone bill for perticular account number
    public void deleteBSNLCelloneDetail(int bsnlCelloneId, int accountNumber) {
        try {
            con = dBUtil.getconnection();
            String query = "delete from bsnlcellonebill where accountNumber=? and bsnlCelloneId=?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            stmt.setInt(2, bsnlCelloneId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
    }

    //deleting SBI Life Insurence bill for perticular account number
    public void deleteSBILifeInsurenceDetail(int sbiLifeInsurenceId, int accountNumber) {
        try {
            con = dBUtil.getconnection();
            String query = "delete from sbilifeinsurence where accountNumber=? and sbiLifeInsurenceId=?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, accountNumber);
            stmt.setInt(2, sbiLifeInsurenceId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dBUtil.freeConnection(con);
            dBUtil.freeResultSet(rs);
            dBUtil.freeStatement(stmt);
        }
    }

    // paying electric bill for particular month  and sending mail to user and inserting into transaction table
    public String payElectricityBill(ElectricityBillBean electricityBillBean, LoginInfoBean loginInfoBean, String BillRemark) {

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
                    double remainingBalance = (availBalance - electricityBillBean.getAmount());
                    if (remainingBalance > minBalance) {
                        String query1 = "insert into transactiondetail (accountNumber,transactionType,transactionDate,transactionAmount,dateOfCreation,dateOfModification,particulars) values(?,?,?,?,now(),now(),?)";
                        stmt = con.prepareStatement(query1);
                        stmt.setInt(1, loginInfoBean.getAccountNumber());
                        stmt.setString(2, "DR");
                        stmt.setDate(3, date);
                        stmt.setDouble(4, electricityBillBean.getAmount());
                        stmt.setString(5, "electricBill/" + BillRemark);
                        int rowUpdate = stmt.executeUpdate();
                        if (rowUpdate > 0) {
                            String query2 = "update clientdetail set availBalance=? where accountNumber=?";
                            stmt = con.prepareStatement(query2);
                            stmt.setDouble(1, remainingBalance);
                            stmt.setInt(2, loginInfoBean.getAccountNumber());
                            stmt.executeUpdate();
                            String query3 = "update electricitybill set amount=?,paidStatus=?,lastPaidDate=? where electricityBillID=?";
                            stmt = con.prepareStatement(query3);
                            stmt.setDouble(1, 0);
                            stmt.setBoolean(2, true);
                            stmt.setDate(3, date);
                            stmt.setInt(4, electricityBillBean.getElectricityBillId());
                            stmt.executeUpdate();
                            String msg = "Hi " + name + " Account Number " + loginInfoBean.getAccountNumber() + " Debited with INR " + electricityBillBean.getAmount() + " on " + date1 + " thru Internet Banking. Aval Bal INR " + remainingBalance + " CR. Thank you banking with us.";
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
        return "technicalError";
    }

     // paying BSNL Cellone bill for particular month  and sending mail to user and inserting into transaction table
    public String payBSNLCelloneBill(BSNLCelloneBillBean bSNLCelloneBillBean, LoginInfoBean loginInfoBean, String BillRemark) {
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
                    double remainingBalance = (availBalance - bSNLCelloneBillBean.getAmount());
                    if (remainingBalance > minBalance) {
                        String query1 = "insert into transactiondetail (accountNumber,transactionType,transactionDate,transactionAmount,dateOfCreation,dateOfModification,particulars) values(?,?,?,?,now(),now(),?)";
                        stmt = con.prepareStatement(query1);
                        stmt.setInt(1, loginInfoBean.getAccountNumber());
                        stmt.setString(2, "DR");
                        stmt.setDate(3, date);
                        stmt.setDouble(4, bSNLCelloneBillBean.getAmount());
                        stmt.setString(5, "BSNLCelloneBill/" + BillRemark);
                        int rowUpdate = stmt.executeUpdate();
                        if (rowUpdate > 0) {
                            String query2 = "update clientdetail set availBalance=? where accountNumber=?";
                            stmt = con.prepareStatement(query2);
                            stmt.setDouble(1, remainingBalance);
                            stmt.setInt(2, loginInfoBean.getAccountNumber());
                            stmt.executeUpdate();

                            String query3 = "update bsnlcellonebill set amount=?,paidStatus=?,lastPaidDate=? where bsnlCelloneId=?";
                            stmt = con.prepareStatement(query3);
                            stmt.setDouble(1, 0);
                            stmt.setBoolean(2, true);
                            stmt.setDate(3, date);
                            stmt.setInt(4, bSNLCelloneBillBean.getBsnlCelloneId());
                            stmt.executeUpdate();

                            String msg = "Hi " + name + " Account Number " + loginInfoBean.getAccountNumber() + " Debited with INR " + bSNLCelloneBillBean.getAmount() + " on " + date1 + " thru Internet Banking. Aval Bal INR " + remainingBalance + " CR. Thank you banking with us.";
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
        return "technicalError";

    }

     // paying SBI Life Insurence bill for particular month  and sending mail to user and inserting into transaction table
    public String paySBILifeInsurenceBill(SBILifeInsurenceBean sBILifeInsurenceBean, LoginInfoBean loginInfoBean, String BillRemark) {
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
                    double remainingBalance = (availBalance - sBILifeInsurenceBean.getAmount());
                    if (remainingBalance > minBalance) {
                        String query1 = "insert into transactiondetail (accountNumber,transactionType,transactionDate,transactionAmount,dateOfCreation,dateOfModification,particulars) values(?,?,?,?,now(),now(),?)";
                        stmt = con.prepareStatement(query1);
                        stmt.setInt(1, loginInfoBean.getAccountNumber());
                        stmt.setString(2, "DR");
                        stmt.setDate(3, date);
                        stmt.setDouble(4, sBILifeInsurenceBean.getAmount());
                        stmt.setString(5, "SBI Life Insurence Bill/" + BillRemark);
                        int rowUpdate = stmt.executeUpdate();
                        if (rowUpdate > 0) {
                            String query2 = "update clientdetail set availBalance=? where accountNumber=?";
                            stmt = con.prepareStatement(query2);
                            stmt.setDouble(1, remainingBalance);
                            stmt.setInt(2, loginInfoBean.getAccountNumber());
                            stmt.executeUpdate();

                            String query3 = "update sbilifeinsurence set paidStatus=?,lastPaidDate=? where sbiLifeInsurenceId=?";
                            stmt = con.prepareStatement(query3);
                            stmt.setBoolean(1, true);
                            stmt.setDate(2, date);
                            stmt.setInt(3, sBILifeInsurenceBean.getSbiLifeinsurenceId());
                            stmt.executeUpdate();

                            String msg = "Hi " + name + " Account Number " + loginInfoBean.getAccountNumber() + " Debited with INR " + sBILifeInsurenceBean.getAmount() + " on " + date1 + " thru Internet Banking. Aval Bal INR " + remainingBalance + " CR. Thank you banking with us.";
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
        return "technicalError";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;

import com.webaccess.netbanking.bean.BSNLCelloneBillBean;
import com.webaccess.netbanking.bean.ElectricityBillBean;
import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.bean.SBILifeInsurenceBean;
import com.webaccess.netbanking.dao.BillDao;
import java.util.ArrayList;

/**
 * Started on: Nov 04 2013
 *
 * Ended On:Nov 2013
 *
 * @author Jay prakash
 */
public class BillDelegate {

    BillDao dao = null;

    public BillDelegate() {
        dao = new BillDao();
    }

    public ArrayList getBiller() {
        return dao.getBiller();
    }

    public ArrayList getState() {
        return dao.getState();
    }

    public String insertBillInformation(ElectricityBillBean electricityBillBean) {
        return dao.insertBillInformation(electricityBillBean);
    }

    public String insertSBILifeInsurenceInformation(SBILifeInsurenceBean sBILifeInsurenceBean) {

        return dao.insertSBILifeInsurenceInformation(sBILifeInsurenceBean);
    }

    public String insertbSNLCelloneBillInformation(BSNLCelloneBillBean bSNLCelloneBillBean) {

        return dao.insertbSNLCelloneBillInformation(bSNLCelloneBillBean);
    }

    public ArrayList getElectricityBillList(int accountnumber) {
        return dao.getElectricityBillList(accountnumber);

    }

    public ArrayList getSBILifeInsurenceList(int accountnumber) {
        return dao.getSBILifeInsurenceList(accountnumber);

    }

    public ArrayList getBSNLCelloneBillList(int accountnumber) {
        return dao.getBSNLCelloneBillList(accountnumber);

    }

    public void deleteElectricityBilldetail(int electricitybillId, int accountNumber) {
        dao.deleteElectricityBilldetail(electricitybillId, accountNumber);

    }

    public void deleteBSNLCelloneDetail(int bsnlCelloneId, int accountNumber) {
        dao.deleteBSNLCelloneDetail(bsnlCelloneId, accountNumber);

    }

    public void deleteSBILifeInsurenceDetail(int sbiLifeInsurenceId, int accountNumber) {
        dao.deleteSBILifeInsurenceDetail(sbiLifeInsurenceId, accountNumber);

    }

    public String payElectricityBill(ElectricityBillBean electricityBillBean,LoginInfoBean loginInfoBean, String BillRemark) {
       return dao.payElectricityBill(electricityBillBean,loginInfoBean, BillRemark);

    }

    public String payBSNLCelloneBill(BSNLCelloneBillBean bSNLCelloneBillBean,LoginInfoBean loginInfoBean,String BillRemark) {
      return dao.payBSNLCelloneBill(bSNLCelloneBillBean,loginInfoBean, BillRemark);

    }

    public String paySBILifeInsurenceBill(SBILifeInsurenceBean bILifeInsurenceBean,LoginInfoBean loginInfoBean,String BillRemark) {
       return dao.paySBILifeInsurenceBill(bILifeInsurenceBean,loginInfoBean, BillRemark);
    }
}

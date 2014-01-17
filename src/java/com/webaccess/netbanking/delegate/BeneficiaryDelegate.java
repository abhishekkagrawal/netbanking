/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;

import com.webaccess.netbanking.bean.BeneficiaryDetailBean;
import com.webaccess.netbanking.bean.TransferBean;
import com.webaccess.netbanking.dao.BeneficiaryDao;
import java.util.ArrayList;

/**
 * Started on: Oct 29 2013
 *
 * @author Jay Prakash
 *
 */
public class BeneficiaryDelegate {

    BeneficiaryDao dao;

    public BeneficiaryDelegate() {
        dao = new BeneficiaryDao();
    }

    public String addBeneficiary(BeneficiaryDetailBean beneficiaryDetail) {
        return dao.addBeneficiary(beneficiaryDetail);
    }

    public ArrayList getDeactiveBeneficiary(int accountNumber, String transactionType) {

        return dao.getDeactiveBeneficiary(accountNumber, transactionType);
    }

    public void deleteBeneficiary(int beneficiartId, int accountNumber) {
        dao.deleteBeneficiary(beneficiartId, accountNumber);
    }

    public ArrayList getActiveBeneficiary(int accountNumber, String transactionType) {
        return dao.getActiveBeneficiary(accountNumber, transactionType);
    }

    public boolean confirmBeneficiary(int accountNumber, int beneficiaryId, int URNNumber) {
        return dao.confirmBeneficiary(accountNumber, beneficiaryId, URNNumber);
    }

    public String validateTransaction(TransferBean transferBean) {
        return dao.validateTransaction(transferBean);
    }

    public ArrayList getBankDetail() {
        return dao.getBankDetail();
    }
}

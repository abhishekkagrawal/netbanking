/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.delegate;

import com.webaccess.netbanking.bean.CardDetailBean;
import com.webaccess.netbanking.bean.LoginInfoBean;
import com.webaccess.netbanking.dao.CreditCardDao;
import java.util.ArrayList;

/**
 * Started On Nov 11 2013
 *
 * Started On Nov 11 2013
 *
 * @author Jay prakash
 */
public class CreditCardDelegate {

    CreditCardDao dao;

    public CreditCardDelegate() {
        dao = new CreditCardDao();
    }

    public ArrayList getCardType() {
        return dao.getCardType();
    }

    public String applyNewCreditCard(int accountNumber, int cardTpe, String nameOnCard) {
      return  dao.applyNewCreditCard(accountNumber, cardTpe, nameOnCard);
    }

    public ArrayList getCreditCardetailList(int accountNumber) {
        return dao.getCreditCardetailList(accountNumber);
    }

    public double getMaxLimit(int cardName) {
        return dao.getMaxLimit(cardName);
    }

    public String payCreditCardBill(LoginInfoBean loginInfoBean, CardDetailBean cardDetailBean,String BillRemark) {
      return dao.payCreditCardBill(loginInfoBean, cardDetailBean,BillRemark);
    }
}

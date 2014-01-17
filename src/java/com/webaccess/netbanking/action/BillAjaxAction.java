/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.delegate.BillDelegate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 * bill ajex action page for deleting bills 
 * 
 * Started on: Nov 06 2013
 *
 * Ended on: Nov 06 2013
 *
 * @author Jay Prakash
 */
public class BillAjaxAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

    HttpServletResponse response;
    HttpServletRequest request;
    BillDelegate billDelegate = new BillDelegate();

    //action for deleting electricity bill detail
    public String deleteElectricityBilldetail() {
        try {
            response.setContentType("text/text;charset=utf-8");
            response.setHeader("cache-control", "no-cache");
            int electricitybillId = Integer.parseInt(request.getParameter("electricitybillId"));
            int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
            billDelegate.deleteElectricityBilldetail(electricitybillId,accountNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //action for deleting bsnl bill detail
     public String deleteBSNLCelloneDetail() {
        try {
            response.setContentType("text/text;charset=utf-8");
            response.setHeader("cache-control", "no-cache");
            int bsnlCelloneId = Integer.parseInt(request.getParameter("bsnlCelloneId"));
            int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
            billDelegate.deleteBSNLCelloneDetail(bsnlCelloneId,accountNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
     //action for deleting SBI life insurence bill detail
      public String deleteSBILifeInsurenceDetail() {
        try {
            response.setContentType("text/text;charset=utf-8");
            response.setHeader("cache-control", "no-cache");
            int sbiLifeInsurenceId = Integer.parseInt(request.getParameter("sbiLifeInsurenceId"));
            int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
            billDelegate.deleteSBILifeInsurenceDetail(sbiLifeInsurenceId,accountNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setServletResponse(HttpServletResponse hsr) {
        this.response = hsr;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
}

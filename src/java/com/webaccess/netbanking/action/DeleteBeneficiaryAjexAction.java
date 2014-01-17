/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.delegate.BeneficiaryDelegate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 * Started On: Oct 30 2013
 *
 * Ended On : Oct 30 2013
 *
 * @author Jay Prakash
 */
public class DeleteBeneficiaryAjexAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

    HttpServletResponse response;
    HttpServletRequest request;
    
    //deleting beneficiary from database
    @Override
    public String execute() {
        try {

            response.setContentType("text/text;charset=utf-8");
            response.setHeader("cache-control", "no-cache");
            int beneficiaryId =Integer.parseInt(request.getParameter("beneficiaryId"));
            int accountNumber= Integer.parseInt(request.getParameter("accountNumber"));

            BeneficiaryDelegate beneficiaryDelegate=new BeneficiaryDelegate();
            beneficiaryDelegate.deleteBeneficiary(beneficiaryId,accountNumber);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }//end of execute() method..

    public void setServletResponse(HttpServletResponse hsr) {
        this.response = hsr;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
}//end of AjaxAction class..


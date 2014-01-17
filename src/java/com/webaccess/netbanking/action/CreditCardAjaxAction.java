/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webaccess.netbanking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webaccess.netbanking.delegate.CreditCardDelegate;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 * Started On: Nov 12 2013
 *
 * Ended On: Nov 12 2013
 *
 * @author Jay Prakash
 */
public class CreditCardAjaxAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    HttpServletRequest request;
    HttpServletResponse response;
    CreditCardDelegate creditCardDelegate=new CreditCardDelegate();

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request=hsr;
    }

    @Override
    public void setServletResponse(HttpServletResponse hsr) {
        this.response=hsr;
    }
    
      //action for deleting SBI life insurence bill detail
      public String getMaxLimit() {
        try {
            response.setContentType("text/text;charset=utf-8");
            response.setHeader("cache-control", "no-cache");
            int cardName = Integer.parseInt(request.getParameter("cardName"));
            double maxLimit=creditCardDelegate.getMaxLimit(cardName);
            PrintWriter out=response.getWriter();
            out.print(maxLimit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

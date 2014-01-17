<%-- 
    Document   : passwordResetPage
    Created on : Oct 16, 2013, 2:02:40 PM
    Author     : Jay Prakash
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>         
        <%@include file="../header/Header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>
        <script type="text/javascript" src="../javascript/allValidation.js"></script>
        <title>Reset Password Page</title>
        <s:head/>
    </head>
    <body onload="window.history.forward(1);">
        <div align='center' style="background-image:url(../header/images/bg.jpg)">
            <s:form action="PasswordReset" onsubmit="return passwordResetPageValidateForm()">
                <h3>Online Password Reset</h3>
                <table align="center">
                    <tr>
                        <td><s:textfield name="userId" label="User Id " readonly="true" id="passwordResetPageUserId" onblur="return passwordResetPageUserIdValidation()" maxLength="10"/></td>
                    </tr>
                    <tr/>
                    <tr>
                        <td><s:textfield name="mobileNumber" label="Enter Registered Mobile Number " id="passwordResetPageMobile" onblur="return passwordResetPageMobileValidation()" maxLength="10"/></td>
                    </tr>                   
                    <tr>
                        <td><s:textfield name="panCardNumber" label="Enter PAN card number" id="passwordResetPagePanCardNumber" onblur="return passwordResetPagePanCardNumberValidation()" maxLength="15"/></td>
                    </tr>
                    <tr>
                        <td><s:textfield name="optNumber" label="Enter OPT Number" id="passwordResetPageOPTNumber" onblur="return passwordResetPageOPTNumberValidation()"/></td>                        
                    </tr>
                    <tr>
                        <td><s:submit value="Submit" align="center"/></td>
                    </tr>
                </table>
            </s:form>
        </div>
    </body>
    <%@include file="/jsps/Footer.jsp" %>
</html>

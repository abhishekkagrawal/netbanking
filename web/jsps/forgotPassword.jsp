<%-- 
    Document   : forgotPassword
    Created on : Oct 14, 2013, 5:11:25 PM
    Author     : Jay Prakash
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>
        <script type="text/javascript" src="../javascript/allValidation.js"></script>
        <title>JSP Page</title>
        <s:head/>
    </head>
    <body>
        <%@include file="../header/Header.jsp" %>
        <div align='center' style="background-image:url(../header/images/bg.jpg)">
            <br><br>
            <s:form action="ForgotUserIdAction" onsubmit="return forgotPasswordValidation()">
                <h3>Enter your User Id to receive one time password (OTP) on your registered EmailId</h3>
                <div>
                    <s:actionerror/>
                </div>
                <table align="center">
                    <tr>
                        <td><s:textfield name="userId" label="Enter User Id" id="forgotPasswordUserId" onblur="return forgotPasswordValidateUserId()" maxLength="10"/></td>
                    </tr> 
                    <tr>                       
                        <td align="right"><s:submit value="Continue" theme="simple" /></td>
                        <td align="right"><a href="<s:url action="backUserIdAction"/>">Back</a></td>
                    </tr>
                </table>
            </s:form>
            <br><br>
        </div>
    </body>
    <%@include file="/jsps/Footer.jsp" %>
</html>

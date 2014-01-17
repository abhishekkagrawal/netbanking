<%-- 
    Document   : loginPage
    Created on : Oct 14, 2013, 10:40:40 AM
    Author     : Jay Prakash
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>

        <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">      
        <script type="text/javascript" src="../javascript/allValidation.js"></script>
        <title>Login Page</title>
        <s:head/>
    </head>
    <body onload="window.history.forward(1);">
        <%@include file="../header/Header.jsp" %>
        <div align='center' style="background-image:url(../header/images/bg.jpg)">
            <br><br>
            <div>
                <s:actionerror/>
            </div>
            <s:form action="LoginPageAction" onsubmit="return validateLoginPage()" >
                <table>
                    <tr>
                        <td><s:textfield name="userId" label="User Id" id="userid"  readonly="true"/></td>
                    </tr>
                    <tr>
                        <td><s:password name="password" label="Enter Password" id="loginPagePassword" onblur="return validateloginPagePassword()" maxLength="15"/></td>
                    </tr>
                    <tr>
                        <td><a href="<s:url action="differentUserLoginPageAction"  />">Different User?</a></td>
                        <td align="center"><s:submit value="Submit"  theme="simple"/></td>
                    </tr>
                    <tr>
                        <td><a href="<s:url action="forgotPasswordLoginPageAction"  />">Forgot Password?</a></td>
                        <td align='right'><a href="<s:url action="newUserLoginPageAction" />">New User?</a></td>
                    </tr>
                </table>
                <br><br>
            </s:form>
        </div>
    </body>
    <%@include file="/jsps/Footer.jsp" %>
</html>

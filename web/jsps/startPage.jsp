<%-- 
    Document   : startPage
    Created on : Oct 14, 2013, 9:57:00 AM
    Author     : Jay Prakash
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib  prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>

        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>UserId Page</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/allValidation.js"></script>
        <s:head/>
    </head>
    <body>      
        <%@include file="../header/Header.jsp" %>
        <div align="center" style="background-image:url(${pageContext.request.contextPath}/header/images/bg.jpg)">
            <h2 style="color: red">!!! Welcome to NetBanking World !!!</h2>
            <s:form action="UserIdAction" onsubmit="return validationStartPageUserId()">
                <s:token name="token"/>
                <table align="center">
                    <tr>
                        <td><span id="userIdError"></span></td>
                        <td><s:textfield name="userId" label="Enter User Id " id="startPageUserId" onblur="validateStartPageUserId()"/></td>   
                    </tr> 
                    <tr>
                        <td><s:submit value="Continue" align="center" /></td>
                    </tr>
                    <tr>
                        <td><a href="<s:url action="forgotPasswordUserIdAction"  />">Forgot Password?</a></td>
                        <td align='right'><a href="<s:url action="newUserUserIdAction" />">New User?</a></td>
                    </tr>
                </table>
            </s:form>
        </div>
    </body>
    <%@include file="/jsps/Footer.jsp" %>
</html>

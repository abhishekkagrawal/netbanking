<%-- 
    Document   : thankURegistration
    Created on : Oct 15, 2013, 2:43:34 PM
    Author     : Jay Prakash
    Ended on   : Oct 16,2013  
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Thanks Page</title>
    </head>
    <body  onload="window.history.forward(1);">
        <%@include file="../header/Header.jsp" %>
        <div align='center' style="background-image:url(../header/images/bg.jpg)">
            <h1>Thank you for registration.</h1>
            <p>Please check your registered email for user id and passwords. </p>
            <p>For login click <a href="<s:url action="thankUPage"  />">Here</a> </p>
        </div>
    </body>
    <%@include file="/jsps/Footer.jsp" %>
</html>

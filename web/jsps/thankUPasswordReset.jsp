<%-- 
    Document   : thankUPasswordReset
    Created on : Oct 17, 2013, 1:28:00 PM
    Author     : Jay Prakash
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Thanks Page</title>
    </head>
    <body onload="window.history.forward(1);">
         <s:token name="token"/>
        <%@include file="../header/Header.jsp" %>
        <div align='center' style="background-image:url(../header/images/bg.jpg)">
            <h1>your password has been reset successfully </h1>
            <p>Please check your registered email for user id and passwords. </p>
            <p>For login click <a href="<s:url action="thankUPage"  />">Here</a> </p>
        </div>
    </body>
    <%@include file="/jsps/Footer.jsp" %>
</html>

</html>

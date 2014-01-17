<%-- 
    Document   : errorPage
    Created on : Oct 19, 2013, 1:01:43 PM
    Ended on   : Oct 19, 2013
    Author     : Jay Prakash
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body onload="window.history.forward(1);">
        <%@include file="../header/Header.jsp" %>
        <s:form action="UserLoginPageAction">
            <h1 align="center">Error Page due to session expire ! </h1>
            <h3 align="center">for login click <a href="<s:url action="differentUserLoginPageAction" />">Here</a></h3>
        </s:form>
        <%@include file="../header/Footer.jsp" %>
    </body>
</html>

<%-- 
    Document   : loginCheck
    Created on : Oct 19, 2013, 12:17:17 PM
    Author     : Administrator
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html" import="java.util.*"%>
<html>
    <head>
     
    </head>
    <body>        
        <s:if test="#session==null ">
            <jsp:forward page="errorPage.jsp" />  
        </s:if>
    </body>
</html>

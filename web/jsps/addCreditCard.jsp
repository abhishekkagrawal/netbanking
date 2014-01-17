<%-- 
    Document   : cardDetailPage
    Created on : Oct 22, 2013, 11:36:10 AM
    Author     : Jay Prakash
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="loginCheck.jsp" />
<!DOCTYPE html>
<%
    String userId = (String) session.getAttribute("userId");
    if (userId == null || userId.equals("")) {
        response.sendRedirect("errorPage.jsp");
    }

%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pay credit Card bill Page</title>
    <link rel="stylesheet" href="../css/layout.css" type="text/css" />
    <link rel="stylesheet" href="../css/navi.css" type="text/css" />
    <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>
</head>
<body id="format" onload="window.history.forward(1);" >
    <%@include file="../header/Header.jsp" %>
    <s:form action="PayBillPageAction"> 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountPayBillPageAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferPayBillPageAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentPayBillPageAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailPayBillPageAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailPayBillPageAction"/>">Personal Detail</a></li> 
                    <li><a href="<s:url action="LogoutAction"/>">Logout</a></li>
                </ul>
            </div>
        </div>
        <br><br>
        <div style="background-image:url(../header/images/bg.jpg)">
            <div>         
                <p style="float: left"> Hi,  <s:property value="#session.userInfo.name" />
                <p align="right" style="float: right"><b align="right">Last Logged In</b> <s:property value="#session.loginInfo.lastLogin"/></p>
            </div>
            <div>
                <% SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
                    String date = ft.format(new Date());
                %>
                <p style="background-image:url(../header/images/bg.jpg)"><b>Account Summary</b> <%=date%></p>               
            </div>
        </div>
        <div>
            <div class="wrapper row4">
                <div id="quicknav" class="clear">
                    <ul>
                        <li><a href="<s:url action="addBillPayBillPageAction"/>">Card Detail</a></li>                        
                        <li><a href="<s:url action="modifyBillPayBillPageAction"/>">Apply Card</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div align="center">
            <s:actionerror/>
        </div>
        <br><br>
        <div>
            <table>
                <tr>

                </tr>
            </table>
        </div>
        <br></s:form>
    </body>
<%@include file="/jsps/Footer.jsp" %>
</html>
<%-- 
    Document   : Home
    Created on : Oct 14, 2013, 3:41:28 PM
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
//            onload="window.history.forward(1);"
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Page</title>
    <link rel="stylesheet" href="../css/layout.css" type="text/css" />
    <link rel="stylesheet" href="../css/navi.css" type="text/css" />
    <!-- Featured Slider Elements -->
    <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>

</head>
<body id="format" onload="window.history.forward(1);" >
    <%@include file="../header/Header.jsp" %>
    <s:form action="homeAction" > 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountHomeAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferHomeAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentHomeAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailHomeAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailHomeAction"/>">Personal Detail</a></li> 
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
            <div id="summaryDiv">
                <h4>Click to account number for get detail</h4>
                <table align="center" border='1'>
                    <div>
                        <p><b><h2 align="center">!!! Welcome to home page !!!</h2></b></p>
                    </div>
                    <tr><td><b align="center">OverView</b></td></tr>
                    <tr>
                        <th align="center">Customer Id</th>
                        <th align="center">Account Type</th>
                        <th align="center">Account Number</th>
                        <th align="center">currency</th>
                        <th align="center">Balance</th>
                    </tr>
                    <tr>
                        <td align="center"><s:property value="#session.loginInfo.userId"/></td>
                        <td align="center"><s:property value="#session.userInfo.accountType"/></td>
                        <td align="center"><a href="<s:url action="getTransactionHomeAction"/>" onclick="" id="accountNumber"><s:property value="#session.userInfo.accountNumber"/></a></td>
                        <td align="center">INR</td>
                        <td align="center"><s:property value="#session.userInfo.availBalance"/></td>
                    </tr>
                </table>
            </div>
        </div>
    </s:form>
    <br/><br/>
</body>
<%@include file="/jsps/Footer.jsp" %>
</html>


<%-- 
    Document   : personalDetailPage
    Created on : Oct 22, 2013, 11:37:17 AM
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
    <title>Personal Detail Page</title>
    <link rel="stylesheet" href="../css/layout.css" type="text/css" />
    <link rel="stylesheet" href="../css/navi.css" type="text/css" />
    <!-- Featured Slider Elements -->
    <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>

</head>
<body onload="window.history.forward(1);">
    <%@include file="../header/Header.jsp" %>
    <s:form action="PersonalDetailPageAction">
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountPersonalDetailPageAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferPersonalDetailPageAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentPersonalDetailPageAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailPersonalDetailPageAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailPersonalDetailPageAction"/>">Personal Detail</a></li> 
                    <li><a href="<s:url action="LogoutAction"/>">Logout</a></li>
                </ul>
            </div>
        </div>  
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
        <div align="center" style="padding-left:1px;">
            <br>
            <table width="60%" cellspacing="8" cellpadding="2" border="0" align="center">
                <tbody>
                    <tr class="tabledatatr3">
                        <td height="45" bgcolor="#faf6f5" align="left" style="border:1px solid #cfc5aa;border-collapse:collapse;">
                            <br>
                            <b>
                                <a class="whiteText" href="<s:url action="myProfilePersonalDetailPageAction"/>">
                                    <font color="#8c062f">My Profile</font>
                                </a>
                            </b>
                            <br>
                            See Your Personal Detail
                            <br>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br>
            <table width="60%" cellspacing="8" cellpadding="2" border="0" align="center">
                <tbody>
                    <tr class="tabledatatr3">
                        <td height="45" bgcolor="#faf6f5" align="left" style="border:1px solid #cfc5aa;border-collapse:collapse;">
                            <br>
                            <b>
                                <a class="whiteText" href="<s:url action="changeLoginPasswordPersonalDetailPageAction"/>">
                                    <font color="#8c062f">Change Login Password</font>
                                </a>
                            </b>
                            <br>
                            Change Your Login Password.
                            <br>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br>
            <table width="60%" cellspacing="8" cellpadding="2" border="0" align="center">
                <tbody>
                    <tr class="tabledatatr3">
                        <td height="45" bgcolor="#faf6f5" align="left" style="border:1px solid #cfc5aa;border-collapse:collapse;">
                            <br>
                            <b>
                                <a class="whiteText" href="<s:url action="changeTransactionPasswordPersonalDetailPageAction"/>">
                                    <font color="#8c062f">Change txn Password</font>
                                </a>
                            </b>
                            <br>
                            Change Your Transaction Password.
                            <br>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br>
            <br>
        </div>
    </s:form>

</body>
<%@include file="../header/Footer.jsp" %>
</html>

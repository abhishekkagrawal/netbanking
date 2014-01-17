<%-- 
    Document   : accountDetail
    Created on : Oct 22, 2013, 3:52:17 PM
    Ended on   : Oct 25, 2013 
    Author     : Jay Prakash
--%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="../header/Header.jsp" %>
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
    <title>Account Detail Page</title>
    <link rel="stylesheet" href="../css/layout.css" type="text/css" />
    <link rel="stylesheet" href="../css/navi.css" type="text/css" />
    <!-- Featured Slider Elements -->
    <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>
    <script type="text/javascript" src="../javascript/jquery-s3slider.js"></script>
    <script type="text/javascript" src="../javascript/jquery-s3slider.setup.js"></script>

</head>
<body id="format" onload="window.history.forward(1);" >

    <s:form action="homeAction" > 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountAccountDetailPageAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferAccountDetailPageAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentAccountDetailPageAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailAccountDetailPageAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailHomeAction"/>">Personal Detail</a></li> 
                    <li><a href="<s:url action="LogoutAction"/>">Logout</a></li>
                </ul>
            </div>
        </div>
        <br><br>
        <div style="background-image:url(../header/images/bg.jpg)">
            <div>         
                <p style="float: left"> Hi,  <s:property value="#session.userInfo.name"/></p>
            </div>
            <div>
                <% SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
                    String date = ft.format(new Date());
                %>
                <p style="background-image:url(../header/images/bg.jpg)"><b>Account Detail</b> <%=date%></p>               
            </div>
            <p>Account detail</p>
            <div class="wrapper row4">
                <div id="quicknav" class="clear">
                    <ul>
                        <li><a href="<s:url action="miniStatementAccountDetailPageAction"/>">Mini Statement</a></li>                        
                        <li><a href="<s:url action="viewAndSaveAccountDetailPageAction"/>">View/Save Statement</a></li>
                        <li><a href="">Account Detail</a></li>
                    </ul>
                </div>
            </div>
            <div>
                Account Number  <s:property value="#session.userInfo.accountNumber"/>
                <table align="center"  border="1">
                    <tr>
                        <td>Account Detail</td>
                    </tr>
                    <tr>
                        <td>Account Id</td>
                        <td><s:property value="#session.loginInfo.userId"/></td>
                    </tr>
                    <tr>
                        <td>Account Number</td>
                        <td><s:property value="#session.userInfo.accountNumber"/></td>
                    </tr>
                    <tr>
                        <td>Account Name</td>
                        <td><s:property value="#session.userInfo.name"/></td>
                    </tr>
                    <tr>
                        <td>Currency</td>
                        <td>INR</td>
                    </tr>
                    <tr>
                        <td>Account Type</td>
                        <td><s:property value="#session.userInfo.accountType"/></td>
                    </tr>
                    <tr>
                        <td>Account Open Date</td>
                        <td><s:property value="#session.userInfo.accountNumber"/></td>
                    </tr>
                    <tr>
                        <td>Balance Detail</td>
                    </tr>
                    <tr>
                        <td>Account balance</td>
                        <td><s:property value="#session.userInfo.availBalance"/></td>
                    </tr>
                    <tr>
                        <td>Available balance</td>
                        <td><s:property value="#session.userInfo.availBalance"/></td>
                    </tr>
                    <tr>
                        <td>Effective balance</td>
                        <td><s:property value="#session.userInfo.availBalance"/></td>
                    </tr>
                    <tr>
                        <td>Unclear balance</td>
                        <td>0.00</td>
                    </tr>
                    <tr>
                        <td>Other balance</td>
                        <td><s:property value="#session.userInfo.availBalance"/></td>
                    </tr>
                </table>
                <s:submit action="backAccountDetailPageAction" value="Back" align="center"/>
            </div>
        </div>
    </s:form>
</body>
<%@include file="/jsps/Footer.jsp" %>
</html>



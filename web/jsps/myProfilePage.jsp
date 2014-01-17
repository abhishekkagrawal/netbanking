<%-- 
    Document   : myProfilePage
    Created on : Oct 25, 2013, 7:08:26 PM
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
    <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>
    <script type="text/css">
        .myprofiletd1 {
        background-color: white;
        color: #333333;
        font-family: Tahoma;
        font-size: 12px;
        height: 28px;
        width: 25%;
        }

    </script>
</head>
<body id="format" onload="window.history.forward(1);" >
    <%@include file="../header/Header.jsp" %>
    <s:form action="MyProfilePageAction" > 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountMyProfilePageAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferMyProfilePageAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentMyProfilePageAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailMyProfilePageAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailMyProfilePageAction"/>">Personal Detail</a></li> 
                    <li><a href="<s:url action="LogoutAction"/>">Logout</a></li>
                </ul>
            </div>
        </div>
        <br/><br/>
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
                        <li><a href="<s:url action="myProfileMyProfilePageAction"/>">My Profile</a></li>                        
                        <li><a href="<s:url action="changeLoginPasswordMyProfilePageAction"/>">Change Login password</a></li>
                        <li><a href="<s:url action="changeTransactionPasswordMyProfilePageAction"/>">Change Transaction password</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div>
            <p><b><h2 align="center">!!! See your personal information !!!</h2></b></p>
        </div>
        <div>
            <div style="text-align: center;">
                <table align="center">
                    <tr><td><p class="myprofiletd1"><b>Personal information </b></p></td></tr>
                    <br/>
                    <tr><td> <s:textfield name="email" label="Email Address" value="%{#session.userInfo.emailId}" readonly="true"/></td></tr>
                    <tr><td><s:textfield name="firstName" label="First Name" value="%{#session.userInfo.name}" readonly="true"/></td></tr>
                    <tr><td><s:textfield name="lastName" label="Last Name" value="" readonly="true"/></td></tr>
                    <tr><td><s:textfield name="middleName" label="User Id" value="%{#session.loginInfo.userId}" readonly="true"/></td></tr>

                </table>
            </div>
            <br/>
            <div align="center">
                <table align="center">
                    <tr><td> <p class="myprofiletd1"><b>Address information </b></p></td></tr>
                    <br/>
                    <tr><td><s:textfield name="address" label="Address" value="%{#session.userInfo.address}" readonly="true"/></td></tr>
                    <tr><td><s:textfield name="mobile" label="Mobile Phone No" value="%{#session.userInfo.number}" readonly="true"/></td></tr>
                    <tr><td><s:textfield name="cityName" label="City Name" value="" readonly="true"/></td></tr>
                    <tr><td><s:textfield name="phoneNumber" label="Phone No" value="" readonly="true"/></td></tr>
                    <tr><td> <s:textfield name="state" label="State" value="" readonly="true"/></td></tr>
                    <tr><td> <s:textfield name="country" label="Country" value="India" readonly="true"/></td></tr>
                </table>
            </div>
            <br/>
            <s:submit action="backMyProfilePageAction" name="Back" value="Back" align="center"/>
            <br/>
            <br/>
        </div>
    </s:form>
</body>
<%@include file="/jsps/Footer.jsp" %>
</html>
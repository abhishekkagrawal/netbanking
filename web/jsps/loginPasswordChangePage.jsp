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
        response.sendRedirect("startPage.jsp");
    }
//            onload="window.history.forward(1);"
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Change login Password Page</title>
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
    <script type="text/javascript">
        function  validation() {
            var flag = false;
            if (oldPasswordvalidation() && newPasswordvalidation() && conformNewPasswordValidation()) {
                return true;
            }
            return flag;
        }
        function oldPasswordvalidation() {

            var oldLoginPassword = $('#oldLoginPassword').val();
            var flag = true;
            var regex = /^[A-Za-z()]+[0-9]+[A-za-z0-9()]+$/;
            if (oldLoginPassword === "" || oldLoginPassword.length === 0) {
                $("#errorpassword").remove();
                $("#oldLoginPassword").parent().append('<span id="errorpassword">*Enter Password</span>');
                $("#oldLoginPassword").focus();
                flag = false;
            } else if (oldLoginPassword.length < 8 || oldLoginPassword.length > 15) {
                $("#errorpassword").remove();
                $("#oldLoginPassword").parent().append('<span id="errorpassword">*Password should be 8- 15 char</span>');
                $("#oldLoginPassword").focus();
                flag = false;
            } else if (!regex.test(oldLoginPassword)) {
                $("#errorpassword").remove();
                $("#oldLoginPassword").parent().append('<span id="errorpassword">*Password contain alphanumeric char</span>');
                $("#oldLoginPassword").focus();
                flag = false;

            }
            if (flag == true) {
                $("#errorpassword").remove();
                return flag;
            } else {
                return flag;
            }

        }
        function newPasswordvalidation() {

            var newLoginPassword = $('#newLoginPassword').val();
            var oldLoginPassword = $('#oldLoginPassword').val();
            var flag = true;
            var regex = /^[A-Za-z()]+[0-9]+[A-za-z0-9()]+$/;
            if (newLoginPassword === "" || newLoginPassword.length === 0) {
                $("#errorpassword").remove();
                $("#newLoginPassword").parent().append('<span id="errorpassword">*Enter Password</span>');
                $("#newLoginPassword").focus();
                flag = false;
            } else if (newLoginPassword.length < 8 || newLoginPassword.length > 15) {
                $("#errorpassword").remove();
                $("#newLoginPassword").parent().append('<span id="errorpassword">*Password should be 8- 15 char</span>');
                $("#newLoginPassword").focus();
                flag = false;
            } else if (!regex.test(newLoginPassword)) {
                $("#errorpassword").remove();
                $("#newLoginPassword").parent().append('<span id="errorpassword">*Password contain alphanumeric char</span>');
                $("#newLoginPassword").focus();
                flag = false;

            } else if (newLoginPassword == oldLoginPassword) {
                $("#errorpassword").remove();
                $("#newLoginPassword").parent().append('<span id="errorpassword">*Password should not match with current password</span>');
                $("#newLoginPassword").focus();
                flag = false;

            }
            if (flag == true) {
                $("#errorpassword").remove();
                return flag;
            } else {
                return flag;
            }

        }
        function conformNewPasswordValidation() {
            var conformNewLoginPassword = $('#conformNewLoginPassword').val();
            var newLoginPassword = $('#newLoginPassword').val();
            var flag = true;
            var regex = /^[A-Za-z()]+[0-9]+[A-za-z0-9()]+$/;
            if (conformNewLoginPassword === "" || conformNewLoginPassword.length === 0) {
                $("#errorpassword").remove();
                $("#conformNewLoginPassword").parent().append('<span id="errorpassword">*Enter Password</span>');
                $("#conformNewLoginPassword").focus();
                flag = false;
            } else if (conformNewLoginPassword.length < 8 || conformNewLoginPassword.length > 15) {
                $("#errorpassword").remove();
                $("#conformNewLoginPassword").parent().append('<span id="errorpassword">*Password should be 8- 15 char</span>');
                $("#conformNewLoginPassword").focus();
                flag = false;
            } else if (!regex.test(conformNewLoginPassword)) {
                $("#errorpassword").remove();
                $("#conformNewLoginPassword").parent().append('<span id="errorpassword">*Password contain alphanumeric character</span>');
                $("#conformNewLoginPassword").focus();
                flag = false;
            } else if (newLoginPassword !== conformNewLoginPassword) {
                alert(newLoginPassword + " " + conformNewLoginPassword);
                $("#errorpassword").remove();
                $("#conformNewLoginPassword").parent().append('<span id="errorpassword">*Password is not matching</span>');
                $("#conformNewLoginPassword").focus();
                flag = false;

            }
            if (flag == true) {
                $("#errorpassword").remove();
                return flag;
            } else {
                return flag;
            }

        }
    </script>
    <s:head/>
</head>
<body id="format" onload="window.history.forward(1);" >
    <%@include file="../header/Header.jsp" %>
    <s:form action="LoginPasswordChangePageAction" onsubmit="return validation();" > 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountLoginPasswordChangePageAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferLoginPasswordChangePageAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentLoginPasswordChangePageAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailLoginPasswordChangePageAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailLoginPasswordChangePageAction"/>">Personal Detail</a></li> 
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
                        <li><a href="<s:url action="myProfileLoginPasswordChangePageAction"/>">My Profile</a></li>                        
                        <li><a href="<s:url action="changeLoginPasswordLoginPasswordChangePageAction"/>">Change Login password</a></li>
                        <li><a href="<s:url action="changeTransactionPasswordLoginPasswordChangePageAction"/>">Change Transaction password</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div>
            <p><b><h2 align="center">!!! Change Your Login Password !!!</h2></b></p>
        </div>
        <div align="center">
            <s:actionerror/>
        </div>
        <div>
            <div align="justify" height="50" width="25">
                <p style="font-family:arial;color:red;font-size:20px;">The password policy is:<br/>
                    1.At least 8 chars<br/>
                    2.Contains at least one digit<br/>
                    3.Contains combination of number and char<br/>
                    4.Contains first character must be alphabets  <br/>
                    5.Not containing blank,tab etc.<br/>
                    6.Password should not be current or old password.<br/>
                </p>
            </div>

            <div style="text-align: center;">
                <table align="center">
                    <tr><td><p class="myprofiletd1"><b>Change Login Password</b></p></td></tr>
                    <br>
                    <tr><td><s:password name="oldLoginPassword" label="Old Password" id="oldLoginPassword" maxLength="10"/></td></tr>
                    <tr></tr>
                    <tr><td><s:password name="newLoginPassword" label="New Password" id="newLoginPassword" maxLength="15"/></td></tr>
                    <tr></tr>
                    <tr><td><s:password name="conformNewLoginPassword" label="Confirm Password" id="conformNewLoginPassword" maxLength="15"/></td></tr>  
                    <tr></tr>
                    <tr>
                        <td align="center"><s:submit align="center" theme="simple"/></td>
                        <td><a href="<s:url action="personalDetailLoginPasswordChangePageAction"/>">Back</a></td>
                    </tr>

                </table>
            </div>
        </div>
    </s:form>
</body>
<%@include file="/jsps/Footer.jsp" %>
</html>
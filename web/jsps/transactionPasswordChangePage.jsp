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
    <title>Change transaction Password Page</title>
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
            var oldTransactionPassword = $('#oldTransactionPassword').val();
            var flag = true;
            var regex = /^[A-Za-z()]+[0-9]+[A-za-z0-9()]+$/;
            if (oldTransactionPassword === "" || oldTransactionPassword.length === 0) {
                $("#errorpassword").remove();
                $("#oldTransactionPassword").parent().append('<span id="errorpassword">*Enter Password</span>');
                $("#oldTransactionPassword").focus();
                flag = false;
            } else if (oldTransactionPassword.length < 8 || oldTransactionPassword.length > 15) {
                $("#errorpassword").remove();
                $("#oldTransactionPassword").parent().append('<span id="errorpassword">*Password should be 8- 15 char</span>');
                $("#oldTransactionPassword").focus();
                flag = false;
            } else if (!regex.test(oldTransactionPassword)) {
                $("#errorpassword").remove();
                $("#oldTransactionPassword").parent().append('<span id="errorpassword">*Password contain alphanumeric char</span>');
                $("#oldTransactionPassword").focus();
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
            var oldTransactionPassword = $('#oldTransactionPassword').val();
            var newTransactionPassword = $('#newTransactionPassword').val();
            var flag = true;
            var regex = /^[A-Za-z()]+[0-9]+[A-za-z0-9()]+$/;
            if (newTransactionPassword === "" || newTransactionPassword.length === 0) {
                $("#errorpassword").remove();
                $("#newTransactionPassword").parent().append('<span id="errorpassword">*Enter Password</span>');
                $("#newTransactionPassword").focus();
                flag = false;
            } else if (newTransactionPassword.length < 8 || newTransactionPassword.length > 15) {
                $("#errorpassword").remove();
                $("#newTransactionPassword").parent().append('<span id="errorpassword">*Password should be 8- 15 char</span>');
                $("#newTransactionPassword").focus();
                flag = false;
            } else if (!regex.test(newTransactionPassword)) {
                $("#errorpassword").remove();
                $("#newTransactionPassword").parent().append('<span id="errorpassword">*Password contain alphanumeric  char</span>');
                $("#newTransactionPassword").focus();
                flag = false;
            } else if (newTransactionPassword == oldTransactionPassword) {
                $("#errorpassword").remove();
                $("#newTransactionPassword").parent().append('<span id="errorpassword">*Password should not match with current password</span>');
                $("#newTransactionPassword").focus();
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
            var conformNewTransactionPassword = $('#conformNewTransactionPassword').val();
            var newTransactionPassword = $('#newTransactionPassword').val();
            var flag = true;
            var regex = /^[A-Za-z()]+[0-9]+[A-za-z0-9()]+$/;
            if (conformNewTransactionPassword === "" || conformNewTransactionPassword.length === 0) {
                $("#errorpassword").remove();
                $("#conformNewTransactionPassword").parent().append('<span id="errorpassword">*Enter Password</span>');
                $("#conformNewTransactionPassword").focus();
                flag = false;
            } else if (conformNewTransactionPassword.length < 8 || conformNewTransactionPassword.length > 15) {
                $("#errorpassword").remove();
                $("#conformNewTransactionPassword").parent().append('<span id="errorpassword">*Password should be 8- 15 char</span>');
                $("#conformNewTransactionPassword").focus();
                flag = false;
            } else if (!regex.test(conformNewTransactionPassword)) {
                $("#errorpassword").remove();
                $("#conformNewTransactionPassword").parent().append('<span id="errorpassword">*Password contain alphanumeric character</span>');
                $("#conformNewTransactionPassword").focus();
                flag = false;
            } else if (newTransactionPassword !== conformNewTransactionPassword) {
                $("#errorpassword").remove();
                $("#conformNewTransactionPassword").parent().append('<span id="errorpassword">*Password is not matching</span>');
                $("#conformNewTransactionPassword").focus();
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
    <s:form action="TransactionPasswordChangePageAction" onsubmit="return validation();"> 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountTransactionPasswordChangePageAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferTransactionPasswordChangePageAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentTransactionPasswordChangePageAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailTransactionPasswordChangePageAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailTransactionPasswordChangePageAction"/>">Personal Detail</a></li> 
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
        <br/><br/>
        <div>
            <div class="wrapper row4">
                <div id="quicknav" class="clear">
                    <ul>
                        <li><a href="<s:url action="myProfileTransactionPasswordChangePageAction"/>">My Profile</a></li>                        
                        <li><a href="<s:url action="changeLoginPasswordTransactionPasswordChangePageAction"/>">Change Login password</a></li>
                        <li><a href="<s:url action="changeTransactionPasswordTransactionPasswordChangePageAction"/>">Change Transaction password</a></li>
                    </ul>
                </div>                   
            </div>
        </div>
        <br/>
        <div>
            <p><b><h2 align="center">!!! Change Your Transaction Password !!!</h2></b></p>
        </div>
        <br/>
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
        <div align="center">
            <p><s:actionerror/></p>
        </div>
        <div style="text-align: center;">
            <table align="center">     

                <tr><td><p class="myprofiletd1"><b>Change Transaction Password</b></p></td></tr>
                <br/>
                <tr><td><s:password name="oldTransactionPassword" label="Old Password" id="oldTransactionPassword" maxLength="15"/></td></tr>
                <tr><td><s:password name="newTransactionPassword" label="New Password" id="newTransactionPassword" maxLength="15"/></td></tr>
                <br/>
                <tr><td><s:password name="conformNewTransactionPassword" label="Confirm Password" id="conformNewTransactionPassword" maxLength="15"/></td></tr>   
                <tr><td><p></p></td></tr>
                <tr>
                    <td align="center"><s:submit align="center" theme="simple"/></td>
                    <td align="center"><a href="<s:url action="personalDetailTransactionPasswordChangePageAction"/>">Back</a></td>
                </tr>
            </table>
        </div>
    </s:form>
    <br/><br/>
</body>
<%@include file="/jsps/Footer.jsp" %>
</html>
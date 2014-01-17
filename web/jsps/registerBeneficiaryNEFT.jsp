<%-- 
    Document   : registerBenificiaryNEFT
    Created on : Oct 29, 2013, 12:50:16 PM
    Author     : Jay prakash
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
    <title>Register Beneficiary Page</title>
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
            var boolBeneficiaryName = beneficiaryNameValidation();
            var boolIFSCCodeValidation = IFSCCodeValidation();
            var boolBeneficiaryAccountNumberValidation = beneficiaryAccountNumberValidation();
            var boolReEnterAccuontNumberValidation = reEnterAccuontNumberValidation();
            if (boolBeneficiaryName && boolIFSCCodeValidation && boolBeneficiaryAccountNumberValidation && boolReEnterAccuontNumberValidation) {
                return true;
            }
            return flag;
        }
        
        function beneficiaryNameValidation() {
            var beneficiaryName = $('#beneficiaryName').val();
            var flag = true;
            var letters = /^[a-zA-Z]+[a-zA-Z/ /]+[a-zA-Z]+$/;
            if (beneficiaryName === "" || beneficiaryName.length === 0) {
                $("#errorbeneficiaryName").remove();
                $("#beneficiaryName").parent().append('<span id="errorbeneficiaryName">*Enter Name</span>');
                $("#beneficiaryName").focus();
                flag = false;
            } else if (!letters.test(beneficiaryName)) {
                $("#errorbeneficiaryName").remove();
                $("#beneficiaryName").parent().append('<span id="errorbeneficiaryName">*Invalid Name or space after or before  name</span>');
                $("#beneficiaryName").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorbeneficiaryName").remove();
                return flag;
            } else {
                return flag;
            }
        }
        function IFSCCodeValidation() {
            var IFSCCode = $('#IFSCCode').val();
            var flag = true;
            if (IFSCCode === "" || IFSCCode.length === 0) {
                $("#errorIFSCCode").remove();
                $("#IFSCCode").parent().append('<span id="errorIFSCCode">*Enter IFSC Code</span>');
                $("#IFSCCode").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorIFSCCode").remove();
                return flag;
            } else {
                return flag;
            }

        }
        function beneficiaryAccountNumberValidation() {
            var beneficiaryAccountNumber = $('#beneficiaryAccountNumber').val();
            var flag = true;
            if (beneficiaryAccountNumber === "" || beneficiaryAccountNumber.length === 0) {
                $("#errorbeneficiaryAccountNumber").remove();
                $("#beneficiaryAccountNumber").parent().append('<span id="errorbeneficiaryAccountNumber">*Enter account number</span>');
                $("#beneficiaryAccountNumber").focus();
                flag = false;
            } else if (beneficiaryAccountNumber == 0) {
                $("#errorbeneficiaryAccountNumber").remove();
                $("#beneficiaryAccountNumber").parent().append('<span id="errorbeneficiaryAccountNumber">*Account number should not be zero</span>');
                $("#beneficiaryAccountNumber").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorbeneficiaryAccountNumber").remove();
                return flag;
            } else {
                return flag;
            }
        }


        function reEnterAccuontNumberValidation() {
            var reEnterAccuontNumber = $('#reEnterAccuontNumber').val();
            var beneficiaryAccountNumber = $('#beneficiaryAccountNumber').val();
            var flag = true;
            if (reEnterAccuontNumber === "" || reEnterAccuontNumber.length === 0) {
                $("#errorreEnterAccuontNumber").remove();
                $("#reEnterAccuontNumber").parent().append('<span id="errorreEnterAccuontNumber">*Enter Account Number</span>');
                $("#reEnterAccuontNumber").focus();
                flag = false;
            } else if (reEnterAccuontNumber == 0) {
                $("#errorreEnterAccuontNumber").remove();
                $("#reEnterAccuontNumber").parent().append('<span id="errorreEnterAccuontNumber">*Account Number Should not be zero</span>');
                $("#reEnterAccuontNumber").focus();
                flag = false;
            } else if (beneficiaryAccountNumber !== reEnterAccuontNumber) {
                $("#errorreEnterAccuontNumber").remove();
                $("#reEnterAccuontNumber").parent().append('<span id="errorreEnterAccuontNumber">*Account Number is not matching</span>');
                $("#reEnterAccuontNumber").focus();
                flag = false;
            }
            if (flag == true) {
                $("#errorreEnterAccuontNumber").remove();
                return flag;
            } else {
                return flag;
            }
        }

        $(document).ready(function() {

            $('#beneficiaryAccountNumber').keydown(function(e) {
                if ((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 8) {
                    e.stopPropagation();
                    return false;
                }
            });
            $('#reEnterAccuontNumber').keydown(function(e) {
                if ((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 8) {
                    e.stopPropagation();
                    return false;
                }
            });
        });


    </script>
    <s:head/>
</head>
<body id="format" onload="window.history.forward(1);" >
    <%@include file="../header/Header.jsp" %>
    <s:form action="RegisterBeneficiaryNEFTAction" onsubmit="return validation();" > 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountRegisterBeneficiaryNEFTAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferRegisterBeneficiaryNEFTAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentRegisterBeneficiaryNEFTAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailRegisterBeneficiaryNEFTAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailRegisterBeneficiaryNEFTAction"/>">Personal Detail</a></li> 
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
                        <li><a href="<s:url action="registerBeneficaryRegisterBeneficiaryNEFTAction"/>">Register Beneficiary</a></li>                        
                        <li><a href="<s:url action="confirAndRejectBeneficiaryRegisterBeneficiaryNEFTAction"/>">Confirm/Reject Beneficiary</a></li>
                        <li><a href="<s:url action="newTransactionRegisterBeneficiaryNEFTAction"/>">New Transaction</a></li>
                        <li><a href="<s:url action="registeredBeneficiaryRegisterBeneficiaryNEFTAction"/>">Registered Beneficiary</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div align="center">
            <s:actionerror/>
        </div>
        <br><br>
        <div>
            <p><b><h2 align="center">!!! Add new beneficiary !!!</h2></b></p>
        </div>
        <table align="center">
            <s:hidden name="transactionType" value="NEFT"/>
            <tr>
                <td> <s:textfield name="beneficiaryName" label=" Beneficiary Name" id="beneficiaryName" maxLength="20"/></td>
            </tr>
            <tr/>
            <tr>
                <td> <s:select list="bankList" listValue="bankName" listKey="bankName" name="beneficiaryBankName" label="Bank Name" /></td>            
            </tr>
            <tr/>
            <tr>
                <td><s:textfield name="IFSCCode" label="NEFT IFSC Code" id="IFSCCode" maxLength="15"/></td>
            </tr>
            <tr/>
            <tr>
                <td><s:textfield name="beneficiaryAccountNumber" label="Account Number" id="beneficiaryAccountNumber" maxLength="20"/></td>
            </tr>
            <tr>
                <td><s:textfield name="reEnterAccuontNumber" label="Re Enter Account Number" id="reEnterAccuontNumber" maxLength="20"/></td>
            </tr>
            <tr/>
            <tr>
                <td><s:select name="beneficiaryAccountType" label="Account Type" list="{'SAVING','CURRENT'}"/></td>
            </tr>
            <tr/>
            <tr>
                <td align="center"><s:submit align="center" theme="simple" /></td>
                <td align="center"><a href="<s:url action="backRegisterBeneficiaryNEFTAction"/>">Back</a></td>
            </tr>
        </table>
        <br><br>
    </s:form>
</body>
<%@include file="/jsps/Footer.jsp" %>
</html>
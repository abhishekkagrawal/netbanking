<%-- 
    Document   : addBillpage
    Created on : Nov 4, 2013, 2:40:00 PM
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
    <title>Pay Bill Page</title>
    <link rel="stylesheet" href="../css/layout.css" type="text/css" />
    <link rel="stylesheet" href="../css/navi.css" type="text/css" />
    <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#electricityBill').hide();
            $('#bsnlCelloneBill').hide();
            $('#sbiLifeInsurenceBill').hide();
            $('#transactionDiv').hide();
            $('#SBITransactionDiv').hide();
            $('#electricityTransactionDiv').hide();
            $('#BSNLTransactionDiv').hide();
            $('#electricityBill').hide();


            $('#electricityBillAmount').keypress(function(event) {
                if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57) && event.keyCode != 8) {
                    event.preventDefault();
                }
            });

            $('#bsnlCelloneBillAmount').keypress(function(event) {
                if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57) && event.keyCode != 8) {
                    event.preventDefault();
                }
            });

            $('#sbiLifeInsurenceBillAmount').keypress(function(event) {
                if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57) && event.keyCode != 8) {
                    event.preventDefault();
                }
            });

            $('#electricityTransactionCancelButton').click(function() {
                $('#electricityBillPayButton').show();
                $('#electricityBillRemark').removeAttr('readOnly');
                $('#electricityTransactionDiv').hide();
            });

            $('#BSNLTransactionCancelButton').click(function() {
                $('#BSNLCellonePayButton').show();
                $('#bsnlCelloneBillRemark').removeAttr('readOnly');
                $('#BSNLTransactionDiv').hide();
            });

            $('#SBITransactionCancelButton').click(function() {
                $('#SBILifeInsurencePayButton').show();
                $('#sbiLifeInsurenceBillRemark').removeAttr('readOnly');
                $('#SBITransactionDiv').hide();
            });
        });

        function payElectricityBill(electricityBillId, nickName, billingUnit, processingCycle, consumerNumber, amount) {
            $('#electricityBillId').val(electricityBillId);
            $('#electricityBillNickName').val(nickName);
            $('#billingUnit').val(billingUnit);
            $('#processingCycle').val(processingCycle);
            $('#consumerNumber').val(consumerNumber);
            $('#electricityBillAmount').val(amount);
            $('#sbiLifeInsurenceBillRemark').val("");
            $('#sbiLifeInsurenceBillAmount').val("");
            $('#electricityBillRemark').val("");
            $('#bsnlCelloneBillAmount').val("");
            $('#bsnlCelloneBillRemark').val("");
            $('#electricityBill').show();
            $('#bsnlCelloneBill').hide();
            $('#sbiLifeInsurenceBill').hide();
            $('#SBITransactionDiv').hide();
            $('#electricityTransactionDiv').hide();
            $('#BSNLTransactionDiv').hide();

            $('#SBILifeInsurencePayButton').show();
            $('#sbiLifeInsurenceBillRemark').removeAttr('readOnly');
            $('#electricityBillPayButton').show();
            $('#electricityBillRemark').removeAttr('readOnly');
            $('#BSNLCellonePayButton').show();
            $('#bsnlCelloneBillRemark').removeAttr('readOnly');
        }
        function payBSNLCelloneBill(bsnlCelloneId, nickName, mobileNumber, amount) {
            $('#bsnlCelloneId').val(bsnlCelloneId);
            $('#bsnlCelloneBillNickName').val(nickName);
            $('#mobileNumber').val(mobileNumber);
            $('#bsnlCelloneBillAmount').val(amount);
            $('#sbiLifeInsurenceBillRemark').val("");
            $('#sbiLifeInsurenceBillAmount').val("");
            $('#electricityBillAmount').val("");
            $('#electricityBillRemark').val("");
            $('#bsnlCelloneBillRemark').val("");

            $('#electricityBill').hide();
            $('#bsnlCelloneBill').show();
            $('#sbiLifeInsurenceBill').hide();

            $('#SBITransactionDiv').hide();
            $('#electricityTransactionDiv').hide();
            $('#BSNLTransactionDiv').hide();

            $('#SBILifeInsurencePayButton').show();
            $('#sbiLifeInsurenceBillRemark').removeAttr('readOnly');
            $('#electricityBillPayButton').show();
            $('#electricityBillRemark').removeAttr('readOnly');
            $('#BSNLCellonePayButton').show();
            $('#bsnlCelloneBillRemark').removeAttr('readOnly');
        }
        function paySBILifeInsurenceBill(sbiLifeinsurenceId, nickName, policynumber, dobOfpolicyHolder, amount) {
            $('#sbiLifeinsurenceId').val(sbiLifeinsurenceId);
            $('#sbiLifeInsurenceBillNickName').val(nickName);
            $('#policynumber').val(policynumber);
            $('#dobOfpolicyHolder').val(dobOfpolicyHolder);
            $('#sbiLifeInsurenceBillAmount').val(amount);
            $('#sbiLifeInsurenceBillRemark').val("");
            $('#electricityBillAmount').val("");
            $('#electricityBillRemark').val("");
            $('#bsnlCelloneBillAmount').val("");
            $('#bsnlCelloneBillRemark').val("");

            $('#electricityBill').hide();
            $('#bsnlCelloneBill').hide();
            $('#sbiLifeInsurenceBill').show();

            $('#SBITransactionDiv').hide();
            $('#electricityTransactionDiv').hide();
            $('#BSNLTransactionDiv').hide();

            $('#SBILifeInsurencePayButton').show();
            $('#sbiLifeInsurenceBillRemark').removeAttr('readOnly');
            $('#electricityBillPayButton').show();
            $('#electricityBillRemark').removeAttr('readOnly');
            $('#BSNLCellonePayButton').show();
            $('#bsnlCelloneBillRemark').removeAttr('readOnly');
        }

        // .......................... validation.......................................//
        function electricityBillRemarkValidation() {
            var particulars = $('#electricityBillRemark').val();
            var flag = true;
            if (particulars === "" || particulars.length === 0) {
                $("#errorelectricityBillRemark").remove();
                $("#electricityBillRemark").parent().append('<span id="errorelectricityBillRemark">*Enter Remark</span>');
                $("#electricityBillRemark").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorelectricityBillRemark").remove();
                return flag;
            } else {
                return flag;
            }
        }


        function sbiLifeInsurenceBillRemarkValidation() {
            var particulars = $('#sbiLifeInsurenceBillRemark').val();
            var flag = true;
            if (particulars === "" || particulars.length === 0) {
                $("#errorsbiLifeInsurenceBillRemark").remove();
                $("#sbiLifeInsurenceBillRemark").parent().append('<span id="errorsbiLifeInsurenceBillRemark">*Enter Remark</span>');
                $("#sbiLifeInsurenceBillRemark").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorsbiLifeInsurenceBillRemark").remove();
                return flag;
            } else {
                return flag;
            }
        }


        function bsnlCelloneBillRemarkValidation() {
            var particulars = $('#bsnlCelloneBillRemark').val();
            var flag = true;
            if (particulars === "" || particulars.length === 0) {
                $("#errorbsnlCelloneBillRemark").remove();
                $("#bsnlCelloneBillRemark").parent().append('<span id="errorbsnlCelloneBillRemark">*Enter Remark</span>');
                $("#bsnlCelloneBillRemark").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorbsnlCelloneBillRemark").remove();
                return flag;
            } else {
                return flag;
            }
        }


        function electricityBillAmountValidation() {
            var amount = $('#electricityBillAmount').val();
            var regex = /^\d+(\.{0,1}\d{0,2})?$/;
            var flag = true;
            if (amount === "" || amount.length === 0) {
                $("#errorelectricityBillAmount").remove();
                $("#electricityBillAmount").parent().append('<span id="errorelectricityBillAmount">*Enter Amount</span>');
                $("#electricityBillAmount").focus();
                flag = false;
            } else if (!regex.test(amount)) {
                $("#errorelectricityBillAmount").remove();
                $("#electricityBillAmount").parent().append('<span id="errorelectricityBillAmount">*Only two digit are allow after decimal</span>');
                $("#electricityBillAmount").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorelectricityBillAmount").remove();
                return flag;
            } else {
                return flag;
            }
        }

        function bsnlCelloneBillAmountValidation() {
            var amount = $('#bsnlCelloneBillAmount').val();
            var regex = /^\d+(\.{0,1}\d{0,2})?$/;
            var flag = true;
            if (amount === "" || amount.length === 0) {
                $("#errorbsnlCelloneBillAmount").remove();
                $("#bsnlCelloneBillAmount").parent().append('<span id="errorbsnlCelloneBillAmount">*Enter Amount</span>');
                $("#bsnlCelloneBillAmount").focus();
                flag = false;
            } else if (!regex.test(amount)) {
                $("#errorbsnlCelloneBillAmount").remove();
                $("#bsnlCelloneBillAmount").parent().append('<span id="errorbsnlCelloneBillAmount">*Only two digit are allow after decimal</span>');
                $("#bsnlCelloneBillAmount").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorbsnlCelloneBillAmount").remove();
                return flag;
            } else {
                return flag;
            }
        }

        function sbiLifeInsurenceBillAmountValidation() {
            var amount = $('#sbiLifeInsurenceBillAmount').val();
            var flag = true;
            var regex = /^\d+(\.{0,1}\d{0,2})?$/;
            if (amount === "" || amount.length === 0) {
                $("#errorsbiLifeInsurenceBillAmount").remove();
                $("#sbiLifeInsurenceBillAmount").parent().append('<span id="errorsbiLifeInsurenceBillAmount">*Enter Amount</span>');
                $("#sbiLifeInsurenceBillAmount").focus();
                flag = false;
            } else if (!regex.test(amount)) {
                $("#errorsbiLifeInsurenceBillAmount").remove();
                $("#sbiLifeInsurenceBillAmount").parent().append('<span id="errorsbiLifeInsurenceBillAmount">*Only two digit are allow after decimal</span>');
                $("#sbiLifeInsurenceBillAmount").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorsbiLifeInsurenceBillAmount").remove();
                return flag;
            } else {
                return flag;
            }
        }

        function  electricityBillValidation() {
            var boolelectricityBillAmountValidation = electricityBillAmountValidation();
            var boolelectricityBillRemarkValidation = electricityBillRemarkValidation();
            if (boolelectricityBillAmountValidation && boolelectricityBillRemarkValidation) {
                $('#SBITransactionDiv').hide();
                $('#electricityTransactionDiv').show();
                $('#BSNLTransactionDiv').hide();
                $('#electricityBillPayButton').hide();
                $('#electricityBillRemark').attr('readOnly', 'true');
                return true;
            } else {
                return false;
            }
        }

        function  bsnlCelloneBillValidation() {
            var boolbsnlCelloneBillAmountValidation = bsnlCelloneBillAmountValidation();
            var boolbsnlCelloneBillRemarkValidation = bsnlCelloneBillRemarkValidation();
            if (boolbsnlCelloneBillAmountValidation && boolbsnlCelloneBillRemarkValidation) {
                $('#SBITransactionDiv').hide();
                $('#electricityTransactionDiv').hide();
                $('#BSNLTransactionDiv').show();
                $('#BSNLCellonePayButton').hide();
                $('#bsnlCelloneBillRemark').attr('readOnly', 'true');
                return true;
            } else {
                return false;
            }
        }

        function  sbiLifeInsurenceBillValidation() {
            var boolsbiLifeInsurenceBillAmountValidation = sbiLifeInsurenceBillAmountValidation();
            var boolsbiLifeInsurenceBillRemarkValidation = sbiLifeInsurenceBillRemarkValidation();
            if (boolsbiLifeInsurenceBillAmountValidation && boolsbiLifeInsurenceBillRemarkValidation) {
                $('#SBITransactionDiv').show();
                $('#electricityTransactionDiv').hide();
                $('#BSNLTransactionDiv').hide();
                $('#SBILifeInsurencePayButton').hide();
                $('#sbiLifeInsurenceBillRemark').attr('readOnly', 'true');
                return true;
            } else {
                return false;
            }
        }


        function  electricityBillUserIdValidation() {
            var userId = $('#electricityBillUserId').val();
            var flag = true;
            var sessionUserId = '<%=session.getAttribute("userId")%>';
            if (userId === "" || userId.length === 0) {
                $("#errorelectricityBillUserId").remove();
                $("#electricityBillUserId").parent().append('<span id="errorelectricityBillUserId">*Enter User Id</span>');
                $("#electricityBillUserId").focus();
                flag = false;
            } else if (sessionUserId !== userId) {
                $("#errorelectricityBillUserId").remove();
                $("#electricityBillUserId").parent().append('<span id="errorelectricityBillUserId">* Invalid user Id</span>');
                $("#electricityBillUserId").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorelectricityBillUserId").remove();
                return flag;
            } else {
                return flag;
            }
        }

        function electricityBillTransactionPasswordValidation() {

            var newTransactionPassword = $('#electricityBillTransactionPassword').val();
            var flag = true;
            var regex = /^[A-Za-z()]+[0-9]+[A-za-z0-9()]+$/;
            if (newTransactionPassword === "" || newTransactionPassword.length === 0) {
                $("#errorelectricityBillTransactionPassword").remove();
                $("#electricityBillTransactionPassword").parent().append('<span id="errorelectricityBillTransactionPassword">*Enter Password</span>');
                $("#electricityBillTransactionPassword").focus();
                flag = false;
            } else if (newTransactionPassword.length < 8 || newTransactionPassword.length > 15) {
                $("#errorelectricityBillTransactionPassword").remove();
                $("#electricityBillTransactionPassword").parent().append('<span id="errorelectricityBillTransactionPassword">*Password should be 8- 15 char</span>');
                $("#electricityBillTransactionPassword").focus();
                flag = false;
            } else if (!regex.test(newTransactionPassword)) {
                $("#errorelectricityBillTransactionPassword").remove();
                $("#electricityBillTransactionPassword").parent().append('<span id="errorelectricityBillTransactionPassword">*Password contain alphanumeric  char</span>');
                $("#electricityBillTransactionPassword").focus();
                flag = false;

            }
            if (flag == true) {
                $("#errorelectricityBillTransactionPassword").remove();
                return flag;
            } else {
                return flag;
            }
        }

        function  electricityBillTransactionValidation() {
            var booluserIdValidation = electricityBillUserIdValidation();
            var boolnewPasswordvalidation = electricityBillTransactionPasswordValidation();
            if (boolnewPasswordvalidation && booluserIdValidation) {
                return true;
            } else {
                return false;
            }
        }


        function  BSNLCelloneUserIdValidation() {
            var userId = $('#BSNLCelloneUserId').val();
            var flag = true;
            var sessionUserId = '<%=session.getAttribute("userId")%>';
            if (userId === "" || userId.length === 0) {
                $("#erroreBSNLCelloneUserId").remove();
                $("#BSNLCelloneUserId").parent().append('<span id="erroreBSNLCelloneUserId">*Enter User Id</span>');
                $("#BSNLCelloneUserId").focus();
                flag = false;
            } else if (sessionUserId !== userId) {
                $("#erroreBSNLCelloneUserId").remove();
                $("#BSNLCelloneUserId").parent().append('<span id="erroreBSNLCelloneUserId">* Invalid user Id</span>');
                $("#BSNLCelloneUserId").focus();
                flag = false;
            }
            if (flag === true) {
                $("#erroreBSNLCelloneUserId").remove();
                return flag;
            } else {
                return flag;
            }
        }

        function BSNLCelloneTransactionPasswordValidation() {

            var newTransactionPassword = $('#BSNLCelloneTransactionPassword').val();
            var flag = true;
            var regex = /^[A-Za-z()]+[0-9]+[A-za-z0-9()]+$/;
            if (newTransactionPassword === "" || newTransactionPassword.length === 0) {
                $("#errorBSNLCelloneTransactionPassword").remove();
                $("#BSNLCelloneTransactionPassword").parent().append('<span id="errorBSNLCelloneTransactionPassword">*Enter Password</span>');
                $("#BSNLCelloneTransactionPassword").focus();
                flag = false;
            } else if (newTransactionPassword.length < 8 || newTransactionPassword.length > 15) {
                $("#errorBSNLCelloneTransactionPassword").remove();
                $("#BSNLCelloneTransactionPassword").parent().append('<span id="errorBSNLCelloneTransactionPassword">*Password should be 8- 15 char</span>');
                $("#BSNLCelloneTransactionPassword").focus();
                flag = false;
            } else if (!regex.test(newTransactionPassword)) {
                $("#errorBSNLCelloneTransactionPassword").remove();
                $("#BSNLCelloneTransactionPassword").parent().append('<span id="errorBSNLCelloneTransactionPassword">*Password contain alphanumeric  char</span>');
                $("#BSNLCelloneTransactionPassword").focus();
                flag = false;

            }
            if (flag == true) {
                $("#errorBSNLCelloneTransactionPassword").remove();
                return flag;
            } else {
                return flag;
            }
        }

        function  BSNLCelloneTransactionValidation() {
            var booluserIdValidation = BSNLCelloneUserIdValidation();
            var boolnewPasswordvalidation = BSNLCelloneTransactionPasswordValidation();
            if (boolnewPasswordvalidation && booluserIdValidation) {
                return true;
            } else {
                return false;
            }
        }


        function  SBILifeUserIdValidation() {
            var userId = $('#SBILifeUserId').val();
            var flag = true;
            var sessionUserId = '<%=session.getAttribute("userId")%>';
            if (userId === "" || userId.length === 0) {
                $("#errorSBILifeUserId").remove();
                $("#SBILifeUserId").parent().append('<span id="errorSBILifeUserId">*Enter User Id</span>');
                $("#SBILifeUserId").focus();
                flag = false;
            } else if (sessionUserId !== userId) {
                $("#errorSBILifeUserId").remove();
                $("#SBILifeUserId").parent().append('<span id="errorSBILifeUserId">* Invalid user Id</span>');
                $("#SBILifeUserId").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorSBILifeUserId").remove();
                return flag;
            } else {
                return flag;
            }
        }

        function SBILifeTransactionPasswordValidation() {

            var newTransactionPassword = $('#SBILifeTransactionPassword').val();
            var flag = true;
            var regex = /^[A-Za-z()]+[0-9]+[A-za-z0-9()]+$/;
            if (newTransactionPassword === "" || newTransactionPassword.length === 0) {
                $("#errorSBILifeTransactionPassword").remove();
                $("#SBILifeTransactionPassword").parent().append('<span id="errorSBILifeTransactionPassword">*Enter Password</span>');
                $("#SBILifeTransactionPassword").focus();
                flag = false;
            } else if (newTransactionPassword.length < 8 || newTransactionPassword.length > 15) {
                $("#errorSBILifeTransactionPassword").remove();
                $("#SBILifeTransactionPassword").parent().append('<span id="errorSBILifeTransactionPassword">*Password should be 8- 15 char</span>');
                $("#SBILifeTransactionPassword").focus();
                flag = false;
            } else if (!regex.test(newTransactionPassword)) {
                $("#errorSBILifeTransactionPassword").remove();
                $("#SBILifeTransactionPassword").parent().append('<span id="errorSBILifeTransactionPassword">*Password contain alphanumeric  char</span>');
                $("#SBILifeTransactionPassword").focus();
                flag = false;

            }
            if (flag == true) {
                $("#errorSBILifeTransactionPassword").remove();
                return flag;
            } else {
                return flag;
            }
        }

        function  SBILifeInsurenceTransactionValidation() {
            var booluserIdValidation = SBILifeUserIdValidation();
            var boolnewPasswordvalidation = SBILifeTransactionPasswordValidation();
            if (boolnewPasswordvalidation && booluserIdValidation) {
                return true;
            } else {
                return false;
            }
        }


        //electricityBillUserId electricityBillTransactionPassword electricityBillTransactionValidation()
        //BSNLCelloneUserId BSNLCelloneTransactionPassword  electricityBillTransactionValidation()
        //SBILifeTransactionPassword  SBILifeUserId SBILifeInsurenceTransactionValidation()
        //electricityBillValidation bsnlCelloneBillValidation sbiLifeInsurenceBillValidation
        //electricityBillRemark sbiLifeInsurenceBillRemark bsnlCelloneBillRemark
        //sbiLifeInsurenceBillAmount bsnlCelloneBillAmount electricityBillAmount
    </script>
    <s:head/>
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
                        <li><a href="<s:url action="addBillPayBillPageAction"/>">Add</a></li>                        
                        <li><a href="<s:url action="modifyBillPayBillPageAction"/>">Modify</a></li>
                        <li><a href="<s:url action="payBillPayBillPageAction"/>">Pay Bill</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div align="center">
            <s:actionerror/>
        </div>
        <br/>
        <div>
            <p><b><h2 align="center">!!! Pay your bill !!!</h2></b></p>
        </div>
        <br/>
        <s:if  test="%{electricityBillList.size >0}">
            <div>           
                <p><h3>Electricity Bill Detail</h3></p>
            <table width="80%" cellspacing="0" cellpadding="0" border="1" align="center">
                <tbody>
                    <tr class="trListHeading" height="30px">
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Sl. no.</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Bill ID</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Name</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Billing Unit</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Processing Cycle</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Consumer Number</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>State Name</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Registration date and time</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Pay</b>
                        </td>
                    </tr>
                    <s:iterator value="electricityBillList" status="status" >
                        <tr class="tabledatatr2" height="25px">
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="%{#status.count}"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="electricityBillId"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"> <s:property value="nickName"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="billingUnit"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="processingCycle"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="consumerNumber"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="stateName"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="dateOfCreation"/></td>
                            <s:if test="%{ paidStatus== true  }">
                                <td>Paid</td>
                            </s:if>
                            <s:else>
                                <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><a href=# onclick='payElectricityBill("<s:property value="electricityBillId"/>", "<s:property value="nickName"/>", "<s:property value="billingUnit"/>", "<s:property value="processingCycle"/>", "<s:property value="consumerNumber"/>", "<s:property value="amount"/>");'>Pay</a></td>
                            </s:else>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
        <br/>
        <div id="electricityBill" align="center">
            <table >
                <tr>
                    <td><s:hidden name="electricityBillId" value="" id="electricityBillId"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="electricityBillName" label="Name" readonly="true" id="electricityBillNickName"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="billingUnit" label="Billing Unit" readonly="true" id="billingUnit"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="processingCycle" label="Processing Cycle" readonly="true" id="processingCycle"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="consumerNumber" label="Consumer Number" readonly="true" id="consumerNumber"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="electricityBillAmount" label="Amount" id="electricityBillAmount" readonly="true"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="electricityBillRemark" label="Remark" id="electricityBillRemark"/></td>
                </tr>
                <tr>
                    <td><input type="button" value="Pay" id="electricityBillPayButton" onclick="return electricityBillValidation();"/></td>
                </tr>
            </table>
        </div>
        <br/>
        <div id="electricityTransactionDiv" align="center">

            <table>
                <tr>
                    <td> <s:textfield name="electricityBillUserId" id="electricityBillUserId" label="Enter user id"/></td>
                </tr>
                <tr>
                    <td><s:password name="electricityBillTransactionPassword" label="Enter Transaction Password" id="electricityBillTransactionPassword" /></td>
                </tr>
                <tr>
                    <td>  
                        <s:submit id="submitId"  action="payElectricityBillPayBillPageAction" onclick="return electricityBillTransactionValidation()" theme="simple"/>               
                    </td>
                    <td>
                        <input type="button" value="Cancel" id="electricityTransactionCancelButton"/>
                    </td>
                </tr>
            </table>
        </div>
    </s:if>      
    <br/>
    <s:if  test="%{bsnlCelloneBillList.size >0}">
        <div>       
            <p><h3>BSNL Cellone Detail</h3></p>
        <table width="80%" cellspacing="0" cellpadding="0" border="1" align="center">
            <tbody>
                <tr class="trListHeading" height="30px">
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                        <b>Sl. no.</b>
                    </td>
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                        <b>Bill ID</b>
                    </td>
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                        <b>Name</b>
                    </td>
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                        <b>Mobile Number</b>
                    </td>
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                        <b>State Name</b>
                    </td>
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                        <b>Registration date and time</b>
                    </td>
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                        <b>Pay</b>
                    </td>
                </tr>
                <s:iterator value="bsnlCelloneBillList" status="status" >
                    <tr class="tabledatatr2" height="25px">
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="%{#status.count}"/></td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="bsnlCelloneId"/>  </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;"> <s:property value="nickName"/> </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="mobileNumber"/></td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="stateName"/></td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="dateOfCreation"/></td>
                        <s:if test="%{ paidStatus== true  }">
                            <td>Paid</td>
                        </s:if>
                        <s:else>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><a href=# onclick='payBSNLCelloneBill("<s:property value="bsnlCelloneId"/>", "<s:property value="nickName"/>", "<s:property value="mobileNumber"/>", "<s:property value="amount"/>");'>Pay</a></td>
                        </s:else>
                    </tr>
                </s:iterator>
            </tbody>
        </table>

    </div>
    <br/>
    <div id="bsnlCelloneBill" align="center">
        <table >
            <tr>
                <td><s:hidden name="bsnlCelloneBillId" value="" id="bsnlCelloneId"/></td>
            </tr>
            <tr>
                <td><s:textfield name="bsnlCelloneBillName" label="Name" id="bsnlCelloneBillNickName" readonly="true"/></td>
            </tr>
            <tr>
                <td><s:textfield name="mobileNumber" label="Mobile Number" id="mobileNumber" readonly="true"/></td>
            </tr>
            <tr>
                <td><s:textfield name="bsnlCelloneBillAmount" label="Amount" id="bsnlCelloneBillAmount" readonly="true"/></td>
            </tr>
            <tr>
                <td><s:textfield name="bsnlCelloneBillRemark" label="Remark" id="bsnlCelloneBillRemark"/></td>
            </tr>
            <tr>
                <td><input type="button" value="Pay" id="BSNLCellonePayButton" onclick="return bsnlCelloneBillValidation();"/></td>
            </tr>
        </table>
    </div>
    <br/>
    <div id="BSNLTransactionDiv" align="center">

        <table>
            <tr>
                <td> <s:textfield name="BSNLCelloneUserId" id="BSNLCelloneUserId" label="Enter user id"/></td>
            </tr>
            <tr>
                <td><s:password name="BSNLCelloneTransactionPassword" label="Enter Transaction Password" id="BSNLCelloneTransactionPassword" /></td>
            </tr>
            <tr>
                <td>  
                    <s:submit id="submitId"  action="payBSNLCelloneBillPayBillPageAction" onclick="return BSNLCelloneTransactionValidation()"/>               
                </td>
                <td>
                    <input type="button" value="Cancel" id="BSNLTransactionCancelButton"/>
                </td>
            </tr>
        </table>
    </div>
</s:if>
<br/>
<s:if  test="%{sbiLifeInsurenceList.size >0}">
    <div>
        <p><h3>SBI Life Insurence Bill Detail</h3></p>
    <table width="80%" cellspacing="0" cellpadding="0" border="1" align="center">
        <tbody>
            <tr class="trListHeading" height="30px">
                <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                    <b>Sl. no.</b>
                </td>
                <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                    <b>Bill ID</b>
                </td>
                <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                    <b>Name</b>
                </td>
                <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                    <b>Policy Number</b>
                </td>
                <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                    <b>Date of Birth</b>
                </td>
                <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                    <b>State Name</b>
                </td>
                <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                    <b>Registration date and time</b>
                </td>
                <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                    <b>Pay</b>
                </td>
            </tr>
            <s:iterator value="sbiLifeInsurenceList" status="status" >
                <tr class="tabledatatr2" height="25px">
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="%{#status.count}"/></td>
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="sbiLifeinsurenceId"/></td>
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;"> <s:property value="nickName"/> </td>
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="policynumber"/></td>
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="dobOfpolicyHolder"/></td>
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="stateName"/></td>
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="dateOfCreation"/></td>
                    <s:if test="%{ paidStatus== true  }">
                        <td>Paid</td>
                    </s:if>
                    <s:else>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><a href=# onclick='paySBILifeInsurenceBill("<s:property value="sbiLifeinsurenceId"/>", "<s:property value="nickName"/>", "<s:property value="policynumber"/>", "<s:property value="dobOfpolicyHolder"/>", "<s:property value="amount"/>");'>Pay</a></td>
                    </s:else>
                </tr>
            </s:iterator>
        </tbody>
    </table>
</div>
<br/>
<div id="sbiLifeInsurenceBill" align="center">
    <table >
        <tr>
            <td><s:hidden name="sbiLifeInsurenceBillId" value="" id="sbiLifeinsurenceId"/></td>
        </tr>
        <tr>
            <td><s:textfield name="sbiLifeInsurenceBillName" label="Name" id="sbiLifeInsurenceBillNickName" readonly="true"/></td>
        </tr>
        <tr>
            <td><s:textfield name="policyNumber" label="Policy Number" id="policynumber" readonly="true"/></td>
        </tr>
        <tr>
            <td><s:textfield name="dobOfPolicyHolder" label="Date of Birth" id="dobOfpolicyHolder" readonly="true"/></td>
        </tr>
        <tr>
            <td><s:textfield name="sbiLifeInsurenceBillAmount" label="Amount" id="sbiLifeInsurenceBillAmount" readonly="true"/></td>
        </tr>
        <tr>
            <td><s:textfield name="sbiLifeInsurenceBillRemark" label="Remark" id="sbiLifeInsurenceBillRemark"/></td>
        </tr>
        <tr>
            <td><input type="button" value="Pay" id="SBILifeInsurencePayButton" onclick="return sbiLifeInsurenceBillValidation();"/></td>
        </tr>
    </table>
</div>
<br/>
<div id="SBITransactionDiv" align="center">

    <table>
        <tr>
            <td> <s:textfield name="SBILifeUserId" id="SBILifeUserId" label="Enter user id" /></td>
        </tr>
        <tr>
            <td><s:password name="SBILifeTransactionPassword" label="Enter Transaction Password" id="SBILifeTransactionPassword" /></td>
        </tr>
        <tr>
            <td>  
                <s:submit id="submitId"  action="paySBILifeInsurenceBillPayBillPageAction" onclick="return SBILifeInsurenceTransactionValidation();"/>               
            </td>
            <td>
                <input type="button" value="Cancel" id="SBITransactionCancelButton"/>
            </td>
        </tr>
    </table>
</div>
</s:if>
<s:if test="%{electricityBillList.size <=0 && bsnlCelloneBillList.size <=0 && sbiLifeInsurenceList.size <=0}">
    <h2 align="center">No Bill is added</h2>
</s:if>
<br/>
</s:form>
</body>
<%@include file="/jsps/Footer.jsp" %>
</html>
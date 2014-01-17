<%-- 
    Document   : newTransactionNEFT
    Created on : Oct 29, 2013, 12:51:28 PM
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
    <title>New Transaction Page</title>
    <link rel="stylesheet" href="../css/layout.css" type="text/css" />
    <link rel="stylesheet" href="../css/navi.css" type="text/css" />
    <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>
    <script type="text/javascript">
        function  validation() {
            var flag = false;
            var boolAmountValidation = amountValidation();
            var boolRemarkValidation = remarkValidation();
            if (boolAmountValidation && boolRemarkValidation) {
                var transferTo = $('#transferTo option:selected').text();
                var transferArr = transferTo.split(" ");
                var amount = $('#amount').val();
                var debitAccountNumber = $('#accountDetail').val();
                var transferRemark = $('#remark').val();
                var arr = debitAccountNumber.split("-");
                $('#payeeName').text(transferArr[2]);
                $('#tblTransactionAmount').text(amount);
                $('#debitAccountNumber').text(transferArr[0]);
                $('#creditAccountNumber').text(arr[1]);
                $('#tblRemark').text(transferRemark);
                $('#firstDiv').hide();
                $('#tbl1').hide();
                $('#tbl2').show();
                $('#tbl3').show();
                $('#secondDiv').show();
                return true;
            }
            return flag;
        }
        function amountValidation() {
            var amount = $('#amount').val();
            var regex = /^\d+(\.{0,1}\d{0,2})?$/;
            var flag = true;
            if (amount === "" || amount.length === 0 || amount == 0) {
                $("#erroramount").remove();
                $("#amount").parent().append('<span id="erroramount">*Enter Amount</span>');
                $("#amount").focus();
                flag = false;
            } else if (amount > 200000) {
                $("#erroramount").remove();
                $("#amount").parent().append('<span id="erroramount">*Amount shold be less than 200000.</span>');
                $("#amount").focus();
                flag = false;
            } else if (!regex.test(amount)) {
                $("#erroramount").remove();
                $("#amount").parent().append('<span id="erroramount">*Only 2 digit after dot.</span>');
                $("#amount").focus();
                flag = false;
            }
            if (flag === true) {
                $("#erroramount").remove();
                return flag;
            } else {
                return flag;
            }
        }
        function remarkValidation() {
            var particulars = $('#remark').val();
            var flag = true;
            if (particulars === "" || particulars.length === 0) {
                $("#errorremark").remove();
                $("#remark").parent().append('<span id="errorremark">*Enter Remark</span>');
                $("#remark").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorremark").remove();
                return flag;
            } else {
                return flag;
            }
        }

        function  validationOnsubmit() {
            var booluserIdValidation = userIdValidation();
            var boolnewPasswordvalidation = newPasswordvalidation();
            if (boolnewPasswordvalidation && booluserIdValidation) {
                return true;
            } else {
                return false;
            }
        }

        function  userIdValidation() {
            var userId = $('#userId').val();
            var flag = true;
            var sessionUserId = '<%=session.getAttribute("userId")%>';
            if (userId === "" || userId.length === 0) {
                $("#erroruserId").remove();
                $("#userId").parent().append('<span id="erroruserId">*Enter User Id</span>');
                $("#userId").focus();
                flag = false;
            } else if (sessionUserId !== userId) {
                $("#erroruserId").remove();
                $("#userId").parent().append('<span id="erroruserId">* Invalid user Id</span>');
                $("#userId").focus();
                flag = false;
            }

            if (flag === true) {
                $("#erroruserId").remove();
                return flag;
            } else {
                return flag;
            }

        }

        function newPasswordvalidation() {

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

            }
            if (flag == true) {
                $("#errorpassword").remove();
                return flag;
            } else {
                return flag;
            }
        }

        $(document).ready(function() {
            $('#secondDiv').hide();
            $('#btn1').click(function() {
                $('#firstDiv').show();
                $('#tbl1').show();
                $('#tbl2').hide();
                $('#tbl3').hide();
                $('#secondDiv').hide();
            });

            $('#amount').keypress(function(event) {
                if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57) && event.keyCode != 8) {
                    event.preventDefault();
                }
            });
        });
    </script>    
    <s:head/>
</head>
<body id="format" onload="window.history.forward(1);" >
    <%@include file="../header/Header.jsp" %>
    <s:form action="NewTransactionNEFTAction" onsubmit="return validationOnsubmit();"> 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountNewTransactionNEFTAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferNewTransactionNEFTAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentNewTransactionNEFTAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailNewTransactionNEFTAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailNewTransactionNEFTAction"/>">Personal Detail</a></li> 
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
                        <li><a href="<s:url action="registerBeneficaryNewTransactionNEFTAction"/>">Register Beneficiary</a></li>                        
                        <li><a href="<s:url action="confirAndRejectBeneficiaryNewTransactionNEFTAction"/>">Confirm/Reject Beneficiary</a></li>
                        <li><a href="<s:url action="newTransactionNewTransactionNEFTAction"/>">New Transaction</a></li>
                        <li><a href="<s:url action="registeredBeneficiaryNewTransactionNEFTAction"/>">Registered Beneficiary</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <br/><br/>
        <div align="center">
            <s:actionerror/>
        </div>   
        <div>
            <p><b><h2 align="center">!!! Transfer your money !!!</h2></b></p>
        </div>
        <s:if  test="%{activeBeneficiaryList.size >0}">
            <div id="firstDiv">
                <table align='center' id="tbl1">
                    <tr>
                        <td><s:select label="Transfer To" name="transferAccount" list="activeBeneficiaryList" listKey="beneficiaryId" listValue="(beneficiaryAccountNumber)+'  '+(beneficiaryName)" id="transferTo" /></td>
                    </tr>                            
                    <tr>
                        <td><s:textfield name="transactionAmount"  label="Amount" id="amount"/></td>                                
                    </tr>
                    <tr>
                        <td><s:textfield name="accountNumber" value= "%{session.userInfo.name}-%{session.userInfo.accountNumber}" readonly="true" label="Debit Account" id="accountDetail"/></td>
                    </tr>
                    <tr>
                        <td><s:textfield name="particulars"  label="Transfer remark" id="remark"/></td>
                    </tr>
                    <tr>
                        <td><input type="button" id="btn" value="Submit" onclick="return  validation();"/></td>
                        <td><a href="<s:url action="transferNewTransactionNEFTAction"/>"><input type="button" value="Cancel"></a></td>
                    </tr>
                </table>
            </div>
            <br/>
            <div id="secondDiv" align='center'>
                <table border='1'  id="tbl2">
                    <tr>
                        <td align="center">Payee Name</td>
                        <td id="payeeName" align="center"></td>
                    </tr>
                    <tr>
                        <td align="center">Transaction Amount</td>
                        <td id="tblTransactionAmount" align="center"></td>
                    </tr>
                    <tr>
                        <td align="center">Debit Account Number</td>
                        <td id="debitAccountNumber" align="center"></td>
                    </tr>
                    <tr>
                        <td align="center">Credit Account Number</td>
                        <td id="creditAccountNumber" align="center"></td>
                    </tr>
                    <tr>
                        <td align="center">Remark</td>
                        <td id="tblRemark" align="center"></td>
                    </tr>
                </table>
                <br/>
                <br/>
                <div align='center'>
                    <table id="tbl3">
                        <tr>
                            <td><s:textfield name="userId" label="Enter User Id" id="userId" maxLength="10"/></td>
                        </tr>
                        <tr>
                            <td><s:password name="transactionPassword" label="Enter Your Transaction Password" id="newTransactionPassword" maxLength="15"/></td>
                        </tr>
                        <tr>
                            <td><s:submit value="Validate"/></td>
                            <td><input type="button" value="Back" id="btn1"/></td>
                        </tr>     
                    </table>
                </div>
            </div>
        </s:if>
        <s:else>
            <div align="cetner" >
                <h3><b>No beneficiary is added first add beneficiary </b></h3>
            </div>
        </s:else>
    </s:form>
    <br/>
</body>
<%@include file="/jsps/Footer.jsp" %>
</html>
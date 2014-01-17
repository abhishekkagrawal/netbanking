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
    <title>Credit Card detail Page</title>
    <link rel="stylesheet" href="../css/layout.css" type="text/css" />
    <link rel="stylesheet" href="../css/navi.css" type="text/css" />
    <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#showCreditDiv').hide();
            $('#transactionDiv').hide();

            $('#creditCartCancelButton').click(function() {
                $('#transactionDiv').hide();
                $('#remark').removeAttr('readOnly');
                $('#payButton').show();
            });


        });
        function  payCreditCardBill(cardId, nameOnCard, creditCardNumber, unbilledAmount) {
            $('#cardId').val(cardId);
            $('#nameOnCard').val(nameOnCard);
            $('#creditCardNumber').val(creditCardNumber);
            $('#unbilledAmount').val(unbilledAmount);

            $('#showCreditDiv').show();
            $('#transactionDiv').hide();
            $('#remark').removeAttr('readOnly');
            $('#remark').val("");
            $('#payButton').show();
        }
        function  creditCardBillValidation() {
            var boolremarkValidation = remarkValidation();
            if (boolremarkValidation) {
                $('#transactionDiv').show();
                $('#remark').attr('readOnly', 'true');
                $('#payButton').hide();
                return true;
            } else {
                return false;
            }
        }

        function remarkValidation() {
            var remark = $('#remark').val();
            var flag = true;
            var letters = /^[a-zA-Z]+[a-zA-Z/ /]+$/;
            if (remark === "" || remark.length === 0) {
                $("#errorRemark").remove();
                $("#remark").parent().append('<span id="errorRemark">*Enter Remark</span>');
                $("#remark").focus();
                flag = false;
            } else if (!letters.test(remark)) {
                $("#errorRemark").remove();
                $("#remark").parent().append('<span id="errorRemark">*Invalid Name or space after or before name</span>');
                $("#remark").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorRemark").remove();
                return flag;
            } else {
                return flag;
            }
        }


        function  userIdValidation() {
            var userId = $('#userId').val();
            var flag = true;
            var sessionUserId = '<%=session.getAttribute("userId")%>';
            if (userId === "" || userId.length === 0) {
                $("#errorUserId").remove();
                $("#userId").parent().append('<span id="errorUserId">*Enter User Id</span>');
                $("#userId").focus();
                flag = false;
            } else if (sessionUserId !== userId) {
                $("#errorUserId").remove();
                $("#userId").parent().append('<span id="errorUserId">* Invalid user Id</span>');
                $("#userId").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorUserId").remove();
                return flag;
            } else {
                return flag;
            }
        }

        function transactionPasswordValidation() {

            var transactionPassword = $('#transactionPassword').val();
            var flag = true;
            var regex = /^[A-Za-z()]+[0-9]+[A-za-z0-9()]+$/;
            if (transactionPassword === "" || transactionPassword.length === 0) {
                $("#errorTransactionPassword").remove();
                $("#transactionPassword").parent().append('<span id="errorTransactionPassword">*Enter Password</span>');
                $("#transactionPassword").focus();
                flag = false;
            } else if (transactionPassword.length < 8 || transactionPassword.length > 15) {
                $("#errorTransactionPassword").remove();
                $("#transactionPassword").parent().append('<span id="errorTransactionPassword">*Password should be 8- 15 char</span>');
                $("#transactionPassword").focus();
                flag = false;
            } else if (!regex.test(transactionPassword)) {
                $("#errorTransactionPassword").remove();
                $("#transactionPassword").parent().append('<span id="errorTransactionPassword">*Password contain alphanumeric  char</span>');
                $("#transactionPassword").focus();
                flag = false;

            }
            if (flag == true) {
                $("#errorTransactionPassword").remove();
                return flag;
            } else {
                return flag;
            }
        }

        function  transactionValidation() {
            var booluserIdValidation = userIdValidation();
            var booltransactionPasswordValidation = transactionPasswordValidation();
            if (booluserIdValidation && booltransactionPasswordValidation) {
                return true;
            } else {
                return false;
            }
        }
    </script>
    <s:head/>
</head>
<body id="format" onload="window.history.forward(1);" >
    <%@include file="../header/Header.jsp" %>
    <s:form action="CardDetailPageAction"> 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountCardDetailPageAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferCardDetailPageAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentCardDetailPageAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailCardDetailPageAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailCardDetailPageAction"/>">Personal Detail</a></li> 
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
                        <li><a href="<s:url action="cardDetailCardDetailPageAction"/>">Card Detail</a></li>                        
                        <li><a href="<s:url action="applyCardCardDetailPageAction"/>">Apply Card</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div align="center">
            <s:actionerror/>
        </div>
        <br/>
        <div>
            <p><b><h2 align="center">!!! See credit card information !!!</h2></b></p>
        </div>
        <br/>
        <s:if  test="%{creditCardDetailList.size >0}">
            <div align="center">            
                <p><h3>Credit Card Detail</h3></p>
            <table width="80%" cellspacing="0" cellpadding="0" border="1" align="center">
                <tbody>
                    <tr class="trListHeading" height="30px">
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Sl. no.</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Credit Card Number</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Name</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Card Type</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Available Credit</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Unbilled Amount</b>
                        </td> 
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Max Limit</b>
                        </td>                       
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Registration date and time</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Pay</b>
                        </td>
                    </tr>
                    <s:iterator value="creditCardDetailList" status="status" >
                        <tr class="tabledatatr2" height="25px">
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="%{#status.count}"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="creditCardNumber"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="nameOnCard"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="cardTypeName"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="%{maxLimit-unbilledAmount}" /></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="unbilledAmount"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="maxLimit"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="dateOfCreation"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><a href=# onclick='payCreditCardBill("<s:property value="cardId"/>", "<s:property value="nameOnCard"/>", "<s:property value="creditCardNumber"/>", "<s:property value="unbilledAmount"/>");'>Pay</a></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
        <br/><br/>
        <div id="showCreditDiv" align="center">
            <table >
                <tr>
                    <td><s:hidden name="cardId" value="" id="cardId"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="nameOnCard" label="Name" id="nameOnCard" readonly="true"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="creditCardNumber" label="Card Number" id="creditCardNumber" readonly="true"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="unbilledAmount" label="Amount" id="unbilledAmount" readonly="true"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="remark" label="Remark" id="remark" /></td>
                </tr>
                <tr>
                    <td><input type="button" value="Pay" id="payButton" onclick="return creditCardBillValidation();"/></td>
                </tr>
            </table>
        </div>
        <br/><br/>
        <div id="transactionDiv" align="center">

            <table>
                <tr>
                    <td> <s:textfield name="userId" id="userId" label="Enter user id" maxLength="10"/></td>
                </tr>
                <tr>
                    <td><s:password name="transactionPassword" label="Enter Transaction Password" id="transactionPassword" maxLength="15"/></td>
                </tr>
                <tr>
                    <td>  
                        <s:submit id="submitId"  onclick="return transactionValidation()" theme="simple"/>               
                    </td>
                    <td>
                        <input type="button" value="Cancel" id="creditCartCancelButton"/>
                    </td>
                </tr>
            </table>
        </div>
    </s:if>
    <s:else>
        <p align="center"><h3><b>No Credit Card is added</b></h3></p>
     </s:else>
<br/>
</s:form>
</body>
<%@include file="/jsps/Footer.jsp" %>
</html>
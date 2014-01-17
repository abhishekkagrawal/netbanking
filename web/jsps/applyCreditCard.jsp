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
    <title>Apply credit Card Page</title>
    <link rel="stylesheet" href="../css/layout.css" type="text/css" />
    <link rel="stylesheet" href="../css/navi.css" type="text/css" />
    <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>
    <script type="text/javascript">
        function validation() {
            var boolValidateName = validateName();
            if (boolValidateName) {
                return true;
            } else {
                return false;
            }
        }
        function validateName() {
            var nameOnCard = $('#nameOnCard').val();
            var flag = true;
            var letters = /^[a-zA-Z]+[a-zA-Z/ /]+[a-zA-Z]+$/;
            if (nameOnCard === "" || nameOnCard.length === 0) {
                $("#errornameOnCard").remove();
                $("#nameOnCard").parent().append('<span id="errornameOnCard">*Enter Name</span>');
                $("#nameOnCard").focus();
                flag = false;
            } else if (!letters.test(nameOnCard)) {
                $("#errornameOnCard").remove();
                $("#nameOnCard").parent().append('<span id="errornameOnCard">*Invalid Name or space after or before name</span>');
                $("#nameOnCard").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errornameOnCard").remove();
                return flag;
            } else {
                return flag;
            }
        }


        function getMaxLimit() {
            // duplicate servicename validation.......
            // get the form values
            var result = true;
            var cardName = $('#cardName').val();
            if (cardName != "") {
                $.ajax({
                    type: "POST",
                    url: "getMaxLimit",
                    data: "cardName=" + cardName,
                    success: function(response) {
                        // we have the response
                        $('#maxLimit').val(response.toString());
                        result = false;
                    },
                    error: function(e) {
                        alert('Error: ' + e);
                        result = false;
                    }
                });
                return result;
            }
        }//end of doAjaxPost() function...

    </script>
    <s:head/>
</head>
<body id="format" onload="window.history.forward(1);" >
    <%@include file="../header/Header.jsp" %>
    <s:form action="ApplyCreditCardAction"> 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountApplyCreditCardAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferApplyCreditCardAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentApplyCreditCardAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailApplyCreditCardAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailApplyCreditCardAction"/>">Personal Detail</a></li> 
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
                        <li><a href="<s:url action="cardDetailApplyCreditCardAction"/>">Card Detail</a></li>                        
                        <li><a href="<s:url action="applyCardApplyCreditCardAction"/>">Apply Card</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <br/><br/>
        <div align="center">
            <s:actionerror/>
        </div>
        <div>
            <p><b><h2 align="center">!!! Apply new credit card online !!!</h2></b></p>
        </div>
        <s:if test="#session.userInfo.noOfCardIssue > 0">
            <div align="center">
                <table align="center" >
                    <tr></tr>
                    <tr>
                        <td><s:select list="cardTypeList" name="cardTpe" label="Select Card Type" listKey="creditCardId" listValue="cardTypeName" id="cardName" onchange="getMaxLimit();"/></td>                    
                    </tr>
                    <tr><td></td></tr>
                    <tr>
                        <td><s:textfield name="maxLimit" label="Max Limit" id="maxLimit" readonly="true"/></td>                    
                    </tr>
                    <tr><td></td></tr>
                    <tr>
                        <td><s:textfield name="nameOnCard" label="Name On Card" id="nameOnCard" maxLength="15"/></td>                    
                    </tr>
                    <tr><td></td></tr>
                    <tr>
                        <td align="right"><s:submit value="Apply" theme="simple" onclick="return validation();"/></td>                    
                    </tr>
                </table>
            </div>
        </s:if>
        <s:else>
            <h2 align="center">You have Issued maximum no card</h2>
        </s:else>
        <br/>
    </s:form>
</body>
<%@include file="/jsps/Footer.jsp" %>
</html>
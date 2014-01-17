<%-- 
    Document   : registeredBenificiary
    Created on : Oct 29, 2013, 12:52:13 PM
    Author     : Jay Prakash
    Ended on   :
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
    <title>Registered Beneficiary Page</title>
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
        function deleteBeneficiary(beneficiaryId1, accountNumber1, id) {
            var check = confirm("Are Confirm To Delete Beneficiary ?");
            var result = false;
            if (check == true) {
                var beneficiaryId = beneficiaryId1;
                var accountNumber = accountNumber1;
                //alert(planName);
                if (beneficiaryId != "") {
                    $.ajax({
                        type: "POST",
                        url: "deleteBeneficiary",
                        data: {
                            'beneficiaryId': beneficiaryId,
                            'accountNumber': accountNumber
                        },
                        success: function(response) {
                            // we have the response
                            $(id).parent().hide();
                            $(id).parent().parent().hide();
                        },
                        error: function(e) {
                            alert('Error aaya hai: ' + e);
                        }
                    });
                }
                result = true;
            }//end of if block...
            return result;
        }//end of deleteMembershipPlan() function...                
    </script>
</head>
<body id="format"  onload="window.history.forward(1);" >
    <%@include file="../header/Header.jsp" %>
    <s:form action="RegisteredBeneficiaryNEFTAction" > 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountRegisteredBeneficiaryNEFTAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferRegisteredBeneficiaryNEFTAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentRegisteredBeneficiaryNEFTAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailRegisteredBeneficiaryNEFTAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailRegisteredBeneficiaryNEFTAction"/>">Personal Detail</a></li> 
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
                        <li><a href="<s:url action="registerBeneficaryRegisteredBeneficiaryNEFTAction"/>">Register Beneficiary</a></li>                        
                        <li><a href="<s:url action="confirAndRejectBeneficiaryRegisteredBeneficiaryNEFTAction"/>">Confirm/Reject Beneficiary</a></li>
                        <li><a href="<s:url action="newTransactionRegisteredBeneficiaryNEFTAction"/>">New Transaction</a></li>
                        <li><a href="<s:url action="registeredBeneficiaryRegisteredBeneficiaryNEFTAction"/>">Registered Beneficiary</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div>
            <p><b><h2 align="center">!!! Registered Beneficiary !!!</h2></b></p>
        </div>
        <br/>
        <s:if  test="%{activeBeneficiaryList.size >0}">
            <table width="80%" cellspacing="0" cellpadding="0" border="1" align="center">
                <tbody>
                    <tr class="trListHeading" height="30px">
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Sl. no.</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Beneficiary ID</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Beneficiary nick name</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Account number</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Registration date and time</b>
                        </td>
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                            <b>Delete</b>
                        </td>
                    </tr>
                    <s:iterator value="activeBeneficiaryList" status="status" >
                        <tr class="tabledatatr2" height="25px">
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="%{#status.count}"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="beneficiaryId"/>  </td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"> <s:property value="beneficiaryName"/> </td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="beneficiaryAccountNumber"/>   </td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="dateOfCreation"/></td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><input type="button" value="Delete" id="Reject" onclick='deleteBeneficiary("<s:property value="beneficiaryId"/>", "<s:property value="#session.userInfo.accountNumber"/>", this)'/></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:else>
            <div align="cetner" >
                <h3><b>No record is present</b></h3>
            </div>
        </s:else>
    </s:form>
    <br/><br/>
</body>
<%@include file="/jsps/Footer.jsp" %>
</html>
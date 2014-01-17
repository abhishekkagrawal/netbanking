<%-- 
    Document   : confirmAndRejectBenificiaryNEFT
    Created on : Oct 29, 2013, 12:51:07 PM
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
    <title>Confirm/Delete beneficiary Page</title>
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
        $(document).ready(function() {
            $('#textFieldHide').hide();
            $('#confDiv').hide();
            $('#back').click(function() {
                $('#confDiv').hide();
                $('#showtblDiv').show();
                $('#tbl1').show();
            });
            $('#URNNumber').keydown(function(e) {
                if ((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 8) {
                    e.stopPropagation();
                    return false;
                }
            });
        });
        function showBeneficiary(beneficiaryId, beneficiaryName, beneficiaryAccountNumber, dateOfCreation) {
            $('#beneficiaryId').text(beneficiaryId);
            $('#beneficiaryName').text(beneficiaryName);
            $('#beneficiaryAccountNumber').text(beneficiaryAccountNumber);
            $('#dateOfCreation').text(dateOfCreation);
            $('#beneficiaryId1').val(beneficiaryId);
            $('#showtblDiv').hide();
            $('#tbl1').hide();
            $('#confDiv').show();
        }
        function deleteBeneficiary(beneficiaryId1, accountNumber1, id) {
            var check = confirm("Are Confirm To Reject Beneficiary ?");
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
    <s:head/>
</head>
<body id="format" onload="window.history.forward(1);" >
    <%@include file="../header/Header.jsp" %>
    <s:form action="ConfirmAndRejectBeneficiaryNEFTAction" > 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountConfirmAndRejectBeneficiaryNEFTAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferConfirmAndRejectBeneficiaryNEFTAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentConfirmAndRejectBeneficiaryNEFTAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailConfirmAndRejectBeneficiaryNEFTAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailConfirmAndRejectBeneficiaryNEFTAction"/>">Personal Detail</a></li> 
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
                        <li><a href="<s:url action="registerBeneficaryConfirmAndRejectBeneficiaryNEFTAction"/>">Register Beneficiary</a></li>                        
                        <li><a href="<s:url action="confirAndRejectBeneficiaryConfirmAndRejectBeneficiaryNEFTAction"/>">Confirm/Reject Beneficiary</a></li>
                        <li><a href="<s:url action="newTransactionConfirmAndRejectBeneficiaryNEFTAction"/>">New Transaction</a></li>
                        <li><a href="<s:url action="registeredBeneficiaryConfirmAndRejectBeneficiaryNEFTAction"/>">Registered Beneficiary</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div>
            <p><b><h2 align="center">!!! Confirm and delete your beneficiary !!!</h2></b></p>
        </div>
        <div align="center">
            <s:actionerror/>
        </div>
        <s:if  test="%{deactiveBeneficiaryList.size >0}">
            <div id="showtblDiv" align='center'>

                <table width="80%" cellspacing="0" cellpadding="0" border="1" align="center" id="tbl1">
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
                                <b>Validate</b>
                            </td>
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;">
                                <b>Reject</b>
                            </td>
                        </tr>
                        <s:iterator value="deactiveBeneficiaryList" status="status" >
                            <tr class="tabledatatr2" height="25px">
                                <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="%{#status.count}"/></td>
                                <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="beneficiaryId"/></td>
                                <td style="border:1px solid #8c8c8c;border-collapse:collapse;"> <s:property value="beneficiaryName"/></td>
                                <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="beneficiaryAccountNumber"/></td>
                                <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><s:property value="dateOfCreation"/></td>
                                <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><input type="button" value="Validate" id="validate" onclick="showBeneficiary('<s:property value="beneficiaryId"/>', '<s:property value="beneficiaryName"/>', '<s:property value="beneficiaryAccountNumber"/>', '<s:property value="dateOfCreation"/>')"/></td>
                                <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><input type="button" value="Delete" id="delete" onclick='deleteBeneficiary("<s:property value="beneficiaryId"/>", "<s:property value="#session.userInfo.accountNumber"/>", this)'/></td>
                            </tr>                           
                        </s:iterator>
                        <tr>
                            <td><a href="<s:url action="transferConfirmAndRejectBeneficiaryNEFTAction" />" align="center">Cancel</a></td>
                        </tr>
                    </tbody>
                </table>  


            </div>
            <div id="textFieldHide">
                <s:textfield name="beneficiaryId" value="" id="beneficiaryId1"/>               
            </div>

            <div id="confDiv">
                <table align="center" border="1">
                    <tr>
                        <td>Beneficiary Account Id</td>
                        <td id="beneficiaryId"></td>
                    </tr>
                    <tr>
                        <td>Beneficiary nick name</td>
                        <td id="beneficiaryName"></td>
                    </tr>
                    <tr>
                        <td>Beneficiary Amount Number</td>
                        <td  id="beneficiaryAccountNumber"></td>
                    </tr>
                    <tr>
                        <td>Registration Date</td>
                        <td id="dateOfCreation"></td>
                    </tr>
                    <br>
                </table>

                <div align='center'>
                    <br>
                    <br>
                    <s:textfield name="URNNumber" id="URNNumber" label="Enter URN Number" maxLength="8"/>
                    <br>
                    <br>
                    <s:submit value="Confirm Beneficiary" align="center"/>
                    <br>
                    <input type="button" value="Back" id="back"/>
                </div>
            </div>
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
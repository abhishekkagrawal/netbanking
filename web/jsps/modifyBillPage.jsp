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
//            onload="window.history.forward(1);"
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Modify Bill Page</title>
    <link rel="stylesheet" href="../css/layout.css" type="text/css" />
    <link rel="stylesheet" href="../css/navi.css" type="text/css" />
    <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>
    <script type="text/javascript">
        function deleteElectricityBilldetail(electricitybillId1, accountNumber1, id) {
            var check = confirm("Are Confirm To Delete Bill ?");
            var result = false;
            if (check == true) {
                var electricitybillId = electricitybillId1;
                var accountNumber = accountNumber1;
                //alert(planName);
                if (electricitybillId != "") {
                    $.ajax({
                        type: "POST",
                        url: "deleteElectricityBilldetail",
                        data: {
                            'electricitybillId': electricitybillId,
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

        function deleteBSNLCelloneDetail(bsnlCelloneId1, accountNumber1, id) {
            var check = confirm("Are Confirm To Delete Bill ?");
            var result = false;
            if (check == true) {
                var bsnlCelloneId = bsnlCelloneId1;
                var accountNumber = accountNumber1;
                //alert(planName);
                if (bsnlCelloneId != "") {
                    $.ajax({
                        type: "POST",
                        url: "deleteBSNLCelloneDetail",
                        data: {
                            'bsnlCelloneId': bsnlCelloneId,
                            'accountNumber': accountNumber
                        },
                        success: function(response) {
                            // we have the response
                            $(id).parent().hide();
                            $(id).parent().parent().hide();
                        },
                        error: function(e) {
                           // alert('Error aaya hai: ' + e);
                        }
                    });
                }
                result = true;
            }//end of if block...
            return result;
        }//end of deleteMembershipPlan() function...             

        function deleteSBILifeInsurenceDetail(sbiLifeInsurenceId1, accountNumber1, id) {
            var check = confirm("Are Confirm To Delete Bill ?");
            var result = false;
            if (check == true) {
                var sbiLifeInsurenceId = sbiLifeInsurenceId1;
                var accountNumber = accountNumber1;
                //alert(planName);
                if (sbiLifeInsurenceId != "") {
                    $.ajax({
                        type: "POST",
                        url: "deleteSBILifeInsurenceDetail",
                        data: {
                            'sbiLifeInsurenceId': sbiLifeInsurenceId,
                            'accountNumber': accountNumber
                        },
                        success: function(response) {
                            // we have the response
                            $(id).parent().hide();
                            $(id).parent().parent().hide();
                        },
                        error: function(e) {
                           // alert('Error aaya hai: ' + e);
                        }
                    });
                }
                result = true;
            }//end of if block...
            return result;
        }//end of deleteMembershipPlan() function...             
    </script>
</head>
<body id="format" onload="window.history.forward(1);" >
    <%@include file="../header/Header.jsp" %>
    <s:form action="ModifyBillPageAction" onsubmit="return validationOnsubmit();"> 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountModifyBillPageAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferModifyBillPageAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentModifyBillPageAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailModifyBillPageAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailModifyBillPageAction"/>">Personal Detail</a></li> 
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
        <br/>
        <div>
            <div class="wrapper row4">
                <div id="quicknav" class="clear">
                    <ul>
                        <li><a href="<s:url action="addBillModifyBillPageAction"/>">Add</a></li>                        
                        <li><a href="<s:url action="modifyBillModifyBillPageAction"/>">Modify</a></li>
                        <li><a href="<s:url action="payBillModifyBillPageAction"/>">Pay Bill</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div align="center">
            <s:actionerror/>
        </div> 
        <br/>
        <div>
            <p><b><h2 align="center">!!! Modify your bill !!!</h2></b></p>
        </div>
        <br/>
        <div>
            <s:if  test="%{electricityBillList.size >0}">
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
                            <b>Delete</b>
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
                            <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><a href=# onclick='deleteElectricityBilldetail("<s:property value="electricityBillId"/>", "<s:property value="#session.userInfo.accountNumber"/>", this);'>Delete</a></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>           
    </div>
    <br/>
    <br/>
    <div>
        <s:if  test="%{bsnlCelloneBillList.size >0}">
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
                        <b>Delete</b>
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
                        <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><a href=# onclick='deleteBSNLCelloneDetail("<s:property value="bsnlCelloneId"/>", "<s:property value="#session.userInfo.accountNumber"/>", this);'>Delete</a></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </s:if>
</div>
<br/>
<br/>
<div>
    <s:if  test="%{sbiLifeInsurenceList.size >0}">
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
                    <b>Delete</b>
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
                    <td style="border:1px solid #8c8c8c;border-collapse:collapse;"><a href=# onclick='deleteSBILifeInsurenceDetail("<s:property value="sbiLifeinsurenceId"/>", "<s:property value="#session.userInfo.accountNumber"/>", this);'>Delete</a></td>
                </tr>
            </s:iterator>
        </tbody>
    </table>
</s:if>
<s:if test="%{electricityBillList.size <=0 && bsnlCelloneBillList.size <=0 && sbiLifeInsurenceList.size <=0}">
    <h2 align="center">No Bill is added</h2>
</s:if>
</div>
<br/>
<br/>   
</s:form>
</body>
<%@include file="/jsps/Footer.jsp" %>
</html>
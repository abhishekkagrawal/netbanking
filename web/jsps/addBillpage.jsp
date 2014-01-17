<%-- 
    Document   : addBillpage
    Created on : Nov 4, 2013, 2:40:00 PM
    Author     : Jay Prakash
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
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
    <title>Add Bill Page</title>
    <link rel="stylesheet" href="../css/layout.css" type="text/css" />
    <link rel="stylesheet" href="../css/navi.css" type="text/css" />
    <!--    <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>-->
    <script type="text/javascript" src="../javascript/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="../javascript/jquery-ui.js"></script>
    <script type="text/css" src="../css/jquery-ui.css"></script>
    <sx:head/>
    <script  type="text/javascript">
        $(function() {
            $("#dateOfBirth").datepicker({
                changeMonth: true,
                changeYear: true,
                maxDate: "0D",
                minDate: "-720M"
            });
        });
        $(document).ready(function() {
            $('#dateOfBirth').val($.datepicker.formatDate('mm/dd/yy', new Date()));
            $('#electricity').hide();
            $('#bsnlCellone').hide();
            $('#sbiLifeInsurence').hide();
            $('#btn').click(function() {

                var biller = $('#biller').val();
                if (biller == 1) {
                    $('#electricity').hide();
                    $('#bsnlCellone').show();
                    $('#sbiLifeInsurence').hide();
                }
                if (biller == 2) {
                    $('#electricity').show();
                    $('#bsnlCellone').hide();
                    $('#sbiLifeInsurence').hide();
                }
                if (biller == 3) {
                    $('#electricity').hide();
                    $('#bsnlCellone').hide();
                    $('#sbiLifeInsurence').show();
                }
            });
            $('#state').change(function() {
                $("#errorelectricityNickName").remove();
                $("#errorelectricityBillingUnit").remove();
                $("#errorelectricityProcessingCycle").remove();
                $("#errorelectricityConsumerNumber").remove();
                $("#errorbsnlCelloneNickName").remove();
                $("#errorbsnlCelloneMobileNumber").remove();
                $("#errorsbiLifeInsurenceNickName").remove();
                $("#errorsbiLifeInsurencePolicyNumber").remove();
                $('#electricity').hide();
                $('#bsnlCellone').hide();
                $('#sbiLifeInsurence').hide();
            });
            $('#biller').change(function() {
                $("#errorelectricityNickName").remove();
                $("#errorelectricityBillingUnit").remove();
                $("#errorelectricityProcessingCycle").remove();
                $("#errorelectricityConsumerNumber").remove();
                $("#errorbsnlCelloneNickName").remove();
                $("#errorbsnlCelloneMobileNumber").remove();
                $("#errorsbiLifeInsurenceNickName").remove();
                $("#errorsbiLifeInsurencePolicyNumber").remove();
                $('#electricity').hide();
                $('#bsnlCellone').hide();
                $('#sbiLifeInsurence').hide();
            });

            $('#electricityBillingUnit').keydown(function(e) {
                if ((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 8) {
                    e.stopPropagation();
                    return false;
                }
            });
            $('#electricityProcessingCycle').keydown(function(e) {
                if ((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 8) {
                    e.stopPropagation();
                    return false;
                }
            });

            $('#electricityConsumerNumber').keydown(function(e) {
                if ((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 8) {
                    e.stopPropagation();
                    return false;
                }
            });
            $('#bsnlCelloneMobileNumber').keydown(function(e) {
                if ((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 8) {
                    e.stopPropagation();
                    return false;
                }
            });
            $('#sbiLifeInsurencePolicyNumber').keydown(function(e) {
                if ((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 8) {
                    e.stopPropagation();
                    return false;
                }
            });

        });

        function electricityNickName() {
            var electricityNickName = $('#electricityNickName').val();
            var flag = true;
            var letters = /^[a-zA-Z]+[a-zA-Z/ /]+[a-zA-Z]+$/;
            if (electricityNickName === "" || electricityNickName.length === 0) {
                $("#errorelectricityNickName").remove();
                $("#electricityNickName").parent().append('<span id="errorelectricityNickName">*Enter Name</span>');
                $("#electricityNickName").focus();
                flag = false;
            } else if (!letters.test(electricityNickName)) {
                $("#errorelectricityNickName").remove();
                $("#electricityNickName").parent().append('<span id="errorelectricityNickName">*Invalid Name or space after or before  name</span>');
                $("#electricityNickName").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorelectricityNickName").remove();
                return flag;
            } else {
                return flag;
            }
        }

        function  electricityBillingUnit() {
            var electricityBillingUnit = $('#electricityBillingUnit').val();
            var flag = true;
            // validation for username..     
            if (electricityBillingUnit === "" || electricityBillingUnit.length === 0) {
                $("#errorelectricityBillingUnit").remove();
                $("#electricityBillingUnit").parent().append('<span id="errorelectricityBillingUnit">*Enter mobile number</span>');
                $("#electricityBillingUnit").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorelectricityBillingUnit").remove();
                return flag;
            } else {
                return flag;
            }

        }
        function electricityProcessingCycle() {
            var electricityProcessingCycle = $('#electricityProcessingCycle').val();
            var flag = true;
            // validation for username..     
            if (electricityProcessingCycle === "" || electricityProcessingCycle.length === 0) {
                $("#errorelectricityProcessingCycle").remove();
                $("#electricityProcessingCycle").parent().append('<span id="errorelectricityProcessingCycle">*Enter mobile number</span>');
                $("#electricityProcessingCycle").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorelectricityProcessingCycle").remove();
                return flag;
            } else {
                return flag;
            }

        }
        function electricityConsumerNumber() {
            var electricityConsumerNumber = $('#electricityConsumerNumber').val();
            var flag = true;
            // validation for username..     
            if (electricityConsumerNumber === "" || electricityConsumerNumber.length === 0) {
                $("#errorelectricityConsumerNumber").remove();
                $("#electricityConsumerNumber").parent().append('<span id="errorelectricityConsumerNumber">*Enter mobile number</span>');
                $("#electricityConsumerNumber").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorelectricityConsumerNumber").remove();
                return flag;
            } else {
                return flag;
            }

        }


        function electricityValidation() {
            var boolElectricityNickName = electricityNickName();
            var boolElectricityBillingUnit = electricityBillingUnit();
            var boolElectricityProcessingCycle = electricityProcessingCycle();
            var boolElectricityConsumerNumber = electricityConsumerNumber();
            if (boolElectricityNickName && boolElectricityBillingUnit && boolElectricityProcessingCycle && boolElectricityConsumerNumber) {
                return true;
            } else {
                return false;
            }
        }

        function bsnlCelloneNickName() {
            var bsnlCelloneNickName = $('#bsnlCelloneNickName').val();
            var flag = true;
            var letters = /^[a-zA-Z]+[a-zA-Z/ /]+[a-zA-Z]+$/;
            if (bsnlCelloneNickName === "" || bsnlCelloneNickName.length === 0) {
                $("#errorbsnlCelloneNickName").remove();
                $("#bsnlCelloneNickName").parent().append('<span id="errorbsnlCelloneNickName">*Enter Name</span>');
                $("#bsnlCelloneNickName").focus();
                flag = false;
            } else if (!letters.test(bsnlCelloneNickName)) {
                $("#errorbsnlCelloneNickName").remove();
                $("#bsnlCelloneNickName").parent().append('<span id="errorbsnlCelloneNickName">*Invalid Name or space after or before  name</span>');
                $("#bsnlCelloneNickName").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorbsnlCelloneNickName").remove();
                return flag;
            } else {
                return flag;
            }
        }
        function bsnlCelloneMobileNumber() {
            var bsnlCelloneMobileNumber = $('#bsnlCelloneMobileNumber').val();
            var no = /[1-9]+\d{9}$/;
            var flag = true;
            // validation for username..     
            if (bsnlCelloneMobileNumber === "" || bsnlCelloneMobileNumber.length === 0) {
                $("#errorbsnlCelloneMobileNumber").remove();
                $("#bsnlCelloneMobileNumber").parent().append('<span id="errorbsnlCelloneMobileNumber">*Enter mobile number</span>');
                $("#bsnlCelloneMobileNumber").focus();
                flag = false;
            } else if (bsnlCelloneMobileNumber.length !== 10) {
                $("#errorbsnlCelloneMobileNumber").remove();
                $("#bsnlCelloneMobileNumber").parent().append('<span id="errorbsnlCelloneMobileNumber">*Invalid mobile No.(enter 10 digit only)</span>');
                $('#bsnlCelloneMobileNumber').focus();
                flag = false;
            }else if (!no.test(bsnlCelloneMobileNumber)) {
                $("#errorbsnlCelloneMobileNumber").remove();
                $("#bsnlCelloneMobileNumber").parent().append('<span id="errorbsnlCelloneMobileNumber">*Invalid mobile No.</span>');
                $('#bsnlCelloneMobileNumber').focus();
                flag = false;
            }//end of if block..
            if (flag === true) {
                $("#errorbsnlCelloneMobileNumber").remove();
                return flag;
            } else {
                return flag;
            }
        }


        function bsnlCelloneValidation() {
            var boolBSNLCelloneMobileNumber = bsnlCelloneMobileNumber();
            var boolBSNLCelloneNickName = bsnlCelloneNickName();

            if (boolBSNLCelloneMobileNumber && boolBSNLCelloneNickName) {
                return true;
            } else {
                return false;
            }
        }

        function sbiLifeInsurenceNickName() {
            var sbiLifeInsurenceNickName = $('#sbiLifeInsurenceNickName').val();
            var flag = true;
            var letters = /^[a-zA-Z]+[a-zA-Z/ /]+[a-zA-Z]+$/;
            if (sbiLifeInsurenceNickName === "" || sbiLifeInsurenceNickName.length === 0) {
                $("#errorsbiLifeInsurenceNickName").remove();
                $("#sbiLifeInsurenceNickName").parent().append('<span id="errorsbiLifeInsurenceNickName">*Enter Name</span>');
                $("#sbiLifeInsurenceNickName").focus();
                flag = false;
            } else if (!letters.test(sbiLifeInsurenceNickName)) {
                $("#errorsbiLifeInsurenceNickName").remove();
                $("#sbiLifeInsurenceNickName").parent().append('<span id="errorsbiLifeInsurenceNickName">*Invalid Name or space after or before  name</span>');
                $("#sbiLifeInsurenceNickName").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorsbiLifeInsurenceNickName").remove();
                return flag;
            } else {
                return flag;
            }
        }
        function sbiLifeInsurencePolicyNumber() {
            var sbiLifeInsurencePolicyNumber = $('#sbiLifeInsurencePolicyNumber').val();
            var flag = true;
            // validation for username..     
            if (sbiLifeInsurencePolicyNumber === "" || sbiLifeInsurencePolicyNumber.length === 0) {
                $("#errorsbiLifeInsurencePolicyNumber").remove();
                $("#sbiLifeInsurencePolicyNumber").parent().append('<span id="errorsbiLifeInsurencePolicyNumber">*Enter mobile number</span>');
                $("#sbiLifeInsurencePolicyNumber").focus();
                flag = false;
            }
            if (flag === true) {
                $("#errorsbiLifeInsurencePolicyNumber").remove();
                return flag;
            } else {
                return flag;
            }

        }


        function sbiLifeInsurenceValidation() {
            var boolSBILifeInsurenceNickName = sbiLifeInsurenceNickName();
            var boolSBILifeInsurencePolicyNumber = sbiLifeInsurencePolicyNumber();

            if (boolSBILifeInsurenceNickName && boolSBILifeInsurencePolicyNumber) {
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
    <s:form action="AddBillPageAction"> 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountAddBillPageAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferAddBillPageAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentAddBillPageAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailAddBillPageAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailAddBillPageAction"/>">Personal Detail</a></li> 
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
                        <li><a href="<s:url action="addBillAddBillPageAction"/>">Add</a></li>                        
                        <li><a href="<s:url action="modifyBillAddBillPageAction"/>">Modify</a></li>
                        <li><a href="<s:url action="payBillAddBillPageAction"/>">Pay Bill</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <br/>
        <div>
            <p><b><h2 align="center">!!! Add new bill !!!</h2></b></p>
        </div>
        <br/>
        <div align="center">
            <s:actionerror/>
        </div>
        <div id="firstDiv">
            <table align='center' id="tbl1">
                <tr>
                    <td><s:select label="City/State" name="state" list="stateList" listKey="stateId" listValue="stateName" id="state" /></td>
                </tr>                            
                <tr>
                    <td><s:select label="Biller Name" name="biller" list="billerList" listKey="billerId" listValue="billerName" id="biller" /></td>
                </tr> 
                <tr>
                    <td align="right"><input type="button" id="btn" value="Go" /></td>
                </tr>
            </table>
        </div>
        <br/>
        <div align="center" id="electricity">
            <p>Electricity Bill</p>
            <table>
                <tr>
                    <td><s:textfield name="electricityNickName" label="Nick Name" id="electricityNickName"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="billingUnit" label="Billing Unit" id="electricityBillingUnit"/></td>
                </tr>
                <tr>
                    <td> <s:textfield name="processingCycle" label="Processing Cycle" id="electricityProcessingCycle"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="consumerNumber" label="Consumer Number" id="electricityConsumerNumber"/></td>
                </tr>
                <tr>
                    <td>Selected Account Number::</td>
                    <td><s:property value="#session.userInfo.accountNumber"/></td>
                </tr>
                <tr>
                    <td><s:submit action="electricityAddBillPageAction" align="center" onclick="return electricityValidation();"/></td>
                </tr>
            </table>
        </div>
        <div align="center" id="bsnlCellone">
            <p>BSNL Cellone</p>
            <table>
                <tr>
                    <td><s:textfield name="bsnlCelloneNickName" label="Nick Name" id="bsnlCelloneNickName"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="mobilenumber" label="Mobile Number" id="bsnlCelloneMobileNumber" maxLength="10"/></td>
                </tr>
                <tr>
                    <td>Selected Account Number:</td>
                    <td><s:property value="#session.userInfo.accountNumber"/></td>
                </tr>
                <tr>
                    <td><s:submit action="bsnlCelloneAddBillPageAction" align="center" onclick="return bsnlCelloneValidation();"/></td>
                </tr>
            </table>
        </div>
        <div align="center" id="sbiLifeInsurence">
            <p>SBI Life Insurence</p>
            <table>
                <tr>
                    <td><s:textfield name="sbiLifeInsurenceNickName" label="Nick Name" id="sbiLifeInsurenceNickName" /></td>
                </tr>
                <tr>
                    <td><s:textfield name="policyNumber" label="Policy Number" id="sbiLifeInsurencePolicyNumber"/></td>
                </tr>
                <tr>
                    <td><s:textfield name="dateOfBirth" id="dateOfBirth" label="Date of birth of policy holder"  readonly="true" /></td>
                </tr>
                <tr>
                    <td>Selected Account Number:</td>
                    <td><s:property value="#session.userInfo.accountNumber"/></td>
                </tr>
                <tr>
                    <td><s:submit action="sbiLifeInsurenceAddBillPageAction" align="center" onclick="return sbiLifeInsurenceValidation();"/></td>
                </tr>

            </table>
        </div>
    </s:form>
    <br/>
</body>
<%@include file="/jsps/Footer.jsp" %>
</html>
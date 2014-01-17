<%--
    Document   : newRegistration
    Created on : Oct 14, 2013, 5:12:02 PM
    Author     : Jay Prakash
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" href="../css/jquery-ui.css" />
        <script src="../javascript/jquery-1.9.1.js"></script>
        <script src="../javascript/jquery-ui.js"></script>
        <script type="text/javascript" src="../javascript/allValidation.js"></script>
        <title>New User Page</title>
        <s:head/>
        <sx:head/>
        <s:head/>
    </head>
    <body onload="window.history.forward(1);">
        <%@include file="../header/Header.jsp" %>
        <div align='center' style="background-image:url(../header/images/bg.jpg)">
            <div>
                <p>After submit form check your email for user Id and password</p>
            </div>
            <div>
                <s:actionerror/>
            </div>
            <s:form action="NewRegistrationPageAction" onsubmit="return newRegistrationValidateForm()">
                <table>
                    <tr>
                        <td><s:textfield name="accountNumber"  label="Enter Account Number" id="newRegistrationAccountNumber" onblur="return newRegistrationAccountNumberValidation()"/></td>
                    </tr>
                    <tr>
                        <td><s:textfield name="mobileNumber" label="Enter Registered Mobile Number " id="newRegistrationMobile" onblur="return newRegistrationMobileValidation()" maxLength="10"/></td>
                    </tr>
                    <tr>
                        <td><s:textfield name="date" id="newRegistrationDateOfBirth" label="Enter date of Birth"  readonly="true" /></td>
                    </tr>
                    <tr>
                        <td><s:textfield name="panCardNumber" label="Enter PAN card number" id="newRegistrationPanCardNumber" onblur="return newRegistrationPanCardNumberValidation()"/></td>
                    </tr>
                    <tr>
                        <td  style="font-style: italic" align="right">Select login type</td>
                        <td>
                            <input id="radioButtonView" checked="true"   style="font-style: italic" type="radio" value="View" name="loginType">View
                            <input id="radioButtonView And transaction"  style="font-style: italic" type="radio" value="View And transaction" name="loginType">View And transaction

                        </td>
                    </tr>
                    <tr>
                        <td align="right"><s:submit action="regitserUSerNewRegistrationPageAction"  theme="simple" /></td>
                        <td align="center"><a href="<s:url action="backNewRegistrationPageAction"/>">BACK</a></td>
                    </tr>
                </table>
            </s:form>
            <div>
                <p>Please note while using New user facility </p>
                <p>The facility is available only for self operated savings, current and overdraft account customers.</p>
                <p> The facility is not available for joint account holders.</p>
                <p>Only customers who are registered for SMS Alerts facility with our Bank can avail this facility.</p>
                <p>Only limited number of attempts per day would be available to the customers who want to avail this facility.</p>
                <p>After successful registration the user would be activated within 24-48 hrs.</p>
                <p>You will not be able to successfully complete the process if the desired details provided by you are incorrect/incomplete.</p>
                <p>If wrong credentials are entered 3 times, the user will be blocked and will not be able to use this facility.</p>
                <p>You can also request for internet banking facility by speaking to our 24 hour Customer Care or by contacting a branch</p>
            </div>
        </div>

    </body>
    <%@include file="/jsps/Footer.jsp" %>
</html>

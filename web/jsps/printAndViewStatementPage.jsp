<%-- 
    Document   : miniStatementPage
    Created on : Oct 22, 2013, 06:25:33 PM
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
%>
<head>
    <s:token name="token" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View/Save Page</title>
    <link rel="stylesheet" href="../css/layout.css" type="text/css" />
    <link rel="stylesheet" href="../css/navi.css" type="text/css" />
    <!-- Featured Slider Elements -->
    <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>
    <script type="text/javascript" src="../javascript/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="../javascript/jquery-ui.js"></script>
    <script type="text/css" src="../css/jquery-ui.css"></script>
    <script type="text/javascript" src="../javascript/allValidation.js"></script>
    <sx:head/>
    <script type="text/javascript">
    </script>
</head>
<body id="format" onload="window.history.forward(1);">
    <%@include file="../header/Header.jsp" %>
    <s:form action="ViewAndSavePageAction" > 
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="myAccountViewAndSavePageAction"/>">My Account</a></li>                        
                    <li><a href="<s:url action="transferViewAndSavePageAction"/>">Transfer</a></li>
                    <li><a href="<s:url action="billPaymentViewAndSavePageAction"/>">Bill Payment</a></li>
                    <li><a href="<s:url action="cardDetailViewAndSavePageAction"/>">Cards Details</a></li>
                    <li><a href="<s:url action="personalDetailViewAndSavePageAction"/>">Personal Detail</a></li> 
                    <li><a href="<s:url action="LogoutAction"/>">Logout</a></li>
                </ul>
            </div>
        </div>
        <div class="wrapper row4">
            <div id="quicknav" class="clear">
                <ul>
                    <li><a href="<s:url action="miniStatementViewAndSavePageAction"/>">Mini Statement</a></li>                        
                    <li><a href="">View/print Statement</a></li>
                    <li><a href="<s:url action="accountDetailViewAndSavePageAction"/>">Account Detail</a></li>
                </ul>
            </div>
        </div>
        <p>Account Number <s:property value="#session.userInfo.accountNumber"/></p>
        <br/><br/>
        <div>
            <table>
                <tr><td>Option 1</td></tr>
                <tr><td>Show me: <a href="<s:url action="oneMonthTransactionDetailViewAndSavePageAction"/>">Last one month transaction</a></td>
                    <td>Show me: <a href="twoMonthTransactionDetailViewAndSavePageAction">Last two month transaction</a></td></tr>            
                <tr></tr><tr></tr><tr></tr><tr></tr>                
            </table>          
        </div>
        <br/>
        <div>
            <p>Option 2</p>
            <p>Show me:  <s:textfield name="noOfTransaction" label="Last" id="last" maxLength="5"/> transactions</p>
        </div>
        <br/>
        <div>
            <p>Option 3</p>
            <p>Select query method</p>
            <label class="label" for="ViewAndSavePageAction_sortBy">Sort transaction by date in:</label>
            <input id="ViewAndSavePageAction_sortByAscending order" type="radio" value="Ascending order" name="sortBy" checked="true">
            <label for="ViewAndSavePageAction_sortByAscending order">Ascending order</label>
            <input id="ViewAndSavePageAction_sortByDescending order" type="radio" value="Descending order" name="sortBy">
            <label for="ViewAndSavePageAction_sortByDescending order">Descending order</label>
            <br/>
            <br/><s:textfield name="startDate" id="startDate" label="From date"  readonly="true" />
            <s:textfield name="endDate" id="endDate" label="To date" readonly="true"/>
        </div>
        <br/>
        <s:submit  value="Statement" align="center" onclick="return printAndViewStatementPageValidation();"/>
    </s:form>
</body>
<br/><br/>
<%@include file="/jsps/Footer.jsp" %>
</html>
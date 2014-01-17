<%-- 
    Document   : showTransactionDetailPage
    Created on : Oct 24, 2013, 2:54:39 PM
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
    <s:token name="token"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mini Statement Page</title>
    <link rel="stylesheet" href="../css/layout.css" type="text/css" />
    <link rel="stylesheet" href="../css/navi.css" type="text/css" />
    <!-- Featured Slider Elements -->
    <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>    

    <script type="text/javascript">
        $(document).ready(function() {
            $('#secondDiv').hide();
            $('#show').click(function(event) {
                $('#table2').show();
                $('#secondDiv').hide();
                $('#btnDiv').show();
            });
        });
    </script>
    <script type="text/javascript">
        function valueFiller(transactionDate, transactionAmount, particulars, transactionType) {
            $('#transactionDate').text(transactionDate);
            $('#transactionAmount').text(transactionAmount);
            $('#particulars').text(particulars);
            $('#transactionType').text(transactionType);
            $('#table2').hide();
            $('#secondDiv').show();
            $('#btnDiv').hide();
        }
       function showPath(){
           alert("Path of saved file: d:/TransactionDetail.pdf ");
       }
    </script>
</head>
<body id="format" onload="window.history.forward(1);">
    <%@include file="../header/Header.jsp" %>
    <div>
        <s:form action="ShowTransactionDetailPageAction" > 
            <div class="wrapper row4">
                <div id="quicknav" class="clear">
                    <ul>
                        <li><a href="<s:url action="myAccountShowTransactionDetailPageAction"/>">My Account</a></li>                        
                        <li><a href="<s:url action="transferShowTransactionDetailPageAction"/>">Transfer</a></li>
                        <li><a href="<s:url action="billPaymentShowTransactionDetailPageAction"/>">Bill Payment</a></li>
                        <li><a href="<s:url action="cardDetailShowTransactionDetailPageAction"/>">Cards Details</a></li>
                        <li><a href="<s:url action="personalDetailShowTransactionDetailPageAction"/>">Personal Detail</a></li> 
                        <li><a href="<s:url action="LogoutAction"/>">Logout</a></li>
                    </ul>
                </div>
            </div>
            <br><br>
            <div>
                <div>         
                    <p style="float: left"> Hi,  <s:property value="#session.userInfo.name"/></p>
                    <p align="right" style="float: right"><b align="right">Last Logged In</b> <s:property value="#session.loginInfo.lastLogin"/></p>
                </div>
                <div>
                    <% SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
                        String date = ft.format(new Date());
                    %>
                    <p><b>Account Summary</b> <%=date%></p>               
                </div>
                <div>
                    <div class="wrapper row4">
                        <div id="quicknav" class="clear">
                            <ul>
                                <li><a href="<s:url action="miniStatementShowTransactionDetailPageAction"/>">Mini Statement</a></li>                        
                                <li><a href="<s:url action="viewaAndSaveStatementShowTransactionDetailPageAction"/>">View/Save Statement</a></li>
                                <li><a href="<s:url action="accountDetailShowTransactionDetailPageAction"/>">Account Detail</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <s:if  test="%{transactionDeatailList.size >0}">
                    <div id="firstDiv">

                        <p>Account Number <s:property value="#session.userInfo.accountNumber"/></p>
                        <p>Transaction Made till <%=date%></p>
                        <table border="1" align="center" id="table2">
                            <tr>
                                <th>S.No.</th>
                                <th>Date</th>
                                <th>Particulars</th>
                                <th>Withdrawal(Dr)</th>
                                <th>Deposit(Cr)</th>
                            </tr>  
                            <s:iterator value="transactionDeatailList" var="transactionDetail" status="status">
                                <tr>     
                                    <s:hidden value="#transactionDetail.transactionType"/>                      
                                    <td><s:property value="%{#status.count}" /> <br /></td>
                                    <td><s:property value="#transactionDetail.dateOfCreation"/></td>
                                    <td><a href="#"  id="getDetail" onclick='valueFiller("<s:property value="#transactionDetail.transactionDate"/>", "<s:property value="#transactionDetail.transactionAmount"/>", "<s:property value="#transactionDetail.particulars"/>", "<s:property value="#transactionDetail.transactionType"/>")'><s:property value="#transactionDetail.particulars"/></a></td>
                                        <s:if test="#transactionDetail.transactionType=='CR'">
                                        <td>---</td>
                                        <td><s:property value="#transactionDetail.transactionAmount"/></td>
                                    </s:if>
                                    <s:else>
                                        <td><s:property value="#transactionDetail.transactionAmount"/></td>
                                        <td>---</td>
                                    </s:else>
                                </tr>                          
                            </s:iterator> 
                        </table>
                        <br><br>
                        <div align="center" id="btnDiv">
                            <s:submit action="saveShowTransactionDetailPageAction" value="Save" theme="simple" onclick="showPath()"/>
                            <s:submit action="backShowTransactionDetailPageAction" value="Back"  theme="simple"/>
                        </div>                  
                    </div>
                    <div id="secondDiv">                 
                        <h3><p align="center"><b>Details of transaction</b></p></h3>
                        <table align="center" border="1">
                            <tr>
                                <td>Account Id</td>
                                <td><s:property value="#session.userInfo.accountNumber"/></td>
                            </tr>
                            <tr>
                                <td>Transaction Date</td>
                                <td id="transactionDate"></td>
                            </tr>
                            <tr>
                                <td>Transaction Amount</td>
                                <td  id="transactionAmount"></td>
                            </tr>
                            <tr>
                                <td>Transaction Type</td>
                                <td id="transactionType"></td>
                            </tr>
                            <tr>
                                <td>Transaction Particular</td>
                                <td id="particulars"></td>
                            </tr>
                            <tr>
                                <td><input type="button" id="show" value="Back to statement"/>   </td>
                            </tr>
                        </table>  
                    </div>
                </s:if>
                <s:else>
                    <p><b><h3 align="center">No Transaction Detail is found</h3></b></p>
                </s:else>
            </div>
        </s:form>
        <br><br>
        </body>
        <%@include file="/jsps/Footer.jsp" %>
        </html>

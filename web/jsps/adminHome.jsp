<%-- 
    Document   : Admin
    Created on : Oct 12, 2013, 1:31:27 PM
    Author     : Chetan Patil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@include file="../header/Header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
         
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="../css/layout.css" type="text/css" />
        <link rel="stylesheet" href="../css/navi.css" type="text/css" />
        <!-- Featured Slider Elements -->
        <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>
        <script type="text/javascript" src="../javascript/jquery-s3slider.js"></script>
        <script type="text/javascript" src="../javascript/jquery-s3slider.setup.js"></script>

    </head>
    <body id="format">

        <s:form action="AdminAction" > 
            <div class="wrapper row4">
                <div id="quicknav" class="clear">
                    <ul>
                        <li><a href="#">My Account</a>
                            <ul>
                                <li><a href="<s:url action="studentAdminAction"/>">Account Summary</a></li>
                                
                            </ul>                    
                        </li>                        
                        <li><a href="#">Transfer</a>
                            <ul>
                                <li><a href="<s:url action="studentAdminAction"/>" >NEFT Transfer</a></li>
                                <li><a href="<s:url action="studentAdminAction"/>" >RTGS Transfer</a></li>
                            </ul>    
                        </li>
                        <li><a href="#">Bill Payment</a></li>
                        <li><a href="#">Cards Details</a></li>
                        <li><a href="#">Funds</a></li>
                        <li><a href="#">Personal Detail</a>
                            <ul>
                                <li><a href="<s:url action="studentAdminAction"/>" > View Details</a></li>                     
                                <li><a href="<s:url action="studentAdminAction"/>" >Change Login Password</a></li>
                                <li><a href="<s:url action="studentAdminAction"/>" >Change txt Password</a></li>
                            </ul> 
                        </li>  
                    </ul>
                </div>
            </div>
        </s:form>
    </body>
</html>

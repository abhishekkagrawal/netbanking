<%-- 
    Document   : test
    Created on : Oct 18, 2013, 12:52:03 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link rel="stylesheet" type="text/css" href="../css/MainRSM_styles_new.css" />
        <link rel="stylesheet" type="text/css" href="../css/styles_new.css" />
        <link rel="stylesheet" type="text/css" href="../css/AS_RSM_sstyle_css.css" />
        <link rel="stylesheet" type="text/css" href="../css/retailcss.css" />
        <script type="text/javascript" src="../javascript/MainRSM_style_new.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Struts 2 download file example</h1>

    <s:url id="fileDownload" namespace="/" action="download" ></s:url>

    <h4>Download file - <s:a href="%{fileDownload}">fileABC</s:a></h4>

</body>
</html>

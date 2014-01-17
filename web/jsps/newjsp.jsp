<%-- 
    Document   : newjsp
    Created on : Oct 16, 2013, 6:45:25 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="../javascript/jquery-1.4.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
        <script type="text/javascript">
            function validation(){
                if(validateUserId()){
                 return true;   
                }
                return false;                
            }
            
            function validateUserId(){
         
              var password=$('.userId').val();
                var flag = true;
                // validation for username..     
                if (password == "" || password.length == 0) {
                    $("#errorpassword").remove();
                    $(".userId").parent().append('<span id="errorpassword">*enter Password</span>');
                    $(".userId").focus();
                    flag = false;
                } 

                if (flag == true) {
                    $("#errorpassword").remove();
                    return flag;
                } else {
                    return flag;
                }
            }
        </script>
        <sj:head/>
    </head>
    <body>
        <s:form onsubmit="return validation()">
            <s:textfield name="jay" label="enter" id="userId" onblur="return validateUserId()"/>  
            
      <sj:datetimepicker value="todayDate" id="date1" name="date1" label="Date Value from Action" />
      <sj:datetimepicker id="date2" name="nameValue" label="Date Value by Name" value="%{'date'}" />
      <sj:datetimepicker value="%{'2010-01-01'}" id="date3" name="date3" displayFormat="dd.MM.yyyy" label="Today" />
      <sj:datetimepicker value="yesterday" id="date4" name="date4" displayFormat="mm/dd/yy" label="Yesterday" />
      <sj:datetimepicker value="tomorrow" id="date5" name="date5" displayFormat="DD, d MM yy" label="Tomorrow" />
      <sj:datetimepicker value="2004-08-14" id="date6" name="date6" displayFormat="d M, yy" label="String Value" />
            <s:submit/>
        </s:form>
    </body>
</html>

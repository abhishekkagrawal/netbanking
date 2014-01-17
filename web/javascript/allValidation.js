/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*..................................... document.ready functions Start.......................................*/
$(document).ready(function() {
    $('#endDate').val($.datepicker.formatDate('mm/dd/yy', new Date()));
    $('#startDate').val($.datepicker.formatDate('mm/dd/yy', new Date()));
    $('#last').keypress(function(event) {
        if ((event.which < 48 || event.which > 57) && event.keyCode != 8) {
            event.preventDefault();
        }
    });

    $('#newRegistrationMobile').keydown(function(e) {
        if ((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 8) {
            e.stopPropagation();
            return false;
        }
    });
    $('#newRegistrationAccountNumber').keydown(function(e) {
        if ((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 8) {
            e.stopPropagation();
            return false;
        }
    });
    
    $('#passwordResetPageMobile').keydown(function(e) {
        if ((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 8) {
            e.stopPropagation();
            return false;
        }
    });
    $('#passwordResetPageOPTNumber').keydown(function(e) {
        if ((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 8) {
            e.stopPropagation();
            return false;
        }
    });
    
});


/*..................................... document.ready functions End .......................................*/



/*.................................... User Id validation start................................................*/
function validationStartPageUserId() {
    var flag = false;
    if (validateStartPageUserId()) {
        return true;
    }
    return flag;

}//end of validationStartPageUserId() function..

function validateStartPageUserId() {
    var uname = $('#startPageUserId').val();
    var flag = true;
    // validation for username..     
    if (uname == "" || uname.length == 0) {
        $("#errorstartPageUserId").remove();
        $("#startPageUserId").parent().append('<span id="errorstartPageUserId">*enter username</span>');
        $("#startPageUserId").focus();
        flag = false;
    } else if (!isNaN(uname)) {
        $("#errorstartPageUserId").remove();
        $("#startPageUserId").parent().append('<span id="errorstartPageUserId" >*enter valid username</span>');
        $("#startPageUserId").focus();
        flag = false;
    }
    if (flag == true) {
        $("#errorstartPageUserId").remove();
        return flag;
    } else {
        return flag;
    }
}// end of validateStartPageUserId() function..
/*.................................... User Id validation End ..............................................*/

/*...................................  LOgin Page Validation Start .......................................*/

function validateLoginPage() {
    var flag = false;
    if (validateloginPagePassword()) {
        return true;
    }
    return flag;

}//end of validateLoginPage() function..

function validateloginPagePassword() {

    var password = $('#loginPagePassword').val();
    var flag = true;
    var regex = /^[A-Za-z()]+[0-9]+[A-za-z0-9()]+$/;
    // validation for password..     
    if (password == "" || password.length == 0) {
        $("#errorloginPagePassword").remove();
        $("#loginPagePassword").parent().append('<span id="errorloginPagePassword">*Enter Password</span>');
        $("#loginPagePassword").focus();
        flag = false;
    } else if (password.length < 8 || password.length > 15) {
        $("#errorloginPagePassword").remove();
        $("#loginPagePassword").parent().append('<span id="errorloginPagePassword">*Password should be 8- 15 char</span>');
        $("#loginPagePassword").focus();
        flag = false;
    } else if (!regex.test(password)) {
        $("#errorloginPagePassword").remove();
        $("#loginPagePassword").parent().append('<span id="errorloginPagePassword">*Password contain alphanumeric char</span>');
        $("#loginPagePassword").focus();
        flag = false;

    }
    if (flag == true) {
        $("#errorloginPagePassword").remove();
        return flag;
    } else {
        return flag;
    }
}// end of validateloginPagePassword() function..

/*................................. Login Page validation End .............................................*/

/*.................................... printAndViewStatementPage validation Start ..............................................*/

$(function() {
    $("#startDate").datepicker({
        changeMonth: true,
        changeYear: true,
        maxDate: "0D",
        minDate: "-24M"
    });
    $("#endDate").datepicker({
        changeMonth: true,
        changeYear: true,
        maxDate: "0D",
        minDate: "-24M"
    });
});

function printAndViewStatementPageValidation() {
    var noOfTransaction = $('#last').val();
    var endDate = $('#endDate').val();
    var startDate = $('#startDate').val();
    if (noOfTransaction === "" || noOfTransaction === 0) {
        if (endDate < startDate) {
            alert("Start date should before of end date");
            return false;
        } else {
            return  true;
        }
    } else {
        return true;
    }
}//end of printAndViewStatementPageValidation() function..
/*...................................  printAndViewStatementPage Validation End .......................................*/


/*.................................... forgotPassword page validation Start ..............................................*/

function forgotPasswordValidation() {
    var flag = false;
    if (forgotPasswordValidateUserId()) {
        return true;
    }
    return flag;

}//end of forgotPasswordValidation() function..

function forgotPasswordValidateUserId() {
    var userId = $('#forgotPasswordUserId').val();
    var flag = true;
    // validation for username..     
    if (userId == "" || userId.length == 0) {
        $("#errorforgotPasswordusername").remove();
        $("#forgotPasswordUserId").parent().append('<span id="errorforgotPasswordusername">*Enter userId</span>');
        $("#forgotPasswordUserId").focus();
        flag = false;
    }
    if (flag == true) {
        $("#errorforgotPasswordusername").remove();
        return flag;
    } else {
        return flag;
    }
}// end of forgotPasswordValidateUserId() function..

/*...................................  forgotPassword page Validation End .......................................*/

/*................................... newRegistration Page Validation Start .......................................*/

$(function() {
    $("#newRegistrationDateOfBirth").datepicker({
        changeMonth: true,
        changeYear: true,
        maxDate: "-216M",
        minDate: "-720M"
    });
});

function newRegistrationValidateForm() {
    var flag = false;
    if (newRegistrationAccountNumberValidation() && newRegistrationMobileValidation() && newRegistrationDateOfBirthValidation() && newRegistrationPanCardNumberValidation()) {
        return true;
    }
    return flag;

}//end of newRegistrationValidateForm() function..

function newRegistrationMobileValidation() {
    var userId = $('#newRegistrationMobile').val();
    var flag = true;
    // validation for username..     
    if (userId == "" || userId.length == 0) {
        $("#errorNewRegistrationMobile").remove();
        $("#newRegistrationMobile").parent().append('<span id="errorNewRegistrationMobile">*Enter mobile number</span>');
        $("#newRegistrationMobile").focus();
        flag = false;
    } else if (userId.length != 10) {
        $("#errorNewRegistrationMobile").remove();
        $("#newRegistrationMobile").parent().append('<span id="errorNewRegistrationMobile">*Invalid mobile No.(enter 10 digit only)</span>');
        $('#newRegistrationMobile').focus();
        flag = false;
    }//end of if block..
    if (flag == true) {
        $("#errorNewRegistrationMobile").remove();
        return flag;
    } else {
        return flag;
    }
}// end of newRegistrationMobileValidation() function..


function newRegistrationPanCardNumberValidation() {
    var panCardNumber = $('#newRegistrationPanCardNumber').val();
    var flag = true;
    // validation for username..     
    if (panCardNumber == "" || panCardNumber.length == 0) {
        $("#errorNewRegistrationPanCardNumber").remove();
        $("#newRegistrationPanCardNumber").parent().append('<span id="errorNewRegistrationPanCardNumber">*Enter Pan Card number</span>');
        $("#newRegistrationPanCardNumber").focus();
        flag = false;
    }
    if (flag == true) {
        $("#errorNewRegistrationPanCardNumber").remove();
        return flag;
    } else {
        return flag;
    }
}// end of newRegistrationPanCardNumberValidation() function..
function newRegistrationAccountNumberValidation() {
    var accountNumber = $('#newRegistrationAccountNumber').val();
    var flag = true;
    // validation for username..     
    if (accountNumber == "" || accountNumber.length == 0) {
        $("#errorNewRegistrationAccountNumber").remove();
        $("#newRegistrationAccountNumber").parent().append('<span id="errorNewRegistrationAccountNumber">*Enter Account Number</span>');
        $("#newRegistrationAccountNumber").focus();
        flag = false;
    }
    if (flag == true) {
        $("#errorNewRegistrationAccountNumber").remove();
        return flag;
    } else {
        return flag;
    }
}// end of newRegistrationAccountNumberValidation() function..dateOfBirth

function newRegistrationDateOfBirthValidation() {
    var dateOfBirth = $('#newRegistrationDateOfBirth').val();
    var flag = true;
    // validation for username..     
    if (dateOfBirth == "" || dateOfBirth.length == 0) {
        $("#errorNewRegistrationDateOfBirth").remove();
        $("#newRegistrationDateOfBirth").parent().append('<span id="errorNewRegistrationDateOfBirth">*Select date of birth</span>');
        $("#newRegistrationDateOfBirth").focus();
        flag = false;
    }
    if (flag == true) {
        $("#errorNewRegistrationDateOfBirth").remove();
        return flag;
    } else {
        return flag;
    }
}// end of newRegistrationDateOfBirthValidation() function..dateOfBirth 

/*.................................... newRegistration page validation End ..............................................*/

/*...................................  passwordResetPage page Validation Start .......................................*/

function passwordResetPageValidateForm() {
    var flag = false;
    if (passwordResetPageUserIdValidation() && passwordResetPageMobileValidation() && passwordResetPagePanCardNumberValidation() && passwordResetPageOPTNumberValidation()) {
        return true;
    }
    return flag;

}//end of validateLogin() function..
function passwordResetPageUserIdValidation() {
    var userId = $('#passwordResetPageUserId').val();
    var flag = true;
    // validation for username..     
    if (userId == "" || userId.length == 0) {
        $("#errorPasswordResetPageUserId").remove();
        $("#passwordResetPageUserId").parent().append('<span id="errorPasswordResetPageUserId">*Plese Enter UserId</span>');
        $("#passwordResetPageUserId").focus();
        flag = false;
    }
    if (flag == true) {
        $("#errorPasswordResetPageUserId").remove();
        return flag;
    } else {
        $("#passwordResetPageUserId").focus();
        return flag;
    }
}// end of passwordResetPageUserIdValidation() function..
function passwordResetPageMobileValidation() {
    var mobileNumber = $('#passwordResetPageMobile').val();
    var flag = true;
    var no = /[1-9]+\d{9}$/;
    // validation for mobile numbre..     
    if (mobileNumber == "" || mobileNumber.length == 0) {
        $("#errorPasswordResetPageMobile").remove();
        $("#passwordResetPageMobile").parent().append('<span id="errorPasswordResetPageMobile">*Enter Mobile number</span>');
        $("#passwordResetPageMobile").focus();
        flag = false;
    } else if (mobileNumber.length != 10) {
        $("#errorPasswordResetPageMobile").remove();
        $("#passwordResetPageMobile").parent().append('<span id="errorPasswordResetPageMobile">*Invalid mobile No.(enter 10 digit only)</span>');
        $('#passwordResetPageMobile').focus();
        flag = false;
    } else if (!no.test(mobileNumber)) {
        $("#errorPasswordResetPageMobile").remove();
        $("#passwordResetPageMobile").parent().append('<span id="errorPasswordResetPageMobile">*Invalid mobile No.</span>');
        $('#passwordResetPageMobile').focus();
        flag = false;
    }//end of if block..
    if (flag == true) {
        $("#errorPasswordResetPageMobile").remove();
        return flag;
    } else {
        return flag;
    }
}// end of passwordResetPageMobileValidation() function..
function passwordResetPagePanCardNumberValidation() {
    var panCardNumber = $('#passwordResetPagePanCardNumber').val();
    var flag = true;
    // validation for username..     
    if (panCardNumber == "" || panCardNumber.length == 0) {
        $("#errorPasswordResetPagePanCardNumber").remove();
        $("#passwordResetPagePanCardNumber").parent().append('<span id="errorPasswordResetPagePanCardNumber">*Enter Pan Card number</span>');
        $("#passwordResetPagePanCardNumber").focus();
        flag = false;
    }
    if (flag == true) {
        $("#errorPasswordResetPagePanCardNumber").remove();
        return flag;
    } else {
        return flag;
    }
}// end of passwordResetPagePanCardNumberValidation() function..
function passwordResetPageOPTNumberValidation() {
    var optnumber = $('#passwordResetPageOPTNumber').val();
    var flag = true;
    // validation for username..     
    if (optnumber == "" || optnumber.length == 0) {
        $("#errorpasswordResetPageOPTNumber").remove();
        $("#passwordResetPageOPTNumber").parent().append('<span id="errorpasswordResetPageOPTNumber">*Enter OPTNumber</span>');
        $("#passwordResetPageOPTNumber").focus();
        flag = false;
    }
    if (flag == true) {
        $("#errorpasswordResetPageOPTNumber").remove();
        return flag;
    } else {
        return flag;
    }
}// end of passwordResetPageOPTNumberValidation() function..


/*................................... passwordResetPage Page Validation End .......................................*/






<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>


<fmt:message bundle="${language}" key="singin" var="signin"/>
<fmt:message bundle="${language}" key="enteroulogin" var="enterlogin"/>
<fmt:message bundle="${language}" key="enteryoupassword" var="enterpassword"/>
<fmt:message bundle="${language}" key="enteryouremail" var="enteremail"/>
<fmt:message bundle="${language}" key="enteryourname" var="entername"/>
<fmt:message bundle="${language}" key="enteryourphone" var="enterphone"/>
<fmt:message bundle="${language}" key="login" var="log"/>
<fmt:message bundle="${language}" key="useaddress" var="useraddress"/>
<fmt:message bundle="${language}" key="username" var="username"/>
<fmt:message bundle="${language}" key="useremail" var="useremail"/>
<fmt:message bundle="${language}" key="userphone" var="userphone"/>
<fmt:message bundle="${language}" key="userpassword" var="userpassword"/>
<fmt:message bundle="${language}" key="errorlogin" var="errorlogin"/>
<fmt:message bundle="${language}" key="signup" var="signup"/>
<fmt:message bundle="${language}" key="haveaccount" var="haveaccount"/>
<fmt:message bundle="${language}" key="logintoseeprofile" var="pleaselogin"/>
<fmt:message bundle="${language}" key="enterinvalid" var="enterinvalid"/>
<fmt:message bundle="${language}" key="donthaveaccount" var="donthaveacc"/>
<fmt:message bundle="${language}" key="continueguest" var="continuegueast"/>
<fmt:message bundle="${language}" key="register" var="register"/>



<html lang="${language}">
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="main-form">
                    <form class="login100-form validate-form p-l-55 p-r-55 p-t-178" method="POST"
                          action="controller?command=login">
					<span class="login100-form-title">
						${signin}
					</span>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="${enterlogin}">
                            <input class="input100" type="text" name="login" placeholder="${log}">
                            <span class="focus-input100"></span>
                        </div>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="${enterlogin}">
                            <input class="input100" type="password" name="password" placeholder="${userpassword}">
                            <span class="focus-input100"></span>
                        </div>


                        <c:if test="${error_authentification eq true}">
                            <div class="container1" role="alert">
                                ${enterinvalid}

                            </div>
                        </c:if>

                        <div class="container-login100-form-btn">
                            <button type="submit" class="login100-form-btn">
                                ${signin}
                            </button>
                        </div>

                        <a href="controller?command=home_page" class="txt2">
                            ${continuegueast}
                        </a>

                        <div class="flex-col-c p-t-120 p-b-40">
						<span class="txt1">
							${donthaveacc}
						</span>
                            <div class="form-group ">
                                <a href="controller?command=register" target="_blank" type="button" id="button"
                                   class="login-button">${register}</a>
                            </div>
                        </div>
                    </form>
        </div>
    </div>
</div>


<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>

<style>
    .wrap-input100 {
        margin-bottom: 5px;
        margin-top: 5px;
    }

    .container1 {
        margin-left: -18%;
    }

    .form-group {
        margin-bottom: 15px;
    }

    .login100-form {
        margin-left: 43%;
    }

    label {
        margin-bottom: 15px;
    }

    .form-control {
        height: auto !important;
        padding: 8px 12px !important;
    }

    .input-group {
        box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.21) !important;
    }

    .login100-form-title {
        margin-left: 3%;
        font-size: 30px;
    }

    .form-group button {
        background-color: whitesmoke;
    }

    #button {
        padding: 6px 12px;
        color: #666;
        text-shadow: 0 1px #fff;
        border-radius: 3px 3px;
        box-shadow: 0 1px #fff inset, 0 1px #ddd;
    }

    .container-login100 {
        margin-top: 30px;
        margin: 0 auto;
        padding: 10px 40px;
        background: #009edf;
        color: #FFF;
        text-shadow: none;
        box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
        height: auto;
    }

    span.input-group-addon i {
        color: #009edf;
        font-size: 17px;
    }

    .login-button {
        margin-top: 5px;
    }

    .txt2 {
        color: whitesmoke;
    }

    .txt3 {
        color: whitesmoke;
    }

    .main-form {
        width: auto;
    }

    .login10-form {
        margin-left: 30%;
        margin-right: 30%;
    }
</style>
</body>

</html>



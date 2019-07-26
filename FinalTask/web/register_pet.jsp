<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
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
    <script type="text/javascript" src="vendor/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="vendor/daterangepicker/moment.js"></script>
    <script type="text/javascript" src="vendor/daterangepicker/daterangepicker.js"></script>
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css"/>
</head>
<body>

<div class="limiter">
    <div class="container-login100">

        <div class="main-form">
                    <form class="login10-form" method="POST"
                          action="controller?command=register_pet">
					<span class="login100-form-title">
						Sign Up
					</span>

                        <div class="form-group">
                            <label for="name" class="cols-sm-2 control-label">Name</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" name="name" id="name"
                                           placeholder="Enter your name"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="cols-sm-2 control-label">Pet</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <input type="radio" name="kind" value="cat">Cat
                                    <input type="radio" name="kind" VALUE="dog">Dog
                                    <input type="radio" name="kind" value="hamster">Hamster
                                    <input type="radio" name="kind" value="parrot">Parrot
                                    <input type="radio" name="kind" value="turtle">Turtle
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <input type="text" class="form-control" name="dateOfBirth" id="dateOfBirth"
                                   placeholder="Enter date of birth">
                            <script>
                                $(function() {
                                    $('input[name="dateOfBirth"]').daterangepicker({
                                        singleDatePicker: true,
                                        showDropdowns: true,
                                        minYear: 1990,
                                        maxYear: parseInt(moment().format('YYYY'),10)
                                    }
                                    );
                                });
                            </script>
                        </div>


                        <c:if test="${error_registration eq true}">
                            <div class="container alert alert-warning alert-dismissible fade show m-t-16"
                                 role="alert">
                                User with this login already exist. Sign in or choose another login.
                            </div>
                        </c:if>


                        <div class="container-login100-form-btn">
                            <button type="submit" class="login100-form-btn">
                                Register
                            </button>
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




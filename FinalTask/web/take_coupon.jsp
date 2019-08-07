<%--
  Created by IntelliJ IDEA.
  User: dianasaz
  Date: 06.08.2019
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:message bundle="${language}" key="signup" var="signup"/>
<fmt:message bundle="${language}" key="pet" var="pet"/>
<fmt:message bundle="${language}" key="cat" var="cat"/>
<fmt:message bundle="${language}" key="dog" var="dog"/>
<fmt:message bundle="${language}" key="turtle" var="turtle"/>
<fmt:message bundle="${language}" key="parrot" var="parrot"/>
<fmt:message bundle="${language}" key="hamster" var="hamster"/>
<fmt:message bundle="${language}" key="enterdate" var="enterdate"/>
<fmt:message bundle="${language}" key="register" var="register"/>

<head>
    <title>Take Coupon</title>
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
                  action="controller?command=take_coupon">
<span class="login100-form-title">
                        Take coupon
                    </span>

                <script>
                    function Selected(a, size) {
                        var label = a.value;
                        var i;
                        for(i = 0; i < size; i++){
                            if (label == i){
                                document.getElementById(i).style.display="block";
                            } else{
                                document.getElementById(i).style.display="none";
                            }
                        }
                    }
                </script>

                <select id="actSelect" class="Validate_Required " name="service" aria-required="true"
                        onChange="Selected(this, ${services.size()})">
                    <option value="" selected="selected">-</option>
                    <c:forEach var="service" items="${serviceNames}" varStatus="i">
                        <option value="${i.index}">${service}</option>
                    </c:forEach>
                </select>


                <c:forEach var="service" items="${serviceNames}" varStatus="i">
                    <div id="${i.index}" style='display: none;'>
                        Choose doctor:
                        <select name="doctor">
                            <c:forEach var="doctor" items="${doctors}">
                                <c:forEach var="docservice" items="${doctor.service}">
                                    <c:if test="${docservice.name eq service}">
                                        <option value="${doctor.name}">${doctor.name}</option>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                        </select>
                    </div>
                </c:forEach>

                <div class="col-md-6">
                    <input type="text" class="form-control" name="date" id="date"
                           placeholder="${enterdate}">
                    <script>
                        $(function() {
                            $('input[name="date"]').daterangepicker({
                                "singleDatePicker": true,
                                "timePicker": true,
                                "timePicker24Hour": true,
                                "timePickerIncrement": 10,
                                "autoApply": true,
                                "locale": {
                                    "format": "YYYY-MM-DD HH:mm",
                                    "separator": " - ",
                                    "applyLabel": "Apply",
                                    "cancelLabel": "Cancel",
                                    "fromLabel": "From",
                                    "toLabel": "To",
                                    "customRangeLabel": "Custom",
                                    "weekLabel": "W",
                                    "daysOfWeek": [
                                        "Su",
                                        "Mo",
                                        "Tu",
                                        "We",
                                        "Th",
                                        "Fr",
                                        "Sa"
                                    ],
                                    "monthNames": [
                                        "January",
                                        "February",
                                        "March",
                                        "April",
                                        "May",
                                        "June",
                                        "July",
                                        "August",
                                        "September",
                                        "October",
                                        "November",
                                        "December"
                                    ],
                                    "firstDay": 1
                                },
                                "opens": "center"
                            });
                        });
                    </script>
                </div>

                <div class="col-md-6">
                    <input type="hidden" class="form-control" name="pet_id"
                           value="${pet.identity}">
                </div>


                <div class="container-login100-form-btn">
                    <button type="submit" class="login100-form-btn">
                        ${register}
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





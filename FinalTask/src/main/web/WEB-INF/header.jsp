<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: dianasaz
  Date: 15.07.2019
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:choose>
    <c:when test="${sessionScope.lang != null}">
        <fmt:setLocale value="${sessionScope.lang}" variant="en"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="ru"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="language" var="language" scope="session"/>

<fmt:message bundle="${language}" key="singin" var="signin"/>
<fmt:message bundle="${language}" key="signout" var="signout"/>
<fmt:message bundle="${language}" key="workhours" var="workh"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <!--/bootstarp-css -->
    <!--css-->
    <link rel="stylesheet" href="css/style_new.css" type="text/css" media="all"/>
    <!--/css-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Petcare Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);
    </script>
    <!--fonts-->
    <link href='http://fonts.googleapis.com/css?family=Arimo:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
    <!--/fonts-->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="js/modernizr.custom.js"></script>
    <script src="js/responsiveslides.min.js"></script>
</head>
<body>
<div class="header">
    <div class="container">
        <ul class="cl-effect-21" style="margin-top: 2%">
            <li style="margin-top: 2%; float: left;">
                <img src="images/logo.png" alt=""/>
            </li>
            <li style="margin-top: 3.5%; color: black; float: left;"><p style="color: black;">${workh}</p></li>

            <li style="margin-top: 3.5%; float: right;">
                <c:choose>
                    <c:when test="${user == null}">
                        <a style="color: black" href="controller?command=login">${signin}</a>
                    </c:when>
                    <c:otherwise>
                        <a style="color: black" href="controller?command=logout">${signout}</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li style="float: right; margin-top: 3.5%;">
                <c:choose>
                    <c:when test="${sessionScope.lang != null}">
                        <a style="color: black"
                           href="controller?command=change_language&lang=${sessionScope.lang}"
                           type="button" id="button"
                           class="login-button">${sessionScope.nextLang}</a>
                    </c:when>
                    <c:otherwise>
                        <a style="color: black" href="controller?command=change_language&lang=ru"
                           type="button" id="button"
                           class="login-button">EN</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li style="float: right; margin-top: 3.5%;">
                <form action="controller?command=search" method="post" id="search">
                    <input type="text" name="search" value="поиск" onfocus="if(this.value=='поиск') this.value='';" class="input" />
                    <input type="submit" name="" value="" class="submit" />
                </form>
            </li>
        </ul>
    </div>
</div>
<style>
    .header {
        background: -webkit-linear-gradient(left, #f5f5f5, #b3def4c4);
        min-height: 180px;
    }
</style>
</body>
</html>

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

<!DOCTYPE html>
<html lang="${lang}">
<head><title>Final Task</title>
    <title>Petcare a Animals & Pets Category Flat Bootstarp responsive Website Template| HOME :: w3layouts</title>
    <!--bootstarp-css-->
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
        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <link href="css/hover.css" rel="stylesheet" media="all">
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
    <div class="container mt-5">
        <ul class="cl-effect-21" style="margin-top: 2%; ">
            <li style="margin-right: 22%">
                <img src="images/logo.png" alt=""/>
            </li>
            <li style="margin-right: 22%">  <c:choose>
                <c:when test="${sessionScope.lang != null}">
                    <a style="color: whitesmoke" href="controller?command=change_language&lang=${sessionScope.lang}" target="_blank" type="button" id="button"
                       class="login-button">${sessionScope.lang}</a>
                </c:when>
                <c:otherwise>
                    <a style="color: whitesmoke" href="controller?command=change_language&lang=ru" target="_blank"
                       type="button" id="button"
                       class="login-button">RU</a>
                </c:otherwise></c:choose>
            </li>

            <li style="margin-right: 22%"><c:choose>
                <c:when test="${user == null}">
                    <a style="color: whitesmoke" href="controller?command=login">${signin}</a>

                </c:when>
                <c:otherwise>
                    <a style="color: whitesmoke" href="controller?command=logout">${signout}</a>
                </c:otherwise>
            </c:choose></li>
        </ul>
    </div>
</div>
<div class="container">
    <div class="header-bottom">
        <div class="menu">
            <span class="menu-info"> </span>
            <jsp:include page="menu.jsp"/>
        </div>
    </div>
    <div class="header-banner">
        <div class="slider">
            <ul class="rslides" id="slider2">
                <li><a href="#"><img src="images/4.jpg" class="img-responsive" alt=""/></a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dianasaz
  Date: 15.07.2019
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
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
         addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }
    </script>
    <link href="css/hover.css" rel="stylesheet" media="all">
    <!--fonts-->
    <link href='http://fonts.googleapis.com/css?family=Arimo:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
    <!--/fonts-->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="js/modernizr.custom.js"></script>
    <script src="js/responsiveslides.min.js"></script>
<body>
<div class="header">
    <div class="header-info">
        <div class="container">
            <div class="logo">
                <img src="images/logo.png" alt=""/>
                <div class="search-box">
                    <a href="controller?command=login">LOG IN</a>
                </div>

                <%--
                     <c:choose>
                       <c:when test="${user.name == null}">

                       </c:when>
                       <c:otherwise>
                           <div class="search-box">
                               <a href="controller?command=logout">LOG OUT</a>
                           </div>
                       </c:otherwise>
      </c:choose>  --%>
            </div>

        </div>
    </div>
</div>
<div class="container">
    <div class="header-bottom">
        <div class="menu">
            <span class="menu-info"> </span>
            <ul class="cl-effect-21">
                <li><a href="index.html" class="active">HOME</a></li>
                <li><a href="about.html">ABOUT</a></li>
                <li><a href="contact.html">PROFILE</a></li>
                <li><a href="projects.html">SERVICES</a></li>
                <li><a href="support.html">DOCTORS</a></li>
                <li><a href="404.html">OUR PETS</a></li>
            </ul>
        </div>

    </div>
</div>
</body>
</html>

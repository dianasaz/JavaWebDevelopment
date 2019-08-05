<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>


<c:choose>
    <c:when test="${sessionScope.lang != null}">
        <fmt:setLocale value="${sessionScope.lang}" variant="en"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="ru"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="language" var="language" scope="session"/>

<fmt:message bundle="${language}" key="noaccesstopage" var="noaccess"/>
<fmt:message bundle="${language}" key="login" var="login"/>

<!DOCTYPE html>
<html lang="${language}" >
<head>
<meta charset="UTF-8">
<title>Day 001 Login Form</title>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
<link rel="stylesheet" href="../styless/loginStyles.css">
</head>

<body>
<div class="login-wrap">
    <div class="login-html">
        <h2>${noaccess}</h2>
    </div>
    <a href="controller?command=login" style="position: absolute;margin-top: 250px;margin-left: 680px;background: white;color: black;font-weight: 700;width: 84px;height: 20px;text-shadow: none;border-radius: 5px;">${login}</a>
</div>

</body>

</html>
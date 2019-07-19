<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div id="wrapper">
    <form id="signin" name="loginForm" method="POST" action="controller?command=login">
        <input type="text" id="user" name="login" placeholder="username" value="login"/>
        <input type="password" id="pass" name="password" placeholder="password" value=""/>
        <button type="submit">&#xf0da;</button>

    </form>
    <div>
        <a href="controller?command=register">Register</a>
    </div>


</div>
<hr/>
</body>
</html>
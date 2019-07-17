<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Login</title>
    <link rel="stylesheet" href="css/style.css"></head>
<body>
<div id="wrapper">
<form id="signin" name="loginForm" method="POST" action="controller?command=login">

    <input type="hidden" name="command" value="login" />
    <input type="text" id="user" name="login" placeholder="username" value="login" />
    <input type="password" id="pass" name="password" placeholder="password" value="" />
    <button type="submit">&#xf0da;</button>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
</form>
</div><hr/>
</body></html>
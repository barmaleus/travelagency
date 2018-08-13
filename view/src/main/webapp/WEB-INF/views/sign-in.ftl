<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Log in</title>
</head>
<body>
<#include "templates/header.ftl">
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Sign In</h1>

<form role="form" action="/sign-in" method="post">
    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
    <input type="hidden" name="loginform" value="loginform"/>
    <div>
        <label for="login">Login</label>
        <input type="text" name="login" id="login" required autofocus/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required/>
    </div>
    <div>
        <label for="remember-me">Remember me</label>
        <input type="checkbox" name="remember-me" id="remember-me"/>
    </div>
    <button type="submit">Sign in</button>
</form>

<#--<#if error.isPresent()>-->
<#--<p>The login or password you have entered is invalid, try again.</p>-->
<#--</#if>-->
<#include "templates/footer.ftl">
</body>
</html>
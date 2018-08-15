<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Sign in</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <script src="/resources/styles/js/lib/jquery-1.12.0.min.js"></script>
    <!-- Bootstrap Core -->
    <link rel="stylesheet" href="/resources/styles/bootstrap/css/bootstrap.min.css" />
    <script src="/resources/styles/bootstrap/js/bootstrap.min.js"></script>
    <!-- EPAM UUI JavaScript Core -->
    <script src="/resources/styles/js/uui-core.min.js" type="text/javascript"></script>
    <!-- EPAM UUI Styles Core -->
    <link rel="stylesheet" href="/resources/styles/css/uui-all.css" />
</head>
<body>
<div class="wrapper">
    <#include "templates/header.ftl">
    <div class="uui-main-container">
        <main>
            <h1>Sign In</h1>

            <form role="form" action="/sign-in" method="post">
            <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
            <#--<input type="hidden" name="loginform" value="loginform"/>-->
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
        </main>
    </div>
    <#include "templates/footer.ftl">
</div>
</body>
</html>
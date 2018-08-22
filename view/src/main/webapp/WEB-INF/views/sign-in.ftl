<#import "/spring.ftl" as spring />
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
        <main class="text-center">
            <div class="uui-login-panel">
                <div class="login-panel-body">
                    <div class="login-panel-section">
                        <h1 class="section-title">Sign In</h1>
                        <form role="form" action='<@spring.url "j_spring_security_check"/>' method="post">
                            <div>
                                <label for="login" class="text"><p class="uui-big-text text-uppercase">Login</p></label>
                                <br>
                                <input type="text" name="j_username" id="login" class="uui-form-element large" required autofocus/>
                            </div>
                            <br>
                            <div>
                                <label for="password" class="text"><p class="uui-big-text text-uppercase">Password</p></label>
                                <input type="password" name="j_password" id="password" class="uui-form-element large" required/>
                            </div>
                            <br>
                            <div>
                                <label for="remember-me" class="text"><p class="uui-big-text">Remember me  </p></label>
                                <input type="checkbox" name="_spring_security_remember_me" id="remember-me" class="uui-checkbox large"/>
                            </div>
                            <br>
                            <button type="submit" class="uui-button large blue">Sign in</button>
                        </form>
                        
                    <#--<#if error.isPresent()>-->
                    <#--<p>The login or password you have entered is invalid, try again.</p>-->
                    <#--</#if>-->
                    </div>
                </div>
            </div>
        </main>
    </div>
    <#include "templates/footer.ftl">
</div>
</body>
</html>
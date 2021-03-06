<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Sign in</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <script src="/resources/styles/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="/resources/styles/bootstrap/css/bootstrap.min.css" />
    <script src="/resources/styles/bootstrap/js/bootstrap.min.js"></script>
    <script src="/resources/styles/js/uui-core.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/resources/styles/css/uui-all.css" />
    <link rel="stylesheet" href="/resources/styles/fonts/font-awesome/css/font-awesome.min.css" />
    <script src="/resources/styles/custom/script-protection.js"></script>
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
                                <input type="text" onkeyup="scriptProtectionLogin(this)" name="j_username" id="login" class="uui-form-element large" pattern=".{3,20}" required title="3 to 20 characters" autofocus/>
                            </div>
                            <br>
                            <div>
                                <label for="password" class="text"><p class="uui-big-text text-uppercase">Password</p></label>
                                <input type="password" onkeyup="scriptProtectionText(this)" name="j_password" id="password" class="uui-form-element large" pattern=".{3,40}" required title="3 to 40 characters" />
                            </div>
                            <br>
                            <div>
                                <label for="remember-me" class="text"><p class="uui-big-text">Remember me  </p></label>
                                <input type="checkbox" name="_spring_security_remember_me" id="remember-me" class="uui-checkbox large"/>
                            </div>
                            <br>
                            <button type="submit" class="uui-button large blue">Sign in</button>
                        </form>
                        <br>
                        <p>${message!""}</p>

                    </div>
                </div>
            </div>
        </main>
    </div>
    <#include "templates/footer.ftl">
</div>
</body>
</html>
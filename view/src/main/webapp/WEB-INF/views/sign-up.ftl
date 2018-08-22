<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Sign Up</title>
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
                        <h1 class="section-title">Sign Up</h1>

                        <form role="form" action="/sign-up" method="post">
                            <div>
                                <label for="login"><h2>Login</h2></label>
                                <input name="login" type="text" placeholder="login" class="uui-form-element large" required autofocus/>
                            </div>
                            <br>
                            <div>
                                <label for="password"><h2>Password</h2></label>
                                <input name="password" type="password" placeholder="password" class="uui-form-element large" required/>
                            </div>
                            <br>
                        <#--<input name="password" type="password" placeholder="confirm password" required/>-->
                            <button type="submit" class="uui-button large blue">Sign up</button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <#include "templates/footer.ftl">
</div>
</body>
</html>
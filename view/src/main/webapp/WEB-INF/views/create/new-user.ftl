<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: User Creator</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <script src="../../../resources/styles/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="../../../resources/styles/bootstrap/css/bootstrap.min.css" />
    <script src="../../../resources/styles/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../resources/styles/js/uui-core.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="../../../resources/styles/css/uui-all.css" />
    <link rel="stylesheet" href="../../../resources/styles/fonts/font-awesome/css/font-awesome.min.css" />
    <script src="../../../resources/styles/custom/script-protection.js"></script>
</head>
<body>
<div class="wrapper">
    <#include "../templates/header.ftl">
    <div class="uui-main-container">
        <main class="text-center">
            <div class="uui-login-panel">
                <div class="login-panel-body">
                    <div class="login-panel-section">
                        <h1 class="section-title">Create new user</h1>

                        <form role="form" action="/reg-user" method="post">
                            <div>
                                <label for="login"><h2>Login</h2></label>
                                <input name="login" type="text" onkeyup="scriptProtectionLogin(this)" placeholder="login" class="uui-form-element large" pattern=".{3,20}" required title="3 to 20 characters" autofocus/>
                            </div>
                            <br>
                            <div>
                                <label for="password"><h2>Password</h2></label>
                                <input name="password" type="password" onkeyup="scriptProtectionText(this)" placeholder="password" class="uui-form-element large" pattern=".{3,40}" required title="3 to 40 characters" />
                                <p>Permitted symbols for password: latin or cyrillic words, numbers, symbols: #,/&?:;%=_@-'.</p>
                            </div>
                            <br>
                            <div class="uui-input-group vertical">
                                <p class="uui-radio large">
                                    <input type="radio" name="role" id="a1" value="ROLE_MEMBER" checked />
                                    <label for="a1">Member</label>
                                </p>
                                <p class="uui-radio large">
                                    <input type="radio" name="role" id="a2" value="ROLE_ADMIN" />
                                    <label for="a2">Administrator</label>
                                </p>
                            </div>
                            <br>
                            <button type="submit" class="uui-button large blue">Register user</button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <#include "../templates/footer.ftl">
</div>
</body>
</html>
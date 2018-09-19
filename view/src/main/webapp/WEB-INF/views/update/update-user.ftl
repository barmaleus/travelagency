<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: User Update</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <script src="../../../resources/styles/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="../../../resources/styles/bootstrap/css/bootstrap.min.css" />
    <script src="../../../resources/styles/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../resources/styles/js/uui-core.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="../../../resources/styles/css/uui-all.css" />
    <link rel="stylesheet" href="../../../resources/styles/fonts/font-awesome/css/font-awesome.min.css" />
</head>
<body>
<div class="wrapper">
    <#include "../templates/header.ftl">
    <div class="uui-main-container">
        <main class="text-center">
            <div class="uui-login-panel">
                <div class="login-panel-body">
                    <div class="login-panel-section">
                        <h1 class="section-title">User Update</h1>

                        <form role="form" action="/update-user" method="post">
                            <input type="hidden" name="id" value="${user.id?c}"/>
                            <div>
                                <label for="login"><h2>Login</h2></label>
                                <input name="login" type="text" value="${user.login}" class="uui-form-element large" required autofocus/>
                            </div>
                            <br>
                            <div>
                                <label for="password"><h2>Password</h2></label>
                                <input name="password" type="password" value="${user.password}" class="uui-form-element large" required/>
                            </div>
                            <br>
                            <div>
                                <label for="role"><h2>Roles</h2></label>
                                <select name="role" class="selectpicker uui-form-element large">
                                <#list userRoles as role>
                                    <#if role == user.role>
                                        <option value="${role}" selected>${role}</option>
                                    <#else>
                                        <option value="${role}">${role}</option>
                                    </#if>
                                </#list>
                                </select>
                            </div>
                            <br>
                            <button type="submit" class="uui-button large blue">Update the user</button>
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
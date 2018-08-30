<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Review Creator</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <script src="../../../resources/styles/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="../../../resources/styles/bootstrap/css/bootstrap.min.css" />
    <script src="../../../resources/styles/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../resources/styles/js/uui-core.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="../../../resources/styles/css/uui-all.css" />
    <link rel="stylesheet" href="../../../resources/styles/fonts/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" href="../../../resources/styles/css/lib/components/bootstrap-select.min.css" />
    <script src="../../../resources/styles/js/lib/components/bootstrap-select.min.js"></script>
    <script src="../../../resources/styles/js/uui-dropdown.min.js"></script>
</head>
<body>
<div class="wrapper">
    <#include "../templates/header.ftl">
    <div class="uui-main-container">
        <main class="text-center">
            <div class="uui-login-panel">
                <div class="login-panel-body">
                    <div class="login-panel-section">
                        <h1 class="section-title">Create new review</h1>

                        <form role="form" action="/reg-review" method="post">
                            <@security.authorize access="hasRole('ROLE_ADMIN')">
                            <div>
                                <label for="user"><h2>User</h2></label>
                                <select name="user" id="user" class="selectpicker uui-form-element large" data-live-search="true">
                                        <#list users as user>
                                            <#if sesUserId = user.id>
                                                <option selected="selected" value="${user.id?c}">${user.login}</option>
                                            <#else>
                                                <option value="${user.id?c}">${user.login}</option>
                                            </#if>
                                        </#list>
                                </select>
                            </div>
                            <div>
                                <label for="tour"><h2>Tour</h2></label>
                                <select name="tour" id="tour" class="selectpicker uui-form-element large" data-live-search="true">
                                    <#list tours as tour>
                                        <#if tourId = tour.id>
                                                <option selected="selected" value="${tour.id?c}">${tour.country.name} ${tour.date}</option>
                                        <#else>
                                                <option value="${tour.id?c}">${tour.country.name} ${tour.date}</option>
                                        </#if>
                                    </#list>
                                </select>
                            </div>
                            </@security.authorize>
                            <@security.authorize access="!hasRole('ROLE_ADMIN')">
                                <p>not granted</p>
                                <input type="hidden" name="user" value="${sesUserId}">
                                <input type="hidden" name="tour" value="${tourId}">
                            </@security.authorize>
                            <div>
                                <label for="text"><h2>Text</h2></label>
                                <textarea  id="text" class="uui-form-element" rows="3" cols="10" name="text" required></textarea>
                            </div>
                            <br>
                            <button type="submit" class="uui-button large blue">Register a review</button>
                        </form>

                        <script>
                            $('.selectpicker').uui_dropdown();
                        </script>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <#include "../templates/footer.ftl">
</div>
</body>
</html>
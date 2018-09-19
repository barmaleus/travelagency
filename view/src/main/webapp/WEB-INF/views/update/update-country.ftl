<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Country Update</title>
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
                        <h1 class="section-title">Country update</h1>

                        <form role="form" action="/update-country" method="post">
                            <input type="hidden" name="id" value="${country.id?c}"/>
                            <div>
                                <label for="name"><h2>Country name</h2></label>
                                <input name="name" type="text"  value="${country.name}" class="uui-form-element large" required autofocus/>
                            </div>
                            <br>
                            <button type="submit" class="uui-button large blue">Update the country</button>
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
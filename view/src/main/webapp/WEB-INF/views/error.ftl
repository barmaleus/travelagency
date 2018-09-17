<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Error Page</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <script src="/resources/styles/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="/resources/styles/bootstrap/css/bootstrap.min.css" />
    <script src="/resources/styles/bootstrap/js/bootstrap.min.js"></script>
    <script src="/resources/styles/js/uui-core.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/resources/styles/css/uui-all.css" />
    <link rel="stylesheet" href="/resources/styles/fonts/font-awesome/css/font-awesome.min.css" />
</head>
<body>
    <div class="wrapper">
        <#include "templates/header.ftl">
        <div class="uui-main-container">
            <main>
                <p><b>Message:</b> ${message!""}</p>
                <br>
                <b>Stack Trace:</b>
                <#list stackTrace as line>
                    ${line}
                </#list>
            </main>
        </div>
        <#include "templates/footer.ftl">
    </div>
</body>
</html>
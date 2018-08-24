<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Home</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link rel="stylesheet" href="/resources/styles/js/lib/components/DataTables-1.10.2/css/jquery.dataTables.min.css" />

    <script src="/resources/styles/js/lib/jquery-1.12.0.min.js"></script>
    <!-- Bootstrap Core -->
    <link rel="stylesheet" href="/resources/styles/bootstrap/css/bootstrap.min.css" />
    <script src="/resources/styles/bootstrap/js/bootstrap.min.js"></script>
    <!-- EPAM UUI JavaScript Core -->
    <script src="/resources/styles/js/uui-core.min.js" type="text/javascript"></script>
    <!-- EPAM UUI Styles Core -->
    <link rel="stylesheet" href="/resources/styles/css/uui-all.css" />

    <link rel="stylesheet" href="/resources/styles/fonts/font-awesome/css/font-awesome.min.css" />
    <!-- jquery.dataTables.min.css should be placed before UUI stylesheets -->
    <script src="/resources/styles/js/lib/components/DataTables-1.10.2/js/jquery.dataTables.min.js"></script>

    <script src="/resources/styles/js/uui-rating.min.js"></script>
</head>
<body>
<div class="wrapper">
        <#include "templates/header.ftl">
    <div class="uui-main-container">
        <main class="text-center">
            <div class="uui-login-panel">
                <div class="login-panel-body">
                    <div class="login-panel-section">
                        <h1 class="section-title">Import tours from .csv file</h1>
                        <h1>Upload Status</h1>
                        <h2>Message : ${message}</h2>
                        <a href="/import" class="uui-button large blue">Back</a>
                    </div>
                </div>
            </div>
        </main>
    </div>
        <#include "templates/footer.ftl">
</div>
</body>
</html>
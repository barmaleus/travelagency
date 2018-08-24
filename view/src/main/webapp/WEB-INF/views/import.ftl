<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Home</title>
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

    <link rel="stylesheet" href="/resources/styles/fonts/font-awesome/css/font-awesome.min.css" />

    <link rel="stylesheet" href="/resources/styles/css/lib/components/jasny-bootstrap.min.css" />
    <script src="/resources/styles/js/lib/components/jasny-bootstrap.min.js"></script>
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

                        <form method="POST" action="/import" enctype="multipart/form-data">
                            <div class="uui-file-uploader fileinput fileinput-new large" data-provides="fileinput">
                                <span class="btn btn-file uui-button">
                                    <span class="fileinput-new">Choose...</span>
                                    <span class="fileinput-exists">Change...</span>
                                    <input type="file" name="file" />
                                </span>
                                <span class="fileinput-filename"></span>
                                <a href="#" class="close fileinput-exists" data-dismiss="fileinput">×</a>
                            </div>
                            <div>
                                <input type="submit" class="uui-button blue large" value="Submit" />
                            </div>
                        </form>


                        <script>
                            $('.fileinput').fileinput();
                        </script>
                    </div>
                </div>
            </div>
        </main>
    </div>
        <#include "templates/footer.ftl">
</div>
</body>
</html>
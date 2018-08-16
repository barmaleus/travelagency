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
</head>
<body>
    <div class="wrapper">
        <#include "templates/header.ftl">
        <div class="uui-main-container">
            <main>
                <div class="promo-section">
                    <div class="section-wrapper">
                        <div class="row">
                            <#list tours as tour>
                                <div class="col-lg-6 col-md-6 col-sm-6">
                                    <section class="uui-animated-image-panel" style="margin: 10px">
                                        <a href="/home/${tour.id}">
                                            <span class="animated-panel-image"><img src="${tour.photo}"></span>
                                            <span class="animated-panel-title">${tour.country.name} ${tour.date}</span>
                                            <span class="animated-panel-footer">${tour.duration} days for $${tour.cost}</span>
                                        </a>
                                    </section>
                                </div>
                            </#list>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <#include "templates/footer.ftl">
    </div>
</body>
</html>
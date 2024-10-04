<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: User</title>
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
                <div class="uui-person-horizontal-profile">
                    <div class="person-profile-body">
                        <div class="person-profile-section">
                            <div class="person-profile-photo">
                                <img src="/resources/styles/images/icons/no_photo.png" alt="" />
                            </div>
                            <div class="person-profile-info">
                                <h2>${user.login}</h2>
                                <p>member</p>
                            </div>
                            <#if tours?has_content>
                                Tours:
                                <ul>
                                    <#list tours as tour>
                                        <li>
                                            ${tour.country.name} ${tour.date}
                                        </li>
                                    </#list>
                                </ul>
                            </#if>
                            <#if reviews?has_content>
                                Reviews:
                                <ul>
                                    <#list reviews as review>
                                        <li>
                                            ${review.tour.country.name} ${review.tour.tourType} ${review.date}
                                            <br>
                                            ${review.text}
                                            <br>
                                        </li>
                                    </#list>
                                </ul>
                            </#if>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <#include "templates/footer.ftl">
    </div>
</body>
</html>
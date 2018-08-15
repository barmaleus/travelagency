<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Tour</title>
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
                <div>
                ${tour.country.name} <br>
                    Date: ${tour.date} <br>
                    Duration: ${tour.duration} days<br>
                    <img src="${tour.photo}"> <br>
                    Hotel:
                    <a href="/hotels/${tour.hotel.id}">${tour.hotel.name}</a>,
                    ${tour.hotel.stars} stars <br>
                    Description: <br>
                    ${tour.description} <br>
                    Cost: $${tour.cost} <br>
                </div>
                Reviews:
                <br>
            <#list reviews as review>
                <article>
                    <a href="/users/${review.user.id}">${review.user.login}</a> ${review.date}
                    <br>
                    ${review.text}
                </article>
            </#list>
            </main>
        </div>

        <#include "templates/footer.ftl">
    </div>
</body>
</html>
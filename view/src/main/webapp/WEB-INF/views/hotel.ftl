<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Hotel</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <script src="/resources/styles/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="/resources/styles/bootstrap/css/bootstrap.min.css" />
    <script src="/resources/styles/bootstrap/js/bootstrap.min.js"></script>
    <script src="/resources/styles/js/uui-core.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/resources/styles/css/uui-all.css" />
    <link rel="stylesheet" href="/resources/styles/fonts/font-awesome/css/font-awesome.min.css" />

    <style>
        #map {
            height: 400px;
            width: 100%;
        }
    </style>

</head>
<body>
<div class="wrapper">
        <#include "templates/header.ftl">
    <div class="uui-main-container">
        <main>
            <div class="uui-person-vertical-profile">
                <div class="person-profile-body">
                    <div class="person-profile-section">
                        <div class="person-profile-info">
                            <h2>${hotel.name}</h2>
                            <p>${hotel.stars} stars</p>
                            <p>
                                <a href="${hotel.website}">${hotel.website}</a>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="person-profile-footer">
                    <div class="person-profile-section">
                        <h2>Additional features:</h2>
                        <#list hotel.features as feature>
                            <p>
                                ${feature}
                            </p>
                        </#list>
                        <h2>Location:</h2>
                        Coordinates: ${hotel.latitude} ${hotel.longitude}

                        <div id="map"></div>

                        <script>
                            function initMap() {
                                var uluru = {lat: ${hotel.latitude}, lng: ${hotel.longitude} };
                                var map = new google.maps.Map(
                                        document.getElementById('map'), {zoom: 6, center: uluru});
                                var marker = new google.maps.Marker({position: uluru, map: map});
                            }
                        </script>
                        <script async defer
                                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA50YqdWhaTZL_IVtYhvpufEDsH7HrT7Is&callback=initMap">
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
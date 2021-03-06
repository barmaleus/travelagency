<#ftl encoding="utf-8" >
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Tours</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link rel="stylesheet" href="../../../resources/styles/js/lib/components/DataTables-1.10.2/css/jquery.dataTables.min.css" />

    <script src="../../../resources/styles/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="../../../resources/styles/bootstrap/css/bootstrap.min.css" />
    <script src="../../../resources/styles/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../resources/styles/js/uui-core.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="../../../resources/styles/css/uui-all.css" />
    <link rel="stylesheet" href="../../../resources/styles/fonts/font-awesome/css/font-awesome.min.css" />
    <script src="../../../resources/styles/js/lib/components/DataTables-1.10.2/js/jquery.dataTables.min.js"></script>
    <script src="../../../resources/styles/js/uui-rating.min.js"></script>
</head>
<body>
    <div class="wrapper">
        <#include "../templates/header.ftl">
        <div class="uui-main-container">
            <main>
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <table class="uui-table dynamic stripe records-per-page search showing-pages paging">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Country</th>
                                <th>Date</th>
                                <th>Duration</th>
                                <th>Description</th>
                                <th>Hotel</th>
                                <th>Hotel stars</th>
                                <th>Type of vacation</th>
                                <th>Cost</th>
                                <@security.authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                                <th>Actions</th>
                                </@security.authorize>
                            </tr>
                            </thead>
                            <tbody>
                                <#list tours as tour>
                                <tr>
                                    <td>${tour.id?c}</td>
                                    <td><a href="/tours/${tour.id?c}">${tour.country.name}</a></td>
                                    <td>${tour.date}</td>
                                    <td>${tour.duration} days</td>
                                    <td>
                                    <#if tour.description?length gte 30>
                                        ${tour.description?substring(0, 30)}...
                                    <#else>
                                        ${tour.description}
                                    </#if>
                                    </td>
                                    <td><a href="/hotels/${tour.hotel.id?c}">${tour.hotel.name}</a></td>
                                    <td>${tour.hotel.stars}</td>
                                    <td>${tour.tourType}</td>
                                    <td>$${tour.cost}</td>
                                    <@security.authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                                    <td>
                                        <form action="/tours/${tour.id?c}/update" method="get" style="display: inline">
                                            <button type="submit" class="uui-button orange small">Update</button>
                                        </form>
                                        <form action="/del-tour" method="post" style="display: inline">
                                            <input type="hidden" name="tourId" value="${tour.id?c}">
                                            <button type="submit" onclick="confirm('Do you really want to delete this tour?')" class="uui-button raspberry small">Delete</button>
                                        </form>
                                    </td>
                                    </@security.authorize>
                                </tr>
                                </#list>
                            </tbody>
                        </table>
                        <@security.authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                        <form action="/new-tour">
                            <button type="submit" class="uui-button blue">Add new tour</button>
                        </form>
                        </@security.authorize>
                    </div>
                </div>

                <script>
                    var tDyn = $('.uui-table.dynamic'),
                            domElements = '';
                    tDyn.addClass('dataTable');
                    if (tDyn.hasClass('records-per-page')) {
                        domElements += 'l';
                    }
                    else {domElements += '';}
                    if (tDyn.hasClass('search')) {
                        domElements += 'f';
                    }
                    domElements += '<t>';
                    if (tDyn.hasClass('showing-pages')) {
                        domElements += 'i';
                    }
                    if (tDyn.hasClass('paging')) {
                        domElements += 'p';
                    }
                    tDyn.dataTable( {
                        'dom': domElements
                    });
                </script>
            </main>
        </div>
        <#include "../templates/footer.ftl">
    </div>
</body>
</html>
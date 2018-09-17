<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Reviews</title>
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
                <div class="col-md-8 col-md-offset-2">
                    <table class="uui-table dynamic stripe records-per-page search showing-pages paging" id="table-users">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Date</th>
                            <th>Text</th>
                            <th>Author</th>
                            <th>Tour</th>
                            <@security.authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                                <th>Actions</th>
                            </@security.authorize>
                        </tr>
                        </thead>
                        <tbody>
                            <#list reviews as review>
                            <tr>
                                <td>${review.id?c}</td>
                                <td>${review.date}</td>
                                <td>
                                    <#if review.text?length gte 30>
                                        ${review.text?substring(0, 30)}...
                                    <#else>
                                        ${review.text}
                                    </#if>
                                </td>
                                <td><a href="/users/${review.user.id?c}">${review.user.login}</a></td>
                                <td><a href="/tours/${review.tour.id?c}">${review.tour.country.name} ${review.tour.date}</a></td>
                                <@security.authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                                <td>
                                    <button class="uui-button orange small" onclick="alert('This function will be realized later!')" >Update</button>
                                    <form action="/del-review" method="post" style="display: inline">
                                        <input type="hidden" name="reviewId" value="${review.id?c}">
                                        <button type="submit" onclick="confirm('Do you really want to delete this review?')" class="uui-button raspberry small">Delete</button>
                                    </form>
                                </td>
                                </@security.authorize>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                    <@security.authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                        <form action="/new-review">
                            <button type="submit" class="uui-button blue">Add new review</button>
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
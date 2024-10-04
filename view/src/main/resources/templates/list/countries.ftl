<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8" />
    <title>Travel Agency :: Countries</title>
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
                <div class="col-md-6 col-md-offset-3">
                    <table class="uui-table dynamic stripe records-per-page search showing-pages paging" id="table-users">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <@security.authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                                <th>Actions</th>
                            </@security.authorize>
                        </tr>
                        </thead>
                        <tbody>
                            <#list countries as country>
                            <tr>
                                <td>${country.id?c}</td>
                                <td>${country.name}</td>
                                <@security.authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                                <td>
                                    <form action="/countries/${country.id?c}/update" method="get" style="display: inline">
                                        <button type="submit" class="uui-button orange small">Update</button>
                                    </form>
                                    <form action="/del-country" method="post" style="display: inline">
                                        <input type="hidden" name="countryId" value="${country.id?c}">
                                        <button type="submit" onclick="confirm('Do you really want to delete this country?')" class="uui-button raspberry small">Delete</button>
                                    </form>
                                </td>
                                </@security.authorize>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                    <@security.authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                        <form action="/new-country">
                            <button type="submit" class="uui-button blue">Add new country</button>
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
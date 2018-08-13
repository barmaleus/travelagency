<html>
<head>
    <title>Travel Agency :: Home Page</title>
</head>
<body>
    <#include "templates/header.ftl">
    <ul>
        <#list tours as tour>
            <li>
                ${tour.id} ${tour.country} ${tour.cost}
            </li>
        </#list>
    </ul>
    <#include "templates/footer.ftl">
</body>
</html>
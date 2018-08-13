<html>
<head>
    <title>Travel Agency :: Countries</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
</head>
<body>
<#include "templates/header.ftl">
<ul>
    <#list countries as country>
        <li>
            ${country.id} ${country.name}
        </li>
    </#list>
</ul>
<#include "templates/footer.ftl">
</body>
</html>
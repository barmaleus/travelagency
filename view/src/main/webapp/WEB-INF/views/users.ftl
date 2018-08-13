<html>
<head>
    <title>Travel Agency :: Users</title>
</head>
<body>
<#include "templates/header.ftl">
<ul>
    <#list users as user>
        <li>
            ${user.id} ${user.login}
        </li>
    </#list>
</ul>
<#include "templates/footer.ftl">
</body>
</html>
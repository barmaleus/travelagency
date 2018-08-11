<html>
<head>
    <title>Travel Agency</title>
</head>
<body>
    <#list tours as tour>
        ${tour.id} ${tour.country} ${tour.cost}
    </#list>
</body>
</html>
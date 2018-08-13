<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<#include "templates/header.ftl">
<ul>
    <form action="/sign-up" method="post">
        <input name="login" type="text" placeholder="login" required autofocus/>
        <input name="password" type="password" placeholder="password" required/>
        <#--<input name="password" type="password" placeholder="confirm password" required/>-->
        <input name="submit" type="submit"/>
    </form>
</ul>
<#include "templates/footer.ftl">
</body>
</html>
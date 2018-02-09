
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>ProjectManager</title>
</head>

<body>
    <#if error>
        <div class="danger">Invalid user or password</div>
    </#if>
        <div class="formBlock_logIn">
            <form method="post">
                <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
                <input type="text" name="username" value="" placeholder="User Name">
                <input type="password" name="password" value="" placeholder="Password">
                <input type="submit" value="Login">
            </form>
            <div class="createAnAccount"> <a href="/login/registration"> Create an Account </a> </div>
        </div>


<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="/js/loginPage.js"> </script>
</body>

</html>
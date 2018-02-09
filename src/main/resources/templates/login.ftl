
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>ProjectManager</title>
    <link rel="stylesheet" type="text/css" href="/css/foundation.min.css">
    <link rel="stylesheet" type="text/css" href="/css/app.css">
</head>

<body>
<#if error??>
<div class="danger">Invalid user or password</div>
</#if>
<div class="row">
    <div class="large-3 column"></div>
    <div class="large-6 large-centered column">
        <div class="formBlock_logIn">
            <form method="post">
            <#--<input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">-->
                <input type="text" name="username" value="" placeholder="User Name">
                <input type="password" name="password" value="" placeholder="Password">
                <input type="submit" value="Login">
            </form>
            <div class="createAnAccount"> <a href="/reg/"> Create an Account </a> </div>
        </div>

    </div>
    <div class="large-3 column"></div>

</div>



<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</body>

</html>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/css/foundation.min.css">
    <link rel="stylesheet" type="text/css" href="/css/app.css">
    <meta charset="utf-8">
    <title>Registration</title>
</head>

<body>
<#import "/spring.ftl" as spring/>

<div class="loginPage-wrapper">
    <div class="row">
        <div class="large-3 column"></div>
        <div class="large-6 large-centered column">


            <div class="form-wrapper">
                <div class="formBlock_signUp">
                    <form action="/reg/do.reg" method="post">
                        <#--<input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">-->
                        <@spring.bind path= "newUser" />
                        <@spring.formInput "newUser.username","placeholder='First name'" />
                        <@spring.showErrors "newUser.username","error" />
                        <@spring.formInput "newUser.surname","placeholder='Last name'" />
                        <@spring.showErrors "newUser.surname","error" />
                        <@spring.formInput "newUser.email","placeholder='Email'" />
                        <@spring.showErrors "newUser.email","error" />
                        <p><input name="role" type="radio" value="DEVELOPER">Developer</p>
                        <p><input name="role" type="radio" value="MANAGER" checked>Manager</p>
                        <@spring.formInput "newUser.password","placeholder='Password'" />
                        <@spring.showErrors "newUser.password","error" />
                        <@spring.formInput "newUser.passwordCheck","placeholder='Confirm Password'" />
                        <@spring.showErrors "newUser.passwordCheck","error" />
                        <input type="submit" value="SignUp">
                            <div class="warning-wrapper">
                            <#if error ??>
                                <div class="danger">${error}</div>
                            </#if>
                            </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="large-3 column"></div>
    </div>


</div>


<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</body>

</html>
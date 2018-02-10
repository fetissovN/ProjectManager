
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>ProjectManager</title>
</head>

<body>
<#include "bar.ftl"/>
    <#--<#if error>-->
        <#--<div class="danger">${error}</div>-->
    <#--</#if>-->
    <div class="row">
        <div class="large-3 column">
            <button class="button" id="create-pr">Create new project</button>
            <div class="form-container-project" style="display: none">
                <form class="form-cr-pr" method="post">
                    <label> Name
                        <input type="text" name="name">
                    </label>
                    <label> Description
                        <input type="text" name="description">
                    </label>
                    <input type="submit" value="Save">
                </form>

                <button class="button" id="close-pr-id">Close</button>
            </div>
        </div>
        <div class="large-9 column">

        </div>


<script src="/js/main.js"> </script>
</body>

</html>
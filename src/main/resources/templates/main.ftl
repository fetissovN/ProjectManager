
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
                        <input type="text" name="title">
                    </label>
                    <label> Description
                        <input type="text" name="description">
                    </label>
                </form>
                <input id="form-cr-pr-submit" type="submit" class="button" value="Save">
                <button class="button" id="close-pr">Close</button>
            </div>
        </div>
        <div class="large-9 column">
            <div class="project-container" style="display: none"></div>
            <div class="task-container" style="display: none"></div>
        </div>


<script src="/js/main.js"> </script>
</body>

</html>
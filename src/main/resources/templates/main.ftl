
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>ProjectManager</title>
</head>

<body>
<#include "bar.ftl"/>
    <#if error??>
        <div class="danger">${error}</div>
    </#if>

<div class="row">
        <div class="large-3 column">
            <button class="button" id="create-pr" style="display: none">Create new project</button>
            <button class="button" id="create-tk" style="display: none">Create new task</button>
            <div class="drop-container" style="display: none">
                <select class="dropdown" id="dropdown" size="1">
                    <option disabled>Choose Developer</option>
                </select>
                <button class="button" id="choose-developer" >Add developer</button>
                <button class="button" id="choose-developer-close" >Close</button>
            </div>
            <div class="developers-container"></div>
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
            <div class="form-container-task" style="display: none">
                <form class="form-cr-tk" method="post">
                    <label> Name
                        <input type="text" name="name">
                    </label>
                    <label> Description
                        <input type="text" name="description">
                    </label>
                </form>
                <input id="form-cr-tk-submit" type="submit" class="button" value="Save">
                <button class="button" id="close-tk">Close</button>
            </div>
        </div>
        <div class="large-9 column">
            <button class="button" id="showProjects" style="alignment: center">Show projects</button>

            <div class="project-container" style="display: none"></div>
            <div class="task-container" style="display: none"></div>
        </div>


<script src="/js/main.js"> </script>
</body>

</html>
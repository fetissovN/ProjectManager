
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
            <h5 class="task-status"></h5>
            <div class="drop-container" style="display: none">
                <select class="dropdown" id="dropdown" size="1">
                    <option disabled>Choose Developer</option>
                </select>
                <button class="button" id="choose-developer" >Add developer</button>
            </div>
            <div class="developers-container"></div>
        </div>
        <div class="large-9 column">
            <h4 data-id="${id}" class="task-name"></h4>
            <h5 class="description"></h5>
            <div class="task-container"></div>

            <div class="comment-container">
                <label>  Type your comment here:
                    <input type="text" class="input-group-field">
                </label>
                <br>
                <button class="button" id="add-comment" style="float: right">Add</button>
            </div>
        </div>


<script src="/js/task.js"> </script>
</body>

</html>

var userId;
var role;
var projectIdClicked;

var projects = [];


function getUserInfoAjax() {
    $.ajax({
        type: 'GET',
        async: false,
        url: '/api/main/getUserInfo',
        success: function(data){
            if('user' in data){
                console.log(data);
                var roleP = $('#role');
                roleP.text(data.user.role);
                role = data.user.role;
                userId = data.user.id;
                // document.location.href = '/';
            }
        },
        error: function () {
            alert('fail');
        }
    });
}

function getUserProjectsAjax() {
    $.ajax({
        type: 'GET',
        url: '/api/main/getUserProjects/'+userId,
        success: function(data){
            console.log(data);
            for (var i = 0; i < data.length; i++){
                projects.push(data[i]);
            }
            createProjectsList();
        },
        error: function () {
            alert('fail');
        }
    });
}

function saveNewProjectAjax() {
    $.ajax({
        type: 'POST',
        data: $('.form-cr-pr').serialize(),
        url: '/api/main/saveNewProject/'+userId,
        success: function(data){
            console.log(data);
            $('.project-container').html('');
            projects = [];
            getUserProjectsAjax();
        },
        error: function () {
            alert('failToSave');
        }
    });
}

function saveNewTaskAjax(projectId) {
    $.ajax({
        type: 'POST',
        data: $('.form-cr-tk').serialize(),
        url: '/api/main/saveNewTask/'+projectId+'/user/'+userId,
        success: function(data){
            console.log(data);
            //todo clear view and create task list
        },
        error: function () {
            alert('failToSave');
        }
    });
}

function getProjectTasks(id) {
    $.ajax({
        type: 'GET',
        url: '/api/main/getProjectTasks/'+id,
        success: function(data){
            console.log(data);
            createTaskList(data);
        },
        error: function () {
            alert('failToLoad');
        }
    });
}

function createTaskList(taskList) {
    var container = $('.task-container');
    var table = $('<table></table>');
    for (var i = 0; i < taskList.length; i++){
        var tr = $('<tr></tr>');
        var task = $('<div></div>');
        task.attr('data-id', taskList[i].id);
        task.on('click',function () {
            // $('.project-container').hide();
            // $('.task-container').show();
            var tkId = this.getAttribute('data-id');
            console.log(tkId);
            //todo
        });
        var name = $('<p></p>');
        name.addClass('pr-name');
        name.text("Task: "+taskList[i].name);
        task.append(name);
        tr.append(task);
        table.append(tr)
    }
    container.append(table);
}

function createProjectsList() {
    var container = $('.project-container');
    var table = $('<table></table>');
    for (var i = 0; i < projects.length; i++){
        var tr = $('<tr></tr>');

        var project = $('<div></div>');
        project.attr('data-id', projects[i].id);
        project.on('click',function () {
            $('.project-container').hide();
            $('.task-container').show();
            var prId = this.getAttribute('data-id');
            console.log(prId);
            hideAddProjectButton();
            showAddTaskButton();
            getProjectTasks(prId);
            projectIdClicked = prId;

        });
        var name = $('<p></p>');
        name.addClass('pr-name');
        name.text("Project: "+projects[i].title);
        project.append(name);
        tr.append(project);
        table.append(tr)
    }
    container.append(table);
}

function loadUserProjects() {
    console.log('load');
    if (userId != null && role != null){
        console.log('no null');
        if(role == "MANAGER"){
            console.log('manager');
            getUserProjectsAjax();
        }
    }else {
        console.log('else');
    }
}

function hideAddProjectButton() {
    $('#create-pr').hide();
    $('.form-container-project').hide();
}

function hideAddTaskButton() {
    $('#create-tk').hide();
}

function showAddProjectButton() {
    $('#create-pr').show();
    // $('.form-container-project').show();
}

function showAddTaskButton() {
    $('#create-tk').show();
}

$('#create-tk').on('click', function () {
    $('.form-container-task').show();
});

$('#close-tk').on('click', function () {
    $('.form-container-task').hide();
});

$('#create-pr').on('click', function () {
    $('.form-container-project').show();
});

$('#close-pr').on('click', function () {
    $('.form-container-project').hide();
});

$('#showProjects').on('click', function () {
    $('.form-container-task').hide();
    $('.task-container').html('');
    $('.task-container').hide();
    $('.project-container').show();
    hideAddTaskButton();
    showAddProjectButton();
});

$('#form-cr-pr-submit').on('click', function () {
    saveNewProjectAjax();
    $('.form-container-project').hide();
});

$('#form-cr-tk-submit').on('click', function () {
    saveNewTaskAjax(projectIdClicked);
    $('.form-container-task').hide();
});


getUserInfoAjax();
showAddProjectButton();
$('.project-container').show();
loadUserProjects();
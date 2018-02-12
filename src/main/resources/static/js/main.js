
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
            showDropdownDevelopers();
            showContainerDevelopers();
            createTaskList(data);
        },
        error: function () {
            alert('failToLoad');
        }
    });
}

function loadAllDevelopersAjax(){
    $.ajax({
        type: 'GET',
        url: '/api/main/getAllDevelopers',
        success: function(data){
            console.log(data);
            fillDropBox(data);
        },
        error: function () {
            alert('failToLoadDevelopers');
        }
    });
}

function addDeveloperToProjectAjax(devId){
    var data = {
        developerId:null,
        projectId:null
    };
    data.developerId = devId;
    data.projectId = projectIdClicked;
    var request = JSON.stringify(data);
    $.ajax({
        type: 'POST',
        data: request,
        contentType: 'application/json',
        url: '/api/main/addDeveloperToProject',
        success: function(data){
            console.log(data);
            $('.developers-container').show();
        },
        error: function () {
            alert('fail');
        }
    });
}

function loadAllDevelopersOfProjectAjax(){
    $.ajax({
        type: 'GET',
        url: '/api/main/getAllDevelopersOfProject/'+projectIdClicked,
        success: function(data){
            console.log(data);
            $('.developers-container').show();
        },
        error: function () {
            alert('fail');
        }
    });
}

function fillDropBox(data) {
    var container = $('#dropdown');
    for (var i = 0; i < data.length; i++){
        var option = $('<option></option>');
        option.attr('value',data[i].id);
        option.text(data[i].username + ' ' + data[i].surname);
        container.append(option);
    }
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

function showContainerDevelopers() {
    $('.developers-container').show();
    if (role == "MANAGER"){
        loadAllDevelopersOfProjectAjax();
    }
    // loadAllDevelopersAjax();
}

function hideContainerDevelopers() {
    $('.developers-container').hide();
}

function showDropdownDevelopers() {
    $('.drop-container').show();
    loadAllDevelopersAjax();
}

function hideDropdownDevelopers() {
    $('.drop-container').hide();

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

$('#choose-developer').on('click', function () {
    var devId = $('#dropdown option:selected').val();
    addDeveloperToProjectAjax(devId);
    hideDropdownDevelopers();
});

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
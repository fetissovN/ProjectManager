
var taskId;
var userId;
var role;
var userCreator;
var projectId;

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
            }
        },
        error: function () {
            alert('fail');
        }
    });
}

function getTaskInfoAjax() {
    $.ajax({
        type: 'GET',
        url: '/api/task/getTaskInfo/'+taskId,
        success: function(data){
            console.log(data);
            var taskName = $('.task-name');
            taskName.text(data.name);
            projectId = data.projectId;
            userCreator = data.userId;
            $('.task-name').text('Task: ' + data.name);
            $('.description').text('Description: ' + data.description);
            $('.task-status').text('Status: ' + data.status);
        },
        error: function () {
            alert('fail');
        }
    });
}

function loadAllDevelopersForTaskAjax() {
    $.ajax({
        type: 'GET',
        url: '/api/main/getAllDevelopers/',
        success: function(data){
            console.log(data);
            fillDropBox(data);
        },
        error: function () {
            alert('fail');
        }
    });
}

function loadAllDevelopersOfTaskAjax() {
    $.ajax({
        type: 'GET',
        url: '/api/task/getDevelopersOfTask/'+taskId,
        success: function(data){
            console.log(data);
            var container = $('.developers-container');
            container.html('');
            var h5 = $('<h5></h5>');
            h5.html('All developers of task:');
            container.append(h5);
            for(var i = 0;i<data.length;i++){
                var p = $('<p></p>');
                p.text(data[i].username + ' ' + data[i].surname);
                container.append(p);
            }
        },
        error: function () {
            alert('fail');
        }
    });
}

function addDeveloperToTaskAjax(devId){
    var data = {
        developerId:null,
        taskId:null
    };
    data.developerId = devId;
    data.taskId = taskId;
    var request = JSON.stringify(data);
    $.ajax({
        type: 'POST',
        data: request,
        contentType: 'application/json',
        url: '/api/task/addDeveloperToTask',
        success: function(data){
            console.log(data);
            $('.developers-container').html('');
            showContainerDevelopers();
        },
        error: function () {
            alert('fail');
        }
    });
}

function showContainerDevelopers() {
    $('.developers-container').show();
    loadAllDevelopersOfTaskAjax();
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

var taskNameContainer = $('.task-name');
var task = taskNameContainer.attr('data-id');
console.log(task);
taskId = task;
getUserInfoAjax();
getTaskInfoAjax();

loadAllDevelopersOfTaskAjax();
if (role == "MANAGER"){
    console.log('manager');
    $('.drop-container').show();
    loadAllDevelopersForTaskAjax();
}else if (role == "DEVELOPER"){
    console.log('developer');
}else {
    document.location.href = "/log/";
}

$('#choose-developer').on('click',function () {
    var devId = $('#dropdown option:selected').val();
    addDeveloperToTaskAjax(devId);
});

$('')




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
            $('.task-status').text(data.status);
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

function loadAllCommentsAjax() {
    $.ajax({
        type: 'GET',
        url: '/api/task/getAllComments/' + taskId,
        success: function(data){
            console.log(data);
            $('.comm-tb').html('');
            createListComments(data);
        },
        error: function () {
            alert('fail');
        }
    });
}

function saveNewCommentAjax(comment) {
    var data = {
        userId:null,
        taskId:null,
        comment:null
    };
    data.userId = userId;
    data.taskId = taskId;
    data.comment = comment;
    var request = JSON.stringify(data);
    console.log(request);
    $.ajax({
        type: 'POST',
        data: request,
        contentType: 'application/json',
        url: '/api/task/saveNewComment',
        success: function(data){
            createSingleComment(data)
        },
        error: function () {
            alert('fail');
        }
    });
}

function deleteCommentAjax(id) {
    $.ajax({
        type: 'DELETE',
        url: '/api/task/deleteComment/' + id,
        success: function(data){
            console.log(data);
            var commentToDelete = $('.comm-option-delete[data-id='+id+']');
            var parent = commentToDelete.parent();
            console.log(parent);
            parent.remove();
        },
        error: function () {
            alert('fail');
        }
    });
}

function updateCommentAjax(id, text) {
    var data = {
        commentId:null,
        text:null
    };
    data.commentId = id;
    data.text = text;
    var request = JSON.stringify(data);
    $.ajax({
        type: 'POST',
        data: request,
        contentType: 'application/json',
        url: '/api/task/updateComment',
        success: function(data){
            console.log(data);
            if(data == 'updated'){
                var commentToUpdate = $('.comment[data-id='+id+']');
                commentToUpdate.text(text);
                closeUpdateBox(id);
            }

        },
        error: function () {
            alert('fail');
        }
    });
}

function changeStatusAjax(status) {
    var data = {
        taskId:null,
        status:null
    };
    data.taskId = taskId;
    data.status = status;
    var request = JSON.stringify(data);
    console.log(request);
    $.ajax({
        type: 'POST',
        data: request,
        contentType: 'application/json',
        url: '/api/task/updateTaskStatus',
        success: function(data){
            console.log(data);
            $('.task-status').text(status);

        },
        error: function () {
            alert('fail');
        }
    });
}

function createSingleComment(data) {
    var table = $('.comm-tb');

    var tr = $('<tr></tr>');
    var p = $('<p></p>');
    p.text(data.comment);
    p.attr('data-id',data.id);
    p.addClass('comment');
    var pDel = $('<a></a>');
    pDel.text('delete');
    pDel.attr('data-id', data.id);
    pDel.addClass('comm-option-delete');
    var pUpd = $('<a></a>');
    pUpd.text('update');
    pUpd.attr('data-id', data.id);
    pUpd.addClass('comm-option-update');
    tr.append(p);
    tr.append(pUpd);
    tr.append(pDel);
    table.prepend(tr);
}

function createListComments(commentsList) {
    for (var i = 0; i < commentsList.length; i++){
        createSingleComment(commentsList[i]);
    }
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

function showCommentUpdateBox(id) {
    var commentToUpdate = $('.comment[data-id='+id+']');
    var parent = commentToUpdate.parent();
    var div = $('<div class="update-comment-container"></div>');
    var input = $('<input type="text" class="update-input">');
    var button = $('<button class="update-comment-button button" type="button">ok</button>');
    var buttonClose = $('<button class="update-comment-button-close button" type="button">forbid</button>');
    button.on('click',function () {
        var text = input.val();
        console.log(text);
        updateCommentAjax(id,text);
    });
    buttonClose.on('click', function () {
        $('.update-comment-container').remove();
    });
    input.val(commentToUpdate.text());
    div.append(input);
    div.append(button);
    div.append(buttonClose);
    parent.append(div);

}

function closeUpdateBox(id) {
    $('.update-comment-container').remove();
}

var taskNameContainer = $('.task-name');
var task = taskNameContainer.attr('data-id');
console.log(task);
taskId = task;
getUserInfoAjax();
getTaskInfoAjax();

loadAllDevelopersOfTaskAjax();

loadAllCommentsAjax();
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

$('#add-comment').on('click', function () {
    //todo
    console.log('click');
    var comment = $('.input-group-field').val();
    saveNewCommentAjax(comment);
});

$(document).on('click', '.comm-option-delete', function () {
    var id = this.getAttribute('data-id');
    deleteCommentAjax(id);
});
$(document).on('click', '.comm-option-update', function () {
    var id = this.getAttribute('data-id');
    showCommentUpdateBox(id);
});

$('#showChangeStatus').on('click', function () {
   $('.dropdown-status-container').show();
});

$('#changeStatus').on('click', function () {
    var statusNew = $('.dropdown-status option:selected').val();
    console.log(statusNew);
    changeStatusAjax(statusNew);
    $('.dropdown-status-container').hide();
});






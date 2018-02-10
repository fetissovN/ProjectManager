
var userId;
var role;

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
            var container = $('.project-container');
            for (var i = 0; i < data.length; i++){
                var project = $('<div></div>');
                project.attr('data-id', data[i].id);
                var name = $('<p></p>');
                name.text(data[i].title);
                project.append(name);
                container.append(project);
            }
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

        },
        error: function () {
            alert('failToSave');
        }
    });
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


$('#create-pr').on('click', function () {
    $('.form-container-project').show();
});

$('#close-pr').on('click', function () {
    $('.form-container-project').hide();
});

$('#form-cr-pr-submit').on('click', function () {
    saveNewProjectAjax();
    $('.project-container').html('');
    getUserProjectsAjax();
    $('.form-container-project').hide();
});





getUserInfoAjax();
loadUserProjects();
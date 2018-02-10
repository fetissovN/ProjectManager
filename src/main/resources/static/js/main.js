
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

function createProjectsList() {
    var container = $('.project-container');
    var table = $('<table></table>')
    for (var i = 0; i < projects.length; i++){
        var tr = $('<tr></tr>');
        tr.on('click',clickOnProject);
        var project = $('<div></div>');
        project.attr('data-id', projects[i].id);
        var name = $('<p></p>');
        name.addClass('pr-name');
        // name.style.marginLeft('40px');
        name.text(projects[i].title);
        project.append(name);
        tr.append(project);
        table.append(tr)
    }
    container.append(table);
}

function clickOnProject() {
    console.log("click");
    $('.project-container').hide();
    $('.task-container').show();

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
    $('.form-container-project').hide();
});


getUserInfoAjax();
$('.project-container').show();
loadUserProjects();
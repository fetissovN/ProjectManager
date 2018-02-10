
var userId;
var role;


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
            if('projects' in data){
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
$('#close-pr-id').on('click', function () {
    $('.form-container-project').hide();

});


getUserInfoAjax();
loadUserProjects();

var userId;
var role;


function getUserInfoAjax() {
    $.ajax({
        type: 'GET',
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

function loadUserProjects() {
    if (userId != null && role != null){

    }else {

    }
}



getUserInfoAjax();
loadUserProjects();
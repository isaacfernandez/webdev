(function () {
    var userService = new UserService();
    $(main);

    function main() {
        $("#updateBtn").click(update);
        $("#logoutBtn").click(logout);
        fetchProfile().then(function (user) {
            $("#usernameFld").val(user["username"]);
            $("#emailFld").val(user["email"]);
            $("#firstnameFld").val(user["firstName"]);
            $("#lastnameFld").val(user["lastName"]);
            $("#phoneFld").val(user["phone"]);
            $("#dobFld").val(user["dateOfBirth"]);
            $("#roleFld").val(user["role"]);
            console.log("Values set");
        });

    }

    //TODO: Include visual signs of success and failure
    function logout() {
        userService.logout();
    }


    function update() {
        var user = new User();
        user["username"] = $("#usernameFld").val();
        user["email"] = $("#emailFld").val();
        user["firstName"] = $("#firstnameFld").val();
        user["lastName"] = $("#lastnameFld").val();
        user["phone"] = $("#phoneFld").val();
        user["dateOfBirth"] = $("#dobFld").val();
        user["role"] = $("#roleFld").val();
        userService.update(user).then(updateSuccess, updateFail);
    }

    function updateSuccess(){
        console.log('update good!');
    }

    function updateFail() {
        console.log('update failed');
    }

    function fetchProfile() {
        return fetch('/profile', {
            'credentials': 'include'
        })
            .then(function (response) {
                return response.json();
            });
    }



})();

(function () {
    var userService = new UserService();
    $(main);

    function main() {
        $("#updateBtn").click(logout);
        $("#logoutBtn").click(update);
        fetchProfile().then(function (user) {
            console.log('promise run?');
            $("#usernameFld").val(user["username"]);
            $("#emailFld").val(user["email"]);
            $("#firstnameFld").val(user["firstname"]);
            $("#lastnameFld").val(user["lastname"]);
            $("#phoneFld").val(user["phone"]);
            $("#dobFld").val(user["dob"]);
            $("#roleFld").val(user["role"]);
            console.log("Values set");
        });

    }

    //TODO: Include visual signs of success and failure
    function logout() {
        userService.logout();
    }


    function update() {

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

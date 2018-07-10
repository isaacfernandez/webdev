(function () {
    var userService = new UserService();
    $(main);

    function main() {
        $("#loginBtn").click(login);
    }

    //TODO: Include visual signs of success and failure
    function login() {
        var username = $("#usernameFld").val();
        var pass = $("#passwordFld").val();
        if ( username.length > 0) {
            console.log("accessing as " + username + ":" + pass);
            status = userService.login(username, pass);
            console.log(status)
        } else {
            console.log("Error, something missing");
        }
    }
})();

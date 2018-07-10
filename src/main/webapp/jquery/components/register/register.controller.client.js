(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserService();
    $(main);

    function main() {
        $("#registerBtn").click(register);
    }
    function register() {
        var username = $("#usernameFld").val();
        var pass = $("#passwordFld").val();
        var pass2 = $("#confirmPasswordFld").val();
        if (pass == pass2 && username.length > 0) {
            console.log("Registering " + username + ":" + pass);
            console.log( userService.register() )
        } else {
            console.log("Error, something missing");
        }
        alert("Register button clicked!");
    }
})();

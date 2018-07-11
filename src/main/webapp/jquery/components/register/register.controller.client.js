(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserService();
    $(main);

    function main() {
        $("#registerBtn").click(register);
    }

    //TODO: Include visual signs of success and failure
    function register() {
        var username = $("#usernameFld").val();
        var pass = $("#passwordFld").val();
        var pass2 = $("#confirmPasswordFld").val();
        if (pass == pass2 && username.length > 0) {
            console.log("Registering " + username + ":" + pass);
            userService.register(username, pass).then(goToProfile,
                function(resp) {
                    console.log("no login" + resp);
                }
            )
        } else {
            console.log("Error, something missing");
        }
    }

    function goToProfile() {
        console.log('goto');
        window.location.href = 'http://localhost:8080/jquery/components/profile/profile.template.client.html';
    }
})();

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
        if (username.length > 0) {
            console.log("accessing as " + username + ":" + pass);
            userService.logon(username, pass).then(goToProfile,
                function (loss) {
                    console.log("Is this loss?")
                });
        } else {
            console.log("Error, something missing");
        }
    }

    function loginError() {
        //paint a pretty red picture on error
    }

    function goToProfile() {
        window.location.href = 'https://wbdv-s2-1.herokuapp.com/jquery/components/profile/profile.template.client.html';
    }
})();

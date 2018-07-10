function UserService() {
    this.register = register;
    this.url =
        'http://localhost:8080/api/user';
    this.login =
        'http://localhost:8080/api/login';


    self = this;

    function register(username, password) {
        var user = new User();
        user.setUsername(username);
        user.setPassword(password);
        console.log("Our register url is " + self.login)
        fetch(self.login, {
            method: 'post',
            body: JSON.stringify({username: username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        }).then( function(resp) {
            return resp.json()});
    }
}
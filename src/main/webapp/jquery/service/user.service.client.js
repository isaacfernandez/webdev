function UserService() {
    this.register = register;
    this.url =
        'http://localhost:8080/api/user';
    this.login =
        'http://localhost:8080/api/login';


    self = this;

    function register(user, pass) {
        console.log("Sending " + JSON.stringify({username:user, password: pass}))
        fetch(self.url, {
            method: 'post',
            body: JSON.stringify({ username: user, password: pass}),
            headers: {
                'content-type': 'application/json'
            }
        }).then( function(resp) {
            console.log("Sent the following:" + JSON.stringify({ username: user, password: pass}));
            return resp.json()});


    }
}
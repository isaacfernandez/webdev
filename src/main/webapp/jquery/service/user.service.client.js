function UserService() {
    this.register = register;
    this.logon = logon;
    this.logout = logout;
    this.update = update;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.url =
        'http://localhost:8080/api/user';
    this.login =
        'http://localhost:8080/api/login';
    this.outURL =
        'http://localhost:8080/api/logout';
    this.profile =
        'http://localhost:8080/api/profile'

    self = this;


    //Refactor, only difference is URL
    function register(user, pass) {
        console.log("Sending " + JSON.stringify({username: user, password: pass}))
        fetch(self.url, {
            method: 'post',
            body: JSON.stringify({username: user, password: pass}),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (resp) {
            console.log("Sent the following:" + JSON.stringify({username: user, password: pass}));
            return resp.json()
        });
    }

    function logon(user, pass) {
        console.log("Sending " + JSON.stringify({username: user, password: pass}))
        return fetch(self.login, {
            method: 'post',
            body: JSON.stringify({username: user, password: pass}),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (resp) {
            return resp.json()
        });
    }

    function logout() {
        return fetch(this.outURL, {
            method: 'post'
        }).then(function (resp) {
            return resp.json()
        });
    }

    function update(user) {
        return fetch(this.profile, {
            method: 'put',
            body: JSON.stringify(user),
            'credentials': 'include',
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (resp) {
            return resp.json()
        });
    }

    function deleteUser(id) {
        return fetch(self.url + "/" + id, {
            method: 'delete',
            'credentials': "include"
        }).then( function (resp) {
            return resp.json()
        });

    }

    function findAllUsers() {
        return  fetch(self.url, {
            method: 'get',
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (resp) {
            return resp.json()
        });
    }

}
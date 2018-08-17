function UserService() {
    this.register = register;
    this.addUser = addUser;
    this.logon = logon;
    this.logout = logout;
    this.update = update;
    this.updateProfile = updateProfile;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.url =
        'https://wbdv-s2-1.herokuapp.com/api/user';
    this.login =
        'https://wbdv-s2-1.herokuapp.com/api/login';
    this.outURL =
        'https://wbdv-s2-1.herokuapp.com/api/logout';
    this.profile =
        'https://wbdv-s2-1.herokuapp.com/api/profile';
    this.regURL =
        'https://wbdv-s2-1.herokuapp.com/api/register';

    self = this;


    function register(user, pass) {
        console.log("Sending " + JSON.stringify({username: user, password: pass}))
        return fetch(self.regURL, {
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

    //unlike register, this is for use with the admin interface -- avoids overwriting user session
    function addUser(user) {
        console.log('service adding user');
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (resp) {
            return resp.json();
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

    function updateProfile(user) {
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

    function update(user) {
        return fetch(this.url + "/" + user.id, {
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

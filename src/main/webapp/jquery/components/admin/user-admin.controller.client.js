(function () {

    var userService = new UserService();

    function init() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    $("#addBtn").click(addUser);
    init();

    function renderUsers(users) {
        console.log(users);

        var tbody = $('tbody');
        tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];

            var tr = $('<tr>');
            var td = $('<td>');
            td.append(user.username);
            tr.append(td);

            td = $('<td class="hide">');
            td.append(user.password);
            tr.append(td);

            td = $('<td>');
            td.append(user.firstName);
            tr.append(td);

            td = $('<td>');
            td.append(user.lastName);
            tr.append(td);

            td = $('<td>');
            td.append(user.role);
            tr.append(td);

            td = $('<td>');
            td.append('Student');
            tr.append(td);

            td = $('<td>');
            var deleteBtn = $('<button>DELETE</button>');
            deleteBtn.click(deleteUser);
            deleteBtn.attr('id', user.id);
            td.append(deleteBtn);
            tr.append(td);

            td = $('<td>');
            var editBtn = $('<button>EDIT</button>');
            editBtn.click(editUser);
            editBtn.attr('id', user.id);
            td.append(editBtn);
            tr.append(td);



            tr.appendTo(tbody);
        }
    }

    function readFromEdit() {
        var user = new User();
        user["username"] = $("#usernameFld").val();
        user["email"] = $("#emailFld").val();
        user["firstName"] = $("#firstNameFld").val();
        user["lastName"] = $("#lastNameFld").val();
        //user["phone"] = $("#phoneFld").val();
        //user["dob"] = $("#dobFld").val();
        user["role"] = $("#roleFld").val();
        return user;
    }

    function addUser() {
        var newUser = readFromEdit();
        userService.addUser(user).then(init);
    }

    function editUser(event) {
        var $btn = $(event.currentTarget);
        var uID = $btn.attr('id');
        var user = readFromEdit();
        user.id = uID;
        console.log(user);
        userService.update(user)
            .then(init);
    }

    function deleteUser(event) {
        console.log(event);
        var $button = $(event.currentTarget);
        var id = $button.attr('id');

        userService
            .deleteUser(id)
            .then(init);
    }
})();
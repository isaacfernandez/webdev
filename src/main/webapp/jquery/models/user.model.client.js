
function User(username, password, firstName, lastName) {
  this.username = username;
  this.password = password;
  this.firstName = firstName;
  this.lastName = lastName;

  this.setUsername =   setUsername;
  this.setPassword =   setPassword;
  this.setFirstName =  setFirstName;
  this.setLastName =   setLastName;

  this.getUsername =   getUsername;
  this.getPassword =   getPassword;
  this.getFirstName =   getFirstName;
  this.getLastName =  getLastName;

  function setUsername( username ) {
    this.username = username;
  }
  function setPassword( password ) {
    this.password = password;
  }
  function setFirstName( firstName ) {
    this.firstName = firstName;
  }
  function setLastName( lastName ) {
    this.lastName = lastName;
  }

  function getUsername( username ) {
    return this.username;
  }
  function getPassword( password ) {
    return this.password;
  }
  function getFirstName( firstName ) {
    return this.firstName;
  }
  function getLastName( lastName ) {
    return this.lastName;
  }




}

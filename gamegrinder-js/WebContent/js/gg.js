function isLoggedIn() {
	return (typeof angular.element('body').scope().currentUser != 'undefined');
}
/*
function setUser(username) {
	angular.injector(['ng', 'gamegrinderApp']).get("userService").login(username);
}
*/

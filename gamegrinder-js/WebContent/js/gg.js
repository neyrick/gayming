function isLoggedIn() {
	return (typeof angular.element('body').scope().currentUser != 'undefined');
}

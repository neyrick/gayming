'use strict';

/* Services */

gamegrinderApp.factory('userService', ['$cookies', function(cookies) {

	return {
		login : function(username) {
			cookies['ggUser'] = username;
		},

		logout : function() {
			delete cookies['ggUser'];
		}
	}
}]);

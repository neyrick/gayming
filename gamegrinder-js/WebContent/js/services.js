'use strict';

/* Services */
/*
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
*/
gamegrinderApp.factory('logger', [function() {

	return {
		log : function() {
		}
	}
}]);

gamegrinderApp.factory('config', [function() {

	return {
		urlbase : "http://localhost:8080/gg"
			
		}
}]);

gamegrinderApp.factory('settingsService', ['$http', 'config', function($http, config) {

	return {
				
		updateSettings : function(callback, callback2) {
			$http.get(config.urlbase + '/setting').success(callback).error(callback2);
		}
		
		
	}
}]);


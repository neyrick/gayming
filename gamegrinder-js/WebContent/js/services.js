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
		urlbase : "http://jafar/rs/gg"
			
		}
}]);

gamegrinderApp.factory('settingsService', ['$http', 'config', function($http, config) {

	return {
				
		updateSettings : function(callback, callback2) {
			$http.get(config.urlbase + '/setting').success(callback).error(callback2);
		}
		
		
	}
}]);

gamegrinderApp.factory('plannerService', ['$http', 'config', function($http, config) {

	return {
				
		validateGame : function(pm_dayid, pm_timeframecode, pm_settingid, pm_gmname, callback) {
			var game = {
				dayid: pm_dayid,
				timeframe: pm_timeframecode,
				setting: pm_settingid,
				gm: pm_gmname
			};
			$http.put(config.urlbase + '/game').success(callback);
		}
		
		
	}
}]);


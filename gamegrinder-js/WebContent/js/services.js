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
        toggleDispo : function(pm_player, pm_dayid, pm_timeframe, pm_setting, pm_role, isAvailable, callback) {
            var schedule = { dayid : pm_dayid, timeframe : pm_timeframe, player : pm_player, role : pm_role, setting : pm_setting};
            if (isAvailable) {
                $http.put(config.urlbase + '/schedule', schedule).success(callback);
            }
            else {
                $http.delete(config.urlbase + '/schedule', {data : schedule}).success(callback);
            }
        },
				
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


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
		urlbase : "http://jafar/rs/gg",
		FIRST_DAY_OF_WEEK : 5,		
			
		}
}]);

gamegrinderApp.factory('settingsService', ['$http', 'config', function($http, config) {

	return {
				
		updateSettings : function(callback, callback2) {
			$http.get(config.urlbase + '/setting').success(callback).error(callback2);
		}
		
		
	}
}]);

gamegrinderApp.factory('planningBuilderService', ['config', function(config) {

	return {
		MS_IN_DAY : 1000 * 60 * 60 * 24,

		getDayId : function(daytime) {
			var date = new Date(daytime);
			return date.getFullYear()*10000 + (date.getMonth()+1)*100 + date.getDate();
		},

		initDay : function(daytime) {
			var day = new Date(daytime);
			var result = new Object();
			result.id = this.getDayId(day);
			result.dow = day.getDay();
			result.dom = day.getDate();
			result.month = day.getMonth()+1;
			result.year = day.getFullYear();
			result.timeframes = [ { code : 'AFTERNOON', settings : [] }, { code : 'EVENING', settings : [] }];
			return result;
		},

		getDefaultMinDay : function() {
			
			var today = new Date();
			var currtime = today.getTime();
			var dow = today.getDay();

			// premier jour de debut anterieur
			var diff = config.FIRST_DAY_OF_WEEK - dow;
			if (dow < config.FIRST_DAY_OF_WEEK) diff -= 7;
			currtime += diff * this.MS_IN_DAY;

			return currtime;
		},

		buildWeeksPlanning : function(mindaytime, daycount, settings, schedules, games, comments) {
			
			// Initialisation des semaines
			var weeks = Array();
			var currtime;
			var maxtime = mindaytime + this.MS_IN_DAY * daycount;
			var currdow = 6;
			var currweek;
			var currday;
			var dayMap = new Object();
			for (currtime = mindaytime; currtime < maxtime; currtime += this.MS_IN_DAY) {
				currdow++;
				if (currdow > 6) {
					currdow = 0;
					currweek = { days : [] }
					weeks.push(currweek);
				}
				currday = this.initDay(currtime);
				currweek.days.push(currday);
				dayMap[currday.id] = currday;
			}

			// ajouter les schedule dans availablepj / available mj
			// ajouter les game
			// parcourir les comment et les ajouter
			return weeks;
		}
	};
}]);

gamegrinderApp.factory('plannerService', ['$http', 'config', 'planningBuilderService', function($http, config, planningBuilderService) {

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

		getPlanning : function(mindaytime, daycount, callback) {
			var minday = planningBuilderService.getDayId(new Date(mindaytime));
			var maxdaytime = mindaytime + daycount * planningBuilderService.MS_IN_DAY;
			var maxday = planningBuilderService.getDayId(new Date(maxdaytime));
			$http.get(config.urlbase + '/planning?minday=' + minday + '&maxday=' + maxday).success(callback);
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


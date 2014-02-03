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

	var settings = { ready : false };
	var updateSettings = function(callback, callback2) {
		$http.get(config.urlbase + '/setting').success(callback).error(callback2);
	};

	return {
		
		getSettings : function(callback) {
			if (!settings.ready) {
				updateSettings(function(result) {
					settings = result;
					settings.ready = true;
					callback(settings);
				}, function(error) {
					window.alert("Impossible de récupérer les chroniques: " + error);
				});
			}
			else callback(settings);
		}
		
	}
}]);

gamegrinderApp.factory('planningBuilderService', ['config', function(config) {

	var timeframeIndex = {
		'AFTERNOON' : 0,
		'EVENING' : 1
	};

	var mergeSetting = function (allSettings, currentArray, settingid) {
		var i;
		for (i = 0; i < currentArray.length; i++) {
			if (currentArray[i].settingid == settingid) return currentArray[i];
		}
		for (i = 0; i < allSettings.length; i++) {
			if (allSettings[i].id == settingid) {
				var settingref = allSettings[i];
				var newsetting = { settingid : settingref.id , code : settingref.code , name : settingref.name,
					mode : settingref.mode, status : settingref.status, games : [], availableplayers : [],
					availablegms : [], unavailable : [], mystatus : new tfSettingStatus(), hasgame : false };
				currentArray.push(newsetting);
				return newsetting;
			}
		}
		return undefined;
		
	}

    var addSchedule = function (rawschedule, timeframe, allSettings, me) {
        var g, game;
        var tfSetting = mergeSetting(allSettings, timeframe.settings, rawschedule.setting);
        var newschedule = { name : rawschedule.player, schedule : rawschedule.idschedule, game: rawschedule.game, idcomment : rawschedule.idcomment, comment : rawschedule.message  };
        if ( rawschedule.game != null) {  
            tfSetting.hasgame = true;
            game = null;
            for (g = 0; g  < tfSetting.games.length; g++) {
                if (tfSetting.games[g].id == rawschedule.game) game = tfSetting.games[g];
            }
            if (game == null) {
                game = { players : [] };
                tfSetting.games.push(game);                
            }
            if (rawschedule.role == 'GM') game.gm = newschedule;
            else if (rawschedule.role == 'PLAYER') game.players.push(newschedule); 
	    timeframe.gaming[rawschedule.player] = rawschedule.setting;
        }
	else {
	        if (rawschedule.role == 'GM') tfSetting.availablegms.push(newschedule);
        	else if (rawschedule.role == 'PLAYER') tfSetting.availableplayers.push(newschedule);
		else tfSetting.unavailable.push(newschedule);
	}
        if ( rawschedule.player == me) {
            if (rawschedule.role == 'GM')  {
                tfSetting.mystatus.dispoMJ = true;
                if (rawschedule.game != null) {
                    tfSetting.mystatus.mj = true;
                    timeframe.busy = true;
                    timeframe.mysetting = tfSetting.code;
                }
            }
            else if (rawschedule.role == 'PLAYER') {
                tfSetting.mystatus.dispoPJ = true;
                if (rawschedule.game != null) {
                    tfSetting.mystatus.pj = true;
                    timeframe.busy = true;
                }
            }
	    if (rawschedule.idcomment != null) tfSetting.idcomment = rawschedule.idcomment;
	    if (rawschedule.message != null) tfSetting.message = rawschedule.message;
        }
    }
    
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
			result.timeframes = [ { code : 'AFTERNOON', settings : [], busy : false, gaming : {} }, { code : 'EVENING', settings : [], busy : false, gaming : {} }];
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

		refreshTimeframeInWeeksPlanning : function(settings, schedules, timeframe, me) {
			var i;

            /*
            var tfSetting, tfSettings = timeframe.settings;
			for (i = 0; i < tfSettings.length; i++) {
				tfSetting = tfSettings[i];
				tfSetting.availablegms.length=0;
				tfSetting.availableplayers.length=0;
				tfSetting.unavailable.length=0;
				tfSetting.games.length=0;
                tfSetting.mystatus.dispoMJ = false;
                tfSetting.mystatus.dispoPJ = false;
                tfSetting.mystatus.pj = false;
                tfSetting.mystatus.mj = false;
			}
            */

            timeframe.busy = false;
            timeframe.gaming = {};
            timeframe.settings.length = 0;
			for (i = 0; i < schedules.length; i++) {
                addSchedule(schedules[i], timeframe, settings, me);
			}
		},

		buildWeeksPlanning : function(mindaytime, daycount, settings, schedules, me) {
			
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

			var i, timeframe, rawschedule;
			var tfSetting;
			for (i = 0; i < schedules.length; i++) {                
				rawschedule = schedules[i];
                timeframe = dayMap[rawschedule.dayid].timeframes[timeframeIndex[rawschedule.timeframe]];
                addSchedule(rawschedule, timeframe, settings, me);
			}

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

		getTimeframePlanning : function(dayid, timeframecode, callback) {
			$http.get(config.urlbase + '/planning?minday=' + dayid + '&maxday=' + dayid + '&timeframe=' + timeframecode).success(callback);
		},
				
		getPlanning : function(mindaytime, daycount, callback) {
			var minday = planningBuilderService.getDayId(new Date(mindaytime));
			var maxdaytime = mindaytime + daycount * planningBuilderService.MS_IN_DAY;
			var maxday = planningBuilderService.getDayId(new Date(maxdaytime));
			$http.get(config.urlbase + '/planning?minday=' + minday + '&maxday=' + maxday).success(callback);
		},
				
		validateGame : function(schedule_id, callback) {
			var game = {
				masterschedule: schedule_id,
			};
			$http.put(config.urlbase + '/game', game).success(callback);
		},

		setComment : function(pm_player, pm_dayid, pm_timeframe, pm_setting, pm_idcomment, pm_message, callback) {
			var comment;

			if (pm_idcomment != null) comment = { id :  pm_idcomment, message : pm_message };
			else comment = { player : pm_player, dayid : pm_dayid, timeframe : pm_timeframe, setting : pm_setting, message : pm_message};
			$http.post(config.urlbase + '/comment', comment).success(callback);
		}
	}
}]);


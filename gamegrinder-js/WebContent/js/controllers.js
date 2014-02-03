'use strict';

/* Controllers */

gamegrinderApp.controller('GameGrinderCtrl', [ '$scope', '$cookies', 'settingsService', 'plannerService', 'planningBuilderService', 'config', function GameGrinderCtrl($scope, $cookies, settingsService, plannerService, planningBuilderService, config) {

    $scope.dayCount = 42;

    $scope.currentUser =  $cookies['ggUser'];

    $scope.firstday = planningBuilderService.getDefaultMinDay();
    
    $scope.blanksetting = { name : '', mode : -1, status : 0, code : ''};
    $scope.newsetting = $scope.blanksetting;

    $scope.settingsReady = false;

    $scope.invisibleStatus = (typeof $cookies.ggInvisibleStatus != 'undefined')?$cookies.ggInvisibleStatus.split("|"):new Array();
    $scope.invisibleOpenSettings = (typeof $cookies.ggInvisibleOpen != 'undefined')?$cookies.ggInvisibleOpen.split("|"):new Array();
    $scope.visibleClosedSettings = (typeof $cookies.ggVisibleClosed != 'undefined')?$cookies.ggVisibleClosed.split("|"):new Array();

    $scope.currentRecruits = [];
	
    $scope.login=function() { $cookies['ggUser'] = $scope.currentUser; initPlanning(); };
    $scope.logout=function() { delete $cookies['ggUser']; $( "#logindialogcontainer" ).qtip( "toggle", true ); };

    $scope.setComment=function() {  };
    
/*    

    $scope.openCommentDialog=function(day, timeframe, setting) {
        $scope.currentDay = day;
        $scope.currentTimeframe = timeframe;
        $scope.currentSettingTf = setting;
        $( "#commentdialogcontainer" ).qtip( "toggle", true );
    }
*/
   $scope.selectTimeframe=function(val) {
     $scope.currentTimeframe=val;
  }
  
  $scope.selectTimeframeSetting = function(val) {
     $scope.currentSettingTf = val;
  }
  
  $scope.mystatus = new Array();

  $scope.toggleRecruit = function(player) {
	for (var key in $scope.currentRecruits) {
	    if (key == player.name) {
		    delete $scope.currentRecruits[key];
		    return;
	    }
	}
	$scope.currentRecruits[player.name] = player;
  }

  $scope.toggleStatusVisibility = function(status) {
  	var settingsArray;
	settingsArray = $scope.invisibleStatus;
	var index = settingsArray.indexOf('' + status);
	if (index != -1) {
		settingsArray.splice(index, 1);
	}
	else {
		settingsArray.push('' + status)
	}
	$scope.updateSettingsCookies();
  }

  $scope.toggleSettingVisibility = function(settingid, isOpen) {
  	var settingsArray;
	if (isOpen) {
		settingsArray = $scope.invisibleOpenSettings;
	}
	else {
		settingsArray = $scope.visibleClosedSettings;
	}
	var index = settingsArray.indexOf('' + settingid);
	if (index != -1) {
		settingsArray.splice(index, 1);
	}
	else {
		settingsArray.push('' + settingid)
	}
	$scope.updateSettingsCookies();
  }

  $scope.updateSettingsCookies = function() {
	$cookies.ggInvisibleOpen = $scope.invisibleOpenSettings.join("|");
	$cookies.ggVisibleClosed = $scope.visibleClosedSettings.join("|");
	$cookies.ggInvisibleStatus = $scope.invisibleStatus.join("|");
  }

  $scope.isInArray = function(item, list) {
	return (list.indexOf('' + item) > -1);
  }
/*
  $scope.userIsInPlayers = function(playerslist) {
        var i,player;
        if (typeof playerslist == "undefined") return false;
        for (i = 0; i < playerslist.length; i++) {
            player = playerslist[i];
            if (player.name == $scope.currentUser) return true;            
        }
        return false;
  }
  
  $scope.userIsIn = function(itemlist) {
      if (typeof itemlist == "undefined") return false;
      return (itemlist.indexOf($scope.currentUser) != -1);
  }
  */

  $scope.statusDesc = [
    { id: 0, desc : "Pas dispo / intéressé", style: "notAvailableBadge" },
    { id: 1, desc : "Je suis dispo", style: "availableBadge" },
    { id: 2, desc : "Je joue !", style: "playBadge" },
    { id: 3, desc : "Partie sans moi", style: "noPlayBadge" }
  ];

  $scope.weeks = Array();

  settingsService.getSettings( function(settings) {
	  $scope.settingsList = settings;
	initPlanning();	  
  }); 

  var initPlanning = function()  {
		plannerService.getPlanning($scope.firstday, $scope.dayCount, function(planning) {
			if (typeof $scope.currentUser != undefined) $scope.weeks = planningBuilderService.buildWeeksPlanning($scope.firstday, $scope.dayCount, $scope.settingsList, planning, $scope.currentUser);
//			  $scope.mystatus = new UserStatus($scope.currentUser, $scope.weeks);
		});
  }
  /*
  $scope.settingsList =  {
    "EP": {"id": 1,
     "name": "Eclipse Phase",
     "code": "EP",
     "open": true},
    "7SP": {"id": 2,
     "name": "7th Sea: Pirates",
     "code": "7SP",
     "open": true},
    "TB": {"id": 3,
     "name": "Terres Balafrées",
     "code": "TB",
     "open": true},
    "SRQ": {"id": 4,
     "name": "Shadowrun Québec",
     "code": "SRQ",
     "open": true},
  };
*/
/*
  $scope.weeks = [
    {"days": [
         {"id":"20130923", "dow": 1, "dom": 23, "month": 9, "year": 2013, "timeframes" : [
	{"code":"AFTERNOON", "settings": [
	{"code": "TB",
		"games": [ { "gm": {"name": "GM1", "comment": "Comment-GM1"}, "players": [{"name": "PJ1"}, {"name": "PJ2", "comment": "Comment-PJ2"}, {"name": "PJ3"}]}, { "gm": {"name": "GM2", "comment": "Comment-GM2"}, "players": [ {"name": "PJ4", "comment": "Comment-PJ4"}, {"name": "PJ5"}, {"name": "PJ6"}]}],
		"availablegms": [ {"name": "MJD1", "comment": "Comment-MJD1"}, {"name": "MJD2"}],
		"availableplayers": [ {"name": "PJD2", "comment": "Comment-PJD2"}, {"name": "PJD1"}, {"name": "PJD3", "comment": "Comment-PJD3"}, {"name": "PJD4"}, { "name": "MJD1"}],
		"unavailable": [ { "name": "UA1", "comment": "Comment-UA1"}, { "name": "UA2", "comment": "Comment-UA2"}, { "name": "UA3", "comment": "Comment-UA3"}]
	},
	{"code": "7SP"}]},
	{"code":"EVENING", "settings": [
	{"code": "TB"},
	{"code": "7SP"}]}]},
         {"id":"20130924", "dow": 2, "dom": 24, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20130925", "dow": 3, "dom": 25, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20130926", "dow": 4, "dom": 26, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20130927", "dow": 5, "dom": 27, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20130928", "dow": 6, "dom": 28, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20130929", "dow": 7, "dom": 29, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
    ]},
    {"days": [
         {"id":"20130930", "dow": 1, "dom": 30, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20131001", "dow": 2, "dom": 1, "month": 10, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20131002", "dow": 3, "dom": 2, "month": 10, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20131003", "dow": 4, "dom": 3, "month": 10, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20131004", "dow": 5, "dom": 4, "month": 10, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20131005", "dow": 6, "dom": 5, "month": 10, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20131006", "dow": 7, "dom": 6, "month": 10, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
    ]},
  ];
*/
}]);



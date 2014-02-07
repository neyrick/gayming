'use strict';

/* Controllers */

gamegrinderApp.controller('GameGrinderCtrl', [ '$scope', '$cookies', 'settingsService', 'plannerService', 'planningBuilderService', 'config', function GameGrinderCtrl($scope, $cookies, settingsService, plannerService, planningBuilderService, config) {

    function timeSlide(days) {
        $scope.firstday = $scope.firstday + days * planningBuilderService.MS_IN_DAY;
        initPlanning();
    }

    function initPlanning()  {
		plannerService.getPlanning($scope.firstday, $scope.dayCount, function(planning) {
			 $scope.weeks = planningBuilderService.buildWeeksPlanning($scope.firstday, $scope.dayCount, $scope.settingsList, planning, $scope.currentUser);
            plannerService.getUpdates($scope.firstday, $scope.dayCount, $scope.currentUser, function(updatesHash) {
                planningBuilderService.dispatchUpdatesFlags(updatesHash, $scope.weeks, 0);
            });
		});
    }
    
    $scope.dayCount = 42;

    $scope.currentUser =  $cookies['ggUser'];
    $scope.tempUser = '';

    $scope.firstday = planningBuilderService.getDefaultMinDay();
    
    $scope.blanksetting = { name : '', mode : -1, status : 0, code : ''};
    $scope.newsetting = $scope.blanksetting;

    $scope.settingsReady = false;

    $scope.tooltipLock = { lock : false};

    $scope.invisibleStatus = (typeof $cookies.ggInvisibleStatus != 'undefined')?$cookies.ggInvisibleStatus.split("|"):new Array();
    $scope.invisibleOpenSettings = (typeof $cookies.ggInvisibleOpen != 'undefined')?$cookies.ggInvisibleOpen.split("|"):new Array();
    $scope.invisibleOneShots = (typeof $cookies.ggInvisibleOneShots != 'undefined')?$cookies.ggInvisibleOneShots.split("|"):new Array();
    $scope.visibleClosedSettings = (typeof $cookies.ggVisibleClosed != 'undefined')?$cookies.ggVisibleClosed.split("|"):new Array();

    $scope.currentRecruits = [];
	
    $scope.login=function() {
	 $scope.currentUser = $scope.tempUser;
     $scope.tempUser = '';
	 $cookies['ggUser'] = $scope.currentUser;
	 $scope.weeks = [];
	 initPlanning();
     };
    $scope.logout=function() {
	 $scope.weeks = [];
	 delete $scope.currentUser;
	 delete $cookies['ggUser'];
	 $( "#logindialogcontainer" ).qtip( "toggle", true ); }
    ;

    $scope.setComment=function() {  };
    
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

  $scope.toggleSettingVisibility = function(settingid, settingmode, force) {
  	var settingsArray;
	var defaultState;
	if (settingmode == 0) {
		settingsArray = $scope.invisibleOpenSettings;
		defaultState = true;
	}
	else if (settingmode == 1) {
		settingsArray = $scope.visibleClosedSettings;
		defaultState = false;
	}
	else {
		settingsArray = $scope.invisibleOneShots;
		defaultState = true;
	}
	var index = settingsArray.indexOf('' + settingid);
	if (index != -1) {
		if ((typeof force == "undefined") || (force === defaultState)) {
			settingsArray.splice(index, 1);
		}
	}
	else if ((typeof force == "undefined") || (force === !defaultState)) {
		settingsArray.push('' + settingid)
	}
	$scope.updateSettingsCookies();
  }

  $scope.updateSettingsCookies = function() {
	$cookies.ggInvisibleOpen = $scope.invisibleOpenSettings.join("|");
	$cookies.ggVisibleClosed = $scope.visibleClosedSettings.join("|");
	$cookies.ggInvisibleStatus = $scope.invisibleStatus.join("|");
	$cookies.ggInvisibleOneShots = $scope.invisibleOneShots.join("|");
  }

  $scope.isInArray = function(item, list) {
	return (list.indexOf('' + item) > -1);
  }

  $scope.statusDesc = [
    { id: 0, desc : "Pas dispo / intéressé", style: "notAvailableBadge" },
    { id: 1, desc : "Je suis dispo", style: "availableBadge" },
    { id: 2, desc : "Je joue !", style: "playBadge" },
    { id: 3, desc : "Partie sans moi", style: "noPlayBadge" }
  ];

  $scope.showPrevious = function () {
      timeSlide(-1 * $scope.dayCount);
  };
    
  $scope.showNext = function () {
      timeSlide($scope.dayCount);
  };
    
  $scope.weeks = Array();

  settingsService.getSettings( function(settings) {
	  $scope.settingsList = settings;
	if ((typeof $scope.currentUser != "undefined") && ($scope.currentUser != '')) initPlanning();	  
  }); 

}]);



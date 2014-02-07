'use strict';

/* Controllers */

gamegrinderApp.controller('GameGrinderCtrl', [ '$scope', 'settingsService', 'plannerService', 'planningBuilderService', 'config', 'localStorageService', function GameGrinderCtrl($scope, settingsService, plannerService, planningBuilderService, config, localStorageService) {

    function timeSlide(days) {
        $scope.firstday = $scope.firstday + days * planningBuilderService.MS_IN_DAY;
        initPlanning();
    }

    function initPlanning()  {
		$('#ggloading').addClass('active');
		plannerService.getPlanning($scope.firstday, $scope.dayCount, function(planning) {
			 $scope.weeks = planningBuilderService.buildWeeksPlanning($scope.firstday, $scope.dayCount, $scope.settingsList, planning, $scope.currentUser);
            plannerService.getUpdates($scope.firstday, $scope.dayCount, $scope.currentUser, function(updatesHash) {
                planningBuilderService.dispatchUpdatesFlags(updatesHash, $scope.weeks, $scope.lastUpdate);
            });
		$scope.lastUpdate = new Date().getTime();
		storeConfig();
		});
		$('#ggloading').removeClass('active');
    }
    
    function loadConfig() {
	if ((typeof $scope.currentUser == "undefined") || ($scope.currentUser == null)) return;
        var config =  localStorageService.get('ggconfig-' + $scope.currentUser);
	if ((typeof config == "undefined") || (config === '') || (config == null)) return;
	$scope.invisibleStatus = (typeof config.invisibleStatus != "undefined") ? config.invisibleStatus : new Array();
	$scope.invisibleOpenSettings = (typeof config.invisibleOpenSettings != "undefined") ? config.invisibleOpenSettings : new Array();
	$scope.invisibleOneShots = (typeof config.invisibleOneShots != "undefined") ? config.invisibleOneShots : new Array();
	$scope.visibleClosedSettings = (typeof config.visibleClosedSettings != "undefined") ? config.visibleClosedSettings : new Array();
	$scope.lastUpdate = (typeof config.lastUpdate != "undefined") ? config.lastUpdate : 0;
    }

    function storeConfig() {
	if (typeof $scope.currentUser == "undefined") return;
	var config = {
		invisibleStatus : $scope.invisibleStatus,
		invisibleOpenSettings : $scope.invisibleOpenSettings,
		invisibleOneShots : $scope.invisibleOneShots,
		visibleClosedSettings : $scope.visibleClosedSettings,
		lastUpdate : $scope.lastUpdate
	};
        localStorageService.add('ggconfig-' + $scope.currentUser, JSON.stringify(config));
    }

    $scope.blanksetting = { name : '', mode : -1, status : 0, code : ''};
    $scope.newsetting = $scope.blanksetting;

    $scope.dayCount = 42;

    $scope.firstday = planningBuilderService.getDefaultMinDay();
    
    $scope.settingsReady = false;

    $scope.tooltipLock = { lock : false};
    $scope.loading = { show : false};

    $scope.currentUser =  localStorageService.get('ggUser');
    $scope.lastUpdate = 0;
    $scope.tempUser = '';
    $scope.mystatus = new Array();
    $scope.weeks = Array();
    $scope.invisibleStatus = new Array();
    $scope.invisibleOpenSettings = new Array();
    $scope.invisibleOneShots = new Array();
    $scope.visibleClosedSettings = new Array();

    loadConfig();

    $scope.login=function() {
	 $scope.currentUser = $scope.tempUser;
         $scope.tempUser = '';
	 localStorageService.add('ggUser', $scope.currentUser);
	 $scope.weeks = [];
	 loadConfig();
	 initPlanning();
     };
    $scope.logout=function() {
	 $scope.lastUpdate = 0;
	 $scope.tempUser = '';
	 $scope.mystatus = new Array();
	 $scope.weeks = Array();
	 $scope.invisibleStatus = new Array();
	 $scope.invisibleOpenSettings = new Array();
	 $scope.invisibleOneShots = new Array();
	 $scope.visibleClosedSettings = new Array();
	 $scope.weeks = [];
	 delete $scope.currentUser;
	 localStorageService.remove('ggUser');
	 $( "#logindialogcontainer" ).qtip( "toggle", true ); }
    ;

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
	storeConfig();
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
	storeConfig();
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
    
  settingsService.getSettings( function(settings) {
	  $scope.settingsList = settings;
	if ((typeof $scope.currentUser != "undefined") && ($scope.currentUser != '') && ($scope.currentUser != null)) initPlanning();	  
  }); 

}]);



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
        $scope.config = config;
    }

    function reset() {
        $scope.tempUser = '';
        $scope.mystatus = new Array();
        $scope.weeks = Array();
        $scope.config = {
            invisibleStatus : [],
            invisibleOpenSettings : [],
            invisibleOneShots : [],
            visibleClosedSettings : [],
            lastUpdate : 0
        };        
    }
    
    function storeConfig() {
        if (typeof $scope.currentUser == "undefined") return;
           $scope.openSettings.forEach(function (item) {
               if (!item.visible) $scope.config.invisibleOpenSettings.push(item.id);
           });
           $scope.closedSettings.forEach(function (item) {
               if (item.visible) $scope.config.visibleClosedSettings.push(item.id);
           });
           $scope.oneShots.forEach(function (item) {
               if (!item.visible) $scope.config.invisibleOneShots.push(item.id);
           });
          for (var i = 0; i < $scope.statusDesc.length; i++) {
              if (!$scope.statusDesc[i].visible) $scope.config.invisibleStatus.push(i);
          }        
        localStorageService.add('ggconfig-' + $scope.currentUser, JSON.stringify($scope.config));
    }

    $scope.blanksetting = { name : '', mode : -1, status : 0, code : ''};
    $scope.newsetting = $scope.blanksetting;

    $scope.dayCount = 42;

    $scope.firstday = planningBuilderService.getDefaultMinDay();
    
    $scope.settingsReady = false;

    $scope.tooltipLock = { lock : false};
    $scope.loading = { show : false};

    $scope.currentUser =  localStorageService.get('ggUser');
    reset();    
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
        reset();
         delete $scope.currentUser;
         localStorageService.remove('ggUser');
         $( "#logindialogcontainer" ).qtip( "toggle", true ); }
    ;

  $scope.refreshSettings = function(andPlanning) {
      settingsService.getSettings( function(settings) {
          $scope.settingsList = settings;
          $scope.openSettings = [];
          $scope.closedSettings = [];
          $scope.oneShots = [];
          $scope.settingsList.forEach(function(item) {
              if (item.status > 0) return;
              if (item.mode == 0) {
                  $scope.openSettings.push(item);
                  item.visible = ($scope.config.invisibleOpenSettings.indexOf(item.id) == -1);
              }
              else if (item.mode == 1) {
                  $scope.closedSettings.push(item);
                  item.visible = ($scope.config.visibleClosedSettings.indexOf(item.id) > -1);
              }
              else if (item.mode == 2) {
                  $scope.oneShots.push(item);
                  item.visible = ($scope.config.invisibleOneShots.indexOf(item.id) == -1);
              }
          });
          if (andPlanning && (typeof $scope.currentUser != "undefined") && ($scope.currentUser != '') && ($scope.currentUser != null)) initPlanning();	 
      }); 
  };
    
  $scope.toggleStatusVisibility = function(status) {
      if ($scope.statusDesc[status].visible) {
          $('.' + $scope.statusDesc[status].style).addClass('ggHidden');
          $scope.statusDesc[status].visible = false;
      }
      else {
          $('.' + $scope.statusDesc[status].style).removeClass('ggHidden');
          $scope.statusDesc[status].visible = true;
      }
	storeConfig();
  };

  $scope.toggleSettingVisibility = function(settingid, force) {
      var setting;
      for (var i = 0; i < $scope.settingsList.length; i++) {
          setting = $scope.settingsList[i];
          if (setting.id == settingid) {
              if ((setting.visible) && (force !== true)) {
                  $('.settingBadge-id-' + settingid).addClass('ggHidden');
                  setting.visible = false;
                  return;
              }
              if ((!setting.visible) && (force !== false)) {
                  $('.settingBadge-id-' + settingid).removeClass('ggHidden');
                  setting.visible = true;
                  return;
              }
          }
      }
	storeConfig();
  }

  $scope.isInArray = function(item, list) {
	return (list.indexOf('' + item) > -1);
  }

  $scope.statusDesc = [
    { id: 0, desc : "Pas dispo / intéressé", style: "notAvailableBadge", visible : true },
    { id: 1, desc : "Partie sans moi", style: "noPlayBadge", visible : true },
    { id: 2, desc : "Je suis dispo", style: "availableBadge", visible : true },
    { id: 3, desc : "Je joue !", style: "playBadge", visible : true }
  ];

  $scope.showPrevious = function () {
      timeSlide(-1 * $scope.dayCount);
  };
    
  $scope.showNext = function () {
      timeSlide($scope.dayCount);
  };
    
  $scope.refreshSettings(true);

    
}]);



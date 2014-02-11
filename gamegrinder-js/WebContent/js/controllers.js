'use strict';

/* Controllers */

gamegrinderApp.controller('GameGrinderCtrl', [ '$scope', 'settingsService', 'plannerService', 'planningBuilderService', 'config', 'localStorageService', 'historyService', function GameGrinderCtrl($scope, settingsService, plannerService, planningBuilderService, config, localStorageService, historyService) {

    function timeSlide(days) {
        $scope.firstday = $scope.firstday + days * planningBuilderService.MS_IN_DAY;
        initPlanning();
    }

    function initPlanning()  {
		$('#ggloading').addClass('active');
        setTimeout(function() {
            plannerService.getPlanning($scope.firstday, $scope.dayCount, function(planning) {
                 $scope.weeks = planningBuilderService.buildWeeksPlanning($scope.firstday, $scope.dayCount, $scope.settingsList, planning, $scope.currentUser);
                plannerService.getUpdates($scope.firstday, $scope.dayCount, $scope.currentUser, function(updatesHash) {
                    planningBuilderService.dispatchUpdatesFlags(updatesHash, $scope.weeks, $scope.lastUpdate);
                    $scope.lastUpdate = new Date().getTime();
                    storeConfig();
                    applyFilters();
                });
            });
            $('#ggloading').removeClass('active');
        }, 0);
    }
    
    function loadConfig() {
	if ((typeof $scope.currentUser == "undefined") || ($scope.currentUser == null)) return;
        var config =  localStorageService.get('ggconfig-' + $scope.currentUser);
	if ((typeof config == "undefined") || (config === '') || (config == null)) return;
        $scope.config = config;
    }

    function reset() {
        $scope.tempUser = '';
        $scope.weeks = Array();
        $scope.config = {
            invisibleStatus : [],
            invisibleOpenSettings : [],
            invisibleOneShots : [],
            visibleClosedSettings : [],
            lastUpdate : 0
        };        
        $scope.currentEdit = {            
        };
    }
    
    function applyFilters () {
        $scope.settingsList.forEach( function (setting) {
            if (setting.visible) $('.settingBadge-id-' + setting.id).removeClass('ggHidden');
            else $('.settingBadge-id-' + setting.id).addClass('ggHidden');
        });
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

    $scope.newsetting = { name : '', mode : -1, status : 0, code : ''};

    $scope.dayCount = 42;

    $scope.firstday = planningBuilderService.getDefaultMinDay();
    
    $scope.settingsReady = false;

    $scope.tooltipLock = { mainlock : false};
    $scope.editingGame = false;
    $scope.editingComment = false;

    $scope.currentUser =  localStorageService.get('ggUser');
    $scope.currentEdit =  {};
    $scope.historyList = [];
    $scope.history = {};
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

    // Fonctions au  niveau de la timeframe
    
    $scope.createAndAddSetting = function() {
        $scope.newsetting.status = 0;
        settingsService.createSetting($scope.newsetting, function(newsetting) {
            $scope.settingsList.push(newsetting);
            $scope.addSetting(newsetting);
            newsetting.visible = true;
            if (newsetting.mode == 0) {
                  $scope.openSettings.push(newsetting);
              }
              else if (newsetting.mode == 1) {
                  $scope.closedSettings.push(newsetting);
              }
              else if (newsetting.mode == 2) {
                  $scope.oneShots.push(newsetting);
              }
            $scope.newsetting.name = '';
            $scope.newsetting.mode = -1;
            $scope.newsetting.status = 0;
            $scope.newsetting.code = '';
            $scope.tooltipLock.mainlock = false;
            $('#addSettingTooltipContainer').qtip('api').hide();
        });
        
    };
    
    $scope.addSetting = function(setting) {
        plannerService.setDispo($scope.currentUser, $scope.currentEdit.day.id, $scope.currentEdit.timeframe.code, setting.id, 'GM', function() {
    
         $scope.toggleSettingVisibility(setting.id, true);
            $scope.tooltipLock.mainlock = false;
            $('#addSettingTooltipContainer').qtip('api').hide();
            $scope.refreshTimeframe();
        });
    };
    $scope.refreshTimeframe = function() {
        plannerService.getTimeframePlanning($scope.currentEdit.day.id, $scope.currentEdit.timeframe.code, function(result) {
            planningBuilderService.refreshTimeframeInWeeksPlanning($scope.settingsList, result, $scope.currentEdit.timeframe, $scope.currentUser);
        });
    };

        // Fonctions au  niveau du tfSetting

    $scope.toggleGamePlayer = function(player) {
        if (typeof $scope.currentEdit.gamePlayers[player.name] != "undefined") {
            delete $scope.currentEdit.gamePlayers[player.name];
            $scope.currentEdit.numPlayers--;
        }
        else {
            $scope.currentEdit.gamePlayers[player.name] = player;
            $scope.currentEdit.numPlayers++;
        }
    }
    $scope.disbandGame = function() {
        plannerService.disbandGame(getMyGMScheduleId($scope.currentUser, $scope.currentEdit.schedule.games), function() {
            $scope.tooltipLock.mainlock = false;
            $('#tfSettingTooltipContainer').qtip('api').hide();
            $scope.refreshTimeframe();
        });
    }
    $scope.dropGame = function() {
        plannerService.dropGame(getMyPlayerScheduleId($scope.currentUser, $scope.currentEdit.schedule.games), function() {
            $scope.tooltipLock.mainlock = false;
            $('#tfSettingTooltipContainer').qtip('api').hide();
            $scope.refreshTimeframe();
        });
    }
    $scope.validateGame = function($event) {
        $scope.tooltipLock.mainlock = false;
        $('#tfSettingTooltipContainer').qtip('api').hide();
        if (typeof $scope.currentEdit.timeframe.mygame == "undefined") {
            plannerService.validateGame(getMyScheduleId($scope.currentUser, $scope.currentEdit.schedule, 'GM'), $scope.currentEdit.gamePlayers, function() {
                $scope.refreshTimeframe();
            });
        }
        else {
            plannerService.reformGame($scope.currentEdit.timeframe.mygame.id, $scope.currentEdit.gamePlayers, function() {
                $scope.refreshTimeframe();
            });
        }
    }
    $scope.setComment = function() {
        plannerService.setComment( $scope.currentUser, $scope.currentEdit.day.id, $scope.currentEdit.timeframe.code, $scope.currentEdit.schedule.settingid, $scope.currentEdit.schedule.idcomment, $scope.currentEdit.schedule.message, function() {
            $scope.tooltipLock.mainlock = false;
            $('#tfSettingTooltipContainer').qtip('api').hide();
            $scope.refreshTimeframe();
         });
    }
    $scope.setDispo = function(role) {
        plannerService.setDispo($scope.currentUser, $scope.currentEdit.day.id, $scope.currentEdit.timeframe.code, $scope.currentEdit.schedule.settingid, role, function() {
            $scope.tooltipLock.mainlock = false;
            $('#tfSettingTooltipContainer').qtip('api').hide();
            $scope.refreshTimeframe();
        });
    };
    $scope.clearDispo = function(role) {
        plannerService.clearDispo(getMyScheduleId($scope.currentUser, $scope.currentEdit.schedule, role), function() {
            $scope.tooltipLock.mainlock = false;
            $('#tfSettingTooltipContainer').qtip('api').hide();
            $scope.refreshTimeframe();
        });
    };

    $scope.openCommentEditor = function() {
        $scope.tooltipLock.mainlock = true;
        $scope.editingComment = true;
        $('.commentEdit').find('.inputComment').focus();
/*
        $('.commentTrigger').slideUp(200);
        var $box=$('.commentTrigger').next('.commentEdit');
        $box.slideDown(200);
*/
    };

    $scope.closeCommentEditor = function() {
	$scope.editingComment = false;
/*
        $('.commentEdit').slideUp(200);
        $('.commentTrigger').slideDown(200);
*/
    };

    $scope.openGameEditor = function() {
        $scope.tooltipLock.mainlock = true;
	    $scope.editingGame = true;
/*
            $('.validateDiv').slideUp(200);
            $('.gameEditor').slideDown(200);
*/
    };			
    
    $scope.closeGameEditor = function() {
	    $scope.editingGame = false;
/*
            $('.validateDiv').slideDown(200);
            $('.gameEditor').slideUp(200);
*/
    };			
    
    $scope.showHistory = function($event) {                
        $scope.historyList.length = 0;
        historyService.getHistory($scope.currentEdit.day.id, $scope.currentEdit.timeframe.code, $scope.currentEdit.schedule.settingid, function(history) {
            for (var i = 0; i < history.length; i++) {
                $scope.historyList.push(history[i]);
            }
        $scope.tooltipLock.mainlock = true;
            $($event.target).qtip('api').show();
        });
        
    };

    $scope.openSettingEditor = function() {
        $scope.tooltipLock.mainlock = true;
        $('.settingTrigger').slideUp(200);
        var $box=$('.settingEditor');
        $box.slideDown(200);
        $box.find('.inputSettingName').focus();
	}

    $scope.closeSettingEditor = function() {
        $('.settingEditor').slideUp(200);
        $('.settingTrigger').slideDown(200);
	}

    $('#tfSettingTooltipContainer').qtip({
        style: {
            classes: 'ggpanel tfSettingEditBox'
        },
        content: {
            text: $('.tfSettingDropdown')
        },
        position: {
            my: 'top center',
            at: 'bottom center',
            target: $('#tfSettingTooltipContainer'),
	    adjust: { x : -10, y : -9 }
        },
        show: {
            event: false,
	    solo: true
        },
        hide: {
            delay: 100,
            fixed: true,
            event: 'mouseleave unfocus',
	    inactive: 2000
        },
        events: {
            hide: function(event, api) {
                if (($scope.tooltipLock.mainlock === true) && ((event.originalEvent.type != 'mousedown') || (event.originalEvent.target.id != 'ggoverlay'))){
                    event.preventDefault();
                    return;
                }
                $scope.closeCommentEditor();
                $scope.closeGameEditor();
                if ($scope.$$phase == null) {
                    $scope.$apply( function () {
                        $scope.tooltipLock.mainlock = false;
                    });
                }
                else {
                    $scope.tooltipLock.mainlock = false;
                }
            }
        }
    });

    $('#addSettingTooltipContainer').qtip({
        style: {
            classes: 'ggpanel extraSettingEditBox'
        },
        content: {
            text: $('.addSettingDropdown')
        },
        position: {
            my: 'top center',
            at: 'bottom left',
            target: $('#addSettingTooltipContainer'),
	    adjust: { x : -4, y : -12 }
        },
        show: {
            event: false,
            solo: true
        },
        hide: {
            delay: 100,
            fixed: true,
            event: 'mouseleave unfocus',
	    inactive: 2000
        },
        events: {
            hide: function(event, api) {
                if (($scope.tooltipLock.mainlock === true) && ((event.originalEvent.type != 'mousedown') || (event.originalEvent.target.id != 'ggoverlay'))){
                    event.preventDefault();
                    return;
                }
                $scope.closeSettingEditor();
                if ($scope.$$phase == null) {
                    $scope.$apply( function () {
                        $scope.tooltipLock.mainlock = false;
                    });
                }
                else {
                    $scope.tooltipLock.mainlock = false;
                }
            }
        }
    });

}]);



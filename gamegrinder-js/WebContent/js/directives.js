'use strict';

/* Directives */

gamegrinderApp.directive('ggTimeframeBox', function(plannerService, planningBuilderService, settingsService) {

	var timeframesDesc = {
			    "AFTERNOON": {"code":"AFTERNOON", pic:"images/aprem.png", name:"Après-midi"},
			    "EVENING": {"code":"EVENING", pic:"images/soir.png", name:"Soirée"},
			};
    
    var allPossibleSettings = {};
    
	return {
        controller : function ($scope, $element, $attrs) {
			$scope.timeframesDesc = timeframesDesc;
            $scope.getSettingClasses = function(schedule) {
                var classes = [];
                classes.push('settingBadge');
                classes.push('settingBadge-id-' + schedule.settingid);
                var mystatus = schedule.mystatus;
                if (mystatus.pj || mystatus.mj) {
                    classes.push('playBadge');
                }
                else if (mystatus.dispoPJ || mystatus.dispoMJ)  {
                    if (schedule.hasgame) classes.push('noPlayBadge');
                    else classes.push('availableBadge');
                }
	            else classes.push('notAvailableBadge');
                return classes;
	        };
            $scope.triggerTfSettingTooltip = function(schedule, element) {
                if($scope.tooltipLock.lock === false) {
                    $scope.currentEdit.day = $scope.day;
                    $scope.currentEdit.timeframe = $scope.timeframe;
                    $scope.currentEdit.schedule = schedule;
                    $scope.currentEdit.status = schedule.mystatus;
                    $scope.currentEdit.gamePlayers = new Array();
                    $scope.currentEdit.potentialPlayers = new Array();
                    $scope.currentEdit.numPlayers = 0;
                    var i, currentItem;
                    for (i = 0; i < schedule.games.length; i++) {
                        currentItem = schedule.games[i];
                        if (currentItem.gm.name == $scope.currentUser) {
                            for (j = 0; j < currentItem.players.length; j++) {
                                $scope.currentEdit.gamePlayers[currentItem.players[j].name] = currentItem.players[j];
                                $scope.currentEdit.potentialPlayers.push(currentItem.players[j]);
                                $scope.currentEdit.numPlayers++;
                            }
                        }
                    }
                    for (i = 0; i < schedule.availableplayers.length; i++ ) {
                        currentItem = schedule.availableplayers[i];
                        if (currentItem.name != $scope.currentUser) {
                            $scope.currentEdit.potentialPlayers.push(currentItem);
                        }
                    }
                    $(element).qtip({
                        style: {
                            classes: 'ggpanel tfSettingEditBox'
                        },
                        content: {
                            text: $('.tfSettingDropdown').first()
                        },
                        position: {
                            my: 'top center',
                            at: 'bottom center',
                            target: $(element)
                        },
                        show: {
                            event: false,
                            solo: '.tfSettingDropdown',
                            ready: true
                        },
                        hide: {
                            delay: 100,
                            fixed: 'true',
                            event: 'mouseleave',
                        },
                        events: {
                            hide: function(event, api) {
                                $('#ggoverlay').removeClass('active');
                                $scope.tooltipLock.lock = false;
                            }
                        }
                    });
                }
            };
            $scope.triggerExtraSettingTooltip = function(element) {
                if($scope.tooltipLock.lock === false) {
                    $scope.currentEdit.day = $scope.day;
                    $scope.currentEdit.timeframe = $scope.timeframe;
                    $scope.currentEdit.possibleSettings = allPossibleSettings[$scope.day.id + '-' + $scope.timeframe.code];
                    $(element).qtip({
                        style: {
                            classes: 'ggpanel extraSettingEditBox'
                        },
                        content: {
                            text: $('.addSettingDropdown').first()
                        },
                        position: {
                            my: 'top center',
                            at: 'bottom left',
                            target: $(element)
                        },
                        show: {
                            event: false,
                            solo: '.tfExtra',
                            ready: true
                        },
                        hide: {
                            delay: 100,
                            fixed: 'true',
                            event: 'mouseleave',
                        },
                        events: {
                            hide: function(event, api) {
                                $('#ggoverlay').removeClass('active');
                                $scope.tooltipLock.lock = false;
                            }
                        }
                    });
                }
            }
        },
		restrict: 'E',
		templateUrl: 'directives/timeframebox.html',
		scope: true,
		link: function(scope, element, attrs) {
            var presentSettings = [];
            var possibleSettings = [];
            scope.timeframe.settings.forEach(function (item) {
                presentSettings.push(item.settingid);
            });
            scope.settingsList.forEach(function (item) {
                if ((item.status == 0) && (presentSettings.indexOf(item.id) == -1)) {
                    possibleSettings.push(item);
                }
            });
            allPossibleSettings[scope.day.id + '-' + scope.timeframe.code] = possibleSettings;
			$(element).find('.timeFramePic').qtip({
				style: { classes: 'infoTooltip' },
				content: { text: timeframesDesc[scope.timeframe.code].name },
				position: {
					my: 'bottom left',
					at: 'top right'
				},
				show: {
					event: 'mouseenter click',
				},
				hide: {
					delay: 10,
					event: 'mouseleave',
				}
			});
		}
	};
});

gamegrinderApp.directive('ggDayTab', function() {

	var dowcodes = { "0":"DIM","1":"LUN","2":"MAR","3":"MER","4":"JEU","5":"VEN","6":"SAM"};

	return {
		restrict: 'E',
		templateUrl: 'directives/daytab.html',
		scope: true,
		link: function(scope, element, attrs) {
            scope.day = scope.$eval(attrs['day']);
			$(element).hover(function(event) {
				$(element).find(".datePanel").addClass("hoverDay");
			},
			function(event) {
				$(element).find(".datePanel").removeClass("hoverDay");
			});
			scope.dowcodes = dowcodes;
		}
	};
});

gamegrinderApp.directive('ggHistory', function() {

	return {
		restrict: 'E',
		templateUrl: 'directives/history.html',
		scope: { row : '=' },
		link: function(scope, element, attrs) {
			scope.tstamp = scope.row.tstamp;
			scope.dayid = scope.row.dayid;
			scope.timeframe = scope.row.timeframe;
			scope.setting = scope.row.setting;
			scope.player = scope.row.player;
			scope.data = scope.row.data;
			scope.action = scope.row.action;
		}
	};
});


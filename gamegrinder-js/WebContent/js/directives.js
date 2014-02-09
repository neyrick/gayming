'use strict';

/* Directives */

function lockTooltip(scope, element) {
	element.qtip('api').set('hide.event', 'unfocus');
	scope.tooltipLock.lock = true;
	$('#ggoverlay').addClass('active');
}

gamegrinderApp.directive('ggTfSettingTooltip', function(plannerService, historyService) {
    
    var getMyScheduleId = function(name, schedule, role) {
        var schedules;
        if (role == 'GM') schedules = schedule.availablegms;
        else if (role == 'PLAYER') schedules = schedule.availableplayers;
        for (var i = 0; i < schedules.length; i++) {
            if (schedules[i].name == name) {
                return schedules[i].id;
            }
        }
    };
    
    var getMyGMScheduleId = function(name, games) {
        for (var i = 0; i < games.length; i++) {
            if (games[i].gm.name == name) {
                return games[i].gm.id;
            }
        }
    };
    
    var getMyPlayerScheduleId = function(name, games) {
        var i, j, players;
        for (i = 0; i < games.length; i++) {
            players = games[i].players;
            for (j = 0; j < players.length; j++) {
                if (players[j].name == name) {
                    return players[i].id;
                }
            }
        }
    };
    
	return {
		restrict: 'E',
		templateUrl: 'directives/tfsettingtooltip.html',
		scope: true,
        link: function(scope, element, attrs) {
		var i, j, currentItem;
            scope.gamePlayers = {};
	    scope.potentialPlayers = [];
            scope.numPlayers = 0;
                scope.historyList = [];
                scope.history = { setting : scope.schedule.name, date : scope.dowcodes[scope.day.dow] + ' ' + scope.day.dom + '/' + scope.day.month, timeframe : scope.timeframesDesc[scope.timeframe.code].name };
		for (i = 0; i < scope.schedule.games.length; i++) {
			currentItem = scope.schedule.games[i];
			if (currentItem.gm.name == scope.currentUser) {
				for (j = 0; j < currentItem.players.length; j++) {
					scope.gamePlayers[currentItem.players[j].name] = currentItem.players[j];
					scope.potentialPlayers.push(currentItem.players[j]);
					scope.numPlayers++;
				}
			}
		}
            scope.status = scope.schedule.mystatus;
			$(element).parent().qtip({
			    style: {
				classes: 'ggpanel tfSettingEditBox'
			    },
			    content: {
				text: $(element).find('.ggdropdown').first()
			    },
			    position: {
				my: 'top center',
				at: 'bottom center'
			    },
			    show: {
				event: 'mouseenter click',
				solo: '.tfExtra'
			    },
			    hide: {
				delay: 100,
				fixed: 'true',
				event: 'mouseleave',
			    },
			    events: {
				show: function(event, api) {
				    if(scope.tooltipLock.lock) {
					try { event.preventDefault(); } catch(e) {}
				    }
				},
				hide: function(event, api) {
					api.set('hide.event', 'mouseleave');
					scope.tooltipLock.lock = false;
					$('#ggoverlay').removeClass('active');
				}
			    }
			});
			$(element).find('.commentTrigger').click(function(event) {
				lockTooltip(scope, $(element).parent());
				$(this).slideUp(200);
				var $box=$(this).next('.commentEdit');
				$box.slideDown(200);
				$box.find('.inputComment').focus();
			});
			$(element).find('.commentButton').click(function(event) {
				var $box = $(this).parents('.commentEdit');
				$box.slideUp(200);
				$box.prev('.commentTrigger').slideDown(200);
			});
			$(element).find("[alt-text]").each(function() {
				$(this).hover(function(event) {
					$(this).text($(this).attr("alt-text"));
				},
				function(event) {
					$(this).text($(this).attr("norm-text"));
				})
			});
			$(element).find("[alt-text]").show(function() {
					$(this).text($(this).attr("norm-text"));
			});
			$(element).find(".validateDiv").click(function($event) {
					lockTooltip(scope, $(element).parent());
					$(element).parent().qtip('api').set('hide.event', 'unfocus');
					$($event.target).slideUp(200);
                            		$($event.target).nextAll(".gameEditor").slideDown(200);
			});			
			$(element).find('.histButton').qtip({
			    style: {
				    classes: 'ggpanel'
			    },
			    content: {
				    text: $(element).find('.historydialog')
			    },
			    position: {
        	            	my: 'center',
        	            	at: 'center',
        	            	target: $(window),
			    },
			    show: {
				    modal: {
	        	                on: true,
				    },
				    event: false
			    },
			    hide: 'unfocus',
			});
			for (i = 0; i < scope.schedule.availableplayers.length; i++ ) {
				currentItem = scope.schedule.availableplayers[i];
				if (currentItem.name != scope.currentUser) {
					scope.potentialPlayers.push(currentItem);
				}
			}
            scope.toggleGamePlayer = function(player, event) {
                if (typeof scope.gamePlayers[player.name] != "undefined") {
                    delete scope.gamePlayers[player.name];
                    scope.numPlayers--;
                }
                else {
                    scope.gamePlayers[player.name] = player;
                    scope.numPlayers++;
                }
            }
			scope.disbandGame = function() {
                plannerService.disbandGame(getMyGMScheduleId(scope.currentUser, scope.schedule.games), function() {
                    $(element).parent().qtip('api').hide();
                    scope.refreshTimeframe();
                });
            }
			scope.dropGame = function() {
                plannerService.dropGame(getMyPlayerScheduleId(scope.currentUser, scope.schedule.games), function() {
                    $(element).parent().qtip('api').hide();
                    scope.refreshTimeframe();
                });
            }
			scope.validateGame = function($event) {
                 var api = $(element).parent().qtip('api');
                 api.hide();
		if (typeof scope.timeframe.mygame == "undefined") {
	                 plannerService.validateGame(getMyScheduleId(scope.currentUser, scope.schedule, 'GM'), scope.gamePlayers, function() {
	                    scope.refreshTimeframe();
                	});
		}
		else {
	                 plannerService.reformGame(scope.timeframe.mygame.id, scope.gamePlayers, function() {
	                    scope.refreshTimeframe();
                	});
		}
			}
		    scope.setComment = function() {
                plannerService.setComment( scope.currentUser, scope.day.id, scope.timeframe.code, scope.schedule.settingid, scope.schedule.idcomment, scope.schedule.message, function() {
		 		    $(element).parent().qtip('api').hide();
                    scope.refreshTimeframe();
			     });
		    }
            scope.setDispo = function(role) {
                plannerService.setDispo(scope.currentUser, scope.day.id, scope.timeframe.code, scope.schedule.settingid, role, function() {
 		             $(element).parent().qtip('api').hide();
                    scope.refreshTimeframe();
                });
            };
            scope.clearDispo = function(role) {
                plannerService.clearDispo(getMyScheduleId(scope.currentUser, scope.schedule, role), function() {
                     $(element).parent().qtip('api').hide();
                    scope.refreshTimeframe();
                });
            };
            scope.showHistory = function($event) {                
                scope.historyList.length = 0;
                historyService.getHistory(scope.day.id, scope.timeframe.code, scope.schedule.settingid, function(history) {
                    for (var i = 0; i < history.length; i++) {
                        scope.historyList.push(history[i]);
                    }
				lockTooltip(scope, $(element).parent());
                    $($event.target).qtip('api').show();
                });
                
            };
		}
	};
});

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
			$scope.createAndAddSetting = function(setting) {
				setting.status = 0;
				console.log("Setting: %j", setting);
				settingsService.createSetting(setting, function(newsetting) {
					$scope.settingsList.push(newsetting);
					$scope.addSetting(newsetting);
				});
				
			};
			$scope.addSetting = function(setting) {
				plannerService.setDispo($scope.currentUser, $scope.day.id, $scope.timeframe.code, $setting.id, 'GM', function() {
			
			     $scope.toggleSettingVisibility(setting.id, setting.mode, true);
                    $scope.refreshTimeframe();
                    $($element).find('.tfExtra').qtip('api').hide();
				});
			};
			$scope.refreshTimeframe = function() {
				plannerService.getTimeframePlanning($scope.day.id, $scope.timeframe.code, function(result) {
					planningBuilderService.refreshTimeframeInWeeksPlanning($scope.settingsList, result, $scope.timeframe, $scope.currentUser);
				});
			};
            $scope.triggerExtraSettingTooltip = function(element) {
                if($scope.tooltipLock.lock === false) {
                    $scope.currentEdit.possibleSettings = allPossibleSettings[$scope.day.id + '-' + $scope.timeframe.code];
                    $(element).qtip({
                        style: {
                            classes: 'ggpanel'
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
//                                api.destroy();
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


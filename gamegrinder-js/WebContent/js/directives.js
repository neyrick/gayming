'use strict';

/* Directives */

gamegrinderApp.directive('ggTfSettingTooltip', function(plannerService) {
	return {
		restrict: 'E',
		templateUrl: 'directives/tfsettingtooltip.html',
		scope: true,
        link: function(scope, element, attrs) {
            scope.gamePlayers = {};
            scope.numPlayers = 0;
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
				hide: function(event, api) {
					api.set('hide.event', 'mouseleave');
				}
			    }
			});
			$('.commentTrigger').click(function(event) {
				$(element).parent().qtip('api').set('hide.event', 'unfocus');
				$(this).slideUp(200);
				var $box=$(this).next('.commentEdit');
				$box.slideDown(200);
				$box.find('.inputComment').focus();
			});
			$('.commentButton').click(function(event) {
				var $box = $(this).parents('.commentEdit');
				$box.slideUp(200);
				$box.prev('.commentTrigger').slideDown(200);
			});
			$("[alt-text]").each(function() {
				$(this).hover(function(event) {
					$(this).text($(this).attr("alt-text"));
				},
				function(event) {
					$(this).text($(this).attr("norm-text"));
				})
			});
			$("[alt-text]").show(function() {
					$(this).text($(this).attr("norm-text"));
			});
			$(".validateDiv").click(function($event) {
					$(element).parent().qtip('api').set('hide.event', 'unfocus');
					$($event.target).slideUp(200);
                            		$($event.target).nextAll(".gameEditor").slideDown(200);
			});
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
			scope.isBusyElsewhere = function(player) {
				var otherSetting = scope.timeframe.gaming[player];
				return ((typeof otherSetting != "undefined") && (otherSetting != scope.schedule.settingid));
			}
			scope.isAvailableForGame = function(player) {
                if (player.name == scope.currentUser) return false;
				var otherSetting = scope.timeframe.gaming[player.name];
				return (typeof otherSetting == "undefined");
			}
			scope.validateGame = function($event) {
                var i, gm;
                for (i = 0; i < scope.schedule.availablegms.length; i++ ) {
                    gm = scope.schedule.availablegms[i];
                    if (gm.name == scope.currentUser) {
			var api = $(element).parent().qtip('api');
			api.hide();
                        plannerService.validateGame(gm.schedule, scope.gamePlayers, function() {
                            
                            scope.refreshTimeframe();
                        });
                        break;
                    }
                }
			}
		scope.setComment = function() {
			plannerService.setComment( scope.currentUser, scope.day.id, scope.timeframe.code, scope.schedule.settingid, scope.schedule.idcomment, scope.schedule.message, function() {
		 		$(element).parent().qtip('api').hide();
				    scope.refreshTimeframe();
			});
		}
            scope.toggleDispo = function(role, isAvailable) {
                plannerService.toggleDispo(scope.currentUser, scope.day.id, scope.timeframe.code, scope.schedule.settingid, role, isAvailable, function() {
 		$(element).parent().qtip('api').hide();
                    scope.refreshTimeframe();
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

	return {
		restrict: 'E',
		templateUrl: 'directives/timeframebox.html',
		scope: true,
		link: function(scope, element, attrs) {
			scope.timeframesDesc = timeframesDesc;
            scope.getStatusCode = function(schedule) {
                var mystatus = schedule.mystatus;
                if (mystatus.pj || mystatus.mj) return 2;
                else if (mystatus.dispoPJ || mystatus.dispoMJ)  {
                    if (schedule.hasgame) return 3;
                    else return 1;
                }
	            else return 0;
	        }
			$(element).find('.tfExtra').each(function() {
				$(this).qtip({
				    style: {
					classes: 'ggpanel'
				    },
				    content: {
					text: $(this).next('div')
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
					hide: function(event, api) {
						api.set('hide.event', 'mouseleave');
						$(this).find('.collapsed').hide();
						$(this).find('.collapsible').show();
					}
				    }
				});
			});
			$(element).find('.settingTrigger').first().click(function(event) {
				$(element).find('.tfExtra').qtip('api').set('hide.event', 'unfocus');
				$(this).slideUp(200);
				var $box=$(this).next('.settingEditor');
				$box.slideDown(200);
				$box.find('.inputSettingName').focus();
			});
			$(element).find('.settingButton').first().click(function(event) {
				var $box = $(this).parents('.settingEditor');
				$box.slideUp(200);
				$box.prev('.settingTrigger').slideDown(200);
			});
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
			scope.createAndAddSetting = function(setting) {
				setting.status = 0;
				console.log("Setting: %j", setting);
				settingsService.createSetting(setting, function(newsetting) {
					scope.settingsList.push(newsetting);
					scope.addSetting(newsetting);
				});
				
			}
			scope.isSettingNew = function(setting) {
				for (var i = 0; i < scope.timeframe.settings.length; i++) {
					if (scope.timeframe.settings[i].settingid == setting.id) return false;
				}
				return true;
			}
			scope.addSetting = function(setting) {
				plannerService.toggleDispo(scope.currentUser, scope.day.id, scope.timeframe.code, setting.id, 'GM', true, function() {
			
			scope.toggleSettingVisibility(setting.id, setting.mode, true);
                    scope.refreshTimeframe();
                    $(element).find('.tfExtra').qtip('api').hide();
				});
			};
			scope.isTfSettingVisible = function(schedule) {
			    if ((schedule.mode == 0) && (scope.invisibleOpenSettings.indexOf('' + schedule.settingid) > -1)) return false;
			    if ((schedule.mode == 1) && (scope.visibleClosedSettings.indexOf('' + schedule.settingid) == -1)) return false;
			    if ((schedule.mode == 2) && (scope.invisibleOneShots.indexOf('' + schedule.settingid) != -1)) return false;
			    if (scope.invisibleStatus.indexOf('' + scope.getStatusCode(schedule)) > -1) return false;
			    return true;
			  };
			scope.refreshTimeframe = function() {
				plannerService.getTimeframePlanning(scope.day.id, scope.timeframe.code, function(result) {
					planningBuilderService.refreshTimeframeInWeeksPlanning(scope.settingsList, result, scope.timeframe, scope.currentUser);
				});
			}
		}
	};
});

gamegrinderApp.directive('ggDayTab', function() {

	var dowcodes = { "0":"DI","1":"LU","2":"MA","3":"ME","4":"JE","5":"VE","6":"SA"};

	return {
		restrict: 'E',
		templateUrl: 'directives/daytab.html',
		scope: true,
		link: function(scope, element, attrs) {
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


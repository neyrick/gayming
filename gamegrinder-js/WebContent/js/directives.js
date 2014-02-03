'use strict';

/* Directives */
/*
function initStandardTooltips(element, scope) {
	$(element).find('[ttip!=""]').qtip({
		style: { classes: 'infoTooltip' },
		content: { attr: 'ttip' },
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
*/

gamegrinderApp.directive('ggTfSettingTooltip', function(plannerService) {
	return {
		restrict: 'E',
		templateUrl: 'directives/tfsettingtooltip.html',
		scope: {

			dayid: '=dayid',
			timeframe: '=timeframe',
			user: '=user',
			schedule: '=schedule',
			comment: '=comment',
			statusdesc: '=statusdec',
			refreshTimeframe: '=refreshtimeframe'
		},
        link: function(scope, element, attrs) {
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
					$(this).find('.collapsed').hide();
					$(this).find('.collapsible').show();
				}
			    }
			});
			$('.commentTrigger').click(function(event) {
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
			scope.validateGame = function($event) {
                var i, gm;
                for (i = 0; i < scope.schedule.availablegms.length; i++ ) {
                    gm = scope.schedule.availablegms[i];
                    if (gm.name == scope.user) {
                        plannerService.validateGame(gm.schedule, function() {
                            $($event.target).slideUp(200);
                            $($event.target).nextAll(".gameEditor").slideDown(200);
                            scope.refreshTimeframe();
                        });
                        break;
                    }
                }
			}
		scope.setComment = function() {
			plannerService.setComment( scope.user, scope.dayid, scope.timeframe.code, scope.schedule.settingid, scope.schedule.idcomment, scope.schedule.message, function() {
		 		$(element).parent().qtip('api').hide();
				    scope.refreshTimeframe();
			});
		}
            scope.toggleDispo = function(role, isAvailable) {
                plannerService.toggleDispo(scope.user, scope.dayid, scope.timeframe.code, scope.schedule.settingid, role, isAvailable, function() {
 		$(element).parent().qtip('api').hide();
                    scope.refreshTimeframe();
                });
            };
		}
	};
});

gamegrinderApp.directive('ggTimeframeBox', function(plannerService, planningBuilderService) {

	var timeframesDesc = {
			    "AFTERNOON": {"code":"AFTERNOON", pic:"images/aprem.png", name:"Après-midi"},
			    "EVENING": {"code":"EVENING", pic:"images/soir.png", name:"Soirée"},
			};

	return {
		restrict: 'E',
		templateUrl: 'directives/timeframebox.html',
		scope: {
			settingsList: '=settings',
			dayid: '=dayid',
			timeframe: '=timeframe',
			user: '=user',
			statusDesc: '=statusdesc',
 			invisibleOpenSettings: '=invisibleopen',
 			visibleClosedSettings: '=visibleclosed',
 			invisibleStatus: '=invisiblestatus'
		},
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
						$(this).find('.collapsed').hide();
						$(this).find('.collapsible').show();
					}
				    }
				});
			});
			$(element).find('.settingTrigger').first().click(function(event) {
				$(this).slideUp(200);
				var $box=$(this).next('.settingEditor');
				$box.slideDown(200);
				$box.find('.inputSetting').focus();
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
			scope.addSetting = function(setting) {
				plannerService.toggleDispo(scope.user, scope.dayid, scope.timeframe.code, setting.id, 'GM', true, function() {
                    scope.refreshTimeframe();
                    $(element).find('.tfExtra').qtip('api').hide();
				});
			};
			scope.isTfSettingVisible = function(setting) {
			    if ((setting.mode == 0) && (scope.invisibleOpenSettings.indexOf('' + setting.id) > -1)) return false;
			    if ((setting.mode == 1) && (scope.visibleClosedSettings.indexOf('' + setting.id) == -1)) return false;
			    if (scope.invisibleStatus.indexOf('' + scope.getStatusCode(setting)) > -1) return false;
			    return true;
			  };
			scope.refreshTimeframe = function() {
				plannerService.getTimeframePlanning(scope.dayid, scope.timeframe.code, function(result) {
					planningBuilderService.refreshTimeframeInWeeksPlanning(scope.settingsList, result, scope.timeframe, scope.user);
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
		scope: {
			settings: '=settings',
			day: '=day',
			user: '=user',
			statusDesc: '=statusdesc',
 			invisibleopen: '=invisibleopen',
 			visibleclosed: '=visibleclosed',
 			invisiblestatus: '=invisiblestatus'
		},
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


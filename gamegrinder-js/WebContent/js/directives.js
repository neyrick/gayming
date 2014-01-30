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
			status: '=status',
			user: '=user',
			schedule: '=schedule',
			comment: '=comment',
			statusdesc: '=statusdec',
		},
		link: function(scope, element, attrs) {
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
			$('.questionDiv').hover(function(event) {
				$(this).data("old-text", $(this).text());
				$(this).text($(this).attr("alt-text"));
			},
			function(event) {
				$(this).text($(this).data("old-text"));
			});
			scope.validateGame = function($event) {
				$($event.target).slideUp(200);
				$($event.target).nextAll(".gameEditor").slideDown(200);
			}
	            scope.toggleDispo = function(role, isAvailable) {
                	plannerService.toggleDispo(scope.user, scope.dayid, scope.timeframe.code, scope.schedule.id, role, isAvailable, function() {
	                    $window.alert('Done !');
	                });
	            };
		}
	};
});

gamegrinderApp.directive('ggTimeframeBox', function(plannerService) {

	var timeframesDesc = {
			    "AFTERNOON": {"key":"AFTERNOON", pic:"images/aprem.gif", name:"Après-midi"},
			    "EVENING": {"key":"EVENING", pic:"images/soir.gif", name:"Soirée"},
			};

	return {
		restrict: 'E',
		templateUrl: 'directives/timeframebox.html',
		scope: {
			settingsList: '=settings',
			dayid: '=dayid',
			timeframe: '=timeframe',
			user: '=user',
			statusDesc: '=statusDesc',
			status: '=status'
		},
		link: function(scope, element, attrs) {
			scope.timeframesDesc = timeframesDesc;
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
				    window.alert('Done !');
				});
			};
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
			statusDesc: '=statusDesc',
			status: '=status'
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


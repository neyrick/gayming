'use strict';

/* Directives */

gamegrinderApp.directive('ggTfSettingTooltip', function() {
	return {
		restrict: 'E',
		templateUrl: 'directives/tfsettingtooltip.html',
		scope: {

			dayid: '=dayid',
			timeframe: '=timeframe',
			setting: '=setting',
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
			scope.validateGame = function(dayid, timeframecode, settingcode, $event) {
				$($event.target).slideUp(200);
				$($event.target).nextAll(".gameEditor").slideDown(200);
			}
		}
	};
});

'use strict';

var dowCode = [ "", "LU", "MA", "ME", "JE", "VE", "SA", "DI"];

angular.module('gamegrinderFilters', []).filter('dowCode', function() {
    return function(input) {
//	return dowCode[input];
	    return "LU";
    };
});

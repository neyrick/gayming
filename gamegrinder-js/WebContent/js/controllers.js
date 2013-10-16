'use strict';

/* Controllers */

gamegrinderApp.controller('GameGrinderCtrl', [ '$scope', '$cookies', 'userService', function GameGrinderCtrl($scope, $cookies, userService) {

  $scope.currentDay = 0;

  $scope.currentUser =  "PJ1";// cookies['ggUser'];

  $scope.login = function() { $cookies['ggUser'] = $scope.currentUser; };
  $scope.logout = function() { delete $cookies['ggUser']; };


//  $scope.login = function() { userService.login($scope.currentUser); };
//  $scope.logout = function() { currentUser = undefined; userService.logout(); };


//	$scope.currentUser = "toto";
  $scope.dowcodes = { "1":"LU","2":"MA","3":"ME","4":"JE","5":"VE","6":"SA","7":"DI"};

  $scope.timeframesDesc = {
    "AFTERNOON": {"key":"AFTERNOON", pic:"images/aprem.gif"},
    "EVENING": {"key":"EVENING", pic:"images/soir.gif"},
  };

  $scope.settingsList =  {
    "EP": {"id": 1,
     "name": "Eclipse Phase",
     "code": "EP",
     "open": true},
    "7SP": {"id": 2,
     "name": "7th Sea: Pirates",
     "code": "7SP",
     "open": true},
    "TB": {"id": 3,
     "name": "Terres Balafrées",
     "code": "TB",
     "open": true},
    "SRQ": {"id": 4,
     "name": "Shadowrun Québec",
     "code": "SRQ",
     "open": true},
  };

  $scope.weeks = [
    {"days": [
         {"id":"20130923", "dow": 1, "dom": 23, "month": 9, "year": 2013, "timeframes" : [
	{"code":"AFTERNOON", "settings": [
	{"code": "TB",
		"games": [ { "gm": "GM1", "players": [{"name": "PJ1"}, {"name": "PJ2"}, {"name": "PJ3"}]}, { "gm": "GM2", "players": [ {"name": "PJ4"}, {"name": "PJ5"}, {"name": "PJ6"}]}],
		"availablegms": [ {"name": "MJD1"}, {"name": "MJD2"}],
		"availableplayers": [ {"name": "PJD2"}, {"name": "PJD1"}, {"name": "PJD3"}, {"name": "PJD4"}],
	},
	{"code": "7SP"}]},
	{"code":"EVENING", "settings": [
	{"code": "TB"},
	{"code": "7SP"}]}]},
         {"id":"20130924", "dow": 2, "dom": 24, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20130925", "dow": 3, "dom": 25, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20130926", "dow": 4, "dom": 26, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20130927", "dow": 5, "dom": 27, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20130928", "dow": 6, "dom": 28, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20130929", "dow": 7, "dom": 29, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
    ]},
    {"days": [
         {"id":"20130930", "dow": 1, "dom": 30, "month": 9, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20131001", "dow": 2, "dom": 1, "month": 10, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20131002", "dow": 3, "dom": 2, "month": 10, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20131003", "dow": 4, "dom": 3, "month": 10, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20131004", "dow": 5, "dom": 4, "month": 10, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20131005", "dow": 6, "dom": 5, "month": 10, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
         {"id":"20131006", "dow": 7, "dom": 6, "month": 10, "year": 2013,"timeframes" : [
	{"code":"AFTERNOON", "settings": []},
	{"code":"EVENING", "settings": []}]},
    ]},
  ];

	$scope.selectTimeframe = function(newDay, newTimeframe) {
		$scope.currentDay = newDay;
		$scope.currentTimeframe = newTimeframe;
	}
}]);



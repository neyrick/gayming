'use strict';

/* App Module */

var gamegrinderApp = angular.module('gamegrinderApp', ['LocalStorageModule']);

gamegrinderApp.config(function ($httpProvider) {
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    });

gamegrinderApp.config(['localStorageServiceProvider', function(localStorageServiceProvider){
  localStorageServiceProvider.setPrefix('ggPrefix');
}]);

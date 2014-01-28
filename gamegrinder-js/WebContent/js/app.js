'use strict';

/* App Module */

var gamegrinderApp = angular.module('gamegrinderApp', ['ngCookies']);

gamegrinderApp.config(function ($httpProvider) {
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    });

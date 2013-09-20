'use strict';

/* Controllers */

function GameGrinderCtrl($scope) {
  $scope.settings = [
    {"id": 1,
     "name": "Eclipse Phase",
     "open": true},
    {"id": 2,
     "name": "7th Sea: Pirates",
     "open": true},
    {"id": 3,
     "name": "Terres Balafrées",
     "open": true},
  ];

  $scope.months = [
    {"index": 9,
     "name": "Septembre",
     "days": [
         {"index": 1, "dow": 7},
         {"index": 2, "dow": 1},
         {"index": 3, "dow": 2},
         {"index": 4, "dow": 3},
         {"index": 5, "dow": 4},
    ]},
    {"index": 10,
     "name": "Octobre",
     "days": [
         {"index": 1, "dow": 2},
         {"index": 2, "dow": 3},
         {"index": 3, "dow": 4},
         {"index": 4, "dow": 5},
    ]},
  ];

}

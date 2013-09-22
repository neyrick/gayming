'use strict';

/* Controllers */

function GameGrinderCtrl($scope) {
  $scope.settings = [
    {"id": 1,
     "name": "Eclipse Phase",
     "code": "EP",
     "open": true},
    {"id": 2,
     "name": "7th Sea: Pirates",
     "code": "7SP",
     "open": true},
    {"id": 3,
     "name": "Terres Balafrées",
     "code": "TB",
     "open": true},
    {"id": 4,
     "name": "Shadowrun Québec",
     "code": "SRQ",
     "open": true},
  ];

  $scope.weeks = [
    {"id": 1, "days": [
         {"dow": 1, "dom": 23, "month": 9, "year": 2013},
         {"dow": 2, "dom": 24, "month": 9, "year": 2013},
         {"dow": 3, "dom": 25, "month": 9, "year": 2013},
         {"dow": 4, "dom": 26, "month": 9, "year": 2013},
         {"dow": 5, "dom": 27, "month": 9, "year": 2013},
         {"dow": 6, "dom": 28, "month": 9, "year": 2013},
         {"dow": 7, "dom": 29, "month": 9, "year": 2013},
    ]},
    {"id": 2, "days": [
         {"dow": 1, "dom": 30, "month": 9, "year": 2013},
         {"dow": 2, "dom": 1, "month": 10, "year": 2013},
         {"dow": 3, "dom": 2, "month": 10, "year": 2013},
         {"dow": 4, "dom": 3, "month": 10, "year": 2013},
         {"dow": 5, "dom": 4, "month": 10, "year": 2013},
         {"dow": 6, "dom": 5, "month": 10, "year": 2013},
         {"dow": 7, "dom": 6, "month": 10, "year": 2013},
    ]},
  ];

}


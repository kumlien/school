/*
 * Erstes JavaScript-Modul der Anwendung: Anwendungskonfiguration
 */
var angularRouting = angular.module('angularRouting', ['ngRoute', 'controllers']);

angularRouting.config(['$routeProvider',
  function ($routeProvider) {
    $routeProvider.
            when('/view-one', {
              templateUrl: 'fragments/one.html',
              controller: 'ViewOneController'
            }).
            when('/view-two/:Id', {
              templateUrl: 'fragments/two.html',
              controller: 'ViewTwoController'
            }).
            otherwise({
              redirectTo: '/view-one'
            });
  }]);
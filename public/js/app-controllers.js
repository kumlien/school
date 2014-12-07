/*
 * Zweites JavaScript-Modul der Anwendung: Controller-Objekte
 *
 */
var controllers = angular.module('controllers', []);

controllers.controller('ViewOneController', ['$scope', '$http', function ($scope, $http) {
    $http.get('data/persons.json').success(function (data) {
      $scope.persons = data;
    });
  }]);

controllers.controller('ViewTwoController', ['$scope', '$routeParams', '$http', function ($scope, $routeParams, $http) {
    var id = $routeParams.Id;

    $http.get('data/persons.json').success(function (data) {

      $scope.person = {id: '???', name: '???', vorname: '???'};
      for (var n in data) {
        if (data[n].id == id) {
          $scope.person = data[n];
        }
      }
    });
  }]);
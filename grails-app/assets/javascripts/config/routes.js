'use strict';

angular.module('app').config(function ($routeProvider) {
  $routeProvider.when("/plays", {templateUrl: "templates/plays.html"});
  $routeProvider.when("/artist/:name", {templateUrl: "templates/artist.html"});
  $routeProvider.when("/artists", {templateUrl: "templates/artists.html"});

  // Default route: plays screen
  $routeProvider.otherwise({templateUrl: "templates/plays.html"});
});
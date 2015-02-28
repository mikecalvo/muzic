'use strict';

angular.module('app').controller('ArtistController', function ($scope, $routeParams, $location) {
  $scope.artist = {
    name: $routeParams.name,
    wikiName: $routeParams.name.replace(' ', '_')
  };

  $scope.returnToPlays = function() {
    console.log('Goodbye '+$scope.artist.name+'.  Going back to the plays view...');
    $location.path('/');
  }
});

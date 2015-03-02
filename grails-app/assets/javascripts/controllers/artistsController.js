'use strict';

angular.module('app').controller('ArtistsController', function ($scope, $resource, confirmDialog) {
  var Artist = $resource('api/artists/:id', {}, {create: {method: 'PUT'}});

  var refreshArtistList = function () {
    $scope.artists = Artist.query();
    $scope.artists.$promise.then(function (result) {
      $scope.totalItems = result.length;
      $scope.currentPage = 1;
    });
  };

  $scope.addArtist = function () {
    $scope.newArtist = {};
    $scope.alerts = [];
  };

  $scope.saveArtist = function () {
    var artist = $scope.newArtist;
    Artist.save(artist).$promise.then(function () {
      $scope.alerts.push({type: 'success', msg: 'Artist added: ' + artist.name});
      refreshArtistList();
    });
    delete $scope.newArtist;
  };

  $scope.deleteArtist = function (artist) {
    $scope.alerts = [];
    confirmDialog('Confirm Play Delete', 'Are you sure you want to delete artist "' + artist.name + '"?')
      .result.then(function () {
        artist.$delete({id: artist.id}).then(function () {
          $scope.alerts.push({type: 'success', msg: artist.name + ' removed'});
          refreshArtistList();
        });
      });
  };

  $scope.alerts = [];
  refreshArtistList();
});
'use strict';

angular.module('app').controller('SongPlaysController', function ($scope, $http, confirmDialog, Artist) {
  var getPlayData = function () {
    return $http.get('play/').then(function (response) {
      $scope.plays = response.data;
    });
  };

  getPlayData();
  $scope.alerts = [];

  $scope.addPlay = function () {
    var now = new Date();
    $scope.newPlay = {timestamp: now};
  };

  $scope.resetAdd = function () {
    delete $scope.newPlay;
    $scope.playForm.$setPristine();
    $scope.playForm.$setUntouched();
  };

  $scope.savePlay = function () {
    if (!$scope.newPlay) {
      return
    }

    var title = $scope.newPlay.song.title;
    var artist = $scope.newPlay.song.artist.name;

    $http.get('play/report', {
      params: {
        title: title,
        artistName: artist
      }
    }).then(function () {
      $scope.alerts.push({type: 'success', msg: 'Added play: "' + title + '" by ' + artist});
      getPlayData();
    }, function (error) {
      $scope.alerts.put({type: 'danger', msg: 'Error creating play: ' + error});
    });

    $scope.resetAdd();
  };

  $scope.getArtist = function (input) {
    $scope.loadingArtists = true;
    return Artist.query({q: input}).$promise.then(function (results) {
      var artists = [];
      for (var i = 0; i < results.length; i++) {
        artists.push(results[i].name)
      }
      $scope.loadingArtists = false;
      return artists;
    });
  };

  $scope.closeAlert = function (index) {
    $scope.alerts.splice(index, 1);
  };

  $scope.deletePlay = function (play) {
    confirmDialog('Confirm Play Delete',
      'Are you sure you want to delete "' + play.song.title + '" by ' + play.song.artist.name + '?')
      .result.then(function () {
        $http.delete('play/delete/' + play.id).then(function () {
          $scope.alerts.push({type: 'success', msg: 'Song play removed'});
          getPlayData();
        })
      });
  }
});
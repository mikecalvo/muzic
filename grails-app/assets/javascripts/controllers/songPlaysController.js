'use strict';

angular.module('app').controller('SongPlaysController', function ($scope, $modal) {
  var getPlayData = function () {
    return [
      {song: {title: 'Blue Monday'}, artist: {name: 'New Order'}, time: new Date('02/14/2015 12:37:00')},
      {song: {title: 'We Want the Airwaives'}, artist: {name: 'Ramones'}, time: new Date('02/14/2015 11:32')},
      {song: {title: 'Kids With Guns'}, artist: {name: 'Gorillaz'}, time: new Date('02/14/2015 11:22')}
    ];
  };

  $scope.plays = getPlayData();
  $scope.alerts = [];

  $scope.addPlay = function () {
    var now = new Date();
    $scope.newPlay = {time: now};
  };

  $scope.savePlay = function () {
    $scope.plays.push($scope.newPlay);
    $scope.alerts.push({type: 'success', msg: 'Song play added'});
    delete $scope.newPlay;
  };

  $scope.getArtist = function (input) {
    var results = [];
    input = input.toLowerCase();
    var plays = $scope.plays;
    for (var i = 0; i < plays.length && results.length < 8; i++) {
      var artist = plays[i].artist;
      if (artist && artist.name.toLowerCase().indexOf(input) !== -1) {
        results.push(artist.name);
      }
    }

    setTimeout(function () {
      return results;
    }, 1000);
    return results;
  };

  $scope.closeAlert = function (index) {
    $scope.alerts.splice(index, 1);
  };

  $scope.deletePlay = function (play) {
    var modalInstance = $modal.open({
      templateUrl: 'templates/dialogs/confirmDialog.html',
      size: 'lg',
      controller: 'confirmDialogController',
      resolve: {
        message: function () {
          return 'Are you sure you want to delete "' + play.song.title + '" by ' + play.artist.name + '?'
        },
        title: function () {
          return 'Confirm Play Delete';
        }
      }
    });

    modalInstance.result.then(function () {
      $scope.plays.splice($scope.plays.indexOf(play), 1);
      $scope.alerts.push({type: 'success', msg: 'Song play removed'});
    });
  }

})

  .controller('confirmDialogController', function ($scope, $modalInstance, title, message) {
    $scope.message = message;
    $scope.title = title;

    $scope.ok = function () {
      $modalInstance.close();
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  });
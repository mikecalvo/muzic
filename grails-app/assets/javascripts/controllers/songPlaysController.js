'use strict';

angular.module('app').controller('SongPlaysController', function ($scope, $modal, $http) {
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

  $scope.savePlay = function () {
    $http.get('play/report', {
      params: {
        title: $scope.newPlay.song.title,
        artistName: $scope.newPlay.song.artist.name
      }
    }).then(function () {
      $scope.alerts.push({type: 'success', msg: 'Song play added'});
      getPlayData();
    }, function (error) {
      $scope.alerts.put({type: 'danger', msg: 'Error creating play: ' + error});
    });
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
          return 'Are you sure you want to delete "' + play.song.title + '" by ' + play.song.artist.name + '?'
        },
        title: function () {
          return 'Confirm Play Delete';
        }
      }
    });

    modalInstance.result.then(function () {
      $http.delete('play/delete/'+play.id).then(function() {
        $scope.alerts.push({type: 'success', msg: 'Song play removed'});
        getPlayData();
      })
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
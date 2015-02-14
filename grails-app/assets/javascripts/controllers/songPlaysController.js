angular.module('app').controller('songPlaysController', function ($scope) {
  var getPlayData = function () {
    return [
      {song: {title: 'Blue Monday'}, artist: {name: 'New Order'}, time: new Date('02/14/2015 12:37:00')},
      {song: {title: 'We Want the Airwaives'}, artist: {name: 'Ramones'}, time: new Date('02/14/2015 11:32')},
      {song: {title: 'Kids With Guns'}, artist: {name: 'Gorillaz'}, time: new Date('02/14/2015 11:22')}
    ];
  };

  $scope.plays = getPlayData();

  $scope.addPlay = function() {
    var now = new Date();
    $scope.newPlay = {time: now};
  };

  $scope.savePlay = function() {
    $scope.plays.push($scope.newPlay);
    delete $scope.newPlay;
  };

});
angular.module('app').controller('welcomeController', function($scope, loggedInUser) {
  $scope.loggedInUser = loggedInUser;
});
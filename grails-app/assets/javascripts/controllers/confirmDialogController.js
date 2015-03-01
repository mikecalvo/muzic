'use strict';

angular.module('app')
  .controller('ConfirmDialogController', function ($scope, $modalInstance, title, message) {
    $scope.message = message;
    $scope.title = title;

    $scope.ok = function () {
      $modalInstance.close();
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  });
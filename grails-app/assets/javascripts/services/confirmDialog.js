'use strict';

angular.module('app').factory('confirmDialog', function($modal) {
  return function(title, message) {

    return $modal.open({
      templateUrl: 'templates/dialogs/confirmDialog.html',
      size: 'lg',
      controller: 'ConfirmDialogController',
      resolve: {
        message: function () { return message; },
        title: function () { return title; }
      }
    });

  }
});
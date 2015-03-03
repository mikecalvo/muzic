'use strict';

angular.module('app').factory('Artist', function ($resource) {
  return $resource('api/artists/:id', {},
    {create: {method: 'PUT'}}
  );
});
<!DOCTYPE html>
<html>
<head>
  <asset:stylesheet href="aplication.css"/>
  <asset:javascript src="application.js"/>

</head>

<body ng-app="app">

<div ng-controller="welcomeController">
  {{ message }}
</div>

<script>
  var app = angular.module('app', []);
  app.controller('welcomeController', function ($scope) {
    $scope.message = 'Welcome to the Muzic App'
  })
</script>
</body>
</html>

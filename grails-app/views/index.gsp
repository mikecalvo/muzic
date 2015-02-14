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

<div ng-controller="songPlaysController">

  <button ng-click="addPlay()" ng-show="!newPlay">Add a Play</button>
  <table>
    <thead>
    <tr>
      <td>Title</td><td>Artist</td><td>Date/Time</td>
    </tr>
    </thead>
    <tr ng-repeat="play in plays">
      <td>{{ play.song.title }}</td>
      <td>{{ play.artist.name }}</td>
      <td>{{ play.time | date :'medium' }}</td>
    </tr>
    <tr ng-show="newPlay">
      <td><input placeholder="Title" ng-model="newPlay.song.title"></td>
      <td><input placeholder="Artist" ng-model="newPlay.artist.name"></td>
      <td>{{ newPlay.time | date :'medium' }} <button ng-click="savePlay()">Save</button></td>
    </tr>
  </table>
</div>

</body>
</html>

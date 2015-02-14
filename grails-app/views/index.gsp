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

  <button ng-click="addPlay()">Add a Play</button>
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
  </table>
</div>

</body>
</html>

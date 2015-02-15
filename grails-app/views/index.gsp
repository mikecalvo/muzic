<!DOCTYPE html>
<html>
<head>
  <asset:stylesheet href="application.css"/>
  <asset:javascript src="application.js"/>

</head>

<body ng-app="app">

<div ng-controller="welcomeController">
  {{ message }}
</div>

<div ng-controller="songPlaysController">

  <alert ng-repeat="alert in alerts" type="{{alert.type}}" close="closeAlert($index)">{{alert.msg}}</alert>

  <div>
    <button id="add-play-btn" class="btn btn-default" ng-click="addPlay()" ng-show="!newPlay">Add a Play</button>
  </div>

  <table>
    <thead>
    <tr>
      <td>Title</td><td>Artist</td><td>Date/Time</td><td></td>
    </tr>
    </thead>
    <tr ng-repeat="play in plays">
      <td>{{ play.song.title }}</td>
      <td>{{ play.artist.name }}</td>
      <td>{{ play.time | date :'medium' }}</td>
      <td>
        <button ng-click="deletePlay(play)"><i class="glyphicon glyphicon-trash"></i></button>
      </td>
    </tr>
    <tr ng-show="newPlay">
      <td><input placeholder="Title" ng-model="newPlay.song.title"></td>
      <td><input placeholder="Artist"
                 ng-model="newPlay.artist.name"
                 typeahead="artist for artist in getArtist($viewValue)"
                 typeahead-loading="loadingArtists"/>

        <div ng-show="loadingArtists" class="spinner" style="position: relative; display: inline-block"></div>
      </td>
      <td>{{ newPlay.time | date :'medium' }}</td>
      <td><button class="btn btn-default" ng-click="savePlay()">Save</button></td>
    </tr>
  </table>
</div>

</body>
</html>

'use strict';

describe('SongPlaysController', function () {

  var $controller, $httpBackend, $scope;
  var now = new Date();
  var plays = [
    {song: {title: 'Loser', artist: {name: 'Beck'}}, timestamp: now},
    {song: {title: 'Enjoy The Silence', artist: {name: 'Depeche Mode'}}, timestamp: now}];

  beforeEach(module('app'));

  beforeEach(inject(function ($injector) {
    $controller = $injector.get('$controller');
    $httpBackend = $injector.get('$httpBackend');
    $scope = $injector.get('$rootScope').$new();
  }));

  describe('initializes scope', function () {

    it('fetches the play data into scope', function () {
      $httpBackend.expectGET('play/').respond(200, plays);
      $controller('SongPlaysController', {$scope: $scope});
      $httpBackend.flush();

      expect($scope.plays).toEqual(plays);
    });

  });

  describe('responds to actions', function () {
    var $templateCache;

    beforeEach(inject(function ($compile) {
      $compile(angular.element('<form name="playForm"></form>'))($scope);

      $httpBackend.expectGET('play/').respond(200, plays);
      $controller('SongPlaysController', {$scope: $scope});
      $httpBackend.flush();
    }));


    it('adds a play', function () {
      expect(typeof $scope.addPlay).toBe('function');
      $scope.addPlay();
      expect(typeof $scope.newPlay).toBe('object');
      expect($scope.newPlay.timestamp).toBeTruthy();
    });

    it('resets an add', function () {
      $scope.resetAdd();
      expect($scope.newPlay).toBe(undefined);
      expect($scope.playForm.$pristine).toBeTruthy();
      expect($scope.playForm.$dirty).toBeFalsy();
    });

    it('saves a play successfully', function () {
      $scope.newPlay = {song: {title: 'Girlfriend in a Coma', artist: {name: 'The Smiths'}}, timestamp: new Date()};
      $httpBackend.expectGET('play/report?artistName=The+Smiths&title=Girlfriend+in+a+Coma').respond(200);
      $httpBackend.expectGET('play/').respond(200, plays);
      $scope.savePlay();
      $httpBackend.flush();
      expect($scope.alerts).toEqual([{type: 'success', msg: 'Added play: "Girlfriend in a Coma" by The Smiths'}]);
    });

    it('saves a play error', function () {
      $scope.newPlay = {song: {title: 'Girlfriend in a Coma', artist: {name: 'The Smiths'}}, timestamp: new Date()};
      $httpBackend.expectGET('play/report?artistName=The+Smiths&title=Girlfriend+in+a+Coma').respond(503, 'Badness');
      $scope.savePlay();
      $httpBackend.flush();
      expect($scope.alerts).toEqual([{type: 'danger', msg: 'Error creating play: Badness'}]);
    });
  });

  afterEach(function () {
    $httpBackend.verifyNoOutstandingExpectation();
    $httpBackend.verifyNoOutstandingRequest();
  });
})
;

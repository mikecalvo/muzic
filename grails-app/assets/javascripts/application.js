// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery/dist/jquery
//= require bootstrap/dist/js/bootstrap
//= require angular/angular
//= require angular-route/angular-route
//= require angular-bootstrap/ui-bootstrap-tpls
//= require_self
//= require_tree .

angular.module('app', ['ngRoute', 'ui.bootstrap']);



var myApp = angular.module("myApp", ["ui.router","ngResource"]);

myApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('home', {
            url: '/',
            controller:'AppController',
            templateUrl: 'templates/home.html'
        });

        // .state('about', {
        //     // we'll get to this in a bit
        // });

});
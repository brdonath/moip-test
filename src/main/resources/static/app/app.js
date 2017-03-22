var myApp = angular.module("myApp", ["ui.router", "ngResource"]);

myApp.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('home', {
            url: '/',
            controller: 'AppController',
            templateUrl: 'templates/home.html'
        })

        .state('checkout', {
            url: '/checkout',
            params: {product: null},
            controller: 'CheckoutController',
            templateUrl: 'templates/checkout.html'
        })
        .state('receipt', {
            url: '/receipt',
            controller: 'ReceiptController',
            templateUrl: 'templates/receipt.html'
        });

});
'use strict';

/**
 * @ngdoc overview
 * @name yunmartApp
 * @description
 * # yunmartApp
 *
 * Main module of the application.
 */
angular
    .module('yunmartApp', [
        'ngAnimate',
        'ngCookies',
        'ngMessages',
        'ngResource',
        'ngSanitize',
        'ngTouch',
        'ui.router',
        'ui.bootstrap',
        'LocalStorageModule'
    ])
    .config(['$urlRouterProvider', '$stateProvider', 'localStorageServiceProvider', function ($urlRouterProvider, $stateProvider, localStorageServiceProvider) {

            localStorageServiceProvider.setPrefix('yunmart');
            $urlRouterProvider.otherwise('/');
            $stateProvider.state('site', {
                abstract: true,
                views: {
                    "navbar@": {
                        templateUrl: 'scripts/components/navbar/navbar.tpl.html'
                    }
                }
            })
        }])
    .run(['$http', 'localStorageService', function ($http, localStorageService) {
        $http.get('/api/product/categories')
            .success(function (data) {
                localStorageService.set('product.categories', data);
            });
    }]);

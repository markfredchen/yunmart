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
    .config(['$urlRouterProvider', '$stateProvider', 'localStorageServiceProvider', '$httpProvider', function ($urlRouterProvider, $stateProvider, localStorageServiceProvider, $httpProvider) {
        $httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN';
        $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';

        localStorageServiceProvider.setPrefix('yunmart');
        $urlRouterProvider.otherwise('/');
        $stateProvider.state('site', {
            abstract: true,
            views: {
                'navbar@': {
                    templateUrl: 'scripts/components/navbar/navbar.tpl.html',
                    controller: 'NavbarController'
                },
                'footer@': {
                    templateUrl: 'scripts/components/footer/footer.tpl.html'
                }
            }
        });
    }])
    .run(['$rootScope', '$state', '$http', 'localStorageService', 'Auth', 'Principal', function ($rootScope, $state, $http, localStorageService, Auth, Principal) {
        $http.get('/api/product/categories')
            .success(function (data) {
                localStorageService.set('product.categories', data);
            });
        $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
            $rootScope.toState = toState;
            $rootScope.toStateParams = toStateParams;

            if (Principal.isIdentityResolved()) {
                Auth.authorize();
            }
        });
        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            $rootScope.previousStateName = fromState.name;
            $rootScope.previousStateParams = fromParams;

        });
        $rootScope.back = function () {
            // If previous state is 'activate' or do not exist go to 'home'
            if ($rootScope.previousStateName === 'activate' || $state.get($rootScope.previousStateName) === null) {
                $state.go('home');
            } else {
                $state.go($rootScope.previousStateName, $rootScope.previousStateParams);
            }
        };
    }]);

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
        'LocalStorageModule',
        'tmh.dynamicLocale',
        'pascalprecht.translate',
        'bootstrap.fileField',
        'ImageCropper'
    ])
    .config(['$urlRouterProvider', '$stateProvider', 'localStorageServiceProvider', '$httpProvider', '$translateProvider',
        function ($urlRouterProvider, $stateProvider, localStorageServiceProvider, $httpProvider, $translateProvider) {
            $httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN';
            $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';
            // i18n
            // Initialize angular-translate
            $translateProvider.useLoader('$translatePartialLoader', {
                urlTemplate: 'i18n/{lang}/{part}.json'
            });
            $translateProvider.preferredLanguage('zh_CN');
            $translateProvider.useCookieStorage();
            $translateProvider.useSanitizeValueStrategy('escaped');
            //$translateProvider.addInterpolation('$translateMessageFormatInterpolation');

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
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }],
                    authorize: ['Auth', function (Auth) {
                        return Auth.authorize();
                    }]
                }
            });
        }])
    .run(['$rootScope', '$state', '$http', 'localStorageService', 'Auth', 'Principal', function ($rootScope, $state, $http, localStorageService, Auth, Principal) {
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

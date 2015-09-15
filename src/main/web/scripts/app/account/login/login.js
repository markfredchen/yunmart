/**
 * Created by markfredchen on 9/12/15.
 */
'use strict';
angular.module('yunmartApp')
    .config(['$stateProvider', function($stateProvider){
        $stateProvider.state('login', {
            parent: 'account',
            url: '/login',
            data:{
                roles: []
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/account/login/login.tpl.html',
                    controller: 'LoginController'
                }
            }
        })
    }])
    .controller('LoginController', ['$scope', 'Auth', '$state', '$timeout', '$rootScope', function($scope, Auth, $state, $timeout, $rootScope) {
        $scope.credential = {};

        $scope.errors = {};

        $timeout(function (){angular.element('[ng-model="credential.username"]').focus();});
        $scope.login = function () {

            Auth.login({
                username: $scope.credential.username,
                password: $scope.credential.password,
                rememberMe: true
            }).then(function () {
                $scope.authenticationError = false;
                if ($rootScope.previousStateName === 'register') {
                    $state.go('home');
                } else {
                    $rootScope.back();
                }
            }).catch(function () {
                $scope.authenticationError = true;
            });
        }
    }]);

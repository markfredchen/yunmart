/**
 * Created by markfredchen on 9/14/15.
 */
'use strict';
angular.module('yunmartApp')
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('forget_password', {
                parent: 'account',
                url: '/forget/password/{token}',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/account/forget/forget.password.reset.tpl.html',
                        controller: 'ForgetPasswordController'
                    }
                },
                resolve: {

                }
            })
            .state('send_forget_password_token', {
                parent: 'account',
                url: '/send/forget/password/token',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/account/forget/forget.password.token.tpl.html',
                        controller: 'SendForgetPasswordTokenController'
                    }
                }
            })
            .state('send_forget_password_token_success', {
                parent: 'account',
                url: '/send/forget/password/token/success',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/account/forget/forget.password.success.tpl.html',
                        controller: 'SendForgetPasswordTokenSuccessController'
                    }
                }
            })
    }])
    .controller('ForgetPasswordController', ['$scope', 'ForgetPassword', function ($scope, ForgetPassword) {

    }])
    .controller('SendForgetPasswordTokenController', ['$scope', 'ForgetPassword', '$state', function ($scope, ForgetPassword, $state) {
        $scope.usernameOrEmail = "";
        $scope.userNotExists = false;
        $scope.getForgetPasswordToken = function () {
            $scope.userNotExists = false;
            ForgetPassword.getForgetPasswordToken($scope.usernameOrEmail)
                .success(function () {
                    $state.go('send_forget_password_token_success');
                })
                .error(function (error, status) {
                    if(status === 400 && error.validationErrors[0].externalPropertyName === 'usernameOrEmail') {
                        $scope.userNotExists = true;
                        console.log(error);
                    } else {
                        $state.go('error');
                    }
                })
        };

    }])
    .controller('SendForgetPasswordTokenSuccessController', ['$rootScope', '$state', function ($rootScope, $state) {
        if($rootScope.previousStateName !== 'send_forget_password_token') {
            $state.go('home');
        }
    }]);

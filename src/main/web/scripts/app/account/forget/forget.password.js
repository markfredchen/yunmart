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
    }])
    .controller('ForgetPasswordController', ['$scope', 'ForgetPassword', function ($scope, ForgetPassword) {

    }])
    .controller('SendForgetPasswordTokenController', ['$scope','ForgetPassword', function ($scope, ForgetPassword) {
        $scope.usernameOrEmail = "";
        $scope.userNotExists = false;
        $scope.getForgetPasswordToken = function () {
            $scope.userNotExists = false;
            ForgetPassword.getForgetPasswordToken($scope.usernameOrEmail)
                .then(function (data) {
                    console.log(data);
                })
                .catch(function (error) {
                    $scope.userNotExists = true;
                    console.log(error);
                })
        };

    }]);

/**
 * Created by markfredchen on 9/12/15.
 */
'use strict';
angular.module('yunmartApp')
    .config(['$stateProvider', function($stateProvider){
        $stateProvider.state('register', {
            parent: 'account',
            url: '/register',
            data:{
                roles: []
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/account/register/register.tpl.html',
                    controller: 'RegisterController'
                }
            }
        })
    }])
    .controller('RegisterController', ['$scope', '$state', 'Auth', 'PublicAccountName', function($scope, $state, Auth, PublicAccountName) {
        $scope.success = null;
        $scope.error = null;
        $scope.doNotMatch = null;
        $scope.errorUserExists = null;
        $scope.user = {};

        $scope.register = function () {
            if ($scope.user.password !== $scope.confirmPassword) {
                $scope.doNotMatch = 'ERROR';
            }else {
                $scope.doNotMatch = null;
                $scope.error = null;
                $scope.errorUserExists = null;
                $scope.errorEmailExists = null;
                $scope.user.accountName = PublicAccountName;
                $scope.user.authorities = ['ROLE_USER'];
                Auth.register($scope.user).then(function () {
                    $state.go('login');
                }).catch(function (response) {
                    $scope.success = null;
                    if (response.status === 400 && response.data === 'login already in use') {
                        $scope.errorUserExists = 'ERROR';
                    } else if (response.status === 400 && response.data === 'e-mail address already in use') {
                        $scope.errorEmailExists = 'ERROR';
                    } else {
                        $scope.error = 'ERROR';
                    }
                })
            }
        };


    }]);

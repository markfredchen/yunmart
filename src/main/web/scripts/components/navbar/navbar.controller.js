/**
 * Created by markfredchen on 9/13/15.
 */
'use strict';
angular.module('yunmartApp')
    .controller('NavbarController', ['$scope', '$state', '$translate', 'Auth', 'Principal',
        function ($scope, $state, $translate, Auth, Principal) {
            $scope.user = {};
            $scope.isAuthenticated = Principal.isAuthenticated;
            $scope.fullName = $translate('navbar.menu.authenticated.fullName', {
                firstName: $scope.user.firstName,
                lastName: $scope.user.lastName
            });
            $scope.$on('userLoggedIn', function () {
                $scope.init();
            })
            $scope.logout = function () {
                Auth.logout();
                $state.go('home');
            };

            $scope.init = function () {
                if(Principal.isAuthenticated()) {
                    Principal.identity().then(function (data) {
                        $scope.user = data;
                    });
                }
            };
            $scope.init();
        }]);

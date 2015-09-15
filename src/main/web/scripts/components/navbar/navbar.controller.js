/**
 * Created by markfredchen on 9/13/15.
 */
'use strict';
angular.module('yunmartApp')
    .controller('NavbarController', ['$scope', '$state', 'Auth', 'Principal',
        function ($scope, $state, Auth, Principal) {
            $scope.user = {};
            Principal.identity().then(function(data) {
                $scope.user = data;
                $scope.isAuthenticated = Principal.isAuthenticated;
            });
            $scope.logout = function () {
                Auth.logout();
                $state.go('home');
            };
        }])

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
    .controller('RegisterController', ['$scope', function($scope) {
        $scope.user = {};

    }]);

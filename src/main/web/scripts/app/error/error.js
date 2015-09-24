/**
 * Created by markfredchen on 9/15/15.
 */
'use strict';
angular.module('yunmartApp')
    .config(['$stateProvider', function($stateProvider){
        $stateProvider.state('error', {
            parent: 'site',
            url: '/error',
            data: {
                roles: []
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/error/error.tpl.html',
                    controller: 'ErrorController'
                }
            }
        });
    }])
    .controller('ErrorController', ['$scope', function($scope) {

    }]);

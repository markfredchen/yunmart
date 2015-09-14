/**
 * Created by markfredchen on 9/12/15.
 */
'use strict';
angular.module('yunmartApp')
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('account', {
            abstract: true,
            parent: 'site'
        })
    }]);

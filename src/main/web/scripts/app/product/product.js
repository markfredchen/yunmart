/**
 * Created by markfredchen on 9/8/15.
 */
'use strict';
angular.module('yunmartApp')
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('product', {
            abstract: true,
            parent: 'site'
        });
    }]);

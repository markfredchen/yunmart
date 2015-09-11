/**
 * Created by markfredchen on 9/8/15.
 */
'use strict';
angular.module('yunmartApp')
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('product.list', {
            parent: 'site',
            url: '/product/list/{categoryID}',
            views: {
                'content@': {
                    templateUrl: 'scripts/app/product/list/product.list.tpl.html',

                }
            }

        })
    }])

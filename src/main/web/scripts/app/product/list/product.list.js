/**
 * Created by markfredchen on 9/8/15.
 */
'use strict';
angular.module('yunmartApp')
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('product.list', {
            url: '/product/list/{categoryID}',
            views: {
                'productContent@product': {
                    templateUrl: 'scripts/app/product/list/product.list.tpl.html',
                    controller: 'ProductController'
                }
            },
            data:{
                roles: []
            },
            resolve: {
                currentCategory: ['$stateParams', 'localStorageService', function ($stateParams, localStorageService) {
                    var categoryID = parseInt($stateParams.categoryID);
                    var categories = localStorageService.get('product.categories');
                    for (var i = 0; i < categories.length; i++) {
                        var category = categories[i];
                        if(category.categoryID === categoryID) {
                            return category;
                        }
                    }
                }]
            }

        });

    }])
    .controller('ProductController', ['$scope', 'currentCategory', function ($scope, currentCategory) {
        $scope.productListTitle = currentCategory.name;
        $scope.category = currentCategory.productCategoryID;
    }])

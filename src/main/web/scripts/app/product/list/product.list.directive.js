/**
 * Created by markfredchen on 9/8/15.
 */
'use strict';
angular.module('yunmartApp')
    .directive('productList', ['Product', function (Product) {
        return {
            restrict: 'E',
            templateUrl: 'scripts/app/product/list/product.list.directive.tpl.html',
            scope: {
              category: "="
            },
            controller: ['$scope', function ($scope) {
                $scope.page = 1;
                $scope.size = 3;
                $scope.maxPage = 15;
                $scope.pageData = {};


                $scope.loadProducts = function () {
                    if ($scope.category) {

                    }else {

                    }
                    Product.query({page: $scope.page - 1, size: $scope.size}, function (result) {
                        $scope.pageData = result;
                    })
                };

                $scope.loadProducts();
            }]
        }
    }])

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
                category: "=",
                title: '='
            },
            controller: ['$scope', function ($scope) {

                $scope.page = 1;
                $scope.size = 3;
                $scope.maxPage = 15;
                $scope.pageData = {};


                $scope.loadProducts = function () {
                    var productRequest = {};
                    productRequest.page = $scope.page - 1;
                    productRequest.size = $scope.size;
                    if ($scope.category) {
                        productRequest.productCategoryID = $scope.category;
                        console.log($scope.category);
                        console.log(productRequest);
                    }


                    Product.query(productRequest, function (result) {
                        $scope.pageData = result;
                    });

                };

                $scope.loadProducts();
            }]
        }
    }])

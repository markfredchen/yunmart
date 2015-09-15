/**
 * Created by markfredchen on 9/11/15.
 */
'use strict';
angular.module('yunmartApp')
    .directive('categoryList', ['localStorageService', function (localStorageService) {
        return {
            restrict: 'E',
            templateUrl: 'scripts/app/category/category.list.directive.tpl.html',
            controller: ['$scope', 'localStorageService', '$http', function ($scope, localStorageService, $http) {
                $scope.categories = {};
                $scope.getCategories = function () {
                    $scope.categories = localStorageService.get('product.categories');
                    if (!$scope.categories) {
                        $http.get('/api/product/categories')
                            .success(function (data) {
                                $scope.categories = data;
                                localStorageService.set('product.categories', data);
                            });
                    }
                };
                $scope.getCategories();

            }]
        };
    }]);

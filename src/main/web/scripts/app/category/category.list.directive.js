/**
 * Created by markfredchen on 9/11/15.
 */
'use strict';
angular.module('yunmartApp')
    .directive('categoryList', ['localStorageService', function (localStorageService) {
        return {
            restrict: 'E',
            templateUrl: 'scripts/app/category/category.list.directive.tpl.html',
            controller: ['$scope', 'localStorageService', function ($scope, localStorageService) {
                $scope.categories = localStorageService.get('product.categories');
            }]
        };
    }])

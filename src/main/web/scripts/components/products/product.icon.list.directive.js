/**
 * Created by markfredchen on 9/23/15.
 */
'use strict';
angular.module('yunmartApp')
    .directive('productIconList', ['$http', 'ParseLinks', function ($http, ParseLinks) {
        return {
            restrict: 'E',
            templateUrl: 'scripts/components/products/product.icon.list.directive.tpl.html',
            scope: {
                apiUrl: "@",
                perPage: "@"
            },
            controller: ['$scope', function ($scope) {
                $scope.init = function () {
                    if (!angular.isDefined($scope.perPage)) {
                        $scope.perPage = 6;
                    }
                    $scope.loadAll();
                };

                $scope.page = 1;
                $scope.pageData = {};

                $scope.loadAll = function () {
                    $http({
                        method: 'GET',
                        url: $scope.apiUrl,
                        params: {
                            page: $scope.page,
                            per_page: $scope.perPage
                        }
                    }).success(function (data, status, headers) {
                        $scope.pageData.links = ParseLinks.parse(headers('link'));
                        $scope.pageData.data = data;
                    })
                };

                $scope.loadPage = function (page) {
                    $scope.page = page;
                    $scope.loadAll();
                };

                $scope.init();
            }]
        }
    }])

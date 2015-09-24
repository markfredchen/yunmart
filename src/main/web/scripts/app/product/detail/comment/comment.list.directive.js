/**
 * Created by markfredchen on 9/16/15.
 */
'use strict';
angular.module('yunmartApp')
    .directive('commentList', ['ProductComment', 'ParseLinks', function (ProductComment, ParseLinks) {
        return {
            restrict: 'E',
            templateUrl: 'scripts/app/product/detail/comment/comment.list.directive.tpl.html',
            scope: {
                productOID: "="
            },
            controller: ['$scope', function ($scope) {
                $scope.page = 1;
                $scope.per_page = 10;

                $scope.$on('productCommentSaved', function () {
                    $scope.page = 1;
                    $scope.refresh();
                })

                $scope.loadAll = function () {
                    ProductComment.query($scope.productOID, $scope.page, $scope.per_page)
                        .success(function (data, status, headers) {
                            $scope.links = ParseLinks.parse(headers('link'));
                            $scope.comments = data;
                        })
                };

                $scope.loadPage = function (page) {
                    $scope.page = page;
                    $scope.loadAll();
                    $('html, body').animate({
                        scrollTop: $('#productCommentList').offset().top
                    }, 500);
                };
                $scope.loadAll();

                $scope.refresh = function () {
                    $scope.loadAll();
                };

            }]

        };
    }]);

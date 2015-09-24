/**
 * Created by markfredchen on 9/16/15.
 */
'use strict';
angular.module('yunmartApp')
    .directive('commentForm', ['ProductComment', '$rootScope', 'Principal', function (ProductComment, $rootScope, Principal) {
        return {
            restrict: 'E',
            templateUrl: 'scripts/app/product/detail/comment/comment.form.directive.tpl.html',
            scope: {
                productOID: '='
            },
            controller: ['$scope', function ($scope) {
                $scope.comment = {};
                $scope.comment.productOID = $scope.productOID;

                $scope.createComment = function () {
                    ProductComment.save($scope.comment).success(function (data) {
                        $scope.comment = {};
                        $scope.comment.productOID = $scope.productOID;
                        $rootScope.$broadcast("productCommentSaved", {commentID: data.commentID});
                    });
                };
            }]
        }
    }])

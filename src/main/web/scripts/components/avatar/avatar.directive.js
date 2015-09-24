/**
 * Created by markfredchen on 9/20/15.
 */
'use strict';
angular.module('yunmartApp')
    .directive('avatar', [function () {
        return {
            restrict: 'E',
            templateUrl: 'scripts/components/avatar/avatar.directive.tpl.html',
            scope: {
                blankAvatarUrl: '@',
                avatarFileType: '=',
                userOID: '@'
            },
            controller: ['$scope', 'AvatarBaseURL', function ($scope, AvatarBaseURL) {
                $scope.$on('AvatarUpdated', function (event, data) {
                    $scope.avatarUrl = AvatarBaseURL + $scope.userOID + '.' + data.avatarFileType;
                    $scope.currentAvatarUrl = $scope.avatarUrl + '?rand=' + Math.random();
                });
                $scope.$watch('avatarFileType', function () {
                    $scope.updateCurrentUrl();
                })

                $scope.updateCurrentUrl = function () {
                    if($scope.avatarFileType) {
                        $scope.avatarUrl = AvatarBaseURL + $scope.userOID + '.' + $scope.avatarFileType;
                        $scope.currentAvatarUrl = $scope.avatarUrl;
                    } else {
                        $scope.currentAvatarUrl = $scope.blankAvatarUrl;
                    }
                };
                $scope.updateCurrentUrl();


            }]
        }
    }])

/**
 * Created by markfredchen on 9/19/15.
 */
'use strict';
angular.module('yunmartApp')
    .config(['$stateProvider', function($stateProvider){
        $stateProvider.state('profile', {
            parent: 'account',
            url: '/profile',
            data: {
                roles: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/account/profile/profile.tpl.html',
                    controller: 'ProfileController'
                }
            },
            resolve: {
                profile: ['Principal', function (Principal) {
                    return Principal.identity();
                }],
                profileTranslateLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('profile');
                    return $translate.refresh();
                }]
            }
        })
    }])
    .controller('ProfileController', ['$scope', '$modal', 'profile', 'PublicAccountOID', function($scope, $modal, profile, PublicAccountOID) {
        $scope.PublicAccountOID = PublicAccountOID;
        $scope.profile = profile;
        $scope.profileAvatar = '/images/blank-avatar.png';
        $scope.openChangeAvatarDialog = function () {
            var modalInstance = $modal.open({
                templateUrl: 'changeAvatarDialog.html',
                controller: 'ChangeAvatarDialogController',
                resolve: {
                    profile: function () {
                        return $scope.profile;
                    }
                }
            });
            modalInstance.result.then(function () {

            })
        };
    }])
    .controller('ChangeAvatarDialogController', ['$rootScope', '$scope', '$modalInstance', '$http', 'profile', 'Principal',
        function ($rootScope, $scope, $modalInstance, $http, profile, Principal) {
            $scope.profile = profile;
            $scope.uploadFailed = false;

            $scope.clear = function() {
                $scope.imageCropStep = 1;
                delete $scope.imageCropResult;
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };

            $scope.ok = function () {
                $http({
                    method: 'POST',
                    url: '/api/user/avatar/' + profile.userOID,
                    data: $.param({ image: $scope.imageCropResult}),
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                })
                    .success(function () {
                        $scope.profile.avatarFileType = $scope.imageCropResult.substring(11, $scope.imageCropResult.indexOf(";"));
                        $rootScope.$broadcast('AvatarUpdated', {avatarFileType: $scope.profile.avatarFileType});
                        $modalInstance.close($scope.profile);
                        Principal.identity(true);
                    })
                    .error(function () {
                        $scope.uploadFailed = true;
                    })
            };

        }]);

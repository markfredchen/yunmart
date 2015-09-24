/**
 * Created by markfredchen on 9/21/15.
 */
'use strict';
angular.module('yunmartApp')
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('company', {
            parent: 'account',
            url: '/company',
            data: {
                roles: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/account/company/company.tpl.html',
                    controller: 'CompanyController'
                }
            },
            resolve: {
                user: ['Principal', function (Principal) {
                    return Principal.identity(true);
                }],
                userAccount: ['user','$http', function (user, $http) {
                    console.log(user);
                    var deferred = $q.defer();
                    $http.get('/api/accounts/' + user.accountOID)
                        .success(function (data) {
                            console.log(data);
                            deferred.resolve(data);
                        })
                        .error(function (error) {
                            deferred.reject(error);
                        });
                    return deferred.promise;
                }]
            }
        })
    }])
    .controller('CompanyController', ['$scope', '$http', 'user', 'userAccount', 'PublicAccountName', 'CompanyService',
        function ($scope, $http, user, userAccount, PublicAccountName, CompanyService) {
            $scope.companyName = user.companyName;
            $scope.user = user;
            $scope.company = {};
            $scope.account = userAccount;
            $scope.createCompany = function () {
                if (user.accountName !== PublicAccountName) {
                    CompanyService.createCompany($scope.company, $scope.companyName)
                        .success(function (data) {

                        })
                        .error(function (error, status) {

                        });
                }
            };

        }]);

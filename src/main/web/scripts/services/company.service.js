/**
 * Created by markfredchen on 9/21/15.
 */
'use strict';
angular.module('yunmartApp')
    .factory('CompanyService', ['$http', function ($http) {
        return {
            createCompany: function (company, companyName) {
                return $http({
                    method: 'POST',
                    url: '/api/company/create/' + companyName,
                    data: company
                });
            },
            get: function (companyOID) {
                return $http({
                    method: 'GET',
                    url: '/api/company/get/' + companyOID
                });
            }
        };
    }]);

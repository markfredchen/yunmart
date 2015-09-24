'use strict';

angular.module('yunmartApp')
    .factory('Account', ['$http', '$q', function Account($http, $q) {
        return {
            get: function (accountOID) {
                var deferred = $q.defer();
                $http.get('/api/accounts/' + accountOID)
                    .success(function (data) {
                        deferred.resolve(data);
                    })
                    .error(function (error) {
                        deferred.reject(error);
                    });
                return deferred.promise;
            }
        }
    }]);

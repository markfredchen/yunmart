/**
 * Created by markfredchen on 9/15/15.
 */
'use strict';
angular.module('yunmartApp')
    .factory('ForgetPassword', ['$http', '$q', function ($http, $q) {
        return {
            getForgetPasswordToken: function (usernameOrEmail) {
                var deferred = $q.defer();
                $http.get('/api/forget/password/token' + usernameOrEmail)
                    .success(function (data) {
                        deferred.resolve(data.token);
                    })
                    .error(function (error) {
                        deferred.resolve(error);
                    });
                return deferred.promise;
            },
            resetPassword: function (token) {
                var deferred = $q.defer();
                $http.get('/api/forget/password/' + token)
                    .success(function (data) {
                        deferred.resolve(data);
                    })
                    .error(function (error) {
                        deferred.resolve(error);
                    });
                return deferred.promise;
            }
        }
    }])

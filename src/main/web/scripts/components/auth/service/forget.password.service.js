/**
 * Created by markfredchen on 9/15/15.
 */
'use strict';
angular.module('yunmartApp')
    .factory('ForgetPassword', ['$http', '$q', function ($http, $q) {
        return {
            getForgetPasswordToken: function (usernameOrEmail) {
                return $http({
                    method: 'GET',
                    url: '/api/forget/password/token',
                    params: {usernameOrEmail: usernameOrEmail}
                });
            },
            resetPassword: function (token) {
                return $http.get('/api/forget/password/' + token)
            }
        }
    }])

/**
 * Created by markfredchen on 9/14/15.
 */
'use strict';
angular.module('yunmartApp')
    .factory('User', function ($resource) {
        return $resource('api/users/:userOID', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            }
        });
    });

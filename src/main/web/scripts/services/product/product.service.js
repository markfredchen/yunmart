/**
 * Created by markfredchen on 9/8/15.
 */
'use strict';
angular.module('yunmartApp')
    .factory('Product', function ($resource) {
        return $resource('api/products/:productOID', {}, {
            'query': { method: 'GET'},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });

/**
 * Created by markfredchen on 9/16/15.
 */
'use strict';
angular.module('yunmartApp')
    .factory('ProductComment', ['$http', function ($http) {
        return {
            save: function (comment) {
                return $http.post('/api/product/comments', comment);
            },
            query: function(productOID, page, per_page) {
                return $http({
                    url: '/api/product/comments/' + productOID,
                    method: 'GET',
                    params: {
                        page: page,
                        per_page: per_page
                    }
                });
            }
        };
    }]);

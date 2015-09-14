'use strict';

angular.module('yunmart')
    .factory('Account', function Account($resource) {
        return $resource('api/principal', {}, {
            'get': { method: 'GET', params: {}, isArray: false,
                interceptor: {
                    response: function(response) {
                        // expose response
                        return response;
                    }
                }
            }
        });
    });

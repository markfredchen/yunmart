/**
 * Created by markfredchen on 9/8/15.
 */
'use strict';
angular.module('yunmartApp')
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('product.detail', {
            url: '/product/{productOID}',
            data: {
                roles: []
            },
            views: {
                'productContent@product': {
                    templateUrl: 'scripts/app/product/detail/product.detail.tpl.html',
                    controller: 'ProductDetailController'
                }
            },

            resolve: {
                product: ['$stateParams', 'Product', '$q', function ($stateParams, Product, $q) {
                    var deferred = $q.defer();
                    Product.get({productOID: $stateParams.productOID}, function (result) {
                        deferred.resolve(result);
                    })
                    return deferred.promise;
                }]
            }
        });
    }])
    .controller('ProductDetailController', ['$scope', 'product', function ($scope, product) {
        $scope.product = product;
    }]);

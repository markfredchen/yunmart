/**
 * Created by markfredchen on 9/6/15.
 */
'use strict';

angular.module('yunmartApp')
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('home', {
            parent: 'site',
            url: '/',
            data:{
                roles: []
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/home/home.tpl.html',
                    controller: 'HomeController'
                }
            }
        })
    }])
    .controller('HomeController', ['$scope', function ($scope) {
        $scope.carousel = {
            myInterval: 5000,
            noWrapSlides: false,
            slides: [
                {image: '/images/c1.jpg'},
                {image: '/images/c3.jpg'}
            ]
        };
        $scope.productListTitle = "热门推荐";
        $scope.category = "1";

    }]);

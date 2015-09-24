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
                {image: '/clients/carousel/office365.png'},
                {image: '/clients/carousel/kp.png'}
            ]
        };
        $scope.productListTitle = "热门推荐";
        $scope.category = "1";

    }]);

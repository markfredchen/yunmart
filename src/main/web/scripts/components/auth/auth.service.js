'use strict';

angular.module('yunmartApp')
    .factory('Auth', function Auth($rootScope, $state, $q, Principal, AuthServerProvider, User) {
        return {
            login: function (credentials, callback) {
                var cb = callback || angular.noop;
                var deferred = $q.defer();

                AuthServerProvider.login(credentials).then(function (data) {
                    // retrieve the logged account information
                    Principal.identity(true).then(function(account) {
                        deferred.resolve(data);
                    });
                    return cb();
                }).catch(function (err) {
                    this.logout();
                    deferred.reject(err);
                    return cb(err);
                }.bind(this));

                return deferred.promise;
            },

            logout: function () {
                AuthServerProvider.logout();
                Principal.authenticate(null);
            },

            authorize: function(force) {
                return Principal.identity(force)
                    .then(function() {
                        var isAuthenticated = Principal.isAuthenticated();

                        if ($rootScope.toState.data.roles && $rootScope.toState.data.roles.length > 0 && !Principal.isInAnyRole($rootScope.toState.data.roles)) {
                            if (isAuthenticated) {
                                // user is signed in but not authorized for desired state
                                $state.go('home');
                            }
                            else {
                                // user is not authenticated. stow the state they wanted before you
                                // send them to the signin state, so you can return them when you're done
                                $rootScope.returnToState = $rootScope.toState;
                                $rootScope.returnToStateParams = $rootScope.toStateParams;

                                // now, send them to the signin state so they can log in
                                $state.go('login');
                            }
                        }
                    });
            },

            register: function(user, callback) {
                var cb = callback || angular.noop;

                return User.save(user,
                    function () {
                        return cb(user);
                    },
                    function (err) {
                        this.logout();
                        return cb(err);
                    }.bind(this)).$promise;
            }
        };
    });

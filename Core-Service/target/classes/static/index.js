(function (){
    angular
        .module('storefront', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/product_info/:productId', {
                templateUrl: 'product_info/product_info.html',
                controller: 'productInfoController'
            })
            .when('/admin', {
                templateUrl: 'admin/admin.html',
                controller: 'adminController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/order_confirmation', {
                templateUrl: 'order_confirmation/order_confirmation.html',
                controller: 'orderConfirmationController'
            })
            .when('/registration', {
                templateUrl: 'registration/registration.html',
                controller: 'registrationUserController'
            })
            .when('/profile', {
                templateUrl: 'profile/profile.html',
                controller: 'profileController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        const contextPath = 'http://localhost:8080/shop-core';
        if ($localStorage.webMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webMarketUser.token;
        }

        if (!$localStorage.webMarketGuestCartId) {
            $http.get('http://localhost:8191/shop-cart/api/v1/cart/generate')
                .then(function successCallback(response) {
                    $localStorage.webMarketGuestCartId = response.data.value;
                });
        }
    }
})();

angular.module('storefront').controller('indexController', function ($rootScope, $scope, $http, $localStorage,$location) {
    const contextPath = 'http://localhost:8080/shop-core';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $http.get('http://localhost:8191/shop-cart/api/v1/cart/' + $localStorage.webMarketGuestCartId + '/merge')
                        .then(function successCallback(response) {
                        });
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.navToRegistration = function() {
        $location.path('/registration');
    }

});

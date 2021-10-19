angular.module('storefront').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8080/shop/';

    $scope.showCart = function() {
        $http.get(contextPath + 'api/v1/cart/'+ $localStorage.webMarketGuestCartId)
        .then(function (response) {
            $scope.cart = response.data;

        });
    }

    $scope.incrementItem = function(productId) {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.webMarketGuestCartId + '/add/' + productId)
        .then(function (response) {
            $scope.showCart();
        });
    }

    $scope.decrementItem = function(productId) {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.webMarketGuestCartId + '/decrement/' + productId)
        .then(function (response) {
            $scope.showCart();
        });
    }

    $scope.removeItem = function(productId) {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.webMarketGuestCartId +'/remove/' + productId)
        .then(function (response) {
            $scope.showCart();
        });
    }

    $scope.checkOut = function() {
        $location.path("/order_confirmation");
    }

    $scope.disabledCheckOut = function() {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.showCart();
});

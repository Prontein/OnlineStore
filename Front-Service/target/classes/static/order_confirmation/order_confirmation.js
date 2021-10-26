angular.module('storefront').controller('orderConfirmationController', function ($scope, $http, $location, $localStorage) {


    $scope.showCart = function() {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.webMarketGuestCartId)
        .then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.createOrder = function() {
        $http({
            url: 'http://localhost:5555/core/api/v1/orders',
            method: 'POST',
            data: $scope.orderDetails

        })
        .then(function (response) {
            alert("Ваш заказ успешно сформирован");
            $location.path('/');
        });
    }

    $scope.showCart();
});

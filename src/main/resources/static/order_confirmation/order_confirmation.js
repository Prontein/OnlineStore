angular.module('storefront').controller('orderConfirmationController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8080/shop/';

    $scope.showCart = function() {
        $http.get(contextPath + 'api/v1/cart/0')
        .then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.createOrder = function() {
        $http({
            url: contextPath + 'api/v1/orders',
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

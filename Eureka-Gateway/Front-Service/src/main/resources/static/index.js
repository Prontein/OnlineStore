angular.module('store', []).controller('indexController', function ($scope, $http) {

    $scope.loadProducts = function () {
        $http({
            url: 'http://localhost:5555/product-service/api/v1/products',
            method: 'GET'
        }).then(function (response) {
            $scope.products = response.data;
        });
    }

    $scope.loadProducts();
});
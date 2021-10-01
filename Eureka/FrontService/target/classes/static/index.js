angular.module('products', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:12211';
    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',

        }).then(function (response) {
            $scope.products = response.data;
        });
    };

    $scope.loadProducts();

});
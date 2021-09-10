angular.module('storefront').controller('createProductController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8080/shop/';

    $scope.createProduct = function () {
        if ($scope.new_product == null) {
            alert('Вы не заполнили поля');
            return;
        }
        $http.post(contextPath + 'api/v1/products', $scope.new_product)
            .then(function successCallback (response) {
                $scope.new_product = null;
                alert("Продукт успешно создан");
                $location.path('/store');
            }, function failureCallback (response) {
                alert(response.data.messages);
            });
    };
});

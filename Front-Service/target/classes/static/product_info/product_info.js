angular.module('storefront').controller('productInfoController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:5555/core/';

    $scope.prepareSendComment = function () {
        $http.get(contextPath + 'api/v1/comments/' + $routeParams.productId)
            .then(function successCallback (response) {
                $scope.product_comment = response.data;
                console.log(response);
            }, function failureCallback (response) {
                alert(response.data.messages);
                $location.path('/store');
            });
    }

    $scope.updateProduct = function () {
        $http.post(contextPath + 'api/v1/comments', $scope.product_comment)
            .then(function successCallback (response) {
                $scope.product_comment = null;
                alert("Ваш отзыв был добавлен");
                $location.path('/store');
            }, function failureCallback (response) {
                alert(response.data.messages);
            });
    }

    $scope.prepareSendComment();
});

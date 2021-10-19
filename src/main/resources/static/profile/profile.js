angular.module('storefront').controller('profileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/shop/';

    $scope.loadOrders = function() {
        $http({
            url: contextPath + 'api/v1/orders',
            method: 'GET'
        })
        .then(function (response) {
            $scope.orders = response.data;
            console.log($scope.orders);
        });
    };

    $scope.loadMyProfile = function () {
        $http({
            url: contextPath + 'api/v1/users/me',
            method: 'GET'
        }).then(function (response) {
            $scope.userProfile = response.data;
        });
    };

    $scope.loadOrders();
    $scope.loadMyProfile();
});

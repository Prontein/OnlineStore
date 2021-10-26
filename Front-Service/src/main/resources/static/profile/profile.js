angular.module('storefront').controller('profileController', function ($scope, $http) {
//    const contextPath = 'http://localhost:5555/core/';

    $scope.loadOrders = function() {
        $http({
            url: 'http://localhost:5555/core/api/v1/orders',
            method: 'GET'
        })
        .then(function (response) {
            $scope.orders = response.data;
            console.log($scope.orders);
        });
    };

    $scope.loadMyProfile = function () {
        $http({
            url: 'http://localhost:5555/auth/api/v1/users/me',
            method: 'GET'
        }).then(function (response) {
            $scope.userProfile = response.data;
        });
    };

    $scope.loadOrders();
    $scope.loadMyProfile();
});

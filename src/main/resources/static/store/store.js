angular.module('storefront').controller('storeController', function ($scope, $http, $routeParams, $location, $rootScope, $localStorage) {
    const contextPath = 'http://localhost:8080/shop/';
    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex) {
    currentPageIndex = pageIndex;
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes (1, $scope.productsPage.totalPages);
        });
    }

     $scope.addToCart = function (productId) {
            $http({
                url: contextPath + 'api/v1/cart/' + $localStorage.webMarketGuestCartId + '/add/' + productId,
                method: 'GET',
            }).then(function (response) {

            });
        }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.navToEditProductPage = function(productId) {
        $location.path('edit_product/' + productId);
    }

    $scope.loadProducts(currentPageIndex);

    $scope.navToCart = function(productId) {
        $location.path('cart/' + productId);
    }
});

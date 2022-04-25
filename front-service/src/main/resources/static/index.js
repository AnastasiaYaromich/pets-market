angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    if ($localStorage.petsMarketUser) {
        try {
            let jwt = $localStorage.petsMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.petsMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        if ($localStorage.petsMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.petsMarketUser.token;
        }
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/authenticate', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $scope.loadOrders();
                    $scope.loadCart();
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.petsMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.clearCart();
    };

    $scope.clearUser = function () {
        delete $localStorage.petsMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.petsMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.clearCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/clear_cart')
            .then(function (response) {
            });
    };


    $scope.loadProducts = function () {
        $http.get('http://localhost:5555/core/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.addToCart = function (id) {
        $http.get('http://localhost:5555/cart/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.loadCart();
            });
    };


    $scope.createOrder = function () {
        $http.post('http://localhost:5555/core/api/v1/orders/create')
            .then(function (response) {
                $scope.loadCart();
                 $scope.loadOrders();
            });
    };
//
    $scope.loadOrders = function() {
        $http.get('http://localhost:5555/core/api/v1/orders')
            .then(function (response) {
                 $scope.order = response.data;
            });
    };

    $scope.loadProducts();
    $scope.loadCart();
    $scope.loadOrders();
});
angular.module('market').controller('registerController', function ($scope, $rootScope, $http, $location, $localStorage) {

       $scope.tryToRegister = function () {
           $http.post('http://localhost:5555/auth/register', $scope.userRegister)
               .then(function successCallback(response) {
                    $localStorage.petsMarketRegisterUser = {username: $scope.userRegister.userName, email: $scope.userRegister.email};

                   $scope.userRegister = response.data;

                   $scope.userRegister.userName = null;
                   $scope.userRegister.email = null;
               });
       };

               $scope.isUserRegister = function () {
                   if ($localStorage.petsMarketRegisterUser) {
                       return true;
                   } else {
                       return false;
                   }
               };
});


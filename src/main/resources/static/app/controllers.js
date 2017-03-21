angular.module("myApp")
    .controller("AppController", ['$scope', 'Item', '$state', function ($scope, Item, $state) {
        Item.query(function (response) {
            $scope.items = response ? response : [];
        });

        $scope.checkout = function (product) {
            $state.go('checkout', {product: product});
        };

    }])
    .controller("CheckoutController", ['$scope', '$stateParams','Order', function ($scope, $stateParams, Order) {
        $scope.product = $stateParams.product;
        $scope.cc = {
            cc_number : "4012001037141112",
            cc_cvc : "123",
            cc_exp_month : "12",
            cc_exp_year : "18",
            public_key : "-----BEGIN PUBLIC KEY-----"
            + "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoBttaXwRoI1Fbcond5mS"
            + "7QOb7X2lykY5hvvDeLJelvFhpeLnS4YDwkrnziM3W00UNH1yiSDU+3JhfHu5G387"
            + "O6uN9rIHXvL+TRzkVfa5iIjG+ap2N0/toPzy5ekpgxBicjtyPHEgoU6dRzdszEF4"
            + "ItimGk5ACx/lMOvctncS5j3uWBaTPwyn0hshmtDwClf6dEZgQvm/dNaIkxHKV+9j"
            + "Mn3ZfK/liT8A3xwaVvRzzuxf09xJTXrAd9v5VQbeWGxwFcW05oJulSFjmJA9Hcmb"
            + "DYHJT+sG2mlZDEruCGAzCVubJwGY1aRlcs9AQc1jIm/l8JwH7le2kpk3QoX+gz0w"
            + "WwIDAQAB"
            + "-----END PUBLIC KEY-----"
        };

        $scope.placeAnOrder = function(cc, product, finalPrice){
            var ccObj = new Moip.CreditCard({
                number  : cc.cc_number,
                cvc     : cc.cc_cvc,
                expMonth: cc.cc_exp_month,
                expYear : cc.cc_exp_year,
                pubKey  : cc.public_key
            });

            if(ccObj.isValid()) {
                console.log(ccObj.hash());
                var order = {
                    ccHash : ccObj.hash(),
                    product :product,
                    finalPrice : finalPrice
                };

                new Order(order).$save(function(order) {
                    console.log(order);
                });
            }
        };
    }]);


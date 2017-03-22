angular.module("myApp")
    .controller("AppController", ['$scope', 'Item', '$state', function ($scope, Item, $state) {
        Item.query(function (response) {
            $scope.items = response ? response : [];
        });

        $scope.checkout = function (product) {
            $state.go('checkout', {product: product});
        };

    }])
    .controller("CheckoutController", ['$scope','$state', '$stateParams', 'Order', function ($scope, $state, $stateParams, Order) {
        $scope.product = $stateParams.product;
        $scope.cartProd = {
            finalPrice: $scope.product.price,
            diffPrice: {
                cupom: 0,
                inst: 0
            }
        };
        $scope.buyBtnDisabled = false;
        $scope.buyBtn = "Compre Agora";
        $scope.cc = {
            cc_number: "4012001037141112",
            cc_cvc: "123",
            cc_exp_month: "12",
            cc_exp_year: "18",
            cc_installments: "1",
            public_key: "-----BEGIN PUBLIC KEY-----"
            + "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoBttaXwRoI1Fbcond5mS"
            + "7QOb7X2lykY5hvvDeLJelvFhpeLnS4YDwkrnziM3W00UNH1yiSDU+3JhfHu5G387"
            + "O6uN9rIHXvL+TRzkVfa5iIjG+ap2N0/toPzy5ekpgxBicjtyPHEgoU6dRzdszEF4"
            + "ItimGk5ACx/lMOvctncS5j3uWBaTPwyn0hshmtDwClf6dEZgQvm/dNaIkxHKV+9j"
            + "Mn3ZfK/liT8A3xwaVvRzzuxf09xJTXrAd9v5VQbeWGxwFcW05oJulSFjmJA9Hcmb"
            + "DYHJT+sG2mlZDEruCGAzCVubJwGY1aRlcs9AQc1jIm/l8JwH7le2kpk3QoX+gz0w"
            + "WwIDAQAB"
            + "-----END PUBLIC KEY-----"
        };

        $scope.placeAnOrder = function (cc, product, finalPrice) {
            var ccObj = new Moip.CreditCard({
                number: cc.cc_number,
                cvc: cc.cc_cvc,
                expMonth: cc.cc_exp_month,
                expYear: cc.cc_exp_year,
                pubKey: cc.public_key
            });

            if (!ccObj.isValid()) {
                alert("cartão inválido");
                return;
            }

            $scope.buyBtnDisabled = true;
            $scope.buyBtn = "Aguarde...";

            new Order({
                ccHash: ccObj.hash(),
                product: product,
                finalPrice: $scope.cartProd.finalPrice,
                installments: cc.cc_installments
            }).$save(function (order) {
                console.log(order);
                $state.go("receipt");
            }, function (err) {
                alert("algo deu errado, tente novamente.")
            });
        };

        $scope.addCupom = function (cupom) {
            if (cupom.length > 0 && !$scope.cartProd.diffPrice.cupom) {
                $scope.cartProd.diffPrice.cupom = $scope.product.price * -0.05;
                calcFinalPrice();
                alert("cupom adicionado com sucesso");
            }
        };

        $scope.addInst = function (installments) {
            $scope.cartProd.diffPrice.inst = $scope.product.price * 0.025;
            if (installments == 1) {
                $scope.cartProd.diffPrice.inst = 0;
            }
            calcFinalPrice();
        };

        var calcFinalPrice = function () {
            $scope.cartProd.finalPrice = $scope.product.price +
                $scope.cartProd.diffPrice.cupom +
                $scope.cartProd.diffPrice.inst;
        };
    }])
    .controller("ReceiptController", ['$scope', function ($scope) {


    }]);


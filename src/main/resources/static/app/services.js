angular.module("myApp")
    .factory("Item", ['$resource', function ($resource) {
        return $resource('/products/:id', {
            id: '@id'
        }, {
            update: {
                method: "PUT"
            },
            remove: {
                method: "DELETE"
            }
        });
    }]).factory("Order", ['$resource', function ($resource) {
    return $resource('/orders/:id', {
        id: '@id'
    }, {
        update: {
            method: "PUT"
        },
        remove: {
            method: "DELETE"
        }
    });
}]);
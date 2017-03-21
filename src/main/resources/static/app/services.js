angular.module("myApp").factory("Item", ['$resource', function ($resource) {
    return $resource('/items/:id', {
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
(function() {
    'use strict';
    angular
        .module('acaipdejhipsterApp')
        .factory('Celular', Celular);

    Celular.$inject = ['$resource'];

    function Celular ($resource) {
        var resourceUrl =  'api/celulars/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

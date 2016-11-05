(function() {
    'use strict';
    angular
        .module('acaipdejhipsterApp')
        .factory('Estabelecimento', Estabelecimento);

    Estabelecimento.$inject = ['$resource', 'DateUtils'];

    function Estabelecimento ($resource, DateUtils) {
        var resourceUrl =  'api/estabelecimentos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataAtualizacaoPreco = DateUtils.convertDateTimeFromServer(data.dataAtualizacaoPreco);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

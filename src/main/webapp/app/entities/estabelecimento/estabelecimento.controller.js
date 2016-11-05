(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .controller('EstabelecimentoController', EstabelecimentoController);

    EstabelecimentoController.$inject = ['$scope', '$state', 'Estabelecimento', 'ParseLinks', 'AlertService'];

    function EstabelecimentoController ($scope, $state, Estabelecimento, ParseLinks, AlertService) {
        var vm = this;
        
        vm.estabelecimentos = [];
        vm.loadPage = loadPage;
        vm.page = 0;
        vm.links = {
            last: 0
        };
        vm.predicate = 'id';
        vm.reset = reset;
        vm.reverse = true;

        loadAll();

        function loadAll () {
            Estabelecimento.query({
                page: vm.page,
                size: 20,
                sort: sort()
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }

            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                for (var i = 0; i < data.length; i++) {
                    vm.estabelecimentos.push(data[i]);
                }
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function reset () {
            vm.page = 0;
            vm.estabelecimentos = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }
    }
})();

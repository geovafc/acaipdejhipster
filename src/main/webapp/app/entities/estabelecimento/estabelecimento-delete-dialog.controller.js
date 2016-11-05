(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .controller('EstabelecimentoDeleteController',EstabelecimentoDeleteController);

    EstabelecimentoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Estabelecimento'];

    function EstabelecimentoDeleteController($uibModalInstance, entity, Estabelecimento) {
        var vm = this;

        vm.estabelecimento = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Estabelecimento.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

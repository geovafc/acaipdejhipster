(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .controller('HorarioFuncionamentoDeleteController',HorarioFuncionamentoDeleteController);

    HorarioFuncionamentoDeleteController.$inject = ['$uibModalInstance', 'entity', 'HorarioFuncionamento'];

    function HorarioFuncionamentoDeleteController($uibModalInstance, entity, HorarioFuncionamento) {
        var vm = this;

        vm.horarioFuncionamento = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            HorarioFuncionamento.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

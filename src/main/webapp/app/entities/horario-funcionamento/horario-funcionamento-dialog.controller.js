(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .controller('HorarioFuncionamentoDialogController', HorarioFuncionamentoDialogController);

    HorarioFuncionamentoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'HorarioFuncionamento', 'Estabelecimento'];

    function HorarioFuncionamentoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, HorarioFuncionamento, Estabelecimento) {
        var vm = this;

        vm.horarioFuncionamento = entity;
        vm.clear = clear;
        vm.save = save;
        vm.estabelecimentos = Estabelecimento.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.horarioFuncionamento.id !== null) {
                HorarioFuncionamento.update(vm.horarioFuncionamento, onSaveSuccess, onSaveError);
            } else {
                HorarioFuncionamento.save(vm.horarioFuncionamento, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('acaipdejhipsterApp:horarioFuncionamentoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

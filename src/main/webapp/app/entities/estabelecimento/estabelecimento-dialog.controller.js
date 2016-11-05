(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .controller('EstabelecimentoDialogController', EstabelecimentoDialogController);

    EstabelecimentoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Estabelecimento', 'Produto', 'Celular', 'HorarioFuncionamento'];

    function EstabelecimentoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Estabelecimento, Produto, Celular, HorarioFuncionamento) {
        var vm = this;

        vm.estabelecimento = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.produtos = Produto.query();
        vm.celulars = Celular.query();
        vm.horariofuncionamentos = HorarioFuncionamento.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.estabelecimento.id !== null) {
                Estabelecimento.update(vm.estabelecimento, onSaveSuccess, onSaveError);
            } else {
                Estabelecimento.save(vm.estabelecimento, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('acaipdejhipsterApp:estabelecimentoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataAtualizacaoPreco = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();

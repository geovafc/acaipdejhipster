(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .controller('CelularDialogController', CelularDialogController);

    CelularDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Celular', 'Estabelecimento'];

    function CelularDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Celular, Estabelecimento) {
        var vm = this;

        vm.celular = entity;
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
            if (vm.celular.id !== null) {
                Celular.update(vm.celular, onSaveSuccess, onSaveError);
            } else {
                Celular.save(vm.celular, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('acaipdejhipsterApp:celularUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

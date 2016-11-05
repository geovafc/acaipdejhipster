(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .controller('ProdutoDialogController', ProdutoDialogController);

    ProdutoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Produto', 'Estabelecimento'];

    function ProdutoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Produto, Estabelecimento) {
        var vm = this;

        vm.produto = entity;
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
            if (vm.produto.id !== null) {
                Produto.update(vm.produto, onSaveSuccess, onSaveError);
            } else {
                Produto.save(vm.produto, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('acaipdejhipsterApp:produtoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

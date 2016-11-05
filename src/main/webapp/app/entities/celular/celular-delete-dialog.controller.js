(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .controller('CelularDeleteController',CelularDeleteController);

    CelularDeleteController.$inject = ['$uibModalInstance', 'entity', 'Celular'];

    function CelularDeleteController($uibModalInstance, entity, Celular) {
        var vm = this;

        vm.celular = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Celular.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

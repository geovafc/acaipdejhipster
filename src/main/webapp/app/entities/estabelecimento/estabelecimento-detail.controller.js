(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .controller('EstabelecimentoDetailController', EstabelecimentoDetailController);

    EstabelecimentoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Estabelecimento', 'Produto', 'Celular', 'HorarioFuncionamento'];

    function EstabelecimentoDetailController($scope, $rootScope, $stateParams, previousState, entity, Estabelecimento, Produto, Celular, HorarioFuncionamento) {
        var vm = this;

        vm.estabelecimento = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('acaipdejhipsterApp:estabelecimentoUpdate', function(event, result) {
            vm.estabelecimento = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

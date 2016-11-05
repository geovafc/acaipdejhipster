(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .controller('HorarioFuncionamentoDetailController', HorarioFuncionamentoDetailController);

    HorarioFuncionamentoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'HorarioFuncionamento', 'Estabelecimento'];

    function HorarioFuncionamentoDetailController($scope, $rootScope, $stateParams, previousState, entity, HorarioFuncionamento, Estabelecimento) {
        var vm = this;

        vm.horarioFuncionamento = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('acaipdejhipsterApp:horarioFuncionamentoUpdate', function(event, result) {
            vm.horarioFuncionamento = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

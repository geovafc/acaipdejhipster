(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .controller('CelularDetailController', CelularDetailController);

    CelularDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Celular', 'Estabelecimento'];

    function CelularDetailController($scope, $rootScope, $stateParams, previousState, entity, Celular, Estabelecimento) {
        var vm = this;

        vm.celular = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('acaipdejhipsterApp:celularUpdate', function(event, result) {
            vm.celular = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

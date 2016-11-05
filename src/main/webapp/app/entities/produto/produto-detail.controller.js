(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .controller('ProdutoDetailController', ProdutoDetailController);

    ProdutoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Produto', 'Estabelecimento'];

    function ProdutoDetailController($scope, $rootScope, $stateParams, previousState, entity, Produto, Estabelecimento) {
        var vm = this;

        vm.produto = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('acaipdejhipsterApp:produtoUpdate', function(event, result) {
            vm.produto = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

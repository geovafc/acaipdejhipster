'use strict';

describe('Controller Tests', function() {

    describe('Estabelecimento Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockEstabelecimento, MockProduto, MockCelular, MockHorarioFuncionamento;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockEstabelecimento = jasmine.createSpy('MockEstabelecimento');
            MockProduto = jasmine.createSpy('MockProduto');
            MockCelular = jasmine.createSpy('MockCelular');
            MockHorarioFuncionamento = jasmine.createSpy('MockHorarioFuncionamento');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Estabelecimento': MockEstabelecimento,
                'Produto': MockProduto,
                'Celular': MockCelular,
                'HorarioFuncionamento': MockHorarioFuncionamento
            };
            createController = function() {
                $injector.get('$controller')("EstabelecimentoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'acaipdejhipsterApp:estabelecimentoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});

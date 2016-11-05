'use strict';

describe('Controller Tests', function() {

    describe('HorarioFuncionamento Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockHorarioFuncionamento, MockEstabelecimento;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockHorarioFuncionamento = jasmine.createSpy('MockHorarioFuncionamento');
            MockEstabelecimento = jasmine.createSpy('MockEstabelecimento');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'HorarioFuncionamento': MockHorarioFuncionamento,
                'Estabelecimento': MockEstabelecimento
            };
            createController = function() {
                $injector.get('$controller')("HorarioFuncionamentoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'acaipdejhipsterApp:horarioFuncionamentoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});

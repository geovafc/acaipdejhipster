(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('horario-funcionamento', {
            parent: 'entity',
            url: '/horario-funcionamento',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'acaipdejhipsterApp.horarioFuncionamento.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/horario-funcionamento/horario-funcionamentos.html',
                    controller: 'HorarioFuncionamentoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('horarioFuncionamento');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('horario-funcionamento-detail', {
            parent: 'entity',
            url: '/horario-funcionamento/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'acaipdejhipsterApp.horarioFuncionamento.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/horario-funcionamento/horario-funcionamento-detail.html',
                    controller: 'HorarioFuncionamentoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('horarioFuncionamento');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'HorarioFuncionamento', function($stateParams, HorarioFuncionamento) {
                    return HorarioFuncionamento.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'horario-funcionamento',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('horario-funcionamento-detail.edit', {
            parent: 'horario-funcionamento-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/horario-funcionamento/horario-funcionamento-dialog.html',
                    controller: 'HorarioFuncionamentoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['HorarioFuncionamento', function(HorarioFuncionamento) {
                            return HorarioFuncionamento.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('horario-funcionamento.new', {
            parent: 'horario-funcionamento',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/horario-funcionamento/horario-funcionamento-dialog.html',
                    controller: 'HorarioFuncionamentoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                diaInicio: null,
                                diaFim: null,
                                horarioInicio: null,
                                horarioFim: null,
                                delivery: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('horario-funcionamento', null, { reload: 'horario-funcionamento' });
                }, function() {
                    $state.go('horario-funcionamento');
                });
            }]
        })
        .state('horario-funcionamento.edit', {
            parent: 'horario-funcionamento',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/horario-funcionamento/horario-funcionamento-dialog.html',
                    controller: 'HorarioFuncionamentoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['HorarioFuncionamento', function(HorarioFuncionamento) {
                            return HorarioFuncionamento.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('horario-funcionamento', null, { reload: 'horario-funcionamento' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('horario-funcionamento.delete', {
            parent: 'horario-funcionamento',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/horario-funcionamento/horario-funcionamento-delete-dialog.html',
                    controller: 'HorarioFuncionamentoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['HorarioFuncionamento', function(HorarioFuncionamento) {
                            return HorarioFuncionamento.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('horario-funcionamento', null, { reload: 'horario-funcionamento' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

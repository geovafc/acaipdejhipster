(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('estabelecimento', {
            parent: 'entity',
            url: '/estabelecimento',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'acaipdejhipsterApp.estabelecimento.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/estabelecimento/estabelecimentos.html',
                    controller: 'EstabelecimentoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('estabelecimento');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('estabelecimento-detail', {
            parent: 'entity',
            url: '/estabelecimento/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'acaipdejhipsterApp.estabelecimento.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/estabelecimento/estabelecimento-detail.html',
                    controller: 'EstabelecimentoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('estabelecimento');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Estabelecimento', function($stateParams, Estabelecimento) {
                    return Estabelecimento.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'estabelecimento',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('estabelecimento-detail.edit', {
            parent: 'estabelecimento-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/estabelecimento/estabelecimento-dialog.html',
                    controller: 'EstabelecimentoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Estabelecimento', function(Estabelecimento) {
                            return Estabelecimento.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('estabelecimento.new', {
            parent: 'estabelecimento',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/estabelecimento/estabelecimento-dialog.html',
                    controller: 'EstabelecimentoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nome: null,
                                responsavel: null,
                                email: null,
                                telefone: null,
                                cep: null,
                                logradouro: null,
                                numero: null,
                                bairro: null,
                                cidade: null,
                                uf: null,
                                descricao: null,
                                latitude: null,
                                longitude: null,
                                uRLimagem: null,
                                status: null,
                                dataAtualizacaoPreco: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('estabelecimento', null, { reload: 'estabelecimento' });
                }, function() {
                    $state.go('estabelecimento');
                });
            }]
        })
        .state('estabelecimento.edit', {
            parent: 'estabelecimento',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/estabelecimento/estabelecimento-dialog.html',
                    controller: 'EstabelecimentoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Estabelecimento', function(Estabelecimento) {
                            return Estabelecimento.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('estabelecimento', null, { reload: 'estabelecimento' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('estabelecimento.delete', {
            parent: 'estabelecimento',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/estabelecimento/estabelecimento-delete-dialog.html',
                    controller: 'EstabelecimentoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Estabelecimento', function(Estabelecimento) {
                            return Estabelecimento.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('estabelecimento', null, { reload: 'estabelecimento' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

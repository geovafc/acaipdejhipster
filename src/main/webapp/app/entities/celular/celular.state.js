(function() {
    'use strict';

    angular
        .module('acaipdejhipsterApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('celular', {
            parent: 'entity',
            url: '/celular',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'acaipdejhipsterApp.celular.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/celular/celulars.html',
                    controller: 'CelularController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('celular');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('celular-detail', {
            parent: 'entity',
            url: '/celular/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'acaipdejhipsterApp.celular.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/celular/celular-detail.html',
                    controller: 'CelularDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('celular');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Celular', function($stateParams, Celular) {
                    return Celular.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'celular',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('celular-detail.edit', {
            parent: 'celular-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/celular/celular-dialog.html',
                    controller: 'CelularDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Celular', function(Celular) {
                            return Celular.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('celular.new', {
            parent: 'celular',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/celular/celular-dialog.html',
                    controller: 'CelularDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                numero: null,
                                operadora: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('celular', null, { reload: 'celular' });
                }, function() {
                    $state.go('celular');
                });
            }]
        })
        .state('celular.edit', {
            parent: 'celular',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/celular/celular-dialog.html',
                    controller: 'CelularDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Celular', function(Celular) {
                            return Celular.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('celular', null, { reload: 'celular' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('celular.delete', {
            parent: 'celular',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/celular/celular-delete-dialog.html',
                    controller: 'CelularDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Celular', function(Celular) {
                            return Celular.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('celular', null, { reload: 'celular' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

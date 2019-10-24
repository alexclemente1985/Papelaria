/**
 * 
 */
var app = angular.module("papelariaManager",['ui.router','ngStorage']);

app.constant('urls', {
    BASE: '<a class="vglnk" href="http://localhost:8080/papelaria" rel="nofollow"><span>http</span><span>://</span><span>localhost</span><span>:</span><span>8080</span><span>/</span><span>papelaria</span></a>',
    
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
 
        $stateProvider
            .state('home', {
                url: '/papelaria',
                templateUrl: 'partials/list',
                controller:'PapelariaController',
                controllerAs:'ctrl',
                resolve: {
                    produtos: function ($q, PapelariaService) {
                        console.log('Carregando os produtos');
                        var deferred = $q.defer();
                        PapelariaService.listarProdutos().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/papelaria');
    }]);
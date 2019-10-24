
'use strict';
 
angular.module('papelariaManager').factory('PapelariaService',
    ['$localStorage', '$http', '$q', 'url',
        function ($localStorage, $http, $q, url) {
 
            var factory = {
                listarProdutos: listarProdutos,
                recuperarProdutos: recuperarProdutos,
                cadastrarProduto: cadastrarProduto,
                atualizarProduto: atualizarProduto,
                exibirProduto: exibirProduto,
                removerProduto: removerProduto,
                listarCategorias: listarCategorias
            };
 
            return factory;
 
            function listarProdutos() {
                console.log('Listando os produtos');
                var deferred = $q.defer();
                $http.get(url+'/listarprodutos')
                    .then(
                        function (response) {
                            console.log('Produtos listados com sucesso!');
                            $localStorage.produtos = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Erro durante a listagem dos produtos.');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function recuperarProdutos(){
                return $localStorage.produtos;
            }
 
            function exibirProduto(id) {
                console.log('Exibindo produto de código de barras:'+id);
                var deferred = $q.defer();
                $http.get(url+'/exibirproduto/'+id)
                    .then(
                        function (response) {
                            console.log('Produto de código de barras:'+id+' encontrado com sucesso!');
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro enquanto ocorria carregamento de produto:'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function cadastrarProduto(produto) {
                console.log('Cadastrando novo produto:');
                var deferred = $q.defer();
                $http.post(urls.BASE+'/cadastrarproduto', produto)
                    .then(
                        function (response) {
                        	listarProdutos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Erro durante o cadastro do produto '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function atualizarProduto(produto,id) {
                console.log('Atualizando produto '+id);
                var deferred = $q.defer();
                $http.put(urls.BASE+'/atualizarproduto/', produto)
                    .then(
                        function (response) {
                        	listarProdutos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro durante a atualização do produto '+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function removerProduto(id) {
                console.log('Remoção do produto '+id);
                var deferred = $q.defer();
                $http.delete(urls.BASE+'/removerproduto/' + id)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro durante a remoção do produto '+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function listarCategorias() {
                console.log('Listando as categorias');
                var deferred = $q.defer();
                $http.get('/categorias/listarcategorias')
                    .then(
                        function (response) {
                            console.log('Categorias listadas com sucesso!');
                            $localStorage.produtos = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Erro durante a listagem das categorias.');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
        }
    ]);
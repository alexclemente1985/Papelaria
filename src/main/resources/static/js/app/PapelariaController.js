'use strict';
angular.module('papelariaManager').controller('PapelariaController', 
		['PapelariaService', '$scope',  function( PapelariaService, $scope) {


        var self = this;
        self.produto = {};
        self.produtos=[];
 
        self.submit = submit;
        self.recuperarProdutos = recuperarProdutos;
        self.cadastrarProduto = cadastrarProduto;
        self.atualizarProduto = atualizarProduto;
        self.exibirProduto = exibirProduto;
        self.removerProduto = removerProduto;
        self.listarCategorias = listarCategorias;
        self.reset = reset;
 
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
 
        self.onlyIntegers = /^\d+$/;
       // self.onlyNumbers = /^\d+([,.]\d+)?$/;
 
        function submit() {
            console.log('Enviando...');
            if (self.produto.barcode === undefined || self.produto.barcode === null) {
                console.log('Iniciando registro do novo produto', self.produto);
                cadastrarProduto(self.produto);
            } else {
            	atualizarProduto(self.produto, self.produto.barcode);
                console.log('Produto atualizado:', self.produto.barcode);
            }
        }
 
        function cadastrarProduto(produto) {
            console.log('Cadastro de produto');
            PapelariaService.cadastrarProduto(produto)
                .then(
                    function (response) {
                        console.log('Cadastro efetuado com sucesso');
                        self.successMessage = 'Cadastro efetuado com sucesso';
                        self.errorMessage='';
                        self.done = true;
                        self.produto={};
                        $scope.produtoForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Erro na criação do registro do produto');
                        self.errorMessage = 'Erro na criação do registro do produto: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
 
 
        function atualizarProduto(produto, produto.barcode){
            console.log('Atualização de produto');
            PapelariaService.atualizarProduto(produto, produto.barcode)
                .then(
                    function (response){
                        console.log('Atualização efetuada com sucesso');
                        self.successMessage='Atualização efetuada com sucesso';
                        self.errorMessage='';
                        self.done = true;
                        $scope.produtoForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Erro durante a atualização do produto');
                        self.errorMessage='Erro durante a atualização do produto '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }
 
 
        function removerProduto(produto.barcode){
            console.log('Remoção do produto '+produto.barcode);
            PapelariaService.removerProduto(produto.barcode)
                .then(
                    function(){
                        console.log('Produto '+produto.barcode + ' removido com sucesso');
                    },
                    function(errResponse){
                        console.error('Erro na remoção do produto '+produto.barcode +', Erro :'+errResponse.data);
                    }
                );
        }
        
        function exibirProduto(produto.barcode){
            console.log('Produto '+produto.barcode);
            PapelariaService.exibirProduto(produto.barcode)
                .then(
                    function(){
                        console.log('Produto '+produto.barcode + ' removido com sucesso');
                    },
                    function(errResponse){
                        console.error('Erro na remoção do produto '+produto.barcode +', Erro :'+errResponse.data);
                    }
                );
        }
        
 
 
        function recuperarProdutos(){
            return PapelariaService.recuperarProdutos();
        }
 
        function atualizarProduto(produto, produto.barcode) {
            self.successMessage='';
            self.errorMessage='';
            PapelariaService.exibirProduto(produto.barcode).then(
                function (produto) {
                    self.produto = produto;
                },
                function (errResponse) {
                    console.error('Erro na atualização do produto ' + produto.barcode + ', Erro :' + errResponse.data);
                }
            );
        }
        
        function listarCategorias(){
        	console.log('Listando categorias');
            PapelariaService.listarCategorias()
                .then(
                    function(){
                        console.log('Categorias listadas com sucesso');
                    },
                    function(errResponse){
                        console.error('Erro na listagem das categorias: '+errResponse.data);
                    }
                );
        }
        
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.user={};
            $scope.produtoForm.$setPristine(); //reset Form
        }
    }
    ]);
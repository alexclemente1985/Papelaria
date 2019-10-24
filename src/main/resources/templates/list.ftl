
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Produto </span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="produtoForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.produto.barcode" />
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="barcode">Código de barras</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.produto.barcode" id="barcode" class="barcode form-control input-sm" placeholder="Informe o código de barras" required ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="pname">Nome</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.produto.nome" id="pname" class="prodname form-control input-sm" placeholder="Informe o nome do produto" required ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="pdescricao">Descrição</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.produto.descricao" id="pdescricao" class="proddesc form-control input-sm" placeholder="Informe a descrição do produto" required ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
                    

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="pqtd">Quantidade</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.produto.quantidade" id="pqtd" class="form-control input-sm" placeholder="Informe a quantidade do produto" required ng-pattern="ctrl.onlyIntegers"/>
                            </div>
                        </div>
                    </div>
     
     
     
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="catdesc">Categoria</label>
                            <div class="col-md-7">
                                <select ng-model="ctrl.produto.categoria.descricao" id="catdesc" class="form-control select-sm" required ng-options="x for x in catdesc"/>
                                	<input type="hidden" ng-model="ctrl.produto.categoria.id" />
                                </select>
                            </div>
                        </div>
                    </div>
 
                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!ctrl.produto.barcode ? 'Cadastrar' : 'Atualizar'}}" class="btn btn-primary btn-sm" ng-disabled="produtoForm.$invalid || produtoForm.$pristine">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="produtoForm.$pristine">Limpar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>    
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Lista de produtos</span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Código de barras</th>
                        <th>Nome</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="p in ctrl.listarProdutos()">
                        <td>{{p.barcode}}</td>
                        <td>{{p.nome}}</td>
                        <td><button type="button" ng-click="ctrl.exibirProduto(p.barcode)" class="btn btn-success custom-width">Detalhes</button></td>
                        <td><button type="button" ng-click="ctrl.atualizarProduto(p,p.barcode)" class="btn btn-success custom-width">Editar</button></td>
                        <td><button type="button" ng-click="ctrl.removerProduto(p.barcode)" class="btn btn-danger custom-width">Remover</button></td>
                    </tr>
                    </tbody>
                </table>      
            </div>
        </div>
    </div>
</div>
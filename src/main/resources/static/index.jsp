<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<link href="/css/app.css" rel="stylesheet"/>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.7.8/angular-route.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<meta charset="ISO-8859-1">
<title>Papelaria</title>
</head>
<body ng-app="papelariaManager">
	<div class="App" ng-controller="PapelariaController">
	<br/>
	<h4 class="papelaria">Gerenciador Papelaria</h4>
	
	<form name="papelariaForm" novalidate ng-submit="submitForm()">
		<table>
			<tr>
				<th colspan="2">Cadastrar Produtos</th>
			</tr>
			<tr>
				<td>Código de barras</td>
				<td><input type="number" name="barcode" required ng-model="produto.barcode"></td>
				<td ng-show="papelariaForm.barcode.$invalid && papelariaForm.barcode.$dirty">Campo obrigatório!</td>
			</tr>
			<tr>
				<td>Nome do produto</td>
				<td><input type="text" name="nome" required ng-model="produto.nome"></td>
				<td ng-show="papelariaForm.nome.$invalid && papelariaForm.nome.$dirty">Campo obrigatório!</td>
			</tr>
			<tr>
				<td>Descrição do produto</td>
				<td><input type="text" name="descricao" required ng-model="produto.descricao"></td>
				<td ng-show="papelariaForm.descricao.$invalid && papelariaForm.descricao.$dirty">Campo obrigatório!</td>
			</tr>
			<tr>
				<td>Quantidade</td>
				<td><input type="number" name="quantidade" required ng-model="produto.quantidade"></td>
				<td ng-show="papelariaForm.quantidade.$invalid && papelariaForm.quantidade.$dirty">Campo obrigatório!</td>
			</tr>
			<tr>
				<td>Categoria do produto</td>
				<td>
					<select id="descCategoria" required ng-model="papelariaForm.descCategoria" ng-options="x for x in descCategoria">
					</select>
				</td>
				<td ng-show="papelariaForm.descCategoria.$invalid && papelariaForm.descCategoria.$dirty">Campo obrigatório!</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Cadastrar Produto" class="blue-button">
				</td>
			</tr>
		</table>
	</form>
	
	<table>
		<tr ng-repeat="produto in produtos">
			<td>{{produto.barcode}}</td>
			<td>{{produto.nome}}</td>
			<td>{{produto.descricao}}</td>
			<td>{{produto.quantidade}}</td>
			<td>{{produto.descCategoria}}</td>
			<td>
				<a ng-click="atualizarProduto(produto)" class="blue-button">Editar</a>
				<a ng-click="removerProduto(produto)" class="red-button">Apagar</a>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>
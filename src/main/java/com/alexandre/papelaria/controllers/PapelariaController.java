package com.alexandre.papelaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.alexandre.papelaria.exceptions.RepeatedBarcodeException;
import com.alexandre.papelaria.models.Categoria;
import com.alexandre.papelaria.models.Produto;
import com.alexandre.papelaria.services.CategoriaService;
import com.alexandre.papelaria.services.ProdutoService;

@RestController
public class PapelariaController {
	@Autowired
	ProdutoService prodService;

	@Autowired
	CategoriaService catService;

	@RequestMapping(value = "/produtos", method = RequestMethod.GET)
	public String getHomePage() {
		
		catService.carregarCategoria();
		
		return "index";
	}

	@RequestMapping(value = "/produtos/listarprodutos", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Produto>> listarProdutos() {
		List<Produto> produtos = prodService.listarProdutos();
		return new ResponseEntity<List<Produto>>(produtos,HttpStatus.OK);
	}

	@RequestMapping(value = "/produtos/cadastrarprodutos", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> cadastrarProduto(@RequestBody Produto p) throws RestClientException, RepeatedBarcodeException{
		catService.carregarCategoria();
		
		Produto produto = prodService.cadastrarProduto(p);
		
		return ResponseEntity.ok().body(produto);
	}

	@RequestMapping(value = "/produtos/atualizarproduto", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<?> atualizarProduto(@RequestBody Produto p) {
		prodService.atualizarProduto(p);
		return new ResponseEntity<Produto>(p,HttpStatus.OK);
	}

	@RequestMapping(value = "/produtos/exibirproduto/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> exibirProduto(@PathVariable Integer id) {
		Produto p = prodService.exibirProduto(id);
		return new ResponseEntity<Produto>(p,HttpStatus.OK);
	}

	@RequestMapping(value = "/produtos/removerproduto/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> removerProduto(@PathVariable Integer id) {
		prodService.removerProduto(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/categorias/listarcategorias", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Categoria>> listarCategorias() {
		List<Categoria> categorias = catService.listarCategorias();
		if(categorias.size()==0) {
			return new ResponseEntity<List<Categoria>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Categoria>>(categorias,HttpStatus.OK);
	}

}

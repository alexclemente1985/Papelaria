package com.alexandre.papelaria.services;

import java.util.List;

import com.alexandre.papelaria.models.Produto;

public interface ProdutoService {
	public List<Produto> listarProdutos();

	public Produto exibirProduto(Integer id);

	public Produto cadastrarProduto(Produto p);

	public Produto atualizarProduto(Produto p);

	public void removerProduto(Integer id);

}	

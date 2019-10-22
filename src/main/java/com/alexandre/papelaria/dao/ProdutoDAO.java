package com.alexandre.papelaria.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alexandre.papelaria.models.Produto;

@Repository
public interface ProdutoDAO {
	public List<Produto> listarProdutos();

	public void cadastrarProduto(Produto p);

	public void removerProduto(Integer id);

	public void atualizarProduto(Produto p);

	public Produto exibirProduto(Integer id);
}

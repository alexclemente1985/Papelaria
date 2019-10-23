package com.alexandre.papelaria.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.alexandre.papelaria.dao.CategoriaDAO;
import com.alexandre.papelaria.dao.ProdutoDAO;
import com.alexandre.papelaria.exceptions.CategoryNotFoundException;
import com.alexandre.papelaria.exceptions.EmptyDatabase;
import com.alexandre.papelaria.exceptions.NullBarcodeException;
import com.alexandre.papelaria.exceptions.ProductNotFoundException;
import com.alexandre.papelaria.exceptions.RepeatedBarcodeException;
import com.alexandre.papelaria.models.Produto;
import com.alexandre.papelaria.services.ProdutoService;
import com.alexandre.papelaria.utils.Message;

@Service("ProdutoService")
public class ProdutoServiceImpl implements ProdutoService {

	/*
	 * private ProdutoDAO dao;
	 * 
	 * ProdutoServiceImpl(ProdutoDAO produtoDAO) { this.dao = produtoDAO; }
	 */

	@Autowired
	private ProdutoDAO dao;

	@Autowired
	private CategoriaDAO catDAO;

	@Autowired
	private Message message;

	@Override
	@Transactional
	public List<Produto> listarProdutos() {

		List<Produto> prodList = new ArrayList<Produto>();
		dao.listarProdutos().forEach(e -> prodList.add(e));

		if (prodList.size() == 0) {
			String errMsg = message.emptyDatabase();

			throw new EmptyDatabase(errMsg);
		}

		return prodList;
	}

	@Override
	@Transactional
	public Produto exibirProduto(Integer id) {
		if (dao.exibirProduto(id).equals(null)) {

			String errMsg = message.productNotFound(id);

			throw new ProductNotFoundException(errMsg);
		}

		Produto prod = dao.exibirProduto(id);

		return prod;
	}

	@Override
	@Transactional
	public synchronized Produto cadastrarProduto(Produto p) throws RestClientException {

		if (p.getBarcode() == null) {
			String errMsg = message.nullBarcode();

			throw new NullBarcodeException(errMsg);
		} else if (dao.exibirProduto(p.getBarcode()) != null) {

			String errMsg = message.repeatedBarcode(p.getBarcode(), p.getNome());

			throw new RepeatedBarcodeException(errMsg);
		} else if (catDAO.encontrarCategoria(p.getCategoria().getId()) == null) {
			String errMsg = message.categoryNotFound(p.getCategoria().getId());

			throw new CategoryNotFoundException(errMsg);
		}

		dao.cadastrarProduto(p);
		return p;
	}

	@Override
	@Transactional
	public Produto atualizarProduto(Produto p) {
		if (dao.exibirProduto(p.getBarcode())==null) {

			String errMsg = message.productNotFound(p.getBarcode());

			throw new ProductNotFoundException(errMsg);
		}

		dao.atualizarProduto(p);
		return p;
	}

	@Override
	@Transactional
	public void removerProduto(Integer id) {
		if (dao.exibirProduto(id).equals(null)) {

			String errMsg = message.productNotFound(id);

			throw new ProductNotFoundException(errMsg);
		}

		dao.removerProduto(id);

	}

}

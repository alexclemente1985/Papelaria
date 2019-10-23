package com.alexandre.papelaria.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alexandre.papelaria.dao.ProdutoDAO;
import com.alexandre.papelaria.models.Produto;

@Repository("ProdutoDAO")
public class ProdutoDAOImpl implements ProdutoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listarProdutos() {
		Session session = sessionFactory.getCurrentSession();
		List<Produto> produtos = session.createQuery("from Produto").list();
		return produtos;
	}

	@Override
	public void cadastrarProduto(Produto p) {
		Session session = sessionFactory.getCurrentSession();
		session.save(p);

	}

	@Override
	public void removerProduto(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Produto p = session.load(Produto.class, id);

		if (p != null) {
			session.delete(p);
		}

	}

	@Override
	public void atualizarProduto(Produto p) {
		Session session = sessionFactory.getCurrentSession();
		session.clear();
		session.update(p);

	}

	@Override
	public Produto exibirProduto(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Produto p = session.get(Produto.class, id);
		return p;
	}

}
